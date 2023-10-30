package com.codeshu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CodeShu
 * @date 2023/10/30 16:09
 */
@Configuration
public class MybatisPlusConfig {
	/**
	 * 自定义批量插入 SQL 注入器
	 */
	@Bean
	public InsertBatchSqlInjector insertBatchSqlInjector() {
		return new InsertBatchSqlInjector();
	}
}
