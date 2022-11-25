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
 * 用于定时任务判断触发时间
 * @author CodeShu
 * @date 2022/11/14 15:46
 */
public class TimePlanUtils {
	public static void main(String[] args) throws ParseException {
		Map<String,String> map = new HashMap<>();

		//指定范围的每一周的第一天和最后一天
		map.put("startDate","2022-10-03");
		map.put("endDate","2022-10-30");
		List week = doDateByStatisticsTypeToWeek("week", map);
		System.out.println(week);

		//指定范围的每一月的第一天和最后一天
		String start = "2022-01";
		String end = "2022-12";
		String[] endTime = end.split("-");
		start = start + "-01";
		end = getLastDayOfMonth(Integer.parseInt(endTime[0]),Integer.parseInt(endTime[1]));
		map.put("startDate",start);
		map.put("endDate",end);
		List<String> month = doDateByStatisticsTypeToWeek("month", map);
		System.out.println(month);

		//指定范围的所有日期
		String begin = "2022-12-10";
		String ending = "2022-12-20";
		List<String> all = rangeAllDate(begin, ending);
		System.out.println(all);

		//两个两个取
		for (int i = 0; i < all.size(); i+=2) {
			System.out.println(all.get(i));
			System.out.println(all.get(i+1));
			System.out.println();
		}

		System.out.println("=============");
		for (int i = 0; i < all.size(); i+=2) {
			System.out.println(all.get(i).substring(0,10));
			System.out.println(all.get(i).substring(0,7));
		}

	}

	//===================================================根据时间范围获取第一天和最后一天===================================================

	/**
	 * 根据时间范围获取这段时间每一周的第一天和最后一天
	 * 根据时间范围获取这段时间每一月的第一天和最后一天
	 * @param statisticsType 类型 week month
	 * @param map startDate和endDate，都为yyyy-MM-dd
	 * @return 结果
	 */
	public static List<String> doDateByStatisticsTypeToWeek(String statisticsType, Map<String, String> map) throws ParseException{
		List<String> listWeekOrMonth = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = (String)map.get("startDate");
		String endDate = (String)map.get("endDate");
		Date sDate = dateFormat.parse(startDate);
		Calendar sCalendar = Calendar.getInstance();
		sCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		sCalendar.setTime(sDate);
		Date eDate = dateFormat.parse(endDate);
		Calendar eCalendar = Calendar.getInstance();
		eCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		eCalendar.setTime(eDate);
		boolean bool =true;
		//周
		if(statisticsType.equals("week")){
			while(sCalendar.getTime().getTime()<eCalendar.getTime().getTime()){
				if(bool||sCalendar.get(Calendar.DAY_OF_WEEK) == 2 ||sCalendar.get(Calendar.DAY_OF_WEEK) == 1){
					listWeekOrMonth.add(dateFormat.format(sCalendar.getTime()));
					bool = false;
				}
				sCalendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			listWeekOrMonth.add(dateFormat.format(eCalendar.getTime()));
			if(listWeekOrMonth.size() % 2 != 0){
				listWeekOrMonth.add(dateFormat.format(eCalendar.getTime()));
			}
		}else{ //月
			while(sCalendar.getTime().getTime()<eCalendar.getTime().getTime()){
				if(bool||sCalendar.get(Calendar.DAY_OF_MONTH)==1||sCalendar.get(Calendar.DAY_OF_MONTH)==sCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
					listWeekOrMonth.add(dateFormat.format(sCalendar.getTime()));
					bool = false;
				}
				sCalendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			listWeekOrMonth.add(dateFormat.format(eCalendar.getTime()));
			if(listWeekOrMonth.size()%2!=0){
				listWeekOrMonth.add(dateFormat.format(eCalendar.getTime()));
			}
		}

		//在每一周的第一天添加上00:00:00，最后一天添加上23:59:59
		for (int i = 0; i < listWeekOrMonth.size(); i++) {
			if (i % 2 == 0){
				listWeekOrMonth.set(i, listWeekOrMonth.get(i) + " 00:00:00");
				continue;
			}
			listWeekOrMonth.set(i, listWeekOrMonth.get(i) + " 23:59:59");
		}
		return listWeekOrMonth;
	}

	//===================================================获取某一年某一月的最后一天===================================================

	/**
	 * 获取某年某月的最后一天
	 * @param year 年份
	 * @param month 月份
	 * @return yyyy-MM-dd
	 */
	public static String getLastDayOfMonth(int year,int month) {
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month);
		//获取当月最小值
		int lastDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中的月份，当月+1月-1天=当月最后一天
		cal.set(Calendar.DAY_OF_MONTH, lastDay-1);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	//===================================================获取某一年某一月的所有日期===================================================

	/**
	 * 获取某一年某一月的最大天数
	 * @param year 年
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
	 * @param year 年份
	 * @param month 月份
	 */
	public static void dayReport(int year,int month) {

		Calendar cal = Calendar.getInstance();
		int dayNumOfMonth = getDaysByYearMonth(year, month);
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH,month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始

		for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
			Date d = cal.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String df = simpleDateFormat.format(d);
			System.out.println(df);
		}
	}

	//===================================================指定范围的所有日期===================================================
	/**
	 * 获取指定范围的所有日期
	 * @param begin 开始时间
	 * @param end 结束时间
	 * @return 此范围的所有日期
	 */
	public static List<String> rangeAllDate(String begin, String end) throws ParseException {
		List<String> listWeekOrMonth = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = dateFormat.parse(begin);
		Calendar sCalendar = Calendar.getInstance();
		sCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		sCalendar.setTime(sDate);
		Date eDate = dateFormat.parse(end);
		Calendar eCalendar = Calendar.getInstance();
		eCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		eCalendar.setTime(eDate);
		boolean bool =true;

		while(sCalendar.getTime().getTime() <= eCalendar.getTime().getTime()){
			listWeekOrMonth.add(dateFormat.format(sCalendar.getTime()) + " 00:00:00");
			listWeekOrMonth.add(dateFormat.format(sCalendar.getTime()) + " 23:59:59");
			sCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return listWeekOrMonth;
	}
}
