package com.codeshu.controller;

import com.codeshu.response.UserWithPostResponse;
import com.codeshu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/11 11:18
 */
@RestController
public class UserWithPostController {
	@Autowired
	private UserService userService;

	@GetMapping("/getUserWithPost")
	public List<UserWithPostResponse> getUserWithPost() {
		return userService.getUserWithPost();
	}
}
