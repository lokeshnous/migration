package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

public class LogoutManager extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		  String cookieName = MMJBCommonConstants.SPRING_SECURITY_REMEMBER_ME_COOKIE;
		  Cookie cookie = new Cookie(cookieName, null);
		  cookie.setMaxAge(0);
		  cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
		  response.addCookie(cookie);
		if (null == authentication) {
			response.sendRedirect(request.getContextPath()
					+ "/healthcarejobs/advanceweb.html");
		} else {

			if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_JOB_SEEKER))) {
				response.sendRedirect(request.getContextPath()
						+ "/commonLogin/login.html?page=jobSeeker");
			} else if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY))
					|| authentication.getAuthorities().contains(
							new SimpleGrantedAuthority(
									MMJBCommonConstants.ROLE_FACILITY_GROUP))) {
				response.sendRedirect(request.getContextPath()
						+"/commonLogin/login.html?page=employer");
			} else if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY_SYSTEM))) {
				response.sendRedirect(request.getContextPath()
						+"/commonLogin/login.html?page=agency");
			}

		}
	}
}
