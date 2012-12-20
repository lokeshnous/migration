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
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.security.DatabaseAuthenticationManager;
import com.advanceweb.afc.jb.user.UserService;

public class PreAuthenticationManager extends AbstractPreAuthenticatedProcessingFilter{
	private static final Logger LOGGER = Logger
			.getLogger(PreAuthenticationManager.class);
	  private AuthenticationDetailsSource ads = new WebAuthenticationDetailsSource();
	  @Autowired
	  private DatabaseAuthenticationManager authenticationManager;
	 // private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
	  @Autowired
	  private UserService userService;
	  @Autowired
	  private LoginManager loginManager;
	  private Authentication authentication ;
	  private UserDetails auth ;
	  UserDTO user=null;
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		LOGGER.debug("getPreAuthenticatedPrincipal=======>");
		 authentication = SecurityContextHolder.getContext().getAuthentication();
		    HttpServletRequest req=(HttpServletRequest)request;
		    if (authentication == null) {   
		    	String userEmail="";
		    	for(Cookie cookie:req.getCookies()){
		    		//boolean value=cookie.getName().equals(".ASPXAUTH");
		    		LOGGER.debug("0 cookie.getName()=======>");
		    		//boolean value=false;
//		    		cookie.getName().equals(".ASPXAUTH")
					if(cookie.getName().equals(".ASPXAUTH")){
						LOGGER.debug("1 .ASPXAUTH cookie is there=======>");
						//String cookieValue=cookie.getValue();
//						String cookieValue="E349A4BE8158C56A72B47385C985CCE72D84498052D28130231E179CCD00CF9F10B475B05B70AC89DFDD07435B5863180224634DF8312E38E6477E74ECB3541B4560838235D41BAE9BF0D3DD9547C9A86FED6BEDF8FE1BFA0A45CEE09941C1839E02C4D67AE5BD9EFD4E47C1";
						String cookieValue=cookie.getValue();
						LOGGER.debug("2 cookieValue=======>"+cookieValue);
						Date date =new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH.mm.ss aa", Locale.US);
						String postData = cookieValue + "^" + formatter.format(date) + "^" + "30" + "^" + "true";
						
						try {
							//URLConnection connection = new URL("http://12.104.61.122:901/testinterpreter.ashx?").openConnection();
//							URL url = new URL("https://securedev.advanceweb.com/interpreter.ashx");
							URL url = new URL("http://12.104.61.122:901/testinterpreter.ashx");
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
							//userEmail="fdnyrk@sbcglobal.net";
							/*while ((userEmail = in.readLine()) != null)*/
							userEmail=in.readLine();
							LOGGER.debug("3 userEmail=======>"+userEmail);
							/*while (in.readLine() != null)
							{
								userEmail = in.readLine();
								System.out.println("email in loop"+userEmail);
							}*/
							/*while ((userEmail = in.readLine()) != null)*/
							LOGGER.debug("3 userEmail=======>"+userEmail);
								in.close();
						} catch (Exception e) {
							LOGGER.debug("4 exception===>"+e);
						
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
//		        	System.out.println("userDTO ===>"+user);
//		        	if(user==null){
//		        		System.out.println(" no user with "+userEmail+" emailId");
//		        		return null;
//		        	}
//		        	PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
//		        			userEmail, null, null);
//		            token.setDetails(ads.buildDetails(request));
//		            try{
//		                logger.debug("Going to set Authentication");
//		                authentication = authenticationManager.authenticate(token);
//		                auth = authenticationManager.loadUserDetails(token);
		                //SecurityContextHolder.getContext().setAuthentication(auth);
		            
		               // logger.debug("Authenticated");
//		            }
//		            catch(AuthenticationException e){
//		                logger.debug("Authentication information was rejected by the authentication manager \n",e);
		               // failureHandler.onAuthenticationFailure((HttpServletRequest)request, (HttpServletResponse)response, e);
//		            }
		        }// end if     
		    }// end if(authenication == null)
//		    return authentication != null ?authentication.getCredentials():null;
		return user != null ?user.getEmailId():null;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		LOGGER.debug("getPreAuthenticatedCredentials=======>");
		//return authentication != null ?authentication.getCredentials():null;
		//return auth != null ?auth.getPassword():null;
		return user != null ?user.getPassword():null;
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
		 try {
			 LOGGER.debug("in successfulAuthentication of pre auth");
			loginManager.onAuthenticationSuccess(request, response, authResult);
		} catch (Exception e) {
			LOGGER.debug("exception in onAuthenticationSuccess");
			e.printStackTrace();
		}
	    }
	
}
