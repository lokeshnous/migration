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

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.employer.web.controller.MetricsForm;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.CheckSessionMap;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.resume.ResumeService;
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
	
	@Autowired
	private PopulateDropdowns populateDropdownsService;
	
	@Value("${savedSearchsLimit}")
	private String savedSearchsLimit;
	
	private String navigationPath;
	
	@Value("${saveThisSearchErrMsg}")
	private String saveThisSearchErrMsg;
	
	@Autowired
	private ResumeService resumeService;

	@Autowired
	private TransformCreateResume transCreateResume;
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
	
	@RequestMapping(value = "/mySavedResumeSearches", method = RequestMethod.GET)
	public ModelAndView mySavedResumeSearches(
			@ModelAttribute("searchResumeForm") SearchResumeForm searchResumeForm,
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
			searchResumeForm.setSaveSearchedJobsDTOList(saveSearchedJobsDTOList);
			model.addObject("notifyMeList", notifyMeList);
			model.addObject("saveSearchedJobsDTOList", saveSearchedJobsDTOList);
		}
		model.addObject(searchResumeForm);
		model.setViewName("empSavedResumeSearchPopup");
		return model;
	}

	/**
	 * This method is used to search resumes directly from the DB.
	 * This is a temporary implementation since SOLR is not yet configured.
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
		LOGGER.info("Calling Search Resume Controller!!!");
		//session.removeAttribute("resumeDTOList");
		session.removeAttribute("jobSrchJsonObj");
		session.removeAttribute(MMJBCommonConstants.KEYWORD_STRING);
		//session.removeAttribute(MMJBCommonConstants.AUTOLOAD);
		List<ResumeDTO> resumeDTOList = null;
		JSONObject jobSrchJsonObj = null;
		// Calling the jobSearch() of Service layer for getting the resume list
		try {
			resumeDTOList = resumeSearchService.resumeSearchFromDB(searchResumeForm.getKeywords());
			//session.setAttribute("resumeDTOList", resumeDTOList);
			if (resumeDTOList != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
				jobSrchJsonObj = jsonConverterService.convertToJSONForResumeFromDB(resumeDTOList);
			}
			jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS, resumeDTOList.size());
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Resume Search Result from DB...");
		}
		// add values in session
		jobSrchJsonObj.put("keywords", searchResumeForm.getKeywords());
		session.setAttribute("resSrchJsonList", jobSrchJsonObj);
		session.setAttribute(MMJBCommonConstants.KEYWORD_STRING, searchResumeForm.getKeywords());
		//session.setAttribute(MMJBCommonConstants.AUTOLOAD, String.valueOf(true));
		return jobSrchJsonObj;
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

		if (saveSrchJobsDTOList.size() > 0) {
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

			session.setAttribute(SearchParamDTO.RESUME_SEARCH_SESSION_MAP, sessionMap);

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
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/findResumePage", method = RequestMethod.GET)
	public ModelAndView findResumePage(Map<String, SearchResumeForm> model,
			HttpSession session) {
		SearchResumeForm searchResumeForm = new SearchResumeForm();
		ModelAndView modelAndView = new ModelAndView();
		MetricsForm employerDashBoardForm = new MetricsForm();
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
			LOGGER.info("Removing keywords, city,state, autoload from session....");
			session.removeAttribute(sessionMap.remove(SearchParamDTO.KEYWORDS));
			session.removeAttribute(sessionMap
					.remove(SearchParamDTO.CITY_STATE));
			session.removeAttribute(sessionMap.remove(SearchParamDTO.RADIUS));
			session.removeAttribute(sessionMap
					.remove(MMJBCommonConstants.AUTOLOAD));
		}
		modelAndView.addObject("searchResumeForm", searchResumeForm);
		modelAndView.addObject("employerDashBoardForm", employerDashBoardForm);
		modelAndView.setViewName("employerDashboard");
		return modelAndView;
	}
	
	/**`
	 * This method is called to delete a Saved Job Search
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
		boolean saveData = resumeSearchService.saveModifiedData(searchedJobsDTOs);
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
		int userId = (Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
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
//			int userId = (Integer) session
//					.getAttribute(MMJBCommonConstants.USER_ID);
			SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();

			if (StringUtils.isEmpty(searchName)) {
				jsonObject.put("EmptySearchName", "EmptySearchName");
			} else {

				boolean isSrchNameExist = resumeSearchService.validateSearchName(
						searchName, userId);

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
		model.put("searchResumeForm", new SearchResumeForm());
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
		 if ((sessionMap
					.get(MMJBCommonConstants.PERFORM_SAVED_SEARCH) == null)
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
					model.put("searchResumeForm", new SearchResumeForm());
					jsonObject.put("LoggedInNavigationPath",
							"../employerSearchResume/empSaveThisSearchPopup");
				} else {
					jsonObject.put("failure", saveThisSearchErrMsg);
				}

			}

		} catch (Exception e) {
			LOGGER.info("Save this search ERROR");
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
	public @ResponseBody List<String> moveResumeToFolder(HttpServletResponse response, 
			HttpServletRequest request, HttpSession session,
			@RequestParam("resumeIdAndDateArr") String resumeIdAndDateArr) {
		List<String> idList = new ArrayList<String>();
		idList.add("Selected Resumes moved successfully to Default Folder.");
		//idList.add("20");
		
		//ModelAndView modelAndView = new ModelAndView();
		LOGGER.info("Publish Resume ID and Created date list :"+resumeIdAndDateArr);
		String[] resumeIdAndDateArray = resumeIdAndDateArr.split(",");
		//String[] createdDateArray = createdDateArr.split(",");
		
		List<String> publishResumeIdArrList = getPublishResumeArrayList(resumeIdAndDateArray);
		//List<Date> createdDateList = getCreatedDateArrayList(createdDateArray);
		
		int userId = (Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
		LOGGER.info("User Id is :"+userId);
		boolean status = resumeService.moveResumesToFolder(publishResumeIdArrList, userId);
		if(status){
			LOGGER.info("Successfully Moved the Resumes to the Common Folder.");
		}else{
			LOGGER.info("Error occurred while moving the Resumes to the specified Folder.");
		}
		
		//modelAndView.setViewName("jobboardsearchresumeresultbody");
		//return modelAndView;
		
		return idList;
	}

	/**
	 * 
	 * @param publishResumeIdArr
	 */
	private List<String> getPublishResumeArrayList(String[] publishResumeIdArr) {
		List<String> publishResumeIDList = new ArrayList<String>(); 
		for(String publishId : publishResumeIdArr){
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
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		/**
		 *  Introduced a new variable "createResumed" to resolve PMD issue. 
		 */
		CreateResume createResume =createResumed; 
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
		session.setAttribute(MMJBCommonConstants.MODULE_STRING, MMJBCommonConstants.EMPLOYER);
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
				LOGGER.info("Error in view resume builder",e);
			}
		} else {
			model.addObject("createResume", createResume);
			model.setViewName("viewCopyPasteResume");
		}
		return model;

	}
	
	
	
}
