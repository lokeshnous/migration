package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.ManageAccessPermissionService;

/**
 * 
 * @author deviprasadm
 * @Created: Aug 03, 2012
 * @Purpose: This class will act as a Controller for the Manage Access Permission
 */

@Controller
@RequestMapping("/employer")
public class ManageAccessPermissionController {
	private static final Logger LOGGER = Logger
			.getLogger(ManageAccessPermissionController.class);

	@Autowired
	private ManageAccessPermissionService manageAccessPermissionService;
	@Autowired
	private TransformEmployerRegistration transformEmpReg;

	@RequestMapping(value = "/manageAccessPermission")
	public ModelAndView showJobOwnerDetails(
			ManageAccessPermissionForm manageAccessPermissionForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			List<ManageAccessPermissionDTO> jbOwnerList = manageAccessPermissionService
					.getJobOwnerList((Integer) session
							.getAttribute(MMJBCommonConstants.FACILITY_ID),
							(Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID));
			model.addObject("jobOwners", jbOwnerList);
			manageAccessPermissionForm
					.setManageAccessPermissiondetails(jbOwnerList);
			model.addObject("manageAccessPermissionForm",
					manageAccessPermissionForm);
			model.setViewName("manageAccessPermission");
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return model;
	}

	@RequestMapping(value = "/addNewJobOwner")
	public ModelAndView addNewJobOwner(HttpSession session,
			ManageAccessPermissionForm manageAccessPermissionForm) {
		ModelAndView model = new ModelAndView();
		try {
			manageAccessPermissionForm.setFullAccess("5");
			model.addObject("manageAccessPermissionForm",
					manageAccessPermissionForm);
			model.setViewName("addNewJobOwner");
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return model;
	}

	@RequestMapping(value = "/saveNewJobOwner", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject saveNewJobOwner(HttpSession session,
			ManageAccessPermissionForm manageAccessPermissionForm) {
		JSONObject warningMessage = new JSONObject();
		try {
			EmployerProfileDTO empDTO = new EmployerProfileDTO();
			int facilityIdParent = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			int userIdParent = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);

			if (null != manageAccessPermissionForm) {
				if (null != manageAccessPermissionForm.getFullAccess()
						&& !manageAccessPermissionForm.getFullAccess()
								.isEmpty()) {
					empDTO.setRollId(Integer.valueOf(manageAccessPermissionForm
							.getFullAccess()));
				}
			}

			UserDTO userDTO = transformEmpReg
					.createUserDTOFromManageAccessForm(manageAccessPermissionForm);
			empDTO.setMerUserDTO(userDTO);

			manageAccessPermissionService.createJobOwner(empDTO,
					facilityIdParent, userIdParent);

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		warningMessage.put("success", "succes");
		return warningMessage;
	}

	@RequestMapping(value = "/deleteJobOwner", method = RequestMethod.POST)
	public @ResponseBody JSONObject deleteJobOwner(
			ManageAccessPermissionForm manageAccessPermissionForm,
			@RequestParam("userId") int userId) {

		JSONObject warningMessage = new JSONObject();
		try {

			manageAccessPermissionService.deleteJobOwner(userId);
			LOGGER.info("Request For - delete" + "user Id :" + userId);

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		warningMessage.put("success", "succes");
		return warningMessage;
	}

	@RequestMapping(value = "/updateJobOwner", method = RequestMethod.POST)
	public ModelAndView updateJobOwner(
			ManageAccessPermissionForm manageAccessPermissionForm) {
		ModelAndView model = new ModelAndView();
		try {

			/*
			 * manageAccessPermissionService
			 * .updateJobOwner(manageAccessPermissionForm
			 * .getManageAccessPermissiondetails());
			 */
			LOGGER.info("Request For - update user Id ");

			model.addObject("manageAccessPermissionForm",
					manageAccessPermissionForm);
			model.setViewName("forward:/employer/manageAccessPermission.html");
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return model;
	}
	
}
