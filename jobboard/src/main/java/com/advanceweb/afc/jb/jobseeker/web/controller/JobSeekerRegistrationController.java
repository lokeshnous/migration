package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
 * @Author : Sasibhushana
 @Version: 1.0
 @Created: Jul 12, 2012
 @Purpose: This class is used as controller for job seeker regigstration
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.login.web.controller.ChangePasswordForm;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.user.UserService;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

@Controller
@RequestMapping("/jobseekerregistration")
@SessionAttributes("registerForm")
@Scope("session")
public class JobSeekerRegistrationController extends AbstractController {
	private static final Logger LOGGER = Logger
			.getLogger(JobSeekerRegistrationController.class);
	@Autowired
	protected AuthenticationManager customAuthenticationManager;

	@Autowired
	private ProfileRegistration profileRegistration;

	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;

	@Autowired
	private JobSeekerRegistrationValidation registerValidation;

	@Value("${jobseekerRegPhoneMsg}")
	private String jobseekerRegPhoneMsg;

	@Value("${socialSignupMsg}")
	private String socialSignupMsg;

	@Value("${followuplinkfacebook}")
	private String followuplinkfacebook;

	@Value("${followuplinktwitter}")
	private String followuplinktwitter;

	@Value("${followuplinkyoutube}")
	private String followuplinkyoutube;

	@Value("${followuplinklinkedin}")
	private String followuplinklinkedin;

	@Value("${js.all.req.fields}")
	private String reqFields;

	@Value("${js.email.exists}")
	private String emailExists;

	@Autowired
	private AdService adService;

	@Value("${js.password.empty}")
	private String pwdEmpty;

	@Value("${js.conform.pass.empty}")
	private String conformPassEmpty;

	@Value("${js.pwd.hint}")
	private String pwdHint;

	@Value("${js.pwd.not.equal}")
	private String pwdNotEqual;

	@Value("${js.pwd.equal}")
	private String pwdEqual;

	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;
	@Value("${jobseekerPageExtention}")
	private String jobseekerPageExtention;
	@Value("${navigationPath}")
	private String navigationPath;
	@Value("${employerPageExtention}")
	private String employerPageExtention;
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	@Autowired
	private MMEmailService emailService;
	@Autowired
	private UserService userService;
	@Autowired
	private FacilityService facilityService;
	// Spring ReCaptcha

	private String recaptchaResponse;
	private String recaptchaChallenge;
	private String remoteAddr;
	private static final String JS_CREATE_ACCOUNT = "jobSeekerCreateAccount";

	private static final String REGISTER_FORM = "registerForm";

	/**
	 * This method is called to display job seeker registration page Step1
	 * Create Your Account
	 * 
	 * @param request
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createJobSeekerCreateYrAcct", method = RequestMethod.GET)
	public ModelAndView createJobSeekerRegistrationStep1(
			HttpSession session,
			@RequestParam(value = "profileId", required = false) String profileId,
			@RequestParam(value = "serviceProviderId", required = false) String serviceProviderId,
			HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		JobSeekerRegistrationForm registerForm = new JobSeekerRegistrationForm();

		UserDTO userDTO = null;
		if (session.getAttribute(MMJBCommonConstants.USER_DTO) != null) {
			userDTO = (UserDTO) session
					.getAttribute(MMJBCommonConstants.USER_DTO);
			registerForm = new JobSeekerRegistrationForm();
			registerForm.setPassword(userDTO.getPassword());
			registerForm.setRetypepassword(userDTO.getPassword());
			registerForm.setEmailId(userDTO.getEmailId());
			registerForm.setConfirmEmailId(userDTO.getEmailId());
			registerForm.setUserId(String.valueOf(userDTO.getUserId()));
			registerForm.setbReadOnly(true);
			session.setAttribute("userName", userDTO.getFirstName() + " "
					+ userDTO.getLastName());
			session.setAttribute(MMJBCommonConstants.USER_ID,
					userDTO.getUserId());
			session.setAttribute("userEmail", userDTO.getEmailId());
		}
		if (profileId != null) {
			registerForm.setServiceProviderName(serviceProviderId);
			registerForm.setSocialProfileId(profileId);
			registerForm.setSocialSignUp(true);
			model.addObject("socialSignUpMsg", socialSignupMsg.replace(
					"?serviceProviderId", serviceProviderId));
		}
		model.setViewName(JS_CREATE_ACCOUNT);
		model.addObject(REGISTER_FORM, registerForm);
		// get the Ads
		populateAds(request, session, model, PageNames.JOBSEEKER_REGISTRATION);
		return model;

	}

	/**
	 * populate Ads for job seeker create account page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param pageName 
	 */
	private void populateAds(HttpServletRequest request,
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

			size = AdSize.IAB_MEDIUM_RECTANGLE;
			position = AdPosition.RIGHT_TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPGRIGHT_TOP, bannerString);

			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
			
