package com.woniu.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.Date;

/************************************** 
 * @program: connexus
 * @author: mike.ma
 * @desc: java8时间类工具类  
 * @date: 2019-07-12 16:37 
 **************************************/
public class LocalDateTimeUtils {


    private static ZoneId zone = ZoneId.systemDefault();

    private LocalDateTimeUtils() {

    }

    // 获取当前时间的LocalDateTime对象
    // LocalDateTime.now()

    // 根据年月日构建
    // LocalDateTime.of()

    // 比较日期先后
    // LocalDateTime.now().isBefore()
    // LocalDateTime.now().isAfter()

    /**
     * Date转换为LocalDateTime.
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date.
     *
     * @param time
     * @return java.utils.Date
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取指定日期的毫秒.
     *
     * @param time
     * @return java.lang.Long
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒.
     *
     * @param time
     * @return java.lang.Long
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式.
     *
     * @param time
     * @param pattern
     * @return java.lang.String
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式.
     *
     * @param pattern
     * @return java.lang.String
     */
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*.
     *
     * @param time
     * @param number
     * @param field
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*.
     *
     * @param time
     * @param number
     * @param field
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*.
     *
     * @param startTime
     * @param endTime
     * @param field
     * @return long
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12L + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00.
     *
     * @param time
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999.
     *
     * @param time
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }
    
    
    /**
     * 获取一天的结束时间，2017,7,22 23:59:59
     *
     * @param time
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDayEndWithSecond(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(0);
    }


    /**
     * 获取一天的开始时间，2017,7,22 00:00.
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public  static  LocalDateTime getDayStart(LocalDate date){
        return getDayStart(convertDateToLDT(Date.from(date.atStartOfDay().atZone(zone).toInstant())));
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999.
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDayEnd(LocalDate date) {

        return getDayEnd(convertDateToLDT(Date.from(date.atStartOfDay().atZone(zone).toInstant())));
    }

    /**
     * 获取日期是当年的第几周
     *
     * @param localDateTime
     * @return
     */
    public static int getWeekOfYear(LocalDateTime localDateTime) {
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
        return localDateTime.get(weekFields.weekOfYear());
    }

    public static String getISO8601TimestampStr(LocalDateTime time) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(time);
    }


    public static String getISO8601TimeStr(LocalDateTime time) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+08:00").format(time);
    }
}
