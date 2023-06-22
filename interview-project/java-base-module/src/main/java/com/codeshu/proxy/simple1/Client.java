package com.codeshu.proxy.simple1;

/**
 * @author CodeShu
 * @date 2023/6/21 21:43
 */
public class Client {
	public static void main(String[] args) {
		//创建目标对象
		Star realStar = new RealStar("周杰伦");
		//创建代理对象，传入目标对象
		ProxyStar proxy = new ProxyStar(realStar);
		//调用代理对象的方法
		proxy.sing();
	}
}
