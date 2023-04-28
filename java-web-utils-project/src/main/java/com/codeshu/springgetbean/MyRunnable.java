package com.codeshu.springgetbean;

/**
 * 线程任务没有交给Spring管理，可以通过工具类获取容器中的Bean
 * @author CodeShu
 * @date 2022/11/18 20:53
 */
public class MyRunnable implements Runnable {
	private MyService myService = SpringApplicationContextHolder.getBeanByClass(MyService.class);

	@Override
	public void run() {
		myService.sayHello();
	}
}
