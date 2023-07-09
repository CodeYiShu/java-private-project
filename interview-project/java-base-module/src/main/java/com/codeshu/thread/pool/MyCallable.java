package com.codeshu.thread.pool;

import lombok.Data;

import java.util.concurrent.Callable;

/**
 * 这是一个简单的Callable类，需要大约5秒钟来执行其任务。
 *
 * @author shuang.kou
 */
@Data
public class MyCallable implements Callable<String> {

	@Override
	public String call() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//返回执行当前 Callable 的线程名字
		return Thread.currentThread().getName();
	}
}
