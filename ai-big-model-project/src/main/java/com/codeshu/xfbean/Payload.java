package com.codeshu.xfbean;

/**
 * 大模型返回的负载信息
 *
 * @author CodeShu
 * @date 2023/10/27 10:26
 */

import lombok.Data;

@Data
public class Payload {
	private Choices choices;
}
