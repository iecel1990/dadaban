package com.dadaban.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jrose.shaw on 14-6-9.
 */
public class DateUtils{

    private static final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyymmdd");

    private static final SimpleDateFormat dateSdfYYMMDDHHMISS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat dateSdfYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getNow() {
        return new Date();
    }
    public static String format(Date date, SimpleDateFormat sdf) {
        if (ObjectUtil.isEmpty(date) || ObjectUtil.isEmpty(sdf)) {
            return null;
        }
        return format(date, sdf);
    }

    public static String format(Date date) {
        if (ObjectUtil.isEmpty(date)) {
            return null;
        }
        return format(date, dateSdf);
    }

    public static String formatYYYYMMDDHHMISS(Date date) {
        if (ObjectUtil.isEmpty(date)) {
            return null;
        }
        return format(date, dateSdfYYMMDDHHMISS);
    }

    public static String formatYYYYMMDD(Date date) {
        if (ObjectUtil.isEmpty(date)) {
            return null;
        }
        return format(date, dateSdfYYMMDD);
    }

    /**
     * 设定date的时分秒
     *
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date formatDate(Date date, int hour, int minute, int second) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), hour, minute, second);
        return c.getTime();
    }

    /**
     * 获取beginDate的后一天
     *
     * @param beginDate
     * @return
     */
    public static Date getNextDate(Date beginDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(beginDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
        return c.getTime();
    }

    /**
     * 获取beginDate的前一天
     *
     * @param beginDate
     * @return
     */
    public static Date getPreviousDate(Date beginDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(beginDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
        return c.getTime();
    }

    /**
     * 获取本周第一天
     *
     * @return
     */
    public static Date getFirstDayOfCurrentWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }


    /**
     * 取Date0和Date1的时间差，取整，不满一天就不计入在内。 比如:输入2013.3.1-2013.3.4
     * 0:00:01得到3天,输入2013.3.1-2013.3.4 0:00:00也是得到3天
     *
     * @param date0
     * @param date1
     * @return
     */
    public static int getIntervalDay(Date date0, Date date1) {
        long milliSeconds = date1.getTime() - date0.getTime();
        int intervalDay = (int) (milliSeconds / 1000 / 60 / 60 / 24);
        return intervalDay;
    }

    /**
     * 取日期差，但只要endDate比beginDate多1秒，也算是多一天 比如:输入2013.3.1-2013.3.4
     * 0:00:01得到4天,输入2013.3.1-2013.3.4 0:00:00得到3天
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int daysBetween(Date beginDate, Date endDate) {
        int days = 0;// 两个日期之前的天数
        Calendar beginCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        beginCalendar.setTime(beginDate);
        endCalendar.setTime(endDate);
        // 计算天数
        while (beginCalendar.before(endCalendar)) {
            days++;
            beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    /**
     * 根据开始时间得到对应的当天结束时间如：2012-03-08 00:00:00返回2012-03:08:23:59:59
     *
     * @param
     * @return
     */
    public static Date getCurrentEndDateFormat(Date endDate) {
        try {
            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar date = Calendar.getInstance();
            date.setTime(endDate);
            date.set(Calendar.AM_PM, 1);
            date.set(Calendar.HOUR, 11);
            date.set(Calendar.MINUTE, 59);
            date.set(Calendar.SECOND, 59);
            return dft.parse(dft.format(date.getTime()));
        } catch (Exception ex) {
            return endDate;
        }
    }

    /**
     * 根据开始时间得到对应的当天开始时间如：2012-03-08 23:59:00返回2012-03:08 00:00:00
     *
     * @param
     * @return
     */
    public static Date getCurrentBeginDateFormat(Date beginDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Calendar date = Calendar.getInstance();
            date.setTime(beginDate);
            date.set(Calendar.AM_PM, 0);
            date.set(Calendar.HOUR, 0);
            date.set(Calendar.MINUTE, 0);
            date.set(Calendar.SECOND, 0);
            return dft.parse(dft.format(date.getTime()));
        } catch (Exception ex) {
            return beginDate;
        }
    }

    /**
     * 根据开始时间得到对应的当天开始时间如：2012-03-08 23:59:00返回2012-03:08 00:00:00
     *
     * @param
     * @return
     */
    public static Date getCurrentDateFormat(Date beginDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return dft.parse(dft.format(beginDate));
        }catch (Exception ex){
            return beginDate;
        }
    }


    /**
     * 时间相加
     *
     * @param beginDate
     * @param duration
     * @return
     */
    public static Date getDateAdded(Date beginDate, int duration) {
        try {
            Calendar date = Calendar.getInstance();
            date.setTime(beginDate);
            date.set(Calendar.DATE, date.get(Calendar.DATE) + duration);
            return date.getTime();
        } catch (Exception ex) {
            return beginDate;
        }
    }


    /**
     * 获取当前月的第一天
     *
     * @return
     */
    public static Date startOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取某月的最后一天
     *
     * @param monthBetween 与当前月相隔的月数
     * @return
     */
    public static Date endOfMonth(int monthBetween) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + monthBetween);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * @param year  参数2011时为2011年
     * @param month 参数1时表示1月
     * @param day   参数1为1号
     * @return
     */
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    public static SimpleDateFormat getSecondFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static SimpleDateFormat getDayFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static String formatDateUseSecondFormat(Date date){
        String result = null;
        if(date != null){
            result = getSecondFormatter().format(date);
        }
        return result;
    }

    public static String formatDate(Date date){
        String result = null;
        if(date != null){
            result = getDayFormatter().format(date);
        }
        return result;
    }
}
