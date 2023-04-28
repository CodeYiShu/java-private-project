package com.codeshu.cron;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author CodeShu
 * @date 2022/11/25 17:50
 */
@Getter
@Setter
@Accessors(chain = true)
public class TaskScheduleModel{

	/**
	 * 所选作业类型:
	 * 1  -> 每天
	 * 2  -> 每月
	 * 3  -> 每周
	 */
	Integer jobType;

	/**一周的哪几天*/
	Integer[] dayOfWeeks;

	/**一个月的哪几天*/
	Integer[] dayOfMonths;

	/**秒  */
	Integer second;

	/**分  */
	Integer minute;

	/**时  */
	Integer hour;

}
