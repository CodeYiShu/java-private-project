package com.codeshu.proxy.dynamic1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author CodeShu
 * @date 2023/6/22 14:46
 */
public class MyInvocationHandler implements InvocationHandler {
	private NetWork netWork;

	public MyInvocationHandler(NetWork netWork) {
		this.netWork = netWork;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("前增强");
		Object returnValue = method.invoke(netWork, args);
		System.out.println("后增强");
		return returnValue;
	}
}
