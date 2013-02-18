package com.advanceweb.common.ads.location.service;

import java.util.List;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.AdLocation;

/**
 * Service for performing a search on the AdLocations based on the latitude
 * longitude combination or by the name of the city / state
 * 
 * @author sukeshnambiar
 * 
 */
public interface LocationIndexService {

	/**
	 * Returns the list of locations within the circle of given radius and the
	 * center located at the given latitude and longitude By the way Lucene
	 * performs spatial query, the results are not arranged in specific order.
	 * 
	 * It is wrong to assume that the first result closest to ther reference
	 * location
	 * 
	 * @param latitude
	 *            The latitude of the reference location
	 * @param longitude
	 *            The longitude of the reference location
	 * @param radius
	 *            The radius withing which the search is performed
	 * @return All the locations within the circle of given radius centered at
	 *         the given latitude and longitude
	 * @throws JobBoardServiceException
	 */
	List<AdLocation> findNearByLocations(Float latitude, Float longitude,
			Float radius) throws JobBoardServiceException;

	/**
	 * Find the location that has names matching with the city / state
	 * parameters passed in. The results are returned in a way that the best
	 * match is at the top of the list (index 0(
	 * 
	 * @param city
	 *            The pattern to match to city field
	 * @param state
	 *            The pattern to match to the state field
	 * @return The search result with city / state name matching the given
	 *         parameters
	 * @throws JobBoardServiceException
	 */
	List<AdLocation> findMatchingLocation(String city, String state)
			throws JobBoardServiceException;
}
