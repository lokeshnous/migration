/**
 * 
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.jobseeker.service.CoverLetterService;



/**
 * @author kartikm
 *
 */
@Controller
@RequestMapping("/jobSeekerCoverLetter")
@SessionAttributes("resCoverLetterForm")
@Scope("session")
public class JobseekerCoverLetterController {
	private static final Logger LOGGER = Logger
			.getLogger("JobseekerCoverLetterController.class");
	@Autowired
	CoverLetterService coverLetterService;
	@RequestMapping(value = "/createCoverLetter", method = RequestMethod.GET)
	public ModelAndView jobSeekerCoverPage(ModelMap map) {
		ResCoverLetterForm resCoverLetterForm=new ResCoverLetterForm();
		ModelAndView model = new ModelAndView();
		model.addObject(resCoverLetterForm);
		model.setViewName("jobseekerCreateCoverLetter");
		return model;
	}
	/**
	 * @author kartikm
	 * This is the save method call when date change and save button 
	 * click then it is Change from active, inactive, Draft, Expired 
	 * @param request
	 * @param session
	 * @param jobPostform
	 * @param result
	 * @return
	 */
	
	
	@ResponseBody
	@RequestMapping(value = "/jobseekerCoverLetterSub", method = RequestMethod.POST)
	public String getJobPostDetailsSave(HttpServletRequest request,
			HttpSession session, ResCoverLetterForm resCoverLetterForm,BindingResult result) {

		try{
			int userId = (Integer) session.getAttribute("userId");
			//boolean findActive=coverLetterService.findActiveStatus(userId,resCoverLetterForm.getActive());
			//if(findActive){
			//	return "Error";
			//}else{
			ResCoverLetterDTO dto=new ResCoverLetterDTO();
			dto.setName(resCoverLetterForm.getName());
			dto.setCoverletterText(resCoverLetterForm.getCoverletterText());
			dto.setActive(resCoverLetterForm.getActive());
			dto.setUserId(userId);
			coverLetterService.coverLetterSaveByjobSeeker(dto);
		//	}
		}catch (Exception e) {
			LOGGER.info("Manager Edit Job Posting Search Option");
		}
		return "";
	}
	
}
