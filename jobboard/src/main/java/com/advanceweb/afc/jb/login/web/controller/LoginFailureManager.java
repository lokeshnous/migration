package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

public class LoginFailureManager extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String pageValue = request.getParameter(MMJBCommonConstants.PAGE_VALUE);

		if (pageValue != null
				&& pageValue.equals(MMJBCommonConstants.JOBSEEKER)) {
			super.setDefaultFailureUrl(MMJBCommonConstants.JOBSEEKER_LOGIN_FAILURE_URL);
		}

		else if (pageValue != null
				&& pageValue.equals(MMJBCommonConstants.EMPLOYER)) {
			super.setDefaultFailureUrl(MMJBCommonConstants.EMPLOYER_LOGIN_FAILURE_URL);
		} else if (pageValue != null
				&& pageValue.equals(MMJBCommonConstants.AGENCY)) {
			super.setDefaultFailureUrl(MMJBCommonConstants.AGENCY_LOGIN_FAILURE_URL);
		}

		super.onAuthenticationFailure(request, response, exception);
	}

}
