package com.codeshu.thread;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 双重校验锁单例模式
 *
 * @author CodeShu
 * @date 2023/6/29 15:46
 */
public class Singleton {
	private volatile static Singleton instance = null;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					ThreadUtil.sleep(500);
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			executorService.execute(() -> {
				System.out.println(Singleton.getInstance());
			});
		}
	}
}
