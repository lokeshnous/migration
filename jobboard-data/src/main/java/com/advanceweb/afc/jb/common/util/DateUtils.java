package com.advanceweb.afc.jb.common.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
	public static Date convertStringToSQLDateTime(String stringDate) {
		DateFormat formater = new SimpleDateFormat(MMJBCommonConstants.SQL_DATE_PATTERN);
		Date sqltDate = null;
		java.util.Date parsedUtilDate;
		try {
			if (null != stringDate) {
				parsedUtilDate = formater.parse(stringDate);
				sqltDate = new java.sql.Date(parsedUtilDate.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqltDate;
	}

	public static String convertSQLDateTimeToStdDateTime(String sqlDate) {
		DateFormat formater = new SimpleDateFormat(MMJBCommonConstants.SQL_DATE_PATTERN);
		java.util.Date dateSqlFormat = null;
		String stdDate = null;
		try {
			if (null != sqlDate) {
				dateSqlFormat = formater.parse(sqlDate);
				DateFormat stdDateFormat = new SimpleDateFormat(MMJBCommonConstants.DISP_DATE_PATTERN);
				stdDate = stdDateFormat.format(dateSqlFormat);

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stdDate;
	}
	
	public static String convertSQLDateToStdDate(String sqlDate){
		DateFormat formater = new SimpleDateFormat(MMJBCommonConstants.SQL_DATE_PATTERN); 
		java.util.Date dateSqlFormat = null;
		String stdDate = null;
		try {
			if(null != sqlDate){
				dateSqlFormat = formater.parse(sqlDate);
				DateFormat stdDateFormat = new SimpleDateFormat(MMJBCommonConstants.DISP_DATE_PATTERN); 
				stdDate = stdDateFormat.format(dateSqlFormat);
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return stdDate;
	}

	/**
	 * This method helps to convert SQLDate To Stdandard Date String
	 * 
	 * @param sqlDate
	 * @return
	 */
	public static String convertSQLDateToStdDateString(String sqlDate) {
		String pattern = MMJBCommonConstants.DATE_PATTERN;
		DateFormat formater = new SimpleDateFormat(pattern);
		java.util.Date dateSqlFormat = null;
		String stdDate = null;
		try {
			if (null != sqlDate) {
				dateSqlFormat = formater.parse(sqlDate);
				DateFormat stdDateFormat = new SimpleDateFormat(
						MMJBCommonConstants.DISP_DATE_PATTERN);
				stdDate = stdDateFormat.format(dateSqlFormat);

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stdDate;
	}

	/**
	 * This method helps to convert date in string To SQL date
	 * 
	 * @param dateInStr
	 *            : Date in string
	 * @return
	 */
	public static Date convertDateStringToSQLDate(String dateInStr) {
		String pattern = MMJBCommonConstants.DATE_PATTERN;
		String dateStrpattern = MMJBCommonConstants.NEWDATE_PATTERN;
		DateFormat formater = new SimpleDateFormat(pattern);
		DateFormat dateStrFormater = new SimpleDateFormat(dateStrpattern);

		Date sqltDate = null;
		java.util.Date parsedUtilDate;
		try {
			if (null != dateInStr) {
				parsedUtilDate = (java.util.Date) formater.parse(formater
						.format(dateStrFormater.parse(dateInStr)));
				sqltDate = new java.sql.Date(parsedUtilDate.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqltDate;
	}

}
