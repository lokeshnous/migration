package com.advanceweb.afc.jb.common.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
	public static Date convertStringToSQLDate(String stringDate){
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		Date sqltDate=null;
		java.util.Date parsedUtilDate;
		try {
			if(null != stringDate){
				parsedUtilDate = formater.parse(stringDate);
				sqltDate= new java.sql.Date(parsedUtilDate.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return sqltDate;
	}
	
	public static String convertSQLDateToStdDate(String sqlDate){
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		java.util.Date dateSqlFormat = null;
		String stdDate = null;
		try {
			if(null != sqlDate){
				dateSqlFormat = formater.parse(sqlDate);
				DateFormat stdDateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
				stdDate = stdDateFormat.format(dateSqlFormat);
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return stdDate;
	}
}
