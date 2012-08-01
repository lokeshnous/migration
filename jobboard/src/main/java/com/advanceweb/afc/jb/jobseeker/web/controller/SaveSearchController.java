package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	/**
	 * This method is called to display Saved Searches
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/viewMySavedSearches", method = RequestMethod.GET)
	public String viewMySavedSearches(@Valid SaveSearchForm saveSearchForm,
			BindingResult result, Map<String, Object> model) {
		saveSearchForm.setUserID(203);
		if (saveSearchForm.getUserID() != 0) {
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
					.viewMySavedSearches(saveSearchForm.getUserID());
			List<DropDownDTO> notifyMeList = populateDropdownsService
					.populateDropdown("NotifyMe");
			model.put("notifyMeList", notifyMeList);
			model.put("saveSearchedJobsDTOList", saveSearchedJobsDTOList);
		}
		return "jobseekersavedsearchespopup";

	}

	/**
	 * This method is called to display Saved Job Searches
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/viewMySavedSearchRecord", method = RequestMethod.GET)
	public String viewMySavedSearchRecord(@Valid SaveSearchForm saveSearchForm,
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
	public String deleteSavedSearch(@Valid SaveSearchForm form,
			BindingResult result) {

		saveSearchService.deleteSavedSearch(1);
		return "viewMySavedSearches";

	}

}
