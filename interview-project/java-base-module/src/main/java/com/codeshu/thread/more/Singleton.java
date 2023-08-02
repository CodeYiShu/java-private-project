package com.codeshu.thread.more;

import cn.hutool.core.thread.ThreadUtil;

/**
 * 双重校验锁单例模式
 * @author CodeShu
 * @date 2023/7/31 12:38
 */
public class Singleton {
	private static volatile Singleton singleton;

	private Singleton(){}

	public static Singleton getSingleton() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
