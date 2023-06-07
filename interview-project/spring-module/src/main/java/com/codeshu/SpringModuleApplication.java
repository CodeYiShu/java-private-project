package com.codeshu;

import com.codeshu.aspect.test.TestClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringModuleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringModuleApplication.class, args);
		TestClass testClass = context.getBean(TestClass.class);
		testClass.testMethod();
	}

}
