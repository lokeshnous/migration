package com.advanceweb.afc.jb.search.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

public interface LocationDAO {
	
	
	/**
	 * This method gets the latitude and longitude from the JPLoaction table.
	 * @param String  Postcode
	 * @return List<Float> of latitude and longitude
	 */
	List<LocationDTO> getLocationByPostcode(String postcode) throws JobBoardDataException;
	
	
	/**
	 * This method gets the latitude and longitude from the JPLoaction table.
	 * @param String  city and state
	 * @return List<Float> of latitude and longitude
	 */
	List<LocationDTO> getLocationByCityState(String city, String state) throws JobBoardDataException;
	
	

}
