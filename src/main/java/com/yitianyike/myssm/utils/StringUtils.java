package com.yitianyike.myssm.utils;

import java.util.List;

public class StringUtils {
	
	public static Integer[] StringArrayToIntArray(String[] strs ) throws NumberFormatException{
		Integer[] ints = new Integer[strs.length];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = Integer.parseInt(strs[i]); 
		}
		return ints;
	}
	
	public static boolean isNotEmpty(String str){
		if (str == null || str.length() == 0) {
			return false;
		}
		return true;
	}
	
	public static boolean isEmpty(String str){
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Integer integer){
		if (integer == null || integer < 0) {
			return true;
		}
		return false;
	}
}
