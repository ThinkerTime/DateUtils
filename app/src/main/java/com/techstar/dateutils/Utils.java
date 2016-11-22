package com.techstar.dateutils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * author lrzg on 16/11/22.
 * 描述：根据传入的日期，判断是否是昨天、今天、明天、还是周几
 */

public class Utils {

    public static final DateTimeFormatter dateFormatter1 = DateTimeFormat.forPattern("MM月dd日");
    public static final DateTimeFormatter dateFormatter2 = DateTimeFormat.forPattern("yyyy.MM.dd");
    public static final DateTimeFormatter dateFormatter3 = DateTimeFormat.forPattern("MM月dd日 HH:mm");
    public static final DateTimeFormatter dateFormatter4 = DateTimeFormat.forPattern("HH:mm");
    public static final DateTimeFormatter dateFormatter5 = DateTimeFormat.forPattern("yyyy年MM月dd日");
    public static final DateTimeFormatter dateFormatter6 = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static final DateTimeFormatter dateFormatter7 = DateTimeFormat.forPattern("yyyy-MM-dd MM月dd日 HH:mm");
    public static final DateTimeFormatter dateFormatter8 = DateTimeFormat.forPattern("HHmm");
    public static final DateTimeFormatter dateFormatter9 = DateTimeFormat.forPattern("yyyy年");
    public static final DateTimeFormatter dateFormatter10 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter dateFormatter11 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter dateFormatter12 = DateTimeFormat.forPattern("yyyy年MM月dd");
    private static final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 转换日期
     *
     * @param specifiedDay
     * @return
     */
    public static String convertTime(String specifiedDay) {
        final String month = specifiedDay.substring(0, 2);
        //获取当前的月份
        DateTime dateTime1 = new DateTime();
        final int todayMonth = dateTime1.getMonthOfYear();

        //如果传入的月份，小于当前的月份默认为明年
        if (Integer.valueOf(month) < todayMonth) {
            specifiedDay = (dateTime1.getYear() + 1) + "年" + specifiedDay;
        } else {
            specifiedDay = dateTime1.getYear() + "年" + specifiedDay;
        }

        //格式化传入的时间
        DateTime dateTime = DateTime.parse(specifiedDay, dateFormatter12);

        //判断传入的日期是星期几
        String weekOfDate = getWeekOfDate(dateTime.toDate());

        //获取当前日期是本年的第几天
        int dayNow = dateTime1.getDayOfYear();
        //获取传入日期是当年的第几天
        int dayPre = dateTime.getDayOfYear();

        //计算两日期之间的时间差
        int days = dayPre - dayNow;
        if (days == -1) {
            return "昨天";
        } else if (days == 0) {
            return "今天";
        } else if (days == 1) {
            return "明天";
        } else {
            return weekOfDate;
        }

    }

    /**
     * 判断今年是瑞年还是平年
     *
     * @return
     */
    public static int getYearDays() {
        GregorianCalendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        //判断今年是瑞年还是平年
        if (calendar.isLeapYear(year)) {
            return 366;
        } else {
            return 365;
        }
    }

    /**
     * 判断今天是周几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
