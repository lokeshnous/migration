package com.advanceweb.afc.jb.webapp.web.controllers.jobsearch;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.SearchResultDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.jobsearch.JobSearchActivity;
import com.advanceweb.afc.jb.webapp.web.forms.jobsearch.JobSearchResultForm;
import com.advanceweb.afc.jb.webapp.web.forms.registration.ContactInfoForm;
import com.advanceweb.afc.jb.webapp.web.forms.registration.JobSeekerRegistrationForm;

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

	public JobSearchActivityController() {
	}

	/**
	 * The view action is called to get the job details by jobId and navigate 
	 * to job view details page.
	 * 
	 * @param jobId
	 * @return : modelandview for respected Jobid
	 */
	@RequestMapping(value = "/viewJobDetails")
	public ModelAndView viewJobDetails(@RequestParam("id") Long jobId) {
		jobSearchActivity.viewJobDetails(jobId);
		return new ModelAndView("jobSeekerActivity");
	}
	
	/**
	 * The apply for job action is called as per the conditions and getting saved 
	 * in DB. 
	 * 
	 * @param jobId
	 * @return 
	 */
	@RequestMapping(value = "/applyJob")
	public ModelAndView applyJob(@RequestParam("id") Long jobId) {
		
		/**
		 * Check for login , navigate to login page if necessary
		 * login by ADVACNE Guest, navigate to Anonymous User Form
		 * apply for job or navigate to employer web page to apply job
		 */
		
		jobSearchActivity.applyJob(jobId);
		
		return new ModelAndView("jobSeekerActivity");
	}

	
	
	/**
	 * This method is called to forward to job search page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findJobPage",method = RequestMethod.GET)
	public ModelAndView findJobPage(Map model) {
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		model.put("jobSearchResultForm", jobSearchResultForm);
		return new ModelAndView("findjob");
	}
	
	/**
	 * This method is called to forward to job search page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findJobSearch",method = RequestMethod.POST)
	public ModelAndView findJobSearch(JobSearchResultForm jobSearchResultForm, BindingResult result,
			Map<String, SearchResultDTO> model) {
		String searchString = jobSearchResultForm.getSearchString();
		System.out.println(searchString);
		SearchResultDTO searchResultDTO = jobSearchActivity.getJobSearchResult(searchString);
		model.put("searchResultDTO", searchResultDTO);
		return new ModelAndView("jobsearchresult","searchResultDTOModel",model);
	}
	
	public void setJobSearchActivity(JobSearchActivity jobSearchActivity) {
		this.jobSearchActivity = jobSearchActivity;
	}

}
