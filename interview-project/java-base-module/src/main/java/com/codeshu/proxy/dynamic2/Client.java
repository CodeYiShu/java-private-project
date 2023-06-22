package com.codeshu.proxy.dynamic2;

/**
 * @author CodeShu
 * @date 2023/6/22 16:35
 */
public class Client {
	public static void main(String[] args) {
		Producer producer = new Producer();
		Producer proxyProducer = (Producer) MyGetProxyInstance.getProxyInstance(producer);
		proxyProducer.saleProduct(1000f);
	}
}
