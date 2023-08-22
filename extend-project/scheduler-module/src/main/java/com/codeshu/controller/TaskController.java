package com.codeshu.controller;

import com.codeshu.service.TaskService;
import com.codeshu.task.MyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/8/22 11:39
 */
@RestController
@RequestMapping("task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping("list")
	public List<MyTask> list() {
		return taskService.list();
	}

	@GetMapping("start/{taskName}")
	public String startTask(@PathVariable String taskName) {
		return taskService.startTask(taskName);
	}

	@GetMapping("stop/{taskName}")
	public String stopTask(@PathVariable String taskName) {
		return taskService.stopTask(taskName);
	}

	@GetMapping("status/{taskName}")
	public String getStatus(@PathVariable String taskName) {
		return taskService.getStatus(taskName);
	}

	@DeleteMapping("{taskName}")
	public String deleteTask(@PathVariable String taskName) {
		return taskService.deleteTask(taskName);
	}
}
