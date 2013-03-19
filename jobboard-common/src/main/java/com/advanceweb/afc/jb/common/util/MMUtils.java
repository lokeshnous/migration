/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(MMUtils.class);

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

	/**
	 * This method checks whether the string is alpha numeric
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isAlphaNumeric(String s) {
		boolean containsDigit = false;

	    if(s != null){
	        for(char c : s.toCharArray()){
	            if(Character.isDigit(c)){
	                containsDigit = true;
	                break;
	            }
	        }
	    }

	    return containsDigit;
	}

	/**
	 * Convert to city state string list.
	 *
	 * @param locationDTOList the location dto list
	 * @return the list
	 */
	public static List<String> convertToCityStateStringList(
			List<LocationDTO> locationDTOList) {

		List<String> list = new ArrayList<String>();
		for (LocationDTO locDTO : locationDTOList) {
			list.add(locDTO.getCity() + ", " + locDTO.getState());
		}
		return list;
	}

	/**
	 * Convert to postcode string list.
	 *
	 * @param locationDTOList the location dto list
	 * @return the list
	 */
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
			LOGGER.error("Exception while getting Current Date And Time",e);
		}
		return date;

	}

	/**
	 * This method is called to compare the current date with the given date
	 * range So that for that particular user we don't need to decrease the
	 * credits and can post unlimited jobs with in the range.
	 * 
	 * @param xmlFeedStartDate
	 * @param xmlFeedEndDate
	 * @return
	 */
	public static boolean compareDateRangeWithCurrentDate(
			Date xmlFeedStartDate, Date xmlFeedEndDate) {

		if (null != xmlFeedStartDate && null != xmlFeedEndDate) {
			Date date = new Date();
			if (date.compareTo(xmlFeedStartDate) >= 0
					&& date.compareTo(xmlFeedEndDate) <= 0) {
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
	
	/**
	 * This method converts the date in string format to 'yyyy-MM-dd' 
	 * format for displaying it in the site map xml page.
	 * 
	 * @param dateString
	 * @return String
	 */	
	public static String convertDateToStdDateString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				MMJBCommonConstants.DATE_PATTERN, Locale.US);
		return formatter.format(date);
	}
	
	/**
	 * The method helps to encode the given string value by replacing the special 
	 * characters to valid strings.
	 * 
	 * @param encodingStr
	 * @return
	 */
	public static String encodeString(String encodingStr) {
		/*String encodedStr = encodingStr;
		encodedStr = encodingStr.replace("/", "U8sl");
		encodedStr = encodedStr.replace("\\", "U8bsl");
		try {
			encodedStr = URLEncoder.encode(encodedStr, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}*/
		String encodedStr;
		/*encodedStr = encodingStr.replace("/", "u82f");
		encodedStr = encodedStr.replace("\\", "u85c");
		encodedStr = encodedStr.replace("-", "u82d");
		encodedStr = encodedStr.replace("#", "u823");
		encodedStr = encodedStr.replace(";", "u83b");*/
		encodedStr = encodingStr.replace(" ", "-");
		encodedStr = encodedStr.replace("/", "-and-");
		return encodedStr;
	}

	/**
	 * The method helps to decode the given string value by replacing valid strings
	 * to special characters.
	 * 
	 * @param decodingStr
	 * @return
	 */
	public static String decodeString(String decodingStr) {
		/*String decodedStr = decodingStr;
		decodedStr = decodingStr.replace("U8sl", "/");
		decodedStr = decodedStr.replace("U8bsl", "\\");
		try {
			decodedStr = URLDecoder.decode(decodedStr, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}*/
		/*String decodedStr;
		decodedStr = decodingStr.replace("u82f", "/");
		decodedStr = decodedStr.replace("u85c", "\\");
		decodedStr = decodedStr.replace("-", " ");
		decodedStr = decodedStr.replace("u82d", "-");
		decodedStr = decodedStr.replace("u823", "#");
		decodedStr = decodedStr.replace("u83b", ";");*/
		String decodedStr;
		decodedStr = decodingStr.replace("-and-", "/");
		decodedStr = decodedStr.replace("-", " ");
		return decodedStr;
	}
	
	

}