			if(pageName.equalsIgnoreCase(PageNames.JOBSEEKER_REGISTRATION_INFO)){
				size = AdSize.IAB_MEDIUM_RECTANGLE;
				position = AdPosition.RIGHT_MIDDLE;
				bannerString = adService.getBanner(clientContext, size, position)
						.getTag();
				model.addObject(MMJBCommonConstants.ADPGRIGHT_MIDDLE, bannerString);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method is called to display job seeker registration page Create Your
	 * information
	 * 
	 * @param request
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createJobSeekerYourInfo", method = RequestMethod.POST, params = "Next")
	public ModelAndView createJobSeekerRegistration(
			@ModelAttribute(REGISTER_FORM) JobSeekerRegistrationForm registerForm,
			BindingResult result, HttpServletRequest req, HttpSession session,
			HttpServletRequest request) {

		ModelAndView model = new ModelAndView();

		try {
			// Spring Recaptcha Starts here
			if (!registerForm.isbReadOnly()) { // it will be executed when the
												// user come's from Sign Up page
				registerValidation.validate(registerForm, result);

				if (result.hasErrors()) {
					model.setViewName(JS_CREATE_ACCOUNT);
					return model;
				}
				/**
				 * OpenAM code starts here for Validate Email-Id
				 * 
				 * @auther Santhosh Gampa
				 * @since Sep 4 2012
				 * 
				 */
				// boolean isinvaliduser = OpenAMEUtility
				// .openAMValidateEmail(registerForm.getEmailId());
				// if (isinvaliduser) {
				// LOGGER.info("OpenAM : user is already exist !");
				// model.setViewName(JS_CREATE_ACCOUNT);
				// result.rejectValue("emailId", "NotEmpty", emailExists);
				// return model;
				// } else {
				// LOGGER.info("OpenAM : valid user!");
				// }

				// Ends of OpenAM code

				if (profileRegistration
						.validateEmail(registerForm.getEmailId())) {
					model.setViewName(JS_CREATE_ACCOUNT);
					result.rejectValue("emailId", "NotEmpty", emailExists);
					return model;
				}
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
			if (StringUtils.isEmpty(req
					.getParameter("recaptcha_response_field"))) {
				model.setViewName(JS_CREATE_ACCOUNT);
				model.addObject("errorMessage", "Captcha should not be blank");
				return model;
			}
			if (!reCaptchaResponse.isValid()) { // Check if valid
				model.setViewName(JS_CREATE_ACCOUNT);
				model.addObject("errorMessage", "Captcha is invalid!");
				return model;
			}
			// Spring Recaptcha Ends here
			if (!registerForm.isClickBack()) {
				JobSeekerRegistrationDTO registerDTO = (JobSeekerRegistrationDTO) profileRegistration
						.getProfileAttributes();
				UserDTO userDTO = null;
				if (session.getAttribute(MMJBCommonConstants.USER_DTO) != null) {
					userDTO = (UserDTO) session
							.getAttribute(MMJBCommonConstants.USER_DTO);
				}
				List<JobSeekerProfileAttribForm> listProfAttribForms = transformJobSeekerRegistration
						.transformDTOToProfileAttribForm(registerDTO, userDTO);
				registerForm.setListProfAttribForms(listProfAttribForms);
			}
			model.setViewName("jobSeekerCreateAccountInfo");
			// get the Ads
			populateAds(request, session, model, PageNames.JOBSEEKER_REGISTRATION_INFO);
			model.addObject(REGISTER_FORM, registerForm);
			model.addObject(MMJBCommonConstants.FOLLOWUP_LINK_FACEBOOK,
					followuplinkfacebook);
			model.addObject(MMJBCommonConstants.FOLLOWUP_LINK_TWITTER,
					followuplinktwitter);
			model.addObject(MMJBCommonConstants.FOLLOWUP_LINK_YOUTUBE,
					followuplinkyoutube);
			model.addObject(MMJBCommonConstants.FOLLOWUP_LINK_LINKEDIN,
					followuplinklinkedin);
		} catch (Exception e) {
			// TODO
			LOGGER.error(e);
		}
		return model;

	}

	/**
	 * This method is called to save employee registration
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveJobSeekerProfile", method = RequestMethod.POST, params = "Finish")
	public ModelAndView saveJobSeekerRegistration(
			@ModelAttribute(REGISTER_FORM) JobSeekerRegistrationForm registerForm,
			BindingResult result, HttpSession session,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		HashMap<String, String> hashmap = new HashMap<String, String>();
		try {

			if (null != registerForm.getListProfAttribForms()) {
				model.setViewName("jobSeekerCreateAccountInfo");
				// get the Ads
				populateAds(request, session, model, PageNames.JOBSEEKER_REGISTRATION_INFO);
				for (JobSeekerProfileAttribForm form : registerForm
						.getListProfAttribForms()) {

					if (form.getStrLabelName() != null
							&& form.getStrLabelName().equalsIgnoreCase(
									MMJBCommonConstants.MYPROFESSION)) {
						for (DropDownDTO dropDown : form.getDropdown()) {
							if (MMJBCommonConstants.PROFESSION_OTHERS
									.equals(dropDown.getOptionName())
									&& form.getStrLabelValue().equals(
											dropDown.getOptionId())) {
								form.setStrLabelValue(registerForm
										.getOtherProfession());
							}
						}
					}

					if (form.getStrLabelName().equals("My Industry")) {
						hashmap.put(form.getStrLabelName(), "Health Care");
					} else {
						hashmap.put(form.getStrLabelName(),
								form.getStrLabelValue());
						// Checking validation for input text box
						if (form.getRequired() != 0
								&& StringUtils.isEmpty(form.getStrLabelValue())
								&& !MMJBCommonConstants.EMAIL_ADDRESS
										.equals(form.getStrLabelName())) {
							model.addObject("message", reqFields);
							return model;
						}
					}
					// Checking validation for dropdowns & checkboxes etc
					if (form.getRequired() != 0
							&& MMJBCommonConstants.ZERO.equals(form
									.getStrLabelValue())
							&& (MMJBCommonConstants.DROP_DOWN.equals(form
									.getStrAttribType()) || MMJBCommonConstants.CHECK_BOX
									.equals(form.getStrAttribType()))) {
						model.addObject("message", reqFields);
						return model;
					}
					// validation mobile number
					if (MMJBCommonConstants.PHONE_NUMBER.equals(form
							.getStrLabelName())
							&& !StringUtils.isEmpty(form.getStrLabelValue())
							&& !registerValidation
									.validateMobileNumberPattern(form
											.getStrLabelValue())) {
						model.addObject("message", jobseekerRegPhoneMsg);
						return model;
					}
				}
			}

			JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
			UserDTO userDTO = transformJobSeekerRegistration
					.createUserDTO(registerForm);
			List<ProfileAttribDTO> attribLists = transformJobSeekerRegistration
					.transformProfileAttribFormToDTO(
							registerForm.getListProfAttribForms(), registerForm);
			jsRegistrationDTO.setAttribList(attribLists);
			jsRegistrationDTO.setMerUserDTO(userDTO);

			// Call to service layer

			userDTO = profileRegistration.createUser(jsRegistrationDTO);

			/**
			 * OpenAM code starts here for creating users in OpenDJ
			 * 
			 * @auther Santhosh Gampa
			 * @since Sep 4 2012
			 * 
			 */
			// boolean isCreated = OpenAMEUtility.openAMCreateUser(userDTO,
			// hashmap);
			// LOGGER.info("Open AM : User is created!" + isCreated);
			// Ends of OpenAM code
			
			// send welcome e-mail- starts
			try{
				sendJobSeekerWelcomeEmail(session, request, userDTO);
			}
			catch(Exception e){
				LOGGER.error("Mail sending failed : "+e);
			}
			
			// send welcome e-mail- Ends
			session.setAttribute("userName",
					userDTO.getFirstName() + " " + userDTO.getLastName());
			session.setAttribute(MMJBCommonConstants.USER_ID, userDTO.getUserId());
			session.setAttribute("userEmail", userDTO.getEmailId());
			
			// send welcome email ends
			
			model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
			
			if (session.getAttribute("jobId") != null) {
				String jobTitle = (String) session.getAttribute("jobTitle");
				jobTitle = jobTitle.replace(" ", "-").toLowerCase();
				model.setViewName("redirect:/jobsearch/jobview/"
						+ session.getAttribute("jobId") +jobTitle+ ".html");
			}
			authenticateUserAndSetSession(userDTO, request);

		} catch (Exception e) {
			// TODO
			LOGGER.error(e);
		}
		return model;
	}

