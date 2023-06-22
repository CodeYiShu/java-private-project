package com.codeshu.proxy.statically;

/**
 * @author CodeShu
 * @date 2023/6/22 12:52
 */
public class ProxyServer implements NetWork {
	//提供接口的引用
	NetWork netWork = null;

	//获取到目标类对象
	public ProxyServer(NetWork netWork) {
		this.netWork = netWork;
	}

	@Override
	public void browse() {
		check();
		//调用目标对象的方法
		netWork.browse();
		end();
	}

	//check()和end()是代理类的额外工作
	public void check() {System.out.println("连网之前的检查工作!");}

	public void end() {System.out.println("连网之后的断开工作");}
}
