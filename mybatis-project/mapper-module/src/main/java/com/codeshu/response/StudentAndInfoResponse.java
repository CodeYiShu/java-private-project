package com.codeshu.response;

import com.codeshu.entity.StudentInfo;
import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/10/17 15:32
 */
@Data
public class StudentAndInfoResponse {
	/**
	 * 学生ID
	 */
	private Long id;

	/**
	 * 学生名称
	 */
	private String studentName;

	/**
	 * 学生额外信息
	 */
	private StudentInfo studentInfo;
}
