package com.codeshu.thread;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.Test;

/**
 * @author CodeShu
 * @date 2023/6/30 10:35
 */
public class BaseMethodTest {
	@Test
	public void testYield() throws InterruptedException {
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				if (i % 2 == 0) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
				//如果i能够被20整除，那么就释放此时的CPU执行权，让CPU重新选择
				if (i % 20 == 0) {
					Thread.currentThread().yield();
				}
			}
		}).start();
	}

	@Test
	public void testSleep() {
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				if (i % 2 == 0) {
					//当前线程不准排队1秒
					try {
						//必须进行异常处理，否则编译不通过，
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
			}
		}).start();
	}

	@Test
	public void testJoin() throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				if (i % 2 == 0) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
			}
		});

		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				if (i % 2 == 0) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
				//当i等于20的时候，将当前线程阻塞，让调用了join方法的线程执行完，再继续执行当前线程
				if (i == 20) {
					try {
						thread1.join();
						System.out.println(thread1.getState());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
	}

	@Test
	public void testWaitNotify() {
		//线程1
		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				//在同步代码块中，同步监视器为BaseMethodTest.class对象
				synchronized (BaseMethodTest.class) {
					System.out.println("线程1的i：" + i);
					if (i == 10) {
						try {
							//i为10时，进入阻塞状态，等待被唤醒
							BaseMethodTest.class.wait();
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		});

		//线程2
		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				//在同步代码块中，同步监视器为BaseMethodTest.class对象
				synchronized (BaseMethodTest.class) {
					System.out.println("线程2的i：" + i);
					if (i == 10) {
						//i为10时，唤醒正在阻塞的线程1
						BaseMethodTest.class.notify();
					}
				}
			}
		});

		thread1.start();
		thread2.start();

		ThreadUtil.sleep(1000);
	}
}
