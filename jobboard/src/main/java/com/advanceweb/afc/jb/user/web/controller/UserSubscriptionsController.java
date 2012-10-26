package com.advanceweb.afc.jb.user.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.user.UserSubscriptionService;

/**
 * This controller belongs to jobseeker, Employer and Agency for Subscriptions
 * 
 * @author sharadk
 * 
 */

@Controller
@RequestMapping("/subscriptions")
public class UserSubscriptionsController {
	private static final Logger LOGGER = Logger
			.getLogger(UserSubscriptionsController.class);
	@Autowired
	private TransformUserubscription userubscription;

	@Autowired
	private UserSubscriptionService userSubService;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/**
	 * to view subscription page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modifySubscription", method = RequestMethod.GET)
	public ModelAndView viewCurrentSubscriptions(HttpSession session) {

		ModelAndView model = new ModelAndView();
		UserSubscriptionForm subscriptform = new UserSubscriptionForm();

		List<DropDownDTO> listSubscriptions = populateDropdownsService
				.getSubscriptionsList();
		List<UserSubscriptionsDTO> currentSubsList = userSubService
				.getCurrentSubscriptions(Integer.valueOf(String.valueOf(session
						.getAttribute(MMJBCommonConstants.USER_ID))));
		userubscription.jsSubscriptionDTOToJobSeekerSubscriptions(
				currentSubsList, subscriptform, listSubscriptions);
		model.addObject("jobSubscriptionsList", listSubscriptions);
		model.addObject("jobSeekerSubscriptionForm", subscriptform);
		model.setViewName("jobseekermodifysubscriptions");
		return model;
	}

	/**
	 * This method is called to save subscription
	 * 
	 * @param UserSubscriptionForm
	 * @param result
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveJobSeekerSubscription", method = RequestMethod.POST)
	public String saveJobSeekerSubscription(UserSubscriptionForm subscriptform,
			BindingResult result, HttpSession session) {
		try {

			subscriptform.setUserId(Integer.valueOf(String.valueOf(session
					.getAttribute(MMJBCommonConstants.USER_ID))));
			List<UserSubscriptionsDTO> listSubsDTO = userubscription
					.jsSubscriptionFormToJobSeekerSubsDTO(subscriptform);
			userSubService.saveJobSeekerSubscription(listSubsDTO,
					subscriptform.getUserId());
		} catch (Exception e) {
			LOGGER.info("error in saving the subscription for job seeker");
		}
		return null;
	}

	/**
	 * This method is used to get the current facility subscription list
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/modifyFacilitySubscriptions", method = RequestMethod.GET)
	public ModelAndView modifyFacilitySubscriptionss(HttpSession session) {
		ModelAndView model = new ModelAndView();
		UserSubscriptionForm subscriptform = new UserSubscriptionForm();
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		List<DropDownDTO> listSubscriptions = populateDropdownsService
				.getFacilitySubList();

		List<UserSubscriptionsDTO> currentSubsList = userSubService
				.getCurrentFacilitySub(facilityId);
		List<UserSubscriptionsDTO> digitalSubList = userSubService
				.getDigitalSubList();
		List<UserSubscriptionsDTO> enewsSubList = userSubService
				.getEnewsLetterSubList();
		List<DropDownDTO> digSubscriptionList = userubscription
				.jsSubDTOToDropDownDTO(digitalSubList, subscriptform);
		List<DropDownDTO> enewSubList = userubscription.jsSubDTOToDropDownDTO(
				enewsSubList, subscriptform);
		userubscription.jsSubscriptionDTOToFacilitySubscriptions(
				currentSubsList, subscriptform, listSubscriptions,
				digSubscriptionList, enewSubList);
		model.addObject("facilitySubList", listSubscriptions);
		model.addObject("digitalSubList", digSubscriptionList);
		model.addObject("enewSubList", enewSubList);
		model.addObject("facilitySubsForm", subscriptform);
		model.setViewName("employerModifySubscriptionsPopup");
		return model;
	}

	/**
	 * This Method to save the facilities subscriptions
	 * 
	 * @param subscriptform
	 * @param result
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveFacilitySubscription", method = RequestMethod.GET)
	public String saveFacilitySubscription(
			@ModelAttribute("facilitySubsForm") UserSubscriptionForm subscriptform,
			BindingResult result, HttpSession session) {
		try {

			subscriptform.setUserId(Integer.valueOf(String.valueOf(session
					.getAttribute(MMJBCommonConstants.USER_ID))));
			subscriptform.setFacilityId(Integer.valueOf(String.valueOf(session
					.getAttribute(MMJBCommonConstants.FACILITY_ID))));
			List<UserSubscriptionsDTO> listSubsDTO = userubscription
					.jsSubscriptionFormToUserSubsDTO(subscriptform);
			userSubService.saveFacilitySubscription(listSubsDTO,
					subscriptform.getFacilityId());
		} catch (Exception e) {
			LOGGER.error("error in saving the subscription for facility" + e);
			LOGGER.info("error in saving the subscription for facility" + e);
		}
		return null;
	}
}
