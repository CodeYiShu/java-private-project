package com.codeshu.controller;

import com.codeshu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeShu
 * @date 2023/8/22 11:39
 */
@RestController
@RequestMapping("task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping("start")
	public String startTask() {
		taskService.startTask("0/2 * * * * ?");
		return "启动成功";
	}

	@GetMapping("stop")
	public String stopTask() {
		taskService.stopTask();
		return "关闭成功";
	}
}
