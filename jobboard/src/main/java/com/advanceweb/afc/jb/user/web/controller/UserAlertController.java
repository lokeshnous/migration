package com.advanceweb.afc.jb.user.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.web.controller.UserAlertForm;
import com.advanceweb.afc.jb.user.UserAlertService;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept, 2012
 */

@Controller
@RequestMapping("/alerts")
public class UserAlertController {

	private static final Logger LOGGER = Logger
			.getLogger(UserAlertController.class);

	@Autowired
	private UserAlertService alertService;

	/**
	 * The method is called to view the alerts.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer/viewAlerts", method = RequestMethod.GET)
	public ModelAndView viewAlerts(
			@ModelAttribute("alertForm") UserAlertForm alertForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		List<UserAlertDTO> alertDTOs = alertService.viewalerts(userId);
		// model.addObject("saveSearchedJobsDTOList", saveSearchedJobsDTOList);
		model.addObject(alertForm);
		model.setViewName("viewAlertPopup");
		return model;
	}

	@RequestMapping(value = "/employer/setAlerts", method = RequestMethod.GET)
	public ModelAndView setAlerts(
			@ModelAttribute("alertForm") UserAlertForm alertForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("setAlertPopup");
		return model;
	}

}
