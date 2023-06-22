package com.codeshu.proxy.simple1;

/**
 * @author CodeShu
 * @date 2023/6/21 21:40
 */
public class ProxyStar implements Star{
	//提供抽象角色的引用，实际指向的是目标对象
	private Star star = null;
	//传入目标对象，赋值给成员
	public ProxyStar(Star star){
		this.star = star;
	}


	@Override
	public void sing() {
		//前增强
		System.out.println("经纪人面谈");
		System.out.println("经纪人签合同");
		System.out.println("经纪人购票");
		//调用目标对象的方法
		star.sing();
		//后增强
		System.out.println("经纪人收钱");
	}
}
