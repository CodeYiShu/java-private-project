package com.codeshu.response;

import lombok.Data;

import java.util.Set;

/**
 * @author CodeShu
 * @date 2023/7/15 22:16
 */
@Data
public class DaLeTouResponse {
	private Set<String> before;

	private Set<String> after;
}
