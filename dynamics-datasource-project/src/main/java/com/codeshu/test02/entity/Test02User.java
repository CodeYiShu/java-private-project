package com.codeshu.test02.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author CodeShu
 * @date 2023/5/6 17:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Test02User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;
}
