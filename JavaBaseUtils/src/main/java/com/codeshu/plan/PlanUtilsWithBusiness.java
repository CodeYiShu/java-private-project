package com.codeshu.plan;

import com.codeshu.time.TimeConvertUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 计划时间判断工具类结合业务
 * @author CodeShu
 * @date 2022/11/17 15:22
 */
public class PlanUtilsWithBusiness {
	public static void main(String[] args) {
		Date now = new Date();
		IdomDetectionPlanDTO planDTO = new IdomDetectionPlanDTO();
		boolean bool = isNeedToRunPlan(now, planDTO);
		System.out.println(bool);
	}

	public static boolean isNeedToRunPlan(Date now, IdomDetectionPlanDTO planDTO){
		//计划类型 1、日 2、周 3、月 4、季度 5、年
		Integer detectionType = planDTO.getDetectionType();
		//下发时间点 HH:mm
		List<String> issueTimes = planDTO.getIssueTimes();
		//计划开始时间
		Date planStartDate = planDTO.getPlanStartDate();
		//计划结束时间
		Date planEndDate = planDTO.getPlanEndDate();
		//提前下发时间
		Integer aheadMinute = planDTO.getAheadMinute();

		try {
			boolean isNeedFlag = false;
			if (detectionType != null && issueTimes != null && issueTimes.size() > 0 && planStartDate != null && planEndDate != null && aheadMinute != null) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String planStartTime = simpleDateFormat.format(planStartDate);
				String planEndTime = simpleDateFormat.format(planEndDate);
				switch (detectionType) {
					case 1:
						isNeedFlag = taskByDay(now,issueTimes,aheadMinute);
						break;
					case 2:
						String[] weekDay = planDTO.getWeekRepeat().split(",");
						isNeedFlag = taskByWeek(now,issueTimes,aheadMinute,weekDay);
						break;
					case 3:
						String[] monthDay = planDTO.getDayRepeat().split(",");
						isNeedFlag = taskByMonth(now,issueTimes,aheadMinute,monthDay);
						break;
					case 4:
						Integer repeatMonth = planDTO.getRepeatPeriod();
						isNeedFlag = taskBySeason(now,issueTimes,planStartTime,aheadMinute,repeatMonth);
						break;
					case 5:
						Integer repeatYear = planDTO.getRepeatPeriod();
						isNeedFlag = taskByYear(now,issueTimes,planStartTime,aheadMinute,repeatYear);
						break;
					default:
				}
				return isNeedFlag;
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 日巡检计划
	 * 每天特定下发时间点执行 如每天的13:53分执行
	 * @param issueTimes 下发时间
	 * @param aheadMinute 提前下发时间
	 * @return 触发返回true
	 */
	public static boolean taskByDay(Date nowDate,List<String> issueTimes,Integer aheadMinute) {
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		Calendar calendar = Calendar.getInstance();
		//下发时间可以多个，依次判断
		for (String issueTime : issueTimes) {
			//提前下发时间
			calendar.setTime(nowDate);
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
			calendar.add(Calendar.MINUTE,-aheadMinute);
			Date aheadDate = calendar.getTime();
			String aheadTime = shortFormat.format(aheadDate);
			//具有提前下发时间，则当前时间到达提前下发时间进行下发
			if (aheadMinute != 0){
				if (nowShort.compareTo(aheadTime) == 0){
					return true;
				}
			}else{ //不具有提前下发时间，则当前时间到达下发时间进行下发
				if (nowShort.compareTo(issueTime) == 0){
					return true;
				}
			}
		}

		//不符合下发时间点
		return false;
	}

	/**
	 * 周巡检计划
	 * 每周指定周几触发
	 * @param issueTimes 下发时间
	 * @param aheadMinute 提前下发时间
	 * @param weekDay 指定周几
	 * @return 触发返回true
	 */
	public static boolean taskByWeek(Date nowDate,List<String> issueTimes,Integer aheadMinute,String[] weekDay) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);
		//转为整型
		Integer[] weekDays = new Integer[weekDay.length];
		for (int i = 0; i < weekDay.length; i++) {
			weekDays[i] = Integer.valueOf(weekDay[i]);
		}

		Calendar calendar = Calendar.getInstance();
		for (String issueTime : issueTimes) {
			//提前下发时间
			calendar.setTime(nowDate);
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
			calendar.add(Calendar.MINUTE, -aheadMinute);
			Date aheadDate = calendar.getTime();
			String aheadTime = shortFormat.format(aheadDate);
			boolean flag = false;
			for (Integer week : weekDays) {
				//当前星期n为指定星期n
				if (week.equals(nowCalendar.get(Calendar.DAY_OF_WEEK) - 1)) {
					flag = true;
					break;
				} else if (week.equals(7) && nowCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					flag = true;
					break;
				}
			}
			//当前星期n不为指定星期n
			if (!flag) {
				return false;
			}
			//具有提前下发时间，则当前时间到达提前下发时间进行下发
			if (aheadMinute != 0){
				if (nowShort.compareTo(aheadTime) == 0){
					return true;
				}
			}else{ //不具有提前下发时间，则当前时间到达下发时间进行下发
				if (nowShort.compareTo(issueTime) == 0){
					return true;
				}
			}
		}

		//不符合下发时间点
		return false;
	}

