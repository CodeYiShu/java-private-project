package com.codeshu.controller;

import com.codeshu.entity.TaskEntity;
import com.codeshu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author CodeShu
 * @date 2022/11/25 21:30
 */
@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping("/getAll")
	public List<TaskEntity> getAll() {
		return taskService.getAll();
	}

	@GetMapping("/getAllByPlanType")
	public List<TaskEntity> getAllByPlanType(Integer planType) {
		return taskService.getAllByPlanType(planType);
	}

	/**
	 * 根据Map参数查询
	 * 比如key为taskName，value为任务1
	 * http://localhost:8080/task/getByMap?taskName=任务1
	 */
	@GetMapping("/getByMap")
	public List<TaskEntity> getByMap(@RequestParam Map<String, Object> params) {
		return taskService.getByMap(params);
	}

	/**
	 * 根据对象绑定查询
	 * 比如key为taskName，value为任务1
	 * http://localhost:8080/task/getByMap?taskName=任务1
	 */
	@GetMapping("/getByObj")
	public List<TaskEntity> getByObj(GetByObjRequest request) {
		return taskService.getByObj(request);
	}

	/**
	 * 根据对象绑定查询，在Mapper中使用@Param起参数名字
	 * 比如key为taskName，value为任务1
	 * http://localhost:8080/task/getByMap?taskName=任务1
	 */
	@GetMapping("/getByObjWithName")
	public List<TaskEntity> getByObjWithName(GetByObjRequest request) {
		return taskService.getByObjWithName(request);
	}
}
