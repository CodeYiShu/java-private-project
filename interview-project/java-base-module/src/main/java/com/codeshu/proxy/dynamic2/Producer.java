package com.codeshu.proxy.dynamic2;

/**
 * @author CodeShu
 * @date 2023/6/22 16:33
 */
public class Producer {
	public void saleProduct(float money) {
		System.out.println("销售产品，并且拿到钱" + money);
	}

	public void afterService(float money) {
		System.out.println("提供售后服务，并且拿到钱" + money);
	}
}
