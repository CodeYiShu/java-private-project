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
 * @date 2023/5/6 16:56
 */
@Configuration
@MapperScan(basePackages = {"com.codeshu.master.mapper"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDatasourceConfig {

	/**
	 * 寻找配置文件中的数据源配置信息
	 */
	@Primary
	@Bean("masterDatasourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	/**
	 * 构建数据源
	 */
	@Primary
	@Bean("masterDatasource")
	public DataSource dataSource(@Qualifier("masterDatasourceProperties") DataSourceProperties properties) {
		return DataSourceBuilder.create()
				.driverClassName(properties.getDriverClassName())
				.url(properties.getUrl())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();
	}

	/**
	 * 构建事务管理器
	 */
	@Primary
	@Bean(name = "masterTransactionManager")
	public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("masterDatasource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 构建sqlSession工厂
	 */
	@Bean(name = "masterSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDatasource") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setTypeHandlersPackage("com.codeshu.master.entity");
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/master/*.xml"));
		return sessionFactoryBean.getObject();
	}

	/**
	 * 构建sqlSession
	 */
	@Primary
	@Bean(name = "masterSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
