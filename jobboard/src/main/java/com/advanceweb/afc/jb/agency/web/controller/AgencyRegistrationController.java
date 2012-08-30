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
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.pgi.service.FetchAdmFacilityConatact;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author muralikc
 *
 */
@Controller
@RequestMapping("/agencyRegistration")
@SessionAttributes("agencyRegForm")
@Scope("session")
public class AgencyRegistrationController {

	private static final Logger LOGGER = Logger
			.getLogger("AgencyRegistrationController.class");

	@Autowired
	private ProfileRegistration agencyRegistration;

	@Autowired
	private TransformAgencyRegistration transformAgencyRegistration;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	FetchAdmFacilityConatact fetchAdmFacilityConatact;

	@Autowired
	AgencyRegistrationValidation registerValidation;

	@Autowired
	protected AuthenticationManager customAuthenticationManager;

	@Value("${jobseekerRegPhoneMsg}")
	private String jobseekerRegPhoneMsg;

	@Autowired
	private LoginService loginService;

	/**
	 * This method is called to display job seeker registration page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/agencyregistration", method = RequestMethod.GET)
	public ModelAndView agencyRegistration() {
		ModelAndView model = new ModelAndView();

		AgencyRegistrationForm agencyRegForm = new AgencyRegistrationForm();

		AgencyProfileDTO registerDTO = (AgencyProfileDTO) agencyRegistration
				.getProfileAttributes();
		List<AgencyProfileAttribForm> listProfAttribForms = transformAgencyRegistration
				.transformDTOToProfileAttribForm(registerDTO);
		agencyRegForm.setListProfAttribForms(listProfAttribForms);
		model.addObject("agencyRegForm", agencyRegForm);
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		model.setViewName("addAjecncyRegistration");
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
			@ModelAttribute("agencyRegForm") AgencyRegistrationForm agencyRegForm,
			HttpServletRequest request, Map map, HttpSession session,
			BindingResult result) {
		ModelAndView model = new ModelAndView();

		if (null != agencyRegForm.getListProfAttribForms()) {
			model.setViewName("addAjecncyRegistration");
			for (AgencyProfileAttribForm form : agencyRegForm
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
		registerValidation.validate(agencyRegForm, result);

		if (result.hasErrors()) {
			model.setViewName("addAjecncyRegistration");
			return model;
		}
		if (agencyRegistration.validateEmail(agencyRegForm.getEmailId())) {
			result.rejectValue("emailId", "NotEmpty",
					"Email Id already Exists!");
			model.setViewName("addAjecncyRegistration");
			return model;
		}

		AgencyProfileDTO empDTO = new AgencyProfileDTO();
		MerUserDTO userDTO = transformAgencyRegistration
				.createUserDTO(agencyRegForm);
		List<MerProfileAttribDTO> attribLists = transformAgencyRegistration
				.transformProfileAttribFormToDTO(agencyRegForm
						.getListProfAttribForms());
		empDTO.setAttribList(attribLists);
		empDTO.setMerUserDTO(userDTO);
		userDTO = agencyRegistration.createNewProfile(empDTO);

		model.addObject("agencyRegForm", agencyRegForm);
		session.setAttribute(MMJBCommonConstants.USER_NAME,
				userDTO.getFirstName() + " " + userDTO.getLastName());
		session.setAttribute(MMJBCommonConstants.USER_ID, userDTO.getUserId());
		session.setAttribute(MMJBCommonConstants.USER_EMAIL,
				userDTO.getEmailId());
		EmployerInfoDTO infoDTO = loginService.facilityDetails(userDTO
				.getUserId());
		session.setAttribute(MMJBCommonConstants.FACILITY_ID,
				infoDTO.getFacilityId());
		model.setViewName("agencyDashboard");
		authenticateUserAndSetSession(userDTO, request);

		return model;
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

}
