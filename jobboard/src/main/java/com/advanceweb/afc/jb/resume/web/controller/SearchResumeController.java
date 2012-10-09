package com.advanceweb.afc.jb.resume.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.CheckSessionMap;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.service.JSONConverterService;
import com.advanceweb.afc.jb.search.service.ResumeSearchService;

/**
 * This controller belongs to the functionalities related to searching of
 * Resume.
 * 
 * @author Reetesh RN
 * @version 1.0
 * @since 4th October 2012
 * 
 */

@Controller
@RequestMapping("/employerSearchResume")
public class SearchResumeController {

	private static final Logger LOGGER = Logger
			.getLogger(SearchResumeController.class);

	@Autowired
	private CheckSessionMap checkSessionMap;

	@Autowired
	private JSONConverterService jsonConverterService;

	@Autowired
	private ResumeSearchService resumeSearchService;

	@Autowired
	private LookupService lookupService;

	/**
	 * This method will be used for doing resume search and Return a JSON Object
	 * which will later be parsed at the UI end and all the results will be
	 * displayed
	 * 
	 * @param searchResumeForm
	 * @param result
	 * @param model
	 * @return JSON Object
	 * 
	 */
	@RequestMapping(value = "/searchResume", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject searchResume(HttpSession session,
			SearchResumeForm searchResumeForm, BindingResult result,
			HttpServletRequest request) {

		LOGGER.info("Calling Search Resume Controller!!!");

		JSONObject jsonObject = new JSONObject();
		// removeSession(session);
		ResumeSearchResultDTO resumeSearchResultDTO = null;
		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);
		String searchName = MMJBCommonConstants.EMPTY;
		// IMP:::This value should be taken from the UI while sorting
		String sortByParam = MMJBCommonConstants.POSTED_DT;
		String next = request.getParameter(MMJBCommonConstants.NEXT);
		String firstFQParam = "";
		String secondFQParam = "";
		int page = 1;
		int displayRecordsPerPage = 0;
		int recordsPerPage = 0;
		int noOfRecords = 0;
		int noOfPages = 0;
		int beginVal = 0;
		int start = 0;
		int rows = 0;

		// Validating the parameters of Resume search
		if (!validateResumeSearch(searchResumeForm, jsonObject)) {
			return jsonObject;
		}

		// Check if city state and radius field is not empty to check for
		// LOCATION search
		if (StringUtils.isEmpty(searchResumeForm.getCityState().trim())) {
			if (!StringUtils.isEmpty(searchResumeForm.getKeywords().trim())) {
				searchName = MMJBCommonConstants.RESUME_KEYWORD_SEARCH;
			}
		} else { // KEYWORD search
			searchName = MMJBCommonConstants.RESUME_LOCATION_SEARCH;
		}
		// int searchSeq = MMJBCommonConstants.ZERO_INT;
		String sessionId = null;
		if (session != null) {
			sessionId = session.getId();
			// Setting the values into sessionMap
			sessionMap = setValuesToSessionMap(sessionMap, searchResumeForm);
		}

		// Putting all the parameters coming from the UI into a Map for further
		// processing.
		Map<String, String> paramMap = getParameterMap(searchResumeForm,
				sessionId, searchName, sortByParam, firstFQParam, secondFQParam);

		try {
			if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
				page = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.PAGE));
			}
			if (request.getParameter(MMJBCommonConstants.RECORDS_PER_PAGE) != null) {
				displayRecordsPerPage = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.RECORDS_PER_PAGE));
			}

			if (0 == displayRecordsPerPage) {
				displayRecordsPerPage = MMJBCommonConstants.DEFAULT_PAGE_SIZE;
			}
			recordsPerPage = displayRecordsPerPage;
			start = (page - 1) * recordsPerPage;
			rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			resumeSearchResultDTO = resumeSearchService.resumeSearch(
					searchName, paramMap, start, rows);

			session.setAttribute(MMJBCommonConstants.START_ROW, ((page - 1)
					* recordsPerPage + 1));
			session.setAttribute(MMJBCommonConstants.END_ROW,
					(((page - 1) * recordsPerPage) + resumeSearchResultDTO
							.getResultList().size()));

			noOfRecords = (int) resumeSearchResultDTO.getResultCount();

		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Resume Search Result from SOLR...");
		}

		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		if (null != next && !next.isEmpty()) {
			beginVal = Integer.parseInt(next);
			page = Integer.parseInt(next);
		} else {
			beginVal = (page / 10) * 10;
		}
		JSONObject jobSrchJsonObj = null;
		if (resumeSearchResultDTO != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = jsonConverterService
					.convertToJSONForResume(resumeSearchResultDTO);
		}
		sessionMap = setSessionForGrid(sessionMap, page, noOfPages, beginVal,
				jobSrchJsonObj);
		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));

		if (session != null) {
			// Setting the sessionMap into the session
			session.setAttribute(MMJBCommonConstants.RESUME_SEARCH_SESSION_MAP,
					sessionMap);
		}

		return jobSrchJsonObj;
	}

	/**
	 * This method is used to validate the fields of resume search.
	 * 
	 * @param jobSearchResultForm
	 * @param jsonObject
	 */
	public boolean validateResumeSearch(SearchResumeForm searchResumeForm,
			JSONObject jsonObject) {
		boolean status = true;
		if (StringUtils.isEmpty(searchResumeForm.getKeywords().trim())) {
			// jsonObject.put(ajaxMsg, jobSearchValidateKeyword);
			status = false;
		} else if ((!searchResumeForm.getRadius().equalsIgnoreCase("0"))
				&& StringUtils.isEmpty(searchResumeForm.getCityState().trim())) {
			// jsonObject.put(ajaxMsg, jobSearchValidateCity);
			status = false;
		}
		return status;
	}

	/**
	 * This method is used to set values into the session map.
	 * 
	 * @param sessionMap
	 * @param searchSeq
	 * @param jobSearchResultForm
	 * @return Map<String, String>
	 */

	private Map<String, String> setValuesToSessionMap(
			Map<String, String> sessionMap, SearchResumeForm searchResumeForm) {

		sessionMap.put(SearchParamDTO.KEYWORDS, searchResumeForm.getKeywords()
				.trim());
		sessionMap.put(SearchParamDTO.CITY_STATE, searchResumeForm
				.getCityState().trim());
		sessionMap.put(SearchParamDTO.RADIUS, searchResumeForm.getRadius()
				.trim());
		sessionMap.put(SearchParamDTO.PHRASE, searchResumeForm.getPhrase()
				.trim());
		sessionMap.put(MMJBCommonConstants.SEARCH_TYPE, searchResumeForm
				.getSearchtype().trim());
		return sessionMap;

	}

	/**
	 * This method is used to create parameter map and populate the required
	 * values into it.
	 * 
	 * @param searchResumeForm
	 * @param sessionId
	 * @param searchName
	 * @param sessionMap
	 * @return Map<String, String>
	 */

	private Map<String, String> getParameterMap(
			SearchResumeForm searchResumeForm, String sessionId,
			String searchName, String sortByParam, String firstFQParam,
			String secondFQParam) {

		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put(SearchParamDTO.KEYWORDS, searchResumeForm.getKeywords()
				.trim());

		paramMap.put(SearchParamDTO.CITY_STATE, searchResumeForm.getCityState()
				.trim());
		paramMap.put(SearchParamDTO.RADIUS, searchResumeForm.getRadius().trim());
		paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
		/*
		 * paramMap.put(SearchParamDTO.SEARCH_SEQ,
		 * sessionMap.get(SearchParamDTO.SEARCH_SEQ));
		 */
		paramMap.put(SearchParamDTO.SEARCH_NAME, searchName.trim());

		// For testing. Remove it while committing
		paramMap.put(MMJBCommonConstants.SORT_PARAM, sortByParam);
		paramMap.put(MMJBCommonConstants.FIRST_FQ_PARAM, firstFQParam);
		paramMap.put(MMJBCommonConstants.SECOND_FQ_PARAM, secondFQParam);
		/*
		 * paramMap.put(MMJBCommonConstants.THIRD_FQ_PARAM, thirdFQParam);
		 * paramMap.put(MMJBCommonConstants.FOURTH_FQ_PARAM, fouthFQParam);
		 */

		return paramMap;

	}

	/**
	 * This method is used to set the parameters into the session.
	 * 
	 * @param session
	 * @param page
	 * @param noOfPages
	 * @param beginVal
	 * @param jobSrchJsonObj
	 * @return Map<String, String>
	 */
	private Map<String, String> setSessionForGrid(
			Map<String, String> sessionMap, int page, int noOfPages,
			int beginVal, JSONObject jobSrchJsonObj) {
		sessionMap.put(MMJBCommonConstants.SEARCH_RESULTS_LIST, jobSrchJsonObj
				.get(MMJBCommonConstants.JSON_ROWS).toString());
		sessionMap.put(MMJBCommonConstants.RECORDS_COUNT,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS)
						.toString());
		sessionMap.put(MMJBCommonConstants.BEGIN_VAL, String.valueOf(beginVal));
		sessionMap.put(MMJBCommonConstants.NO_OF_PAGES,
				String.valueOf(noOfPages));
		sessionMap.put(MMJBCommonConstants.CURRENT_PAGE, String.valueOf(page));
		sessionMap.put(MMJBCommonConstants.BEGIN,
				String.valueOf((beginVal <= 0 ? 1 : beginVal)));
		return sessionMap;
	}

	/**
	 * This method will be used for Autocomplete for city, state or Postcode and
	 * Return List<String>.
	 * 
	 * @param String
	 *            keyword
	 * @return List<String> Object
	 */

	@RequestMapping(value = "/findLocation", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	List<String> findLocation(@RequestParam("term") String keyword) {

		List<LocationDTO> locationDTOList = lookupService
				.locationSearch(keyword.trim());

		if (locationDTOList != null) {
			/*
			 * Returning the List<String> based on Post code search or CityState
			 * search
			 */

			if (MMUtils.isIntNumber(keyword)) {
				return MMUtils.convertToPostcodeStringList(locationDTOList);
			} else {
				return MMUtils.convertToCityStateStringList(locationDTOList);
			}
		}

		return null;
	}

	/**
	 * This method is called to forward to Advance job search page
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/advanceresumesearch", method = RequestMethod.GET)
	public ModelAndView advanceResumeSearch(HttpSession session, HttpServletRequest request,
			Map<String, SearchResumeForm> model) {
		
		SearchResumeForm searchResumeForm = new SearchResumeForm();
		model.put("searchResumeForm", searchResumeForm);
		//removeSession(session);
		return new ModelAndView("advanceresumesearch");

	}

}
