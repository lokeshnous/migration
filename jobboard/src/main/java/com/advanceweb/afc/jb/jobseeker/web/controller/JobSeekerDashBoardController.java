/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.advanceweb.afc.jb.search.service.JobSearchService;
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
public class JobSeekerDashBoardController extends AbstractController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("JobSeekerDashBoardController.class");

	/** The user sub service. */
	@Autowired
	private UserSubscriptionService userSubService;

	/** The userubscription. */
	@Autowired
	private TransformUserubscription userubscription;

	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/** The save search service. */
	@Autowired
	private SaveSearchService saveSearchService;

	/** The job seeker service. */
	@Autowired
	private JobSeekerJobDetailService jobSeekerService;

	/** The ad service. */
	@Autowired
	private AdService adService;

	/** The followuplinkfacebook. */
	@Value("${followuplinkfacebook}")
	private String followuplinkfacebook;

	/** The followuplinktwitter. */
	@Value("${followuplinktwitter}")
	private String followuplinktwitter;

	/** The followuplinkyoutube. */
	@Value("${followuplinkyoutube}")
	private String followuplinkyoutube;

	/** The followuplinklinkedin. */
	@Value("${followuplinklinkedin}")
	private String followuplinklinkedin;

	/** The check session map. */
	@Autowired
	private CheckSessionMap checkSessionMap;

	/** The job search service. */
	@Autowired
	private JobSearchService jobSearchService;

	/**
	 * Display dash board.
	 *
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping("/jobSeekerDashBoard")
	public ModelAndView displayDashBoard(HttpSession session,
			HttpServletRequest request) {

		String session_id = session.getId();
		int userId = (Integer) session.getAttribute("userId");

		jobSearchService.inserSessinfo(session_id, userId);

		ModelAndView model = new ModelAndView();
		JobSeekerDashBoardForm form = new JobSeekerDashBoardForm();
		if(null !=session.getAttribute("virusStatus") ){
			boolean virusStatus = (Boolean) session.getAttribute("virusStatus");
			request.setAttribute("virusStatus", virusStatus);
			session.removeAttribute("virusStatus");
			LOGGER.debug("Virus Status : " + true);
		}
		if(null !=session.getAttribute("parseError") ){
			boolean fileParserError = (Boolean) session.getAttribute("parseError");
			request.setAttribute("fileParserError", fileParserError);
			session.removeAttribute("parseError");
			LOGGER.debug("Parse Error Status : " + true);
		}
		if(session.getAttribute("advancePassUser")!=null){
			model.addObject("advUserMessg", "advancePassUser");
		}
		if (null != session.getAttribute("uploadStatus")) {
			boolean status = (Boolean) session.getAttribute("uploadStatus");
			request.setAttribute("uploadStatus", status);
			session.removeAttribute("uploadStatus");
			LOGGER.debug("UPLOAD Status : " + status);
		}
		// Retrieve Current subscriptions of the user
		int nUserId = (Integer) session.getAttribute("userId");
		List<DropDownDTO> listSubscriptions = populateDropdownsService
				.getSubscriptionsList();
		List<UserSubscriptionsDTO> currentSubsList = userSubService
				.getCurrentSubscriptions(nUserId);

		List<DropDownDTO> currentSubs = userubscription
				.jsSubscriptionDTOToJobSeekerSubscriptionForm(currentSubsList,
						listSubscriptions);
		Set<DropDownDTO> set = new HashSet<DropDownDTO>();
		for (DropDownDTO dto : currentSubs) {
			set.add(dto);
		}
		form.setUserName((String) session.getAttribute("UserName"));
		model.addObject("currentSubs", set);

		int savedSearchCount = 0;
		int savedJobsCount = 0;
		int appliedJobsCount = 0;
		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = saveSearchService
				.viewMySavedSearches(nUserId, false);
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
		populateAds(request, session, model);

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

				LOGGER.debug("Removing from session....");
				
				removeSessionAttr(session, sessionMap);
			}

		}

		return model;
	}

	/**
	 * Populate the Ads for job seeker dashboard page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void populateAds(HttpServletRequest request,
			HttpSession session, ModelAndView model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.JOBSEEKER_DASHBOARD);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method removes the session attributes
	 * 
	 * @param session
	 * @param sessionMap
	 */
	public void removeSessionAttr(HttpSession session,
			Map<String, String> sessionMap) {
		if (null == session.getAttribute(MMJBCommonConstants.RETAIN_SEARCH)) {
			session.removeAttribute(sessionMap.remove(SearchParamDTO.KEYWORDS));
			session.removeAttribute(sessionMap
					.remove(SearchParamDTO.CITY_STATE));
			session.removeAttribute(sessionMap.remove(SearchParamDTO.RADIUS));
		}
	}	
}
