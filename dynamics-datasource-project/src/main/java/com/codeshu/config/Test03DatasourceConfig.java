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

@Configuration
@MapperScan(basePackages = {"com.codeshu.test03.mapper"},
		sqlSessionFactoryRef = "test03SqlSessionFactory")
public class Test03DatasourceConfig {

	@Bean("test03DatasourceProperties")
	@ConfigurationProperties(prefix = "dynamic-datasource.test03")
	public DataSourceProperties dataSourceProperties(){
		return new DataSourceProperties();
	}

	@Primary
	@Bean("test03Datasource")
	public DataSource dataSource(@Qualifier("test03DatasourceProperties") DataSourceProperties properties){
		return DataSourceBuilder.create()
				.driverClassName(properties.getDriverClassName())
				.url(properties.getUrl())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();
	}

	@Primary
	@Bean(name = "test03TransactionManager")
	public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("test03Datasource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "test03SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("test03Datasource") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setTypeHandlersPackage("com.codeshu.test03.entity");
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/test03/*.xml"));
		return sessionFactoryBean.getObject();
	}

	@Primary
	@Bean(name = "test03SqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("test02SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
