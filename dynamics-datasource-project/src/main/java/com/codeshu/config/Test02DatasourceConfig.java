package com.codeshu.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author CodeShu
 * @date 2023/5/6 17:04
 */
@Configuration
@MapperScan(basePackages = {"com.codeshu.test02.mapper"},
		sqlSessionFactoryRef = "test02SqlSessionFactory")
public class Test02DatasourceConfig {

	@Bean("test02DatasourceProperties")
	@ConfigurationProperties(prefix = "dynamic-datasource.test02")
	public DataSourceProperties dataSourceProperties(){
		return new DataSourceProperties();
	}

	@Primary
	@Bean("test02Datasource")
	public DataSource dataSource(@Qualifier("test02DatasourceProperties") DataSourceProperties properties){
		return DataSourceBuilder.create()
				.driverClassName(properties.getDriverClassName())
				.url(properties.getUrl())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();
	}

	@Primary
	@Bean(name = "test02TransactionManager")
	public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("test02Datasource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "test02SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("test02Datasource") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setTypeHandlersPackage("com.codeshu.test02.entity");
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/test02/*.xml"));
		return sessionFactoryBean.getObject();
	}

	@Primary
	@Bean(name = "test02SqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("test02SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}

