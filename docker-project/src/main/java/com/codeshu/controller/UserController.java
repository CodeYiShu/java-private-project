package com.codeshu.controller;

import com.codeshu.entity.User;
import com.codeshu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2023-08-13 18:03:29
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public List<User> list() {
		return userService.list();
	}

	@PostMapping
	public String save(@RequestBody User user) {
		userService.save(user);
		return "保存成功";
	}
}

