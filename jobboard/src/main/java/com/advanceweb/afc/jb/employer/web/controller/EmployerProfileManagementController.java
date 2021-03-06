/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
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
import com.advanceweb.afc.jb.common.util.AVScannerHelper;
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
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(EmployerProfileManagementController.class);
	
	/** The Constant STR_NOTEMPTY. */
	private static final String STR_NOTEMPTY = "NotEmpty";
	
	/** The Constant STR_UNDERSCORE. */
	private static final String STR_UNDERSCORE = "_";

	/** The manage featured employer profile. */
	@Autowired
	private ManageFeaturedEmployerProfile manageFeaturedEmployerProfile;
	
	/** The base directory path image and media. */
	private @Value("${baseDirectoryPathImageAndMedia}")
	String baseDirectoryPathImageAndMedia;
	
	/** The app media path. */
	private @Value("${appMediaPath}")
	String appMediaPath;
	
	/** The feature employer prefix. */
	private @Value("${featureEmployerPrefix}")
	String featureEmployerPrefix;
	
	/** The is featured employer error msg. */
	private @Value("${isFeaturedEmployerErrorMsg}")
	String isFeaturedEmployerErrorMsg;
	
	/** The virus found msg. */
	private @Value("${virus.found.video.msg}")
	String virusFoundMsg;
	
	/** The virus found image msg. */
	private @Value("${virus.found.image.msg}")
	String 	virusFoundImageMsg;

	
	/** The ad service. */
	@Autowired
	private AdService adService;
	
	/** The NetSuite package internal ID. */
	@Value("${FEATURE_30}")
	private String FEATURE_30;

	/** The NetSuite package internal ID. */
	@Value("${FEATURE_90}")
	private String FEATURE_90;
	
	/** The NetSuite package internal ID. */
	@Value("${FEATURE_180}")
	private String FEATURE_180;
	
	/** The NetSuite package internal ID. */
	@Value("${FEATURE_365}")
	private String FEATURE_365;
	
	/**
	 * Gets the employee profile.
	 *
	 * @param employerProfileManagementForm the employer profile management form
	 * @param session the session
	 * @param request the request
	 * @return the employee profile
	 */
	@RequestMapping(value = "/employerprofile", method = RequestMethod.GET)

	public ModelAndView getEmployeeProfile(EmployerProfileManagementForm employerProfileManagementForm,
			HttpSession session, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		int admFacilityId = Integer.parseInt(session.getAttribute(MMJBCommonConstants.FACILITY_ID).toString());
		
		//get parent facility id if logged in user is job owner
		int parentFacilityId = manageFeaturedEmployerProfile
				.getParentId((Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID));
		// Getting the customer ID from Adm Facility table.
		int nsCustomerID = manageFeaturedEmployerProfile.getNSCustomerIDFromAdmFacility(parentFacilityId);
		// Get the list of valid packages purchased by customers from NetSuite
		List<String> purchasedPackages = manageFeaturedEmployerProfile
				.getNSCustomerPackages(nsCustomerID);
				
		if (purchasedPackages.contains(FEATURE_30)
				|| purchasedPackages.contains(FEATURE_90)
				|| purchasedPackages.contains(FEATURE_180)
				|| purchasedPackages.contains(FEATURE_365)) {
			CompanyProfileDTO companyProfileDTO = manageFeaturedEmployerProfile
					.getEmployerDetails(parentFacilityId);
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
				
				/*employerProfileManagementForm.setPositionalMedia(companyProfileDTO.getPositionalMedia());*/
				employerProfileManagementForm.setPositionalMediaUrl(companyProfileDTO.getPositionalMedia());
			}
			String logoName = null;
			String mediaName = null;
			if(employerProfileManagementForm.getLogoPath() != null){
				String[] list = employerProfileManagementForm.getLogoPath().split("_");
				logoName = employerProfileManagementForm.getLogoPath().replace(list[0]+"_", "")
						.replace(list[1]+"_", "");
			}
			if(employerProfileManagementForm.getPositionalMediaUrl() != null){
				String[] list = employerProfileManagementForm.getPositionalMediaUrl().split("_");
				mediaName = employerProfileManagementForm.getPositionalMediaUrl().replace(list[0]+"_", "")
						.replace(list[1]+"_", "");
			}
			model.addObject("employerProfileManagementForm", employerProfileManagementForm);
			model.addObject("logoName", logoName);
			model.addObject("mediaName", mediaName);
		}else{
			LOGGER.debug("Not a Featured Employer.");
			model.addObject("error",isFeaturedEmployerErrorMsg);
		}
		model.setViewName("manageFeatureEmpPro");
		// get the Ads
		populateAds (request, session, model, PageNames.EMPLOYER_MANAGE_FEATURED_EMP);

		return model;
	}
	
	/**
	 * The method helps to populate the ads for the page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param pageName
	 */
	private void populateAds (HttpServletRequest request,
			HttpSession session, ModelAndView model, String pageName) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, pageName);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);

			
			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
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
		int facilityId = 0;
		int nsCustomerId = 0;
		int parentFacilityId = manageFeaturedEmployerProfile
				.getParentId((Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID));
		/*
		 * CompanyProfileDTO companyProfileDTO = manageFeaturedEmployerProfile
		 * .getEmployerDetails((Integer) session
		 * .getAttribute(MMJBCommonConstants.FACILITY_ID));
		 */
		CompanyProfileDTO companyProfileDTO = manageFeaturedEmployerProfile
				.getEmployerDetails(parentFacilityId);
		// companyProfileDTO.setCompanyName(managementForm.getCompanyName());
		companyProfileDTO.setCompanyOverview(managementForm
				.getCompanyOverview());

		// Validate email id
		//checkEmail(managementForm.getCompanyEmail(), result);
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
					checkImageFileForVirus(dest,result);
					
				}
				if (null != promoMedia && promoMedia.getSize() > 0) {
					fileName = promoMedia.getOriginalFilename();
					String modifiedFileName = featureEmployerPrefix+STR_UNDERSCORE
							+ random.nextInt(10000) + STR_UNDERSCORE + fileName;
					companyProfileDTO.setPositionalMedia(modifiedFileName);
					filePath = System.getProperty("catalina.home") + appMediaPath + modifiedFileName;
					File transfer = new File(filePath);
					promoMedia.transferTo(transfer);
					checkVedioFileForVirus(transfer,result);

				}
				if (result.hasErrors()) {
					model.addObject("managementForm", managementForm);
					model.setViewName("manageFeatureEmpPro");
					return model;
				} 

				facilityId = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
				nsCustomerId = manageFeaturedEmployerProfile.getNSCustomerIDFromAdmFacility(facilityId);
				UserDTO userDTO = manageFeaturedEmployerProfile.getNSFeatureDates(nsCustomerId);
				companyProfileDTO.setFeaturedStartDate(userDTO.getFeaturedStartDate());
				companyProfileDTO.setFeaturedEndDate(userDTO.getFeaturedEndDate());
				
				manageFeaturedEmployerProfile.saveEmployerProfile(companyProfileDTO);
				
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		model.setViewName("redirect:/employer/employerDashBoard.html");
		return model;
	}

	/**
	 * Method to scan file for anti-virus
	 * @param virusChkFiledest
	 * @param error
	 */
	private void checkImageFileForVirus(File virusChkFiledest,Errors error) {
		// Code to implement Antivirus Check Starts	
		boolean virusFound = scanFileForVirus(virusChkFiledest.getPath(), virusChkFiledest.getName()); 
		
		if (virusFound) {
			error.rejectValue("logoUrl", STR_NOTEMPTY, virusFoundImageMsg);
		}
		// Code to implement Antivirus Check Ends	
	}
	/**
	 * @param session
	 */
	private void checkVedioFileForVirus(File virusChkFiledest,Errors error) {
		// Code to implement Antivirus Check Starts	
		boolean virusFound = scanFileForVirus(virusChkFiledest.getPath(), virusChkFiledest.getName()); 
		
		if (virusFound) {
			error.rejectValue("positionalMedia", STR_NOTEMPTY, virusFoundMsg);
		}
		// Code to implement Antivirus Check Ends	
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
	
	/**
	 * Scan the file for virus
	 * @param uploadedFile File that is uploaded
	 * @param uploadFileName name of the file being uploaded 
	 * @return boolean "true" if the file is virus free, "false" informing that the file
	 *  is not clean and might contain virus thus we do not proceed to upload the file
	 */
	private boolean scanFileForVirus(String uploadFilePath, String uploadFileName) {
		boolean virusFound = false;
		AVScannerHelper avScanHelper = new AVScannerHelper();
		virusFound = avScanHelper.scanFile(uploadFilePath, uploadFileName);
		return virusFound;
	}
}
