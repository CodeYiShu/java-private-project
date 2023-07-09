package com.codeshu.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author CodeShu
 * @date 2023/7/1 19:26
 */
public class CallableRunnableTest {
	@Test
	public void callableTest() throws ExecutionException, InterruptedException {
		FutureTask<String> futureTask = new FutureTask<>(new CallableImpl());
		futureTask.run();
		String returnValue = futureTask.get();
		System.out.println("主线程获取子线程的返回结果" + returnValue);
	}

	@Test
	public void runnableTest() {
		Thread thread = new Thread(new RunnableImpl());
		thread.start();
	}


	static class CallableImpl implements Callable<String> {

		@Override
		public String call() throws Exception {
			Thread.sleep(10000);
			return "执行完成";
		}
	}

	static class RunnableImpl implements Runnable {

		@Override
		public void run() {
			System.out.println("执行任务");
		}
	}
}
