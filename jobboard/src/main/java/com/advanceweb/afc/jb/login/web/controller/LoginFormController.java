package com.advanceweb.afc.jb.login.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.LoginFormDTO;
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
public class LoginFormController {

	@Autowired
	private LoginFormService loginFormService;

	@Autowired
	private LoginFormValidator loginFormValidator;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView jobSeekerLogin(Map<String, LoginForm> model) {

		model.put("loginForm", new LoginForm());
		return new ModelAndView("jobSeekerLogin");
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
	public ModelAndView validateLogin(@Valid LoginForm form,
			BindingResult result) throws Exception {

		boolean value = false;

		form.setRoleId(2);
		String emailAddress = form.getEmailAddress();
		String password = form.getPassword();

		LoginFormDTO loginFormDTO = new LoginFormDTO();
		loginFormDTO.setEmailAddress(form.getEmailAddress());
		loginFormDTO.setPassword(form.getPassword());
		loginFormDTO.setRoleId(form.getRoleId());

		// Get the details of logged in user using email and password
		LoginFormDTO loginFormDTOForUser = (LoginFormDTO) loginFormService
				.validateLoginFormValues(emailAddress, password);

		if (loginFormDTOForUser != null) {
			value = loginFormValidator.validateLoginValues(form,
					loginFormDTOForUser);
		}

		if (value) {
			return new ModelAndView("jobboardadvancedsearch");
		} else {
			return new ModelAndView("jobSeekerLogin", "errors", false);
		}

	}
}
