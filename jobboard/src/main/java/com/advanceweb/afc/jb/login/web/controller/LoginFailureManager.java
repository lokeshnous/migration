/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

public class LoginFailureManager extends SimpleUrlAuthenticationFailureHandler {
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String pageValue = request.getParameter(MMJBCommonConstants.PAGE_VALUE);

		if (pageValue != null
				&& pageValue.equals(MMJBCommonConstants.JOB_SEEKER)) {
			super.setDefaultFailureUrl("/commonlogin/login.html?error=true&page=jobSeeker");
		}

		else if (pageValue != null
				&& pageValue.equals(MMJBCommonConstants.EMPLOYER)) {
			super.setDefaultFailureUrl("/commonlogin/login.html?error=true&page=employer");
		} else if (pageValue != null
				&& pageValue.equals(MMJBCommonConstants.AGENCY)) {
			super.setDefaultFailureUrl("/commonlogin/login.html?error=true&page=agency");
		}

		super.onAuthenticationFailure(request, response, exception);
	}

}
