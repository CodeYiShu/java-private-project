package com.codeshu.thread.threadllocal;

/**
 * @author CodeShu
 * @date 2023/7/11 9:05
 */
public class ContentThreadLocal {
	//创建ThreadLocal对象
	private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static void setContent(String content) {
		//每个线程调用此方法时，将变量 content 绑定到当前线程上
		threadLocal.set(content);
	}

	public static String getContent() {
		//每个线程调用此方法时，将绑定到当前线程上的变量 content 获取出来
		return threadLocal.get();
	}
}
