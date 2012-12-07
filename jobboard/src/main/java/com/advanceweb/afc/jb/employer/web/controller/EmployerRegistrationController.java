package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayService;
import com.advanceweb.afc.jb.pgi.web.controller.BillingAddressForm;
import com.advanceweb.afc.jb.pgi.web.controller.TransformPaymentMethod;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

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
public class EmployerRegistrationController extends AbstractController{

	private static final Logger LOGGER = Logger
			.getLogger(EmployerRegistrationController.class);
	// private static final String _FORM_VIEW = "employerDashboard";

	@Autowired
	private ProfileRegistration employerRegistration;

	@Autowired
	private TransformEmployerRegistration transformEmpReg;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private PaymentGatewayService fetchAdmFacilityConatact;

	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private AdService adService;

	@Autowired
	private EmployerRegistrationValidation registerValidation;

	@Autowired
	protected AuthenticationManager customAuthenticationManager;

	@Autowired
	private EmloyerRegistartionService empRegService;

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	@Value("${navigationPath}")
	private String navigationPath;
	@Value("${employerPageExtention}")
	private String employerPageExtention;
	@Autowired
	private MMEmailService emailService;

	@Value("${dothtmlExtention}")
	private String dothtmlExtention;
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	// @Autowired
	// private AdmManagePermission admManagePermission;

	@Autowired
	private TransformPaymentMethod transformPaymentMethod;

	@Value("${jobseekerRegPhoneMsg}")
	private String jobseekerRegPhoneMsg;
	@Value("${socialSignupMsg}")
	private  String socialSignupMsg;
	@Value("${emp.all.req.fields}")
	private String reqFields;

	@Value("${emp.email.exists}")
	private String emailExists;

	@Value("${view.media.kit.url}")
	private String viewMediaUrl;

	@Value("${ns.validate.user}")
	private String nsValidateUser;

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
	@Value("${emailInUse}")
	private String emailInUse;

	// Spring ReCaptcha

	private String recaptchaResponse;
	private String recaptchaChallenge;
	private String remoteAddr;
	private final static String EMPLOYERREG = "employerregistration";
	private final static String EMPREGFORM = "empRegisterForm";
	private final static String MESSAGE = "message";

