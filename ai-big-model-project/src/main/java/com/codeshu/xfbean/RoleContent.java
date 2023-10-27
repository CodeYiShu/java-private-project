package com.codeshu.xfbean;

import lombok.Data;

/**
 * 提问或回答信息
 *
 * @author CodeShu
 * @date 2023/10/27 10:27
 */
@Data
public class RoleContent {
	/**
	 * 角色
	 * - user 表示用户的提问
	 * - assistant 表示 AI 的回答
	 */
	private String role;
	/**
	 * 提问或回答的内容
	 */
	private String content;
}
