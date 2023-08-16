package com.codeshu.service.impl;

import com.codeshu.dao.ClassroomDao;
import com.codeshu.response.ClassroomGetByIdObjectTypeResponse;
import com.codeshu.response.ClassroomGetByIdSimpleTypeResponse;
import com.codeshu.service.ClassroomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Mybatis的映射技巧(Classroom)表服务实现类
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
@Service("classroomService")
public class ClassroomServiceImpl implements ClassroomService {
	@Resource
	private ClassroomDao dao;

	@Override
	public ClassroomGetByIdSimpleTypeResponse getByIdSimpleType(Long id) {
		return dao.getByIdSimpleType(id);
	}

	@Override
	public ClassroomGetByIdObjectTypeResponse getByIdObjectType(Long id) {
		return dao.getByIdObjectType(id);
	}
}
