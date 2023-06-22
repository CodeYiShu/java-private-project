package com.codeshu.proxy.statically;

/**
 * @author CodeShu
 * @date 2023/6/22 12:52
 */
//提供目标类（被代理类）
class Server implements NetWork {
	//目标类去重写接口里的方法，这是目标类的真正业务逻辑方法
	public void browse() {
		System.out.println("访问服务器");
	}
}
