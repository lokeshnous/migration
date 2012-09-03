package com.advanceweb.afc.jb.employer.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
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


import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

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
	
	private static final Logger LOGGER = Logger
			.getLogger(BrandingTemplateController.class);

	@Autowired
	private BrandingTemplateService brandingTemplateService;

	@Autowired
	private TransformEmployerBrandTemplate transformEmpoyerBrandTemplate;

	@Autowired
	private BrandingTemplateValidation brandingTemplateValidation;

	private @Value("${baseDirectoryPathImageAndMedia}")
	String baseDirectoryPathImageAndMedia;

//	@Value("${ajaxNavigationPath}")
//	private String ajaxNavigationPath;
	
	private static final String STR_BRANDINGTEMPLATEFORM = "brandingTemplateForm";
	private static final String STR_CREATEBRANDINGTEMPLATE = "createBrandingTemplate";
	private static final String STR_LOGOFILEDATA = "logoFileData";
	private static final String STR_NOTEMPTY = "NotEmpty";
	private static final String STR_TEMPLATE_ = "Template_";
	private static final String STR_SILVERPREVIEW = "brandTemplateSilverPreview";
	
	/**
	 * The method is called to create the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/createBrandingTemplate", method = RequestMethod.POST, params = "Save")
	public ModelAndView createEmpBrandTemp(
			@ModelAttribute(STR_BRANDINGTEMPLATEFORM) BrandingTemplateForm brandingTemplateForm,
			BindingResult result, HttpSession session) {

		/**
		 *  Introduced a new variable "templateForm" to resolve PMD issue. 
		 */
		BrandingTemplateForm brandingTemplate = brandingTemplateForm; 

		Boolean status = null;
		BrandingTemplateDTO empBrandTempDTO = new BrandingTemplateDTO();
		ModelAndView model = new ModelAndView();

//		
//		for (int i=0; i<brandingTemplateForm.getListTestimony().size();i++)
//		{
//		System.out.println("TESTIMONY LIST is -->"+brandingTemplateForm.getListTestimony().get(i).getTestimony());
//		}
		
//		Retrieve facilityId and userId from session.
		int facility_id = (Integer)session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		brandingTemplate.setFacilityId(facility_id);
		int user_id = (Integer)session.getAttribute(MMJBCommonConstants.USER_ID);
		brandingTemplate.setEmployerId(user_id);
		
		// Verify if the employer is of Siver caegory
		brandingTemplate.setIsSilverCustomer(Boolean.TRUE);

		// Modify the names of media files to save on File server

		brandingTemplate = modifyMediaName(brandingTemplate);

		// Validate the form data
		
		if(brandingTemplate.getIsSilverCustomer())
		{
			brandingTemplateValidation.validateSilver(brandingTemplate, result);
	
			if (result.hasErrors()) {
				model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
				model.addObject("errorMessage", null);
				model.setViewName(STR_CREATEBRANDINGTEMPLATE);
				return model;
			}
		}
		else
		{
//			TODO Validation for non Silver customers
			
			
//			String errorMessage = brandingTemplateValidation.validateNonSilver(brandingTemplateForm, result);
//			if (!StringUtils.isEmpty(errorMessage)) {
//				model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
//				model.addObject("errorMessage", errorMessage);
//				model.setViewName(CREATEBRANDINGTEMPLATE);
//				return model;
//			}

//		brandingTemplateValidation.validate(templateForm, result);


		}
		
		
		// Upload the media files to File server

		status = uploadMedia(brandingTemplate);
		if (!status) {
			result.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
					"An error occured while saving the file");
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			status = null;
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			return model;
		}

		
		
		
		
		
		// Transform form data to DTO
		empBrandTempDTO = transformEmpoyerBrandTemplate

				.createEmpBrandTempDTO(brandingTemplate);
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
		model.setViewName("manageBrandingTemplatePopup");


		// Call to service layer and DAO
		status = brandingTemplateService.createEmpBrandTemp(empBrandTempDTO);
		if (status) {
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			model.setViewName("employerDashboard");
			return model;
		} else {

			result.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
					"An error occured while creating the Template. Please try again");
			status = null;
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;

		}

