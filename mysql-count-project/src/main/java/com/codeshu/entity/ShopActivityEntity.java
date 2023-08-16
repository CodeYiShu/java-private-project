package com.codeshu.entity;

import lombok.Data;

import java.util.Date;

/**
 * mysql_count_project_shop_activity
 * @author CodeShu
 * @date 2023/1/14 12:38
 */
@Data
public class ShopActivityEntity {
	private Long id;

	/**
	 * 门店名字
	 */
	private String name;

	/**
	 * 活动类型 1-打折活动 2-促销活动 3-抢购活动 4-秒杀活动 5-普通活动
	 */
	private Integer type;

	/**
	 * 举办时间
	 */
	private Date openTime;
}
