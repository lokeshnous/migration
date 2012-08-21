package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;
import java.util.Map;

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
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerProfileAttribForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerRegistrationValidation;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

@Controller
@RequestMapping("/employerRegistration")
@SessionAttributes("empRegisterForm")
@Scope("session")
public class EmployerRegistrationController {

	
	@Autowired
	private ProfileRegistration employerRegistration;

	@Autowired
	private TransformEmployerRegistration transformEmployerRegistration;
	
	@Autowired
	private PopulateDropdowns populateDropdownsService;
	
	@Autowired
	EmployerRegistrationValidation registerValidation;

	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/employerregistration",method = RequestMethod.GET)
	public ModelAndView employerregistration() {
		ModelAndView model = new ModelAndView();
		EmployerRegistrationForm form = new EmployerRegistrationForm();
		EmployerProfileDTO registerDTO = (EmployerProfileDTO) employerRegistration.getProfileAttributes();
		List<EmployerProfileAttribForm> listProfAttribForms = 
				transformEmployerRegistration.transformDTOToProfileAttribForm(registerDTO);
		form.setListProfAttribForms(listProfAttribForms);
		model.addObject("listProfAttribForms", listProfAttribForms);
		model.addObject("empRegisterForm", form);
		List<CountryDTO> countryList= populateDropdownsService.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
//		map.put("empRegisterForm", empRegisterForm);
		model.setViewName("employerregistration");
		return model;
	}
	
	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveEmployerProfile", method = RequestMethod.POST)
	public ModelAndView saveEmployerRegistration(
			@Valid EmployerRegistrationForm empRegisterForm, Map map,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		registerValidation.validate(empRegisterForm, result);

		if (result.hasErrors()) {
			model.setViewName("employerregistration");
			return model;
		}

		if (employerRegistration.validateEmail(empRegisterForm.getEmailId())) {
			result.rejectValue("emailId", "NotEmpty",
					"Email Id already Exists!");
			model.setViewName("employerregistration");
			return model;
		}
		EmployerProfileDTO empDTO = new EmployerProfileDTO();
		AddressDTO addDTO = transformEmployerRegistration
				.transformEmpFormToAddressDTO(empRegisterForm);
		CompanyProfileDTO compProfileDTO = transformEmployerRegistration
				.transformEmpFormToCompProfileDTO(empRegisterForm);
		MerUserDTO merUserDTO = transformEmployerRegistration
				.transformEmpFormToMerUserDTO(empRegisterForm);
		empDTO.setAddDTO(addDTO);
		empDTO.setCompProfileDTO(compProfileDTO);
		empDTO.setMerUserDTO(merUserDTO);
		employerRegistration.createNewProfile(empDTO);

		model.setViewName("jobBoardEmployerPostJobs01");
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
	@RequestMapping(value="/changePassword",method = RequestMethod.GET)
	public String jsChangePassword(@Valid EmployerRegistrationForm form, Map model,BindingResult result) {
		
		try {			
			EmployerProfileDTO empDTO = new EmployerProfileDTO();
			MerUserDTO merUserDTO = transformEmployerRegistration.transformEmpFormToMerUserDTO(form);
			empDTO.setMerUserDTO(merUserDTO);
			// Call to service layer
			employerRegistration.changePassword(empDTO);
//			model.put("jobSeekerRegistrationForm", jsRegistrationForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registrationsuccess";
	}

}
