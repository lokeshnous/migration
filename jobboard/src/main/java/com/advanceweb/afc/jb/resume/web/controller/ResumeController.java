/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.ContactInformationDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.PhoneDetailDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeAttribListDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.ResumeVisibilityDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.common.util.AVScannerHelper;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.TransformJobSeekerRegistration;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;
/**
 * This class has been created to perform resume activity such as create,
 * delete, edit, download
 * 
 * @author anilm
 * @version 1.0
 * @created Jul 9, 2012
 */

@Controller
@RequestMapping(value = "/jobSeekerResume")
@SessionAttributes("createResume")
public class ResumeController extends AbstractController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ResumeController.class);

	/** The Constant CREATE_RESUME. */
	private static final String CREATE_RESUME = "createResume";

	/** The Constant RESUME_ID. */
	private static final String RESUME_ID = "resumeId";

	/** The Constant EMP_TYPE_LIST. */
	private static final String EMP_TYPE_LIST = "empTypeList";

	/** The Constant PHONE_TYPE_LIST. */
	private static final String PHONE_TYPE_LIST = "phoneTypeList";

	/** The Constant CAREER_LVL_LIST. */
	private static final String CAREER_LVL_LIST = "careerLvlList";

	/** The Constant AN_SALARY_LIST. */
	private static final String AN_SALARY_LIST = "annualSalarylList";

	/** The Constant LANGUAGE_LIST. */
	private static final String LANGUAGE_LIST = "languagelList";

	/** The Constant PROFIENCY_LIST. */
	private static final String PROFIENCY_LIST = "langProficiencylList";

	/** The Constant EDU_DEGREE_LIST. */
	private static final String EDU_DEGREE_LIST = "eduDegreeList";

	/** The Constant COUNTRY_LIST. */
	private static final String COUNTRY_LIST = "countryList";

	/** The Constant STATE_LIST. */
	private static final String STATE_LIST = "stateList";

	/** The Constant CREATE_RES_BUILDER. */
	private static final String CREATE_RES_BUILDER = "createResumeBuilder";

	/** The Constant JS_REDIRECT_URL. */
	private static final String JS_REDIRECT_URL = "redirect:/jobSeeker/jobSeekerDashBoard.html";

	/** The resume service. */
	@Autowired
	private ResumeService resumeService;

	/** The trans create resume. */
	@Autowired
	private TransformCreateResume transCreateResume;

	/** The ad service. */
	@Autowired
	private AdService adService;

	/** The transform job seeker registration. */
	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;

	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/** The resume validator. */
	@Autowired
	private ResumeValidator resumeValidator;

	/** The basedirectorypath upload. */
	private @Value("${basedirectorypathUpload}")
	String basedirectorypathUpload;

	/** The resume warning msg. */
	private @Value("${resumeWarningMsg}")
	String resumeWarningMsg;

	/** The resume duplicate. */
	private @Value("${resumeDuplicate}")
	String resumeDuplicate;

	/** The resume delete success. */
	private @Value("${resumeDeleteSuccess}")
	String resumeDeleteSuccess;

	/** The resume delete failure. */
	private @Value("${resumeDeleteFailure}")
	String resumeDeleteFailure;
	
	
	/** The Rchilli Key. */
	private @Value("${Key}")
	String key;
	
	/** The Rchilli Sub Key. */
	private @Value("${SubKey}")
	String subKey;
	
	/** The Rchilli Version. */
	private @Value("${version}")
	String version;
	
	/** The Rchilli Country. */
	private @Value("${Country}")
	String country;
	
	/** The Rchilli Services. */
	private @Value("${Services}")
	String services;
	
	/**
	 * This method is called to display resume list belonging to a logged in
	 * jobSeeker
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/manageResume", method = RequestMethod.GET)
	public String manageResume(HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {

		List<ResumeDTO> resumeDTOList = resumeService
				.retrieveAllResumes((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));

		List<ResumeVisibilityDTO> visiblityList = populateDropdownsService
				.getResumeVisibilityList();

		Map<String, String> visibilityMap = new HashMap<String, String>();

		for (int i = 0; i < visiblityList.size(); i++) {
			visibilityMap.put(visiblityList.get(i).getVisibilityId(),
					visiblityList.get(i).getVisibilityName());
		}

		List<ResumeDTO> resumeDTOListNew = new ArrayList<ResumeDTO>();

		for (ResumeDTO resumeDTO : resumeDTOList) {
			resumeDTO.setResumeVisibility(visibilityMap.get(resumeDTO
					.getResumeVisibility()));
			resumeDTOListNew.add(resumeDTO);
		}

		map.put("resumeList", resumeDTOList);

		return "manageResumePopup";
	}

	/**
	 * This method is called to populate drop downs in resume popup
	 * 
	 * @param
	 * @return model
	 */
	private ModelAndView populateResumeDropDowns() {
		ModelAndView model = new ModelAndView();
		List<ResumeAttribListDTO> resumeTypeList = populateDropdownsService
				.populateResumeDropdown(MMJBCommonConstants.RESUME_TYPE);
		List<ResumeAttribListDTO> employmentTypeList = populateDropdownsService
				.populateResumeDropdown(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<ResumeAttribListDTO> workAuthUSList = populateDropdownsService
				.populateResumeDropdown(MMJBCommonConstants.WORK_AUTH_US);
		List<ResumeAttribListDTO> relocateList = populateDropdownsService
				.populateResumeDropdown(MMJBCommonConstants.RELOCATE);
		List<ResumeVisibilityDTO> visibilityList = populateDropdownsService
				.getResumeVisibilityList();
		

		model.addObject("resumeTypeList", resumeTypeList);
		model.addObject("employmentType", employmentTypeList);
		model.addObject("workAuthUS", workAuthUSList);
		model.addObject("resumeVisibility", visibilityList);
		model.addObject("relocate", relocateList);
		return model;
	}

	/**
	 * This method is called to validate a maximum resume & duplicate resume.
	 * 
	 * @param resumeName
	 * @param resumeId
	 * @return warningMessage
	 */
	@RequestMapping(value = "/validateCreateResumePopUp", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject validateCreateResumePopUp(
			@RequestParam("resumeName") String resumeName,
			@RequestParam(RESUME_ID) String resumeId, HttpSession session) {

		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		JSONObject warningMessage = new JSONObject();
		resumeName = resumeName.replace("'", "''");
		if ("".equals(resumeId) || resumeId == null) {
			int resumeCount = resumeService.findResumeCount(userId);
			if (resumeCount >= 5) {
				warningMessage.put("maxResume", resumeWarningMsg);
				return warningMessage;
			}
		}
		if (!("".equals(resumeName))
				&& resumeService.checkDuplicateResumeName(resumeId, resumeName,
						userId)) {
			warningMessage.put("duplicateResume", resumeDuplicate);
			return warningMessage;
		}
		return warningMessage;
	}

	/**
	 * This method is called to fetch the resume data to edit
	 * 
	 * @param createResume
	 * @param resumeId
	 * @return model
	 */
	@RequestMapping(value = "/editResume", method = RequestMethod.GET)
	public ModelAndView editResume(@RequestParam(RESUME_ID) int resumeId,HttpServletRequest request) {
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);

		CreateResume createResume = new CreateResume();
		transCreateResume.transformResumeDTOToCreateResume(createResume,
				resumeDTO);
		ModelAndView model = populateResumeDropDowns();
		List<DropDownDTO> blockedCompanies = new ArrayList<DropDownDTO>();
		if(null!=createResume.getUploadResumeId()){
			 blockedCompanies = populateDropdownsService
					.getBlockedCompanyList(Integer.valueOf(createResume.getUploadResumeId()));
			}
		model.addObject("blockedCompanies", blockedCompanies);
		model.addObject(CREATE_RESUME, createResume);
		request.setAttribute("resVisibility", createResume.getResumeVisibility());
		
		if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeDTO
				.getResumeType())) {
			model.setViewName("editresumepopup");
			return model;
		}

		if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO
				.getResumeType())) {
			model.setViewName("editUploadResumePopup");
			return model;
		}

		if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO
				.getResumeType())) {
			model.setViewName("editCopyPasteResumePopup");
			return model;
		}

		getTotalNotNullField(createResume);
		model.setViewName("editresumepopup");
		return model;
	}

	/**
	 * This method is called to delete a resume
	 * 
	 * @param resumeId
	 * @return deleteStatusJson
	 */
	@RequestMapping(value = "/deleteResume", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject deleteResume(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam(RESUME_ID) int resumeId) {
           ResumeDTO resumeDTO = new ResumeDTO();
           resumeDTO.setUploadResumeId(resumeId);
		boolean deleteStatus = resumeService.deleteResume(resumeId,
				(Integer) session.getAttribute(MMJBCommonConstants.USER_ID));

		JSONObject deleteStatusJson = new JSONObject();
		// delete the related entry from resume blocked company table
		try {
			resumeService.saveBlockedCompanydetails(resumeDTO);
		} catch (JobBoardServiceException jbex) {
			LOGGER.error("Error occured while saving Blocked Company Details",
					jbex);
		}	
		if (deleteStatus) {
			deleteStatusJson.put("success", resumeDeleteSuccess);
			return deleteStatusJson;
		} else {
			deleteStatusJson.put("failed", resumeDeleteFailure);
			return deleteStatusJson;
		}
	}

	/**
	 * This method is called to fetch the resume data to edit
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateResumePopup", method = RequestMethod.POST)
	public ModelAndView updateResumePopup(CreateResume resumeForm,
			HttpSession session, HttpServletRequest request) {
		/**
		 * Introduced a new variable "resumeForm" to resolve PMD issue.
		 */
		CreateResume createResume = resumeForm;
		ModelAndView model = new ModelAndView();
		 List<Integer> selectedList = createResume.getSelectedList();
		
		ResumeDTO resumeDTO = transCreateResume
				.transformCreateResumeToResumeDTO(createResume);
		resumeDTO.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));

		if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeDTO
				.getResumeType())) {
			setResumeBuilderDetails(session, request, model, selectedList,
					resumeDTO);
		}

		return model;
	}
