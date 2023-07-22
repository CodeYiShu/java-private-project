package com.codeshu.annotation;

import org.springframework.stereotype.Component;

/**
 * @author CodeShu
 * @date 2023/7/19 16:44
 */
@Component(value = "accountDao")
public class AccountDaoImpl implements IAccountDao{
	@Override
	public void saveAccount() {
		System.out.println("AccountDaoImpl的saveAccount()把数据保存进数据库了");
	}
}
