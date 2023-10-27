package com.codeshu.xfbean;

import lombok.Data;

/**
 * 大模型的返回结果
 *
 * @author CodeShu
 * @date 2023/10/27 10:25
 */
@Data
public class JsonParse {
	/**
	 * 头信息
	 */
	private Header header;
	/**
	 * 负载信息
	 */
	private Payload payload;
}