	/**
	 * Method To send Welcome Email
	 * @param session
	 * @param request
	 * @param userDTO
	 */
	private void sendJobSeekerWelcomeEmail(HttpSession session,
			HttpServletRequest request, UserDTO userDTO) {
		StringBuffer stringBuffer = new StringBuffer();
		InternetAddress[] jsToAddress = new InternetAddress[1];

		try {
			jsToAddress[0] = new InternetAddress(userDTO.getEmailId());
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}

		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setToAddress(jsToAddress);
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration.getProperty("welcome.mail.message")
				.trim());
		String loginPath = navigationPath.substring(2);
		String jonseekerloginUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), loginPath)
				+ dothtmlExtention + jobseekerPageExtention;
		String jobseekerwelcomemailbody = emailConfiguration.getProperty(
				"jobseeker.welcome.mail.body").trim();
		jobseekerwelcomemailbody = jobseekerwelcomemailbody.replace(
				"?jobSeekerFirstName", userDTO.getFirstName());
		jobseekerwelcomemailbody = jobseekerwelcomemailbody.replace(
				"?jsdashboardLink", jonseekerloginUrl);
		stringBuffer.append(emailConfiguration.getProperty(
				"jobseeker.email.header").trim());
		stringBuffer.append(jobseekerwelcomemailbody);
		stringBuffer.append(emailConfiguration.getProperty("email.footer")
				.trim());
		emailDTO.setBody(stringBuffer.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}

	private void authenticateUserAndSetSession(UserDTO user,
			HttpServletRequest request) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_JOB_SEEKER));
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getEmailId(), user.getPassword(), authList);
		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = customAuthenticationManager
				.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

	/**
	 * This method is called to save employee registration
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveJobSeekerProfile", method = RequestMethod.POST, params = "Back")
	public ModelAndView backToCreateJobSeekerCreateYrAcct(
			@ModelAttribute(REGISTER_FORM) JobSeekerRegistrationForm registerForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		registerForm.setClickBack(true);
		model.addObject(REGISTER_FORM, registerForm);
		model.setViewName(JS_CREATE_ACCOUNT);
		return model;
	}

	/**
	 * This method is called to navigate to home page on click of cancel
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveJobSeekerProfile", method = RequestMethod.POST, params = "Cancel")
	public ModelAndView backToHomePage() {

		return new ModelAndView("healthcarejobs/advanceweb.html", "", "");
	}

	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewJobSeekerProfile", method = RequestMethod.GET)
	public ModelAndView viewJobSeekerProfileSettings(HttpSession session) {

		ModelAndView model = new ModelAndView();
		try {
			JobSeekerRegistrationForm form = new JobSeekerRegistrationForm();
			// Call to service layer
			JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration
					.viewProfile((Integer) session
							.getAttribute(MMJBCommonConstants.USER_ID));
			List<JobSeekerProfileAttribForm> listProfAttribForms = transformJobSeekerRegistration
					.transformDTOToProfileAttribForm(jsRegistrationDTO, null);

			for (JobSeekerProfileAttribForm profileForm : listProfAttribForms) {

				if (profileForm.getStrLabelValue() != null
						&& profileForm.getStrLabelName().equalsIgnoreCase(
								MMJBCommonConstants.MYPROFESSION)
						&& !isInteger(profileForm.getStrLabelValue())) {
					form.setOtherProfession(profileForm.getStrLabelValue());
					for (DropDownDTO dropDown : profileForm.getDropdown()) {
						if (MMJBCommonConstants.PROFESSION_OTHERS
								.equals(dropDown.getOptionName())) {
							profileForm
									.setStrLabelValue(dropDown.getOptionId());
						}
					}
				}
			}

			form.setListProfAttribForms(listProfAttribForms);
			form.setEmailId(jsRegistrationDTO.getEmailId());
			model.addObject(REGISTER_FORM, form);
			model.setViewName("jobseekerEditProfileSettings");

		} catch (Exception e) {
			// TODO
			LOGGER.error(e);
		}
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
	@ResponseBody
	@RequestMapping(value = "/updateJobSeekerProfile", method = RequestMethod.POST)
	public String updateJobSeekerProfileSettings(
			@ModelAttribute(REGISTER_FORM) JobSeekerRegistrationForm registerForm,
			BindingResult result, HttpSession session) {
		String firstName = "";
		String lastName = "";
		/**
		 * Added by Santhosh Gampa for OpenAM integration
		 */
		HashMap<String, String> hm = new HashMap<String, String>();

		try {

			if (null != registerForm.getListProfAttribForms()) {
				for (JobSeekerProfileAttribForm form : registerForm
						.getListProfAttribForms()) {
					// Hold the required value for openAM
					hm.put(form.getStrLabelName(), form.getStrLabelValue());

					// Checking validation for input text box
					if (form.getRequired() != 0
							&& StringUtils.isEmpty(form.getStrLabelValue())) {
						return "Please fill the required fields";
					}

					// Checking validation for dropdowns & checkboxes etc
					if (form.getRequired() != 0
							&& !MMJBCommonConstants.LABEL_SUSBSCRIPTION
									.equals(form.getStrLabelName())
							&& MMJBCommonConstants.ZERO.equals(form
									.getStrLabelValue())
							&& (MMJBCommonConstants.DROP_DOWN.equals(form
									.getStrAttribType()) || MMJBCommonConstants.CHECK_BOX
									.equals(form.getStrAttribType()))) {
						return "Please fill the required fields";
					}
					// validation mobile number
					if (MMJBCommonConstants.PHONE_NUMBER.equals(form
							.getStrLabelName())
							&& !StringUtils.isEmpty(form.getStrLabelValue())
							&& !registerValidation
									.validateMobileNumberPattern(form
											.getStrLabelValue())) {
						return jobseekerRegPhoneMsg;
					}

					// validation mobile number
					if (MMJBCommonConstants.EMAIL_ADDRESS.equals(form
							.getStrLabelName())) {
						if (!registerValidation.validateEmailPattern(form
								.getStrLabelValue())) {
							return "Please enter the correct E-Mail Address";
						} else {
							if (!(registerForm.getEmailId().equals(form
									.getStrLabelValue()))
									&& profileRegistration.validateEmail(form
											.getStrLabelValue())) {
								return "Email address already exists";
							}
						}
					}
				}
			}

			JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
			List<ProfileAttribDTO> attribList = transformJobSeekerRegistration
					.transformProfileAttribFormToDTO(
							registerForm.getListProfAttribForms(), registerForm);
			UserDTO userDTO = transformJobSeekerRegistration
					.createUserDTO(registerForm);
			userDTO.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));
			jsRegistrationDTO.setAttribList(attribList);
			jsRegistrationDTO.setMerUserDTO(userDTO);

			// OpenAM code for Modify the user profile details
			// Added by Santhosh Gampa on Sep 4 2012
			// boolean isUdated = OpenAMEUtility.openAMUpdateUser(userDTO, hm);
			// LOGGER.info("User Profile updated in OpenAM - " + isUdated);
			// End of OpenAM code

			// Call to service layer
			profileRegistration.modifyProfile(jsRegistrationDTO);

			for (JobSeekerProfileAttribForm attribForm : registerForm
					.getListProfAttribForms()) {
				if (attribForm.getStrLabelName().equalsIgnoreCase(
						MMJBCommonConstants.FIRST_NAME)) {
					firstName = attribForm.getStrLabelValue();
				} else if (attribForm.getStrLabelName().equalsIgnoreCase(
						MMJBCommonConstants.LAST_NAME)) {
					lastName = attribForm.getStrLabelValue();
					// break;
				}
			}

			session.setAttribute(MMJBCommonConstants.USER_NAME, firstName + " "
					+ lastName);

		} catch (Exception e) {
			// TODO
			LOGGER.error(e);
		}

		return "";
	}

	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jobSeekerUpdatePassword", method = RequestMethod.POST)
	public String updateNewPassword(ChangePasswordForm form,
			HttpSession session, HttpServletRequest request) {
		try {

			JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();

			String errorMessage = validatePasswords(form.getPassword(),
					form.getRetypepassword(), form.getCurrentPassword());
			if (!StringUtils.isEmpty(errorMessage)) {
				return errorMessage;
			}

			UserDTO userDTO = transformJobSeekerRegistration
					.transformChangePasswordFormToMerUserDTO(form);
			userDTO.setUserId((Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID));

			jsRegistrationDTO.setMerUserDTO(userDTO);

			// Call to service layer
			if (profileRegistration.validatePassword(jsRegistrationDTO)) {

				// OpenAM User Update password
				// Added by Santhosh Gampa on 4th Sep 2012
				// boolean isUdated = OpenAMEUtility.openAMUpdatePassword(
				// form.getEmailId(), form.getPassword());
				// LOGGER.info("User password updated - " + isUdated);
				// Ends OpenAM User Update password

				profileRegistration.changePassword(jsRegistrationDTO);

				sendUpdatedPasswordMail(session, request, jsRegistrationDTO);
			} else {
				return "Invalid Current Password";
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return "";
	}

	/**
	 * This method is called to validate passwords
	 * 
	 * @param password
	 * @param retypePassword
	 * @return
	 */
	private String validatePasswords(String password, String retypePassword,
			String currentPassword) {
		if (StringUtils.isEmpty(password)) {
			return pwdEmpty;
		}

		if (StringUtils.isEmpty(retypePassword)) {
			return conformPassEmpty;
		}

		if (!StringUtils.isEmpty(password)
				&& !StringUtils.isEmpty(retypePassword)) {

			if (!registerValidation.validatePasswordPattern(password)) {
				return pwdHint;
			}

			if (!registerValidation.validatePasswordPattern(retypePassword)) {
				return pwdHint;
			}

			if (!password.equals(retypePassword)) {
				return pwdNotEqual;
			}

			if (currentPassword.equals(password)) {
				return pwdEqual;
			}
		}

		return null;
	}

	/**
	 * This method is called to view/modify job seeker profile settings
	 * 
	 * @param jobSeekerRegistrationForm
	 * @param result
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/jobSeekerChangePassword", method = RequestMethod.GET)
	public String jsChangePassword(Map model, HttpSession session) {

		try {
			ChangePasswordForm form = new ChangePasswordForm();
			form.setEmailId((String) session.getAttribute("userEmail"));
			model.put("changePasswordForm", form);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return "jobseekerchangepassword";
	}

	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void sendUpdatedPasswordMail(HttpSession session,
			HttpServletRequest request,
			JobSeekerRegistrationDTO jsRegistrationDTO) {
		StringBuffer stringBuffer = new StringBuffer();
		
		if (null != jsRegistrationDTO.getMerUserDTO()
				&& null != jsRegistrationDTO.getMerUserDTO().getEmailId()) {
			UserDTO userDTO = userService.getUser(jsRegistrationDTO
					.getMerUserDTO().getEmailId());
			EmployerInfoDTO facilityDetail =facilityService.facilityDetails(userDTO.getUserId());
			if(null !=facilityDetail){
			userDTO.setCompany(facilityDetail.getCustomerName());
			}
			List<UserRoleDTO> userRoleDTOs = userService.getUserRole(userDTO
					.getUserId());
			String userRole = userRoleDTOs.get(0).getRoleName();
			String loginPath = navigationPath.substring(2);
			EmailDTO emailDTO =prepareEmailDTO(session, request, jsRegistrationDTO, stringBuffer,
					userDTO, userRole, loginPath);

			emailDTO.setHtmlFormat(true);
			emailService.sendEmail(emailDTO);
			LOGGER.info("Mail sent to JobSeeker");
		}

	}

	/**
	 * @param session
	 * @param request
	 * @param emailDTO
	 * @param stringBuffer
	 * @param userDTO
	 * @param userRole
	 * @param loginPath
	 */
	private EmailDTO prepareEmailDTO(HttpSession session,
			HttpServletRequest request, JobSeekerRegistrationDTO jsRegistrationDTO,
			StringBuffer stringBuffer, UserDTO userDTO, String userRole,
			String loginPath) {
		EmailDTO emailDTO = new EmailDTO();
		InternetAddress[] jsToAddress = new InternetAddress[1];

		try {
			jsToAddress[0] = new InternetAddress(jsRegistrationDTO
					.getMerUserDTO().getEmailId());
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}
		emailDTO.setToAddress(jsToAddress);
		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration
				.getProperty("change.password.successful.subject").trim());
		if (null != userRole && userRole.equals(MMJBCommonConstants.JOBSEEKER)) {
			String jonseekerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + jobseekerPageExtention;
			String jobSeekerChangePwdBody=emailConfiguration
					.getProperty("jobseeker.change.pwdbody").trim(); 
			
			jobSeekerChangePwdBody=jobSeekerChangePwdBody.replace("?jobSeekerFirstName",
						userDTO.getFirstName());
			jobSeekerChangePwdBody=jobSeekerChangePwdBody.replace("?jsdashboardLink",
						jonseekerloginUrl);
			stringBuffer.append(emailConfiguration
					.getProperty("jobseeker.email.header").trim());
			stringBuffer.append(jobSeekerChangePwdBody);
			stringBuffer.append(emailConfiguration
					.getProperty("email.footer").trim());
			emailDTO.setBody(stringBuffer.toString());
		} else {
			String employerloginUrl = request.getRequestURL().toString()
					.replace(request.getServletPath(), loginPath)
					+ dothtmlExtention + employerPageExtention;
			String employerChangePwdBody = emailConfiguration.getProperty(
					"employer.change.pwdbody").trim();
			employerChangePwdBody = employerChangePwdBody.replace(
					"?user_name", (String) session
							.getAttribute(MMJBCommonConstants.USER_NAME));
			employerChangePwdBody = employerChangePwdBody.replace(
					"?company_name", userDTO.getCompany());
			employerChangePwdBody = employerChangePwdBody.replace(
					"?empdashboardLink", employerloginUrl);

			stringBuffer.append(emailConfiguration
					.getProperty("employer.email.header").trim());
			stringBuffer.append(employerChangePwdBody);
			stringBuffer.append(emailConfiguration
					.getProperty("email.footer").trim());
			emailDTO.setBody(stringBuffer.toString());
		}
		return emailDTO;
	}
}
