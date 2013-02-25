/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.ResumePackageService;
import com.advanceweb.afc.jb.employer.web.controller.MetricsForm;
import com.advanceweb.afc.jb.event.service.ClickService;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.CheckSessionMap;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.ResumeSearchValidator;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.service.ResumeSearchService;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

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
public class SearchResumeController extends AbstractController {

	/** The Constant CREATE_RESUME_SEARCH. */
	private static final String CREATE_RESUME_SEARCH = "createResumeSearch";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(SearchResumeController.class);

	/** The check session map. */
	@Autowired
	private CheckSessionMap checkSessionMap;

	/** The resume search service. */
	@Autowired
	private ResumeSearchService resumeSearchService;

	/** The lookup service. */
	@Autowired
	private LookupService lookupService;

	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/** The saved searchs limit. */
	@Value("${savedSearchsLimit}")
	private String savedSearchsLimit;
	
	/** The move resume to folder msg. */
	@Value("${moveResumeToFolderMsg}")
	private String moveResumeToFolderMsg;

	/** The navigation path. */
	private String navigationPath;

	/** The save this search err msg. */
	@Value("${saveThisSearchErrMsg}")
	private String saveThisSearchErrMsg;

	/** The resume service. */
	@Autowired
	private ResumeService resumeService;

	/** The click service. */
	@Autowired
	private ClickService clickService;

	/** The trans create resume. */
	@Autowired
	private TransformCreateResume transCreateResume;

	/** The ad service. */
	@Autowired
	private AdService adService;
	
	/** The resume search validator. */
	@Autowired
	private ResumeSearchValidator resumeSearchValidator;
	
	/** The resume package service. */
	@Autowired
	private ResumePackageService resumePackageService;

	/** The Constant STR_SRCH_RES_FORM. */
	private static final String STR_SRCH_RES_FORM = "searchResumeForm";

	/**NOT IN USE
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

		LOGGER.debug("Calling Search Resume Controller!!!");

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
			if (request.getParameter(MMJBCommonConstants.JOBSEACRH_PAGE_SIZE) != null) {
				displayRecordsPerPage = Integer.parseInt(request
						.getParameter(MMJBCommonConstants.JOBSEACRH_PAGE_SIZE));
			}

			if (0 == displayRecordsPerPage) {
				displayRecordsPerPage = MMJBCommonConstants.JOBSEACRH_DEFAULT_PAGE_SIZE;
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
			jobSrchJsonObj = searchResumeToJSON(resumeSearchResultDTO);
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
				&& StringUtils.isBlank(searchResumeForm.getCityState().trim())) {
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
		sessionMap.put(MMJBCommonConstants.SEARCHED_JOBSCOUNT,
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
	public ModelAndView advanceResumeSearch(HttpSession session,
			HttpServletRequest request, Map<String, SearchResumeForm> model) {

		SearchResumeForm searchResumeForm = new SearchResumeForm();
		ModelAndView modelView = new ModelAndView();
		model.put(STR_SRCH_RES_FORM, searchResumeForm);
		// removeSession(session);

		populateAds(session, request, modelView, PageNames.EMP_ADV_RESUME_SEARCH);

		modelView.setViewName("advanceresumesearch");
		return modelView;

	}

	/**
	 * This method displays the ads
	 * 
	 * @param session
	 * @param request
	 * @param pageName 
	 * @param model
	 */
	private void populateAds(HttpSession session, HttpServletRequest request,
			ModelAndView modelView, String pageName) {
		// Add the Ads
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, pageName);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			modelView.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			modelView.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
			
