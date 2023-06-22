package com.codeshu.proxy.dynamic2;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author CodeShu
 * @date 2023/6/22 16:33
 */
public class MyCallback implements MethodInterceptor {
	Object realObject = null;

	public MyCallback(Object realObject) { //和目标对象绑定
		this.realObject = realObject;
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		pre();
		Object returnValue = method.invoke(realObject, objects);
		abo();
		return returnValue;
	}

	//提供增强方法
	public void pre() {
		System.out.println("前置");
	}

	public void abo() {
		System.out.println("后置");
	}
}
