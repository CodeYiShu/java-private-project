package com.codeshu.proxy.simple1;

/**
 * @author CodeShu
 * @date 2023/6/21 21:39
 */
public class RealStar implements Star{
	private String name;
	public RealStar(String name){
		this.name = name;
	}

	@Override
	public void sing() {
		System.out.println(name + "唱歌");
	}
}
