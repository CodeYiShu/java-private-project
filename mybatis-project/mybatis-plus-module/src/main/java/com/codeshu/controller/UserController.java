package com.codeshu.controller;

import com.codeshu.entity.User;
import com.codeshu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/10/30 13:51
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("insertBatch")
	public void insertBatch() {
		long startTime = System.currentTimeMillis();
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			userList.add(new User(null, "" + i, 1, ""));
		}
		userService.insertBatch(userList);
		long endTime = System.currentTimeMillis();
		log.error("耗时 秒：{}，毫秒：{}", (endTime - startTime) / 1000, endTime - startTime);
	}

	@GetMapping("updateBatchById")
	public void updateBatchById() {
		long startTime = System.currentTimeMillis();
		List<User> userList = new ArrayList<>();
		for (long i = 1718914723287744514L; i < 1718914723287744523L; i++) {
			userList.add(new User(i, "" + i, 2, ""));
		}
		userService.updateBatchById(userList);
		long endTime = System.currentTimeMillis();
		log.error("耗时 秒：{}，毫秒：{}", (endTime - startTime) / 1000, endTime - startTime);
	}
}
