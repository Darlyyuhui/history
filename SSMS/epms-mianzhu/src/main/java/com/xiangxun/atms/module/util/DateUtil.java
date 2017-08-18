package com.xiangxun.atms.module.util;

import com.xiangxun.atms.framework.constant.FORMAT;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhouhaij on 2016/12/6.
 */
public class DateUtil extends com.xiangxun.atms.framework.util.DateUtil {

    private DateUtil() {

    }

    /***
     * 获取一周的第一天
     * @param date
     * @return
     */
    public static Date getBeginOfWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int dayOfWeek = ca.get(7);
        ca.add(5, -((dayOfWeek + 5) % 7));
        return (Date) ca.getTime().clone();
    }

    /***
     * 获取一周的最后一天
     * @param date
     * @return
     */
    public static Date getEndOfWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_WEEK, ca.getActualMaximum(Calendar.DAY_OF_WEEK));

        Date last = (Date) ca.getTime().clone();
        ca.setTime(last);
        int day = ca.get(Calendar.DAY_OF_YEAR);
        ca.set(Calendar.DAY_OF_YEAR, day + 1);
        return getEndOfDay(ca.getTime());
    }

    public static void main(String[] args) {


        try {
            Date beginOfWeek = DateUtil.getBeginOfWeek(parseDate("2016-12-05", FORMAT.DATE.DEFAULT));
            System.out.println(beginOfWeek);


            Date endOfWeek = DateUtil.getEndOfWeek(parseDate("2016-12-05", FORMAT.DATE.DEFAULT));
            System.out.println(endOfWeek);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
