package com.codeshu.service;

import com.codeshu.response.ClassroomGetByIdObjectTypeResponse;
import com.codeshu.response.ClassroomGetByIdSimpleTypeResponse;

/**
 * Mybatis的映射技巧(Classroom)表服务接口
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
public interface ClassroomService {
	/**
	 * 一对多（简单类型）
	 * <p>
	 * 查询一个教室，同时查询出这个教室下的所有学生名字（不是学生对象）。
	 */
	ClassroomGetByIdSimpleTypeResponse getByIdSimpleType(Long id);

	/**
	 * 一对多（对象类型）
	 * <p>
	 * 查询一个教室，同时查询出这个教室下的所有学生信息（是学生对象）。
	 */
	ClassroomGetByIdObjectTypeResponse getByIdObjectType(Long id);
}
