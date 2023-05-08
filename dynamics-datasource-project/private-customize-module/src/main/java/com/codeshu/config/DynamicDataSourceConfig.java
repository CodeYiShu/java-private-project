/**
 * 版权所有，侵权必究！
 */

package com.codeshu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.codeshu.properties.BaseDataSourceProperties;
import com.codeshu.properties.DynamicDataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 */
@Configuration
public class DynamicDataSourceConfig {

	/**
	 * 主数据源的配置项
	 * <p>
	 * 使用 spring.datasource.druid 配置项进行配置
	 *
	 * @return 主数据源配置项
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	public BaseDataSourceProperties dataSourceProperties() {
		return new BaseDataSourceProperties();
	}

	/**
	 * 从数据源的配置项
	 * <p>
	 * 使用 dynamic 配置项进行配置
	 *
	 * @return 从数据源配置项
	 */
	@Bean
	@ConfigurationProperties(prefix = "dynamic")
	public DynamicDataSourceProperties dynamicDataSourceProperties() {
		return new DynamicDataSourceProperties();
	}

	/**
	 * 构建主数据源和从数据源
	 *
	 * @param baseDataSourceProperties    主数据源配置项
	 * @param dynamicDataSourceProperties 从数据源配置项
	 * @return 主数据源
	 */
	@Bean
	public DynamicDataSource dynamicDataSource(BaseDataSourceProperties baseDataSourceProperties, DynamicDataSourceProperties dynamicDataSourceProperties) {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();

		//获取所有配置的子数据源对象 Map ，设置到targetDataSources
		dynamicDataSource.setTargetDataSources(getDynamicDataSource(dynamicDataSourceProperties));

		//构建默认数据源（即用 spring.datasource.druid 配置的数据源）
		DruidDataSource defaultDataSource = DynamicDataSourceFactory.buildDruidDataSource(baseDataSourceProperties);
		dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);

		return dynamicDataSource;
	}

	/**
	 * 构建配置的多个从数据源，返回所有配置的子数据源对象Map
	 * <p>
	 * 构建的是 dynamic.datasource 配置的数据源，不是主数据源
	 *
	 * @return key：数据源名称 value：数据源对象
	 */
	private Map<Object, Object> getDynamicDataSource(DynamicDataSourceProperties properties) {
		//获取配置的 dynamic.datasource 配置项，key 为 数据源名称，value 为数据源基本配置项
		Map<String, BaseDataSourceProperties> dataSourcePropertiesMap = properties.getDatasource();

		//存放配置的所有子数据源对象，key 为数据源名称，value 为数据源对象
		Map<Object, Object> targetDataSources = new HashMap<>(dataSourcePropertiesMap.size());

		dataSourcePropertiesMap.forEach((datasourceName, baseDataSourceProperties) -> {
			//构建配置的每个子数据源
			DruidDataSource druidDataSource = DynamicDataSourceFactory.buildDruidDataSource(baseDataSourceProperties);
			//数据源对象存入 Map 中
			targetDataSources.put(datasourceName, druidDataSource);
		});

		return targetDataSources;
	}

}
