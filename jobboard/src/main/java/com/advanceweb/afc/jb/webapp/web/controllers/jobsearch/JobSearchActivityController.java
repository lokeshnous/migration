package com.advanceweb.afc.jb.webapp.web.controllers.jobsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.jobsearch.JobSearchActivity;

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

	public void setJobSearchActivity(JobSearchActivity jobSearchActivity) {
		this.jobSearchActivity = jobSearchActivity;
	}

}
