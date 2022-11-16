package com.woniu.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalDateUtils {
	
	private static ZoneId zone = ZoneId.systemDefault();
	
	static String defaultFormat="yyyy-MM-dd";
	

	/**
	 * Date 转LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate convertDateToLD(Date date) {	
		return LocalDateTime.ofInstant(date.toInstant(), zone).toLocalDate();
	}

	/**
	 * String 转 LocalDate
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static LocalDate parse(String str,String pattern) {
		return LocalDate.parse(str,DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * 获取指定日期的秒.
	 * @param date
	 * @return
	 */
	public static Long getSecondsByTime(LocalDate date) {	
		return date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
	}

	/**
	 * LocalDate转换为Date.
	 *
	 * @param time
	 * @return java.utils.Date
	 */
	public static Date convertLDToDate(LocalDate time) {
		if(time==null) return null;
		return Date.from(time.atStartOfDay().atZone(zone).toInstant());
	}

  
	
	public static LocalDateTime covertLDToLDT(LocalDate date,int hours,int minuters,int seconds) {
		return LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), hours, minuters,seconds);
	}
	
	
	public static LocalDate coverLDTToLD(LocalDateTime dateTime) {
		return dateTime.toLocalDate();
	}

	public static String formatDate(LocalDate localDate, String pattern) {
		if (localDate == null) {
			return "";
		} else {
			return localDate.format(DateTimeFormatter.ofPattern(pattern));
		}
	}

	
	public static String formatDate(LocalDate localDate) {
		if (localDate == null) {
			return "";
		} else {
			return localDate.format(DateTimeFormatter.ofPattern(defaultFormat));
		}
	}
	
	
	
	public static int getWeekOfYear(LocalDate LocalDate) {
		WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
		return LocalDate.get(weekFields.weekOfYear());
	}

	/**
	 * 从大到小
	 */
	public static List<LocalDate> getDescDateList(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> result = new ArrayList<>();
		if(endDate.compareTo(startDate) < 0 ){
			return  result;
		}
		while (true){
			result.add(endDate);
			if(endDate.compareTo(startDate) <= 0){
				break;
			}
			endDate= endDate.plusDays(-1);
		}
		return result;
	}

	/**
	 * 从小到大
	 */
	public  static List<LocalDate> getAscDateList(LocalDate startDate,LocalDate endDate) {
		List<LocalDate> result = new ArrayList<>();
		if(endDate.compareTo(startDate) < 0 ){
			return  result;
		}
		while (true){
			result.add(startDate);
			if(startDate.compareTo(endDate) >= 0){
				break;
			}
			startDate = startDate.plusDays(1);
		}
		return result;
	}
	
	public static void main(String[] a) {
		System.out.println(formatDate(LocalDate.now(), "yyyy-MM-dd") );
	}
}
