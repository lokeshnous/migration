package com.advanceweb.afc.jb.common.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.log4j.Logger;

public class DateUtils {

	private static final Logger LOGGER = Logger
			.getLogger("JobSearchActivityController.class");

	public static Date convertStringToSQLDateTime(String stringDate) {
		DateFormat formater = new SimpleDateFormat(
				MMJBCommonConstants.SQL_DATE_PATTERN,Locale.ENGLISH);
		Date sqltDate = null;
		java.util.Date parsedUtilDate;
		try {
			if (null != stringDate) {
				parsedUtilDate = formater.parse(stringDate);
				sqltDate = new java.sql.Date(parsedUtilDate.getTime());
			}
		} catch (ParseException e) {
			LOGGER.info("convertStringToSQLDateTime Exception");
			// e.printStackTrace();
		}
		return sqltDate;
	}

	public static String convertSQLDateTimeToStdDateTime(String sqlDate) {
		DateFormat formater = new SimpleDateFormat(
				MMJBCommonConstants.SQL_DATE_PATTERN,Locale.ENGLISH);
		java.util.Date dateSqlFormat = null;
		String stdDate = null;
		try {
			if (null != sqlDate) {
				dateSqlFormat = formater.parse(sqlDate);
				DateFormat stdDateFormat = new SimpleDateFormat(
						MMJBCommonConstants.DISP_DATE_PATTERN,Locale.ENGLISH);
				stdDate = stdDateFormat.format(dateSqlFormat);

			}
		} catch (ParseException e) {
			LOGGER.info("convertSQLDateTimeToStdDateTime Exception");
			// e.printStackTrace();
		}
		return stdDate;
	}

	public static String convertSQLDateToStdDate(String sqlDate) {
		DateFormat formater = new SimpleDateFormat(
				MMJBCommonConstants.SQL_DATE_PATTERN,Locale.ENGLISH);
		java.util.Date dateSqlFormat = null;
		String stdDate = null;
		try {
			if (null != sqlDate) {
				dateSqlFormat = formater.parse(sqlDate);
				DateFormat stdDateFormat = new SimpleDateFormat(
						MMJBCommonConstants.DISP_DATE_PATTERN,Locale.ENGLISH);
				stdDate = stdDateFormat.format(dateSqlFormat);

			}
		} catch (ParseException e) {
			LOGGER.info("convertSQLDateToStdDate Exception");
			// e.printStackTrace();
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
		DateFormat formater = new SimpleDateFormat(pattern,Locale.ENGLISH);
		java.util.Date dateSqlFormat = null;
		String stdDate = null;
		try {
			if (null != sqlDate) {
				dateSqlFormat = formater.parse(sqlDate);
				DateFormat stdDateFormat = new SimpleDateFormat(
						MMJBCommonConstants.DISP_DATE_PATTERN,Locale.ENGLISH);
				stdDate = stdDateFormat.format(dateSqlFormat);

			}
		} catch (ParseException e) {
			LOGGER.info("convertSQLDateToStdDateString Exception");
			// e.printStackTrace();
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
		DateFormat formater = new SimpleDateFormat(pattern,Locale.ENGLISH);
		DateFormat dateStrFormater = new SimpleDateFormat(dateStrpattern,Locale.ENGLISH);

		Date sqltDate = null;
		java.util.Date parsedUtilDate;
		try {
			if (null != dateInStr) {
				parsedUtilDate = (java.util.Date) formater.parse(formater
						.format(dateStrFormater.parse(dateInStr)));
				sqltDate = new java.sql.Date(parsedUtilDate.getTime());
			}
		} catch (ParseException e) {
			LOGGER.info("convertDateStringToSQLDate Exception");
			// e.printStackTrace();
		}
		return sqltDate;
	}

}
