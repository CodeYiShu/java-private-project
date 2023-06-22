package com.codeshu.proxy.simple2;

/**
 * @author CodeShu
 * @date 2023/6/21 22:02
 */
public class Client {
	public static void main(String[] args) {
		//创建目标对象
		UserServiceImpl userService = new UserServiceImpl();
		//创建代理对象，传入目标对象，让代理对象对此目标对象进行代理
		ProxyRecordLog proxy = new ProxyRecordLog(userService);
		//调用代理对象的同名方法
		proxy.coreMethod1();
		proxy.coreMethod2();
	}
}
