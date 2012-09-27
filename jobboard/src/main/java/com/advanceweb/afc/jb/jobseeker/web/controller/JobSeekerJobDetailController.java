package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;

/**
 * This controller belongs to all JobSeeker JobDetails 
 * 
 * @author sharadk
 * 
 */

@Controller
@RequestMapping(value = "/jobSeekerJobDetail")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JobSeekerJobDetailController {

	@Autowired
	private JobSeekerJobDetailService jobSeekerJobDetailService;

	@RequestMapping(value = "/viewAppliedJob")
	public ModelAndView getAppliedJob(HttpSession session, Map model) {
		List<AppliedJobDTO> appliedJobDTOList = jobSeekerJobDetailService
				.getAppliedJobs((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		model.put("appliedJobDTOList", appliedJobDTOList);
		return new ModelAndView("jobseekerviewappliedjobspopup");
	}

	/**
	 * delete action for applied job
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value = "/deleteAppliedJob")
	public ModelAndView deleteAppliedJob(HttpSession session,
			@RequestParam("appliedJobId") int jobId, Map model) {
		boolean result = jobSeekerJobDetailService.updateAppliedSavedJobs(jobId);
		List<AppliedJobDTO> appliedJobDTOList = jobSeekerJobDetailService
				.getAppliedJobs((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		model.put("appliedJobDTOList", appliedJobDTOList);
		return new ModelAndView("jobseekerviewappliedjobspopup");
	}

	/**
	 * delete action for Saved job
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value = "/deleteSavedJob")
	public ModelAndView deleteSavedJob(HttpSession session,
			@RequestParam("appliedJobId") int jobId, Map model) {

		boolean result = jobSeekerJobDetailService.updateAppliedSavedJobs(jobId);
		List<AppliedJobDTO> savedJobDTOList = jobSeekerJobDetailService
				.getSavedJobs((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
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
	public ModelAndView getSavedJob(HttpSession session, Map model) {
		List<AppliedJobDTO> savedJobDTOList = jobSeekerJobDetailService
				.getSavedJobs((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		model.put("savedJobDTOList", savedJobDTOList);
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
	public ModelAndView viewResumeCount(HttpSession session, Map model) {
		/*List<AppliedJobDTO> savedJobDTOList = jobSeekerJobDetailService
				.getSavedJobs((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		model.put("savedJobDTOList", savedJobDTOList);*/
		return new ModelAndView("jobSeekerViewCount");
	}
	
	

}
