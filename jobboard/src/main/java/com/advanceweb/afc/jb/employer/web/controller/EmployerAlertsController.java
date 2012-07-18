package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <code>EmployerAlertsController</code>This controller helps to manage the
 * alerts.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 16 July 2012
 * 
 */
@Controller
@RequestMapping("/employerAlerts")
public class EmployerAlertsController {

//	@Autowired
//	EmployerAlerts employerAlerts;

	/**
	 * The method is called to set the alerts and send mail to 
	 * job owner.
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/setAlerts", method = RequestMethod.GET)
	public ModelAndView setAlerts(Map model) {
		/**
		 * Get the events by DB or server
		 * Get the list of  job Owners
		 * Get the email of job owner and send mail after saving in DB
		 */
//		CompanyProfileDTO 
//		employerAlerts.setAlerts(model);
		return new ModelAndView("setAlertPopUp");
	}
}
