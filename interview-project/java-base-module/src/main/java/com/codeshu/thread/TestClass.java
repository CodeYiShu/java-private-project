package com.codeshu.thread;

import cn.hutool.core.thread.ThreadUtil;

import java.util.Objects;

/**
 * @author CodeShu
 * @date 2023/6/29 16:34
 */
public class TestClass {
	private static final Object obj = new Object();
	private static int i = 1;
	public void method() {
		synchronized (obj) {
			//操作i
			if (i > 0) {
				ThreadUtil.sleep(500);
				i--;
			}
		}
	}

	public static void main(String[] args) {
		new Thread(()->{
			TestClass testClass = new TestClass();
			testClass.method();
		}).start();

		new Thread(()->{
			TestClass testClass = new TestClass();
			testClass.method();
		}).start();

		ThreadUtil.sleep(1000);
		System.out.println(i);
	}
}
