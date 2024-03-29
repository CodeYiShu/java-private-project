package com.codeshu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 用户ID
	 */
	private String uid;
	/**
	 * 角色 user-表示用户的提问 assistant-表示 AI 的回答
	 */
	private String role;
	/**
	 * 提问或回答的内容
	 */
	private String content;
}

