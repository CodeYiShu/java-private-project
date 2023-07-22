package com.codeshu.config;

import com.codeshu.bean.Car;
import com.codeshu.bean.Pet;
import com.codeshu.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author CodeShu
 * @date 2023/7/21 14:25
 */
@Configuration
//@Import(value = {MyImportSelector.class})
@EnableConfigurationProperties(Car.class)
public class MyConfig {
	@Bean(name = "pet")
	public Pet petFactory(){
		return new Pet();
	}

	@Bean(name = "user")
	@ConditionalOnBean(value = {Pet.class})
	public User userFactory(){
		User user = new User();
		user.setPet(petFactory());
		return user;
	}
}
