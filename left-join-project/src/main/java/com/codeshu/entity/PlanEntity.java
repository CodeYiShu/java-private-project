package com.codeshu.entity;

import lombok.Data;

import java.util.Date;

/**
 * left_join_plan
 * @author CodeShu
 * @date 2022/11/26 9:20
 */
@Data
public class PlanEntity {
	private Long id;
	private Integer planType;
	private String planName;
	private Date issueTime;
}
