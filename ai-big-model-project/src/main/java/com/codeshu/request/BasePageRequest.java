package com.codeshu.request;

import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/10/29 12:43
 */
@Data
public class BasePageRequest {
	/**
	 * 所选页数
	 */
	private Integer pageNum;

	/**
	 * 限制每页记录数
	 */
	private Integer pageSize;
}
