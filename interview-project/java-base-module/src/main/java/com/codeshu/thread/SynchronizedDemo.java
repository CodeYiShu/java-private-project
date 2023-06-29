package com.codeshu.thread;

/**
 * @author CodeShu
 * @date 2023/6/29 17:04
 */
public class SynchronizedDemo {
	public void method() {
		synchronized (this) {
			System.out.println("synchronized 代码块");
		}
	}
}
