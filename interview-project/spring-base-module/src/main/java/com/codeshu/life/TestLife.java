package com.codeshu.life;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * @author CodeShu
 * @date 2023/7/19 11:34
 */
public class TestLife {
	@Test
	public void testFile() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		applicationContext.close();
	}
}
