package com.advanceweb.afc.jb.security;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("authenticationDelegate")
public class DatabaseAuthenticationDelegateImpl implements
		DatabaseAuthenticationDelegate {
	private static final Logger LOGGER = Logger
			.getLogger(DatabaseAuthenticationDelegateImpl.class);
	@Override
	public boolean validateUser(String email, String password) {
		URLConnection connection=null;
		String cookieValue=null;
		boolean returnValue=false;
		try {
		connection = getConnection(email, password);
		} catch (Exception e) {
			LOGGER.debug("Exception while creating the URLConnection for authentication"+e.getMessage());
		}
		cookieValue= getCookieValue(connection);
		if(cookieValue!=null && !cookieValue.contains("Invalid User info")){
			returnValue=true;
			}
		return returnValue;
		}
	
	public String getCookieValue(URLConnection connection){
		String cookieValue=null;
		if(connection!=null){
		try {
			List<String> cookies = connection.getHeaderFields().get(
					"Set-Cookie");
			for (String c : cookies) {
				if (c.contains(".ASPXAUTH")) {
					cookieValue = c.substring(c.lastIndexOf(".ASPXAUTH")
							+ ".ASPXAUTH".length() + 1, c.indexOf(";"));
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching the cookie value from connection");
		}
		}
	return cookieValue;
}
	public URLConnection getConnection(String email, String password)
			throws IOException, MalformedURLException {
		URLConnection connection=null;
		try {
			connection = new URL(
					"http://12.104.61.122:901/authenticateuser.aspx?EmailAddress="
							+ email + "&password=" + password).openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("method", "POST");
			connection.setRequestProperty("keepAlive", "true");
			connection.getOutputStream();
		} catch (Exception e) {
			LOGGER.error("Error occured while connecting to http://12.104.61.122:901/authenticateuser.aspx with email :"+email);
		}
		return connection;
	}

}