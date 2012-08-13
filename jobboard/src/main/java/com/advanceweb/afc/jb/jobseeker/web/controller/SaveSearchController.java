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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.SaveSearchService;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

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
			.getLogger("SaveSearchController.class");

	@Autowired
	private SaveSearchService saveSearchService;

	@Autowired
	PopulateDropdowns populateDropdownsService;

	/**
	 * This method is used to save the searches in adm_save_search table
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/saveSearchedJobs", method = RequestMethod.GET)
	public ModelAndView saveSearchedJobs(@Valid SaveSearchForm saveSearchForm,
			BindingResult result, Map<String, JobSearchResultForm> model,
			@RequestParam("searchName") String searchName, HttpSession session) {
		
		/*if(result.hasErrors()){
			return new ModelAndView("jobseekersavethissearchpopup");
		}*/
		
		
		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			return new ModelAndView("jobSeekerLogin");
		} else {
			int userId = (Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
			SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();

			searchedJobsDTO.setUserID(userId);
			searchedJobsDTO.setUrl(MMJBCommonConstants.SEARCH_TYPE
					+ MMJBCommonConstants.EQUAL_TO
					+ saveSearchForm.getSearchName()
					+ MMJBCommonConstants.SEMICOLON
					+ MMJBCommonConstants.KEYWORDS
					+ MMJBCommonConstants.EQUAL_TO
					+ session.getAttribute(MMJBCommonConstants.KEYWORDS)
					+ MMJBCommonConstants.SEMICOLON
					+ MMJBCommonConstants.CITY_STATE
					+ MMJBCommonConstants.EQUAL_TO
					+ session.getAttribute(MMJBCommonConstants.CITY_STATE)
					+ MMJBCommonConstants.SEMICOLON
					+ MMJBCommonConstants.RADIUS + MMJBCommonConstants.EQUAL_TO
					+ session.getAttribute(MMJBCommonConstants.RADIUS));

			searchedJobsDTO.setSearchName(searchName);
			searchedJobsDTO.setCreatedDate(DateUtils.getCurrentDateAndTime());
			saveSearchService.saveSearchedJobs(searchedJobsDTO);
			
			//System.out.println("ifffff");
			model.put("jobSearchResultForm", new JobSearchResultForm());
			//System.out.println("ifffff");
			return new ModelAndView("redirect:/jobSeeker/jobSeekerDashBoard.html");
			
		}

	}

	/**
	 * This method is used to navigate the save this search pages to Login page
	 * or pop up page depending upon whether the user is a ananymous user or 
	 * registered user.
	 * @param saveSearchForm
	 * @param model
	 * @param session
	 * @return JSonObject
	 */

	@RequestMapping(value = "/saveThisSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveThisSearch(@Valid SaveSearchForm saveSearchForm,
			Map<String, SaveSearchForm> model, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		try {

			// Check for job seeker login
			if (session.getAttribute("userId") == null) {
				model.put("SaveSearchForm", new SaveSearchForm());
				jsonObject.put("NavigationPath",
						"../loginFormForJobSeeker/login");
			} else {
				model.put("SaveSearchForm", new SaveSearchForm());
				jsonObject.put("LoggedInNavigationPath",
						"../savedSearches/displaySaveThisSearchPopup");
			}

		} catch (Exception e) {
			LOGGER.info("Save this search ERROR");
		}
		return jsonObject;
	}

	/**
	 * This method is used to display the Save Search pop up.
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
		int userId = (Integer) session.getAttribute("userId");
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
	public String editSavedSearch(@Valid SaveSearchForm form,
			BindingResult result) {

		saveSearchService.editSavedSearch(1);

		// System.out.println(jpSaveSearch);

		return "viewMySavedSearches";

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
