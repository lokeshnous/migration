/**
 * 
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.jobseeker.service.CoverLetterService;

/**
 * This method is called for create cover letter
 * etc.
 * @author kartikm
 * @version V.0.1
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
		ResCoverLetterForm resCoverLetterForm = new ResCoverLetterForm();
		ModelAndView model = new ModelAndView();
		model.addObject(resCoverLetterForm);
		model.setViewName("jobseekerCreateCoverLetter");
		return model;
	}

	/**
	 * @author kartikm This is the save method call when date change and save
	 *         button click then it is Change from active, inactive, Draft,
	 *         Expired
	 * @param request
	 * @param session
	 * @param jobPostform
	 * @param result
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/jobseekerCoverLetterSub", method = RequestMethod.POST)
	public String getJobPostDetailsSave(HttpServletRequest request,
			HttpSession session, ResCoverLetterForm resCoverLetterForm,
			BindingResult result) {

		try {
			int userId = (Integer) session.getAttribute("userId");
			ResCoverLetterDTO dto = new ResCoverLetterDTO();
			dto.setName(resCoverLetterForm.getName());
			dto.setCoverletterText(resCoverLetterForm.getCoverletterText());
			dto.setActive(resCoverLetterForm.getActive());
			dto.setUserId(userId);
			boolean findFirstActive = coverLetterService.findFirstActiveStatus(
					userId, resCoverLetterForm.getActive());
			boolean findActive = coverLetterService.findActiveStatus(userId,
					resCoverLetterForm.getActive());
			boolean findName = coverLetterService.findNameActiveStatus(userId,
					resCoverLetterForm.getName());
			boolean findDuplicate = coverLetterService
					.findDuplicateActiveStatus(userId,
							resCoverLetterForm.getActive());
			if (findFirstActive) {
				coverLetterService.coverLetterSaveByjobSeeker(dto);
			} else if (!findActive) {
				return "max 5 cover letters can be created";
			} else if (findName) {
				return "Cover Letter Name already exists, Please try again";
			} else if (findDuplicate) {
				coverLetterService.coverLetterUpdateByjobSeeker(dto);
				coverLetterService.coverLetterSaveByjobSeeker(dto);
			} else {
				coverLetterService.coverLetterSaveByjobSeeker(dto);
			}

		} catch (Exception e) {
			LOGGER.info("error" + e);
			LOGGER.info("Manager Edit Job Posting Search Option");
		}
		return "";
	}

	/**
	 * This method is called to Account Setting update page and
	 * editAccountSetting method take Bean class binding result from Jsp pages
	 * Seession for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@RequestMapping(value = "/manageExistProfile", method = RequestMethod.GET)
	public ModelAndView manageExitProfileSettings(HttpSession session) {
		ModelAndView model = new ModelAndView();
		ResCoverLetterForm resCoverLetterForm = new ResCoverLetterForm();
		try {
			int userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			List<ResCoverLetterDTO> jbOwnerList = new ArrayList<ResCoverLetterDTO>();
			try {
				jbOwnerList = coverLetterService.getJobOwnerList(userId);
			} catch (JobBoardException jbex) {
				LOGGER.error("Error occured while updating the job owner", jbex);
			}
			model.addObject("jobOwners", jbOwnerList);
			model.addObject("employeeAccountForm", resCoverLetterForm);
			model.setViewName("jobSeekerManageExitWright");
		} catch (Exception e) {

			LOGGER.info("This is Account Addresss edite option error");
		}
		return model;
	}
	
	
	
	/**
	 * This method is called to Account Setting update page and
	 * editAccountSetting method take Bean class binding result from Jsp pages
	 * Seession for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@RequestMapping(value = "/deleteManageExistProfile", method = RequestMethod.GET)
	public ModelAndView deleteExitProfileSettings(HttpSession session) {
		ModelAndView model = new ModelAndView();
		ResCoverLetterForm resCoverLetterForm = new ResCoverLetterForm();
		try {
			int userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			int coverLetterId=36;
			boolean isdelete=coverLetterService.isDelete(userId,coverLetterId);
			
			List<ResCoverLetterDTO> jbOwnerList = new ArrayList<ResCoverLetterDTO>();
			try {
				jbOwnerList = coverLetterService.getJobOwnerList(userId);
			} catch (JobBoardException jbex) {
				LOGGER.error("Error occured while updating the job owner", jbex);
			}
			model.addObject("jobOwners", jbOwnerList);
			model.addObject("employeeAccountForm", resCoverLetterForm);
			model.setViewName("jobSeekerManageExitWright");
		} catch (Exception e) {

			LOGGER.info("This is Account Addresss edite option error");
		}
		return model;
	}
	
	@RequestMapping(value = "/jobseekerViewCoverLetter", method = RequestMethod.GET)
	public ModelAndView jobseekerViewCoverLetter(HttpServletRequest request,
			HttpSession session, ResCoverLetterForm resCoverLetterForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		
		try {
			int userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			String covId=request.getParameter("coverletterId");
			String covType=request.getParameter("type");
			int coverletterId=Integer.parseInt(covId);
			ResCoverLetterDTO listOfCoverLetter=coverLetterService.getCoverList(coverletterId);
			if (listOfCoverLetter!=null){
				resCoverLetterForm.setActive(listOfCoverLetter.getActive());
				resCoverLetterForm.setCoverletterId(listOfCoverLetter.getCoverletterId());
				resCoverLetterForm.setCoverletterText(listOfCoverLetter.getCoverletterText());
				resCoverLetterForm.setName(listOfCoverLetter.getName());
				resCoverLetterForm.setUserId(listOfCoverLetter.getUserId());
			}		
			
			model.addObject("covType", covType);
			model.addObject("employeeAccountForm", resCoverLetterForm);
			model.setViewName("viewEditCoverLetter");
		} catch (Exception e) {

			LOGGER.info("This is Account Addresss edite option error");
		}
		return model;
	}
	
	
	
	
}
