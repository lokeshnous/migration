package com.advanceweb.afc.jb.employer.web.controller;

import java.io.File;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.ManageFeatureEmployerProfile;

/**
 * <code> EmployerProfileManagementController <code>
 * 
 * @author Sharad
 * @version 1.0
 * @since July 13th, 2012
 * 
 */
@Controller
@RequestMapping("/empProfile")
@SessionAttributes("employerProfileManagementForm")
public class EmployerProfileManagementController {
	private static final Logger LOGGER = Logger.getLogger(EmployerProfileManagementController.class);
	private static final String STR_NOTEMPTY = "NotEmpty";

	@Autowired
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;
	
	private @Value("${baseDirectoryPathImageAndMedia}")
	String baseDirectoryPathImageAndMedia;
	
	private @Value("${isFeaturedEmployerErrorMsg}")
	String isFeaturedEmployerErrorMsg;
	
	@RequestMapping(value = "/employerprofile", method = RequestMethod.GET)

	public ModelAndView getEmployeeProfile(EmployerProfileManagementForm employerProfileManagementForm,
			HttpSession session) {

		ModelAndView model = new ModelAndView();
		boolean isDateRange = false;
		int admFacilityId = Integer.parseInt(session.getAttribute(MMJBCommonConstants.FACILITY_ID).toString());
		// Getting the customer ID from Adm Facility table.
		int nsCustomerID = manageFeatureEmployerProfile.getNSCustomerIDFromAdmFacility(admFacilityId);
		UserDTO userDTO = manageFeatureEmployerProfile.getNSCustomerDetails(nsCustomerID);
		
		Date featuredStartDate = userDTO.getFeaturedStartDate();
		Date featuredEndDate = userDTO.getFeaturedEndDate();
		if(featuredStartDate != null && featuredEndDate != null){
			isDateRange = isInDateRange(featuredStartDate, featuredEndDate);
		}
		LOGGER.info("Featured Start Date is "+featuredStartDate);
		LOGGER.info("Featured End Date is "+featuredEndDate);
		LOGGER.info("Is in Date Range=>"+isDateRange);
		//kuserDTO.setFeatured(true);
		if(userDTO.isFeatured() && isDateRange){
			CompanyProfileDTO companyProfileDTO = manageFeatureEmployerProfile
					.getEmployerDetails((Integer) session
							.getAttribute(MMJBCommonConstants.FACILITY_ID));
			if (null != companyProfileDTO) {
				employerProfileManagementForm.setCompanyName(companyProfileDTO
						.getCompanyName());
				employerProfileManagementForm.setCompanyNews(companyProfileDTO
						.getCompanyNews());
				employerProfileManagementForm.setCompanyOverview(companyProfileDTO
						.getCompanyOverview());
				employerProfileManagementForm.setCompanyWebsite(companyProfileDTO
						.getCompanyWebsite());
				employerProfileManagementForm.setCompanyEmail(companyProfileDTO
						.getCompanyEmail());
				employerProfileManagementForm.setPositionTitle(companyProfileDTO
						.getPositionTitle());
				employerProfileManagementForm.setLogoPath(companyProfileDTO
						.getLogoPath());
				employerProfileManagementForm.setPrimaryColor(companyProfileDTO.getPrimaryColor());
				/*
				employerProfileManagementForm.setPositionalMedia(companyProfileDTO.getPositionalMedia());*/
			}
			model.addObject("employerProfileManagementForm", employerProfileManagementForm);
			model.setViewName("manageFeatureEmpPro");
		}else{
			LOGGER.info("Not a Featured Employer.");
			model.setViewName("manageFeatureEmpPro");
			model.addObject("error",isFeaturedEmployerErrorMsg);
		}
		

		

		return model;
	}

	/**
	 * Saving Manage Featured Employer Profile
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveemployerprofile", method = RequestMethod.POST)
	public ModelAndView saveEmployeeProf(EmployerProfileManagementForm managementForm, BindingResult result,HttpSession session) {
		ModelAndView model = new ModelAndView();
		CompanyProfileDTO companyProfileDTO =  manageFeatureEmployerProfile
				.getEmployerDetails((Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID));
		companyProfileDTO.setCompanyName(managementForm.getCompanyName());
		companyProfileDTO.setCompanyOverview(managementForm.getCompanyOverview());
//		Validate email id
		checkEmail(managementForm.getCompanyEmail(), result);
		/*companyProfileDTO.setFacilityid( (String) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID));*/
		companyProfileDTO.setCompanyEmail(managementForm.getCompanyEmail());
		companyProfileDTO.setCompanyWebsite(managementForm.getCompanyWebsite());
        companyProfileDTO.setPrimaryColor(managementForm.getPrimaryColor());
        companyProfileDTO.setCompanyNews(managementForm.getCompanyNews());
        String fileName = null, filePath = null;
        
