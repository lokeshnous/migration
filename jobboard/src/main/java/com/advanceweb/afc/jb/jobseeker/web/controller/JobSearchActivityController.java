package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.ApplyJobDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.job.service.JobSearchActivity;
import com.advanceweb.afc.jb.job.service.JobSearchService;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.job.web.controller.JobSearchViewDetailForm;
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

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private JobSearchService jobSearchService;

	public JobSearchActivityController() {
	}

	@Autowired
	@Resource(name = "solrConfiguration")
	private Properties solrConfiguration;

	@PostConstruct
	public void init() {
		// do whatever you need with properties
	}

	/**
	 * The view action is called to get the job details by jobId and navigate to
	 * job view details page.
	 * 
	 * @param jobId
	 * @return : modelandview for respected Jobid
	 */
	@RequestMapping(value = "/viewJobDetails")
	public ModelAndView viewJobDetails(@RequestParam("id") Long jobId) {
		/**
		 * View the job with template
		 */
		jobSearchActivity.viewJobDetails(jobId);
		return new ModelAndView("jobSeekerActivity");
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
	public ModelAndView applyJob(@Valid applyJobForm form,
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
			employerEmailDTO.setFromAddress(form.getUseremail());
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
			// emailService.sendEmail(employerEmailDTO);
			System.out.println("-------Mail sent to employer-----");
			/**
			 * confirm mail:Send mail to job seeker by sub as job title and body
			 * as short job desc
			 */
			EmailDTO jobSeekerEmailDTO = new EmailDTO();
			jobSeekerEmailDTO.setFromAddress(form.getUseremail());
			jobSeekerEmailDTO.setCcAddress(null);
			jobSeekerEmailDTO.setBccAddress(null);
			InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
			jobSeekerToAddress[0] = new InternetAddress("to1@gmail.com");
			jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
			jobSeekerEmailDTO.setSubject(searchedJobDTO.getJobTitle());
			jobSeekerEmailDTO.setBody(searchedJobDTO.getJobDesc());
			jobSeekerEmailDTO.setHtmlFormat(true);
			// emailService.sendEmail(jobSeekerEmailDTO);
			System.out.println("-------Mail sent to jobseeker-----");

			/**
			 * saving the job in applied job in jobseeker table
			 */
			ApplyJobDTO applyJobDTO = new ApplyJobDTO();
			applyJobDTO.setJobId(jobId.intValue());
			applyJobDTO.setUserId(1);
			applyJobDTO.setCreateDate(new Date());
			applyJobDTO.setAppliedDate(new Date());
			applyJobDTO.setIsApplied((byte) 1);
			jobSearchActivity.applyJob(applyJobDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("jobSeekerActivity");
	}

	/**
	 * This method is called to forward to job search page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findJobPage", method = RequestMethod.GET)
	public ModelAndView findJobPage(Map<String, JobSearchResultForm> model) {
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		model.put("jobSearchResultForm", jobSearchResultForm);
		return new ModelAndView("findjob");
	}

	/**
	 * This method is called to forward to job search page
	 * 
	 * @param JobSearchResultForm
	 *            , BindingResult, model
	 * @return
	 */
	@RequestMapping(value = "/findJobSearch", method = RequestMethod.POST)
	public ModelAndView findJobSearch(JobSearchResultForm jobSearchResultForm,
			BindingResult result, Map<String, JobSearchResultDTO> model) {
		JobSearchResultDTO jobSearchResultDTO = null;
		Map<String, String> paramMap = new HashMap<String, String>();
		String searchName = "basicjobsearch";
		// Each search fields will be put into this map
		//System.out.println("jobSearchResultForm.getSearchString().trim()=="
				//+ jobSearchResultForm.getSearchString().trim());
		paramMap.put("titlesearch", jobSearchResultForm.getSearchString()
				.trim());
		//System.out.println("Search String ====>>"
				//+ jobSearchResultForm.getSearchString() + "<==");

		// long rows = jobSearchResultForm.getRows();
		// long start = jobSearchResultForm.getStart();
		long rows = 5;
		long start = 0;

		jobSearchResultDTO = jobSearchService.jobSearch(searchName, paramMap,
				rows, start);
		model.put("jobSearchResultDTO", jobSearchResultDTO);
		return new ModelAndView("jobsearchresult", "jobSearchResultDTOModel",
				jobSearchResultDTO);

	}

	public void setJobSearchActivity(JobSearchActivity jobSearchActivity) {
		this.jobSearchActivity = jobSearchActivity;
	}

	/**
	 * It saves the job with the details of company name,jobTitle, CreatedDate
	 * 
	 * @param JobSearchViewDetailForm
	 * @return
	 */
	@RequestMapping(value = "/saveThisJob")
	public ModelAndView saveThisJob(@Valid JobSearchViewDetailForm form,
			BindingResult result) {

		// Transform JobSearchViewDetailForm to searchedJobDTO
		SearchedJobDTO searchedJobDTO = new SearchedJobDTO();
		// Transforming from form value to DTO
		searchedJobDTO.setUserID(form.getUserID());
		searchedJobDTO.setJobID(form.getJobID());
		searchedJobDTO.setCreatedDate(form.getCreatedDate());
		searchedJobDTO.setJobTitle(form.getJobTitle());
		searchedJobDTO.setCompanyName(form.getCompanyName());
		// Saving the Job to DB
		jobSearchActivity.saveJob(searchedJobDTO);

		return new ModelAndView();
	}

}
