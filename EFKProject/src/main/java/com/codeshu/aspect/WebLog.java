package com.codeshu.aspect;

import lombok.Data;

/**
 * Controller层的日志封装类
 *
 * @author CodeShu
 * @date 2023/4/7 19:42
 */
@Data
public class WebLog {
	/**
	 * 类名 + 方法名
	 */
	private String interfaceInfo;

	/**
	 * 请求参数
	 */
	private Object parameter;

	/**
	 * 请求返回的结果
	 */
	private Object result;

	/**
	 * 操作描述
	 */
	private String description;

	/**
	 * 操作用户
	 */
	private String username;

	/**
	 * 操作时间
	 */
	private Long startTime;

	/**
	 * 消耗时间（毫秒）
	 */
	private Integer spendTime;

	/**
	 * 根路径
	 */
	private String basePath;

	/**
	 * URI
	 */
	private String uri;

	/**
	 * URL
	 */
	private String url;

	/**
	 * 请求方式
	 */
	private String method;

	/**
	 * IP地址
	 */
	private String ip;
}
