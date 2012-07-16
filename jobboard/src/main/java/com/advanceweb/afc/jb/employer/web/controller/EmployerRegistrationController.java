package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
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
public class EmployerRegistrationController {

	
	@Autowired
	private ProfileRegistration employerRegistration;

	@Autowired
	private TransformEmployerRegistration transformEmployerRegistration;
	
	@Autowired
	private PopulateDropdowns populateDropdownsService;


	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createEmployerProfile",method = RequestMethod.GET)
	public ModelAndView createEmployerRegistration(Map model) {
		
		EmployerRegistrationForm form = new EmployerRegistrationForm();
		List<CountryDTO> countryList= populateDropdownsService.getCountryList();
		model.put("employerRegistrationForm", form);		
		
		return new ModelAndView("employerregistration");
	}
	
	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveEmployerProfile",method = RequestMethod.POST)
	public ModelAndView saveEmployerRegistration(@Valid EmployerRegistrationForm form, Map model) {	
		EmployerProfileDTO empDTO = new EmployerProfileDTO();
		AddressDTO addDTO = transformEmployerRegistration.transformEmpFormToAddressDTO(form);
		CompanyProfileDTO compProfileDTO = transformEmployerRegistration.transformEmpFormToCompProfileDTO(form);
		MerUserDTO merUserDTO = transformEmployerRegistration.transformEmpFormToMerUserDTO(form);
		empDTO.setAddDTO(addDTO);
		empDTO.setComapnyProfileDTO(compProfileDTO);
		empDTO.setMerUserDTO(merUserDTO);
		employerRegistration.createNewProfile(empDTO);
		return new ModelAndView("jobseekerregistration");
	}

}
