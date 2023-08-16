package com.codeshu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.codeshu.mapper")
public class BatchModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchModuleApplication.class, args);
	}

}
