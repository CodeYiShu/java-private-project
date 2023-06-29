package com.codeshu.thread;

import org.junit.Test;

/**
 * @author CodeShu
 * @date 2023/6/28 16:54
 */
public class StateTest {
	static volatile boolean running = true;

	@Test
	public void testSleep() throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (running) {
						System.out.println("t1 running is false,将sleep");
						Thread.sleep(3000L);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		System.out.println("new t1 t1的状态：" + t1.getState());

		t1.start();

		Thread.sleep(2000L);
		running = false;
		Thread.sleep(2000L);
		System.out.println("t1.sleep()时的状态：" + t1.getState());
	}

	@Test
	public void testJoin() throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					Thread.sleep(10000L); //睡眠10s
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					System.out.println("t2中执行t1.join(5000L)");
					t1.join(5000L); //t2等待t1 5s
					System.out.println("t2中执行t1.join()");
					t1.join(); //t2等待t1执行完
					System.out.println("t2执行完");
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

		Thread.sleep(1000L);
		System.out.println("t2的状态：" + t2.getState());
		Thread.sleep(5000L);
		System.out.println("t2的状态：" + t2.getState());
	}
}
