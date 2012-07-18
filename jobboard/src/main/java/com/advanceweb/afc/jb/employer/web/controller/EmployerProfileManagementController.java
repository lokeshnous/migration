package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
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
public class EmployerProfileManagementController {

	@Autowired
	ManageFeatureEmployerProfile manageFeatureEmployerProfile;

	@RequestMapping(value = "/employerprofile", method = RequestMethod.GET)
	public ModelAndView getEmployeeProfile(Map model) {

		return new ModelAndView("manageFeatureEmpPro");
	}

	/**
	 * Saving Manage Featured Employer Profile
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveemployerprofile", method = RequestMethod.GET)
	public ModelAndView saveEmployeeProf(Map model) {
		CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();
		companyProfileDTO.setCompanyName("Merion Matters");
		companyProfileDTO.setCompanyOverview("Company1");
		companyProfileDTO.setCompanyEmail("mm@mm.com");
		companyProfileDTO.setCompanyWebsite("http//www.mm.com");

		manageFeatureEmployerProfile.saveEmployerProfile(companyProfileDTO);

		return new ModelAndView("manageFeatureEmpPro");
	}

	/**
	 * Cancel Manage Featured Employer Profile
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cancelemployerprofile", method = RequestMethod.GET)
	public ModelAndView cancelemployerprofile(Map model) {

		return new ModelAndView("manageFeatureEmpPro");
	}
	
	
	
	/**
	 * to get Saved Job
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/viewEmployerDetails", method = RequestMethod.GET)
	public ModelAndView getSavedJob(Map model) {
		CompanyProfileDTO companyProfileDTO = manageFeatureEmployerProfile.getEmployerDetails(109);

		return new ModelAndView("manageFeatureEmpPro");
	}
	
	
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editAccountSettings",method = RequestMethod.GET)
	public String editAccountSettings(
			BindingResult result,Map model) {		
		try {			
			
			List<EmployerProfileDTO> listEmpProfile = manageFeatureEmployerProfile.getEmployerAccountDetails(2);
			
//			model.put("jobSeekerRegistrationForm", jsRegistrationForm);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registrationsuccess";
	}
	
	

}
