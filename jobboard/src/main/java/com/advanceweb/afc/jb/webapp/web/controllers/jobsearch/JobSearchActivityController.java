package com.advanceweb.afc.jb.webapp.web.controllers.jobsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.SearchResultDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.jobsearch.JobSearchActivity;
import com.advanceweb.afc.jb.webapp.web.forms.jobsearch.JobSearchResultForm;
import com.advanceweb.afc.jb.webapp.web.forms.search.applyJobForm;

import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.jobsearch.JobSearchActivity;
import com.advanceweb.afc.jb.webapp.web.forms.jobsearch.JobSearchViewDetailForm;
import com.advanceweb.afc.jb.webapp.web.helper.ReadSolrServerDetails;

import java.util.Date;

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

//	@Autowired
	private MMEmailService mailSender;

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
	 * @param jobId
	 * @return
	 */
	@RequestMapping(value = "/applyJob")
	public ModelAndView applyJob(@Valid applyJobForm form,
			@RequestParam("id") Long jobId) {

		/**
		 * Check for login , navigate to login page if necessary login by
		 * ADVACNE Guest, navigate to Anonymous User Form apply for job or
		 * navigate to employer web page to apply job
		 */

		try {

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
			attachmentpaths.add("C:\\ppResume.txt");
			employerEmailDTO.setAttachmentPaths(attachmentpaths);
			// mailSender.sendEmail(employerEmailDTO);

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
			// mailSender.sendEmail(jobSeekerEmailDTO);

			/**
			 * saving the job in applied job in jobseeker table
			 */
			jobSearchActivity.applyJob(jobId);

			EmailDTO testemailDTO = new EmailDTO();
			testemailDTO.setFromAddress("from@gmail.com");
			InternetAddress[] ccAddress = new InternetAddress[1];
			ccAddress[0] = new InternetAddress("cc1@gmail.com");
			testemailDTO.setCcAddress(ccAddress);
			InternetAddress[] bccAddress = new InternetAddress[1];
			bccAddress[0] = new InternetAddress("bcc1@gmail.com");
			testemailDTO.setBccAddress(bccAddress);
			InternetAddress[] testtoAddress = new InternetAddress[1];
			testtoAddress[0] = new InternetAddress("to1@gmail.com");
			testemailDTO.setToAddress(testtoAddress);
			testemailDTO.setSubject(searchedJobDTO.getJobTitle());
			testemailDTO.setBody(searchedJobDTO.getJobDesc());
			testemailDTO.setHtmlFormat(true);
			List<String> testAttachmentpaths = new ArrayList<String>();
			testAttachmentpaths.add("C:\\testResume.txt");
			testemailDTO.setAttachmentPaths(testAttachmentpaths);

			mailSender.sendEmail(testemailDTO);
			System.out.println("-------Mail sent-----");
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
			BindingResult result, Map<String, SearchResultDTO> model) {
		ReadSolrServerDetails readSolrServerDetails = new ReadSolrServerDetails();
		SearchResultDTO searchResultDTO = null;
		Map<String, String> serverDetailsMap = readSolrServerDetails
				.getServerDetails(solrConfiguration);
		String searchString = jobSearchResultForm.getSearchString();

		// Need to uncomment below 2 lines to take the rows and start values
		// form the form
		// String rows = jobSearchResultForm.getRows();
		// String start = jobSearchResultForm.getStart();

		// Hard coded for the time being for testing
		String rows = "5";
		String start = "0";
			
		UrlValidator urlValidator = new UrlValidator();
		System.out.println("############"+urlValidator.isValid(serverDetailsMap.get("url")));
		if(urlValidator.isValid(serverDetailsMap.get("url"))){
			
			searchResultDTO = jobSearchActivity.getJobSearchResult(
					searchString.trim(), serverDetailsMap, rows, start);
			
			model.put("searchResultDTO", searchResultDTO);
			return new ModelAndView("jobsearchresult", "searchResultDTOModel",
					model);
		}else{
			
			System.out.println("SERVER URL is not proper.");
			model.put("searchResultDTO", null);
			return new ModelAndView("jobsearchresult", "searchResultDTOModel",
					model);
		}
		
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
