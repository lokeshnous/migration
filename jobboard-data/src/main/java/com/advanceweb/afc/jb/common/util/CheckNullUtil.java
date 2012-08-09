package com.advanceweb.afc.jb.common.util;

/**
 * This class has been created as a Utility class
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 30th July 2012
 */

public class CheckNullUtil {
	
	/**
	 * This method will check if the passed String is Null or not and 
	 * depending on the condition it will return the string.
	 * @param checkStr
	 * @return
	 */
	public static String isNull(String checkStr){
		if(checkStr == null){
			return "N/A";
		}else{
			return checkStr;
		}
	}
	

}
