package com.codeshu.string;

import org.junit.Test;

/**
 * @author CodeShu
 * @date 2023/5/23 11:04
 */
@SuppressWarnings("all")
public class CreateStringTest {

	@Test
	public void create1() {
		String str1 = "hello";
		String str2 = "hello";
		System.out.println(str1 == str2);
	}

	@Test
	public void test2() {
		String str1 = new String("hello");
		String str2 = new String("hello");
		System.out.println(str1 == str2);
	}

	@Test
	public void test3() {
		//s1和s2使用字面量创建的字符串对象"javaEE"，它是存储在字符串常量池当中的
		String s1 = "javaEE";
		String s2 = "javaEE";

		//s3和s2使用String参数构造器创建的字符串对象，传入一个字符串对象"javaEE"
		String s3 = new String("javaEE");
		String s4 = new String("javaEE");

		System.out.println(s1 == s2);  //true
		System.out.println(s1 == s3);  //false
		System.out.println(s3 == s4);  //false
	}

	@Test
	public void test4(){
		char[] value = {'h','e','l','l','o'};
		String str1 = new String(value);  //将整个字符数组转为字符串
		String str2 = new String(value, 1, 3);  //将字符数组从索引1开始的3个字符，转为字符串
		System.out.println(str1);
		System.out.println(str2);
	}
}


