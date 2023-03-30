package com.codeshu.condition.controller;

import com.codeshu.condition.bean.User;
import com.codeshu.condition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConditionalOnProperty注解的使用
 * 作用：通过配置文件的配置项，让特定Bean生效
 * 缺陷：切换时需要重启项目，如果是需要动态切换则采用策略模式
 *
 * @author CodeShu
 * @date 2023/3/30 9:08
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("getUser")
	public User getUser() {
		return userService.getUser();
	}
}
