package com.codeshu.plan;

import com.codeshu.time.TimeConvertUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 备份智能设备运维系统
 * @author CodeShu
 * @date 2022/11/17 15:22
 */
public class PlanUtils2 {
	public static void main(String[] args) throws ParseException {

	}

	public static boolean isNeedToRunPlan(IdomDetectionPlanDTO planDTO){
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
						isNeedFlag = taskByDay(issueTimes,planStartTime,planEndTime,aheadMinute);
						break;
					case 2:
						String[] weekDay = planDTO.getWeekRepeat().split(",");
						isNeedFlag = taskByWeek(issueTimes,planStartTime,planEndTime,aheadMinute,weekDay);
						break;
					case 3:
						String[] monthDay = planDTO.getDayRepeat().split(",");
						isNeedFlag = taskByMonth(issueTimes,planStartTime,planEndTime,aheadMinute,monthDay);
						break;
					case 4:
						Integer repeatMonth = planDTO.getRepeatPeriod();
						isNeedFlag = taskBySeason(issueTimes,planStartTime,planEndTime,aheadMinute,repeatMonth);
						break;
					case 5:
						Integer repeatYear = planDTO.getRepeatPeriod();
						isNeedFlag = taskByYear(issueTimes,planStartTime,planEndTime,aheadMinute,repeatYear);
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
	 * @param startTime 计划开始时间
	 * @param endTime 计划截至时间
	 * @param aheadMinute 提前下发时间
	 * @return 触发返回true
	 */
	public static boolean taskByDay(List<String> issueTimes,String startTime,String endTime,Integer aheadMinute) {
		Date nowDate = new Date();
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//格式化后和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//格式化后和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return false;
		}
		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0) {
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
				//当前时间到达提前下发时间或准确下发时间
				if (nowShort.compareTo(aheadTime) == 0 || nowShort.compareTo(issueTime) == 0){
					return true;
				}
			}
		}else { //未达到计划开始时间
			return false;
		}
		//不符合下发时间点
		return false;
	}

	/**
	 * 周巡检计划 ：每周指定周几触发
	 * @param issueTimes 下发时间
	 * @param startTime 计划开始时间
	 * @param endTime 计划截至时间
	 * @param aheadMinute 提前下发时间
	 * @param weekDay 指定周几
	 * @return 触发返回true
	 */
	public static boolean taskByWeek(List<String> issueTimes,String startTime,String endTime,Integer aheadMinute,String[] weekDay) {
		Date nowDate = new Date();
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

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return false;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0){
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
				//当前时间到达提前下发时间或准确下发时间
				if (nowShort.compareTo(aheadTime) == 0 || nowShort.compareTo(issueTime) == 0){
					return true;
				}
			}
		}else { //未达到计划开始时间
			return false;
		}
		//不符合下发时间点
		return false;
	}

	/**
	 * 月巡检计划 ：每月指定多少号触发
	 * @param issueTimes 下发时间
	 * @param startTime 计划开始时间
	 * @param endTime 计划截至时间
	 * @param aheadMinute 提前下发时间
	 * @param monthDay 指定几号
	 * @return 触发返回true
	 */
	public static boolean taskByMonth(List<String> issueTimes,String startTime,String endTime,Integer aheadMinute,String[] monthDay) {
		Date nowDate = new Date();
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

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return false;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0){
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
				//当前时间到达提前下发时间或准确下发时间
				return nowShort.compareTo(aheadTime) == 0 || nowShort.compareTo(issueTime) == 0;
			}
		}else { //未达到计划开始时间
			return false;
		}
		//不符合下发时间点
		return false;
	}

	/**
	 * 季巡检计划 ：指定每隔多少个月触发
	 * @param issueTimes 下发时间
	 * @param startTime 计划开始时间
	 * @param endTime 计划截至时间
	 * @param aheadMinute 提前下发时间
	 * @param intervalMonth 间隔多少月
	 * @return 触发返回true
	 */
	public static boolean taskBySeason(List<String> issueTimes,String startTime,String endTime,Integer aheadMinute,Integer intervalMonth) throws ParseException {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//格式化后和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//格式化后和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return false;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0){
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
				//当前时间到达提前下发时间或准确下发时间
				if (nowShort.compareTo(aheadTime) == 0 || nowShort.compareTo(issueTime) == 0){
					return true;
				}else {
					return false;
				}
			}
		}else { //未达到计划开始时间
			return false;
		}
		//不符合下发时间点
		return false;
	}

	/**
	 * 年巡检计划 ：指定每隔多少个年触发
	 * @param issueTimes 下发时间
	 * @param startTime 计划开始时间
	 * @param endTime 计划截至时间
	 * @param aheadMinute 提前下发时间
	 * @param intervalYear 间隔多少年
	 * @return 触发返回true
	 */
	public static boolean taskByYear(List<String> issueTimes,String startTime,String endTime,Integer aheadMinute,Integer intervalYear) throws ParseException {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat shortFormat = new SimpleDateFormat("HH:mm");
		//格式化后和计划时间进行相比
		String nowLong = longFormat.format(nowDate);
		//格式化后和下发时间进行相比
		String nowShort = shortFormat.format(nowDate);

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return false;
		}

		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0){
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
		}else { //未达到计划开始时间
			return false;
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
		//开始的天号
		int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);

		//间隔为0表示每个月都需要，如果今天和开始时间的天数一样，返回true
		if (intervalMonth == 0){
			if (nowCalendar.get(Calendar.DAY_OF_MONTH) == startCalendar.get(Calendar.DAY_OF_MONTH)){
				return true;
			}else {
				return false;
			}
		}
		//间隔不为0则需要进行递增开始计划时间
		while (true){
			//二月份需要特殊处理，假设开始时间是2022-01-29，那么递增到2月份就变为2022-02-28(2022年2月份最大天数为28)，再递增到3月份就变为2022-03-28却不是2022-03-29
			//错误：2022-01-29 2022-02-28 2022-03-28 正确：2022-01-29 2022-02-28 2022-03-29
			//如果是2月份，即将递增为3月份
			if (startCalendar.get(Calendar.MONTH) + 1 == 2){
				//得到今年2月份的最后一天，可能是28，也可能是29
				String lastDayOfMonth = TimeConvertUtils.getLastDayOfMonth(startCalendar.get(Calendar.YEAR), 2);
				int twoMonthLastDay = Integer.parseInt(lastDayOfMonth.split("-")[2]);
				//如果开始天号小于2月份的最后一天，则可以直接递增到3月份
				if (startDay <= twoMonthLastDay){
					startCalendar.add(Calendar.MONTH,intervalMonth);
				}else {
					//如果开始天好大于2月份的最后一天，3月份需要恢复为开始天号，再去递增到4月份
					startCalendar.add(Calendar.MONTH,intervalMonth);
					startCalendar.set(Calendar.DAY_OF_MONTH,startDay);
				}
			}else {
				//如果不是2月份，则可以直接递增
				startCalendar.add(Calendar.MONTH,intervalMonth);
			}

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
