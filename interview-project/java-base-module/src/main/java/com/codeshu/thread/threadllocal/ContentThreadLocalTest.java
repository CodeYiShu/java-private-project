package com.codeshu.thread.threadllocal;

/**
 * @author CodeShu
 * @date 2023/7/11 9:11
 */
public class ContentThreadLocalTest {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					//为当前线程设置一个 Content 本地变量
					ContentThreadLocal.setContent(Thread.currentThread().getName() + "的数据");
					System.out.println("------------------");
					//当前线程获取自己的 Content 本地变量
					System.out.println(Thread.currentThread().getName() + "--->" + ContentThreadLocal.getContent());
				}
			});
			thread.setName("线程" + i);
			thread.start();
		}
	}
}
