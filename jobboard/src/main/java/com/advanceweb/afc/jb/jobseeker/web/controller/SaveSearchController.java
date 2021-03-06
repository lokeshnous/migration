/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
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
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.job.service.SaveSearchService;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.search.SearchParamDTO;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 10th July 2012
 */

@Controller
@RequestMapping(value = "/savedSearches")
public class SaveSearchController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(SaveSearchController.class);

	/** The navigation path. */
	private String navigationPath;

	/** The Constant LOGGED_NAV_PATH. */
	private static final String LOGGED_NAV_PATH = "LoggedInNavigationPath";

	/** The Constant SAVE_SEARCH_FORM. */
	private static final String SAVE_SEARCH_FORM = "saveSearchForm";

	/** The Constant RECENT_SRCH_LIST. */
	private static final String RECENT_SRCH_LIST = "recentSearchList";

	/** The save search service. */
	@Autowired
	private SaveSearchService saveSearchService;

	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/** The save this search err msg. */
	@Value("${saveThisSearchErrMsg}")
	private String saveThisSearchErrMsg;

	/** The saved searchs limit. */
	@Value("${savedSearchsLimit}")
	private String savedSearchsLimit;

	/** The check session map. */
	@Autowired
	private CheckSessionMap checkSessionMap;

	/**
	 * This method is used to save the searches in adm_save_search table
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/saveSearchedJobs", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveSearchedJobs(@Valid SaveSearchForm saveSearchForm,
			BindingResult result, Map<String, JobSearchResultForm> model,
			@RequestParam("searchName") String searchName, HttpSession session) {

		// Before user saves his search need to check save search
		// records are more than 5 searches.
		// if yes then delete the first saved search
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);

		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
				.viewMySavedSearches(userId, false);
		/*
		 * int savedSearchCount = saveSearchedJobsDTOList.size(); if
		 * (savedSearchCount == Integer.parseInt(savedSearchsLimit)) {
		 * saveSearchService.deleteFirstSearch(userId); }
		 */

		JSONObject jsonObject = new JSONObject();
		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);

		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			jsonObject.put("NavigationPath", navigationPath);

		} else {
			// int userId = (Integer) session
			// .getAttribute(MMJBCommonConstants.USER_ID);
			SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();

			if (StringUtils.isEmpty(searchName)) {
				jsonObject.put("EmptySearchName", "EmptySearchName");
			} else {

				boolean isSrchNameExist = saveSearchService.validateSearchName(
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
					if (session.getAttribute("clearAllSearchId") != null) {
						int id = Integer.parseInt((String) session
								.getAttribute("clearAllSearchId"));
						saveSearchService.updateSearchName(id, searchName);
					} else {

						int savedSearchCount = saveSearchedJobsDTOList.size();
						if (savedSearchCount == Integer
								.parseInt(savedSearchsLimit)) {
							saveSearchService.deleteFirstSearch(userId);
						}
						saveSearchService.saveSearchedJobs(searchedJobsDTO);
					}
					if (null != session
							.getAttribute(MMJBCommonConstants.RETAIN_SEARCH)) {
						session.removeAttribute(MMJBCommonConstants.RETAIN_SEARCH);
						jsonObject.put(MMJBCommonConstants.RETAIN_SEARCH, true);
					}
					jsonObject.put(LOGGED_NAV_PATH, "");
					jsonObject.put("success", "success");
				}

			}

		}
		return jsonObject;

	}

	/**
	 * This method is used to navigate the save this search pages to Login page
	 * or pop up page depending upon whether the user is a ananymous user or
	 * registered user.
	 * 
	 * @param saveSearchForm
	 * @param model
	 * @param session
	 * @return JSonObject
	 */

	@RequestMapping(value = "/saveThisSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveThisSearch(@Valid SaveSearchForm saveSearchForm,
			Map<String, SaveSearchForm> model, HttpSession session,
			@RequestParam("keywords") String keywords,
			HttpServletRequest request) {

		JSONObject jsonObject = new JSONObject();
		try {

			Map<String, String> sessionMap = checkSessionMap
					.getSearchSessionMap(session);

			// Check for job seeker login
			if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
				model.put(SAVE_SEARCH_FORM, new SaveSearchForm());
				jsonObject.put(LOGGED_NAV_PATH, request.getContextPath()
						+ "/savedSearches/anonymousSaveThisSearchPopUp");
			} else if ((sessionMap
					.get(MMJBCommonConstants.PERFORM_SAVED_SEARCH) == null)
					&& (sessionMap.get(MMJBCommonConstants.SEARCH_TYPE) != null
							&& sessionMap
									.get(MMJBCommonConstants.SEARCH_TYPE)
									.equals(MMJBCommonConstants.BASIC_SEARCH_TYPE) && sessionMap
							.get(MMJBCommonConstants.SAVE_SEARCH_ID) != null)) {

				SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();

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

				// searchedJobsDTO.setUserID((Integer) session
				// .getAttribute(MMJBCommonConstants.USER_ID));

				saveSearchService.updateSearchDetails(searchedJobsDTO);

				session.removeAttribute(sessionMap
						.remove(MMJBCommonConstants.SAVE_SEARCH_NAME));
				session.removeAttribute(sessionMap
						.remove(MMJBCommonConstants.SEARCH_TYPE));

				jsonObject.put("NavigationPath", request.getContextPath()
						+ "/jobSeeker/jobSeekerDashBoard");

			} else {
				if (keywords != null && keywords != MMJBCommonConstants.EMPTY) {
					model.put(SAVE_SEARCH_FORM, new SaveSearchForm());
					jsonObject.put(LOGGED_NAV_PATH, request.getContextPath()
							+ "/savedSearches/displaySaveThisSearchPopup");
				} else {
					jsonObject.put("failure", saveThisSearchErrMsg);
				}

			}

		} catch (Exception e) {
			LOGGER.error("Save this search ERROR on save search :", e);
		}
		return jsonObject;
	}

	/**
	 * This method helps to display the popup to save the selected recent search
	 * 
	 * @param saveSearchForm
	 * @param model
	 * @param session
	 * @return JSonObject
	 */
	@RequestMapping(value = "/displaysavesearchpopup", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject displaySaveSearchPopup(@Valid SaveSearchForm saveSearchForm,
			Model model, HttpSession session, HttpServletRequest request,
			@RequestParam("savesearchid") int saveSearchID) {

		JSONObject jsonObject = new JSONObject();
		try {
			session.setAttribute("recentSearchId", saveSearchID);
			saveSearchForm.setSaveSearchId(saveSearchID);
			model.addAttribute(SAVE_SEARCH_FORM, saveSearchForm);
			jsonObject.put("NavigationPath", request.getContextPath()
					+ "/savedSearches/displaySaveThisSearchPopup");
		} catch (Exception e) {
			LOGGER.error(
					"display the Save This SearchPopup on recent search page :",
					e);
		}
		return jsonObject;
	}

	/**
	 * This method helps to save the recent search in my recent search page
	 * 
	 * @param saveSearchForm
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveRecentSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveRecentSearch(Model model, HttpSession session,
			HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		try {
			int saveSearchId = Integer.parseInt(session.getAttribute(
					"recentSearchId").toString());
			String saveSearchName = request.getParameter("searchName");

			if (saveSearchName == MMJBCommonConstants.EMPTY) {
				jsonObject.put("EmptySearchName", "EmptySearchName");
				return jsonObject;
			}
			boolean isSrchNameExist = saveSearchService.validateSearchName(
					saveSearchName, getUserID(session));

			if (isSrchNameExist) {
				jsonObject.put("DuplicateSearchName", "DuplicateSearchName");
				return jsonObject;

			}
			session.removeAttribute("recentSearchId");
			// Before user saves his search need to check save search
			// records are more than 5 searches.
			// if yes then delete the first saved search
			int userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);

			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
					.viewMySavedSearches(userId, false);
			int savedSearchCount = saveSearchedJobsDTOList.size();
			if (savedSearchCount == Integer.parseInt(savedSearchsLimit)) {
				saveSearchService.deleteFirstSearch(userId);
			}

			saveSearchService.saveRecentSearch(saveSearchId, saveSearchName);

			jsonObject.put("NavigationPath", request.getContextPath()
					+ "/savedSearches/viewrecentsearches");
		} catch (Exception e) {
			LOGGER.error("Save this search ERROR", e);
		}
		return jsonObject;
	}

	/**
	 * The method is called to close the SaveThisJob popup
	 * 
	 * @param JobSearchViewDetailForm
	 * @return
	 */
	@RequestMapping(value = "/anonymousSaveThisSearchPopUp")
	public ModelAndView callanonymousSaveThisSearchPopUp(
			Map<String, JobSearchResultForm> model) {
		return new ModelAndView("anonymousSaveThisSearchPopUp");
	}

	/**
	 * This method is used to display the Save Search pop up.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/displaySaveThisSearchPopup", method = RequestMethod.GET)
	public ModelAndView displaySaveThisSearchPopup(
			Map<String, SaveSearchForm> model) {
		model.put(SAVE_SEARCH_FORM, new SaveSearchForm());
		return new ModelAndView("jobseekersavethissearchpopup");
	}

	/**
	 * This method is called to display Saved Searches
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/viewMySavedSearches", method = RequestMethod.GET)
	public ModelAndView viewMySavedSearches(
			@ModelAttribute(SAVE_SEARCH_FORM) SaveSearchForm saveSearchForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = getUserID(session);
		if (userId != 0) {
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
					.viewMySavedSearches(userId, false);
			List<DropDownDTO> notifyMeList = populateDropdownsService
					.populateDropdown("NotifyMe");
			saveSearchForm.setSaveSearchedJobsDTOList(saveSearchedJobsDTOList);
			model.addObject("notifyMeList", notifyMeList);
			model.addObject("saveSearchedJobsDTOList", saveSearchedJobsDTOList);
		}
		model.addObject(saveSearchForm);
		model.setViewName("jobseekersavedsearchespopup");
		return model;
	}

	/**
	 * This method is called to edit a Saved Job Search
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/editSavedSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject editSavedSearch(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("searchId") int searchId,
			@RequestParam("performSearch") String performSearch) {

		JSONObject jsonObject = new JSONObject();

		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);

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

		List<SaveSearchedJobsDTO> saveSrchJobsDTOList = saveSearchService
				.getSavedSearch(searchId);

		if (!saveSrchJobsDTOList.isEmpty()) {
			String urlString = saveSrchJobsDTOList.get(0).getUrl();
			String saveSearchName = saveSrchJobsDTOList.get(0).getSearchName();
			Map<String, String> urlMap = MMUtils.getUrlMap(urlString);

			sessionMap.put(MMJBCommonConstants.SEARCH_TYPE,
					urlMap.get(MMJBCommonConstants.SEARCH_TYPE));
			sessionMap.put(SearchParamDTO.SEARCH_NAME, saveSearchName);
			sessionMap.put(SearchParamDTO.KEYWORDS,
					urlMap.get(SearchParamDTO.KEYWORDS));
			sessionMap.put(SearchParamDTO.CITY_STATE,
					urlMap.get(SearchParamDTO.CITY_STATE));
			sessionMap.put(SearchParamDTO.RADIUS,
					urlMap.get(SearchParamDTO.RADIUS));
			sessionMap.put(MMJBCommonConstants.AUTOLOAD, String.valueOf(true));
			sessionMap.put(MMJBCommonConstants.SAVE_SEARCH_ID,
					String.valueOf(searchId));

			session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, sessionMap);

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
	 * This method is called to delete a Saved Job Search
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/deleteSavedSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject deleteResume(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("saveSearchId") int saveSearchId) {

		boolean deleteStatus = saveSearchService
				.deleteSavedSearch(saveSearchId);
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
	@RequestMapping(value = "/saveSearchedNames", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveMySavedSearches(HttpServletRequest request,
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
		boolean saveData = saveSearchService.saveModifiedData(searchedJobsDTOs);
		JSONObject saveStatusJson = new JSONObject();
		if (saveData) {
			saveStatusJson.put("success", "Data Updated Successfully");
		} else {
			saveStatusJson.put("failed", "Failed to update the data");
		}
		return saveStatusJson;
	}

	/**
	 * This method redirects user to job seeker login page
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/retainSaveSearch", method = RequestMethod.GET)
	public ModelAndView retainSaveSearch(HttpSession session) {
		ModelAndView model = new ModelAndView();
		session.setAttribute(MMJBCommonConstants.RETAIN_SEARCH, true);
		model.setViewName("redirect:/commonlogin/login.html?page=jobSeeker");
		return model;

	}

	/**
	 * This method calls the Save This Search Popup when an anonymous user logs
	 * in
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/saveSearchPopup", method = RequestMethod.GET)
	public ModelAndView callSaveSearchPopup(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("jobseekersavethissearchpopup");
		return model;

	}

	/**
	 * Method to return User id.
	 * 
	 * @param session
	 * @return
	 */
	private Integer getUserID(HttpSession session) {

		int userId = 0;

		if ((null != session.getAttribute(MMJBCommonConstants.USER_ID))
				&& StringUtils.isNotEmpty(session.getAttribute(
						MMJBCommonConstants.USER_ID).toString())) {

			userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);

		}

		return userId;
	}

	/**
	 * The method helps to view all recent searches of user
	 * 
	 * @param session
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/viewrecentsearches", method = RequestMethod.GET)
	public ModelAndView viewRecentsearches(HttpSession session,
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		session.removeAttribute("recentSearchId");
		// get the userId from session
		int userId = getUserID(session);
		if (userId > 0) {
			List<SaveSearchedJobsDTO> recentSearches = saveSearchService
					.viewMySavedSearches(userId, true);

			session.setAttribute(RECENT_SRCH_LIST, recentSearches);
		}
		modelAndView.setViewName("myrecentsearchespopup");

		return modelAndView;
	}

	/**
	 * This method is used to remove the retainSaveSearch session values after
	 * clicking the cancel button from save this search popup
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteData", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject deleteData(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		if (null != session.getAttribute(MMJBCommonConstants.RETAIN_SEARCH)) {
			session.removeAttribute(MMJBCommonConstants.RETAIN_SEARCH);
			jsonObject.put(MMJBCommonConstants.RETAIN_SEARCH, false);
		}
		return jsonObject;
	}

}
