package com.advanceweb.afc.jb.resume.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

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
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.TransformJobSeekerRegistration;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

/**
 * This class has been created to perform resume activity such as create, delete, edit, download  
 * @author anilm
 * @version 1.0
 * @created Jul 9, 2012
 */

@Controller
@RequestMapping(value = "/jobSeekerResume")
@SessionAttributes("createResume")
public class ResumeController extends AbstractController{
	
	private static final Logger LOGGER = Logger
			.getLogger(ResumeController.class);
	
	private static final String CREATE_RESUME = "createResume";
	
	private static final String RESUME_ID = "resumeId";
	
	private static final String EMP_TYPE_LIST = "empTypeList";
	
	private static final String PHONE_TYPE_LIST = "phoneTypeList";
	
	private static final String CAREER_LVL_LIST = "careerLvlList";
	
	private static final String AN_SALARY_LIST = "annualSalarylList";
	
	private static final String LANGUAGE_LIST = "languagelList";
	
	private static final String PROFIENCY_LIST = "langProficiencylList";
	
	private static final String EDU_DEGREE_LIST = "eduDegreeList";
	
	private static final String COUNTRY_LIST = "countryList";
	
	private static final String STATE_LIST = "stateList";
	
	private static final String CREATE_RES_BUILDER = "createResumeBuilder";
	
	private static final String JS_REDIRECT_URL = "redirect:/jobSeeker/jobSeekerDashBoard.html";
	
	@Autowired
	private ResumeService resumeService;

	@Autowired
	private TransformCreateResume transCreateResume;
	
	@Autowired
	private AdService adService;

	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private ResumeValidator resumeValidator;

	private @Value("${basedirectorypathUpload}")
	String basedirectorypathUpload;

	private @Value("${resumeWarningMsg}")
	String resumeWarningMsg;

	private @Value("${resumeDuplicate}")
	String resumeDuplicate;

	private @Value("${resumeDeleteSuccess}")
	String resumeDeleteSuccess;
	
	private @Value("${resumeDeleteFailure}")
	String resumeDeleteFailure;

	

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
	 * @param createResume
	 * @param resumeId
	 * @return model
	 */
	@RequestMapping(value = "/editResume", method = RequestMethod.GET)
	public ModelAndView editResume(@RequestParam(RESUME_ID) int resumeId) {
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);
		
		CreateResume createResume = new CreateResume();
		transCreateResume.transformResumeDTOToCreateResume(createResume,
				resumeDTO);
		ModelAndView model = populateResumeDropDowns();		
		model.addObject(CREATE_RESUME, createResume);
		
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

		boolean deleteStatus = resumeService.deleteResume(resumeId,
				(Integer) session.getAttribute(MMJBCommonConstants.USER_ID));
		
