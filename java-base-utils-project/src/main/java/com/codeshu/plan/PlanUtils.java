package com.codeshu.plan;

import com.codeshu.time.TimeConvertUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 计划时间判断工具类
 * 简便版本
 *
 * @author codeshu
 */
public class PlanUtils {

	public static void main(String[] args) throws ParseException {
		Date nowDate = new Date();
		taskByDay(nowDate);
		taskByWeek(nowDate);
		taskByMonth(nowDate);
		taskBySeason(nowDate);
		taskByYear(nowDate);
	}

	/**
	 * 日巡检计划
	 * 每天特定下发时间点执行 如每天的13:53分执行
	 */
	public static void taskByDay(Date nowDate) {
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		//下发时间点
		String issueTime = "14:35";
		//计划开始时间
		String startTime = "2022-11-17";
		//计划结束时间
		String endTime = "2023-12-30";

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0) {
			return;
		}
		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
			//当前时间到达下发时间
			if (nowShort.compareTo(issueTime) == 0) {
				heXinYeWu();
			}
		}
		//未到达计划开始时间
	}

	/**
	 * 周巡检计划
	 * 每周的周几的特定下发时间点执行
	 */
	public static void taskByWeek(Date nowDate) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		//下发时间点
		String issueTime = "14:22";
		//计划开始时间
		String startTime = "2022-11-17";
		//计划结束时间
		String endTime = "2023-12-30";
		//每周几执行
		Integer[] weekDay = {1, 4, 7};


		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0) {
			return;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
			boolean flag = false;
			//判断当前周号是否为指定周号
			for (Integer week : weekDay) {
				//Calendar的周日是1，周一是2，....，周六是7
				if (week.equals(nowCalendar.get(Calendar.DAY_OF_WEEK) - 1)) {
					flag = true;
					break;
				} else if (week.equals(7) && nowCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					flag = true;
					break;
				}
			}
			//当前周号不在指定周号中
			if (!flag) {
				return;
			}
			//当前周号在指定周号中，且当前时间到达下发时间
			if (nowShort.compareTo(issueTime) == 0) {
				heXinYeWu();
			}
		}

		//未到达计划开始时间
	}

	/**
	 * 月巡检计划
	 * 每月的多少号的特定下发时间点执行
	 */
	public static void taskByMonth(Date nowDate) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		//下发时间点
		String issueTime = "14:24";
		//计划开始时间
		String startTime = "2022-11-01";
		//计划结束时间
		String endTime = "2023-12-30";
		//每月的几号执行
		Integer[] monthdayArray = {1, 3, 5};

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0) {
			return;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
			boolean flag = false;
			//判断当前天号是否为指定天号
			for (Integer day : monthdayArray) {
				//Calendar的天号从1开始
				if (day.equals(nowCalendar.get(Calendar.DAY_OF_MONTH))) {
					flag = true;
					break;
				}
			}
			//当前天号不在指定天号中
			if (!flag) {
				return;
			}
			//当前天号在指定天号中，且当前时间到达下发时间
			if (nowShort.compareTo(issueTime) == 0) {
				heXinYeWu();
			}
		}

		//未到达计划开始时间
	}

	/**
	 * 季巡检计划
	 * 每隔多少月的特定下发时间点执行
	 * 和开始计划时间的天号有关
	 * 例子：开始计划时间为2020-10-10，每隔2个月，下发时间点为10:00
	 * 结果：2020-12-10 10:00，2021-02-10 10:00，2021-04-10 10:00
	 */
	public static void taskBySeason(Date nowDate) throws ParseException {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		//下发时间点
		String issueTime = "14:25";
		//计划开始时间
		String startTime = "2000-10-01";
		//计划结束时间
		String endTime = "2023-12-30";
		//每隔1个月触发一次
		int intervalMonth = 1;

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0) {
			return;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
			//当前时间是否符合间隔和开始计划时间的天号
			if (isPatchBySeason(nowLong, startTime, intervalMonth)) {
				//当前时间到达下发时间
				if (nowShort.compareTo(issueTime) == 0) {
					heXinYeWu();
				}
			}
			//当前时间不符合间隔和开始计划时间的天号
		}
		//未到达计划开始时间
	}

	/**
	 * 年巡检计划
	 * 每隔多少年的特定下发时间点执行
	 * 和开始计划时间的月份和天号有关
	 * 例子：开始计划时间为2020-10-10，每隔2年，下发时间点为10:00
	 * 结果：2022-10-10 10:00，2024-10-10 10:00，2026-10-10 10:00
	 */
	public static void taskByYear(Date nowDate) throws ParseException {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		//下发时间点
		String issueTime = "10:46";
		//计划开始时间
		String startTime = "2000-12-01";
		//计划结束时间
		String endTime = "2022-12-30";
		//每隔1个年触发一次
		int intervalYear = 1;

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0) {
			return;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
			//当前时间是否符合间隔和开始计划时间的月份和天号
			if (isPatchByYear(nowLong, startTime, intervalYear)) {
				//当前时间到达提前下发时间或准确下发时间
				if (nowShort.compareTo(issueTime) == 0) {
					heXinYeWu();
				}
			}
			//当前时间不符合间隔和开始计划时间的月份和天号
		}

		//未到达计划开始时间
	}

	/**
	 * 季计划：判断当前时间是否满足指定时间间隔
	 *
	 * @param now           当前时间
	 * @param start         计划开始时间
	 * @param intervalMonth 间隔月份数
	 * @return 满足返回true，否则返回false
	 */
	public static boolean isPatchBySeason(String now, String start, int intervalMonth) throws ParseException {
		//将时间转为统一格式，用于比较，忽略时分秒
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//开始计划时间
		Calendar startCalendar = Calendar.getInstance();
		//现在时间
		Calendar nowCalendar = Calendar.getInstance();
		//从开始计划时间，进行递增的时间
		Calendar addCalendar = Calendar.getInstance();
		startCalendar.setTime(format.parse(start));
		nowCalendar.setTime(format.parse(now));
		addCalendar.setTime(format.parse(start));
		System.out.println("现在的时间：" + now);
		System.out.println("开始的时间：" + start);
		//开始的天号
		int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
		//今天的天号
		int nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);

		//开始天号和今天天号相同才可能执行，如果开始天号和今天天号不同，则直接不执行
		if (startDay != nowDay) {
			//开始时间是2020-11-30 今天天号是2022-11-31 30和31不同则不需要往下走
			return false;
		}

		//往下就是开始天号和今天天号相同，判断的是间隔月份是否满足
		//比如开始时间是2022-10-30，间隔2个月，而今天天号是2022-11-30是不满足的，而今天天号是2022-12-30是满足的

		//间隔为0表示每个月都需要
		if (intervalMonth == 0) {
			return true;
		}
		//间隔不为0则需要根据间隔时间对开始计划时间进行递增月份
		while (true) {
			//从开始时间进行递增月份
			TimeConvertUtils.addMonth(startDay, intervalMonth, addCalendar);
			System.out.println("间隔" + intervalMonth + "个月时间：" + format.format(addCalendar.getTime()));
			//递增后大于当前时间，则返回false
			if (addCalendar.compareTo(nowCalendar) > 0) {
				return false;
			}
			//递增后等于当前时间，则返回true
			if (addCalendar.compareTo(nowCalendar) == 0) {
				System.out.println("匹配的时间：" + format.format(addCalendar.getTime()));
				return true;
			}
		}
	}

	/**
	 * 年计划：判断当前时间是否满足指定时间间隔
	 *
	 * @param now          当前时间
	 * @param start        计划开始时间
	 * @param intervalYear 间隔时间
	 * @return 满足返回true，否则返回false
	 */
	public static boolean isPatchByYear(String now, String start, int intervalYear) throws ParseException {
		//将时间转为统一格式，用于比较，忽略时分秒
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//计划开始时间
		Calendar startCalendar = Calendar.getInstance();
		//现在时间
		Calendar nowCalendar = Calendar.getInstance();
		//在计划开始时间，进行递增的时间
		Calendar addCalendar = Calendar.getInstance();
		startCalendar.setTime(format.parse(start));
		nowCalendar.setTime(format.parse(now));
		addCalendar.setTime(format.parse(start));
		System.out.println("现在的时间：" + now);
		System.out.println("开始的时间：" + start);
		int nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);
		int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
		int nowMonth = nowCalendar.get(Calendar.MONTH);
		int startMonth = startCalendar.get(Calendar.MONTH);

		//开始月份、天号和今天月份、天号相同才可能执行，如果不同，则直接不执行
		if (nowDay != startDay && nowMonth != startMonth) {
			//开始时间是2020-11-30，那么今天是2021-11-31（天号不同）和2021-10-30（月份不同）都不符合触发条件
			return false;
		}

		//往下就是开始月份、天号和今天月份、天号相同，判断的是间隔年份是否满足
		//比如开始时间是2020-10-30，间隔2年，而今天天号是2021-10-30是不满足的，而今天天号是2022-10-30是满足的

		//间隔为0表示每个年都需要，如果今天和开始时间的天数且月份也一样，返回true
		if (intervalYear == 0) {
			return true;
		}
		//间隔不为0
		while (true) {
			//让计划开始时间递增指定间隔年份
			TimeConvertUtils.addYear(startDay, intervalYear, addCalendar);
			System.out.println("间隔" + intervalYear + "个年时间：" + format.format(addCalendar.getTime()));
			//递增后大于当前时间，则返回false
			if (addCalendar.compareTo(nowCalendar) > 0) {
				return false;
			}
			//递增后等于当前时间，则返回true
			if (addCalendar.compareTo(nowCalendar) == 0) {
				System.out.println("匹配的时间：" + format.format(addCalendar.getTime()));
				return true;
			}
		}
	}

	/**
	 * 核心业务
	 */
	public static void heXinYeWu() {
		//关联任务
		List<String> tasks = new ArrayList<>();
		tasks.add("任务1");
		tasks.add("任务2");
		//指派对象
		List<String> person = new ArrayList<>();
		person.add("张三");

		for (String p : person) {
			for (String task : tasks) {
				System.out.println("为" + p + "根据" + task + "添加1条巡检记录");
			}
		}
	}
}
