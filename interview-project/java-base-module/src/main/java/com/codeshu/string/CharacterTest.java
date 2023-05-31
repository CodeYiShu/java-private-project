package com.codeshu.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author CodeShu
 * @date 2023/5/25 13:56
 */
public class CharacterTest {
	@Test
	public void test1(){
		String str = "😊";
		char c1 = str.charAt(0);
		char c2 = str.charAt(1);
		System.out.println(str.length());
		System.out.println(str.codePointCount(1,str.length()));
	}

	@Test
	public void test2(){
		String str = "😊";
		int[] codePoints = str.codePoints().toArray();
		System.out.println(Arrays.toString(codePoints));
	}

	@Test
	public void test3(){
		boolean isCodePoint1 = Character.isSupplementaryCodePoint(97); //97代表a，是一个基本字符
		boolean isCodePoint2 = Character.isSupplementaryCodePoint(128522); //128522代表😊，是一个辅助字符
		System.out.println(isCodePoint1);
		System.out.println(isCodePoint2);
	}

	@Test
	public void test4(){
		String str = "😊a";
		char c1 = str.charAt(0);
		char c2 = str.charAt(1);
		char c3 = str.charAt(2);
		System.out.println(c1 + "是一个辅助字符：" + Character.isSurrogate(c1));
		System.out.println(c2 + "是一个辅助字符：" + Character.isSurrogate(c2));
		System.out.println(c3 + "是一个辅助字符：" + Character.isSurrogate(c3));
	}

	@Test
	public void test5(){
		char c1 = 'a';
		char c2 = 97;
		//char c2 = '97'; 报错，这是一个辅助字符
		System.out.println(c1);
		System.out.println(c2);
	}

	@Test
	public void test6(){
		char c1 = '1';
		char c2 = 1;
		System.out.println(c1);
		System.out.println(c2);
	}

	@Test
	public void test7(){
		byte b1 = -128;
		byte b2 = 127;
		byte b3 = 'a'; //97
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
	}
}
