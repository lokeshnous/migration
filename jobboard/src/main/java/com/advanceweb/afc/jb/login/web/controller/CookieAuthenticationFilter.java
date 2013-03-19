package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

public class CookieAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		if (session != null) {
			boolean cookieExist = false;

			if (request.getCookies() != null
					&& !SecurityContextHolder
							.getContext()
							.getAuthentication()
							.getAuthorities()
							.contains(
									new SimpleGrantedAuthority(
											MMJBCommonConstants.ROLE_ANONYMOUS_USER))
					&& !SecurityContextHolder
							.getContext()
							.getAuthentication()
							.getAuthorities()
							.contains(
									new SimpleGrantedAuthority(
											MMJBCommonConstants.ROLE_ADVANCE_PASS_USER))
					&& !SecurityContextHolder
							.getContext()
							.getAuthentication()
							.getAuthorities()
							.contains(
									new SimpleGrantedAuthority(
											MMJBCommonConstants.ROLE_MERION_ADMIN))) {
				for (Cookie cookie : request.getCookies()) {
					if (cookie.getName().equals(".ASPXAUTH")) {
						cookieExist = true;
						break;
					}
				}
				if (!cookieExist) {
					SecurityContextHolder.getContext().setAuthentication(null);
					SecurityContextHolder.clearContext();
					session.invalidate();
					 response.addCookie(deleteUserCookie());
					response.sendRedirect(request.getContextPath()
							+ "/healthcare/redirectCookieExpired.html");
				} else {
					chain.doFilter(request, response);
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	
	/**
	 * Delete user cookie.
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

