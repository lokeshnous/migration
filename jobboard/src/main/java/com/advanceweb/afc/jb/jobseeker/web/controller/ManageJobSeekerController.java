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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.ManageJobSeekerService;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.resume.web.controller.CertificationsForm;
import com.advanceweb.afc.jb.resume.web.controller.CreateResume;
import com.advanceweb.afc.jb.resume.web.controller.EducationForm;
import com.advanceweb.afc.jb.resume.web.controller.LanguageForm;
import com.advanceweb.afc.jb.resume.web.controller.PhoneDetailForm;
import com.advanceweb.afc.jb.resume.web.controller.ReferenceForm;
import com.advanceweb.afc.jb.resume.web.controller.TransformCreateResume;
import com.advanceweb.afc.jb.resume.web.controller.WorkExpForm;
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

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private TransformCreateResume transCreateResume;

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
		if (null != admFolderDTOList && !admFolderDTOList.isEmpty()) {
			manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
		}
		if (null != manageJobSeekerDTOList && !manageJobSeekerDTOList.isEmpty()) {
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
			if (null != admFolderDTOList && !admFolderDTOList.isEmpty()) {
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
						&& !manageJobSeekerDTOList.isEmpty()) {
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
	/**
	 * Called to view resume 
	 * 
	 * @param createResume
	 * @param result
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/viewResume", method = RequestMethod.GET)
	public ModelAndView viewResume(CreateResume createResumed,
			BindingResult result, @RequestParam("resumeId") int resumeId,
			HttpServletRequest request, HttpServletResponse response) {
		/**
		 *  Introduced a new variable "createResumed" to resolve PMD issue. 
		 */
		CreateResume createResume =createResumed; 
		ModelAndView model = new ModelAndView();
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);
		createResume = transCreateResume.transformCreateResumeForm(resumeDTO);
		List<CertificationsForm> listCertForm = transCreateResume
				.transformCertForm(resumeDTO.getListCertDTO());
		List<ReferenceForm> listRefForm = transCreateResume
				.transformReferenceForm(resumeDTO.getListRefDTO());
		List<EducationForm> listEduForm = transCreateResume
				.transformEducationForm(resumeDTO.getListEduDTO());
		List<WorkExpForm> listWorkExpForm = transCreateResume
				.transformWorkExpForm(resumeDTO.getListWorkExpDTO());
		List<LanguageForm> listLangForm = transCreateResume
				.transformLanguageForm(resumeDTO.getListLangDTO());
		ContactInfoForm contactForm = transCreateResume
				.transformContactInfoForm(resumeDTO.getContactInfoDTO());
		List<PhoneDetailForm> listPhoneDtl = transCreateResume
				.transformPhoneDetailDTOToForm(resumeDTO.getListPhoneDtl());
		createResume.setbHideBackButton(true);
		createResume.setListCertForm(listCertForm);
		createResume.setListEduForm(listEduForm);
		createResume.setListLangForm(listLangForm);
		createResume.setListRefForm(listRefForm);
		createResume.setListWorkExpForm(listWorkExpForm);
		createResume.setContactInfoForm(contactForm);
		createResume.setListPhoneDtlForm(listPhoneDtl);
		resumeDTO.getContactInfoDTO();
		if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(createResume
				.getResumeType())) {
			model.addObject("createResume", createResume);
			model.setViewName("viewresume");
		} else if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume
				.getResumeType())) {
			try {
				model.setViewName("redirect:/jobSeekerResume/exportResume.html?fileName="
						+ resumeDTO.getFilePath());
				return model;
			} catch (Exception e) {
				LOGGER.info("Error in view resume builder",e);
			}
		} else {
			model.addObject("createResume", createResume);
			model.setViewName("viewCopyPasteResume");
		}
		return model;

	}
	
	/**
	 * Method is called to delete the job details
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param manageJobSeekerForm
	 * @param appStatus
	 * @return
	 */
	@RequestMapping(value ="/deleteJobSeekerDetails", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject deleteJobSeekerDetails(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("resumeId") int resumeId) {

		LOGGER.info("Delete Job Seeker : Process to delete the job seeker !");
		JSONObject warningMessage = new JSONObject();
		if (resumeId > 0) {
			try {
				manageJobSeekerService.deleteJobSeeker(resumeId);
			} catch (JobBoardServiceException jbex) {
				LOGGER.error("Error occured while Updating Data", jbex);
			}
		}
		warningMessage.put("success", "updated succesfully");
		return warningMessage;
	}
}
