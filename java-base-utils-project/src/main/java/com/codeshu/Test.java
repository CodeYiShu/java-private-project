package com.codeshu;

import java.text.MessageFormat;

/**
 * @author CodeShu
 * @date 2023/1/8 20:54
 */
public class Test {
	private static final StringBuilder url = new StringBuilder("http://xx:8602/danger/securityrisk/id={id}");

	public static void main(String[] args) {
		String s = url.toString();
		String format = MessageFormat.format(s, "127.0.0.1");
		System.out.println(format);
	}
}