//		return model;

	}

	@ResponseBody
	@RequestMapping(value = "/createBrandingTemplate", method = RequestMethod.POST, params = "Preview")
	public ModelAndView previewEmpBrandTemp(
			@ModelAttribute(STR_BRANDINGTEMPLATEFORM) BrandingTemplateForm form,
			BindingResult result, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {


		/**
		 *  Introduced a new variable "templateForm" to resolve PMD issue. 
		 */
		BrandingTemplateForm brandingTemplateForm = form;
		Boolean status = null;
		ModelAndView model = new ModelAndView();


		// Verify if the employer is of Silver category
		brandingTemplateForm.setIsSilverCustomer(Boolean.TRUE);

		// Verify if the employer is of Siver caegory

		// Modify the names of media files to save on File server
		brandingTemplateForm = modifyMediaName(brandingTemplateForm);

		// Validate the form data

		brandingTemplateValidation.validateSilver(brandingTemplateForm, result);

//		brandingTemplateValidation.validate(templateForm, result);


		if (result.hasErrors()) {
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;
		}

		// Upload the media files to File server
		status = uploadMedia(brandingTemplateForm);
		if (!status) {
			result.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
					"An error occured while saving the file");
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			status = null;
			return model;
		}

		if (brandingTemplateForm.getIsSilverCustomer()) {
			model.setViewName(STR_SILVERPREVIEW);
		} else {
			model.setViewName("brandTemplateGoldPreview");
		}

		
		return model;
	}

	
	
	@RequestMapping(value = "/previewExisting", method = RequestMethod.POST)
	public ModelAndView previewExisting(
			BrandingTemplateForm brandingTemplateForm,
			@RequestParam("templateId") int templateId, HttpSession session) {

		BrandingTemplateDTO templateDTO = brandingTemplateService
				.editBrandingTemplate(templateId);

		ModelAndView model = new ModelAndView();
		transformEmpoyerBrandTemplate.fromBrandDTOToBrandForm(
				brandingTemplateForm, templateDTO);
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName(STR_SILVERPREVIEW);
		return model;

	}	
	
	
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
//	public @ResponseBody JSONObject previewNew(BrandingTemplateForm form, Map model)
	public ModelAndView previewNew(
			@ModelAttribute(STR_BRANDINGTEMPLATEFORM) BrandingTemplateForm form,
			BindingResult result, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

			
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put(ajaxNavigationPath, "../brandingTemplates/brandTemplateSilverPreview");
//		jsonObject.put("brandingTemplateForm",brandingTemplateForm);
//		return jsonObject;
		
		BrandingTemplateForm brandingTemplateForm = form;
		ModelAndView model = new ModelAndView();
		brandingTemplateForm.setLogoPath("C:\\mmsource\\LogoAndMediaFiles\\Logo.jpg");
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName(STR_SILVERPREVIEW);
				
		return model;
		
	}
	
	@RequestMapping(value = "/brandTemplateSilverPreview")
	public ModelAndView previewPopUp(
			Map<String, BrandingTemplateForm> model) {
		return new ModelAndView(STR_SILVERPREVIEW);
	}
	
	@RequestMapping("/viewImage")
	public void getPhoto(@RequestParam("id") String imageId,
			HttpServletResponse response, HttpServletRequest request,
			BrandingTemplateForm brandingTemplateForm) {

		try {
			BufferedImage originalImage = ImageIO.read(new File(imageId));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage,
					imageId.substring(imageId.length() - 3, imageId.length()),
					baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			ResponseEntity<byte[]> result = handleGetMyBytesRequest(imageInByte);
			// Display the image
			write(response, result.getBody());
		} catch (Exception e) {
			
			LOGGER.error(e);

		}
	}

	/**
	 * Writes the report to the output stream
	 */
	public void write(HttpServletResponse response, byte[] byteArray) {

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
			LOGGER.error(e);
		}
	}

	public ResponseEntity<byte[]> handleGetMyBytesRequest(byte[] imageInByte) {
		// Get bytes from somewhere...
		byte[] byteData = imageInByte;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(byteData.length);

		return new ResponseEntity<byte[]>(byteData, responseHeaders,
				HttpStatus.OK);
	}

	/**
	 * The method is is used to create unique media file names.
	 * 
	 * @param brandingTemplateForm
	 * @return brandingTemplateForm
	 */
	public BrandingTemplateForm modifyMediaName(
			BrandingTemplateForm brandingTemplateForm) {
		String logoOrigName = null;
		String mainImageOrigName = null;
		String logoModifiedName = null;
		String mainImageModifiedName = null;

//		String addImageOrigName = null;
//		String videoOrigName = null;
//		String addImageModifiedName = null;
//		String videoModifiedName = null;

		Random random = new Random();

		// MultipartFile logoFile = brandingTemplateForm.getLogoFileData()
		// MultipartFile mainImageFile =
		// brandingTemplateForm.getMainImageFileData();

		logoOrigName = brandingTemplateForm.getLogoFileData()
				.getOriginalFilename();
		mainImageOrigName = brandingTemplateForm.getMainImageFileData()
				.getOriginalFilename();

		logoModifiedName = STR_TEMPLATE_ + random.nextInt(10000) + "_"
				+ logoOrigName;
		mainImageModifiedName = STR_TEMPLATE_ + random.nextInt(10000) + "_"
				+ mainImageOrigName;

		brandingTemplateForm.setLogoPath(baseDirectoryPathImageAndMedia
				+ logoModifiedName);
		brandingTemplateForm.setMainImagePath(baseDirectoryPathImageAndMedia
				+ mainImageModifiedName);

		// Only for Multi media section
		if (!brandingTemplateForm.getIsSilverCustomer()) {
			
//			addImageOrigName = brandingTemplateForm.getAddImageFileData()
//					.getOriginalFilename();
//			videoOrigName = brandingTemplateForm.getVideoFileData()
//					.getOriginalFilename();
//
//			addImageModifiedName = STR_TEMPLATE_ + random.nextInt(10000) + "_"
//					+ addImageOrigName;
//			videoModifiedName = STR_TEMPLATE_ + random.nextInt(10000) + "_"
//					+ videoOrigName;
//
//			brandingTemplateForm.setAddImagePath(baseDirectoryPathImageAndMedia
//					+ addImageModifiedName);
//			brandingTemplateForm.setVideoPath(baseDirectoryPathImageAndMedia
//					+ videoModifiedName);

			
//			videoOrigName = brandingTemplateForm.getVideoFileData()
//					.getOriginalFilename();
//
//			
//			videoModifiedName = STR_TEMPLATE_ + random.nextInt(10000) + "_"
//					+ videoOrigName;
//
//			brandingTemplateForm.setAddImagePath(baseDirectoryPathImageAndMedia
//					+ addImageModifiedName);
//			brandingTemplateForm.setVideoPath(baseDirectoryPathImageAndMedia
//					+ videoModifiedName);

			List<String> addImageModifiedName = new ArrayList<String>();
			List<String> videoModifiedName = new ArrayList<String>();
						
			
			List<AddImageForm> listImages = new ArrayList<AddImageForm>();
			listImages = brandingTemplateForm.getListAddImages();
			
			for(AddImageForm image: listImages)
			{
				addImageModifiedName.add(baseDirectoryPathImageAndMedia+STR_TEMPLATE_ + random.nextInt(10000) + "_"+ image.getAddImageFileData().getOriginalFilename());
			}
			
			brandingTemplateForm.setListAddImagePath(addImageModifiedName);
			
			List<VideoForm> listVideos = new ArrayList<VideoForm>();
			listVideos = brandingTemplateForm.getListVideos();
			
			for(VideoForm video: listVideos)
			{
				videoModifiedName.add(baseDirectoryPathImageAndMedia+STR_TEMPLATE_ + random.nextInt(10000) + "_"+ video.getVideoFileData().getOriginalFilename());
			}
			
			brandingTemplateForm.setListVideoPath(videoModifiedName);
			
		}

		return brandingTemplateForm;

	}

	/**
	 * The method is is used to upload the media file to file server.
	 * 
	 * @param brandingTemplateForm
	 * @return Boolean
	 */
	public Boolean uploadMedia(BrandingTemplateForm brandingTemplateForm) {
		Boolean status = null;
		File logoFileDest = new File(brandingTemplateForm.getLogoPath());
		File mainImageFileDest = new File(
				brandingTemplateForm.getMainImagePath());
		try {

			MultipartFile logoFile = brandingTemplateForm.getLogoFileData();
			MultipartFile mainImageFile = brandingTemplateForm
					.getMainImageFileData();
			logoFile.transferTo(logoFileDest);
			mainImageFile.transferTo(mainImageFileDest);

			if (!brandingTemplateForm.getIsSilverCustomer()) {
//				File addImageFileDest = new File(
//						brandingTemplateForm.getAddImagePath());
//				File videoFileDest = new File(
//						brandingTemplateForm.getVideoPath());
//
//				MultipartFile addImageFile = brandingTemplateForm
//						.getAddImageFileData();
//				MultipartFile videoFile = brandingTemplateForm
//						.getVideoFileData();
//				addImageFile.transferTo(addImageFileDest);
//				videoFile.transferTo(videoFileDest);

				
				
				List<File> addImageFilesDest = new ArrayList<File>();
				
				for(String addImagePath: brandingTemplateForm.getListAddImagePath())
				{
					addImageFilesDest.add(new File(addImagePath));
					
				}
				
				
				int indexImage=0;
				for(AddImageForm addImageSource : brandingTemplateForm.getListAddImages())
				{
				
//					addImageSource.getAddImageFileData().transferTo(new File(baseDirectoryPathImageAndMedia+STR_TEMPLATE_ + random.nextInt(10000) + "_"+ addImageSource.getAddImageFileData().getOriginalFilename()));
					addImageSource.getAddImageFileData().transferTo(addImageFilesDest.get(indexImage));
					indexImage++;
				}
				
				
				List<File> videoFilesDest = new ArrayList<File>();
				
				for(String videoPath: brandingTemplateForm.getListVideoPath())
				{
					videoFilesDest.add(new File(videoPath));
					
				}
				
				
				int indexVideo=0;
				for(VideoForm videoSource : brandingTemplateForm.getListVideos())
				{
				
					videoSource.getVideoFileData().transferTo(videoFilesDest.get(indexVideo));
					indexVideo++;
				}
				
				
				
				
			}
			status = Boolean.TRUE;

		} catch (IllegalStateException e) {
			
			status = Boolean.FALSE;
			
			LOGGER.error("ERROR OCCURED: "+e);

		} catch (IOException e) {
			
			status = Boolean.FALSE;
			LOGGER.error("ERROR OCCURED: "+e);

		} catch (Exception e) {

			status = Boolean.FALSE;
			LOGGER.error("ERROR OCCURED: "+e);

		}

		return status;

	}

	/**
	 * The method is called to view the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	// @RequestMapping(value = "/viewEmpBrandTemp", method = RequestMethod.POST)
	// public ModelAndView viewEmpBrandTemp(Map model) {
	// EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
	// empBrandTempDTO.setJpBrandTempId(1);
	// empBrandTempDTO = empBrandTemp.viewEmpBrandTemp(empBrandTempDTO);
	// return new ModelAndView("empBrandTempView");
	// }

	/**
	 * The method is called to edit the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	// @RequestMapping(value = "/editEmpBrandTemp", method = RequestMethod.POST)
	// public ModelAndView editEmpBrandTemp(Map model) {
	// EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
	// empBrandTempDTO.setTemplateName("Test Template Desc updated");
	// empBrandTempDTO.setEmployerId(30);
	// empBrandTempDTO.setJpBrandTempId(9);
	// empBrandTempDTO.setImagePath("c://imageupd2.jpg");
	// empBrandTempDTO.setLogoPath("c://logoupd2.jpg");
	// empBrandTempDTO.setColor("#ffff00");
	// // empBrandTempDTO.setUpdatedDate(new Date().toString());
	// empBrandTempDTO = empBrandTemp.editEmpBrandTemp(empBrandTempDTO);
	// return new ModelAndView("empBrandTempEdit");
	// }

	/**
	 * The method is called to delete the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	// @RequestMapping(value = "/deleteEmpBrandTemp", method =
	// RequestMethod.POST)
	// public ModelAndView deleteEmpBrandTemp(Map model) {
	// Boolean status = null;
	// EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
	//
	// empBrandTempDTO.setJpBrandTempId(11);
	// status = empBrandTemp.deleteEmpBrandTemp(empBrandTempDTO);
	// if (status) {
	// return null;
	// }
	// return new ModelAndView("empBrandTempListPopup");
	// }

	/**
	 * The method is called to close the manageBrandingTemplatePopup.
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/cancelBrandTemp", method = RequestMethod.GET)
	public ModelAndView cancelBrandTemp(Map model) {
//		return new ModelAndView("redirect:/jobSeeker/jobSeekerDashBoard.html");
		return new ModelAndView("redirect:/employer/employerDashBoard.html");
	}
	
	
	// @RequestMapping(value = "/cancelEmpBrandTemp", method =
	// RequestMethod.POST)
	// public ModelAndView cancelEmpBrandTemp(Map model) {
	// // return new
	// ModelAndView("redirect:/jobSeeker/jobSeekerDashBoard.html");
	// return new ModelAndView("redirect:/employer/employerDashBoard.html");
	// }


	/**
	 * The method is called to create a the createBrandingTemplate.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newBrandingTemplate", method = RequestMethod.GET)
	public ModelAndView newBrandTemp(Map modelMap) {
		BrandingTemplateForm brandingTemplateForm = new BrandingTemplateForm();
//		modelMap.put(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		ModelAndView model = new ModelAndView();
		TestimonyForm testimonyForm= new TestimonyForm();
		ArrayList<TestimonyForm> nonEmptyList = new ArrayList<TestimonyForm>();
		nonEmptyList.add(testimonyForm);
		brandingTemplateForm.setListTestimony(nonEmptyList);
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName(STR_CREATEBRANDINGTEMPLATE);
//		Dummy list created to have a non zero List

		return model;
	}

	/**
	 * The method is called to display the createBrandingTemplate with data.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/displayTemplate", method = RequestMethod.GET)
	public ModelAndView displayTemplate(Map modelMap, @ModelAttribute(STR_BRANDINGTEMPLATEFORM) BrandingTemplateForm form,
			BindingResult result, HttpSession session) {
		BrandingTemplateForm brandingTemplateForm = form;

		ModelAndView model = new ModelAndView();
		TestimonyForm testimonyForm= new TestimonyForm();
		ArrayList<TestimonyForm> nonEmptyList = new ArrayList<TestimonyForm>();
		nonEmptyList.add(testimonyForm);
		brandingTemplateForm.setListTestimony(nonEmptyList);
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName(STR_CREATEBRANDINGTEMPLATE);
//		Dummy list created to have a non zero List

		return model;
	}
	
	
	/**
	 * This method is called to add Testimonies
	 * 
	 * @param session
	 * @param brandingTemplateForm
	 * @return
	 */
	@RequestMapping(value = "/addTestimonies", method = RequestMethod.POST)
