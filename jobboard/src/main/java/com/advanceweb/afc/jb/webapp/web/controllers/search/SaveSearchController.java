package com.advanceweb.afc.jb.webapp.web.controllers.search;



import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.search.SaveSearchService;
import com.advanceweb.afc.jb.webapp.web.forms.search.SaveSearchForm;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 10th July 2012
 */

@Controller
public class SaveSearchController {

	@Autowired
	private SaveSearchService saveSearchService;

	@RequestMapping(value = "/saveSearchedJobs", method = RequestMethod.GET)
	public ModelAndView saveSearchedJobs(@Valid SaveSearchForm form,
			BindingResult result) {

		// Transform SaveSearchForm to saveSearchedJobsDTO
		SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();
		/*saveSearchedJobsDTO.setLoginID(form.getLoginID());
		saveSearchedJobsDTO.setUrl(form.getUrl());
		saveSearchedJobsDTO.setUrlName(form.getUrlName());
		saveSearchedJobsDTO.setCreatedDate(form.getCreatedDate());*/
		
		saveSearchedJobsDTO.setLoginID("1");
		saveSearchedJobsDTO.setUrl("file://///nibc452/06%20JOB%20PORTAL/06%20IA/JobBoardPortalIAFinal/JobBoardPortalIAVer18/HTML/SearchResults.html?button2=Find+Jobs");
		saveSearchedJobsDTO.setUrlName("NewJobs");
		saveSearchedJobsDTO.setCreatedDate(new Date());
		saveSearchService.saveSearchedJobs(saveSearchedJobsDTO);
		// new ModelAndView("redirect:/saveSearchedJobs.html");
		return new ModelAndView();

	}

}
