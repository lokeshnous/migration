package com.advanceweb.afc.jb.employer.web.controller;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	@Autowired
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;
	private @Value("${baseDirectoryPathImageAndMedia}")
	String baseDirectoryPathImageAndMedia;
	@RequestMapping(value = "/employerprofile", method = RequestMethod.GET)
	public ModelAndView getEmployeeProfile(EmployerProfileManagementForm managementForm) {
		ModelAndView model = new ModelAndView();
		model.addObject("employerProfileManagementForm", managementForm);
		model.setViewName("manageFeatureEmpPro");
		return model;
	}

	/**
	 * Saving Manage Featured Employer Profile
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveemployerprofile", method = RequestMethod.POST)
	public ModelAndView saveEmployeeProf(EmployerProfileManagementForm managementForm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();
		companyProfileDTO.setCompanyName(managementForm.getCompanyName());
		companyProfileDTO.setCompanyOverview(managementForm.getCompanyOverview());
//		Validate email id
		checkEmail(managementForm.getCompanyEmail(), result);
		
		if (result.hasErrors()) {
			model.addObject("managementForm", managementForm);
			model.setViewName("manageFeatureEmpPro");
			return model;
		}
		
		companyProfileDTO.setCompanyEmail(managementForm.getCompanyEmail());
		companyProfileDTO.setCompanyWebsite(managementForm.getCompanyWebsite());
        companyProfileDTO.setPrimaryColor(managementForm.getPrimaryColor());
        companyProfileDTO.setCompanyNews(managementForm.getCompanyNews());
        String fileName = null, filePath = null;
        
		try {
			MultipartFile logoUrl = managementForm.getLogoUrl();
			MultipartFile promoMedia = managementForm.getPositionalMedia();

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
			manageFeatureEmployerProfile.saveEmployerProfile(companyProfileDTO);
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

			errors.rejectValue("companyEmail", "NotEmpty", "Invalid Email Id");

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
	
	
}
