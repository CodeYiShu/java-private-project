package com.codeshu.string;

import org.junit.Test;

/**
 * @author CodeShu
 * @date 2023/5/24 11:07
 */
@SuppressWarnings("all")
public class AppendStringTest {
	@Test
	public void test1() {
		//s1使用字面量创建的字符串对象，它是存储在字符串常量池当中的
		String s1 = "javaEEhadoop";
		//字面量拼接
		String s2 = "javaEE" + "hadoop";
		System.out.println(s1 == s2); //true
	}

	@Test
	public void test2() {
		final String s1 = "javaEE";  //使用final修饰
		final String s2 = "hadoop";  //使用final修饰
		String s3 = "javaEEhadoop";
		String s4 = s1 + s2;

		System.out.println(s3 == s4); //true
	}

	@Test
	public void test3(){
		String s1 = "javaEE";
		String s2 = "javaEEhadoop";
		String s3 = s1 + "hadoop";
		System.out.println(s2 == s3); //false
	}

	@Test
	public void test6(){
		String s1 = "javaEE";
		String s2 = "hadoop";
		String s3 = "javaEEhadoop";
		String s4 = s1 + s2;
		System.out.println(s3 == s4); //false
	}
}
