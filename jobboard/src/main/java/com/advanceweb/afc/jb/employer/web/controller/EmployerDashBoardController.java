package com.advanceweb.afc.jb.employer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employer")
public class EmployerDashBoardController {

	@RequestMapping("/employerDashBoard")
	public ModelAndView displayDashBoard() {return new ModelAndView("employerDashboard");}
}
