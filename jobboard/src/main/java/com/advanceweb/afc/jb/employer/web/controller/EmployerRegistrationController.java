package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.pgi.service.FetchAdmFacilityConatact;
import com.advanceweb.afc.jb.pgi.web.controller.BillingAddressForm;
import com.advanceweb.afc.jb.pgi.web.controller.TransformPaymentMethod;
import com.advanceweb.afc.jb.user.ProfileRegistration;

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
	private ProfileRegistration profileRegistration;

	@Autowired
	private TransformEmployerRegistration transformEmpReg;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private FetchAdmFacilityConatact fetchAdmFacilityConatact;

	@Autowired
	private EmployerRegistrationValidation registerValidation;

	@Autowired
	protected AuthenticationManager customAuthenticationManager;

	@Autowired
	private EmloyerRegistartionService empRegService;

	// @Autowired
	// private AdmManagePermission admManagePermission;

	@Autowired
	private TransformPaymentMethod transformPaymentMethod;

	@Value("${jobseekerRegPhoneMsg}")
	private String jobseekerRegPhoneMsg;

	@Autowired
	private LoginService loginService;

	private final static String EMPLOYERREG = "employerregistration";

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

		EmployerProfileDTO registerDTO = (EmployerProfileDTO) profileRegistration
				.getProfileAttributes();
		List<EmployerProfileAttribForm> listProfAttribForms = transformEmpReg
				.transformDTOToProfileAttribForm(registerDTO);
		empRegisterForm.setListProfAttribForms(listProfAttribForms);
		model.addObject("empRegisterForm", empRegisterForm);
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		// map.put("empRegisterForm", empRegisterForm);
		model.setViewName(EMPLOYERREG);
		return model;
	}

	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/saveEmployerProfile", method = RequestMethod.POST)
	public ModelAndView saveEmployerRegistration(
			@ModelAttribute("empRegisterForm") EmployerRegistrationForm empRegForm,
			HttpServletRequest request, Map map, HttpSession session,
			BindingResult result) {
		ModelAndView model = new ModelAndView();

		if (null != empRegForm.getListProfAttribForms()) {
			model.setViewName(EMPLOYERREG);
			if (!validateEmpRegForm(empRegForm, model, result)) {
				return model;
			}
		}
		EmployerProfileDTO empDTO = new EmployerProfileDTO();
		MerUserDTO userDTO = transformEmpReg.createUserDTO(empRegForm);
		List<MerProfileAttribDTO> attribLists = transformEmpReg
				.transformProfileAttribFormToDTO(empRegForm
						.getListProfAttribForms());
		empDTO.setAttribList(attribLists);
		empDTO.setMerUserDTO(userDTO);
		userDTO = profileRegistration.createNewProfile(empDTO);

		model.addObject("empRegisterForm", empRegForm);
		session.setAttribute(MMJBCommonConstants.USER_NAME,
				userDTO.getFirstName() + " " + userDTO.getLastName());
		session.setAttribute(MMJBCommonConstants.USER_ID, userDTO.getUserId());
		session.setAttribute(MMJBCommonConstants.USER_EMAIL,
				userDTO.getEmailId());
		EmployerInfoDTO infoDTO = loginService.facilityDetails(userDTO
				.getUserId());
		session.setAttribute(MMJBCommonConstants.FACILITY_ID,
				infoDTO.getFacilityId());
		model.setViewName("jobBoardEmployerPostJobs01");
		authenticateUserAndSetSession(userDTO, request);

		return model;
	}

	/**
	 * validation for EmployerRegistration Form
	 * 
	 * @param empRegisterForm
	 * @param model
	 * @param result
	 * @return
	 */
	public boolean validateEmpRegForm(EmployerRegistrationForm empRegForm,
			ModelAndView model, BindingResult result) {
		boolean status = true;
		for (EmployerProfileAttribForm form : empRegForm
				.getListProfAttribForms()) {

			// Checking validation for input text box
			if (form.getbRequired() != 0
					&& StringUtils.isEmpty(form.getStrLabelValue())
					&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(form
							.getStrLabelName())) {
				model.addObject("message", "Please fill the required fields");
				return false;
			}

			// Checking validation for dropdowns & checkboxes etc
			if (form.getbRequired() != 0
					&& MMJBCommonConstants.ZERO.equals(form.getStrLabelValue())
					&& (MMJBCommonConstants.DROP_DOWN.equals(form
							.getStrAttribType()) || MMJBCommonConstants.CHECK_BOX
							.equals(form.getStrAttribType()))) {
				model.addObject("message", "Please fill the required fields");
				return false;
			}
			// validation mobile number
			if (MMJBCommonConstants.PRIMARY_PHONE
					.equals(form.getStrLabelName())
					&& !StringUtils.isEmpty(form.getStrLabelValue())
					&& !registerValidation.validateMobileNumberPattern(form
							.getStrLabelValue())) {
				model.addObject("message", jobseekerRegPhoneMsg);
				return false;
			} else if (MMJBCommonConstants.SECONDARY_PHONE.equals(form
					.getStrLabelName())
					&& !StringUtils.isEmpty(form.getStrLabelValue())
					&& !registerValidation.validateMobileNumberPattern(form
							.getStrLabelValue())) {
				model.addObject("message", jobseekerRegPhoneMsg);
				return false;
			}
		}
		registerValidation.validate(empRegForm, result);
		if (profileRegistration.validateEmail(empRegForm.getEmailId())) {
			result.rejectValue("emailId", "NotEmpty",
					"Email Id already Exists!");
			// model.setViewName(employerReg);
			return false;
		}

		if (result.hasErrors()) {
			// model.setViewName(employerReg);
			return false;
		}

		return status;
	}

	/**
	 * @param user
	 * @param request
	 */
	private void authenticateUserAndSetSession(MerUserDTO user,
			HttpServletRequest request) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new GrantedAuthorityImpl(
				MMJBCommonConstants.ROLE_FACILITY_ADMIN));
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
			MerUserDTO merUserDTO = transformEmpReg
					.transformEmpFormToMerUserDTO(form);
			empDTO.setMerUserDTO(merUserDTO);
			// Call to service layer
			profileRegistration.changePassword(empDTO);
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
		List<AdmFacilityContact> listProfAttribForms = empRegService
				.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);
		if (null != listProfAttribForms && listProfAttribForms.size() != 0) {
			int admfacilityid = listProfAttribForms.get(0)
					.getFacilityContactId();

			AccountProfileDTO dto = transformEmpReg
					.transformAccountProfileFormToDto(employeeAccountForm);

			if (!registerValidation
					.accountValidate(employeeAccountForm, result)) {
				result.rejectValue("email", "NotEmpty",
						"Email Id already Exists!");
				model.setViewName("accountSetting");
				return model;
			}

			empRegService.editEmployeeAccount(dto, admfacilityid,userId,MMJBCommonConstants.PRIMARY);
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
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		List<AdmFacilityContact> listProfAttribForms = empRegService
				.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);

		if (null != listProfAttribForms && listProfAttribForms.size() != 0) {
			int admfacilityid = listProfAttribForms.get(0)
					.getFacilityContactId();
			AccountProfileDTO dto = transformEmpReg
					.transformBillingProfileFormToDto(employeeBillingForm);
			empRegService.editEmployeeAccount(dto, admfacilityid,userId,MMJBCommonConstants.BILLING);

		} else {

			BillingAddressForm billingAddressForm = employeeBillingForm.billingAddressForm;
			AccountBillingDTO billingAddressDTO = transformPaymentMethod
					.transformDataBillingAddreFormToDto(billingAddressForm);
			billingAddressDTO.setFacilityId(facilityId);
			billingAddressDTO.setCompanyName(employeeBillingForm.getCompany());
			billingAddressDTO.setEmail(employeeBillingForm.getEmail());
			billingAddressDTO.setPhone(employeeBillingForm.getPhone());
			billingAddressDTO.setCreateDate(new Date());
			fetchAdmFacilityConatact.saveDataBillingAddress(billingAddressDTO);

			// model.setViewName("employerDashboard");
			// return model;
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
			employeeBillingForm.setBillingAddressForm(new BillingAddressForm());

			int userId = (Integer) session.getAttribute("userId");

			List<CountryDTO> countryList = populateDropdownsService
					.getCountryList();

			List<StateDTO> stateList = populateDropdownsService.getStateList();

			List<AdmFacilityContact> listProfAttribForms = empRegService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);

			employeeAccountForm.setFirstName(listProfAttribForms.get(0)
					.getFirstName());
			employeeAccountForm.setLastName(listProfAttribForms.get(0)
					.getLastName());
			employeeAccountForm.setCompany(listProfAttribForms.get(0)
					.getCompany());
			employeeAccountForm.setStreetAddress(listProfAttribForms.get(0)
					.getStreet());
			employeeAccountForm.setCityOrTown(listProfAttribForms.get(0)
					.getCity());
			employeeAccountForm.setState(listProfAttribForms.get(0).getState());
			employeeAccountForm.setCountry(listProfAttribForms.get(0)
					.getCountry());
			employeeAccountForm.setEmail(listProfAttribForms.get(0).getEmail());
			employeeAccountForm.setZipCode(listProfAttribForms.get(0)
					.getPostcode());
			employeeAccountForm.setPhone(listProfAttribForms.get(0).getPhone());

			/**
			 * this is for billing pages
			 */
			int count = 0;
			List<AdmFacilityContact> listBillingForms = empRegService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);
			if ((listBillingForms.size() <= 0) || ("".equals(listBillingForms))) {
				count = listBillingForms.size();

			} else {
				employeeBillingForm.getBillingAddressForm()
						.setFnameForBillingAddr(
								listBillingForms.get(0).getFirstName());
				employeeBillingForm.getBillingAddressForm()
						.setLnameForBillingAddr(
								listProfAttribForms.get(0).getLastName());
				employeeBillingForm.setCompany(listBillingForms.get(0)
						.getCompany());
				employeeBillingForm.getBillingAddressForm()
						.setStreetForBillingAddr(
								listBillingForms.get(0).getStreet());
				employeeBillingForm.getBillingAddressForm()
						.setCityOrTownForBillingAddr(
								listBillingForms.get(0).getCity());
				employeeBillingForm
						.setEmail(listBillingForms.get(0).getEmail());
				employeeBillingForm.getBillingAddressForm()
						.setZipCodeForBillingAddr(
								listBillingForms.get(0).getPostcode());
				employeeBillingForm
						.setPhone(listBillingForms.get(0).getPhone());
				employeeBillingForm.getBillingAddressForm()
						.setStateBillingAddress(
								listBillingForms.get(0).getState());
				employeeBillingForm.getBillingAddressForm()
						.setCountryForBillingAddr(
								listBillingForms.get(0).getCountry());
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
