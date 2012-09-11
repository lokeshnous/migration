package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.advanceweb.afc.jb.common.util.OpenAMEUtility;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.job.service.ManageAccessPermissionService;
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
	@Value("${jobseekerForgotPwdSub}")
	private String jobseekerForgotPwdSub;
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	@Value("${navigationPath}")
	private String navigationPath;
	@Autowired
	private MMEmailService emailService;
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
			ManageAccessPermissionForm manageAccessPermissionForm,
			@RequestParam(value = "page", required = false) String page) {
		ModelAndView model = new ModelAndView();
		try {
			manageAccessPermissionForm.setFullAccess(MMJBCommonConstants.FULL_ACCESS);
			if (page.equals(MMJBCommonConstants.SET_ALERT)) {
				manageAccessPermissionForm.setSetAlertPage("true");
			}
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
			ManageAccessPermissionForm manageAccessPermissionForm,HttpServletRequest request) throws JobBoardDataException {
		LOGGER.info("Save Job Owner : Process to save new job Owner detail Starts !");
		JSONObject warningMessage = new JSONObject();
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
		
		/**
		 * OpenAM code starts here for Validate Email-Id
		 * 
		 * @auther Santhosh Gampa
		 * @since Sep 4 2012
		 * 
		 */
		boolean isinvaliduser = OpenAMEUtility
				.openAMValidateEmail(manageAccessPermissionForm
						.getOwnerEmail());
		if (isinvaliduser) {
			LOGGER.info("OpenAM : user is already exist !");
			warningMessage.put("failure", "Email Id already Exists!");
			return warningMessage;
		} else {
			LOGGER.info("OpenAM : valid user!");
		}

		// End of openAM code

		if (employerRegistration.validateEmail(manageAccessPermissionForm
				.getOwnerEmail())) {
			warningMessage.put("failure", "Email Id already Exists!");
			return warningMessage;
		}
		UserDTO userDTO = transformEmpReg
				.createUserDTOFromManageAccessForm(manageAccessPermissionForm);
		empDTO.setMerUserDTO(userDTO);
		
		manageAccessPermissionService.createJobOwner(empDTO,
				facilityIdParent, userIdParent);
		sendEmail(manageAccessPermissionForm
				.getOwnerEmail(),userDTO,request);

		warningMessage.put("success", "succes");
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

			manageAccessPermissionService
					.updateJobOwner(manageAccessPermissionForm
							.getManageAccessPermissiondetails());

			LOGGER.info("Request For - update user Id ");

			model.addObject("manageAccessPermissionForm",
					manageAccessPermissionForm);
			model.setViewName("forward:/employer/manageAccessPermission.html");
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
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
	public void sendEmail(String emailId, UserDTO userDTO,
			HttpServletRequest request) {
		EmailDTO emailDTO = new EmailDTO();
		InternetAddress[] employerToAddress = new InternetAddress[1];
		String loginPath = navigationPath.substring(2);
		String employerloginUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), loginPath)
				+ dothtmlExtention + "?page=employer";
		try {
			employerToAddress[0] = new InternetAddress(emailId);
			emailDTO.setToAddress(employerToAddress);
			emailDTO.setFromAddress(advanceWebAddress);
			emailDTO.setCcAddress(null);
			emailDTO.setBccAddress(null);
			emailDTO.setSubject(jobseekerForgotPwdSub);
			String forgotPwdMailBody = jobOwnerPwdBody.replace(
					"?temporarypassword", userDTO.getPassword());
			forgotPwdMailBody = forgotPwdMailBody.replace("?employerLoginLink",
					employerloginUrl);
			emailDTO.setBody(forgotPwdMailBody);
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		} catch (AddressException e) {
			e.printStackTrace();
		}

	}
}
