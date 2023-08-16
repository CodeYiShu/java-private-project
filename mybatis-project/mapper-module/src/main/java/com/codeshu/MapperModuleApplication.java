package com.codeshu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.codeshu.dao"})
public class MapperModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapperModuleApplication.class, args);
	}

}
