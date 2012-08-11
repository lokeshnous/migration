package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.job.service.SaveSearchService;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerService;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerSubscriptionService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Controller
@RequestMapping("/jobSeeker")
@Scope("session")
public class JobSeekerDashBoardController {

	@Autowired
	private JobSeekerSubscriptionService	jobSeekerSubscriptionsService;

	@Autowired
	private TransformJobSeekerSubscription	transformJobSeekerSubscription;

	@Autowired
	private PopulateDropdowns				populateDropdownsService;

	@Autowired
	private SaveSearchService				saveSearchService;

	@Autowired
	private JobSeekerService				jobSeekerActivity;
	
	@Value("${follouplinkfacebook}")
	private String follouplinkfacebook;

	@Value("${follouplinktwitter}")
	private String follouplinktwitter;

	@Value("${follouplinkyoutube}")
	private String follouplinkyoutube;

	@Value("${follouplinklinkedin}")
	private String follouplinklinkedin;

	@RequestMapping("/jobSeekerDashBoard")
	public ModelAndView displayDashBoard(HttpSession session) {
		ModelAndView model = new ModelAndView();
		JobSeekerDashBoardForm form = new JobSeekerDashBoardForm();

		// Retrieve Current subscriptions of the user
		int nUserId = (Integer) session.getAttribute("userId");
		List<DropDownDTO> listSubscriptions = populateDropdownsService
				.getSubscriptionsList();
		List<JobSeekerSubscriptionsDTO> currentSubsList = jobSeekerSubscriptionsService
				.getCurrentSubscriptions(nUserId);

		List<DropDownDTO> currentSubs = transformJobSeekerSubscription
				.jsSubscriptionDTOToJobSeekerSubscriptionForm(currentSubsList,
						listSubscriptions);
		form.setUserName((String) session.getAttribute("UserName"));
		model.addObject("currentSubs", currentSubs);

		int savedSearchCount = 0;
		int savedJobsCount = 0;
		int appliedJobsCount = 0;
		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
				.viewMySavedSearches(nUserId);
		savedSearchCount = saveSearchedJobsDTOList.size();
		form.setSavedSearchCount(savedSearchCount);
		List<AppliedJobDTO> savedJobDTOList = jobSeekerActivity
				.getSavedJobs(nUserId);
		savedJobsCount = savedJobDTOList.size();
		form.setSavedJobsCount(savedJobsCount);
		List<AppliedJobDTO> appliedJobDTOList = jobSeekerActivity
				.getAppliedJobs(nUserId);
		appliedJobsCount = appliedJobDTOList.size();
		form.setAppliedJobsCount(appliedJobsCount);
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		model.addObject("follouplinkfacebook", follouplinkfacebook);
		model.addObject("follouplinktwitter", follouplinktwitter);
		model.addObject("follouplinkyoutube", follouplinkyoutube);
		model.addObject("follouplinklinkedin", follouplinklinkedin);
		model.addObject("jobSearchResultForm", jobSearchResultForm);
		model.addObject("jobSeekerDashBoardForm", form);
		model.setViewName("jobSeekerDashBoard");
		return model;
	}
}
