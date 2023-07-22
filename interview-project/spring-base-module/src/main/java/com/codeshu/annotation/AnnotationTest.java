package com.codeshu.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author CodeShu
 * @date 2023/7/19 16:47
 */
public class AnnotationTest {
	@Test
	public void testAnnotation() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountService accountService = context.getBean("accountService", AccountService.class);
		accountService.saveAccount();
	}
}
