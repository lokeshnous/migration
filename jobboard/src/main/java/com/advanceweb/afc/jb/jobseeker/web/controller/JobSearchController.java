package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;
import com.advanceweb.afc.jb.employer.web.controller.BrandingTemplateForm;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.home.web.controller.ClickController;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;
import com.advanceweb.afc.jb.login.web.controller.LoginForm;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.service.JSONConverterService;
import com.advanceweb.afc.jb.search.service.JobSearchService;

/**
 * <code>JobSearchController</code>This controller belongs to all searched jobs.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */

@Controller
@RequestMapping("/jobsearch")
public class JobSearchController {

	private static final String CURRENT_URL = "currentUrl";

	private static final Logger LOGGER = Logger
			.getLogger(JobSearchController.class);

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private JSONConverterService jsonConverterService;

	@Autowired
	private JobSeekerJobDetailService jobSeekerService;

	@Autowired
	private JobSearchService jobSearchService;

	@Autowired
	private LookupService lookupService;

	@Autowired
	private CheckSessionMap checkSessionMap;
	
	@Autowired
	private BrandingTemplateService brandingTemplateService;

	@Value("${jobSearchValidateKeyword}")
	private String jobSearchValidateKeyword;

	@Value("${jobSearchValidateCity}")
	private String jobSearchValidateCity;

	@Value("${navigationPath}")
	private String navigationPath;
	
	@Value("${jobseekerJobApplicationSub}")
	private String jobseekerJobApplicationSub;

	@Value("${jobseekerJobApplicationBody}")
	private String jobseekerJobApplicationBody;

	@Value("${employeJobApplicationSub}")
	private String employeJobApplicationSub;

	@Value("${employeJobApplicationBody}")
	private String employeJobApplicationBody;

	@Value("${saveJobsLimit}")
	private String saveJobsLimit;

	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	@Value("${jobseekerPageExtention}")
	private String jobseekerPageExtention;

	@Value("${employerPageExtention}")
	private String employerPageExtention;

	@Value("${defaultResumeExtension}")
	private String defaultResumeExtension;

	@Autowired
	private ResumeService resumeService;

	@Value("${saveThisJobSuccessMsg}")
	private String saveThisJobSuccessMsg;

	@Value("${saveThisJobErrMsg}")
	private String saveThisJobErrMsg;

	@Value("${applyJobSuccessMsg}")
	private String applyJobSuccessMsg;

	@Value("${applyJobErrMsg}")
	private String applyJobErrMsg;
	
	@Value("${resumeNotFoundMsg}")
	private String resumeNotFoundMsg;

	@Value("${ajaxMsg}")
	private String ajaxMsg;

	/*@Value("${invalidemail}")
	private String invalidemail;*/

	@Value("${ajaxNavigationPath}")
	private String ajaxNavigationPath;

	/*@Value("${notempty}")
	private String notempty;*/

	@Value("${jobseekerSuggestFrdSub}")
	private String jobseekerSuggestFrdSub;

	@SuppressWarnings("unused")
	@Value("${jobseekerSuggestFrdBody}")
	private String jobseekerSuggestFrdBody;

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	@Autowired
	ClickController clickController;
	
