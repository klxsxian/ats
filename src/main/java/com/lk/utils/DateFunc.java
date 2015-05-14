/*
 * @(#)DateFunc.java 1.0 2006-7-26
 * Copyright 2006 VandaGroup, Inc. All rights reserved.
 */
package com.lk.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 日期工具类. <br>
 * 
 * @author 王瑛 <br>
 * @version Version 1.00 <br>
 */
public class DateFunc {
	
	
    private static final String FORMAT_DATE = "yyyyMMdd";

    private static final String FORMAT_TIME = "HHmmss";

    public static java.sql.Date convertDate(Date date){
    	return new java.sql.Date(date.getTime());
    }
    /**
     * 将日期转成Integer型,例如日期为2006-07-24,返回20060724
     * 
     * @param date
     *            指定日期
     * @return Integer型日期
     */
    public static Integer date2Int(Date date) {
    	if ( date == null ) {
    		return null;
    	}
        DateFormat format = new SimpleDateFormat(FORMAT_DATE);
        String str = format.format(date);
        //logger.error("FORMATDATE......FORMATDATE"+str);
        return Integer.valueOf(str);
    }
    
    /**
     * 将日期的时间转成Integer型,例如日期为2006-07-24 12:32:28,返回123228
     * @param date
     * @return
     */
    public static Integer date2TimeInt(Date date) {
        DateFormat format = new SimpleDateFormat(FORMAT_TIME);
        String str = format.format(date);
        return Integer.valueOf(str);
    }
    
    /**
     * 将日期转变为当月的信用卡有效期，如20150731 -> 0715
     * @param date
     * @return
     */
    public static String date2CardExpDate(Date date){
    	Integer dateInt = date2Int(date);
    	String str=String.valueOf(dateInt);
    	str = str.substring(4,6) + str.substring(2,4);
    	return str;
    }
    
    
    /**
     * 将信用卡有效期转换成日期 如0715->20150731
     * @param expDateStr
     * @return
     */
    public static Date cardExpDate2Date(String expDateStr){
    	if(PubFunc.isEmpty(expDateStr)||expDateStr.trim().length()!=4){
    		return null;
    	}
    	String mm = expDateStr.substring(0, 2);
    	String yy = expDateStr.substring(2, 4);
    	String yyyymm = "20"+(yy+mm);
        Date str2Date = DateFunc.str2Date(yyyymm+"01");
        Date expiredLastDate = DateFunc.getLastDayOfThisMonth(str2Date);
    	return expiredLastDate;
    }
    
    /**
     * 将日期转成Integer型,例如日期为2006-07-24 15:28:30,返回20060724152830
     * 
     * @param date
     *            指定日期
     * @return Integer型日期
     */
    public static Double date2Int(Date date,String formatStr) {
        DateFormat format = new SimpleDateFormat(formatStr);
        String str = format.format(date);
        return Double.valueOf(str);
    }

    /**
     * 将时间戳转成Integer型日期
     * 
     * @param ts
     *            指定时间戳
     * @return Integer型日期
     */
    public static Integer date2Int(Timestamp ts) {
        DateFormat format = new SimpleDateFormat(FORMAT_DATE);
        String str = format.format(ts);
        return Integer.valueOf(str);
    }

    /**
     * 将时间戳转成Integer型时间
     * 
     * @param ts
     *            指定时间戳
     * @return Integer型时间
     */
    public static Integer time2Int(Timestamp ts) {
        DateFormat format = new SimpleDateFormat(FORMAT_TIME);
        String str = format.format(ts);
        return Integer.valueOf(str);
    }
    
    /**
     * 将时间戳转成Integer型时间
     * 
     * @param ts
     *            指定时间戳
     * @param formatStr
     *            格式
     * @return String型时间
     */
    public static String time2Str(Timestamp ts,String formatStr) {
        DateFormat format = new SimpleDateFormat(formatStr);
        String str = format.format(ts);
        return str;
    }

