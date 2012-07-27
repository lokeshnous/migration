package com.advanceweb.afc.jb.login.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.LoginFormDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.login.service.LoginFormService;

/**
 * This Class validates the login form
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

@Controller
@RequestMapping(value = "/loginFormForJObSeeker")
@SessionAttributes("loginForm")
public class LoginFormController {

	@Value("${mail_subject}")
	private String mailSubject;

	@Value("${mail_body}")
	private String mailBody;

	@Value("$(loginvalidation.message)")
	private String loginValidationMsg;

	private static final Logger LOGGER = Logger
			.getLogger("JobSearchActivityController.class");

	@Autowired
	private MMEmailService emailService;

	@Autowired
	private LoginFormService loginFormService;

	@Autowired
	private LoginFormValidator loginFormValidator;

	/**
	 * This method to login
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView jobSeekerLogin() {

		/*
		 * model.put("loginForm", new LoginForm()); return new
		 * ModelAndView("jobSeekerLogin");
		 */

		ModelAndView model = new ModelAndView();
		LoginForm loginForm = new LoginForm();
		model.setViewName("jobSeekerLogin");
		model.addObject("loginForm", loginForm);
		return model;
	}

	/**
	 * This method gets the userId and roleId based on the logged in user email
	 * and password Also it validates whether logged in user is authorized user
	 * or not
	 * 
	 * @param form
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jobSeekerLogin", method = RequestMethod.POST)
	public ModelAndView validateLogin(
			@ModelAttribute("loginForm") @Valid LoginForm loginForm,
			BindingResult result, Map map) {

		ModelAndView model = new ModelAndView();
        
		//programmatic validation is done here which validates based on patters
		//loginFormValidator.validate(loginForm, result);

		boolean value = false;

		loginForm.setRoleId(2);
		String emailAddress = loginForm.getEmailAddress();
		String password = loginForm.getPassword();

		// Get the details of logged in user using email and password
		LoginFormDTO loginFormDTOForUser = (LoginFormDTO) loginFormService
				.validateLoginFormValues(emailAddress, password);

		if (loginFormDTOForUser != null) {
			value = loginFormValidator.validateLoginValues(loginForm,
					loginFormDTOForUser);
		}
		if (value) {
			return new ModelAndView("jobboardadvancedsearch");
		} else {
			return new ModelAndView("jobSeekerLogin","message","Login Failure.The User Name/Password you have entered is invalid Or you are not authorized to Login to the site.");
		}

	}

	/**
	 * This method to login in to the forgot your password page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/forgrtPasswordLogin", method = RequestMethod.GET)
	public ModelAndView jobSeekerForgotYourPasswordPagePopUp(
			Map<String, LoginForm> model) {

		model.put("loginForm", new LoginForm());
		return new ModelAndView("jobSeekerForgotYourPasswordPagePopUp");
	}

	/**
	 * This Method is used for forgot password functionality
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/jobSeekerForgotYourPasswordPagePopUp", method = RequestMethod.POST)
	public ModelAndView emailThePassword(@Valid LoginForm form,
			BindingResult result) {
		String emailAddress = form.getEmailAddress();
		boolean value = false;

		LoginFormDTO userDetailsLoginFormDTO = loginFormService
				.getUserEmailDetails(emailAddress);

		// User Validation based on email address of user
		if (userDetailsLoginFormDTO != null) {
			value = loginFormValidator.validateEmailValues(form,
					userDetailsLoginFormDTO);
		}

		// Sending mail to the logged in user if he is valid user
		if (value) {
			try {
				EmailDTO jobSeekerEmailDTO = new EmailDTO();
				// jobSeekerEmailDTO.setFromAddress(form.getEmailAddress());
				jobSeekerEmailDTO.setCcAddress(null);
				jobSeekerEmailDTO.setBccAddress(null);
				InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
				jobSeekerToAddress[0] = new InternetAddress(
						form.getEmailAddress());
				jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
				jobSeekerEmailDTO.setSubject(mailSubject);
				jobSeekerEmailDTO.setBody(mailBody);
				jobSeekerEmailDTO
						.setBody(userDetailsLoginFormDTO.getPassword());
				jobSeekerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(jobSeekerEmailDTO);
			} catch (Exception e) {
				// loggers call
				LOGGER.info("ERROR");
			}
			return new ModelAndView("jobSeekerLogin");
		}
		return new ModelAndView("jobSeekerForgotYourPasswordPagePopUp","message","Please Enter valid Email Address");
	}
}