			if (pageName.equalsIgnoreCase(PageNames.EMP_ADV_RESUME_SEARCH)) {
				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_TOP;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				modelView.addObject(MMJBCommonConstants.ADPGRIGHT_TOP,
						bannerString);

				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_MIDDLE;
				bannerString = adService.getBanner(clientContext, size,
						position).getTag();
				modelView.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE,
						bannerString);
			}
			
		} catch (Exception e) {
			LOGGER.error(
					"Error occurred while getting the html content for Ads", e);
		}
	}

	/**
	 * My saved resume searches.
	 *
	 * @param searchResumeForm the search resume form
	 * @param result the result
	 * @param session the session
	 * @return the model and view
	 */
	@RequestMapping(value = "/mySavedResumeSearches", method = RequestMethod.GET)
	public ModelAndView mySavedResumeSearches(
			@ModelAttribute(STR_SRCH_RES_FORM) SearchResumeForm searchResumeForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		searchResumeForm.setUserID(userId);
		if (searchResumeForm.getUserID() != 0) {
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = resumeSearchService
					.mySavedResumeSearches(searchResumeForm.getUserID());

			List<DropDownDTO> notifyMeList = populateDropdownsService
					.populateDropdown("NotifyMe");
			searchResumeForm
					.setSaveSearchedJobsDTOList(saveSearchedJobsDTOList);
			model.addObject("notifyMeList", notifyMeList);
			model.addObject("saveSearchedJobsDTOList", saveSearchedJobsDTOList);
		}
		model.addObject(searchResumeForm);
		model.setViewName("empSavedResumeSearchPopup");
		return model;
	}

	/**
	 * This method is used to search resumes directly from the DB. This is a
	 * temporary implementation since SOLR is not yet configured.
	 * 
	 * @param session
	 * @param searchResumeForm
	 * @param result
	 * @param request
	 * @return JSONObject
	 */

	@RequestMapping(value = "/searchResumeFromDB", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject searchResumeFromDB(HttpSession session,
			SearchResumeForm searchResumeForm, BindingResult result,
			HttpServletRequest request) {
		LOGGER.debug("Calling Search Resume Controller!!!");
		// session.removeAttribute("resumeDTOList");
		removeSession(session);
		session.removeAttribute("jobSrchJsonObj");
		session.removeAttribute(MMJBCommonConstants.KEYWORD_STRING);
		// session.removeAttribute(MMJBCommonConstants.AUTOLOAD);
		JSONObject jobSrchJsonObj = null;
		
		jobSrchJsonObj = resumeSearchValidator.validateResumeSearch(searchResumeForm);
		if (jobSrchJsonObj != null) {
			return jobSrchJsonObj;
		}
		
		Map<String, String> sessionMap = checkSessionMap
				.getResumeSearchSessionMap(session);
		if (session != null) {
			// Setting the values into sessionMap
			sessionMap = setValuesToSessionMap(sessionMap, searchResumeForm);
		}
		
		List<ResumeDTO> resumeDTOList = null;
		
		int page = 1;
		int displayRecordsPerPage = 0;
		int recordsPerPage = 0;
		int noOfRecords = 0;
		String next = request.getParameter(MMJBCommonConstants.NEXT);

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
			int start = (page - 1) * recordsPerPage;
			int rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
//			jobSearchResultDTO = jobSearchService.jobSearch(searchName,
//					paramMap, start, rows);
			resumeDTOList = resumeSearchService
					.resumeSearchFromDB(searchResumeForm.getKeywords(), start, rows);

			session.setAttribute(MMJBCommonConstants.START_ROW, ((page - 1)
					* recordsPerPage + 1));
			session.setAttribute(MMJBCommonConstants.END_ROW,
					(((page - 1) * recordsPerPage) + resumeDTOList
							.size()));

			noOfRecords = (int) resumeSearchService.getTotalNumberOfResume();

			// TODO: Advertise part is not done
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		int beginVal = 0;
		if (null == next || next.isEmpty()) {
			beginVal = (page / 10) * 10;
		} else {
			beginVal = Integer.parseInt(next);
			page = Integer.parseInt(next);
		}
		if (resumeDTOList != null) {
			// Save the list of resumes which appeared in the search
			clickService.saveResAppearance(resumeDTOList);
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = searchResumeToJSONFromDB(resumeDTOList);
		}
		List<HashMap<String, Object>> currentSearchList = null;
		setSessionForGrid(session, page, noOfPages, beginVal, jobSrchJsonObj,
				currentSearchList);
		// Calling the jobSearch() of Service layer for getting the resume list
		// add values in session
		jobSrchJsonObj.put(MMJBCommonConstants.KEYWORD_STRING, searchResumeForm.getKeywords());
		jobSrchJsonObj.put(MMJBCommonConstants.RESUME_RECORDS_COUNT, resumeSearchService.getTotalNumberOfResume());
		
		// This is to check if Resume Package is ACTIVE for the logged in user.
		if (session.getAttribute(MMJBCommonConstants.FACILITY_ID) != null) {
			int facilityId = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
			
			System.out.println("Valu=================="+resumePackageService.isResumePackageActive(facilityId));
			
			//jobSrchJsonObj.put(MMJBCommonConstants.IS_RESUME_PACKAGE_ACTIVE, resumePackageService.isResumePackageActive(userID));
			session.setAttribute(MMJBCommonConstants.IS_RESUME_PACKAGE_ACTIVE, resumePackageService.isResumePackageActive(facilityId));
		}
		
		
		session.setAttribute(MMJBCommonConstants.RESUME_SEARCH_JSON_LIST, jobSrchJsonObj);
//		session.setAttribute(MMJBCommonConstants.KEYWORD_STRING,
//				searchResumeForm.getKeywords());
		sessionMap.put(SearchParamDTO.KEYWORDS,
				searchResumeForm.getKeywords());
		sessionMap.put(SearchParamDTO.RADIUS,
				searchResumeForm.getRadius());
		// session.setAttribute(MMJBCommonConstants.AUTOLOAD,
		// String.valueOf(true));
		
		if (session != null) {
			// Setting the sessionMap into the session
			session.setAttribute(MMJBCommonConstants.RESUME_SEARCH_SESSION_MAP,
					sessionMap);
		}
		
		return jobSrchJsonObj;
	}
	
	/**
	 * removing session for search results grid
	 * 
	 * @param session
	 */
	private void removeSession(HttpSession session) {
		// TODO :Need to Use sessionMap
		LOGGER.debug("Removing from session....");
		session.removeAttribute(MMJBCommonConstants.RESUME_SEARCH_JSON_LIST);
		session.removeAttribute(MMJBCommonConstants.CITY);
		session.removeAttribute(MMJBCommonConstants.STATE);
		session.removeAttribute(MMJBCommonConstants.COMPANY);
		session.removeAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST);
		session.removeAttribute(MMJBCommonConstants.NO_OF_PAGES);
		session.removeAttribute(MMJBCommonConstants.CURRENT_PAGE);
		session.removeAttribute(MMJBCommonConstants.RECORDS_PER_PAGE);
		session.removeAttribute(MMJBCommonConstants.RECORDS_COUNT);
		session.removeAttribute(MMJBCommonConstants.RESUME_RECORDS_COUNT);
		session.removeAttribute(MMJBCommonConstants.TOTAL_NO_RECORDS);
		session.removeAttribute(MMJBCommonConstants.START_ROW);
		session.removeAttribute(MMJBCommonConstants.END_ROW);
		session.removeAttribute(MMJBCommonConstants.BEGIN_VAL);
		session.removeAttribute(MMJBCommonConstants.BEGIN);
		session.removeAttribute(MMJBCommonConstants.DISPLAY_RADIUS);

		// Added for Browse By job title, By Employer And By Location task
		session.removeAttribute("jobTitlePage");
		session.removeAttribute("employerPage");
		session.removeAttribute("locationPage");
//		session.removeAttribute(MMJBCommonConstants.BROWSE_BY_TITLE);
		session.removeAttribute("list");
		// session.removeAttribute("locationPage");
		session.removeAttribute("areaPage");

		// Remove FQ params for non Refine Search
		if (null == session.getAttribute(MMJBCommonConstants.REFINED)
				|| session.getAttribute(MMJBCommonConstants.REFINED).toString()
						.isEmpty()
				|| !Boolean.valueOf(session.getAttribute(
						MMJBCommonConstants.REFINED).toString())) {
			session.removeAttribute(MMJBCommonConstants.SECOND_FQ_PARAM);
			session.removeAttribute(MMJBCommonConstants.THIRD_FQ_PARAM);
			session.removeAttribute(MMJBCommonConstants.FOURTH_FQ_PARAM);
			session.removeAttribute(MMJBCommonConstants.REFINERADIUS);
		}
		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);
		if (sessionMap.get(SearchParamDTO.KEYWORDS) != null) {
			session.setAttribute(MMJBCommonConstants.PREV_JOB_SEARCH_KEYWORDS,
					sessionMap.get(SearchParamDTO.KEYWORDS));
		}
	}
	
	/**
	 * This method is used to setting the required values into the session for
	 * displaying the results in the grid.
	 * 
	 * @param session
	 * @param page
	 * @param noOfPages
	 * @param beginVal
	 * @param jobSrchJsonObj
	 * @param currentSearchList
	 */
	private void setSessionForGrid(HttpSession session, int page,
			int noOfPages, int beginVal, JSONObject jobSrchJsonObj,
			List<HashMap<String, Object>> currentSearchList) {
		// TODO: Need to use session Map
		session.setAttribute(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		session.setAttribute(MMJBCommonConstants.CITY,
				jobSrchJsonObj.get(MMJBCommonConstants.CITY));
		session.setAttribute(MMJBCommonConstants.STATE,
				jobSrchJsonObj.get(MMJBCommonConstants.STATE));
		session.setAttribute(MMJBCommonConstants.COMPANY,
				jobSrchJsonObj.get(MMJBCommonConstants.COMPANY));
		session.setAttribute(MMJBCommonConstants.CURRENT_SEARCH_LIST,
				currentSearchList);
		session.setAttribute(MMJBCommonConstants.RESUME_RECORDS_COUNT,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		session.setAttribute(MMJBCommonConstants.BEGIN_VAL, beginVal);
		session.setAttribute(MMJBCommonConstants.NO_OF_PAGES, noOfPages);
		session.setAttribute(MMJBCommonConstants.CURRENT_PAGE, page);
		session.setAttribute(MMJBCommonConstants.BEGIN, (beginVal <= 0 ? 1
				: beginVal));
		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
	}

	/**
	 * Get the jobboardsearchresumeresultbody page
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jobboardsearchresumeresultbody")
	public ModelAndView getjobboardsearchresultsBody(
			HttpServletResponse response, HttpServletRequest request,
			Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jobboardsearchresumeresultbody");
		return modelAndView;
	}

	/**
	 * This method is called to edit a Saved Job Search
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/editSavedResumeSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject editSavedResumeSearch(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("searchId") int searchId,
			@RequestParam("performSearch") String performSearch) {
		JSONObject jsonObject = new JSONObject();
		Map<String, String> sessionMap = checkSessionMap
				.getResumeSearchSessionMap(session);
		
		if (sessionMap.get(MMJBCommonConstants.PERFORM_SAVED_SEARCH) != null
				&& sessionMap.get(MMJBCommonConstants.PERFORM_SAVED_SEARCH)
						.equalsIgnoreCase(
								MMJBCommonConstants.PERFORM_SAVED_SEARCH)) {
			session.removeAttribute(sessionMap
					.remove(MMJBCommonConstants.PERFORM_SAVED_SEARCH));
		}

		if (performSearch != null
				&& performSearch
						.equalsIgnoreCase(MMJBCommonConstants.PERFORM_SAVED_SEARCH)) {
			sessionMap.put(MMJBCommonConstants.PERFORM_SAVED_SEARCH,
					performSearch);
		}

		List<SaveSearchedJobsDTO> saveSrchJobsDTOList = resumeSearchService
				.editSavedResumeSearch(searchId);

		if (!saveSrchJobsDTOList.isEmpty()) {
			String urlString = saveSrchJobsDTOList.get(0).getUrl();
			String saveSearchName = saveSrchJobsDTOList.get(0).getSearchName();
			Map<String, String> urlMap = MMUtils.getUrlMap(urlString);

			sessionMap.put(MMJBCommonConstants.SEARCH_TYPE,
					urlMap.get(MMJBCommonConstants.SEARCH_TYPE));
			sessionMap
					.put(MMJBCommonConstants.SAVE_SEARCH_NAME, saveSearchName);
			sessionMap.put(SearchParamDTO.KEYWORDS,
					urlMap.get(SearchParamDTO.KEYWORDS));
			sessionMap.put(SearchParamDTO.CITY_STATE,
					urlMap.get(SearchParamDTO.CITY_STATE));
			sessionMap.put(SearchParamDTO.RADIUS,
					urlMap.get(SearchParamDTO.RADIUS));
			sessionMap.put(MMJBCommonConstants.AUTOLOAD, String.valueOf(true));
			sessionMap.put(MMJBCommonConstants.SAVE_SEARCH_ID,
					String.valueOf(searchId));

			session.setAttribute(SearchParamDTO.RESUME_SEARCH_SESSION_MAP,
					sessionMap);

			jsonObject.put(MMJBCommonConstants.SEARCH_TYPE,
					urlMap.get(MMJBCommonConstants.SEARCH_TYPE));
			jsonObject.put(SearchParamDTO.KEYWORDS,
					urlMap.get(SearchParamDTO.KEYWORDS));
			jsonObject.put(SearchParamDTO.CITY_STATE,
					urlMap.get(SearchParamDTO.CITY_STATE));
			jsonObject.put(SearchParamDTO.RADIUS,
					urlMap.get(SearchParamDTO.RADIUS));
			jsonObject.put(MMJBCommonConstants.AUTOLOAD, true);
			return jsonObject;
		} else {
			jsonObject.put("failed", "Failed to Edit this record");
			return jsonObject;
		}
	}

	/**
	 * This method is called to forward to job search page
	 * 
	 * @param model
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/findResumePage", method = RequestMethod.GET)
	public ModelAndView findResumePage(Map<String, SearchResumeForm> model,
			HttpSession session, HttpServletRequest request,@RequestParam(value = "link", required = false ) String link) {
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		SearchResumeForm searchResumeForm = new SearchResumeForm();
		ModelAndView modelAndView = new ModelAndView();
		MetricsForm employerDashBoardForm = new MetricsForm();
		int resumeSearchCount = 0;
		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = resumeSearchService
				.viewMySavedSearches(userId);
		resumeSearchCount = saveSearchedJobsDTOList.size();
		employerDashBoardForm.setResumeSearchCount(resumeSearchCount);
		if(CREATE_RESUME_SEARCH.equals(link)){
			session.removeAttribute(MMJBCommonConstants.RESUME_SEARCH_SESSION_MAP);
		}
		Map<String, String> sessionMap = checkSessionMap
				.getResumeSearchSessionMap(session);
		
		if (!sessionMap.isEmpty()) {
			String searchType = sessionMap.get(MMJBCommonConstants.SEARCH_TYPE);
			String radius = MMJBCommonConstants.EMPTY;
			String cityState = MMJBCommonConstants.EMPTY;
			String keywords = MMJBCommonConstants.EMPTY;
			String saveSearchName = MMJBCommonConstants.EMPTY;
			keywords = sessionMap.get(SearchParamDTO.KEYWORDS);
			cityState = sessionMap.get(SearchParamDTO.CITY_STATE);
			radius = sessionMap.get(SearchParamDTO.RADIUS);
			saveSearchName = sessionMap
					.get(MMJBCommonConstants.SAVE_SEARCH_NAME);
			searchResumeForm.setSaveSearchName(saveSearchName);
			searchResumeForm.setSearchtype(searchType);
			searchResumeForm.setKeywords(keywords);
			searchResumeForm.setCityState(cityState);
			searchResumeForm.setRadius(radius);
			searchResumeForm.setAutoload(true);
			LOGGER.debug("Removing keywords, city,state, autoload from session....");
			session.removeAttribute(sessionMap.remove(SearchParamDTO.KEYWORDS));
			session.removeAttribute(sessionMap
					.remove(SearchParamDTO.CITY_STATE));
			session.removeAttribute(sessionMap.remove(SearchParamDTO.RADIUS));
			session.removeAttribute(sessionMap
					.remove(MMJBCommonConstants.AUTOLOAD));
		}
		modelAndView.addObject(STR_SRCH_RES_FORM, searchResumeForm);
		modelAndView.addObject("employerDashBoardForm", employerDashBoardForm);
		modelAndView.setViewName("employerDashboard");
		// get the Ads
		populateAds(session, request, modelAndView, PageNames.EMPLOYER_DASHBOARD);
		return modelAndView;
	}

	/**
	 * ` This method is called to delete a Saved Job Search
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/deleteSavedResume", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject deleteSavedResume(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("saveSearchId") int saveSearchId) {

		boolean deleteStatus = resumeSearchService
				.deleteSavedResume(saveSearchId);
		JSONObject deleteStatusJson = new JSONObject();
		if (deleteStatus) {
			deleteStatusJson.put("success", "Record Deleted Successfully");
			return deleteStatusJson;
		} else {
			deleteStatusJson.put("failed", "Failed to Delete this record");
			return deleteStatusJson;
		}
	}

	/**
	 * This method is used to update the modified notify me value in the table
	 * adm_save_search
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param stringObjNew
	 * @return
	 */
	@RequestMapping(value = "/saveSearchedResumeNames", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveMyResumeSearches(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("stringObjNew") String stringObjNew) {

		// Splitting the string
		StringTokenizer stringNew = new StringTokenizer(stringObjNew, ";");
		List<SaveSearchedJobsDTO> searchedJobsDTOs = new ArrayList<SaveSearchedJobsDTO>();

		while (stringNew.hasMoreElements()) {
			SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();
			String stringObject = (String) stringNew.nextElement();
			StringTokenizer stringAlter = new StringTokenizer(stringObject, "=");
			int saveId = Integer.parseInt((String) stringAlter.nextElement());
			String notifyMe = stringAlter.nextElement().toString();
			searchedJobsDTO.setSaveSearchID(saveId);
			searchedJobsDTO.setEmailFrequency(notifyMe);
			searchedJobsDTOs.add(searchedJobsDTO);
		}
		// update the data in DB
		boolean saveData = resumeSearchService
				.saveModifiedData(searchedJobsDTOs);
		JSONObject saveStatusJson = new JSONObject();
		if (saveData) {
			saveStatusJson.put("success", "Data Updated Successfully");
		} else {
			saveStatusJson.put("failed", "Failed to update the data");
		}
		return saveStatusJson;
	}

	/**
	 * This method is used to save the searches in adm_save_search table
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/saveSearchedResumes", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveSearchedResumes(@Valid SearchResumeForm searchResumeForm,
			BindingResult result, Map<String, JobSearchResultForm> model,
			@RequestParam("searchName") String searchName, HttpSession session) {

		// Before user saves his search need to check save search
		// records are more than 5 searches.
		// if yes then delete the first saved search
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = resumeSearchService
				.viewMySavedSearches(userId);
		int savedSearchCount = saveSearchedJobsDTOList.size();
		if (savedSearchCount == Integer.parseInt(savedSearchsLimit)) {
			resumeSearchService.deleteFirstSearch(userId);
		}

		JSONObject jsonObject = new JSONObject();
		Map<String, String> sessionMap = checkSessionMap
				.getResumeSearchSessionMap(session);

		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			jsonObject.put("NavigationPath", navigationPath);
		} else {
			// int userId = (Integer) session
			// .getAttribute(MMJBCommonConstants.USER_ID);
			SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();

			if (StringUtils.isEmpty(searchName)) {
				jsonObject.put("EmptySearchName", "EmptySearchName");
			} else {

				boolean isSrchNameExist = resumeSearchService
						.validateSearchName(searchName, userId);

				if (isSrchNameExist) {
					jsonObject
							.put("DuplicateSearchName", "DuplicateSearchName");
				} else {
					searchedJobsDTO.setUserID(userId);
					searchedJobsDTO.setUrl(MMJBCommonConstants.SEARCH_TYPE
							+ MMJBCommonConstants.EQUAL_TO
							+ sessionMap.get(MMJBCommonConstants.SEARCH_TYPE)
							+ MMJBCommonConstants.SEMICOLON
							+ SearchParamDTO.KEYWORDS
							+ MMJBCommonConstants.EQUAL_TO
							+ sessionMap.get(SearchParamDTO.KEYWORDS)
							+ MMJBCommonConstants.SEMICOLON
							+ SearchParamDTO.CITY_STATE
							+ MMJBCommonConstants.EQUAL_TO
							+ sessionMap.get(SearchParamDTO.CITY_STATE)
							+ MMJBCommonConstants.SEMICOLON
							+ SearchParamDTO.RADIUS
							+ MMJBCommonConstants.EQUAL_TO
							+ sessionMap.get(SearchParamDTO.RADIUS));

					searchedJobsDTO.setSearchName(searchName);
					searchedJobsDTO.setCreatedDate(MMUtils
							.getCurrentDateAndTime());
					resumeSearchService.saveSearchedResumes(searchedJobsDTO);
					jsonObject.put("LoggedInNavigationPath", "");
				}
			}

		}
		return jsonObject;
	}

	/**
	 * This method is used to display the Save Search pop up.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/empSaveThisSearchPopup", method = RequestMethod.GET)
	public ModelAndView displaySaveThisSearchPopup(
			Map<String, SearchResumeForm> model) {
		model.put(STR_SRCH_RES_FORM, new SearchResumeForm());
		return new ModelAndView("empSaveThisSearchPopup");
	}

	/**
	 * This method is used to navigate the save this search pages to Login page
	 * or pop up page depending upon whether the user is a ananymous user or
	 * registered user.
	 * 
	 * @param searchResumeForm
	 * @param model
	 * @param session
	 * @return JSonObject
	 */

	@RequestMapping(value = "/saveThisResumeSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveThisResumeSearch(@Valid SearchResumeForm searchResumeForm,
			Map<String, SearchResumeForm> model, HttpSession session,
			@RequestParam("keywords") String keywords) {
		JSONObject jsonObject = new JSONObject();
		try {

			Map<String, String> sessionMap = checkSessionMap
					.getResumeSearchSessionMap(session);
			if ((sessionMap.get(MMJBCommonConstants.PERFORM_SAVED_SEARCH) == null)
					&& (sessionMap.get(MMJBCommonConstants.SEARCH_TYPE) != null
							&& sessionMap
									.get(MMJBCommonConstants.SEARCH_TYPE)
									.equals(MMJBCommonConstants.BASIC_SEARCH_TYPE_RESUME) && sessionMap
							.get(MMJBCommonConstants.SAVE_SEARCH_NAME) != null)) {

				SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();

				searchedJobsDTO.setSearchName(sessionMap
						.get(MMJBCommonConstants.SAVE_SEARCH_NAME));
				searchedJobsDTO.setSaveSearchID(Integer.parseInt(sessionMap
						.get(MMJBCommonConstants.SAVE_SEARCH_ID)));
				searchedJobsDTO.setUrl(MMJBCommonConstants.SEARCH_TYPE
						+ MMJBCommonConstants.EQUAL_TO
						+ sessionMap.get(MMJBCommonConstants.SEARCH_TYPE)
						+ MMJBCommonConstants.SEMICOLON
						+ SearchParamDTO.KEYWORDS
						+ MMJBCommonConstants.EQUAL_TO
						+ sessionMap.get(SearchParamDTO.KEYWORDS)
						+ MMJBCommonConstants.SEMICOLON
						+ SearchParamDTO.CITY_STATE
						+ MMJBCommonConstants.EQUAL_TO
						+ sessionMap.get(SearchParamDTO.CITY_STATE)
						+ MMJBCommonConstants.SEMICOLON + SearchParamDTO.RADIUS
						+ MMJBCommonConstants.EQUAL_TO
						+ sessionMap.get(SearchParamDTO.RADIUS));

				searchedJobsDTO.setUserID((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));

				resumeSearchService.updateSearchDetails(searchedJobsDTO);

				session.removeAttribute(sessionMap
						.remove(MMJBCommonConstants.SAVE_SEARCH_NAME));
				session.removeAttribute(sessionMap
						.remove(MMJBCommonConstants.SEARCH_TYPE));

				jsonObject.put("NavigationPath",
						"../employer/employerDashBoard");

			} else {
				if (keywords != null && keywords != MMJBCommonConstants.EMPTY) {
					model.put(STR_SRCH_RES_FORM, new SearchResumeForm());
					jsonObject.put("LoggedInNavigationPath",
							"../employerSearchResume/empSaveThisSearchPopup");
				} else {
					jsonObject.put("failure", saveThisSearchErrMsg);
				}

			}

		} catch (Exception e) {
			LOGGER.error("Save this search ERROR");
		}
		return jsonObject;
	}

	/**
	 * Get the jobboardsearchresumeresultbody page
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/moveResumeToFolder")
	public @ResponseBody
	List<String> moveResumeToFolder(HttpServletResponse response,
			HttpServletRequest request, HttpSession session,
			@RequestParam("resumeIdAndDateArr") String resumeIdAndDateArr) {
		List<String> idList = new ArrayList<String>();
		idList.add(moveResumeToFolderMsg);

		LOGGER.debug("Publish Resume ID and Created date list :"
				+ resumeIdAndDateArr);
		String[] resumeIdAndDateArray = resumeIdAndDateArr.split(",");

		List<String> publishResumeIdArrList = getPublishResumeArrayList(resumeIdAndDateArray);

		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		LOGGER.debug("User Id is :" + userId);
		boolean status = resumeService.moveResumesToFolder(
				publishResumeIdArrList, userId);
		if (status) {
			LOGGER.debug("Successfully Moved the Resumes to the Default Folder.");
		} else {
			LOGGER.error("Error occurred while moving the Resumes to the Default Folder.");
		}

		// modelAndView.setViewName("jobboardsearchresumeresultbody");
		// return modelAndView;

		return idList;
	}

	/**
	 * 
	 * @param publishResumeIdArr
	 */
	private List<String> getPublishResumeArrayList(String[] publishResumeIdArr) {
		List<String> publishResumeIDList = new ArrayList<String>();
		for (String publishId : publishResumeIdArr) {
			publishResumeIDList.add(publishId);
		}

		return publishResumeIDList;
	}

	/**
	 * Called to create resume it Contains 1.Contact information 2.Objective
	 * 3.Work Experience 4.Education 5.Certifiation 6.Skills 7.Awards
	 * 8.Memberships 9.Other Details 10.References
	 * 
	 * @param createResume
	 * @param result
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/viewResume", method = RequestMethod.GET)
	public ModelAndView viewResume(CreateResume createResumed,
			BindingResult result, @RequestParam("resumeId") int resumeId,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		/**
		 * Introduced a new variable "createResumed" to resolve PMD issue.
		 */
		CreateResume createResume = createResumed;
		ModelAndView model = new ModelAndView();
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);
		createResume = transCreateResume.transformCreateResumeForm(resumeDTO);
		List<CertificationsForm> listCertForm = transCreateResume
				.transformCertForm(resumeDTO.getListCertDTO());
		List<ReferenceForm> listRefForm = transCreateResume
				.transformReferenceForm(resumeDTO.getListRefDTO());
		List<EducationForm> listEduForm = transCreateResume
				.transformEducationForm(resumeDTO.getListEduDTO());
		List<WorkExpForm> listWorkExpForm = transCreateResume
				.transformWorkExpForm(resumeDTO.getListWorkExpDTO());
		List<LanguageForm> listLangForm = transCreateResume
				.transformLanguageForm(resumeDTO.getListLangDTO());
		ContactInfoForm contactForm = transCreateResume
				.transformContactInfoForm(resumeDTO.getContactInfoDTO());
		List<PhoneDetailForm> listPhoneDtl = transCreateResume
				.transformPhoneDetailDTOToForm(resumeDTO.getListPhoneDtl());
		createResume.setbHideBackButton(true);
		createResume.setListCertForm(listCertForm);
		createResume.setListEduForm(listEduForm);
		createResume.setListLangForm(listLangForm);
		createResume.setListRefForm(listRefForm);
		createResume.setListWorkExpForm(listWorkExpForm);
		createResume.setContactInfoForm(contactForm);
		createResume.setListPhoneDtlForm(listPhoneDtl);
		resumeDTO.getContactInfoDTO();
		session.setAttribute(MMJBCommonConstants.MODULE_STRING,
				MMJBCommonConstants.EMPLOYER);

		// Save the resume which was viewed by employer
		clickService.saveResumeEmpViews(resumeId);

		if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(createResume
				.getResumeType())) {
			model.addObject("createResume", createResume);
			model.setViewName("viewresume");
		} else if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume
				.getResumeType())) {
			try {
				model.setViewName("redirect:/jobSeekerResume/exportResume.html?fileName="
						+ resumeDTO.getFilePath());
				return model;
			} catch (Exception e) {
				LOGGER.error("Error in view resume builder", e);
			}
		} else {
			model.addObject("createResume", createResume);
			model.setViewName("viewCopyPasteResume");
		}
		return model;

	}

	/**
	 * This method is used to convert the ResumeDTOList coming from DB to JSON
	 * object.
	 * 
	 * @param List
	 *            <ResumeDTO>
	 * @return JSONObject
	 */
	public JSONObject searchResumeToJSONFromDB(List<ResumeDTO> resumeDTOList) {

		final JSONObject jobSrchJsonObj = new JSONObject();
		final JSONArray jsonRows = new JSONArray();

		for (ResumeDTO resumeDTO : resumeDTOList) {

			final JSONObject jobSrchJson = new JSONObject();

			jobSrchJson.put(MMJBCommonConstants.UPLOAD_RESUME_ID,
					resumeDTO.getUploadResumeId());
			jobSrchJson.put(MMJBCommonConstants.PUBLISH_RESUME_ID,
					resumeDTO.getPublishResumeId());
			jobSrchJson.put(MMJBCommonConstants.RESUME_DESIRED_POSTION,
					MMUtils.isNull(resumeDTO.getResumeName()));
			jobSrchJson.put(MMJBCommonConstants.APPLICANT_NAME,
					resumeDTO.getFullName());

			/*
			 * String location = null; if (resumeDTO.getCity() != null &&
			 * resumeDTO.getState() != null) { location = resumeDTO.getCity() +
			 * MMJBCommonConstants.COMMA + resumeDTO.getState(); } else if
			 * (resumeDTO.getCity() != null && resumeDTO.getState() == null) {
			 * location = resumeDTO.getCity(); }
			 */
			jobSrchJson.put(MMJBCommonConstants.LOCATION,
					MMUtils.isNull(resumeDTO.getState()));
			jobSrchJson.put(MMJBCommonConstants.EXPERIENCE,
					resumeDTO.getExperience());
			jobSrchJson.put(MMJBCommonConstants.EMPLOYMENT_TYPE,
					resumeDTO.getEmploymentType());
			jobSrchJson.put(MMJBCommonConstants.RELOCATE, "Yes");
			jobSrchJson.put(MMJBCommonConstants.POSTED_DT,
					MMUtils.convertToReqdDateString(resumeDTO.getPostDt()));

			jsonRows.add(jobSrchJson);

		}

		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				resumeDTOList.size());
		jobSrchJsonObj.put(MMJBCommonConstants.JSON_ROWS, jsonRows);

		return jobSrchJsonObj;
	}

	/**
	 * This method will convert the ResumeSearchResultDTO to JSON object
	 * 
	 * @param ResumeSearchResultDTO
	 * @return JSONObject
	 */
	private JSONObject searchResumeToJSON(
			final ResumeSearchResultDTO resumeSearchResultDTO) {

		final JSONObject jobSrchJsonObj = new JSONObject();
		// final JSONArray jsonRows = new JSONArray();
		// final List<ResumeDTO> jobDTOList =
		// resumeSearchResultDTO.getResultList();

		return jobSrchJsonObj;
	}
	
	/**
	 * The method is called to close the SaveThisJob popup
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employerpurchaseresumepopup")
	public ModelAndView openEmployerPurchaseResumePopup() {
		return new ModelAndView("employerpurchaseresumepopup");
	}

}