    /**
     * 将字符串型日期转成日期型，例如：20060202
     * 
     * @param strDate
     *            指定字符串型日期
     * @return 日期型
     */
    public static Date str2Date(String strDate) {
        Calendar c = Calendar.getInstance();

        //String strDate = String.valueOf(nDate);
        if (strDate.length() != 8) {
            return null;
        }
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
        int date = Integer.parseInt(strDate.substring(6));
        
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        return new Date(c.getTimeInMillis());
    }

	
    /**
     * 将字符串型时间转成时间戳，日期默认为系统日期
     * 
     * @param strTime
     *            字符串型时间
     * @return 时间戳型
     */
    private static Timestamp str2Time1(String strTime) {
        Calendar c = Calendar.getInstance();

        if (strTime.length() < 6) {
            String prefix = "";
            for (int i = 0; i < 6 - strTime.length(); i++) {
                prefix += "0";
            }
            strTime = prefix + strTime;
        }
        if (strTime.length() > 6) {
            return null;
        }
        int hour = Integer.parseInt(strTime.substring(0, 2));
        int minute = Integer.parseInt(strTime.substring(2, 4));
        int second = Integer.parseInt(strTime.substring(4));

        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        c.set(Calendar.MILLISECOND, 0);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 将字符串型转换成Timestamp型 例如：20060820142010
     * 
     * @param strTime
     *            字符串型时间戳
     * @return 时间戳型
     */
    private static Timestamp str2Time2(String strTime) {
        Calendar c = Calendar.getInstance();

        int year = Integer.parseInt(strTime.substring(0, 4));
        int month = Integer.parseInt(strTime.substring(4, 6));
        int day = Integer.parseInt(strTime.substring(6, 8));
        int hour = Integer.parseInt(strTime.substring(8, 10));
        int minute = Integer.parseInt(strTime.substring(10, 12));
        int second = Integer.parseInt(strTime.substring(12));

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);

        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        c.set(Calendar.MILLISECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 将字符串型时间或时间戳转成时间戳类型
     * 
     * @param strTime
     *            字符串型时间或时间戳
     * @return 时间戳类型
     */
    public static Timestamp str2Time(String strTime) {
        if (strTime.length() <= 6) {
            return str2Time1(strTime);
        }
        return str2Time2(strTime);
    }

    /**
     * 将Interger日期型转成Date型
     * 
     * @param nDate
     *            Integer型日期
     * @return Date型日期
     */
    public static Date int2Date(Integer nDate) {
        return str2Date(String.valueOf(nDate));
    }

    /**
     * 将Interger日期型转成时间戳型
     * 
     * @param nTime
     *            Integer型日期
     * @return 时间戳型
     */
    public static Timestamp int2Time(Integer nTime) {
        return str2Time(String.valueOf(nTime));
    }

    /**
     * 按yyyy-MM-dd格式显示日期
     * 
     * @param date
     *            日期
     * @return
     */
    public static String showDate(Integer date) {
        if (date == null) {
            return null;
        }
        String formatStr = "yyyy-MM-dd";
        String value = "";
        
       // DateFormat format = new SimpleDateFormat(formatStr);

        value = showDate(date,formatStr);//format.format(DateFunc.int2Date(date)).toString();

        return value;
    }
    /**
     * 按yyyy/MM/dd格式显示日期
     * 
     * @param date
     *            日期
     * @return
     */
    public static String showDate(Integer date,String formatStr) {
        if (date == null) {
            return null;
        }
        //String formatStr = "yyyy/MM/dd";
        String value = "";
        
        DateFormat format = new SimpleDateFormat(formatStr);

        value = format.format(DateFunc.int2Date(date)).toString();

        return value;
    }
    
    
    /**
     * 按yyyy/MM/dd HH:mm:ss格式显示日期
     * 
     * @param dateTime
     *            日期时间
     * @return
     */
    public static String showDate(Date dateTime,boolean isShowTime) {
        if (dateTime == null) {
            return null;
        }
        /*String date=DateFunc.showDate(DateFunc.date2Int(DateFunc.str2Time(DateFunc.getCurrentDateTime())),"yyyy/MM/dd");
		String time=DateFunc.showTime(DateFunc.time2Int(DateFunc.str2Time(DateFunc.getCurrentDateTime())) , "HH:mm:ss");
		input.put("printDate",printDate+" "+printTime);*/
        String value = "";
		String date=showDate(date2Int(dateTime),"yyyy/MM/dd");
		value=date;
		if(isShowTime){
			String time=showTime(time2Int(dateTime) , "HH:mm:ss");
			value+=" "+time;
		}		
        return value;
    }
    
    /**
     * 按yyyy/MM/dd HH:mm:ss格式显示日期
     * 
     * @param date String
     *            日期时间 yyyyMMddHHmmss
     * @return
     */
    public static String showDate(String dateTime) {
        if (dateTime == null) {
            return null;
        }
        String value="";
        String date=showDate(date2Int(str2Time(dateTime)),"yyyy/MM/dd");
		String time=showTime(time2Int(str2Time(dateTime)) , "HH:mm:ss");
		value=date +" "+time;
        return value;
    }

    /**
     * 按yyyy/MM/dd格式显示日期
     * 
     * @param date
     *            日期
     * @return
     */
    public static String showDate(Date date,String formatStr) {
        if (date == null) {
            return null;
        }
        String value = "";
        
        DateFormat format = new SimpleDateFormat(formatStr);

        value = format.format(DateFunc.date2Int(date,"yyyyMMDDHHmmss")).toString();

        return value;
    }

    /**
     * 按HH:mm:ss格式显示时间
     * 
     * @param time
     *            时间
     * @return
     */
    public static String showTime(Integer time) {
        return showTime(time, "HH:mm:ss");
    }

    /**
     * 按指定格式显示时间
     * 
     * @param time
     *            时间
     * @param formatStr
     *            格式
     * @return
     */
    public static String showTime(Integer time, String formatStr) {
        if (time == null) {
            return null;
        }
        String value = "";
        //String formatStr = "HH:mm:ss";
        
        DateFormat format = new SimpleDateFormat(formatStr);

        value = format.format(DateFunc.int2Time(time)).toString();
        

        return value;
    }

    /** 一天的毫秒数 */
    private static final long ONEDATE = 24 * 60 * 60 * 1000;

    /**
     * 取得某日期之后或之前n天的日期
     * 
     * @param date
     * @param days
     *            正－之后，负－之前 取得2006-01-01后三天的日期,返回2006-01-04 Date afterDate =
     *            addDate(new Date(2006-01-01),3);
     *            取得2006-01-01之前三天的日期,返回2005-12-29 Date beforeDate = addDate(new
     *            Date(2006-01-01),-3);
     * @return
     */
    public static Date addDate(Date date, int days) {
        long time = date.getTime() + (ONEDATE * days);

        return new Date(time);
    }
    
    /**
     * 取得某日期之后或之前n天的日期
     * 
     * @param date
     * @param days
     *            正－之后，负－之前 取得2006-01-01后三天的日期,返回2006-01-04 Date afterDate =
     *            addDate(new Date(2006-01-01),3);
     *            取得2006-01-01之前三天的日期,返回2005-12-29 Date beforeDate = addDate(new
     *            Date(2006-01-01),-3);
     * @return
     */
    public static Integer addDate(Integer nDate,int days){
    	Date date = int2Date(nDate);
    	Date result = addDate(date,days);
    	return date2Int(result);
    }

    /**
     * 取得某日期之后或之前n月的日期
     * 
     * @param date
     * @param months
     *            正－之后，负－之前 取得2006-01-01后三月的日期,返回2006-04-01 Date afterDate =
     *            addMonth(new Date(2006-01-01),3);
     *            取得2006-01-01之前三月的日期,返回2005-10-01 Date beforeDate =
     *            addMonth(new Date(2006-01-01),-3);
     * @return
     */
    public static Date addMonth(Date date, int months) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyy");
        int year = Integer.valueOf(format.format(date)).intValue();
        format = new SimpleDateFormat("MM");
        int month = Integer.valueOf(format.format(date)).intValue() - 1;
        format = new SimpleDateFormat("dd");
        int date_of_month = Integer.valueOf(format.format(date)).intValue();
        calendar.set(year, month, date_of_month);
        calendar.add(GregorianCalendar.MONTH, months);
        return new Date(calendar.getTime().getTime());
    }
    
    /**
     * 20090205 + 6 month => 20090801
     * @param date
     * @param months
     * @return
     * @throws Exception
     */
    public static Date addMonthFirstDay(Date date, int months) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyy");
        int year = Integer.valueOf(format.format(date)).intValue();
        format = new SimpleDateFormat("MM");
        int month = Integer.valueOf(format.format(date)).intValue() - 1;
        calendar.set(year, month, 1);
        calendar.add(GregorianCalendar.MONTH, months);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 20090205 + 6 month => 20090831
     * @param date
     * @param months
     * @return
     * @throws Exception
     */
    public static Integer addMonthLastDay(Integer date, int months) throws Exception {
        
        if (date == null) return null ;
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        calendar.setTime(format.parse(date + "000000"));
        calendar.add(GregorianCalendar.MONTH, months + 1);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 0) ;
        //calendar.add(GregorianCalendar.DATE, -1);
        
        return new Integer(format.format(calendar.getTime()).substring(0,8)) ;
    }
    
