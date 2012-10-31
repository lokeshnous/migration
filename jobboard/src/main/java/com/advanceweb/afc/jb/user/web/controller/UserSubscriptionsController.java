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
import org.springframework.web.bind.annotation.RequestParam;
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

		// get List of subscriptions which are assigned to job seeker
		List<DropDownDTO> listSubscriptions = populateDropdownsService
				.getSubscriptionsList();

		// Get current subscription and publication list
		List<UserSubscriptionsDTO> currentSubsList = userSubService
				.getCurrentSubscriptions(Integer.valueOf(String.valueOf(session
						.getAttribute(MMJBCommonConstants.USER_ID))));
		userubscription.jsSubscriptionDTOToJobSeekerSubscriptions(
				currentSubsList, subscriptform, listSubscriptions, session);

		// Getting list of print magazine,digital magazine and e-news letter
		// publications which are applicable for each subscription
		List<DropDownDTO> listpublicationprint = userSubService
				.getSubscriptionscheck(Integer.valueOf(String.valueOf(session
						.getAttribute("userId"))));
		List<DropDownDTO> listpublicationdigital = userSubService
				.getSubscriptionsdigital(Integer.valueOf(String.valueOf(session
						.getAttribute("userId"))));
		List<DropDownDTO> listnewsletter = userSubService
				.getSubscriptionsletter(Integer.valueOf(String.valueOf(session
						.getAttribute("userId"))));

		model.addObject("jobSubscriptionsList", listSubscriptions);
		model.addObject("listpublicationprint", listpublicationprint);
		model.addObject("listpublicationdigital", listpublicationdigital);
		model.addObject("listnewsletter", listnewsletter);
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
			BindingResult result, HttpSession session,
			@RequestParam("printCheckbox") boolean printCheckbox,
			@RequestParam("digCheckbox") boolean digCheckbox,
			@RequestParam("enewsCheckbox") boolean enewsCheckbox,
			@RequestParam("mailCheckbox") boolean mailCheckbox) {
		try {

			subscriptform.setUserId(Integer.valueOf(String.valueOf(session
					.getAttribute(MMJBCommonConstants.USER_ID))));
			List<UserSubscriptionsDTO> listSubsDTO = userubscription
					.jsSubscriptionFormToJobSeekerSubsDTO(subscriptform,
							printCheckbox, digCheckbox, enewsCheckbox,
							mailCheckbox);
			userSubService.saveJobSeekerSubscription(listSubsDTO,
					subscriptform.getUserId());
		} catch (Exception e) {
			LOGGER.error("error in saving the subscription for job seeker", e);
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

		// get List of subscriptions which are assigned to facility( for agecny
		// and employer)
		List<DropDownDTO> listSubscriptions = populateDropdownsService
				.getFacilitySubList();

		// Get current subscription and publication list
		List<UserSubscriptionsDTO> currentSubsList = userSubService
				.getCurrentFacilitySub(facilityId);

		// Getting list of digital magazine and e-news letter
		// publications which are applicable for each subscription
		List<UserSubscriptionsDTO> digitalSubList = userSubService
				.getDigitalSubList();
		List<UserSubscriptionsDTO> enewsSubList = userSubService
				.getEnewsLetterSubList();

		List<DropDownDTO> digSubscriptionList = userubscription
				.jsSubDTOToDropDownDTO(digitalSubList, subscriptform);
		List<DropDownDTO> enewSubList = userubscription.jsSubDTOToDropDownDTO(
				enewsSubList, subscriptform);

		userubscription.jsSubscriptionDTOToFacilitySubscriptions(
				currentSubsList, subscriptform, listSubscriptions);

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
			BindingResult result, HttpSession session,
			@RequestParam("digCheckbox") boolean digCheckbox,
			@RequestParam("enewsCheckbox") boolean enewsCheckbox,
			@RequestParam("mailCheckbox") boolean mailCheckbox) {
		try {

			subscriptform.setUserId(Integer.valueOf(String.valueOf(session
					.getAttribute(MMJBCommonConstants.USER_ID))));
			subscriptform.setFacilityId(Integer.valueOf(String.valueOf(session
					.getAttribute(MMJBCommonConstants.FACILITY_ID))));

			List<UserSubscriptionsDTO> listSubsDTO = userubscription
					.jsSubscriptionFormToUserSubsDTO(subscriptform,
							digCheckbox, enewsCheckbox, mailCheckbox);
			userSubService.saveFacilitySubscription(listSubsDTO,
					subscriptform.getFacilityId());
		} catch (Exception e) {
			LOGGER.error("error in saving the subscription for facility", e);
		}
		return null;
	}
}
