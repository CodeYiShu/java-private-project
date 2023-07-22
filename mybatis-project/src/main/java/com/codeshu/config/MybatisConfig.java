package com.codeshu.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.codeshu.interceptor.ProjectFilterInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CodeShu
 * @date 2023/7/22 17:09
 */
@Configuration
public class MybatisConfig {
	public MybatisConfig() {
	}

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		//把自定义的过滤器注册进去
		mybatisPlusInterceptor.addInnerInterceptor(new ProjectFilterInterceptor());
		mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
		return mybatisPlusInterceptor;
	}
}
