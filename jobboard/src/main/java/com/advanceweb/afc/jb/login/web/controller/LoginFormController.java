package com.advanceweb.afc.jb.login.web.controller;

import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.OpenAMEUtility;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;

/**
 * This Class validates the login form
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

@Controller
@RequestMapping(value = "/commonLogin")
@SessionAttributes("loginForm")
public class LoginFormController {

	@Value("${jobseekerForgotPwdSub}")
	private String jobseekerForgotPwdSub;

	@Value("${jobseekerForgotPwdBody}")
	private String jobseekerForgotPwdBody;

	@Value("${loginErrMsg}")
	private String loginErrMsg;

	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	@Value("${navigationPath}")
	private String navigationPath;

	@Value("${invalidemail}")
	private String invalidmail;

	@Value("${notempty}")
	private String emptyerrormsg;

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	private static final Logger LOGGER = Logger
			.getLogger("LoginFormController.class");

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoginFormValidator loginValidator;

	/**
	 * This method to login
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "error", required = false) boolean error,
			@RequestParam(value = "page", required = false) String page,
			ModelMap model) {
		if (error) {
			model.put("error", loginErrMsg);
		} else {
			model.put("error", MMJBCommonConstants.EMPTY);
		}
		String pageValue = "";
		if (page.equals(MMJBCommonConstants.JOB_SEEKER)) {
			pageValue = "jobSeekerLogin";
		} else if (page.equals(MMJBCommonConstants.EMPLOYER)) {
			pageValue = "employerLogin";
		} else {
			pageValue = "agencyLogin";
		}
		return pageValue;
	}

	/**
	 * This method to login in to the forgot your password page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/forgrtPasswordLogin", method = RequestMethod.GET)
	public ModelAndView jobSeekerForgotYourPasswordPagePopUp(
			Map<String, LoginForm> model,
			@RequestParam(value = "page", required = false) String page,
			Model modelconstants) {
		LoginForm loginForm = new LoginForm();
		loginForm.setPage(page);
		model.put("loginForm", loginForm);
		modelconstants.addAttribute("MMJBCommonConstantserror",
				MMJBCommonConstants.ERROR_STRING);
		modelconstants.addAttribute("MMJBCommonConstantsok",
				MMJBCommonConstants.OK_STRING);

		return new ModelAndView("jobSeekerForgotPWDPopUp");
	}

	/**
	 * This Method is used for forgot password functionality
	 * 
	 * @param form
	 * @param result
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jobSeekerForgotPWDPopUp", method = RequestMethod.POST)
	public String emailThePassword(@Valid LoginForm form, BindingResult result,
			@RequestParam("email") String email, Model model,
			HttpServletRequest request) {
		String emailAddress = email;
		String finalresult = "";
		boolean value = false;
		String page = form.getPage();
		LoginDTO formDTO = loginService.getUserEmailDetails(emailAddress);

		// User Validation based on email address of user
		if (formDTO != null) {
			//value = loginValidator.validateEmailValues(email, formDTO, page);
		}

		// Sending mail to the logged in user if he is valid user
		if (email.length() < 0) {
			finalresult = MMJBCommonConstants.ERROR_STRING + ","
					+ emptyerrormsg;
		} else if (email.length() > 0 && value) {
			try {
				// Based on the login user need to send the email
				String loginPath = navigationPath.substring(2);
				String jonseekerloginUrl = MMJBCommonConstants.EMPTY;
				if (page.equals(MMJBCommonConstants.JOB_SEEKER)) {
					jonseekerloginUrl = request.getRequestURL().toString()
							.replace(request.getServletPath(), loginPath)
							+ dothtmlExtention + "?page=jobSeeker";
				} else if (page.equals(MMJBCommonConstants.EMPLOYER)) {
					jonseekerloginUrl = request.getRequestURL().toString()
							.replace(request.getServletPath(), loginPath)
							+ dothtmlExtention + "?page=employer";
				} else if (page.equals(MMJBCommonConstants.AGENCY)) {
					jonseekerloginUrl = request.getRequestURL().toString()
							.replace(request.getServletPath(), loginPath)
							+ dothtmlExtention + "?page=agency";
				}
				EmailDTO jobSeekerEmailDTO = new EmailDTO();
				jobSeekerEmailDTO.setFromAddress(advanceWebAddress);
				jobSeekerEmailDTO.setCcAddress(null);
				jobSeekerEmailDTO.setBccAddress(null);
				InternetAddress[] jobSeekerToAdd = new InternetAddress[1];
				jobSeekerToAdd[0] = new InternetAddress(email);
				jobSeekerEmailDTO.setToAddress(jobSeekerToAdd);
				jobSeekerEmailDTO.setSubject(jobseekerForgotPwdSub);

				// Automatic generated password from OpenAM
				String tempassword = OpenAMEUtility.newPassword();

				if (tempassword != null) {
					// Updating the generated password to OpenAm

					boolean updatepassword = OpenAMEUtility
							.openAMUpdatePassword(emailAddress, tempassword);

					// Updating the generated password to merUser table.
					try {
						loginService.saveNewPWD(emailAddress, tempassword);
					} catch (JobBoardException e) {
						LOGGER.info("Temporary password could not be generated");
					}

					String forgotPwdMailBody = jobseekerForgotPwdBody.replace(
							"?temporarypassword", tempassword);

					forgotPwdMailBody = forgotPwdMailBody.replace(
							"?jsLoginLink", jonseekerloginUrl);
					jobSeekerEmailDTO.setBody(forgotPwdMailBody);
					jobSeekerEmailDTO.setHtmlFormat(true);
					emailService.sendEmail(jobSeekerEmailDTO);
				}

			} catch (Exception e) {
				// loggers call
				LOGGER.info("ERROR  ");
			}
			finalresult = MMJBCommonConstants.OK_STRING;
		} else {
			finalresult = MMJBCommonConstants.ERROR_STRING + "," + invalidmail;
		}

		return finalresult;
	}
}
