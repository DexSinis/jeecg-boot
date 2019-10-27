package org.jeecg.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 *时间格式化工具类
 */
public class DateUtil {
	public static final String DATE_TIME_FORMAT = "YYYY-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "YYYY-MM-dd";
	public static final String DATE_HOUR_FORMAT = "HH:mm:ss";


	 // 格式化时间
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	//设置当前月份日期,日期超过当前月份最大天数月份进1,如传6月5日,1 => 6月1日 6月5日,33 => 7月3日
	public static Date setMonthDay(Date date, int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,day);
		return calendar.getTime();
	}

	//获取当前月份日期的最后一天日期,如传6月5日进来,获取6月(最后一天)日
	public static Date getMonthLastDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.add(Calendar.MONTH,1);
		calendar.add(Calendar.DAY_OF_MONTH,-1);
		return calendar.getTime();
	}

	/***
	 * date 转localDate
	 * @param date
	 * @return
	 */
	public static LocalDate datePastLocalDate(Date date) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDate localDate = instant.atZone(zoneId).toLocalDate();
		return localDate;

	}

	/***
	 * date 转localDateTime
	 * @param date
	 * @return
	 */
	public static  LocalDateTime datePastLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		return localDateTime;

	}

	/**
	 * localDate 转date
	 *
	 * @param localDate
	 * @return
	 */
	public static Date localDatePastDate(LocalDate localDate) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}

	/**
	 * localDateTime 转date
	 *
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimePastDate(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}


}
