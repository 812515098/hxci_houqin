package com.example.myapplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {


    public static String getdata(String s){
        String datatime="";
        //创建一个SimpleDateFormat并且告知它要读取的字符串格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //将一个字符串转换为相应的Date对象
        Date date = null;//要先捕获异常
        try {
            date = sdf.parse(s);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format11 = simpleDateFormat.format(date);
            datatime =   format11;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datatime;
    }
    public static String getdata02(String s){
        String datatime="";
        //创建一个SimpleDateFormat并且告知它要读取的字符串格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //将一个字符串转换为相应的Date对象
        Date date = null;//要先捕获异常
        try {
            date = sdf.parse(s);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            String format11 = simpleDateFormat.format(date);
            datatime =   format11;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datatime;
    }
    public static String getdata03(String s){
        String datatime="";
        //创建一个SimpleDateFormat并且告知它要读取的字符串格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        //将一个字符串转换为相应的Date对象
        Date date = null;//要先捕获异常
        try {
            date = sdf.parse(s);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
            String format11 = simpleDateFormat.format(date);
            datatime =   format11;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datatime;
    }
    public static String getdataYYMMdd(String s){
        String datatime="";
        //创建一个SimpleDateFormat并且告知它要读取的字符串格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //将一个字符串转换为相应的Date对象
        Date date = null;//要先捕获异常
        try {
            date = sdf.parse(s);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format11 = simpleDateFormat.format(date);
            datatime =   format11;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datatime;
    }

    public static String getNowTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期时间格式
        String format = dateFormat.format(date);//格式化一下
        return format;
    }

    /**
     * 获取两个日期之间的间隔天数
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }



    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getDate01(String str) {
        Date date;
        try {
            java.text.SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            date = formatter.parse(str);
            return date;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;


    }
}
