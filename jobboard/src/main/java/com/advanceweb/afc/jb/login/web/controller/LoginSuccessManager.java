package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

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
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("userName",
				user.getFirstName() + " " + user.getLastName());
		session.setAttribute("userEmail", user.getEmail());
		response.sendRedirect(request.getContextPath() + getDefaultTargetUrl());
	}

}