	/**
	 * 月巡检计划 ：每月指定多少号触发
	 * 每月的多少号的特定下发时间点执行
	 * @param issueTimes 下发时间
	 * @param aheadMinute 提前下发时间
	 * @param monthDay 指定几号
	 * @return 触发返回true
	 */
	public static boolean taskByMonth(Date nowDate,List<String> issueTimes,Integer aheadMinute,String[] monthDay) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);
		//转为整型
		Integer[] monthDays = new Integer[monthDay.length];
		for (int i = 0; i < monthDay.length; i++) {
			monthDays[i] = Integer.valueOf(monthDay[i]);
		}

		Calendar calendar = Calendar.getInstance();
		for (String issueTime : issueTimes) {
			calendar.setTime(nowDate);
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
			calendar.add(Calendar.MINUTE,-aheadMinute);
			Date aheadDate = calendar.getTime();
			String aheadTime = shortFormat.format(aheadDate);

			boolean flag = false;
			for (Integer day : monthDays) {
				//本天为指定n号
				if (day.equals(nowCalendar.get(Calendar.DAY_OF_MONTH))) {
					flag = true;
					break;
				}
			}
			if (!flag){
				return false;
			}
			//具有提前下发时间，则当前时间到达提前下发时间进行下发
			if (aheadMinute != 0){
				if (nowShort.compareTo(aheadTime) == 0){
					return true;
				}
			}else{ //不具有提前下发时间，则当前时间到达下发时间进行下发
				if (nowShort.compareTo(issueTime) == 0){
					return true;
				}
			}
		}
		//不符合下发时间点
		return false;
	}

	/**
	 * 季巡检计划 ：指定每隔多少个月触发
	 * 每隔多少月的特定下发时间点执行
	 * 和开始计划时间的天号有关
	 * 例子：开始计划时间为2020-10-10，每隔2个月，下发时间点为10:00
	 * 结果：2020-12-10 10:00，2021-02-10 10:00，2021-04-10 10:00
	 * @param issueTimes 下发时间
	 * @param startTime 计划开始时间
	 * @param aheadMinute 提前下发时间
	 * @param intervalMonth 间隔多少月
	 * @return 触发返回true
	 */
	public static boolean taskBySeason(Date nowDate,List<String> issueTimes,String startTime,Integer aheadMinute,Integer intervalMonth) throws ParseException {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		Calendar calendar = Calendar.getInstance();
		for (String issueTime : issueTimes) {
			calendar.setTime(nowDate);
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
			calendar.add(Calendar.MINUTE,-aheadMinute);
			Date aheadDate = calendar.getTime();
			String aheadTime = shortFormat.format(aheadDate);

			//当前时间是否符合间隔和开始计划时间的天号
			if (isPatchBySeason(nowLong,startTime,intervalMonth)){
				//具有提前下发时间，则当前时间到达提前下发时间进行下发
				if (aheadMinute != 0){
					if (nowShort.compareTo(aheadTime) == 0){
						return true;
					}
				}else{ //不具有提前下发时间，则当前时间到达下发时间进行下发
					if (nowShort.compareTo(issueTime) == 0){
						return true;
					}
				}
			}else {
				//当前时间不符合间隔和开始计划时间的天号
				return false;
			}
		}

		//不符合下发时间点
		return false;
	}

	/**
	 * 年巡检计划 ：指定每隔多少个年触发
	 * @param issueTimes 下发时间
	 * @param startTime 计划开始时间
	 * @param aheadMinute 提前下发时间
	 * @param intervalYear 间隔多少年
	 * @return 触发返回true
	 */
	public static boolean taskByYear(Date nowDate,List<String> issueTimes,String startTime,Integer aheadMinute,Integer intervalYear) throws ParseException {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		Calendar calendar = Calendar.getInstance();
		for (String issueTime : issueTimes) {
			calendar.setTime(nowDate);
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
			calendar.add(Calendar.MINUTE,-aheadMinute);
			Date aheadDate = calendar.getTime();
			String aheadTime = shortFormat.format(aheadDate);

			//当前时间是否符合间隔和开始计划时间的月份和天号
			if (isPatchByYear(nowLong,startTime,intervalYear)){
				//具有提前下发时间，则当前时间到达提前下发时间进行下发
				if (aheadMinute != 0){
					if (nowShort.compareTo(aheadTime) == 0){
						return true;
					}
				}else{ //不具有提前下发时间，则当前时间到达下发时间进行下发
					if (nowShort.compareTo(issueTime) == 0){
						return true;
					}
				}
			}else {
				//当前时间不否符合间隔和开始计划时间的月份和天号
				return false;
			}
		}

		//未达到下发时间点
		return false;
	}

	/**
	 * 季计划：判断当前时间是否满足指定时间间隔
	 * @param now 当前时间
	 * @param start 计划开始时间
	 * @param intervalMonth 间隔时间
	 * @return 满足返回true，否则返回false
	 */
	public static boolean isPatchBySeason(String now,String start,int intervalMonth) throws ParseException {
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
		//开始的天号
		int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
		//今天的天号
		int nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);

		//开始天号和今天天号相同才可能执行，如果开始天号和今天天号不同，则直接不执行
		if (startDay != nowDay){
			//开始时间是2020-11-30 今天天号是2022-11-31 30和31不同则不需要往下走
			return false;
		}

		//往下就是开始天号和今天天号相同，判断的是间隔月份是否满足
		//比如开始时间是2022-10-30，间隔2个月，而今天天号是2022-11-30是不满足的，而今天天号是2022-12-30是满足的

		//间隔为0表示每个月都需要
		if (intervalMonth == 0){
			return true;
		}

		//间隔不为0则需要根据间隔时间对开始计划时间进行递增月份
		while (true){
			//从开始时间进行递增月份
			TimeConvertUtils.addMonth(startDay,intervalMonth,addCalendar);
			//递增后大于当前时间，则返回false
			if (addCalendar.compareTo(nowCalendar) > 0){
				return false;
			}
			//递增后等于当前时间，则返回true
			if (addCalendar.compareTo(nowCalendar) == 0){
				return true;
			}
		}

	}

	/**
	 * 年计划：判断当前时间是否满足指定时间间隔
	 * @param now 当前时间
	 * @param start 计划开始时间
	 * @param intervalYear 间隔时间
	 * @return 满足返回true，否则返回false
	 */
	public static boolean isPatchByYear(String now,String start,int intervalYear) throws ParseException {
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
		int nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);
		int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
		int nowMonth = nowCalendar.get(Calendar.MONTH);
		int startMonth = startCalendar.get(Calendar.MONTH);

		//开始月份、天号和今天月份、天号相同才可能执行，如果不同，则直接不执行
		if (nowDay != startDay && nowMonth != startMonth){
			//开始时间是2020-11-30，那么今天是2021-11-31（天号不同）和2021-10-30（月份不同）都不符合触发条件
			return false;
		}

		//往下就是开始月份、天号和今天月份、天号相同，判断的是间隔年份是否满足
		//比如开始时间是2020-10-30，间隔2年，而今天天号是2021-10-30是不满足的，而今天天号是2022-10-30是满足的

		//间隔为0表示每个年都需要，如果今天和开始时间的天数且月份也一样，返回true
		if (intervalYear == 0){
			return true;
		}

		//间隔不为0
		while (true){
			//让计划开始时间递增指定间隔年份
			TimeConvertUtils.addYear(startDay,intervalYear,addCalendar);
			//递增后大于当前时间，则返回false
			if (addCalendar.compareTo(nowCalendar) > 0){
				return false;
			}
			//递增后等于当前时间，则返回true
			if (addCalendar.compareTo(nowCalendar) == 0){
				return true;
			}
		}
	}
}
