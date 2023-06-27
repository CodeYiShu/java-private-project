package com.codeshu.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author CodeShu
 * @date 2023/6/23 15:55
 */
public class BaseCodeTest {
	private static int count = 1;

	private static AtomicInteger atomicIntegerCount = new AtomicInteger(1);

	private static int var = 1;

	@Test
	public void testThreadUnSafe() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				if (count > 0) {
					try {
						Thread.sleep(100L);
						count--;
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}).start();
		}
		Thread.sleep(500);
		System.out.println(count);
	}

	@Test
	public void testThreadSafe() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				synchronized (BaseCodeTest.class) {
					if (count > 0) {
						try {
							Thread.sleep(100L);
							count--;
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				}
			}).start();
		}
		Thread.sleep(500);
		System.out.println(count);
	}

	@Test
	public void testOtherThreadUnSafe() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			new Thread(()->{
				try {
					Thread.sleep(10L);
					count++;
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}).start();
		}

		Thread.sleep(1000L);
		System.out.println("最后的大小：" + count);
	}

	@Test
	public void testOtherThreadSafe() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			new Thread(()->{
				try {
					Thread.sleep(10L);
					atomicIntegerCount.incrementAndGet();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}).start();
		}

		Thread.sleep(1000L);
		System.out.println("最后的大小：" + atomicIntegerCount.get());
	}

	@Test
	public void testSynchronize() throws InterruptedException {
		new Thread(()->{  //线程1
			try {
				Thread.sleep(500);
				synchronized (BaseCodeTest.class) { //线程1使用ThreadImageTest对象锁
					//读取共享变量var
					int i = var;
					//修改共享变量var加1
					var = ++i;
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();

		new Thread(()->{  //线程2
			try {
				Thread.sleep(500);
				synchronized (BaseCodeTest.class) { //线程2使用ThreadImageTest对象锁
					//读取共享变量var
					int i = var;
					//修改共享变量var为减1
					var = --i;
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();

		Thread.sleep(2000);
		System.out.println(var);
	}
}
