package com.codeshu.thread.more;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.Test;

/**
 * 线程的常用方法
 *
 * @author CodeShu
 * @date 2023/7/31 14:19
 */
@SuppressWarnings("all")
public class ThreadMethod {
	@Test
	public void testYield() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					if (i % 2 == 0) {
						System.out.println(Thread.currentThread().getName() + ":" + i);
					}
					//如果i能够被20整除，那么就释放此时的CPU执行权，让CPU重新选择
					if (i % 20 == 0) {
						Thread.yield();
					}
				}
			}
		}).start();

		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + ":" + i);
			}
			//如果i能够被20整除，那么就释放此时的CPU执行权，让CPU重新选择
			if (i % 20 == 1) {
				Thread.yield();
			}
		}
	}

	@Test
	public void testJoin() {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					if (i % 2 == 0) {
						System.out.println(Thread.currentThread().getName() + ":" + i);
					}
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					if (i % 2 == 0) {
						System.out.println(Thread.currentThread().getName() + ":" + i);
					}

					if (i == 20) {
						//当i等于20的时候，将当前线程阻塞，让调用了join方法的线程执行完，再继续执行当前线程
						try {
							thread1.join();
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		});

		thread1.start();
		thread2.start();

		ThreadUtil.sleep(500);
	}

	@Test
	public void testWaitNotify() {
		//线程1
		new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				//在同步代码块中，同步监视器为 ThreadMethod.class 对象
				synchronized (ThreadMethod.class) {
					System.out.println("线程1的i：" + i);
					if (i == 10) {
						try {
							//i 为 10 时，进入阻塞状态，等待被唤醒
							ThreadMethod.class.wait();
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		}).start();

		//线程2
		new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				//在同步代码块中，同步监视器为 ThreadMethod.class 对象
				synchronized (ThreadMethod.class) {
					System.out.println("线程2的i：" + i);
					if (i == 10) {
						//i 为 10 时，唤醒正在阻塞的线程 1
						ThreadMethod.class.notify();
					}
				}
			}
		}).start();

		ThreadUtil.sleep(500);
	}


}
