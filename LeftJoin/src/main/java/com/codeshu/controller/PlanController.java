package com.codeshu.controller;

import com.codeshu.entity.PlanEntity;
import com.codeshu.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CodeShu
 * @date 2022/11/26 9:18
 */
@RestController
@RequestMapping("/plan")
public class PlanController {
	@Autowired
	private PlanService planService;

	@GetMapping("/getAll")
	public List<PlanEntity> getAll() {
		return planService.getAll();
	}
}
