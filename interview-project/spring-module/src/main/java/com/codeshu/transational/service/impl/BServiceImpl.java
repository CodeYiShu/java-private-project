package com.codeshu.transational.service.impl;

import com.codeshu.transational.entity.AccountEntity;
import com.codeshu.transational.mapper.AccountMapper;
import com.codeshu.transational.service.BService;
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
public class BServiceImpl implements BService {
	@Resource
	private AccountMapper accountMapper;

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void bMethod() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(2L);
		accountEntity.setMoney(BigDecimal.valueOf(1100));
		accountMapper.update(accountEntity);
	}

}

