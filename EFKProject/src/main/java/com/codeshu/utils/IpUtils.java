
package com.codeshu.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 */
public class IpUtils {
	private static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

	private static final String HEADER1 = "x-forwarded-for";
	private static final String HEADER2 = "Proxy-Client-IP";
	private static final String HEADER3 = "WL-Proxy-Client-IP";
	private static final String HEADER4 = "HTTP_CLIENT_IP";
	private static final String HEADER5 = "HTTP_X_FORWARDED_FOR";
	private static final String UNKNOWN = "unknown";

	public static String getIpAddress(HttpServletRequest request) {
		String ip = null;
		try {
			ip = request.getHeader(HEADER1);
			if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader(HEADER2);
			}
			if (StringUtils.isEmpty(ip) || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader(HEADER3);
			}
			if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader(HEADER4);
			}
			if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getHeader(HEADER5);
			}
			if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} catch (Exception e) {
			logger.error("IpUtils ERROR ", e);
		}

		return ip;
	}
}
