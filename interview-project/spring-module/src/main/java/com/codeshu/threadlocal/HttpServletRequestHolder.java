package com.codeshu.threadlocal;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CodeShu
 * @date 2023/7/11 10:48
 */
public class HttpServletRequestHolder {
	private final static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

	public static void put(HttpServletRequest request) {
		threadLocal.set(request);
	}

	public static HttpServletRequest get() {
		return threadLocal.get();
	}

	public static void remove() {
		threadLocal.remove();
	}
}
