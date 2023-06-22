package com.codeshu.proxy.dynamic1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author CodeShu
 * @date 2023/6/22 14:50
 */
public class Client2 {
	public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		//创建目标对象
		Server server = new Server();
		//传入目标对象，创建 InvocationHandler 实现类对象
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler(server);
		//传入目标对象的类加载器、实现的接口和InvocationHandler，创建出代理对象
		NetWork proxyObject = (NetWork) Proxy.newProxyInstance(server.getClass().getClassLoader(), server.getClass().getInterfaces(), myInvocationHandler);
		//调用代理对象的方法
		proxyObject.browse();
	}
}
