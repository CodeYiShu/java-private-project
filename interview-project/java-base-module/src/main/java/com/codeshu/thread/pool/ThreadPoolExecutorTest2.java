package com.codeshu.thread.pool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 同 ThreadPoolExecutorTest，这里只是巩固
 *
 * @author CodeShu
 * @date 2023/7/31 13:31
 */
@SuppressWarnings("all")
public class ThreadPoolExecutorTest2 {
	@Test
	public void test() throws ExecutionException, InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				5,
				7,
				10,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(100),
				new ThreadPoolExecutor.AbortPolicy());

		Future<String> future = executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "return value";
			}
		});

		System.out.println(future.get());
	}
}
