package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import net.sf.json.JSONObject;

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

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.job.service.JobSearchActivity;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.login.web.controller.LoginForm;
import com.advanceweb.afc.jb.search.JobSearchService;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.engine.solr.SOLRSearchHelper;

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

	@Value("${saveThisJobSuccessMsg}")
	private String saveThisJobSuccessMsg;

	@Value("${saveThisJobErrMsg}")
	private String saveThisJobErrMsg;

	@Value("${applyJobSuccessMsg}")
	private String applyJobSuccessMsg;

	@Value("${applyJobErrMsg}")
	private String applyJobErrMsg;

	@Autowired
	private JobSearchService jobSearchService;

	@Autowired
	private SOLRSearchHelper sOLRSearchHelper;

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
	 * saved in DB.
	 * 
	 * @param form
	 * @param jobId
	 * @return
	 */
	@RequestMapping(value = "/applyJob", method = RequestMethod.GET)
	public @ResponseBody String applyJob(@Valid ApplyJobForm form, Map<String, Object> map,
			@RequestParam String userID) {

		/**
		 * Check for login , navigate to login page if necessary login by
		 * ADVACNE Guest navigate to Anonymous User Form apply for job or
		 * navigate to employer web page to apply job
		 */

		try {
			/**
			 * Check for job seeker login
			 */
			Boolean isjobSeekerLogedin = Boolean.FALSE;
			if (isjobSeekerLogedin) {
				map.put("loginForm", new LoginForm());
//				return new ModelAndView("jobSeekerLogin");
			}

			map.put("isJobAction", true);
			form.setJobID(13158);
			int userId = 30;
			form.setUseremail("mmnousinfo@gmail.com");
			
			/**
			 * Get the Job details
			 */
			SearchedJobDTO searchedJobDTO = jobSearchActivity
					.viewJobDetails(form.getJobID());

			/**
			 * Validate if job is already applied
			 */
			AppliedJobDTO appliedJobDTO = jobSearchActivity
					.fetchSavedOrAppliedJob(searchedJobDTO, userId);
			if (appliedJobDTO != null && appliedJobDTO.getAppliedDt() != null) {
				applyJobErrMsg = applyJobErrMsg.replace("?",
						appliedJobDTO.getAppliedDt());
				map.put("jobActionInfo", applyJobErrMsg);
//				return new ModelAndView(
//						"redirect:/jobsearchactivity/findJobPage.html");
				return applyJobErrMsg;
			}

			/**
			 * Send mail to employer's by sub as job title and body as short job
			 * desc with attached public resume
			 */
			EmailDTO employerEmailDTO = new EmailDTO();
			// employerEmailDTO.setFromAddress(form.getUseremail());
			employerEmailDTO.setCcAddress(null);
			employerEmailDTO.setBccAddress(null);
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress(
					searchedJobDTO.getEmployerEmailAddress());
			employerEmailDTO.setToAddress(employerToAddress);
			employerEmailDTO.setSubject(searchedJobDTO.getJobTitle()+" employer");
			employerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			employerEmailDTO.setHtmlFormat(true);
			List<String> attachmentpaths = new ArrayList<String>();

			// TODO: Fetch the path of public resume
			attachmentpaths.add("C:\\testResume.txt");
			employerEmailDTO.setAttachmentPaths(attachmentpaths);
			emailService.sendEmail(employerEmailDTO);
			LOGGER.info("Mail sent to employer");
			/**
			 * confirm mail:Send mail to job seeker by sub as job title and body
			 * as short job desc
			 */
			EmailDTO jobSeekerEmailDTO = new EmailDTO();
			// jobSeekerEmailDTO.setFromAddress(form.getUseremail());
			jobSeekerEmailDTO.setCcAddress(null);
			jobSeekerEmailDTO.setBccAddress(null);
			InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
			jobSeekerToAddress[0] = new InternetAddress(form.getUseremail());
			jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
			jobSeekerEmailDTO.setSubject(searchedJobDTO.getJobTitle()+" jobseeker");
			jobSeekerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			jobSeekerEmailDTO.setHtmlFormat(true);
			emailService.sendEmail(jobSeekerEmailDTO);
			LOGGER.info("Mail sent to jobseeker");

			/**
			 * save the applied job in DB
			 */
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
			map.put("jobActionInfo", applyJobSuccessMsg);
		} catch (Exception e) {
			// loggers call
			LOGGER.info("ERROR");
		}
//		return new ModelAndView("redirect:/jobsearchactivity/findJobPage.html");
		return applyJobSuccessMsg;
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
	 * This method will be used for doing Job search
	 * 
	 * @param jobSearchResultForm
	 * @param result
	 * @param model
	 * @return ModelAndView
	 */

	@RequestMapping(value = "/findJobSearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject findJobSearch(JobSearchResultForm jobSearchResultForm,
			BindingResult result, Map<String, JSONObject> modelMap) {

		JobSearchResultDTO jobSearchResultDTO = null;
		Map<String, String> paramMap = new HashMap<String, String>();
		String searchName = "KEYWORD";// will be replaced by BASIC_SEARCH

		/*
		 * String keywords = jobSearchResultForm.getKeywords().trim(); String
		 * radius = jobSearchResultForm.getRadius().trim(); String cityState =
		 * jobSearchResultForm.getCityState().trim();
		 */
		/*
		 * String keywords = "nurse"; String radius = ""; String cityState =
		 * "st";
		 */

		// System.out.println("keywords=============================="+keywords);
		// System.out.println("radius=============================="+radius);
		// System.out.println("cityState=============================="+cityState);

		paramMap.put("keywords", jobSearchResultForm.getKeywords().trim());
		paramMap.put("cityState", jobSearchResultForm.getCityState().trim());
		paramMap.put("radius", jobSearchResultForm.getRadius());

		/*
		 * paramMap.put("keywords", keywords); paramMap.put("cityState",
		 * cityState); paramMap.put("radius",radius);
		 */

		paramMap.put("sessionid", "JS0011");
		paramMap.put("search_seq", "");

		long start = Long.parseLong(jobSearchResultForm.getStart());
		long rows = Long.parseLong(jobSearchResultForm.getRows());
		/*
		 * long start = 0; long rows = 20;
		 */

		// System.out.println("Start=============================="+start);
		// System.out.println("rows=============================="+rows);

		jobSearchResultDTO = jobSearchService.jobSearch(searchName, paramMap,
				start, rows);
		JSONObject jobSrchJsonObj = null;
		if (jobSearchResultDTO != null) {
			jobSrchJsonObj = sOLRSearchHelper
					.convertToJSON(jobSearchResultDTO);
			return jobSrchJsonObj;

			// modelMap.put("jobSrchJsonObj", jobSrchJsonObj);
		}
		return null;
	}

	/*
	 * @RequestMapping(value = "/findJobSearchJSON", method = RequestMethod.GET)
	 * public @ResponseBody JSONObject getJSONObj() { return jobSrchJsonObj; }
	 */

	/*
	 * @RequestMapping(value = "/findJobSearch", method = RequestMethod.GET)
	 * public @ResponseBody List<String> getCountryList() { List<String>
	 * countryList = new ArrayList<String>(); countryList.add("1");
	 * countryList.add("2"); countryList.add("3"); countryList.add("4");
	 * countryList.add("5");
	 * 
	 * return countryList; }
	 */

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
	public @ResponseBody String saveThisJob(@Valid ApplyJobForm form,
			Map<String, Object> map, @RequestParam String userID ) {
				map.put("isJobAction", true);
		/**
		 * Check for job seeker login ,open popup if not logged in.
		 */
		Boolean isjobSeekerLogedin = Boolean.FALSE;
		if (isjobSeekerLogedin) {
			return "jobseekersaveThisJobPopUp";
//			return new ModelAndView("jobseekersaveThisJobPopUp");
		}
		form.setJobID(13158);
		int userId = 30;
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
			if(appliedJobDTO.getAppliedDt() != null){
				applyJobErrMsg = applyJobErrMsg.replace("?", appliedJobDTO
						.getAppliedDt().toString());
				map.put("jobActionInfo", applyJobErrMsg);
				return applyJobErrMsg;
			}else{
				saveThisJobErrMsg = saveThisJobErrMsg.replace("?", appliedJobDTO
						.getCreateDt().toString());
				map.put("jobActionInfo", saveThisJobErrMsg);
				return saveThisJobErrMsg;
			}
//			return new ModelAndView("findJob");
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
		map.put("jobActionInfo", saveThisJobSuccessMsg);
//		return new ModelAndView("redirect:/jobsearchactivity/findJobPage.html");
		return saveThisJobSuccessMsg;
	}

	/**
	 * The method is called to close the SaveThisJob popup
	 * 
	 * @param JobSearchViewDetailForm
	 * @return
	 */
	@RequestMapping(value = "/cancelSaveThisJobPopUp")
	public ModelAndView cancelSaveThisJobPopUp(
			Map<String, JobSearchResultForm> model) {
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		model.put("jobSearchResultForm", jobSearchResultForm);
		return new ModelAndView("findJob");
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

}
