package com.codeshu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2023-08-13 18:03:31
 */
@Data
public class User implements Serializable {
	private static final long serialVersionUID = -17169015865764566L;

	private Long id;

	private String name;

	private Integer age;
}

