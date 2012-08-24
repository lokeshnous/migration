package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
//import com.advanceweb.afc.jb.common.AddressDTO;
//import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.pgi.service.FetchAdmFacilityConatact;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July 2012
 */

@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/employerRegistration")
@SessionAttributes("empRegisterForm")
@Scope("session")
public class EmployerRegistrationController {

	private static final Logger LOGGER = Logger
			.getLogger("EmployerRegistrationController.class");
	@Autowired
	
	private ProfileRegistration employerRegistration;

	@Autowired
	
	private TransformEmployerRegistration transformEmployerRegistration;

	@Autowired
	
	private PopulateDropdowns populateDropdownsService;


	
	@Autowired
	
	FetchAdmFacilityConatact fetchAdmFacilityConatact;
	

	
	@Autowired
	EmployerRegistrationValidation registerValidation;

	@Autowired
	protected AuthenticationManager customAuthenticationManager;
	@Autowired
	
	private EmloyerRegistartionService emloyerRegistartionService;

	@Value("${jobseekerRegPhoneMsg}")
	private String jobseekerRegPhoneMsg;


	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employerregistration", method = RequestMethod.GET)
	public ModelAndView employerregistration() {
		ModelAndView model = new ModelAndView();

		EmployerRegistrationForm empRegisterForm = new EmployerRegistrationForm();

		EmployerProfileDTO registerDTO = (EmployerProfileDTO) employerRegistration
				.getProfileAttributes();
		List<EmployerProfileAttribForm> listProfAttribForms = transformEmployerRegistration
				.transformDTOToProfileAttribForm(registerDTO);
		empRegisterForm.setListProfAttribForms(listProfAttribForms);
		model.addObject("empRegisterForm", empRegisterForm);
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		// map.put("empRegisterForm", empRegisterForm);
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
			@ModelAttribute("empRegisterForm") EmployerRegistrationForm empRegisterForm,
			HttpServletRequest request, Map map, BindingResult result) {
		ModelAndView model = new ModelAndView();
	

		if (null != empRegisterForm.getListProfAttribForms()) {
			model.setViewName("employerregistration");
			for (EmployerProfileAttribForm form : empRegisterForm
					.getListProfAttribForms()) {

				// Checking validation for input text box
				if (form.getbRequired() != 0
						&& StringUtils.isEmpty(form.getStrLabelValue())
						&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(form
								.getStrLabelName())) {
					model.addObject("message",
							"Please fill the Required fields");
					return model;
				}

				// Checking validation for dropdowns & checkboxes etc
				if (form.getbRequired() != 0
						&& MMJBCommonConstants.ZERO.equals(form
								.getStrLabelValue())
						&& (MMJBCommonConstants.DROP_DOWN.equals(form
								.getStrAttribType()) || MMJBCommonConstants.CHECK_BOX
								.equals(form.getStrAttribType()))) {
					model.addObject("message",
							"Please fill the Required fields");
					return model;
				}
				// validation mobile number
				if (MMJBCommonConstants.PRIMARY_PHONE.equals(form
						.getStrLabelName())
						&& !StringUtils.isEmpty(form.getStrLabelValue())
						&& !registerValidation.validateMobileNumberPattern(form
								.getStrLabelValue())) {
					model.addObject("message", jobseekerRegPhoneMsg);
					return model;
				}
				if (MMJBCommonConstants.SECONDARY_PHONE.equals(form
						.getStrLabelName())
						&& !StringUtils.isEmpty(form.getStrLabelValue())
						&& !registerValidation.validateMobileNumberPattern(form
								.getStrLabelValue())) {
					model.addObject("message", jobseekerRegPhoneMsg);
					return model;
				}
			}
		}
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
		MerUserDTO userDTO = transformEmployerRegistration
				.createUserDTO(empRegisterForm);
		List<MerProfileAttribDTO> attribLists = transformEmployerRegistration
				.transformProfileAttribFormToDTO(empRegisterForm
						.getListProfAttribForms());
		empDTO.setAttribList(attribLists);
		empDTO.setMerUserDTO(userDTO);
		employerRegistration.createNewProfile(empDTO);

		model.setViewName("jobBoardEmployerPostJobs01");
		model.addObject("empRegisterForm", empRegisterForm);
		authenticateUserAndSetSession(userDTO, request);

		return model;
	}

	/**
	 * @param user
	 * @param request
	 */
	@SuppressWarnings("deprecation")
	private void authenticateUserAndSetSession(MerUserDTO user,
			HttpServletRequest request) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new GrantedAuthorityImpl(MMJBCommonConstants.ROLE_FACILITY_ADMIN));
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getEmailId(), user.getPassword(), authList);

		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = customAuthenticationManager
				.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String jsChangePassword(@Valid EmployerRegistrationForm form,
			Map model, BindingResult result) {

		try {
			EmployerProfileDTO empDTO = new EmployerProfileDTO();
			MerUserDTO merUserDTO = transformEmployerRegistration
					.transformEmpFormToMerUserDTO(form);
			empDTO.setMerUserDTO(merUserDTO);
			// Call to service layer
			employerRegistration.changePassword(empDTO);
			// model.put("jobSeekerRegistrationForm", jsRegistrationForm);
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
		List<AdmFacilityContact> listProfAttribForms = emloyerRegistartionService
				.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);
		if (null != listProfAttribForms && listProfAttribForms.size() != 0) {
			int admfacilityid = listProfAttribForms.get(0)
					.getFacilityContactId();

			AccountProfileDTO dto = transformEmployerRegistration
					.transformAccountProfileFormToDto(employeeAccountForm);
			emloyerRegistartionService.editEmployeeAccount(dto, admfacilityid);
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
		List<AdmFacilityContact> listProfAttribForms = emloyerRegistartionService
				.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);

		if (null != listProfAttribForms && listProfAttribForms.size() != 0) {
			int admfacilityid = listProfAttribForms.get(0)
					.getFacilityContactId();
			AccountProfileDTO dto = transformEmployerRegistration
					.transformAccountProfileFormToDto(employeeBillingForm);
			emloyerRegistartionService.editEmployeeAccount(dto, admfacilityid);

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

			List<AdmFacilityContact> listProfAttribForms = emloyerRegistartionService
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
			int count=0;
			List<AdmFacilityContact> listBillingForms = emloyerRegistartionService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);
			if((listBillingForms.size()<=0)||("".equals(listBillingForms)))
			{
				count=listBillingForms.size();
				
			}
			else
			{
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
			}
			model.addObject("countryList", countryList);
			model.addObject("stateList", stateList);
			model.addObject("listProfAttribForms", listProfAttribForms);
			model.addObject("count", count);
			model.setViewName("accountSetting");
			model.addObject("employeeAccountForm", employeeAccountForm);
			model.addObject("employeeBillingForm", employeeBillingForm);
			

		} catch (Exception e) {
			LOGGER.info("Error For controller");
		}

		return model;
	}
	
}
