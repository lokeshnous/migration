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
import org.springframework.validation.Errors;
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
import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
import com.advanceweb.afc.jb.common.util.AVScannerHelper;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

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
public class BrandingTemplateController extends AbstractController {

	private static final Logger LOGGER = Logger
			.getLogger(BrandingTemplateController.class);

	@Autowired
	private BrandingTemplateService brandingTemplateService;

	@Autowired
	private TransformEmployerBrandTemplate transformEmpoyerBrandTemplate;

	@Autowired
	private BrandingTemplateValidation brandingTemplateValidation;

	@Autowired
	private AdService adService;

	private @Value("${baseDirectoryPathImageAndMedia}")
	String baseDirectoryPathImageAndMedia;

	private @Value("${appMediaPath}")
	String appMediaPath;

	private @Value("${mediaPath}")
	String mediaPath;

	private @Value("${imageSizeLimit}")
	String imageSizeLimit;

	private @Value("${videoSizeLimit}")
	String videoSizeLimit;

	private @Value("${empBrandFileError}")
	String empBrandFileError;

	private @Value("${empBrandTemplateError}")
	String empBrandTemplateError;

	private @Value("${empBrandTemplatePurchase}")
	String empBrandTemplatePurchase;

	private @Value("${defaultColor}")
	String defaultColor;

	private @Value("${empBrandTemplateDelete}")
	String empBrandTemplateDelete;

