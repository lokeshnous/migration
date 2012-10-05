package com.advanceweb.afc.jb.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

public class CommonUtil {

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
				MMJBCommonConstants.DISP_DATE_PATTERN);
		Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.info(e);
		}
		return convertedDate;
	}

	public static Date convertDateStringToSQLDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				MMJBCommonConstants.NEWDATE_PATTERN);
		Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.info(e);
		}
		return convertedDate;
	}

	public static String convertSQLDateTimeToStdDateTime(String dateString) {

		String stdDateFormat = null;

		try {
			stdDateFormat = new SimpleDateFormat(
					MMJBCommonConstants.DISP_DATE_PATTERN, Locale.ENGLISH)
					.format(new SimpleDateFormat(
							MMJBCommonConstants.SQL_DATE_PATTERN,
							Locale.ENGLISH).parse(dateString));
		} catch (ParseException e) {
			LOGGER.info("" + e);
		}
		return stdDateFormat;
	}

	public static String convertSQLDateToStdDateString(String sqlDate) {

		String stdDateFormat = null;
		try {
			stdDateFormat = new SimpleDateFormat(
					MMJBCommonConstants.DISP_DATE_PATTERN, Locale.ENGLISH)
					.format(new SimpleDateFormat(
							MMJBCommonConstants.DATE_PATTERN, Locale.ENGLISH)
							.parse(sqlDate));
		} catch (ParseException e) {
			LOGGER.info(e);
		}
		return stdDateFormat;
	}

	public static Date convertStringToSQLDate(String startDate) {
		Date sqltDate = null;
		try {
			sqltDate = new java.sql.Date(new SimpleDateFormat(
					MMJBCommonConstants.SQL_DATE_PATTERN, Locale.ENGLISH)
					.parse(new SimpleDateFormat(
							MMJBCommonConstants.DISP_DATE_PATTERN,
							Locale.ENGLISH).format(startDate)).getTime());
		} catch (ParseException e) {
			LOGGER.info(e);
			e.printStackTrace();
		}
		return sqltDate;
	}

}
