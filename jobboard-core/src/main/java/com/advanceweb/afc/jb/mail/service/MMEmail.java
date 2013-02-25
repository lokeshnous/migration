/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.mail.service;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:21 PM
 */
public interface MMEmail {

	/** The m emaildto. */
	EmailDTO M_EMAILDTO=null;

	/**
	 * Sending mail
	 * 
	 * @param emailDTO
	 */
	void sendEmail(EmailDTO emailDTO);

}