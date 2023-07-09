package com.codeshu.thread;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author CodeShu
 * @date 2023/7/8 15:10
 */
public class ThreadPoolExecutorTest {
	private static final int CORE_POOL_SIZE = 5; //核心线程数
	private static final int MAX_POOL_SIZE = 10; //运行的最大线程数量
	private static final int QUEUE_CAPACITY = 100; //任务队列大小
	private static final Long KEEP_ALIVE_TIME = 1L; //等待的时间超过了 keepAliveTime 回收大于 corePoolSize 的线程

	@Test
	public void testDemo() {
		//1、创建线程池
		ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
				MAX_POOL_SIZE,
				KEEP_ALIVE_TIME,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(QUEUE_CAPACITY),
				new ThreadPoolExecutor.CallerRunsPolicy());

		for (int i = 0; i < 10; i++) {
			//2、创建任务对象
			MyRunnable myRunnable = new MyRunnable("" + i);
			//3、从线程池中获取一个线程，来处理此任务
			executor.execute(myRunnable);
		}
		//终止线程池
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}

	static class MyRunnable implements Runnable {

		private final String command;

		public MyRunnable(String s) {
			this.command = s;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
			processCommand();
			System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
		}

		private void processCommand() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return this.command;
		}
	}
}
