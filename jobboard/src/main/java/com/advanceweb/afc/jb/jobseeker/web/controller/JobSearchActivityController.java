package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.io.BufferedWriter;
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

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.JobSearchActivity;
import com.advanceweb.afc.jb.job.service.SaveSearchService;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerService;
import com.advanceweb.afc.jb.login.web.controller.LoginForm;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.search.engine.solr.LookupService;
import com.advanceweb.afc.jb.search.service.JobSearchService;
import com.advanceweb.afc.jb.search.service.JSONConverterService;
import com.advanceweb.afc.jb.common.JobSearchResultDTO;

/**
 * <code>JobSearchDetailsController</code>This controller belongs to all
 * searched jobs details.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */

@Controller
@RequestMapping("/jobsearchactivity")
public class JobSearchActivityController {

	@Autowired
	private JobSearchActivity jobSearchActivity;

	private static final Logger LOGGER = Logger
			.getLogger("JobSearchActivityController.class");

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private JSONConverterService jsonConverterService;

	@Autowired
	private JobSeekerService jobSeekerActivity;

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

	@Value("${ajaxMsg}")
	private String ajaxMsg;

	@Value("${invalidemail}")
	private String invalidemail;

	@Value("${ajaxNavigationPath}")
	private String ajaxNavigationPath;

	@Value("${notempty}")
	private String notempty;

	@Value("${jobseekerSuggestFrdSub}")
	private String jobseekerSuggestFrdSub;

	@Value("${jobseekerSuggestFrdBody}")
	private String jobseekerSuggestFrdBody;

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	@Autowired
	private JobSearchService jobSearchService;

	@Autowired
	private SaveSearchService saveSearchService;

	@Autowired
	private LookupService lookupService;

	/**
	 * The view action is called to get the job details by jobId and navigate to
	 * job view details page.
	 * 
	 * @param jobId
	 * @param request
	 * @return : modelandview for respected Jobid
	 */
	@RequestMapping(value = "/viewJobDetails")
	public ModelAndView viewJobDetails(@RequestParam("id") Long jobId,
			Map<String, Object> model, HttpServletRequest request, HttpSession session,
			@RequestParam("currentUrl") String currentUrl) {
		try {
			// View the job with template
			SearchedJobDTO jobDTO = jobSearchActivity.viewJobDetails(jobId);
			model.put("jobDetail", jobDTO);
			model.put("isHideCity", jobDTO.getCity() != null);
			model.put("isHideState", jobDTO.getStateFullName() != null);
			model.put("isHideCoutry", jobDTO.getCountry() != null);
			model.put("isFeatureEmployer", jobDTO.isFeatureEmployer());
			model.put("returnResults", currentUrl);
			
			session.setAttribute(MMJBCommonConstants.AUTOLOAD,true);
			
		} catch (Exception e) {
			// loggers call
			LOGGER.info("ERROR");
		}
		return new ModelAndView("jobseekerJobDetails");
	}

