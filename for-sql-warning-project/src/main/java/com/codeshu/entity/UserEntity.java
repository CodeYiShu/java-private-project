package com.codeshu.entity;

import lombok.Data;

/**
 * for_sql_warning_user
 * @author CodeShu
 * @date 2023/1/7 17:35
 */
@Data
public class UserEntity {
	private Long id;
	private String name;
	private Integer age;
	private Long deptId;
}
