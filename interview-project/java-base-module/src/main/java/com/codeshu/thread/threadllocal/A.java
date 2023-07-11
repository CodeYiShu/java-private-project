package com.codeshu.thread.threadllocal;

/**
 * @author CodeShu
 * @date 2023/7/10 17:03
 */
//A类
public class A {
	//变量
	public String var;

	static class TestA {
		public static void main(String[] args) {

			A a = new A();
			//创建 5 个线程
			for (int i = 0; i < 5; i++) {
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						//设置成员变量 var 为当前线程的名字+的数据
						a.var = Thread.currentThread().getName() + "的数据";
						System.out.println("------------------");
						//输出成员变量 var 的数据
						System.out.println(a.var);
					}
				});
				thread.start();
			}
		}
	}
}


