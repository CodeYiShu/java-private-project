/**
 * 版权所有，侵权必究！
 */

package com.codeshu.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源对象
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * 当寻找数据源时会到这里来获取数据源的名称，从而可以获取到数据源对象
	 *
	 * @return 数据源名称
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.peek();
	}
}
