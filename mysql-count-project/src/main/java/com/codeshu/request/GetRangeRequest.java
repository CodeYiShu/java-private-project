package com.codeshu.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author CodeShu
 * @date 2023/1/14 13:22
 */
@Data
public class GetRangeRequest {
	@NotBlank(message = "开始时间不能为空 yyyy-MM-dd")
	private String startDate;

	@NotBlank(message = "结束时间不能为空 yyyy-MM-dd")
	private String endDate;
}
