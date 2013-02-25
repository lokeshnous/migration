/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.security;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;

public interface DatabaseAuthenticationDelegate {

	/**
	 * Validate user.
	 *
	 * @param email the email
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean validateUser(String email, String password);
	
	/**
	 * Gets the connection.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the connection
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MalformedURLException the malformed url exception
	 */
	public URLConnection getConnection(String email, String password)throws IOException, MalformedURLException;
	
	/**
	 * Gets the cookie value.
	 *
	 * @param connection the connection
	 * @return the cookie value
	 */
	public String getCookieValue(URLConnection connection);
}
