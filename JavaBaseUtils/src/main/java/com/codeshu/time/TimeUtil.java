package com.codeshu.time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author CodeShu
 * @date 2022/11/13 17:25
 */
public class TimeUtil {
	public static void main(String[] args) {
		List<String> dates = getCurrent24Hour();
		System.out.println(dates);
		getCurrentWeek();
		getCurrentMonth();
		getCurrentYear();
	}

	public static List<String> getCurrent24Hour(){
		List<String> dates = new ArrayList<>();

		//现在 2022/11/13 17:00:00
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//起始时间 2022/11/13 00:00:00
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		dates.add(start);

		//中间时间 2022/11/13 00:01:00 ~ 2022/11/13 00:22:00
		for (int i = 1; i < 23; i++){
			calendar.set(Calendar.HOUR_OF_DAY, i);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			String zhongjian = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
			dates.add(zhongjian);
		}

		//结束时间 2022/11/13 23:59:59
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		dates.add(end);
		return dates;
	}

	public static List<String> getCurrentWeek(){
		List<String> dates = new ArrayList<>();

		//现在 2022/11/13 17:00:00
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//起始时间 2022/11/7 00:00:00
		calendar.add(Calendar.WEEK_OF_MONTH, 0);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		System.out.println(start);

		calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		System.out.println(end);

		return dates;
	}

	public static List<String> getCurrentMonth(){
		List<String> dates = new ArrayList<>();

		//现在 2022/11/13 17:00:00
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//起始时间 2022/11/7 00:00:00
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		System.out.println(start);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		System.out.println(end);

		return dates;
	}

	public static List<String> getCurrentYear(){
		List<String> dates = new ArrayList<>();

		//现在 2022/11/13 17:00:00
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//起始时间 2022/11/7 00:00:00
		String start = new SimpleDateFormat("yyyy").format(new Date()) + "-01-01" + " 00:00:00";
		System.out.println(start);

		calendar.set(Calendar.MONTH,calendar.getActualMaximum(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		System.out.println(end);

		return dates;
	}
}
