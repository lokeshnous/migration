/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.agency.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.agency.service.AgencyService;
import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.web.controller.EmployeeAccountForm;
import com.advanceweb.afc.jb.employer.web.controller.EmployerProfileAttribForm;
import com.advanceweb.afc.jb.employer.web.controller.EmployerRegistrationForm;
import com.advanceweb.afc.jb.employer.web.controller.MetricsForm;
import com.advanceweb.afc.jb.employer.web.controller.TransformEmployerRegistration;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.job.service.JobPostInventoryService;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayService;
import com.advanceweb.afc.jb.pgi.web.controller.BillingAddressForm;
import com.advanceweb.afc.jb.pgi.web.controller.TransformPaymentMethod;
import com.advanceweb.afc.jb.search.service.JobSearchService;
import com.advanceweb.afc.jb.security.DatabaseAuthenticationManager;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.user.UserService;
import com.advanceweb.afc.jb.user.UserSubscriptionService;
import com.advanceweb.afc.jb.user.web.controller.TransformUserubscription;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 21st August, 2012
 * 
 */

@Controller
@RequestMapping("/agency")
public class AgencyDashBoardController extends AbstractController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(AgencyDashBoardController.class);
	
	/** The Constant FACILITY_ID. */
	private static final String FACILITY_ID = "facilityId";

	/** The Constant JB_POST_TOTAL_LIST. */
	private static final String JB_POST_TOTAL_LIST = "jbPostTotalList";

	/** The emp reg service. */
	@Autowired
	private EmloyerRegistartionService empRegService;
	
	/** The custom authentication manager. */
	@Autowired
	protected DatabaseAuthenticationManager customAuthenticationManager;
	
	/** The inventory service. */
	@Autowired
	private JobPostInventoryService inventoryService;

	/** The facility service. */
	@Autowired
	private FacilityService facilityService;

	/** The user sub service. */
	@Autowired
	private UserSubscriptionService userSubService;

	/** The userubscription. */
	@Autowired
	private TransformUserubscription userubscription;
	
	/** The agency service. */
	@Autowired
	private AgencyService agencyService;
	
	/** The transform emp reg. */
	@Autowired
	private TransformEmployerRegistration transformEmpReg;
	
	/** The fetch adm facility conatact. */
	@Autowired
	private PaymentGatewayService fetchAdmFacilityConatact;
	
	/** The transform payment method. */
	@Autowired
	private TransformPaymentMethod transformPaymentMethod;
	
	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/** The employer registration. */
	@Autowired
	private ProfileRegistration employerRegistration;

	/** The ad service. */
	@Autowired
	private AdService adService;

	/** The employer job post. */
	@Autowired
	private JobPostService employerJobPost;

	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The job search service. */
	@Autowired
	private JobSearchService jobSearchService;

	/** The required field. */
	@Value("${requiredField}")
	private String requiredField;
	
	/** The required all fields. */
	@Value("${requiredAllFields}")
	private String requiredAllFields;
	
	/** The employer linked. */
	@Value("${employerLinked}")
	private String employerLinked;
	
	/** The employer add validation. */
	@Value("${employerAddValidation}")
	private String employerAddValidation;
	
	/** The already added. */
	@Value("${alreadyAdded}")
	private String alreadyAdded;
	
	/** The email in use. */
	@Value("${emailInUse}")
	private String emailInUse;
	
	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	/** The validate city state. */
	@Value("${validateCityState}")
	private String validateCityState;
	
	/** The lookup service. */
	@Autowired
	private LookupService lookupService;
	
	/** The email service. */
	@Autowired
	private MMEmailService emailService;

	/** The email configuration. */
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	/**
	 * Display dash board.
	 *
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping("/agencyDashboard")
	public ModelAndView displayDashBoard(HttpSession session,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Map<String, List<FacilityDTO>> emplyrsByState = new HashMap<String, List<FacilityDTO>>();
		Set<String> stateList = new HashSet<String>();
		populateAds(session, request, model);
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		List<FacilityDTO> assocEmplyrsNames;
		if(session.getAttribute("advancePassUser")!=null){
			model.addObject("advUserMessg", "advancePassUser");
		}
		try {
			assocEmplyrsNames = agencyService
					.getLinkedFacilityNames(facilityId);
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
		} catch (JobBoardException e) {
			LOGGER.debug("Error while getting the linked facilities related to the corresponding agency");
		}

		// Retrieve Current subscriptions of the user
		int parentFacilityId = userSubService.getParentId(facilityId);

		List<DropDownDTO> currentSubs = getCurrentSubscriptions(parentFacilityId);
		Set<DropDownDTO> set = new HashSet<DropDownDTO>();
		for (DropDownDTO dto : currentSubs) {
			set.add(dto);
		}

		model.addObject("currentSubs", set);
		model.addObject("emplyrsByState", emplyrsByState);
		model.setViewName("agencyDashboard");
		return model;
	}

	/**
	 * This method is called to Account Setting update page and
	 * editAccountSetting method take Bean class binding result from Jsp pages
	 * Session for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@ResponseBody
	@RequestMapping(value = "/agencyAccountSetting", method = RequestMethod.POST)
	public String editAccountSetting(EmployeeAccountForm employeeAccountForm,
			BindingResult result, HttpSession session,HttpServletRequest request) {
		boolean isUpdated = false;
		try {
			if (employeeAccountForm.isAdminLogin()) {
				if (facilityService.getUser(employeeAccountForm.getEmail()) != null
						|| userService.getAdvancePassUser(employeeAccountForm
								.getEmail()) != null) {
					return emailInUse;
				}
			}
			int userId = (Integer) session.getAttribute("userId");
			AdmFacilityContactDTO listProfAttribForms = empRegService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);
			if (listProfAttribForms.getCount() > 0) {
				int admfacilityid = listProfAttribForms.getFacilityContactId();
				String errMessage = validateAccountDetails(employeeAccountForm);
				if (!StringUtils.isEmpty(errMessage)) {

					return errMessage;
				}

				AccountProfileDTO dto = transformEmpReg
						.transformAccountProfileFormToDto(employeeAccountForm);

				isUpdated = empRegService.editUser(dto, admfacilityid, userId,
						MMJBCommonConstants.PRIMARY);
				// send Email after updating the employer email address by Agency through Impersonation 
				isUpdated = empRegService.editUser(dto, admfacilityid, userId,
						MMJBCommonConstants.PRIMARY);
				if(null!=dto.getEmail() && isUpdated){
					UserDTO userDTO = new UserDTO();
					userDTO.setUserId(userId);
					userDTO.setFirstName(dto.getFirstName());
					userDTO.setLastName(dto.getLastName());
					userDTO.setCompany(dto.getCompanyName());
					userDTO.setEmailId(dto.getEmail());
					try{
						sendEmployerWelcomeEmail(request, userDTO);
					}
					catch(Exception e){
						LOGGER.error("Mail sending failed : "+e);
					}
				}
				session.setAttribute(MMJBCommonConstants.USER_NAME,
						employeeAccountForm.getFirstName() + " "
								+ employeeAccountForm.getLastName());
				session.setAttribute(MMJBCommonConstants.COMPANY_EMP,
						employeeAccountForm.getCompany());
				if (isUpdated) {
					LOGGER.debug("This is Account Addresss edite option done successfully");
				} else {
					return MMJBCommonConstants.UPDATE_ERROR;
				}

			}

		} catch (Exception e) {

			LOGGER.error("This is Account Addresss edite option error");
		}
		return "";
	}

	/**
	 * @param employeeAccountForm
	 */
	private String validateAccountDetails(EmployeeAccountForm employeeAccountForm) {
		
		if (StringUtils.isBlank(employeeAccountForm.getEmail())
				|| StringUtils.isBlank(employeeAccountForm.getPhone())
				|| StringUtils.isBlank(employeeAccountForm.getFirstName())
				|| StringUtils.isBlank(employeeAccountForm.getLastName())
				|| StringUtils.isBlank(employeeAccountForm.getZipCode())
				|| StringUtils.isBlank(employeeAccountForm.getState())
				|| StringUtils.isBlank(employeeAccountForm.getStreetAddress())
				|| StringUtils.isBlank(employeeAccountForm.getCityOrTown())
				|| (StringUtils.isBlank(employeeAccountForm.getCompany())
						&& StringUtils.isBlank(employeeAccountForm.getCountry()) && StringUtils
							.isBlank(employeeAccountForm.getState()))) {

			return "Please fill the required fields";
		}
		boolean validateStateCityZip;
		try {
			validateStateCityZip = lookupService.validateCityStateZip(
					employeeAccountForm.getCityOrTown(),
					employeeAccountForm.getState(),
					employeeAccountForm.getZipCode());

			if (!validateStateCityZip) {
				return validateCityState;
			}
		} catch (JobBoardServiceException ex) {

			ex.printStackTrace();
		}
		if (!validateEmailPattern(employeeAccountForm.getEmail())) {
			return MMJBCommonConstants.EMAIL_MESSAGE;
		} else if (!validatePhonePattern(employeeAccountForm.getPhone())) {
			return MMJBCommonConstants.PHONE_NO;
		}  
		return null;
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
	@RequestMapping(value = "/agencyBillingSetting", method = RequestMethod.POST)
	public String editBillingSetting(EmployeeAccountForm employeeBillingForm,
			BindingResult result, HttpSession session) {

		try {
			int userId = (Integer) session.getAttribute("userId");
			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			AdmFacilityContactDTO listProfAttribForms = empRegService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);
			String errMessage = validateBillingAccDetails(employeeBillingForm);
			if (!StringUtils.isEmpty(errMessage)) {

				return errMessage;
			}
			if (listProfAttribForms.getCount() > 0) {
				int admfacilityid = listProfAttribForms.getFacilityContactId();
				AccountProfileDTO dto = transformEmpReg
						.transformBillingProfileFormToDto(employeeBillingForm);
				empRegService.editUser(dto, admfacilityid, userId,
						MMJBCommonConstants.BILLING);

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
				LOGGER.debug("This is Billing Addresss save done successfully");
			}

		} catch (Exception e) {
			LOGGER.error("This is Billing Addresss edite or save error");
		}
		return "";
	}

	/**
	 * Validate Billing address details
	 * @param employeeBillingForm
	 */
	private String validateBillingAccDetails(
			EmployeeAccountForm employeeBillingForm) {

		if (StringUtils.isEmpty(employeeBillingForm.getEmail())
				|| StringUtils.isBlank(employeeBillingForm.getPhone())
				|| StringUtils.isBlank(employeeBillingForm
						.getBillingAddressForm().getFnameForBillingAddr())
				|| StringUtils.isBlank(employeeBillingForm
						.getBillingAddressForm().getLnameForBillingAddr())
				|| StringUtils.isBlank(employeeBillingForm
						.getBillingAddressForm().getZipCodeForBillingAddr())
				|| StringUtils.isBlank(employeeBillingForm
						.getBillingAddressForm().getCityOrTownForBillingAddr())
				|| StringUtils.isBlank(employeeBillingForm
						.getBillingAddressForm().getCountryForBillingAddr())
				|| StringUtils.isEmpty(employeeBillingForm
						.getBillingAddressForm().getStateBillingAddress())
				|| (StringUtils.isBlank(employeeBillingForm.getCompany())
						&& StringUtils.isBlank(employeeBillingForm
								.getBillingAddressForm()
								.getStreetForBillingAddr()) && StringUtils
							.isEmpty(employeeBillingForm.getState()))) {

			return "Please fill the required fields";
		}
		boolean validateStateCityZip;
		try {
			validateStateCityZip = lookupService.validateCityStateZip(
					employeeBillingForm.getBillingAddressForm()
							.getCityOrTownForBillingAddr(), employeeBillingForm
							.getBillingAddressForm().getStateBillingAddress(),
					employeeBillingForm.getBillingAddressForm()
							.getZipCodeForBillingAddr());

			if (!validateStateCityZip) {
				return validateCityState;
			}
		} catch (JobBoardServiceException ex) {

			ex.printStackTrace();
		}
		if (!validateEmailPattern(employeeBillingForm.getEmail())) {
			return MMJBCommonConstants.EMAIL_MESSAGE;
		} else if (!validatePhonePattern(employeeBillingForm.getPhone())) {
			return MMJBCommonConstants.PHONE_NO;
		}
		return null;
	}

	/**
	 * This method is called to Account Setting display page call from dash
	 * board of employer
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@RequestMapping(value = "/viewAgencyAccountProfile", method = RequestMethod.GET)
	public ModelAndView viewEmpAccountProfileSettings(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			EmployeeAccountForm employeeAccountForm = new EmployeeAccountForm();
			EmployeeAccountForm employeeBillingForm = new EmployeeAccountForm();
			employeeBillingForm.setBillingAddressForm(new BillingAddressForm());

			int userId = (Integer) session.getAttribute("userId");
			// Post/Edit Only users: should NOT be able to change Account
			// Settings
			List<UserRoleDTO> roleList = userService.getUserRole(userId);
			if (null != roleList) {
				for (UserRoleDTO userRole : roleList) {
					if (null != userRole.getRoleName()
							&& userRole.getRoleName().equals(
									MMJBCommonConstants.FACILITY_POST_EDIT)) {
						employeeAccountForm.setReadOnly(true);
					}
				}
			}
			if (session.getAttribute("adminLogin") != null) {
				employeeAccountForm.setAdminLogin(true);
			}
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
			model.setViewName("agencyAccountSetting");
			model.addObject("employeeAccountForm", employeeAccountForm);
			model.addObject("employeeBillingForm", employeeBillingForm);

		} catch (Exception e) {
			LOGGER.error("Error For Account Setting Link call in controller class");
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

	/**
	 * Gets the adds the facility popup.
	 *
	 * @param session the session
	 * @return the adds the facility popup
	 */
	@RequestMapping(value = "/getAddFacilityPopup", method = RequestMethod.GET)
	public ModelAndView getAddFacilityPopup(HttpSession session) {
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

	/**
	 * Gets the facility details popup.
	 *
	 * @param facilityId the facility id
	 * @return the facility details popup
	 */
	@RequestMapping(value = "/viewFacilityDetails", method = RequestMethod.GET)
	public ModelAndView getFacilityDetailsPopup(
			@RequestParam(FACILITY_ID) int facilityId) {
		ModelAndView model = new ModelAndView();
		try {
			EmployerRegistrationForm empRegisterForm = new EmployerRegistrationForm();
			EmployerProfileDTO registerDTO = (EmployerProfileDTO) employerRegistration
					.getProfileAttributes();
			List<EmployerProfileAttribForm> listProfAttribForms = transformEmpReg
					.transformDTOToProfileAttribForm(registerDTO, null);
			empRegisterForm.setListProfAttribForms(listProfAttribForms);
			FacilityDTO facilityDTO = agencyService
					.getLinkedFacilityDetails(facilityId);
			model.setViewName("agencyEditEmployer");
			if (facilityDTO != null) {
				empRegisterForm.setFirstName(facilityDTO.getName());
				empRegisterForm.setStreet(facilityDTO.getStreet());
				empRegisterForm.setPrimaryPhone(facilityDTO.getPhone());
				empRegisterForm.setZipCode(facilityDTO.getPostcode());
				empRegisterForm.setState(facilityDTO.getState());
				empRegisterForm.setCountry(facilityDTO.getCountry());
				empRegisterForm.setCity(facilityDTO.getCity());
				empRegisterForm.setFacilityId(facilityDTO.getFacilityId());
			}
			model.addObject("listProfAttribForms", facilityDTO);
			model.addObject("empRegisterForm", empRegisterForm);

		} catch (JobBoardException e) {
			LOGGER.debug("Error while getting the linked facility" + e);
		}

		return model;
	}

	/**
	 * Gets the facility names list.
	 *
	 * @param name the name
	 * @return the facility names list
	 */
	@RequestMapping(value = "/getFacilityNamesList", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	JSONObject getFacilityNamesList(@RequestParam("term") String name) {
		List<FacilityDTO> dtoList;
		JSONObject jsonObject = new JSONObject();
		try {
			dtoList = agencyService.getFacilityNames(name);
			JSONArray jsonRows = new JSONArray();
			for (FacilityDTO dto : dtoList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("value", dto.getName());
				jsonObj.put("ID", dto.getFacilityId());
				jsonObj.put("NAME", dto.getName());
				jsonRows.add(jsonObj);
			}
			jsonObject.put("EmpList", jsonRows);
		} catch (JobBoardException e) {
			LOGGER.debug("Error while getting the Facility List Details based on the Facility Name"
					+ e);
		}

		return jsonObject;
	}

	/**
	 * Gets the selected facility.
	 *
	 * @param facilityId the facility id
	 * @return the selected facility
	 */
	@RequestMapping(value = "/getSelectedFacility")
	@ResponseBody
	public FacilityDTO getSelectedFacility(
			@RequestParam(FACILITY_ID) int facilityId) {
		FacilityDTO facilityDTO = null;
		try {
			facilityDTO = agencyService.getLinkedFacilityDetails(facilityId);
		} catch (JobBoardException e) {
			LOGGER.debug("Error while getting the Facility Details based on the facilityId"
					+ e);
		}
		return facilityDTO;
	}

	/**
	 * Link selected facility.
	 *
	 * @param employerRegistrationForm the employer registration form
	 * @param result the result
	 * @param session the session
	 * @return the string
	 */
	@ResponseBody
	@RequestMapping(value = "/linkSelectedFacility", method = RequestMethod.POST)
	public String linkSelectedFacility(
			EmployerRegistrationForm employerRegistrationForm,
			BindingResult result, HttpSession session) {
		try {
			AccountProfileDTO dto = transformEmpReg
					.transformEmployerFormToDto(employerRegistrationForm);
			if (StringUtils.isEmpty(dto.getFirstName())) {
				return requiredField;
			}
			if (StringUtils.isEmpty(dto.getStreet())) {
				return requiredAllFields;
			}
			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			String email = (String) session
					.getAttribute(MMJBCommonConstants.USER_EMAIL);
			FacilityDTO facility = facilityService.getFacilityByFacilityId(dto
					.getFacilityId());
			if (facility.getFacilityParentId() == facilityId) {
				return alreadyAdded;
			}
			if (facility.getFacilityParentId() != -1) {
				return employerLinked;
			}
			boolean validateStateCityZip;
			try {
				validateStateCityZip = lookupService.validateCityStateZip(
						employerRegistrationForm.getCity(),
						employerRegistrationForm.getState(),
						employerRegistrationForm.getZipCode());

				if (!validateStateCityZip) {
					return validateCityState;
				}
			} catch (JobBoardServiceException ex) {

				ex.printStackTrace();
			}
			FacilityDTO facilityDTO = facilityService
					.getFacilityByFacilityId(dto.getFacilityId());
			UserDTO userDTO = agencyService.getNSCustomerDetails(facilityDTO
					.getNsCustomerID());
			List<String> emailList = userDTO.getEmailList();
			if (emailList != null && emailList.contains(email)) {
				agencyService.linkFacility(dto, facilityId);
			} else {
				return employerAddValidation;
			}

		} catch (JobBoardException e) {
			LOGGER.debug("Error while linking the selected facility to the corresponding agency"
					+ e);
			return "Error while saving the record";
		}
		return "";
	}

	/**
	 * Gets the manage facility popup.
	 *
	 * @param session the session
	 * @return the manage facility popup
	 */
	@RequestMapping(value = "/getManageFacilityPopup", method = RequestMethod.GET)
	public ModelAndView getManageFacilityPopup(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			int facilityId = (Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID);
			List<FacilityDTO> assocEmplyrsNames = agencyService
					.getLinkedFacilityNames(facilityId);

			model.addObject("assocEmplyrsNames", assocEmplyrsNames);
			model.setViewName("agencyManageEmployers");
		} catch (JobBoardException e) {
			LOGGER.debug("Error while fetching the all linked facility to the corresponding agency"
					+ e);
		}

		return model;
	}

	/**
	 * Delete assoc facility.
	 *
	 * @param facilityId the facility id
	 * @param session the session
	 * @return the jSON object
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAssocFacility", method = RequestMethod.POST)
	public JSONObject deleteAssocFacility(
			@RequestParam(FACILITY_ID) int facilityId, HttpSession session) {
		boolean deleteStatus;
		JSONObject deleteStatusJson = new JSONObject();
		deleteStatusJson.put("failed", "Failed");

		try {
			deleteStatus = agencyService.deleteAssocEmployer(facilityId);
			if (deleteStatus) {
				deleteStatusJson.put("success", "Deleted");
				return deleteStatusJson;
			}
		} catch (JobBoardServiceException e) {
			LOGGER.debug("Error while unlinking the facility from the Agency");
		}
		return deleteStatusJson;
	}

	/**
	 * Impersonate agency to facility.
	 *
	 * @param facilityId the facility id
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/impersonateAgencyToFacility", method = RequestMethod.GET)
	public ModelAndView impersonateAgencyToFacility(
			@RequestParam(FACILITY_ID) int facilityId, HttpSession session,
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
		FacilityDTO facilityDto = facilityService
				.getFacilityByFacilityId((Integer) session
						.getAttribute(MMJBCommonConstants.FACILITY_ID));
		session.setAttribute(MMJBCommonConstants.FACILITY_FULL_ACCESS,
				MMJBCommonConstants.FACILITY_FULL_ACCESS);
		if ((facilityDto.getRoleId() == 6)) {
			session.setAttribute(MMJBCommonConstants.FACILITY_POST_EDIT,
					MMJBCommonConstants.FACILITY_POST_EDIT);
			session.removeAttribute(MMJBCommonConstants.FACILITY_FULL_ACCESS);
		}
		int userId = facilityService.getfacilityUserId(facilityId);
		EmployerInfoDTO infoDTO = facilityService.facilityDetails(userId);
		session.setAttribute(MMJBCommonConstants.COMPANY_EMP,
				infoDTO.getCustomerName());
		UserDTO userDTO = agencyService.getUserByUserId(userId);
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

		UserDTO user = userService.getAdvancePassUser(userDTO.getEmailId());

		// UsernamePasswordAuthenticationToken token = new
		// UsernamePasswordAuthenticationToken(
		// userDTO.getEmailId(), userDTO.getPassword(), authList);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getEmailId(), user.getPassword(), authList);
		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = customAuthenticationManager
				.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		return model;
	}

	/**
	 * Impersonate facility to agency.
	 *
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/impersonateFacilityToAgency", method = RequestMethod.GET)
	public ModelAndView impersonateFacilityToAgency(HttpSession session,
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
		session.removeAttribute(MMJBCommonConstants.FACILITY_POST_EDIT);
		session.removeAttribute(MMJBCommonConstants.FACILITY_FULL_ACCESS);
		EmployerInfoDTO infoDTO = facilityService
				.facilityDetails((Integer) session
						.getAttribute(MMJBCommonConstants.AGENCY_USER_ID));
		session.setAttribute(MMJBCommonConstants.COMPANY_EMP,
				infoDTO.getCustomerName());
		UserDTO userDTO = agencyService.getUserByUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));
		model.setViewName("redirect:/agency/agencyDashboard.html");
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY_GROUP));
		UserDTO user = userService.getAdvancePassUser(userDTO.getEmailId());
		// UsernamePasswordAuthenticationToken token = new
		// UsernamePasswordAuthenticationToken(
		// userDTO.getEmailId(), userDTO.getPassword(), authList);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getEmailId(), user.getPassword(), authList);

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = customAuthenticationManager
				.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		return model;
	}

	/**
	 * This method is used to display the metrics details for selected employer
	 * in the drop down
	 * 
	 * @param employerDashBoardForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/showFacilityMetrics", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView showFacilityMetrics(
			@ModelAttribute("metricsForm") MetricsForm metricsForm,
			BindingResult result, HttpSession session,
			@RequestParam(FACILITY_ID) int facilityId) {
		ModelAndView model = new ModelAndView();
		session.removeAttribute(JB_POST_TOTAL_LIST);
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();
		List<DropDownDTO> downDTOs = new ArrayList<DropDownDTO>();
		FacilityDTO employerDetails = facilityService
				.getFacilityByFacilityId(facilityId);
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		List<JobPostingInventoryDTO> inventiryDTO = inventoryService
				.getInventoryDetails(userId, facilityId);
		int avaQuantity = 0;
		for (JobPostingInventoryDTO dto : inventiryDTO) {
			avaQuantity = avaQuantity + dto.getAvailableQty();

		}
		downDTOs = populateDropdownsService.populateCompanyNames(facilityId,
				true);
		// active job posting
		int count = employerJobPost.getEmpJobsCountByStatus(
				MMJBCommonConstants.POST_NEW_JOB, downDTOs);

		session.setAttribute("count", count);
		session.setAttribute("avaQuantity", avaQuantity);

		// getting the metrics details
		jbPostTotalList = getMetricsDetails(Integer.parseInt(downDTOs.get(0)
				.getOptionId()));
		model.addObject("downDTOs", downDTOs);
		session.setAttribute(JB_POST_TOTAL_LIST, jbPostTotalList);
		model.addObject("employerDetails", employerDetails);
		model.setViewName("employersMetricsPopup");
		return model;
	}

	/**
	 * Show metrics.
	 *
	 * @param metricsForm the metrics form
	 * @param result the result
	 * @param session the session
	 * @param facilityId the facility id
	 */
	@RequestMapping(value = "/viewFacilityMetrics", method = RequestMethod.GET)
	public @ResponseBody
	void showMetrics(@ModelAttribute("metricsForm") MetricsForm metricsForm,
			BindingResult result, HttpSession session,
			@RequestParam(FACILITY_ID) int facilityId) {
		session.removeAttribute(JB_POST_TOTAL_LIST);
		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();
		// getting the metrics details
		jbPostTotalList = getMetricsDetails(facilityId);
		session.setAttribute(JB_POST_TOTAL_LIST, jbPostTotalList);
		// return;
	}

	/**
	 * Get the metric details page
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/metricsDetails")
	public ModelAndView getMetricsDetails(HttpServletResponse response,
			HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("metricsDetails");
		return modelAndView;
	}

	/**
	 * This method is to get the metrics details for facility
	 * 
	 * @param facilityId
	 * @return
	 */
	private List<MetricsDTO> getMetricsDetails(int facilityId) {
		long views = 0;
		long clicks = 0;
		long applies = 0;

		List<MetricsDTO> jbPostTotalList = new ArrayList<MetricsDTO>();
		// Get the job post details of logged in employer
		MetricsDTO metricsDTO = facilityService.getJobPostTotal(facilityId);
		
		views = metricsDTO.getViews();
		clicks = metricsDTO.getClicks();
		applies = metricsDTO.getApplies();

		// Getting metrics values from look up table
		List<DropDownDTO> metricsList = populateDropdownsService
				.populateDropdown("Metrics");
		metricsDTO.setMetricsName(metricsList.get(0).getOptionName());
		
		// jbPostTotalList will be having job post total details for metrics
		jbPostTotalList.add(0, metricsDTO);
		MetricsDTO avgMetricsDTO = new MetricsDTO();

		// Calculating average per job posting
		long avgViews = 0;
		long avgClicks = 0;
		long avgApplies = 0;
		long totalJobs = 0;

		totalJobs = facilityService.getJobsByFacility(facilityId);
		if (totalJobs > 0) {
			avgViews = Math.round((double) views / totalJobs);
			avgClicks = Math.round((double) clicks / totalJobs);
			avgApplies = Math.round((double) applies / totalJobs);
		}
		avgMetricsDTO.setMetricsName(metricsList.get(1).getOptionName());
		avgMetricsDTO.setViews(avgViews);
		avgMetricsDTO.setClicks(avgClicks);
		avgMetricsDTO.setApplies(avgApplies);
		jbPostTotalList.add(1, avgMetricsDTO);

		// Calculating site - wide average per job posting
		long swAvgViews = 0;
		long swAvgClicks = 0;
		long swAvgApplies = 0;
		long activeJobs = 0;
		activeJobs = jobSearchService.getActiveJobs();
		MetricsDTO dto = facilityService.getAllJobStats();

		if (activeJobs > 0) {
			swAvgViews = Math.round((double) dto.getViews() / activeJobs);
			swAvgClicks = Math.round((double) dto.getClicks() / activeJobs);
			swAvgApplies = Math.round((double) dto.getApplies() / activeJobs);
		}
		dto.setMetricsName(metricsList.get(2).getOptionName());
		dto.setViews(swAvgViews);
		dto.setClicks(swAvgClicks);
		dto.setApplies(swAvgApplies);
		jbPostTotalList.add(2, dto);

		return jbPostTotalList;
	}

	/**
	 * Gets the image.
	 *
	 * @param imagePath the image path
	 * @param response the response
	 * @param request the request
	 * @return the image
	 */
	@RequestMapping("/viewImage")
	public void getImage(@RequestParam("path") String imagePath,
			HttpServletResponse response, HttpServletRequest request) {

		try {
			BufferedImage originalImage = ImageIO.read(new File(imagePath));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(
					originalImage,
					imagePath.substring(imagePath.length() - 3,
							imagePath.length()), baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			ResponseEntity<byte[]> result = getResponseEntity(imageInByte);
			// Display the image
			writeToOutputStream(response, result.getBody());
		} catch (Exception e) {

			LOGGER.error(e);

		}
	}

	/**
	 * Writes to the output stream
	 */
	private void writeToOutputStream(HttpServletResponse response,
			byte[] byteArray) {
		ServletOutputStream outputStream = null;
		try {
			// Retrieve the output stream
			outputStream = response.getOutputStream();
			// Write to the output stream
			outputStream.write(byteArray);
			// Flush the stream
			outputStream.flush();
			// Close the stream
			outputStream.close();

		} catch (Exception e) {
			LOGGER.error(e);
		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 * Gets the response entity.
	 *
	 * @param imageInByte the image in byte
	 * @return the response entity
	 */
	private ResponseEntity<byte[]> getResponseEntity(byte[] imageInByte) {
		byte[] byteData = imageInByte;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(byteData.length);

		return new ResponseEntity<byte[]>(byteData, responseHeaders,
				HttpStatus.OK);
	}

	/**
	 * Retrieve Current subscriptions of the user
	 * 
	 * @param userId
	 * @return
	 */
	private List<DropDownDTO> getCurrentSubscriptions(int facilityId) {

		List<DropDownDTO> listSubscriptions = populateDropdownsService
				.getFacilitySubList();

		List<UserSubscriptionsDTO> currentSubsList = userSubService
				.getCurrentFacilitySub(facilityId);

		List<DropDownDTO> currentSubs = userubscription
				.jsSubscriptionDTOToJobSeekerSubscriptionForm(currentSubsList,
						listSubscriptions);
		return currentSubs;

	}

	/**
	 * The method helps to populate the ads for the page
	 * 
	 * @param session
	 * @param request
	 * @param model
	 */
	private void populateAds(HttpSession session, HttpServletRequest request,
			ModelAndView model) {
		// Add the Ads
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.AGENCY_DASHBOARD);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
		} catch (Exception e) {
			LOGGER.error(
					"Error occurred while getting the html content for Ads", e);
		}
	}
	/**
	 * @param request
	 * @param userDTO
	 */
	private void sendEmployerWelcomeEmail(HttpServletRequest request,
			UserDTO userDTO) {
		InternetAddress[] jsToAddress = new InternetAddress[1];

		try {
			jsToAddress[0] = new InternetAddress(userDTO.getEmailId());
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}

		EmployerInfoDTO facilityDetail = facilityService
				.facilityDetails(userDTO.getUserId());
		if (null != facilityDetail) {
			userDTO.setCompany(facilityDetail.getCustomerName());
		}
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setToAddress(jsToAddress);
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty(
				"welcome.mail.message").trim());
		String userName=userDTO.getFirstName()+" " + userDTO.getLastName();
		String employerWelcomeMailBody = emailConfiguration.getProperty(
				"employer.welcome.mail.body").trim();
		String employerloginUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), emailConfiguration.getProperty(
						"agency.email.login.url").trim());
//				+ dothtmlExtention + agencyPageExtention;
		employerWelcomeMailBody = employerWelcomeMailBody.replace("?userName",
				userDTO.getFirstName());
		employerWelcomeMailBody = employerWelcomeMailBody.replace("?user_name",
				userName);
		employerWelcomeMailBody = employerWelcomeMailBody.replace(
				"?company_name", userDTO.getCompany());
		employerWelcomeMailBody = employerWelcomeMailBody.replace(
				"?empdashboardLink", employerloginUrl);

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		stringBuffer.append(employerWelcomeMailBody);
		stringBuffer.append(emailConfiguration.getProperty("email.footer")
				.trim());
		emailDTO.setBody(stringBuffer.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}
}
