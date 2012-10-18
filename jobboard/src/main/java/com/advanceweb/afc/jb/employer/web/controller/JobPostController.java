package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.UrlValidator;
import org.apache.log4j.Logger;
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
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.service.ManageFeatureEmployerProfile;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @Author : Prince Mathew
 * @Version: 1.0
 * @Created: Jul 18, 2012
 * @Purpose: This class will act as a Controller for the Post New Job
 */

@Controller
@RequestMapping("/employer")
public class JobPostController {

	private static final Logger LOGGER = Logger
			.getLogger(JobPostController.class);

	@Autowired
	private JobPostService employerJobPost;

	@Autowired
	private TransformJobPost transformJobPost;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private BrandingTemplateService brandingTemplateService;

	@Autowired
	private FacilityService facilityService;

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
	private static final String USER_ID = "userId";
	private static final String TEMPLATE_LIST = "templateList";
	private static final String FACILITY_ADMIN = "facility_admin";
	private static final String COMPANY_LIST = "companyList";
	@Autowired
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;

	@RequestMapping(value = "/postNewJobs", method = RequestMethod.GET)
	public ModelAndView showPostJob(HttpSession session) {
		int facilityId = 0;
		ModelAndView model = new ModelAndView();
		JobPostForm jobPostForm = new JobPostForm();
		List<DropDownDTO> templateList;
		facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(
				(Integer) session.getAttribute(USER_ID), FACILITY_ADMIN);
		List<DropDownDTO> empTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);

		// Template List will be populated later based on facility and job
		// posting package type is selected
		templateList = new ArrayList<DropDownDTO>();
		List<DropDownDTO> jbPostingTypeList = populateDropdownsService
				.populateJobPostingTypeDropdowns(employerInfoDTO
						.getFacilityId());
		List<DropDownDTO> jbOwnerList = populateDropdownsService
				.populateJobOwnersDropdown(employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId(),
						employerInfoDTO.getRoleId());
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		List<FromZipcodeDTO> zipCodeList = populateDropdownsService
				.getFromZipcodeList();

		// jobPostForm.setCompanyName(employerInfoDTO.getCustomerName());

