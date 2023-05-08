package com.codeshu.aspect;

import com.codeshu.annotation.DynamicsDataSourceAnnotation;
import com.codeshu.config.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源切面类
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataSourceAspect {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("@annotation(com.codeshu.annotation.DynamicsDataSourceAnnotation) " +
			"|| @within(com.codeshu.annotation.DynamicsDataSourceAnnotation)")
	public void dynamicsDataSourcePointCut() {
	}

	@Around("dynamicsDataSourcePointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		//标注了 @DynamicDataSource 的方法或类
		MethodSignature signature = (MethodSignature) point.getSignature();
		Class<?> targetClass = point.getTarget().getClass();
		Method method = signature.getMethod();
		DynamicsDataSourceAnnotation targetDataSource = targetClass.getAnnotation(DynamicsDataSourceAnnotation.class);
		DynamicsDataSourceAnnotation methodDataSource = method.getAnnotation(DynamicsDataSourceAnnotation.class);

		if (targetDataSource != null || methodDataSource != null) {
			//获取出注解的 value 值，表示选择的数据源名称
			String value;
			if (methodDataSource != null) {
				value = methodDataSource.value();
			} else {
				value = targetDataSource.value();
			}

			//将选择的数据源名称，绑定到当前线程的本地变量上
			DynamicDataSourceContextHolder.push(value);
			logger.debug("设置数据源为 {}", value);
		}

		try {
			//放行执行方法
			return point.proceed();
		} finally {
			//删除本地变量上
			DynamicDataSourceContextHolder.poll();
			logger.debug("清理数据源");
		}
	}
}
