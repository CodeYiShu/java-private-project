package com.codeshu.string;

import org.junit.Test;

/**
 * @author CodeShu
 * @date 2023/5/24 11:40
 */
@SuppressWarnings("all")
public class InternTest {
	@Test
	public void test1() {
		//变量和变量的拼接，创建StringBuilder进行拼接再转为堆中的String对象
		String str = new String("str") + new String("01");
		//常量池是否存在元素和str1一样的字符串对象，没有则创建一个，指向str1所指向的字符串对象
		String internStr = str.intern();
		System.out.println(str == internStr); //true
	}

	@Test
	public void test2(){
		//变量和变量的拼接，创建StringBuilder进行拼接再转为堆中的String对象
		String str1 = new String("str") + new String("01");
		//常量池是否存在元素和str1一样的字符串对象，没有则创建一个，指向str1所指向的字符串对象
		str1.intern();
		//字面量创建str2，先去常量池寻找是否有元素为"str01"的字符串对象
		String str2 = "str01";

		System.out.println(str2==str1);  //true
	}

	@Test
	public void test3(){
		//变量和变量的拼接，创建StringBuilder进行拼接再转为堆中的String对象
		String str1 = new String("str") + new String("01");
		//字面量创建str2，先去常量池寻找是否有元素为"str01"的字符串对象
		String str2 = "str01";
		//常量池是否存在元素和str1一样的字符串对象，没有则创建一个，指向str1所指向的字符串对象
		str1.intern();

		System.out.println(str2==str1);  //true
	}
}
