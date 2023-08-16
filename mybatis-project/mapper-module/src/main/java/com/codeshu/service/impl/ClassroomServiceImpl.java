package com.codeshu.service.impl;

import com.codeshu.dao.ClassroomDao;
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
	private ClassroomDao classroomDao;
}
