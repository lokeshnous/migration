/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */

package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.ExcludeFromDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.JobPostedDateDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * @ Author : Prince Mathew
   @ Version: 1.0
   @ Created: Jul 10, 2012
   @ Purpose: This class will act as a controller for the Job Seekers Advance Search  
 */


@Controller
@RequestMapping("/jobseekeradvancesearch")
public class JobSeekerAdvanceSearchController {

	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;
	
	
	/**
	 * Creates the job seeker advance search.
	 *
	 * @param model the model
	 * @return the model and view
	 */
	@RequestMapping(value="/jobseekeradvancesearch",method = RequestMethod.GET)
	public ModelAndView createJobSeekerAdvanceSearch(Map model) {
		
		JobseekerAdvanceSearchForm jobseekerAdvanceSearchForm = new JobseekerAdvanceSearchForm();
		List<RadiusDTO> radiusList=populateDropdownsService.getRadiusList(); 
		List<ExcludeFromDTO> excludeFromList=populateDropdownsService.getExcludeFromList(); 
		List<FromZipcodeDTO> fromZipcodeList=populateDropdownsService.getFromZipcodeList();
		List<StateDTO> stateList=populateDropdownsService.getStateList();
		List<MetroAreaDTO> metroAreaList=populateDropdownsService.getMetroAreaList();
		List<EmploymentTypeDTO> employmentTypeList=populateDropdownsService.getEmploymentTypeList();
		List<JobPostedDateDTO> jobPostedDateList=populateDropdownsService.getJobPostedDateList();
		
		model.put("radiusList",radiusList);
		model.put("excludeFromList",excludeFromList);
		model.put("fromZipcodeList",fromZipcodeList);
		model.put("stateList",stateList);
		model.put("metroAreaList",metroAreaList);
		model.put("employmentTypeList",employmentTypeList);
		model.put("jobPostedDateList",jobPostedDateList);
		model.put("jobseekerAdvanceSearchForm", jobseekerAdvanceSearchForm);
		
		return new ModelAndView("jobseekerAdvanceSearchForm");
	}

	
	
}
