package com.codeshu.plan;

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

		//重复周期类型
		String type = "day";
		//下发时间点
		String issueTime = "23:02";
		//计划开始时间
		String startTime = "2022-11-17";
		//计划结束时间
		String endTime = "2022-11-18";
		//提前下发时间
		int tiqian = 30;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
		calendar.add(Calendar.MINUTE,-tiqian);
		Date tiqianDate = calendar.getTime();
		String tiqianTime = simpleDateFormat2.format(tiqianDate);

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return;
		}
		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0){
			//当前时间到达提前下发时间或准确下发时间
			if (nowShort.compareTo(tiqianTime) == 0 || nowShort.compareTo(issueTime) == 0){
				heXinYeWu();
			}else {
				return;
			}
		}else { //未到达计划开始时间
			return;
		}
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
		Integer[] weekDay = {1,3,7};
		//提前下发时间
		int tiqian = 30;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
		calendar.add(Calendar.MINUTE,-tiqian);
		Date tiqianDate = calendar.getTime();
		String tiqianTime = simpleDateFormat2.format(tiqianDate);

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return;
		}
		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0){
			boolean flag = false;
			//nowCalendar.add(Calendar.DAY_OF_MONTH,-4);
			for (Integer week : weekDay) {
				//当前周为指定周
				if (week.equals(nowCalendar.get(Calendar.DAY_OF_WEEK) - 1)) {
					flag = true;
					break;
				}else if (week.equals(7) && nowCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
					flag = true;
					break;
				}
			}
			if (!flag){
				return;
			}
			//当前时间到达提前下发时间或准确下发时间
			if (nowShort.compareTo(tiqianTime) == 0 || nowShort.compareTo(issueTime) == 0){
				heXinYeWu();
			}else {
				return;
			}
		}else { //未到达计划开始时间
			return;
		}
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
		Integer[] monthDay = {17,18,19};
		//提前下发时间
		int tiqian = 30;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
		calendar.add(Calendar.MINUTE,-tiqian);
		Date tiqianDate = calendar.getTime();
		String tiqianTime = simpleDateFormat2.format(tiqianDate);

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return;
		}
		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0){
			boolean flag = false;
			//nowCalendar.add(Calendar.DAY_OF_MONTH,-4);
			for (Integer day : monthDay) {
				//当前周为指定周
				if (day.equals(nowCalendar.get(Calendar.DAY_OF_MONTH))) {
					flag = true;
					break;
				}
			}
			if (!flag){
				return;
			}
			//当前时间到达提前下发时间或准确下发时间
			if (nowShort.compareTo(tiqianTime) == 0 || nowShort.compareTo(issueTime) == 0){
				heXinYeWu();
			}else {
				return;
			}
		}else { //未到达计划开始时间
			return;
		}
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
		String startTime = "2000-11-18";
		//计划结束时间
		String endTime = "2022-12-20";
		//每隔2个月触发一次
		Integer monthDay =2;
		//提前下发时间
		int tiqian = 30;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
		calendar.add(Calendar.MINUTE,-tiqian);
		Date tiqianDate = calendar.getTime();
		String tiqianTime = simpleDateFormat2.format(tiqianDate);

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return;
		}
		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0){
			//符合月份-天
			if (isPatchBySeason(nowLong,startTime,monthDay)){

			}else {
				return;
			}
			//当前时间到达提前下发时间或准确下发时间
			if (nowShort.compareTo(tiqianTime) == 0 || nowShort.compareTo(issueTime) == 0){
				heXinYeWu();
			}else {
				return;
			}
		}else { //未到达计划开始时间
			return;
		}
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
		String startTime = "2000-11-18";
		//计划结束时间
		String endTime = "2022-11-20";
		//每隔2个月触发一次
		Integer monthDay = 2;
		//提前下发时间
		int tiqian = 30;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(issueTime.split(":")[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(issueTime.split(":")[1]));
		calendar.add(Calendar.MINUTE,-tiqian);
		Date tiqianDate = calendar.getTime();
		String tiqianTime = simpleDateFormat2.format(tiqianDate);

		//此计划过期：当前时间到达计划结束时间
		if (nowLong.compareTo(endTime) >= 0){
			return;
		}
		//此计划生效：当前时间到达计划开始时间
		if (nowLong.compareTo(startTime) >= 0){
			//符合年份-月份-天
			if (isPatchByYear(nowLong,startTime,monthDay)){

			}else {
				return;
			}
			//当前时间到达提前下发时间或准确下发时间
			if (nowShort.compareTo(tiqianTime) == 0 || nowShort.compareTo(issueTime) == 0){
				heXinYeWu();
			}else {
				return;
			}
		}else { //未到达计划开始时间
			return;
		}
	}

	/**
	 * 季计划：判断当前时间是否满足指定时间间隔
	 * @param now 当前时间
	 * @param start 计划开始时间
	 * @param jiange 间隔时间
	 * @return 满足返回true，否则返回false
	 */
	public static boolean isPatchBySeason(String now,String start,int jiange) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//将时间转为统一格式，用于比较，忽略时分秒
		Calendar startCalendar = Calendar.getInstance();
		Calendar nowCalendar = Calendar.getInstance();
		startCalendar.setTime(format.parse(start));
		nowCalendar.setTime(format.parse(now));
		System.out.println("现在的时间：" + now);
		System.out.println("开始的时间：" + start);

		//间隔为0表示每个月都需要，如果今天和开始时间的天数一样，返回true
		if (jiange == 0){
			if (nowCalendar.get(Calendar.DAY_OF_MONTH) == startCalendar.get(Calendar.DAY_OF_MONTH)){
				return true;
			}else {
				return false;
			}
		}
		//间隔不为0
		while (true){
			//让计划开始时间递增指定间隔月份
			startCalendar.add(Calendar.MONTH,jiange);
			System.out.println("间隔" + jiange + "个月时间：" + format.format(startCalendar.getTime()));
			//递增后大于当前时间，则返回false
			if (startCalendar.compareTo(nowCalendar) > 0){
				return false;
			}
			//递增后等于当前时间，则返回true
			if (startCalendar.compareTo(nowCalendar) == 0){
				System.out.println("匹配的时间：" + format.format(startCalendar.getTime()));
				return true;
			}
		}
	}

	/**
	 * 年计划：判断当前时间是否满足指定时间间隔
	 * @param now 当前时间
	 * @param start 计划开始时间
	 * @param jiange 间隔时间
	 * @return 满足返回true，否则返回false
	 */
	public static boolean isPatchByYear(String now,String start,int jiange) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//将时间转为统一格式，用于比较，忽略时分秒
		Calendar startCalendar = Calendar.getInstance();
		Calendar nowCalendar = Calendar.getInstance();
		startCalendar.setTime(format.parse(start));
		nowCalendar.setTime(format.parse(now));
		System.out.println("现在的时间：" + now);
		System.out.println("开始的时间：" + start);

		//间隔为0表示每个年都需要，如果今天和开始时间的天数且月份也一样，返回true
		if (jiange == 0){
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
			startCalendar.add(Calendar.YEAR,jiange);
			System.out.println("间隔" + jiange + "个年时间：" + format.format(startCalendar.getTime()));
			//递增后大于当前时间，则返回false
			if (startCalendar.compareTo(nowCalendar) > 0){
				return false;
			}
			//递增后等于当前时间，则返回true
			if (startCalendar.compareTo(nowCalendar) == 0){
				System.out.println("匹配的时间：" + format.format(startCalendar.getTime()));
				return true;
			}
		}
	}

	public static void heXinYeWu(){
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