	/**
	 * This method is called to display  employer registration page
	 * @param request 
	 * 
	 * @param HttpSession session
	 * @param String profileId(Optional,used while displaying the the registration page if user wants to register with his social media(e.g Facebook,LinkedIn) account)
	 * @param String serviceProviderId(Optional,used while displaying the the registration page if user wants to register with his social media(e.g Facebook,LinkedIn) account)
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "/employerregistration", method = RequestMethod.GET)
	public ModelAndView showEmployerRegistrationForm(HttpSession session,@RequestParam(value = "profileId", required = false) String profileId,@RequestParam(value = "serviceProviderId", required = false) String serviceProviderId, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();

		EmployerRegistrationForm empRegisterForm = new EmployerRegistrationForm();

		EmployerProfileDTO registerDTO = (EmployerProfileDTO) employerRegistration
				.getProfileAttributes();
		UserDTO userDTO = null;
		if (session.getAttribute(MMJBCommonConstants.USER_DTO) != null) {
			userDTO = (UserDTO) session
					.getAttribute(MMJBCommonConstants.USER_DTO);
			empRegisterForm.setEmailId(userDTO.getEmailId());
			empRegisterForm.setConfirmEmailId(userDTO.getEmailId());
			empRegisterForm.setPassword(userDTO.getPassword());
			empRegisterForm.setConfirmPassword(userDTO.getPassword());
			empRegisterForm.setUserId(userDTO.getUserId());
			empRegisterForm.setbReadOnly(true);
		}
		if(profileId!=null){
			empRegisterForm.setServiceProviderName(serviceProviderId);
			empRegisterForm.setSocialProfileId(profileId);
			empRegisterForm.setSocialSignUp(true);
			model.addObject("socialSignUpMsg", socialSignupMsg.replace(
					"?serviceProviderId", serviceProviderId));
			
		}
		List<EmployerProfileAttribForm> listProfAttribForms = transformEmpReg
				.transformDTOToProfileAttribForm(registerDTO, userDTO);

		empRegisterForm.setListProfAttribForms(listProfAttribForms);
		model.addObject(EMPREGFORM, empRegisterForm);
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		// map.put("empRegisterForm", empRegisterForm);
		model.setViewName(EMPLOYERREG);
		// get the Ads
		populateAds (request, session, model, PageNames.EMPLOYER_REGISTRATION);
		return model;
	}
	
	/**
	 * This method is called to save the employer registration information
	 * @param request 
	 * 
	 * @param EmployerRegistrationForm empRegForm
	 * @param Map map
	 * @param HttpSession session 
	 * @param HttpServletRequest req
	 * @param BindingResult result
	 * @return ModelAndView
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/saveEmployerProfile", method = RequestMethod.POST)
	public ModelAndView saveEmployerRegistration(
			@ModelAttribute(EMPREGFORM) EmployerRegistrationForm empRegForm,
			Map map, HttpSession session,HttpServletRequest req,
			BindingResult result, HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		if (null != empRegForm.getListProfAttribForms()) {
			model.setViewName(EMPLOYERREG);
			// get the Ads
			populateAds (request, session, model, PageNames.EMPLOYER_REGISTRATION);
			if (!validateEmpRegForm(empRegForm, model, result)) {
				return model;
			}
		}
		// Spring Recaptcha Starts here
		if (StringUtils.isEmpty(req
				.getParameter("recaptcha_response_field"))) {
			model.setViewName(EMPLOYERREG);
			// get the Ads
			populateAds (request, session, model,PageNames.EMPLOYER_REGISTRATION);
			model.addObject("errorMessage", "Captcha should not be blank");
			return model;
		}
		if (req.getParameter("recaptcha_response_field") != null) {
			recaptchaResponse = req
					.getParameter("recaptcha_response_field");
			recaptchaChallenge = req
					.getParameter("recaptcha_challenge_field");
			remoteAddr = req.getRemoteAddr();
		}
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey(MMJBCommonConstants.RECAPTCHA_PRIVATE_KEY);

		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
				remoteAddr, recaptchaChallenge, recaptchaResponse);
		// Send HTTP request to validate user's Captcha
		if (!reCaptchaResponse.isValid()) { 
			// Check if valid
			model.setViewName(EMPLOYERREG);
			// get the Ads
			populateAds (request, session, model, PageNames.EMPLOYER_REGISTRATION);
			model.addObject("errorMessage", "Captcha is invalid!");
			return model;
		}
		//Recaptcha code end here
		EmployerProfileDTO empDTO = new EmployerProfileDTO();
		UserDTO userDTO = transformEmpReg.createUserDTO(empRegForm);
		List<ProfileAttribDTO> attribLists = transformEmpReg
				.transformProfileAttribFormToDTO(empRegForm);
		empDTO.setAttribList(attribLists);
		empDTO.setMerUserDTO(userDTO);
		userDTO = employerRegistration.createUser(empDTO);
		
		// send welcome e-mail- starts
		try{
			sendEmployerWelcomeEmail(request, userDTO);
		}
		catch(Exception e){
			LOGGER.error("Mail sending failed : "+e);
		}
		// send welcome e-mail- Ends
		
		if (userDTO.getEmailId() == null) {
			model.addObject(MESSAGE, nsValidateUser);
			return model;
		} else {
			model.addObject(EMPREGFORM, empRegForm);
			session.setAttribute(MMJBCommonConstants.USER_NAME,
					userDTO.getFirstName() + " " + userDTO.getLastName());
			session.setAttribute(MMJBCommonConstants.USER_ID,
					userDTO.getUserId());
			session.setAttribute(MMJBCommonConstants.USER_EMAIL,
					userDTO.getEmailId());
			EmployerInfoDTO infoDTO = facilityService.facilityDetails(userDTO
					.getUserId());
			session.setAttribute(MMJBCommonConstants.FACILITY_ID,
					infoDTO.getFacilityId());
			session.setAttribute(MMJBCommonConstants.COMPANY_EMP,
					infoDTO.getCustomerName());
			model.addObject("viewMediaUrl", viewMediaUrl);
			model.setViewName("jobBoardEmployerPostJobs01");
			String role = MMJBCommonConstants.ROLE_FACILITY;
			if (empRegForm.isHelthSystem()) {
				role = MMJBCommonConstants.ROLE_FACILITY_GROUP;
			}
			authenticateUserAndSetSession(userDTO, req, role);
			// get the Ads
			populateAds (request, session, model, PageNames.EMPLOYER_POSTJOB_REG);
			return model;
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
		String loginPath = navigationPath.substring(2);
		String employerWelcomeMailBody = emailConfiguration.getProperty(
				"employer.welcome.mail.body").trim();
		String employerloginUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), loginPath)
				+ dothtmlExtention + employerPageExtention;
		employerWelcomeMailBody = employerWelcomeMailBody.replace("?user_name",
				userDTO.getFirstName());
		employerWelcomeMailBody = employerWelcomeMailBody.replace("?userName",
				userDTO.getFirstName());
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
	
	/**
	 * Populate the Ads for Employer Post Job and employer registration pages
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param pageName
	 */
	private void populateAds (HttpServletRequest request,
			HttpSession session, ModelAndView model, String pageName) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, pageName);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);

			
			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);		}
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
			if (form.getRequired() != 0
					&& StringUtils.isEmpty(form.getStrLabelValue())
					&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(form
							.getStrLabelName())) {
				model.addObject(MESSAGE, reqFields);
				return false;
			}
			// Checking validation for dropdowns & checkboxes etc
			if (form.getRequired() != 0
					&& MMJBCommonConstants.ZERO.equals(form.getStrLabelValue())
					&& (MMJBCommonConstants.DROP_DOWN.equals(form
							.getStrAttribType()) || MMJBCommonConstants.CHECK_BOX
							.equals(form.getStrAttribType()))) {
				model.addObject(MESSAGE, reqFields);
				return false;
			}
			// validation mobile number
			if (MMJBCommonConstants.PRIMARY_PHONE
					.equals(form.getStrLabelName())
					&& !StringUtils.isEmpty(form.getStrLabelValue())
					&& !registerValidation.validateMobileNumberPattern(form
							.getStrLabelValue())) {
				model.addObject(MESSAGE, jobseekerRegPhoneMsg);
				return false;
			} else if (MMJBCommonConstants.SECONDARY_PHONE.equals(form
					.getStrLabelName())
					&& !StringUtils.isEmpty(form.getStrLabelValue())
					&& !registerValidation.validateMobileNumberPattern(form
							.getStrLabelValue())) {
				model.addObject(MESSAGE, jobseekerRegPhoneMsg);
				return false;
			}
		}
		registerValidation.validate(empRegForm, result);
		if (!empRegForm.isbReadOnly()
				&& employerRegistration.validateEmail(empRegForm.getEmailId())) {
			result.rejectValue("emailId", "NotEmpty", emailExists);
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
	private void authenticateUserAndSetSession(UserDTO user,
			HttpServletRequest request, String role) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new GrantedAuthorityImpl(role));
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
			BindingResult result) {

		try {
			EmployerProfileDTO empDTO = new EmployerProfileDTO();
			UserDTO merUserDTO = transformEmpReg
					.transformEmpFormToMerUserDTO(form);
			empDTO.setMerUserDTO(merUserDTO);
			// Call to service layer
			employerRegistration.changePassword(empDTO);
			// model.put("jobSeekerRegistrationForm", jsRegistrationForm);
		} catch (Exception e) {
			LOGGER.info("Error occurred while changing the password." + e);
		}
		return "registrationsuccess";
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
			if(employeeAccountForm.isAdminLogin()){
				if(facilityService.getUser(employeeAccountForm.getEmail())!=null){
					return emailInUse;
				}
			}
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
				} 
				AccountProfileDTO dto = transformEmpReg
						.transformAccountProfileFormToDto(employeeAccountForm);
				// By passing netsuite call
				isUpdated = empRegService.editUser(dto, admfacilityid, userId,
						MMJBCommonConstants.PRIMARY);

				session.setAttribute(MMJBCommonConstants.USER_NAME,
						employeeAccountForm.getFirstName() + " "
								+ employeeAccountForm.getLastName());

				session.setAttribute(MMJBCommonConstants.COMPANY_EMP, employeeAccountForm.getCompany());
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
				isUpdated = empRegService.editUser(dto, admfacilityid, userId,
						MMJBCommonConstants.BILLING);
				if (isUpdated) {
					LOGGER.info("This is Account Addresss edite option done successfully");
				} else {
					return MMJBCommonConstants.UPDATE_ERROR;
				}

			} else {
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
			if(session.getAttribute("adminLogin")!=null ){
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

			
			AdmFacilityContactDTO listBillingForms = empRegService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);
			if ((listBillingForms.getCount() > 0)) {
				
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

}
