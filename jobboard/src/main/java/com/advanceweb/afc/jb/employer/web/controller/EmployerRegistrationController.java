package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.servlet.ModelAndView;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerProfileAttribForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerRegistrationValidation;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.pgi.service.FetchAdmFacilityConatact;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import org.apache.log4j.Logger;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;


/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

@Controller
@RequestMapping("/employerRegistration")


//@SessionAttributes("employeeAccountForm")

@SessionAttributes("empRegisterForm")
@Scope("session")

public class EmployerRegistrationController {

	private static final Logger LOGGER = Logger
			.getLogger("EmployerRegistrationController.class");
	@Autowired
	@Transient
	private ProfileRegistration employerRegistration;

	@Autowired
	@Transient
	private TransformEmployerRegistration transformEmployerRegistration;
	
	@Autowired
	@Transient
	private PopulateDropdowns populateDropdownsService;

	
	@Autowired
	@Transient
	FetchAdmFacilityConatact fetchAdmFacilityConatact;
	

	
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
	
	/**
	 * This method is called to Account Setting update page
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@ResponseBody
	@RequestMapping(value = "/employeeAccountSetting", method = RequestMethod.GET)
	public ModelAndView editAccountSetting(
			EmployeeAccountForm employeeAccountForm, BindingResult result,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = (Integer) session.getAttribute("userId");
		List<AdmFacilityContact> listProfAttribForms = employerRegistration
				.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);
		if (null != listProfAttribForms && listProfAttribForms.size() != 0) {
			int admfacilityid = listProfAttribForms.get(0)
					.getFacilityContactId();

			AccountProfileDTO dto = transformEmployerRegistration
					.transformAccountProfileFormToDto(employeeAccountForm);
			employerRegistration.editEmployeeAccount(dto, admfacilityid);
		} else {
			model.setViewName("employerDashboard");
			return model;
		}

		model.setViewName("employerDashboard");
		return model;
	}

	/**
	 * This method is called to Account Setting update page
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@ResponseBody
	@RequestMapping(value = "/employeeBillingSetting", method = RequestMethod.GET)
	public ModelAndView editBillingSetting(
			EmployeeAccountForm employeeBillingForm, BindingResult result,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = (Integer) session.getAttribute("userId");
		List<AdmFacilityContact> listProfAttribForms = employerRegistration
				.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);

		if (null != listProfAttribForms && listProfAttribForms.size() != 0) {
			int admfacilityid = listProfAttribForms.get(0)
					.getFacilityContactId();
			AccountProfileDTO dto = transformEmployerRegistration
					.transformAccountProfileFormToDto(employeeBillingForm);
			employerRegistration.editEmployeeAccount(dto, admfacilityid);

		} else {
			model.setViewName("employerDashboard");
			return model;
		}

		model.setViewName("employerDashboard");
		return model;

	}

	/**
	 * This method is called to Account Setting display page
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@RequestMapping(value = "/viewEmpAccountProfile", method = RequestMethod.GET)
	public ModelAndView viewEmpAccountProfileSettings(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			EmployeeAccountForm employeeAccountForm = new EmployeeAccountForm();
			EmployeeAccountForm employeeBillingForm = new EmployeeAccountForm();
			

			int userId = (Integer) session.getAttribute("userId");
			

			List<CountryDTO> countryList = populateDropdownsService
					.getCountryList();

			List<StateDTO> stateList = populateDropdownsService.getStateList();

			List<AdmFacilityContact> listProfAttribForms = employerRegistration
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);

			employeeAccountForm.setFirstName(listProfAttribForms.get(0)
					.getFirstName());
			employeeAccountForm.setCompany(listProfAttribForms.get(0)
					.getCompany());
			employeeAccountForm.setStreetAddress(listProfAttribForms.get(0)
					.getStreet());
			employeeAccountForm.setCityOrTown(listProfAttribForms.get(0)
					.getCity());
			employeeAccountForm.setEmail(listProfAttribForms.get(0).getEmail());
			employeeAccountForm.setZipCode(listProfAttribForms.get(0)
					.getPostcode());
			employeeAccountForm.setPhone(listProfAttribForms.get(0).getPhone());

			/**
			 * this is for billing pages
			 */

			List<AdmFacilityContact> listBillingForms = employerRegistration
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);
			employeeBillingForm.setFirstName(listBillingForms.get(0)
					.getFirstName());
			employeeBillingForm
					.setCompany(listBillingForms.get(0).getCompany());
			employeeBillingForm.setStreetAddress(listBillingForms.get(0)
					.getStreet());
			employeeBillingForm
					.setCityOrTown(listBillingForms.get(0).getCity());
			employeeBillingForm.setEmail(listBillingForms.get(0).getEmail());
			employeeBillingForm.setZipCode(listBillingForms.get(0)
					.getPostcode());
			employeeBillingForm.setPhone(listBillingForms.get(0).getPhone());

			model.addObject("countryList", countryList);
			model.addObject("stateList", stateList);
			model.addObject("listProfAttribForms", listProfAttribForms);
			model.setViewName("accountSetting");
			model.addObject("employeeAccountForm", employeeAccountForm);
			model.addObject("employeeBillingForm", employeeBillingForm);

		} catch (Exception e) {
			LOGGER.info("Error For controller");
		}

		return model;
	}
	
}
