package com.advanceweb.afc.jb.agency.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService;
import com.advanceweb.afc.jb.employer.web.controller.EmployeeAccountForm;
import com.advanceweb.afc.jb.employer.web.controller.EmployerProfileAttribForm;
import com.advanceweb.afc.jb.employer.web.controller.EmployerRegistrationForm;
import com.advanceweb.afc.jb.employer.web.controller.EmployerRegistrationValidation;
//import com.advanceweb.afc.jb.employer.web.controller.EmployerRegistrationValidation;
import com.advanceweb.afc.jb.employer.web.controller.TransformEmployerRegistration;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayService;
import com.advanceweb.afc.jb.pgi.web.controller.BillingAddressForm;
import com.advanceweb.afc.jb.pgi.web.controller.TransformPaymentMethod;
import com.advanceweb.afc.jb.user.ProfileRegistration;

//import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 21st August, 2012
 * 
 */

@Controller
@RequestMapping("/agency")
public class AgencyDashBoardController {

	private static final Logger LOGGER = Logger
			.getLogger("EmployerRegistrationController.class");

	private static final String _FORM_VIEW = "agencyDashboard";

	@Autowired
	private EmloyerRegistartionService empRegService;
	@Autowired
	private TransformEmployerRegistration transformEmpReg;
	@Autowired
	private PaymentGatewayService fetchAdmFacilityConatact;
	@Autowired
	private TransformPaymentMethod transformPaymentMethod;
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private ProfileRegistration employerRegistration;
	@Autowired
	EmployerRegistrationValidation registerValidation;

	@Autowired
	private ProfileRegistration agencyRegistration;

	// @Autowired
	// private ProfileRegistration employerRegistration;

	@RequestMapping("/agencyDashboard")
	public ModelAndView displayDashBoard() {
		return new ModelAndView(_FORM_VIEW);
	}

