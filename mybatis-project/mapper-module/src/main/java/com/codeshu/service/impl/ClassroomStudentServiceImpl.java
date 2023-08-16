package com.codeshu.service.impl;

import com.codeshu.dao.ClassroomStudentDao;
import com.codeshu.service.ClassroomStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Mybatis的映射技巧(ClassroomStudent)表服务实现类
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
@Service("classroomStudentService")
public class ClassroomStudentServiceImpl implements ClassroomStudentService {
	@Resource
	private ClassroomStudentDao classroomStudentDao;
}
