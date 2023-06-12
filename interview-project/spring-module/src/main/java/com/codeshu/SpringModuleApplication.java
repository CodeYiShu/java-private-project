package com.codeshu;

import com.codeshu.aspect.test.TestClass;
import com.codeshu.ioc.controller.DBController;
import com.codeshu.ioc.service.DBService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.codeshu.transational.mapper")
public class SpringModuleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringModuleApplication.class, args);
//		TestClass testClass = context.getBean(TestClass.class);
//		testClass.testMethod();
		DBService dbService = context.getBean(DBService.class);
		dbService.display();

	}

}
