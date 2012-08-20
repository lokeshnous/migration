package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
		EmployerInfoDTO employerInfoDTO=employerJobPost.getEmployerInfo(1,"facility_admin");
		List<DropDownDTO> empTypeList = populateDropdownsService .populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> templateList = populateDropdownsService .populateBrandingTemplateDropdown(employerInfoDTO.getFacilityId(),employerInfoDTO.getUserId());
		List<DropDownDTO> jbPostingTypeList = populateDropdownsService .populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> jbOwnerList = populateDropdownsService .populateJobOwnersDropdown(employerInfoDTO.getFacilityId(), employerInfoDTO.getUserId(), employerInfoDTO.getRoleId());
		List<CountryDTO> countryList = populateDropdownsService.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		
		jobPostForm.setCompanyName(employerInfoDTO.getCustomerName());
		jobPostForm.setCustomerNo("CT"+String.valueOf((new Random()).nextLong()));
		jobPostForm.setJobId("JB"+String.valueOf((new Random()).nextLong()));	
		
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
	
	/**
	 * This method is called to save the job details
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/saveNewJob",method = RequestMethod.POST)
	public ModelAndView savePostJob(JobPostForm form,BindingResult result) {

		ModelAndView  model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if(!StringUtils.isEmpty(errMessage)){
			model.setViewName("postNewJobs");
			model.addObject("errorMessage", errMessage);
			return model;
		}
		JobPostDTO dto=transformJobPost.jobPostFormToJobPostDTO(form);
		employerJobPost.savePostJob(dto);
		model.setViewName("postNewJobs");
		return model;
	}	
	
	/**
	 * This method is called to validate the form
	 * 
	 * @param form
	 * @return
	 */
	private String validateJobPostDetails(JobPostForm form){
		
		if(null != form){
			if(StringUtils.isEmpty(form.getJobTitle()) || 
					StringUtils.isEmpty(form.getJobDesc()) ||
					(StringUtils.isEmpty(form.getApplyUrl()) && 
							StringUtils.isEmpty(form.getAtsUrl()) && 
							StringUtils.isEmpty(form.getApplyEmail()) )){
				return "Please fill the required fields";
			}
		}
		
		return null;
	}
	
}
