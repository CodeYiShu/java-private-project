package com.codeshu.plan;

import com.codeshu.time.TimeConvertUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author CodeShu
 * @date 2022/11/17 15:22
 */
public class PlanUtils {
	public static void main(String[] args) throws ParseException {
		//taskByDay();
		//taskByWeek();
		//taskByMonth();
		taskBySeason();
		//taskByYear();
	}

	//日巡检计划
	public static void taskByDay() throws ParseException {
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
		String nowLong = simpleDateFormat1.format(nowDate);  //和计划时间进行相比
		String nowShort = simpleDateFormat2.format(nowDate); //和下发时间进行相比

		//下发时间点
		String issueTime = "23:02";
		//计划开始时间
		String startTime = "2022-11-17";
		//计划结束时间
		String endTime = "2022-11-18";

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

	//周巡检计划
	public static void taskByWeek() throws ParseException {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
		String nowLong = simpleDateFormat1.format(nowDate);  //和计划时间进行相比
		String nowShort = simpleDateFormat2.format(nowDate); //和下发时间进行相比

		//下发时间点
		String issueTime = "23:02";
		//计划开始时间
		String startTime = "2022-11-17";
		//计划结束时间
		String endTime = "2022-11-18";
		//每周几执行
		Integer[] weekDay = {1, 3, 7};


		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0) {
			return;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
			boolean flag = false;
			for (Integer week : weekDay) {
				//当前周为指定周
				if (week.equals(nowCalendar.get(Calendar.DAY_OF_WEEK) - 1)) {
					flag = true;
					break;
				} else if (week.equals(7) && nowCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				return;
			}
			//当前时间到达下发时间
			if (nowShort.compareTo(issueTime) == 0) {
				heXinYeWu();
			}
		}
		//未到达计划开始时间

	}

	//月巡检计划
	public static void taskByMonth() throws ParseException {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
		String nowLong = simpleDateFormat1.format(nowDate);  //和计划时间进行相比
		String nowShort = simpleDateFormat2.format(nowDate); //和下发时间进行相比

		//下发时间点
		String issueTime = "10:46";
		//计划开始时间
		String startTime = "2022-11-01";
		//计划结束时间
		String endTime = "2022-11-20";
		//每月的几号执行
		Integer[] monthDay = {17, 18, 19};

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0) {
			return;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
			boolean flag = false;
			for (Integer day : monthDay) {
				//当前周为指定周
				if (day.equals(nowCalendar.get(Calendar.DAY_OF_MONTH))) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				return;
			}
			//当前时间到达提前下发时间或准确下发时间
			if (nowShort.compareTo(issueTime) == 0) {
				heXinYeWu();
			}
		}
		//未到达计划开始时间
	}

	//季巡检计划
	public static void taskBySeason() throws ParseException {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
		String nowLong = simpleDateFormat1.format(nowDate);  //和计划时间进行相比
		String nowShort = simpleDateFormat2.format(nowDate); //和下发时间进行相比

		//下发时间点
		String issueTime = "10:46";
		//计划开始时间
		String startTime = "2000-10-30";
		//计划结束时间
		String endTime = "2022-12-20";
		//每隔2个月触发一次
		int monthDay = 1;

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0) {
			return;
		}
		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
			//符合月份-天
			if (isPatchBySeason(nowLong, startTime, monthDay)) {

			} else {
				return;
			}
			//当前时间到达提前下发时间或准确下发时间
			if (nowShort.compareTo(issueTime) == 0) {
				heXinYeWu();
			}
		}
		//未到达计划开始时间
	}

	//年巡检计划
	public static void taskByYear() throws ParseException {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
		String nowLong = simpleDateFormat1.format(nowDate);  //和计划时间进行相比
		String nowShort = simpleDateFormat2.format(nowDate); //和下发时间进行相比

		//下发时间点
		String issueTime = "10:46";
		//计划开始时间
		String startTime = "2000-02-29";
		//计划结束时间
		String endTime = "2022-12-30";
		//每隔2个月触发一次
		int monthDay = 1;

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0) {
			return;
		}
		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
			//符合年份-月份-天
			if (isPatchByYear(nowLong, startTime, monthDay)) {

			} else {
				return;
			}
			//当前时间到达提前下发时间或准确下发时间
			if (nowShort.compareTo(issueTime) == 0) {
				heXinYeWu();
			}
		}
		//未到达计划开始时间

	}

	/**
	 * 季计划：判断当前时间是否满足指定时间间隔
	 *
	 * @param now    当前时间
	 * @param start  计划开始时间
	 * @param jiange 间隔时间
	 * @return 满足返回true，否则返回false
	 */
	public static boolean isPatchBySeason(String now, String start, int jiange) throws ParseException {
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
		if (jiange == 0) {
			return true;
		}
		//间隔不为0则需要根据间隔时间对开始计划时间进行递增月份
		while (true) {
			//从开始时间进行递增月份
			TimeConvertUtils.addMonth(startDay, jiange, addCalendar);
			System.out.println("间隔" + jiange + "个月时间：" + format.format(addCalendar.getTime()));
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
	 * @param now    当前时间
	 * @param start  计划开始时间
	 * @param jiange 间隔时间
	 * @return 满足返回true，否则返回false
	 */
	public static boolean isPatchByYear(String now, String start, int jiange) throws ParseException {
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
		if (jiange == 0) {
			return true;
		}
		//间隔不为0
		while (true) {
			//让计划开始时间递增指定间隔年份
			TimeConvertUtils.addYear(startDay, jiange, addCalendar);
			System.out.println("间隔" + jiange + "个年时间：" + format.format(addCalendar.getTime()));
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

	public static void heXinYeWu() {
		//关联任务
		List<String> tasks = new ArrayList<>();
		tasks.add("任务1");
		tasks.add("任务2");
		//指派对象
		List<String> person = new ArrayList<>();
		person.add("张三");

		System.out.println("为张三根据任务1和任务2，添加两条巡检记录");
	}
}
