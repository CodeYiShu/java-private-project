package com.codeshu.thread.pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CodeShu
 * @date 2023/7/9 14:12
 */
public class ThreadPoolExecutorTest {
	private static final int CORE_POOL_SIZE = 5; //核心线程数
	private static final int MAX_POOL_SIZE = 10; //运行的最大线程数量
	private static final int QUEUE_CAPACITY = 100; //任务队列大小
	private static final Long KEEP_ALIVE_TIME = 1L; //等待的时间超过了 keepAliveTime 回收大于 corePoolSize 的线程

	@Test
	public void testRunnable() {
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

	@Test
	public void testCallable() {
		//1、创建线程池
		ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
				MAX_POOL_SIZE,
				KEEP_ALIVE_TIME,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(QUEUE_CAPACITY),
				new ThreadPoolExecutor.CallerRunsPolicy());

		List<Future<String>> futureList = new ArrayList<>();
		//2、创建任务对象
		Callable<String> callable = new MyCallable();

		for (int i = 0; i < 10; i++) {
			//3、从线程池中获取一个线程，来处理此任务
			Future<String> future = executor.submit(callable);
			//4、获取此任务的 FutureTask 对象
			futureList.add(future);
		}

		//5、从 FutureTask 中获取各个任务的返回值
		for (Future<String> future : futureList) {
			try {
				System.out.println(new Date() + "::" + future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		//6、关闭线程池
		executor.shutdown();
	}
}
