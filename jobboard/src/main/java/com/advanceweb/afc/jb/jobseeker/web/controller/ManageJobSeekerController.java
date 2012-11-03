
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.ManageJobSeekerService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
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
	private TransformCreateResume transCreateResume;
	@Autowired
	private PDFGenerator pdfGenerator;
	private static final String MANAGEJOBSEEKERFORM = "manageJobSeekerForm";
	private static final String ERRORMSG = "Error occured while Updating Data";
	private static final String APP_STATUS_LIST = "appStatusList";
	private static final String RESUMEID = "resumeId";
	
	
	@Autowired
	private MMEmailService emailService;


	@Autowired
	private ResumeService resumeService;


	@Autowired
	private JobSearchValidator jobSearchValidator;

	
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	

	private @Value("${SUBJECT_OF_SEND_RESUME_MAIL}")
	String subOfmail;

	private @Value("${BODY_OF_SEND_RESUME_MAIL}")
	String bodyOfMailFirst;

	private @Value("${URL_REDIRECT_MAIL}")
	String urlRedirectMail;

	private @Value("${ERROR_SENDING_MAIL}")
	String errSendingMail;

	private @Value("${EMAIL_MESSAGE}")
	String emailMsg;

	private @Value("${WEB_MAIL_SERVER}")
	String webMailServer;

	private @Value("${EMAIL_MESSAGE_BLANK}")
	String emailMsgBlank;
	private @Value("${basedirectorypathUpload}")
	String basedirectorypathUpload;
	
	private static final String CURRENT_URL = "currentUrl";
	private static final String END_TAGS = "</TD></TR>\n";
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
		 String next = request.getParameter("next");
		 int page = 1;
		 int displayRecordsPerPage=10;
		 if(null!=request.getParameter("noOfPage")){
			 displayRecordsPerPage =Integer.parseInt(request.getParameter("noOfPage"));
			 manageJobSeekerForm.setNoOfPage(displayRecordsPerPage);
			 manageJobSeekerForm.setNoOfPageLower(displayRecordsPerPage);
		 }
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			int recordsPerPage = 0;

			int noOfRecords = 0;
		manageJobSeekerForm.setFolderId(folderId);
		if ((Integer) session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			recordsPerPage =displayRecordsPerPage;
			try {
				if (folderId > 0) {
					manageJobSeekerDTOList = manageJobSeekerService
							.retrieveAllResumeByFolder((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID),
									folderId,(page - 1) * recordsPerPage, recordsPerPage);
					noOfRecords = manageJobSeekerService
							.getTotalNumberOfRecords((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID));
					model.setViewName("manageJobSeekerContent");
				} else if (folderId == 0) {
					manageJobSeekerDTOList = manageJobSeekerService
							.retrieveAllResume((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID),(page - 1) * recordsPerPage, recordsPerPage);
					model.setViewName("manageJobSeekerContent");
					noOfRecords = manageJobSeekerService
							.getTotalNumberOfRecords((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID));

				} else {
					manageJobSeekerDTOList = manageJobSeekerService
							.retrieveAllResume((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID),(page - 1) * recordsPerPage, recordsPerPage);
					model.setViewName("manageJobSeekers");
					noOfRecords = manageJobSeekerService
							.getTotalNumberOfRecords((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID));
				}
				appStatusList = manageJobSeekerService.applicationStatusList();
				admFolderDTOList = manageJobSeekerService
						.folderDetailList((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
			} catch (JobBoardServiceException jbex) {

				LOGGER.error("Error occured while Retriving Data", jbex);
			}
		}
		if (null != admFolderDTOList && !admFolderDTOList.isEmpty()) {
			manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
		}
		if (null != manageJobSeekerDTOList && !manageJobSeekerDTOList.isEmpty()) {
			manageJobSeekerForm
					.setManageJobSeekerDTOList(manageJobSeekerDTOList);
		}
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		if (null == next || !next.isEmpty()) {
			manageJobSeekerForm.setBeginVal((page / 10) * 10);
		} else {
			manageJobSeekerForm.setBeginVal(Integer.parseInt(next));
			page = Integer.parseInt(next);
		}

		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		model.addObject("begin", (manageJobSeekerForm.getBeginVal() <= 0 ? 1
				: manageJobSeekerForm.getBeginVal()));
		session.setAttribute(MMJBCommonConstants.MODULE_STRING, MMJBCommonConstants.MANAGEJOBSEEKER);
		model.addObject(MANAGEJOBSEEKERFORM, manageJobSeekerForm);
		model.addObject(APP_STATUS_LIST, appStatusList);
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
			@RequestParam(RESUMEID) int resumeId) {
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
			@RequestParam(RESUMEID) int resumeId) {

		LOGGER.info("Update rating : Process to update the rating !");
		ModelAndView model = new ModelAndView();
		List<DropDownDTO> appStatusList = new ArrayList<DropDownDTO>();
		List<ManageJobSeekerDTO> manageJobSeekerDTOList = new ArrayList<ManageJobSeekerDTO>();
		int folderId =0;
		if(null!=request.getParameter("folderId")){
			folderId =  Integer.parseInt(request.getParameter("folderId"));
		}
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
				model.addObject(APP_STATUS_LIST, appStatusList);
			} catch (JobBoardServiceException jbex) {
				LOGGER.error("Error occured while Updating The Rating", jbex);
			}
		}
		if (null != manageJobSeekerDTOList && !manageJobSeekerDTOList.isEmpty()) {
			manageJobSeekerForm
					.setManageJobSeekerDTOList(manageJobSeekerDTOList);
		}
		model.addObject(MANAGEJOBSEEKERFORM, manageJobSeekerForm);
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
			model.addObject(APP_STATUS_LIST, appStatusList);
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
				model.setViewName("redirect:/employer/manageJobSeeker.html?folderId=-1");
			} else {
				model.setViewName("manageJobSeekerFolderView");
			}
		} catch (JobBoardServiceException jbex) {
			LOGGER.error("Error occured while Retriving folder detail", jbex);
		}

		model.addObject(MANAGEJOBSEEKERFORM, manageJobSeekerForm);

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
			BindingResult result, @RequestParam(RESUMEID) int resumeId,
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
				model.setViewName("redirect:/employer/exportResume.html?fileName="
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
			@RequestParam(RESUMEID) int resumeId) {

		LOGGER.info("Delete Job Seeker : Process to delete the job seeker !");
		JSONObject warningMessage = new JSONObject();
		if (resumeId > 0) {
			try {
				manageJobSeekerService.deleteJobSeeker(resumeId);
			} catch (JobBoardServiceException jbex) {
				LOGGER.error(ERRORMSG, jbex);
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
			@RequestParam("folderName") String folderName1) {
		String folderName = folderName1;
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
				LOGGER.error(ERRORMSG, jbex);
			}
		}
		if (null != admFolderDTOList && !admFolderDTOList.isEmpty()) {
			manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
		}
		model.addObject(MANAGEJOBSEEKERFORM, manageJobSeekerForm);
		model.addObject(APP_STATUS_LIST, appStatusList);
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
				LOGGER.error(ERRORMSG, jbex);
			}
		}
		if (null != admFolderDTOList && !admFolderDTOList.isEmpty()) {
			manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
		}
		model.addObject(MANAGEJOBSEEKERFORM, manageJobSeekerForm);
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
			@RequestParam("folderName") String folderName1) {
		String folderName = folderName1;
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
				LOGGER.error(ERRORMSG, jbex);
			}
		}
		if (null != admFolderDTOList && !admFolderDTOList.isEmpty()) {
			manageJobSeekerForm.setAdmFolderDTOList(admFolderDTOList);
		}
		model.addObject(MANAGEJOBSEEKERFORM, manageJobSeekerForm);
		model.setViewName("manageJobSeekerFolder");
		return model;
	}

	/**
	 * This method is called to download  resume.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/downloadResume", method = RequestMethod.GET)
	public ModelAndView downloadResume(CreateResume createResumed,
			@RequestParam(RESUMEID) int resumeId, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		try {
			ResumeDTO resumeDTO = resumeService.editResume(resumeId);

			// if the resume Type is Upload then we download the Resume as is
			if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO
					.getResumeType())) {
				model.setViewName("redirect:/employer/exportResume.html?fileName="
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
	 * This method is called to print resume.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/printResume", method = RequestMethod.GET)
	public ModelAndView printResume(CreateResume createResumed,
			@RequestParam(RESUMEID) int resumeId, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		try {
			ResumeDTO resumeDTO = resumeService.editResume(resumeId);

			// if the resume Type is Upload then we download the Resume as is
			if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO
					.getResumeType())) {
				model.setViewName("redirect:/employer/exportResume.html?fileName="
						+ resumeDTO.getFilePath());
			} else {

				// if the Resume had been generated through Resume Builder or
				// CopyPaste
				// The resulting resume download will produce a PDF format
				pdfGenerator.generateAndExportResumeAsPdfForPrint(request, response,
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
								resumeDTO.setFolderResumeId(manageJobSeekerDTO.getFolderResumeId());
								if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER
										.equals(resumeDTO.getResumeType())) {

									resumeDTOList.add(resumeDTO);
								} else {
									List<DropDownDTO> appStatusList = new ArrayList<DropDownDTO>();
									appStatusList = manageJobSeekerService
											.applicationStatusList();
									model.addObject("errorMsg",
											"One of the selected job seeker is not created by ADVANCE Resume Builder");

									model.addObject(APP_STATUS_LIST,
											appStatusList);
									model.setViewName("forward:/employer/manageJobSeeker.html?folderId=-1");
									model.addObject(MANAGEJOBSEEKERFORM,
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

		model.addObject(MANAGEJOBSEEKERFORM, manageJobSeekerForm);

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
			@RequestParam(RESUMEID) int resumeId) {

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
				manageJobSeekerDTOList = manageJobSeekerService
						.retrieveAllResume((Integer) session
								.getAttribute(MMJBCommonConstants.USER_ID));
				if (null != manageJobSeekerDTOList
						&& !manageJobSeekerDTOList.isEmpty()) {
					addResumeDTOtoResumeDTOList(manageJobSeekerForm, resumeId,
							manageJobSeekerDTOList, resumeDTOList, tokenize);
				}
				manageJobSeekerForm.setTotalRecordForComp(resumeDTOList.size());
				manageJobSeekerForm.setResumeDTOList(resumeDTOList);
				model.setViewName("compareJobSeeker");
			}
		} catch (JobBoardServiceException jbex) {
			LOGGER.error("Error occured while Retriving resume detail", jbex);
		}

		model.addObject(MANAGEJOBSEEKERFORM, manageJobSeekerForm);

		return model;
	}
	/**
	 * @param manageJobSeekerForm
	 * @param resumeId
	 * @param manageJobSeekerDTOList
	 * @param resumeDTOList
	 * @param tokenize
	 */
	private void addResumeDTOtoResumeDTOList(
			ManageJobSeekerForm manageJobSeekerForm, int resumeId,
			List<ManageJobSeekerDTO> manageJobSeekerDTOList,
			List<ResumeDTO> resumeDTOList, StringTokenizer tokenize) {
		int folderResumeId;
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

	/**
	 * This method is called to send job to a friend for call the Mail page open
	 * and here hold URl userid and job id etc.
	 * 
	 * @author devi prasad
	 * @version V.0.1
	 * @param sendtofriendmail
	 *            -
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendtofriend", method = RequestMethod.GET)
	public ModelAndView sendToFriend(SendToFriend sendtofriendmail,
			BindingResult result, ManageJobSeekerForm manageJobSeekerForm,HttpServletRequest request, Model model) {

		try {

			int resumeId = Integer.parseInt(request.getParameter("id"));
			String resumeName = request.getParameter("resumeName");
			resumeName = resumeName.replace(" ", "-").toLowerCase();


			String fullPath = request
					.getRequestURL()
					.toString()
					.replace(
							request.getServletPath(),
							"/jobsearch/viewJobDetails/" + resumeId + "/"
									+ resumeName + dothtmlExtention);
			sendtofriendmail.setResumeId(resumeId);
			sendtofriendmail.setJoburl(fullPath);
			model.addAttribute("joburl", fullPath);
			model.addAttribute("jobId", request.getParameter("id"));
			model.addAttribute(CURRENT_URL, request.getParameter(CURRENT_URL));
			model.addAttribute("sendtofriendmail", sendtofriendmail);
		} catch (Exception e) {
			LOGGER.info("ERROR");
		}

		return new ModelAndView("jobSeekerSendResumePopUp");
	}

	/**
	 * Mail Sending method sendTofriendPost take Bean file Binding result and
	 * Http servlet request and Session for Many place it is hold User id and
	 * facilityid
	 * 
	 * @author devi prasad
	 * @version V.0.1
	 * @param sendtofriendmail
	 * @param result
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/sendtofriendpost", method = RequestMethod.POST)
	public String sendToFriendPost(
			@ModelAttribute("sendtofriendmail") SendToFriend sendtofriendmail,
			BindingResult result, HttpServletRequest request, HttpServletResponse response,
			HttpSession session,ManageJobSeekerForm manageJobSeekerForm) {
		ModelAndView modelData = new ModelAndView();
		Boolean status = Boolean.TRUE;
		String finalmailbody;
		StringBuffer mesg = new StringBuffer();
		StringBuffer dataString = new StringBuffer();
		String bodyMesg = MMJBCommonConstants.EMPTY;
		try {
			String data = sendtofriendmail.getEmail().toString();
			if ((null == data.trim())
					|| (MMJBCommonConstants.EMPTY.equals(data.trim()))) {
				return emailMsgBlank;
			}
			data = data.replace(',', ';');
			int len = data.length();
			if (data.charAt(len - 1) == ';') {
				data = data.substring(0, len - 1);
			}
			String str[] = data.split(";");
			int countString = str.length;

			try {
				int userId = 0;
				String userName = null;
				String userEmail = null;
				String jobseekerName = null;
				if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
					userId = (Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID);
					userName = (String) session
							.getAttribute(MMJBCommonConstants.USER_NAME);
					userEmail = (String) session
							.getAttribute(MMJBCommonConstants.USER_EMAIL);
				}
				EmailDTO jobSeekerEmailDTO = new EmailDTO();
				jobSeekerEmailDTO.setFromAddress(webMailServer);

				int iterationCount = 0;
				InternetAddress[] jobSeekerToAddress = new InternetAddress[str.length];
				for (String string : str) {

					if (!jobSearchValidator.validateEmailPattern(string.trim())) {
						return emailMsg;
					}

					jobSeekerToAddress[iterationCount] = new InternetAddress(
							string.trim());
					iterationCount++;

				}
				jobSeekerEmailDTO.setToAddress(jobSeekerToAddress);
				String msgSubject = MMJBCommonConstants.EMPTY;
				if (session.getAttribute(MMJBCommonConstants.USER_ID) == null) {

					jobseekerName = "XXXX-XXX";
					msgSubject = subOfmail + " " + jobseekerName;
				} else {
					jobseekerName = (String) session
							.getAttribute(MMJBCommonConstants.USER_NAME);
					msgSubject = subOfmail + " " + jobseekerName;
				}

				jobSeekerEmailDTO.setSubject(msgSubject);
				ResumeDTO resumeDTO = resumeService
						.editResume(sendtofriendmail.getResumeId());
				List<String> attachmentpaths = new ArrayList<String>();
				
				if(null !=resumeDTO.getFilePath() && ! resumeDTO.getFilePath().isEmpty()){
					// Resume Type Upload
                MultipartFile file = resumeDTO.getFileData();
                
                File upLoadedfile = new File(file.getOriginalFilename());
                upLoadedfile.createNewFile();
                FileOutputStream fos = new FileOutputStream(upLoadedfile);
                fos.write(file.getBytes());
                fos.close(); 
                upLoadedfile.deleteOnExit();
			
				attachmentpaths.add(upLoadedfile.getAbsolutePath());
				}else if(null !=resumeDTO.getResumeText() && !resumeDTO.getResumeText().isEmpty()){
					// Resume Type copy paste
					 File upLoadedfile = new File(resumeDTO.getResumeName());
		                upLoadedfile.createNewFile();
		                FileOutputStream fos = new FileOutputStream(upLoadedfile);
		                fos.write(resumeDTO.getResumeText().getBytes());
		                fos.close(); 
		                upLoadedfile.deleteOnExit();					
						attachmentpaths.add(upLoadedfile.getAbsolutePath());
				}else{
					String fileName = (null != resumeDTO.getResumeName() ? resumeDTO
							.getResumeName() : "Profile");
					pdfGenerator.generateAndExportResumeAsPdfForAttachment(request, response, resumeDTO);
					 File upLoadedfile = new File(basedirectorypathUpload+fileName+".pdf");
					 attachmentpaths.add(upLoadedfile.getAbsolutePath());
				}
				jobSeekerEmailDTO.setAttachmentPaths(attachmentpaths);
				String Subject = subOfmail + " " + jobseekerName+".";
				
				String bodyHead2 = sendtofriendmail.getMessage();
				String jobUrl = sendtofriendmail.getJoburl();
				mesg = mesg.append("<TABLE><TR><TD>" + Subject + END_TAGS);
				mesg = mesg.append("<TR><TD>" + "With the following message :" + "\n"
						+ END_TAGS);
				mesg = mesg.append("<TR><TD>" +  bodyHead2
						+ END_TAGS);
				mesg = mesg.append("<TR><TD>" + bodyOfMailFirst + "." 
						+ END_TAGS);
				
				bodyMesg = mesg.toString();
				jobSeekerEmailDTO.setBody(bodyMesg);
				jobSeekerEmailDTO.setHtmlFormat(true);
				emailService.sendEmail(jobSeekerEmailDTO);
			} catch (Exception e) {
				LOGGER.info(errSendingMail);
			}

		} catch (Exception e) {
			status = Boolean.FALSE;
			throw new MailParseException(e);
		}
		modelData.setViewName(urlRedirectMail);
		return "";

	}
	/**
	 * This method is called to export an uploaded resume. 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/exportResume", method = RequestMethod.GET)
	public void exporting(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("fileName") String fileName) {

		File file = new File(fileName);

		String fname = file.getName();
		int index = fname.indexOf('_');
		fname = fname.substring(index + 1);
		index = fname.lastIndexOf('.');
		String fileExtn = fname.substring(index + 1);

		if (MMJBCommonConstants.FILE_TYPE_DOC.equalsIgnoreCase(fileExtn)) {
			response.setContentType("application/vnd.ms-word");
		} else if (MMJBCommonConstants.FILE_TYPE_DOCX
				.equalsIgnoreCase(fileExtn)) {
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		} else if (MMJBCommonConstants.FILE_TYPE_PDF.equalsIgnoreCase(fileExtn)) {
			response.setContentType("application/pdf");
		}

		response.setHeader("Content-Disposition", "attachment; filename="
				+ fname);

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			input = new BufferedInputStream(new FileInputStream(file));
			output = new BufferedOutputStream(response.getOutputStream());

			byte[] buffer = new byte[8192];
			for (int length = 0; (length = input.read(buffer)) > 0;) {
				output.write(buffer, 0, length);
			}
		} 
		 catch (Exception e) {
			LOGGER.info("Error while exporting",e);
		}
		finally {
			if (output != null){
				try {
						output.close();
					} catch (IOException ignore) {
						LOGGER.info("Error while exporting",ignore);
					}
			}	
			if (input != null){
				try {
						input.close();
					}catch (IOException ignore) {
						LOGGER.info("Error while exporting",ignore);
					}
			}	
		}

	}
}