		JSONObject deleteStatusJson = new JSONObject();		
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
	public ModelAndView updateResumePopup(CreateResume createResumed,
			HttpSession session, HttpServletRequest request) {
		/**
		 *  Introduced a new variable "createResumed" to resolve PMD issue. 
		 */
		CreateResume createResume =createResumed; 
		ModelAndView model = new ModelAndView();

		ResumeDTO resumeDTO = transCreateResume
				.transformCreateResumeToResumeDTO(createResume);
		resumeDTO.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));

		if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeDTO
				.getResumeType())) {
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
			createResume.setListCertForm(listCertForm);
			createResume.setListEduForm(listEduForm);
			createResume.setListLangForm(listLangForm);
			createResume.setListRefForm(listRefForm);
			createResume.setListWorkExpForm(listWorkExpForm);
			createResume.setContactInfoForm(contactForm);
			createResume.setListPhoneDtlForm(listPhoneDtl);
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
			getAdsForResumePage(request, session, model);
			model.setViewName(CREATE_RES_BUILDER);
		}

		return model;
	}

	/**
	 * This method is called to open the create resume pop up depending on the resume type 
	 * @param resumeType
	 * @return model
	 */
	@RequestMapping(value = "/createResumePopUp", method = RequestMethod.GET)
	public ModelAndView createResumePopUp(
			@RequestParam("resumeType") String resumeType,HttpSession session) {
		
		CreateResume createResume = new CreateResume();

		createResume.setWillingToRelocate(MMJBCommonConstants.RELOCATE_NO);
		createResume
				.setResumeVisibility(MMJBCommonConstants.VISIBILITY_PRIVATE);

		createResume.setResumeType(resumeType);

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
			resumeService.createResumeCopyPaste(resumeDTO);
			model.setViewName(JS_REDIRECT_URL);
		}
		return model;
	}
	
	/**
	 * This method is called to update resume of type copy paste. 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/updateCopyPasteResume", method = RequestMethod.POST)
	public ModelAndView updateCopyPasteResume(CreateResume createResume,
			HttpSession session) {
		ModelAndView model = populateResumeDropDowns();
		ResumeDTO resumeDTO = transCreateResume
				.transformCreateResumeToResumeDTO(createResume);
		resumeDTO.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));
		resumeService.updateResumeCopyPaste(resumeDTO);
		model.setViewName(JS_REDIRECT_URL);
		return model;
	}

	/**
	 * This method is called to save resume of type upload. 
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
						resumeDTO = resumeService.createResumeUpload(resumeDTO);

						File dest = new File(resumeDTO.getFilePath());
						file.transferTo(dest);
				}
			} catch (Exception e) {
				LOGGER.error(e);
			}

			model.setViewName(JS_REDIRECT_URL);
		}
		return model;
	}

	/**
	 * This method is called to update resume of type upload. 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/updateResumeUpload", method = RequestMethod.POST)
	public ModelAndView updateResumeUpload(CreateResume createResume,
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
					}
				}
			} catch (Exception e) {
				LOGGER.error(e);
			}
			resumeDTO.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));
			resumeService.updateResumeUpload(resumeDTO);
			model.setViewName(JS_REDIRECT_URL);
		}
		return model;
	}
	
	/**
	 * This method is called to save resume resume pop up & move to Advanced Resume Builder. 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/saveCreateResume", method = RequestMethod.GET)
	public ModelAndView saveCreateResume(CreateResume createResume,
			HttpSession session) {
		ResumeDTO resumeDTO = new ResumeDTO();
		ModelAndView model = new ModelAndView();
		
		resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
		
		resumeDTO.setUserId((Integer) session.getAttribute(MMJBCommonConstants.USER_ID));
		
		resumeDTO = resumeService.createResume(resumeDTO);
		
		transCreateResume.transformResumeDTOToCreateResume(createResume, resumeDTO);
				
		model.addObject(CREATE_RESUME, createResume);
		model.setViewName("redirect:/jobSeekerResume/createResumeBuilder.html");
		
		return model;
	}	
	
	/**
	 * This method is called to save resume resume pop up & move to Advanced Resume Builder. 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/createResumeBuilder", method = RequestMethod.GET)
	public ModelAndView createResumebuilder(CreateResume createResume,
			HttpSession session, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		
		/*createResume.setUploadResumeId(String.valueOf(createResume
				.getUploadResumeId()));*/
		
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
		getAdsForResumePage(request, session, model);
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
	public ModelAndView saveResumeBuilder(CreateResume createResumed,
			HttpSession session, HttpServletRequest request) {
		/**
		 *  Introduced a new variable "createResumed" to resolve PMD issue. 
		 */
		CreateResume createResume =createResumed;
		if("0".equals(createResume.getUploadResumeId())){
			createResume.setUploadResumeId(null);
		}
		ModelAndView model = new ModelAndView();
		ResumeDTO resumeDTO = new ResumeDTO();
		createResume.setUserId((Integer) session.getAttribute(MMJBCommonConstants.USER_ID));
		String errorMessage = resumeValidator.validateResumeBuilder(createResume);

		if (!StringUtils.isEmpty(errorMessage)) {

			model = populateDropdowns(model);

			model.addObject(CREATE_RESUME, createResume);
			model.addObject("errorMessage", errorMessage);
			// Ads for resume page
			getAdsForResumePage(request, session, model);
			model.setViewName(CREATE_RES_BUILDER);
			return model;
		}
		
		resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
		//if resume does not exist create resume
		if(StringUtils.isEmpty(createResume.getUploadResumeId())){
			resumeDTO = resumeService.createResume(resumeDTO);
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
		//createResume is a session variable & we have make it null once the resume is saved, 
		//otherwise if we go to create new resume screen we will get the session data displayed in the 
		//create resume screen . So have to reassign the new object to session variable 
		createResume = new CreateResume();
		return model;

	}

	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params = "Preview")
	public ModelAndView previewResumeBuilder(CreateResume createResume) {
		ModelAndView model = new ModelAndView();
		model.addObject(CREATE_RESUME, createResume);
		if(StringUtils.isEmpty(createResume.getUploadResumeId())){
			createResume.setUploadResumeId("0");
		}
		model.setViewName("viewresume");
		return model;

	}
	
	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params = "Back")
	public ModelAndView backToResumeBuilder(CreateResume createResume, HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.addObject(CREATE_RESUME, createResume);
		model = populateDropdowns(model);
		// Ads for resume page
		getAdsForResumePage(request, session, model);
		model.setViewName(CREATE_RES_BUILDER);
		return model;

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
		
		List<DropDownDTO> empTypeList = (List<DropDownDTO>) session.getAttribute(EMP_TYPE_LIST);
		List<DropDownDTO> careerLvlList = (List<DropDownDTO>) session.getAttribute(CAREER_LVL_LIST);
		List<DropDownDTO> annualSalarylList = (List<DropDownDTO>) session.getAttribute(AN_SALARY_LIST);
		if(null == empTypeList){
			empTypeList = populateDropdownsService.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		}
				
		if(null == careerLvlList){
			careerLvlList = populateDropdownsService.populateResumeBuilderDropdowns(MMJBCommonConstants.CAREER_LEVEL);
		}
		
		if(null == annualSalarylList){
			annualSalarylList = populateDropdownsService.populateResumeBuilderDropdowns(MMJBCommonConstants.ANNUAL_SALARY);
		}
		
		model.addObject(CAREER_LVL_LIST, careerLvlList);
		model.addObject(EMP_TYPE_LIST, empTypeList);
		model.addObject(AN_SALARY_LIST,annualSalarylList);
		model.addObject("workExpPositionId", createResume.getListWorkExpForm()
				.size());
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
	 * This method is called to add Education Details
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@RequestMapping(value = "/addEducationDetails", method = RequestMethod.POST)
	public ModelAndView addEducationDetails(HttpSession session,
			CreateResume createResume) {

		EducationForm form = new EducationForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addEducation");
		
		List<DropDownDTO> eduDegreeList = (List<DropDownDTO>) session.getAttribute(EDU_DEGREE_LIST);
		
		if(null == eduDegreeList){
			eduDegreeList = populateDropdownsService.populateEducationDegreesDropdowns();
		}
		model.addObject(EDU_DEGREE_LIST,eduDegreeList);
		model.addObject("eduPositionId", createResume.getListEduForm().size());
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

		List<DropDownDTO> langProficiencylList = (List<DropDownDTO>) session.getAttribute(PROFIENCY_LIST);				
		List<DropDownDTO> languagelList =(List<DropDownDTO>) session.getAttribute(LANGUAGE_LIST);
		
		if(null == languagelList){
			languagelList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_TYPE);
		}
		
		if(null == langProficiencylList){
			langProficiencylList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_PROFICIENCY_TYPE);
		}
		
		model.addObject(LANGUAGE_LIST, languagelList);	
		model.addObject(PROFIENCY_LIST,langProficiencylList);
		model.addObject("langPositionId", createResume.getListLangForm().size());
		
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
		
		List<DropDownDTO> phoneTypeList = (List<DropDownDTO>) session.getAttribute(PHONE_TYPE_LIST);
		if(null == phoneTypeList){
			phoneTypeList = populateDropdownsService
					.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
		}
		model.addObject(PHONE_TYPE_LIST, phoneTypeList);
		model.addObject("phNoPositionId", createResume.getListPhoneDtlForm().size());
		if (null != createResume.getListPhoneDtlForm()) {
			createResume.getListPhoneDtlForm().add(form);
		} else {
			List<PhoneDetailForm> listPhDtlForms = new ArrayList<PhoneDetailForm>();
			listPhDtlForms.add(form);
			createResume.setListPhoneDtlForm(listPhDtlForms);
		}
		return model;
	}

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
	 * @param createResume
	 * @param result
	 * @param session 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/viewResumeBuilder", method = RequestMethod.POST)
	public ModelAndView viewResumeBuilder(CreateResume createResumed,
			BindingResult result, @RequestParam(RESUME_ID) int resumeId,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
			model.addObject(CREATE_RESUME, createResume);
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
			model.addObject(CREATE_RESUME, createResume);
			model.setViewName("viewCopyPasteResume");
		}
		// Ads for resume page
		getAdsForResumePage(request, session, model);
		return model;

	}

	/**
	 * Get Ads for Resume view page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void getAdsForResumePage(HttpServletRequest request,
			HttpSession session, ModelAndView model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.JOBSEEKER_RESUME_VIEW);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageTop", bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageBtm", bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param createResume
	 * @return
	 */
	public void getTotalNotNullField(CreateResume createResume) {
		Long count = 0L;
		if (null != createResume.getListCertForm()) {
			for (CertificationsForm certForm : createResume.getListCertForm()) {
				if (null != certForm.getCertificationName()
						&& !certForm.getCertificationName().equals("")){
					count = count + 1L;
				}
				if (null !=certForm.getDateOfReceipt()
						&& !certForm.getDateOfReceipt().equals("")){
					count = count + 1L;
				}
				if (null !=certForm.getInstituteName()
						&& !certForm.getInstituteName().equals("")){
					count = count + 1L;
				}
				if (null !=certForm.getSummary()
						&& !certForm.getSummary().equals("")){
					count = count + 1L;
				}
				break;
			}
		}
		if (null != createResume.getListRefForm()) {
			for (ReferenceForm refForm : createResume.getListRefForm()) {
				if (null !=refForm.getCompanyName()
						&& !refForm.getCompanyName().equals("")){
					count = count + 1L;
				}
				if (null !=refForm.getEmail() && !refForm.getEmail().equals("")){
					count = count + 1L;
				}
				if (null != refForm.getJobTitle()
						&& refForm.getJobTitle().equals("")){
					count = count + 1L;
				}
				if (refForm.getName() != null && refForm.getName().equals("")){
					count = count + 1L;
				}
				break;
			}
		}
		if (null != createResume.getListEduForm()) {
			for (EducationForm eduForm : createResume.getListEduForm()) {
				if (null !=eduForm.getCertifications()
						&& !eduForm.getCertifications().equals("")){
					count = count + 1L;
				}
				if (null !=eduForm.getDegreeLvl()
						&& !eduForm.getDegreeLvl().equals("")){
					count = count + 1L;
				}
				if (null !=eduForm.getDegrees()
						&& !eduForm.getDegrees().equals("")){
					count = count + 1L;
				}
				if (null != eduForm.getEndDate() && !eduForm.getEndDate().equals("")){
					count = count + 1L;
				}
				if (null != eduForm.getFieldOfStudy()
						&& !eduForm.getFieldOfStudy().equals("")){
					count = count + 1L;
				}
				if (null != eduForm.getInstituteName()
						&& !eduForm.getInstituteName().equals("")){
					count = count + 1L;
				}
				if (null !=eduForm.getLanguage()
						&& !eduForm.getLanguage().equals("")){
					count = count + 1L;
				}
				if (null != eduForm.getStartDate()
						&& !eduForm.getStartDate().equals("")){
					count = count + 1L;
				}
				break;

			}
		}
		if (null != createResume.getContactInfoForm()) {
			ContactInfoForm cntInfoForm = createResume.getContactInfoForm();
			if (null !=cntInfoForm.getAddressLine1()
					&& !cntInfoForm.getAddressLine1().equals("")){
				count = count + 1L;
			}
			if (null !=cntInfoForm.getAddressLine2()
					&& !cntInfoForm.getAddressLine2().equals("")){
				count = count + 1L;
			}
			if (null !=cntInfoForm.getCity() && !cntInfoForm.getCity().equals("")){
				count = count + 1L;
			}
			if (null !=cntInfoForm.getCountry()
					&& !cntInfoForm.getCountry().equals("")){
				count = count + 1L;
			}
			if (null !=cntInfoForm.getPhoneNo()
					&& !cntInfoForm.getPhoneNo().equals("")){
				count = count + 1L;
			}
			if (null !=cntInfoForm.getState() && !cntInfoForm.getState().equals("")){
				count = count + 1L;
			}
			if (null != cntInfoForm.getPostalCode()
					&& !cntInfoForm.getPostalCode().equals("")){
				count = count + 1L;
			}
			if (null !=cntInfoForm.getFirstName()
					&& !cntInfoForm.getMiddleName().equals("")){
				count = count + 1L;
			}
			if (null !=cntInfoForm.getMiddleName()
					&& !cntInfoForm.getMiddleName().equals("")){
				count = count + 1L;
			}
			if (null !=cntInfoForm.getLastName()
					&& !cntInfoForm.getMiddleName().equals("")){
				count = count + 1L;
			}

		}
		if (null != createResume.getListWorkExpForm()) {
			for (WorkExpForm wrkExpForm : createResume.getListWorkExpForm()) {
				if (null != wrkExpForm.getAnnualSalary()
						&& !wrkExpForm.getAnnualSalary().equals("")
						&& !wrkExpForm.getAnnualSalary().equals("0")) {
					count = count + 1L;
				}
				if (null !=wrkExpForm.getCurrentCareerLvl()
						&& !wrkExpForm.getCurrentCareerLvl().equals("")){
					count = count + 1L;
				}
				if (null !=wrkExpForm.getDescription()
						&& !wrkExpForm.getDescription().equals("")){
					count = count + 1L;
				}
				if (null !=wrkExpForm.getEmployerName()
						&& !wrkExpForm.getEmployerName().equals("")){
					count = count + 1L;
				}
				if (null !=wrkExpForm.getEmploymentType()
						&& !wrkExpForm.getEmploymentType().equals("")){
					count = count + 1L;
				}
				if (null != wrkExpForm.getEndDate() 
						&& !wrkExpForm.getEndDate().equals("")){
					count = count + 1L;
				}
				if (null !=wrkExpForm.getHrlyPayRate()
						&& !wrkExpForm.getHrlyPayRate().equals("")){
					count = count + 1L;
				}
				if (null !=wrkExpForm.getJobTitle()
						&& !wrkExpForm.getJobTitle().equals("")){
					count = count + 1L;
				}
				if (null !=wrkExpForm.getStartDate()
						&& !wrkExpForm.getStartDate().equals("")){
					count = count + 1L;
				}
				if (null !=wrkExpForm.getYrsAtPostion()
						&& !wrkExpForm.getYrsAtPostion().equals("")){
					count = count + 1L;
				}
				break;
			}

		}
		if (null != createResume.getListLangForm()) {
			for (LanguageForm langForm : createResume.getListLangForm()) {
				if (null !=langForm.getExpLvl() && !langForm.getExpLvl().equals("")){
					count = count + 1L;
				}
				if (null !=langForm.getLanguage()
						&& !langForm.getLanguage().equals("")){
					count = count + 1L;
				}
				break;
			}

		}
		if (null != createResume.getListPhoneDtlForm()) {
			for (PhoneDetailForm phnDtlForm : createResume
					.getListPhoneDtlForm()) {
				if (null !=phnDtlForm.getPhoneNumber()
						&& !phnDtlForm.getPhoneNumber().equals("")){
					count = count + 1L;
				}
				if (null !=phnDtlForm.getPhoneType()
						&& !phnDtlForm.getPhoneType().equals("")){
					count = count + 1L;
				}
				break;
			}

		}
		if (null !=createResume.getObjective()
				&& !createResume.getObjective().equals("")) {
			count = count + 1L;

		}
		if (null !=createResume.getSkills() && !createResume.getSkills().equals("")) {
			count = count + 1L;

		}
		if (null !=createResume.getAwards() && !createResume.getAwards().equals("")) {
			count = count + 1L;

		}
		if (null !=createResume.getMemberships()
				&& !createResume.getMemberships().equals("")) {
			count = count + 1L;

		}
		if (null !=createResume.getOtherDetails()
				&& !createResume.getOtherDetails().equals("")) {
			count = count + 1L;

		}
		createResume.setTotalProgress((long) Math.round(count * 2.08));

	}

	/**
	 * This method is called to download an uploaded resume. 
	 * @param createResume
	 * @return model
	 */
	@RequestMapping(value = "/downloadResume", method = RequestMethod.GET)
	public ModelAndView downloadResume(CreateResume createResume,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		try {
			ResumeDTO resumeDTO = resumeService.editResume(Integer.parseInt(createResume
					.getUploadResumeId()));
			
			model.setViewName("redirect:/jobSeekerResume/exportResume.html?fileName="
					+ resumeDTO.getFilePath());
		} catch (Exception e) {
			LOGGER.info("Error in download resume", e);
		}
		return model;

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
	
	/**
	* This method is called to retrieve resume builder progress status
	* on click of save button
	* 
	* @param session
	* @param createResume
	* @return
	*/
	@ResponseBody
	@RequestMapping(value = "/getResumeProgress", method = RequestMethod.POST)
	public String getResumeProgess(HttpSession session, @ModelAttribute("createResume") CreateResume createResume) {
		
		getTotalNotNullField(createResume);		
		return String.valueOf(createResume.getTotalProgress());
	}

}
