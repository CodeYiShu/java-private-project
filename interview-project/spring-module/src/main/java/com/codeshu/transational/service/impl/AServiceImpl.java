package com.codeshu.transational.service.impl;

import com.codeshu.transational.entity.AccountEntity;
import com.codeshu.transational.mapper.AccountMapper;
import com.codeshu.transational.service.AService;
import com.codeshu.transational.service.BService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author CodeShu
 * @date 2023/6/8 15:27
 */
@Service
public class AServiceImpl implements AService {
	@Resource
	private AccountMapper accountMapper;

	@Autowired
	private BService bService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void aMethod() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(1L);
		accountEntity.setMoney(BigDecimal.valueOf(900));

		//做更新
		accountMapper.update(accountEntity);

		bService.bMethod();

		//发生异常
		int i = 1 / 0;
	}
}
