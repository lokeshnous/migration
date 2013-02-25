/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search.engine.solr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.search.JobSearchDelegate;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchIndex;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.SearchResultDTO;
import com.advanceweb.afc.jb.search.dao.LocationDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This class has been created for the Solr Server Job search functionalities.
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 10 July 2012
 */

public class SolrJobSearchDelegate extends AbstractSolrSearchDelegate
		implements JobSearchDelegate {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(SolrJobSearchDelegate.class);

	/** The location dao. */
	@Autowired
	private LocationDAO locationDAO;

	/**
	 * Instantiates a new solr job search delegate.
	 *
	 * @param searchIndex the search index
	 */
	public SolrJobSearchDelegate(SearchIndex searchIndex) {
		super(searchIndex);
	}

	/**
	 * This method is used to do the Solr Server Job Search based on the passed
	 * parameters.
	 * 
	 * @param inputParams
	 *            contains the input parameters from the UI
	 * @param rows
	 *            represents how many rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return JobSearchResultDTO
	 * @throws JobBoardServiceException
	 * @throws JobBoardServiceException
	 */
	@Override
	public JobSearchResultDTO jobSearch(
			Map<String, String> inputParams, long start, long rows)
					throws JobBoardServiceException {
		
		// get the search name
		String searchName = inputParams.get(SearchParamDTO.SEARCH_NAME);
		/*
		 * Check whether all the parameters coming from the UI is blank or not.
		 */
		if (searchName.equals(MMJBCommonConstants.KEYWORD_SEARCH) && validateInputParams(inputParams)) {
			LOGGER.debug("Empty Search criteria. Please enter a search criteria to search jobs.");
			return null;
		}
		
		// Prepare the parameters to be replaced in the buildParams method
		inputParams.put(SearchParamDTO.ROWS, Long.toString(rows));
		inputParams.put(SearchParamDTO.START, Long.toString(start));
		
		// The query parameter related to location is received as a name of a
		// city / state or as a pincode. The search parameter requires a
		// position represented as a sting of latitude and longitude in the
		// format "(latitude, longitude)". If a parameter of type cityState is
		// found we calculate the position and add it to the list
		String cityState = inputParams.get(SearchParamDTO.CITY_STATE);
		if (!StringUtils.isEmpty(cityState)) {
			LocationDTO location = getLocationFromCityStateOrZipCode(cityState);
			String position = location.getLatitude() + ","
					+ location.getLongitude();
			inputParams.put("position", position);
		}
		
		SearchResultDTO<SolrJobDTO> searchResult = search(searchName,
				inputParams, SolrJobDTO.class);
		
		if (searchResult == null) {
			LOGGER.debug("No Results Found...");
			return null;
			
		} else {
			return prepareSearchResult(searchResult);
		}
		
	}
	
	/**
	 * Convert the SOLR specific result bean to JobDTO within the search result
	 * 
	 * @param searchResult
	 *            The search result with Solr specific JobBean
	 * @return Search result with JobDTO
	 */
	private JobSearchResultDTO prepareSearchResult(
			SearchResultDTO<SolrJobDTO> searchResult) {

		JobSearchResultDTO result = new JobSearchResultDTO();
		result.setFacetMap(searchResult.getFacetMap());
		result.setRows(searchResult.getRows());
		result.setStart(searchResult.getStart());
		result.setResultCount(searchResult.getResultCount());

		List<JobDTO> jobList = new ArrayList<JobDTO>();
		for (SolrJobDTO dto : searchResult.getResultList()) {
			jobList.add(copyToJobDTO(dto));
		}
		result.setResultList(jobList);

		return result;
	}

	/**
	 * Validate the input parameters passed to the search service from the UI
	 * 
	 * @param searchName
	 *            - The name of the search to be used
	 * @param inputParams
	 *            - The user input parameters
	 * @return true if the parameters are valid, else false
	 */
	private boolean validateInputParams(final Map<String, String> inputParams) {

		if (StringUtils.isEmpty(inputParams.get(SearchParamDTO.SEARCH_SEQ))) {
			LOGGER.debug(" Sequence ID is not present in the Search query.");
		}

		if(SearchParamDTO.SEARCH_NAME.equalsIgnoreCase(MMJBCommonConstants.BROWSE_SEARCH)){
			return false;
		}
		LOGGER.debug("Validating search inputs"
				+ inputParams.get(SearchParamDTO.KEYWORDS) + ","
				+ inputParams.get(SearchParamDTO.CITY_STATE) + ","
				+ inputParams.get(SearchParamDTO.RADIUS));

		// TODO implement searchName specific validations as appropriate
		return (StringUtils.isEmpty(inputParams.get(SearchParamDTO.KEYWORDS)))
				&& (StringUtils.isEmpty(inputParams
						.get(SearchParamDTO.CITY_STATE)))
				&& (StringUtils.isEmpty(inputParams.get(SearchParamDTO.RADIUS)));
	}

	/**
	 * This method is used to copy the content of JobSearchDTO list to JobDTO
	 * list to make the returning list independent of Solr.
	 * 
	 * @param jobSearchDTOList
	 * @return List<JobDTO>
	 */

	private JobDTO copyToJobDTO(SolrJobDTO jobSearchDTO) {

		JobDTO jobDTO = new JobDTO();
		jobDTO.setAdText(jobSearchDTO.getAdText());
		jobDTO.setApplyOnline(jobSearchDTO.getApplyOnline());
		jobDTO.setBlindAd(jobSearchDTO.getBlindAd());
		jobDTO.setCity(jobSearchDTO.getCity());
		jobDTO.setCompany(jobSearchDTO.getCompany());
		jobDTO.setEmail(jobSearchDTO.getEmail());
		jobDTO.setEmailDisplay(jobSearchDTO.getEmailDisplay());
		jobDTO.setFacilityName(jobSearchDTO.getFacilityName());
		jobDTO.setFeatured(jobSearchDTO.isFeatured());
		jobDTO.setInternationalJob(jobSearchDTO.isInternationalJob());
		jobDTO.setJobCount(jobSearchDTO.getJobCount());
		jobDTO.setJobGeo(jobSearchDTO.getJobGeo());
		jobDTO.setJobGeo0LatLon(jobSearchDTO.getJobGeo0LatLon());
		jobDTO.setJobGeo1LatLon(jobSearchDTO.getJobGeo1LatLon());
		jobDTO.setJobId(Integer.parseInt(jobSearchDTO.getJobId()));
		jobDTO.setJobNumber(jobSearchDTO.getJobNumber());
		jobDTO.setJobPosition(jobSearchDTO.getJobPosition());
		jobDTO.setJobTitle(jobSearchDTO.getJobTitle());
		jobDTO.setNationalJob(jobSearchDTO.isNationalJob());
		jobDTO.setPostCode(jobSearchDTO.getPostCode());
		jobDTO.setPostedDate(jobSearchDTO.getPostedDate());
		jobDTO.setState(jobSearchDTO.getState());
		jobDTO.setUrl(jobSearchDTO.getUrl());
		jobDTO.setUrlDisplay(jobSearchDTO.getUrlDisplay());
		//Newly added Fields
		jobDTO.setTemplateId(jobSearchDTO.getTemplateId());
		jobDTO.setPackageName(jobSearchDTO.getPackageName());
		jobDTO.setIsPremium(jobSearchDTO.getIsPremium());
		jobDTO.setUniversalGeo(jobSearchDTO.isUniversalGeo());
		jobDTO.setHideCity(jobSearchDTO.getHideCity());
		jobDTO.setHideState(jobSearchDTO.getHideState());
		jobDTO.setHidePostcode(jobSearchDTO.getHidePostcode());
		jobDTO.setHideCountry(jobSearchDTO.getHideCountry());
		jobDTO.setCountry(jobSearchDTO.getCountry());
		jobDTO.setFacilityId(jobSearchDTO.getFacilityId());

		return jobDTO;

	}

	/**
	 * Finds a city from the given combination of city and state or zip code. If
	 * multiple results are found matching the result, the first result is
	 * returned.
	 * 
	 * @param cityState
	 *            String representing the city and state in the format
	 *            <city>,<state> or a zip code
	 * @return The Location representing the input parameter. Returns null if no
	 *         match is found
	 * @throws JobBoardServiceException
	 *             TODO Ideally be part of a location service
	 */
	private LocationDTO getLocationFromCityStateOrZipCode(String cityState)
			throws JobBoardServiceException {
		List<LocationDTO> locations = null;
		if (MMUtils.isAlphaNumeric(cityState)) {
			try {
				locations = locationDAO.getLocationByPostcode(cityState);
			} catch (JobBoardDataException e) {
				throw new JobBoardServiceException(
						"Error while fetching the postcode details..." + e);
			}

		} else {

			String[] tokens = cityState.split(MMJBCommonConstants.COMMA);
			if (tokens.length >= 2) {
				try {
					locations = locationDAO.getLocationByCityState(
							tokens[0].trim(), tokens[1].trim());
				} catch (JobBoardDataException e) {
					throw new JobBoardServiceException(
							"Error while fetching the city state details..."
									+ e);
				}
			} else {
				LOGGER.debug("Please Enter City and State by provinding comma(,) in between them. ");
			}

		}

		return (locations.isEmpty()) ? new LocationDTO() : locations.get(0);

	}

}
