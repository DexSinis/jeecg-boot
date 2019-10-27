package org.jeecg.util.date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

public class DateUtil {
    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

    public static final String DATE_FORMAT_HMS = "HH:mm:ss";

    public static final String DATE_FORMAT_HM = "HH:mm";

    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    
    public static String getLiveDayString(Long birthday) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        int theYear = calendar.get(Calendar.YEAR);
        int theMonth = calendar.get(Calendar.MONTH) + 1;
        int theDay = calendar.get(Calendar.DATE);
        Date date = new Date(birthday);
        calendar.setTime(date);

        int birthYear = calendar.get(Calendar.YEAR);
        int birthMonth = calendar.get(Calendar.MONTH) + 1;
        int birthDay = calendar.get(Calendar.DATE);

        int year = theYear - birthYear;
        int month = theMonth - birthMonth;
        int day = theDay - birthDay;
        if (day < 0) {
            calendar.add(Calendar.MONTH, +1);
            int days = calendar.getActualMaximum(Calendar.DATE);
            day = day + days;
            birthMonth = calendar.get(Calendar.MONTH) + 1;
//            month = theMonth - birthMonth;
            month--;
        }
        if (month < 0) {
            year--;
            month = month + 12;
        }

        String retstr = "";
        if (year != 0) {
            retstr += (year + "岁");
        }
        if (month != 0) {
            retstr += (month + "个月");
        }
        if (day != 0) {
            retstr += (day + "天");
        }
        return StringUtils.isEmpty(retstr)?"今天":retstr;
    }

