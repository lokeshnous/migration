package com.advanceweb.afc.jb.agency.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
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
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

/**
 * @author muralikc
 * 
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/agencyRegistration")
@SessionAttributes("agencyRegForm")
@Scope("session")
public class AgencyRegistrationController extends AbstractController {

	private static final Logger LOGGER = Logger
			.getLogger(AgencyRegistrationController.class);
	private static final String AGENCY_REG_FORM = "agencyRegForm";
	private static final String MESSAGE = "message";

	@Autowired
	private ProfileRegistration agencyRegistration;

	@Autowired
	private TransformAgencyRegistration transformAgencyRegistration;

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private AgencyRegistrationValidation registerValidation;

	@Autowired
	protected AuthenticationManager customAuthenticationManager;

	@Autowired
	private AdService adService;

	@Value("${jobseekerRegPhoneMsg}")
	private String jobseekerRegPhoneMsg;
	@Value("${socialSignupMsg}")
	private String socialSignupMsg;

	@Value("${age.all.req.fields}")
	private String reqFields;

	@Value("${age.email.exists}")
	private String emailExists;

	@Value("${ns.validate.user}")
	private String nsValidateUser;

	private String recaptchaResponse;
	private String recaptchaChallenge;
	private String remoteAddr;
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;

	@Value("${navigationPath}")
	private String navigationPath;
	@Value("${employerPageExtention}")
	private String employerPageExtention;
	@Autowired
	private MMEmailService emailService;

	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;
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
		if (session.getAttribute(MMJBCommonConstants.USER_DTO) != null) {
			userDTO = (UserDTO) session
					.getAttribute(MMJBCommonConstants.USER_DTO);
			agencyRegForm.setEmailId(userDTO.getEmailId());
			agencyRegForm.setConfirmEmailId(userDTO.getEmailId());
			agencyRegForm.setPassword(userDTO.getPassword());
			agencyRegForm.setConfirmPassword(userDTO.getPassword());
			agencyRegForm.setUserId(userDTO.getUserId());
			agencyRegForm.setbReadOnly(true);
		}

		if (profileId != null) {
			agencyRegForm.setServiceProviderName(serviceProviderId);
			agencyRegForm.setSocialProfileId(profileId);
			agencyRegForm.setSocialSignUp(true);
			model.addObject("socialSignUpMsg", socialSignupMsg.replace(
					"?serviceProviderId", serviceProviderId));
		}
		List<AgencyProfileAttribForm> listProfAttribForms = transformAgencyRegistration
				.transformDTOToProfileAttribForm(registerDTO, userDTO);
		agencyRegForm.setListProfAttribForms(listProfAttribForms);
		model.addObject(AGENCY_REG_FORM, agencyRegForm);
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);

		getAds(request, session, model);
		model.setViewName(AGENCYREG);
		return model;
	}

	private void getAds(HttpServletRequest request, HttpSession session,
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
			model.addObject("adPageTop", bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageRightTop", bannerString);

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_MIDDLE;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageRightMiddle", bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject("adPageBottom", bannerString);
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
			HttpSession session, HttpServletRequest req, BindingResult result) {
		ModelAndView model = new ModelAndView();

		getAds(req, session, model);

		if (null != agencyRegistrationForm.getListProfAttribForms()) {
			model.setViewName(AGENCYREG);
			if (!validateEmpRegForm(agencyRegistrationForm, model, result)) {
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
		List<ProfileAttribDTO> attribLists = transformAgencyRegistration
				.transformProfileAttribFormToDTO(agencyRegistrationForm);
		empDTO.setAttribList(attribLists);
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
		authenticateUserAndSetSession(userDTO, req);
		LOGGER.info("Registration is completed.");
		return model;
	}

	private boolean validateEmpRegForm(
			AgencyRegistrationForm agencyRegistrationForm, ModelAndView model,
			BindingResult result) {
		boolean status = true;

		if (null != agencyRegistrationForm.getListProfAttribForms()) {
			model.setViewName(AGENCYREG);
			for (AgencyProfileAttribForm form : agencyRegistrationForm
					.getListProfAttribForms()) {

				// Checking validation for input text box
				if (form.getbRequired() != 0
						&& StringUtils.isEmpty(form.getStrLabelValue())
						&& !MMJBCommonConstants.EMAIL_ADDRESS.equals(form
								.getStrLabelName())) {
					model.addObject(MESSAGE, reqFields);
					return false;
				}

				// Checking validation for dropdowns & checkboxes etc
				if (form.getbRequired() != 0
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
			}
		}
		registerValidation.validate(agencyRegistrationForm, result);

		if (result.hasErrors()) {
			// model.setViewName(AGENCYREG);
			return false;
		}
		if (!agencyRegistrationForm.isbReadOnly()
				&& agencyRegistration.validateEmail(agencyRegistrationForm
						.getEmailId())) {
			result.rejectValue("emailId", "NotEmpty", emailExists);
			// model.setViewName(AGENCYREG);
			return false;
		}

		return status;
	}

	/**
	 * @param user
	 * @param request
	 */
	private void authenticateUserAndSetSession(UserDTO user,
			HttpServletRequest request) {
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