    /**
     * 20090205 + 6 month => 20090831
     * @param date
     * @param months
     * @return
     * @throws Exception
     */
    public static Date addMonthLastDay(Date date, int months) throws Exception {
        
        if (date == null) return null ;
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        calendar.setTime(format.parse(date2Int(date) + "000000"));
        calendar.add(GregorianCalendar.MONTH, months + 1);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 0) ;
        //calendar.add(GregorianCalendar.DATE, -1);
        
        return new Date(calendar.getTime().getTime()) ;
    }
    
    /**
     * 20090205 + 6 month => 20090801
     * @param date
     * @param months
     * @return
     * @throws Exception
     */
    public static Integer addMonthFirstDayReturnInteger(Integer date, int months) throws Exception {
        
        if (date == null) return null ;
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        calendar.setTime(format.parse(date + "000000"));
        calendar.add(GregorianCalendar.MONTH, months);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, 1) ;
        //calendar.add(GregorianCalendar.DATE, -1);
        
        return new Integer(format.format(calendar.getTime()).substring(0,8)) ;
    }
    
    
    /**
     * 取得某日期之后或之前n年的日期
     * 
     * @param date
     * @param months
     *            正－之后，负－之前 取得2006-01-01后三年的日期,返回2009-01-01 Date afterDate =
     *            addYear(new Date(2006-01-01),3);
     *            取得2006-01-01之前三月的日期,返回2003-01-01 Date beforeDate = addYear(new
     *            Date(2006-01-01),-3);
     * @return
     */
    public static Date addYear(Date date, int years) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyy");
        int year = Integer.valueOf(format.format(date)).intValue();
        format = new SimpleDateFormat("MM");
        int month = Integer.valueOf(format.format(date)).intValue() - 1;
        format = new SimpleDateFormat("dd");
        int date_of_month = Integer.valueOf(format.format(date)).intValue();
        calendar.set(year, month, date_of_month);
        calendar.add(GregorianCalendar.YEAR, years);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 取得某日期的上月的最后一天
     * 
     * @param date
     *            取得2006-01-20的上月最后一天,返回2005-12-31 Date date =
     *            getLastDayOfLastMonth(new Date(2006-01-20));
     * 
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Date dtfirstDay = getFirstDayOfThisMonth(date);
        return addDate(dtfirstDay, -1);
    }

    /**
     * 取得某日期的当前月的第一天
     * 
     * @param date
     *            取得2006-01-20的上月最后一天,返回2006-01-01 Date date =
     *            getFirstDayOfThisMonth(new Date(2006-01-20));
     * 
     * @return
     */
    public static Date getFirstDayOfThisMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyy");
        int year = Integer.valueOf(format.format(date)).intValue();
        format = new SimpleDateFormat("MM");
        int month = Integer.valueOf(format.format(date)).intValue() - 1;
        format = new SimpleDateFormat("dd");
        int date_of_month = Integer.valueOf(format.format(date)).intValue();
        calendar.set(year, month, 1, 0, 0, 0);
        
        return new Date(calendar.getTime().getTime());
    }
    
    public static Date getLastDayOfThisMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyy");
        int year = Integer.valueOf(format.format(date)).intValue();
        format = new SimpleDateFormat("MM");
        int month = Integer.valueOf(format.format(date)).intValue() - 1;
        format = new SimpleDateFormat("dd");
        int date_of_month = Integer.valueOf(format.format(date)).intValue();
        
        calendar.set(year, month, 1, 23, 59, 59);
        int lastDay = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, lastDay);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 计算两天相隔的天数
     * 
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return 相隔天数
     */
    public static int substractDate(Date startDate, Date endDate) {
        //modified by chenlei 20070604 fixed：20070429～20070509为11天而不是10天
        return (int) ((endDate.getTime() - startDate.getTime()) / ONEDATE) + 1;
    }

    /**
     * 计算两个时间相隔的秒数 <br>
     * 如果结束时间>开始时间，则认为是同一日期，否则，结束时间的日期比开始时间的日期大一天
     * 
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return 相隔秒数
     */
    public static Integer subtractTime(Integer startTime, Integer endTime) {
        Timestamp startTime2 = str2Time(startTime.toString());
        Timestamp endTime2 = str2Time(endTime.toString());
        long result = endTime2.getTime() - startTime2.getTime();
        if (result < 0) {
            result = result + ONEDATE;
        }
        result /= 1000;
        return new Integer((int) result);
    }
    
    /**
     * 返回指定的月的开始日期和结束日期
     * 
     * @param nDate
     *            例如date=20060910,则返回20060901,20060930
     * @return
     */
    public static Date[] getMonthArrange(Date date,int monthNum) {
        if(monthNum <=0 ){
        	return null;
        }
        
        Date[] dates = new Date[2];
        
        dates[0] = DateFunc.getFirstDayOfThisMonth(DateFunc.addMonth(date, -(monthNum-1)));
        dates[1] = DateFunc.getLastDayOfThisMonth(date);
        return dates;
    }

    
    /**
     * 返回受理号前缀
     * 
     * @param saleDate
     * @return
     */
    public static Integer getBusinessPrefix(Integer saleDate) {
        String date = String.valueOf(saleDate);
        String year = date.substring(2, 4);
        String month = date.substring(4, 6);

        return Integer.valueOf(year.substring(1) + month);
    }

    /**
     * 返回当前年月 2007-02-01月返回702
     * 
     * @param date
     * @return
     */
    public static String getYearMonthStr(Integer date) {
   	
        return (date.toString()).substring(3,6);
    }
    
    /**
     * 得到应用服务器当前日期
     * @return
     */
    public static Date getAppServerDate() {
    	return new Date(System.currentTimeMillis());
    }
    
    /**
     * 将date的时分秒设置为系统时间的时分秒
     * @param date
     * @return
     */
    public static Date setHourAndSecondBySystemTime(Date date){
    	Calendar dateCalendar = Calendar.getInstance() ;
		Calendar sysCalendar = Calendar.getInstance() ;
		dateCalendar.setTime(date) ;
		sysCalendar.setTime(getAppServerDate()) ;
		dateCalendar.add(Calendar.HOUR_OF_DAY, sysCalendar.get(Calendar.HOUR_OF_DAY)) ;
		dateCalendar.add(Calendar.MINUTE, sysCalendar.get(Calendar.MINUTE)) ;
		dateCalendar.add(Calendar.SECOND, sysCalendar.get(Calendar.SECOND)) ;
		return dateCalendar.getTime() ;
    }
    
    /**
     * 校验是否为合法的日期值
     * @return boolean
     */
    public static boolean checkDate(Integer idate) {
        try {
            int2Date(idate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * 返回当前时间，格式为yyyyMMddHHmmss
     * @return
     */
    public static String getCurrentDateTime(){
    	//java.util.Date date = new java.util.Date();
    	/*SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE+FORMAT_TIME);
    	String ret = sdf.format(date);*/
    	String ret=getCurrentDateTime(FORMAT_DATE+FORMAT_TIME);
    	return ret;
    }
    
    /**
     * 返回当前时间，格式为yyyyMMddHHmmss
     * @return
     */
    public static String getCurrentDateTime(String format){
    	java.util.Date date = new java.util.Date();
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	String ret = sdf.format(date);
    	return ret;
    }
    /**
     * 返回当前时间，格式为yyyyMMdd
     * @return
     */
    public static String getCurrentDate(){
    	java.util.Date date = new java.util.Date();
    	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
    	String ret = sdf.format(date);
    	return ret;
    }   
    

    
    /**
     * 将时间转成Integer型,例如日期为2006-07-24 23:12:30,返回231230
     * 
     * @param date
     *            指定日期时间
     * @return Integer型时间
     */
    public static Integer time2Int(java.util.Date date) {
        DateFormat format = new SimpleDateFormat(FORMAT_TIME);
        String str = format.format(date);
        return Integer.valueOf(str);
    }
    
    /**
     * 将日期(String)转成java.util.Date型
     * 
     * @param date
     *            指定日期
     * @return Integer型日期
     */
    public static java.util.Date Str2javaDate(String str) {
        DateFormat format = new SimpleDateFormat(FORMAT_DATE);
        java.util.Date date = null;
        try{
        	date = format.parse(str);
        }catch(Exception e){}
        return date;
    }
    /**
     * Check if the range between begin date and end date is less than or equal to the limitation
     * @param begin: the begin date of the date range
     * @param end: the end date of the date range
     * @param maxDaysInRange: the limitation of the date range
     * @return
     */
    public static boolean isInDateRangeLimitation(Date begin, Date end, int maxDaysInRange) {
    	Calendar calBegin = Calendar.getInstance();
    	calBegin.setTime(begin);
    	Calendar calEnd = Calendar.getInstance();
    	calEnd.setTime(end);
    	boolean isInRange = true;
    	if(calBegin.compareTo(calEnd) > 0) {
    		isInRange = false;
    	}
    	calBegin.add(Calendar.DAY_OF_YEAR, maxDaysInRange);
    	if(calBegin.compareTo(calEnd) < 0) {
    		isInRange=false;
    	}
    	return isInRange;
    }
    
    /**
     * 取得某日期的最早时间
     * 
     * @param date
     *            输入20111010 ,返回20111010:00:00:00
     * 
     * @return
     */
    public static Date getEarliestOfThisDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyy");
        int year = Integer.valueOf(format.format(date)).intValue();
        format = new SimpleDateFormat("MM");
        int month = Integer.valueOf(format.format(date)).intValue() - 1;
        format = new SimpleDateFormat("dd");
        int day = Integer.valueOf(format.format(date)).intValue();
        calendar.set(year, month, day, 0, 0, 0);
        
        return new Date(calendar.getTime().getTime());
    }
    
    /**
     * 取得某日期的最晚时间
     * 
     * @param date
     *            输入20111010 ,返回20111010:23:59:59
     * 
     * @return
     */
    public static Date getLatestOfThisDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyy");
        int year = Integer.valueOf(format.format(date)).intValue();
        format = new SimpleDateFormat("MM");
        int month = Integer.valueOf(format.format(date)).intValue() - 1;
        format = new SimpleDateFormat("dd");
        int day = Integer.valueOf(format.format(date)).intValue();
        calendar.set(year, month, day, 23, 59, 59);
        
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 取得岁数
     * 
     * @param currentDate 当前日期
     * @param birthday 生日日期
     * @return 岁数
     */
    public static int getAge(Integer currentDate, Integer birthday) {
        int currentYear = currentDate.intValue()/10000 ;
        int currentMonthDate = currentDate.intValue()%10000 ;
        int birthYear = birthday.intValue()/10000 ;
        int birthMonthDate = birthday.intValue()%10000 ;
        
        if (currentMonthDate >= birthMonthDate) {
            return currentYear - birthYear ;
        } else {
            return currentYear - birthYear - 1 ;
        }
    }
    
    /**
     * 20090205 + 6 month => 20090805
     * @param date
     * @param months
     * @return
     * @throws Exception
     */
    public static Integer addMonth(Integer date, int months) throws Exception {
        
        if (date == null) return null ;
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        calendar.setTime(format.parse(date + "000000"));
        calendar.add(GregorianCalendar.MONTH, months);
        
        return new Integer(format.format(calendar.getTime()).substring(0,8)) ;
    }
    
    /**
     * 4GPOS日期格式转换成TW日期格式
     * @param posDate 4GPOS日期
     * @return TW日期
     */
    public static Integer pos2twDate(Integer posDate){
    	Integer twDate = null;        
        if(posDate != null && posDate.toString().length() == 8){       		
        	twDate = new Integer(posDate.intValue() - 19000000);
        }
        return twDate;
    }
    
    /**
     * 5GPOS日期格式转换成TW日期格式
     * @param posDate 5GPOS日期
     * @return TW日期
     */
    public static Integer pos5g2twDate(Integer posDate){
    	Integer twDate = null;        
        if(posDate != null && posDate.toString().length() == 8){       		
        	twDate = new Integer(posDate.intValue() - 19110000);
        }
        return twDate;
    }
    
    /**
     * TW日期格式转换成5GPOS日期格式
     * @param posDate 5GPOS日期
     * @return TW日期
     */
    public static Integer twDate2pos5g(Integer posDate){
    	Integer twDate = null;        
        if(posDate != null){       		
        	twDate = new Integer(posDate.intValue() + 19000000);
        }
        return twDate;
    }
    
	public static String buildDateStr(int year,int month,int day){
		StringBuilder str=new StringBuilder("");
		str.append(year);
		str.append(month>=10?month:"0"+month);
		str.append(day>=10?day:"0"+day);
		return str.toString();
	}
	
	/**
	 * 计算14个期望日期列表
	 * @param StdDate   计算所得到的基准配置时间
	 * @param deliveryDateSize  期望日期数量
	 * @param dateList 相应地区公共假期和店铺非送货日期集合(这些日期都是非工作日，不送货),统称公众假期
	 * @return 期望日期列表
	 */
	public static List<String> calcExpectedDateList(List<String> dateList,Date stdDate,int deliveryDateSize){
		List<String> result=new ArrayList<String>();//保存结果值
		boolean flag = false;
		while(deliveryDateSize>0){
			flag = DateFunc.checkDateInDateList(stdDate, dateList);// 检查日期是否在公众假期内
			if(!flag){
				result.add(DateFunc.date2Int(stdDate).toString());// 将符合条件的日期加入返回的结果列表里面
				deliveryDateSize--;
			}
			stdDate=DateFunc.addDate(stdDate,1);
		}
		return result;
	}
	
	public static boolean checkDateInDateList(Date expectDate,List<String> dateList){
		String orgDate=DateFunc.date2Int(expectDate).toString();
		if(dateList!=null&&dateList.size()>0){
			for (String dateStr : dateList) {
				if(orgDate.equals(dateStr)){
					return true;
				}
			}
		}
		return false;
	}
	
	//得到指定月的天数
	public static int getMonthLastDay(int year, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
		calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = calendar.get(Calendar.DATE);
		return maxDate;
	}
	
	/**
	 * 计算期望日期基准时间
	 * @param duration1   下午3点前基准配置时间
	 * @param duration2   下午3后后基准配置时间
	 * @param duration3   公众假期或周六周日基准配置时间
	 * @param dateList 相应地区公共假期和店铺非送货日期集合(这些日期都是非工作日，不送货),统称公众假期
	 * @return 计算得到的期望日期基准时间
	 */
	public static Date calcStandardDate(int duration1,int duration2,int duration3,List<String> dateList){
		Date targetDate = new Date();
		boolean flag=DateFunc.checkDateInDateList(targetDate,dateList);//检查下单当天是否为公众假期
		int duration=0;//根据规则，计算下单当天需要往前推移N个送货工作日
		
		if(flag){
			//如果是公众假期或(周六周日)
			duration=duration3;
		}else{
			//非公众假期(需要判断是下午3点前还是3点后)
			int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if(hours >= 15){
				duration=duration2;
			}else{
				duration=duration1;
			}
		}
		
		//计算推移后的几天当中，有多少天是在公众假期当中
		int delayDay=0;
		for (int i=0;i< duration;i++) {
			targetDate=DateFunc.addDate(targetDate,1);
			flag=DateFunc.checkDateInDateList(targetDate,dateList);
			if(flag){
				delayDay++;
			}
		}
		
		//最后得到总的需要推移的天数
		duration+=delayDay;
		
		return DateFunc.addDate(new Date(),duration);
	}
	
	/**
     * 将字符串型日期转成Sql日期型，例如：20060202
     * 
     * @param strDate
     *            指定字符串型日期
     * @return 日期型
     */
    public static java.sql.Date str2SqlDate(String strDate) {
        Calendar c = Calendar.getInstance();

        //String strDate = String.valueOf(nDate);
        if (strDate.length() != 8) {
            return null;
        }
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
        int date = Integer.parseInt(strDate.substring(6));

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, date);

        return new java.sql.Date(c.getTimeInMillis());
    }
    
    /**
     * 将Interger日期型转成SqlDate型
     * 
     * @param nDate
     *            Integer型日期
     * @return Date型日期
     */
    public static java.sql.Date int2SqlDate(Integer nDate) {
        return str2SqlDate(String.valueOf(nDate));
    }
    
    /**
     * 校验输入的时间格式是否正确
     * @param dateStr(yyyyMMdd HH.mm.ss)
     * @return
     */
    public static boolean checkDateTimeIsValid(String dateStr){
    	//第一种格式检验为yyyy－MM－dd HH:mm:ss
    	//Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    	
    	//第二种格式检验为yyyyMMdd HH:mm:ss
    	Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))((((0?[13578])|(1[02]))((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))((0?[1-9])|([1-2][0-9])|(30)))|(0?2((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))((((0?[13578])|(1[02]))((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))((0?[1-9])|([1-2][0-9])|(30)))|(0?2((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\.([0-5]?[0-9])((\\s)|(\\.([0-5]?[0-9])))))?$");
    	return  p.matcher(dateStr).matches();
    }

    /**
     * 将日期(String)转成java.util.Date型
     * 
     * @param dateTime
     *            指定日期
     * @return Date型日期
     */
    public static java.util.Date time2javaDate(String dateTime) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd HH.mm.ss");
        java.util.Date date = null;
        try{
        	date = format.parse(dateTime);
        }catch(Exception e){}
        return date;
    }
    
    public static String date2Str(Date date,String formatStr) {
    	if ( date == null ) {
    		return null;
    	}
        DateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }
   
	public static void main(String[] args) { 
      	 System.out.println( DateFunc.twDate2pos5g(Integer.valueOf("1141112")).intValue());
       
      	 String s = "20030229 23.59.59"; 
         System.out.println(s + " " + checkDateTimeIsValid(s)); 
 
         s = "20040229 23.59.59"; 
         System.out.println(s + " " + checkDateTimeIsValid(s)); 
 
         s = "20040431 0.59.59"; 
         System.out.println(s + " " + checkDateTimeIsValid(s)); 
 
         s = "20040430 01.11.0"; 
         System.out.println(s + " " + checkDateTimeIsValid(s)); 
 
         s = "20040430 0.0.0"; 
         System.out.println(s + " " + checkDateTimeIsValid(s)); 

         s = "20040430 00.00.59"; 
         System.out.println(s + " " + checkDateTimeIsValid(s)); 
    }
}
