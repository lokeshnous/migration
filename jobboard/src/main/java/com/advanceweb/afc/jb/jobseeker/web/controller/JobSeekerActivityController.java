package com.advanceweb.afc.jb.jobseeker.web.controller;

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
import com.advanceweb.afc.jb.job.service.JobSeekerActivity;

/**
 * This controller belongs to all jobseekers activity
 * 
 * @author sharadk
 * 
 */

@Controller
@RequestMapping(value = "/jobSeekerActivity")
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

	@RequestMapping(value = "/viewAppliedJob")
	public ModelAndView getAppliedJob(/*@RequestParam("userId") int userId,*/Map model) {
		List<AppliedJobDTO> appliedJobDTOList = jobSeekerActivity.getAppliedJobs(30);
		model.put("appliedJobDTOList", appliedJobDTOList);
		return new ModelAndView("jobseekerviewappliedjobspopup");
	}

	/**
	 * delete action for applied job
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteAppliedJob")
	public ModelAndView deleteAppliedJob(/*@RequestParam("userId") int userId,*/@RequestParam("appliedJobId") int appliedJobId,Map model) {
		
		boolean result=jobSeekerActivity.deleteAppliedJobs(appliedJobId);
		List<AppliedJobDTO> appliedJobDTOList = jobSeekerActivity.getAppliedJobs(30);
		model.put("appliedJobDTOList", appliedJobDTOList);
		return new ModelAndView("jobseekerviewappliedjobspopup");
	}

	/**
	 * delete action for Saved job
	 * 
	 * @param model
	 * @return
	 */
/*	@RequestMapping(value = "/deleteSavedJob")
	public ModelAndView deleteSavedJob(@RequestParam("id") int id) {

		jobSeekerActivity.deleteSavedJobs(id);
		return new ModelAndView("jobSeekerActivity");
	}
*/
	/**
	 * to get Saved Job
	 * 
	 * @param model
	 * @return
	 */

/*	@RequestMapping(value = "/viewSavedJob", method = RequestMethod.GET)
	public ModelAndView getSavedJob(Map model) {
		List<SavedJobDTO> savedJobDTO = jobSeekerActivity.getSavedJobs(13100);

		return new ModelAndView("jobSeekerActivity");
	}

	public void setJobSeekerActivity(JobSeekerActivity jobSeekerActivity) {
		this.jobSeekerActivity = jobSeekerActivity;
	}
*/
}
