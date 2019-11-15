package com.study.meiyx.learning.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimpleDateFormatDemo {

    public static void main(String[] args) throws Exception {
        // Negative example:
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(format.parse("2019-12-30 00:00:00")));

        //Positive example:
        SimpleDateFormat format2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        System.out.println(format2.format(format2.parse("2019-12-30 00:00:00")));
        //
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 11, 30);
        Date date = calendar.getTime();
        System.out.println(format.format(date));
        System.out.println(format2.format(date));



    }
    //SimpleDateFormat 是线程不安全的类，一般不要定义为static变量，如果定义为static，必须加锁，或者使用DateUtils工具类
    //Positive example 1：
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public String getFormat(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        return sdf.format(date);
    }

    //Positive example 2：
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public void getFormat(){
        synchronized (SIMPLE_DATE_FORMAT){
            SIMPLE_DATE_FORMAT.format(new Date());
//
        }
    }

    //Positive example 3：
    private static final ThreadLocal<DateFormat> DATE_FORMATTER = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
}
