package com.advanceweb.afc.jb.login.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

@Controller
public class LoginFormController {

	@Autowired
	private LoginFormService loginFormService;

	@Autowired
	private LoginFormValidator loginFormValidator;

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public ModelAndView jobSeekerLogin(@Valid LoginForm form,
			BindingResult result) {

		return new ModelAndView("JobSeekerLogin");
	}

	@RequestMapping(value = "/JobSeekerLogin", method = RequestMethod.GET)
	public ModelAndView validateLogin(@Valid LoginForm form,
			BindingResult result) {

		String emailAddress = form.getEmailAddress();
		String password = form.getPassword();

		LoginFormDTO loginFormDTO = new LoginFormDTO();
		loginFormDTO.setEmailAddress(form.getEmailAddress());
		loginFormDTO.setPassword(form.getPassword());
		loginFormDTO.setRoleId(form.getRoleId());

		emailAddress = "manish@yahoo.com";
		password = "deo";

		// Get the details of logged in user using email and password
		LoginFormDTO loginFormDTOForUser = (LoginFormDTO) loginFormService
				.validateLoginFormValues(emailAddress, password);

		if (loginFormDTOForUser != null) {

			loginFormValidator.validateLoginValues(form, loginFormDTOForUser);
		}

		return null;
	}
}
