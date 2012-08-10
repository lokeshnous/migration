package com.advanceweb.afc.jb.resume.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

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
@RequestMapping(value="/jobSeekerResume")
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
	
	private @Value("${basedirectorypathUpload}") String basedirectorypathUpload;

	public static final String FileServerPath = "asdfasd";
	
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
		//set this from session
		int userId = 2;
		List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(userId);		
		
		List<ResumeVisibilityDTO> visiblityList= populateDropdownsService.getResumeVisibilityList();
		Map<String,String> visibilityMap = new HashMap<String , String>();		
		for(int i = 0 ; i< visiblityList.size() ; i++){
			visibilityMap.put(visiblityList.get(i).getVisibilityId(), visiblityList.get(i).getVisibilityName());
		}
		
		List<ResumeDTO> resumeDTOListNew = new ArrayList<ResumeDTO>();  
		for(ResumeDTO resumeDTO : resumeDTOList){
			resumeDTO.setResumeVisibility(visibilityMap.get(resumeDTO.getResumeVisibility()));
			resumeDTOListNew.add(resumeDTO);
		}
		map.put("resumeList", resumeDTOList);
		return "manageResumePopup";
	}
	
	private ModelAndView populateResumeDropDowns(CreateResume createResume) {
		ModelAndView model = new ModelAndView();
		List<ResumeAttribListDTO> resumeTypeList = populateDropdownsService.populateResumeDropdown(MMJBCommonConstants.RESUME_TYPE);
		List<ResumeAttribListDTO> employmentTypeList = populateDropdownsService.populateResumeDropdown(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<ResumeAttribListDTO> workAuthUSList = populateDropdownsService.populateResumeDropdown(MMJBCommonConstants.WORK_AUTH_US);
		List<ResumeAttribListDTO> relocateList = populateDropdownsService.populateResumeDropdown(MMJBCommonConstants.RELOCATE);
		List<ResumeVisibilityDTO> visibilityList = populateDropdownsService.getResumeVisibilityList();
		
		model.addObject("resumeTypeList", resumeTypeList);
		model.addObject("employmentType", employmentTypeList);
		model.addObject("workAuthUS", workAuthUSList);
		model.addObject("resumeVisibility", visibilityList);
		model.addObject("relocate", relocateList);
		return model;
	}
	
	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/validateCreateResumePopUp", method = RequestMethod.GET)
	public @ResponseBody JSONObject validateCreateResumePopUp(@RequestParam("resumeName") String resumeName,@RequestParam("resumeId") String resumeId) {
		//set this from session
		int userId = 2;
		JSONObject warningMessage = new JSONObject();
		if("".equals(resumeId) || resumeId == null){
			int resumeCount = resumeService.findResumeCount(userId);
			if(resumeCount >= 5){
				warningMessage.put("maxResume", "max 5 resumes (total size 750K) can be created, Please delete any existing resume and try again.");
				return warningMessage;
			}
		}
		if(!("".equals(resumeName)) && resumeService.checkDuplicateResumeName(resumeId,resumeName, userId)){
			warningMessage.put("duplicateResume", "Resume Name already exists, Please try again.");
			return warningMessage;
		}
		return warningMessage;		
	}
	
	/**
	 * This method is called to fetch the resume data to edit
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/editResume", method = RequestMethod.GET)
	public ModelAndView editResume(CreateResume createResume,@RequestParam("resumeId") int resumeId) {
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);
		
		transCreateResume.transformResumeDTOToCreateResume(createResume, resumeDTO);
		ModelAndView model = populateResumeDropDowns(createResume);
		model.addObject("createResume", createResume);
		if(MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeDTO.getResumeType())){
			model.setViewName("editresumepopup");
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO.getResumeType())){
			model.setViewName("editUploadResumePopup");
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO.getResumeType())){
			model.setViewName("editCopyPasteResumePopup");
			return model;
		}	
		model.setViewName("editresumepopup");
		return model;
	}
	
	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/deleteResume", method = RequestMethod.POST)
	public @ResponseBody JSONObject deleteResume(HttpServletRequest request,HttpServletResponse response, HttpSession session, @RequestParam("resumeId") int resumeId) {
		//Integer.parseInt(String.valueOf(session.getAttribute("userId")));
		int userId = 2;
		boolean deleteStatus = resumeService.deleteResume(resumeId,userId);
		JSONObject deleteStatusJson = new JSONObject();
		if(deleteStatus){
			deleteStatusJson.put("success", "Profile Deleted Succesfully");
			return deleteStatusJson;
		}
		else{
			deleteStatusJson.put("failed", "Failed to Delete this record");
			return deleteStatusJson;
		}
	}
	
	/**
	 * This method is called to fetch the resume data to edit
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateResumePopup", method = RequestMethod.POST)
	public ModelAndView updateResumePopup(CreateResume createResume) {

		ModelAndView model = new ModelAndView();
		
		ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
		//set it from session
		resumeDTO.setUserId(2);				
		//depending on the resume type either move to resume builder or show excel file 
		
		if(MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeDTO.getResumeType())){
			resumeService.updateResume(resumeDTO);
			resumeDTO = resumeService.editResume(resumeDTO.getUploadResumeId());
			
			transCreateResume.transformCreateResumeForm(resumeDTO);
			List<CertificationsForm> listCertForm = transCreateResume.transformCertForm(resumeDTO.getListCertDTO());
			List<ReferenceForm> listRefForm = transCreateResume.transformReferenceForm(resumeDTO.getListRefDTO());
			List<EducationForm> listEduForm = transCreateResume.transformEducationForm(resumeDTO.getListEduDTO());
			List<WorkExpForm> listWorkExpForm = transCreateResume.transformWorkExpForm(resumeDTO.getListWorkExpDTO());
			List<LanguageForm> listLangForm = transCreateResume.transformLanguageForm(resumeDTO.getListLangDTO());
			ContactInfoForm contactForm = transCreateResume.transformContactInfoForm(resumeDTO.getContactInfoDTO());
	
			createResume.setListCertForm(listCertForm);
			createResume.setListEduForm(listEduForm);
			createResume.setListLangForm(listLangForm);
			createResume.setListRefForm(listRefForm);
			createResume.setListWorkExpForm(listWorkExpForm);
			createResume.setContactInfoForm(contactForm);
			resumeDTO.getContactInfoDTO();
			model.addObject("createResume",createResume);
			model.setViewName("createResumeBuilder");
		}
		/*else if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO.getResumeType()))
		{
			//Show excel file 
		}
		else{
			//Show copy paste resume page
		}*/
		return model;
	}
	
	
	@RequestMapping(value = "/createResumePopUp", method = RequestMethod.GET)
	public ModelAndView createResumePopUp(@RequestParam("resumeType") String resumeType) {
		
		CreateResume createResume = new CreateResume();
		
		//ResumeDTO resumeDTO = resumeService.getProfileAttributes();
		//createResume.setResumeProfileAttribForm(transformDTOToProfileAttribForm(resumeDTO));
		
		createResume.setWillingToRelocate(MMJBCommonConstants.RELOCATE_NO);
		createResume.setResumeVisibility(MMJBCommonConstants.VISIBILITY_PRIVATE);
		
		createResume.setResumeType(resumeType);
		
		ModelAndView model = populateResumeDropDowns(createResume);
		model.addObject("createResume", createResume);
		
		if(MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeType)){
			model.setViewName("createresumepopup");
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD .equals(resumeType)){
			model.setViewName("createResumeUploadPopup");			
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeType)){
			model.setViewName("createResumeCopyPastePopup");
			return model;
		}
		model.setViewName("createresumepopup");
		return model;
	}
	
	@RequestMapping(value = "/copyPasteResume", method = RequestMethod.POST)
	public ModelAndView createCopyPasteResume(CreateResume createResume, HttpSession session) {
		ModelAndView model = populateResumeDropDowns(createResume);
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(createResume.getResumeType())){
			
			ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
			//set it from session
			resumeDTO.setUserId(2);
			resumeService.createResumeCopyPaste(resumeDTO);
			model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		}
		return model;
	}
	
	@RequestMapping(value = "/updateCopyPasteResume", method = RequestMethod.POST)
	public ModelAndView updateCopyPasteResume(CreateResume createResume, HttpSession session) {
		ModelAndView model = populateResumeDropDowns(createResume);
		ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
		//set it from session
		resumeDTO.setUserId(2);
		resumeService.updateResumeCopyPaste(resumeDTO);
		model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		return model;
	}

	@RequestMapping(value = "/createResumeUpload", method = RequestMethod.POST)
	public ModelAndView createResumeUpload(CreateResume createResume){
		
		ModelAndView model = populateResumeDropDowns(createResume);
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume.getResumeType())){
			
			ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
			String fileName = null, filePath = null;
			try {
				MultipartFile file = createResume.getFileData();
				
				if (file.getSize() > 0) {
					if (file.getSize() > 100000) {
						//return "/uploadfile";
					}else{
						fileName = file.getOriginalFilename();
						filePath = basedirectorypathUpload+fileName;
						File dest = new File(filePath);
						file.transferTo(dest);
						resumeDTO.setFileServer(basedirectorypathUpload);
						resumeDTO.setFileName(fileName);
						resumeDTO.setFilePath(filePath);
						//set it from session
						resumeDTO.setUserId(2);
						resumeService.createResumeUpload(resumeDTO);
					}
				}	
			}catch (Exception e) {
				
			}
			
			model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		}
		return model;
	}
	
	
	@RequestMapping(value = "/updateResumeUpload", method = RequestMethod.POST)
	public ModelAndView updateResumeUpload(CreateResume createResume){
		
		ModelAndView model = populateResumeDropDowns(createResume);
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume.getResumeType())){
			
			ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
			String fileName = null, filePath = null;
			try {
				MultipartFile file = createResume.getFileData();
				
				if (file.getSize() > 0) {
					if (file.getSize() > 100000) {
						//return "/uploadfile";
					}else{
						fileName = file.getOriginalFilename();
						filePath = basedirectorypathUpload+fileName;
						File dest = new File(filePath);
						file.transferTo(dest);
						resumeDTO.setFileServer(basedirectorypathUpload);
						resumeDTO.setFileName(fileName);
						resumeDTO.setFilePath(filePath);
						//set it from session
						resumeDTO.setUserId(2);
						resumeService.updateResumeUpload(resumeDTO);
					}
				}	
			}catch (Exception e) {
				
			}
			
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
	public ModelAndView createResumebuilder(CreateResume createResume) {
		
		ModelAndView model = new ModelAndView();
		ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
		//set it from session
		resumeDTO.setUserId(2);
		resumeService.createResume(resumeDTO);
		List<DropDownDTO> empTypeList = populateDropdownsService.populateResumeBuilderDropdowns(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> phoneTypeList = populateDropdownsService.populateResumeBuilderDropdowns(MMJBCommonConstants.PHONE_TYPE);
		List<DropDownDTO> careerLvlList = populateDropdownsService.populateResumeBuilderDropdowns(MMJBCommonConstants.CAREER_LEVEL);
		List<DropDownDTO> annualSalarylList = populateDropdownsService.populateResumeBuilderDropdowns(MMJBCommonConstants.ANNUAL_SALARY);
		List<DropDownDTO> languagelList = populateDropdownsService.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_TYPE);
		List<DropDownDTO> langProficiencylList = populateDropdownsService.populateResumeBuilderDropdowns(MMJBCommonConstants.LANGUAGE_PROFICIENCY_TYPE);
		List<DropDownDTO> eduDegreeList = populateDropdownsService.populateEducationDegreesDropdowns();
		List<CountryDTO>  countryList = populateDropdownsService.getCountryList();
		List<StateDTO>    stateList = populateDropdownsService.getStateList();
		CertificationsForm certForm = new CertificationsForm();
		EducationForm eduForm = new EducationForm();
		LanguageForm langForm = new LanguageForm();
		ReferenceForm refForm = new ReferenceForm();
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
		//DropDowns
		model.addObject("empTypeList",empTypeList);
		model.addObject("phoneTypeList",phoneTypeList);
		model.addObject("careerLvlList",careerLvlList);
		model.addObject("annualSalarylList",annualSalarylList);
		model.addObject("languagelList",languagelList);
		model.addObject("langProficiencylList",langProficiencylList);
		model.addObject("eduDegreeList",eduDegreeList);
		model.addObject("countryList",countryList);
		model.addObject("stateList",stateList);
		//DropDowns end
		model.addObject("createResume", createResume);
		model.setViewName("createResumeBuilder");
		return model;
	}


	/**
	 * Called to create resume
	 * it Contains 
	 * 		1.Contact information
	 * 		2.Objective
	 * 		3.Work Experience
	 * 		4.Education
	 * 		5.Certifiation
	 * 		6.Skills
	 * 		7.Awards
	 * 		8.Memberships
	 * 		9.Other Details
	 * 		10.References	
	 * @param createResume
	 * @param result
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params="Save")
	public ModelAndView saveResumeBuilder(CreateResume createResume){		

		ModelAndView model = new ModelAndView();
		
		ResumeDTO resumeDTO = new ResumeDTO();
		AddressDTO addDTO = transformJobSeekerRegistration.createAddressDTO(createResume.getContactInfoForm());
		ContactInformationDTO contactInfoDTO = transCreateResume.transformContactInfoDTO(createResume.getContactInfoForm());
		contactInfoDTO.setAddressDTO(addDTO);
		List<CertificationDTO> listCertDTO = transCreateResume.transformCertificationDTO(createResume.getListCertForm());
		List<ReferenceDTO> listRefDTO = transCreateResume.transformReferenceDTO(createResume.getListRefForm());
		List<WorkExpDTO> listWorkExpDTO = transCreateResume.transformWorkExpDTO(createResume.getListWorkExpForm());
		List<EducationDTO> listEduDTO = transCreateResume.transformEducationDTO(createResume.getListEduForm());
		List<LanguageDTO> listLangDTO = transCreateResume.transformLanguageDTO(createResume.getListLangForm());
		List<PhoneDetailDTO> listPhoneDTO = transCreateResume.transformPhoneDetailDTO(createResume.getListPhoneDtlForm());
		resumeDTO = transCreateResume.transformResumeDTO(resumeDTO, createResume);
		resumeDTO.setContactInfoDTO(contactInfoDTO);
		resumeDTO.setListCertDTO(listCertDTO);
		resumeDTO.setListEduDTO(listEduDTO);
		resumeDTO.setListLangDTO(listLangDTO);
		resumeDTO.setListRefDTO(listRefDTO);
		resumeDTO.setListWorkExpDTO(listWorkExpDTO);
		resumeDTO.setListPhoneDtl(listPhoneDTO);
		resumeService.createResumeBuilder(resumeDTO);
		
		model.setViewName("forward:/jobSeeker/jobSeekerDashBoard.html");
		
		return model;

	}

	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params="Preview")
	public ModelAndView previewResumeBuilder(CreateResume createResume){		
		ModelAndView model = new ModelAndView();
		model.addObject("createResume", createResume);
		model.setViewName("viewresume");
		return model;

	}

	
	/**
	 * This method is called to save work experience
	 * Ajax call
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addWorkExp", method = RequestMethod.POST)
	public String addWorkExp(HttpServletRequest request, HttpSession session,
			CreateResume createResume, Model model, Map<String, Object> map) {
		List<WorkExpDTO> listWorkExpDTO = transCreateResume.transformWorkExpDTO(createResume.getListWorkExpForm());
		resumeService.addWorkExp(listWorkExpDTO);
		return null;
	}

	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addCertifications", method = RequestMethod.POST)
	public ModelAndView addCertifications(HttpSession session,CreateResume createResume) {
		/*List<CertificationDTO> listCertDTO = transCreateResume.transformCertificationDTO(createResume.getListCertForm());*/
//		resumeService.addCertifications(listCertDTO);
		CertificationsForm form = new CertificationsForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addCerts");
		if(null != createResume.getListCertForm()){
			createResume.getListCertForm().add(form);	
		}else{
			List<CertificationsForm> listCertForms = new ArrayList<CertificationsForm>();
			listCertForms.add(form);
			createResume.setListCertForm(listCertForms);
		}
		return model;
	}

	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addEducationDetails", method = RequestMethod.POST)
	public String addEducationDetails(HttpServletRequest request, HttpSession session,
			CreateResume createResume, Model model, Map<String, Object> map) {
		List<EducationDTO> listEduDTO = transCreateResume.transformEducationDTO(createResume.getListEduForm());
		resumeService.addEducation(listEduDTO);
		return null;
	}

	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addLanguages", method = RequestMethod.POST)
	public String addLanguage(HttpServletRequest request, HttpSession session,
			CreateResume createResume, Model model, Map<String, Object> map) {
		List<LanguageDTO> listLangDTO = transCreateResume.transformLanguageDTO(createResume.getListLangForm());
		resumeService.addLanguage(listLangDTO);
		return null;
	}

	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addReferences", method = RequestMethod.POST)
	public String addReference(HttpServletRequest request, HttpSession session,
			CreateResume createResume, Model model, Map<String, Object> map) {
		List<ReferenceDTO> listRefDTO = transCreateResume.transformReferenceDTO(createResume.getListRefForm());
		resumeService.addReference(listRefDTO);
		return null;
	}

	/*public List<ResumeProfileAttribForm> transformDTOToProfileAttribForm(ResumeDTO resumeDTO){
		
		List<ResumeProfileAttribForm> listForms = new ArrayList<ResumeProfileAttribForm>();
		
		if(null != resumeDTO.getResumeAttribList()){
			for(MerProfileAttribDTO dto : resumeDTO.getResumeAttribList()){
				ResumeProfileAttribForm form = new ResumeProfileAttribForm();
				form.setDropdown(dto.getDropdown());
				form.setStrAttribType(dto.getStrAttribType());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrProfileAttribId(dto.getStrProfileAttribId());
				//form.setStrScreenName(dto.getStrScreenName());
				//form.setStrSectionName(dto.getStrSectionName());
				
				listForms.add(form);
			}
		}
		
		return listForms;
		
	}
	
	public ResumeDTO ProfileAttribFormTotransformDTO(List<ResumeProfileAttribForm> resumeProfileAttribForm){
		
		List<MerProfileAttribDTO> list = new ArrayList<MerProfileAttribDTO>();
		
		if(null != resumeProfileAttribForm){
			for(ResumeProfileAttribForm form : resumeProfileAttribForm){
				MerProfileAttribDTO dto = new MerProfileAttribDTO();
				dto.setDropdown(form.getDropdown());
				dto.setStrAttribType(form.getStrAttribType());
				dto.setStrLabelName(form.getStrLabelName());
				dto.setStrLabelName(form.getStrLabelName());
				dto.setStrProfileAttribId(form.getStrProfileAttribId());
				//dto.setStrScreenName(form.getStrScreenName());
				//dto.setStrSectionName(form.getStrSectionName());
				list.add(dto);
			}
		}
		ResumeDTO resumeDTO = new ResumeDTO();
		resumeDTO.setResumeAttribList(list);
		return resumeDTO;
		
	}*/

	/**
	 * Called to create resume
	 * it Contains 
	 * 		1.Contact information
	 * 		2.Objective
	 * 		3.Work Experience
	 * 		4.Education
	 * 		5.Certifiation
	 * 		6.Skills
	 * 		7.Awards
	 * 		8.Memberships
	 * 		9.Other Details
	 * 		10.References	
	 * @param createResume
	 * @param result
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/viewResumeBuilder", method = RequestMethod.POST)
	public ModelAndView viewResumeBuilder(CreateResume createResume, BindingResult result, @RequestParam("resumeId") int resumeId){

//		ResumeDTO resumeDTO = resumeService.editResume(createResume.getBuilderResumeId());
		ModelAndView model = new ModelAndView();
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);
		transCreateResume.transformCreateResumeForm(resumeDTO);
		List<CertificationsForm> listCertForm = transCreateResume.transformCertForm(resumeDTO.getListCertDTO());
		List<ReferenceForm> listRefForm = transCreateResume.transformReferenceForm(resumeDTO.getListRefDTO());
		List<EducationForm> listEduForm = transCreateResume.transformEducationForm(resumeDTO.getListEduDTO());
		List<WorkExpForm> listWorkExpForm = transCreateResume.transformWorkExpForm(resumeDTO.getListWorkExpDTO());
		List<LanguageForm> listLangForm = transCreateResume.transformLanguageForm(resumeDTO.getListLangDTO());
		ContactInfoForm contactForm = transCreateResume.transformContactInfoForm(resumeDTO.getContactInfoDTO());

		createResume.setListCertForm(listCertForm);
		createResume.setListEduForm(listEduForm);
		createResume.setListLangForm(listLangForm);
		createResume.setListRefForm(listRefForm);
		createResume.setListWorkExpForm(listWorkExpForm);
		createResume.setContactInfoForm(contactForm);
		resumeDTO.getContactInfoDTO();
		
		model.addObject("createResume", createResume);
		model.setViewName("viewresume");
		
		return model;

	}


}
