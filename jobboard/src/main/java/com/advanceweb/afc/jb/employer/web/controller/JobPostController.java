package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
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
		model.setViewName("forward:/employer/manageJobPost.html");
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
		model.setViewName("forward:/employer/manageJobPost.html");
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
		model.setViewName("forward:/employer/manageJobPost.html");
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
					MMJBCommonConstants.ZERO.equals(form.getJobTitle())||
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
	 * This method is called to fetch the resume data to edit
	 * @param createResume
	 * @param resumeId
	 * @return model
	 */
	@RequestMapping(value = "/editJob", method = RequestMethod.GET)
	public ModelAndView editJob(HttpServletRequest request,JobPostForm jobPostform,
			@RequestParam("jobId") int jobId) {
		JobPostDTO jobPostDTO = employerJobPost.editJob(jobId);
		String readOnly=request.getParameter("readOnly");
		if(null !=readOnly && readOnly.equalsIgnoreCase("true")){
			jobPostform.setReadOnly(true);
		}
		transformJobPost.transformJobPostDTOToForm(jobPostform,
				jobPostDTO);
		ModelAndView model = new ModelAndView();
		model.addObject("jobPostForm", jobPostform);
		
		model.setViewName("postNewJobs");
		return model;
	}

	@RequestMapping(value = "/getCityList", method = RequestMethod.GET, headers="Accept=*/*")
	@ResponseBody 
	public List<String> getCityList(@RequestParam("term") String query) {
		  
	  List<String > countryList = populateDropdownsService.populateCityAutoComplete(query);
	   
	  return countryList;
	}
	
	@RequestMapping(value = "/getState")
	@ResponseBody 
	public String getState(@RequestParam("city") String city) {
		  
	  String state = populateDropdownsService.populateStateAutoComplete(city);
	   
	  return state;
	}
	
	@RequestMapping(value = "/getPostalCodeAutoPopulation")
	@ResponseBody 
	public List<String> getPostalCodeAutoPopulation(@RequestParam("postalCode") String postalCode) {
		  
	  List<String> postalCodeList = populateDropdownsService.populatePostalCodeAutoComplete(postalCode);
	   
	  return postalCodeList;
	}
	
	@RequestMapping(value = "/getPostalCode")
	@ResponseBody 
	public String getPostalCode(@RequestParam("city") String city, @RequestParam("state") String state) {
		  
	  String postalCode = populateDropdownsService.getPostalCode(city,state);
	   
	  return postalCode;
	}
	
	@RequestMapping(value = "/getCountry")
	@ResponseBody 
	public String getCountry(@RequestParam("city") String city, @RequestParam("state") String state, 
			@RequestParam("postalCode") String postalCode) {
		  
	  String country = populateDropdownsService.getCountry(city,state, postalCode);
	   
	  return country;
	}

	/**
	 * This method is called to display resume list belonging to a logged in
	 * jobSeeker
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/manageJobPost")
	public ModelAndView getJobPostDetails(HttpServletRequest request,
			HttpSession session, JobPostForm jobPostform) {
		ModelAndView model = new ModelAndView();
		List<JobPostDTO> postedJobList = new ArrayList<JobPostDTO>();
		String jobStatus = request.getParameter("jobStatus");
		if ((Integer) session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			if (null == jobStatus || jobStatus.isEmpty()) {
				postedJobList = employerJobPost
						.retrieveAllJobPost((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
			} else {
				postedJobList = employerJobPost.retrieveAllJobByStatus(
						jobStatus, (Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
			}
		}

		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(1,
				"facility_admin");
		List<DropDownDTO> templateList = populateDropdownsService
				.populateBrandingTemplateDropdown(
						employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId());
		// jobPostform.setAutoRenew(autoRenew)
		jobPostform.setJobPostDTOList(postedJobList);
		model.addObject("jobPostForm", jobPostform);
		model.addObject("templateList", templateList);
		// model.addObject("jobStatusList",populateDropdownsService.getJobStatusList());
		model.setViewName("manageJobPosting");
		/* map.put("jobList", postedJobList); */

		return model;
	}
	/**
	 * This method is called to delete job(s)
	 * 
	 * @param resumeId
	 * @return deleteStatusJson
	 */
	@RequestMapping(value = "/updateJob", method = RequestMethod.POST,params="DELETE")
	public @ResponseBody
	ModelAndView deleteJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,JobPostForm jobPostform) {
		String selectedRows= jobPostform.getSelectedRow();
		int jobId=0;
		StringTokenizer tokenize = new StringTokenizer(selectedRows, ","); 
		ModelAndView model = new ModelAndView();
		model.addObject("jobPostForm", jobPostform);
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			 employerJobPost
					.deleteJob(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));			
		}
	 return new ModelAndView("forward:/employer/manageJobPost.html");
	}
	/**
	 * This method is called to delete job(s)
	 * 
	 * @param resumeId
	 * @return deleteStatusJson
	 */
	@RequestMapping(value = "/updateJobs", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView updateJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			JobPostForm jobPostform, @RequestParam("jobId") int jobId) {
         String template=jobPostform.getBrandTemplate();
        template= template.replace(",0", "");
        template= template.replace("0,", "");
		employerJobPost.updateManageJob(jobPostform.isAutoRenew(),
				template, jobId,
				(Integer) session.getAttribute(MMJBCommonConstants.USER_ID));

		return new ModelAndView("forward:/employer/manageJobPost.html");
	}
	/**
	 * This method is called to deactivate job(s)
	 * 
	 * @param resumeId
	 * @return deleteStatusJson
	 */
	@RequestMapping(value = "/updateJob", method = RequestMethod.POST,params="DEACTIVATED")
	public @ResponseBody
	ModelAndView deactivateJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,JobPostForm jobPostform) {
		String selectedRows= jobPostform.getSelectedRow();
		int jobId=0;
		StringTokenizer tokenize = new StringTokenizer(selectedRows, ","); 
		ModelAndView model = new ModelAndView();
		model.addObject("jobPostForm", jobPostform);
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			 employerJobPost
					.deactivateJob(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));			
		}
	 return new ModelAndView("forward:/employer/manageJobPost.html");
	}
	/**
	 * This method is called to Repost job(s)
	 * 
	 * @param resumeId
	 * @return deleteStatusJson
	 */
	@RequestMapping(value = "/updateJob", method = RequestMethod.POST,params="REPOST")
	public @ResponseBody
	ModelAndView repostJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,JobPostForm jobPostform) {
		String selectedRows= jobPostform.getSelectedRow();
		int jobId=0;
		StringTokenizer tokenize = new StringTokenizer(selectedRows, ","); 
		ModelAndView model = new ModelAndView();
		model.addObject("jobPostForm", jobPostform);
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			 employerJobPost
					.repostJob(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));			
		}
	 return new ModelAndView("forward:/employer/manageJobPost.html");
	}
	
}
