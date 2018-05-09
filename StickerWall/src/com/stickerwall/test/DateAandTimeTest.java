package com.stickerwall.test;

import com.stickerwall.util.DateAndTime;

import java.sql.Timestamp;

public class DateAandTimeTest {

    public static void main(String[] args) {
        System.out.println("strToDate:" + strToDateTest());
        System.out.println("strTime:" + strToTimeTest());
        System.out.println("strToDateTime:" + strToDateTimeTest());
    }
    public static java.sql.Date strToDateTest(){
        String str = "1996-03-13";
        System.out.println(DateAndTime.strToDate(str));

        return DateAndTime.strToDate(str);
    }

    public static java.sql.Time strToTimeTest (){
        String str = "22:29:23";
        System.out.println(DateAndTime.strToTime(str));

        return DateAndTime.strToTime(str);
    }

    public static Timestamp strToDateTimeTest(){
        String str = "1996-03-13 22:22:22";
        System.out.println(DateAndTime.strToDateTime(str));
        return DateAndTime.strToDateTime(str);
    }
} 