package com.advanceweb.afc.jb.common.util;

import java.util.ArrayList;
import java.util.List;

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
	
	
	public static List<String> convertToCityStateStringList(List<LocationDTO> locationDTOList){
		
		List<String> list = new ArrayList<String>();
		for(LocationDTO locDTO : locationDTOList){
			list.add(locDTO.getCity()+", "+locDTO.getState());
		}
		return list;
	}
	
	public static List<String> convertToPostcodeStringList(List<LocationDTO> locationDTOList){
		
		List<String> list = new ArrayList<String>();
		for(LocationDTO locDTO : locationDTOList){
			list.add(locDTO.getPostcode());
		}
		return list;
	}

}
