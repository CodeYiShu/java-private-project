package com.codeshu.response;

import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/1/7 17:49
 */
@Data
public class UserWithDeptNameResponse {
	private Long id;
	private String name;
	private Integer age;
	private Long deptId;
	/**
	 * 部门名称
	 */
	private String deptName;
}
