package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONObject;

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
import com.advanceweb.afc.jb.job.service.SaveSearchService;
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
			BindingResult result) {

		// Transform SaveSearchForm to saveSearchedJobsDTO
		SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();

		saveSearchedJobsDTO.setUserID(saveSearchForm.getUserID());
		saveSearchedJobsDTO.setUrl(saveSearchForm.getUrl());
		saveSearchedJobsDTO.setSearchName(saveSearchForm.getSearchName());
		saveSearchedJobsDTO.setCreatedDate(saveSearchForm.getCreatedDate());
		saveSearchService.saveSearchedJobs(saveSearchedJobsDTO);
		// new ModelAndView("redirect:/saveSearchedJobs.html");
		return new ModelAndView();

	}
	
	@RequestMapping(value = "/saveSearchedNames", method = RequestMethod.GET)
	public ModelAndView saveMySavedSearches(@ModelAttribute("saveSearchForm") SaveSearchForm saveSearchForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		saveSearchForm.setUserID(5);
		if (saveSearchForm.getUserID() != 0) {
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
					.viewMySavedSearches(saveSearchForm.getUserID());
			List<DropDownDTO> notifyMeList = populateDropdownsService
					.populateDropdown("NotifyMe");
			model.addObject("notifyMeList", notifyMeList);
			model.addObject("saveSearchedJobsDTOList", saveSearchedJobsDTOList);
		}
		model.addObject(saveSearchForm);
		model.setViewName("jobseekersavedsearchespopup");
        return model;
	}

	/**
	 * This method is called to display Saved Searches
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/viewMySavedSearches", method = RequestMethod.GET)
	public ModelAndView viewMySavedSearches(@ModelAttribute("saveSearchForm") SaveSearchForm saveSearchForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		saveSearchForm.setUserID(5);
		if (saveSearchForm.getUserID() != 0) {
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
					.viewMySavedSearches(saveSearchForm.getUserID());
			List<DropDownDTO> notifyMeList = populateDropdownsService
					.populateDropdown("NotifyMe");
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
	public String viewMySavedSearchRecord(@ModelAttribute("saveSearchForm") SaveSearchForm saveSearchForm,
			BindingResult result) {

		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
				.viewMySavedSearchRecord(saveSearchForm.getUserID(),
						saveSearchForm.getSearchName());

		for (SaveSearchedJobsDTO saveSearchedJobsDTO : saveSearchedJobsDTOList) {
			// System.out.println(saveSearchedJobsDTO); }
		}
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

}
