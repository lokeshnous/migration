/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.agency.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityContactDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.login.web.controller.LoginManager;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.user.UserService;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

/**
 * @author muralikc
 * 
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/agencyreg")
@SessionAttributes("agencyRegForm")
@Scope("session")
public class AgencyRegistrationController extends AbstractController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(AgencyRegistrationController.class);
	
	/** The Constant AGENCY_REG_FORM. */
	private static final String AGENCY_REG_FORM = "agencyRegForm";
	
	/** The Constant MESSAGE. */
	private static final String MESSAGE = "message";

	/** The agency registration. */
	@Autowired
	private ProfileRegistration agencyRegistration;

	/** The transform agency registration. */
	@Autowired
	private TransformAgencyRegistration transformAgencyRegistration;

	/** The facility service. */
	@Autowired
	private FacilityService facilityService;

	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/** The register validation. */
	@Autowired
	private AgencyRegistrationValidation registerValidation;

	/** The custom authentication manager. */
	@Autowired
	protected AuthenticationManager customAuthenticationManager;

	/** The ad service. */
	@Autowired
	private AdService adService;

	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The jobseeker reg phone msg. */
	@Value("${jobseekerRegPhoneMsg}")
	private String jobseekerRegPhoneMsg;
	
	/** The social signup msg. */
	@Value("${socialSignupMsg}")
	private String socialSignupMsg;

	/** The req fields. */
	@Value("${age.all.req.fields}")
	private String reqFields;

	/** The email exists. */
	@Value("${age.email.exists}")
	private String emailExists;

	/** The ns validate user. */
	@Value("${ns.validate.user}")
	private String nsValidateUser;

	/** The recaptcha response. */
	private String recaptchaResponse;
	
	/** The recaptcha challenge. */
	private String recaptchaChallenge;
	
	/** The remote addr. */
	private String remoteAddr;
	
	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	/** The navigation path. */
	@Value("${navigationPath}")
	private String navigationPath;
	
	/** The email service. */
	@Autowired
	private MMEmailService emailService;
	
	/** The login success manager. */
	@Autowired
	private LoginManager loginSuccessManager;
	
	/** The email configuration. */
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	/** The lookup service. */
	@Autowired
	private LookupService lookupService;
	
	/** The validate city state. */
	@Value("${validateCityState}")
	private String validateCityState;
	
	/** The Constant AGENCYREG. */
	private final static String AGENCYREG = "addAgencyRegistration";

	/**
	 * This method is called to display agency registration page
	 * 
	 * @param HttpSession
	 *            session
	 * @param String
	 *            profileId(Optional,used while displaying the the registration
	 *            page if user wants to register with his social media(e.g
	 *            Facebook,LinkedIn) account)
	 * @param String
	 *            serviceProviderId(Optional,used while displaying the the
	 *            registration page if user wants to register with his social
	 *            media(e.g Facebook,LinkedIn) account)
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/agencyregistration", method = RequestMethod.GET)
	public ModelAndView showAgencyRegistrationForm(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "profileId", required = false) String profileId,
			@RequestParam(value = "serviceProviderId", required = false) String serviceProviderId) {
		ModelAndView model = new ModelAndView();

		AgencyRegistrationForm agencyRegForm = new AgencyRegistrationForm();

		AgencyProfileDTO registerDTO = (AgencyProfileDTO) agencyRegistration
				.getProfileAttributes();
		UserDTO userDTO = null;
		
		if (session.getAttribute("advancePassUserDetails") != null) {
			userDTO = (UserDTO) session
					.getAttribute("advancePassUserDetails");
			agencyRegForm.setEmailId(userDTO.getEmailId());
			agencyRegForm.setConfirmEmailId(userDTO.getEmailId());
			agencyRegForm.setPassword(userDTO.getPassword());
			agencyRegForm.setConfirmPassword(userDTO.getPassword());
			agencyRegForm.setFirstName(userDTO.getFirstName());
			agencyRegForm.setLastName(userDTO.getLastName());
			agencyRegForm.setbReadOnly(true);
			agencyRegForm.setAdvPassUser(true);
			session.removeAttribute("advancePassUserDetails");
		}
		
		if (session.getAttribute(MMJBCommonConstants.USER_DTO) != null) {
			userDTO = (UserDTO) session
					.getAttribute(MMJBCommonConstants.USER_DTO);
			agencyRegForm.setEmailId(userDTO.getEmailId());
			agencyRegForm.setConfirmEmailId(userDTO.getEmailId());
			agencyRegForm.setPassword(userDTO.getPassword());
			agencyRegForm.setConfirmPassword(userDTO.getPassword());
			agencyRegForm.setUserId(userDTO.getUserId());
			agencyRegForm.setbReadOnly(true);
			agencyRegForm.setOldUSer(true);
		}

		if (profileId != null && !profileId.equals("null")) {
			agencyRegForm.setServiceProviderName(serviceProviderId);
			agencyRegForm.setSocialProfileId(profileId);
			agencyRegForm.setSocialSignUp(true);
			
			if(session.getAttribute("socialProfileAttrId")!=null){
				serviceProviderId="Social Media";
				if(String.valueOf(session.getAttribute("socialProfileAttrId")).equals(MMJBCommonConstants.FACEBOOK_PROFILE_ATTR_ID)){
					serviceProviderId="Facebook";
				}
				
				if(String.valueOf(session.getAttribute("socialProfileAttrId")).equals(MMJBCommonConstants.LINKEDIN_PROFILE_ATTR_ID)){
					serviceProviderId="LinkedIn";
				}
				
				model.addObject("socialSignUpMsg", socialSignupMsg.replace(
						"?serviceProviderId", serviceProviderId));
				session.removeAttribute("socialProfileId");
				session.removeAttribute("socialProfileAttrId");
			}
			else{
			model.addObject("socialSignUpMsg", socialSignupMsg.replace(
					"?serviceProviderId", serviceProviderId));
			}
		}
		if(session.getAttribute("FacilityContactDTO") != null){
			FacilityContactDTO facilityContactDTO= (FacilityContactDTO) session.getAttribute("FacilityContactDTO");
			agencyRegForm.setListProfAttribForms(transformAgencyRegistration.transformContactDTOToProfileAttribForm(registerDTO,facilityContactDTO,userDTO));
			session.removeAttribute("FacilityContactDTO");
		}else{
		List<AgencyProfileAttribForm> listProfAttribForms = transformAgencyRegistration
				.transformDTOToProfileAttribForm(registerDTO, userDTO);
		agencyRegForm.setListProfAttribForms(listProfAttribForms);
		}
		model.addObject(AGENCY_REG_FORM, agencyRegForm);
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);

		populateAds(request, session, model);
		model.setViewName(AGENCYREG);
		return model;
	}
	
	/**
	 * The method helps to populate the ads for the page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 */
	private void populateAds(HttpServletRequest request, HttpSession session,
			ModelAndView model) {
		// Add the Ads
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.AGENCY_REGISTRATION);
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
			LOGGER.error("Error occurred while getting the html content for Ads"
					+ e);
		}
	}

	/**
	 * This method is called to save the agency registration information
	 * 
	 * @param AgencyRegistrationForm
	 *            agencyRegistrationForm
	 * @param HttpSession
	 *            session
	 * @param HttpServletRequest
	 *            req
	 * @param BindingResult
	 *            result
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/saveAgencyRegistraion", method = RequestMethod.POST)
	public ModelAndView saveEmployerRegistration(
			@ModelAttribute(AGENCY_REG_FORM) AgencyRegistrationForm agencyRegistrationForm,
			HttpSession session, HttpServletRequest req,HttpServletResponse response, BindingResult result) {
		ModelAndView model = new ModelAndView();
		boolean advPassUser=false;
		boolean advPassUserWithNullPass=false;
		populateAds(req, session, model);
		
		if(!agencyRegistrationForm.isAdvPassUser()){
			advPassUserWithNullPass=userService.checkAdvUserPassword(agencyRegistrationForm.getEmailId());
			}
		if(!agencyRegistrationForm.isAdvPassUser()){
			advPassUser=userService.checkUserMail(agencyRegistrationForm.getEmailId());
		}
		if (null != agencyRegistrationForm.getListProfAttribForms()) {
			model.setViewName(AGENCYREG);
			if (!validateEmpRegForm(agencyRegistrationForm, model, result,req,advPassUser,advPassUserWithNullPass)) {
				return model;
			}
		}
		// Spring Recaptcha Starts here
		if (StringUtils.isEmpty(req.getParameter("recaptcha_response_field"))) {
			model.setViewName(AGENCYREG);
			model.addObject("errorMessage", "Captcha should not be blank");
			return model;
		}
		if (req.getParameter("recaptcha_response_field") != null) {
			recaptchaResponse = req.getParameter("recaptcha_response_field");
			recaptchaChallenge = req.getParameter("recaptcha_challenge_field");
			remoteAddr = req.getRemoteAddr();
		}
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey(MMJBCommonConstants.RECAPTCHA_PRIVATE_KEY);
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr,
				recaptchaChallenge, recaptchaResponse);
		// Send HTTP request to validate user's Captcha
		if (!reCaptchaResponse.isValid()) {
			// Check if valid
			model.setViewName(AGENCYREG);
			model.addObject("errorMessage", "Captcha is invalid!");
			return model;
		}
		// Recaptcha ends here
		AgencyProfileDTO empDTO = new AgencyProfileDTO();
		UserDTO userDTO = transformAgencyRegistration
				.createUserDTO(agencyRegistrationForm);
		userDTO.setAdvPassUserWithNullPass(advPassUserWithNullPass);
		List<ProfileAttribDTO> attribLists = transformAgencyRegistration
				.transformProfileAttribFormToDTO(agencyRegistrationForm);
		empDTO.setAttribList(attribLists);
		if (agencyRegistrationForm.isOldUSer()) {
			userDTO.setFacilityId((Integer) session
					.getAttribute(MMJBCommonConstants.FACILITY_ID));
		}
		empDTO.setMerUserDTO(userDTO);
		userDTO = agencyRegistration.createUser(empDTO);
		// send welcome e-mail- starts
				try{
					sendEmployerWelcomeEmail(req, userDTO);
				}
				catch(Exception e){
					LOGGER.error("Mail sending failed : "+e);
				}
				// send welcome e-mail- Ends
		if (userDTO.getEmailId() == null) {
			model.addObject(MESSAGE, nsValidateUser);
			return model;
		} else {
			model.addObject(AGENCY_REG_FORM, agencyRegistrationForm);
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
			model.setViewName("redirect:/agency/agencyDashboard.html");
		}
		authenticateUserAndSetSession(userDTO, req,response);
		LOGGER.debug("Registration is completed.");
		return null;
	}

	/**
	 * Validate emp reg form.
	 *
	 * @param agencyRegistrationForm the agency registration form
	 * @param model the model
	 * @param result the result
	 * @param req the req
	 * @param advPassUser the adv pass user
	 * @return true, if successful
	 */
	private boolean validateEmpRegForm(
			AgencyRegistrationForm agencyRegistrationForm, ModelAndView model,
			BindingResult result, HttpServletRequest req,Boolean advPassUser, Boolean advPassUserWithNullPass) {
		boolean status = true;

		if (null != agencyRegistrationForm.getListProfAttribForms()) {
			model.setViewName(AGENCYREG);
			String state=null,city=null,zipCode=null;
			for (AgencyProfileAttribForm form : agencyRegistrationForm
					.getListProfAttribForms()) {

				// Checking validation for input text box
				if (form.getRequired() != 0
						&& StringUtils.isBlank(form.getStrLabelValue())
						&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(form
								.getStrLabelName())) {
					model.addObject(MESSAGE, reqFields);
					return false;
				}

				// Checking validation for dropdowns & checkboxes etc
				if (form.getRequired() != 0
						&& MMJBCommonConstants.ZERO.equals(form
								.getStrLabelValue())
						&& (MMJBCommonConstants.DROP_DOWN.equals(form
								.getStrAttribType()) || MMJBCommonConstants.CHECK_BOX
								.equals(form.getStrAttribType()))) {
					model.addObject(MESSAGE, reqFields);
					return false;
				}
				// validation mobile number
				if (MMJBCommonConstants.PRIMARY_PHONE.equals(form
						.getStrLabelName())
						&& !StringUtils.isEmpty(form.getStrLabelValue())
						&& !registerValidation.validateMobileNumberPattern(form
								.getStrLabelValue())) {
					model.addObject(MESSAGE, jobseekerRegPhoneMsg);
					return false;
				}
				if (MMJBCommonConstants.SECONDARY_PHONE.equals(form
						.getStrLabelName())
						&& !StringUtils.isEmpty(form.getStrLabelValue())
						&& !registerValidation.validateMobileNumberPattern(form
								.getStrLabelValue())) {
					model.addObject(MESSAGE, jobseekerRegPhoneMsg);
					return false;
				}
				if (form.getStrLabelName() != null
						&& form.getStrLabelName().equalsIgnoreCase(
								MMJBCommonConstants.LABEL_STATE)) {
					state=form
					.getStrLabelValue();
				}
				if (form.getStrLabelName() != null
						&& form.getStrLabelName().equalsIgnoreCase(
								MMJBCommonConstants.CITY_EMP)) {
					city=form
					.getStrLabelValue();
				}
				if (form.getStrLabelName() != null
						&& form.getStrLabelName().equalsIgnoreCase(
								MMJBCommonConstants.ZIP_CODE)) {
					zipCode=form
					.getStrLabelValue();
				}
				if (null != state && null != city && null != zipCode) {
					boolean validateStateCityZip;
					try {
						validateStateCityZip = lookupService
								.validateCityStateZip(city, state, zipCode);
						if (!validateStateCityZip) {
							model.addObject(MESSAGE, validateCityState);
							return false;
						}
					} catch (JobBoardServiceException e) {
						e.printStackTrace();
					}

				}
			}
		}
		registerValidation.validate(agencyRegistrationForm, result);

		if (result.hasErrors()) {
			// model.setViewName(AGENCYREG);
			return false;
		}
		if(!advPassUserWithNullPass){
		if ((!agencyRegistrationForm.isbReadOnly()
				&& agencyRegistration.validateEmail(agencyRegistrationForm
						.getEmailId()) && advPassUser)|| (agencyRegistration.validateEmail(agencyRegistrationForm
								.getEmailId()) && !agencyRegistrationForm.isAdvPassUser() && !agencyRegistrationForm.isOldUSer())) {
			result.rejectValue("emailId", "NotEmpty", emailExists.replace(
					"?ageLoginLink",req.getRequestURL().toString()
					.replace(req.getServletPath(),"/commonlogin/login.html?page=agency")));
			// model.setViewName(AGENCYREG);
			return false;
		}
		}

		return status;
	}

	/**
	 * @param user
	 * @param request
	 */
	private void authenticateUserAndSetSession(UserDTO user,
			HttpServletRequest request,HttpServletResponse response) {
		try{
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new GrantedAuthorityImpl(
				MMJBCommonConstants.ROLE_FACILITY_SYSTEM));
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getEmailId(), user.getPassword(), authList);

		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = customAuthenticationManager
				.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		request.setAttribute("userRegistration","agencyRegistration");
		loginSuccessManager.onAuthenticationSuccess(request, response,
				authenticatedUser);
	} catch (Exception e) {
		LOGGER.error("Exception while authenticating Agency while registration ("
				+ e.getMessage() + ")");
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
