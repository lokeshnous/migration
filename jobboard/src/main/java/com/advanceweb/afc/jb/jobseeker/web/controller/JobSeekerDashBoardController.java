package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;
import java.util.Map;

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
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.SaveSearchService;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.user.UserSubscriptionService;
import com.advanceweb.afc.jb.user.web.controller.TransformUserubscription;

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
	private UserSubscriptionService userSubService;

	@Autowired
	private TransformUserubscription userubscription;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private SaveSearchService saveSearchService;

	@Autowired
	private JobSeekerJobDetailService jobSeekerService;

	@Value("${followuplinkfacebook}")
	private String followuplinkfacebook;

	@Value("${followuplinktwitter}")
	private String followuplinktwitter;

	@Value("${followuplinkyoutube}")
	private String followuplinkyoutube;

	@Value("${followuplinklinkedin}")
	private String followuplinklinkedin;

	@Autowired
	private CheckSessionMap checkSessionMap;

	@RequestMapping("/jobSeekerDashBoard")
	public ModelAndView displayDashBoard(HttpSession session) {
		ModelAndView model = new ModelAndView();
		JobSeekerDashBoardForm form = new JobSeekerDashBoardForm();

		// Retrieve Current subscriptions of the user
		int nUserId = (Integer) session.getAttribute("userId");
		List<DropDownDTO> listSubscriptions = populateDropdownsService
				.getSubscriptionsList();
		List<UserSubscriptionsDTO> currentSubsList = userSubService
				.getCurrentSubscriptions(nUserId);

		List<DropDownDTO> currentSubs = userubscription
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
		try {
			List<AppliedJobDTO> savedJobDTOList = jobSeekerService
					.getSavedJobs(nUserId);
			savedJobsCount = savedJobDTOList.size();
			form.setSavedJobsCount(savedJobsCount);
			List<AppliedJobDTO> appliedJobDTOList = jobSeekerService
					.getAppliedJobs(nUserId);
			appliedJobsCount = appliedJobDTOList.size();
		} catch (JobBoardException e) {
			LOGGER.debug("Error occured while fetching the saved or applied job details"
					+ e);
		}
		form.setAppliedJobsCount(appliedJobsCount);
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		model.addObject(MMJBCommonConstants.FOLLOWUP_LINK_FACEBOOK,
				followuplinkfacebook);
		model.addObject(MMJBCommonConstants.FOLLOWUP_LINK_TWITTER,
				followuplinktwitter);
		model.addObject(MMJBCommonConstants.FOLLOWUP_LINK_YOUTUBE,
				followuplinkyoutube);
		model.addObject(MMJBCommonConstants.FOLLOWUP_LINK_LINKEDIN,
				followuplinklinkedin);
		model.addObject("jobSearchResultForm", jobSearchResultForm);
		model.addObject("jobSeekerDashBoardForm", form);
		model.setViewName("jobSeekerDashBoard");

		/** Getting the value from the session **/

		// Added for view my saved search task
		if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {

			Map<String, String> sessionMap = checkSessionMap
					.getSearchSessionMap(session);

			if (!sessionMap.isEmpty()) {

				String searchType = sessionMap
						.get(MMJBCommonConstants.SEARCH_TYPE);
				String radius = MMJBCommonConstants.EMPTY;
				String cityState = MMJBCommonConstants.EMPTY;
				String keywords = MMJBCommonConstants.EMPTY;
				String saveSearchName = MMJBCommonConstants.EMPTY;

				keywords = sessionMap.get(SearchParamDTO.KEYWORDS);
				cityState = sessionMap.get(SearchParamDTO.CITY_STATE);
				radius = sessionMap.get(SearchParamDTO.RADIUS);
				saveSearchName = sessionMap
						.get(MMJBCommonConstants.SAVE_SEARCH_NAME);
				model.addObject(MMJBCommonConstants.SAVE_SEARCH_NAME,
						saveSearchName);
				model.addObject(MMJBCommonConstants.SEARCH_TYPE, searchType);
				model.addObject(SearchParamDTO.KEYWORDS, keywords);
				model.addObject(SearchParamDTO.CITY_STATE, cityState);
				model.addObject(SearchParamDTO.RADIUS, radius);

				LOGGER.info("Removing from session....");

				session.removeAttribute(sessionMap
						.remove(SearchParamDTO.KEYWORDS));
				session.removeAttribute(sessionMap
						.remove(SearchParamDTO.CITY_STATE));
				session.removeAttribute(sessionMap
						.remove(SearchParamDTO.RADIUS));
			}

		}

		return model;
	}
}
