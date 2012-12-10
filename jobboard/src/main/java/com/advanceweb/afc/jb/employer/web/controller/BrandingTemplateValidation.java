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

	private @Value("${imageSizeLimit}")
	long imageSizeLimit;

	private @Value("${videoSizeLimit}")
	long videoSizeLimit;

	private @Value("${empBrandMainImage}")
	String empBrandMainImage;

	private @Value("${empBrandMainImageSize}")
	String empBrandMainImageSize;

	private @Value("${empBrandLogo}")
	String empBrandLogo;

	private @Value("${empBrandLogoSize}")
	String empBrandLogoSize;

	private @Value("${empBrandAddImage}")
	String empBrandAddImage;

	private @Value("${empBrandAddImageSize}")
	String empBrandAddImageSize;

	private @Value("${empBrandVideo}")
	String empBrandVideo;

	private @Value("${empBrandVideoSize}")
	String empBrandVideoSize;

	private @Value("${empBrandTemplateName}")
	String empBrandTemplateName;

	private @Value("${empBrandTemplateExists}")
	String empBrandTemplateExists;
	
	@Autowired
	private BrandingTemplateService brandingTemplateService;
	
	private static final String STR_NOTEMPTY = "NotEmpty";
	
	private static final String MAIN_IMAGE_FILE = "mainImageFileData";
	
	private static final String LOGO_FILE = "logoFileData";

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

			if (image.getAddImageFileData().getSize() > 0) {
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
			if (image.getAddImageFileData().getSize() > 0) {
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
			if (video.getVideoFileData().getSize() > 0) {
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
			if (video.getVideoFileData().getSize() > 0) {
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
