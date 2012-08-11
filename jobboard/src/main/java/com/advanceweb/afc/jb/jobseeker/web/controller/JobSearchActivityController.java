package com.advanceweb.afc.jb.jobseeker.web.controller;

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
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.JobSearchActivity;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerService;
import com.advanceweb.afc.jb.login.web.controller.LoginForm;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.search.JobSearchService;
import com.advanceweb.afc.jb.search.engine.JSONConverterService;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;

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
	private JSONConverterService jSONConverterService;
	
	@Autowired
	private JobSeekerService jobSeekerActivity;
	
	@Value("${saveThisJobLimitsMsg}")
	private String saveThisJobLimitsMsg;

	@SuppressWarnings("unused")
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

	@Value("${subject}")
	private String subject;

	@Value("${commonupperbody}")
	private String commonupperbody;

	@Value("${commonlowerbody}")
	private String commonlowerbody;

	@Value("${commonbeforefriendmsgbody}")
	private String commonbeforefriendmsgbody;

	@Value("${joburl}")
	private String joburl;

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	@Autowired
	private JobSearchService jobSearchService;

	/**
	 * The view action is called to get the job details by jobId and navigate to
	 * job view details page.
	 * 
	 * @param jobId
	 * @return : modelandview for respected Jobid
	 */
	@RequestMapping(value = "/viewJobDetails")
	public ModelAndView viewJobDetails(@RequestParam("id") Long jobId,
			Map<String, Object> model) {
		try {
			/**
			 * View the job with template
			 */
			SearchedJobDTO jobDTO = jobSearchActivity.viewJobDetails(jobId);
			model.put("jobDetail", jobDTO);
			model.put("isHideCity", jobDTO.getCity() != null);
			model.put("isHideState", jobDTO.getStateFullName() != null);
			model.put("isHideCoutry", jobDTO.getCountry() != null);
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
	 * @return
	 */
	@RequestMapping(value = "/applyJob", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject applyJob(@Valid ApplyJobForm form, Map<String, Object> map,
			@RequestParam String userID, @RequestParam("id") int jobId, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		form.setJobID(jobId);
		form.setUseremail("merion@nousinfosystems.com");
		try {
			
			 //Check for job seeker login
			if (session.getAttribute("userId") == null) {
				map.put("loginForm", new LoginForm());
				jsonObject.put(ajaxNavigationPath, "../loginFormForJobSeeker/login");
				return jsonObject;
			}
			int userId = (Integer)session.getAttribute("userId");

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
			EmailDTO employerEmailDTO = new EmailDTO();
			employerEmailDTO.setFromAddress(advanceWebAddress);
			employerEmailDTO.setCcAddress(null);
			employerEmailDTO.setBccAddress(null);
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress(
					searchedJobDTO.getEmployerEmailAddress());
			employerEmailDTO.setToAddress(employerToAddress);
			employerEmailDTO.setSubject(searchedJobDTO.getJobTitle());
			employerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			employerEmailDTO.setHtmlFormat(true);
			List<String> attachmentpaths = new ArrayList<String>();
			// TODO: Exception if resume not found
//			ResumeDTO resumeDTO = resumeService
//					.fetchPublicResumeByUserId(userId);
//			attachmentpaths.add(resumeDTO.getFilePath());
			try{
				attachmentpaths.add("c:\\testResume.txt");
			}catch (Exception e) {
				// TODO: handle exception
				LOGGER.info("Resume not found");
			}
			employerEmailDTO.setAttachmentPaths(attachmentpaths);
			emailService.sendEmail(employerEmailDTO);
			LOGGER.info("Mail sent to employer");

			// Send confirmation mail to job seeker regarding job application
			EmailDTO jobSeekerEmailDTO = new EmailDTO();
			jobSeekerEmailDTO.setFromAddress(advanceWebAddress);
			jobSeekerEmailDTO.setCcAddress(null);
			jobSeekerEmailDTO.setBccAddress(null);
			InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
			jobSeekerToAddress[0] = new InternetAddress(form.getUseremail());
			jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
			jobSeekerEmailDTO.setSubject(searchedJobDTO.getJobTitle());
			jobSeekerEmailDTO.setBody(searchedJobDTO.getJobDesc());
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
	public ModelAndView findJobPage(Map<String, JobSearchResultForm> model) {
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
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

	@RequestMapping(value = "/findJobSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject findJobSearch(HttpSession session, JobSearchResultForm jobSearchResultForm,
			BindingResult result, Map<String, JSONObject> modelMap) {

		JobSearchResultDTO jobSearchResultDTO = null;
		Map<String, String> paramMap = new HashMap<String, String>();
		
		String searchName = MMJBCommonConstants.EMPTY;// will be replaced by BASIC_SEARCH
		
		/**Check if city state and radius field is not empty to check for LOCATION search**/
		if(StringUtils.isEmpty(jobSearchResultForm.getCityState().trim())){
			
			if(!StringUtils.isEmpty(jobSearchResultForm.getKeywords().trim())){
				searchName = MMJBCommonConstants.KEYWORD;
			}
		}else{
			searchName = MMJBCommonConstants.LOCATION;
		}
		
		// The value of Search_seq will be changed when the session management
		// is done.
		// This value needs to be increased every time when there is a search
		// happening for a session
		int search_seq = MMJBCommonConstants.ZERO_INT;
		//String sessionId = MMJBCommonConstants.TEMP_SESSION_ID;
		String sessionId = MMJBCommonConstants.NULL_STR;
		
		if(!MMJBCommonConstants.NULL_STR.equalsIgnoreCase((String.valueOf(session.getAttribute(MMJBCommonConstants.USER_ID))))){
			sessionId = String.valueOf(session.getAttribute(MMJBCommonConstants.USER_ID));
			session.setAttribute(MMJBCommonConstants.KEYWORDS, jobSearchResultForm
					.getKeywords().trim());
			session.setAttribute(MMJBCommonConstants.CITY_STATE, jobSearchResultForm
					.getCityState().trim());
			session.setAttribute(MMJBCommonConstants.RADIUS, jobSearchResultForm
					.getRadius().trim());
		}else{
			LOGGER.info("Session ID is not present since it is a Anonymous user.");
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
		paramMap.put(MMJBCommonConstants.SEARCH_SEQ, String.valueOf(search_seq));
		paramMap.put(MMJBCommonConstants.QUERY_TYPE, searchName.trim());

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
			jobSrchJsonObj = jSONConverterService
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

		/**
		 * Check for job seeker login ,open popup if not logged in.
		 */
		if (session.getAttribute("userId") == null) {
			jsonObject.put(ajaxNavigationPath, "../jobsearchactivity/jobseekersaveThisJobPopUp");
			return jsonObject;
			// return new ModelAndView("jobseekersaveThisJobPopUp");
		}
		form.setJobID(jobId);
		int userId = (Integer)session.getAttribute("userId");
		int savedJobsCount = 0;
		List<AppliedJobDTO> savedJobDTOList = jobSeekerActivity.getSavedJobs(userId);
		savedJobsCount = savedJobDTOList.size();
		if(savedJobsCount > 30){
			jsonObject.put(ajaxMsg, saveThisJobLimitsMsg);
			return jsonObject;
		}
		/**
		 * Get the Job details
		 */
		SearchedJobDTO searchedJobDTO = jobSearchActivity.viewJobDetails(form
				.getJobID());

		/**
		 * Validate if job is already applied
		 */
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
			// return new ModelAndView("findJob");
		}

		/**
		 * save the applied job in DB
		 */
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
		// return new
		// ModelAndView("redirect:/jobsearchactivity/findJobPage.html");
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
			model.addAttribute("joburl", joburl + request.getParameter("id"));
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
			BindingResult result, Model model, HttpServletRequest request) {

		Boolean status = Boolean.TRUE;
		String finalmailbody;
		if (sendtofriendmail.getMessage().length() > 0) {
			finalmailbody = commonupperbody + "<a href="
					+ sendtofriendmail.getJoburl() + ">"
					+ sendtofriendmail.getJoburl() + "</a>"
					+ commonbeforefriendmsgbody + sendtofriendmail.getMessage()
					+ commonlowerbody;
		} else {
			finalmailbody = commonupperbody + "<a href="
					+ sendtofriendmail.getJoburl() + ">"
					+ sendtofriendmail.getJoburl() + "</a>" + commonlowerbody;
		}
		try {
			if (sendtofriendmail.getEmail().length() > 0
					&& validateEmailPattern(sendtofriendmail.getEmail())) {
				try {
					EmailDTO jobSeekerEmailDTO = new EmailDTO();
					// jobSeekerEmailDTO.setFromAddress(form.getEmailAddress());
					jobSeekerEmailDTO.setCcAddress(null);
					jobSeekerEmailDTO.setBccAddress(null);
					InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
					jobSeekerToAddress[0] = new InternetAddress(
							sendtofriendmail.getEmail());
					jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
					jobSeekerEmailDTO.setSubject(subject);
					jobSeekerEmailDTO.setBody(finalmailbody);
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
//				return "jobseekersendtofriendpopup";
			} else {
				model.addAttribute("visible", false);
				model.addAttribute("notempty", notempty);
//				return "jobseekersendtofriendpopup";
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
	 * This method will be used for Autocomplete for city, state or Postcode 
	 * and Return List<String>.
	 * @param String keyword
	 * @return List<String> Object
	 */
	
	@RequestMapping(value = "/findLocation", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody
	List<String> findLocation(@RequestParam("term") String keyword) {

		List<LocationDTO> locationDTOList = jobSearchService.locationSearch(keyword.trim());
		
		if (locationDTOList != null) {
			/**
			 * Returning the List<String> based on Post code search or CityState search
			 */
			if(MMUtils.isIntNumber(keyword)){
				return MMUtils.convertToPostcodeStringList(locationDTOList);
			}else{
				return MMUtils.convertToCityStateStringList(locationDTOList);
			}
		}
		
		return null;
	}
	
	

}
