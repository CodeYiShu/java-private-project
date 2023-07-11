package com.codeshu.thread.semaphore;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CodeShu
 * @date 2023/7/10 15:18
 */
public class NoSemaphoreTest {
	private static final int CORE_POOL_SIZE = 10;   //核心线程数
	private static final int MAX_POOL_SIZE = 10;    //运行的最大线程数量
	private static final int QUEUE_CAPACITY = 100;  //任务队列大小
	private static final Long KEEP_ALIVE_TIME = 1L; //等待的时间超过了 keepAliveTime 回收大于 corePoolSize 的线程

	public static void main(String[] args) throws InterruptedException {
		//创建线程池
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_CAPACITY), new ThreadPoolExecutor.CallerRunsPolicy());

		//一共 10 张表
		int tableCount = 10;
		//创建 CountDownLatch 类，指定 latch 值为 10，也就是有 10 个工作线程
		CountDownLatch countDownLatch = new CountDownLatch(tableCount);

		//循环处理每张表，循环一次分配 1 个线程
		for (int i = 1; i <= tableCount; i++) {
			//将 CountDownLatch 传入和操作表号给工作线程
			ThreadTask threadTask = new ThreadTask(countDownLatch, i);
			//从线程池分配 1 个线程去完成任务
			threadPoolExecutor.submit(threadTask);
		}

		//主线程等待所有工作线程完成任务，再继续往下执行
		countDownLatch.await();
		//主线程打印完成任务的日志
		Thread.currentThread().setName("主线程");
		System.out.println(Thread.currentThread().getName() + "主线程打印完成任务的日志");
	}

	static class ThreadTask implements Runnable {
		//父线程传递的countDownLatch，当工作线程完成任务之后让latch-1
		CountDownLatch countDownLatch;
		//父线程交给工作线程操作的数据
		int table;

		public ThreadTask(CountDownLatch countDownLatch, int table) {
			//父线程传入countDownLatch
			this.countDownLatch = countDownLatch;
			//父线程传入工作线程要操作的表
			this.table = table;
		}

		@Override
		public void run() {
			Thread.currentThread().setName("线程" + table);
			//工作线程的任务
			System.out.println(Thread.currentThread().getName() + "：操作表" + table);
			//任务完成
			countDownLatch.countDown();
		}
	}
}
