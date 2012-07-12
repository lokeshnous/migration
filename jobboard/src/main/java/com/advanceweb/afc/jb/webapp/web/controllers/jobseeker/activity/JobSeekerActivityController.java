package com.advanceweb.afc.jb.webapp.web.controllers.jobseeker.activity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.jobseeker.activity.JobSeekerActivity;

/**
 * This controller belongs to all jobseekers activity
 * 
 * @author sharadk
 * 
 */

@Controller
public class JobSeekerActivityController {

	@Autowired
	private JobSeekerActivity jobSeekerActivity;

	public JobSeekerActivityController() {
	}

	/**
	 * to get applied Job
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/viewAppliedJob", method = RequestMethod.GET)
	public ModelAndView getAppliedJob(Map model) {

		List<AppliedJobDTO> appliedJobDTO = jobSeekerActivity
				.getAppliedJobs(13100);

		return new ModelAndView("jobSeekerActivity");
	}

	/**
	 * delete action for applied job
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteAppliedJob")
	public ModelAndView deleteAppliedJob(@RequestParam("id") Long id) {

		jobSeekerActivity.deleteAppliedJobs(id);
		return new ModelAndView("jobSeekerActivity");
	}

	/**
	 * delete action for Saved job
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteSavedJob")
	public ModelAndView deleteSavedJob(@RequestParam("id") Long id) {

		jobSeekerActivity.deleteSavedJobs(id);
		return new ModelAndView("jobSeekerActivity");
	}

	/**
	 * to get Saved Job
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/viewSavedJob", method = RequestMethod.GET)
	public ModelAndView getSavedJob(Map model) {
		List<SavedJobDTO> savedJobDTO = jobSeekerActivity.getSavedJobs(13100);

		return new ModelAndView("jobSeekerActivity");
	}

	public void setJobSeekerActivity(JobSeekerActivity jobSeekerActivity) {
		this.jobSeekerActivity = jobSeekerActivity;
	}

}
