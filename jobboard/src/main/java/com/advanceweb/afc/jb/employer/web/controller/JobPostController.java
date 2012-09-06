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
import org.springframework.beans.factory.annotation.Value;
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
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * @Author : Prince Mathew
 * @Version: 1.0
 * @Created: Jul 18, 2012
 * @Purpose: This class will act as a Controller for the Post New Job
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

	@Value("${deleteFail}")
	private String deleteFailErrMsg;
	@Value("${repostFail}")
	private String repostFail;
	@Value("${deactivationFail}")
	private String deactivateMsg;
	@Value("${deleteSuccess}")
	private String deleteSuccessMsg;

	private static final String JOB_POST_FORM = "jobPostForm";
	private static final String POST_NEW_JOBS = "postNewJobs";
	private static final String SAVE_NEW_JOB = "/saveNewJob";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String FORWORD_MANAGE_JOBPOST = "forward:/employer/manageJobPost.html";
	private static final String UPDATE_JOBS = "/updateJobs";

	@RequestMapping(value = "/postNewJobs", method = RequestMethod.GET)
	public ModelAndView showPostJob(HttpSession session) {

		ModelAndView model = new ModelAndView();
		JobPostForm jobPostForm = new JobPostForm();
		
		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo((Integer) session.getAttribute("userId"),"facility_admin");
		List<DropDownDTO> empTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> templateList = populateDropdownsService
				.populateBrandingTemplateDropdown(
						employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId());
		List<DropDownDTO> jbPostingTypeList = populateDropdownsService
				.populateJobPostingTypeDropdowns();
		List<DropDownDTO> jbOwnerList = populateDropdownsService
				.populateJobOwnersDropdown(employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId(),
						employerInfoDTO.getRoleId());
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		List<FromZipcodeDTO> zipCodeList = populateDropdownsService
				.getFromZipcodeList();

		jobPostForm.setCompanyName(employerInfoDTO.getCustomerName());

		// The following commented section can be deleted.

		/*
		 * SecureRandom random=null; try { random =
		 * SecureRandom.getInstance("SHA1PRNG"); } catch
		 * (NoSuchAlgorithmException e) { e.printStackTrace(); } int myInt =
		 * random.nextInt(); jobPostForm.setCustomerNo("MMCN"+Math.abs(myInt));
		 * jobPostForm.setJobNumber("JT"+Math.abs(myInt));
		 */

		// Populating Dropdowns
		model.addObject("stateList", stateList);
		model.addObject("empTypeList", empTypeList);
		model.addObject("countryList", countryList);
		model.addObject("templateList", templateList);
		model.addObject("jbOwnerList", jbOwnerList);
		model.addObject("zipCodeList", zipCodeList);
		model.addObject("jbPostingTypeList", jbPostingTypeList);
		// Populating Dropdowns

		model.addObject(JOB_POST_FORM, jobPostForm);
		model.setViewName(POST_NEW_JOBS);

		return model;
	}

	/**
	 * This method is called to save the job details
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = SAVE_NEW_JOB, method = RequestMethod.POST, params = "PostNewJob")
	public ModelAndView savePostJob(JobPostForm form, BindingResult result,
			HttpSession session) {

		ModelAndView model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if (!StringUtils.isEmpty(errMessage)) {
			model = populateDropdowns(model);
			model.setViewName(POST_NEW_JOBS);
			model.addObject(ERROR_MESSAGE, errMessage);
			return model;
		}
		form.setJobStatus(MMJBCommonConstants.POST_NEW_JOB);
		JobPostDTO dto = transformJobPost.jobPostFormToJobPostDTO(form);
		dto.setbActive(true);
		dto.setFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID));
		employerJobPost.savePostJob(dto);
		model.setViewName(FORWORD_MANAGE_JOBPOST);
		return model;
	}

	/**
	 * This method is called to save the job details
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = SAVE_NEW_JOB, method = RequestMethod.POST, params = "ScheduleJob")
	public ModelAndView schedulePostJob(JobPostForm form, BindingResult result,
			HttpSession session) {

		ModelAndView model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if (!StringUtils.isEmpty(errMessage)) {
			model = populateDropdowns(model);
			model.setViewName(POST_NEW_JOBS);
			model.addObject(ERROR_MESSAGE, errMessage);
			return model;
		}
		form.setJobStatus(MMJBCommonConstants.POST_JOB_SCHEDULED);
		JobPostDTO dto = transformJobPost.jobPostFormToJobPostDTO(form);
		dto.setFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID));
		employerJobPost.savePostJob(dto);
		model.setViewName(FORWORD_MANAGE_JOBPOST);
		return model;
	}

	/**
	 * This method is called to save the job details
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = SAVE_NEW_JOB, method = RequestMethod.POST, params = "SaveAsDraft")
	public ModelAndView savePostJobAsDraft(JobPostForm form,
			BindingResult result, HttpSession session) {

		ModelAndView model = new ModelAndView();
		String errMessage = validateJobPostDetails(form);
		if (!StringUtils.isEmpty(errMessage)) {
			model = populateDropdowns(model);
			model.setViewName(POST_NEW_JOBS);
			model.addObject(ERROR_MESSAGE, errMessage);
			return model;
		}
		form.setJobStatus(MMJBCommonConstants.POST_JOB_DRAFT);
		JobPostDTO dto = transformJobPost.jobPostFormToJobPostDTO(form);
		dto.setFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID));
		employerJobPost.savePostJob(dto);
		model.setViewName(FORWORD_MANAGE_JOBPOST);
		return model;
	}

	/**
	 * This method is called to save the job details
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = SAVE_NEW_JOB, method = RequestMethod.POST, params = "Cancel")
	public ModelAndView cancelPostJob(JobPostForm form, BindingResult result) {

		return new ModelAndView("forward:/employer/employerDashBoard.html", "",
				"");
	}

	/**
	 * This method is called to validate the form
	 * 
	 * @param form
	 * @return
	 */
	private String validateJobPostDetails(JobPostForm form) {

		UrlValidator urlValidator = new UrlValidator();
		if (null != form) {
			if (StringUtils.isEmpty(form.getJobTitle())
					|| StringUtils.isEmpty(form.getJobCity())
					|| StringUtils.isEmpty(form.getJobZipCode())
					|| MMJBCommonConstants.ZERO.equals(form.getJobTitle())
					|| MMJBCommonConstants.ZERO.equals(form.getJobCountry())
					|| MMJBCommonConstants.ZERO.equals(form.getJobState())
					|| MMJBCommonConstants.ZERO.equals(form.getBrandTemplate())
					|| StringUtils.isEmpty(form.getJobDesc())
					|| (StringUtils.isEmpty(form.getApplyUrl())
							&& StringUtils.isEmpty(form.getAtsUrl()) && StringUtils
								.isEmpty(form.getApplyEmail()))) {
				return "Please fill the required fields";
			}

			// Validating URL
			if ((!StringUtils.isEmpty(form.getApplyUrl()) && !urlValidator
					.isValid(form.getApplyUrl()))
					|| (!StringUtils.isEmpty(form.getAtsUrl()) && !urlValidator
							.isValid(form.getAtsUrl()))
					|| (!StringUtils.isEmpty(form.getApplyEmail()) && !urlValidator
							.isValid(form.getApplyEmail()))) {

				return "Please enter valid URL";
			}
		}

		return null;
	}

	private ModelAndView populateDropdowns(ModelAndView model) {
		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(1,
				"facility_admin");
		List<DropDownDTO> empTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> templateList = populateDropdownsService
				.populateBrandingTemplateDropdown(
						employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId());
		List<DropDownDTO> jbPostingTypeList = populateDropdownsService
				.populateJobPostingTypeDropdowns();
		List<DropDownDTO> jbOwnerList = populateDropdownsService
				.populateJobOwnersDropdown(employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId(),
						employerInfoDTO.getRoleId());
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		List<FromZipcodeDTO> zipCodeList = populateDropdownsService
				.getFromZipcodeList();
		// Populating Dropdowns
		model.addObject("stateList", stateList);
		model.addObject("empTypeList", empTypeList);
		model.addObject("countryList", countryList);
		model.addObject("templateList", templateList);
		model.addObject("jbOwnerList", jbOwnerList);
		model.addObject("zipCodeList", zipCodeList);
		model.addObject("jbPostingTypeList", jbPostingTypeList);
		// Populating Dropdowns

		return model;
	}

	/**
	 * This method is called to fetch the job data to edit
	 * 
	 * @param request
	 * @param jobPostform
	 * @param jobId
	 * @return model
	 */
	@RequestMapping(value = "/editJob", method = RequestMethod.GET)
	public ModelAndView editJob(HttpServletRequest request,
			JobPostForm jobPostform, @RequestParam("jobId") int jobId) {
		JobPostDTO jobPostDTO = employerJobPost.editJob(jobId);
		String readOnly = request.getParameter("readOnly");
		if (null != readOnly && readOnly.equalsIgnoreCase("true")) {
			jobPostform.setReadOnly(true);
		}
		transformJobPost.transformJobPostDTOToForm(jobPostform, jobPostDTO);
		ModelAndView model = new ModelAndView();
		model.addObject(JOB_POST_FORM, jobPostform);

		model.setViewName(POST_NEW_JOBS);
		return model;
	}

	@RequestMapping(value = "/getCityList", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public List<String> getCityList(@RequestParam("term") String query) {

		List<String> countryList = populateDropdownsService
				.populateCityAutoComplete(query);

		return countryList;
	}

	@RequestMapping(value = "/getState")
	@ResponseBody
	public String getState(@RequestParam("city") String city) {

		String state = populateDropdownsService.populateStateAutoComplete(city);

		return state;
	}

	@RequestMapping(value = "/getPostalCodeAutoPopulation", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public List<String> getPostalCodeAutoPopulation(
			@RequestParam("term") String postalCode) {

		List<String> postalCodeList = populateDropdownsService
				.populatePostalCodeAutoComplete(postalCode);

		return postalCodeList;
	}

	@RequestMapping(value = "/getPostalCode")
	@ResponseBody
	public String getPostalCode(@RequestParam("city") String city,
			@RequestParam("state") String state) {

		String postalCode = populateDropdownsService.getPostalCode(city, state);

		return postalCode;
	}

	@RequestMapping(value = "/getCountry")
	@ResponseBody
	public String getCountry(@RequestParam("city") String city,
			@RequestParam("state") String state,
			@RequestParam("postalCode") String postalCode) {

		String country = populateDropdownsService.getCountry(city, state,
				postalCode);

		return country;
	}

	/**
	 * This method is called to display jobs list belonging to a logged in
	 * employer
	 * 
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
		String jobStatus = (null!=jobPostform.getStatusValue()?jobPostform.getStatusValue():null!=request.getParameter("jobStatus")?request.getParameter("jobStatus"):null);
		jobPostform.setStatusValue(jobStatus);
		DropDownDTO dto = new DropDownDTO();
		dto.setOptionId(MMJBCommonConstants.RELOCATE_YES);
		dto.setOptionName(MMJBCommonConstants.RELOCATE_YES);
		DropDownDTO downDTO = new DropDownDTO();
		downDTO.setOptionId(MMJBCommonConstants.RELOCATE_NO);
		downDTO.setOptionName(MMJBCommonConstants.RELOCATE_NO);
		
		List<DropDownDTO> autoRenewList = new ArrayList<DropDownDTO>();
		String next=request.getParameter("next");
		autoRenewList.add(dto);
		autoRenewList.add(downDTO);
		int page = 1;
		String displayRecordsPerPage = jobPostform.getNoOfPage();
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int recordsPerPage = 0;

		int noOfRecords = 0;
		if (null == displayRecordsPerPage) {
			displayRecordsPerPage = "20";
		}
		if ((Integer) session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			if (null == jobStatus || jobStatus.isEmpty() ) {

				recordsPerPage = Integer.parseInt(displayRecordsPerPage);
				postedJobList = employerJobPost.retrieveAllJobPost(
						(Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID),
						(page - 1) * recordsPerPage, recordsPerPage);

				noOfRecords = employerJobPost
						.getTotalNumberOfJobRecords((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
			} else {
				recordsPerPage = Integer.parseInt(displayRecordsPerPage);
				postedJobList = employerJobPost.retrieveAllJobByStatus(
						jobStatus, (Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID),
						(page - 1) * recordsPerPage, recordsPerPage);
				noOfRecords = employerJobPost
						.getTotalNumberOfJobRecordsByStatus();
			}
		}

		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(1,
				"facility_admin");
		List<DropDownDTO> templateList = populateDropdownsService
				.populateBrandingTemplateDropdown(
						employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId());
		jobPostform.setJobPostDTOList(postedJobList);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		if(null != next && !next.isEmpty()){
			
			jobPostform.setBeginVal(Integer.parseInt(next));
			page=Integer.parseInt(next);
		}
		else{
			jobPostform.setBeginVal((page/10)*10);
		}
		
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("begin",(jobPostform.getBeginVal()<=0?1:jobPostform.getBeginVal()));
		model.addObject(JOB_POST_FORM, jobPostform);
		model.addObject("templateList", templateList);
		model.addObject("autoRenewList", autoRenewList);
		model.addObject("jobStatusList",
				populateDropdownsService.getJobStatusList());
		model.setViewName("manageJobPosting");

		return model;
	}

	/**
	 * Method is used to delete selected job
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param jobPostform
	 * @return
	 */
	@RequestMapping(value = UPDATE_JOBS, method = RequestMethod.POST, params = "DELETE")
	public @ResponseBody
	ModelAndView deleteJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			JobPostForm jobPostform) {
		String selectedRows = jobPostform.getSelectedRow();
		int jobId = 0;
		String errorMsg = null;
		StringTokenizer tokenize = new StringTokenizer(selectedRows, ",");
		ModelAndView model = new ModelAndView();
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			boolean result = employerJobPost
					.deleteJob(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
			if (!result) {
				errorMsg = deleteFailErrMsg;
			}
		}
		if (null == errorMsg) {
			errorMsg = deleteSuccessMsg;
		}
		model.addObject(ERROR_MESSAGE, errorMsg);
		model.addObject(JOB_POST_FORM, jobPostform);
		model.setViewName(FORWORD_MANAGE_JOBPOST);
		return model;
	}

	/**
	 * Method is called to update the job details
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param jobPostform
	 * @param jobId
	 * @return
	 */
	@RequestMapping(value = UPDATE_JOBS, method = RequestMethod.POST)
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
		model.addObject(JOB_POST_FORM, jobPostform);
		model.setViewName("redirect:/employer/manageJobPost.html");
		return model;
	}

	/**
	 * This method is called to deactivate job(s)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param jobPostform
	 * @return
	 */
	@RequestMapping(value = "/updateJobs", method = RequestMethod.POST, params = "DEACTIVATED")
	public @ResponseBody
	ModelAndView deactivateJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			JobPostForm jobPostform) {
		String selectedRows = jobPostform.getSelectedRow();
		int jobId = 0;
		StringTokenizer tokenize = new StringTokenizer(selectedRows, ",");
		ModelAndView model = new ModelAndView();
		model.addObject(JOB_POST_FORM, jobPostform);
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			boolean result = employerJobPost
					.deactivateJob(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
			if (!result) {
				model.addObject(ERROR_MESSAGE, deactivateMsg);
			}
		}
		model.addObject(JOB_POST_FORM, jobPostform);

		model.setViewName(FORWORD_MANAGE_JOBPOST);
		return model;
	}

	/**
	 * This method is called to Repost job(s)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param jobPostform
	 * @return
	 */
	@RequestMapping(value = "/updateJobs", method = RequestMethod.POST, params = "REPOST")
	public @ResponseBody
	ModelAndView repostJobs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			JobPostForm jobPostform) {
		String selectedRows = jobPostform.getSelectedRow();
		int jobId = 0;
		StringTokenizer tokenize = new StringTokenizer(selectedRows, ",");
		ModelAndView model = new ModelAndView();
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			boolean result = employerJobPost
					.repostJob(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
			if (!result) {
				model.addObject(ERROR_MESSAGE, repostFail);
			}

		}
		model.addObject(JOB_POST_FORM, jobPostform);
		model.setViewName(FORWORD_MANAGE_JOBPOST);
		return model;
	}

	@RequestMapping(value = "/getLocations")
	@ResponseBody 
	public LocationDTO populateLocation(@RequestParam("zipCode") String zipCode) {
		  
	  LocationDTO dto = populateDropdownsService.populateLocation(zipCode);
	   
	  return dto;
	}
	
}
