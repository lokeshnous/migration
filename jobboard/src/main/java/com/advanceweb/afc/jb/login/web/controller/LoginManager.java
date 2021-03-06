/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;
import java.net.URLConnection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityContactDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.security.DatabaseAuthenticationDelegate;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.user.UserService;

public class LoginManager extends SimpleUrlAuthenticationSuccessHandler {
	
	/** The user service. */
	@Autowired
	private UserService userService;

	/** The facility service. */
	@Autowired
	private FacilityService facilityService;

	/** The profile registration. */
	@Autowired
	private ProfileRegistration profileRegistration;
	
	/** The authentication delegate. */
	@Autowired
	private DatabaseAuthenticationDelegate authenticationDelegate;
	
	/** The dothtml extention. */
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(LoginManager.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		String registrationValue=(String) request.getAttribute("userRegistration");
		
		String pageValue = request.getParameter(MMJBCommonConstants.PAGE_VALUE);
		
		HttpSession session;
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			session = request.getSession(false);
		} 
		 else {
			session = request.getSession();
		}
		if (authentication.getAuthorities().contains(
				new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_ADVANCE_PASS_USER))){
			UserDTO advPassUser=userService.getAdvancePassUserDetails(authentication.getName());
			session.setAttribute("advancePassUserDetails",advPassUser);
			String profileId=null;
			String serviceProviderId=null;
			if(session.getAttribute("socialProfileId")!=null){
			profileId=session.getAttribute("socialProfileId").toString();
			serviceProviderId=session.getAttribute("socialProfileAttrId").toString();
			}
			if(pageValue!=null && pageValue.equals(MMJBCommonConstants.JOB_SEEKER)){
				sendRedirect(request, response,
						"/jobseekerregistration/createjobseekercreateyracct.html?profileId="+profileId+"&serviceProviderId="+serviceProviderId);
			}
			if(pageValue!=null && pageValue.equals(MMJBCommonConstants.EMPLOYER)){
				sendRedirect(request, response,
						"/employerreg/employerregistration.html?profileId="+profileId+"&serviceProviderId="+serviceProviderId);
			}
			if(pageValue!=null && pageValue.equals(MMJBCommonConstants.AGENCY)){
				sendRedirect(request, response,
						"/agencyreg/agencyregistration.html?profileId="+profileId+"&serviceProviderId="+serviceProviderId);
			}
			
		}
		else{
		String socialSignUp = (String) request.getAttribute("socialSignUp");
		UserDTO user = userService.getUser(authentication.getName());
		
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
		
		if(!(authentication instanceof PreAuthenticatedAuthenticationToken)){
			UserDTO advancePassUser=null;
			if(authentication instanceof RememberMeAuthenticationToken){
				User details =(User)authentication.getPrincipal();
				advancePassUser = userService.getAdvancePassUser(details.getUsername());	
				String pw=details.getPassword();
			}
			else{
				advancePassUser = userService.getAdvancePassUser(authentication.getPrincipal().toString());
			}
			if (!authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_MERION_ADMIN))) {
		response.addCookie(createCookie(advancePassUser.getEmailId(),advancePassUser.getPassword()));
		response.addCookie(createUserCookie());
//		response.addCookie(createAuthCookie());
			}
		
		}
		session.setAttribute(MMJBCommonConstants.USER_ID, user.getUserId());
		session.setAttribute(MMJBCommonConstants.USER_NAME, user.getFirstName()
				+ " " + user.getLastName());
		session.setAttribute(MMJBCommonConstants.USER_EMAIL, user.getEmailId());

		if (isJobSeeker(authentication, pageValue,registrationValue)) {
			session.setAttribute(MMJBCommonConstants.LOGIN_DATE_TIME,
					new Date());
			redirectJobSeeker(user, request, response, session);
		} else if (isFacility(authentication, pageValue,registrationValue)) {

			/**
			 * Added to put facility id in the session
			 */
			EmployerInfoDTO infoDTO = facilityService.facilityDetails(user
					.getUserId());
			session.setAttribute(MMJBCommonConstants.FACILITY_ID,
					infoDTO.getFacilityId());
			session.setAttribute(MMJBCommonConstants.COMPANY_EMP,
					infoDTO.getCustomerName());
			redirectFacility(user, request, response, session,registrationValue);
		} else if (isFacilitySystem(authentication, pageValue,registrationValue)) {

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
			response.addCookie(deleteCookie());
			response.addCookie(deleteUserCookie());
			session.invalidate();
			sendRedirect(request, response,
					"/commonlogin/login.html?error=true&page=" + pageValue
							+ "&socalLogin=" + socalLogin);
		}
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
			String pageValue,String userRegistration) {
		if (authentication instanceof RememberMeAuthenticationToken || authentication instanceof PreAuthenticatedAuthenticationToken||(userRegistration!=null && userRegistration.equals("agencyRegistration"))) {
			return authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY_SYSTEM));
		} 
		
		else {
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
	public boolean isFacility(Authentication authentication, String pageValue,String userRegistration) {
		if (authentication instanceof RememberMeAuthenticationToken || authentication instanceof PreAuthenticatedAuthenticationToken||(userRegistration!=null && userRegistration.equals("employerRegistration"))) {
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
	public boolean isJobSeeker(Authentication authentication, String pageValue,String userRegistration) {
		if (authentication instanceof RememberMeAuthenticationToken || authentication instanceof PreAuthenticatedAuthenticationToken ||(userRegistration!=null && userRegistration.equals("jobseekerRegistration"))) {
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
						"/search/jobview/"
								+ session.getAttribute("jobId")+
								"/"+jobTitle.replaceAll(MMJBCommonConstants.IGNORE_SPECIAL_CHAR_PATTERN,"")+dothtmlExtention);
			}else{
				sendRedirect(request, response,
						"/jobSeeker/jobSeekerDashBoard.html");
			}

		} else {
			/**
			 * if the logged in user is new
			 */
			UserDTO advPassUser=userService.getAdvancePassUser(user.getEmailId());
			user.setPassword(advPassUser.getPassword());
			session.setAttribute(MMJBCommonConstants.USER_DTO, user);
			sendRedirect(request, response,
					"/jobseekerregistration/createjobseekercreateyracct.html");

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
			HttpServletResponse response, HttpSession session,String userRegistration)
			throws IOException, ServletException {
		if(userRegistration!=null){
			sendRedirect(request, response, "/employerreg/redirectToAddPage.html");
		}
		
		if (profileRegistration.validateProfileAttributes(user.getUserId())) {
			sendRedirect(request, response, "/employer/employerDashBoard.html");

		} else {
			UserDTO advPassUser=userService.getAdvancePassUser(user.getEmailId());
			user.setPassword(advPassUser.getPassword());
			EmployerInfoDTO infoDTO = facilityService.facilityDetails(user
					.getUserId());
			if(infoDTO!=null){
			FacilityContactDTO facilityContactDTO=facilityService.getFacilityContactDetails(infoDTO.getFacilityId());
			session.setAttribute("FacilityContactDTO", facilityContactDTO);
			}
			session.setAttribute(MMJBCommonConstants.USER_DTO, user);
			sendRedirect(request, response,
					"/employerreg/employerregistration.html");
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
			UserDTO advPassUser=userService.getAdvancePassUser(user.getEmailId());
			user.setPassword(advPassUser.getPassword());
			EmployerInfoDTO infoDTO = facilityService.facilityDetails(user
					.getUserId());
			if(infoDTO!=null){
			FacilityContactDTO facilityContactDTO=facilityService.getFacilityContactDetails(infoDTO.getFacilityId());
			session.setAttribute("FacilityContactDTO", facilityContactDTO);
			}
			session.setAttribute(MMJBCommonConstants.USER_DTO, user);
			sendRedirect(request, response,
					"/agencyreg/agencyregistration.html");
		}

	}
	
	/**
	 * Creates the cookie.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the cookie
	 */
	private Cookie createCookie(String userName,String password){
		Cookie cookie= null;
		try {
			URLConnection connection=authenticationDelegate.getConnection(userName, password);
			String cookieValue=authenticationDelegate.getCookieValue(connection);
			cookie=new Cookie(".ASPXAUTH",cookieValue);
			cookie.setDomain(".advanceweb.com");
			cookie.setPath("/");
			cookie.setMaxAge((int)TimeUnit.SECONDS.convert(120, TimeUnit.MINUTES));
		} catch (Exception e) {
			LOGGER.error("createCookie exception"+e.getMessage());
		}
		return cookie;
	}
	
//	private Cookie createAuthCookie(){
//		Cookie cookie= null;
//		try {
//			cookie=new Cookie(".ASPXAUTH","localemp1@local.com");
//			cookie.setDomain(".localhost");
//			cookie.setPath("/");
//			cookie.setMaxAge((int)TimeUnit.SECONDS.convert(120, TimeUnit.MINUTES));
//		} catch (Exception e) {
//			LOGGER.error("createCookie exception"+e.getMessage());
//		}
//		return cookie;
//	}
	
	private Cookie createUserCookie(){
		Cookie cookie= null;
		try {
			cookie=new Cookie(".USERCOOKIE","cookie value");
			cookie.setDomain(".advanceweb.com");
			cookie.setPath("/");
//			cookie.setMaxAge((int)TimeUnit.SECONDS.convert(30, TimeUnit.MINUTES));
		} catch (Exception e) {
			LOGGER.error("createUserCookie() exception "+e.getMessage());
		}
		return cookie;
	}
	
	/**
	 * Delete cookie.
	 *
	 * @return the cookie
	 */
	private Cookie deleteCookie(){
		Cookie cookie=new Cookie(".ASPXAUTH","Deleted cookie");
			cookie.setDomain(".advanceweb.com");
			cookie.setPath("/");
			cookie.setMaxAge(0);
		return cookie;
	}
	
	/**
	 * Delete user cookie.
	 *
	 * @return the cookie
	 */
	private Cookie deleteUserCookie(){
		Cookie cookie=new Cookie(".USERCOOKIE","Deleted cookie");
			cookie.setDomain(".advanceweb.com");
			cookie.setPath("/");
			cookie.setMaxAge(0);
		return cookie;
	}
}
