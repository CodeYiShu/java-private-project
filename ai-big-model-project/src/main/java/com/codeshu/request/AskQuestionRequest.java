package com.codeshu.request;

import lombok.Data;

/**
 * 发起提问的请求参数
 *
 * @author CodeShu
 * @date 2023/10/27 13:52
 */
@Data
public class AskQuestionRequest {
	/**
	 * 用户ID
	 */
	private String uid;

	/**
	 * 提问
	 */
	private String question;
}
