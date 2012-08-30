package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;
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
	public ModelAndView savePostJob(JobPostForm form,BindingResult result, HttpSession session) {

		ModelAndView  model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if(!StringUtils.isEmpty(errMessage)){
			model=populateDropdowns(model);
			model.setViewName("postNewJobs");
			model.addObject("errorMessage", errMessage);
			return model;
		}
		form.setJobStatus(MMJBCommonConstants.POST_NEW_JOB);
		JobPostDTO dto=transformJobPost.jobPostFormToJobPostDTO(form);
		dto.setbActive(true);
		dto.setFacilityId((Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID));
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
	public ModelAndView schedulePostJob(JobPostForm form,BindingResult result, HttpSession session) {

		ModelAndView  model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if(!StringUtils.isEmpty(errMessage)){
			model=populateDropdowns(model);
			model.setViewName("postNewJobs");
			model.addObject("errorMessage", errMessage);
			return model;
		}
		form.setJobStatus(MMJBCommonConstants.POST_JOB_SCHEDULED);
		JobPostDTO dto=transformJobPost.jobPostFormToJobPostDTO(form);
		dto.setFacilityId((Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID));
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
	public ModelAndView savePostJobAsDraft(JobPostForm form,BindingResult result, HttpSession session) {

		ModelAndView  model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if(!StringUtils.isEmpty(errMessage)){
			model=populateDropdowns(model);
			model.setViewName("postNewJobs");
			model.addObject("errorMessage", errMessage);
			return model;
		}
		form.setJobStatus(MMJBCommonConstants.POST_JOB_DRAFT);
		JobPostDTO dto=transformJobPost.jobPostFormToJobPostDTO(form);
		dto.setFacilityId((Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID));
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
	 * This method is called to fetch the job data to edit
	 * @param request
	 * @param jobPostform
	 * @param jobId
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
	 * This method is called to display jobs list belonging to a logged in
	 * employer
	 * @param request
	 * @param session
	 * @param jobPostform
	 * @return
	 */
	@RequestMapping(value = "/manageJobPost")
	public ModelAndView getJobPostDetails(HttpServletRequest request,
			HttpSession session, JobPostForm jobPostform) {
		ModelAndView model = new ModelAndView();
		List<JobPostDTO> postedJobList = new ArrayList<JobPostDTO>();
		String jobStatus = jobPostform.getStatusValue();
	//	String jobStatus = request.getParameter("jobStatus");
		DropDownDTO dto = new DropDownDTO();
		dto.setOptionId(MMJBCommonConstants.RELOCATE_YES);
		dto.setOptionName(MMJBCommonConstants.RELOCATE_YES);
		
		DropDownDTO downDTO = new DropDownDTO();
		downDTO.setOptionId(MMJBCommonConstants.RELOCATE_NO);
		downDTO.setOptionName(MMJBCommonConstants.RELOCATE_NO);
		
		List<DropDownDTO> autoRenewList = new ArrayList<DropDownDTO>();
		autoRenewList.add(dto);
		autoRenewList.add(downDTO);
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
		jobPostform.setJobPostDTOList(postedJobList);
		
		
		model.addObject("jobPostForm", jobPostform);
		model.addObject("templateList", templateList);
		model.addObject("autoRenewList", autoRenewList);
		model.addObject("jobStatusList",
				populateDropdownsService.getJobStatusList());
		model.setViewName("manageJobPosting");

		return model;
	}
	/**
	 *  Method is used to delete selected job
	 * @param request
	 * @param response
	 * @param session
	 * @param jobPostform
	 * @return
	 */
	@RequestMapping(value = "/updateJobs", method = RequestMethod.POST,params="DELETE")
	public @ResponseBody
	ModelAndView deleteJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,JobPostForm jobPostform) {
		String selectedRows= jobPostform.getSelectedRow();
		int jobId=0;
		String errorMsg=null;
		StringTokenizer tokenize = new StringTokenizer(selectedRows, ","); 
		ModelAndView model = new ModelAndView();
		model.addObject("jobPostForm", jobPostform);
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			boolean result= employerJobPost
					.deleteJob(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
			if(result==false){
				errorMsg="Only inactive and expired  jobs can be deleted";
			}
		}
		if(null == errorMsg){
			errorMsg="Jobs deleted Successfully";
		}
	 return new ModelAndView("forward:/employer/manageJobPost.html");
	}
	/**
	 * Method is called to update the job details
	 * @param request
	 * @param response
	 * @param session
	 * @param jobPostform
	 * @param jobId
	 * @return
	 */
	@RequestMapping(value = "/updateJobs", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView updateJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			JobPostForm jobPostform, @RequestParam("jobId") int jobId) {
		String template = null;
		boolean autoRenew = false;
		ModelAndView model = new ModelAndView();
		for (JobPostDTO jobPostDTO : jobPostform.getJobPostDTOList()) {
			if (jobPostDTO.getJobId() == jobId) {
				template = jobPostDTO.getBrandTemplate();
				autoRenew = jobPostDTO.isAutoRenew();
			}
		}

		if (null != template) {
			employerJobPost
					.updateManageJob(autoRenew, template, jobId,
							(Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID));
		}
		model.addObject("jobPostForm", jobPostform);
		model.setViewName("redirect:/employer/manageJobPost.html");
		return model;
	}
	/**
	 * This method is called to deactivate job(s)
	 * @param request
	 * @param response
	 * @param session
	 * @param jobPostform
	 * @return
	 */
	@RequestMapping(value = "/updateJobs", method = RequestMethod.POST,params="DEACTIVATED")
	public @ResponseBody
	ModelAndView deactivateJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,JobPostForm jobPostform) {
		String selectedRows= jobPostform.getSelectedRow();
		int jobId=0;
		String errorMsg=null;
		StringTokenizer tokenize = new StringTokenizer(selectedRows, ","); 
		ModelAndView model = new ModelAndView();
		model.addObject("jobPostForm", jobPostform);
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			boolean result= employerJobPost
					.deactivateJob(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
			if(result==false){
				errorMsg="Please Select Only Active Jobs For Deactivation";
			}
		}
		model.addObject("jobPostForm", jobPostform);
		model.addObject("errorMessage", errorMsg);
		model.setViewName("forward:/employer/manageJobPost.html");
	 return model;
	}
	/**
	 *  This method is called to Repost job(s)
	 * @param request
	 * @param response
	 * @param session
	 * @param jobPostform
	 * @return
	 */
	@RequestMapping(value = "/updateJobs", method = RequestMethod.POST,params="REPOST")
	public @ResponseBody
	ModelAndView repostJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,JobPostForm jobPostform) {
		String selectedRows= jobPostform.getSelectedRow();
		int jobId=0;
		String errorMsg=null;
		StringTokenizer tokenize = new StringTokenizer(selectedRows, ","); 
		ModelAndView model = new ModelAndView();
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			boolean result= employerJobPost
					.repostJob(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));	
			System.out.println("HI" + result);
			if(result==false){
				errorMsg="Only inactive and expired  jobs can be reposted";
			}
			
		}
		model.addObject("jobPostForm", jobPostform);
		//model.addObject("valiedJobId",valiedJobId);
		model.addObject("errorMessage", errorMsg);
		model.setViewName("forward:/employer/manageJobPost.html");
	 return model;
	}
	
}