//	@ResponseBody
	public ModelAndView addTestimonies(HttpSession session,
			BrandingTemplateForm brandingTemplateForm) {

		TestimonyForm testimony= new TestimonyForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addTestimonies");		
		
		model.addObject("testimonyPosId", brandingTemplateForm.getListTestimony().size());
		if (null == brandingTemplateForm.getListTestimony()) {
			List<TestimonyForm> listTestimonies = new ArrayList<TestimonyForm>();
			listTestimonies.add(testimony);
			brandingTemplateForm.setListTestimony(listTestimonies);
		} else {

			brandingTemplateForm.getListTestimony().add(testimony);

		}
		return model;
	}
	
	
	
	/**
	 * The method is called to create a the empBrandTemplatePreview popup.
	 * 
	 * @param model
	 * @return
	 */
	// @RequestMapping(value = "/empBrandTemplatePreview", method =
	// RequestMethod.GET)
	// public ModelAndView previewBrandingTemplate(Map model) {
	// model.put(STR_BRANDINGTEMPLATEFORM, new BrandingTemplateForm());
	// return new ModelAndView("empBrandTemplatePreview");
	// }

	// @RequestMapping(value = "/viewResumeBuilder", method =
	// RequestMethod.POST)
	// public ModelAndView viewResumeBuilder(BrandingTemplateForm
	// brandingTemplateForm,
	// BindingResult result, @RequestParam("resumeId") int resumeId,
	// HttpServletRequest request, HttpServletResponse response) {
	//
	// ModelAndView model = new ModelAndView();
	// ResumeDTO resumeDTO = resumeService.editResume(resumeId);
	// createResume = transCreateResume.transformCreateResumeForm(resumeDTO);
	// List<CertificationsForm> listCertForm = transCreateResume
	// .transformCertForm(resumeDTO.getListCertDTO());
	// List<ReferenceForm> listRefForm = transCreateResume
	// .transformReferenceForm(resumeDTO.getListRefDTO());
	// List<EducationForm> listEduForm = transCreateResume
	// .transformEducationForm(resumeDTO.getListEduDTO());
	// List<WorkExpForm> listWorkExpForm = transCreateResume
	// .transformWorkExpForm(resumeDTO.getListWorkExpDTO());
	// List<LanguageForm> listLangForm = transCreateResume
	// .transformLanguageForm(resumeDTO.getListLangDTO());
	// ContactInfoForm contactForm = transCreateResume
	// .transformContactInfoForm(resumeDTO.getContactInfoDTO());
	// List<PhoneDetailForm> listPhoneDtl = transCreateResume
	// .transformPhoneDetailDTOToForm(resumeDTO.getListPhoneDtl());
	//
	// createResume.setListCertForm(listCertForm);
	// createResume.setListEduForm(listEduForm);
	// createResume.setListLangForm(listLangForm);
	// createResume.setListRefForm(listRefForm);
	// createResume.setListWorkExpForm(listWorkExpForm);
	// createResume.setContactInfoForm(contactForm);
	// createResume.setListPhoneDtlForm(listPhoneDtl);
	// resumeDTO.getContactInfoDTO();
	// if (MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(createResume
	// .getResumeType())) {
	// model.addObject("createResume", createResume);
	// model.setViewName("viewresume");
	// } else if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(createResume
	// .getResumeType())) {
	// try {
	// model.setViewName("redirect:/jobSeekerResume/exportResume.html?fileName="
	// + resumeDTO.getFilePath());
	// return model;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// } else {
	// model.addObject("createResume", createResume);
	// model.setViewName("viewCopyPasteResume");
	// }
	// return model;
	//
	// }

	/**
	 * The method is called to fetch the job posting Branding Templates
	 * 
	 * @param Map
	 *            <String, List<BrandingTemplateDTO>>
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/employer/manageBrandingTemplate", method = RequestMethod.GET)
	public ModelAndView fetchEmpBrandTemp(
			Map<String, List<BrandingTemplateDTO>> model, HttpSession session) {
		List<BrandingTemplateDTO> brandTemplateList = brandingTemplateService
				.getBrandingTemplate((Integer)session.getAttribute(MMJBCommonConstants.USER_ID));
		model.put("templatesList", brandTemplateList);
		return new ModelAndView("manageBrandingTemplatePopup");
	}

	/**
	 * The method is called to edit the job posting Branding Template.
	 * 
	 * @param templateId
	 * @param BrandingTemplateForm
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/employer/editTemplate", method = RequestMethod.GET)
	public ModelAndView editEmpBrandTemp(
			BrandingTemplateForm brandingTemplateForm,
			@RequestParam("templateId") int templateId) {
		BrandingTemplateDTO templateDTO = brandingTemplateService
				.editBrandingTemplate(templateId);
//		TODO retrieve information from DB or session  if Silver/NonSilver customer
		templateDTO.setIsSilverCustomer(Boolean.TRUE);
		ModelAndView model = new ModelAndView();
		transformEmpoyerBrandTemplate.fromBrandDTOToBrandForm(
				brandingTemplateForm, templateDTO);
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName("editBrandingTemplate");
		return model;
	}

	/**
	 * The method is called to update the job posting Branding Template.
	 * 
	 * @param BrandingTemplateForm
	 * @param HttpSession
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/employer/updateBrandingTemplate", method = RequestMethod.POST)
	public ModelAndView updateBrandingTemplate(
			@ModelAttribute(STR_BRANDINGTEMPLATEFORM) BrandingTemplateForm form,
			BindingResult result, HttpSession session) {



		/**
		 *  Introduced a new variable "templateForm" to resolve PMD issue. 
		 */
		BrandingTemplateForm brandingTemplateForm = form;
		Boolean status = null;
		BrandingTemplateDTO templateDTO = new BrandingTemplateDTO();
		ModelAndView model = new ModelAndView();

		brandingTemplateForm.setIsSilverCustomer(Boolean.TRUE);

		// Modify the names of media files to save on File server
		brandingTemplateForm = modifyMediaName(brandingTemplateForm);

		// Validate the form data

		brandingTemplateValidation.validateSilver(brandingTemplateForm, result);
