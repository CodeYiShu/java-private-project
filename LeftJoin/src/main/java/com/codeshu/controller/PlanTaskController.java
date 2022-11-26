package com.codeshu.controller;

import com.codeshu.entity.PlanTaskEntity;
import com.codeshu.service.PlanTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CodeShu
 * @date 2022/11/26 9:24
 */
@RestController
@RequestMapping("/planTask")
public class PlanTaskController {
	@Autowired
	private PlanTaskService planTaskService;

	@GetMapping("/getAll")
	public List<PlanTaskEntity> getAll() {
		return planTaskService.getAll();
	}
}