	/**
	 * The apply for job action is called as per the conditions and getting
	 * saved in DB. Check for login , navigate to login page if necessary login
	 * by ADVACNE Guest navigate to Anonymous User Form apply for job or
	 * navigate to employer web page to apply job
	 * 
	 * @param form
	 * @param jobId
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/applyJob", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject applyJob(@Valid ApplyJobForm form, Map<String, Object> map,
			@RequestParam String userID, @RequestParam("id") int jobId,
			HttpSession session, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
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

			// Check for job seeker login
			if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {
				map.put("loginForm", new LoginForm());
				jsonObject.put(ajaxNavigationPath, navigationPath
						+ dothtmlExtention + jobseekerPageExtention);
				return jsonObject;
			}
			// Get the Job details
			SearchedJobDTO searchedJobDTO = jobSearchActivity
					.viewJobDetails(form.getJobID());
			// Validate if job is already applied
			AppliedJobDTO appliedJobDTO = jobSearchActivity
					.fetchSavedOrAppliedJob(searchedJobDTO, userId);
			if (appliedJobDTO != null && appliedJobDTO.getAppliedDt() != null) {
				applyJobErrMsg = applyJobErrMsg.replace("?",
						appliedJobDTO.getAppliedDt());
				jsonObject.put(ajaxMsg, applyJobErrMsg);
				return jsonObject;
			}

			// Send mail to Employer regarding job application
			String loginPath = navigationPath.substring(2);
			// TODO: login Url is for jobseeker. The URL should be changed after
			// creation of employer login page
			String jonseekerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + jobseekerPageExtention;
			String employerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + employerPageExtention;

			EmailDTO employerEmailDTO = new EmailDTO();
			employerEmailDTO.setFromAddress(advanceWebAddress);
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress(
					searchedJobDTO.getEmployerEmailAddress());
			// TODO: Remove hard codes of mails
			// employerToAddress[0] = new InternetAddress(
			// "pramodap@nousinfo.com");
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
			List<String> attachmentpaths = new ArrayList<String>();
			// TODO: Exception if resume not found. File server is not
			// maintained
			try {
				ResumeDTO resumeDTO = resumeService
						.fetchPublicResumeByUserId(userId);
				// TODO : Add the resume as per the type
				if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER
						.equalsIgnoreCase(resumeDTO.getResumeType())) {

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
						// Delete temp file when program exits.
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
				} else if (MMJBCommonConstants.RESUME_TYPE_UPLOAD
						.equalsIgnoreCase(resumeDTO.getResumeType())) {
					// TODO: Need to clarify
				}
				if(resumeDTO.getFilePath() != null){
					attachmentpaths.add(resumeDTO.getFilePath());
				}
				employerEmailDTO.setAttachmentPaths(attachmentpaths);
			} catch (Exception e) {
				// TODO: handle exception
				LOGGER.info("Resume not found");
			}
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
				jobSearchActivity.saveOrApplyJob(applyJobDTO);
			} else {
				applyJobDTO = appliedJobDTO;
				applyJobDTO.setAppliedDt(currentDate.toString());
				jobSearchActivity.updateSaveOrApplyJob(applyJobDTO);
			}
			jsonObject.put(ajaxMsg, applyJobSuccessMsg);
		} catch (Exception e) {
			// loggers call
			LOGGER.info("applyJob ERROR");
		}
		return jsonObject;
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

		// Added for view my saved searches
		//if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			if (session.getAttribute(MMJBCommonConstants.SEARCH_TYPE) != null
					&& session
							.getAttribute(MMJBCommonConstants.SEARCH_TYPE)
							.toString()
							.equalsIgnoreCase(
									MMJBCommonConstants.BASIC_SEARCH_TYPE)) {

				String searchType = session.getAttribute(
						MMJBCommonConstants.SEARCH_TYPE).toString();
				String radius = MMJBCommonConstants.EMPTY;
				String cityState = MMJBCommonConstants.EMPTY;
				String keywords = MMJBCommonConstants.EMPTY;
				String saveSearchName = MMJBCommonConstants.EMPTY;
				boolean autoload = false;
				if (session.getAttribute(MMJBCommonConstants.KEYWORDS) != null) {
					keywords = session.getAttribute(
							MMJBCommonConstants.KEYWORDS).toString();
				}
				if (session.getAttribute(MMJBCommonConstants.CITY_STATE) != null) {
					cityState = session.getAttribute(
							MMJBCommonConstants.CITY_STATE).toString();
				}
				if (session.getAttribute(MMJBCommonConstants.RADIUS) != null) {
					radius = session.getAttribute(MMJBCommonConstants.RADIUS)
							.toString();
				}
				if (session.getAttribute(MMJBCommonConstants.SAVE_SEARCH_NAME) != null) {
					saveSearchName = session.getAttribute(
							MMJBCommonConstants.SAVE_SEARCH_NAME).toString();
				}
				if (session.getAttribute(MMJBCommonConstants.AUTOLOAD) != null) {
					autoload = Boolean.parseBoolean(session.getAttribute(
							MMJBCommonConstants.AUTOLOAD).toString());
				}


				jobSearchResultForm.setSaveSearchName(saveSearchName);
				jobSearchResultForm.setSearchtype(searchType);
				jobSearchResultForm.setKeywords(keywords);
				jobSearchResultForm.setCityState(cityState);
				jobSearchResultForm.setRadius(radius);
				jobSearchResultForm.setAutoload(autoload);

				LOGGER.info("Removing keywords, city,state, autoload from session....");

				session.removeAttribute(MMJBCommonConstants.KEYWORDS);
				session.removeAttribute(MMJBCommonConstants.CITY_STATE);
				session.removeAttribute(MMJBCommonConstants.RADIUS);
				session.removeAttribute(MMJBCommonConstants.AUTOLOAD);

			}
		//}
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
			Map<String, JSONObject> modelMap) {

		JobSearchResultDTO jobSearchResultDTO = null;
		Map<String, String> paramMap = new HashMap<String, String>();

		String searchName = MMJBCommonConstants.EMPTY;// will be replaced by
														// BASIC_SEARCH

		/**
		 * Check if city state and radius field is not empty to check for
		 * LOCATION search
		 **/
		if (StringUtils.isEmpty(jobSearchResultForm.getCityState().trim())) {

			if (!StringUtils.isEmpty(jobSearchResultForm.getKeywords().trim())) {
				searchName = MMJBCommonConstants.KEYWORD;
			}
		} else {
			searchName = MMJBCommonConstants.LOCATION;
		}

