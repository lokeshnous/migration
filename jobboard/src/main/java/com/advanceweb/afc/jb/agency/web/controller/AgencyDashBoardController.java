package com.advanceweb.afc.jb.agency.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 21st August, 2012
 * 
 */

@Controller
@RequestMapping("/agency")
public class AgencyDashBoardController {
	@RequestMapping("/agencyDashboard")
	public ModelAndView displayDashBoard() {
		return new ModelAndView("agencyDashboard");
	}
}