	/**
	 * The view action is called to get the job details by jobId and navigate to
	 * job view details page.
	 * 
	 * @param jobId
	 * @param request
	 * @return : modelandview for respected Jobid
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewJobDetails")
	public ModelAndView viewJobDetails(@RequestParam("id") int jobId,
			Map<String, Object> model, HttpServletRequest request, Model model1,
			HttpSession session,HttpServletResponse response, @RequestParam(CURRENT_URL) String currentUrl,
			@RequestParam(value="clickType",required=false) String clickType) {
		ModelAndView modelView = new ModelAndView();
		
		try {
			
			if(clickType.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_VIEW)){
				clickController.getclickevent(jobId, clickType, request, response, model1);
			}

			Map<String, String> sessionMap = null;
			if (session.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP) != null) {
				sessionMap = (Map<String, String>) session
						.getAttribute(SearchParamDTO.SEARCH_SESSION_MAP);
			}
			// View the job with template
			SearchedJobDTO jobDTO = jobSearchService.viewJobDetails(jobId);
			if(MMJBCommonConstants.ZERO_INT != jobDTO.getTemplateId())
			{
				jobDTO = checkBrand(jobDTO);
			}
			model.put("jobDetail", jobDTO);
			model.put("isFeatureEmployer", jobDTO.isFeatureEmployer());
			model.put("returnResults", currentUrl);
			if(sessionMap != null){
				sessionMap.put(MMJBCommonConstants.AUTOLOAD, String.valueOf(true));
				session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, sessionMap);
			}


			if(MMJBCommonConstants.ZERO_INT != jobDTO.getTemplateId())
			{
				modelView.setViewName("jobseekerJobDetailsTemplate");
			}
			else
			{
				modelView.setViewName("jobseekerJobDetails");
			}
			
		} catch (Exception e) {
			// loggers call
			LOGGER.info("ERROR"+e);
		}
		
		return modelView;
	}
	
	@RequestMapping(value = "/clicksTrack")
	public ModelAndView trackClicks(HttpServletResponse response,
			HttpServletRequest request, Model model,@RequestParam("id") int jobId,
			@RequestParam("clickType") String clickType){
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(clickType.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_CLICK)){
			clickController.getclickevent(jobId, clickType, request, response, model);
		}
		
		return modelAndView;
	}

	/**
	 * The apply for job action is called as per the conditions and getting
	 * saved in DB. Check for login , navigate to login page if necessary login
	 * by ADVACNE Guest navigate to Anonymous User Form apply for job or
	 * navigate to employer web page to apply job
	 * 
	 * @param form
	 * @param jobId
	 * @param map
	 * @param session
	 * @param request
	 * @param currentUrl
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyJob", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject applyJob(@Valid ApplyJobForm form, Map<String, Object> map,
			@RequestParam String userID, @RequestParam("id") int jobId,
			@RequestParam(CURRENT_URL) String currentUrl, HttpServletResponse response,
			Model model,
			@RequestParam("clickType") String clickType, HttpSession session,
			HttpServletRequest request) {
		
		JSONObject jsonObject = new JSONObject();
		
		if(clickType.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_APPLY)){
			clickController.getclickevent(jobId, clickType, request, response, model);
		}
		
		form.setJobID(jobId);
		int userId = 0;
		String userName = null;
		String userEmail = null;
		
		if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			userName = (String) session
					.getAttribute(MMJBCommonConstants.USER_NAME);
			userEmail = (String) session
					.getAttribute(MMJBCommonConstants.USER_EMAIL);
		}
		
		form.setUseremail(userEmail);
		
		try {
			// Get the Job details
			SearchedJobDTO searchedJobDTO = jobSearchService
					.viewJobDetails(form.getJobID());

			// apply job by apply type like by ATS, Website or Email
			JobApplyTypeDTO jobApplyTypeDTO = jobSearchService
					.applyJobDetails(form.getJobID());
		
				if (jobApplyTypeDTO != null && (jobApplyTypeDTO.getApplyMethod().equalsIgnoreCase(
						MMJBCommonConstants.APPLY_TO_ATS)
						|| jobApplyTypeDTO.getApplyMethod().equalsIgnoreCase(
								MMJBCommonConstants.APPLY_TO_URL))) {
					jsonObject.put("applyMethod",
							jobApplyTypeDTO.getApplyMethod());
					jsonObject.put("applyLink", jobApplyTypeDTO.getApplyLink());
					return jsonObject;
				}

			// Check for job seeker login
			if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
				map.put("loginForm", new LoginForm());
				jsonObject.put(ajaxNavigationPath, navigationPath
						+ dothtmlExtention + jobseekerPageExtention);
				session.setAttribute("jobId", jobId);
				session.setAttribute(CURRENT_URL, currentUrl);
				return jsonObject;
			}
			// Validate if job is already applied
			AppliedJobDTO appliedJobDTO = jobSearchService
					.fetchSavedOrAppliedJob(searchedJobDTO, userId);
			if (appliedJobDTO != null && appliedJobDTO.getAppliedDt() != null) {
				applyJobErrMsg = applyJobErrMsg.replace("?",
						appliedJobDTO.getAppliedDt());
				jsonObject.put(ajaxMsg, applyJobErrMsg);
				return jsonObject;
			}

			// Send mail to Employer regarding job application
			String loginPath = navigationPath.substring(2);
			String jonseekerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + jobseekerPageExtention;
			String employerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + employerPageExtention;
			// Fetch the public resume
			List<String> attachmentpaths = fetchPublicVisibleResume(userId);
			if(attachmentpaths == null){
				jsonObject.put(ajaxMsg, resumeNotFoundMsg);
				return jsonObject;
			}

			EmailDTO employerEmailDTO = new EmailDTO();
			employerEmailDTO.setFromAddress(advanceWebAddress);
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress(
					searchedJobDTO.getEmployerEmailAddress());
			// TODO: Remove hard codes of mails
//			 employerToAddress[0] = new InternetAddress(
//			 "pramodap@nousinfo.com");
			employerEmailDTO.setToAddress(employerToAddress);
			String employerMailSub = employeJobApplicationSub.replace(
					"?jobseekername", userName);
			employerEmailDTO.setSubject(employerMailSub);
			String employerMailBody = employeJobApplicationBody.replace(
					"?empDashboardLink", employerloginUrl);
			employerMailBody = employerMailBody.replace("?jobseekername",
					userName);
			employerEmailDTO.setBody(employerMailBody);
			employerEmailDTO.setHtmlFormat(true);
			employerEmailDTO.setAttachmentPaths(attachmentpaths);
			emailService.sendEmail(employerEmailDTO);
			LOGGER.info("Mail sent to employer");

			// Send confirmation mail to job seeker regarding job application
			EmailDTO jobSeekerEmailDTO = new EmailDTO();
			jobSeekerEmailDTO.setFromAddress(advanceWebAddress);
			InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
			jobSeekerToAddress[0] = new InternetAddress(form.getUseremail());
			// TODO: Remove hard codes of mails
			// jobSeekerToAddress[0] = new
			// InternetAddress("pramodap@nousinfo.com");
			jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
			String jobseekerMailSub = jobseekerJobApplicationSub.replace(
					"?companyname", searchedJobDTO.getCompanyName());
			jobSeekerEmailDTO.setSubject(jobseekerMailSub);
			String jobseekerMailBody = jobseekerJobApplicationBody.replace(
					"?jsdashboardLink", jonseekerloginUrl);
			jobseekerMailBody = jobseekerMailBody.replace("?companyname",
					searchedJobDTO.getCompanyName());
			jobSeekerEmailDTO.setBody(jobseekerMailBody);
			jobSeekerEmailDTO.setHtmlFormat(true);
			emailService.sendEmail(jobSeekerEmailDTO);
			LOGGER.info("Mail sent to jobseeker");

			// save the applied job in DB
			Date currentDate = new Date();
			AppliedJobDTO applyJobDTO = null;
			if (appliedJobDTO == null || appliedJobDTO.getAppliedDt() != null) {
				applyJobDTO = new AppliedJobDTO();
				JobPostDTO jpJob = new JobPostDTO();
				jpJob.setJobId(form.getJobID());
				applyJobDTO.setJpJob(jpJob);
				applyJobDTO.setUserId(userId);
				applyJobDTO.setJobTitle(searchedJobDTO.getJobTitle());
				applyJobDTO.setFacilityName(searchedJobDTO.getCompanyName());
				applyJobDTO.setCreateDt(currentDate.toString());
				applyJobDTO.setAppliedDt(currentDate.toString());
				applyJobDTO.setDeleteDt(null);
				jobSearchService.saveOrApplyJob(applyJobDTO);
			} else {
				applyJobDTO = appliedJobDTO;
				applyJobDTO.setAppliedDt(currentDate.toString());
				jobSearchService.updateSaveOrApplyJob(applyJobDTO);
			}
			jsonObject.put(ajaxMsg, applyJobSuccessMsg);
		} catch (Exception e) {
			// loggers call
			LOGGER.info("applyJob ERROR");
		}
		return jsonObject;
	}

	
	private List<String> fetchPublicVisibleResume(int userId) {
		List<String> attachmentpaths = null;
		try {
			ResumeDTO resumeDTO = resumeService
					.fetchPublicResumeByUserId(userId);
			if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER
					.equalsIgnoreCase(resumeDTO.getResumeType())) {
				// TODO: Need to clarify
				
			} else if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE
					.equalsIgnoreCase(resumeDTO.getResumeType())) {
				try {
					// Create temp file.
					File temp = File.createTempFile(
							resumeDTO.getResumeName(),
							defaultResumeExtension);
					File newFile = new File(temp.getParent() + "\\"
							+ resumeDTO.getResumeName()
							+ defaultResumeExtension);
					// Rename
					newFile.deleteOnExit();
					if (temp.renameTo(newFile)) {
						LOGGER.info("File has been renamed.");
					}
					temp.deleteOnExit();

					// Write to temp file
					BufferedWriter out = new BufferedWriter(new FileWriter(
							newFile));
					out.write(resumeDTO.getResumeText());
					out.close();
					resumeDTO.setFilePath(newFile.getAbsolutePath());
				} catch (IOException e) {
					LOGGER.info("Copy Paste resume error");
				}
			}else if (MMJBCommonConstants.RESUME_TYPE_UPLOAD
					.equalsIgnoreCase(resumeDTO.getResumeType())) {
				// TODO: Need to clarify
				
			}
			if (resumeDTO.getFilePath() != null) {
				attachmentpaths = new ArrayList<String>();
				attachmentpaths.add(resumeDTO.getFilePath());
			}
			//employerEmailDTO.setAttachmentPaths(attachmentpaths);
		} catch (Exception e) {
			LOGGER.info("Resume not found");
		}
		return attachmentpaths;
	}

	/**
	 * This method is called to forward to job search page
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/findJobPage", method = RequestMethod.GET)
	public ModelAndView findJobPage(Map<String, JobSearchResultForm> model,
			HttpSession session) {
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();

		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);

		if (!sessionMap.isEmpty()) {

			String searchType = sessionMap.get(MMJBCommonConstants.SEARCH_TYPE);
			String radius = MMJBCommonConstants.EMPTY;
			String cityState = MMJBCommonConstants.EMPTY;
			String keywords = MMJBCommonConstants.EMPTY;
			String saveSearchName = MMJBCommonConstants.EMPTY;
			keywords = sessionMap.get(SearchParamDTO.KEYWORDS);
			cityState = sessionMap.get(SearchParamDTO.CITY_STATE);
			radius = sessionMap.get(SearchParamDTO.RADIUS);
			saveSearchName = sessionMap
					.get(MMJBCommonConstants.SAVE_SEARCH_NAME);
			jobSearchResultForm.setSaveSearchName(saveSearchName);
			jobSearchResultForm.setSearchtype(searchType);
			jobSearchResultForm.setKeywords(keywords);
			jobSearchResultForm.setCityState(cityState);
			jobSearchResultForm.setRadius(radius);
			jobSearchResultForm.setAutoload(true);

			LOGGER.info("Removing keywords, city,state, autoload from session....");

			session.removeAttribute(sessionMap.remove(SearchParamDTO.KEYWORDS));
			session.removeAttribute(sessionMap
					.remove(SearchParamDTO.CITY_STATE));
			session.removeAttribute(sessionMap.remove(SearchParamDTO.RADIUS));
			session.removeAttribute(sessionMap
					.remove(MMJBCommonConstants.AUTOLOAD));

		}

		model.put("jobSearchResultForm", jobSearchResultForm);
		return new ModelAndView("jobboardsearchresults");
	}

	/**
	 * This method will be used for doing Job search and Return a JSON Object
	 * which will later be parsed at the UI end and all the results will be
	 * displayed
	 * 
	 * @param jobSearchResultForm
	 * @param result
	 * @param model
	 * @return JSON Object
	 * 
	 */
	@RequestMapping(value = "/searchJob", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject searchJob(HttpSession session,
			JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, JSONObject> modelMap, HttpServletRequest request) {
		 JSONObject jsonObject = new JSONObject();
		// TODO :Need to Use sessionMap
		LOGGER.info("Removing from session....");
		session.removeAttribute(MMJBCommonConstants.SEARCH_RESULTS_LIST);
		session.removeAttribute(MMJBCommonConstants.NO_OF_PAGES);
		session.removeAttribute(MMJBCommonConstants.CURRENT_PAGE);
		session.removeAttribute(MMJBCommonConstants.RECORDS_PER_PAGE);
		session.removeAttribute(MMJBCommonConstants.RECORDS_COUNT);
		session.removeAttribute(MMJBCommonConstants.TOTAL_NO_RECORDS);
		session.removeAttribute(MMJBCommonConstants.START_ROW);
		session.removeAttribute(MMJBCommonConstants.END_ROW);
		session.removeAttribute(MMJBCommonConstants.BEGIN_VAL);
		session.removeAttribute(MMJBCommonConstants.BEGIN);
		session.removeAttribute("filterVals");
		session.removeAttribute("filterVal");
		
		JobSearchResultDTO jobSearchResultDTO = null;
		// Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, String> sessionMap = checkSessionMap
				.getSearchSessionMap(session);
		String searchName = MMJBCommonConstants.EMPTY;
		
		if (StringUtils.isEmpty(jobSearchResultForm.getKeywords().trim())) {
			jsonObject.put(ajaxMsg, jobSearchValidateKeyword);
			return jsonObject;
		} else if ((!jobSearchResultForm.getRadius().equalsIgnoreCase("0"))
				&& StringUtils.isEmpty(jobSearchResultForm.getCityState()
						.trim())) {
			jsonObject.put(ajaxMsg, jobSearchValidateCity);
			return jsonObject;
		}

		// Check if city state and radius field is not empty to check for
		// LOCATION search
		if (StringUtils.isEmpty(jobSearchResultForm.getCityState().trim())) {
			if (!StringUtils.isEmpty(jobSearchResultForm.getKeywords().trim())) {
				searchName = MMJBCommonConstants.KEYWORD_SEARCH;
			}
		} else {
			searchName = MMJBCommonConstants.LOCATION_SEARCH;
		}
		int searchSeq = MMJBCommonConstants.ZERO_INT;
		String sessionId = null;
		if (session != null) {
			sessionId = session.getId();
			// Setting the values into sessionMap
			sessionMap = setValuesToSessionMap(sessionMap, searchSeq,
					jobSearchResultForm);
			session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, sessionMap);

		}

		// Putting all the parameters coming from the UI into a Map for further
		// processing.
		Map<String, String> paramMap = getParameterMap(jobSearchResultForm,
				sessionId, searchName, sessionMap);

		int page = 1;
		int displayRecordsPerPage = 0;
		if (request.getParameter(MMJBCommonConstants.PAGE) != null) {
			page = Integer.parseInt(request.getParameter(MMJBCommonConstants.PAGE));
		}
		if (request.getParameter(MMJBCommonConstants.RECORDS_PER_PAGE) != null) {
			displayRecordsPerPage = Integer.parseInt(request
					.getParameter(MMJBCommonConstants.RECORDS_PER_PAGE));
		}
		String next = request.getParameter(MMJBCommonConstants.NEXT);
		int recordsPerPage = 0;

		int noOfRecords = 0;
		if (0 == displayRecordsPerPage) {
			displayRecordsPerPage = MMJBCommonConstants.DEFAULT_PAGE_SIZE;
		}

		try {
			recordsPerPage = displayRecordsPerPage;			
			long start = (page - 1) * recordsPerPage;			
			long rows = recordsPerPage;
			// Calling the jobSearch() of Service layer from getting the object
			// of JobSearchResultDTO
			jobSearchResultDTO = jobSearchService.jobSearch(searchName,
					paramMap, start, rows);

			session.setAttribute(MMJBCommonConstants.START_ROW, ((page - 1) * recordsPerPage + 1));
			session.setAttribute(MMJBCommonConstants.END_ROW,
					(((page - 1) * recordsPerPage) + jobSearchResultDTO
							.getResultList().size()));

			noOfRecords = (int) jobSearchResultDTO.getResultCount();

			// TODO: Advertise part is not done
			// session.setAttribute("AddsPerPage",
			// MMJBCommonConstants.ADDS_PER_PAGE);
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		int beginVal = 0;
		if (null != next && !next.isEmpty()) {
			beginVal = Integer.parseInt(next);
			page = Integer.parseInt(next);
		} else {
			beginVal = (page / 10) * 10;
		}
		JSONObject jobSrchJsonObj = null;
		if (jobSearchResultDTO != null) {
			// Calling the service layer for converting the JobSearchResultDTO
			// object into JSON Object
			jobSrchJsonObj = jsonConverterService
					.convertToJSON(jobSearchResultDTO);
		}
		// Set the Filter values for search table
		List<DropDownDTO> filterVals = new ArrayList<DropDownDTO>();
		for (int val : MMJBCommonConstants.FILTER_VALS) {
			DropDownDTO downDTO = new DropDownDTO();
			downDTO.setOptionId(String.valueOf(val));
			downDTO.setOptionName(val+" Miles");			
			filterVals.add(downDTO);
		}
		session.setAttribute("filterVals",
				filterVals);
		session.setAttribute("filterVal",
				displayRecordsPerPage+"");
		//TODO: Need to use session Map
		session.setAttribute(MMJBCommonConstants.SEARCH_RESULTS_LIST,
				jobSrchJsonObj.get(MMJBCommonConstants.JSON_ROWS));
		session.setAttribute(MMJBCommonConstants.RECORDS_COUNT,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		session.setAttribute(MMJBCommonConstants.BEGIN_VAL, beginVal);
		session.setAttribute(MMJBCommonConstants.NO_OF_PAGES, noOfPages);
		session.setAttribute(MMJBCommonConstants.CURRENT_PAGE, page);
		session.setAttribute(MMJBCommonConstants.BEGIN, (beginVal <= 0 ? 1 : beginVal));
		jobSrchJsonObj.put(MMJBCommonConstants.TOTAL_NO_RECORDS,
				jobSrchJsonObj.get(MMJBCommonConstants.TOTAL_NO_RECORDS));
		return jobSrchJsonObj;
	}

	/**
	 * It saves the job with the details of company name,jobTitle, CreatedDate
	 * 
	 * @param JobSearchViewDetailForm
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/saveThisJob", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveThisJob(@Valid ApplyJobForm form, Map<String, Object> map,
			@RequestParam("id") int jobId, HttpSession session) {
		JSONObject jsonObject = new JSONObject();

		// Check for job seeker login ,open popup if not logged in.
		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			jsonObject.put(ajaxNavigationPath,
					"../jobsearch/jobseekersaveThisJobPopUp");
			return jsonObject;
		}
		form.setJobID(jobId);
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int savedJobsCount = 0;
		List<AppliedJobDTO> savedJobDTOList = jobSeekerService
				.getSavedJobs(userId);
		savedJobsCount = savedJobDTOList.size();
		if (savedJobsCount >= Integer.parseInt(saveJobsLimit)) {
			int oldJobId = savedJobDTOList.get(0).getSaveJobId();
			jobSeekerService.updateAppliedSavedJobs(oldJobId);
		}

		// Get the Job details
		SearchedJobDTO searchedJobDTO = jobSearchService.viewJobDetails(form
				.getJobID());

		// Validate if job is already applied
		AppliedJobDTO appliedJobDTO = jobSearchService.fetchSavedOrAppliedJob(
				searchedJobDTO, userId);
		if (appliedJobDTO != null) {
			if (appliedJobDTO.getAppliedDt() != null) {
				applyJobErrMsg = applyJobErrMsg.replace("?", appliedJobDTO
						.getAppliedDt().toString());
				jsonObject.put(ajaxMsg, applyJobErrMsg);
				return jsonObject;
			} else {
				saveThisJobErrMsg = saveThisJobErrMsg.replace("?",
						appliedJobDTO.getCreateDt().toString());
				jsonObject.put(ajaxMsg, saveThisJobErrMsg);
				return jsonObject;
			}
		}

		// save the applied job in DB
		AppliedJobDTO saveJobDTO = new AppliedJobDTO();
		Date currentDate = new Date();
		JobPostDTO jpJob = new JobPostDTO();
		jpJob.setJobId(form.getJobID());
		saveJobDTO.setJpJob(jpJob);
		saveJobDTO.setUserId(userId);
		saveJobDTO.setJobTitle(searchedJobDTO.getJobTitle());
		saveJobDTO.setFacilityName(searchedJobDTO.getCompanyName());
		saveJobDTO.setCreateDt(currentDate.toString());
		saveJobDTO.setAppliedDt(null);
		saveJobDTO.setDeleteDt(null);
		jobSearchService.saveOrApplyJob(saveJobDTO);
		jsonObject.put(ajaxMsg, saveThisJobSuccessMsg);
		return jsonObject;
	}

	/**
	 * The method is called to close the SaveThisJob popup
	 * 
	 * @param JobSearchViewDetailForm
	 * @return
	 */
	@RequestMapping(value = "/jobseekersaveThisJobPopUp")
	public ModelAndView callSaveThisJobPopUp(
			Map<String, JobSearchResultForm> model) {
		return new ModelAndView("jobseekersaveThisJobPopUp");
	}

	/**
	 * The method is called to close the SaveThisJob popup
	 * 
	 * @param JobSearchViewDetailForm
	 * @return
	 */
	@RequestMapping(value = "/jobseekerPostYourResume")
	public @ResponseBody
	JSONObject callPostYourResumePopUp(HttpSession session, Map<String, Object> map) {
		JSONObject jsonObject = new JSONObject();

		// Check for job seeker login ,open popup if not logged in.
		if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
			map.put("loginForm", new LoginForm());
			jsonObject.put("LoggedInNavigationPath", navigationPath
					+ dothtmlExtention + jobseekerPageExtention);
			return jsonObject;
		}
		jsonObject.put(ajaxNavigationPath,
				"../jobSeekerResume/createResumePopUp.html?resumeType=createResume");
		return jsonObject;
	}
	/**
	 * The method is called to navigate job seeker to login
	 * 
	 * @param JobSearchViewDetailForm
	 * @return
	 */
	@RequestMapping(value = "/navigateToLogin")
	public ModelAndView navigateToLogin(Map<String, Object> model) {
		/**
		 * Maintain the saving job detail in session
		 */
		model.put("loginForm", new LoginForm());
		return new ModelAndView("jobSeekerLogin");
	}

	/**
	 * This method is called to forward to Advance job search page
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/advanceSearch", method = RequestMethod.GET)
	public ModelAndView forwardToAdvanceJobSearch(
			Map<String, JobSearchResultForm> model) {
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		model.put("jobSearchResultForm", jobSearchResultForm);
		return new ModelAndView("jobboardadvancedsearch");
	}

	/**
	 * This method is called to send job to a friend
	 * for call the Mail page open and here hold 
	 * URl userid and job id etc.
	 * @author kartikm
	 * @version V.0.1
	 * @param sendtofriendmail - 
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendtofriend", method = RequestMethod.GET)
	public ModelAndView sendToFriend(SendToFriend sendtofriendmail,
			BindingResult result, HttpServletRequest request, Model model) {
		
		try {
		
			int jobId = Integer.parseInt(request.getParameter("id"));
			sendtofriendmail.setJobId(jobId);
			sendtofriendmail.setJoburl(request.getRequestURL().toString());
			model.addAttribute("joburl", request.getRequestURL().toString());
			model.addAttribute("jobId", request.getParameter("id"));
			model.addAttribute(CURRENT_URL, request.getParameter(CURRENT_URL));
			model.addAttribute("sendtofriendmail", sendtofriendmail);
		} catch (Exception e) {
			LOGGER.info("ERROR");
		}

		return new ModelAndView("jobseekersendtofriendpopup");
	}

	/**
	 * Mail Sending method sendTofriendPost
	 * take Bean file Binding result and 
	 * Http servlet request and Session for 
	 * Many place it is hold User id and 
	 * facilityid
	 * @author kartikm
	 * @version V.0.1
	 * @param sendtofriendmail
	 * @param result
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/sendtofriendpost", method = RequestMethod.POST)
	public String sendToFriendPost(
			@ModelAttribute("sendtofriendmail") SendToFriend sendtofriendmail,
			BindingResult result, HttpServletRequest request,
			HttpSession session) {
		ModelAndView modelData = new ModelAndView();
		Boolean status = Boolean.TRUE;
		String finalmailbody;
		StringBuffer mesg = new StringBuffer();
		StringBuffer dataString = new StringBuffer();
		String bodyMesg = "";
		try {
			String data = sendtofriendmail.getEmail().toString();
			if((null==data.trim())||("".equals(data.trim()))){
				return MMJBCommonConstants.EMAIL_MESSAGE_BLANK;
			}
			data = data.replace(',', ';');
			int len = data.length();
			if (data.charAt(len - 1) == ';') {
				data = data.substring(0, len - 1);
			}
			String str[] = data.split(";");
			int countString = str.length;
			
			try {
				int userId = 0;
				String userName = null;
				String userEmail = null;
				String jobseekerName = null;
				if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
					userId = (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID);
					userName = (String) session
							.getAttribute(MMJBCommonConstants.USER_NAME);
					userEmail = (String) session
							.getAttribute(MMJBCommonConstants.USER_EMAIL);
				}
				EmailDTO jobSeekerEmailDTO = new EmailDTO();
				jobSeekerEmailDTO.setFromAddress(MMJBCommonConstants.WEB_MAIL_SERVER);

				int iterationCount = 0;
				InternetAddress[] jobSeekerToAddress = new InternetAddress[str.length];
				for (String string : str) {
					
					if (!validateEmailPattern(string.trim())) {
						return MMJBCommonConstants.EMAIL_MESSAGE;
					}

					jobSeekerToAddress[iterationCount] = new InternetAddress(string.trim());
					iterationCount++;

				}
				jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
				if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
					jobseekerName = (String) session
							.getAttribute(MMJBCommonConstants.USER_NAME);
					jobseekerSuggestFrdSub = jobseekerSuggestFrdSub.replace(
							"?Jobseekername", jobseekerName);
					
				} else {
					jobseekerName = "XXXX-XXX";
					jobseekerSuggestFrdSub = jobseekerSuggestFrdSub.replace(
							"?Jobseekername", jobseekerName);
				}
						
				
				jobSeekerEmailDTO.setSubject(jobseekerSuggestFrdSub);
				SearchedJobDTO searchedJobDTO = jobSearchService
						.viewJobDetails(sendtofriendmail.getJobId());

				String Subject = MMJBCommonConstants.SUBJECT_OF_MAIL+" "
						+ jobseekerName;
				String bodyHead1 = MMJBCommonConstants.BODY_OFMAIL_FIRST+" "
						+ jobseekerName +" "+MMJBCommonConstants.BODY_OFMAIL_SECOND;
				String bodyHead2 = sendtofriendmail.getMessage();
				String jobTitle = MMJBCommonConstants.JOB_TITLE_HEADING;
				String companyName = MMJBCommonConstants.COMAPNY_NAME_HEADING;
				String jobUrl = sendtofriendmail.getJoburl();
				String joburl = MMJBCommonConstants.URL_LINK_FIRST
						+""+jobUrl
						+""+MMJBCommonConstants.URL_LINK_SECOND;
				mesg = mesg
						.append("<TABLE><TR><TD>" + Subject + "</TD></TR>\n");
				mesg = mesg.append("<TR><TD>" + bodyHead1 + "\n" + bodyHead2
						+ "</TD></TR>\n");
				mesg = mesg.append("<TR><TD><B>[" + jobTitle + "]</B>"
						+ searchedJobDTO.getJobTitle() + "</TD></TR>\n");
				mesg = mesg.append("<TR><TD><B>[" + companyName + "]</B>"
						+ searchedJobDTO.getCompanyName() + "</TD></TR>\n");
				mesg = mesg.append("<TR><TD>" + joburl + "</TD></TR>\n\n\n");
				mesg = mesg.append("<TR><TD>" + jobUrl + "</TD></TR></TABLE>");
				bodyMesg = mesg.toString();
				jobSeekerEmailDTO.setBody(bodyMesg);
				jobSeekerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(jobSeekerEmailDTO);
			} catch (Exception e) {
				LOGGER.info(MMJBCommonConstants.ERROR_SENDING_MAIL);
			}

		} catch (Exception e) {
			status = Boolean.FALSE;
			throw new MailParseException(e);
		}
		if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			modelData.setViewName(MMJBCommonConstants.URL_REDIRECT_MAIL);
			return "";
		} else {
			modelData.setViewName(MMJBCommonConstants.URL_REDIRECT_MAIL);
			return "";
		}

	}

	/**
	 * 
	 * @param emailAddress
	 *            emailAddress.
	 * @return true.
	 */

	private boolean validateEmailPattern(String emailAddress) {
		Pattern pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}

	/**
	 * This method will be used for Autocomplete for city, state or Postcode and
	 * Return List<String>.
	 * 
	 * @param String
	 *            keyword
	 * @return List<String> Object
	 */

	@RequestMapping(value = "/findLocation", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	List<String> findLocation(@RequestParam("term") String keyword) {

		List<LocationDTO> locationDTOList = lookupService
				.locationSearch(keyword.trim());

		if (locationDTOList != null) {
			/*
			 * Returning the List<String> based on Post code search or CityState
			 * search
			 */

			if (MMUtils.isIntNumber(keyword)) {
				return MMUtils.convertToPostcodeStringList(locationDTOList);
			} else {
				return MMUtils.convertToCityStateStringList(locationDTOList);
			}
		}

		return null;
	}

	/**
	 * This method is used to create parameter map and populate the required
	 * values into it.
	 * 
	 * @param jobSearchResultForm
	 * @param sessionId
	 * @param searchName
	 * @param sessionMap
	 * @return Map<String, String>
	 */

	private Map<String, String> getParameterMap(
			JobSearchResultForm jobSearchResultForm, String sessionId,
			String searchName, Map<String, String> sessionMap) {

		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put(SearchParamDTO.KEYWORDS, jobSearchResultForm.getKeywords()
				.trim());

		paramMap.put(SearchParamDTO.CITY_STATE, jobSearchResultForm
				.getCityState().trim());
		paramMap.put(SearchParamDTO.RADIUS, jobSearchResultForm.getRadius()
				.trim());
		paramMap.put(SearchParamDTO.SESSION_ID, sessionId.trim());
		paramMap.put(SearchParamDTO.SEARCH_SEQ,
				sessionMap.get(SearchParamDTO.SEARCH_SEQ));
		paramMap.put(SearchParamDTO.SEARCH_NAME, searchName.trim());

		return paramMap;

	}

	/**
	 * This method is used to set values into the session map.
	 * 
	 * @param sessionMap
	 * @param searchSeq
	 * @param jobSearchResultForm
	 * @return Map<String, String>
	 */

	private Map<String, String> setValuesToSessionMap(
			Map<String, String> sessionMap, int searchSeq,
			JobSearchResultForm jobSearchResultForm) {

		if (sessionMap.get(SearchParamDTO.SEARCH_SEQ) == null) {
			sessionMap.put(SearchParamDTO.SEARCH_SEQ,
					String.valueOf(searchSeq + 1));
		} else {
			sessionMap.put(SearchParamDTO.SEARCH_SEQ, String.valueOf(Integer
					.parseInt(sessionMap.get(SearchParamDTO.SEARCH_SEQ)) + 1));
		}

		sessionMap.put(SearchParamDTO.KEYWORDS, jobSearchResultForm
				.getKeywords().trim());
		sessionMap.put(SearchParamDTO.CITY_STATE, jobSearchResultForm
				.getCityState().trim());
		sessionMap.put(SearchParamDTO.RADIUS, jobSearchResultForm.getRadius()
				.trim());
		sessionMap.put(MMJBCommonConstants.SEARCH_TYPE, jobSearchResultForm
				.getSearchtype().trim());
		return sessionMap;

	}
	
	@RequestMapping(value = "/jobboardsearchresultsBody")
	public ModelAndView getjobboardsearchresultsBody(HttpServletResponse response,
			HttpServletRequest request, Model model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jobboardsearchresultsBody");
		return modelAndView;
	}

	
	@RequestMapping("/viewImage")
	public void getPhoto(@RequestParam("id") String imageId,
			HttpServletResponse response, HttpServletRequest request,
			BrandingTemplateForm brandingTemplateForm) {

		try {
			BufferedImage originalImage = ImageIO.read(new File(imageId));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage,
					imageId.substring(imageId.length() - 3, imageId.length()),
					baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			ResponseEntity<byte[]> result = handleGetMyBytesRequest(imageInByte);
			// Display the image
			write(response, result.getBody());
		} catch (Exception e) {

			LOGGER.error(e);

		}
	}

	/**
	 * Writes the report to the output stream
	 */
	public void write(HttpServletResponse response, byte[] byteArray) {
		ServletOutputStream outputStream =null;
		try {
			// Retrieve the output stream
			outputStream = response.getOutputStream();
			// Write to the output stream
			outputStream.write(byteArray);
			// Flush the stream
			outputStream.flush();
			// Close the stream
			outputStream.close();

		} catch (Exception e) {
			LOGGER.error(e);
		}
		finally{
			try{
			outputStream.close();
			}catch (Exception e) {
				LOGGER.error(e);
			}
		}
	}
	
	
	public ResponseEntity<byte[]> handleGetMyBytesRequest(byte[] imageInByte) {
		// Get bytes from somewhere...
		byte[] byteData = imageInByte;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(byteData.length);

		return new ResponseEntity<byte[]>(byteData, responseHeaders,
				HttpStatus.OK);
	}

	@RequestMapping("/viewTestimonial")
	public ModelAndView enlargeTestimonial(@RequestParam("id") String testimonyId,
			HttpServletResponse response, HttpServletRequest request,
			BrandingTemplateForm brandingTemplateForm) {
		ModelAndView model = new ModelAndView();
		brandingTemplateForm.setTestimonyContainer(testimonyId);
		model.addObject("brandingTemplateForm", brandingTemplateForm);
		model.setViewName("viewTestimony");
		return model;
	}
	
	/**
	 * The method is called to read the branding information from database.
	 * 
	 * @param dto
	 * @return SearchedJobDTO
	 */
	public SearchedJobDTO checkBrand(SearchedJobDTO dto)
	{
		SearchedJobDTO searchedJobDTO = dto;
		int packageId = brandingTemplateService.getBrandingInformation(searchedJobDTO.getFacilityId());
		
		if(packageId == MMJBCommonConstants.INT_GOLD || packageId == MMJBCommonConstants.INT_PLATINUM)
		{
			searchedJobDTO.setIsSilverCustomer(Boolean.FALSE);
		}
		else
		{
			searchedJobDTO.setIsSilverCustomer(Boolean.TRUE);
		}
		
		searchedJobDTO.setPackageId(packageId);
		return searchedJobDTO;
	}
}
