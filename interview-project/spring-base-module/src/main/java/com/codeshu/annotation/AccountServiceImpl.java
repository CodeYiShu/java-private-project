package com.codeshu.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CodeShu
 * @date 2023/7/19 16:45
 */
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private IAccountDao accountDao;

	@Override
	public void saveAccount() {
		accountDao.saveAccount();
	}
}
