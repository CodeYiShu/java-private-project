package com.codeshu.proxy.statically;

/**
 * @author CodeShu
 * @date 2023/6/22 12:54
 */
public class Client {
	public static void main(String[] args) {
		//提供目标对象
		Server server = new Server();
		//目标对象传入到代理类构造器作为实参
		ProxyServer proxyServer = new ProxyServer(server);
		//通过代理类对象去调用方法，实际上真正调用者是目标对象server
		proxyServer.browse();
	}
}
