package com.codeshu.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CodeShu
 * @date 2023/1/11 17:08
 */
public class TestTimeUtils {
	public static void main(String[] args) throws ParseException {
		getRangeFirstAndEndDateWithWeek();
		getRangeFirstAndEndDateWithMonth();
		getRangeAllDate();
		getLastDayOfMonth();
		getDayReport();
		getDaysByYearMonth();
		addMonth();
		addYear();
	}

	public static void getRangeFirstAndEndDateWithWeek() {
		//对于周类型，startDate必须是周一，endDate必须是周日
		Map<String, Object> params = new HashMap<>();
		params.put("startDate", "2023-01-02");
		params.put("endDate", "2023-01-22");
		List<String> week = TimeConvertUtils.rangeFirstAndEndDate("week", params);
		//[2023-01-02 00:00:00, 2023-01-08 23:59:59, 2023-01-09 00:00:00, 2023-01-15 23:59:59, 2023-01-16 00:00:00, 2023-01-22 23:59:59]
		System.out.println(week);

		//两个两个取
		for (int i = 0; i < week.size(); i += 2) {
			System.out.println(week.get(i));
			System.out.println(week.get(i + 1));
			System.out.println();
		}
	}

	public static void getRangeFirstAndEndDateWithMonth() {
		//对于周类型，startDate必须是周一，endDate必须是周日
		Map<String, Object> params = new HashMap<>();
		params.put("startDate", "2023-01-01");
		params.put("endDate", "2023-03-31");
		//[2023-01-01 00:00:00, 2023-01-31 23:59:59, 2023-02-01 00:00:00, 2023-02-28 23:59:59, 2023-03-01 00:00:00, 2023-03-31 23:59:59]
		List<String> month = TimeConvertUtils.rangeFirstAndEndDate("month", params);
		System.out.println(month);
	}

	public static void getRangeAllDate() {
		String begin = "2023-02-26";
		String end = "2023-03-01";
		List<String> allDate = TimeConvertUtils.rangeAllDate(begin, end);
		//[2023-02-26 00:00:00, 2023-02-26 23:59:59, 2023-02-27 00:00:00, 2023-02-27 23:59:59, 2023-02-28 00:00:00, 2023-02-28 23:59:59, 2023-03-01 00:00:00, 2023-03-01 23:59:59]
		System.out.println(allDate);
	}

	public static void getLastDayOfMonth() {
		String lastDayOfMonth1 = TimeConvertUtils.getLastDayOfMonth(2020, 2);
		//2020-02-29
		System.out.println(lastDayOfMonth1);

		String lastDayOfMonth2 = TimeConvertUtils.getLastDayOfMonth(2022, 2);
		//2022-02-28
		System.out.println(lastDayOfMonth2);


		String lastDayOfMonth3 = TimeConvertUtils.getLastDayOfMonth(2023, 1);
		//2023-01-31
		System.out.println(lastDayOfMonth3);
	}

	public static void getDaysByYearMonth() {
		int daysByYearMonth1 = TimeConvertUtils.getDaysByYearMonth(2020, 2);
		int daysByYearMonth2 = TimeConvertUtils.getDaysByYearMonth(2022, 2);
		int daysByYearMonth3 = TimeConvertUtils.getDaysByYearMonth(2023, 1);
		//29
		System.out.println(daysByYearMonth1);
		//28
		System.out.println(daysByYearMonth2);
		//31
		System.out.println(daysByYearMonth3);
	}

	public static void getDayReport() {
		List<String> dayReport = TimeConvertUtils.dayReport(2023, 2);
		//[2023-02-01, 2023-02-02, 2023-02-03, ... , 2023-02-27, 2023-02-28]
		System.out.println(dayReport);
	}

	public static void addMonth() throws ParseException {
		//开始递增时间
		Calendar addCalendar = Calendar.getInstance();
		addCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-31"));

		//将2000-10-31，从31号开始递增，递增1个月
		TimeConvertUtils.addMonth(31, 1, addCalendar);
		//2000-11-30
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(addCalendar.getTime()));

		//将2000-11-30，从31号开始递增，递增1个月
		TimeConvertUtils.addMonth(31, 1, addCalendar);
		//2000-12-31
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(addCalendar.getTime()));

		//将2000-10-31，从31号开始递增，递增1个月
		TimeConvertUtils.addMonth(31, 1, addCalendar);
		//2001-01-31
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(addCalendar.getTime()));
	}

	public static void addYear() throws ParseException {
		//开始递增时间
		Calendar addCalendar = Calendar.getInstance();
		addCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-31"));

		//将2000-10-31，从31号开始递增，递增1年
		TimeConvertUtils.addYear(31, 1, addCalendar);
		//2001-10-31
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(addCalendar.getTime()));

		//将2001-10-31，从31号开始递增，递增1年
		TimeConvertUtils.addYear(31, 1, addCalendar);
		//2002-10-31
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(addCalendar.getTime()));

		//将2002-10-31，从31号开始递增，递增1年
		TimeConvertUtils.addYear(31, 1, addCalendar);
		//2003-10-31
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(addCalendar.getTime()));
	}
}
