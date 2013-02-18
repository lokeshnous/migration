package com.advanceweb.afc.jb.lookup.service;

import java.util.List;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 21st Aug 2012 onwards
 */

public interface LookupService {
	
	/**
	 * This method will do the location search for city and state
	 * to display in the auto complete box in the UI.
	 * @param keywords represents  search word for auto complete
	 * @return List<LocationDTO> which contains the city and state or postcode 
	 */
	
	List<LocationDTO> locationSearch(String keywords);
	
	/**
	 * This method will do the location search for city and state
	 * to display in the auto complete box in the UI.
	 * @param keywords represents  search word for auto complete
	 * @return List<LocationDTO> which contains the city and state or postcode 
	 */
	
	List<LocationDTO> cityStateSearch(String keywords);

	/**
	 * Get the state full name by short or alias name of state.
	 * 
	 * @param stateShortForm
	 * @return
	 */
	String getStateFullName(String stateShortForm);
	
	/**
	 * Validate city state and zip code
	 * @param city
	 * @param state
	 * @param Zip
	 * @return
	 * @throws JobBoardServiceException
	 */
	boolean validateCityStateZip(String city,String state,String zip)  throws JobBoardServiceException;
	

}
