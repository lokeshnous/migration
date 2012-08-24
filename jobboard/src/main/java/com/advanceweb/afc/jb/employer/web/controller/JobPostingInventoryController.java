package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.login.web.controller.LoginForm;


/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 23rd August, 2012
 */

@Controller 
@RequestMapping("/inventory")
public class JobPostingInventoryController {

	/**
	 * This method to login in to the forgot your password page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer/jobInventory", method = RequestMethod.GET)
	public ModelAndView jobInventory(Map<String, LoginForm> model,
			@RequestParam(value = "page", required = false) String page,
			Model modelconstants) {
		//model.put("loginForm", loginForm);
		return new ModelAndView("jobPostingInventoryPopup");
	}
}
