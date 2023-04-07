package com.codeshu.config;

import cn.hutool.json.JSONUtil;
import com.codeshu.utils.IpUtils;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 请求过滤器
 *
 * @author CodeShu
 * @date 2023/4/7 11:32
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestLoggingFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String ipAddress = IpUtils.getIpAddress(httpRequest);
		String requestUri = httpRequest.getRequestURI();
		String requestMethod = httpRequest.getMethod();
		Map<String, String[]> parameterMap = httpRequest.getParameterMap();

		try {
			MDC.put("client.ip", ipAddress);
			MDC.put("client.requestUri", requestUri);
			MDC.put("client.requestMethod", requestMethod);
			MDC.put("client.requestParams", JSONUtil.toJsonStr(parameterMap));
			filterChain.doFilter(request, response);
		} finally {
			MDC.remove("client.ip");
			MDC.remove("client.requestUri");
			MDC.remove("client.requestMethod");
			MDC.remove("client.requestParams");
		}
	}
}
