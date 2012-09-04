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
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.user.ProfileRegistration;

public class LoginSuccessManager extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ProfileRegistration profileRegistration;

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String pageValue = request.getParameter(MMJBCommonConstants.PAGE_VALUE);
		response.reset();
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Cache-Control", "must-revalidate");
		response.setDateHeader("Expires", 0);
		UserDTO user = loginService.getUser(authentication.getName());
		HttpSession session = request.getSession(false);
		session.setAttribute(MMJBCommonConstants.USER_ID, user.getUserId());
		session.setAttribute(MMJBCommonConstants.USER_NAME, user.getFirstName()
				+ " " + user.getLastName());
		session.setAttribute(MMJBCommonConstants.USER_EMAIL, user.getEmailId());
		if (authentication.getAuthorities().contains(
						new SimpleGrantedAuthority(MMJBCommonConstants.ROLE_JOB_SEEKER))
						&& pageValue.equals(MMJBCommonConstants.JOB_SEEKER)) {
			if(profileRegistration.validateProfileAttributes(user.getUserId())){
				response.sendRedirect(request.getContextPath()
						+ "/jobSeeker/jobSeekerDashBoard.html");//if the user already registered
			}else{//if the logged in user is new
				session.setAttribute(MMJBCommonConstants.USER_DTO, user);
				response.sendRedirect(request.getContextPath()+ "/jobseekerregistration/createJobSeekerCreateYrAcct.html");
			}

		} else if (authentication.getAuthorities().contains(
				new SimpleGrantedAuthority(MMJBCommonConstants.ROLE_FACILITY))
				&& pageValue.equals(MMJBCommonConstants.EMPLOYER)) {
			//Added to put facility id in the session
			EmployerInfoDTO infoDTO = loginService.facilityDetails(user.getUserId());			
			session.setAttribute(MMJBCommonConstants.FACILITY_ID, infoDTO.getFacilityId());
			if(profileRegistration.validateProfileAttributes(user.getUserId())){
				response.sendRedirect(request.getContextPath()+ "/employer/employerDashBoard.html");
			}else{
				session.setAttribute(MMJBCommonConstants.USER_DTO, user);
				response.sendRedirect(request.getContextPath()+ "/employerRegistration/employerregistration.html");
			}
		} else if (authentication.getAuthorities().contains(
				new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_FACILITY_GROUP))
				&& pageValue.equals(MMJBCommonConstants.AGENCY)) {
			response.sendRedirect(request.getContextPath()
					+ "/agency/agencyDashboard.html");
		} else {
			session.invalidate();
			response.sendRedirect(request.getContextPath()
					+ "/commonLogin/login.html?error=true&page=" + pageValue);
		}
	}
}
