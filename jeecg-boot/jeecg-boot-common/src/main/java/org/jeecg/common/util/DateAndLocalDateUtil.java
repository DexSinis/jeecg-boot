package org.jeecg.common.util;

import java.time.*;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 * 张科
 * 2019年1月14日20:55:54
 */
public class DateAndLocalDateUtil {

    /**
     * localDate转Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("Asia/Shanghai"));
        Instant instant1 = zonedDateTime.toInstant();
        Date from = Date.from(instant1);
        return from;
    }

    /**
     * Date 转 localDate
     */
    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.of("Asia/Shanghai"));
        LocalDate localDate = zdt.toLocalDate();
        return localDate;
    }

    //获取月第一天
    public static Date getStartDayOfMonth(String date) {
        LocalDate now = LocalDate.parse(date);
        return getStartDayOfMonth(now);
    }

    public static Date getStartDayOfMonth(LocalDate date) {
        LocalDate now = date.with(TemporalAdjusters.firstDayOfMonth());
        return localDate2Date(now);
    }

    public static Date getStartDayOfMonth() {
        return getStartDayOfMonth(LocalDate.now(ZoneId.of("Asia/Shanghai")));
    }


    //获取月最后一天
    public static Date getEndDayOfMonth(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return getEndDayOfMonth(localDate);
    }

    public static Date getEndDayOfMonth(Date date) {
        return getEndDayOfMonth(date2LocalDate(date));
    }

    public static Date getEndDayOfMonth(LocalDate date) {
        LocalDate now = date.with(TemporalAdjusters.lastDayOfMonth());
        Date.from(now.atStartOfDay(ZoneId.of("Asia/Shanghai")).plusDays(1L).minusNanos(1L).toInstant());
        return localDate2Date(now);
    }

    public static Date getEndDayOfMonth() {
        return getEndDayOfMonth(LocalDate.now(ZoneId.of("Asia/Shanghai")));
    }


    //获取周第一天
    public static Date getStartDayOfWeek(String date) {
        LocalDate now = LocalDate.parse(date);
        return getStartDayOfWeek(now);
    }

    public static Date getStartDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate = localDate.with(fieldISO, 1);
        return localDate2Date(localDate);
    }

    //获取周最后一天
    public static Date getEndDayOfWeek(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return getEndDayOfWeek(localDate);
    }

    public static Date getEndDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate = localDate.with(fieldISO, 7);
        return Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Shanghai")).plusDays(1L).minusNanos(1L).toInstant());
    }

    //一天的开始
    public static LocalDateTime getStartOfDay(LocalDate date) {
        LocalDateTime time = LocalDateTime.of(date, LocalTime.MIN);
        return time;
    }

    public static LocalDateTime getStartOfDay() {
        return getStartOfDay(LocalDate.now(ZoneId.of("Asia/Shanghai")));
    }

    /**
     * 一天的结束
     */
    public static LocalDateTime getEndOfDay(LocalDate date) {
        LocalDateTime time = LocalDateTime.of(date, LocalTime.MAX);
        return time;
    }

    public static LocalDateTime getEndOfDay() {
        return getEndOfDay(LocalDate.now(ZoneId.of("Asia/Shanghai")));
    }



}