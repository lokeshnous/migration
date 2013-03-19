package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class SessionManagementFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (request.getRequestedSessionId() != null
				&& !request.isRequestedSessionIdValid()) {
			boolean userCookieExist = false;
			if (request.getCookies() != null) {
				for (Cookie cookie : request.getCookies()) {
					if (cookie.getName().equals(".USERCOOKIE")) {
						userCookieExist = true;
						break;
					}
				}
			}
			if (userCookieExist) {

				boolean cookieExist = false;
				if (request.getCookies() != null) {
					for (Cookie cookie : request.getCookies()) {
						if (cookie.getName().equals(".ASPXAUTH")) {
							cookieExist = true;
							break;
						}
					}
					if (cookieExist) {
						SecurityContextHolder.getContext().setAuthentication(
								null);
						SecurityContextHolder.clearContext();
						// response.addCookie(deleteUserCookie());
						response.sendRedirect(request.getContextPath()
								+ "/healthcare/redirectSessionExpiredCookieExist.html");
					} else {
						// response.addCookie(deleteUserCookie());
						response.sendRedirect(request.getContextPath()
								+ "/healthcare/redirectSessionExpired.html");
					}
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	/**
	 * Delete cookie.
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
