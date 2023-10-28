package com.codeshu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (AiRoleContent)实体类
 *
 * @author makejava
 * @since 2023-10-28 13:36:43
 */
@Data
public class AiRoleContent implements Serializable {
	private static final long serialVersionUID = 381561358949283595L;

	private Long id;

	private Long userId;
	/**
	 * 角色 user-表示用户的提问 assistant-表示 AI 的回答
	 */
	private String role;
	/**
	 * 提问或回答的内容
	 */
	private String content;
}

