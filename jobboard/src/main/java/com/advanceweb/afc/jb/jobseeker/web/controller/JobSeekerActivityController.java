package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerService;

/**
 * This controller belongs to all jobseekers activity
 * 
 * @author sharadk
 * 
 */

@Controller
@RequestMapping(value = "/jobSeekerActivity")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JobSeekerActivityController {

	@Autowired
	private JobSeekerService jobSeekerActivity;

	@RequestMapping(value = "/viewAppliedJob")
	public ModelAndView getAppliedJob(
			/* @RequestParam("userId") int userId, */Map model) {
		List<AppliedJobDTO> appliedJobDTOList = jobSeekerActivity
				.getAppliedJobs(30);
		model.put("appliedJobDTOList", appliedJobDTOList);
		return new ModelAndView("jobseekerviewappliedjobspopup");
	}

	/**
	 * delete action for applied job
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unused"})
	@RequestMapping(value = "/deleteAppliedJob")
	public ModelAndView deleteAppliedJob(
			/* @RequestParam("userId") int userId, */@RequestParam("appliedJobId") int appliedJobId,
			Map model) {
		boolean result = jobSeekerActivity.deleteAppliedJobs(appliedJobId);
		List<AppliedJobDTO> appliedJobDTOList = jobSeekerActivity
				.getAppliedJobs(30);
		model.put("appliedJobDTOList", appliedJobDTOList);
		return new ModelAndView("jobseekerviewappliedjobspopup");
	}

	/**
	 * delete action for Saved job
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unused"})
	@RequestMapping(value = "/deleteSavedJob")
	public ModelAndView deleteSavedJob(
			/* @RequestParam("userId") int userId, */@RequestParam("appliedJobId") int appliedJobId,
			Map model) {

		boolean result = jobSeekerActivity.deleteAppliedJobs(appliedJobId);
		List<AppliedJobDTO> savedJobDTOList = jobSeekerActivity
				.getSavedJobs(30);
		model.put("savedJobDTOList", savedJobDTOList);

		return new ModelAndView("jobseekermysavedjobspopup");

	}

	/**
	 * to get Saved Job
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewSavedJob")
	public ModelAndView getSavedJob(Map model) {
		List<AppliedJobDTO> savedJobDTOList = jobSeekerActivity
				.getSavedJobs(30);
		model.put("savedJobDTOList", savedJobDTOList);
		return new ModelAndView("jobseekermysavedjobspopup");
	}

}
