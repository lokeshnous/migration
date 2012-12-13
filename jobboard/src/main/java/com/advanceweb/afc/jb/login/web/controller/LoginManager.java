package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.user.UserService;

public class LoginManager extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private UserService userService;

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ProfileRegistration profileRegistration;
	
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String pageValue = request.getParameter(MMJBCommonConstants.PAGE_VALUE);
		String socialSignUp = (String) request.getAttribute("socialSignUp");
		UserDTO user = userService.getUser(authentication.getName());
		HttpSession session;
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			session = request.getSession(false);
		} else {
			session = request.getSession();
		}

		if (authentication.getAuthorities().contains(
				new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_MERION_ADMIN))) {
			session.setAttribute(MMJBCommonConstants.FACILITY_FULL_ACCESS,
					MMJBCommonConstants.FACILITY_FULL_ACCESS);
			session.setAttribute("adminLogin", "adminLogin");
			String cookieName = MMJBCommonConstants.SPRING_SECURITY_REMEMBER_ME_COOKIE;
			Cookie cookie = new Cookie(cookieName, null);
			cookie.setMaxAge(0);
			cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request
					.getContextPath() : "/");
			response.addCookie(cookie);
		}
		session.setAttribute(MMJBCommonConstants.USER_ID, user.getUserId());
		session.setAttribute(MMJBCommonConstants.USER_NAME, user.getFirstName()
				+ " " + user.getLastName());
		session.setAttribute(MMJBCommonConstants.USER_EMAIL, user.getEmailId());

		if (isJobSeeker(authentication, pageValue)) {
			session.setAttribute(MMJBCommonConstants.LOGIN_DATE_TIME,
					new Date());
			redirectJobSeeker(user, request, response, session);
		} else if (isFacility(authentication, pageValue)) {

			/**
			 * Added to put facility id in the session
			 */
			EmployerInfoDTO infoDTO = facilityService.facilityDetails(user
					.getUserId());
			session.setAttribute(MMJBCommonConstants.FACILITY_ID,
					infoDTO.getFacilityId());
			session.setAttribute(MMJBCommonConstants.COMPANY_EMP,
					infoDTO.getCustomerName());
			redirectFacility(user, request, response, session);
		} else if (isFacilitySystem(authentication, pageValue)) {

			/**
			 * Added to put facility id in the session
			 */
			EmployerInfoDTO infoDTO = facilityService.facilityDetails(user
					.getUserId());
			session.setAttribute(MMJBCommonConstants.FACILITY_ID,
					infoDTO.getFacilityId());
			session.setAttribute(MMJBCommonConstants.COMPANY_EMP,
					infoDTO.getCustomerName());
			redirectFacilitySystem(user, request, response, session);
		} else {
			boolean socalLogin = false;
			if (socialSignUp != null) {
				socalLogin = true;
			}
			session.invalidate();
			sendRedirect(request, response,
					"/commonLogin/login.html?error=true&page=" + pageValue
							+ "&socalLogin=" + socalLogin);

		}
		
	}

	/**
	 * Method to redirect to the target URL
	 * 
	 * @param request
	 * @param response
	 * @param target
	 * @throws IOException
	 */
	private void sendRedirect(HttpServletRequest request,
			HttpServletResponse response, String target) throws IOException {

		response.sendRedirect(request.getContextPath() + target);
	}

	/**
	 * Method return true if authenticated by FACILITY SYSTEM otherwise false.
	 * 
	 * @param authentication
	 * @param pageValue
	 * @return
	 */
	public boolean isFacilitySystem(Authentication authentication,
			String pageValue) {
		if (authentication instanceof RememberMeAuthenticationToken) {
			return authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY_SYSTEM));
		} else {
			return authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY_SYSTEM))
					&& pageValue.equals(MMJBCommonConstants.AGENCY);
		}
	}

	/**
	 * Method return true if authenticated by FACILITY or FACILITY_GROUP
	 * otherwise false.
	 * 
	 * @param authentication
	 * @param pageValue
	 * @return
	 */
	public boolean isFacility(Authentication authentication, String pageValue) {
		if (authentication instanceof RememberMeAuthenticationToken) {
			return authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY))
					|| authentication.getAuthorities().contains(
							new SimpleGrantedAuthority(
									MMJBCommonConstants.ROLE_FACILITY_GROUP));
		} else {
			return (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY)) || authentication
					.getAuthorities().contains(
							new SimpleGrantedAuthority(
									MMJBCommonConstants.ROLE_FACILITY_GROUP)))
					&& pageValue.equals(MMJBCommonConstants.EMPLOYER);
		}
	}

	/**
	 * Method return true if authenticated by JOB SEEKER otherwise false.
	 * 
	 * @param authentication
	 * @param pageValue
	 * @return
	 */
	public boolean isJobSeeker(Authentication authentication, String pageValue) {
		if (authentication instanceof RememberMeAuthenticationToken) {
			return authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_JOB_SEEKER));
		} else {
			return authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_JOB_SEEKER))
					&& pageValue.equals(MMJBCommonConstants.JOB_SEEKER);
		}
	}

	/**
	 * Method to redirect job seeker to the corresponding page depending on the
	 * condition
	 * 
	 * @param UserDTO
	 *            user
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 *            response
	 * @param HttpSession
	 *            session
	 */
	private void redirectJobSeeker(UserDTO user, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ServletException {
		if (profileRegistration.validateProfileAttributes(user.getUserId())) {
			/**
			 * if the user already registered
			 */
			if (session.getAttribute("isRedirect") != null
					&& ((Boolean) session.getAttribute("isRedirect"))) {
				String jobTitle = (String) session.getAttribute("jobTitle");
				jobTitle = jobTitle.replace(" ", "-").toLowerCase();
				sendRedirect(
						request,
						response,
						"/jobsearch/jobview/"
								+ session.getAttribute("jobId")+
								"/"+jobTitle+dothtmlExtention);
			}else{
				sendRedirect(request, response,
						"/jobSeeker/jobSeekerDashBoard.html");
			}
		} else {
			/**
			 * if the logged in user is new
			 */
			session.setAttribute(MMJBCommonConstants.USER_DTO, user);
			sendRedirect(request, response,
					"/jobseekerregistration/createJobSeekerCreateYrAcct.html");

		}
	}

	/**
	 * Method to redirect facility to the corresponding page depending on the
	 * condition
	 * 
	 * @param UserDTO
	 *            user
	 * @param EmployerInfoDTO
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 *            response
	 * @param HttpSession
	 *            session
	 */
	private void redirectFacility(UserDTO user, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ServletException {

		if (profileRegistration.validateProfileAttributes(user.getUserId())) {
			sendRedirect(request, response, "/employer/employerDashBoard.html");

		} else {

			session.setAttribute(MMJBCommonConstants.USER_DTO, user);
			sendRedirect(request, response,
					"/employerRegistration/employerregistration.html");
		}

	}

	/**
	 * Method to redirect facility system to the corresponding page depending on
	 * the condition
	 * 
	 * @param UserDTO
	 *            user
	 * @param EmployerInfoDTO
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 *            response
	 * @param HttpSession
	 *            session
	 */
	private void redirectFacilitySystem(UserDTO user,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException, ServletException {

		if (profileRegistration.validateProfileAttributes(user.getUserId())) {

			sendRedirect(request, response, "/agency/agencyDashboard.html");

		} else {
			session.setAttribute(MMJBCommonConstants.USER_DTO, user);
			sendRedirect(request, response,
					"/agencyRegistration/agencyregistration.html");
		}

	}
}
