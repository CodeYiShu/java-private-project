package com.codeshu.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/8/17 10:55
 */
@Data
@ApiModel("批量查询用户")
public class SelectBatchResponse {
	@ApiModelProperty("用户id")
	private Long id;

	@ApiModelProperty("用户名")
	private String username;
}
