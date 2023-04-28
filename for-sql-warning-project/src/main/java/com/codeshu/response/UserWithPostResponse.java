package com.codeshu.response;

import lombok.Data;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/11 11:19
 */
@Data
public class UserWithPostResponse {
	private Long id;
	private String name;
	private Integer age;
	private List<Long> postIds;
	/**
	 * 岗位名称
	 */
	private List<String> postNames;
}
