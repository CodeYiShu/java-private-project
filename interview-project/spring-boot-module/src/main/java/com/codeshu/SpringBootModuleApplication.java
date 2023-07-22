package com.codeshu;

import com.codeshu.bean.Car;
import com.codeshu.bean.Pet;
import com.codeshu.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootModuleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootModuleApplication.class, args);
		System.out.println(context.getBean(User.class));
		System.out.println(context.getBean(Pet.class));
		System.out.println(context.getBean(Car.class));
	}

}
