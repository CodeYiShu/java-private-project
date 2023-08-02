package com.codeshu.thread.more;

/**
 * @author CodeShu
 * @date 2023/7/31 12:39
 */
public class SingletonTest {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				Singleton singleton = Singleton.getSingleton();
				System.out.println(Thread.currentThread().getName() + "--->" + singleton);
			}).start();
		}
	}
}
