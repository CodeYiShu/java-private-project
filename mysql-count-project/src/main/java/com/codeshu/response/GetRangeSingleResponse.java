package com.codeshu.response;

import lombok.Data;

/**
 * @author CodeShu
 * @date 2023/1/14 15:48
 */
@Data
public class GetRangeSingleResponse {
	/**
	 * 活动类型 1-打折活动 2-促销活动 3-抢购活动 4-秒杀活动 5-普通活动
	 */
	private Integer type;

	/**
	 * 此活动类型的举办数量
	 */
	private Integer count;

	/**
	 * 统计时间 yyyy-MM-dd
	 */
	private String date;
}
