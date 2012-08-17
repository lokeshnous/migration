package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.login.service.LoginFormService;

public class LoginSuccessManager extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private LoginFormService loginFormService;

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		response.reset();
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Cache-Control", "must-revalidate");
		response.setDateHeader("Expires", 0);

		MerUser user = loginFormService.getUser(authentication.getName());
		HttpSession session = request.getSession(false);
		session.setAttribute(MMJBCommonConstants.USER_ID, user.getUserId());
		session.setAttribute(MMJBCommonConstants.USER_NAME,user.getFirstName() + " " + user.getLastName());

		session.setAttribute(MMJBCommonConstants.USER_EMAIL, user.getEmail());
		if (authentication.getAuthorities().contains(new GrantedAuthorityImpl(
				MMJBCommonConstants.ROLE_JOB_SEEKER))) {
			response.sendRedirect(request.getContextPath()
					+ "/jobSeeker/jobSeekerDashBoard.html");
		} else if (authentication.getAuthorities().contains(new GrantedAuthorityImpl(
				MMJBCommonConstants.ROLE_FACILITY_ADMIN))) {
			response.sendRedirect(request.getContextPath()
					+ "/employer/employerDashBoard.html");
		}
		else if (authentication.getAuthorities().contains(new GrantedAuthorityImpl(
				MMJBCommonConstants.ROLE_FACILITY_USER))) {
			response.sendRedirect(request.getContextPath()
					+ "/agency/agencyDashBoard.html");
		}
		

	}
	
}
