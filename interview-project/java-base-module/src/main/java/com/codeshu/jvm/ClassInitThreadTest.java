package com.codeshu.jvm;

/**
 * @author CodeShu
 * @date 2023/5/31 16:54
 */
public class ClassInitThreadTest {
	public static void main(String[] args) {
		Runnable r = ()->{
			System.out.println(Thread.currentThread().getName() + "开始");
			DeadThread deadThread = new DeadThread();
			System.out.println(Thread.currentThread().getName() + "结束");
		};
		Thread t1 = new Thread(r,"线程1");
		Thread t2 = new Thread(r,"线程2");

		t1.start();
		t2.start();
	}
}