/**
 * 
 * @param session
 * @param request
 * @param model
 * @param selectedList
 * @param resumeDTO
 */
	private void setResumeBuilderDetails(HttpSession session,
			HttpServletRequest request, ModelAndView model,
			List<Integer> selectedList, ResumeDTO resumeDTO) {
		CreateResume createResume;
		resumeService.updateResume(resumeDTO);
		resumeDTO = resumeService.editResume(resumeDTO.getUploadResumeId());

		createResume = transCreateResume
				.transformCreateResumeForm(resumeDTO);

		List<DropDownDTO> empTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> phoneTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
		List<DropDownDTO> careerLvlList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.CAREER_LEVEL);
		List<DropDownDTO> annualSalarylList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.ANNUAL_SALARY);
		List<DropDownDTO> languagelList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_TYPE);
		List<DropDownDTO> langProficiencylList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_PROFICIENCY_TYPE);
		List<DropDownDTO> eduDegreeList = populateDropdownsService
				.populateEducationDegreesDropdowns();
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();

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
		List<DropDownDTO> blockedCompanies = new ArrayList<DropDownDTO>();
		if(null!=createResume.getUploadResumeId()){
		 blockedCompanies = populateDropdownsService
				.getBlockedCompanyList(Integer.valueOf(createResume.getUploadResumeId()));
		}
		createResume.setListCertForm(listCertForm);
		createResume.setListEduForm(listEduForm);
		createResume.setListLangForm(listLangForm);
		createResume.setListRefForm(listRefForm);
		createResume.setListWorkExpForm(listWorkExpForm);
		createResume.setContactInfoForm(contactForm);
		createResume.setListPhoneDtlForm(listPhoneDtl);
		createResume.setSelectedList(selectedList);
		getTotalNotNullField(createResume);
		// DropDowns
		model.addObject(EMP_TYPE_LIST, empTypeList);
		model.addObject(PHONE_TYPE_LIST, phoneTypeList);
		model.addObject(CAREER_LVL_LIST, careerLvlList);
		model.addObject(AN_SALARY_LIST, annualSalarylList);
		model.addObject(LANGUAGE_LIST, languagelList);
		model.addObject(PROFIENCY_LIST, langProficiencylList);
		model.addObject(EDU_DEGREE_LIST, eduDegreeList);
		model.addObject(COUNTRY_LIST, countryList);
		model.addObject(STATE_LIST, stateList);
		model.addObject("blockedCompanies", blockedCompanies);

		session.setAttribute(EMP_TYPE_LIST, empTypeList);
		session.setAttribute(PHONE_TYPE_LIST, phoneTypeList);
		session.setAttribute(CAREER_LVL_LIST, careerLvlList);
		session.setAttribute(AN_SALARY_LIST, annualSalarylList);
		session.setAttribute(LANGUAGE_LIST, languagelList);
		session.setAttribute(PROFIENCY_LIST, langProficiencylList);
		session.setAttribute(EDU_DEGREE_LIST, eduDegreeList);
		session.setAttribute(COUNTRY_LIST, countryList);
		session.setAttribute(STATE_LIST, stateList);

		// DropDowns end
		getTotalNotNullField(createResume);
		resumeDTO.getContactInfoDTO();
		model.addObject(CREATE_RESUME, createResume);
		// Ads for resume page
		populateAds(request, session, model);
		model.setViewName(CREATE_RES_BUILDER);
	}

	/**
	 * This method is called to open the create resume pop up depending on the
	 * resume type
	 * 
	 * @param resumeType
	 * @return model
	 */
	@RequestMapping(value = "/createResumePopUp", method = RequestMethod.GET)
	public ModelAndView createResumePopUp(
			@RequestParam("resumeType") String resumeType, HttpSession session,
			HttpServletRequest request) {

		CreateResume createResume = new CreateResume();

		createResume.setWillingToRelocate(MMJBCommonConstants.RELOCATE_NO);
		createResume
				.setResumeVisibility(MMJBCommonConstants.VISIBILITY_PRIVATE);

		createResume.setResumeType(resumeType);
		if (null != request.getParameter("virus")) {
			if (null != session.getAttribute("resumeName")) {
				createResume.setResumeName((String) session
						.getAttribute("resumeName"));
				session.removeAttribute("resumeName");
			}
			if (null != session.getAttribute("jobTitle")) {
				createResume.setDesiredJobTitle((String) session
						.getAttribute("jobTitle"));
				session.removeAttribute("jobTitle");
			}
			if (null != session.getAttribute("empType")) {
				createResume.setDesiredEmploymentType((String) session
						.getAttribute("empType"));
				session.removeAttribute("empType");
			}
			if (null != session.getAttribute("workAuthorizationUS")) {
				createResume.setWorkAuthorizationUS((String) session
						.getAttribute("workAuthorizationUS"));
				session.removeAttribute("workAuthorizationUS");
			}
			if (null != session.getAttribute("willingToRelocate")) {
				createResume.setWillingToRelocate((String) session
						.getAttribute("willingToRelocate"));
				session.removeAttribute("willingToRelocate");
			}
			if (null != session.getAttribute("resumeVisibility")) {
				createResume.setResumeVisibility((String) session
						.getAttribute("resumeVisibility"));
				session.removeAttribute("resumeVisibility");
			}

			createResume.setVirusFound(true);
		}else if(null != request.getParameter("fileParserError")){
			createResume.setParseError(true);
		}
		ModelAndView model = populateResumeDropDowns();
		model.addObject(CREATE_RESUME, createResume);

		if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeType)) {
			model.setViewName("createresumepopup");
			return model;
		}
		if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeType)) {
			model.setViewName("createResumeUploadPopup");
			return model;
		}
		if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeType)) {
			model.setViewName("createResumeCopyPastePopup");
			return model;
		}
		model.setViewName("createresumepopup");
		return model;
	}

	/**
	 * This method is called to save resume of type copy paste.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/copyPasteResume", method = RequestMethod.POST)
	public ModelAndView createCopyPasteResume(CreateResume createResume,
			HttpSession session) {
		ModelAndView model = populateResumeDropDowns();
		if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(createResume
				.getResumeType())) {

			ResumeDTO resumeDTO = transCreateResume
					.transformCreateResumeToResumeDTO(createResume);
			resumeDTO.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));
			resumeDTO.setFileName(resumeDTO.getResumeName());
			resumeDTO.setFilePath(basedirectorypathUpload);
			resumeDTO.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));
			createResume.setFilename(basedirectorypathUpload);
			createResume.setFilePath(basedirectorypathUpload);
			createResume.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));
			// if(!StringUtils.isBlank(resumeDTO.getResumeText())){ --To DO--
			String tempDirectoryFilePath = createDirectoryFilePath(resumeDTO);
			File copyPasteFiledest = new File(tempDirectoryFilePath+".docx");
			try {
				FileUtils.writeStringToFile(copyPasteFiledest,
						resumeDTO.getResumeText());
				createResume = callFileParser(copyPasteFiledest, createResume);
							
				 /* String   -- To Do--
				  resValidator=resumeValidator.validateParser(createResume); if
				  (!StringUtils.isEmpty(resValidator)) {
				  model.setViewName(JS_REDIRECT_URL); }*/
				 
			} catch (IOException IOE) {
				LOGGER.error(IOE);
			} catch (Exception jbex) {
				LOGGER.error("Error Occured While Uploading the File" + jbex);
			}
			List<CountryDTO> countryList = populateDropdownsService
					.getCountryList();
			List<StateDTO> stateList = populateDropdownsService.getStateList();
			List<DropDownDTO> phoneTypeList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
			model.addObject(PHONE_TYPE_LIST, phoneTypeList);
			model.addObject(COUNTRY_LIST, countryList);
			model.addObject(STATE_LIST, stateList);
			model.addObject(CREATE_RESUME, createResume);
			// Ads for resume page
			// populateAds(request, session, model);
			model.setViewName(CREATE_RES_BUILDER);
			// }-- TO DO --
			/*
			 * resumeService.createResumeCopyPaste(resumeDTO); // if user want
			 * to block some company save the blocked comapny details if (null
			 * != createResume.getSelectedList() &&
			 * createResume.getSelectedList().size() > 0) {
			 * resumeDTO.setSelectedList(createResume.getSelectedList()); try {
			 * resumeService.saveBlockedCompanydetails(resumeDTO); } catch
			 * (JobBoardServiceException jbex) { LOGGER.error(
			 * "Error occured while saving Blocked Company Details", jbex); } }
			 */
		//	model.setViewName(JS_REDIRECT_URL);
		}
		return model;
	}

	/**
	 * This method is called to update resume of type copy paste.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/updateCopyPasteResume", method = RequestMethod.POST)
	public ModelAndView updateCopyPasteResume(CreateResume createResume,
			HttpSession session, HttpServletRequest request) {
		ModelAndView model = populateResumeDropDowns();

		ResumeDTO resumeDTO = resumeService.editResume(Integer
				.valueOf(createResume.getUploadResumeId()));
		resumeDTO.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));
		if (!StringUtils.isBlank(resumeDTO.getResumeText())
				&& !resumeDTO.getResumeText().equalsIgnoreCase(
						createResume.getResumeText())) {
			resumeDTO.setFileName(resumeDTO.getResumeName());
			resumeDTO.setFilePath(basedirectorypathUpload);
			resumeDTO.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));
			createResume.setFilename(basedirectorypathUpload);
			createResume.setFilePath(basedirectorypathUpload);
			createResume.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));
			String tempDirectoryFilePath = createDirectoryFilePath(resumeDTO);
			File copyPasteFiledest = new File(tempDirectoryFilePath);
			try {
				FileUtils.writeStringToFile(copyPasteFiledest,
						resumeDTO.getResumeText());
				createResume = callFileParser(copyPasteFiledest, createResume);

			} catch (IOException IOE) {
				LOGGER.error(IOE);
			} catch (Exception jbex) {
				LOGGER.error("Error Occured While Uploading the File" + jbex);
			}

			List<CountryDTO> countryList = populateDropdownsService
					.getCountryList();
			List<StateDTO> stateList = populateDropdownsService.getStateList();
			List<DropDownDTO> phoneTypeList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
			model.addObject(PHONE_TYPE_LIST, phoneTypeList);
			model.addObject(COUNTRY_LIST, countryList);
			model.addObject(STATE_LIST, stateList);
			model.addObject(CREATE_RESUME, createResume);
		} else {
			List<Integer> selectedList = createResume.getSelectedList();
			setResumeBuilderDetails(session, request, model, selectedList,
					resumeDTO);
		}
		// Ads for resume page
		// populateAds(request, session, model);
		model.setViewName(CREATE_RES_BUILDER);
		return model;
	}

	/**
	 * This method is called to save resume of type upload.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/createResumeUpload", method = RequestMethod.POST)
	public ModelAndView createResumeUpload(CreateResume createResume,
			HttpSession session) {

		ModelAndView model = populateResumeDropDowns();
		if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume
				.getResumeType())) {

			ResumeDTO resumeDTO = transCreateResume
					.transformCreateResumeToResumeDTO(createResume);
			String fileName = null, filePath = null;
			try {
				MultipartFile file = createResume.getFileData();

				if (null != file && file.getSize() > 0) {
					fileName = file.getOriginalFilename();
					filePath = basedirectorypathUpload;
					resumeDTO.setFileServer(basedirectorypathUpload);
					resumeDTO.setFileName(fileName);
					resumeDTO.setFilePath(filePath);
					resumeDTO.setUserId((Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
					createResume.setFilename(fileName);
					createResume.setFilePath(filePath);
					createResume.setUserId((Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
					String tempDirectoryFilePath = createDirectoryFilePath(resumeDTO);
					String tempVirusChkFile= tempDirectoryFilePath;
					// Code to implement Antivirus Check Starts
					File virusChkFiledest = new File(tempVirusChkFile);
					
					file.transferTo(virusChkFiledest);
					boolean virusFound = scanFileForVirus(
							virusChkFiledest.getPath(),
							virusChkFiledest.getName());

					if (virusFound) {
						LOGGER.debug("Virus Found In File "
								+ resumeDTO.getFileName() + " Uploaded By !"
								+ resumeDTO.getUserId());
						// delete the temporary storage location in the server
						// which was used
						// for file uploading and virus scanning purposes
						virusChkFiledest.delete();

						// alert the user that the uploaded document contains
						// virus and reject the resume
						createResume
								.setResumeType(MMJBCommonConstants.RESUME_TYPE_UPLOAD);
						createResume.setVirusFound(true);
						session.setAttribute("virusStatus", true);
						if (null != createResume.getResumeName()) {
							session.setAttribute("resumeName",
									createResume.getResumeName());
						}
						if (null != createResume.getDesiredJobTitle()) {
							session.setAttribute("jobTitle",
									createResume.getDesiredJobTitle());
						}
						if (null != createResume.getDesiredEmploymentType()) {
							session.setAttribute("empType",
									createResume.getDesiredEmploymentType());
						}
						if (null != createResume.getWorkAuthorizationUS()) {
							session.setAttribute("workAuthorizationUS",
									createResume.getWorkAuthorizationUS());
						}
						if (null != createResume.getWillingToRelocate()) {
							session.setAttribute("willingToRelocate",
									createResume.getWillingToRelocate());
						}
						if (null != createResume.getResumeVisibility()) {
							session.setAttribute("resumeVisibility",
									createResume.getResumeVisibility());
						}
						model.addObject(CREATE_RESUME, createResume);
						model.setViewName(JS_REDIRECT_URL);
						return model;
						// Code to implement Antivirus Check Ends
					} else {
						LOGGER.debug("No Virus Found In File "
								+ resumeDTO.getFileName() + " Uploaded By !"
								+ resumeDTO.getUserId());
											
						
						File dest = new File(resumeDTO.getFilePath());						
						virusChkFiledest.renameTo(dest);
						createResume =  callFileParser(virusChkFiledest,createResume);
						if(createResume.isParseError()){
							session.setAttribute("parseError", true);
							model.addObject(CREATE_RESUME, createResume);
							model.setViewName(JS_REDIRECT_URL);
							return model;
						}
						List<CountryDTO> countryList = populateDropdownsService
								.getCountryList();
						List<StateDTO> stateList = populateDropdownsService.getStateList();
						List<DropDownDTO> phoneTypeList = populateDropdownsService
								.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
						model.addObject(PHONE_TYPE_LIST, phoneTypeList);
						model.addObject(COUNTRY_LIST, countryList);
						model.addObject(STATE_LIST, stateList);
						model.addObject(CREATE_RESUME, createResume);
						// Ads for resume page
						//populateAds(request, session, model);
						model.setViewName(CREATE_RES_BUILDER);
					}

				}
			} catch (Exception jbex) {
				LOGGER.error("Error Occured While Uploading the File" + jbex);
			}
			//session.setAttribute("uploadStatus", true);
			//model.setViewName(JS_REDIRECT_URL);
		}
		return model;
	}

	/**
	 * Construct the destination directory file path where the uploaded file
	 * would be stored in the server
	 * 
	 * @param resumeDTO
	 * @return
	 */
	private String createDirectoryFilePath(ResumeDTO resumeDTO) {
		String newUploadedPath = resumeDTO.getFilePath()
				+ Calendar.getInstance().get(Calendar.DATE) +Calendar.getInstance().getTimeInMillis() +"_" + resumeDTO.getFileName();

		return newUploadedPath;
	}

	/**
	 * Scan the file for virus
	 * 
	 * @param uploadedFile
	 *            File that is uploaded
	 * @param uploadFileName
	 *            name of the file being uploaded
	 * @return boolean "true" if the file is virus free, "false" informing that
	 *         the file is not clean and might contain virus thus we do not
	 *         proceed to upload the file
	 */
	private boolean scanFileForVirus(String uploadFilePath,
			String uploadFileName) {
		boolean virusFound = false;
		AVScannerHelper avScanHelper = new AVScannerHelper();
		virusFound = avScanHelper.scanFile(uploadFilePath, uploadFileName);
		return virusFound;
	}

	/**
	 * This method is called to update resume of type upload.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/updateResumeUpload", method = RequestMethod.POST)
	public ModelAndView updateResumeUpload(CreateResume createResume,
			HttpSession session, HttpServletRequest request) {

		ModelAndView model = populateResumeDropDowns();
		if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume
				.getResumeType())) {

			ResumeDTO resumeDTO = transCreateResume
					.transformCreateResumeToResumeDTO(createResume);
			String fileName = null, filePath = null;
			try {
				MultipartFile file = createResume.getFileData();

				if (null != file && file.getSize() > 0) {

					fileName = file.getOriginalFilename();
					File deleteFile = new File(resumeDTO.getFilePath());
					if (deleteFile.delete()) {
						filePath = basedirectorypathUpload
								+ resumeDTO.getUploadResumeId() + "_"
								+ fileName;
						File dest = new File(filePath);
						file.transferTo(dest);
						resumeDTO.setFileServer(basedirectorypathUpload);
						resumeDTO.setFileName(fileName);
						resumeDTO.setFilePath(filePath);

						String tempDirectoryFilePath = createDirectoryFilePath(resumeDTO);
						String tempVirusChkFile = tempDirectoryFilePath;
						// Code to implement Antivirus Check Starts
						File virusChkFiledest = new File(tempVirusChkFile);
						file.transferTo(virusChkFiledest);
						boolean virusFound = scanFileForVirus(
								virusChkFiledest.getPath(),
								virusChkFiledest.getName());

						if (virusFound) {
							LOGGER.debug("Virus Found In File "
									+ resumeDTO.getFileName()
									+ " Uploaded By !" + resumeDTO.getUserId());
							// delete the temporary storage location in the
							// server
							// which was used
							// for file uploading and virus scanning purposes
							virusChkFiledest.delete();

							// alert the user that the uploaded document
							// contains
							// virus and reject the resume
							createResume
									.setResumeType(MMJBCommonConstants.RESUME_TYPE_UPLOAD);
							createResume.setVirusFound(true);
							session.setAttribute("virusStatus", true);
							if (null != createResume.getResumeName()) {
								session.setAttribute("resumeName",
										createResume.getResumeName());
							}
							if (null != createResume.getDesiredJobTitle()) {
								session.setAttribute("jobTitle",
										createResume.getDesiredJobTitle());
							}
							if (null != createResume.getDesiredEmploymentType()) {
								session.setAttribute("empType",
										createResume.getDesiredEmploymentType());
							}
							if (null != createResume.getWorkAuthorizationUS()) {
								session.setAttribute("workAuthorizationUS",
										createResume.getWorkAuthorizationUS());
							}
							if (null != createResume.getWillingToRelocate()) {
								session.setAttribute("willingToRelocate",
										createResume.getWillingToRelocate());
							}
							if (null != createResume.getResumeVisibility()) {
								session.setAttribute("resumeVisibility",
										createResume.getResumeVisibility());
							}
							model.addObject(CREATE_RESUME, createResume);
							model.setViewName(JS_REDIRECT_URL);
							return model;
							// Code to implement Antivirus Check Ends
						} else {
							createResume = callFileParser(dest, createResume);
							if(createResume.isParseError()){
								session.setAttribute("parseError", true);
								model.addObject(CREATE_RESUME, createResume);
								model.setViewName(JS_REDIRECT_URL);
								return model;
							}
							List<CountryDTO> countryList = populateDropdownsService
									.getCountryList();
							List<StateDTO> stateList = populateDropdownsService
									.getStateList();
							List<DropDownDTO> phoneTypeList = populateDropdownsService
									.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
							model.addObject(PHONE_TYPE_LIST, phoneTypeList);
							model.addObject(COUNTRY_LIST, countryList);
							model.addObject(STATE_LIST, stateList);
							model.addObject(CREATE_RESUME, createResume);
						}

					}
				} else {
					List<Integer> selectedList = createResume.getSelectedList();
					setResumeBuilderDetails(session, request, model,
							selectedList, resumeDTO);
				}
			} catch (Exception jbex) {
				LOGGER.error("Error Occured While updating resume" + jbex);
			}

			
			resumeDTO.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));
			createResume.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));
			createResume.setFilename(fileName);
			createResume.setFilePath(filePath);
			

			model.setViewName(CREATE_RES_BUILDER);
			
		}
		return model;
	}

	/**
	 * This method is called to save resume resume pop up & move to Advanced
	 * Resume Builder.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/saveCreateResume", method = RequestMethod.GET)
	public ModelAndView saveCreateResume(CreateResume createResume,
			HttpSession session) {
		ResumeDTO resumeDTO = new ResumeDTO();
		ModelAndView model = new ModelAndView();

		resumeDTO = transCreateResume
				.transformCreateResumeToResumeDTO(createResume);

		resumeDTO.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));

		resumeDTO = resumeService.createResume(resumeDTO);

		transCreateResume.transformResumeDTOToCreateResume(createResume,
				resumeDTO);

		model.addObject(CREATE_RESUME, createResume);
		model.setViewName("redirect:/jobSeekerResume/createResumeBuilder.html");

		return model;
	}

	/**
	 * This method is called to save resume resume pop up & move to Advanced
	 * Resume Builder.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/createResumeBuilder", method = RequestMethod.GET)
	public ModelAndView createResumebuilder(CreateResume createResume,
			HttpSession session, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();

		/*
		 * createResume.setUploadResumeId(String.valueOf(createResume
		 * .getUploadResumeId()));
		 */

		List<DropDownDTO> empTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> phoneTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
		List<DropDownDTO> careerLvlList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.CAREER_LEVEL);
		List<DropDownDTO> annualSalarylList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.ANNUAL_SALARY);
		List<DropDownDTO> languagelList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_TYPE);
		List<DropDownDTO> langProficiencylList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_PROFICIENCY_TYPE);
		List<DropDownDTO> eduDegreeList = populateDropdownsService
				.populateEducationDegreesDropdowns();
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		CertificationsForm certForm = new CertificationsForm();
		EducationForm eduForm = new EducationForm();
		LanguageForm langForm = new LanguageForm();
		langForm.setLanguage(MMJBCommonConstants.LANGUAGE_ENGLISH);
		ReferenceForm refForm = new ReferenceForm();
		refForm.setReferenceType(MMJBCommonConstants.REFERENCE_TYPE_PERSONAL);
		WorkExpForm workExpForm = new WorkExpForm();
		PhoneDetailForm phoneDtlForm = new PhoneDetailForm();
		ContactInfoForm contactInfoForm = new ContactInfoForm();
		List<CertificationsForm> listCertForm = new ArrayList<CertificationsForm>();
		List<EducationForm> listEduForm = new ArrayList<EducationForm>();
		List<LanguageForm> listLangForm = new ArrayList<LanguageForm>();
		List<ReferenceForm> listRefForm = new ArrayList<ReferenceForm>();
		List<WorkExpForm> listWorkExpForm = new ArrayList<WorkExpForm>();
		List<PhoneDetailForm> listPhoneDtlForm = new ArrayList<PhoneDetailForm>();
		listCertForm.add(certForm);
		listEduForm.add(eduForm);
		listLangForm.add(langForm);
		listRefForm.add(refForm);
		listWorkExpForm.add(workExpForm);
		listPhoneDtlForm.add(phoneDtlForm);
		createResume.setContactInfoForm(contactInfoForm);
		createResume.setListCertForm(listCertForm);
		createResume.setListEduForm(listEduForm);
		createResume.setListLangForm(listLangForm);
		createResume.setListRefForm(listRefForm);
		createResume.setListWorkExpForm(listWorkExpForm);
		createResume.setListPhoneDtlForm(listPhoneDtlForm);
		// DropDowns
		model.addObject(EMP_TYPE_LIST, empTypeList);
		model.addObject(PHONE_TYPE_LIST, phoneTypeList);
		model.addObject(CAREER_LVL_LIST, careerLvlList);
		model.addObject(AN_SALARY_LIST, annualSalarylList);
		model.addObject(LANGUAGE_LIST, languagelList);
		model.addObject(PROFIENCY_LIST, langProficiencylList);
		model.addObject(EDU_DEGREE_LIST, eduDegreeList);
		model.addObject(COUNTRY_LIST, countryList);
		model.addObject(STATE_LIST, stateList);
		// DropDowns end
		model.addObject(CREATE_RESUME, createResume);
		// Ads for resume page
		populateAds(request, session, model);
		model.setViewName(CREATE_RES_BUILDER);
		return model;
	}

	/**
	 * Called to create resume it Contains 1.Contact information 2.Objective
	 * 3.Work Experience 4.Education 5.Certifiation 6.Skills 7.Awards
	 * 8.Memberships 9.Other Details 10.References
	 * 
	 * @param createResume
	 * @param result
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params = "Save")
	public ModelAndView saveResumeBuilder(CreateResume createResume,
			HttpSession session, HttpServletRequest request) {

		if ("0".equals(createResume.getUploadResumeId())) {
			createResume.setUploadResumeId(null);
		}
		ModelAndView model = new ModelAndView();
		ResumeDTO resumeDTO = new ResumeDTO();
		createResume.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));
		String errorMessage = resumeValidator
				.validateResumeBuilder(createResume);
		
		if (!StringUtils.isEmpty(errorMessage)) {

			model = populateDropdowns(model);

			model.addObject(CREATE_RESUME, createResume);
			model.addObject("errorMessage", errorMessage);
			// Ads for resume page
			populateAds(request, session, model);
			model.setViewName(CREATE_RES_BUILDER);
			return model;
		}

		resumeDTO = transCreateResume
				.transformCreateResumeToResumeDTO(createResume);
		// if resume does not exist create resume
		if (StringUtils.isEmpty(createResume.getUploadResumeId())) {
			resumeDTO = resumeService.createResume(resumeDTO);
		}

		// if user want to block some company save the blocked comapny details
		
			resumeDTO.setSelectedList(createResume.getSelectedList());
			try {
				resumeService.saveBlockedCompanydetails(resumeDTO);
			} catch (JobBoardServiceException jbex) {
				LOGGER.error(
						"Error occured while saving Blocked Company Details",
						jbex);
			}
	
		AddressDTO addDTO = transformJobSeekerRegistration
				.createAddressDTO(createResume.getContactInfoForm());
		ContactInformationDTO contactInfoDTO = transCreateResume
				.transformContactInfoDTO(createResume.getContactInfoForm());
		contactInfoDTO.setAddressDTO(addDTO);
		List<CertificationDTO> listCertDTO = transCreateResume
				.transformCertificationDTO(createResume.getListCertForm());
		List<ReferenceDTO> listRefDTO = transCreateResume
				.transformReferenceDTO(createResume.getListRefForm());
		List<WorkExpDTO> listWorkExpDTO = transCreateResume
				.transformWorkExpDTO(createResume.getListWorkExpForm());
		List<EducationDTO> listEduDTO = transCreateResume
				.transformEducationDTO(createResume.getListEduForm());
		List<LanguageDTO> listLangDTO = transCreateResume
				.transformLanguageDTO(createResume.getListLangForm());
		List<PhoneDetailDTO> listPhoneDTO = transCreateResume
				.transformPhoneDetailDTO(createResume.getListPhoneDtlForm());
		resumeDTO = transCreateResume.transformResumeDTO(resumeDTO,
				createResume);
		resumeDTO.setContactInfoDTO(contactInfoDTO);
		resumeDTO.setListCertDTO(listCertDTO);
		resumeDTO.setListEduDTO(listEduDTO);
		resumeDTO.setListLangDTO(listLangDTO);
		resumeDTO.setListRefDTO(listRefDTO);
		resumeDTO.setListWorkExpDTO(listWorkExpDTO);
		resumeDTO.setListPhoneDtl(listPhoneDTO);
		resumeService.createResumeBuilder(resumeDTO);
		
		getTotalNotNullField(createResume);
		model.setViewName(JS_REDIRECT_URL);
		return model;

	}

	/**
	 * Preview resume builder.
	 *
	 * @param createResume the create resume
	 * @return the model and view
	 */
	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params = "Preview")
	public ModelAndView previewResumeBuilder(CreateResume createResume) {
		ModelAndView model = new ModelAndView();
		model.addObject(CREATE_RESUME, createResume);
		if (StringUtils.isEmpty(createResume.getUploadResumeId())) {
			createResume.setUploadResumeId("0");
			// since resume is not saved yet , setting the current date as
			// available date
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			createResume.setAvailableDate(df.format(new Date()));
		}
		model.setViewName("viewresume");
		return model;

	}

	/**
	 * Back to resume builder.
	 *
	 * @param createResume the create resume
	 * @param request the request
	 * @param session the session
	 * @return the model and view
	 */
	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params = "Back")
	public ModelAndView backToResumeBuilder(CreateResume createResume,
			HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.addObject(CREATE_RESUME, createResume);
		model = populateDropdowns(model);
		// Ads for resume page
		populateAds(request, session, model);
		model.setViewName(CREATE_RES_BUILDER);
		return model;

	}

	// */
	/**
	 * Removes the work exp.
	 *
	 * @param session the session
	 * @param createResume the create resume
	 * @param id the id
	 * @return the int
	 */
	@ResponseBody
	@RequestMapping(value = "/removeWorkExp", method = RequestMethod.POST)
	public int removeWorkExp(HttpSession session, CreateResume createResume,
			@RequestParam("id") int id) {
		try {
			if (null != createResume.getListWorkExpForm()) {
				int count = 0;
				for (WorkExpForm expform : createResume.getListWorkExpForm()) {
					if (expform.getItemId() == id) {
						break;
					}
					count++;
				}
				createResume.getListWorkExpForm().remove(count);
			}
		} catch (Exception jbex) {
			LOGGER.error("Error Occured While removing work experience" + jbex);
		}
		return id;

	}

	/**
	 * This method is called to add work experience
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addWorkExp", method = RequestMethod.POST)
	public ModelAndView addWorkExp(HttpSession session,
			CreateResume createResume) {

		WorkExpForm form = new WorkExpForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addWorkExp");

		List<DropDownDTO> empTypeList = (List<DropDownDTO>) session
				.getAttribute(EMP_TYPE_LIST);
		List<DropDownDTO> careerLvlList = (List<DropDownDTO>) session
				.getAttribute(CAREER_LVL_LIST);
		List<DropDownDTO> annualSalarylList = (List<DropDownDTO>) session
				.getAttribute(AN_SALARY_LIST);
		if (null == empTypeList) {
			empTypeList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		}

		if (null == careerLvlList) {
			careerLvlList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.CAREER_LEVEL);
		}

		if (null == annualSalarylList) {
			annualSalarylList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.ANNUAL_SALARY);
		}

		model.addObject(CAREER_LVL_LIST, careerLvlList);
		model.addObject(EMP_TYPE_LIST, empTypeList);
		model.addObject(AN_SALARY_LIST, annualSalarylList);
		model.addObject("workExpPositionId", createResume.getListWorkExpForm()
				.size());
		form.setItemId(createResume.getListWorkExpForm().size());
		if (null == createResume.getListCertForm()) {
			List<WorkExpForm> listWorkExpForms = new ArrayList<WorkExpForm>();
			listWorkExpForms.add(form);
			createResume.setListWorkExpForm(listWorkExpForms);
		} else {
			createResume.getListWorkExpForm().add(form);
		}
		return model;
	}

	/**
	 * Removes the certifications.
	 *
	 * @param session the session
	 * @param createResume the create resume
	 * @param id the id
	 * @return the int
	 */
	@ResponseBody
	@RequestMapping(value = "/removeCertifications", method = RequestMethod.POST)
	public int removeCertifications(HttpSession session,
			CreateResume createResume, @RequestParam("id") int id) {
		try {
			if (null != createResume.getListCertForm()) {
				int count = 0;
				for (CertificationsForm certform : createResume
						.getListCertForm()) {
					if (certform.getItemId() == id) {
						break;
					}
					count++;
				}

				createResume.getListCertForm().remove(count);

			}
		} catch (Exception jbex) {
			LOGGER.error("Error Occured While removing certifications" + jbex);
		}
		return id;

	}

	/**
	 * This method is called add Certifications
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addCertifications", method = RequestMethod.POST)
	public ModelAndView addCertifications(HttpSession session,
			CreateResume createResume) {

		CertificationsForm form = new CertificationsForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addCerts");
		model.addObject("certPositionId", createResume.getListCertForm().size());
		form.setItemId(createResume.getListCertForm().size());
		if (null != createResume.getListCertForm()) {
			createResume.getListCertForm().add(form);
		} else {
			List<CertificationsForm> listCertForms = new ArrayList<CertificationsForm>();
			listCertForms.add(form);
			createResume.setListCertForm(listCertForms);
		}
		return model;
	}

	/**
	 * This method is called to remove Education Details
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeEducationDetails", method = RequestMethod.POST)
	public int addEducationDetails(HttpSession session,
			CreateResume createResume, @RequestParam("id") int id) {

		try {
			if (null != createResume.getListEduForm()) {
				int count = 0;
				for (EducationForm phform : createResume.getListEduForm()) {
					if (phform.getItemId() == id) {
						break;
					}
					count++;
				}

				createResume.getListEduForm().remove(count);

			}
		} catch (Exception jbex) {
			LOGGER.error("Error Occured While adding education" + jbex);
		}
		return id;

	}

	/**
	 * Adds the education details.
	 *
	 * @param session the session
	 * @param createResume the create resume
	 * @return the model and view
	 */
	@RequestMapping(value = "/addEducationDetails", method = RequestMethod.POST)
	public ModelAndView addEducationDetails(HttpSession session,
			CreateResume createResume) {

		EducationForm form = new EducationForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addEducation");

		List<DropDownDTO> eduDegreeList = (List<DropDownDTO>) session
				.getAttribute(EDU_DEGREE_LIST);

		if (null == eduDegreeList) {
			eduDegreeList = populateDropdownsService
					.populateEducationDegreesDropdowns();
		}
		model.addObject(EDU_DEGREE_LIST, eduDegreeList);
		model.addObject("eduPositionId", createResume.getListEduForm().size());
		form.setItemId(createResume.getListEduForm().size());
		if (null != createResume.getListEduForm()) {
			createResume.getListEduForm().add(form);
		} else {
			List<EducationForm> listEduForms = new ArrayList<EducationForm>();
			listEduForms.add(form);
			createResume.setListEduForm(listEduForms);
		}
		return model;
	}

	/**
	 * Removes the language.
	 *
	 * @param session the session
	 * @param createResume the create resume
	 * @param id the id
	 * @return the int
	 */
	@ResponseBody
	@RequestMapping(value = "/removeLanguage", method = RequestMethod.POST)
	public int removeLanguage(HttpSession session, CreateResume createResume,
			@RequestParam("id") int id) {
		try {
			if (null != createResume.getListLangForm()) {
				int count = 0;
				for (LanguageForm langform : createResume.getListLangForm()) {
					if (langform.getItemId() == id) {
						break;
					}
					count++;
				}

				createResume.getListLangForm().remove(count);

			}
		} catch (Exception jbex) {
			LOGGER.error("Error Occured While removing language" + jbex);
		}
		return id;
	}

	/**
	 * This method is called add Languages
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@RequestMapping(value = "/addLanguages", method = RequestMethod.POST)
	public ModelAndView addLanguage(HttpSession session,
			CreateResume createResume) {

		LanguageForm form = new LanguageForm();
		form.setLanguage(MMJBCommonConstants.LANGUAGE_ENGLISH);
		ModelAndView model = new ModelAndView();
		model.setViewName("addLanguage");

		List<DropDownDTO> langProficiencylList = (List<DropDownDTO>) session
				.getAttribute(PROFIENCY_LIST);
		List<DropDownDTO> languagelList = (List<DropDownDTO>) session
				.getAttribute(LANGUAGE_LIST);

		if (null == languagelList) {
			languagelList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_TYPE);
		}

		if (null == langProficiencylList) {
			langProficiencylList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_PROFICIENCY_TYPE);
		}

		model.addObject(LANGUAGE_LIST, languagelList);
		model.addObject(PROFIENCY_LIST, langProficiencylList);
		model.addObject("langPositionId", createResume.getListLangForm().size());
		form.setItemId(createResume.getListLangForm().size());
		if (null != createResume.getListLangForm()) {
			createResume.getListLangForm().add(form);
		} else {
			List<LanguageForm> listLangForms = new ArrayList<LanguageForm>();
			listLangForms.add(form);
			createResume.setListLangForm(listLangForms);
		}
		return model;
	}

	/**
	 * Removes the refrences.
	 *
	 * @param session the session
	 * @param createResume the create resume
	 * @param id the id
	 * @return the int
	 */
	@ResponseBody
	@RequestMapping(value = "/removeRefrences", method = RequestMethod.POST)
	public int removeRefrences(HttpSession session, CreateResume createResume,
			@RequestParam("id") int id) {
		try {
			if (null != createResume.getListRefForm()) {
				int count = 0;
				for (ReferenceForm refform : createResume.getListRefForm()) {
					if (refform.getItemId() == id) {
						break;
					}
					count++;
				}
				createResume.getListRefForm().remove(count);
			}
		} catch (Exception jbex) {
			LOGGER.error("Error Occured While removing reference" + jbex);
		}
		return id;
	}

	/**
	 * This method is called to add References
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@RequestMapping(value = "/addReferences", method = RequestMethod.POST)
	public ModelAndView addReference(HttpSession session,
			CreateResume createResume) {
		ReferenceForm form = new ReferenceForm();
		form.setReferenceType(MMJBCommonConstants.REFERENCE_TYPE_PERSONAL);
		ModelAndView model = new ModelAndView();
		model.setViewName("addReference");
		model.addObject("refPositionId", createResume.getListRefForm().size());
		form.setItemId(createResume.getListRefForm().size());
		if (null != createResume.getListRefForm()) {
			createResume.getListRefForm().add(form);
		} else {
			List<ReferenceForm> listRefForms = new ArrayList<ReferenceForm>();
			listRefForms.add(form);
			createResume.setListRefForm(listRefForms);
		}
		return model;
	}

	/**
	 * This method is called to add phone Numbers
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@RequestMapping(value = "/addPhoneNos", method = RequestMethod.POST)
	public ModelAndView addPhoneNumbers(HttpSession session,
			CreateResume createResume) {

		PhoneDetailForm form = new PhoneDetailForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addPhoneNos");

		List<DropDownDTO> phoneTypeList = (List<DropDownDTO>) session
				.getAttribute(PHONE_TYPE_LIST);
		if (null == phoneTypeList) {
			phoneTypeList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
		}
		model.addObject(PHONE_TYPE_LIST, phoneTypeList);
		model.addObject("phNoPositionId", createResume.getListPhoneDtlForm()
				.size());
		form.setItemId(createResume.getListPhoneDtlForm().size());
		if (null != createResume.getListPhoneDtlForm()) {
			createResume.getListPhoneDtlForm().add(form);
		} else {
			List<PhoneDetailForm> listPhDtlForms = new ArrayList<PhoneDetailForm>();
			listPhDtlForms.add(form);
			createResume.setListPhoneDtlForm(listPhDtlForms);
		}
		return model;
	}

	/**
	 * Removes the phone numbers.
	 *
	 * @param session the session
	 * @param createResume the create resume
	 * @param id the id
	 * @return the int
	 */
	@ResponseBody
	@RequestMapping(value = "/removePhoneNos", method = RequestMethod.POST)
	public int removePhoneNumbers(HttpSession session,
			CreateResume createResume, @RequestParam("id") int id) {
		try {
			if (null != createResume.getListPhoneDtlForm()) {
				int count = 0;
				for (PhoneDetailForm phform : createResume
						.getListPhoneDtlForm()) {
					if (phform.getItemId() == id) {
						break;
					}
					count++;
				}

				createResume.getListPhoneDtlForm().remove(count);

			}
		} catch (Exception jbex) {
			LOGGER.error("Error Occured While removing phone number" + jbex);
		}
		return id;
	}

	/**
	 * This method is called to add phone Numbers
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@RequestMapping(value = "/validateAddBlocks", method = RequestMethod.POST)
	public @ResponseBody
	String validateAddBlocks(HttpSession session, CreateResume createResume,
			HttpServletRequest request) {
		String blockType = request.getParameter("blockType");
		String validateMsg = "";
		if (blockType.equalsIgnoreCase("phoneBlock")) {
			validateMsg = resumeValidator.validatePhoneNumbers(createResume);
			if (validateMsg == null) {
				validateMsg = "";
			}
		} else if (blockType.equalsIgnoreCase("workBlock")) {
			validateMsg = resumeValidator.validateWorkExperience(createResume
					.getListWorkExpForm());
			if (validateMsg == null) {
				validateMsg = "";
			}
		} else if (blockType.equalsIgnoreCase("educBlock")) {
			validateMsg = resumeValidator.validateEducation(createResume
					.getListEduForm());
			if (validateMsg == null) {
				validateMsg = "";
			}
		} 
		return validateMsg;
	}

	/**
	 * Populate dropdowns.
	 *
	 * @param model the model
	 * @return the model and view
	 */
	private ModelAndView populateDropdowns(ModelAndView model) {

		List<DropDownDTO> empTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> phoneTypeList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
		List<DropDownDTO> careerLvlList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.CAREER_LEVEL);
		List<DropDownDTO> annualSalarylList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.ANNUAL_SALARY);
		List<DropDownDTO> languagelList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_TYPE);
		List<DropDownDTO> langProficiencylList = populateDropdownsService
				.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_PROFICIENCY_TYPE);
		List<DropDownDTO> eduDegreeList = populateDropdownsService
				.populateEducationDegreesDropdowns();
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();

		model.addObject(EMP_TYPE_LIST, empTypeList);
		model.addObject(PHONE_TYPE_LIST, phoneTypeList);
		model.addObject(CAREER_LVL_LIST, careerLvlList);
		model.addObject(AN_SALARY_LIST, annualSalarylList);
		model.addObject(LANGUAGE_LIST, languagelList);
		model.addObject(PROFIENCY_LIST, langProficiencylList);
		model.addObject(EDU_DEGREE_LIST, eduDegreeList);
		model.addObject(COUNTRY_LIST, countryList);
		model.addObject(STATE_LIST, stateList);

		return model;
	}

	/**
	 * Called to create resume it Contains 1.Contact information 2.Objective
	 * 3.Work Experience 4.Education 5.Certifiation 6.Skills 7.Awards
	 * 8.Memberships 9.Other Details 10.References
	 * 
	 * @param resumeForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewResumeBuilder", method = RequestMethod.POST)
	public ModelAndView viewResumeBuilder(CreateResume resumeForm,
			BindingResult result, @RequestParam(RESUME_ID) int resumeId,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		/**
		 * Introduced a new variable "resumeForm" to resolve PMD issue.
		 */
		CreateResume createResume = resumeForm;
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
			model.addObject(CREATE_RESUME, createResume);
			model.setViewName("viewresume");
		// Ads for resume page
		populateAds(request, session, model);
		return model;

	}

	/**
	 * populate Ads for Resume view page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void populateAds(HttpServletRequest request, HttpSession session,
			ModelAndView model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.JOBSEEKER_RESUME_VIEW);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
		} catch (Exception jbex) {
			LOGGER.error("Error Occured While populating add" + jbex);
		}
	}

	/**
	 * 
	 * @param createResume
	 * @return
	 */
	public void getTotalNotNullField(CreateResume createResume) {
		Long count = 0L;

		// Commented below fields because need to calculate % based on only
		// required fields
		// Its done according to new change requirement

		/*
		 * if (null != createResume.getListCertForm()) { for (CertificationsForm
		 * certForm : createResume.getListCertForm()) { if (null !=
		 * certForm.getCertificationName() &&
		 * !certForm.getCertificationName().equals("")){ count = count + 1L; }
		 * if (null !=certForm.getDateOfReceipt() &&
		 * !certForm.getDateOfReceipt().equals("")){ count = count + 1L; } if
		 * (null !=certForm.getCertifyingAuthority() &&
		 * !certForm.getCertifyingAuthority().equals("")){ count = count + 1L; }
		 * if (null !=certForm.getSummary() &&
		 * !certForm.getSummary().equals("")){ count = count + 1L; } break; } }
		 * if (null != createResume.getListRefForm()) { for (ReferenceForm
		 * refForm : createResume.getListRefForm()) { if (null
		 * !=refForm.getCompanyName() && !refForm.getCompanyName().equals("")){
		 * count = count + 1L; } if (null !=refForm.getEmail() &&
		 * !refForm.getEmail().equals("")){ count = count + 1L; } if (null !=
		 * refForm.getJobTitle() && !refForm.getJobTitle().equals("")){ count =
		 * count + 1L; } if (refForm.getName() != null &&
		 * !refForm.getName().equals("")){ count = count + 1L; } break; } }
		 */
		if (null != createResume.getListEduForm()) {
			for (EducationForm eduForm : createResume.getListEduForm()) {
				/*
				 * if (null !=eduForm.getCertifications() &&
				 * !eduForm.getCertifications().equals("")){ count = count + 1L;
				 * }
				 */
				if ((null != eduForm.getDegreeLvl()
						&& !eduForm.getDegreeLvl().equals("0") && !eduForm
						.getDegreeLvl().equals(MMJBCommonConstants.EMPTY))
						|| (eduForm.isbNotGraduatedYet())) {
					count = count + 1L;
				}
				/*
				 * if (null !=eduForm.getDegrees() &&
				 * !eduForm.getDegrees().equals("")){ count = count + 1L; } if
				 * (null != eduForm.getEndDate() &&
				 * !eduForm.getEndDate().equals("")){ count = count + 1L; } if
				 * (null != eduForm.getFieldOfStudy() &&
				 * !eduForm.getFieldOfStudy().equals("")){ count = count + 1L; }
				 */
				if (null != eduForm.getInstituteName()
						&& !eduForm.getInstituteName().equals(
								MMJBCommonConstants.EMPTY)) {
					count = count + 1L;
				}
				/*
				 * if (null != eduForm.getStartDate() &&
				 * !eduForm.getStartDate().equals("")){ count = count + 1L; }
				 */
				break;

			}
		}
		if (null != createResume.getContactInfoForm()) {
			ContactInfoForm cntInfoForm = createResume.getContactInfoForm();
			if (null != cntInfoForm.getAddressLine1()
					&& !cntInfoForm.getAddressLine1().equals(
							MMJBCommonConstants.EMPTY)) {
				count = count + 1L;
			}
			/*
			 * if (null !=cntInfoForm.getAddressLine2() &&
			 * !cntInfoForm.getAddressLine2().equals("")){ count = count + 1L; }
			 */
			if (null != cntInfoForm.getCity()
					&& !cntInfoForm.getCity().equals(MMJBCommonConstants.EMPTY)) {
				count = count + 1L;
			}
			if (null != cntInfoForm.getCountry()
					&& !cntInfoForm.getCountry().equals("0")
					&& !cntInfoForm.getCountry().equals(
							MMJBCommonConstants.EMPTY)) {
				count = count + 1L;
			}
			/*
			 * if (null !=cntInfoForm.getPhoneNo() &&
			 * !cntInfoForm.getPhoneNo().equals("")){ count = count + 1L; }
			 */
			if (null != cntInfoForm.getState()
					&& !cntInfoForm.getState().equals("0")
					&& !cntInfoForm.getState()
							.equals(MMJBCommonConstants.EMPTY)) {
				count = count + 1L;
			}
			if (null != cntInfoForm.getPostalCode()
					&& !cntInfoForm.getPostalCode().equals("")) {
				count = count + 1L;
			}
			if (null != cntInfoForm.getFirstName()
					&& !cntInfoForm.getFirstName().equals("")) {
				count = count + 1L;
			}
			/*
			 * if (null !=cntInfoForm.getMiddleName() &&
			 * !cntInfoForm.getMiddleName().equals("")){ count = count + 1L; }
			 */
			if (null != cntInfoForm.getLastName()
					&& !cntInfoForm.getLastName().equals("")) {
				count = count + 1L;
			}

		}

		if (null != createResume.getListPhoneDtlForm()) {
			for (PhoneDetailForm phnDtlForm : createResume
					.getListPhoneDtlForm()) {
				if (null != phnDtlForm.getPhoneNumber()
						&& !phnDtlForm.getPhoneNumber().equals("")) {
					count = count + 1L;
				}
				break;
			}

		}
		if (null != createResume.getListWorkExpForm()) {
			for (WorkExpForm wrkExpForm : createResume.getListWorkExpForm()) {
				/*
				 * if (null != wrkExpForm.getAnnualSalary() &&
				 * !wrkExpForm.getAnnualSalary().equals("") &&
				 * !wrkExpForm.getAnnualSalary().equals("0")) { count = count +
				 * 1L; }
				 */
				if (null != wrkExpForm.getCurrentCareerLvl()
						&& !wrkExpForm.getCurrentCareerLvl().equals("0")
						&& !wrkExpForm.getCurrentCareerLvl().equals(
								MMJBCommonConstants.EMPTY)) {
					count = count + 1L;
				}
				/*
				 * if (null !=wrkExpForm.getDescription() &&
				 * !wrkExpForm.getDescription().equals("")){ count = count + 1L;
				 * }
				 */
				if (null != wrkExpForm.getEmployerName()
						&& !wrkExpForm.getEmployerName().equals("")) {
					count = count + 1L;
				}
				if (null != wrkExpForm.getEmploymentType()
						&& !wrkExpForm.getEmploymentType().equals("0")
						&& !wrkExpForm.getCurrentCareerLvl().equals(
								MMJBCommonConstants.EMPTY)) {
					count = count + 1L;
				}
				if ((null != wrkExpForm.getEndDate() && !wrkExpForm
						.getEndDate().equals("")) || (wrkExpForm.isbPresent())) {
					count = count + 1L;
				}
				/*
				 * if (null !=wrkExpForm.getHrlyPayRate() &&
				 * !wrkExpForm.getHrlyPayRate().equals("")){ count = count + 1L;
				 * }
				 */
				if (null != wrkExpForm.getJobTitle()
						&& !wrkExpForm.getJobTitle().equals("")) {
					count = count + 1L;
				}
				if (null != wrkExpForm.getStartDate()
						&& !wrkExpForm.getStartDate().equals("")) {
					count = count + 1L;
				}
				if (null != wrkExpForm.getYrsAtPostion()
						&& !wrkExpForm.getYrsAtPostion().equals("")) {
					count = count + 1L;
				}
				break;
			}

		}
		/*
		 * if (null != createResume.getListLangForm()) { for (LanguageForm
		 * langForm : createResume.getListLangForm()) { if (null
		 * !=langForm.getExpLvl() && !langForm.getExpLvl().equals("0")){ count =
		 * count + 1L; } if (null !=langForm.getLanguage() &&
		 * !langForm.getLanguage().equals("")){ count = count + 1L; } break; }
		 * 
		 * } if (null != createResume.getListPhoneDtlForm()) { for
		 * (PhoneDetailForm phnDtlForm : createResume .getListPhoneDtlForm()) {
		 * if (null !=phnDtlForm.getPhoneNumber() &&
		 * !phnDtlForm.getPhoneNumber().equals("")){ count = count + 1L; }
		 * break; }
		 * 
		 * } if (null !=createResume.getObjective() &&
		 * !createResume.getObjective().equals("")) { count = count + 1L;
		 * 
		 * } if (null !=createResume.getSkills() &&
		 * !createResume.getSkills().equals("")) { count = count + 1L;
		 * 
		 * } if (null !=createResume.getAwards() &&
		 * !createResume.getAwards().equals("")) { count = count + 1L;
		 * 
		 * } if (null !=createResume.getMemberships() &&
		 * !createResume.getMemberships().equals("")) { count = count + 1L;
		 * 
		 * } if (null !=createResume.getOtherDetails() &&
		 * !createResume.getOtherDetails().equals("")) { count = count + 1L;
		 * 
		 * }
		 */
		createResume.setTotalProgress((long) Math.round(count * 5.88));

	}

	/**
	 * This method is called to download an uploaded resume.
	 * 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/downloadResume", method = RequestMethod.GET)
	public ModelAndView downloadResume(CreateResume createResume,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		try {
			ResumeDTO resumeDTO = resumeService.editResume(Integer
					.parseInt(createResume.getUploadResumeId()));

			model.setViewName("redirect:/jobSeekerResume/exportResume.html?fileName="
					+ resumeDTO.getFilePath());
		} catch (Exception jbex) {
			LOGGER.error("Error in download resume", jbex);
		}
		return model;

	}

	/**
	 * This method is called to export an uploaded resume.
	 * 
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
			response.setContentType("application/msword");
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
		} catch (Exception e) {
			LOGGER.error("Error while exporting", e);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException ignore) {
					LOGGER.error("Error while exporting", ignore);
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException ignore) {
					LOGGER.error("Error while exporting", ignore);
				}
			}
		}

	}

	/**
	 * This method is called to retrieve resume builder progress status on click
	 * of save button
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getResumeProgress", method = RequestMethod.POST)
	public String getResumeProgess(HttpSession session,
			@ModelAttribute("createResume") CreateResume createResume) {

		getTotalNotNullField(createResume);
		return String.valueOf(createResume.getTotalProgress());
	}

	/**
	 * Method which will parse the specified input file and return the details
	 * in createResume form
	 * 
	 * @param inFile
	 * @param createResume
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public CreateResume callFileParser(File inFile, CreateResume createResume)
			throws ServletException, IOException {
		File file = new File(basedirectorypathUpload +"\\temp.docx");
		
		if (!file.exists()) {
			if (inFile.createNewFile()) {
				LOGGER.debug("Success!");
			} else {
				LOGGER.error("Error, file already exists.");
			}
			FileOutputStream fop = new FileOutputStream(file);

			if (file.exists()) {

				fop.write(services.getBytes());
				fop.flush();
				fop.close();
				LOGGER.debug("The data has been written");
			} else {
				LOGGER.error("File not Found ");
			}
		} else {
			FileOutputStream fop = new FileOutputStream(file);

			if (file.exists()) {
				fop.write(services.getBytes());
				fop.flush();
				fop.close();
				LOGGER.debug("The data has been written");
			} else {
				LOGGER.debug("File not Found");
			}
		}
		try {
			File inFileCopy = inFile;
			FileInputStream fin = new FileInputStream(inFileCopy);
			byte[] fileContent = new byte[(int) inFileCopy.length()];
			fin.read(fileContent);
			String fName=inFileCopy.getName();
			int index = fName.lastIndexOf('.');
			String fileExtn = fName.substring(index + 1);
			String encodedString = new sun.misc.BASE64Encoder()
					.encode(fileContent); 

			StringBuffer soapXML = new StringBuffer();
			soapXML.append("<?xml version='1.0' encoding='utf-8'?>");
			soapXML.append("<soap:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>");
			soapXML.append("<soap:Body>");
			soapXML.append("<ParseResumeBinary xmlns='http://tempuri.org/'>");
			soapXML.append("<filedata>" + encodedString + "</filedata>");
			soapXML.append("<filetype>"+fileExtn+"</filetype>");
			soapXML.append("<key>" + key + "</key>");
			soapXML.append("<version>" + version + "</version>");
			soapXML.append("<countryKey>" + country + "</countryKey>");
			soapXML.append("<subUserId>" + subKey + "</subUserId>");
			soapXML.append("</ParseResumeBinary>");
			soapXML.append("</soap:Body>");
			soapXML.append("</soap:Envelope>");
			URL url = new URL(services); // correction done
			URLConnection urlc = url.openConnection();
			urlc.setRequestProperty("SOAPAction",
					"http://tempuri.org/ParseResumeBinary");

			// correction done
			urlc.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			DataOutputStream printout; // = urlc.getOutputStream ();

			// Let the run-time system (RTS) know that we want input.
			urlc.setDoInput(true);
			// Let the RTS know that we want to do output.
			urlc.setDoOutput(true);

			printout = new DataOutputStream(urlc.getOutputStream());
			printout.writeBytes(soapXML.toString());
			printout.flush();
			printout.close();

			InputStream in = urlc.getInputStream();
			BufferedReader is = new BufferedReader(new InputStreamReader(in));
			String line = "";
			StringBuffer str = new StringBuffer();
			createResume.setParseError(false);
			while ((line = is.readLine()) != null) {
				LOGGER.debug(line);
				if(line.contains("errorcode")){
					LOGGER.debug("Empty File");
					createResume.setParseError(true);
				}
				str.append(line);

			}
			LOGGER.debug(str.toString());

			String ss1 = str.toString();

			String ssp = ss1.replace("&lt;", "<");
			String ssp1 = ssp.replace("&gt;", ">");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is1 = new InputSource();
			is1.setCharacterStream(new StringReader(ssp1));
			Document dom = db.parse(is1);

			createResume = readXmlDocument(dom,createResume);
			if(null!=createResume){
				LOGGER.debug("Parsing from XML to form done successfully");
			}

		} catch (Exception ex) {
			LOGGER.debug(ex.getMessage());

		}
		return createResume;
	}
	/**
	 * Parsing XML content 
	 * @param xmlDocument
	 * @param createResume
	 * @return
	 */
	public CreateResume readXmlDocument(Document xmlDocument,
			CreateResume createResume) {

		try {
			xmlDocument.getDocumentElement().normalize();
			NodeList profile = xmlDocument
					.getElementsByTagName("ResumeParserData");
			if (profile.getLength() > 0) {

				Node profileNode = profile.item(0);
				if (profileNode.getNodeType() == Node.ELEMENT_NODE) {
					Element profileElement = (Element) profileNode;
					NodeList list = profileElement.getChildNodes();
					transCreateResume.convertNodeDetailToResumeForm(createResume,list);
				}
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return createResume;
	}

}
