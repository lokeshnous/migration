/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.login.web.controller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.security.DatabaseAuthenticationManager;
import com.advanceweb.afc.jb.user.UserService;

public class PreAuthenticationManager extends AbstractPreAuthenticatedProcessingFilter{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(PreAuthenticationManager.class);
	  
  	/** The ads. */
  	private AuthenticationDetailsSource ads = new WebAuthenticationDetailsSource();
	  
  	/** The authentication manager. */
  	@Autowired
	  private DatabaseAuthenticationManager authenticationManager;
	  
  	/** The user service. */
  	@Autowired
	  private UserService userService;
	  
  	/** The login manager. */
  	@Autowired
	  private LoginManager loginManager;
	  
  	/** The authentication. */
  	private Authentication authentication ;
	  
  	/** The user. */
  	UserDTO user=null;
	  
  	/** The advancepass interpreter url. */
  	private @Value("${advancepass.interpreter.url}")
		String advancepassInterpreterUrl;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter#getPreAuthenticatedPrincipal(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		LOGGER.debug("getPreAuthenticatedPrincipal=======>");
		 authentication = SecurityContextHolder.getContext().getAuthentication();
		    HttpServletRequest req=(HttpServletRequest)request;
		    if (authentication == null && null != req.getCookies()) { 
		    	String userEmail="";
		    	for(Cookie cookie:req.getCookies()){
					if(cookie.getName().equals(".ASPXAUTH")){
						LOGGER.debug("1 .ASPXAUTH cookie is there=======>");
						String cookieValue=cookie.getValue();
						LOGGER.debug("2 cookieValue=======>"+cookieValue);
						
						Date date =new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH.mm.ss aa", Locale.US);
						String postData = cookieValue + "^" + formatter.format(date) + "^" + "30" + "^" + "true";
						LOGGER.debug("Date Passing to interpreter=======>"+formatter.format(date));
						try {
							URL url = new URL(advancepassInterpreterUrl);
							TrustManager[] trustAllCerts = new TrustManager[]{
				                       new X509TrustManager() {
				                           public java.security.cert.X509Certificate[] getAcceptedIssuers() {return null;}
				                          public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType){}
				                           public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType){}
				                       }
				                   };
							SSLContext sc = SSLContext.getInstance("SSL");
			                   sc.init(null, trustAllCerts, new java.security.SecureRandom());
			                   HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			                      URLConnection connection = url.openConnection();
							connection.setDoInput(true);
							connection.setDoOutput(true);
							connection.setRequestProperty("Accept-Charset", "UTF-8");
							connection.setRequestProperty("Content-Type",
									"application/x-www-form-urlencoded");
							connection.setRequestProperty("method", "POST");
							connection.setRequestProperty("keepAlive", "true");
							OutputStream oStrem = connection.getOutputStream();
							oStrem.write(postData.getBytes(), 0, postData.length());
							oStrem.close();
							InputStream iStream = connection.getInputStream();
							BufferedReader in = new BufferedReader(
									new InputStreamReader(iStream));
							userEmail=in.readLine();
							LOGGER.debug("userEmail=======>"+userEmail);
								in.close();
						} catch (Exception e) {
							LOGGER.error("exception===>"+e);
						
						}	
						break;
					}
				}
		        if(userEmail!=null && userEmail.contains("@")){
		        	LOGGER.debug("user email is  "+userEmail);
		        	UserDTO jbuser = userService.getUser(userEmail);
		        	if(jbuser!=null){
		        	user = userService.getAdvancePassUser(userEmail);
		        	}
		        	if(user==null){
		        		logger.debug(" no user with "+userEmail+" emailId");
		        		return null;
		        	}
		        	PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
		        			user.getEmailId(), user.getPassword(), null);
		            token.setDetails(ads.buildDetails(request));
		            try{
		                logger.debug("Going to set Authentication");
		                authentication = authenticationManager.authenticate(token);
		                SecurityContextHolder.getContext().setAuthentication(authentication);
		            
		                logger.debug("Authenticated");
		            }
		            catch(AuthenticationException e){
		                logger.error("Authentication information was rejected by the authentication manager \n",e);
		            }
		        }// end if     
		    }

		    return  authentication != null ?authentication.getPrincipal():null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter#getPreAuthenticatedCredentials(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		LOGGER.debug("getPreAuthenticatedCredentials");
		return authentication != null ?authentication.getCredentials():null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter#successfulAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
		 try {
			 LOGGER.debug("in successfulAuthentication of pre auth");
			SecurityContextHolder.getContext().setAuthentication(authResult);
			loginManager.onAuthenticationSuccess(request, response, authResult);
		} catch (Exception e) {
			LOGGER.error("exception in onAuthenticationSuccess "+e.getMessage());
		}
	    }
	
}
