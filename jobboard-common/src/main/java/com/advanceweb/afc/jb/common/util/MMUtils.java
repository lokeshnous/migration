package com.advanceweb.afc.jb.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import com.advanceweb.afc.jb.common.LocationDTO;

public class MMUtils {

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
			list.add(locDTO.getCity() + ", " + locDTO.getState());
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
			return "N/A";
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
//			LOGGER.info("getCurrentDateAndTime Exception");
		}
		return date;

	}

}
