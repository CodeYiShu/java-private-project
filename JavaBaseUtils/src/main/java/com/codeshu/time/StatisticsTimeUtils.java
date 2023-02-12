package com.codeshu.time;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 统计相关的时间转换（时间坐标轴）
 *
 * @author CodeShu
 * @date 2023/2/12 14:03
 */
public class StatisticsTimeUtils {

	/**
	 * 时间格式(yyyy-MM-dd)
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	/**
	 * 时间格式(yyyy-MM-dd HH:mm:ss)
	 */
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static SimpleDateFormat format1 = new SimpleDateFormat(DATE_PATTERN);
	public static SimpleDateFormat format2 = new SimpleDateFormat(DATE_TIME_PATTERN);

	public static void main(String[] args) {
		//[2023-02-25, 2023-02-26, 2023-02-27, 2023-02-28, 2023-03-01]
		List<String> rangeDay = getRangeDay("2023-02-25", "2023-03-01");
		System.out.println(rangeDay);

		//[2023-01, 2023-02, 2023-03]
		List<String> rangeMonth = getRangeMonth("2023-01", "2023-03");
		System.out.println(rangeMonth);

		//[2023-02-25 00:00:00, 2023-03-01 23:59:59]
		List<String> dateAddStartAndEndTime = dateAddStartAndEndTime("2023-02-25", "2023-03-01");
		System.out.println(dateAddStartAndEndTime);
	}

	/**
	 * 时间范围内的所有天数（横坐标 yyyy-MM-dd）
	 *
	 * @param startDate 开始时间 yyyy-MM-dd
	 * @param endDate   结束时间 yyyy-MM-dd
	 * @return 时间范围内的所有天数（横坐标 yyyy-MM-dd）
	 */
	public static List<String> getRangeDay(String startDate, String endDate) {
		DateTime startDateTime = DateUtil.parse(startDate);
		DateTime endDateTime = DateUtil.parse(endDate);
		List<DateTime> dateTimes = DateUtil.rangeToList(startDateTime, endDateTime, DateField.DAY_OF_MONTH);
		List<String> rangeList = new ArrayList<>();
		for (DateTime dateTime : dateTimes) {
			rangeList.add(DateUtil.format(dateTime, "yyyy-MM-dd"));
		}
		return rangeList;
	}

	/**
	 * 时间范围内的所有月份（横坐标 yyyy-MM）
	 *
	 * @param startDate 开始时间 yyyy-MM
	 * @param endDate   结束时间 yyyy-MM
	 * @return 时间范围内的所有月份（横坐标 yyyy-MM）
	 */
	public static List<String> getRangeMonth(String startDate, String endDate) {
		DateTime startDateTime = DateUtil.parse(startDate + "-01");
		DateTime endDateTime = DateUtil.parse(endDate + "-01");
		List<DateTime> dateTimes = DateUtil.rangeToList(startDateTime, endDateTime, DateField.MONTH);
		List<String> rangeList = new ArrayList<>();
		for (DateTime dateTime : dateTimes) {
			rangeList.add(DateUtil.format(dateTime, "yyyy-MM"));
		}
		return rangeList;
	}

	/**
	 * 为开始时间加上00:00:00，结束时间加上23:59:59
	 *
	 * @param dateStart 开始时间 yyyy-MM-dd
	 * @param endStart  结束时间 yyyy-MM-dd
	 * @return yyyy-MM-dd 00:00:00  yyyy-MM-dd 23:59:59
	 */
	public static List<String> dateAddStartAndEndTime(String dateStart, String endStart) {
		List<String> dateString = new ArrayList<>();
		DateTime startDateTime = DateUtil.parse(dateStart);
		DateTime endDateTime = DateUtil.parse(endStart);
		String beginOfDay = DateUtil.beginOfDay(startDateTime).toString(DATE_TIME_PATTERN);
		String endOfDay = DateUtil.endOfDay(endDateTime).toString(DATE_TIME_PATTERN);
		dateString.add(beginOfDay);
		dateString.add(endOfDay);
		return dateString;
	}
}
