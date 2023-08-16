package com.codeshu.controller;

import com.codeshu.response.ClassroomGetByIdObjectTypeResponse;
import com.codeshu.response.ClassroomGetByIdSimpleTypeResponse;
import com.codeshu.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mybatis的映射技巧(Classroom)表控制层
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
@RestController
@RequestMapping("classroom")
public class ClassroomController {
	@Autowired
	private ClassroomService service;

	/**
	 * 一对多（简单类型）
	 * <p>
	 * 查询一个教室，同时查询出这个教室下的所有学生名字（不是学生对象）。
	 */
	@GetMapping("simpleType/{id}")
	public ClassroomGetByIdSimpleTypeResponse getByIdSimpleType(@PathVariable("id") Long id) {
		return service.getByIdSimpleType(id);
	}

	/**
	 * 一对多（对象类型）
	 * <p>
	 * 查询一个教室，同时查询出这个教室下的所有学生信息（是学生对象）。
	 */
	@GetMapping("objectType/{id}")
	public ClassroomGetByIdObjectTypeResponse getByIdObjectType(@PathVariable("id") Long id) {
		return service.getByIdObjectType(id);
	}
}

