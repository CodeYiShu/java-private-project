package com.codeshu;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
public class DynamicsDatasourceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicsDatasourceProjectApplication.class, args);
	}

}
