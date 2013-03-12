/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.mysql.jdbc.StringUtils;

public class CommonUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);

	/**
	 * This method converts the date in string format to required format for
	 * displaying it in the job search result page.
	 * 
	 * @param dateString
	 * @return String
	 */

	public static String convertToReqdDateString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				MMJBCommonConstants.DISP_DATE_PATTERN, Locale.US);
		return formatter.format(date);
	}

	/**
	 * This method is used to convert a String date to Date object into the
	 * required format.
	 * 
	 * @param date
	 * @return Date object
	 */

	public static Date convertToDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				MMJBCommonConstants.DISP_DATE_PATTERN, Locale.ENGLISH);
		Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.error(e);
		}
		return convertedDate;
	}

	/**
	 * Convert date string to sql date.
	 *
	 * @param date the date
	 * @return the date
	 */
	public static Date convertDateStringToSQLDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				MMJBCommonConstants.NEWDATE_PATTERN, Locale.ENGLISH);
		Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.error(e);
		}
		return convertedDate;
	}

	/**
	 * Convert sql date time to std date time.
	 *
	 * @param dateString the date string
	 * @return the string
	 */
	public static String convertSQLDateTimeToStdDateTime(String dateString) {

		String stdDateFormat = null;

		try {
			stdDateFormat = new SimpleDateFormat(
					MMJBCommonConstants.DISP_DATE_PATTERN, Locale.ENGLISH)
					.format(new SimpleDateFormat(
							MMJBCommonConstants.SQL_DATE_PATTERN,
							Locale.ENGLISH).parse(dateString));
		} catch (ParseException e) {
			LOGGER.error(e);
		}
		return stdDateFormat;
	}

	/**
	 * Convert sql date to std date string.
	 *
	 * @param sqlDate the sql date
	 * @return the string
	 */
	public static String convertSQLDateToStdDateString(String sqlDate) {

		String stdDateFormat = null;
		try {
			if (null != sqlDate) {
				stdDateFormat = new SimpleDateFormat(
						MMJBCommonConstants.DISP_DATE_PATTERN, Locale.ENGLISH)
						.format(new SimpleDateFormat(
								MMJBCommonConstants.DATE_PATTERN,
								Locale.ENGLISH).parse(sqlDate));
			}
		} catch (ParseException e) {
			LOGGER.error(e);
		}
		return stdDateFormat;
	}

	/**
	 * Convert string to sql date.
	 *
	 * @param startDate the start date
	 * @return the date
	 */
	public static Date convertStringToSQLDate(String startDate) {
		Date sqltDate = null;
		try {
			sqltDate = new java.sql.Date(new SimpleDateFormat(
					MMJBCommonConstants.SQL_DATE_PATTERN, Locale.ENGLISH)
					.parse(new SimpleDateFormat(
							MMJBCommonConstants.DISP_DATE_PATTERN,
							Locale.ENGLISH).format(startDate)).getTime());
		} catch (ParseException e) {
			LOGGER.error(e);
//			e.printStackTrace();
		}
		return sqltDate;
	}

	/**
	 * This method helps to convert Date from string To java.util.Date
	 * 
	 * @param dateInStr
	 *            : Date in string
	 * @return Date object from util package
	 */
	public static Date convertDateStringToDate(String dateInStr) {
		String pattern = MMJBCommonConstants.DATE_PATTERN;
		DateFormat formater = new SimpleDateFormat(pattern,Locale.ENGLISH);
		Date utilDate = null;
		
		try {
			if (null != dateInStr) {
				utilDate=(Date)formater.parse(dateInStr);
			}
		} catch (ParseException e) {
			LOGGER.error("convertDateStringToDate Exception",e);
		}
		return utilDate;
	}
	
	/**
	 * This method helps to convert UtilDate To SQLDate format.
	 * 
	 * @param sqlDate
	 * @return
	 */
	public static Date convtStringToSQLDate(String strDate) {

		Date sqltDate = null;

		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(strDate)) {

				sqltDate = new java.sql.Date(new SimpleDateFormat(
						MMJBCommonConstants.SQL_DATE_PATTERN, Locale.ENGLISH)
						.parse(new SimpleDateFormat(
								MMJBCommonConstants.SQL_DATE_PATTERN,
								Locale.ENGLISH).format(new SimpleDateFormat(
								MMJBCommonConstants.DISP_DATE_PATTERN,
								Locale.ENGLISH).parse(strDate))).getTime());

			}
		} catch (ParseException e) {
			LOGGER.error("convertDateStringToSQLDate Exception",e);
		}
		return sqltDate;
	}
	
	/**
	 * This method will convert String date to Date object.
	 * @param strDate
	 * @return Date
	 */
	
	public static Date stringDateToSQLDate(String strDate) {

		Date sqltDate = null;

		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(strDate)) {

				sqltDate = new java.sql.Date(new SimpleDateFormat(
						MMJBCommonConstants.SQL_DATE_PATTERN, Locale.ENGLISH)
						.parse(new SimpleDateFormat(
								MMJBCommonConstants.SQL_DATE_PATTERN,
								Locale.ENGLISH).format(new SimpleDateFormat(
								MMJBCommonConstants.JSON_DATE_FORMAT,
								Locale.ENGLISH).parse(strDate))).getTime());

			}
		} catch (ParseException e) {
			LOGGER.error("convertDateStringToSQLDate Exception",e);
		}
		return sqltDate;
	}
	
	
	/**
	 * Str date to sql date.
	 *
	 * @param strDate the str date
	 * @return the date
	 */
	public static Date strDateToSQLDate(String strDate) {

		Date sqltDate = null;

		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(strDate)) {

				sqltDate = new java.sql.Date(new SimpleDateFormat(
						MMJBCommonConstants.SQL_DATE_PATTERN, Locale.ENGLISH)
						.parse(new SimpleDateFormat(
								MMJBCommonConstants.SQL_DATE_PATTERN,
								Locale.ENGLISH).format(new SimpleDateFormat(
								MMJBCommonConstants.NEWDATE_PATTERN,
								Locale.ENGLISH).parse(strDate))).getTime());

			}
		} catch (ParseException e) {
			LOGGER.error("convertDateStringToSQLDate Exception",e);
		}
		return sqltDate;
	}
	
	
	
}
