package com.codeshu.service.impl;

import com.codeshu.dao.StudentDao;
import com.codeshu.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Mybatis的映射技巧(Student)表服务实现类
 *
 * @author makejava
 * @since 2023-08-16 15:40:43
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
	@Resource
	private StudentDao studentDao;
}
