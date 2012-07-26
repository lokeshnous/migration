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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.SaveOrApplyJobDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.job.service.JobSearchActivity;
import com.advanceweb.afc.jb.job.service.JobSearchService;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.job.web.controller.JobSearchViewDetailForm;
import com.advanceweb.afc.jb.login.web.controller.LoginForm;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.engine.solr.ReadSolrServerDetails;

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

	// @Autowired
	@SuppressWarnings("unused")
	private MMEmailService emailService;

	@Autowired
	private JobSearchService jobSearchService;

	@Autowired
	private ReadSolrServerDetails readSolrServerDetails;
	
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
			SearchedJobDTO jobDTO= jobSearchActivity.viewJobDetails(jobId);
			model.put("jobDetail", jobDTO);
			model.put("isHideCity", jobDTO.getCity()!= null);
			model.put("isHideState", jobDTO.getStateFullName()!= null);
			model.put("isHideCoutry", jobDTO.getCountry()!= null);
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
	@RequestMapping(value = "/applyJob")
	public ModelAndView applyJob(@Valid ApplyJobForm form,
			@RequestParam("id") Long jobId) {

		/**
		 * Check for login , navigate to login page if necessary login by
		 * ADVACNE Guest navigate to Anonymous User Form apply for job or
		 * navigate to employer web page to apply job
		 */

		try {
			/**
			 * Get the Job details
			 */
			SearchedJobDTO searchedJobDTO = jobSearchActivity
					.viewJobDetails(jobId);

			/**
			 * Send mail to employer's by sub as job title and body as short job
			 * desc with attached public resume
			 */
			EmailDTO employerEmailDTO = new EmailDTO();
			// employerEmailDTO.setFromAddress(form.getUseremail());
			employerEmailDTO.setCcAddress(null);
			employerEmailDTO.setBccAddress(null);
			InternetAddress[] employerToAddress = new InternetAddress[1];
			employerToAddress[0] = new InternetAddress("to1@gmail.com");
			employerEmailDTO.setToAddress(employerToAddress);
			employerEmailDTO.setSubject(searchedJobDTO.getJobTitle());
			employerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			employerEmailDTO.setHtmlFormat(true);
			List<String> attachmentpaths = new ArrayList<String>();

			// TODO: Fetch the path of public resume
			attachmentpaths.add("C:\\ppResume.txt");
			employerEmailDTO.setAttachmentPaths(attachmentpaths);
//			emailService.sendEmail(employerEmailDTO);
			// System.out.println("-------Mail sent to employer-----");
			/**
			 * confirm mail:Send mail to job seeker by sub as job title and body
			 * as short job desc
			 */
			EmailDTO jobSeekerEmailDTO = new EmailDTO();
			// jobSeekerEmailDTO.setFromAddress(form.getUseremail());
			jobSeekerEmailDTO.setCcAddress(null);
			jobSeekerEmailDTO.setBccAddress(null);
			InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
			jobSeekerToAddress[0] = new InternetAddress("to1@gmail.com");
			jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
			jobSeekerEmailDTO.setSubject(searchedJobDTO.getJobTitle());
			jobSeekerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			jobSeekerEmailDTO.setHtmlFormat(true);
//			emailService.sendEmail(jobSeekerEmailDTO);
			// System.out.println("-------Mail sent to jobseeker-----");

			/**
			 * save the applied job in DB
			 */
//			TODO:Validate if job is already applied:You already applied this job on <Date>
			SaveOrApplyJobDTO applyJobDTO = new SaveOrApplyJobDTO();
			applyJobDTO.setJobId(jobId.intValue());
			applyJobDTO.setUserId(1);
			applyJobDTO.setCreateDate(new Date());
			applyJobDTO.setAppliedDate(new Date());
			applyJobDTO.setIsApplied((byte) 1);
			jobSearchActivity.saveOrApplyJob(applyJobDTO);

		} catch (Exception e) {
			// loggers call
			LOGGER.info("ERROR");
		}
		return new ModelAndView("jobSeekerActivity");
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
		return new ModelAndView("findjob");
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
	public ModelAndView findJobSearch(JobSearchResultForm jobSearchResultForm,
			BindingResult result, Map<String, JSONObject> modelMap) {
		
		JobSearchResultDTO jobSearchResultDTO = null;
		Map<String, String> paramMap = new HashMap<String, String>();
		String searchName = "KEYWORD";// will be replaced by BASIC_SEARCH
		paramMap.put("keywords", jobSearchResultForm.getKeywords()
				.trim());
		paramMap.put("cityState", jobSearchResultForm.getCityState().trim());
		paramMap.put("radius",jobSearchResultForm.getRadius());
		paramMap.put("sessionid", "JS0011");
		paramMap.put("search_seq", "");
		
		
		long start = Long.parseLong(jobSearchResultForm.getStart());
		long rows = Long.parseLong(jobSearchResultForm.getRows());
		
		LOGGER.debug("Start=============================="+start);
		LOGGER.debug("rows=============================="+rows);
		
		jobSearchResultDTO = jobSearchService.jobSearch(searchName, paramMap,
				start, rows);
		if (jobSearchResultDTO != null){
			JSONObject jobSrchJsonObj = readSolrServerDetails.convertToJSON(jobSearchResultDTO);
			modelMap.put("jobSrchJsonObj", jobSrchJsonObj);
		}
		
		return new ModelAndView("findjob", modelMap);
		// return new ModelAndView("jsonView", modelMap);
		/*
		 * return new ModelAndView("findjob", "jobSrchJsonObj", jobSrchJsonObj);
		 */
	}
	
	/*@RequestMapping(value = "/findJobSearchJSON", method = RequestMethod.GET)
	public @ResponseBody	JSONObject getJSONObj() {
		return jobSrchJsonObj;
	}*/
	
	

	/*@RequestMapping(value = "/findJobSearch", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getCountryList() {
		List<String> countryList = new ArrayList<String>();
		countryList.add("1");
		countryList.add("2");
		countryList.add("3");
		countryList.add("4");
		countryList.add("5");
			
		return countryList;
	}*/

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
	@RequestMapping(value = "/saveThisJob")
	public ModelAndView saveThisJob(@Valid JobSearchViewDetailForm form,
			@RequestParam("id") Long jobId) {

		/**
		 * Check for job seeker login ,open popup if not logged in.
		 */
		Boolean isjobSeekerLogedin = Boolean.FALSE;
		if (!isjobSeekerLogedin) {
			return new ModelAndView("jobseekersaveThisJobPopUp");
		}
		/**
		 * save the applied job in DB
		 */
//		TODO:Validate if job is already saved.You already saved this job on <Date>
		SaveOrApplyJobDTO saveJobDTO = new SaveOrApplyJobDTO();
		saveJobDTO.setJobId(jobId.intValue());
		saveJobDTO.setUserId(1);
		saveJobDTO.setCreateDate(new Date());
		saveJobDTO.setAppliedDate(new Date());
		saveJobDTO.setIsApplied((byte) 0);
		jobSearchActivity.saveOrApplyJob(saveJobDTO);
		return new ModelAndView("redirect:/jobSeeker/jobSeekerDashBoard.html");
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
