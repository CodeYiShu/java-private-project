package com.codeshu.aspect.test;

import org.springframework.stereotype.Component;

/**
 * @author CodeShu
 * @date 2023/6/7 17:27
 */
@Component
public class TestClass {
	public void testMethod() {
		System.out.println("我是代理方法");
	}
}
