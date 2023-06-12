package com.codeshu.ioc.service.impl;

import com.codeshu.ioc.service.DBService;
import org.springframework.stereotype.Service;

/**
 * @author CodeShu
 * @date 2023/6/8 13:38
 */
//@Service
public class OracleServiceImpl implements DBService {
	@Override
	public void display() {
		System.out.println("Oracle Service");
	}
}