	/**
	 * This method is called to Account Setting update page and
	 * editAccountSetting method take Bean class binding result from Jsp pages
	 * Seession for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@ResponseBody
	@RequestMapping(value = "/employeeAccountSetting", method = RequestMethod.POST)
	public String editAccountSetting(EmployeeAccountForm employeeAccountForm,
			BindingResult result, HttpSession session) {
		boolean isUpdated = false;
		try {
			int userId = (Integer) session.getAttribute("userId");
			AdmFacilityContactDTO listProfAttribForms = empRegService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);
			if (listProfAttribForms.getCount() > 0) {
				int admfacilityid = listProfAttribForms.getFacilityContactId();
				if (!validateEmailPattern(employeeAccountForm.getEmail())) {
					return MMJBCommonConstants.EMAIL_MESSAGE;
				}  else if (!validatePhonePattern(employeeAccountForm.getPhone())) {
					return MMJBCommonConstants.PHONE_NO;
				} else if (null == employeeAccountForm.getPhone()) {
					return MMJBCommonConstants.PHONE_NULL_NO;
				}else if (employerRegistration
						.validateEmail(employeeAccountForm.getEmail())) {
					// return MMJBCommonConstants.EMAIL_NULL_MESSAGE;
				}
				AccountProfileDTO dto = transformEmpReg
						.transformAccountProfileFormToDto(employeeAccountForm);
				// By passing net suite call
				isUpdated = true;//empRegService.editUser(dto, admfacilityid, userId,
						//MMJBCommonConstants.PRIMARY);
				if (isUpdated) {
					LOGGER.info("This is Account Addresss edite option done successfully");
				} else {
					return MMJBCommonConstants.UPDATE_ERROR;
				}

			}

		} catch (Exception e) {

			LOGGER.info("This is Account Addresss edite option error");
		}
		return "";
	}

	/**
	 * This method is called to Billing Setting update page and
	 * editBillingSetting method take Bean class binding result from Jsp pages
	 * Seession for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@ResponseBody
	@RequestMapping(value = "/employeeBillingSetting", method = RequestMethod.POST)
	public String editBillingSetting(EmployeeAccountForm employeeBillingForm,
			BindingResult result, HttpSession session) {
		boolean isUpdated = false;
		try {
			int userId = (Integer) session.getAttribute("userId");
			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			AdmFacilityContactDTO listProfAttribForms = empRegService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);
			if (!validateEmailPattern(employeeBillingForm.getEmail())) {
				return MMJBCommonConstants.EMAIL_MESSAGE;
			} else if (!validatePhonePattern(employeeBillingForm.getPhone())) {
				return MMJBCommonConstants.PHONE_NO;
			} else if (null == employeeBillingForm.getPhone()) {
				return MMJBCommonConstants.PHONE_NULL_NO;
			}

			if (listProfAttribForms.getCount() > 0) {
				int admfacilityid = listProfAttribForms.getFacilityContactId();
				AccountProfileDTO dto = transformEmpReg
						.transformBillingProfileFormToDto(employeeBillingForm);
				empRegService.editUser(dto, admfacilityid, userId,
						MMJBCommonConstants.BILLING);
				if (isUpdated) {
					LOGGER.info("This is Account Addresss edite option done successfully");
				} else {
					return MMJBCommonConstants.UPDATE_ERROR;
				}

			} else {
				// if (listProfAttribForms.getEmail().toString()
				// .equals(employeeBillingForm.getEmail())) {
				// return MMJBCommonConstants.EMAIL_NULL_MESSAGE;
				// }
				BillingAddressForm billingAddressForm = employeeBillingForm.billingAddressForm;
				AccountBillingDTO billingAddressDTO = transformPaymentMethod
						.transformDataBillingAddreFormToDto(billingAddressForm);
				billingAddressDTO.setFacilityId(facilityId);
				billingAddressDTO.setCompanyName(employeeBillingForm
						.getCompany());
				billingAddressDTO.setEmail(employeeBillingForm.getEmail());
				billingAddressDTO.setPhone(employeeBillingForm.getPhone());
				billingAddressDTO.setCreateDate(new Date());
				fetchAdmFacilityConatact
						.saveDataBillingAddress(billingAddressDTO);
				LOGGER.info("This is Billing Addresss save done successfully");
			}

		} catch (Exception e) {
			LOGGER.info("This is Billing Addresss edite or save error");
		}
		return "";
	}

	/**
	 * This method is called to Account Setting display page call from dash
	 * board of employer
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

			AdmFacilityContactDTO listProfAttribForms = empRegService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);

			employeeAccountForm
					.setFirstName(listProfAttribForms.getFirstName());
			employeeAccountForm.setLastName(listProfAttribForms.getLastName());
			employeeAccountForm
					.setCompany(listProfAttribForms.getCompanyName());
			employeeAccountForm.setStreetAddress(listProfAttribForms
					.getStreet());
			employeeAccountForm.setCityOrTown(listProfAttribForms.getCity());
			employeeAccountForm.setState(listProfAttribForms.getState());
			employeeAccountForm.setCountry(listProfAttribForms.getCountry());
			employeeAccountForm.setEmail(listProfAttribForms.getEmail());
			employeeAccountForm.setZipCode(listProfAttribForms.getZipCode());
			employeeAccountForm.setPhone(listProfAttribForms.getPhone());

			/**
			 * this is for billing pages
			 */
			// int count = 0;
			AdmFacilityContactDTO listBillingForms = empRegService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);
			if ((listBillingForms.getCount() > 0)) {
				/*
				 * count = listBillingForms.getCount();
				 * 
				 * } else {
				 */
				employeeBillingForm
						.getBillingAddressForm()
						.setFnameForBillingAddr(listBillingForms.getFirstName());
				employeeBillingForm.getBillingAddressForm()
						.setLnameForBillingAddr(
								listBillingForms.getLastName());
				employeeBillingForm.setCompany(listBillingForms
						.getCompanyName());
				employeeBillingForm.getBillingAddressForm()
						.setStreetForBillingAddr(listBillingForms.getStreet());
				employeeBillingForm
						.getBillingAddressForm()
						.setCityOrTownForBillingAddr(listBillingForms.getCity());
				employeeBillingForm.setEmail(listBillingForms.getEmail());
				employeeBillingForm
						.getBillingAddressForm()
						.setZipCodeForBillingAddr(listBillingForms.getZipCode());
				employeeBillingForm.setPhone(listBillingForms.getPhone());
				employeeBillingForm.getBillingAddressForm()
						.setStateBillingAddress(listBillingForms.getState());
				employeeBillingForm
						.getBillingAddressForm()
						.setCountryForBillingAddr(listBillingForms.getCountry());
			}
			model.addObject("countryList", countryList);
			model.addObject("stateList", stateList);
			model.addObject("listProfAttribForms", listProfAttribForms);
			model.addObject("count", listBillingForms.getCount());
			model.setViewName("accountSetting");
			model.addObject("employeeAccountForm", employeeAccountForm);
			model.addObject("employeeBillingForm", employeeBillingForm);

		} catch (Exception e) {
			LOGGER.info("Error For Account Setting Link call in controller class");
		}

		return model;
	}

	/**
	 * 
	 * @param emailAddress
	 *            emailAddress.
	 * @return true.
	 */

	private boolean validateEmailPattern(String emailAddress) {
		Pattern pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}

	/**
	 * 
	 * @param phoneNumber
	 *            phoneNumber.
	 * @return true.
	 */

	private boolean validatePhonePattern(String phoneNumber) {
		Pattern pattern = Pattern.compile(MMJBCommonConstants.MOBILE_PATTERN);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	@RequestMapping(value = "/addEmployer", method = RequestMethod.GET)
	public ModelAndView addEmployer(HttpSession session) {
		ModelAndView model = new ModelAndView();
		EmployerRegistrationForm empRegisterForm = new EmployerRegistrationForm();

		EmployerProfileDTO registerDTO = (EmployerProfileDTO) employerRegistration
				.getProfileAttributes();
		List<EmployerProfileAttribForm> listProfAttribForms = transformEmpReg
				.transformDTOToProfileAttribForm(registerDTO, null);
		empRegisterForm.setListProfAttribForms(listProfAttribForms);
		model.addObject("empRegisterForm", empRegisterForm);
		// List<CountryDTO> countryList = populateDropdownsService
		// .getCountryList();
		// List<StateDTO> stateList = populateDropdownsService.getStateList();
		// model.addObject("countryList", countryList);
		// model.addObject("stateList", stateList);
		model.setViewName("agencyAddEmployer");
		return model;
	}

	// @RequestMapping(value = "/manageEmployers", method = RequestMethod.GET)
	// public ModelAndView manageEmployers() {
	// ModelAndView model = new ModelAndView();
	// List<String> employersList = new ArrayList<String>();
	// model.setViewName("agencyManageEmployers");
	// return model;
	//
	// }

	@RequestMapping(value = "/editEmployer", method = RequestMethod.GET)
	public ModelAndView editEmployer(
			@RequestParam("employerName") String employerName) {
		ModelAndView model = new ModelAndView();
		try {
			EmployerRegistrationForm empRegisterForm = new EmployerRegistrationForm();

			List<CountryDTO> countryList = populateDropdownsService
					.getCountryList();

			List<StateDTO> stateList = populateDropdownsService.getStateList();
			EmployerProfileDTO registerDTO = (EmployerProfileDTO) employerRegistration
					.getProfileAttributes();
			List<EmployerProfileAttribForm> listProfAttribForms = transformEmpReg
					.transformDTOToProfileAttribForm(registerDTO, null);
			empRegisterForm.setListProfAttribForms(listProfAttribForms);

			Map<String, Object> profAttribFormsMap = populateDropdownsService
					.getEmployerDetails(employerName);
			model.setViewName("agencyEditEmployer");

			if (profAttribFormsMap != null && !profAttribFormsMap.isEmpty()) {
				empRegisterForm
						.setFirstName(profAttribFormsMap.get("name") != null ? profAttribFormsMap
								.get("name").toString() : "");
				empRegisterForm
						.setStreet(profAttribFormsMap.get("street") != null ? profAttribFormsMap
								.get("street").toString() : "");

				empRegisterForm
						.setZipCode(profAttribFormsMap.get("postcode") != null ? profAttribFormsMap
								.get("postcode").toString() : "");

				empRegisterForm
						.setState(profAttribFormsMap.get("state") != null ? profAttribFormsMap
								.get("state").toString() : "");

				empRegisterForm
						.setCountry(profAttribFormsMap.get("country") != null ? profAttribFormsMap
								.get("country").toString() : "");
				empRegisterForm
						.setCity(profAttribFormsMap.get("city") != null ? profAttribFormsMap
								.get("city").toString() : "");
				empRegisterForm.setFacilityId((Integer) profAttribFormsMap
						.get("facilityId"));
			}
			empRegisterForm.setListProfAttribForms(listProfAttribForms);

			model.addObject("countryList", countryList);
			model.addObject("stateList", stateList);
			model.addObject("listProfAttribForms", profAttribFormsMap);
			model.addObject("empRegisterForm", empRegisterForm);

		} catch (Exception e) {
			LOGGER.info("Error is occured in controllr");
			LOGGER.error("ERROR"+e);
		}

		return model;
	}

	@RequestMapping(value = "/getEmployerNamesList", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	List<String> getEmployerNamesList(@RequestParam("term") String query) {

		return populateDropdownsService.getEmployerNamesList(query);

	}

	@RequestMapping(value = "/getEmployerDetails")
	@ResponseBody
	public Map<String, Object> getEmployerDetails(
			@RequestParam("name") String employerName) {
		return populateDropdownsService.getEmployerDetails(employerName);

	}

	@ResponseBody
	@RequestMapping(value = "/addEmployerDetails", method = RequestMethod.POST)
	public ModelAndView addEmployerDetails(
			EmployerRegistrationForm employerRegistrationForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {

			AccountProfileDTO dto = transformEmpReg
					.transformEmployerFormToDto(employerRegistrationForm);
			int agencyUserId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			agencyRegistration.addEmployer(dto, facilityId, agencyUserId);

			model.setViewName("employerDashboard");

			return model;
		} catch (Exception e) {
			model.setViewName("employerDashboard");
			return model;
		}
	}

	@RequestMapping(value = "/manageEmployers", method = RequestMethod.GET)
	public ModelAndView manageEmployers(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {

			int agencyUserId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			List<AdmFacility> assocEmplyrsNames = agencyRegistration
					.getAssocEmployerNames(agencyUserId, facilityId);

			model.addObject("assocEmplyrsNames", assocEmplyrsNames);
			model.setViewName("agencyManageEmployers");
			// model.addObject("employerRegForm", employerRegForm);

		} catch (Exception e) {
			LOGGER.info("Error is occured in controllr");
			LOGGER.error("ERROR"+e);
		}

		return model;

	}

	@RequestMapping(value = "/saveEmployerDetails", method = RequestMethod.GET)
	public ModelAndView saveEmployerDetails(
			EmployerRegistrationForm employerRegistrationForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		try {

			AccountProfileDTO dto = transformEmpReg
					.transformEmployerFormToDto(employerRegistrationForm);

			agencyRegistration.saveEmployerDetails(dto);

			model.setViewName("employerDashboard");

			return model;
		} catch (Exception e) {
			model.setViewName("employerDashboard");
			return model;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/deleteAssocEmployer", method = RequestMethod.GET)
	public ModelAndView deleteAssocEmployer(
			@RequestParam("facilityId") String facilityId, HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			int userId = (Integer) session.getAttribute("userId");
			agencyRegistration.deleteAssocEmployer(facilityId, userId);

			model.setViewName("employerDashboard");

			return model;
		} catch (Exception e) {
			model.setViewName("employerDashboard");
			return model;
		}
	}

	@RequestMapping(value = "/getAssocEmployersByState", method = RequestMethod.GET)
	public ModelAndView getAssocEmployersByState(HttpSession session) {
		ModelAndView model = new ModelAndView();
		int agencyUserId = (Integer) session.getAttribute("userId");
		Map<String, List<AdmFacility>> emplyrsByState = new HashMap<String, List<AdmFacility>>();
		Set<String> stateList = new HashSet<String>();

		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		List<AdmFacility> assocEmplyrsNames = agencyRegistration
				.getAssocEmployerNames(agencyUserId, facilityId);
		for (AdmFacility assocEmplyr : assocEmplyrsNames) {
			String state = assocEmplyr.getState();
			if (stateList.add(state)) {
				List<AdmFacility> emplyrs = new ArrayList<AdmFacility>();
				emplyrs.add(assocEmplyr);
				emplyrsByState.put(state, emplyrs);
			} else {
				emplyrsByState.get(state).add(assocEmplyr);
			}
		}

		model.addObject("emplyrsByState", emplyrsByState);
		model.setViewName("agencyDashboard");
		return model;

	}

}
