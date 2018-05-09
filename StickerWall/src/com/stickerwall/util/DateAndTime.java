package com.stickerwall.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 此类为时间工具类，用以获取当前的系统时间
 */
public class DateAndTime {

    /**
     * 获取数据库类型的日期
     * @return
     */
    public static java.sql.Date getDateForSql(){

        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

        return date;
    }
    /**
     * 获取数据库类型的日期和时间
     * @return
     */
    public static Timestamp getDateTimeForSql(){

        Timestamp dateTime = new Timestamp(new java.util.Date().getTime());

        return dateTime;
    }

    /**
     * String转Date，返回java.sql.Date
     * 注：此处的月份必须是大写的MM，小时必须是大写的HH，否则转换会不正确
     * @param strDate
     * @return
     */
    public static java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());

        return date;
    }

    /**
     * String转time,返回java.sql.Time
     * @param strTime
     * 注：此处的月份必须是大写的MM，小时必须是大写的HH，否则转换会不正确
     * @return
     */
    public static java.sql.Time strToTime(String strTime) {
        String str = strTime;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time.valueOf(str);
    }

    /**
     * String转DateTime,返回java.sql.Timestamp
     * 注：此处的月份必须是大写的MM，小时必须是大写的HH，否则转换会不正确
     * @param strDateTime
     * @return
     */
    public static Timestamp strToDateTime(String strDateTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date dateTime = null;
        try {
            dateTime = format.parse(strDateTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(dateTime.getTime());

        return timestamp;
    }
} 