package com.codeshu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.codeshu.mapper"})
public class MySqlCountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySqlCountApplication.class, args);
	}

}
