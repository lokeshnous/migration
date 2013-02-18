package com.advanceweb.afc.jb.user.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.web.controller.UserAlertForm;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.ManageAccessPermissionService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.UserAlertService;
import com.advanceweb.afc.jb.user.UserService;

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
	private FacilityService facilityService;

	@Autowired
	private ManageAccessPermissionService permissionService;

	@Autowired
	private TransferUserAlert transferUserAlert;

	@Autowired
	private UserService userService;

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

		String enableAccess = "true";
		String enablePostEditAccess = "true";

		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		ModelAndView model = new ModelAndView();

		// Added to check whether logged in job owner has rights to set the
		// alerts
		EmployerInfoDTO roleList = facilityService.facilityDetails(userId);
		if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.FULL_ACCESS)) {
			enableAccess = "false";
			model.addObject("enableAccess", enableAccess);
		} else if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.MANAGEEDITACCESS)) {
			enablePostEditAccess = "false";
			model.addObject("enablePostEditAccess", enablePostEditAccess);
		}
		model.addObject("enableAccess", enableAccess);
		model.addObject("enablePostEditAccess", enablePostEditAccess);

		// Get All check box values from DB
		List<DropDownDTO> alertList = alertService.populateValues("FACILITY");

		// Getting the job owner list for employer
		List<ManageAccessPermissionDTO> jbOwnerList = null;
		try {
			jbOwnerList = permissionService.getJobOwnerList(facilityId, userId);
		} catch (JobBoardException e) {
			LOGGER.error("Error occurred while set alert" + e);
		}

		List<DropDownDTO> dropDownList = new ArrayList<DropDownDTO>();

		// Based on the role of logged in user we are fetching the owner list
		// If logged in user role is 3 then we get all job owners of employer
		// and we are going to enable the add new job owner link in pop up
		if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.EMPLOYER_ROLE_ID)) {
			UserDTO userDTO = userService.getUserByUserId(userId);
			DropDownDTO downDTO = new DropDownDTO();
			downDTO.setOptionId(String.valueOf(userDTO.getUserId()));
			downDTO.setOptionName(userDTO.getFirstName() + " "
					+ userDTO.getLastName());
			dropDownList.add(downDTO);
			if (jbOwnerList != null && !jbOwnerList.isEmpty()) {
				dropDownList.addAll(transferUserAlert
						.jbOwnerListTODropDownDTO(jbOwnerList));
			}

			// If logged in user role is 5 then we get all job owners of
			// employer But we are going to disable the add new job owner link
			// in pop up
		} else if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.FULL_ACCESS)) {
			List<ManageAccessPermissionDTO> jbOwners = null;
			try {
				jbOwners = alertService.getJobOwner(facilityId, userId);
			} catch (JobBoardException e) {
				LOGGER.error("Error occurred while getting the data for set alert"
						+ e);
			}
			dropDownList = transferUserAlert.jbOwnerListTODropDownDTO(jbOwners);
			// If logged in user role is 6 then we get only logged in user name
			// in the drop down list
		} else if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.MANAGEEDITACCESS)) {
			List<ManageAccessPermissionDTO> jbOwners = null;
			try {
				jbOwners = alertService.getOwnerDetails(userId);
			} catch (JobBoardException e) {
				LOGGER.error("Error occurred while getting the data for set alert"
						+ e);
			}
			dropDownList = transferUserAlert.jbOwnerListTODropDownDTO(jbOwners);
		}
		// set the alerts for first job owner
		int firstJobOwnerId = 0;
		if (dropDownList != null && dropDownList.get(0).getOptionId() != null) {
			firstJobOwnerId = Integer.parseInt(dropDownList.get(0)
					.getOptionId());
		}
		List<UserAlertDTO> userAlertDTO = alertService
				.viewAlerts(firstJobOwnerId);
		String[] selectedAlerts = new String[userAlertDTO.size()];
		if (firstJobOwnerId != 0) {
			int count = 0;
			for (UserAlertDTO userAlertDTO2 : userAlertDTO) {
				selectedAlerts[count] = String.valueOf(userAlertDTO2
						.getAlertId());
				count++;
			}
			alertForm.setSelectedAlerts(selectedAlerts);
		}
		/*
		 * String[] selectedAlerts = null; if(firstJobOwnerId != 0){
		 * List<UserAlertDTO> alertDTOs = alertService.viewalerts(userId,
		 * facilityId, jbOwnerList.subList(0, 1)); int index = 0; selectedAlerts
		 * = new String[alertDTOs.size()]; for (UserAlertDTO userAlertDTO :
		 * alertDTOs) { selectedAlerts[index] =
		 * String.valueOf(userAlertDTO.getAlertId()); index++; } }
		 * alertForm.setSelectedAlerts(selectedAlerts);
		 */

		// select all alerts if user doesn't select any alerts
		if (selectedAlerts.length == 0) {
			selectedAlerts = new String[alertList.size()];
			for (int i = 0; i < alertList.size(); i++) {
				selectedAlerts[i] = String.valueOf(alertList.get(i)
						.getOptionId());
			}
			alertForm.setSelectedAlerts(selectedAlerts);
		}

		model.addObject("alertList", alertList);
		model.addObject("jbOwnerList", dropDownList);
		model.addObject("alertForm", alertForm);
		// model.setViewName("alertForm");
		model.setViewName("setAlertPopup");
		return model;
	}

	/**
	 * The method is called to set the alerts for selected job owner.
	 * 
	 * @param alertForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/employer/viewJobOwnerAlerts", method = RequestMethod.GET)
	public ModelAndView viewAlerts(Map model,
			@ModelAttribute("alertForm") UserAlertForm alertForm,
			BindingResult result, HttpSession session,
			HttpServletRequest request) {
		ModelAndView modelandview = new ModelAndView();

		// set the alerts for selected jobowner
		List<UserAlertDTO> userAlertDTO = alertService.viewAlerts(Integer
				.parseInt(alertForm.getSelJobOwner()));
		String[] selectedAlerts = new String[userAlertDTO.size()];
		int count = 0;
		for (UserAlertDTO userAlertDTO2 : userAlertDTO) {
			selectedAlerts[count] = String.valueOf(userAlertDTO2.getAlertId());
			count++;
		}
		alertForm.setSelectedAlerts(selectedAlerts);
		String enableAccess = "true";
		String enablePostEditAccess = "true";

		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		// Added to check whether logged in job owner has rights to set the
		// alerts
		EmployerInfoDTO roleList = facilityService.facilityDetails(userId);
		if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.FULL_ACCESS)) {
			enableAccess = "false";
			model.put("enableAccess", enableAccess);
		} else if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.MANAGEEDITACCESS)) {
			enablePostEditAccess = "false";
			model.put("enablePostEditAccess", enablePostEditAccess);
		}
		model.put("enableAccess", enableAccess);
		model.put("enablePostEditAccess", enablePostEditAccess);

		// Get All check box values from DB
		List<DropDownDTO> alertList = alertService.populateValues("FACILITY");

		// Getting the job owner list for employer
		List<ManageAccessPermissionDTO> jbOwnerList = null;
		try {
			jbOwnerList = permissionService.getJobOwnerList(facilityId, userId);
		} catch (JobBoardException e) {
			LOGGER.error("Error occurred while set alert" + e);
		}

		List<DropDownDTO> dropDownList = null;

		// Based on the role of logged in user we are fetching the owner list
		// If logged in user role is 3 then we get all job owners of employer
		// and we are going to enable the add new job owner link in pop up
		if (jbOwnerList != null
				&& !jbOwnerList.isEmpty()
				&& (roleList.getRoleId() == Integer
						.valueOf(MMJBCommonConstants.EMPLOYER_ROLE_ID))) {
			dropDownList = transferUserAlert
					.jbOwnerListTODropDownDTO(jbOwnerList);
			UserDTO userDTO = userService.getUserByUserId(userId);
			DropDownDTO downDTO = new DropDownDTO();
			downDTO.setOptionId(String.valueOf(userDTO.getUserId()));
			downDTO.setOptionName(userDTO.getFirstName() + " "
					+ userDTO.getLastName());
			dropDownList.add(0, downDTO);
			// If logged in user role is 5 then we get all job owners of
			// employer But we are going to disable the add new job owner link
			// in pop up
		} else if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.FULL_ACCESS)) {
			List<ManageAccessPermissionDTO> jbOwners = null;
			try {
				jbOwners = alertService.getJobOwner(facilityId, userId);
			} catch (JobBoardException e) {
				LOGGER.error("Error occurred while getting the data for set alert"
						+ e);
			}
			dropDownList = transferUserAlert.jbOwnerListTODropDownDTO(jbOwners);
			// If logged in user role is 6 then we get only logged in user name
			// in the drop down list
		} else if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.MANAGEEDITACCESS)) {
			List<ManageAccessPermissionDTO> jbOwners = null;
			try {
				jbOwners = alertService.getOwnerDetails(userId);
			} catch (JobBoardException e) {
				LOGGER.error("Error occurred while getting the data for set alert"
						+ e);
			}
			dropDownList = transferUserAlert.jbOwnerListTODropDownDTO(jbOwners);
		}
		// set the alerts for first job owner
		/*
		 * int firstJobOwnerId = 0; if(dropDownList != null &&
		 * dropDownList.get(0).getOptionId() != null){ firstJobOwnerId =
		 * Integer.parseInt(dropDownList.get(0).getOptionId()); } String[]
		 * selectedAlerts = null; if(firstJobOwnerId != 0){ List<UserAlertDTO>
		 * alertDTOs = alertService.viewalerts(userId, facilityId,
		 * jbOwnerList.subList(0, 1)); int index = 0; selectedAlerts = new
		 * String[alertDTOs.size()]; for (UserAlertDTO userAlertDTO : alertDTOs)
		 * { selectedAlerts[index] = String.valueOf(userAlertDTO.getAlertId());
		 * index++; } } alertForm.setSelectedAlerts(selectedAlerts);
		 */

		// select all alerts if user doesn't select any alerts
		if (selectedAlerts.length == 0) {
			selectedAlerts = new String[alertList.size()];
			for (int i = 0; i < alertList.size(); i++) {
				selectedAlerts[i] = String.valueOf(alertList.get(i)
						.getOptionId());
			}
			alertForm.setSelectedAlerts(selectedAlerts);
		}
		model.put("alertList", alertList);
		model.put("jbOwnerList", dropDownList);
		model.put("alertForm", alertForm);
		modelandview.setViewName("setAlertPopup");
		// return
		// request.getServletPath()+"/employer/setAlerts/setAlertPopup.html";
		// return "";
		return modelandview;
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
			List<UserAlertDTO> alertDTOs = transferUserAlert
					.jsAlertFormToUserAlertDTO(alertForm);
			alertForm.setUserId(Integer.parseInt(alertForm.getSelJobOwner()));
			alertService.saveAlerts(alertForm.getUserId(), alertDTOs);
		} catch (Exception e) {
			LOGGER.error("error in saving the subscription for job seeker");
		}
		return jsonObject;
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
		} else {
			deleteStatusJson.put("failed", dataDeleteFailure);
		}
		return deleteStatusJson;
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
		EmployerInfoDTO roleList = facilityService.facilityDetails(userId);

		List<ManageAccessPermissionDTO> jbOwnerList = null;
		try {
			jbOwnerList = permissionService.getJobOwnerList(facilityId, userId);
		} catch (JobBoardServiceException e) {
			LOGGER.error("error in viewalerts", e);
		}

		// If logged in user role is 5 then we get all job owners of
		// employer But we are going to disable the add new job owner link
		// in pop up
		if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.FULL_ACCESS)) {
			try {
				jbOwnerList = alertService.getJobOwner(facilityId, userId);
			} catch (JobBoardException e) {
				LOGGER.error(
						"Error occurred while getting the data for set alert",
						e);
			}
			// If logged in user role is 6 then we get only logged in user name
			// in the drop down list
		} else if (roleList.getRoleId() == Integer
				.valueOf(MMJBCommonConstants.MANAGEEDITACCESS)) {
			try {
				jbOwnerList = alertService.getOwnerDetails(userId);
			} catch (JobBoardException e) {
				LOGGER.error(
						"Error occurred while getting the data for set alert",
						e);
			}
		}
		if (jbOwnerList != null
				&& !jbOwnerList.isEmpty()
				&& (roleList.getRoleId() == Integer
						.valueOf(MMJBCommonConstants.EMPLOYER_ROLE_ID))) {

		}
		List<UserAlertDTO> alertList = alertService.viewalerts(userId,
				facilityId, jbOwnerList);
		if (jbOwnerList != null
				&& !jbOwnerList.isEmpty()
				&& (roleList.getRoleId() == Integer
						.valueOf(MMJBCommonConstants.EMPLOYER_ROLE_ID))) {
			List<UserAlertDTO> alertDTOs = alertService.viewAlerts(userId);
			List<UserAlertDTO> alertDTODetails = new ArrayList<UserAlertDTO>();
			UserDTO userDTO = userService.getUserByUserId(userId);
			for (UserAlertDTO alertDTO : alertDTOs) {
				alertDTO.setJobOwner(userDTO.getFirstName() + " "
						+ userDTO.getLastName());
				alertDTODetails.add(alertDTO);
			}
			alertList.addAll(alertDTODetails);
		}

		model.addObject("alertList", alertList);
		model.addObject(alertForm);
		model.setViewName("viewAlertPopup");
		return model;
	}

}
