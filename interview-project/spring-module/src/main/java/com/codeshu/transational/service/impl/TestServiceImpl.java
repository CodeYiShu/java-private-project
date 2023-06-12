package com.codeshu.transational.service.impl;

import com.codeshu.transational.mapper.AccountMapper;
import com.codeshu.transational.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CodeShu
 * @date 2023/6/12 11:16
 */
@Service
public class TestServiceImpl implements TestService {
	@Resource
	private AccountMapper accountMapper;

	@Override
	public void method() {

	}
}
