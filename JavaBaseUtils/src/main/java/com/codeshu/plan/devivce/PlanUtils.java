package com.codeshu.plan.devivce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author CodeShu
 * @date 2022/11/17 15:22
 */
public class PlanUtils {
	public static void main(String[] args) {

	}

	public static boolean isNeedToRunPlan(Date now, IdomDetectionPlanDTO planDTO){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
						isNeedFlag = false;
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
	 * 日巡检计划 ：每日指定时间触发
	 * @param issueTimes 下发时间
	 * @param aheadMinute 提前下发时间
	 * @return 触发返回true
	 */
	public static boolean taskByDay(Date nowDate,List<String> issueTimes,Integer aheadMinute) {
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//格式化后和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//格式化后和下发时间进行相比
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
	 * 周巡检计划 ：每周指定周几触发
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
		//格式化后和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//格式化后和下发时间进行相比
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
		//格式化后和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//格式化后和下发时间进行相比
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
		//格式化后和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//格式化后和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		Calendar calendar = Calendar.getInstance();
		for (String issueTime : issueTimes) {
			calendar.setTime(nowDate);
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
			calendar.add(Calendar.MINUTE,-aheadMinute);
			Date aheadDate = calendar.getTime();
			String aheadTime = shortFormat.format(aheadDate);

			//符合月份-天
			if (isPatchBySeason(nowLong,startTime,intervalMonth)){

			}else {
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
		//格式化后和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//格式化后和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		Calendar calendar = Calendar.getInstance();
		for (String issueTime : issueTimes) {
			calendar.setTime(nowDate);
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
			calendar.add(Calendar.MINUTE,-aheadMinute);
			Date aheadDate = calendar.getTime();
			String aheadTime = shortFormat.format(aheadDate);

			//符合年份-月份-天
			if (isPatchByYear(nowLong,startTime,intervalYear)){

			}else {
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//将时间转为统一格式，用于比较，忽略时分秒
		Calendar startCalendar = Calendar.getInstance();
		Calendar nowCalendar = Calendar.getInstance();
		startCalendar.setTime(format.parse(start));
		nowCalendar.setTime(format.parse(now));

		//间隔为0表示每个月都需要，如果今天和开始时间的天数一样，返回true
		if (intervalMonth == 0){
			if (nowCalendar.get(Calendar.DAY_OF_MONTH) == startCalendar.get(Calendar.DAY_OF_MONTH)){
				return true;
			}else {
				return false;
			}
		}
		//间隔不为0
		while (true){
			//让计划开始时间递增指定间隔月份
			startCalendar.add(Calendar.MONTH,intervalMonth);
			//递增后大于当前时间，则返回false
			if (startCalendar.compareTo(nowCalendar) > 0){
				return false;
			}
			//递增后等于当前时间，则返回true
			if (startCalendar.compareTo(nowCalendar) == 0){
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//将时间转为统一格式，用于比较，忽略时分秒
		Calendar startCalendar = Calendar.getInstance();
		Calendar nowCalendar = Calendar.getInstance();
		startCalendar.setTime(format.parse(start));
		nowCalendar.setTime(format.parse(now));

		//间隔为0表示每个年都需要，如果今天和开始时间的天数且月份也一样，返回true
		if (intervalYear == 0){
			if (nowCalendar.get(Calendar.DAY_OF_MONTH) == startCalendar.get(Calendar.DAY_OF_MONTH)
					&& nowCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)) {
				return true;
			}else {
				return false;
			}
		}
		//间隔不为0
		while (true){
			//让计划开始时间递增指定间隔年份
			startCalendar.add(Calendar.YEAR,intervalYear);
			//递增后大于当前时间，则返回false
			if (startCalendar.compareTo(nowCalendar) > 0){
				return false;
			}
			//递增后等于当前时间，则返回true
			if (startCalendar.compareTo(nowCalendar) == 0){
				return true;
			}
		}
	}
}