		try {
			MultipartFile logoUrl = managementForm.getLogoUrl();
			MultipartFile promoMedia = managementForm.getPositionalMedia();
			validateImageFileFormat(logoUrl.getOriginalFilename(),result);
			validateMediaFileFormat(promoMedia.getOriginalFilename(),result);
			if (result.hasErrors()) {
				model.addObject("managementForm", managementForm);
				model.setViewName("manageFeatureEmpPro");
				return model;
			} else {
				if (null != logoUrl && logoUrl.getSize() > 0) {
					fileName = logoUrl.getOriginalFilename();
					filePath = baseDirectoryPathImageAndMedia + fileName;

					companyProfileDTO.setLogoPath(filePath);
					File dest = new File(filePath);
					logoUrl.transferTo(dest);

				}
				if (null != promoMedia && promoMedia.getSize() > 0) {
					fileName = promoMedia.getOriginalFilename();
					filePath = baseDirectoryPathImageAndMedia + fileName;

					companyProfileDTO.setPositionTitle(filePath);
					File transfer = new File(filePath);
					promoMedia.transferTo(transfer);

				}

				manageFeatureEmployerProfile
						.saveEmployerProfile(companyProfileDTO);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		model.setViewName("redirect:/employer/employerDashBoard.html");
		return model;
	}
	
	
	/**
	 * Validating the emailId
	 * 
	 * @param registerForm
	 * @return
	 * @return
	 */
	public void checkEmail(String email, Errors errors) {

		if (StringUtils.isEmpty(email)) {
			errors.rejectValue("companyEmail", "NotEmpty",
					"Email id should not be blank");
		}

		if (!StringUtils.isEmpty(email) && !validateEmailPattern(email)) {

			errors.rejectValue("companyEmail", "NotEmpty", " Please enter the correct E-Mail Address");

		}
	}
	
	/**
	 * Validating Email Pattern
	 * 
	 * @param emailId
	 * @return
	 */
	public boolean validateEmailPattern(String emailId) {
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		matcher = pattern.matcher(emailId);
		return matcher.matches();
	}
	/**
	 * Validating Image File Format
	 * 
	 * @param emailId
	 * @return
	 */
	public void validateImageFileFormat(String fileName, Errors errors) {
		int imageLength = fileName.length();
		if (imageLength > 0) {
			String fileExtension = fileName.substring(imageLength - 4,
					imageLength);
			if (!(fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_JPG)
					|| fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_GIF)
					|| fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_PNG) || fileExtension
						.contains(MMJBCommonConstants.IMAGE_TYPE_TIF))) {
				errors.rejectValue("logoUrl", STR_NOTEMPTY,
						"Please select the appropriate image");
			}
		}
	}
	/**
	 * Validating Vedio File Format
	 * 
	 * @param emailId
	 * @return
	 */
	public void validateMediaFileFormat(String fileName, Errors errors) {
		int imageLength = fileName.length();
		if (imageLength > 0) {
			String fileExtension = fileName.substring(imageLength - 4,
					imageLength);
			if (!(fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_JPG)
					|| fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_GIF)
					|| fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_PNG)
					|| fileExtension
							.contains(MMJBCommonConstants.IMAGE_TYPE_TIF)
					|| fileExtension
							.contains(MMJBCommonConstants.MEDIA_TYPE_AVI)
					|| fileExtension
							.contains(MMJBCommonConstants.MEDIA_TYPE_MPEG)
					|| fileExtension
							.contains(MMJBCommonConstants.MEDIA_TYPE_MPEG_4)
					|| fileExtension
							.contains(MMJBCommonConstants.VIDEO_TYPE_MPG)
					|| fileExtension
							.contains(MMJBCommonConstants.MEDIA_TYPE_PPT)
					|| fileExtension
							.contains(MMJBCommonConstants.MEDIA_TYPE_PPTX) || fileExtension
						.contains(MMJBCommonConstants.VIDEO_TYPE_MOV))) {
				errors.rejectValue("positionalMedia", STR_NOTEMPTY,
						"Please select the appropriate media file");
			}
		}
	}
	
	/**
	 * This method used to compare whether the current date is in the range
	 * of start date and end date.
	 * @param startDate
	 * @param endDate
	 * @return boolean
	 */
	public boolean isInDateRange(Date startDate, Date endDate){
		Date currentDate = new Date();
		if(currentDate.getTime() > startDate.getTime() && currentDate.getTime() < endDate.getTime()){
			return true;
		}else{
			return false;
		}
	}
	
	
}
