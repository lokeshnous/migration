package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

public class LogoutSuccessManager extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.reset();
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Cache-Control", "must-revalidate");
		response.setDateHeader("Expires", 0);
		if (null == authentication) {
			response.sendRedirect(request.getContextPath()
					+ "/healthcarejobs/advanceweb.html");
		} else {

			if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_JOB_SEEKER))) {
				response.sendRedirect(request.getContextPath()
						+ MMJBCommonConstants.JOBSEEKER_LOGOUT_URL);
			} else if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY))
					| authentication.getAuthorities().contains(
							new SimpleGrantedAuthority(
									MMJBCommonConstants.ROLE_FACILITY_GROUP))) {
				response.sendRedirect(request.getContextPath()
						+ MMJBCommonConstants.EMPLOYER_LOGOUT_URL);
			} else if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY_SYSTEM))) {
				response.sendRedirect(request.getContextPath()
						+ MMJBCommonConstants.AGENCY_LOGOUT_URL);
			}

		}
	}
}
