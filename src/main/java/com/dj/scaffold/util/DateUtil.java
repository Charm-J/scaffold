package com.dj.scaffold.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final String DATE_FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    public static int getSecondTimeDif(Date targetDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ONE);
        String fromDate = sdf.format(targetDate);
        long from = 0L;
        try {
            from = sdf.parse(fromDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long to = new Date().getTime();
        return (int) ((to - from) / (1000 * 60));
    }


    public static Date getRandomTimeBeforeNow(int difSecond) {
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -difSecond);// N分钟之前的时间
        return beforeTime.getTime();
    }

    public static String getRandomTimeBeforeNowStr(int difSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ONE);
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -difSecond);// N分钟之前的时间
        Date beforeD = beforeTime.getTime();
        return sdf.format(beforeD);
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ONE);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = sdf.parse(strDate, pos);
        return strtodate;
    }


    /**
     * 输入时间点与分钟数得到上一时间点的分钟数
     *
     * @param inputDate 时间参数 1 格式：1990-01-01 12:00:00
     * @param mins      分钟参数 2 格式：mm
     * @return String 返回值为：yyyy-MM-dd HH:mm:ss
     */
    public static String getPrevDateByMins(String inputDate, int mins) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MINUTE, -mins);
        return sdf.format(ca.getTime());
    }



    public static String stringDateToDateFormat(String string) {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String str = "";
        try {
            Date data = format1.parse(string);
            str = format2.format(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static String stringMillisToDateFormat(String millis) {
        Long ms = Long.valueOf(millis);
        Date date = new Date(Long.valueOf(millis));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    public static String getDatetime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    public static int getTimeDiffSeconds(String startTime, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(startTime);
            Date d2 = df.parse(endTime);
            if ((d1.getTime() - d2.getTime()) < 0) {
                return -1;
            } else {
                int diff = (int) (d1.getTime() - d2.getTime());
                int seconds = diff / (1000);
                return seconds;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }


}
