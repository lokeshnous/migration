package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Controller
@RequestMapping("jobSeeker")
public class JobSeekerDashBoardController {
	
	@RequestMapping("/jobSeekerDashBoard")
	public ModelAndView displayDashBoard(Map model){
		JobSeekerDashBoardForm form = new JobSeekerDashBoardForm();
		model.put("jobSeekerDashBoardForm", form);
		return new ModelAndView("jobseekerdashboard");		
	}
	
}
