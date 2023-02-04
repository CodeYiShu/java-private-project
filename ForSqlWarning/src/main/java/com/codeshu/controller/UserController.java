package com.codeshu.controller;

import com.codeshu.entity.UserEntity;
import com.codeshu.response.UserWithDeptNameResponse;
import com.codeshu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/7 17:45
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/getAll")
	public List<UserEntity> getAll() {
		return userService.getAll();
	}

	@GetMapping("/getUserWithDeptName")
	public List<UserWithDeptNameResponse> getUserWithDeptName(){
		//return userService.getUserWithDeptName();
		//return userService.getUserWithDeptNameOptimize1();
		//return userService.getUserWithDeptNameOptimize2();
		//return userService.getUserWithDeptNameOptimize3();
		return userService.getUserWithDeptNameOptimize4();
	}
}
