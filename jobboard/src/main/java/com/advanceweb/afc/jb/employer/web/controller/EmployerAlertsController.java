package com.advanceweb.afc.jb.employer.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * <code>EmployerAlertsController</code>This controller helps to manage the
 * alerts.
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 04th sept 2012
 * 
 */
@Controller
@RequestMapping("/alerts")
public class EmployerAlertsController {

	/**
	 * The method is called to view the alerts.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer/viewAlerts", method = RequestMethod.GET)
	public ModelAndView viewAlerts(
			@ModelAttribute("alertForm") AlertForm alertForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		// model.addObject("saveSearchedJobsDTOList", saveSearchedJobsDTOList);
		model.addObject(alertForm);
		model.setViewName("viewAlertPopup");
		return model;
	}

	@RequestMapping(value = "/employer/setAlerts", method = RequestMethod.GET)
	public ModelAndView setAlerts(
			@ModelAttribute("alertForm") AlertForm alertForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("setAlertPopup");
		return model;
	}

}
