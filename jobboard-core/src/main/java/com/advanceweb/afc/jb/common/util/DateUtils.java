package com.advanceweb.afc.jb.common.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.StringUtils;

@Repository("dateUtils")
public class DateUtils {

	private static final Logger LOGGER = Logger.getLogger(DateUtils.class);

	/**
	 * Method to convert sql date time to standard date time format.
	 * 
	 * @param sqlDate
	 * @return
	 */
	public static String convertSQLDateTimeToStdDateTime(String sqlDate) {

		String stdDate = null;

		try {
			if (null != sqlDate) {

				stdDate = new SimpleDateFormat(
						MMJBCommonConstants.DISP_DATE_PATTERN, Locale.ENGLISH)
						.format(new SimpleDateFormat(
								MMJBCommonConstants.SQL_DATE_PATTERN,
								Locale.ENGLISH).parse(sqlDate));
			}

		} catch (ParseException e) {
			LOGGER.info("convertSQLDateTimeToStdDateTime Exception");
		}
		return stdDate;
	}

	/**
	 * This method helps to convert SQLDate To standard Date String
	 * 
	 * @param sqlDate
	 * @return
	 */
	public static String convertSQLDateToStdDateString(String sqlDate) {

		String stdDate = null;

		try {
			if (null != sqlDate) {
				stdDate = new SimpleDateFormat(
						MMJBCommonConstants.DISP_DATE_PATTERN, Locale.ENGLISH)
						.format(new SimpleDateFormat(
								MMJBCommonConstants.DATE_PATTERN,
								Locale.ENGLISH).parse(sqlDate));

			}
		} catch (ParseException e) {
			LOGGER.info("convertSQLDateToStdDateString Exception");
		}
		return stdDate;
	}

	/**
	 * This method helps to convert date in string To SQL date format.
	 * 
	 * @param dateInStr
	 *            : Date in string
	 * @return Date object
	 */
	public static Date convertDateStringToSQLDate(String dateInStr) {

		Date sqltDate = null;

		try {
			if (null != dateInStr) {

				sqltDate = new java.sql.Date(new SimpleDateFormat(
						MMJBCommonConstants.DATE_PATTERN, Locale.ENGLISH)
						.parse(new SimpleDateFormat(
								MMJBCommonConstants.DATE_PATTERN,
								Locale.ENGLISH).format(new SimpleDateFormat(
								MMJBCommonConstants.NEWDATE_PATTERN,
								Locale.ENGLISH).parse(dateInStr))).getTime());

			}
		} catch (ParseException e) {
			LOGGER.info("convertDateStringToSQLDate Exception");
		}
		return sqltDate;
	}

	/**
	 * This method helps to convert UtilDate To SQLDate format.
	 * 
	 * @param sqlDate
	 * @return
	 */
	public static Date convertStringToSQLDate(String strDate) {

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
			LOGGER.info("convertDateStringToSQLDate Exception");
		}
		return sqltDate;
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
			convertedDate = (Date) dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.info(e);
		}
		return convertedDate;
	}


}
