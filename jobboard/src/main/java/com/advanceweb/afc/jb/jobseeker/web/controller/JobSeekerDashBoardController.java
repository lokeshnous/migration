package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.SaveSearchService;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.user.UserSubscriptionService;
import com.advanceweb.afc.jb.user.web.controller.TransformUserubscription;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

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
public class JobSeekerDashBoardController extends AbstractController{

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
	
	@Autowired
	private AdService adService;

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
	public ModelAndView displayDashBoard(HttpSession session, HttpServletRequest request) {
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
		// get the Ads
		getAdsForJobseekerDashboard(request, session, model);

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
	
	/**
	 * Get Ads for job seeker dashboard page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void getAdsForJobseekerDashboard (HttpServletRequest request,
			HttpSession session, ModelAndView model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.JOBSEEKER_DASHBOARD);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageTop", bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageRightTop", bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageRightMiddle", bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageBtm", bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);		}
	}

}