//		brandingTemplateValidation.validate(templateForm, result);


		if (result.hasErrors()) {
			model.setViewName("editBrandingTemplate");
			return model;
		}

		// Upload the media files to File server
		status = uploadMedia(brandingTemplateForm);
		if (!status) {
			result.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
					"An error occured while saving the file");
			model.setViewName("editBrandingTemplate");
			status = null;
			return model;
		}

		// Transform form form data to DTO
		templateDTO = transformEmpoyerBrandTemplate
				.createEmpBrandTempDTO(brandingTemplateForm);

		// call to service layer
		status = brandingTemplateService.updateBrandingTemplate(templateDTO);
		if (status) {
			model.setViewName("redirect:/employer/employerDashBoard.html");
		}
		model.setViewName("redirect:/employer/employerDashBoard.html");
		return model;
	}

	/**
	 * The method is called to delete the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer/deleteBrandingTemplate", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject deleteEmpBrandTemp(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("templateId") int templateId) {
		int deleteUserId=(Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
		
		boolean status = brandingTemplateService
				.deleteBrandingTemplate(templateId, deleteUserId);
		JSONObject statusJson = new JSONObject();
		if (status) {
			statusJson.put("success", "template deleted successfully");
			return statusJson;
		} else {
			statusJson.put("failed", "failed");
			return statusJson;
		}
	}

}
