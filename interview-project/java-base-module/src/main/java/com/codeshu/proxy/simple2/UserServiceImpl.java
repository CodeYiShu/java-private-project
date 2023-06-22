package com.codeshu.proxy.simple2;

/**
 * @author CodeShu
 * @date 2023/6/21 21:51
 */
public class UserServiceImpl implements UserService{
	@Override
	public void coreMethod1() {
		System.out.println("核心业务逻辑1");
	}

	@Override
	public void coreMethod2() {
		System.out.println("核心业务逻辑2");
	}
}
