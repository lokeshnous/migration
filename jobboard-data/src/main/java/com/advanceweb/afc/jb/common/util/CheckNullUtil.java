package com.advanceweb.afc.jb.common.util;

public class CheckNullUtil {
	
	public static String isNull(String checkStr){
		if(checkStr == null)
			return "Not Available";
		else
			return checkStr;
	}
	

}
