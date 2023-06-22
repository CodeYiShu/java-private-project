package com.codeshu.proxy.dynamic2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author CodeShu
 * @date 2023/6/22 16:38
 */
public class MyGetProxyInstance {
	public static Object getProxyInstance(Object realObject) { //参数是目标对象，返回值是代理对象
		return Enhancer.create(realObject.getClass(), new MethodInterceptor() {
			//提供增强方法
			public void pre() {
				System.out.println("前置");
			}

			public void abo() {
				System.out.println("后置");
			}

			@Override
			public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
				Object returnValue = null;
				pre();
				returnValue = method.invoke(realObject, objects);
				abo();
				return returnValue;
			}
		});
	}
}
