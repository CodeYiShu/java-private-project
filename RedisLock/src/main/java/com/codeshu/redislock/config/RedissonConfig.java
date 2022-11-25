package com.codeshu.redislock.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CodeShu
 * @date 2022/11/22 16:20
 */
@Configuration
public class RedissonConfig {
	@Bean
	public Redisson redisson() {
		Config config = new Config();
		//配置地址、数据库
		config.useSingleServer().setAddress("http://localhost:6379");
		return (Redisson) Redisson.create(config);
	}
}
