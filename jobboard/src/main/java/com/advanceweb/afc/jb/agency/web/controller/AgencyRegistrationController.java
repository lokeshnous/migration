package com.advanceweb.afc.jb.agency.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author muralikc
 * 
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/agencyRegistration")
@SessionAttributes("agencyRegForm")
@Scope("session")
public class AgencyRegistrationController {

	private static final Logger LOGGER = Logger
			.getLogger(AgencyRegistrationController.class);
	private static final String AGENCY_REG_FORM = "agencyRegForm";
	private static final String MESSAGE = "message";
	@Autowired
	private ProfileRegistration agencyRegistration;

	@Autowired
	private TransformAgencyRegistration transformAgencyRegistration;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private AgencyRegistrationValidation registerValidation;

	@Autowired
	protected AuthenticationManager customAuthenticationManager;

	@Value("${jobseekerRegPhoneMsg}")
	private String jobseekerRegPhoneMsg;

	@Value("${age.all.req.fields}")
	private String reqFields;

	@Value("${age.email.exists}")
	private String emailExists;

	@Value("${ns.validate.user}")
	private String nsValidateUser;

	@Autowired
	private LoginService loginService;

	private final static String AGENCYREG = "addAjecncyRegistration";

	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/agencyregistration", method = RequestMethod.GET)
	public ModelAndView agencyRegistration(HttpSession session) {
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
		List<AgencyProfileAttribForm> listProfAttribForms = transformAgencyRegistration
				.transformDTOToProfileAttribForm(registerDTO, userDTO);
		agencyRegForm.setListProfAttribForms(listProfAttribForms);
		model.addObject("agencyRegForm", agencyRegForm);
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		model.setViewName(AGENCYREG);
		return model;
	}

	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveAgencyRegistraion", method = RequestMethod.POST)
	public ModelAndView saveEmployerRegistration(
			@ModelAttribute(AGENCY_REG_FORM) AgencyRegistrationForm agencyRegistrationForm,
			HttpServletRequest request, Map<?, ?> map, HttpSession session,
			BindingResult result) {
		ModelAndView model = new ModelAndView();

		if (null != agencyRegistrationForm.getListProfAttribForms()) {
			model.setViewName(AGENCYREG);
			if (!validateEmpRegForm(agencyRegistrationForm, model, result)) {
				return model;
			}
		}

		AgencyProfileDTO empDTO = new AgencyProfileDTO();
		UserDTO userDTO = transformAgencyRegistration
				.createUserDTO(agencyRegistrationForm);
		List<ProfileAttribDTO> attribLists = transformAgencyRegistration
				.transformProfileAttribFormToDTO(agencyRegistrationForm
						.getListProfAttribForms());
		empDTO.setAttribList(attribLists);
		empDTO.setMerUserDTO(userDTO);
		userDTO = agencyRegistration.createUser(empDTO);

		if (userDTO.getEmailId() == null) {
			model.addObject("message", nsValidateUser);
			return model;
		} else {
			model.addObject("agencyRegForm", agencyRegistrationForm);
			session.setAttribute(MMJBCommonConstants.USER_NAME,
					userDTO.getFirstName() + " " + userDTO.getLastName());
			session.setAttribute(MMJBCommonConstants.USER_ID,
					userDTO.getUserId());
			session.setAttribute(MMJBCommonConstants.USER_EMAIL,
					userDTO.getEmailId());
			EmployerInfoDTO infoDTO = loginService.facilityDetails(userDTO
					.getUserId());
			session.setAttribute(MMJBCommonConstants.FACILITY_ID,
					infoDTO.getFacilityId());
			model.setViewName("redirect:/agency/agencyDashboard.html");
		}
		authenticateUserAndSetSession(userDTO, request);
		LOGGER.info("Registration is completed.");
		return model;
	}

	private boolean validateEmpRegForm(
			AgencyRegistrationForm agencyRegistrationForm, ModelAndView model,
			BindingResult result) {
		boolean status = true;

		if (null != agencyRegistrationForm.getListProfAttribForms()) {
			model.setViewName("addAjecncyRegistration");
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

}
