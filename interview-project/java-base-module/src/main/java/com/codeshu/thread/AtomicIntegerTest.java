package com.codeshu.thread;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author CodeShu
 * @date 2023/7/8 16:30
 */
public class AtomicIntegerTest {
	private static final AtomicInteger count = new AtomicInteger(1);
	//public static int count = 1;

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				ThreadUtil.sleep(10);
				System.out.println(count.incrementAndGet());
			}).start();
		}

		Thread.sleep(1000L);
		System.out.println("最后的大小：" + count);
	}
}
