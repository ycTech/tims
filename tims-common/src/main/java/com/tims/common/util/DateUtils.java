package com.tims.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author liuzm 2017/8/31 20:40
 */
public class DateUtils {

    /**
     * 时间格式化 "yyyy-MM-dd"
     *
     * @return
     */
    private static SimpleDateFormat getSdfDayFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * 时间格式化 "yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    private static SimpleDateFormat getSdfTimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getCurrentDay() {
        return getSdfDayFormat().format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getCurrentTime() {
        return getSdfTimeFormat().format(new Date());
    }

    /**
     * 与当前日期比较 都格式化为:YYYY-MM-DD格式
     *
     * @param date
     * @return
     */
    public static int compareWithCurrentDay(Date date) {
        return getSdfDayFormat().format(date).compareTo(getCurrentDay());
    }

    /**
     * 获取 几天 之后的日期 格式：yyyy-MM-dd
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(Integer days) {
        Calendar calendar = Calendar.getInstance(); // java.util包
        calendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        Date date = calendar.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);

        return dateStr;
    }
}
