package com.advanceweb.afc.jb.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.advanceweb.afc.jb.common.LocationDTO;

public class MMUtils {

	private static final Logger LOGGER = Logger
			.getLogger(MMUtils.class);
	
	/**
	 * This method checks whether the String parameter is int or not.
	 * 
	 * @param String
	 * @return boolean
	 */

	public static boolean isIntNumber(String num) {
		try {
			Integer.parseInt(num);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static List<String> convertToCityStateStringList(
			List<LocationDTO> locationDTOList) {

		List<String> list = new ArrayList<String>();
		for (LocationDTO locDTO : locationDTOList) {
			list.add(locDTO.getCity() + "," + locDTO.getState());
		}
		return list;
	}

	public static List<String> convertToPostcodeStringList(
			List<LocationDTO> locationDTOList) {

		List<String> list = new ArrayList<String>();
		for (LocationDTO locDTO : locationDTOList) {
			list.add(locDTO.getPostcode());
		}
		return list;
	}

	/**
	 * This method is used to get the Url splitted into key and value.
	 * 
	 * @param url
	 * @return Map<String, String>
	 */
	public static Map<String, String> getUrlMap(String url) {

		Map<String, String> urlMap = new HashMap<String, String>();
		StringTokenizer stoken = new StringTokenizer(url, ";");
		while (stoken.hasMoreTokens()) {
			String key = stoken.nextToken();

			StringTokenizer str = new StringTokenizer(key, "=");
			while (str.hasMoreTokens()) {
				String nameStr = str.nextToken();
				if (nameStr == null) {
					nameStr = " ";
				}
				String valStr = "";
				if (str.hasMoreTokens()) {
					valStr = str.nextToken();
					if (valStr == null) {
						valStr = " ";
					}
				}
				urlMap.put(nameStr, valStr);
			}

			String val = stoken.nextToken();
			StringTokenizer strFrst = new StringTokenizer(val, "=");
			while (strFrst.hasMoreTokens()) {
				String nameStr1 = strFrst.nextToken();
				if (nameStr1 == null) {
					nameStr1 = " ";
				}
				String valStr1 = "";
				if (strFrst.hasMoreTokens()) {
					valStr1 = strFrst.nextToken();
					if (valStr1 == null) {
						valStr1 = " ";
					}
				}

				urlMap.put(nameStr1, valStr1);
			}
		}
		return urlMap;
	}

	/**
	 * This method will check if the passed String is Null or not and depending
	 * on the condition it will return the string.
	 * 
	 * @param checkStr
	 * @return
	 */
	public static String isNull(String checkStr) {
		if (checkStr == null) {
			return "";
		} else {
			return checkStr;
		}
	}

	/**
	 * This method will return current Date.
	 * 
	 * @return Date object
	 */

	public static java.util.Date getCurrentDateAndTime() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
				Locale.US);
		java.util.Date date = new java.util.Date();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
				Locale.US);

		try {
			date = parser.parse(dateFormat.format(date));
		} catch (ParseException e) {
			LOGGER.info("Exception while getting Current Date And Time");
		}
		return date;

	}
	
	/**
	 * This method is called to compare the current date with the given date range
	 * So that for that particular user we don't need to decrease the credits
	 * and can post unlimited jobs with in the range.
	 * @param xmlFeedStartDate
	 * @param xmlFeedEndDate
	 * @return
	 */
	public static boolean compareDateRangeWithCurrentDate(Date xmlFeedStartDate, Date xmlFeedEndDate){
		
		if(null != xmlFeedStartDate && null != xmlFeedEndDate){
			Date date = new Date();
			if(date.compareTo(xmlFeedStartDate) >= 0 && date.compareTo(xmlFeedEndDate) <=0){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method converts the date in string format to required format for
	 * displaying it in the job search result page.
	 * 
	 * @param dateString
	 * @return String
	 */

	public static String convertToReqdDateString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				MMJBCommonConstants.JSON_DATE_FORMAT, Locale.US);
		return formatter.format(date);
	}
	
}
