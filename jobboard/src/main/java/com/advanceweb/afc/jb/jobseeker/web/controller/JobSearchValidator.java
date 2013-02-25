/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.lookup.service.LookupService;

/**
 * 
 * @author Pramoda Patil
 * 
 * @Version 1.0
 * @Since 9th Oct, 2012
 */
@Component("jobSearchValidator")
public class JobSearchValidator {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JobSearchValidator.class);

	/** The Constant CURRENT_URL. */
	private static final String CURRENT_URL = "currentUrl";

	/** The ajax msg. */
	@Value("${ajaxMsg}")
	private String ajaxMsg;

	/** The dothtml extention. */
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	/** The ajax navigation path. */
	@Value("${ajaxNavigationPath}")
	private String ajaxNavigationPath;

	/** The jb search val keyword. */
	@Value("${jobSearchValidateKeyword}")
	private String jbSearchValKeyword;

	/** The jb search val city. */
	@Value("${jobSearchValidateCity}")
	private String jbSearchValCity;

	/** The jb searh val city state. */
	@Value("${jobSearchValidateCityState}")
	private String jbSearhValCityState;
	
	/** The lookup service. */
	@Autowired
	private LookupService lookupService;

	/**
	 * validate job apply method like by ATS, Website or Email and navigate to
	 * respected site if by ATS or Website.
	 * 
	 * @param jobId
	 * @param jobApplyTypeDTO
	 * @return
	 */
	public boolean validateApplyType(int jobId, JSONObject jsonObject,
			JobApplyTypeDTO jobApplyTypeDTO) {
		// apply job by apply type like by ATS, Website or Email
		boolean status = true;

		if (jobApplyTypeDTO != null
				&& (jobApplyTypeDTO.getApplyMethod().equalsIgnoreCase(
						MMJBCommonConstants.APPLY_TO_ATS) || jobApplyTypeDTO
						.getApplyMethod().equalsIgnoreCase(
								MMJBCommonConstants.APPLY_TO_URL))) {
			status = false;
			jsonObject.put("applyMethod", jobApplyTypeDTO.getApplyMethod());
			boolean httpsStatus = false;
			String finalUrl = jobApplyTypeDTO.getApplyLink();
			if (jobApplyTypeDTO.getApplyLink().startsWith("https://")) {
				httpsStatus = true;

			} else if (jobApplyTypeDTO.getApplyLink().startsWith("http://")) {
				httpsStatus = true;
			}
			if (!httpsStatus) {
				finalUrl = "http://" + jobApplyTypeDTO.getApplyLink();
			}
			jsonObject.put("applyLink", finalUrl);
		}
		return status;
	}

	/**
	 * Check for Login and navigate to login page if not logged in.
	 * 
	 * @param map
	 * @param jobId
	 * @param currentUrl
	 * @param session
	 * @param jsonObject
	 * @param request
	 */
	public boolean isLoggedIn(int jobId, String jobTitle, String currentUrl,
			HttpSession session, HttpServletRequest request) {
		boolean status = true;
		// Check for job seeker login
		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			session.setAttribute("jobId", jobId);
			session.setAttribute("jobTitle", jobTitle.replaceAll(
					MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN, ""));
			session.setAttribute(CURRENT_URL, currentUrl);
			status = false;
		}
		return status;
	}

	/**
	 * Method called to validate the search criteria
	 * 
	 * @param jobSearchResultForm
	 * @param session
	 * @param jsonObject
	 */
	public JSONObject validateJobSearch(
			JobSearchResultForm jobSearchResultForm, HttpSession session) {

		JSONObject jsonObject = null;
		// if (session.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP) != null)
		// {
		// String searchtype =((HashMap<String, String>) session
		// .getAttribute(SearchParamDTO.SEARCH_SESSION_MAP))
		// .get(MMJBCommonConstants.SEARCH_TYPE);

		String searchName = jobSearchResultForm.getSearchName();
		String cityState = jobSearchResultForm.getCityState();
//		String[] tokens = cityState.split(MMJBCommonConstants.COMMA);
		if (searchName.equalsIgnoreCase(MMJBCommonConstants.KEYWORD_SEARCH)) {
			if (StringUtils.isEmpty(jobSearchResultForm.getKeywords().trim())) {
				jsonObject = new JSONObject();
				jsonObject.put(ajaxMsg, jbSearchValKeyword);
			} else if ((!jobSearchResultForm.getRadius().equalsIgnoreCase(
					MMJBCommonConstants.ZERO))
					&& StringUtils.isBlank(jobSearchResultForm.getCityState()
							.trim())) {
				jsonObject = new JSONObject();
				jsonObject.put(ajaxMsg, jbSearchValCity);
			}
		} else if (searchName
				.equalsIgnoreCase(MMJBCommonConstants.LOCATION_SEARCH)) {
			
			if (StringUtils.isEmpty(jobSearchResultForm.getKeywords().trim())) {
				jsonObject = new JSONObject();
				jsonObject.put(ajaxMsg, jbSearchValKeyword);
			} else if ((!jobSearchResultForm.getRadius().equalsIgnoreCase(
					MMJBCommonConstants.ZERO))
					&& StringUtils.isBlank(jobSearchResultForm.getCityState()
							.trim())) {
				jsonObject = new JSONObject();
				jsonObject.put(ajaxMsg, jbSearchValCity);
			} else {
				LOGGER.debug("Validating the city and state or Zip code field");
				
				// Get the keyword entered
				String keyword = jobSearchResultForm.getCityState();
				keyword = keyword.replace(" ", "");
				// Get the autoCompleteList
				if (!MMUtils.isAlphaNumeric(keyword)) {
				String[] data = keyword.split(",");
				if(!(data.length > 1)){
					LOGGER.debug("Validating for city and state : state not added");
					jsonObject = new JSONObject();
					jsonObject.put(ajaxMsg, jbSearhValCityState);
					return jsonObject;
				}
				}
				List<LocationDTO> locationDTOList = lookupService
						.cityStateSearch(keyword);
				List<String> autoCompleteList = null;
				if (locationDTOList != null) {
					/*
					 * Returning the List<String> based on Post code search or CityState
					 * search
					 */
					if (MMUtils.isAlphaNumeric(keyword)) {
						LOGGER.debug("Validating for city and state");
						autoCompleteList = MMUtils.convertToPostcodeStringList(locationDTOList);
					} else {
						LOGGER.debug("Validating for Zip code field");
						autoCompleteList = MMUtils.convertToCityStateStringList(locationDTOList);
					}
				}
				
//				if(autoCompleteList.contains(keyword))
				// Compare the given value autocomplete
				boolean isValueFound = false;
				String origanlCityStateVal = null; 
				for (String value : autoCompleteList) {
					if(value.replace(" ", "").toUpperCase().equalsIgnoreCase(keyword)){
						LOGGER.debug("The city and state or Zip code field found :"+keyword);
						isValueFound = true;
						origanlCityStateVal = value;
						break;
					}
				}
				if(!isValueFound){
					jsonObject = new JSONObject();
					jsonObject.put(ajaxMsg, jbSearchValCity);
				}else{
					session.setAttribute("origanlCityStateVal", origanlCityStateVal);
				}
			}
		}
		return jsonObject;
	}

	/**
	 * validate the Email pattern 
	 * 
	 * @param emailAddress
	 *            emailAddress.
	 * @return true for valid
	 * 		   false for invalid mail.
	 */

	public boolean validateEmailPattern(String emailAddress) {
		Pattern pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}
}