		// The value of Search_seq will be changed when the session management
		// is done.
		// This value needs to be increased every time when there is a search
		// happening for a session
		int searchSeq = MMJBCommonConstants.ZERO_INT;
		// String sessionId = MMJBCommonConstants.TEMP_SESSION_ID;

		String sessionId = null;
		if (session != null) {
			sessionId = session.getId();

			if (session.getAttribute(MMJBCommonConstants.SEARCH_SEQ) == null) {
				session.setAttribute(MMJBCommonConstants.SEARCH_SEQ,
						searchSeq + 1);
			} else {
				session.setAttribute(
						MMJBCommonConstants.SEARCH_SEQ,
						Integer.parseInt(session.getAttribute(
								MMJBCommonConstants.SEARCH_SEQ).toString()) + 1);
			}
			session.setAttribute(MMJBCommonConstants.KEYWORDS,
					jobSearchResultForm.getKeywords().trim());
			session.setAttribute(MMJBCommonConstants.CITY_STATE,
					jobSearchResultForm.getCityState().trim());
			session.setAttribute(MMJBCommonConstants.RADIUS,
					jobSearchResultForm.getRadius().trim());
			session.setAttribute(MMJBCommonConstants.SEARCH_TYPE,
					jobSearchResultForm.getSearchtype().trim());
		}

		long start = Long.parseLong(jobSearchResultForm.getStart());
		long rows = Long.parseLong(jobSearchResultForm.getRows());

		/**
		 * Putting all the parameters coming from the UI into a Map for further
		 * processing
		 */
		paramMap.put(MMJBCommonConstants.KEYWORDS, jobSearchResultForm
				.getKeywords().trim());
		paramMap.put(MMJBCommonConstants.CITY_STATE, jobSearchResultForm
				.getCityState().trim());
		paramMap.put(MMJBCommonConstants.RADIUS, jobSearchResultForm
				.getRadius().trim());
		paramMap.put(MMJBCommonConstants.SESSION_ID, sessionId.trim());
		paramMap.put(MMJBCommonConstants.SEARCH_SEQ, session.getAttribute(
				MMJBCommonConstants.SEARCH_SEQ).toString());
		paramMap.put(MMJBCommonConstants.SEARCH_NAME, searchName.trim());

