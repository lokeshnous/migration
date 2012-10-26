package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.user.ProfileRegistration;

public class LoginManager extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private LoginService loginService;

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ProfileRegistration profileRegistration;

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String pageValue = request.getParameter(MMJBCommonConstants.PAGE_VALUE);
		String socialSignUp =(String)request.getAttribute("socialSignUp");
		response.reset();
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Cache-Control", "must-revalidate");
		response.setDateHeader("Expires", 0);
		UserDTO user = loginService.getUser(authentication.getName());
		HttpSession session = request.getSession(false);
		if(user.isAdmin()){
			session.setAttribute(MMJBCommonConstants.FACILITY_FULL_ACCESS,MMJBCommonConstants.FACILITY_FULL_ACCESS);
		}
		session.setAttribute(MMJBCommonConstants.USER_ID, user.getUserId());
		session.setAttribute(MMJBCommonConstants.USER_NAME, user.getFirstName()
				+ " " + user.getLastName());
		session.setAttribute(MMJBCommonConstants.USER_EMAIL, user.getEmailId());

		if (isJobSeeker(authentication, pageValue)) {

			redirectJobSeeker(user,request,response,session);
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
			redirectFacility(user,request,response,session);
		} else if (isFacilitySystem(authentication, pageValue)) {

			/**
			 * Added to put facility id in the session
			 */
			EmployerInfoDTO infoDTO = facilityService.facilityDetails(user
					.getUserId());
			session.setAttribute(MMJBCommonConstants.FACILITY_ID,
					infoDTO.getFacilityId());
			redirectFacilitySystem(user,request,response,session);
		} else {
			boolean socalLogin=false;
			if(socialSignUp!=null){socalLogin=true;}
			session.invalidate();
			sendRedirect(request, response,
					"/commonLogin/login.html?error=true&page=" + pageValue+"&socalLogin="+socalLogin);

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
	private boolean isFacilitySystem(Authentication authentication,
			String pageValue) {
		return authentication.getAuthorities().contains(
				new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_FACILITY_SYSTEM))
				&& pageValue.equals(MMJBCommonConstants.AGENCY);
	}

	/**
	 * Method return true if authenticated by FACILITY or FACILITY_GROUP
	 * otherwise false.
	 * 
	 * @param authentication
	 * @param pageValue
	 * @return
	 */
	private boolean isFacility(Authentication authentication, String pageValue) {
		return (authentication.getAuthorities().contains(
				new SimpleGrantedAuthority(MMJBCommonConstants.ROLE_FACILITY))
				|| authentication.getAuthorities().contains(
						new SimpleGrantedAuthority(
								MMJBCommonConstants.ROLE_FACILITY_GROUP)))
				&& pageValue.equals(MMJBCommonConstants.EMPLOYER);
	}

	/**
	 * Method return true if authenticated by JOB SEEKER otherwise false.
	 * 
	 * @param authentication
	 * @param pageValue
	 * @return
	 */
	private boolean isJobSeeker(Authentication authentication, String pageValue) {

		return authentication.getAuthorities()
				.contains(
						new SimpleGrantedAuthority(
								MMJBCommonConstants.ROLE_JOB_SEEKER))
				&& pageValue.equals(MMJBCommonConstants.JOB_SEEKER);
	}
	
	/**
	 * Method to redirect job seeker to the corresponding page depending on the condition
	 * 
	 * @param UserDTO user
	 * @param HttpServletRequest
	 * @param HttpServletResponse response
	 * @param HttpSession session
	 */
	private void redirectJobSeeker(UserDTO user, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ServletException {
		if (profileRegistration.validateProfileAttributes(user.getUserId())) {

			/**
			 * if the user already registered
			 */
			if (session.getAttribute("jobId") == null) {
				sendRedirect(request, response,
						"/jobSeeker/jobSeekerDashBoard.html");
			} else {
				sendRedirect(
						request,
						response,
						"/jobsearch/viewJobDetails.html?id="
								+ session.getAttribute("jobId")
								+ "&currentUrl=" + request.getContextPath()
								+ "/jobsearch/findJobPage.html"
								+ "&clickType=view");
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
	 * Method to redirect facility to the corresponding page depending on the condition
	 * 
	 * @param UserDTO user
	 * @param EmployerInfoDTO
	 * @param HttpServletRequest
	 * @param HttpServletResponse response
	 * @param HttpSession session
	 */
	private void redirectFacility(UserDTO user,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws IOException, ServletException {
		 
		if (profileRegistration
				.validateProfileAttributes(user.getUserId())) {
			sendRedirect(request, response,
					"/employer/employerDashBoard.html");

		} else {

			session.setAttribute(MMJBCommonConstants.USER_DTO, user);
			sendRedirect(request, response,
					"/employerRegistration/employerregistration.html");
		}

		}
	
	/**
	 * Method to redirect facility system to the corresponding page depending on the condition
	 * 
	 * @param UserDTO user
	 * @param EmployerInfoDTO
	 * @param HttpServletRequest
	 * @param HttpServletResponse response
	 * @param HttpSession session
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

