package com.advanceweb.afc.jb.user.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.web.controller.UserAlertForm;
import com.advanceweb.afc.jb.job.service.ManageAccessPermissionService;
import com.advanceweb.afc.jb.user.UserAlertService;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 4th sept, 2012
 */

@Controller
@RequestMapping("/alerts")
public class UserAlertController {

	private static final Logger LOGGER = Logger
			.getLogger(UserAlertController.class);

	@Autowired
	private UserAlertService alertService;

	@Autowired
	private ManageAccessPermissionService permissionService;

	@Autowired
	private TransferUserAlert transferUserAlert;

	@Value("${dataDeleteSuccess}")
	private String dataDeleteSuccess;

	@Value("${dataDeleteFailure}")
	private String dataDeleteFailure;

	/**
	 * The method is called to set the alerts for employer.
	 * 
	 * @param alertForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/employer/setAlerts", method = RequestMethod.GET)
	public ModelAndView setAlerts(
			@ModelAttribute("alertForm") UserAlertForm alertForm,
			BindingResult result, HttpSession session) {

		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		ModelAndView model = new ModelAndView();

		List<DropDownDTO> alertList = alertService.populateValues("FACILITY");
		List<ManageAccessPermissionDTO> jbOwnerList = permissionService
				.getJobOwnerList(facilityId, userId);

		List<DropDownDTO> dropDownList = null;

		if (jbOwnerList != null && !jbOwnerList.isEmpty()) {
			dropDownList = transferUserAlert
					.jbOwnerListTODropDownDTO(jbOwnerList);
		}
		model.addObject("alertList", alertList);
		model.addObject("jbOwnerList", dropDownList);
		model.setViewName("alertForm");
		model.setViewName("setAlertPopup");
		return model;
	}

	/**
	 * This method is called to save the selected alerts
	 * 
	 * @param JobSeekerSubscriptionForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer/saveAlerts", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject saveAlerts(UserAlertForm alertForm, BindingResult result,
			HttpSession session, @RequestParam("selOwnerId") int selOwnerId) {
		JSONObject jsonObject = new JSONObject();
		try {

			alertForm.setUserId(Integer.valueOf(String.valueOf(session
					.getAttribute(MMJBCommonConstants.USER_ID))));
			alertForm.setFacilityId(Integer.valueOf(String.valueOf(session
					.getAttribute(MMJBCommonConstants.FACILITY_ID))));
			List<UserAlertDTO> alertDTOs = transferUserAlert
					.jsAlertFormToUserAlertDTO(alertForm);
			alertService.saveAlerts(alertForm.getUserId(), alertDTOs);
		} catch (Exception e) {
			LOGGER.info("error in saving the subscription for job seeker");
		}
		return jsonObject;
	}

	/**
	 * The method is called to view the alerts for employer.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer/viewAlerts", method = RequestMethod.GET)
	public ModelAndView viewAlerts(
			@ModelAttribute("alertForm") UserAlertForm alertForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		List<ManageAccessPermissionDTO> jbOwnerList = permissionService
				.getJobOwnerList(facilityId, userId);
		List<UserAlertDTO> alertList = alertService.viewalerts(userId,
				facilityId, jbOwnerList);
		model.addObject("alertList", alertList);
		model.addObject(alertForm);
		model.setViewName("viewAlertPopup");
		return model;
	}

	/**
	 * This method is called to delete the alert
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/employer/deleteAlert", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject deleteResume(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("facilityAlertId") int facilityAlertId) {

		boolean deleteStatus = alertService.deleteAlert(facilityAlertId);
		JSONObject deleteStatusJson = new JSONObject();

		if (deleteStatus) {
			deleteStatusJson.put("success", dataDeleteSuccess);
			return deleteStatusJson;
		} else {
			deleteStatusJson.put("failed", dataDeleteFailure);
			return deleteStatusJson;
		}
	}

}
