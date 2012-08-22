package com.advanceweb.afc.jb.employer.web.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.helper.JobPostConversionHelper;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.resume.web.controller.CreateResume;

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
		List<DropDownDTO> jbPostingTypeList = populateDropdownsService .populateJobPostingTypeDropdowns();
		List<DropDownDTO> jbOwnerList = populateDropdownsService .populateJobOwnersDropdown(employerInfoDTO.getFacilityId(), employerInfoDTO.getUserId(), employerInfoDTO.getRoleId());
		List<CountryDTO> countryList = populateDropdownsService.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		List<FromZipcodeDTO> zipCodeList = populateDropdownsService.getFromZipcodeList();
		
		jobPostForm.setCompanyName(employerInfoDTO.getCustomerName());
		
		//The following commented section can be deleted.
		
/*		SecureRandom random=null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		int myInt = random.nextInt();
		jobPostForm.setCustomerNo("MMCN"+Math.abs(myInt));
		jobPostForm.setJobNumber("JT"+Math.abs(myInt));	*/
		
		//Populating Dropdowns
		model.addObject("stateList",stateList);
		model.addObject("empTypeList",empTypeList);
		model.addObject("countryList",countryList);
		model.addObject("templateList",templateList);
		model.addObject("jbOwnerList", jbOwnerList);
		model.addObject("zipCodeList",zipCodeList);
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
	@RequestMapping(value="/saveNewJob",method = RequestMethod.POST, params="PostNewJob")
	public ModelAndView savePostJob(JobPostForm form,BindingResult result) {

		ModelAndView  model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if(!StringUtils.isEmpty(errMessage)){
			model=populateDropdowns(model);
			model.setViewName("postNewJobs");
			model.addObject("errorMessage", errMessage);
			return model;
		}
		JobPostDTO dto=transformJobPost.jobPostFormToJobPostDTO(form);
		employerJobPost.savePostJob(dto);
		model.setViewName("manageJobPosting");
		return model;
	}	
	
	/**
	 * This method is called to save the job details
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/saveNewJob",method = RequestMethod.POST, params="ScheduleJob")
	public ModelAndView schedulePostJob(JobPostForm form,BindingResult result) {

		ModelAndView  model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if(!StringUtils.isEmpty(errMessage)){
			model=populateDropdowns(model);
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
	 * This method is called to save the job details
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/saveNewJob",method = RequestMethod.POST, params="SaveAsDraft")
	public ModelAndView savePostJobAsDraft(JobPostForm form,BindingResult result) {

		ModelAndView  model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if(!StringUtils.isEmpty(errMessage)){
			model=populateDropdowns(model);
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
	 * This method is called to save the job details
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/saveNewJob",method = RequestMethod.POST, params="Cancel")
	public ModelAndView cancelPostJob(JobPostForm form,BindingResult result) {

		return new ModelAndView("forward:/employer/employerDashBoard.html","", "");
	}
	
	/**
	 * This method is called to validate the form
	 * 
	 * @param form
	 * @return
	 */
	private String validateJobPostDetails(JobPostForm form){
		
		 UrlValidator urlValidator = new UrlValidator();
		if(null != form){
			if(StringUtils.isEmpty(form.getJobTitle()) || 
					StringUtils.isEmpty(form.getJobCity()) ||
					StringUtils.isEmpty(form.getJobZipCode()) ||
					MMJBCommonConstants.ZERO.equals(form.getJobCountry()) ||
					MMJBCommonConstants.ZERO.equals(form.getJobState()) ||
					StringUtils.isEmpty(form.getJobDesc()) ||
					(StringUtils.isEmpty(form.getApplyUrl()) && 
							StringUtils.isEmpty(form.getAtsUrl()) && 
							StringUtils.isEmpty(form.getApplyEmail()) )){
				return "Please fill the required fields";
			}
									
			
			//Validating URL
			if((!StringUtils.isEmpty(form.getApplyUrl()) && !urlValidator.isValid(form.getApplyUrl())) || 
				(!StringUtils.isEmpty(form.getAtsUrl()) && !urlValidator.isValid(form.getAtsUrl())) || 
				(!StringUtils.isEmpty(form.getApplyEmail()) && !urlValidator.isValid(form.getApplyEmail()))){
				
				return "Please enter valid URL";
			}
		}
		
		return null;
	}
	
	private ModelAndView populateDropdowns(ModelAndView  model){
		EmployerInfoDTO employerInfoDTO=employerJobPost.getEmployerInfo(1,"facility_admin");
		List<DropDownDTO> empTypeList = populateDropdownsService .populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> templateList = populateDropdownsService .populateBrandingTemplateDropdown(employerInfoDTO.getFacilityId(),employerInfoDTO.getUserId());
		List<DropDownDTO> jbPostingTypeList = populateDropdownsService .populateJobPostingTypeDropdowns();
		List<DropDownDTO> jbOwnerList = populateDropdownsService .populateJobOwnersDropdown(employerInfoDTO.getFacilityId(), employerInfoDTO.getUserId(), employerInfoDTO.getRoleId());
		List<CountryDTO> countryList = populateDropdownsService.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		List<FromZipcodeDTO> zipCodeList = populateDropdownsService.getFromZipcodeList();
		//Populating Dropdowns
		model.addObject("stateList",stateList);
		model.addObject("empTypeList",empTypeList);
		model.addObject("countryList",countryList);
		model.addObject("templateList",templateList);
		model.addObject("jbOwnerList", jbOwnerList);
		model.addObject("zipCodeList",zipCodeList);
		model.addObject("jbPostingTypeList", jbPostingTypeList);
		//Populating Dropdowns
		
		return model;
	}
	/**
	 * This method is called to display resume list belonging to a logged in
	 * jobSeeker
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/manageJobPost", method = RequestMethod.GET)
	public String getJobPostDetails(HttpServletRequest request,
			HttpSession session, Model model, Map<String, Object> map) {
		List<JobPostDTO> postedJobList = new ArrayList<JobPostDTO>();
		if ((Integer) session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			postedJobList = employerJobPost
					.retrieveAllJobPost((Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
		}

		map.put("jobList", postedJobList);
		return "manageJobPosting";
	}
	/**
	 * This method is called to fetch the resume data to edit
	 * @param createResume
	 * @param resumeId
	 * @return model
	 */
	@RequestMapping(value = "/editJob", method = RequestMethod.GET)
	public ModelAndView editJob(JobPostForm jobPostform,
			@RequestParam("jobId") int jobId) {
		JobPostDTO jobPostDTO = employerJobPost.editJob(jobId);

		transformJobPost.transformJobPostDTOToCreateResume(jobPostform,
				jobPostDTO);
		ModelAndView model = new ModelAndView();
		model.addObject("jobPostForm", jobPostform);
		
		model.setViewName("postNewJobs");
		return model;
	}

}
