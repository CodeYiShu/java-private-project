/**
 * 版权所有，侵权必究！
 */

package com.codeshu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.codeshu.properties.BaseDataSourceProperties;

import java.sql.SQLException;

/**
 * 构建Druid数据源工厂
 */
public class DynamicDataSourceFactory {

	/**
	 * 构建 Druid 数据源
	 *
	 * @param properties 数据源基本配置项
	 * @return Druid 数据源
	 */
	public static DruidDataSource buildDruidDataSource(BaseDataSourceProperties properties) {
		DruidDataSource druidDataSource = new DruidDataSource();
		//设置驱动、URL、账号和密码
		druidDataSource.setDriverClassName(properties.getDriverClassName());
		druidDataSource.setUrl(properties.getUrl());
		druidDataSource.setUsername(properties.getUsername());
		druidDataSource.setPassword(properties.getPassword());

		//设置其他 Druid 配置信息
		druidDataSource.setInitialSize(properties.getInitialSize());
		druidDataSource.setMaxActive(properties.getMaxActive());
		druidDataSource.setMinIdle(properties.getMinIdle());
		druidDataSource.setMaxWait(properties.getMaxWait());
		druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
		druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
		druidDataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());
		druidDataSource.setValidationQuery(properties.getValidationQuery());
		druidDataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
		druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
		druidDataSource.setTestOnReturn(properties.isTestOnReturn());
		druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
		druidDataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
		druidDataSource.setSharePreparedStatements(properties.isSharePreparedStatements());

		try {
			//初始化数据源
			druidDataSource.init();
		} catch (SQLException e) {
			druidDataSource.close();

			e.printStackTrace();
		}

		return druidDataSource;
	}
}
