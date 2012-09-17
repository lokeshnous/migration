package com.advanceweb.afc.jb.employer.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.advanceweb.afc.jb.common.UserDTO;
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

	private @Value("${imageSizeLimit}")
	String imageSizeLimit;

	private @Value("${empBrandFileError}")
	String empBrandFileError;
	 
	private @Value("${empBrandTemplateError}")
	String empBrandTemplateError;
	 
	private @Value("${defaultColor}")
	String defaultColor;
	 

	private static final String STR_BRANDINGTEMPLATEFORM = "brandingTemplateForm";
	private static final String STR_CREATEBRANDINGTEMPLATE = "createBrandingTemplate";
	private static final String STR_LOGOFILEDATA = "logoFileData";
	private static final String STR_NOTEMPTY = "NotEmpty";
	private static final String STR_TEMPLATE_ = "Template_";
	private static final String STR_BRANDTEMPLATEPREVIEW = "brandTemplatePreview";
	private static final String STR_EMPDASHBOARD = "redirect:/employer/employerDashBoard.html";
	private static final String STR_UNDERSCORE = "_";
	
	/**
	 * The method is called to create the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	// @ResponseBody
	@RequestMapping(value = "/createBrandingTemplate", method = RequestMethod.POST, params = "Save")
	public ModelAndView createEmpBrandTemp(
			@ModelAttribute(STR_BRANDINGTEMPLATEFORM) BrandingTemplateForm brandingTemplateForm,
			BindingResult result, HttpSession session) {

		
		// Introduced a new variable "templateForm" to resolve PMD issue.
		 
		BrandingTemplateForm brandingTemplate = brandingTemplateForm;

		Boolean status = null;
		BrandingTemplateDTO empBrandTempDTO = new BrandingTemplateDTO();
		ModelAndView model = new ModelAndView();
		
		// Retrieve facilityId and userId from session.
		int facility_id = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		brandingTemplate.setFacilityId(facility_id);
		int user_id = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		brandingTemplate.setEmployerId(user_id);

		// Read the Silver/Gold customer details from database
		brandingTemplate = checkBrand(brandingTemplate, facility_id);
				
		// Modify the names of media files to save on File server

		brandingTemplate = modifyMediaName(brandingTemplate);

		// Validate the form data
		brandingTemplateValidation.validateSilver(brandingTemplate, result);

		if (result.hasErrors()) {
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			model.addObject("errorMessage", null);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;
		}

		if (!brandingTemplate.getIsSilverCustomer()) {
			String errorMessage = brandingTemplateValidation.validateNonSilver(
					brandingTemplate, result);

			if (!StringUtils.isEmpty(errorMessage)) {
				if (brandingTemplate.getListTestimony().isEmpty()) {
					brandingTemplate.getListTestimony()
							.add(new TestimonyForm());
				}
				if (brandingTemplate.getListAddImages().isEmpty()) {
					brandingTemplate.getListAddImages().add(new AddImageForm());
				}
				if (brandingTemplate.getListVideos().isEmpty()) {
					brandingTemplate.getListVideos().add(new VideoForm());
				}
				model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
				model.addObject("errorMessage", errorMessage);
				model.setViewName(STR_CREATEBRANDINGTEMPLATE);
				return model;
			}

		}

		// Upload the media files to File server

		status = uploadMedia(brandingTemplate);
		if (!status) {
			result.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
					empBrandFileError);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			status = null;
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			return model;
		}

		// Transform form data to DTO
		empBrandTempDTO = transformEmpoyerBrandTemplate
				.createEmpBrandTempDTO(brandingTemplate);
		// model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);

		// model.setViewName("manageBrandingTemplatePopup");

		// Call to service layer and DAO
		status = brandingTemplateService.createEmpBrandTemp(empBrandTempDTO);
		if (status) {
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			model.setViewName("employerDashboard");
			return model;
		} else {

			result.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
					empBrandTemplateError);
			status = null;
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;

		}

		// return model;

	}

	@ResponseBody
	@RequestMapping(value = "/createBrandingTemplate", method = RequestMethod.POST, params = "Preview")
	public ModelAndView previewEmpBrandTemp(
			@ModelAttribute(STR_BRANDINGTEMPLATEFORM) BrandingTemplateForm form,
			BindingResult result, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		/**
		 * Introduced a new variable "templateForm" to resolve PMD issue.
		 */
		BrandingTemplateForm brandingTemplateForm = form;
		Boolean status = null;
		ModelAndView model = new ModelAndView();

		// Retrieve facilityId from session.
		int facility_id = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		// Read the Silver/Gold customer details from database
		brandingTemplateForm = checkBrand(brandingTemplateForm, facility_id);

		brandingTemplateForm.setBrowsePath("create");

		// Modify the names of media files to save on File server
		brandingTemplateForm = modifyMediaName(brandingTemplateForm);

		// Validate the form data
		brandingTemplateValidation.validateSilver(brandingTemplateForm, result);

		if (result.hasErrors()) {
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
			model.addObject("errorMessage", null);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;
		}

		if (!brandingTemplateForm.getIsSilverCustomer()) {
			String errorMessage = brandingTemplateValidation.validateNonSilver(
					brandingTemplateForm, result);
			if (!StringUtils.isEmpty(errorMessage)) {
				if (brandingTemplateForm.getListTestimony().isEmpty()) {
					brandingTemplateForm.getListTestimony().add(
							new TestimonyForm());
				}
				if (brandingTemplateForm.getListAddImages().isEmpty()) {
					brandingTemplateForm.getListAddImages().add(
							new AddImageForm());
				}
				if (brandingTemplateForm.getListVideos().isEmpty()) {
					brandingTemplateForm.getListVideos().add(new VideoForm());
				}
				model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
				model.addObject("errorMessage", errorMessage);
				model.setViewName(STR_CREATEBRANDINGTEMPLATE);
				return model;
			}

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

		if(null==brandingTemplateForm.getColor() || brandingTemplateForm.getColor().isEmpty())
		{
			brandingTemplateForm.setColor(defaultColor);
		}
			model.setViewName(STR_BRANDTEMPLATEPREVIEW);


		return model;
	}
	
	/**
	 * The method is called to read the branding information from database.
	 * 
	 * @param form
	 * @param facility_id
	 * @return brandingTemplate
	 */
	public BrandingTemplateForm checkBrand(BrandingTemplateForm form, int facility_id)
	{
		int packageId = 1;
		BrandingTemplateForm brandingTemplateForm = form;
		
		// Getting the customer ID from Adm Facility table.
		int nsCustomerID = brandingTemplateService.getNSCustomerIDFromAdmFacility(facility_id);
		
		UserDTO userDTO = brandingTemplateService.getBrandingInformation(nsCustomerID);

		if (null != userDTO.getPackageName()) {
			if (userDTO.getPackageName().equalsIgnoreCase("Gold") ) 
			{
				brandingTemplateForm.setIsSilverCustomer(Boolean.FALSE);
				packageId = MMJBCommonConstants.INT_GOLD;
			} 
			else if(userDTO.getPackageName().equalsIgnoreCase("Platinum"))
			{
				brandingTemplateForm.setIsSilverCustomer(Boolean.FALSE);
				packageId = MMJBCommonConstants.INT_PLATINUM;
			}else 
			{
				brandingTemplateForm.setIsSilverCustomer(Boolean.TRUE);
			}
		}
		else
		{
			brandingTemplateForm.setIsSilverCustomer(Boolean.TRUE);
		}
		
		brandingTemplateForm.setPackageId(packageId);
		return brandingTemplateForm;
	}

	@RequestMapping(value = "/previewExisting", method = RequestMethod.GET)
	public ModelAndView previewExisting(
			BrandingTemplateForm form,
			@RequestParam("templateId") int templateId, HttpSession session) {

		BrandingTemplateForm brandingTemplateForm = form;
		// Retrieve facilityId from session.
		int facility_id = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		// Read the Silver/Gold customer details from database
		brandingTemplateForm = checkBrand(brandingTemplateForm, facility_id);
		
		BrandingTemplateDTO templateDTO = brandingTemplateService
				.editBrandingTemplate(templateId);

		ModelAndView model = new ModelAndView();
		templateDTO.setIsSilverCustomer(brandingTemplateForm
				.getIsSilverCustomer());
		brandingTemplateForm.setBrowsePath("manage");
		transformEmpoyerBrandTemplate.fromBrandDTOToBrandForm(
				brandingTemplateForm, templateDTO);

		if(null==brandingTemplateForm.getColor() || brandingTemplateForm.getColor().isEmpty())
		{
			brandingTemplateForm.setColor(defaultColor);
		}
		
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName(STR_BRANDTEMPLATEPREVIEW);
		return model;

	}

	@RequestMapping("/viewTestimonial")
	public ModelAndView enlargeTestimonial(@RequestParam("id") String testimonyId,
			HttpServletResponse response, HttpServletRequest request,
			BrandingTemplateForm brandingTemplateForm) {
		ModelAndView model = new ModelAndView();
		brandingTemplateForm.setTestimonyContainer(testimonyId);
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName("viewTestimony");
		return model;
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
		ServletOutputStream outputStream =null;
		try {
			// Retrieve the output stream
			outputStream = response.getOutputStream();
			// Write to the output stream
			outputStream.write(byteArray);
			// Flush the stream
			outputStream.flush();
			// Close the stream
			outputStream.close();

		} catch (Exception e) {
			LOGGER.error(e);
		}
		finally{
			try{
			outputStream.close();
			}catch (Exception e) {
				LOGGER.error(e);
			}
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
		Random random = new Random();

		logoOrigName = brandingTemplateForm.getLogoFileData()
				.getOriginalFilename();
		mainImageOrigName = brandingTemplateForm.getMainImageFileData()
				.getOriginalFilename();

		logoModifiedName = STR_TEMPLATE_ + random.nextInt(10000) + STR_UNDERSCORE
				+ logoOrigName;
		mainImageModifiedName = STR_TEMPLATE_ + random.nextInt(10000) + STR_UNDERSCORE
				+ mainImageOrigName;

		brandingTemplateForm.setLogoPath(baseDirectoryPathImageAndMedia
				+ logoModifiedName);
		brandingTemplateForm.setMainImagePath(baseDirectoryPathImageAndMedia
				+ mainImageModifiedName);

		// Only for Multi media section
		if (!brandingTemplateForm.getIsSilverCustomer()) {

			List<AddImageForm> listImages = new ArrayList<AddImageForm>();
			List<AddImageForm> listModImages = new ArrayList<AddImageForm>();
			listImages = brandingTemplateForm.getListAddImages();

			for (AddImageForm image : listImages) {

				if (!image.getAddImageFileData().getOriginalFilename()
						.isEmpty()) {

					image.setMediaPath(baseDirectoryPathImageAndMedia
							+ STR_TEMPLATE_ + random.nextInt(10000) + STR_UNDERSCORE
							+ image.getAddImageFileData().getOriginalFilename());
					listModImages.add(image);
				}
			}

			brandingTemplateForm.setListAddImages(listModImages);

			List<VideoForm> listVideos = new ArrayList<VideoForm>();
			List<VideoForm> listModVideos = new ArrayList<VideoForm>();
			listVideos = brandingTemplateForm.getListVideos();

			for (VideoForm video : listVideos) {
				if (!video.getVideoFileData().getOriginalFilename().isEmpty()) {
					video.setMediaPath(baseDirectoryPathImageAndMedia
							+ STR_TEMPLATE_ + random.nextInt(10000) + STR_UNDERSCORE
							+ video.getVideoFileData().getOriginalFilename());
					listModVideos.add(video);
				}
			}
			brandingTemplateForm.setListVideos(listModVideos);

			List<TestimonyForm> listTestimonies = new ArrayList<TestimonyForm>();
			List<TestimonyForm> listModTestimonies = new ArrayList<TestimonyForm>();
			listTestimonies = brandingTemplateForm.getListTestimony();

			for (TestimonyForm testimony : listTestimonies) {
				if (!testimony.getTestimony().isEmpty()) {

					listModTestimonies.add(testimony);
				}
			}
			brandingTemplateForm.setListTestimony(listModTestimonies);

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

				for (AddImageForm addImageForm : brandingTemplateForm
						.getListAddImages()) {
					addImageForm.getAddImageFileData().transferTo(
							new File(addImageForm.getMediaPath()));

				}

				for (VideoForm videoForm : brandingTemplateForm.getListVideos()) {
					videoForm.getVideoFileData().transferTo(
							new File(videoForm.getMediaPath()));

				}
			}
			status = Boolean.TRUE;

		} catch (IllegalStateException e) {

			status = Boolean.FALSE;

			LOGGER.error("ERROR OCCURED: " + e);

		} catch (IOException e) {

			status = Boolean.FALSE;
			LOGGER.error("ERROR OCCURED: " + e);

		} catch (Exception e) {

			status = Boolean.FALSE;
			LOGGER.error("ERROR OCCURED: " + e);

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

	// @RequestMapping(value = "/cancelBrandTemp", method = RequestMethod.GET)
	@RequestMapping(value = "/createBrandingTemplate", method = RequestMethod.POST, params = "Cancel")
	public ModelAndView cancelBrandTemp(Map model) {
		// return new
		// ModelAndView("redirect:/jobSeeker/jobSeekerDashBoard.html");
		return new ModelAndView(STR_EMPDASHBOARD);
	}

	/**
	 * The method is called to create a the createBrandingTemplate.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newBrandingTemplate", method = RequestMethod.GET)
	public ModelAndView newBrandTemp(Map modelMap, HttpSession session) {
		BrandingTemplateForm brandingTemplateForm = new BrandingTemplateForm();
		// modelMap.put(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		ModelAndView model = new ModelAndView();

		// Retrieve facilityId from session.
		int facility_id = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		// Read the Silver/Gold customer details from database
		brandingTemplateForm = checkBrand(brandingTemplateForm, facility_id);

		TestimonyForm testimonyForm = new TestimonyForm();
		ArrayList<TestimonyForm> nonEmptyTestimonyList = new ArrayList<TestimonyForm>();
		nonEmptyTestimonyList.add(testimonyForm);
		brandingTemplateForm.setListTestimony(nonEmptyTestimonyList);

		AddImageForm addImageForm = new AddImageForm();
		ArrayList<AddImageForm> nonEmptyImageList = new ArrayList<AddImageForm>();
		nonEmptyImageList.add(addImageForm);
		brandingTemplateForm.setListAddImages(nonEmptyImageList);

		VideoForm videoForm = new VideoForm();
		ArrayList<VideoForm> nonEmptyVideoList = new ArrayList<VideoForm>();
		nonEmptyVideoList.add(videoForm);
		brandingTemplateForm.setListVideos(nonEmptyVideoList);

		brandingTemplateForm.setImageSizeLimit(imageSizeLimit.substring(0, imageSizeLimit.length()-3));
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName(STR_CREATEBRANDINGTEMPLATE);
		// Dummy list created to have a non zero List

		return model;
	}

	/**
	 * The method is called to display the createBrandingTemplate with data.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/displayTemplate", method = RequestMethod.GET)
	public ModelAndView displayTemplate(
			Map modelMap,
			@ModelAttribute(STR_BRANDINGTEMPLATEFORM) BrandingTemplateForm form,
			BindingResult result, HttpSession session,
			@RequestParam("id") String browsePath) {
		BrandingTemplateForm brandingTemplateForm = form;

		ModelAndView model = new ModelAndView();
		TestimonyForm testimonyForm = new TestimonyForm();
		ArrayList<TestimonyForm> nonEmptyList = new ArrayList<TestimonyForm>();
		nonEmptyList.add(testimonyForm);
		brandingTemplateForm.setListTestimony(nonEmptyList);
		
//		if(null != form.getMainImagePath())
//		{
////			TODO
//			getOriginalName(form.getMainImagePath());
//		}
		
		
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		if (brandingTemplateForm.getBrowsePath().equalsIgnoreCase("manage")) {
			model.setViewName(STR_EMPDASHBOARD);

		} else {
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
		}

		
		
		return model;
	}

	/**
	 * This method is called to add Testimonies
	 * 
	 * @param session
	 * @param brandingTemplateForm
	 * @return model
	 */
	@RequestMapping(value = "/addTestimonies", method = RequestMethod.POST)
	// @ResponseBody
	public ModelAndView addTestimonies(HttpSession session,
			BrandingTemplateForm brandingTemplateForm) {

		TestimonyForm testimony = new TestimonyForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addTestimonies");

		model.addObject("testimonyPosId", brandingTemplateForm
				.getListTestimony().size());
		
		boolean isTestimonialEmpty = (null == brandingTemplateForm.getListTestimony()?true:false);
		
		if (isTestimonialEmpty) {
			List<TestimonyForm> listTestimonies = new ArrayList<TestimonyForm>();
			listTestimonies.add(testimony);
			brandingTemplateForm.setListTestimony(listTestimonies);
		} else {

			brandingTemplateForm.getListTestimony().add(testimony);

		}
		return model;
	}

	/**
	 * This method is called to add Additional Images
	 * 
	 * @param session
	 * @param brandingTemplateForm
	 * @return model
	 */
	@RequestMapping(value = "/addImages", method = RequestMethod.POST)
	// @ResponseBody
	public ModelAndView addImages(HttpSession session,
			BrandingTemplateForm brandingTemplateForm) {

		AddImageForm image = new AddImageForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addImages");

		model.addObject("imagePosId", brandingTemplateForm.getListAddImages()
				.size());
		if (null == brandingTemplateForm.getListAddImages()) {
			List<AddImageForm> listImages = new ArrayList<AddImageForm>();
			listImages.add(image);
			brandingTemplateForm.setListAddImages(listImages);
		} else {

			brandingTemplateForm.getListAddImages().add(image);

		}

		return model;
	}

	/**
	 * This method is called to add Videos
	 * 
	 * @param session
	 * @param brandingTemplateForm
	 * @return model
	 */
	@RequestMapping(value = "/addVideos", method = RequestMethod.POST)
	// @ResponseBody
	public ModelAndView addVideos(HttpSession session,
			BrandingTemplateForm brandingTemplateForm) {

		VideoForm video = new VideoForm();
		ModelAndView model = new ModelAndView();
		model.setViewName("addVideos");

		model.addObject("videoPosId", brandingTemplateForm.getListVideos()
				.size());
		if (null == brandingTemplateForm.getListVideos()) {
			List<VideoForm> listVideos = new ArrayList<VideoForm>();
			listVideos.add(video);
			brandingTemplateForm.setListVideos(listVideos);
		} else {

			brandingTemplateForm.getListVideos().add(video);

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
				.getBrandingTemplate((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
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
	@RequestMapping(value = "/editTemplate", method = RequestMethod.GET)
	public ModelAndView editEmpBrandTemp(
			BrandingTemplateForm form,
			@RequestParam("templateId") int templateId, HttpSession session) {
		BrandingTemplateForm brandingTemplateForm = form;
		// Retrieve facilityId from session.
		int facility_id = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		// Read the Silver/Gold customer details from database
		brandingTemplateForm = checkBrand(brandingTemplateForm, facility_id);
		
		BrandingTemplateDTO templateDTO = brandingTemplateService
				.editBrandingTemplate(templateId);

		templateDTO.setIsSilverCustomer(brandingTemplateForm.getIsSilverCustomer());
		ModelAndView model = new ModelAndView();
		transformEmpoyerBrandTemplate.fromBrandDTOToBrandForm(
				brandingTemplateForm, templateDTO);
		//TODO write the below logic in a separate common method
		if(!brandingTemplateForm.getIsSilverCustomer())
		{
		if (null == brandingTemplateForm.getListTestimony() || brandingTemplateForm.getListTestimony().isEmpty()) {
			List<TestimonyForm> listEmptyTestimonies = new ArrayList<TestimonyForm>();
			listEmptyTestimonies.add(new TestimonyForm());
			brandingTemplateForm.setListTestimony(listEmptyTestimonies);
		}
		if (null == brandingTemplateForm.getListAddImages() || brandingTemplateForm.getListAddImages().isEmpty()) {
			
			List<AddImageForm> listEmptyAddImages = new ArrayList<AddImageForm>();
			listEmptyAddImages.add(new AddImageForm());
			brandingTemplateForm.setListAddImages(listEmptyAddImages);
		}
		if (null == brandingTemplateForm.getListVideos() || brandingTemplateForm.getListVideos().isEmpty()) {
			List<VideoForm> listEmptyVideos = new ArrayList<VideoForm>();
			listEmptyVideos.add(new VideoForm());
			brandingTemplateForm.setListVideos(listEmptyVideos);
		}
		}
		
		
//		if(null!=brandingTemplateForm.getMainImagePath())
//		{
//			TODO
//		}
		
		
		brandingTemplateForm.setImageSizeLimit(imageSizeLimit.substring(0, imageSizeLimit.length()-3));
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		// model.setViewName("editBrandingTemplate");
		model.setViewName(STR_CREATEBRANDINGTEMPLATE);
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
		 * Introduced a new variable "templateForm" to resolve PMD issue.
		 */
		BrandingTemplateForm brandingTemplateForm = form;
		Boolean status = null;
		BrandingTemplateDTO templateDTO = new BrandingTemplateDTO();
		ModelAndView model = new ModelAndView();

		// Retrieve facilityId from session.
		int facility_id = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		// Read the Silver/Gold customer details from database
		brandingTemplateForm = checkBrand(brandingTemplateForm, facility_id);


		// Modify the names of media files to save on File server
		brandingTemplateForm = modifyMediaName(brandingTemplateForm);

		// Validate the form data

		brandingTemplateValidation.validateSilver(brandingTemplateForm, result);
		// brandingTemplateValidation.validate(templateForm, result);

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
			model.setViewName(STR_EMPDASHBOARD);
		}
		model.setViewName(STR_EMPDASHBOARD);
		return model;
	}

	/**
	 * The method is called to delete the job posting Branding Template.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer/deleteBrandingTemplate", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject deleteEmpBrandTemp(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("templateId") int templateId) {
		int deleteUserId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);

		boolean status = brandingTemplateService.deleteBrandingTemplate(
				templateId, deleteUserId);
		JSONObject statusJson = new JSONObject();
		if (status) {
			statusJson.put("success", "template deleted successfully");
			return statusJson;
		} else {
			statusJson.put("failed", "failed");
			return statusJson;
		}
	}

	/**
	 * This method retrieves the original name of file present in file server.
	 * 
	 * @param filePath
	 * @return OriginalFileName
	 */
	public String getOriginalName(String filePath) {
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		String fileName;
		String ModifiedFileName1;
		String ModifiedFileName2;
		String OriginalFileName;

		fileName = filePath;

		index1 = fileName.lastIndexOf("/");
		if (index1 == -1) {
			index1 = fileName.lastIndexOf("\\");
		}

		ModifiedFileName1 = fileName.substring(index1 + 1);

		index2 = ModifiedFileName1.indexOf(STR_UNDERSCORE);
		ModifiedFileName2 = ModifiedFileName1.substring(index2 + 1);

		index3 = ModifiedFileName2.indexOf(STR_UNDERSCORE);
		OriginalFileName = ModifiedFileName2.substring(index3 + 1);

		return OriginalFileName;
	}
	
}
