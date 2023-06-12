package com.codeshu.transational.controller;

import com.codeshu.transational.service.AService;
import com.codeshu.transational.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeShu
 * @date 2023/6/8 14:58
 */
@RestController
public class TestController {
	@Autowired
	private AService aService;

	@Autowired
	private TestService testService;

	@GetMapping("test")
	public void test() {
		aService.aMethod();
	}

	@GetMapping("testTransactionFault")
	public void testTransactionFault(){
		testService.method();
	}
}
