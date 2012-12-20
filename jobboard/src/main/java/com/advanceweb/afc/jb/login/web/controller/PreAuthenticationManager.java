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
		System.out.println("getPreAuthenticatedPrincipal=======>");
		 authentication = SecurityContextHolder.getContext().getAuthentication();
		    HttpServletRequest req=(HttpServletRequest)request;
		    if (authentication == null) {   
		    	String userEmail="";
		    	for(Cookie cookie:req.getCookies()){
		    		//boolean value=cookie.getName().equals(".ASPXAUTH");
		    		System.out.println("0 cookie.getName()=======>");
		    		//boolean value=false;
//		    		cookie.getName().equals(".ASPXAUTH")
					if(true){
						System.out.println("1 .ASPXAUTH cookie is there=======>");
						//String cookieValue=cookie.getValue();
						String cookieValue="DD0D76549C2BC74A23D04236BE4AAFDF3B88D304F9EE7302E9F8FCE06D651B97DDEAE3E1A8CA0CED79C845A91656C1CE472F7C98D785AC20BE7091BE623CC790C6B079254BB0195F2B8E5C5C4D9B0C022C508F5F6979B320C1D8FC872723625D90456F51A5EEEF099B69F31C";
//						String cookieValue=cookie.getValue();
						System.out.println("2 cookieValue=======>"+cookieValue);
						Date date =new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH.mm.ss aa", Locale.US);
						String postData = cookieValue + "^" + formatter.format(date) + "^" + "30" + "^" + "true";
						
						try {
							//URLConnection connection = new URL("http://12.104.61.122:901/testinterpreter.ashx?").openConnection();
							URL url = new URL("https://securedev.advanceweb.com/interpreter.ashx");
							//URL url = new URL("http://12.104.61.122:901/testinterpreter.ashx");
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
								System.out.println("3 userEmail=======>"+userEmail);
							/*while (in.readLine() != null)
							{
								userEmail = in.readLine();
								System.out.println("email in loop"+userEmail);
							}*/
							/*while ((userEmail = in.readLine()) != null)*/
								System.out.println("3 userEmail=======>"+userEmail);
								in.close();
						} catch (Exception e) {
							System.out.println("4 exception===>"+e);
							e.printStackTrace();
							// TODO: handle exception
						}	
						break;
					}
				}
		    	
		        if(userEmail!=null && userEmail.contains("@")){
		        	System.out.println("user email is  "+userEmail);
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
		System.out.println("getPreAuthenticatedCredentials=======>");
		//return authentication != null ?authentication.getCredentials():null;
		//return auth != null ?auth.getPassword():null;
		return user != null ?user.getPassword():null;
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
		 try {
			 System.out.println("in successfulAuthentication of pre auth");
			loginManager.onAuthenticationSuccess(request, response, authResult);
		} catch (Exception e) {
			System.out.println("exception in onAuthenticationSuccess");
			e.printStackTrace();
		}
	    }
	
}
