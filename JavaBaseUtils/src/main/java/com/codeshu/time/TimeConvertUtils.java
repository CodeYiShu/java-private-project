package com.codeshu.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 时间的一些转换和获取的工具方法
 * 可用于统计图的统计某些时间段的数据（参见智能设备运维系统的统计）
 *
 * @author CodeShu
 * @date 2022/11/15 9:21
 */
public class TimeConvertUtils {
	private static final String START_DATE = "startDate";
	private static final String END_DATE = "endDate";
	private static final String LONG_FORMAT = "yyyy-MM-dd";
	//===================================================根据时间范围获取第一天和最后一天===================================================

	/**
	 * 根据时间范围获取这段时间每一周的第一天和最后一天
	 * 根据时间范围获取这段时间每一月的第一天和最后一天
	 *
	 * @param statisticsType 类型 week month
	 * @param map            startDate和endDate，都为yyyy-MM-dd
	 * @return 结果，相邻两个元素为一对
	 *
	 * 注意点：
	 * （1）对于周类型，startDate必须是周一，endDate必须是周日
	 * （2）对于月类型，startDate必须是一号，endDate最好是月末
	 */
	public static List<String> rangeFirstAndEndDate(String statisticsType, Map<String, Object> map) {
		List<String> listWeekOrMonth = new ArrayList<>();
		try {
			DateFormat dateFormat = new SimpleDateFormat(LONG_FORMAT);
			String startDate = (String) map.get(START_DATE);
			String endDate = (String) map.get(END_DATE);
			Date sDate = dateFormat.parse(startDate);
			Calendar sCalendar = Calendar.getInstance();
			sCalendar.setFirstDayOfWeek(Calendar.MONDAY);
			sCalendar.setTime(sDate);
			Date eDate = dateFormat.parse(endDate);
			Calendar eCalendar = Calendar.getInstance();
			eCalendar.setFirstDayOfWeek(Calendar.MONDAY);
			eCalendar.setTime(eDate);
			boolean bool = true;
			//取每一周的第一天和最后一天
			if ("week".equals(statisticsType)) {
				while (sCalendar.getTime().getTime() < eCalendar.getTime().getTime()) {
					if (bool || sCalendar.get(Calendar.DAY_OF_WEEK) == 2 || sCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
						listWeekOrMonth.add(dateFormat.format(sCalendar.getTime()));
						bool = false;
					}
					sCalendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				listWeekOrMonth.add(dateFormat.format(eCalendar.getTime()));
				if (listWeekOrMonth.size() % 2 != 0) {
					listWeekOrMonth.add(dateFormat.format(eCalendar.getTime()));
				}
			} else { //取每一月的第一天和最后一天
				while (sCalendar.getTime().getTime() < eCalendar.getTime().getTime()) {
					if (bool || sCalendar.get(Calendar.DAY_OF_MONTH) == 1 || sCalendar.get(Calendar.DAY_OF_MONTH) == sCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
						listWeekOrMonth.add(dateFormat.format(sCalendar.getTime()));
						bool = false;
					}
					sCalendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				listWeekOrMonth.add(dateFormat.format(eCalendar.getTime()));
				if (listWeekOrMonth.size() % 2 != 0) {
					listWeekOrMonth.add(dateFormat.format(eCalendar.getTime()));
				}
			}

			//在每一组的第一天添加上00:00:00，最后一天添加上23:59:59
			for (int i = 0; i < listWeekOrMonth.size(); i++) {
				if (i % 2 == 0) {
					listWeekOrMonth.set(i, listWeekOrMonth.get(i) + " 00:00:00");
					continue;
				}
				listWeekOrMonth.set(i, listWeekOrMonth.get(i) + " 23:59:59");
			}
		} catch (Exception e) {
			throw new RuntimeException("时间获取失败");
		}
		return listWeekOrMonth;
	}

	//===================================================指定范围的所有日期===================================================

	/**
	 * 获取指定范围的所有日期
	 *
	 * @param begin 开始时间 yyyy-MM-dd
	 * @param end   结束时间 yyyy-MM-dd
	 * @return 此范围的所有日期，相邻两个元素为一对
	 */
	public static List<String> rangeAllDate(String begin, String end) {
		List<String> listWeekOrMonth = new ArrayList<>();
		try {
			DateFormat dateFormat = new SimpleDateFormat(LONG_FORMAT);
			Date sDate = dateFormat.parse(begin);
			Calendar sCalendar = Calendar.getInstance();
			sCalendar.setFirstDayOfWeek(Calendar.MONDAY);
			sCalendar.setTime(sDate);
			Date eDate = dateFormat.parse(end);
			Calendar eCalendar = Calendar.getInstance();
			eCalendar.setFirstDayOfWeek(Calendar.MONDAY);
			eCalendar.setTime(eDate);

			while (sCalendar.getTime().getTime() <= eCalendar.getTime().getTime()) {
				listWeekOrMonth.add(dateFormat.format(sCalendar.getTime()) + " 00:00:00");
				listWeekOrMonth.add(dateFormat.format(sCalendar.getTime()) + " 23:59:59");
				sCalendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (Exception e) {
			throw new RuntimeException("时间获取失败");
		}
		return listWeekOrMonth;
	}

	//===================================================获取某一年某一月的最后一天===================================================

	/**
	 * 获取某年某月的最后一天
	 *
	 * @param year  年份
	 * @param month 月份
	 * @return yyyy-MM-dd
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR, year);
		//设置月份
		cal.set(Calendar.MONTH, month);
		//获取当月最小值
		int lastDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中的月份，当月+1月-1天=当月最后一天
		cal.set(Calendar.DAY_OF_MONTH, lastDay - 1);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	//===================================================获取某一年某一月的所有日期===================================================

	/**
	 * 获取某一年某一月的最大天数
	 *
	 * @param year  年
	 * @param month 月
	 * @return 最大天数
	 */
	public static int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		return a.get(Calendar.DATE);
	}

	/**
	 * 打印某年某月的所有日期
	 *
	 * @param year  年份
	 * @param month 月份
	 */
	public static List<String> dayReport(int year, int month) {
		List<String> days = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		int dayNumOfMonth = getDaysByYearMonth(year, month);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		//从一号开始
		cal.set(Calendar.DAY_OF_MONTH, 1);

		for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
			Date d = cal.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String df = simpleDateFormat.format(d);
			days.add(df);
		}
		return days;
	}

	//===================================================递增n个月和n个年之后的时间=============================================

	/**
	 * 递增n个月之后的时间
	 *
	 * @param startDay      开始时间的天号
	 * @param intervalMonth 间隔多少个月（递增多少个月）
	 * @param addCalendar   递增后的时间
	 *                      例子：开始时间为2000-10-31，间隔时间为1年，每次调用此方法进行递增，则2000-10-31 2000-11-30 2000-12-31 2001-01-31 2001-02-28 2001-03-31 2001-04-30
	 */
	public static void addMonth(Integer startDay, Integer intervalMonth, Calendar addCalendar) {
		//开始时间天号小于等于28号之前，可以直接递增下一个月
		if (startDay <= 28) {
			addCalendar.add(Calendar.MONTH, intervalMonth);
			return;
		}

		//假设开始时间是2022-01-29，递增1个月，那么递增到2月份就变为2022-02-28(2022年2月份最大天数为28)，再递增到3月份就变为2022-03-28却不是2022-03-29
		//错误：2022-01-29 2022-02-28 2022-03-28 正确：2022-01-29 2022-02-28 2022-03-29

		//设置当前时间的天号为1号
		addCalendar.set(Calendar.DAY_OF_MONTH, 1);
		//对当前时间进行递增jiange个月
		addCalendar.add(Calendar.MONTH, intervalMonth);
		//取得递增后的月份的最后一天
		String lastDayOfMonth = TimeConvertUtils.getLastDayOfMonth(addCalendar.get(Calendar.YEAR), addCalendar.get(Calendar.MONTH) + 1);
		//递增后的月份的最后一天的天号
		int lastDay = Integer.parseInt(lastDayOfMonth.split("-")[2]);
		//递增后的月份的最后一天，要小于开始时间
		if (lastDay < startDay) {
			//设置递增后的月份的天号为最后一天
			addCalendar.set(Calendar.DAY_OF_MONTH, lastDay);
		} else {
			//设置递增后的月份的天号为开始天号
			addCalendar.set(Calendar.DAY_OF_MONTH, startDay);
		}
	}

	/**
	 * 递增n个年之后的时间
	 *
	 * @param startDay     开始时间的天号
	 * @param intervalYear 间隔多少个年（递增多少个年）
	 * @param addCalendar  递增后的时间
	 *                     例子：开始时间为2000-02-29，间隔时间为1年，每次调用此方法进行递增，则2000-02-29 2001-02-28 2002-02-28 2003-02-28 2004-02-29
	 */
	public static void addYear(Integer startDay, Integer intervalYear, Calendar addCalendar) {
		//假设开始时间是2000-02-29，那么递增1年就变为2001-02-28(2001年2月份最大天数为28)，递增到2004年还是2004-02-28而不是2004-02-29
		//错误：2000-02-29 2001-02-28 2002-02-28 2003-02-28 2004-02-28 正确：2000-02-29 2001-02-28 2002-02-28 2003-02-28 2004-02-29

		//设置当前时间的天号为1号
		addCalendar.set(Calendar.DAY_OF_MONTH, 1);
		//对当前时间进行递增jiange个年
		addCalendar.add(Calendar.YEAR, intervalYear);
		//取得递增后那一年的月份的最后一天
		String lastDayOfMonth = TimeConvertUtils.getLastDayOfMonth(addCalendar.get(Calendar.YEAR), addCalendar.get(Calendar.MONTH) + 1);
		//递增后的月份的最后一天的天号
		int lastDay = Integer.parseInt(lastDayOfMonth.split("-")[2]);
		//递增后的月份的最后一天，要小于开始时间
		if (lastDay < startDay) {
			//设置递增后的月份的天号为最后一天
			addCalendar.set(Calendar.DAY_OF_MONTH, lastDay);
		} else {
			//设置递增后的月份的天号为开始天号
			addCalendar.set(Calendar.DAY_OF_MONTH, startDay);
		}
	}

	public static void main(String[] args) throws ParseException {
//		Map<String, String> map = new HashMap<>(10);
//		//指定范围的每一周的第一天和最后一天
//		map.put("startDate", "2022-10-03");
//		map.put("endDate", "2022-10-30");
//		List<String> week = rangeFirstAndEndDate("week", map);
//		System.out.println(week);
//		//两个两个取
//		for (int i = 0; i < week.size(); i += 2) {
//			System.out.println(week.get(i));
//			System.out.println(week.get(i + 1));
//			System.out.println();
//		}
//		//指定范围的每一月的第一天和最后一天
//		String start = "2022-01";
//		String end = "2022-12";
//		String[] endTime = end.split("-");
//		start = start + "-01";
//		end = getLastDayOfMonth(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]));
//		map.put("startDate", start);
//		map.put("endDate", end);
//		List<String> month = rangeFirstAndEndDate("month", map);
//		System.out.println(month);

		//指定范围的所有日期
//		String begin = "2022-12-10";
//		String ending = "2022-12-20";
//		List<String> all = rangeAllDate(begin, ending);
//		System.out.println(all);
//		//两个两个取
//		for (int i = 0; i < all.size(); i += 2) {
//			System.out.println(all.get(i));
//			System.out.println(all.get(i + 1));
//			System.out.println();
//		}
//		System.out.println("=============");
//		for (int i = 0; i < all.size(); i += 2) {
//			System.out.println(all.get(i).substring(0, 10));
//			System.out.println(all.get(i).substring(0, 7));
//		}


		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2000-02-29"));
		for (int i = 0; i < 20; i++) {
			addYear(29,1,startCalendar);
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(startCalendar.getTime()));
		}

	}
}
