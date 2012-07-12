package com.advanceweb.afc.jb.webapp.web.controllers.search;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;
import com.advanceweb.afc.jb.search.SaveSearchService;
import com.advanceweb.afc.jb.webapp.web.forms.search.SaveSearchForm;

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

	@RequestMapping(value = "/saveSearchedJobs", method = RequestMethod.GET)
	public ModelAndView saveSearchedJobs(@Valid SaveSearchForm form,
			BindingResult result) {

		// Transform SaveSearchForm to saveSearchedJobsDTO
		SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();
		/*
		 * saveSearchedJobsDTO.setLoginID(form.getLoginID());
		 * saveSearchedJobsDTO.setUrl(form.getUrl());
		 * saveSearchedJobsDTO.setUrlName(form.getUrlName());
		 * saveSearchedJobsDTO.setCreatedDate(form.getCreatedDate());
		 */

		saveSearchedJobsDTO.setLoginID("1");
		saveSearchedJobsDTO
				.setUrl("file://///nibc452/06%20JOB%20PORTAL/06%20IA/JobBoardPortalIAFinal/JobBoardPortalIAVer18/HTML/SearchResults.html?button2=Find+Jobs");
		saveSearchedJobsDTO.setUrlName("NewJobs");
		saveSearchedJobsDTO.setCreatedDate(new Date());
		saveSearchService.saveSearchedJobs(saveSearchedJobsDTO);
		// new ModelAndView("redirect:/saveSearchedJobs.html");
		return new ModelAndView();

	}

	/**
	 * This method is called to display Saved Job Searches
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/viewMySavedSearches", method = RequestMethod.GET)
	public String viewMySavedSearches(@Valid SaveSearchForm form,
			BindingResult result) {

		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
				.viewMySavedSearches(1);

		for (SaveSearchedJobsDTO saveSearchedJobsDTO : saveSearchedJobsDTOList) {
			System.out.println(saveSearchedJobsDTO);
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

		JpSaveSearch jpSaveSearch = saveSearchService.editSavedSearch(1);

		System.out.println(jpSaveSearch);

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

		if (saveSearchService.deleteSavedSearch(1)) {
			System.out.println("Deleted Succcessfully");
		}

		return "viewMySavedSearches";

	}

}
