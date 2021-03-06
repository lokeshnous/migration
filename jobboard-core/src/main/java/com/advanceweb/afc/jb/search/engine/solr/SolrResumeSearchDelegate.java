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

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.resume.dao.ResumeSearchDAO;
import com.advanceweb.afc.jb.search.ResumeSearchDelegate;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchIndex;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.SearchResultDTO;
import com.advanceweb.afc.jb.search.dao.LocationDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public class SolrResumeSearchDelegate extends AbstractSolrSearchDelegate
		implements ResumeSearchDelegate {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(SolrResumeSearchDelegate.class);
	
	/** The Constant POSITION_STRING. */
	private static final String POSITION_STRING = "position";
	
	/** The location dao. */
	@Autowired
	private LocationDAO locationDAO;
	
	/** The resume search dao. */
	@Autowired
	private ResumeSearchDAO resumeSearchDAO;
	
	/**
	 * Instantiates a new solr resume search delegate.
	 *
	 * @param searchIndex the search index
	 */
	public SolrResumeSearchDelegate(SearchIndex searchIndex) {
		super(searchIndex);
	}

	
	/**
	 * This method is used to do the Solr Server Resume Search based on the passed
	 * parameters.
	 * 
	 * @param searchName
	 *            represents the type of the job search
	 * @param inputParams
	 *            contains the input parameters from the UI
	 * @param rows
	 *            represents how many rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return ResumeSearchResultDTO
	 * @throws JobBoardServiceException
	 * @throws JobBoardServiceException
	 */
	
	public ResumeSearchResultDTO resumeSearch(String searchName,
			Map<String, String> inputParams, long start, long rows)
			throws JobBoardServiceException {

		/*
		 * Check whether all the parameters coming from the UI is blank or not.
		 */
		if (validateInputParams(inputParams)) {
			LOGGER.debug("Empty Search criteria. Please enter a search criteria to search resumes.");
			return null;
		}

		// Prepare the parameters to be replaced in the buildParams method
		inputParams.put(SearchParamDTO.ROWS, Long.toString(rows));
		inputParams.put(SearchParamDTO.START, Long.toString(start));

		// The query parameter related to location is received as a name of a
		// city / state or as a pincode. The search parameter requires a
		// position represented as a string of latitude and longitude in the
		// format "(latitude, longitude)". If a parameter of type cityState is
		// found we calculate the position and add it to the list
		String cityState = inputParams.get(SearchParamDTO.CITY_STATE);
		if (!StringUtils.isEmpty(cityState)) {
			LocationDTO location = getLocationFromCityStateOrZipCode(cityState);
			String position = location.getLatitude() + ","
					+ location.getLongitude();
			inputParams.put(POSITION_STRING, position);
		}

		SearchResultDTO<SolrResumeDTO> searchResult = search(searchName,
				inputParams, SolrResumeDTO.class);

		if (searchResult == null) {
			LOGGER.debug("Resume not Found...");
			return null;

		} else {
			return prepareSearchResult(searchResult);
		}

	}
	
	
	/**
	 * Convert the SOLR specific result bean to ResumeDTO within the search result
	 * 
	 * @param searchResult
	 *            The search result with Solr specific ResumeBean
	 * @return Search result with ResumeDTO
	 */
	private ResumeSearchResultDTO prepareSearchResult(
			SearchResultDTO<SolrResumeDTO> searchResult) {

		ResumeSearchResultDTO result = new ResumeSearchResultDTO();
		result.setFacetMap(searchResult.getFacetMap());
		result.setRows(searchResult.getRows());
		result.setStart(searchResult.getStart());
		result.setResultCount(searchResult.getResultCount());

		List<ResumeDTO> resumeList = new ArrayList<ResumeDTO>();
		for (SolrResumeDTO dto : searchResult.getResultList()) {
			//resumeList.add(copyToResumeDTO(dto));
		}
		result.setResultList(resumeList);

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

		/*if (StringUtils.isEmpty(inputParams.get(SearchParamDTO.SEARCH_SEQ))) {
			LOGGER.debug(" Sequence ID is not present in the Search query.");
		}*/

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

	/*private ResumeDTO copyToResumeDTO(SolrResumeDTO solrResumeDTO) {

		ResumeDTO resumeDTO = new ResumeDTO();
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
		jobDTO.setJobId(jobSearchDTO.getJobId());
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

		return resumeDTO;

	}*/

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
		if (MMUtils.isIntNumber(cityState)) {
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


	/**
	 * This method is temporarily beig used to retrieving the 
	 * resume search result from DB.
	 * @param String searchString
	 * @return List<ResumeDTO>
	 */
	
	public List<ResumeDTO> resumeSearchFromDB(String searchString, int offset, int noOfRecords)
			throws JobBoardServiceException {
		return  resumeSearchDAO.getResumeSearchDetails(searchString, offset, noOfRecords);
	}


}
