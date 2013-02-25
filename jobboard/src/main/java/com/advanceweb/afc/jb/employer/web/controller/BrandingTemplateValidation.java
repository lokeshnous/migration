/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;

/**
 * 
 * @author Harsha
 * 
 * @Version 1.0
 * @Since 22nd August, 2012
 */
@Component("brandingTemplateValidation")
public class BrandingTemplateValidation {

	/** The image size limit. */
	private @Value("${imageSizeLimit}")
	long imageSizeLimit;

	/** The video size limit. */
	private @Value("${videoSizeLimit}")
	long videoSizeLimit;

	/** The emp brand main image. */
	private @Value("${empBrandMainImage}")
	String empBrandMainImage;

	/** The emp brand main image size. */
	private @Value("${empBrandMainImageSize}")
	String empBrandMainImageSize;

	/** The emp brand logo. */
	private @Value("${empBrandLogo}")
	String empBrandLogo;

	/** The emp brand logo size. */
	private @Value("${empBrandLogoSize}")
	String empBrandLogoSize;

	/** The emp brand add image. */
	private @Value("${empBrandAddImage}")
	String empBrandAddImage;

	/** The emp brand add image size. */
	private @Value("${empBrandAddImageSize}")
	String empBrandAddImageSize;

	/** The emp brand video. */
	private @Value("${empBrandVideo}")
	String empBrandVideo;

	/** The emp brand video size. */
	private @Value("${empBrandVideoSize}")
	String empBrandVideoSize;

	/** The emp brand template name. */
	private @Value("${empBrandTemplateName}")
	String empBrandTemplateName;

	/** The emp brand template exists. */
	private @Value("${empBrandTemplateExists}")
	String empBrandTemplateExists;
	
	/** The branding template service. */
	@Autowired
	private BrandingTemplateService brandingTemplateService;
	
	/** The Constant STR_NOTEMPTY. */
	private static final String STR_NOTEMPTY = "NotEmpty";
	
	/** The Constant MAIN_IMAGE_FILE. */
	private static final String MAIN_IMAGE_FILE = "mainImageFileData";
	
	/** The Constant LOGO_FILE. */
	private static final String LOGO_FILE = "logoFileData";

	/**
	 * Supports.
	 *
	 * @param form the form
	 * @return true, if successful
	 */
	public boolean supports(Class<?> form) {
		return BrandingTemplateValidation.class.isAssignableFrom(form);
	}

