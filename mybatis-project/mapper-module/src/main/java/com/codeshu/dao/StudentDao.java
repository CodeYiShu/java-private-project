package com.codeshu.dao;

import com.codeshu.response.StudentAndInfoResponse;

import java.util.List;

/**
 * Mybatis的映射技巧(Student)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
public interface StudentDao {
	/**
	 * 查询所有学生信息，同时查询出学生的额外信息
	 */
	List<StudentAndInfoResponse> getStudentAndInfoList();
}

