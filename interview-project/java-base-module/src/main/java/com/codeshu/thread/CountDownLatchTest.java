package com.codeshu.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author CodeShu
 * @date 2023/7/10 14:44
 */
public class CountDownLatchTest {
	public static void main(String[] args) throws InterruptedException {
		//创建CountDownLatch类，指定latch值为2，也就是有2个工作线程
		CountDownLatch countDownLatch = new CountDownLatch(2);

		ThreadTask threadTask = new ThreadTask(countDownLatch);
		//创建两个工作线程
		Thread thread1 = new Thread(threadTask);
		Thread thread2 = new Thread(threadTask);
		//启动工作线程去完成任务
		thread1.start();
		thread2.start();

		//主线程等待所有工作线程完成任务，再继续往下执行
		countDownLatch.await();

		//主线程打印完成任务的日志
		Thread.currentThread().setName("主线程");
		System.out.println(Thread.currentThread().getName() + "打印完成任务的日志");
	}


	static class ThreadTask implements Runnable {
		int i = 1;
		CountDownLatch countDownLatch;

		public ThreadTask(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			Thread.currentThread().setName("线程" + i++);
			System.out.println(Thread.currentThread().getName() + "执行任务");
			countDownLatch.countDown(); //任务完成
		}
	}
}
