package com.codeshu.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CodeShu
 * @date 2023/10/29 12:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetAllQuestionAnswerRequest extends BasePageRequest {
	private Long uid;
}
