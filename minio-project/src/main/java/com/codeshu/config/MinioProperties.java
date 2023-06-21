package com.codeshu.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

	/**
	 * Minio 服务地址
	 */
	private String endpoint;

	/**
	 * Minio API 端口
	 */
	private Integer port;

	/**
	 * accessKey
	 */
	private String accessKey;

	/**
	 * secretKey
	 */
	private String secretKey;

	/**
	 * true 则用的是 https 而不是 http
	 */
	private boolean secure;

	/**
	 * 默认存储桶
	 */
	private String bucketName;

	/**
	 * 图片的最大大小
	 */
	private long imageSize;

	/**
	 * 其他文件的最大大小
	 */
	private long fileSize;


	/**
	 * Minio 客户端
	 */
	@Bean
	public MinioClient minioClient() {
		return MinioClient.builder()
				.credentials(accessKey, secretKey)
				.endpoint(endpoint, port, secure)
				.build();
	}
}

