package com.codeshu.ioc.controller;

import com.codeshu.ioc.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author CodeShu
 * @date 2023/6/8 13:43
 */
@Controller
public class DBController {
	@Autowired
	private DBService dbService;

	public DBService getDbService() {
		return dbService;
	}
}
