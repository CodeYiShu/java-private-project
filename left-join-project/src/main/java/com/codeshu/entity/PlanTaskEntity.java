package com.codeshu.entity;

import lombok.Data;

/**
 * left_join_plan_task
 * @author CodeShu
 * @date 2022/11/26 9:25
 */
@Data
public class PlanTaskEntity {
	private Long id;
	private Long planId;
	private Long taskId;
}
