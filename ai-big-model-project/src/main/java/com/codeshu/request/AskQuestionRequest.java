package com.codeshu.request;

import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/10/27 13:52
 */
@Data
public class AskQuestionRequest {
	private String uid;

	private String question;
}
