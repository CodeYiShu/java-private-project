package com.codeshu.response;

import lombok.Data;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/8/16 15:59
 */
@Data
public class ClassroomGetByIdSimpleTypeResponse {
	private Long id;

	private String className;

	private List<String> studentNameList;
}
