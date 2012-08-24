package com.advanceweb.afc.jb.employer.web.controller;

import java.io.File;
import java.io.IOException;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.EmpBrandTempDTO;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;

/**
 * <code>EmpBrandTempController</code>This controller belongs to manage job
 * posting Branding Template
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Controller
@RequestMapping("/brandingTemplates")
@SessionAttributes("brandingTemplateForm")
@SuppressWarnings("rawtypes")
public class BrandingTemplateController {
	
	@Autowired
	private BrandingTemplateService empBrandTemp;
	
	@Autowired
	private TransformEmpoyerBrandTemp transformEmpoyerBrandTemp;

	@Autowired
	private BrandingTemplateValidation brandingTemplateValidation;
	
	private @Value("${baseDirectoryPathImageAndMedia}")
	String baseDirectoryPathImageAndMedia;
	
	/**
	 * The method is called to fetch the job posting Branding Templates
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/empBrandTempList", method = RequestMethod.GET)
	public ModelAndView fetchEmpBrandTemp(Map<String, Object> model) {

//		MerUserDTO merUserDTO = new MerUserDTO();
//		merUserDTO.setUserId(36);
//		List<EmpBrandTempDTO> empBrandTempDTOs = empBrandTemp
//				.fetchEmpBrandTemp(merUserDTO);
//		
//		model.put("templatesList", empBrandTempDTOs);

		return new ModelAndView("empBrandTempListPopup");
	}

	/**
	 * The method is called to create the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveEmpBrandTemp", method = RequestMethod.POST, params="Save")
	public ModelAndView createEmpBrandTemp( @ModelAttribute("brandingTemplateForm") BrandingTemplateForm brandingTemplateForm, 
			BindingResult result, HttpSession session) 
	{
		Boolean status = null;
		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
		ModelAndView model = new ModelAndView();
		
//		Modify the names of media files to save on File server
		brandingTemplateForm = modifyMediaName(brandingTemplateForm);

//		Validate the form data
		brandingTemplateValidation.validate(brandingTemplateForm, result);
		
		if(result.hasErrors()){
			model.setViewName("empBrandTemplate");
			return model;
		}

//		Upload the media files to File server
		status=uploadMedia(brandingTemplateForm);
		if(!status)
		{
			result.rejectValue("logoFileData", "NotEmpty", "An error occured while saving the file");
			model.setViewName("empBrandTemplate");
			status=null;
			return model;
		}
		
//		Transform form data to DTO
		empBrandTempDTO = transformEmpoyerBrandTemp.createEmpBrandTempDTO(brandingTemplateForm);

		model.addObject("brandingTemplateForm",brandingTemplateForm);
		model.setViewName("empBrandTempListPopup");
		
//		Call to service layer and DAO		
		status = empBrandTemp.createEmpBrandTemp(empBrandTempDTO);
		if (status) {

			return model;
		}

		return model;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveEmpBrandTemp", method = RequestMethod.POST, params="Preview")
	public ModelAndView previewEmpBrandTemp( @ModelAttribute("brandingTemplateForm") BrandingTemplateForm brandingTemplateForm, 
			BindingResult result, HttpSession session) 
	{
		Boolean status = null;
		ModelAndView model = new ModelAndView();
		
//		Modify the names of media files to save on File server
		brandingTemplateForm = modifyMediaName(brandingTemplateForm);

//		Validate the form data
		brandingTemplateValidation.validate(brandingTemplateForm, result);
		
		if(result.hasErrors()){
			model.setViewName("empBrandTemplate");
			return model;
		}

//		Upload the media files to File server
		status=uploadMedia(brandingTemplateForm);
		if(!status)
		{
			result.rejectValue("logoFileData", "NotEmpty", "An error occured while saving the file");
			model.setViewName("empBrandTemplate");
			status=null;
			return model;
		}
		
		model.setViewName("empBrandTemplatePreview");
		
		return model;
	}
	
	
	/**
	 * The method is is used to create unique media file names.
	 * 
	 * @param brandingTemplateForm
	 * @return brandingTemplateForm
	 */
	public BrandingTemplateForm modifyMediaName(BrandingTemplateForm brandingTemplateForm)
	{
		String logoOrigName=null;
		String mainImageOrigName=null;
		String logoModifiedName=null;
		String mainImageModifiedName=null;
		Random random = new Random();
		
		MultipartFile logoFile = brandingTemplateForm.getLogoFileData();
		MultipartFile mainImageFile = brandingTemplateForm.getMainImageFileData();
				
		logoOrigName=logoFile.getOriginalFilename();
		mainImageOrigName=mainImageFile.getOriginalFilename();
		
		logoModifiedName = "Template_"+random.nextInt(10000)+"_"+logoOrigName;
		mainImageModifiedName = "Template_"+random.nextInt(10000)+"_"+mainImageOrigName;
		
		brandingTemplateForm.setLogoPath(baseDirectoryPathImageAndMedia+logoModifiedName);
		brandingTemplateForm.setMainImagePath(baseDirectoryPathImageAndMedia+mainImageModifiedName);
		
		return brandingTemplateForm;
		
	}
	
	
	/**
	 * The method is is used to upload the media file to file server.
	 * 
	 * @param brandingTemplateForm
	 * @return Boolean
	 */
	public Boolean uploadMedia(BrandingTemplateForm brandingTemplateForm)
	{
		Boolean status=null;
		File logoFileDest = new File(brandingTemplateForm.getLogoPath());
		File mainImageFileDest = new File(brandingTemplateForm.getMainImagePath());
		try {
			
			MultipartFile logoFile = brandingTemplateForm.getLogoFileData();
			MultipartFile mainImageFile = brandingTemplateForm.getMainImageFileData();
			logoFile.transferTo(logoFileDest);
			mainImageFile.transferTo(mainImageFileDest);
			status = Boolean.TRUE;
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			status = Boolean.FALSE;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			status = Boolean.FALSE;
			e.printStackTrace();
		}

		return status;
		
	}

	/**
	 * The method is called to view the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/viewEmpBrandTemp", method = RequestMethod.POST)
//	public ModelAndView viewEmpBrandTemp(Map model) {
//		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
//		empBrandTempDTO.setJpBrandTempId(1);
//		empBrandTempDTO = empBrandTemp.viewEmpBrandTemp(empBrandTempDTO);
//		return new ModelAndView("empBrandTempView");
//	}

	/**
	 * The method is called to edit the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/editEmpBrandTemp", method = RequestMethod.POST)
//	public ModelAndView editEmpBrandTemp(Map model) {
//		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
//		empBrandTempDTO.setTemplateName("Test Template Desc updated");
//		empBrandTempDTO.setEmployerId(30);
//		empBrandTempDTO.setJpBrandTempId(9);
//		empBrandTempDTO.setImagePath("c://imageupd2.jpg");
//		empBrandTempDTO.setLogoPath("c://logoupd2.jpg");
//		empBrandTempDTO.setColor("#ffff00");
////		empBrandTempDTO.setUpdatedDate(new Date().toString());
//		empBrandTempDTO = empBrandTemp.editEmpBrandTemp(empBrandTempDTO);
//		return new ModelAndView("empBrandTempEdit");
//	}

	/**
	 * The method is called to delete the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/deleteEmpBrandTemp", method = RequestMethod.POST)
//	public ModelAndView deleteEmpBrandTemp(Map model) {
//		Boolean status = null;
//		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
//
//		empBrandTempDTO.setJpBrandTempId(11);
//		status = empBrandTemp.deleteEmpBrandTemp(empBrandTempDTO);
//		if (status) {
//			return null;
//		}
//		return new ModelAndView("empBrandTempListPopup");
//	}

	/**
	 * The method is called to close the empBrandTempList popup.
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/cancelEmpBrandTemp", method = RequestMethod.POST)
//	public ModelAndView cancelEmpBrandTemp(Map model) {
////		return new ModelAndView("redirect:/jobSeeker/jobSeekerDashBoard.html");
//		return new ModelAndView("redirect:/employer/employerDashBoard.html");
//	}
	
	/**
	 * The method is called to create a the empBrandTempList popup.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newEmpBrandTemp", method = RequestMethod.GET)
	public ModelAndView newBrandTemp(Map model) {
		model.put("brandingTemplateForm", new BrandingTemplateForm());
		return new ModelAndView("empBrandTemplate");
	}

	/**
	 * The method is called to create a the empBrandTemplatePreview popup.
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/empBrandTemplatePreview", method = RequestMethod.GET)
//	public ModelAndView previewBrandingTemplate(Map model) {
//		model.put("brandingTemplateForm", new BrandingTemplateForm());
//		return new ModelAndView("empBrandTemplatePreview");
//	}
	
	
//	@RequestMapping(value = "/viewResumeBuilder", method = RequestMethod.POST)
//	public ModelAndView viewResumeBuilder(BrandingTemplateForm brandingTemplateForm,
//			BindingResult result, @RequestParam("resumeId") int resumeId,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		ModelAndView model = new ModelAndView();
//		ResumeDTO resumeDTO = resumeService.editResume(resumeId);
//		createResume = transCreateResume.transformCreateResumeForm(resumeDTO);
//		List<CertificationsForm> listCertForm = transCreateResume
//				.transformCertForm(resumeDTO.getListCertDTO());
//		List<ReferenceForm> listRefForm = transCreateResume
//				.transformReferenceForm(resumeDTO.getListRefDTO());
//		List<EducationForm> listEduForm = transCreateResume
//				.transformEducationForm(resumeDTO.getListEduDTO());
//		List<WorkExpForm> listWorkExpForm = transCreateResume
//				.transformWorkExpForm(resumeDTO.getListWorkExpDTO());
//		List<LanguageForm> listLangForm = transCreateResume
//				.transformLanguageForm(resumeDTO.getListLangDTO());
//		ContactInfoForm contactForm = transCreateResume
//				.transformContactInfoForm(resumeDTO.getContactInfoDTO());
//		List<PhoneDetailForm> listPhoneDtl = transCreateResume
//				.transformPhoneDetailDTOToForm(resumeDTO.getListPhoneDtl());
//
//		createResume.setListCertForm(listCertForm);
//		createResume.setListEduForm(listEduForm);
//		createResume.setListLangForm(listLangForm);
//		createResume.setListRefForm(listRefForm);
//		createResume.setListWorkExpForm(listWorkExpForm);
//		createResume.setContactInfoForm(contactForm);
//		createResume.setListPhoneDtlForm(listPhoneDtl);
//		resumeDTO.getContactInfoDTO();
//		if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(createResume
//				.getResumeType())) {
//			model.addObject("createResume", createResume);
//			model.setViewName("viewresume");
//		} else if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume
//				.getResumeType())) {
//			try {
//				model.setViewName("redirect:/jobSeekerResume/exportResume.html?fileName="
//						+ resumeDTO.getFilePath());
//				return model;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else {
//			model.addObject("createResume", createResume);
//			model.setViewName("viewCopyPasteResume");
//		}
//		return model;
//
//	}

	

}
