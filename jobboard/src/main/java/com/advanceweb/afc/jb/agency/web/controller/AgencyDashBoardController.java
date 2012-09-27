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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.agency.service.ImpersonateAgencyService;
import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService;
import com.advanceweb.afc.jb.employer.web.controller.EmployeeAccountForm;
import com.advanceweb.afc.jb.employer.web.controller.EmployerProfileAttribForm;
import com.advanceweb.afc.jb.employer.web.controller.EmployerRegistrationForm;
import com.advanceweb.afc.jb.employer.web.controller.TransformEmployerRegistration;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.login.service.LoginService;
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
			.getLogger(AgencyDashBoardController.class);
	@Autowired
	private EmloyerRegistartionService empRegService;
	@Autowired
	protected AuthenticationManager customAuthenticationManager;
	@Autowired
	private LoginService loginService;
	@Autowired
	private ImpersonateAgencyService impersonateAgencyService;
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

	@Value("${account_first_name}")
	private String accountFirstName;
	@Value("${account_last_name}")
	private String accountLastName;
	@Value("${account_company_name}")
	private String accountCompanyName;
	@Value("${account_zip_code}")
	private String accountZipCode;
	@Value("${account_state}")
	private String accountState;
	@Value("${account_country}")
	private String accountCountry;
	@Value("${account_city}")
	private String accountCity;
	@Value("${js.email.blank}")
	private String accountEmail;
	@Value("${account_Street}")
	private String accountStreet;

	/*
	 * @Autowired EmployerRegistrationValidation registerValidation;
	 */

	@RequestMapping("/agencyDashboard")
	public ModelAndView displayDashBoard(HttpSession session) {
		ModelAndView model = new ModelAndView();
		int agencyUserId = (Integer) session.getAttribute("userId");
		Map<String, List<FacilityDTO>> emplyrsByState = new HashMap<String, List<FacilityDTO>>();
		Set<String> stateList = new HashSet<String>();
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		List<FacilityDTO> assocEmplyrsNames = impersonateAgencyService
				.getAssocEmployerNames(agencyUserId, facilityId);
		for (FacilityDTO assocEmplyr : assocEmplyrsNames) {
			String state = assocEmplyr.getState();
			if (stateList.add(state)) {
				List<FacilityDTO> emplyrs = new ArrayList<FacilityDTO>();
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
				} else if (!validatePhonePattern(employeeAccountForm.getPhone())) {
					return MMJBCommonConstants.PHONE_NO;
				} else if ((null == employeeAccountForm.getEmail())
						|| ("".equals(employeeAccountForm.getEmail()))) {
					return accountEmail;
				} else if ((null == employeeAccountForm.getPhone())
						|| ("".equals(employeeAccountForm.getPhone()))) {
					return MMJBCommonConstants.PHONE_NULL_NO;
				} else if ((null == employeeAccountForm.getFirstName())
						|| ("".equals(employeeAccountForm.getFirstName()))) {
					return accountFirstName;
				} else if ((null == employeeAccountForm.getLastName())
						|| ("".equals(employeeAccountForm.getLastName()))) {
					return accountLastName;
				} else if ((null == employeeAccountForm.getZipCode())
						|| ("".equals(employeeAccountForm.getZipCode()))) {
					return accountZipCode;
				} else if ((null == employeeAccountForm.getCityOrTown())
						|| ("".equals(employeeAccountForm.getCityOrTown()))) {
					return accountCity;
				} else if ((null == employeeAccountForm.getCompany())
						|| ("".equals(employeeAccountForm.getCompany()))) {
					return accountCompanyName;
				} else if ((null == employeeAccountForm.getCountry())
						|| ("".equals(employeeAccountForm.getCountry()))) {
					return accountCountry;
				} else if ((null == employeeAccountForm.getState())
						|| ("".equals(employeeAccountForm.getState()))) {
					return accountState;
				} else if ((null == employeeAccountForm.getStreetAddress())
						|| ("".equals(employeeAccountForm.getStreetAddress()))) {
					return accountStreet;
				} else if (employerRegistration
						.validateEmail(employeeAccountForm.getEmail())) {
					// return MMJBCommonConstants.EMAIL_NULL_MESSAGE;
				}
				AccountProfileDTO dto = transformEmpReg
						.transformAccountProfileFormToDto(employeeAccountForm);

				isUpdated = empRegService.editUser(dto, admfacilityid, userId,
						MMJBCommonConstants.PRIMARY);
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
			} else if ((null == employeeBillingForm.getEmail())
					|| ("".equals(employeeBillingForm.getEmail()))) {
				return accountEmail;
			} else if ((null == employeeBillingForm.getPhone())
					|| ("".equals(employeeBillingForm.getPhone()))) {
				return MMJBCommonConstants.PHONE_NULL_NO;
			} else if ((null == employeeBillingForm.getBillingAddressForm()
					.getFnameForBillingAddr())
					|| ("".equals(employeeBillingForm.getBillingAddressForm()
							.getFnameForBillingAddr()))) {
				return accountFirstName;
			} else if ((null == employeeBillingForm.getBillingAddressForm()
					.getLnameForBillingAddr())
					|| ("".equals(employeeBillingForm.getBillingAddressForm()
							.getLnameForBillingAddr()))) {
				return accountLastName;
			} else if ((null == employeeBillingForm.getBillingAddressForm()
					.getZipCodeForBillingAddr())
					|| ("".equals(employeeBillingForm.getBillingAddressForm()
							.getZipCodeForBillingAddr()))) {
				return accountZipCode;
			} else if ((null == employeeBillingForm.getBillingAddressForm()
					.getCityOrTownForBillingAddr())
					|| ("".equals(employeeBillingForm.getBillingAddressForm()
							.getCityOrTownForBillingAddr()))) {
				return accountCity;
			} else if ((null == employeeBillingForm.getCompany())
					|| ("".equals(employeeBillingForm.getCompany()))) {
				return accountCompanyName;
			} else if ((null == employeeBillingForm.getBillingAddressForm()
					.getCountryForBillingAddr())
					|| ("".equals(employeeBillingForm.getBillingAddressForm()
							.getCountryForBillingAddr()))) {
				return accountCountry;
			} else if ((null == employeeBillingForm.getBillingAddressForm()
					.getStateBillingAddress())
					|| ("".equals(employeeBillingForm.getBillingAddressForm()
							.getStateBillingAddress()))) {
				return accountState;
			} else if ((null == employeeBillingForm.getBillingAddressForm()
					.getStreetForBillingAddr())
					|| ("".equals(employeeBillingForm.getBillingAddressForm()
							.getStreetForBillingAddr()))) {
				return accountStreet;
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
						.setLnameForBillingAddr(listBillingForms.getLastName());
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
	 * @author kartikm
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
	 * @author kartikm
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
		model.setViewName("agencyAddEmployer");
		return model;
	}

	@RequestMapping(value = "/editEmployer", method = RequestMethod.GET)
	public ModelAndView editEmployer(@RequestParam("facilityId") int facilityId) {
		ModelAndView model = new ModelAndView();
		try {
			EmployerRegistrationForm empRegisterForm = new EmployerRegistrationForm();
			EmployerProfileDTO registerDTO = (EmployerProfileDTO) employerRegistration
					.getProfileAttributes();
			List<EmployerProfileAttribForm> listProfAttribForms = transformEmpReg
					.transformDTOToProfileAttribForm(registerDTO, null);
			empRegisterForm.setListProfAttribForms(listProfAttribForms);

			Map<String, Object> profAttribFormsMap = impersonateAgencyService
					.getEmployerDetails(facilityId);
			model.setViewName("agencyEditEmployer");
			if (profAttribFormsMap != null && !profAttribFormsMap.isEmpty()) {
				empRegisterForm
						.setFirstName(profAttribFormsMap.get("name") != null ? profAttribFormsMap
								.get("name").toString() : "");
				empRegisterForm
						.setStreet(profAttribFormsMap.get("street") != null ? profAttribFormsMap
								.get("street").toString() : "");
				empRegisterForm
						.setPrimaryPhone(profAttribFormsMap.get("phone") != null ? profAttribFormsMap
								.get("phone").toString() : "");

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
			model.addObject("listProfAttribForms", profAttribFormsMap);
			model.addObject("empRegisterForm", empRegisterForm);

		} catch (Exception e) {
			LOGGER.info("Error is occured in controllr");
			LOGGER.error("ERROR" + e);
		}

		return model;
	}

	@RequestMapping(value = "/getEmployerNamesList", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	JSONObject getEmployerNamesList(@RequestParam("term") String name) {
		List<FacilityDTO> dtoList = impersonateAgencyService
				.getEmployerNamesList(name);
		JSONArray jsonRows = new JSONArray();
		JSONObject jsonObj2 = new JSONObject();
		for (FacilityDTO dto : dtoList) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("value", dto.getName());
			jsonObj.put("ID", dto.getFacilityId());
			jsonObj.put("NAME", dto.getName());
			jsonRows.add(jsonObj);
		}
		jsonObj2.put("EmpList", jsonRows);
		return jsonObj2;
	}

	@RequestMapping(value = "/getEmployerDetails")
	@ResponseBody
	public Map<String, Object> getEmployerDetails(
			@RequestParam("facilityId") int facilityId) {
		return impersonateAgencyService.getEmployerDetails(facilityId);
	}

	@ResponseBody
	@RequestMapping(value = "/addEmployerDetails", method = RequestMethod.POST)
	public String addEmployerDetails(
			EmployerRegistrationForm employerRegistrationForm,
			BindingResult result, HttpSession session) {
		try {
			AccountProfileDTO dto = transformEmpReg
					.transformEmployerFormToDto(employerRegistrationForm);
			if (StringUtils.isEmpty(dto.getFirstName())) {
				return "Please enter the required field";
			}
			int agencyUserId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			String email = (String) session
					.getAttribute(MMJBCommonConstants.USER_EMAIL);
			FacilityDTO facility = loginService
					.getFacilityByFacilityId(facilityId);
			if (facility.getFacilityParentId() != 0) {
				return "The employer has been linked with other agency. Please contact administrator.";
			}
			int nsCustomerID = impersonateAgencyService
					.getNSCustomerIDFromAdmFacility(dto.getFacilityId());
			UserDTO userDTO = impersonateAgencyService
					.getNSCustomerDetails(nsCustomerID);
			List<String> emailList = userDTO.getEmailList();
			if (emailList != null && emailList.contains(email)) {
				impersonateAgencyService.addEmployer(dto, facilityId,
						agencyUserId);
			} else {
				return "You are not allowed to add the selected employer. Please contact administrator.";
			}
		} catch (Exception e) {

			return "Error while saving the record";
		}
		return "";
	}

	@RequestMapping(value = "/manageEmployers", method = RequestMethod.GET)
	public ModelAndView manageEmployers(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {

			int agencyUserId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			List<FacilityDTO> assocEmplyrsNames = impersonateAgencyService
					.getAssocEmployerNames(agencyUserId, facilityId);

			model.addObject("assocEmplyrsNames", assocEmplyrsNames);
			model.setViewName("agencyManageEmployers");
		} catch (Exception e) {
			LOGGER.info("Error is occured in controllr");
			LOGGER.error("ERROR" + e);
		}

		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAssocEmployer", method = RequestMethod.POST)
	public JSONObject deleteAssocEmployer(
			@RequestParam("facilityId") String facilityId, HttpSession session) {
		int userId = (Integer) session.getAttribute("userId");
		boolean deleteStatus = impersonateAgencyService.deleteAssocEmployer(
				facilityId, userId);
		JSONObject deleteStatusJson = new JSONObject();
		if (deleteStatus) {
			deleteStatusJson.put("success", "Deleted");
			return deleteStatusJson;
		} else {
			deleteStatusJson.put("failed", "Failed");
			return deleteStatusJson;
		}
	}

	@RequestMapping(value = "/impersonateAgencyToEmployer", method = RequestMethod.GET)
	public ModelAndView impersonateAgencyToEmployer(
			@RequestParam("facilityId") int facilityId, HttpSession session,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		session.setAttribute(MMJBCommonConstants.AGENCY_USER_ID,
				session.getAttribute(MMJBCommonConstants.USER_ID));
		session.setAttribute(MMJBCommonConstants.AGENCY_FACILITY_ID,
				session.getAttribute(MMJBCommonConstants.FACILITY_ID));
		session.setAttribute(MMJBCommonConstants.AGENCY_USER_NAME,
				session.getAttribute(MMJBCommonConstants.USER_NAME));
		session.setAttribute(MMJBCommonConstants.AGENCY_EMAIL,
				session.getAttribute(MMJBCommonConstants.USER_EMAIL));
		FacilityDTO facilityDto = loginService
				.getFacilityByFacilityId((Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID));
		if ((facilityDto.getRoleId() == 6)) {
			session.setAttribute("postEdit", "postEdit");
		}
		if ((facilityDto.getRoleId() == 5)) {
			session.setAttribute("fullAcess", "fullAcess");
		}
		int userId = impersonateAgencyService.getfacility(facilityId);
		UserDTO userDTO = impersonateAgencyService.getUserByUserId(userId);
		session.setAttribute(MMJBCommonConstants.USER_ID, userDTO.getUserId());
		session.setAttribute(MMJBCommonConstants.USER_NAME,
				userDTO.getFirstName() + " " + userDTO.getLastName());
		session.setAttribute(MMJBCommonConstants.USER_EMAIL,
				userDTO.getEmailId());
		session.setAttribute(MMJBCommonConstants.FACILITY_ID, facilityId);
		model.setViewName("redirect:/employer/employerDashBoard.html");
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY));
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userDTO.getEmailId(), userDTO.getPassword(), authList);
		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = customAuthenticationManager
				.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		return model;
	}

	@RequestMapping(value = "/impersonateEmployerToAgency", method = RequestMethod.GET)
	public ModelAndView impersonateEmployerToAgency(HttpSession session,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		session.setAttribute(MMJBCommonConstants.USER_ID,
				session.getAttribute(MMJBCommonConstants.AGENCY_USER_ID));
		session.setAttribute(MMJBCommonConstants.FACILITY_ID,
				session.getAttribute(MMJBCommonConstants.AGENCY_FACILITY_ID));
		session.setAttribute(MMJBCommonConstants.USER_NAME,
				session.getAttribute(MMJBCommonConstants.AGENCY_USER_NAME));
		session.setAttribute(MMJBCommonConstants.USER_EMAIL,
				session.getAttribute(MMJBCommonConstants.AGENCY_EMAIL));
		session.removeAttribute("postEdit");
		session.removeAttribute("fullAcess");
		UserDTO userDTO = impersonateAgencyService
				.getUserByUserId((Integer) session
						.getAttribute(MMJBCommonConstants.USER_ID));
		model.setViewName("redirect:/agency/agencyDashboard.html");
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY_GROUP));
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userDTO.getEmailId(), userDTO.getPassword(), authList);
		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = customAuthenticationManager
				.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		return model;
	}

	@RequestMapping(value = "/showEmployerMetrics", method = RequestMethod.GET)
	public ModelAndView showEmployerMetrics(
			@RequestParam("facilityId") int facilityId, HttpSession session,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();
		MetricsDTO metricsDTO = new MetricsDTO();

		// Get the job post details of logged in employer
		List<MetricsDTO> metricsDTOs = loginService.getJobPostTotal(facilityId);
		FacilityDTO employerDetails = loginService
				.getFacilityByFacilityId(facilityId);
		// Getting metrics values from look up table
		List<DropDownDTO> metricsList = populateDropdownsService
				.populateDropdown("Metrics");

		// jbPostTotalList will be having job post total details for metrics
		int views = 0;
		int clicks = 0;
		int applies = 0;
		int size = metricsDTOs.size();
		for (int i = 0; i < metricsDTOs.size(); i++) {
			MetricsDTO dto = new MetricsDTO();
			dto = (MetricsDTO) metricsDTOs.get(i);
			views = views + dto.getViews();
			clicks = clicks + dto.getClicks();
			applies = applies + dto.getApplies();
		}
		metricsDTO.setMetricsName(metricsList.get(0).getOptionName());
		metricsDTO.setViews(views);
		metricsDTO.setClicks(clicks);
		metricsDTO.setApplies(applies);
		jbPostTotalList.add(0, metricsDTO);
		metricsDTO = new MetricsDTO();

		// Calculating average per job posting
		int avgViews = 0;
		int avgClicks = 0;
		int avgApplies = 0;
		if (size > 0) {
			avgViews = views / size;
			avgClicks = clicks / size;
			avgApplies = applies / size;
		}
		metricsDTO.setMetricsName(metricsList.get(1).getOptionName());
		metricsDTO.setViews(avgViews);
		metricsDTO.setClicks(avgClicks);
		metricsDTO.setApplies(avgApplies);
		jbPostTotalList.add(1, metricsDTO);
		metricsDTO = new MetricsDTO();

		// Calculating site - wide average per job posting
		int swAvgViews = 0;
		int swAvgClicks = 0;
		int swAvgApplies = 0;
		long count = 0;
		try {
			count = loginService.getEmployerCount();
		} catch (JobBoardException e) {
			LOGGER.info("Error occured while getting the Result from Database");
		}

		if (count > 0) {
			swAvgViews = (int) (views / count);
			swAvgClicks = (int) (clicks / count);
			swAvgApplies = (int) (applies / count);
		}
		metricsDTO.setMetricsName(metricsList.get(2).getOptionName());
		metricsDTO.setViews(swAvgViews);
		metricsDTO.setClicks(swAvgClicks);
		metricsDTO.setApplies(swAvgApplies);
		jbPostTotalList.add(2, metricsDTO);

		model.addObject("jbPostTotalList", jbPostTotalList);
		model.addObject("employerDetails", employerDetails);
		model.setViewName("employersMetricsPopup");
		return model;
	}
}
