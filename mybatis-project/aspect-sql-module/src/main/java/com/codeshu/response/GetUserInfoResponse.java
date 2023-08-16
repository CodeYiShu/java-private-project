package com.codeshu.response;

import lombok.Data;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/7/22 16:55
 */
@Data
public class GetUserInfoResponse {
	private Long id;

	private String username;

	private List<Long> projectIds;
}
