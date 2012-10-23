package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.ManageAccessPermissionService;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * 
 * @author deviprasadm
 * @Created: Aug 03, 2012
 * @Purpose: This class will act as a Controller for the Manage Access
 *           Permission
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
	@Autowired
	private ProfileRegistration employerRegistration;
	@Value("${jobOwnerPwdBody}")
	private String jobOwnerPwdBody;
	@Value("${jobOwnerMailSubject}")
	private String jobOwnerMailSubject;
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	@Value("${navigationPath}")
	private String navigationPath;
	@Value("${jobOwnerExist}")
	private String jobOwnerExist;
	@Value("${jobOwnerAddSuccess}")
	private String jobOwnerAddSuccess;

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/manageAccessPermission")
	public ModelAndView showJobOwnerDetails(
			ManageAccessPermissionForm manageAccessPermissionForm,
			HttpSession session,
			@RequestParam(value = "page", required = false) String page) {
		LOGGER.info("showJobOwner Method");
		ModelAndView model = new ModelAndView();

		// Added for agency manage access permission task
		if (page != null && page.equals(MMJBCommonConstants.AGEN_PER_PAGE)) {
			manageAccessPermissionForm.setAgePermPage("true");
			session.setAttribute("agePermPage", "agePermPage");
		}

		List<ManageAccessPermissionDTO> jbOwnerList = new ArrayList<ManageAccessPermissionDTO>();
		try {
			jbOwnerList = manageAccessPermissionService
					.getJobOwnerList((Integer) session
							.getAttribute(MMJBCommonConstants.FACILITY_ID),
							(Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID));
		} catch (JobBoardException jbex) {
			LOGGER.error("Error occured while updating the job owner", jbex);
		}

		manageAccessPermissionForm.setTotalSize(jbOwnerList.size());
		model.addObject("jobOwners", jbOwnerList);
		manageAccessPermissionForm
				.setManageAccessPermissiondetails(jbOwnerList);
		model.addObject("manageAccessPermissionForm",
				manageAccessPermissionForm);
		model.setViewName("manageAccessPermission");

		return model;
	}

	@RequestMapping(value = "/addNewJobOwner")
	public ModelAndView addNewJobOwner(HttpSession session,
			ManageAccessPermissionForm manageAccessPermissionForm,
			@RequestParam(value = "page", required = false) String page) {
		ModelAndView model = new ModelAndView();
		manageAccessPermissionForm
				.setFullAccess(MMJBCommonConstants.FULL_ACCESS);
		if (page != null && page.equals(MMJBCommonConstants.SET_ALERT)) {
			manageAccessPermissionForm.setSetAlertPage("true");
		}
		model.addObject("manageAccessPermissionForm",
				manageAccessPermissionForm);
		model.setViewName("addNewJobOwner");

		return model;
	}

	@RequestMapping(value = "/saveNewJobOwner", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject saveNewJobOwner(HttpSession session,
			ManageAccessPermissionForm manageAccessPermissionForm,
			HttpServletRequest request) {
		LOGGER.info("Save Job Owner : Process to save new job Owner detail Starts !");
		JSONObject warningMessage = new JSONObject();

		EmployerProfileDTO empDTO = new EmployerProfileDTO();
		int facilityIdParent = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		int userIdParent = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);

		if (null != manageAccessPermissionForm
				&& null != manageAccessPermissionForm.getFullAccess()
				&& !manageAccessPermissionForm.getFullAccess().isEmpty()) {
			empDTO.setRollId(Integer.valueOf(manageAccessPermissionForm
					.getFullAccess()));
		}

		/**
		 * OpenAM code starts here for Validate Email-Id
		 * 
		 * @auther Santhosh Gampa
		 * @since Sep 4 2012
		 * 
		 */
		// boolean isinvaliduser = OpenAMEUtility
		// .openAMValidateEmail(manageAccessPermissionForm.getOwnerEmail());
		// if (isinvaliduser) {
		// LOGGER.info("OpenAM : user is already exist !");
		// warningMessage.put("failure", jobOwnerExist);
		// return warningMessage;
		// } else {
		// LOGGER.info("OpenAM : valid user!");
		// }

		// End of openAM code

		if (employerRegistration.validateEmail(manageAccessPermissionForm
				.getOwnerEmail())) {
			warningMessage.put("failure", jobOwnerExist);
			return warningMessage;
		}

		UserDTO userDTO = transformEmpReg
				.createUserDTOFromManageAccessForm(manageAccessPermissionForm);

		UserDTO userDTOID = loginService.getUser(manageAccessPermissionForm
				.getOwnerEmail());
		if (null != userDTOID && userDTOID.getUserId() > 0) {
			userDTO.setUserId(userDTOID.getUserId());
		}

		// try {
		// MerUser merUser = manageAccessPermissionService
		// .getUserListByEmail(manageAccessPermissionForm
		// .getOwnerEmail());
		// if (null != merUser && merUser.getUserId() > 0) {
		// userDTO.setUserId(merUser.getUserId());
		// }
		// } catch (JobBoardServiceException ex) {
		// LOGGER.error(ex);
		// }

		empDTO.setMerUserDTO(userDTO);
		try {
			manageAccessPermissionService.createJobOwner(empDTO,
					facilityIdParent, userIdParent);
		} catch (JobBoardException jbex) {
			LOGGER.error("Error occured while creating the new job owner", jbex);
		}

		sendEmail(manageAccessPermissionForm, userDTO, request, session);
		LOGGER.info("Email : sent Email!");
		warningMessage.put("success", jobOwnerAddSuccess);
		return warningMessage;
	}

	@RequestMapping(value = "/deleteJobOwner", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject deleteJobOwner(
			ManageAccessPermissionForm manageAccessPermissionForm,
			@RequestParam("userId") int userId) {

		JSONObject warningMessage = new JSONObject();

		try {
			manageAccessPermissionService.deleteJobOwner(userId);
		} catch (JobBoardException jbex) {
			LOGGER.error("Error occured while deleting the job owner", jbex);
		}
		LOGGER.info("Request For - delete" + "user Id :" + userId);

		warningMessage.put("success", "succes");
		return warningMessage;
	}

	@RequestMapping(value = "/updateJobOwner", method = RequestMethod.POST)
	public ModelAndView updateJobOwner(
			ManageAccessPermissionForm manageAccessPermissionForm) {
		ModelAndView model = new ModelAndView();

		try {
			LOGGER.info("Request For - update user Id ");
			if (null != manageAccessPermissionForm
					.getManageAccessPermissiondetails()
					&& manageAccessPermissionForm
							.getManageAccessPermissiondetails().size() > 0) {
				manageAccessPermissionService
						.updateJobOwner(manageAccessPermissionForm
								.getManageAccessPermissiondetails());
			}
		} catch (JobBoardException jbex) {
			LOGGER.error("Error occured while updating the job owner", jbex);
		}

		model.addObject("manageAccessPermissionForm",
				manageAccessPermissionForm);
		model.setViewName("forward:/employer/manageAccessPermission.html");

		return model;
	}

	/**
	 * method to send a email to the new job owner with the default password
	 * 
	 * @param emailId
	 * @param userDTO
	 * @param request
	 * @return
	 */
	public void sendEmail(
			ManageAccessPermissionForm manageAccessPermissionForm,
			UserDTO userDTO, HttpServletRequest request, HttpSession session) {
		EmailDTO emailDTO = new EmailDTO();
		InternetAddress[] employerToAddress = new InternetAddress[1];
		String loginPath = navigationPath.substring(2);
		String employerloginUrl;

		// Below condition differs for agency and employer.If owner is creating
		// from agency dash board then, in email login link should be agency
		// login and same applies to employer
		if (session.getAttribute(MMJBCommonConstants.AGEN_PER_PAGE) != null) {
			employerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + "?page=agency";
		} else {
			employerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + "?page=employer";
		}

		try {
			employerToAddress[0] = new InternetAddress(
					manageAccessPermissionForm.getOwnerEmail());
			emailDTO.setToAddress(employerToAddress);
			emailDTO.setFromAddress(advanceWebAddress);
			emailDTO.setCcAddress(null);
			emailDTO.setBccAddress(null);
			String emailSubject = jobOwnerMailSubject.replace("?EmployersName",
					userDTO.getLastName());
			emailDTO.setSubject(emailSubject);
			String forgotPwdMailBody = jobOwnerPwdBody.replace(
					"?temporarypassword", userDTO.getPassword());
			forgotPwdMailBody = forgotPwdMailBody.replace("?employerLoginLink",
					employerloginUrl);
			String permissionType = "Post/Edit Access";
			if (manageAccessPermissionForm.getFullAccess().equals(
					MMJBCommonConstants.FULL_ACCESS)) {
				permissionType = "Full Access";
			}
			forgotPwdMailBody = forgotPwdMailBody.replace("?permission",
					permissionType);
			emailDTO.setBody(forgotPwdMailBody);
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		} catch (AddressException ex) {
			LOGGER.error("Error:" + ex);
		}

	}
}
