/**
 * 
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



/**
 * @author kartikm
 *
 */
@Controller
@RequestMapping("/jobSeekerCoverLetter")
@Scope("session")
public class JobseekerCoverLetterController {
	private static final Logger LOGGER = Logger
			.getLogger("JobseekerCoverLetterController.class");
	
	@RequestMapping(value = "/createCoverLetter", method = RequestMethod.GET)
	public ModelAndView jobSeekerCoverPage(ModelMap map) {
		ModelAndView model = new ModelAndView();
		model.setViewName("jobseekerCreateCoverLetter");
		return model;
	}
}
