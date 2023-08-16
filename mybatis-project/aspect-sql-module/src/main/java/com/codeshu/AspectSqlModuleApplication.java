package com.codeshu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.codeshu.mapper")
public class AspectSqlModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspectSqlModuleApplication.class, args);
	}

}
