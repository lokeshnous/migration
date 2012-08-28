package com.advanceweb.afc.jb.employer.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
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
	private BrandingTemplateService brandingTemplateService;
	
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
	@RequestMapping(value = "/manageBrandingTemplate", method = RequestMethod.GET)
	public ModelAndView fetchEmpBrandTemp(Map<String, Object> model) {

//		MerUserDTO merUserDTO = new MerUserDTO();
//		merUserDTO.setUserId(36);
//		List<EmpBrandTempDTO> empBrandTempDTOs = empBrandTemp
//				.fetchEmpBrandTemp(merUserDTO);
//		
//		model.put("templatesList", empBrandTempDTOs);

		return new ModelAndView("manageBrandingTemplatePopup");
	}

	/**
	 * The method is called to create the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createBrandingTemplate", method = RequestMethod.POST, params="Save")
	public ModelAndView createEmpBrandTemp( @ModelAttribute("brandingTemplateForm") BrandingTemplateForm brandingTemplateForm, 
			BindingResult result, HttpSession session) 
	{
		Boolean status = null;
		BrandingTemplateDTO empBrandTempDTO = new BrandingTemplateDTO();
		ModelAndView model = new ModelAndView();
		
//		facility id will be available in session. Similarly take user id.
//		session.getAttribute(MMJBCommonConstants.FACILITY_ID)
		
//		Verify if the employer is of Siver caegory
		brandingTemplateForm.setIsSilverCustomer(Boolean.TRUE);
		
//		Modify the names of media files to save on File server
		brandingTemplateForm = modifyMediaName(brandingTemplateForm);

//		Validate the form data
		brandingTemplateValidation.validate(brandingTemplateForm, result);
		
		if(result.hasErrors()){
			model.setViewName("createBrandingTemplate");
			return model;
		}

//		Upload the media files to File server
		status=uploadMedia(brandingTemplateForm);
		if(!status)
		{
			result.rejectValue("logoFileData", "NotEmpty", "An error occured while saving the file");
			model.setViewName("createBrandingTemplate");
			status=null;
			return model;
		}
		
//		Transform form data to DTO
		empBrandTempDTO = transformEmpoyerBrandTemp.createEmpBrandTempDTO(brandingTemplateForm);

		model.addObject("brandingTemplateForm",brandingTemplateForm);
		model.setViewName("manageBrandingTemplatePopup");
		
//		Call to service layer and DAO		
		status = brandingTemplateService.createEmpBrandTemp(empBrandTempDTO);
		if (status) {

			return model;
		}

		return model;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/createBrandingTemplate", method = RequestMethod.POST, params="Preview")
	public ModelAndView previewEmpBrandTemp( @ModelAttribute("brandingTemplateForm") BrandingTemplateForm brandingTemplateForm, 
			BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response) 
	{
		Boolean status = null;
		ModelAndView model = new ModelAndView();
		
//		Verify if the employer is of Siver caegory
		brandingTemplateForm.setIsSilverCustomer(Boolean.FALSE);
		
//		Modify the names of media files to save on File server
		brandingTemplateForm = modifyMediaName(brandingTemplateForm);

//		Validate the form data
		brandingTemplateValidation.validate(brandingTemplateForm, result);
		
		if(result.hasErrors()){
			model.setViewName("createBrandingTemplate");
			return model;
		}

//		Upload the media files to File server
		status=uploadMedia(brandingTemplateForm);
		if(!status)
		{
			result.rejectValue("logoFileData", "NotEmpty", "An error occured while saving the file");
			model.setViewName("createBrandingTemplate");
			status=null;
			return model;
		}
		
		if (brandingTemplateForm.getIsSilverCustomer()) 
		{
			model.setViewName("brandTemplateSilverPreview");
		}
		else 
		{
			model.setViewName("brandTemplateGoldPreview");
		}
		
		
//		BrandingTemplateForm btf = (BrandingTemplateForm)session.getAttribute("brandingTemplateForm");
//		System.out.println("SESSION ATTRIBUTE->"+btf.getLogoPath());
//		request.setAttribute("logoImageSource", brandingTemplateForm.getLogoPath());
		
//		try{
//			BufferedImage originalImage = 
//					ImageIO.read(new File(brandingTemplateForm.getLogoPath()));
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write( originalImage, "jpg", baos );
//			baos.flush();
//			byte[] imageInByte = baos.toByteArray();
//			baos.close();
//
//			ResponseEntity<byte[]> result2 =handleGetMyBytesRequest(imageInByte); 
//			// Display the image
//			write(response, result2.getBody());
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		
		return model;
	}
	
	
	@RequestMapping("/viewImage")
	public void getPhoto(@RequestParam("id") String imageId, HttpServletResponse response,HttpServletRequest request, BrandingTemplateForm brandingTemplateForm) {
		
		try{
			BufferedImage originalImage = 
					ImageIO.read(new File(imageId));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( originalImage, imageId.substring(imageId.length()-3, imageId.length()), baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			ResponseEntity<byte[]> result =handleGetMyBytesRequest(imageInByte); 
			// Display the image
			write(response, result.getBody());
		}catch(Exception e){

		}
	}

	/**
	 * Writes the report to the output stream
	 */
	public  void write(HttpServletResponse response, byte[] byteArray) {

		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			outputStream.write(byteArray);
			// Flush the stream
			outputStream.flush();
			// Close the stream
			outputStream.close();

		} catch (Exception e) {
		}
	}
	
	public ResponseEntity< byte[] > handleGetMyBytesRequest(byte[] imageInByte)
	{
		// Get bytes from somewhere...
		byte[] byteData = imageInByte;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType( MediaType.IMAGE_PNG );
		responseHeaders.setContentLength( byteData.length );

		return new ResponseEntity< byte[] >( byteData, responseHeaders, HttpStatus.OK );
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
		
		String addImageOrigName=null;
		String videoOrigName=null;
		String addImageModifiedName=null;
		String videoModifiedName=null;
		
		
		Random random = new Random();
		
//		MultipartFile logoFile = brandingTemplateForm.getLogoFileData()
//		MultipartFile mainImageFile = brandingTemplateForm.getMainImageFileData();
				
		logoOrigName=brandingTemplateForm.getLogoFileData().getOriginalFilename();
		mainImageOrigName=brandingTemplateForm.getMainImageFileData().getOriginalFilename();
		
		logoModifiedName = "Template_"+random.nextInt(10000)+"_"+logoOrigName;
		mainImageModifiedName = "Template_"+random.nextInt(10000)+"_"+mainImageOrigName;
		
		brandingTemplateForm.setLogoPath(baseDirectoryPathImageAndMedia+logoModifiedName);
		brandingTemplateForm.setMainImagePath(baseDirectoryPathImageAndMedia+mainImageModifiedName);
		
//		Only for Multi media section
		if(!brandingTemplateForm.getIsSilverCustomer())
		{
			addImageOrigName=brandingTemplateForm.getAddImageFileData().getOriginalFilename();
			videoOrigName=brandingTemplateForm.getVideoFileData().getOriginalFilename();
			
			addImageModifiedName = "Template_"+random.nextInt(10000)+"_"+addImageOrigName;
			videoModifiedName = "Template_"+random.nextInt(10000)+"_"+videoOrigName;
			
			brandingTemplateForm.setAddImagePath(baseDirectoryPathImageAndMedia+addImageModifiedName);
			brandingTemplateForm.setVideoPath(baseDirectoryPathImageAndMedia+videoModifiedName);
		}
		
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
			
			if(!brandingTemplateForm.getIsSilverCustomer())
			{
				File addImageFileDest = new File(brandingTemplateForm.getAddImagePath());
				File videoFileDest = new File(brandingTemplateForm.getVideoPath());
			
				MultipartFile addImageFile = brandingTemplateForm.getAddImageFileData();
				MultipartFile videoFile = brandingTemplateForm.getVideoFileData();
				addImageFile.transferTo(addImageFileDest);
				videoFile.transferTo(videoFileDest);
				
			}
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
	@RequestMapping(value = "/newBrandingTemplate", method = RequestMethod.GET)
	public ModelAndView newBrandTemp(Map model) {
		model.put("brandingTemplateForm", new BrandingTemplateForm());
		return new ModelAndView("createBrandingTemplate");
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