	/**
	 * Validating the Main image file
	 * 
	 * @param brandingTemplateForm
	 * @param errors
	 * @return void
	 */
	public void validateMainImage(BrandingTemplateForm brandingTemplateForm,
			Errors errors) {
		if (null != brandingTemplateForm.getMainImagePath()) {
			int imageLength = brandingTemplateForm.getMainImagePath().length();
			String fileExtension = brandingTemplateForm.getMainImagePath()
					.substring(imageLength - 4, imageLength);
			fileExtension = fileExtension.toLowerCase(Locale.ENGLISH);
			long imageSize = brandingTemplateForm.getMainImageFileData()
					.getSize();

			if (!(fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_JPG)
					|| fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_GIF)
					|| fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_PNG) || fileExtension
						.contains(MMJBCommonConstants.IMAGE_TYPE_TIF))) {
				errors.rejectValue(MAIN_IMAGE_FILE, STR_NOTEMPTY,
						empBrandMainImage);
			}
			
			if (imageSize == 0
					|| imageSize > (imageSizeLimit * MMJBCommonConstants.KILO_BYTE) && !errors.hasFieldErrors(MAIN_IMAGE_FILE)) {
				errors.rejectValue(MAIN_IMAGE_FILE, STR_NOTEMPTY,
						empBrandMainImageSize);
			}
		}
	}

	/**
	 * Validating the Logo image file
	 * 
	 * @param brandingTemplateForm
	 * @param errors
	 * @return void
	 */
	public void validateLogo(BrandingTemplateForm brandingTemplateForm,
			Errors errors) {

		if (null != brandingTemplateForm.getLogoPath()) {
			int imageLength = brandingTemplateForm.getLogoPath().length();
			String fileExtension = brandingTemplateForm.getLogoPath()
					.substring(imageLength - 4, imageLength);
			fileExtension = fileExtension.toLowerCase(Locale.ENGLISH);
			long imageSize = brandingTemplateForm.getLogoFileData().getSize();

			if (!(fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_JPG)
					|| fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_GIF)
					|| fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_PNG) || fileExtension
						.contains(MMJBCommonConstants.IMAGE_TYPE_TIF))) {
				errors.rejectValue(LOGO_FILE, STR_NOTEMPTY, empBrandLogo);
			}
			
			if (imageSize == 0
					|| imageSize > (imageSizeLimit * MMJBCommonConstants.KILO_BYTE) && !errors.hasFieldErrors(LOGO_FILE)) {
				errors.rejectValue(LOGO_FILE, STR_NOTEMPTY,
						empBrandLogoSize);
			}
		}
	}

	/**
	 * Validating the Additional image
	 * 
	 * @param brandingTemplateForm
	 * @param errors
	 * @return String
	 */
	public String validateAddImage(BrandingTemplateForm brandingTemplateForm) {

		int imageLength;
		String fileExtension;

		for (AddImageForm image : brandingTemplateForm.getListAddImages()) {

			if (null!= image.getAddImageFileData() && image.getAddImageFileData().getSize() > 0) {
				imageLength = image.getMediaPath().length();
				fileExtension = image.getMediaPath().substring(imageLength - 4,
						imageLength);
				fileExtension = fileExtension.toLowerCase(Locale.ENGLISH);
				if (!(fileExtension
						.contains(MMJBCommonConstants.IMAGE_TYPE_JPG)
						|| fileExtension
								.contains(MMJBCommonConstants.IMAGE_TYPE_GIF)
						|| fileExtension
								.contains(MMJBCommonConstants.IMAGE_TYPE_PNG) || fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_TIF))) {
					return empBrandAddImage;
				}
			}
		}

		return null;

	}

	/**
	 * Validating the Additional image file size
	 * 
	 * @param brandingTemplateForm
	 * @param errors
	 * @return String
	 */
	public String validateAddImageSize(BrandingTemplateForm brandingTemplateForm) {

		long imageSize;

		for (AddImageForm image : brandingTemplateForm.getListAddImages()) {
			if (null!=image.getAddImageFileData() && image.getAddImageFileData().getSize() > 0) {
				imageSize = image.getAddImageFileData().getSize();
				if (imageSize > (imageSizeLimit * MMJBCommonConstants.KILO_BYTE)) {
					return empBrandAddImageSize;
				}
			}
		}

		return null;

	}

	/**
	 * Validating the video
	 * 
	 * @param brandingTemplateForm
	 * @param errors
	 * @return String
	 */
	public String validateVideo(BrandingTemplateForm brandingTemplateForm) {

		int videoLength;
		String fileExtension;

		for (VideoForm video : brandingTemplateForm.getListVideos()) {
			if (null != video.getVideoFileData() && video.getVideoFileData().getSize() > 0) {
				videoLength = video.getMediaPath().length();
				fileExtension = video.getMediaPath().substring(videoLength - 4,
						videoLength);
				fileExtension = fileExtension.toLowerCase(Locale.ENGLISH);
				if (!(fileExtension
						.contains(MMJBCommonConstants.MEDIA_TYPE_WMV) || fileExtension
						.contains(MMJBCommonConstants.MEDIA_TYPE_MPEG_4))) {
					return empBrandVideo;
				}
			}
		}

		return null;

	}

	/**
	 * Validating the video file size
	 * 
	 * @param brandingTemplateForm
	 * @param errors
	 * @return String
	 */
	public String validateVideoSize(BrandingTemplateForm brandingTemplateForm) {

		long videoSize;

		for (VideoForm video : brandingTemplateForm.getListVideos()) {
			if (null != video.getVideoFileData() && video.getVideoFileData().getSize() > 0) {
				videoSize = video.getVideoFileData().getSize();
				if (videoSize > (videoSizeLimit * MMJBCommonConstants.MEGA_BYTE)) {
					return empBrandVideoSize;
				}
			}
		}

		return null;

	}

	/**
	 * Validating form for Silver customers
	 * 
	 * @param target
	 * @param errors
	 */
	public void validateSilver(Object target, Errors errors) {

		BrandingTemplateForm brandingTemplateForm = (BrandingTemplateForm) target;

		if (!brandingTemplateForm.isEditMode() && checkTempNameExists(brandingTemplateForm)) {
			errors.rejectValue("templateName", STR_NOTEMPTY,
					empBrandTemplateExists);
		}

		if (null == brandingTemplateForm.getTemplateName()
				|| brandingTemplateForm.getTemplateName().isEmpty()) {
			errors.rejectValue("templateName", STR_NOTEMPTY,
					empBrandTemplateName);
		}

		if (null == brandingTemplateForm.getLogoPath()
				|| brandingTemplateForm.getLogoPath().isEmpty()) {
			errors.rejectValue(LOGO_FILE, STR_NOTEMPTY, empBrandLogo);
		}

		if (null == brandingTemplateForm.getMainImagePath()
				|| brandingTemplateForm.getMainImagePath().isEmpty()) {
			errors.rejectValue(MAIN_IMAGE_FILE, STR_NOTEMPTY,
					empBrandMainImage);
		}

		validateMultiPartData(errors, brandingTemplateForm);

	}

	/**
	 * This method checks if the template name already exists
	 * 
	 * @param brandingTemplateForm
	 * @return boolean
	 */
	private boolean checkTempNameExists(BrandingTemplateForm form){
		
		return brandingTemplateService.checkTemplateName(form.getFacilityId(), form.getTemplateName());
		
	}
		
	/**
	 * This method is used the validate the non empty MultiPart data
	 * 
	 * @param errors
	 * @param brandingTemplateForm
	 */
	private void validateMultiPartData(Errors errors,
			BrandingTemplateForm brandingTemplateForm) {
		if (null == brandingTemplateForm.getChosenLogo()
				|| brandingTemplateForm.getLogoFileData().getSize() > 0) {
			validateLogo(brandingTemplateForm, errors);
		}

		if (null == brandingTemplateForm.getChosenMainImage()
				|| brandingTemplateForm.getMainImageFileData().getSize() > 0) {
			validateMainImage(brandingTemplateForm, errors);
		}
	}

	/**
	 * Validating form for Non Silver customers
	 * 
	 * @param target
	 * @param errors
	 */
	public String validateNonSilver(Object target, Errors errors) {

		BrandingTemplateForm brandingTemplateForm = (BrandingTemplateForm) target;
		String validationMessage;

		// Validation for Additional Images
		validationMessage = validateAddImage(brandingTemplateForm);
		if (!StringUtils.isEmpty(validationMessage)) {
			return validationMessage;
		}

		validationMessage = validateAddImageSize(brandingTemplateForm);
		if (!StringUtils.isEmpty(validationMessage)) {
			return validationMessage;
		}

		// Validation for Videos
		validationMessage = validateVideo(brandingTemplateForm);
		if (!StringUtils.isEmpty(validationMessage)) {
			return validationMessage;
		}

		validationMessage = validateVideoSize(brandingTemplateForm);
		if (!StringUtils.isEmpty(validationMessage)) {
			return validationMessage;
		}

		return null;
	}

}
