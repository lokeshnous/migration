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

	private static final Logger LOGGER = Logger
			.getLogger(SaveSearchController.class);

	private String navigationPath;

	@Autowired
	private SaveSearchService saveSearchService;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Value("${saveThisSearchErrMsg}")
	private String saveThisSearchErrMsg;

	@Value("${savedSearchsLimit}")
	private String savedSearchsLimit;

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
		int userId = (Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
				.viewMySavedSearches(userId);
		int savedSearchCount = saveSearchedJobsDTOList.size();
		if (savedSearchCount == Integer.parseInt(savedSearchsLimit)) {
			saveSearchService.deleteFirstSearch(userId);
		}

		JSONObject jsonObject = new JSONObject();
		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);

		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			jsonObject.put("NavigationPath", navigationPath);
		} else {
//			int userId = (Integer) session
//					.getAttribute(MMJBCommonConstants.USER_ID);
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
					saveSearchService.saveSearchedJobs(searchedJobsDTO);
					jsonObject.put("LoggedInNavigationPath", "");
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
			@RequestParam("keywords") String keywords) {
		JSONObject jsonObject = new JSONObject();
		try {

			Map<String, String> sessionMap = checkSessionMap
					.getSearchSessionMap(session);

			// Check for job seeker login
			if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
				model.put("SaveSearchForm", new SaveSearchForm());
				jsonObject.put("LoggedInNavigationPath",
						"../savedSearches/anonymousSaveThisSearchPopUp");
			} else if ((sessionMap
					.get(MMJBCommonConstants.PERFORM_SAVED_SEARCH) == null)
					&& (sessionMap.get(MMJBCommonConstants.SEARCH_TYPE) != null
							&& sessionMap
									.get(MMJBCommonConstants.SEARCH_TYPE)
									.equals(MMJBCommonConstants.BASIC_SEARCH_TYPE) && sessionMap
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

				saveSearchService.updateSearchDetails(searchedJobsDTO);

				session.removeAttribute(sessionMap
						.remove(MMJBCommonConstants.SAVE_SEARCH_NAME));
				session.removeAttribute(sessionMap
						.remove(MMJBCommonConstants.SEARCH_TYPE));

				jsonObject.put("NavigationPath",
						"../jobSeeker/jobSeekerDashBoard");

			} else {
				if (keywords != null && keywords != MMJBCommonConstants.EMPTY) {
					model.put("SaveSearchForm", new SaveSearchForm());
					jsonObject.put("LoggedInNavigationPath",
							"../savedSearches/displaySaveThisSearchPopup");
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
		model.put("saveSearchForm", new SaveSearchForm());
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
			@ModelAttribute("saveSearchForm") SaveSearchForm saveSearchForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		saveSearchForm.setUserID(userId);
		if (saveSearchForm.getUserID() != 0) {
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
					.viewMySavedSearches(saveSearchForm.getUserID());
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
	 * This method is called to display Saved Job Searches
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/viewMySavedSearchRecord", method = RequestMethod.GET)
	public String viewMySavedSearchRecord(
			@ModelAttribute("saveSearchForm") SaveSearchForm saveSearchForm,
			BindingResult result) {

		// List<SaveSearchedJobsDTO> saveSearchedJobsDTOList =
		saveSearchService.viewMySavedSearchRecord(saveSearchForm.getUserID(),
				saveSearchForm.getSearchName());

		return "viewMySavedSearches";

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
				.editSavedSearch(searchId);

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

}
