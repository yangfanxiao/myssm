package com.yitianyike.myssm.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy年MM月dd日");

    private static final DateFormat LOG_DATE_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static String getCurrentDate() {
        return DATEFORMAT.format(Calendar.getInstance().getTime());
    }

    public static Date getNowDate(){
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String tt = sdf.format(date);
	    Timestamp time =  java.sql.Timestamp.valueOf(tt); 
        return time;
    }
    
    public static String getFormatDateAndTime(Date date) {
    	if(date == null){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    sdf.format(date);
        return sdf.format(date);
    }
    
    public static String getFormatDate(Date date) {
    	if(date == null){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        return sdf.format(date);
    }
    
}
