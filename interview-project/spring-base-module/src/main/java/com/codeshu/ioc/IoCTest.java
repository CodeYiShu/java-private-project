package com.codeshu.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author CodeShu
 * @date 2023/7/19 10:29
 */
public class IoCTest {
	@Test
	public void testIoCBase(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloBean helloBean1 = applicationContext.getBean(HelloBean.class);
		HelloBean helloBean2 = applicationContext.getBean(HelloBean.class);
		System.out.println(helloBean1 == helloBean2);
	}

}
