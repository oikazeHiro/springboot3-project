package com.oik.api.utils.time;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/10 16:00
 */
public class JodaTimeUtil {


    private static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PATTERN_TIME = "HH:mm:ss";

    /**
     * date类型 -> string类型
     *
     * @param date
     * @return
     */
    public static String date2Str(Date date) {
        if (date == null) {
            return "";
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(PATTERN_STANDARD);
    }

    /**
     * date类型 -> string类型
     *
     * @param date
     * @param formatPattern
     * @return
     */
    public static String date2Str(Date date, String formatPattern) {
        if (date == null) {
            return "";
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatPattern);
    }

    /**
     * string类型 -> date类型
     *
     * @param timeStr
     * @return
     */
    public static Date str2Date(String timeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(PATTERN_STANDARD);
        DateTime dateTime = dateTimeFormatter.parseDateTime(timeStr);
        return dateTime.toDate();
    }

    /**
     * 获取指定时间
     * @param year 年
     * @param month 月
     * @param day 日
     * @param hour 时
     * @param minute 分
     * @param seconds 秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getAssignedDateTime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer seconds) {
        DateTime dt = new DateTime(year, month, day, hour, minute, seconds);
        String date = dt.toString(PATTERN_STANDARD);
        return date;
    }
    /**
     * 获取指定日期
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getAssignedDate(Integer year, Integer month, Integer day) {
        LocalDate dt = new LocalDate(year, month, day);
        String date = dt.toString(PATTERN_DATE);
        return date;
    }
    /**
     * 获取指定时间
     * @param hour
     * @param minutes
     * @param seconds
     * @return
     */
    public static String getAssignedTime(Integer hour, Integer minutes, Integer seconds) {
        LocalTime dt = new LocalTime(hour, minutes, seconds);
        String date = dt.toString(PATTERN_TIME);
        return date;
    }
    /**
     * 判断date日期是否过期(与当前时刻比较)
     *
     * @param date
     * @return
     */
    public static boolean isTimeExpired(Date date) {
        if (null == date) {
            return true;
        }
        String timeStr = date2Str(date);
        return isBeforeNow(timeStr);
    }

    /**
     * 判断date日期是否过期(与当前时刻比较)
     *
     * @param timeStr
     * @return
     */
    public static boolean isTimeExpired(String timeStr) {
        if (StringUtils.isBlank(timeStr)) {
            return true;
        }

        return isBeforeNow(timeStr);
    }

    /**
     * 判断timeStr是否在当前时刻之前
     *
     * @param timeStr
     * @return
     */
    private static boolean isBeforeNow(String timeStr) {
        DateTimeFormatter format = DateTimeFormat.forPattern(PATTERN_STANDARD);
        DateTime dateTime = DateTime.parse(timeStr, format);
        return dateTime.isBeforeNow();
    }


    /**
     * 加天数
     *
     * @param date
     * @param days
     * @return
     */
    private static Date plusDays(Date date, Integer days) {
        if (null == date) {
            return null;
        }
        days = null == days ? 0 : days;

        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusDays(days);

        return dateTime.toDate();
    }

    /**
     * 减天数
     *
     * @param date
     * @param days
     * @return
     */
    private static Date minusDays(Date date, Integer days) {
        if (null == date) {
            return null;
        }
        days = null == days ? 0 : days;

        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.minusDays(days);

        return dateTime.toDate();
    }


    /**
     * 加分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    private static Date plusMinutes(Date date, Integer minutes) {
        if (null == date) {
            return null;
        }
        minutes = null == minutes ? 0 : minutes;

        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusMinutes(minutes);

        return dateTime.toDate();
    }

    /**
     * 减分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    private static Date minusMinutes(Date date, Integer minutes) {
        if (null == date) {
            return null;
        }
        minutes = null == minutes ? 0 : minutes;

        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.minusMinutes(minutes);

        return dateTime.toDate();
    }

    /**
     * 加月份
     *
     * @param date
     * @param months
     * @return
     */
    private static Date plusMonths(Date date, Integer months) {
        if (null == date) {
            return null;
        }
        months = null == months ? 0 : months;

        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusMonths(months);

        return dateTime.toDate();
    }

    /**
     * 减月份
     *
     * @param date
     * @param months
     * @return
     */
    private static Date minusMonths(Date date, Integer months) {
        if (null == date) {
            return null;
        }
        months = null == months ? 0 : months;

        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.minusMonths(months);

        return dateTime.toDate();
    }

    /**
     * 判断target是否在开始和结束时间之间
     *
     * @param target
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isBetween(Date target, Date startTime, Date endTime) {
        if (null == target || null == startTime || null == endTime) {
            return false;
        }

        DateTime dateTime = new DateTime(target);
        return dateTime.isAfter(startTime.getTime()) && dateTime.isBefore(endTime.getTime());
    }

    /**
     * 获取当前系统时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime() {
        DateTime dt = new DateTime();
        String time = dt.toString(PATTERN_STANDARD);
        return time;
    }
    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDate() {
        DateTime dt = new DateTime();
        String date = dt.toString(PATTERN_DATE);
        return date;
    }
    /**
     * 获取系统当前时间按照指定格式返回
     * @return
     */
    public static String getCurrentTime() {
        DateTime dt = new DateTime();
        String time = dt.toString(PATTERN_TIME);
        return time;
    }
    /**
     * 获取当前是一周星期几
     * @return
     */
    public static String getWeek() {
        DateTime dts = new DateTime();
        String week = null;
        switch (dts.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                week = "星期日";
                break;

            case DateTimeConstants.MONDAY:
                week = "星期一";
                break;

            case DateTimeConstants.TUESDAY:
                week = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                week = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                week = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                week = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                week = "星期六";
            default:
                break;
        }
        return week;
    }
    /**
     * 获取指定时间是一周的星期几
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getWeek(Integer year, Integer month, Integer day) {
        LocalDate dts = new LocalDate(year, month, day);
        String week = null;
        switch (dts.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                week = "星期日";
                break;
            case DateTimeConstants.MONDAY:
                week = "星期一";
                break;
            case DateTimeConstants.TUESDAY:
                week = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                week = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                week = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                week = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                week = "星期六";
                break;

            default:
                break;
        }
        return week;
    }
    /**
     * 计算两个时间相差多少天
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer diffDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        DateTime dt1 = new DateTime(startDate);
        DateTime dt2 = new DateTime(endDate);
        int day = Days.daysBetween(dt1, dt2).getDays();
        return Math.abs(day);
    }
}

