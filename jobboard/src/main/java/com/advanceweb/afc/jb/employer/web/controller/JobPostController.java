package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will act as a Controller for the Post New Job 
 */

@Controller
@RequestMapping("/employer")
public class JobPostController {
	@Autowired
	private JobPostService employerJobPost;
	
	@Autowired
	private TransformJobPost transformJobPost;
	
	@Autowired
	private PopulateDropdowns populateDropdownsService;	
	
	@RequestMapping(value="/postNewJobs",method = RequestMethod.GET)
	public ModelAndView showPostJob() {
		
		ModelAndView model = new ModelAndView();
		JobPostForm jobPostForm=new JobPostForm();		
		
		List<DropDownDTO> empTypeList = populateDropdownsService .populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> templateList = populateDropdownsService .populateBrandingTemplateDropdown();
		List<DropDownDTO> jbPostingTypeList = populateDropdownsService .populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> jbOwnerList = populateDropdownsService .populateJobOwnersDropdown(110, 1);
		List<CountryDTO> countryList = populateDropdownsService.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		
		jobPostForm.setCustomerNo("CT"+String.valueOf((new Random()).nextLong()));
		jobPostForm.setJobId("JB"+String.valueOf((new Random()).nextLong()));
		
		EmployerInfoDTO employerInfoDTO=employerJobPost.getEmployerInfo(1);
		
		//Populating Dropdowns
		model.addObject("stateList",stateList);
		model.addObject("empTypeList",empTypeList);
		model.addObject("countryList",countryList);
		model.addObject("templateList",templateList);
		model.addObject("jbOwnerList", jbOwnerList);
		model.addObject("jbPostingTypeList", jbPostingTypeList);
		//Populating Dropdowns
		
		model.addObject("jobPostForm",jobPostForm);
		model.setViewName("postNewJobs");
		
		return model;
	}
	
	
	@RequestMapping(value="/saveNewJob",method = RequestMethod.POST)
	public ModelAndView savePostJob(JobPostForm form,BindingResult result) {

/*		if (result.hasErrors()) {
			return new ModelAndView("postnewjob");
		}*/
		
		
		JobPostDTO dto=new JobPostDTO();
		dto=transformJobPost.jobPostFormToJobPostDTO(form);
		employerJobPost.savePostJob(dto);
		return new ModelAndView("postnewjob");
	}	
	
}
