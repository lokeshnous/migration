/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.login.web.controller;

import org.springframework.social.connect.Connection;
import org.springframework.web.context.request.RequestAttributes;

/**
 * This call contains utility method that support service provider login
 * scenarios.
 */
public class SocialLoginUtils {

	/**
	 * Get the connection to the service provider user the client attempted to
	 * login as. This connection will be useful to fetch a service provider user
	 * profile to pre-populate a local user registration form. Returns null if
	 * no service provider login has been attempted for the current user
	 * session.
	 * 
	 * @param request
	 *            the current request attributes, used to extract login attempt
	 *            information from the current user session
	 */
	public static Connection<?> getConnection(RequestAttributes request) {
		SocialConnectionManager signInAttempt = getProviderUserSignInAttempt(request);
		return signInAttempt != null ? signInAttempt.getConnection() : null;
	}

	/**
	 * Gets the provider user sign in attempt.
	 *
	 * @param request the request
	 * @return the provider user sign in attempt
	 */
	private static SocialConnectionManager getProviderUserSignInAttempt(
			RequestAttributes request) {
		return (SocialConnectionManager) request.getAttribute(
				SocialConnectionManager.SESSION_ATTRIBUTE,
				RequestAttributes.SCOPE_SESSION);
	}

}