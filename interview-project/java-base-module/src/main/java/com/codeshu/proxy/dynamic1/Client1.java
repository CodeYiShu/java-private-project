package com.codeshu.proxy.dynamic1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author CodeShu
 * @date 2023/6/22 14:50
 */
public class Client1 {
	public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		//创建目标对象
		Server server = new Server();
		//传入目标对象，创建 InvocationHandler 实现类对象
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler(server);
		//根据目标对象的类加载器、目标对象所实现的接口，动态创建代理类
		Class<?> proxyClass = Proxy.getProxyClass(server.getClass().getClassLoader(), server.getClass().getInterfaces());
		//获取代理类的构造器
		Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
		//根据构造器创建出代理对象
		NetWork proxyObject = (NetWork) constructor.newInstance(myInvocationHandler);
		//调用代理对象的方法
		proxyObject.browse();
	}
}
