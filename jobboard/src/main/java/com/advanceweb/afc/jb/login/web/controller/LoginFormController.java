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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.LoginFormDTO;
import com.advanceweb.afc.jb.common.email.EmailDTO;
import com.advanceweb.afc.jb.common.email.MMEmailService;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.login.service.LoginFormService;

/**
 * This Class validates the login form
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

@Controller
@RequestMapping(value = "/loginFormForJobSeeker")
@SessionAttributes("loginForm")
public class LoginFormController {

	@Value("${mail_subject}")
	private String mailSubject;

	@Value("${mail_body}")
	private String mailBody;

	@Value("$(loginvalidation.message)")
	private String loginValidationMsg;

	@Value("${invalidemail}")
	private String invalidmail;

	@Value("${notempty}")
	private String emptyerrormsg;

	private static final Logger LOGGER = Logger
			.getLogger("LoginFormController.class");

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
	public ModelAndView jobSeekerLogin(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
		LoginForm loginForm = new LoginForm();
		model.setViewName("jobSeekerLogin");
		model.addObject("loginForm", loginForm);
        model.addObject("postjobfeatures", true);
		if(request.getParameter("id")!=null && request.getParameter("id").equalsIgnoreCase(MMJBCommonConstants.POST_RESUME)){
	        model.addObject("postjobfeatures", false);
		}
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
			BindingResult result) {
        
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
			return new ModelAndView("jobSeekerDashBoard");
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
			Map<String, LoginForm> model,Model modelconstants) {

		model.put("loginForm", new LoginForm());
		modelconstants.addAttribute("MMJBCommonConstantserror", MMJBCommonConstants.ERROR_STRING);
		modelconstants.addAttribute("MMJBCommonConstantsok", MMJBCommonConstants.OK_STRING);

		return new ModelAndView("jobSeekerForgotYourPasswordPagePopUp");
	}

	/**
	 * This Method is used for forgot password functionality
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jobSeekerForgotYourPasswordPagePopUp", method = RequestMethod.POST)
	public String emailThePassword(@Valid LoginForm form,
			BindingResult result,@RequestParam("email") String email,Model model) {
		String emailAddress = email;
		String finalresult="";
		boolean value = false;
        
		LoginFormDTO userDetailsLoginFormDTO = loginFormService
				.getUserEmailDetails(emailAddress);

		// User Validation based on email address of user
		if (userDetailsLoginFormDTO != null) {
			value = loginFormValidator.validateEmailValues(email,
					userDetailsLoginFormDTO);
		}

		// Sending mail to the logged in user if he is valid user
        if(!(email.length()>0)){
			finalresult=MMJBCommonConstants.ERROR_STRING+","+emptyerrormsg;
		}else if (email.length()>0 && value) {
			try {
				EmailDTO jobSeekerEmailDTO = new EmailDTO();
				// jobSeekerEmailDTO.setFromAddress(form.getEmailAddress());
				jobSeekerEmailDTO.setCcAddress(null);
				jobSeekerEmailDTO.setBccAddress(null);
				InternetAddress[] jobSeekerToAddress = new InternetAddress[1];
				jobSeekerToAddress[0] = new InternetAddress(
				//		form.getEmailAddress());
						email);
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
			finalresult=MMJBCommonConstants.OK_STRING;
		}else{
			finalresult=MMJBCommonConstants.ERROR_STRING+","+invalidmail;
		}
		
		return finalresult;
	}
}
