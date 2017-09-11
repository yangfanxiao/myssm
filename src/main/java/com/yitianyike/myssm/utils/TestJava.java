package com.yitianyike.myssm.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestJava {
	public static void main(String[] args) {
		System.out.println(DateUtils.getFormatDate(getDate()));
		String a = "2017";
		String aa = a + "-12-31";
		String bb = "2017-08-18";
		Date datea = java.sql.Date.valueOf(aa);
				Date dateb = java.sql.Date.valueOf(bb);
		if (datea.before(dateb) ) {
			System.out.println("bb>aa:"+ "ture");
		}else{
			System.out.println("bb>aa:"+ "false");
		}
	}
	
	 public static Date getDate(){
	    	Date date = new Date();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    String tt = sdf.format(date);
		    Date time =  java.sql.Date.valueOf(tt); 
	        return time;
	    }
}
