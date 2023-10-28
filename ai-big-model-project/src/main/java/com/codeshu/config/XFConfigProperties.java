package com.codeshu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 讯飞星火相关配置
 *
 * @author CodeShu
 * @date 2023/10/27 10:21
 */
@Data
@Component
@ConfigurationProperties("xf.config")
public class XFConfigProperties {
	/**
	 * 讯飞星火 API 地址
	 */
	private String hostUrl;

	/**
	 * APPID
	 */
	private String appid;

	/**
	 * APP_Secret
	 */
	private String apiSecret;

	/**
	 * APP_Key
	 */
	private String apiKey;

	/**
	 * 最大响应时间（单位秒）
	 */
	private Integer maxResponseTime;
}
