package com.codeshu.ioc;

import org.springframework.stereotype.Component;

/**
 * @author CodeShu
 * @date 2023/7/19 10:28
 */
public class HelloBean {
	private String str;

	public HelloBean() {
		System.out.println("HelloBean 被创建");
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
