package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
 * @Author : Sasibhushana
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class is used as controller for job seeker regigstration
 */
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.login.web.controller.ChangePasswordForm;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.user.ProfileRegistration;

@Controller
@RequestMapping("/jobseekerregistration")
@SessionAttributes("registerForm")
@Scope("session")
public class JobSeekerRegistrationController {
	
	@Autowired
	private ProfileRegistration profileRegistration;

	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;
	
	@Autowired
	private PopulateDropdowns populateDropdownsService;
	
	@Autowired
	private JobSeekerRegistrationValidation registerValidation;

	
	/**
	 * This method is called to display job seeker registration page Step1
	 * Create Your Account
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createJobSeekerCreateYrAcct",method = RequestMethod.GET)
	public ModelAndView createJobSeekerRegistrationStep1() {
		
		ModelAndView model = new ModelAndView();
		JobSeekerRegistrationForm registerForm = new JobSeekerRegistrationForm();
		model.setViewName("jobSeekerCreateAccount");
		model.addObject("registerForm", registerForm);	
		return model;
		
	}
	
	
	/**
	 * This method is called to display job seeker registration page
	 * Create Your information
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createJobSeekerYourInfo",method = RequestMethod.POST, params="Next")
	public ModelAndView createJobSeekerRegistration(@ModelAttribute("registerForm") JobSeekerRegistrationForm registerForm, 
			BindingResult result,Map map) {
		
		ModelAndView model = new ModelAndView();
				
		registerValidation.validate(registerForm, result);
		
		if(result.hasErrors()){
			model.setViewName("jobSeekerCreateAccount");
			return model;
		}
		
		List<CountryDTO> countryList= populateDropdownsService.getCountryList();
		List<StateDTO> stateList= populateDropdownsService.getStateList();
		List<EmploymentInfoDTO> empInfoList= populateDropdownsService.getEmployementInfoList();
		List<EthenticityDTO> ethnicityList= populateDropdownsService.getEthenticityList();
		List<GenderDTO> genderList= populateDropdownsService.getGenderList();
		List<VeteranStatusDTO> veteranStatusList= populateDropdownsService.getVeteranStatusList();
		List<DropDownDTO> empTyepList = populateDropdownsService.populateDropdown(MMJBCommonConstants.EMPLOYMENT_TYPE);
		model.addObject("countryList",countryList);
		model.addObject("employmentInfoList",empInfoList);
		model.addObject("genderList",genderList);
		model.addObject("ethnicityList",ethnicityList);
		model.addObject("veteranStatusList",veteranStatusList);
		model.addObject("empTyepList",empTyepList);
		model.setViewName("jobSeekerCreateAccountInfo");
		model.addObject("registerForm", registerForm);
		return model;
		
	}
	
	/**
	 * This method is called to save employee registration
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveJobSeekerProfile",method = RequestMethod.POST, params="Finish")
	public ModelAndView saveJobSeekerRegistration(@ModelAttribute("registerForm") @Valid JobSeekerRegistrationForm registerForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {			
				if (result.hasErrors()) {
					return new ModelAndView("jobSeekerCreateAccountInfo");
				}
		
				// Transform JobSeeker Registration Form to JobSeekerRegistrationDTO
				JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
				AddressDTO addDTO = transformJobSeekerRegistration.createAddressDTO(registerForm);
				MerUserDTO userDTO = transformJobSeekerRegistration.createUserDTO(registerForm);
				JobSeekerProfileDTO jsProfileSettingsDTO = transformJobSeekerRegistration.createJSProfileSettingsDTO(registerForm);
				jsRegistrationDTO.setAddressDTO(addDTO);
				jsRegistrationDTO.setJobSeekerProfileDTO(jsProfileSettingsDTO);
				jsRegistrationDTO.setMerUserDTO(userDTO);
				// Call to service layer
				profileRegistration.createNewProfile(jsRegistrationDTO);
				session.setAttribute("UserName", registerForm.getFirstName()+" "+registerForm.getLastName());
//				model.addObject(attributeName, attributeValue)
				model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * This method is called to save employee registration
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveJobSeekerProfile",method = RequestMethod.POST, params="Back")
	public ModelAndView backToCreateJobSeekerCreateYrAcct(@ModelAttribute("registerForm") @Valid JobSeekerRegistrationForm registerForm,
			BindingResult result) {
		try {			
/*				if (result.hasErrors()) {
					return new ModelAndView("jobseekerregistration");
				}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("jobSeekerCreateAccount","registerForm", registerForm);
	}
		
	/**
	 * This method is called to navigate to home page on click of cancel
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveJobSeekerProfile",method = RequestMethod.POST, params="Cancel")
	public ModelAndView backToHomePage() {

		return new ModelAndView("healthcarejobs/advanceweb.html","", "");
	}
			
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewJobSeekerProfile",method = RequestMethod.GET)
	public ModelAndView viewJobSeekerProfileSettings() {
		ModelAndView model = new ModelAndView();
		try {

			// Call to service layer
			List<CountryDTO> countryList= populateDropdownsService.getCountryList();
			List<StateDTO> stateList= populateDropdownsService.getStateList();
			List<EmploymentInfoDTO> empInfoList= populateDropdownsService.getEmployementInfoList();
			List<EthenticityDTO> ethnicityList= populateDropdownsService.getEthenticityList();
			List<GenderDTO> genderList= populateDropdownsService.getGenderList();
			List<VeteranStatusDTO> veteranStatusList= populateDropdownsService.getVeteranStatusList();
			List<DropDownDTO> empTyepList = populateDropdownsService.populateDropdown(MMJBCommonConstants.EMPLOYMENT_TYPE);
			model.addObject("countryList",countryList);
			model.addObject("employmentInfoList",empInfoList);
			model.addObject("genderList",genderList);
			model.addObject("ethnicityList",ethnicityList);
			model.addObject("veteranStatusList",veteranStatusList);
			model.addObject("empTyepList",empTyepList);
			JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration.viewProfile(322);
			JobSeekerRegistrationForm form = transformJobSeekerRegistration.jsRegistrationDTOToJobSeekerRegistrationForm(jsRegistrationDTO);
			model.addObject("registerForm", form);
			model.setViewName("jobseekerEditProfileSettings");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateJobSeekerProfile",method = RequestMethod.GET)
	public String updateJobSeekerProfileSettings(@Valid JobSeekerRegistrationForm jsRegistrationForm,
			BindingResult result,Map model) {
		
		try {			
			JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
			/*AddressDTO addDTO = transformJobSeekerRegistration.createAddressDTO(jsRegistrationForm.getContactForm());*/
			MerUserDTO userDTO = transformJobSeekerRegistration.createUserDTO(jsRegistrationForm);
//			jsRegistrationDTO.setAddressDTO(addDTO);
			jsRegistrationDTO.setMerUserDTO(userDTO);
			// Call to service layer
			profileRegistration.modifyProfile(jsRegistrationDTO);
			model.put("jobseekerregistration", jsRegistrationForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registrationsuccess";
	}
	
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/jobSeekerUpdatePassword",method = RequestMethod.GET)
	public String updateNewPassword(@Valid ChangePasswordForm form,
			BindingResult result,Map model) {
		
		try {			
			JobSeekerRegistrationDTO jsRegistrationDTO = new  JobSeekerRegistrationDTO();
			MerUserDTO userDTO = transformJobSeekerRegistration.transformChangePasswordFormToMerUserDTO(form);
			jsRegistrationDTO.setMerUserDTO(userDTO);
			// Call to service layer
			profileRegistration.changePassword(jsRegistrationDTO);
			model.put("changePasswordForm", form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registrationsuccess";
	}
	
	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/jobSeekerChangePassword",method = RequestMethod.GET)
	public String jsChangePassword(Map model) {
		
		try {		
			ChangePasswordForm form = new ChangePasswordForm();
			form.setEmailId("sasi@sasi1.com");
			model.put("changePasswordForm", form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jobseekerchangepassword";
	}
	

}
