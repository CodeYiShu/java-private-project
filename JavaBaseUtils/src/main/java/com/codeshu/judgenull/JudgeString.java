package com.codeshu.judgenull;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CodeShu
 * @date 2023/1/17 11:46
 */
public class JudgeString {
	public static void main(String[] args) {
		System.out.println("--------------------------------");
		generate();
		System.out.println("--------------------------------");
		useStringUtils();
		System.out.println("--------------------------------");
		useStrUtil();
		System.out.println("--------------------------------");
		useSpringStringUtils();
	}

	/**
	 * 原生判断
	 */
	public static void generate() {
		String str1 = "abc";
		String str2 = "";
		String str3 = null;

		System.out.println(str1 != null && !str1.equals(""));
		System.out.println(str2 != null && !str2.equals(""));
		System.out.println(str3 != null && !str3.equals(""));
	}

	/**
	 * 使用StringUtils判断
	 * 需要引入commons-lang3依赖
	 */
	public static void useStringUtils() {
		String str1 = "abc";
		String str2 = "";
		String str3 = null;
		String str4 = null;

		System.out.println("StringUtils.isNotBlank(str1) = " + StringUtils.isNotBlank(str1));
		System.out.println("StringUtils.isNoneBlank(str2) = " + StringUtils.isNotBlank(str2));
		System.out.println("StringUtils.isNoneBlank(str3) = " + StringUtils.isNotBlank(str3));

		System.out.println("StringUtils.isBlank(str1) = " + StringUtils.isBlank(str1));
		System.out.println("StringUtils.isBlank(str2) = " + StringUtils.isBlank(str2));
		System.out.println("StringUtils.isBlank(str3) = " + StringUtils.isBlank(str3));

		System.out.println("StringUtils.isNoneBlank(str1) = " + StringUtils.isNoneBlank(str1));
		System.out.println("StringUtils.isNoneBlank(str1,str2) = " + StringUtils.isNoneBlank(str1, str2));
		System.out.println("StringUtils.isNoneBlank(str1,str3) = " + StringUtils.isNoneBlank(str1, str3));

		System.out.println("StringUtils.isAllBlank(str1,str2) = " + StringUtils.isAllBlank(str1, str2));
		System.out.println("StringUtils.isAllBlank(str2,str3) = " + StringUtils.isAllBlank(str2, str3));
		System.out.println("StringUtils.isAllBlank(str3,str4) = " + StringUtils.isAllBlank(str3, str4));

		System.out.println("StringUtils.isAnyBlank(str1,str2) = " + StringUtils.isAnyBlank(str1, str2));
		System.out.println("StringUtils.isAnyBlank(str1,str3) = " + StringUtils.isAnyBlank(str1, str3));
	}

	/**
	 * 使用StrUtil判断
	 * 需要引入Hutool依赖
	 */
	public static void useStrUtil() {
		String str1 = "abc";
		String str2 = "";
		String str3 = null;
		String str4 = null;

		System.out.println("StrUtil.isNotBlank(str1) = " + StrUtil.isNotBlank(str1));
		System.out.println("StrUtil.isNoneBlank(str2) = " + StrUtil.isNotBlank(str2));
		System.out.println("StrUtil.isNoneBlank(str3) = " + StrUtil.isNotBlank(str3));

		System.out.println("StrUtil.isBlank(str1) = " + StrUtil.isBlank(str1));
		System.out.println("StrUtil.isBlank(str2) = " + StrUtil.isBlank(str2));
		System.out.println("StrUtil.isBlank(str3) = " + StrUtil.isBlank(str3));

		System.out.println("StrUtil.isAllNotBlank(str1) = " + StrUtil.isAllNotBlank(str1));
		System.out.println("StrUtil.isAllNotBlank(str1,str2) = " + StrUtil.isAllNotBlank(str1, str2));
		System.out.println("StrUtil.isAllNotBlank(str1,str3) = " + StrUtil.isAllNotBlank(str1, str3));

		System.out.println("StrUtil.isAllBlank(str1,str2) = " + StrUtil.isAllBlank(str1, str2));
		System.out.println("StrUtil.isAllBlank(str2,str3) = " + StrUtil.isAllBlank(str2, str3));
		System.out.println("StrUtil.isAllBlank(str3,str4) = " + StrUtil.isAllBlank(str3, str4));

		System.out.println("StrUtil.hasBlank(str1,str2) = " + StrUtil.hasBlank(str1, str2));
		System.out.println("StrUtil.hasBlank(str1,str3) = " + StrUtil.hasBlank(str1, str3));
	}

	/**
	 * 使用Spring的StringUtils
	 * 需要引入spring-core依赖
	 */
	public static void useSpringStringUtils() {
		String str1 = "abc";
		String str2 = "";
		String str3 = null;
		String str4 = null;

		System.out.println("StringUtils.hasLength(str1) = " + org.springframework.util.StringUtils.hasLength(str1));
		System.out.println("StringUtils.hasLength(str2) = " + org.springframework.util.StringUtils.hasLength(str2));
		System.out.println("StringUtils.hasLength(str3) = " + org.springframework.util.StringUtils.hasLength(str3));
		System.out.println("StringUtils.hasLength(str4) = " + org.springframework.util.StringUtils.hasLength(str4));
		System.out.println("StringUtils.hasText(str1) = " + org.springframework.util.StringUtils.hasText(str1));
		System.out.println("StringUtils.hasText(str2) = " + org.springframework.util.StringUtils.hasText(str2));
		System.out.println("StringUtils.hasText(str3) = " + org.springframework.util.StringUtils.hasText(str3));
		System.out.println("StringUtils.hasText(str4) = " + org.springframework.util.StringUtils.hasText(str4));
	}
}
