package com.codeshu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author CodeShu
 * @date 2023/10/27 10:21
 */
@Data
@Component
@ConfigurationProperties("xf.config")
public class XFConfigProperties {

	private String hostUrl;

	private String appid;

	private String apiSecret;

	private String apiKey;

	private Integer maxResponseTime;
}
