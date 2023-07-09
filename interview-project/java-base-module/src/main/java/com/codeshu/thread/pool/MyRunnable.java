package com.codeshu.thread.pool;

import lombok.Data;

import java.util.Date;

/**
 * 这是一个简单的Runnable类，需要大约5秒钟来执行其任务。
 *
 * @author shuang.kou
 */
@Data
public class MyRunnable implements Runnable {

	private String command;

	public MyRunnable(String s) {
		this.command = s;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
	}
}
