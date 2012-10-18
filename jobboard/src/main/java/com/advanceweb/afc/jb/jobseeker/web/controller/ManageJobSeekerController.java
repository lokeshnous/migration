package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.ManageJobSeekerService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @Author :Devi Mishra
 * @Version: 1.0
 * @Created: Oct 09, 2012
 * @Purpose: This class will act as a Controller for the Manage Job Seeker
 */

@Controller
@RequestMapping("/employer")
public class ManageJobSeekerController {

	private static final Logger LOGGER = Logger
			.getLogger(ManageJobSeekerController.class);

	@Autowired
	private ManageJobSeekerService manageJobSeekerService;

	/**
	 * This method is called to display jobs list belonging to a logged in
	 * employer
	 * 
	 * @param request
	 * @param session
	 * @param manageJobSeekerForm
	 *  @param folderId
	 * @return
	 */
	@RequestMapping(value = "/manageJobSeeker")
	public ModelAndView getJobSeekerDetails(HttpServletRequest request,
			HttpSession session, ManageJobSeekerForm manageJobSeekerForm, @RequestParam("folderId") int folderId) {
		ModelAndView model = new ModelAndView();
		List<ManageJobSeekerDTO> manageJobSeekerDTOList = new ArrayList<ManageJobSeekerDTO>();
		List<DropDownDTO> appStatusList = new ArrayList<DropDownDTO>();
		List<AdmFolderDTO> admFolderDTOList = new ArrayList<AdmFolderDTO>();
		LOGGER.info("Folder Id:"+folderId);
		manageJobSeekerForm.setFolderId(folderId);
		try {
			if (folderId > 0) {
				manageJobSeekerDTOList =manageJobSeekerService.retrieveAllResumeByFolder(
						(Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID),
						folderId);
				model.setViewName("manageJobSeekerContent");
			} else if (folderId==0) {
				manageJobSeekerDTOList = manageJobSeekerService
						.retrieveAllResume((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
				model.setViewName("manageJobSeekerContent");
				
			}else{
				manageJobSeekerDTOList = manageJobSeekerService
						.retrieveAllResume((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
				model.setViewName("manageJobSeekers");
			}
			appStatusList = manageJobSeekerService.applicationStatusList();
			admFolderDTOList = manageJobSeekerService
					.folderDetailList((Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
		} catch (JobBoardServiceException jbex) {

			LOGGER.error("Error occured while Retriving Data", jbex);
		}
		if (null != admFolderDTOList && admFolderDTOList.size() > 0) {
			manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
		}
		if (null != manageJobSeekerDTOList && manageJobSeekerDTOList.size() > 0) {
			manageJobSeekerForm
					.setManageJobSeekerDTOList(manageJobSeekerDTOList);
		}
		model.addObject("manageJobSeekerForm", manageJobSeekerForm);
		model.addObject("appStatusList", appStatusList);
		model.addObject("manageJobSeekerDTOList", manageJobSeekerDTOList);
		

		return model;
	}
	/**
	 * Method is called to update the job details
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param manageJobSeekerForm
	 * @param appStatus
	 * @return
	 */
	@RequestMapping(value ="/updateJobSeeker", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject updateJobSeeker(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("appStatus") int appStatus) {
		int applicationStatus = 0;
		int resumeId=0;
		LOGGER.info("Update Job Seeker : Process to update the job seeker !");
		JSONObject warningMessage = new JSONObject();
		for (ManageJobSeekerDTO jobSeekerDTO : manageJobSeekerForm.getManageJobSeekerDTOList()) {
			if (jobSeekerDTO.getApplicationStatus() == appStatus) {
				applicationStatus = jobSeekerDTO.getApplicationStatus();
				resumeId=jobSeekerDTO.getFolderResumeId();
			}
		}

		if (applicationStatus>0 && resumeId > 0) {
			try {
				manageJobSeekerService
						.updateJobSeeker(applicationStatus,resumeId,3);
			} catch (JobBoardServiceException jbex) {
				LOGGER.error("Error occured while Updating Data", jbex);
			}
		}
		/*model.addObject("manageJobSeekerForm", manageJobSeekerForm);
		model.setViewName("redirect:/employer/manageJobSeeker.html?folderId="+manageJobSeekerForm.getFolderId());
		return model;*/
		warningMessage.put("success", "updated succesfully");
		return warningMessage;
	}
	/**
	 * Method is called Implement the Move to folder functionality
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param manageJobSeekerForm
	 * @return
	 */
	@RequestMapping(value = "/moveToFolder")
	public ModelAndView moveToFolderImpl(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("folderId") int folderId,
			@RequestParam("selectedVal") String selectedVal) {

		LOGGER.info("Move To Folder : Process to Implement Move to Folder Functionality !");
		ModelAndView model = new ModelAndView();
		List<AdmFolderDTO> admFolderDTOList = new ArrayList<AdmFolderDTO>();
		List<ManageJobSeekerDTO> manageJobSeekerDTOList = new ArrayList<ManageJobSeekerDTO>();

		List<DropDownDTO> appStatusList = new ArrayList<DropDownDTO>();

		String selectedRows = null;
		if (null != selectedVal && !selectedVal.isEmpty()) {
			selectedRows = selectedVal;
			manageJobSeekerForm.setSelectedRow(selectedVal);
		}

		try {
			admFolderDTOList = manageJobSeekerService
					.folderDetailList((Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
			appStatusList = manageJobSeekerService.applicationStatusList();
			model.addObject("appStatusList", appStatusList);
			if (null != admFolderDTOList && admFolderDTOList.size() > 0) {
				manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
			}
			if (null != selectedRows && folderId > 0) {
				StringTokenizer tokenize = new StringTokenizer(selectedRows,
						",");
				int folderResumeId = 0;
				while (tokenize.hasMoreTokens()) {
					folderResumeId = Integer.valueOf(tokenize.nextToken());
					manageJobSeekerService.updateResumeFolder(folderId,
							folderResumeId);
				}
				manageJobSeekerDTOList = manageJobSeekerService
						.retrieveAllResume((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
				if (null != manageJobSeekerDTOList
						&& manageJobSeekerDTOList.size() > 0) {
					manageJobSeekerForm
							.setManageJobSeekerDTOList(manageJobSeekerDTOList);
				}
				model.setViewName("manageJobSeekers");
			} else {
				model.setViewName("manageJobSeekerFolderView");
			}
		} catch (JobBoardServiceException jbex) {
			LOGGER.error("Error occured while Retriving folder detail", jbex);
		}

		model.addObject("manageJobSeekerForm", manageJobSeekerForm);

		return model;
	}
}
