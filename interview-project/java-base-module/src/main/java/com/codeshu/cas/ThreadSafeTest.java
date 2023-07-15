package com.codeshu.cas;

/**
 * @author CodeShu
 * @date 2023/7/11 11:10
 */
public class ThreadSafeTest {
	//静态变量count
	public static int count = 0;

	public static void main(String[] args) throws InterruptedException {
		//开启两个线程
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//让每个线程去让count值自增100次
				for (int j = 0; j < 100; j++) {
					//count = count + 1;
					synchronized (ThreadSafeTest.class) {
						count++;
					}
				}
			}).start();
		}

		Thread.sleep(2000);  //让主线程睡眠2秒，防止子线程没有执行完就打印出count
		System.out.println("count = " + count);
	}
}