//    public static void main(String[] args) {
//        System.out.println(System.currentTimeMillis());
//        try {
//            Date da = DateUtils.parseDate("2018-09-07", "yyyy-MM-dd");
//
//            System.out.println(getLiveDayString(1523203200000l));
//            System.out.println(getLiveDayString(da.getTime()));
//            System.out.println(da.getTime());
//
//            String str = "asdfsdfsd\nAfasdfsadfsadf";
//            System.out.println(str);
//            System.out.println(str.replaceAll("\\n", "<br/>"));
//
//
//        } catch (Exception e) {
//        }
//        Date now = formatToDate("20170102", "yyyyMMdd");
//        Date end = formatToDate("20161231", "yyyyMMdd");
//        System.out.println(getDayBetween(end, now));
//        System.out.println(getCurrentDateWithTimeStr("10:00"));
//        System.out.println(getCurrentDateWithTimeStr("1:00"));
//        System.out.println(getCurrentDateWithTimeStr("1:30"));
//        System.out.println(getCurrentDateWithoutTimestamp());
//
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.MINUTE, 30);
//        Calendar start = DateUtil.getCurrentDateWithTimeStr("17:00");
//        Calendar ends = DateUtil.getCurrentDateWithTimeStr("17:30");
//
//        if (c.getTimeInMillis() > start.getTimeInMillis() && c.getTimeInMillis() < ends.getTimeInMillis()){
//            System.out.println("-----");
//        }
//
//
//        System.out.println(formatToDate("2017-11-22 15:30:00",DATE_FORMAT_YMDHMS).getTime());
//        System.out.println(formatToDate("2017-11-22 17:15:00",DATE_FORMAT_YMDHMS).getTime());
//       System.out.println(formatToDate("2017-11-27 15:30:50",DATE_FORMAT_YMDHMS).getTime());
//        System.out.println(formatToDate("2017-11-23 19:00:50",DATE_FORMAT_YMDHMS).getTime());
//
//
//    }

    /**
     * 获取上个月的时间
     */
    public static Date getLastMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return  c.getTime();
    }


    /**
     * 是否在今天之前
     * @param time
     * @return
     */
    public static boolean isBeforeToday(Long time) {
        if (time == null){
            return true;
        }
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();	//今天
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set( Calendar.HOUR_OF_DAY, 0);
        today.set( Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 001);
        current.setTimeInMillis(time);
        if(current.before(today)){
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * 日期对象转字符串
     * </pre>
     * @param date
     * @param format
     * @return
     */
    public static String formatToStr(Date date, String format)
    {
        if (date == null)
        {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * <pre>
     * 字符串转日期对象
     * </pre>
     * @param dateStr
     * @param format
     * @return
     */
    public static Date formatToDate(String dateStr, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);// 从0开始解析
        Date strtodate = formatter.parse(dateStr, pos);
        return strtodate;
    }

    /**
     * <pre>
     * 字符串转日期对象
     * </pre>
     * @param dateStr
     * @param format
     * @return
     */
    public static Date formatStrToDate(String dateStr, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date strtodate = new Date();
        try {
            strtodate = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
        return strtodate;
    }


    /**
     * <pre>
     * 格式化日期字符串
     * </pre>
     * @param dateStr
     * @param oldformat
     * @param newformat
     * @return
     */
    public static String formatStrToStr(String dateStr, String oldformat, String newformat)
    {
        return formatToStr(formatToDate(dateStr, oldformat), newformat);
    }

    /**
     * <pre>
     * 格式化日期（简短型）
     * </pre>
     * @param date
     * @return 返回格式 yyyy-MM-dd
     */
    public static String formatShortStr(Date date)
    {
        if (date == null)
        {
            return null;
        }
        return formatToStr(date, DATE_FORMAT_YMD);
    }

    /**
     * <pre>
     * 格式化日期（全格式型）
     * </pre>
     * @param date
     * @return 返回格式 yyyy-MM-dd HH:mm:ss
     */
    public static String formatLongStr(Date date)
    {
        if (date == null)
        {
            return null;
        }
        return formatToStr(date, DATE_FORMAT_YMDHMS);
    }

    public static String formatLongStrShort(Date date)
    {
        if (date == null)
        {
            return null;
        }
        return formatToStr(date, DATE_FORMAT_YMDHM);
    }




    /**
     * <pre>
     *  格式化日期（时间型）
     * </pre>
     * @param date
     * @return 返回格式 HH:mm:ss
     */
    public static String formatTimeStr(Date date)
    {
        if (date == null)
        {
            return null;
        }
        return formatToStr(date, DATE_FORMAT_HMS);
    }

    /**
     * <pre>
     * 增加天数
     * </pre>
     * @param date 原始日期
     * @param days 增加的天数
     * @return 增加days天后的日期
     */
    public static Date addDate(Date date, int days)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    //本周最后一天(周一开始)
    public static Date getEndDayOfWeek() {
        LocalDate localDate = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
        LocalDate localDateNew = LocalDate.from(localDate);
        localDateNew = localDateNew.with(fieldISO,7);
        return Date.from(localDateNew.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    //本周第一天(周一开始)
    public static Date getStartDayOfWeek() {
        LocalDate localDate = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
        LocalDate localDateNew = LocalDate.from(localDate);
        localDateNew = localDateNew.with(fieldISO,1);
        return Date.from(localDateNew.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * <pre>
     *  得到日期的小时(24小时制)
     * </pre>
     * @param date
     * @return
     */
    public static int getHour(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * <pre>
     *  得到日期的分钟
     * </pre>
     * @param date
     * @return
     */
    public static int getMinute(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * <pre>
     * 增加num秒后的时间
     * </pre>
     * @param date
     * @return
     */
    public static Date addSecond(Date date, long num)
    {
        long time = date.getTime() + num * 1000;
        return new Date(time);
    }

    /**
     * 判断是否润年
     * @return
     */
    public static boolean isLeapYear(Date date)
    {
        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年 3.能被4整除同时能被100整除则不是闰年
         */
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        if ((year % 400) == 0)
        {
            return true;
        }
        else if ((year % 4) == 0)
        {
            if ((year % 100) == 0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * 获取年得第一天
     * @param year
     * @return
     */
    public static Date getFristDateForYear(int year)
    {
        String timeStr = year + "-01-01 00:00:00";
        return formatToDate(timeStr, DATE_FORMAT_YMDHMS);
    }

    /*
     * 获取年得最后一天
     */
    public static Date getLastDateForYear(int year)
    {
        String timeStr = year + "-12-31 23:59:59";
        return formatToDate(timeStr, DATE_FORMAT_YMDHMS);
    }

    /**
     * 获取一个月的第一天
     * @return
     */
    public static Date getFristDateForMonth(int year, int month)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取一个月的第一天
     * @param date
     * @return
     */
    public static Date getFristDateForMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取一个月的第一天的开始时间
     * @param date
     * @return
     */
    public static Date getFristTimeForMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取一个月的最后一天
     * @param date
     * @return
     */
    public static Date getEndDateOfMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int curMonth = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, curMonth + 1);
        c.set(Calendar.DAY_OF_MONTH, 0);
        return c.getTime();
    }

    /**
     * 获取一个月的最后一天
     * @param date
     * @return
     */
    public static Date getEndTimeOfMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int curMonth = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, curMonth + 1);
        c.set(Calendar.DAY_OF_MONTH, 0);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 判断二个时间是否在同一个周
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2)
    {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        if (c1.get(Calendar.WEEK_OF_YEAR) == c2.get(Calendar.WEEK_OF_YEAR))
        {
            return true;
        }
        return false;
    }

    /**
     * 判断二个时间是否在同一个月
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameMonthDates(Date date1, Date date2)
    {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
        {
            return true;
        }
        return false;
    }

    /**
     * 判断二个时间是否在同一个年
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameYearDates(Date date1, Date date2)
    {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
        {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * 获取日期的年份
     * </pre>
     * @param date
     * @return
     */
    public static int getYear(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * <pre>
     * 获取日期的月份(特殊是月份要加1)
     * </pre>
     * @param date
     * @return
     */
    public static int getMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * <pre>
     * 获取日期在一年中的第几周
     * </pre>
     * @param date
     * @return
     */
    public static int getWeekForYear(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * <pre>
     * 获取日期在当前月中的第几周
     * </pre>
     * @param date
     * @return
     */
    public static int getWeekForMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * <pre>
     * 判断当前日期是周几
     * 1-7 分别表示周日,周一,周二,周三,周四,周五,周六
     * 1:SUNDAY, 2:MONDAY, 3:TUESDAY, 4:WEDNESDAY, 5:THURSDAY, 6:FRIDAY, 7:SATURDAY
     * </pre>
     * @param date
     * @return
     */
    public static int getDayForWeek(Date date)
    {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取本周周几
     * * 1-7 分别表示周日,周一,周二,周三,周四,周五,周六
     * @return
     */
    public static Date getWeekDate(Integer weekDay){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, weekDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        return date;
    }


    /**
     * <pre>w
     * 获得一个日期所在周的周几的日期
     * 例：获得当前周的周一：getFristForWeek(new Date(),Calendar.MONDAY)
     * </pre>
     * @param date 日期
     * @param weekday 周数 1-7 分别表示周日,周一,周二,周三,周四,周五,周六
     * @return 所在日期
     */
    public static Date getDayForWeek(Date date, int weekday)
    {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, weekday);
        return c.getTime();
    }

    public static Date getMonDayForYear(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int between = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int subMonday = 0;
        if (between >= 1 && between <= 6)
        {
            subMonday = 1 - between;
        }
        else if (between == 0)
        {
            subMonday = -6;
        }
        cal.add(Calendar.DAY_OF_MONTH, subMonday);
        return cal.getTime();

    }

    public static Date getSunDayForYear(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int between = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int subMonday = 0;
        if (between >= 1 && between <= 6)
        {
            subMonday = 1 - between;
        }
        else if (between == 0)
        {
            subMonday = -6;
        }
        cal.add(Calendar.DAY_OF_MONTH, subMonday);
        cal.add(Calendar.DAY_OF_MONTH, 6);
        return cal.getTime();
    }

    /**
     * 两个时间之间的天数
     * @return
     */
    public static int getDayNum(Date start, Date end)
    {
        return Integer.parseInt(String.valueOf((end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000))) + 1;
    }

    /**
     * 两个时间之间的天数差
     * @return
     */
    public static int getDayBetween(Date start, Date end)
    {
        return Integer.parseInt(String.valueOf((end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000)));
    }

    /**
     * <pre>
     * 获取两个时间之间的日期数组
     * </pre>
     * @param start
     * @param end
     * @return
     */
    public static List<Date> getDayArray(Date start, Date end)
    {
        List<Date> dates = new ArrayList<Date>();
        int count = getDayNum(start, end);
        for (int i = 0; i < count; i++)
        {
            Calendar c = Calendar.getInstance();
            c.setTime(start);
            c.add(Calendar.DAY_OF_YEAR, i);
            dates.add(c.getTime());
        }
        return dates;
    }

    /**
     * 获得昨天
     * @return Date
     */
    public static Date getPreviousDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获得明天
     * @return Date
     */
    public static Date getNextDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +1);
        return calendar.getTime();
    }


    /**
     * 获取某天的开始时间
     */
    public static Date getOtherDayStartDate(Integer daysNum){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysNum);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获得昨天结束时间
     * @return Date
     */
    public static Date getPreviousEndDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获得昨天结束时间
     * @return Date
     */
    public static Date getPreviousEndDateInMin()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    /**
     * 获取七天前的日期
     */
    public static Date getLastWeekDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        return calendar.getTime();
    }

    /**
     * 获取30天前的日期
     */
    public static Date getLastMonthDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        return calendar.getTime();
    }
    /**
     * 获取某天的开始时间
     * @return
     */
    public static Date getSomeDayStartTimes(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 000);
        return calendar.getTime();
    }

    /**
     * 获取某天的结束时间
     * @return
     */
    public static Date getSomeDayEndTimes(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取某天的某个时间
     * @return
     */
    public static Date getSomeDaySomeTimes(Date date, int day, int minute, int second)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, day);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 计算某年某周的开始日期
     * @param yearNum 格式 yyyy ，必须大于1900年度 小于9999年
     * @param weekNum 1到52或者53
     * @return 日期
     */
    public static Date getYearWeekFirstDay(int yearNum, int weekNum)
    {
        if (yearNum < 1900 || yearNum > 9999)
        {
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, yearNum);
        c.set(Calendar.WEEK_OF_YEAR, weekNum);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    /**
     * 计算某年某周的结束日期
     * @param yearNum 格式 yyyy ，必须大于1900年度 小于9999年
     * @param weekNum 1到52或者53
     * @return 日期，格式为yyyy-MM-dd
     */
    public static Date getYearWeekEndDay(int yearNum, int weekNum)
    {
        if (yearNum < 1900 || yearNum > 9999)
        {
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, yearNum);
        c.set(Calendar.WEEK_OF_YEAR, weekNum);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        c.roll(Calendar.DAY_OF_WEEK, 6);

        return c.getTime();
    }

    /**
     * 获取两个日期间的月份间隔
     * @param start
     * @param end
     * @return
     */
    public static int getMonthInterval(Date start, Date end)
    {
        int monthInterval = 0;
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        int sYear = startCal.get(Calendar.YEAR);
        int sMonth = startCal.get(Calendar.MONTH);
        int sDay = startCal.get(Calendar.DATE);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        int eYear = endCal.get(Calendar.YEAR);
        int eMonth = endCal.get(Calendar.MONTH);
        int eDay = endCal.get(Calendar.DATE);

        monthInterval = ((eYear - sYear) * 12 + (eMonth - sMonth));
        if (sDay < eDay)
        {
            monthInterval = monthInterval + 1;
        }
        monthInterval = Math.abs(monthInterval);
        return monthInterval;
    }

    /**
     * 获取两个日期间的年份间隔(同年算1年)
     * @param start
     * @param end
     * @return
     */
    public static int getYearInterval(Date start, Date end)
    {
        int yearInterval = 0;
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        int sYear = startCal.get(Calendar.YEAR);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        int eYear = endCal.get(Calendar.YEAR);
        yearInterval = eYear - sYear + 1;
        yearInterval = Math.abs(yearInterval);
        return yearInterval;
    }

    /**
     * <pre>
     *  上N个月的第一天
     * </pre>
     * @param date
     * @param month
     * @return
     */
    public static Date getLastFristDateForMonth(Date date, int month)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - month);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * <pre>
     *  获取下N个月的第一天
     * </pre>
     * @param date
     * @param month
     * @return
     */
    public static Date getNextFristDateForMonth(Date date, int month)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + month);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     *
     * @return
     */
    public static Calendar getCurrentDateWithoutTimestamp(){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YMDHM);
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(formatter.parse((new SimpleDateFormat(DATE_FORMAT_YMD)).format(new Date())+" 00:00"));
        }catch (Exception e){
        }
        return c;
    }

    /**
     *
     * @param time
     * @return
     */
    public static Calendar getCurrentDateWithTimeStr(String time){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YMDHM);
        Date formDate = null;
        Calendar c = Calendar.getInstance();

        try{
            formDate = formatter.parse((new SimpleDateFormat(DATE_FORMAT_YMD)).format(new Date())+" " + time);
            c.setTime(formDate);
            return c;
        }catch (Exception e){
            return null;
        }

    }

    public static String getCurrentDay() {
        Date date = new Date();
        return formatToStr(date, DATE_FORMAT_YMD);
    }

    public static Date getCurrenDayStr() {
        String date = getCurrentDay();
        return formatToDate(date, DATE_FORMAT_YMD);
    }

    /**
     * 两个时间进行比较
     * @Title: compareDate
     * @author hlc004
     * @param dt1
     * @param dt2
     * @return
     * @return int
     * @date 2015年8月24日 下午3:03:41
     * @throws
     */
    public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 日期加秒
     * @param date
     * @param second
     * @return
     */
    public static Date getDateAddSecond(Date date, Integer second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.SECOND,second);
        return calendar.getTime();
    }

    public static Date getDateByLong(Long time) {
        if (time == null){
            return null;
        }
        Calendar current = Calendar.getInstance();
        current.setTimeInMillis(time);
        return current.getTime();
    }

}
