package com.advanceweb.afc.jb.employer.web.controller;

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
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

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerProfile;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

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
public class EmployerProfileManagementController extends AbstractController{
	private static final Logger LOGGER = Logger.getLogger(EmployerProfileManagementController.class);
	private static final String STR_NOTEMPTY = "NotEmpty";
	private static final String STR_UNDERSCORE = "_";

	@Autowired
	private ManageFeaturedEmployerProfile manageFeaturedEmployerProfile;
	
	private @Value("${baseDirectoryPathImageAndMedia}")
	String baseDirectoryPathImageAndMedia;
	
	private @Value("${appMediaPath}")
	String appMediaPath;
	
	private @Value("${featureEmployerPrefix}")
	String featureEmployerPrefix;
	
	private @Value("${isFeaturedEmployerErrorMsg}")
	String isFeaturedEmployerErrorMsg;
	
	@Autowired
	private AdService adService;
	
	@RequestMapping(value = "/employerprofile", method = RequestMethod.GET)

	public ModelAndView getEmployeeProfile(EmployerProfileManagementForm employerProfileManagementForm,
			HttpSession session, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		boolean isDateRange = false;
		int admFacilityId = Integer.parseInt(session.getAttribute(MMJBCommonConstants.FACILITY_ID).toString());
		// Getting the customer ID from Adm Facility table.
		int nsCustomerID = manageFeaturedEmployerProfile.getNSCustomerIDFromAdmFacility(admFacilityId);
		UserDTO userDTO = manageFeaturedEmployerProfile.getNSCustomerDetails(nsCustomerID);
		
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
			CompanyProfileDTO companyProfileDTO = manageFeaturedEmployerProfile
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
		}else{
			LOGGER.info("Not a Featured Employer.");
			model.addObject("error",isFeaturedEmployerErrorMsg);
		}
		model.setViewName("manageFeatureEmpPro");
		// get the Ads
		getAdsForManagedFeaturedEmp(request, session, model);

		return model;
	}
	
	/**
	 * Get Ads for manage featured employers page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void getAdsForManagedFeaturedEmp (HttpServletRequest request,
			HttpSession session, ModelAndView model) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.EMPLOYER_MANAGE_FEATURED_EMP);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageTop", bannerString);

			
			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageBtm", bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);		}
	}


	/**
	 * Saving Manage Featured Employer Profile
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveemployerprofile", method = RequestMethod.POST)
	public ModelAndView saveEmployeeProf(
			EmployerProfileManagementForm managementForm, BindingResult result,
			HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		CompanyProfileDTO companyProfileDTO = manageFeaturedEmployerProfile
				.getEmployerDetails((Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID));
		//companyProfileDTO.setCompanyName(managementForm.getCompanyName());
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
        Random random = new Random();
        
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
					String modifiedFileName = featureEmployerPrefix+STR_UNDERSCORE
							+ random.nextInt(10000) + STR_UNDERSCORE + fileName;
					
					filePath = baseDirectoryPathImageAndMedia + modifiedFileName;
					companyProfileDTO.setLogoPath(filePath);
					File dest = new File(filePath);
					logoUrl.transferTo(dest);

				}
				if (null != promoMedia && promoMedia.getSize() > 0) {
					fileName = promoMedia.getOriginalFilename();
					String modifiedFileName = featureEmployerPrefix+STR_UNDERSCORE
							+ random.nextInt(10000) + STR_UNDERSCORE + fileName;
					companyProfileDTO.setPositionTitle(modifiedFileName);
					filePath = System.getProperty("catalina.home") + appMediaPath + modifiedFileName;
					File transfer = new File(filePath);
					promoMedia.transferTo(transfer);

				}

				manageFeaturedEmployerProfile
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
					"Email address should not be blank");
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
	 * @param fileName
	 * @param errors
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
						"Please select an appropriate logo");
			}
		}
	}
	
	/**
	 * Validating Video File Format
	 * 
	 * @param fileName
	 * @param errors
	 */
	public void validateMediaFileFormat(String fileName, Errors errors) {
		int imageLength = fileName.length();
		if (imageLength > 0) {
			String fileExtension = fileName.substring(imageLength - 4,
					imageLength);
			fileExtension = fileExtension.toLowerCase(Locale.ENGLISH);
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
						.contains(MMJBCommonConstants.VIDEO_TYPE_MOV)|| fileExtension
						.contains(MMJBCommonConstants.MEDIA_TYPE_WMV))) {
				errors.rejectValue("positionalMedia", STR_NOTEMPTY,
						"Please select an appropriate media file");
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
	public boolean isInDateRange(Date startDate, Date endDate) {
		Date currentDate = new Date();
		return (currentDate.getTime() > startDate.getTime() && currentDate
				.getTime() < endDate.getTime());
	}
	
	
}
