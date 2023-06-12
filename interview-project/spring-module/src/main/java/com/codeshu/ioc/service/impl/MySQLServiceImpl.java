package com.codeshu.ioc.service.impl;

import com.codeshu.ioc.service.DBService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CodeShu
 * @date 2023/6/8 13:38
 */
@Service
public class MySQLServiceImpl implements DBService {
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void display() {
		System.out.println("MySQL");
	}
}
