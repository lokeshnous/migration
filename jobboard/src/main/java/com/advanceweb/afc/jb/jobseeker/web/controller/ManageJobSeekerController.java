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
import com.advanceweb.afc.jb.web.utils.PDFGenerator;

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
	@Autowired
	private PDFGenerator pdfGenerator;

	/**
	 * This method is called to display jobs list belonging to a logged in
	 * employer
	 * 
	 * @param request
	 * @param session
	 * @param manageJobSeekerForm
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/manageJobSeeker")
	public ModelAndView getJobSeekerDetails(HttpServletRequest request,
			HttpSession session, ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("folderId") int folderId) {
		ModelAndView model = new ModelAndView();
		List<ManageJobSeekerDTO> manageJobSeekerDTOList = new ArrayList<ManageJobSeekerDTO>();
		List<DropDownDTO> appStatusList = new ArrayList<DropDownDTO>();
		List<AdmFolderDTO> admFolderDTOList = new ArrayList<AdmFolderDTO>();
		LOGGER.info("Folder Id:" + folderId);
		// String next = request.getParameter("next");
		manageJobSeekerForm.setFolderId(folderId);
		try {
			if (folderId > 0) {
				manageJobSeekerDTOList = manageJobSeekerService
						.retrieveAllResumeByFolder((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID),
								folderId);
				model.setViewName("manageJobSeekerContent");
			} else if (folderId == 0) {
				manageJobSeekerDTOList = manageJobSeekerService
						.retrieveAllResume((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
				model.setViewName("manageJobSeekerContent");

			} else {
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
	@RequestMapping(value = "/updateJobSeeker", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject updateAppStatus(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("appStatus") int appStatus,
			@RequestParam("resumeId") int resumeId) {
		LOGGER.info("Update Application Status : Process to update the Application Status !");
		JSONObject warningMessage = new JSONObject();
		if (appStatus > 0 && resumeId > 0) {
			try {
				manageJobSeekerService.updateAppStatus(appStatus, resumeId);
			} catch (JobBoardServiceException jbex) {
				LOGGER.error("Error occured while Updating Application Status",
						jbex);
			}
		}
		warningMessage.put("success", "updated succesfully");
		return warningMessage;
	}

	/**
	 * Method is called to update the rating of the resume
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param manageJobSeekerForm
	 * @param appStatus
	 * @return
	 */
	@RequestMapping(value = "/updateRating", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView updateRating(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("rating") int rating,
			@RequestParam("resumeId") int resumeId) {

		LOGGER.info("Update rating : Process to update the rating !");
		ModelAndView model = new ModelAndView();
		List<DropDownDTO> appStatusList = new ArrayList<DropDownDTO>();
		List<ManageJobSeekerDTO> manageJobSeekerDTOList = new ArrayList<ManageJobSeekerDTO>();
		int folderId = manageJobSeekerForm.getFolderId();
		if (rating > 0 && resumeId > 0) {
			try {
				manageJobSeekerService.updateRatings(rating, resumeId);

				if (folderId > 0) {
					manageJobSeekerDTOList = manageJobSeekerService
							.retrieveAllResumeByFolder((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID),
									folderId);
				} else {
					manageJobSeekerDTOList = manageJobSeekerService
							.retrieveAllResume((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID));
				}
				appStatusList = manageJobSeekerService.applicationStatusList();
				model.addObject("appStatusList", appStatusList);
			} catch (JobBoardServiceException jbex) {
				LOGGER.error("Error occured while Updating The Rating", jbex);
			}
		}
		if (null != manageJobSeekerDTOList && !manageJobSeekerDTOList.isEmpty()) {
			manageJobSeekerForm
					.setManageJobSeekerDTOList(manageJobSeekerDTOList);
		}
		model.addObject("manageJobSeekerForm", manageJobSeekerForm);
		model.setViewName("manageJobSeekerContent");
		return model;
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
		 * Introduced a new variable "createResumed" to resolve PMD issue.
		 */
		CreateResume createResume = createResumed;
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
				LOGGER.info("Error in view resume builder", e);
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
	@RequestMapping(value = "/deleteJobSeekerDetails", method = RequestMethod.POST)
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

	/**
	 * Method is called to add a new folder
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param manageJobSeekerForm
	 * @param appStatus
	 * @return
	 */
	@RequestMapping(value = "/addFolder", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView addFolder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("folderName") String folderName) {

		LOGGER.info("Add New Folder : Process to Add New Folder !");

		List<DropDownDTO> appStatusList = new ArrayList<DropDownDTO>();
		List<AdmFolderDTO> admFolderDTOList = new ArrayList<AdmFolderDTO>();
		List<ManageJobSeekerDTO> manageJobSeekerDTOList = new ArrayList<ManageJobSeekerDTO>();
		ModelAndView model = new ModelAndView();
		if (null != folderName) {
			if (folderName.length() <= 0) {
				folderName = "New Folder";
			}
			try {
				manageJobSeekerService.addFolder((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID), folderName);
				admFolderDTOList = manageJobSeekerService
						.folderDetailList((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
				appStatusList = manageJobSeekerService.applicationStatusList();
				manageJobSeekerDTOList = manageJobSeekerService
						.retrieveAllResume((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
			} catch (JobBoardServiceException jbex) {
				LOGGER.error("Error occured while Updating Data", jbex);
			}
		}
		if (null != admFolderDTOList && !admFolderDTOList.isEmpty()) {
			manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
		}
		model.addObject("manageJobSeekerForm", manageJobSeekerForm);
		model.addObject("appStatusList", appStatusList);
		model.addObject("manageJobSeekerDTOList", manageJobSeekerDTOList);
		model.setViewName("manageJobSeekerFolder");
		return model;
	}

	/**
	 * Method is called to rename a folder
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param manageJobSeekerForm
	 * @param folderName
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/renameFolder", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView renameFolder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("folderName") String folderName,
			@RequestParam("folderId") int folderId) {

		LOGGER.info("Rename Folder : Process to Rename Folder !");
		List<AdmFolderDTO> admFolderDTOList = new ArrayList<AdmFolderDTO>();
		ModelAndView model = new ModelAndView();
		if (null != folderName) {

			try {
				manageJobSeekerService.renameFolder((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID), folderId,
						folderName);
				admFolderDTOList = manageJobSeekerService
						.folderDetailList((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
			} catch (JobBoardServiceException jbex) {
				LOGGER.error("Error occured while Updating Data", jbex);
			}
		}
		if (null != admFolderDTOList && !admFolderDTOList.isEmpty()) {
			manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
		}
		model.addObject("manageJobSeekerForm", manageJobSeekerForm);
		model.setViewName("manageJobSeekerFolder");
		return model;
	}

	/**
	 * Method is called to add a new folder
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param manageJobSeekerForm
	 * @param appStatus
	 * @return
	 */
	@RequestMapping(value = "/removeFolder", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView removeFolder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("folderName") String folderName) {

		LOGGER.info("Delete Folder : Process to Delete Folder !");

		List<AdmFolderDTO> admFolderDTOList = new ArrayList<AdmFolderDTO>();
		ModelAndView model = new ModelAndView();
		if (null != folderName) {
			if (folderName.length() <= 0) {
				folderName = "New Folder";
			}
			try {
				manageJobSeekerService.removeFolder((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID), folderName);
				admFolderDTOList = manageJobSeekerService
						.folderDetailList((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));

			} catch (JobBoardServiceException jbex) {
				LOGGER.error("Error occured while Updating Data", jbex);
			}
		}
		if (null != admFolderDTOList && !admFolderDTOList.isEmpty()) {
			manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
		}
		model.addObject("manageJobSeekerForm", manageJobSeekerForm);
		model.setViewName("manageJobSeekerFolder");
		return model;
	}

	/**
	 * This method is called to download an uploaded resume.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/downloadResume", method = RequestMethod.GET)
	public ModelAndView downloadResume(CreateResume createResumed,
			@RequestParam("resumeId") int resumeId, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		try {
			ResumeDTO resumeDTO = resumeService.editResume(resumeId);

			// if the resume Type is Upload then we download the Resume as is
			if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO
					.getResumeType())) {
				model.setViewName("redirect:/jobSeekerResume/exportResume.html?fileName="
						+ resumeDTO.getFilePath());
			} else {

				// if the Resume had been generated through Resume Builder or
				// CopyPaste
				// The resulting resume download will produce a PDF format
				pdfGenerator.generateAndExportResumeAsPdf(request, response,
						resumeDTO);
			}
		} catch (Exception e) {
			LOGGER.info("Error in download resume", e);
		}
		return model;

	}

	/**
	 * Method is called implement the compare job seeker functionality
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param manageJobSeekerForm
	 * @return
	 */
	@RequestMapping(value = "/compareResume")
	public ModelAndView compareResume(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("selectedVal") String selectedVal) {

		LOGGER.info("Move To Folder : Process to Implement compare job seeker Functionality !");
		ModelAndView model = new ModelAndView();
		List<ManageJobSeekerDTO> manageJobSeekerDTOList = new ArrayList<ManageJobSeekerDTO>();
		List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
		String selectedRows = null;
		if (null != selectedVal && !selectedVal.isEmpty()) {
			selectedRows = selectedVal;
			manageJobSeekerForm.setSelectedRow(selectedVal);
		}

		try {
			if (null != selectedRows) {

				StringTokenizer tokenize = new StringTokenizer(selectedRows,
						",");
				int folderResumeId = 0;

				manageJobSeekerDTOList = manageJobSeekerService
						.retrieveAllResume((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
				if (null != manageJobSeekerDTOList
						&& !manageJobSeekerDTOList.isEmpty()) {
					manageJobSeekerForm
							.setManageJobSeekerDTOList(manageJobSeekerDTOList);

					while (tokenize.hasMoreTokens()) {
						ResumeDTO resumeDTO = new ResumeDTO();
						folderResumeId = Integer.valueOf(tokenize.nextToken());
						for (ManageJobSeekerDTO manageJobSeekerDTO : manageJobSeekerDTOList) {

							if (folderResumeId == manageJobSeekerDTO
									.getFolderResumeId()) {
								resumeDTO = resumeService
										.editResume(manageJobSeekerDTO
												.getOrgResumeId());
								if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER
										.equals(resumeDTO.getResumeType())) {

									resumeDTOList.add(resumeDTO);
								} else {
									List<DropDownDTO> appStatusList = new ArrayList<DropDownDTO>();
									appStatusList = manageJobSeekerService
											.applicationStatusList();
									model.addObject("errorMsg",
											"One of the selected job seeker is not created by ADVANCE Resume Builder");

									model.addObject("appStatusList",
											appStatusList);
									model.setViewName("manageJobSeekers");
									model.addObject("manageJobSeekerForm",
											manageJobSeekerForm);

									return model;
								}
							}
						}

					}
				}
				manageJobSeekerForm.setTotalRecordForComp(resumeDTOList.size());
				manageJobSeekerForm.setResumeDTOList(resumeDTOList);
				model.setViewName("compareJobSeeker");
			}
		} catch (JobBoardServiceException jbex) {
			LOGGER.error("Error occured while Retriving resume detail", jbex);
		}

		model.addObject("manageJobSeekerForm", manageJobSeekerForm);

		return model;
	}

	/**
	 * Method to remove the selected job seeker details from the comparision
	 * list
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param manageJobSeekerForm
	 * @return
	 */
	@RequestMapping(value = "/removeCompareResume")
	public ModelAndView removeCompareResume(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ManageJobSeekerForm manageJobSeekerForm,
			@RequestParam("resumeId") int resumeId) {

		LOGGER.info("Move To Folder : Process to Implement compare job seeker Functionality !");
		ModelAndView model = new ModelAndView();
		List<ManageJobSeekerDTO> manageJobSeekerDTOList = new ArrayList<ManageJobSeekerDTO>();
		List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
		String selectedRows = null;
		if (null != manageJobSeekerForm.getSelectedRow()
				&& !manageJobSeekerForm.getSelectedRow().isEmpty()) {
			selectedRows = manageJobSeekerForm.getSelectedRow();
			manageJobSeekerForm.setSelectedRow(manageJobSeekerForm
					.getSelectedRow());
		}

		try {
			if (null != selectedRows) {

				StringTokenizer tokenize = new StringTokenizer(selectedRows,
						",");
				int folderResumeId = 0;

				manageJobSeekerDTOList = manageJobSeekerService
						.retrieveAllResume((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
				if (null != manageJobSeekerDTOList
						&& !manageJobSeekerDTOList.isEmpty()) {
					manageJobSeekerForm
							.setManageJobSeekerDTOList(manageJobSeekerDTOList);

					while (tokenize.hasMoreTokens()) {
						ResumeDTO resumeDTO = new ResumeDTO();
						folderResumeId = Integer.valueOf(tokenize.nextToken());
						for (ManageJobSeekerDTO manageJobSeekerDTO : manageJobSeekerDTOList) {

							if (folderResumeId == manageJobSeekerDTO
									.getFolderResumeId()) {
								resumeDTO = resumeService
										.editResume(manageJobSeekerDTO
												.getOrgResumeId());
								if (resumeId != resumeDTO.getUploadResumeId()) {
									resumeDTOList.add(resumeDTO);
								}
							}
						}

					}
				}
				manageJobSeekerForm.setTotalRecordForComp(resumeDTOList.size());
				manageJobSeekerForm.setResumeDTOList(resumeDTOList);
				model.setViewName("compareJobSeeker");
			}
		} catch (JobBoardServiceException jbex) {
			LOGGER.error("Error occured while Retriving resume detail", jbex);
		}

		model.addObject("manageJobSeekerForm", manageJobSeekerForm);

		return model;
	}

}
