/**
 * 版权所有，侵权必究！
 */

package com.codeshu.properties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 从数据源配置项
 *
 * <p>
 * 配置项是 dynamic 前缀
 */
public class DynamicDataSourceProperties {
	/**
	 * 从数据源配置信息
	 *
	 * <p>
	 * 配置项是 dynamic.datasource 前缀，key 为数据源名称，value 为数据源基本配置信息
	 */
	private Map<String, BaseDataSourceProperties> datasource = new LinkedHashMap<>();

	public Map<String, BaseDataSourceProperties> getDatasource() {
		return datasource;
	}

	public void setDatasource(Map<String, BaseDataSourceProperties> datasource) {
		this.datasource = datasource;
	}
}
