package com.codeshu.thread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CodeShu
 * @date 2023/7/1 19:37
 */
public class ExecuteSubmitTest {
	@Test
	public void executeSubmitTest() throws ExecutionException, InterruptedException {
		//创建线程池对象
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.CallerRunsPolicy());

		//从线程池分配1个线程去执行Runnable任务，没有返回值
		executor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("执行Runnable任务");
			}
		});

		//从线程池分配1个线程去执行Callable任务，有返回值
		Future<String> future = executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("执行Callable任务");
				return "Callable任务完成";
			}
		});
		System.out.println(future.get()); //获取返回值
	}
}
