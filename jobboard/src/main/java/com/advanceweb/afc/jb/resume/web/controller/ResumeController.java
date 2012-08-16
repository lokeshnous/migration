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
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.TransformJobSeekerRegistration;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.resume.ResumeService;

/**
 * anilm
 * 
 * @version 1.0
 * @created Jul 9, 2012
 */

@Controller
@RequestMapping(value = "/jobSeekerResume")
@SessionAttributes("createResume")
public class ResumeController {

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private TransformCreateResume transCreateResume;
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

	private Long PLACE_KEY;

	/**
	 * This method is called to display resume list belonging to a logged in
	 * jobSeeker
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/manageResume", method = RequestMethod.GET)
	public String getResumes(HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {
		// set this from session
		List<ResumeDTO> resumeDTOList = resumeService
				.retrieveAllResumes((Integer) session.getAttribute("userId"));

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

	private ModelAndView populateResumeDropDowns(CreateResume createResume) {
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
	 * This method is called to delete a resume
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/validateCreateResumePopUp", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject validateCreateResumePopUp(
			@RequestParam("resumeName") String resumeName,
			@RequestParam("resumeId") String resumeId, HttpSession session) {
		// set this from session
		int userId = (Integer) session.getAttribute("userId");
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
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/editResume", method = RequestMethod.GET)
	public ModelAndView editResume(CreateResume createResume,
			@RequestParam("resumeId") int resumeId) {
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);

		transCreateResume.transformResumeDTOToCreateResume(createResume,
				resumeDTO);
		ModelAndView model = populateResumeDropDowns(createResume);
		model.addObject("createResume", createResume);
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
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/deleteResume", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject deleteResume(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("resumeId") int resumeId) {

		boolean deleteStatus = resumeService.deleteResume(resumeId,
				(Integer) session.getAttribute("userId"));
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
	public ModelAndView updateResumePopup(CreateResume createResume,
			HttpSession session) {
		
		PLACE_KEY = (new Random()).nextLong();
		
		ModelAndView model = new ModelAndView();

		ResumeDTO resumeDTO = transCreateResume
				.transformCreateResumeToResumeDTO(createResume);
		// set it from session
		resumeDTO.setUserId((Integer) session.getAttribute("userId"));
		// depending on the resume type either move to resume builder or show
		// excel file

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
			model.addObject("empTypeList", empTypeList);
			model.addObject("phoneTypeList", phoneTypeList);
			model.addObject("careerLvlList", careerLvlList);
			model.addObject("annualSalarylList", annualSalarylList);
			model.addObject("languagelList", languagelList);
			model.addObject("langProficiencylList", langProficiencylList);
			model.addObject("eduDegreeList", eduDegreeList);
			model.addObject("countryList", countryList);
			model.addObject("stateList", stateList);
			
			session.setAttribute("empTypeList", empTypeList);
			session.setAttribute("phoneTypeList", phoneTypeList);
			session.setAttribute("careerLvlList", careerLvlList);
			session.setAttribute("annualSalarylList", annualSalarylList);
			session.setAttribute("languagelList", languagelList);
			session.setAttribute("langProficiencylList", langProficiencylList);
			session.setAttribute("eduDegreeList", eduDegreeList);
			session.setAttribute("countryList", countryList);
			session.setAttribute("stateList", stateList);
			
			// DropDowns end
			getTotalNotNullField(createResume);
			resumeDTO.getContactInfoDTO();
			model.addObject("createResume", createResume);
			model.setViewName("createResumeBuilder");
		}
		/*
		 * else
		 * if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO.getResumeType
		 * ())) { //Show excel file } else{ //Show copy paste resume page }
		 */
		return model;
	}

	@RequestMapping(value = "/createResumePopUp", method = RequestMethod.GET)
	public ModelAndView createResumePopUp(@RequestParam("resumeType") String resumeType) {

		CreateResume createResume = new CreateResume();

		// ResumeDTO resumeDTO = resumeService.getProfileAttributes();
		// createResume.setResumeProfileAttribForm(transformDTOToProfileAttribForm(resumeDTO));

		createResume.setWillingToRelocate(MMJBCommonConstants.RELOCATE_NO);
		createResume.setResumeVisibility(MMJBCommonConstants.VISIBILITY_PRIVATE);

		createResume.setResumeType(resumeType);

		ModelAndView model = populateResumeDropDowns(createResume);
		model.addObject("createResume", createResume);

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

	@RequestMapping(value = "/copyPasteResume", method = RequestMethod.POST)
	public ModelAndView createCopyPasteResume(CreateResume createResume,
			HttpSession session) {
		ModelAndView model = populateResumeDropDowns(createResume);
		if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(createResume
				.getResumeType())) {

			ResumeDTO resumeDTO = transCreateResume
					.transformCreateResumeToResumeDTO(createResume);
			// set it from session
			resumeDTO.setUserId((Integer) session.getAttribute("userId"));
			resumeService.createResumeCopyPaste(resumeDTO);
			model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		}
		return model;
	}

	@RequestMapping(value = "/updateCopyPasteResume", method = RequestMethod.POST)
	public ModelAndView updateCopyPasteResume(CreateResume createResume,
			HttpSession session) {
		ModelAndView model = populateResumeDropDowns(createResume);
		ResumeDTO resumeDTO = transCreateResume
				.transformCreateResumeToResumeDTO(createResume);
		// set it from session
		resumeDTO.setUserId((Integer) session.getAttribute("userId"));
		resumeService.updateResumeCopyPaste(resumeDTO);
		model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		return model;
	}

	@RequestMapping(value = "/createResumeUpload", method = RequestMethod.POST)
	public ModelAndView createResumeUpload(CreateResume createResume,
			HttpSession session) {

		ModelAndView model = populateResumeDropDowns(createResume);
		if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume
				.getResumeType())) {

			ResumeDTO resumeDTO = transCreateResume
					.transformCreateResumeToResumeDTO(createResume);
			String fileName = null, filePath = null;
			try {
				MultipartFile file = createResume.getFileData();

				if (null != file && file.getSize() > 0) {
					if (file.getSize() > 100000) {
						// return "/uploadfile";
					} else {
						fileName = file.getOriginalFilename();
						filePath = basedirectorypathUpload + fileName;
						File dest = new File(filePath);
						file.transferTo(dest);
						resumeDTO.setFileServer(basedirectorypathUpload);
						resumeDTO.setFileName(fileName);
						resumeDTO.setFilePath(filePath);
						// set it from session
						resumeDTO.setUserId((Integer) session.getAttribute("userId"));
						resumeService.createResumeUpload(resumeDTO);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		}
		return model;
	}

	@RequestMapping(value = "/updateResumeUpload", method = RequestMethod.POST)
	public ModelAndView updateResumeUpload(CreateResume createResume,
			HttpSession session) {

		ModelAndView model = populateResumeDropDowns(createResume);
		if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume
				.getResumeType())) {

			ResumeDTO resumeDTO = transCreateResume
					.transformCreateResumeToResumeDTO(createResume);
			String fileName = null, filePath = null;
			try {
				MultipartFile file = createResume.getFileData();

				if (null != file && file.getSize() > 0) {
					if (file.getSize() > 100000) {
						// return "/uploadfile";
					} else {
						fileName = file.getOriginalFilename();
						filePath = basedirectorypathUpload + fileName;
						File dest = new File(filePath);
						file.transferTo(dest);
						resumeDTO.setFileServer(basedirectorypathUpload);
						resumeDTO.setFileName(fileName);
						resumeDTO.setFilePath(filePath);
						// set it from session
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			resumeDTO.setUserId((Integer) session.getAttribute("userId"));
			resumeService.updateResumeUpload(resumeDTO);
			model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		}
		return model;
	}

	/**
	 * This method is called to display resume list belonging to a logged in
	 * jobSeeker
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/createResumeBuilder", method = RequestMethod.GET)
	public ModelAndView createResumebuilder(CreateResume createResume,
			HttpSession session) {
		
		PLACE_KEY = (new Random()).nextLong();
		
		ModelAndView model = new ModelAndView();
		ResumeDTO resumeDTO = transCreateResume
				.transformCreateResumeToResumeDTO(createResume);
		// set it from session
		resumeDTO.setUserId((Integer) session.getAttribute("userId"));
		resumeDTO = resumeService.createResume(resumeDTO);
		createResume.setUploadResumeId(String.valueOf(resumeDTO
				.getUploadResumeId()));
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
		model.addObject("empTypeList", empTypeList);
		model.addObject("phoneTypeList", phoneTypeList);
		model.addObject("careerLvlList", careerLvlList);
		model.addObject("annualSalarylList", annualSalarylList);
		model.addObject("languagelList", languagelList);
		model.addObject("langProficiencylList", langProficiencylList);
		model.addObject("eduDegreeList", eduDegreeList);
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		// DropDowns end
		model.addObject("createResume", createResume);
		model.setViewName("createResumeBuilder");
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
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		if (((Long) session.getAttribute("LAST_PLACE_KEY"))!=null && ((Long) session.getAttribute("LAST_PLACE_KEY")).equals(PLACE_KEY)) {
			model.setViewName("forward:/jobSeeker/jobSeekerDashBoard.html");
			return model;
		}
		
		ResumeDTO resumeDTO = new ResumeDTO();
		createResume.setUserId((Integer) session.getAttribute("userId"));
		String errorMessage = resumeValidator.validateResumeBuilder(createResume);
		
		if (!StringUtils.isEmpty(errorMessage)) {

			model = populateDropdowns(model);

			model.addObject("createResume", createResume);
			model.addObject("errorMessage", errorMessage);
			model.setViewName("createResumeBuilder");
			return model;
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
		session.setAttribute(MMJBCommonConstants.LAST_PLACE_KEY, PLACE_KEY);
		model.setViewName("forward:/jobSeeker/jobSeekerDashBoard.html");
		createResume = null;
		return model;

	}

	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params = "Preview")
	public ModelAndView previewResumeBuilder(CreateResume createResume) {
		ModelAndView model = new ModelAndView();
		model.addObject("createResume", createResume);
		model.setViewName("viewresume");
		return model;

	}

	/**
	 * This method is called to add work experience
	 * 
	 * @param session
	 * @param createResume
	 * @return
	 */
	@RequestMapping(value = "/addWorkExp", method = RequestMethod.POST)
	public ModelAndView addWorkExp(HttpSession session, CreateResume createResume) {
		
		WorkExpForm form = new WorkExpForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addWorkExp");
		model.addObject("empTypeList",session.getAttribute("empTypeList"));
		model.addObject("careerLvlList",session.getAttribute("careerLvlList"));
		model.addObject("annualSalarylList",session.getAttribute("annualSalarylList"));
		model.addObject("workExpPositionId",createResume.getListWorkExpForm().size());
		if (null != createResume.getListCertForm()) {
			createResume.getListWorkExpForm().add(form);
		} else {
			List<WorkExpForm> listWorkExpForms = new ArrayList<WorkExpForm>();
			listWorkExpForms.add(form);
			createResume.setListWorkExpForm(listWorkExpForms);
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
	public ModelAndView addCertifications(HttpSession session,CreateResume createResume) {
		
		CertificationsForm form = new CertificationsForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addCerts");		
		model.addObject("certPositionId",createResume.getListCertForm().size());
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
	public ModelAndView addEducationDetails(HttpSession session, CreateResume createResume) {
		
		EducationForm form = new EducationForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addEducation");
		model.addObject("eduDegreeList", session.getAttribute("eduDegreeList"));
		model.addObject("eduPositionId",createResume.getListEduForm().size());
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
	public ModelAndView addLanguage(HttpSession session, CreateResume createResume) {
		
		LanguageForm form = new LanguageForm();
		form.setLanguage(MMJBCommonConstants.LANGUAGE_ENGLISH);
		ModelAndView model = new ModelAndView();
		model.setViewName("addLanguage");
		model.addObject("languagelList", session.getAttribute("languagelList"));
		model.addObject("langProficiencylList", session.getAttribute("langProficiencylList"));
		model.addObject("langPositionId",createResume.getListLangForm().size());
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
	public ModelAndView addReference(HttpSession session, CreateResume createResume) {
		ReferenceForm form = new ReferenceForm();
		form.setReferenceType(MMJBCommonConstants.REFERENCE_TYPE_PERSONAL);
		ModelAndView model = new ModelAndView();
		model.setViewName("addReference");
		model.addObject("refPositionId",createResume.getListRefForm().size());
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
	public ModelAndView addPhoneNumbers(HttpSession session, CreateResume createResume) {
		
		PhoneDetailForm form = new PhoneDetailForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addPhoneNos");
		model.addObject("phoneTypeList", session.getAttribute("phoneTypeList"));
		model.addObject("phNoPositionId",createResume.getListPhoneDtlForm().size());
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

		model.addObject("empTypeList", empTypeList);
		model.addObject("phoneTypeList", phoneTypeList);
		model.addObject("careerLvlList", careerLvlList);
		model.addObject("annualSalarylList", annualSalarylList);
		model.addObject("languagelList", languagelList);
		model.addObject("langProficiencylList", langProficiencylList);
		model.addObject("eduDegreeList", eduDegreeList);
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);

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
	@RequestMapping(value = "/viewResumeBuilder", method = RequestMethod.POST)
	public ModelAndView viewResumeBuilder(CreateResume createResume,
			BindingResult result, @RequestParam("resumeId") int resumeId,
			HttpServletRequest request, HttpServletResponse response) {

		// ResumeDTO resumeDTO =
		// resumeService.editResume(createResume.getBuilderResumeId());
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
		} else if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume.getResumeType())) {
			try {
				model.setViewName("redirect:/jobSeekerResume/exportResume.html?fileName="+resumeDTO.getFileName());
				return model;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			model.addObject("createResume", createResume);
			model.setViewName("viewCopyPasteResume");
		}
		return model;

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
				if (certForm.getCertificationName() != null
						&& certForm.getCertificationName() != "")
					count = count + 1L;
				if (certForm.getDateOfReceipt() != null
						&& certForm.getDateOfReceipt() != "")
					count = count + 1L;
				if (certForm.getInstituteName() != null
						&& certForm.getInstituteName() != "")
					count = count + 1L;
				if (certForm.getSummary() != null
						&& certForm.getSummary() != "")
					count = count + 1L;
			}
		}
		if (null != createResume.getListRefForm()) {
			for (ReferenceForm refForm : createResume.getListRefForm()) {
				if (refForm.getCompanyName() != null
						&& refForm.getCompanyName() != "")
					count = count + 1L;
				if (refForm.getEmail() != null && refForm.getEmail() != "")
					count = count + 1L;
				if (refForm.getJobTitle() != null
						&& refForm.getJobTitle() != "")
					count = count + 1L;
				if (refForm.getName() != null && refForm.getName() != "")
					count = count + 1L;
			}
		}
		if (null != createResume.getListEduForm()) {
			for (EducationForm eduForm : createResume.getListEduForm()) {
				if (eduForm.getCertifications() != null
						&& eduForm.getCertifications() != "")
					count = count + 1L;
				if (eduForm.getDegreeLvl() != null
						&& eduForm.getDegreeLvl() != "")
					count = count + 1L;
				if (eduForm.getDegrees() != null && eduForm.getDegrees() != "")
					count = count + 1L;
				if (eduForm.getEndDate() != null && eduForm.getEndDate() != "")
					count = count + 1L;
				if (eduForm.getFieldOfStudy() != null
						&& eduForm.getFieldOfStudy() != "")
					count = count + 1L;
				if (eduForm.getInstituteName() != null
						&& eduForm.getInstituteName() != "")
					count = count + 1L;
				if (eduForm.getLanguage() != null
						&& eduForm.getLanguage() != "")
					count = count + 1L;
				if (eduForm.getStartDate() != null
						&& eduForm.getStartDate() != "")
					count = count + 1L;

			}
		}
		if (null != createResume.getContactInfoForm()) {
			ContactInfoForm cntInfoForm = createResume.getContactInfoForm();
			if (cntInfoForm.getAddressLine1() != null
					&& cntInfoForm.getAddressLine1() != "")
				count = count + 1L;
			if (cntInfoForm.getAddressLine2() != null
					&& cntInfoForm.getAddressLine2() != "")
				count = count + 1L;
			if (cntInfoForm.getCity() != null && cntInfoForm.getCity() != "")
				count = count + 1L;
			if (cntInfoForm.getCountry() != null
					&& cntInfoForm.getCountry() != "")
				count = count + 1L;
			if (cntInfoForm.getPhoneNo() != null
					&& cntInfoForm.getPhoneNo() != "")
				count = count + 1L;
			if (cntInfoForm.getState() != null && cntInfoForm.getState() != "")
				count = count + 1L;
			if (cntInfoForm.getPostalCode() != null
					&& cntInfoForm.getPostalCode() != "")
				count = count + 1L;
			if (cntInfoForm.getFirstName() != null
					&& cntInfoForm.getMiddleName() != "")
				count = count + 1L;
			if (cntInfoForm.getMiddleName() != null
					&& cntInfoForm.getMiddleName() != "")
				count = count + 1L;
			if (cntInfoForm.getLastName() != null
					&& cntInfoForm.getMiddleName() != "")
				count = count + 1L;

		}
		if (null != createResume.getListWorkExpForm()) {
			for (WorkExpForm wrkExpForm : createResume.getListWorkExpForm()) {
				if (wrkExpForm.getAnnualSalary() != null
						&& wrkExpForm.getAnnualSalary() != "")
					count = count + 1L;
				if (wrkExpForm.getCurrentCareerLvl() != null
						&& wrkExpForm.getCurrentCareerLvl() != "")
					count = count + 1L;
				if (wrkExpForm.getDescription() != null
						&& wrkExpForm.getDescription() != "")
					count = count + 1L;
				if (wrkExpForm.getEmployerName() != null
						&& wrkExpForm.getEmployerName() != "")
					count = count + 1L;
				if (wrkExpForm.getEmploymentType() != null
						&& wrkExpForm.getEmploymentType() != "")
					count = count + 1L;
				if (wrkExpForm.getEndDate() != null
						&& wrkExpForm.getEndDate() != "")
					count = count + 1L;
				if (wrkExpForm.getHrlyPayRate() != null
						&& wrkExpForm.getHrlyPayRate() != "")
					count = count + 1L;
				if (wrkExpForm.getJobTitle() != null
						&& wrkExpForm.getJobTitle() != "")
					count = count + 1L;
				if (wrkExpForm.getStartDate() != null
						&& wrkExpForm.getStartDate() != "")
					count = count + 1L;
				if (wrkExpForm.getYrsAtPostion() != null
						&& wrkExpForm.getYrsAtPostion() != "")
					count = count + 1L;
			}

		}
		if (null != createResume.getListLangForm()) {
			for (LanguageForm langForm : createResume.getListLangForm()) {
				if (langForm.getExpLvl() != null && langForm.getExpLvl() != "")
					count = count + 1L;
				if (langForm.getLanguage() != null
						&& langForm.getLanguage() != "")
					count = count + 1L;

			}

		}
		if (null != createResume.getListPhoneDtlForm()) {
			for (PhoneDetailForm phnDtlForm : createResume
					.getListPhoneDtlForm()) {
				if (phnDtlForm.getPhoneNumber() != null
						&& phnDtlForm.getPhoneNumber() != "")
					count = count + 1L;
				if (phnDtlForm.getPhoneType() != null
						&& phnDtlForm.getPhoneType() != "")
					count = count + 1L;

			}

		}
		if (createResume.getObjective() != null
				&& createResume.getObjective() != "") {
			count = count + 1L;

		}
		if (createResume.getSkills() != null && createResume.getSkills() != "") {
			count = count + 1L;

		}
		if (createResume.getAwards() != null && createResume.getAwards() != "") {
			count = count + 1L;

		}
		if (createResume.getMemberships() != null
				&& createResume.getMemberships() != "") {
			count = count + 1L;

		}
		if (createResume.getOtherDetails() != null
				&& createResume.getOtherDetails() != "") {
			count = count + 1L;

		}
		createResume.setTotalProgress(count * 2);

	}
	
	

	@RequestMapping(value = "/downloadResume", method = RequestMethod.GET)
	public ModelAndView downloadResume(CreateResume createResume,BindingResult result,HttpServletRequest request,HttpServletResponse response 
		)	throws Exception {
		
		ResumeDTO resumeDTO = resumeService.editResume(Integer.parseInt(createResume.getUploadResumeId()));
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/jobSeekerResume/exportResume.html?fileName="+resumeDTO.getFileName());
		return model;

	}	
	
	@RequestMapping(value = "/exportResume", method = RequestMethod.GET)
	public void exporting(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("fileName") String fileName) throws Exception {

		response.setContentType("application/vnd.ms-word");
		response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
		
		File file = new File(basedirectorypathUpload, fileName);
		response.setHeader("Content-Disposition","inline; filename=\"" + file.getName() + "\"");

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			input = new BufferedInputStream(new FileInputStream(file));
			output = new BufferedOutputStream(response.getOutputStream());

			byte[] buffer = new byte[8192];
			for (int length = 0; (length = input.read(buffer)) > 0;) {
				output.write(buffer, 0, length);
			}
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException ignore) {
				}
			if (input != null)
				try {
					input.close();
				} catch (IOException ignore) {
				}
		}

	}

}
