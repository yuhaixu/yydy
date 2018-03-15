package com.freesheep.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static void main(String[] args){
//        String data = "MC73,2,901245,13552649736,021117071017,4010.71908,11627.29618,0000,000000,1";
//        String[] dataArr = data.split(",");
//        Date date =  DateUtils.formatDeviceTime(dataArr[4]);
//        System.out.println(date==null?"null":date.toString());


        Date date = new Date();
        System.out.println(date.getTime());

    }

    public static Date fromTimer(String timeStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long time = format.parse(timeStr).getTime();
            return new Date(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String monthFirst(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(c.getTime());
    }

    public static String monthLast(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(c.getTime());
    }

    public static String beforeDay(String d, int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, day);
        Date date = c.getTime();
        return format.format(date);
    }

//
    public static String getNowTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date formatDeviceTime(String deviceTime){
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        try {
            long time = format.parse(deviceTime).getTime();
            return new Date(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date formatTime(String formatType, long time) {
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        String result = format.format(new Date(time));
        try {
            return format.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getNowDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date getAfterMinuterDate(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

}
