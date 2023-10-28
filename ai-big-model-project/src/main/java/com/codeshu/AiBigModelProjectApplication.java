package com.codeshu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.codeshu.dao"})
public class AiBigModelProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiBigModelProjectApplication.class, args);
	}

}
