package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.security.web.util.UrlUtils;
/*AuthenticationProcessingFilterEntryPoint*/
public class AdminAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        String redirectUrl = null;
        
        if(request.getServletPath().contains("/admin/")){
        	response.sendRedirect("/admin/login.html");
		 }
        else{
        	super.commence(request, response, authException);
        }
//        if (useForward) {

//            if (forceHttps && "http".equals(request.getScheme())) {
//                // First redirect the current request to HTTPS.
//                // When that request is received, the forward to the login page will be used.
//                redirectUrl = buildHttpsRedirectUrlForRequest(request);
//            }

//            if (redirectUrl == null) {
//                String loginForm = determineUrlToUseForThisRequest(request, response, authException);

//                if (logger.isDebugEnabled()) {
//                    logger.debug("Server side forward to: " + loginForm);
//                }

//                RequestDispatcher dispatcher = request.getRequestDispatcher(loginForm);

//                dispatcher.forward(request, response);

//                return;
            }
//        } else {
            // redirect to login page. Use https if forceHttps true

//            redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);

//        }

//        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

//    protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response,
//            AuthenticationException authException) {
//
//        String loginForm = determineUrlToUseForThisRequest(request, response, authException);
//
//        if (UrlUtils.isAbsoluteUrl(loginForm)) {
//            return loginForm;
//        }

//        int serverPort = portResolver.getServerPort(request);
//        String scheme = request.getScheme();

//        RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
//
//        urlBuilder.setScheme(scheme);
//        urlBuilder.setServerName(request.getServerName());
//        urlBuilder.setPort(serverPort);
//        urlBuilder.setContextPath(request.getContextPath());
//        urlBuilder.setPathInfo(loginForm);

//        if (forceHttps && "http".equals(scheme)) {
//            Integer httpsPort = portMapper.lookupHttpsPort(Integer.valueOf(serverPort));
//
//            if (httpsPort != null) {
//                // Overwrite scheme and port in the redirect URL
//                urlBuilder.setScheme("https");
//                urlBuilder.setPort(httpsPort.intValue());
//            } else {
//                logger.warn("Unable to redirect to HTTPS as no port mapping found for HTTP port " + serverPort);
//            }
//        }
//
//        return urlBuilder.getUrl();
//    }

    /**
     * Builds a URL to redirect the supplied request to HTTPS. Used to redirect the current request
     * to HTTPS, before doing a forward to the login page.
     */
//    protected String buildHttpsRedirectUrlForRequest(HttpServletRequest request)
//            throws IOException, ServletException {

//        int serverPort = portResolver.getServerPort(request);
//        Integer httpsPort = portMapper.lookupHttpsPort(Integer.valueOf(serverPort));

//        if (httpsPort != null) {
//            RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
//            urlBuilder.setScheme("https");
//            urlBuilder.setServerName(request.getServerName());
//            urlBuilder.setPort(httpsPort.intValue());
//            urlBuilder.setContextPath(request.getContextPath());
//            urlBuilder.setServletPath(request.getServletPath());
//            urlBuilder.setPathInfo(request.getPathInfo());
//            urlBuilder.setQuery(request.getQueryString());

//            return urlBuilder.getUrl();
//        }

        // Fall through to server-side forward with warning message
//        logger.warn("Unable to redirect to HTTPS as no port mapping found for HTTP port " + serverPort);

//        return null;
//    }


	
/*	
	 protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
	            AuthenticationException exception) {
System.out.println("******************************************************************************************");
		 if(request.getServletPath().contains("/admin/")){
			 return "/admin/login.html";
		 }
	        return super.getLoginFormUrl();
	    }
}
*/