		List<DropDownDTO> companyList = getCompanyList(facilityId);
		jobPostForm.setCustomerNo(employerInfoDTO.getCustomerNo());

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
		model.addObject(TEMPLATE_LIST, templateList);
		model.addObject("jbOwnerList", jbOwnerList);
		model.addObject("zipCodeList", zipCodeList);
		model.addObject("jbPostingTypeList", jbPostingTypeList);
		model.addObject(COMPANY_LIST, companyList);
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
			model = populateDropdowns(model, session);
			model.setViewName(POST_NEW_JOBS);
			model.addObject(ERROR_MESSAGE, errMessage);
			return model;
		}
		// Should be used while posting the job
		// Calling net suite to check whether the employer is featured or not
		// And to know, whether the employer is applicable for free job posting
		int nsCustomerID = manageFeatureEmployerProfile
				.getNSCustomerIDFromAdmFacility((Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID));

		UserDTO userDTO = manageFeatureEmployerProfile
				.getNSCustomerDetails(nsCustomerID);

		form.setXmlStartEndDateEnabled(MMUtils.compareDateRangeWithCurrentDate(
				userDTO.getFeaturedStartDate(), userDTO.getFeaturedEndDate()));

		// If free jobs is enabled then we should not decrease credits
		if (!form.isXmlStartEndDateEnabled()) {
			// Checking for whether the employer is having credits or not
			boolean bValidCredits = employerJobPost.validateAvailableCredits(
					Integer.valueOf(form.getJobPostingType()),
					(Integer) session
							.getAttribute(MMJBCommonConstants.FACILITY_ID));

			if (!bValidCredits) {
				model = populateDropdowns(model, session);
				model.setViewName(POST_NEW_JOBS);
				model.addObject(ERROR_MESSAGE,
						MMJBCommonConstants.DO_NOT_HAVE_CREDITS);
				return model;
			}
		}
		// Should be used while posting the job

		form.setJobStatus(MMJBCommonConstants.POST_NEW_JOB);
		JobPostDTO dto = transformJobPost.jobPostFormToJobPostDTO(form);
		if (form.getJobId() > 0) {
			dto.setJobId(form.getJobId());
		}
		dto.setbFeatured(userDTO.isFeatured());
		dto.setbActive(true);
		dto.setFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID));
		dto.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));
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
			model = populateDropdowns(model, session);
			model.setViewName(POST_NEW_JOBS);
			model.addObject(ERROR_MESSAGE, errMessage);
			return model;
		}

		form.setJobStatus(MMJBCommonConstants.POST_JOB_SCHEDULED);
		JobPostDTO dto = transformJobPost.jobPostFormToJobPostDTO(form);
		if (form.getJobId() > 0) {
			dto.setJobId(form.getJobId());
		}
		dto.setFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID));
		dto.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));
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
			model = populateDropdowns(model, session);
			model.setViewName(POST_NEW_JOBS);
			model.addObject(ERROR_MESSAGE, errMessage);
			return model;
		}
		form.setJobStatus(MMJBCommonConstants.POST_JOB_DRAFT);
		JobPostDTO dto = transformJobPost.jobPostFormToJobPostDTO(form);
		if (form.getJobId() > 0) {
			dto.setJobId(form.getJobId());
		}
		dto.setFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID));
		dto.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));
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
					|| MMJBCommonConstants.ZERO
							.equals(form.getJobPostingType())
					|| StringUtils.isEmpty(form.getJobDesc())
					|| (StringUtils.isEmpty(form.getApplyUrl())
							&& StringUtils.isEmpty(form.getAtsUrl()) && StringUtils
								.isEmpty(form.getApplyEmail()))) {

				return "Please fill the required fields";
			}

			// Validating URL
			if ((MMJBCommonConstants.APPLY_TO_URL.equals(form.getApplMethod()) || MMJBCommonConstants.APPLY_TO_ATS
					.equals(form.getApplMethod()))
					&& ((!StringUtils.isEmpty(form.getApplyUrl()) && !urlValidator
							.isValid(form.getApplyUrl())) || (!StringUtils
							.isEmpty(form.getAtsUrl()) && !urlValidator
							.isValid(form.getAtsUrl())))) {

				return "Please enter valid URL. (Eg: http://www.google.com)";
			}
			// Validating EMail
			if (MMJBCommonConstants.APPLY_TO_EMAIL.equals(form.getApplMethod())
					&& (!StringUtils.isEmpty(form.getApplyEmail()) && !validateEmailPattern(form
							.getApplyEmail()))) {

				return "Please enter valid Email address";
			}
		}

		return null;
	}

	private ModelAndView populateDropdowns(ModelAndView model,
			HttpSession session) {
		List<DropDownDTO> templateList;
		int facilityId = 0;
		facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		List<DropDownDTO> companyList = getCompanyList(facilityId);
		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(
				(Integer) session.getAttribute(USER_ID), FACILITY_ADMIN);
		List<DropDownDTO> empTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		if (brandingTemplateService.getBrandPurchaseInfo(employerInfoDTO
				.getFacilityId())) {
			templateList = populateDropdownsService
					.populateBrandingTemplateDropdown(
							employerInfoDTO.getFacilityId(),
							employerInfoDTO.getUserId());
		} else {
			templateList = new ArrayList<DropDownDTO>();
		}
		List<DropDownDTO> jbPostingTypeList = populateDropdownsService
				.populateJobPostingTypeDropdowns(employerInfoDTO
						.getFacilityId());
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
		model.addObject(TEMPLATE_LIST, templateList);
		model.addObject("jbOwnerList", jbOwnerList);
		model.addObject("zipCodeList", zipCodeList);
		model.addObject("jbPostingTypeList", jbPostingTypeList);
		model.addObject(COMPANY_LIST, companyList);
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
			HttpSession session, JobPostForm jobPostFormP,
			@RequestParam("jobId") int jobId) {
		JobPostForm jobPostForm = jobPostFormP;
		ModelAndView model = new ModelAndView();

		String readOnly = request.getParameter("readOnly");
		String jobStatus = request.getParameter("jobStatus");
		jobPostForm.setJobStatus(jobStatus);

		int facilityId = 0;
		facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		List<DropDownDTO> companyList = getCompanyList(facilityId);

		int jobPostType = employerJobPost
				.getinvDetIdByJobId(jobId, (Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID),
						(Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
		JobPostDTO jobPostDTO = employerJobPost.editJob(jobId, jobPostType);

		jobPostDTO.setJobPostingType(String.valueOf(jobPostType));
		jobPostForm = transformJobPost.transformJobPostDTOToForm(jobPostForm,
				jobPostDTO);

		// Populating Dropdowns
		List<DropDownDTO> jbPostingTypeList = populateDropDowns(session, jobId,
				jobPostForm, model, companyList);

		jobPostForm.setActiveInactive(false);
		if (null != readOnly) {
			if (readOnly.equalsIgnoreCase("true")) {
				jobPostForm.setReadOnly(true);
				model.setViewName(POST_NEW_JOBS);
			} else if (readOnly.equalsIgnoreCase("false")) {

				setReadOnlyFlag(jobPostForm, model, jobStatus, jobPostType,
						jbPostingTypeList,facilityId);
			}
		}
		model.addObject(JOB_POST_FORM, jobPostForm);
		return model;
	}

	/**
	 * @param jobPostForm
	 * @param model
	 * @param jobStatus
	 * @param jobPostType
	 * @param jbPostingTypeList
	 */
	private void setReadOnlyFlag(JobPostForm jobPostForm, ModelAndView model,
			String jobStatus, int jobPostType,
			List<DropDownDTO> jbPostingTypeList,int facilityId) {
		if (MMJBCommonConstants.POST_NEW_JOB.equals(jobStatus)
				|| MMJBCommonConstants.POST_JOB_INACTIVE.equals(jobStatus)) {
			jobPostForm.setReadOnly(true);
			jobPostForm.setEnableJobTitle(false);
			jobPostForm.setActiveInactive(true);
			List<DropDownDTO> jobPostTypeCombo = populateDropdownsService.populateJobPostingTypeDropdown(facilityId, jobPostType);
			boolean flag = false;
			//Need to add the comments here on each conditions
			for (DropDownDTO dropDownDto : jbPostingTypeList) {
				if (jobPostType == Integer.parseInt(dropDownDto.getOptionId()) ||
						jobPostTypeCombo.get(0).getOptionName().equals(dropDownDto.getOptionName())){
					if(jobPostTypeCombo.get(0).getOptionName().contains(MMJBCommonConstants.SLOT_POSTING)) {
						jobPostForm.setEnableJobTitle(true);
					}
				}
				if(!(jobPostType == Integer.parseInt(dropDownDto.getOptionId()))){
					if(jobPostTypeCombo.get(0).getOptionName().equals(dropDownDto.getOptionName())){
						if (MMJBCommonConstants.POST_NEW_JOB.equals(jobStatus)
								|| MMJBCommonConstants.POST_JOB_INACTIVE.equals(jobStatus) || 
								MMJBCommonConstants.POST_JOB_EXPIRED.equals(jobStatus)) {
							dropDownDto.setOptionId(jobPostTypeCombo.get(0).getOptionId());	
						}else if (MMJBCommonConstants.POST_JOB_DRAFT.equals(jobStatus)
								|| MMJBCommonConstants.POST_JOB_SCHEDULED.equals(jobStatus)) {
							jobPostForm.setJobPostingType(dropDownDto.getOptionId());
						}
					}
					else{
						flag = true;
					}
				}
				
			}
			if(flag){
				jbPostingTypeList.add(jobPostTypeCombo.get(0));
			}
			model.setViewName(POST_NEW_JOBS);
		} else if (MMJBCommonConstants.POST_JOB_DRAFT.equals(jobStatus)
				|| MMJBCommonConstants.POST_JOB_SCHEDULED.equals(jobStatus)) {
			jobPostForm.setReadOnly(false);
			model.setViewName(POST_NEW_JOBS);
		} else if (MMJBCommonConstants.POST_JOB_EXPIRED.equals(jobStatus)) {
			jobPostForm.setReadOnly(true);
			model.setViewName(POST_NEW_JOBS);
		}
	}

	/**
	 * @param session
	 * @param jobId
	 * @param jobPostForm
	 * @param model
	 * @param companyList
	 * @return
	 */
	private List<DropDownDTO> populateDropDowns(HttpSession session, int jobId,
			JobPostForm jobPostForm, ModelAndView model,
			List<DropDownDTO> companyList) {
		List<DropDownDTO> templateList;
		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(
				(Integer) session.getAttribute(USER_ID), FACILITY_ADMIN);
		List<DropDownDTO> empTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);

		if (brandingTemplateService.getBrandPurchaseInfo(employerInfoDTO
				.getFacilityId())) {
			templateList = populateDropdownsService
					.populateBrandingTemplateDropdown(
							employerInfoDTO.getFacilityId(),
							employerInfoDTO.getUserId());
		} else {
			templateList = new ArrayList<DropDownDTO>();
		}
		List<DropDownDTO> jbPostingTypeList = populateDropdownsService
				.populateJobPostingTypeDropdowns(employerInfoDTO
						.getFacilityId());
		List<DropDownDTO> jbOwnerList = populateDropdownsService
				.populateJobOwnersDropdown(employerInfoDTO.getFacilityId(),
						employerInfoDTO.getUserId(),
						employerInfoDTO.getRoleId());
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		List<FromZipcodeDTO> zipCodeList = populateDropdownsService
				.getFromZipcodeList();
		jobPostForm.setJobId(jobId);

		model.addObject("stateList", stateList);
		model.addObject("empTypeList", empTypeList);
		model.addObject("countryList", countryList);
		model.addObject(TEMPLATE_LIST, templateList);
		model.addObject("jbOwnerList", jbOwnerList);
		model.addObject("zipCodeList", zipCodeList);
		model.addObject("jbPostingTypeList", jbPostingTypeList);
		model.addObject(COMPANY_LIST, companyList);
		return jbPostingTypeList;
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

	public List<DropDownDTO> getCompanyList(int facilityId) {
		int facilityParentId = 0;
		try {
			facilityParentId = facilityService.getFacilityParent(facilityId);
		} catch (JobBoardServiceException e) {
			LOGGER.error(e);
		}

		List<DropDownDTO> companyList = populateDropdownsService
				.populateCompanyNames(facilityId, facilityParentId);

		return companyList;
	}

	@RequestMapping(value = "/getTemplate")
	@ResponseBody
	public List<DropDownDTO> getTemplate(
			@RequestParam("company") String company,
			@RequestParam("product") String product) {
		int productId = Integer.parseInt(product);

		if (brandingTemplateService.getBrandPackage(productId)) {
			return populateDropdownsService
					.populateTemplateAutoComplete(company);
		} else {
			return new ArrayList<DropDownDTO>();
		}
	}

	@RequestMapping(value = "/getFacilityTemplate")
	@ResponseBody
	public List<DropDownDTO> getFacilityTemplate(
			@RequestParam("isChecked") boolean isChecked,
			@RequestParam("company") String company,
			@RequestParam("product") String product, HttpSession session) {

		List<DropDownDTO> templateList;
		int productId = Integer.parseInt(product);
		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(
				(Integer) session.getAttribute(USER_ID), FACILITY_ADMIN);

		if (brandingTemplateService.getBrandPackage(productId) && isChecked) {
			templateList = populateDropdownsService
					.populateBrandingTemplateDropdown(
							employerInfoDTO.getFacilityId(),
							employerInfoDTO.getUserId());
		} else {
			templateList = getTemplate(company, product);
		}

		return templateList;
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
		List<DropDownDTO> templateList;
		List<JobPostDTO> postedJobList = new ArrayList<JobPostDTO>();
		int facilityId = 0;
		facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		List<DropDownDTO> companyList = getCompanyList(facilityId);
		String jobStatus = (null == jobPostform.getStatusValue() ? null == request
				.getParameter("jobStatus") ? null : request
				.getParameter("jobStatus") : jobPostform.getStatusValue());
		jobPostform.setStatusValue(jobStatus);
		DropDownDTO dto = new DropDownDTO();
		dto.setOptionId(MMJBCommonConstants.RELOCATE_YES);
		dto.setOptionName(MMJBCommonConstants.RELOCATE_YES);
		DropDownDTO downDTO = new DropDownDTO();
		downDTO.setOptionId(MMJBCommonConstants.RELOCATE_NO);
		downDTO.setOptionName(MMJBCommonConstants.RELOCATE_NO);

		List<DropDownDTO> autoRenewList = new ArrayList<DropDownDTO>();
		String next = request.getParameter("next");
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
			if (null == jobStatus || jobStatus.isEmpty()) {

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
		// to set the company Id

		EmployerInfoDTO employerInfoDTO = employerJobPost.getEmployerInfo(
				(Integer) session.getAttribute(USER_ID), FACILITY_ADMIN);

		if (brandingTemplateService.getBrandPurchaseInfo(employerInfoDTO
				.getFacilityId())) {
			templateList = populateDropdownsService
					.populateBrandingTemplateDropdown(
							employerInfoDTO.getFacilityId(),
							employerInfoDTO.getUserId());
		} else {
			templateList = new ArrayList<DropDownDTO>();
		}
		jobPostform.setJobPostDTOList(postedJobList);
		for (JobPostDTO jobPostDTO : postedJobList) {
			for (DropDownDTO dropDownDTO : companyList) {
				if (jobPostDTO.getCompanyName().equalsIgnoreCase(
						dropDownDTO.getOptionId())) {
					jobPostDTO.setCompanyName(dropDownDTO.getOptionName());
				}
			}
		}
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		if (null == next || !next.isEmpty()) {
			jobPostform.setBeginVal((page / 10) * 10);
		} else {
			jobPostform.setBeginVal(Integer.parseInt(next));
			page = Integer.parseInt(next);
		}

		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("begin", (jobPostform.getBeginVal() <= 0 ? 1
				: jobPostform.getBeginVal()));
		model.addObject(JOB_POST_FORM, jobPostform);
		model.addObject(TEMPLATE_LIST, templateList);
		model.addObject("autoRenewList", autoRenewList);
		model.addObject("jobStatusList",
				populateDropdownsService.getJobStatusList());

		model.addObject(COMPANY_LIST, companyList);
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
		int nsCustomerID = manageFeatureEmployerProfile
				.getNSCustomerIDFromAdmFacility((Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID));

		UserDTO userDTO = manageFeatureEmployerProfile
				.getNSCustomerDetails(nsCustomerID);
		jobPostform.setXmlStartEndDateEnabled(MMUtils
				.compareDateRangeWithCurrentDate(
						userDTO.getFeaturedStartDate(),
						userDTO.getFeaturedEndDate()));
		while (tokenize.hasMoreTokens()) {
			jobId = Integer.valueOf(tokenize.nextToken());
			List<JobPostDTO> jobPostDTOs = employerJobPost
					.retrieveAllJobPostByADvSearch(jobId);
			if (null != jobPostDTOs && jobPostDTOs.size() > 0) {
				JobPostDTO jobPostDTO = jobPostDTOs.get(0);
				if (jobPostDTO.getJobStatus() != MMJBCommonConstants.POST_JOB_EXPIRED
						&& jobPostDTO.getJobStatus() != MMJBCommonConstants.POST_JOB_INACTIVE) {
					model = populateDropdowns(model, session);
					model.setViewName(FORWORD_MANAGE_JOBPOST);
					model.addObject(ERROR_MESSAGE, repostFail);
					return model;
				}
			}
			int jobPostType = employerJobPost
					.getinvDetIdByJobId(jobId, (Integer) session
							.getAttribute(MMJBCommonConstants.FACILITY_ID),
							(Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID));
			// validate and check the credit Starts
			if (!jobPostform.isXmlStartEndDateEnabled()) {

				boolean bValidCredits = employerJobPost
						.validateAvailableCredits(
								jobPostType,
								(Integer) session
										.getAttribute(MMJBCommonConstants.FACILITY_ID));

				if (!bValidCredits) {
					model = populateDropdowns(model, session);
					model.setViewName(FORWORD_MANAGE_JOBPOST);
					model.addObject(ERROR_MESSAGE,
							MMJBCommonConstants.DO_NOT_HAVE_CREDITS_REPOST);
					return model;
				}
			}
			// validate and check the credit Ends
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

	/**
	 * Validating Email Pattern
	 * 
	 * @param emailId
	 * @return
	 */
	public boolean validateEmailPattern(String emailId) {
		Pattern pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailId);
		return matcher.matches();
	}

}
