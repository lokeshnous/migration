/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
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

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.ManageAccessPermissionService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.user.UserAlertService;
import com.advanceweb.afc.jb.user.UserService;

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
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ManageAccessPermissionController.class);

	/** The manage access permission service. */
	@Autowired
	private ManageAccessPermissionService manageAccessPermissionService;
	
	/** The transform emp reg. */
	@Autowired
	private TransformEmployerRegistration transformEmpReg;
	
	/** The employer registration. */
	@Autowired
	private ProfileRegistration employerRegistration;
	
	/** The job owner pwd body. */
	@Value("${jobOwnerPwdBody}")
	private String jobOwnerPwdBody;
	
	/** The job owner mail subject. */
	@Value("${jobOwnerMailSubject}")
	private String jobOwnerMailSubject;
	
	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	/** The dothtml extention. */
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	/** The facility service. */
	@Autowired
	private FacilityService facilityService;
	
	/** The navigation path. */
	@Value("${navigationPath}")
	private String navigationPath;
	
	/** The job owner exist. */
	@Value("${jobOwnerExist}")
	private String jobOwnerExist;
	
	/** The job owner add success. */
	@Value("${jobOwnerAddSuccess}")
	private String jobOwnerAddSuccess;

	/** The email service. */
	@Autowired
	private MMEmailService emailService;

	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The email configuration. */
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	/** The alert service. */
	@Autowired
	private UserAlertService alertService;
	
	/**
	 * Show job owner details.
	 *
	 * @param manageAccessPermissionForm the manage access permission form
	 * @param session the session
	 * @param page the page
	 * @return the model and view
	 */
	@RequestMapping(value = "/manageAccessPermission")
	public ModelAndView showJobOwnerDetails(
			ManageAccessPermissionForm manageAccessPermissionForm,
			HttpSession session,
			@RequestParam(value = "page", required = false) String page) {
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

	/**
	 * Adds the new job owner.
	 *
	 * @param session the session
	 * @param manageAccessPermissionForm the manage access permission form
	 * @param page the page
	 * @return the model and view
	 */
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

	/**
	 * Save new job owner.
	 *
	 * @param session the session
	 * @param manageAccessPermissionForm the manage access permission form
	 * @param request the request
	 * @return the jSON object
	 */
	@RequestMapping(value = "/saveNewJobOwner", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject saveNewJobOwner(HttpSession session,
			ManageAccessPermissionForm manageAccessPermissionForm,
			HttpServletRequest request) {
		LOGGER.debug("Save Job Owner : Process to save new job Owner detail Starts !");
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

		

		if (employerRegistration.validateEmail(manageAccessPermissionForm
				.getOwnerEmail())) {
			warningMessage.put("failure", jobOwnerExist);
			return warningMessage;
		}

		UserDTO userDTO = transformEmpReg
				.createUserDTOFromManageAccessForm(manageAccessPermissionForm);

		UserDTO userDTOID = userService.getUser(manageAccessPermissionForm
				.getOwnerEmail());
		if (null != userDTOID && userDTOID.getUserId() > 0) {
			userDTO.setUserId(userDTOID.getUserId());
		}

		 try {
		 UserDTO merUser = manageAccessPermissionService
		 .getUserListByEmail(manageAccessPermissionForm
		 .getOwnerEmail());
		 if (null != merUser && merUser.getUserId() > 0) {
		 userDTO.setUserId(merUser.getUserId());
		 }
		 } catch (JobBoardServiceException ex) {
		 LOGGER.error(ex);
		 }

		empDTO.setMerUserDTO(userDTO);
		try {
			manageAccessPermissionService.createJobOwner(empDTO,
					facilityIdParent, userIdParent);
		} catch (JobBoardException jbex) {
			LOGGER.error("Error occured while creating the new job owner", jbex);
		}
		EmployerInfoDTO facilityDetail =facilityService.facilityDetails(userIdParent);
		if(null !=facilityDetail){
		userDTO.setCompany(facilityDetail.getCustomerName());
		}
		String accessType=null;
		if(null !=manageAccessPermissionForm.getFullAccess()&& manageAccessPermissionForm.getFullAccess().equals("5") ){
			accessType="Full access";
		}else{
			accessType = "Post / Edit access";
		}
        String changeRgn=emailConfiguration
				.getProperty("admin.jobowner.added").trim(); 
        		
        changeRgn=changeRgn.replace("?companyName", userDTO.getCompany());
        changeRgn=changeRgn.replace("?accessType", accessType);

        // Send the mail to job owner with temporary password 
		sendAdministratorUpdateMail(manageAccessPermissionForm.getOwnerEmail(),
				request,
				changeRgn.replace("?temporarypassword", userDTO.getPassword()),
				session,null);
		
        // Send the mail to employer on interest
        FacilityDTO mainFacilityDTO = facilityService.getParentFacility(facilityIdParent);
        UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
				.getUserId());
		List<UserAlertDTO> alertDTOs = alertService.viewAlerts(mainuserDto.getUserId());
		if (null != alertDTOs && alertDTOs.size() > 0) {
			for (UserAlertDTO alertDTO : alertDTOs) {
				if (alertDTO.getAlertId() > 0
						&& alertDTO.getAlertId() == MMJBCommonConstants.ADMINISTRATOR_CHANGES) {
					LOGGER.debug("Employer has the administrator alerts");
					sendAdministratorUpdateMail(
							mainuserDto.getEmailId(),
							request,
							changeRgn.replace("?temporarypassword",
									userDTO.getPassword()), session,manageAccessPermissionForm.getOwnerEmail());
					break;
				}
			}
		}else{
			LOGGER.debug("Employer has the default administrator alerts");
			sendAdministratorUpdateMail(
					mainuserDto.getEmailId(),
					request,
					changeRgn.replace("?temporarypassword",
							userDTO.getPassword()), session,manageAccessPermissionForm.getOwnerEmail());
		}
		//sendEmail(manageAccessPermissionForm, userDTO, request, session);
		LOGGER.debug("Email : sent Email!");
		warningMessage.put("success", jobOwnerAddSuccess);
		return warningMessage;
	}

	/**
	 * Delete job owner.
	 *
	 * @param manageAccessPermissionForm the manage access permission form
	 * @param userId the user id
	 * @return the jSON object
	 */
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
		LOGGER.debug("Request For - delete" + "user Id :" + userId);

		warningMessage.put("success", "succes");
		return warningMessage;
	}

	/**
	 * Update job owner.
	 *
	 * @param manageAccessPermissionForm the manage access permission form
	 * @param request the request
	 * @param session the session
	 * @return the model and view
	 */
	@RequestMapping(value = "/updateJobOwner", method = RequestMethod.POST)
	public ModelAndView updateJobOwner(
			ManageAccessPermissionForm manageAccessPermissionForm,
			HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();

		try {
			LOGGER.debug("Request For - update access permission ");
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
		StringBuffer mailBody = new StringBuffer();
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
			mailBody.append(emailConfiguration.getProperty(
					"employer.email.header").trim());
			mailBody.append(forgotPwdMailBody);
			mailBody.append(emailConfiguration.getProperty("email.footer")
					.trim());
			emailDTO.setBody(mailBody.toString());
			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
		} catch (AddressException ex) {
			LOGGER.error("Error:" + ex);
		}

	}
	/**
	 * method to send mail when change made by administrator
	 * @param form
	 */
	public void sendAdministratorUpdateMail(String email,
			HttpServletRequest request, String ChangeRsn, HttpSession session,
			String employerEmail) {
		UserDTO merUserdto = null;
		if (null != employerEmail) {
			merUserdto = userService.getUser(employerEmail);
		} else {
			merUserdto = userService.getUser(email);
		}
		EmployerInfoDTO facilityDetail = facilityService
				.facilityDetails(merUserdto.getUserId());
		StringBuffer admChangeDetail = new StringBuffer();
		String userName = merUserdto.getFirstName() + " "
				+ merUserdto.getLastName();
		String employerloginUrl;
		if (session.getAttribute(MMJBCommonConstants.AGEN_PER_PAGE) != null) {
			employerloginUrl = request
					.getRequestURL()
					.toString()
					.replace(
							request.getServletPath(),
							emailConfiguration.getProperty(
									"agency.email.login.url").trim());
			// + dothtmlExtention + "?page=agency";
		} else {
			employerloginUrl = request
					.getRequestURL()
					.toString()
					.replace(
							request.getServletPath(),
							emailConfiguration.getProperty(
									"employer.email.login.url").trim());
			// + dothtmlExtention + "?page=employer";
		}
		String emailContent = emailConfiguration.getProperty(
				"new.jobowner.email.body").trim();

		emailContent = emailContent.replace("?userName", userName);

		emailContent = emailContent.replace("?companyName",
				facilityDetail.getCustomerName());

		emailContent = emailContent.replace("?empdashboardLink",
				employerloginUrl);

		emailContent = emailContent.replace("?changeType", ChangeRsn);
		EmailDTO emailDTO = new EmailDTO();
		InternetAddress[] jsToAddress = new InternetAddress[1];

		try {
			jsToAddress[0] = new InternetAddress(email);
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}
		emailDTO.setToAddress(jsToAddress);
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty(
				"new.jobowner.mail.subject").trim());
		admChangeDetail.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		admChangeDetail.append(emailContent);
		admChangeDetail.append(emailConfiguration.getProperty("email.footer")
				.trim());
		emailDTO.setBody(admChangeDetail.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}

}
