/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.user.web.controller;

import java.util.ArrayList;
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
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerProfileAttribForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.TransformJobSeekerRegistration;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.user.ProfileRegistration;
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
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(UserSubscriptionsController.class);
	
	/** The userubscription. */
	@Autowired
	private TransformUserubscription userubscription;

	/** The user sub service. */
	@Autowired
	private UserSubscriptionService userSubService;

	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/** The profile registration. */
	@Autowired
	private ProfileRegistration profileRegistration;

	/** The transform job seeker registration. */
	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;

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

		List<DropDownDTO> listEmailer = userSubService
				.getSubscriptionsEmailer(Integer.valueOf(String.valueOf(session
						.getAttribute("userId"))));

		// Get the subscription list which selected during registration for
		// logged in user
		List<UserSubscriptionsDTO> getSelectedSub = userSubService
				.getSelectedSub(Integer.valueOf(String.valueOf(session
						.getAttribute("userId"))));
		boolean printSubscription = false;
		boolean digSubscription = false;
		boolean enewsSubscription = false;
		boolean emailSubscription = false;

		// Call to service layer
		JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration
				.viewProfile((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		List<JobSeekerProfileAttribForm> listProfAttribForms = transformJobSeekerRegistration
				.transformDTOToProfileAttribForm(jsRegistrationDTO, null);
		for (JobSeekerProfileAttribForm profileForm : listProfAttribForms) {
			if (profileForm.getStrLabelValue() != null
					&& profileForm.getStrLabelName().equalsIgnoreCase(
							MMJBCommonConstants.COUNTRY)) {
				if (profileForm.getStrLabelValue().equalsIgnoreCase(
						MMJBCommonConstants.COUNTRY_USA)) {
					/*
					 * printSubscription = true; digSubscription = true;
					 * enewsSubscription = true; emailSubscription = true;
					 */
					model.addObject("listpublicationprint",
							listpublicationprint);
				} else if (profileForm.getStrLabelValue().equalsIgnoreCase(
						MMJBCommonConstants.COUNTRY_CA)) {
					// printSubscription = true;
					listpublicationprint = new ArrayList<DropDownDTO>();
					model.addObject("listpublicationprint",
							listpublicationprint);
				}
			}
		}
		/*
		 * if (null != getSelectedSub) { for (UserSubscriptionsDTO
		 * subscriptionsDTO : getSelectedSub) { if
		 * (subscriptionsDTO.getSubscriptionId() ==
		 * MMJBCommonConstants.PRINT_JS_SUBSCRIPTION) { printSubscription =
		 * true; } if (subscriptionsDTO.getSubscriptionId() ==
		 * MMJBCommonConstants.DIGITAL_JS_SUBSCRIPTION) { digSubscription =
		 * true; } if (subscriptionsDTO.getSubscriptionId() ==
		 * MMJBCommonConstants.ENEWS_JS_SUBSCRIPTION) { enewsSubscription =
		 * true; } if (subscriptionsDTO.getSubscriptionId() ==
		 * MMJBCommonConstants.EMAIL_JS_SUBSCRIPTION) { emailSubscription =
		 * true; } } }
		 */
		model.addObject("jobSubscriptionsList", listSubscriptions);
		model.addObject("listpublicationdigital", listpublicationdigital);
		model.addObject("listnewsletter", listnewsletter);
		model.addObject("listEmailer", listEmailer);
		model.addObject("printSubscription", printSubscription);
		model.addObject("digSubscription", digSubscription);
		model.addObject("enewsSubscription", enewsSubscription);
		model.addObject("emailSubscription", emailSubscription);
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

		// If logged in user is job owner then get his parent facility id to get
		// his parent subscriptions
		int parentFacilityId = userSubService.getParentId(facilityId);

		// Get current subscription and publication list
		List<UserSubscriptionsDTO> currentSubsList = userSubService
				.getCurrentFacilitySub(parentFacilityId);

		// Getting list of digital magazine and e-news letter
		// publications which are applicable for each subscription
		List<UserSubscriptionsDTO> digitalSubList = userSubService
				.getDigitalSubList();
		List<UserSubscriptionsDTO> enewsSubList = userSubService
				.getEnewsLetterSubList();
		List<DropDownDTO> listEmailer = userSubService.getSubEmailerList();

		List<DropDownDTO> digSubscriptionList = userubscription
				.jsSubDTOToDropDownDTO(digitalSubList, subscriptform);
		List<DropDownDTO> enewSubList = userubscription.jsSubDTOToDropDownDTO(
				enewsSubList, subscriptform);

		userubscription.jsSubscriptionDTOToFacilitySubscriptions(
				currentSubsList, subscriptform, listSubscriptions);

		model.addObject("facilitySubList", listSubscriptions);
		model.addObject("digitalSubList", digSubscriptionList);
		model.addObject("enewSubList", enewSubList);
		model.addObject("listEmailer", listEmailer);
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

			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);

			subscriptform.setUserId(Integer.valueOf(String.valueOf(session
					.getAttribute(MMJBCommonConstants.USER_ID))));

			// If logged in user is job owner then need to get his parent
			// facility id
			facilityId = userSubService.getParentId(facilityId);
			subscriptform.setFacilityId(facilityId);

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
