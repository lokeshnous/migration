package com.advanceweb.afc.jb.resume.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.jobseeker.web.controller.CheckSessionMap;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;
import com.advanceweb.afc.jb.lookup.service.LookupService;
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