		try {

			/**
			 * Calling the jobSearch() of Service layer from getting the object
			 * of JobSearchResultDTO
			 */
			jobSearchResultDTO = jobSearchService.jobSearch(searchName,
					paramMap, start, rows);

		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while getting the Job Search Result from SOLR...");
		}
		JSONObject jobSrchJsonObj = null;
		if (jobSearchResultDTO != null) {
			/**
			 * Calling the service layer for converting the JobSearchResultDTO
			 * object into JSON Object
			 */
			jobSrchJsonObj = jsonConverterService
					.convertToJSON(jobSearchResultDTO);
			return jobSrchJsonObj;
		}
		return null;
	}

	public void setJobSearchActivity(JobSearchActivity jobSearchActivity) {
		this.jobSearchActivity = jobSearchActivity;
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
					"../jobsearchactivity/jobseekersaveThisJobPopUp");
			return jsonObject;
		}
		form.setJobID(jobId);
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int savedJobsCount = 0;
		List<AppliedJobDTO> savedJobDTOList = jobSeekerActivity
				.getSavedJobs(userId);
		savedJobsCount = savedJobDTOList.size();
		if (savedJobsCount > Integer.parseInt(saveJobsLimit)) {
			int oldJobId = savedJobDTOList.get(0).getSaveJobId();
			jobSeekerActivity.updateAppliedSavedJobs(oldJobId);
		}

		// Get the Job details
		SearchedJobDTO searchedJobDTO = jobSearchActivity.viewJobDetails(form
				.getJobID());

		// Validate if job is already applied
		AppliedJobDTO appliedJobDTO = jobSearchActivity.fetchSavedOrAppliedJob(
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
		jobSearchActivity.saveOrApplyJob(saveJobDTO);
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
	 * 
	 */
	@RequestMapping(value = "/sendtofriend", method = RequestMethod.GET)
	public String sendToFriend(HttpServletRequest request, Model model) {
		try {
			model.addAttribute("joburl", request.getRequestURL().toString());
			model.addAttribute("jobId", request.getParameter("id"));
			model.addAttribute("sendtofriendmail", new SendToFriend());
		} catch (Exception e) {// Catch exception if any
			// System.err.println("Error: " + e.getMessage());
			// e.printStackTrace();
			LOGGER.info("ERROR");
		}

		return "jobseekersendtofriendpopup";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/sendtofriendpost", method = RequestMethod.POST)
	public String sendToFriendPost(
			@ModelAttribute("sendtofriendmail") SendToFriend sendtofriendmail,
			BindingResult result, Model model, HttpServletRequest request,
			HttpSession session) {

		Boolean status = Boolean.TRUE;
		String finalmailbody;
		/*
		 * if (sendtofriendmail.getMessage().length() > 0) { finalmailbody =
		 * commonupperbody + "<a href=" + sendtofriendmail.getJoburl() + ">" +
		 * sendtofriendmail.getJoburl() + "</a>" + commonbeforefriendmsgbody +
		 * sendtofriendmail.getMessage() + commonlowerbody; } else {
		 * finalmailbody = commonupperbody + "<a href=" +
		 * sendtofriendmail.getJoburl() + ">" + sendtofriendmail.getJoburl() +
		 * "</a>" + commonlowerbody; }
		 */
		try {
			if (sendtofriendmail.getEmail().length() > 0
					&& validateEmailPattern(sendtofriendmail.getEmail())) {
				try {
					EmailDTO jobSeekerEmailDTO = new EmailDTO();
					// jobSeekerEmailDTO.setFromAddress(form.getEmailAddress());
					InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
					jobSeekerToAddress[0] = new InternetAddress(
							sendtofriendmail.getEmail());
					jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
					String jobseekerName = (String) session
							.getAttribute(MMJBCommonConstants.USER_NAME);
					jobseekerSuggestFrdSub = jobseekerSuggestFrdSub.replace(
							"?Jobseekername", jobseekerName);
					jobSeekerEmailDTO.setSubject(jobseekerSuggestFrdSub);
					// ?Jobtitle</b><br/>?Companyname
					SearchedJobDTO searchedJobDTO = jobSearchActivity
							.viewJobDetails(sendtofriendmail.getJobId());
					jobseekerSuggestFrdBody = jobseekerSuggestFrdBody.replace(
							"?Jobtitle", searchedJobDTO.getJobTitle());
					jobseekerSuggestFrdBody = jobseekerSuggestFrdBody.replace(
							"?Companyname", searchedJobDTO.getCompanyName());
					jobseekerSuggestFrdBody = jobseekerSuggestFrdBody.replace(
							"?Jobseekername", jobseekerName);
					jobseekerSuggestFrdBody = jobseekerSuggestFrdBody.replace(
							"?joburl", sendtofriendmail.getJoburl());
					jobSeekerEmailDTO.setBody(jobseekerSuggestFrdBody);
					jobSeekerEmailDTO.setHtmlFormat(true);
					emailService.sendEmail(jobSeekerEmailDTO);
				} catch (Exception e) {
					// loggers call
					LOGGER.info("ERROR");
					// e.printStackTrace();
				}
				model.addAttribute("visible", true);
			} else if (sendtofriendmail.getEmail().length() > 0
					&& !validateEmailPattern(sendtofriendmail.getEmail())) {
				model.addAttribute("visible", false);
				model.addAttribute("invalidemail", invalidemail);
				// return "jobseekersendtofriendpopup";
			} else {
				model.addAttribute("visible", false);
				model.addAttribute("notempty", notempty);
				// return "jobseekersendtofriendpopup";
			}

		} catch (Exception e) {
			status = Boolean.FALSE;
			throw new MailParseException(e);
		}
		return "jobseekersendtofriendpopup";
	}

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
			/**
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

}