	private @Value("${empBrandTemplateExceed}")
	String empBrandTemplateExceed;
	private @Value("${virus.found.file.msg}")
	String virusFoundMsg;
	private static final String STR_BRANDINGTEMPLATEFORM = "brandingTemplateForm";
	private static final String STR_CREATEBRANDINGTEMPLATE = "createBrandingTemplate";
	private static final String STR_LOGOFILEDATA = "logoFileData";
	private static final String STR_NOTEMPTY = "NotEmpty";
	private static final String STR_TEMPLATE_ = "Template_";
	private static final String STR_BRANDTEMPLATEPREVIEW = "brandTemplatePreview";
	private static final String STR_EMPDASHBOARD = "redirect:/employer/employerDashBoard.html";
	private static final String STR_UNDERSCORE = "_";
	private static final String STR_ERRORMESSAGE = "errorMessage";
	private static final String STR_TEMPLATEID = "templateId";

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
			BindingResult result, HttpSession session,
			HttpServletRequest request) {

		// Introduced a new variable "templateForm" to resolve PMD issue.
		BrandingTemplateForm brandingTemplate = brandingTemplateForm;

		Boolean status = null;
		BrandingTemplateDTO empBrandTempDTO = new BrandingTemplateDTO();
		ModelAndView model = new ModelAndView();

		// Retrieve facilityId and userId from session.
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		// If logged in user is job owner then get his parent id
		int parentFacilityId = brandingTemplateService.getParentId(facilityId);
		brandingTemplate.setFacilityId(parentFacilityId);
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int parentUserId = brandingTemplateService.getParentUserId(userId,
				parentFacilityId);
		brandingTemplate.setEmployerId(parentUserId);

		populateAds(session, request, model);

		// Check if the user has exceeded the branding template limit
		status = brandingTemplateService.checkTemplateLimit(parentFacilityId);
		if (!status) {
			result.rejectValue("templateName", STR_NOTEMPTY,
					empBrandTemplateExceed);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			return model;
		}

		// Read the Silver/Gold customer details from database
		brandingTemplate = checkBrand(brandingTemplate, parentFacilityId);

		// Modify the names of media files to save on File server
		brandingTemplate = modifyMediaName(brandingTemplate);

		// Validate the form data
		brandingTemplateValidation.validateSilver(brandingTemplate, result);

		if (result.hasErrors()) {
			if (!brandingTemplateForm.getIsSilverCustomer()) {
				clearChosendata(brandingTemplate);
				verifyMultimediaContent(brandingTemplateForm);
			}
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			model.addObject(STR_ERRORMESSAGE, null);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;
		}

		if (!brandingTemplate.getIsSilverCustomer()
				&& !validateNonSilverCust(brandingTemplate, result, model)) {
			return model;
		}

		// Upload the media files to File server
		status = uploadMedia(brandingTemplate, result);
		if (result.hasErrors()) {
			/*
			 * result.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
			 * empBrandFileError);
			 */
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			return model;
		}

		// Transform form data to DTO
		empBrandTempDTO = transformEmpoyerBrandTemplate
				.createEmpBrandTempDTO(brandingTemplate);

		// Call to service layer and DAO
		status = brandingTemplateService.createEmpBrandTemp(empBrandTempDTO);
		if (status) {
			brandingTemplate.setSaveSuccess("true");
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;
		} else {

			result.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
					empBrandTemplateError);
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;

		}
	}

	/**
	 * The method helps to clear the chosen multimedia section files when errors
	 * occurred
	 * 
	 * @param templateForm
	 * 
	 */
	private void clearChosendata(BrandingTemplateForm templateForm) {
		templateForm.setListAddImages(new ArrayList<AddImageForm>());
		templateForm.setListVideos(new ArrayList<VideoForm>());
	}

	public boolean validateNonSilverCust(BrandingTemplateForm brandingTemplate,
			BindingResult result, ModelAndView model) {
		String errorMessage = brandingTemplateValidation.validateNonSilver(
				brandingTemplate, result);

		if (!StringUtils.isEmpty(errorMessage)) {
			if (brandingTemplate.getListTestimony().isEmpty()) {
				brandingTemplate.getListTestimony().add(new TestimonyForm());
			}
			if (brandingTemplate.getListAddImages().isEmpty()) {
				brandingTemplate.getListAddImages().add(new AddImageForm());
			}
			if (brandingTemplate.getListVideos().isEmpty()) {
				brandingTemplate.getListVideos().add(new VideoForm());
			}
			verifyMultimediaContent(brandingTemplate);
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplate);
			model.addObject(STR_ERRORMESSAGE, errorMessage);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return false;
		} else {
			return true;
		}

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
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		brandingTemplateForm.setFacilityId(facilityId);

		// Read the Silver/Gold customer details from database
		brandingTemplateForm = checkBrand(brandingTemplateForm, facilityId);

		brandingTemplateForm.setBrowsePath("create");

		// Modify the names of media files to save on File server
		brandingTemplateForm = modifyMediaName(brandingTemplateForm);

		// Validate the form data
		brandingTemplateValidation.validateSilver(brandingTemplateForm, result);

		if (result.hasErrors()) {
			if (!brandingTemplateForm.getIsSilverCustomer()) {
				clearChosendata(brandingTemplateForm);
				verifyMultimediaContent(brandingTemplateForm);
			}
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
			model.addObject(STR_ERRORMESSAGE, null);
			populateAds(session, request, model);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;
		}

		if (!brandingTemplateForm.getIsSilverCustomer()
				&& !validateNonSilverCust(brandingTemplateForm, result, model)) {
			return model;
		}

		// Upload the media files to File server
		status = uploadMedia(brandingTemplateForm, result);
		if (result.hasErrors()) {
			/*
			 * result.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
			 * "An error occured while saving the file");
			 */
			populateAds(session, request, model);
			model.setViewName(STR_CREATEBRANDINGTEMPLATE);
			return model;
		}

		if (null == brandingTemplateForm.getColor()
				|| brandingTemplateForm.getColor().isEmpty()) {
			brandingTemplateForm.setColor(defaultColor);
		}
		if (!brandingTemplateForm.getIsSilverCustomer()) {
			setVideoURL(brandingTemplateForm, request);
		}
		model.setViewName(STR_BRANDTEMPLATEPREVIEW);

		return model;
	}

	/**
	 * This method converts the video file path to playable video URL
	 * 
	 * @param brandingTemplateForm
	 * @param request
	 */
	public void setVideoURL(BrandingTemplateForm brandingTemplateForm,
			HttpServletRequest request) {
		List<VideoForm> listVideoForm = brandingTemplateForm.getListVideos();
		List<VideoForm> modListVideoForm = new ArrayList<VideoForm>();
		StringBuffer videoURL = new StringBuffer();

		videoURL.append(request.getRequestURL().toString()
				.replace(request.getRequestURI(), MMJBCommonConstants.EMPTY));
		videoURL.append(mediaPath);

		if (null != listVideoForm && !listVideoForm.isEmpty()) {
			for (VideoForm videoForm : listVideoForm) {
				int index = 0;
				String path = videoForm.getMediaPath();
				index = videoForm.getMediaPath().lastIndexOf('/');
				if (index == -1) {
					index = videoForm.getMediaPath().lastIndexOf('\\');
				}
				videoForm.setMediaPath(videoURL.append(
						path.substring(index + 1)).toString());
				modListVideoForm.add(videoForm);
			}
			brandingTemplateForm.setListVideos(modListVideoForm);
		}
	}

	/**
	 * The method is called to read the branding information from database.
	 * 
	 * @param form
	 * @param facility_id
	 * @return brandingTemplate
	 */
	public BrandingTemplateForm checkBrand(BrandingTemplateForm form,
			int facility_id) {
		int packageId = MMJBCommonConstants.INT_SILVER;
		BrandingTemplateForm brandingTemplateForm = form;
		List<String> purchasedPackages = null;
		// Getting the customer ID from Adm Facility table.
		int nsCustomerID = brandingTemplateService
				.getNSCustomerIDFromAdmFacility(facility_id);

		purchasedPackages = brandingTemplateService
				.getBrandingInformation(nsCustomerID);
		if (null != purchasedPackages && !purchasedPackages.isEmpty()) {
			if (purchasedPackages.contains(MMJBCommonConstants.PLATINUM_90)
					|| purchasedPackages
							.contains(MMJBCommonConstants.PLATINUM_180)
					|| purchasedPackages
							.contains(MMJBCommonConstants.PLATINUM_365)) {
				brandingTemplateForm.setIsSilverCustomer(Boolean.FALSE);
				packageId = MMJBCommonConstants.INT_PLATINUM;
			} else if (purchasedPackages.contains(MMJBCommonConstants.GOLD_90)
					|| purchasedPackages.contains(MMJBCommonConstants.GOLD_180)
					|| purchasedPackages.contains(MMJBCommonConstants.GOLD_365)) {
				brandingTemplateForm.setIsSilverCustomer(Boolean.FALSE);
				packageId = MMJBCommonConstants.INT_GOLD;
			} else {
				brandingTemplateForm.setIsSilverCustomer(Boolean.TRUE);
			}
		} else {
			brandingTemplateForm.setIsSilverCustomer(Boolean.TRUE);
		}

		brandingTemplateForm.setPackageId(packageId);

		return brandingTemplateForm;
	}

	@RequestMapping(value = "/previewExisting", method = RequestMethod.GET)
	public ModelAndView previewExisting(BrandingTemplateForm form,
			@RequestParam(STR_TEMPLATEID) int templateId, HttpSession session,
			HttpServletRequest request) {

		BrandingTemplateForm brandingTemplateForm = form;
		// Retrieve facilityId from session.
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		brandingTemplateForm.setFacilityId(facilityId);

		// Read the Silver/Gold customer details from database
		brandingTemplateForm = checkBrand(brandingTemplateForm, facilityId);

		BrandingTemplateDTO templateDTO = brandingTemplateService
				.editBrandingTemplate(templateId);

		ModelAndView model = new ModelAndView();
		templateDTO.setIsSilverCustomer(brandingTemplateForm
				.getIsSilverCustomer());
		brandingTemplateForm.setBrowsePath("manage");
		transformEmpoyerBrandTemplate.fromBrandDTOToBrandForm(
				brandingTemplateForm, templateDTO);

		if (null == brandingTemplateForm.getColor()
				|| brandingTemplateForm.getColor().isEmpty()) {
			brandingTemplateForm.setColor(defaultColor);
		}

		if (!brandingTemplateForm.getIsSilverCustomer()) {
			setVideoURL(brandingTemplateForm, request);
		}
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName(STR_BRANDTEMPLATEPREVIEW);
		return model;

	}

	@RequestMapping("/viewTestimonial")
	public ModelAndView enlargeTestimonial(
			@RequestParam("id") String testimonyId,
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
		ServletOutputStream outputStream = null;
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
		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
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

		if (brandingTemplateForm.getLogoFileData().getSize() > 0) {
			logoOrigName = brandingTemplateForm.getLogoFileData()
					.getOriginalFilename();
			logoModifiedName = STR_TEMPLATE_ + random.nextInt(10000)
					+ STR_UNDERSCORE + logoOrigName;

			brandingTemplateForm.setLogoPath(baseDirectoryPathImageAndMedia
					+ logoModifiedName);
		}

		if (brandingTemplateForm.getMainImageFileData().getSize() > 0) {
			mainImageOrigName = brandingTemplateForm.getMainImageFileData()
					.getOriginalFilename();
			mainImageModifiedName = STR_TEMPLATE_ + random.nextInt(10000)
					+ STR_UNDERSCORE + mainImageOrigName;
			brandingTemplateForm
					.setMainImagePath(baseDirectoryPathImageAndMedia
							+ mainImageModifiedName);
		}

		// Only for Multi media section
		if (!brandingTemplateForm.getIsSilverCustomer()) {
			modifyMultiMediaName(brandingTemplateForm, random);
		}

		return brandingTemplateForm;
	}

	/**
	 * @param brandingTemplateForm
	 * @param random
	 */
	private void modifyMultiMediaName(
			BrandingTemplateForm brandingTemplateForm, Random random) {
		List<AddImageForm> listImages = new ArrayList<AddImageForm>();
		List<AddImageForm> listModImages = new ArrayList<AddImageForm>();
		listImages = brandingTemplateForm.getListAddImages();

		for (AddImageForm image : listImages) {

			if (null!= image.getAddImageFileData() && image.getAddImageFileData().getSize() > 0) {
				image.setMediaPath(baseDirectoryPathImageAndMedia
						+ STR_TEMPLATE_ + random.nextInt(10000)
						+ STR_UNDERSCORE
						+ image.getAddImageFileData().getOriginalFilename());
			}
			if (null != image.getMediaPath()) {
				listModImages.add(image);
			}
		}

		brandingTemplateForm.setListAddImages(listModImages);

		List<VideoForm> listVideos = new ArrayList<VideoForm>();
		List<VideoForm> listModVideos = new ArrayList<VideoForm>();
		listVideos = brandingTemplateForm.getListVideos();

		for (VideoForm video : listVideos) {
			if (null != video.getVideoFileData() && video.getVideoFileData().getSize() > 0) {
				video.setMediaPath(STR_TEMPLATE_ + random.nextInt(10000)
						+ STR_UNDERSCORE
						+ video.getVideoFileData().getOriginalFilename());

			}
			if (null != video.getMediaPath()) {
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

	/**
	 * The method is is used to upload the media file to file server.
	 * 
	 * @param brandingTemplateForm
	 * @return Boolean
	 */
	public Boolean uploadMedia(BrandingTemplateForm brandingTemplateForm,
			Errors error) {
		Boolean status = null;
		File logoFileDest = new File(brandingTemplateForm.getLogoPath());
		File mainImageFileDest = new File(
				brandingTemplateForm.getMainImagePath());
		try {

			if (brandingTemplateForm.getLogoFileData().getSize() > 0) {
				MultipartFile logoFile = brandingTemplateForm.getLogoFileData();
				logoFile.transferTo(logoFileDest);
				// Antivirus Scan
				checkFileForVirus(logoFileDest, error);
			}

			if (brandingTemplateForm.getMainImageFileData().getSize() > 0) {
				MultipartFile mainImageFile = brandingTemplateForm
						.getMainImageFileData();
				mainImageFile.transferTo(mainImageFileDest);
				// Antivirus Scan
				checkFileForVirus(mainImageFileDest, error);

			}
			if (error.hasErrors()) {
				status = Boolean.FALSE;
				error.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY,
						empBrandFileError);
			}
			if (!brandingTemplateForm.getIsSilverCustomer()) {

				uploadMultiMedia(brandingTemplateForm);
			}
			status = Boolean.TRUE;

		} catch (Exception e) {
			status = Boolean.FALSE;
			error.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY, empBrandFileError);
			LOGGER.error("ERROR OCCURED: " + e);
		}

		return status;

	}

	/**
	 * @param brandingTemplateForm
	 * @throws IOException
	 */
	private void uploadMultiMedia(BrandingTemplateForm brandingTemplateForm)
			throws IOException {
		for (AddImageForm addImageForm : brandingTemplateForm
				.getListAddImages()) {
			if (addImageForm.getAddImageFileData().getSize() > 0) {
				addImageForm.getAddImageFileData().transferTo(
						new File(addImageForm.getMediaPath()));
			}
		}

		for (VideoForm videoForm : brandingTemplateForm.getListVideos()) {
			if (videoForm.getVideoFileData().getSize() > 0) {
				if (null != System.getProperty("catalina.home")) {
					videoForm.getVideoFileData().transferTo(
							new File(System.getProperty("catalina.home")
									+ appMediaPath + videoForm.getMediaPath()));
				} else {
					LOGGER.error("Could not upload the video file, as catalina home was not set.");
				}
			}
		}
	}

	/**
	 * The method is called to close the manageBrandingTemplatePopup.
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/createBrandingTemplate", method = RequestMethod.POST, params = "Cancel")
	public ModelAndView cancelBrandTemp(Map model) {
		return new ModelAndView(STR_EMPDASHBOARD);
	}

	/**
	 * The method is called to create a the createBrandingTemplate.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newBrandingTemplate", method = RequestMethod.GET)
	public ModelAndView newBrandTemp(Map modelMap, HttpSession session,
			HttpServletRequest request) {
		BrandingTemplateForm brandingTemplateForm = new BrandingTemplateForm();
		ModelAndView model = new ModelAndView();

		// Retrieve facilityId from session.
		int facility_id = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
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

		brandingTemplateForm.setImageSizeLimit(imageSizeLimit);
		brandingTemplateForm.setVideoSizeLimit(videoSizeLimit);

		populateAds(session, request, model);
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
			HttpServletRequest request, @RequestParam("id") String browsePath) {
		BrandingTemplateForm brandingTemplateForm = form;

		ModelAndView model = new ModelAndView();

		if (!brandingTemplateForm.getIsSilverCustomer()) {
			brandingTemplateForm = verifyMultimediaContent(brandingTemplateForm);
		}

		if (null != brandingTemplateForm.getMainImagePath()) {
			brandingTemplateForm
					.setChosenMainImage(getOriginalName(brandingTemplateForm
							.getMainImagePath()));
		}

		if (null != brandingTemplateForm.getLogoPath()) {
			brandingTemplateForm
					.setChosenLogo(getOriginalName(brandingTemplateForm
							.getLogoPath()));
		}
		if (brandingTemplateForm.getBrowsePath().equalsIgnoreCase("manage")) {
			session.setAttribute("brandingTemplate", true);
			model.setViewName(STR_EMPDASHBOARD);

		} else {
			populateAds(session, request, model);
			model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
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

		boolean isTestimonialEmpty = (null == brandingTemplateForm
				.getListTestimony() ? true : false);
		testimony.setItemId(brandingTemplateForm.getListTestimony().size());
		if (isTestimonialEmpty) {
			List<TestimonyForm> listTestimonies = new ArrayList<TestimonyForm>();
			listTestimonies.add(testimony);
			brandingTemplateForm.setListTestimony(listTestimonies);
		} else {

			brandingTemplateForm.getListTestimony().add(testimony);

		}
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
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
		image.setItemId(brandingTemplateForm.getListAddImages().size());
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
		video.setItemId(brandingTemplateForm.getListVideos().size());
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
	 * The method is called to fetch the job posting Branding Templates
	 * 
	 * @param Map
	 *            <String, List<BrandingTemplateDTO>>
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/employer/manageBrandingTemplate", method = RequestMethod.GET)
	public ModelAndView fetchEmpBrandTemp(
			Map<String, List<BrandingTemplateDTO>> model, HttpSession session) {
		boolean isBrandPurchased = false;
		int parentUserId = 0;
		// Retrieve facilityId from session.
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);

		// If logged in user is job owner then get his parent id
		int parentFacilityId = brandingTemplateService.getParentId(facilityId);
		parentUserId = brandingTemplateService.getParentUserId(userId,
				parentFacilityId);
		isBrandPurchased = brandingTemplateService
				.getBrandPurchaseInfo(parentFacilityId);
		ModelAndView modelView = new ModelAndView();
		List<BrandingTemplateDTO> brandTemplateList = brandingTemplateService
				.getBrandingTemplate(parentUserId);
		model.put("templatesList", brandTemplateList);
		modelView.addObject("isBrandPurchased", isBrandPurchased);
		modelView.addObject(STR_ERRORMESSAGE, empBrandTemplatePurchase);
		modelView.setViewName("manageBrandingTemplatePopup");
		return modelView;
	}

	/**
	 * The method is called to edit the job posting Branding Template.
	 * 
	 * @param templateId
	 * @param BrandingTemplateForm
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editTemplate", method = RequestMethod.GET)
	public ModelAndView editEmpBrandTemp(BrandingTemplateForm form,
			@RequestParam(STR_TEMPLATEID) int templateId, HttpSession session,
			HttpServletRequest request) {
		BrandingTemplateForm brandingTemplateForm = form;
		// Retrieve facilityId from session.
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);

		// If logged in user is job owner then get his parent id
		int parentFacilityId = brandingTemplateService.getParentId(facilityId);

		// Read the Silver/Gold customer details from database
		brandingTemplateForm = checkBrand(brandingTemplateForm,
				parentFacilityId);

		BrandingTemplateDTO templateDTO = brandingTemplateService
				.editBrandingTemplate(templateId);

		templateDTO.setIsSilverCustomer(brandingTemplateForm
				.getIsSilverCustomer());
		ModelAndView model = new ModelAndView();
		transformEmpoyerBrandTemplate.fromBrandDTOToBrandForm(
				brandingTemplateForm, templateDTO);

		if (!brandingTemplateForm.getIsSilverCustomer()) {
			brandingTemplateForm = verifyMultimediaContent(brandingTemplateForm);
		}

		if (null != brandingTemplateForm.getMainImagePath()) {
			brandingTemplateForm
					.setChosenMainImage(getOriginalName(brandingTemplateForm
							.getMainImagePath()));
		}

		if (null != brandingTemplateForm.getLogoPath()) {
			brandingTemplateForm
					.setChosenLogo(getOriginalName(brandingTemplateForm
							.getLogoPath()));
		}

		brandingTemplateForm.setImageSizeLimit(imageSizeLimit);
		brandingTemplateForm.setVideoSizeLimit(videoSizeLimit);
		brandingTemplateForm.setEditMode(true);

		populateAds(session, request, model);
		brandingTemplateForm.setSaveSuccess("false");
		model.addObject(STR_BRANDINGTEMPLATEFORM, brandingTemplateForm);
		model.setViewName(STR_CREATEBRANDINGTEMPLATE);
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
			@RequestParam(STR_TEMPLATEID) int templateId) {
		int deleteUserId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		// If logged in user is job owner then get his parent id
		int parentFacilityId = brandingTemplateService.getParentId(facilityId);

		int parentUserId = brandingTemplateService.getParentUserId(
				deleteUserId, parentFacilityId);

		boolean status = brandingTemplateService.deleteBrandingTemplate(
				templateId, parentUserId);
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
	 * This method checks if any active job is using the template.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer/checkJobUsage", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject checkJobUsage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam(STR_TEMPLATEID) int templateId) {

		boolean status = brandingTemplateService.checkTemplateUsage(templateId);
		JSONObject statusJson = new JSONObject();
		if (status) {
			statusJson.put("present", empBrandTemplateDelete);
			return statusJson;
		} else {
			statusJson.put("absent", "template not used in active job");
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
		String OriginalFileName = "";

		if (null != filePath) {
			fileName = filePath;

			index1 = fileName.lastIndexOf('/');
			if (index1 == -1) {
				index1 = fileName.lastIndexOf('\\');
			}

			ModifiedFileName1 = fileName.substring(index1 + 1);

			index2 = ModifiedFileName1.indexOf(STR_UNDERSCORE);
			ModifiedFileName2 = ModifiedFileName1.substring(index2 + 1);

			index3 = ModifiedFileName2.indexOf(STR_UNDERSCORE);
			OriginalFileName = ModifiedFileName2.substring(index3 + 1);
		}
		return OriginalFileName;
	}

	/**
	 * This method verifies the MultiMedia content
	 * 
	 * @param form
	 * @return
	 */
	public BrandingTemplateForm verifyMultimediaContent(
			BrandingTemplateForm form) {
		BrandingTemplateForm brandingTemplateForm = form;
		if (null == brandingTemplateForm.getListTestimony()
				|| brandingTemplateForm.getListTestimony().isEmpty()) {
			List<TestimonyForm> listEmptyTestimonies = new ArrayList<TestimonyForm>();
			listEmptyTestimonies.add(new TestimonyForm());
			brandingTemplateForm.setListTestimony(listEmptyTestimonies);
		}
		if (null == brandingTemplateForm.getListAddImages()
				|| brandingTemplateForm.getListAddImages().isEmpty()) {

			List<AddImageForm> listEmptyAddImages = new ArrayList<AddImageForm>();
			listEmptyAddImages.add(new AddImageForm());
			brandingTemplateForm.setListAddImages(listEmptyAddImages);
		}
		if (null == brandingTemplateForm.getListVideos()
				|| brandingTemplateForm.getListVideos().isEmpty()) {
			List<VideoForm> listEmptyVideos = new ArrayList<VideoForm>();
			listEmptyVideos.add(new VideoForm());
			brandingTemplateForm.setListVideos(listEmptyVideos);
		}

		verifyChosenMediaContent(brandingTemplateForm);

		return brandingTemplateForm;
	}

	/**
	 * This method is used to verify the MultiMedia file content chosen by user
	 * 
	 * @param brandingTemplateForm
	 */
	private void verifyChosenMediaContent(
			BrandingTemplateForm brandingTemplateForm) {
		if (null != brandingTemplateForm.getListAddImages()) {
			List<AddImageForm> updatedAddImagesList = new ArrayList<AddImageForm>();
			for (AddImageForm image : brandingTemplateForm.getListAddImages()) {
				if (null != image.getMediaPath()) {
					image.setChosenAddImage(getOriginalName(image
							.getMediaPath()));
				}
				updatedAddImagesList.add(image);
			}

			brandingTemplateForm.setListAddImages(updatedAddImagesList);
		}

		if (null != brandingTemplateForm.getListVideos()) {
			List<VideoForm> updatedVideoList = new ArrayList<VideoForm>();
			for (VideoForm video : brandingTemplateForm.getListVideos()) {
				if (null != video.getMediaPath()) {
					int index = 0;
					String path = video.getMediaPath();
					index = video.getMediaPath().lastIndexOf('/');
					if (index == -1) {
						index = video.getMediaPath().lastIndexOf('\\');
					}
					video.setMediaPath(path.substring(index + 1));
					video.setChosenVideo(getOriginalName(video.getMediaPath()));
				}
				updatedVideoList.add(video);
			}

			brandingTemplateForm.setListVideos(updatedVideoList);
		}
	}

	/**
	 * The method helps to populate the ads for the page
	 * 
	 * @param session
	 * @param request
	 * @param model
	 */
	private void populateAds(HttpSession session, HttpServletRequest request,
			ModelAndView model) {
		// Add the Ads
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.EMPLOYER_BTEMPLATE);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
		} catch (Exception e) {
			LOGGER.error(
					"Error occurred while getting the html content for Ads", e);
		}
	}

	/**
	 * Method to scan file for anti-virus
	 * 
	 * @param virusChkFiledest
	 * @param error
	 */
	private void checkFileForVirus(File virusChkFiledest, Errors error) {
		// Code to implement Antivirus Check Starts
		boolean virusFound = scanFileForVirus(virusChkFiledest.getPath(),
				virusChkFiledest.getName());

		if (virusFound && !error.hasErrors()) {
			error.rejectValue(STR_LOGOFILEDATA, STR_NOTEMPTY, virusFoundMsg);
		}
		// Code to implement Antivirus Check Ends
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
	 * 
	 * @param session
	 * @param brandingTemplateForm
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeTestimonies", method = RequestMethod.POST)
	public int removeTestimonies(HttpSession session,
			BrandingTemplateForm brandingTemplateForm, @RequestParam("id") int id) {

		try {
			if (null != brandingTemplateForm.getListTestimony()) {
				//int count = 0;
				for (TestimonyForm testimonyForm : brandingTemplateForm.getListTestimony()) {
					if (testimonyForm.getItemId() == id) {
						brandingTemplateForm.getListTestimony().remove(testimonyForm);
						break;
					}
					//count++;
				}

			//	brandingTemplateForm.getListTestimony().remove(count);

			}
		} catch (Exception exp) {
			LOGGER.info("Exception occured while deleting testimlni");
		}
		return id;
	}

	/**
	 * 
	 * @param session
	 * @param brandingTemplateForm
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeImage", method = RequestMethod.POST)
	public int removeImages(HttpSession session,
			BrandingTemplateForm brandingTemplateForm, @RequestParam("id") int id) {

		try {
			if (null != brandingTemplateForm.getListAddImages()) {
				//int count = 0;
				for (AddImageForm addImageForm : brandingTemplateForm.getListAddImages()) {
					if (addImageForm.getItemId() == id) {
						brandingTemplateForm.getListAddImages().remove(addImageForm);
						break;
					}
					//count++;
				}

			//	brandingTemplateForm.getListTestimony().remove(count);

			}
		} catch (Exception exp) {
			LOGGER.info("Exception occured while deleting images");
		}
		return id;
	}

	/**
	 * 
	 * @param session
	 * @param brandingTemplateForm
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeVideo", method = RequestMethod.POST)
	public int removeVideos(HttpSession session,
			BrandingTemplateForm brandingTemplateForm,
			@RequestParam("id") int id) {

		try {
			if (null != brandingTemplateForm.getListVideos()) {
				// int count = 0;
				for (VideoForm videoForm : brandingTemplateForm.getListVideos()) {
					if (videoForm.getItemId() == id) {
						brandingTemplateForm.getListVideos().remove(videoForm);
						break;
					}
					// count++;
				}

				// brandingTemplateForm.getListTestimony().remove(count);

			}
		} catch (Exception exp) {
			LOGGER.info("Exception occured while deleting Videos");
		}
		return id;
	}
}
