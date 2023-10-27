package com.codeshu.xfbean;

import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/10/27 10:27
 */
@Data
public class Text {
	/**
	 * 角色标识，固定为assistant，标识角色为AI，assistant 表示回答
	 */
	private String role;
	/**
	 * AI的回答内容
	 */
	private String content;
}
