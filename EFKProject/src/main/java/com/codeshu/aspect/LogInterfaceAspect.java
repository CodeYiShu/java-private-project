package com.codeshu.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.codeshu.annotation.LogInterface;
import com.codeshu.utils.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一日志处理切面
 */
@Aspect
@Component
@Order(1)
public class LogInterfaceAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogInterfaceAspect.class);

	/**
	 * 拦截所有添加了@LogInterface的方法
	 * <p>
	 * 若要拦截所有接口不用注解，则用下面的切入表达式
	 * "execution(public * com.codeshu.controller.*.*(..))"
	 */
	@Pointcut("@annotation(com.codeshu.annotation.LogInterface)")
	public void webLog() {
	}

	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		//获取当前请求对象
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		WebLog webLog = new WebLog();

		//执行方法
		Object result = joinPoint.proceed();

		//获取执行的方法上的注解信息
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		LogInterface logInterface = method.getAnnotation(LogInterface.class);
		webLog.setDescription(logInterface.value());
		webLog.setInterfaceInfo(method.getDeclaringClass().getName() + ": " + method.getName());

		long endTime = System.currentTimeMillis();

		//其他信息
		String urlStr = request.getRequestURL().toString();
		webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
		webLog.setIp(IpUtils.getIpAddress(request));
		webLog.setMethod(request.getMethod());
		webLog.setParameter(getParameter(method, joinPoint.getArgs()));
		webLog.setResult(result);
		webLog.setSpendTime((int) (endTime - startTime));
		webLog.setStartTime(startTime);
		webLog.setUri(request.getRequestURI());
		webLog.setUrl(request.getRequestURL().toString());

		LOGGER.info("{}", JSONUtil.parse(webLog));
		return result;
	}

	/**
	 * 根据方法和传入的参数获取请求参数
	 */
	private Object getParameter(Method method, Object[] args) {
		List<Object> argList = new ArrayList<>();
		Parameter[] parameters = method.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			//将RequestBody注解修饰的参数作为请求参数
			RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
			if (requestBody != null) {
				argList.add(args[i]);
			}
			//将RequestParam注解修饰的参数作为请求参数
			RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
			if (requestParam != null) {
				Map<String, Object> map = new HashMap<>();
				String key = parameters[i].getName();
				if (!StringUtils.isBlank(requestParam.value())) {
					key = requestParam.value();
				}
				map.put(key, args[i]);
				argList.add(map);
			}
		}
		if (argList.size() == 0) {
			return null;
		} else if (argList.size() == 1) {
			return argList.get(0);
		} else {
			return argList;
		}
	}
}
