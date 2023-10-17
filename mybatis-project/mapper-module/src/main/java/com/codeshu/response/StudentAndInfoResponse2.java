package com.codeshu.response;

import com.codeshu.entity.Student;
import com.codeshu.entity.StudentInfo;
import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/10/17 15:32
 */
@Data
public class StudentAndInfoResponse2 {
	/**
	 * 学生信息
	 */
	private Student student;

	/**
	 * 学生额外信息
	 */
	private StudentInfo studentInfo;
}
