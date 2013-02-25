/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

public interface LocationDAO {

	/**
	 * This method gets the latitude and longitude from the JPLoaction table.
	 * 
	 * @param String
	 *            Postcode
	 * @return List<Float> of latitude and longitude
	 */
	List<LocationDTO> getLocationByPostcode(String postcode)
			throws JobBoardDataException;

	/**
	 * This method gets the latitude and longitude from the JPLoaction table.
	 * 
	 * @param String
	 *            city and state
	 * @return List<Float> of latitude and longitude
	 */
	List<LocationDTO> getLocationByCityState(String city, String state)
			throws JobBoardDataException;

	/**
	 * This method gets the Postcode from the JPLocation table.
	 * 
	 * @param String
	 *            city and state
	 * @return List<LocationDTO> of postcode
	 */

	List<LocationDTO> getPostcodeLocationByKeyword(String keywords);

	/**
	 * This method gets the City and State from the JPLocation table.
	 * 
	 * @param String
	 *            postcode
	 * @return List<LocationDTO> of city and state
	 */

	List<LocationDTO> getCityStateLocationByKeyword(String keywords);
	
	/**
	 * This method gets the City and State from the JPLocation table.
	 * 
	 * @param String
	 *            postcode
	 * @return List<LocationDTO> of city and state
	 */

	List<LocationDTO> getCityAndStateLocationByKeyword(String keywords);

	/**
	 * Get the state full name by short or alias name of state.
	 * 
	 * @param stateShortForm
	 * @return
	 */
	String getStateFullName(String stateShortForm);

	/**
	 * Get all the locations from jp_location database. There is no order
	 * guaranteed. List contains the objects in the order received from the ORM
	 * layer
	 * 
	 * @return All the locations specified in jp_location table.
	 */

	List<LocationDTO> findAll();

	/**
	 * 
	 * @param city
	 * @return
	 */
	boolean validateCityStateZip(String city,String state,String zip) throws JobBoardDataException;

	
}
