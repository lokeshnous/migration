/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;

/**
 * This controller belongs to all JobSeeker JobDetails 
 * 
 * @author sharadk
 * 
 */

@Controller
@RequestMapping(value = "/jobSeekerJobDetail")
public class JobSeekerJobDetailController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JobSeekerJobDetailController.class);

	/** The job seeker job detail service. */
	@Autowired
	private JobSeekerJobDetailService jobSeekerJobDetailService;

	/**
	 * This method is used to get the pop up to display all the jobs applied by the corresponding job seeker
	 * @param Map model
	 * @param HttpSession session
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewAppliedJob")
	public ModelAndView getAppliedJob(HttpSession session, Map<String, Object> model) {
		try{
		List<AppliedJobDTO> appliedJobDTOList = jobSeekerJobDetailService
				.getAppliedJobs((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		model.put("appliedJobDTOList", appliedJobDTOList);
	}catch (JobBoardException e) {
		LOGGER.debug("Error while fetching all the applied jobs of the corresponding job seeker"
				+ e);
	}
		return new ModelAndView("jobseekerviewappliedjobspopup");
	}

	/**
	 * This method is used to delete the applied job of the corresponding job seeker and display the remaining jobs in the pop up
	 *  @param HttpSession session
	 *  @param int appliedJobId
	 *  @param Map model
	 * @return ModelAndView
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value = "/deleteAppliedJob")
	public ModelAndView deleteAppliedJob(HttpSession session,
			@RequestParam("appliedJobId") int appliedJobId, Map<String, Object> model) {
		try{
		boolean result = jobSeekerJobDetailService.updateAppliedSavedJobs(appliedJobId);
		List<AppliedJobDTO> appliedJobDTOList = jobSeekerJobDetailService
				.getAppliedJobs((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		model.put("appliedJobDTOList", appliedJobDTOList);
	}catch (JobBoardException e) {
			LOGGER.debug("Error while updating the delete date of the applied job "
					+ e);
		}
		return new ModelAndView("jobseekerviewappliedjobspopup");
	}

	/**
	 * This method is used to delete the saved job of the corresponding job seeker and display the remaining jobs in the pop up
	 *  @param HttpSession session
	 *  @param int jobId
	 *  @param Map model
	 * @return ModelAndView
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value = "/deleteSavedJob")
	public ModelAndView deleteSavedJob(HttpSession session,
			@RequestParam("appliedJobId") int jobId, Map<String, Object> model) {
		try{
		boolean result = jobSeekerJobDetailService.updateAppliedSavedJobs(jobId);
		List<AppliedJobDTO> savedJobDTOList = jobSeekerJobDetailService
				.getSavedJobs((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		model.put("savedJobDTOList", savedJobDTOList);
	}catch (JobBoardException e) {
		LOGGER.debug("Error while updating the delete date of the saved job "
				+ e);
	}
		return new ModelAndView("jobseekermysavedjobspopup");

	}

	/**
	 * This method is used to get the pop up to display all the jobs saved by the corresponding job seeker
	 * @param Map model
	 * @param HttpSession session
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewSavedJob")
	public ModelAndView getSavedJob(HttpSession session, Map<String, Object> model) {
		try{
		List<AppliedJobDTO> savedJobDTOList = jobSeekerJobDetailService
				.getSavedJobs((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		model.put("savedJobDTOList", savedJobDTOList);
	}catch (JobBoardException e) {
		LOGGER.debug("Error while fetching all the saved jobs of the corresponding job seeker "
				+ e);
	}
		return new ModelAndView("jobseekermysavedjobspopup");
	}
	
	/**
	 * @author kartikm
	 * @version v.0.1
	 * @param session
	 * @param model
	 * @return jobSeekerViewCount
	 * @Perpous this is view job by employer count
	 */
	
	@RequestMapping(value = "/viewResumeCount")
	public ModelAndView viewResumeCount(HttpSession session, Map<String, Object> model) {
		List<Integer> empResumeViews = jobSeekerJobDetailService
				.getEmployerViews((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		
		
		model.put("empResumeViews", empResumeViews);
		return new ModelAndView("jobSeekerViewCount");
	}
	
	

}
