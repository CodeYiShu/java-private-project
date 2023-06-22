package com.codeshu.proxy.simple2;

/**
 * @author CodeShu
 * @date 2023/6/21 21:58
 */
public class ProxyRecordLog implements UserService {
	private UserService userService;

	public ProxyRecordLog(UserService userService) {
		this.userService = userService;
	}


	@Override
	public void coreMethod1() {
		//前置增强
		beforeLog();
		//调用目标对象的同名方法
		userService.coreMethod1();
		//后置增强
		afterLog();
	}

	@Override
	public void coreMethod2() {
		//前置增强
		beforeLog();
		//调用目标对象的同名方法
		userService.coreMethod2();
		//后置增强
		afterLog();
	}

	public void beforeLog() {
		System.out.println("前记录日志");
	}

	public void afterLog() {
		System.out.println("后记录日志");
	}
}
