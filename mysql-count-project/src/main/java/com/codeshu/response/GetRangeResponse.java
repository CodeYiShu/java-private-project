package com.codeshu.response;

import lombok.Data;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/1/14 13:27
 */
@Data
public class GetRangeResponse {
	/**
	 * 多个子响应对象 1个响应对象代表1种类型的统计
	 */
	List<GetRangeSingleResponse> subResponseList;

	/**
	 * 统计时间 yyyy-MM-dd
	 */
	private String date;
}
