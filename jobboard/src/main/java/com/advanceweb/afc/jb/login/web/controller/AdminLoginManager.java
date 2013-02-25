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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.user.UserService;

public class AdminLoginManager extends SimpleUrlAuthenticationSuccessHandler {
	
	/** The user service. */
	@Autowired
	private UserService userService;

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		UserDTO user = userService.getAdminInfo(authentication.getName());
		HttpSession	session = request.getSession();
		session.setAttribute(MMJBCommonConstants.USER_NAME, user.getFirstName()
				+ " " + user.getLastName());
		session.setAttribute(MMJBCommonConstants.USER_ID, user.getUserId());
		response.sendRedirect(request.getContextPath()+"/admin/adminMenu.html");
	}
}
