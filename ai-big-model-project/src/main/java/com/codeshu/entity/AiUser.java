package com.codeshu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (AiUser)实体类
 *
 * @author makejava
 * @since 2023-10-28 13:38:27
 */
@Data
public class AiUser implements Serializable {
	private static final long serialVersionUID = -98738441642715845L;

	private Long id;

	private String username;
}

