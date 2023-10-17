package com.codeshu.controller;

import com.codeshu.dao.StudentDao;
import com.codeshu.response.StudentAndInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/10/17 15:46
 */
@RestController
@RequestMapping("student")
public class StudentController {
	@Resource
	private StudentDao studentDao;

	@GetMapping("getStudentAndInfo")
	public List<StudentAndInfoResponse> getStudentAndInfo() {
		return studentDao.getStudentAndInfoList();
	}
}
