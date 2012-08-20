package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
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

	private static final Logger LOGGER = Logger
			.getLogger("JobSeekerDashBoardController.class");

	@Autowired
	private JobSeekerSubscriptionService jobSeekerSubscriptionsService;

	@Autowired
	private TransformJobSeekerSubscription transformJobSeekerSubscription;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private SaveSearchService saveSearchService;

	@Autowired
	private JobSeekerService jobSeekerActivity;

	@Value("${followuplinkfacebook}")
	private String followuplinkfacebook;

	@Value("${followuplinktwitter}")
	private String followuplinktwitter;

	@Value("${followuplinkyoutube}")
	private String followuplinkyoutube;

	@Value("${followuplinklinkedin}")
	private String followuplinklinkedin;

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
		model.addObject("followuplinkfacebook", followuplinkfacebook);
		model.addObject("followuplinktwitter", followuplinktwitter);
		model.addObject("followuplinkyoutube", followuplinkyoutube);
		model.addObject("followuplinklinkedin", followuplinklinkedin);
		model.addObject("jobSearchResultForm", jobSearchResultForm);
		model.addObject("jobSeekerDashBoardForm", form);
		model.setViewName("jobSeekerDashBoard");

		/** Getting the value from the session **/

		//Added for view my saved search task
		if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			if (session.getAttribute(MMJBCommonConstants.SEARCH_TYPE) != null
					&& session
							.getAttribute(MMJBCommonConstants.SEARCH_TYPE)
							.toString()
							.equalsIgnoreCase(
									MMJBCommonConstants.BASIC_SEARCH_TYPE)) {

				String searchType = session.getAttribute(
						MMJBCommonConstants.SEARCH_TYPE).toString();
				String radius = MMJBCommonConstants.EMPTY;
				String cityState = MMJBCommonConstants.EMPTY;
				String keywords = MMJBCommonConstants.EMPTY;
				String saveSearchName = MMJBCommonConstants.EMPTY;
				if (session.getAttribute(MMJBCommonConstants.KEYWORDS) != null) {
					keywords = session.getAttribute(
							MMJBCommonConstants.KEYWORDS).toString();
				}
				if (session.getAttribute(MMJBCommonConstants.CITY_STATE) != null) {
					cityState = session.getAttribute(
							MMJBCommonConstants.CITY_STATE).toString();
				}
				if (session.getAttribute(MMJBCommonConstants.RADIUS) != null) {
					radius = session.getAttribute(MMJBCommonConstants.RADIUS)
							.toString();
				}
				if (session.getAttribute(MMJBCommonConstants.SAVE_SEARCH_NAME) != null) {
					saveSearchName = session.getAttribute(MMJBCommonConstants.SAVE_SEARCH_NAME)
							.toString();
				}

				model.addObject(MMJBCommonConstants.SAVE_SEARCH_NAME, saveSearchName);
				model.addObject(MMJBCommonConstants.SEARCH_TYPE, searchType);
				model.addObject(MMJBCommonConstants.KEYWORDS, keywords);
				model.addObject(MMJBCommonConstants.CITY_STATE, cityState);
				model.addObject(MMJBCommonConstants.RADIUS, radius);

				LOGGER.info("Removing from session....");

				session.removeAttribute(MMJBCommonConstants.KEYWORDS);
				session.removeAttribute(MMJBCommonConstants.CITY_STATE);
				session.removeAttribute(MMJBCommonConstants.RADIUS);

			}
		}

		return model;
	}
}
