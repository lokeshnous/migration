/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.mail.service;

import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * <code>EmailDTO</code> is a DTO class for email.
 * 
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:23 PM
 */
public class EmailDTO {

	/** The from address. */
	private String fromAddress;
	
	/** The to address. */
	private InternetAddress[] toAddress;
	
	/** The cc address. */
	private InternetAddress[] ccAddress;
	
	/** The bcc address. */
	private InternetAddress[] bccAddress;
	
	/** The subject. */
	private String subject;
	
	/** The body. */
	private String body;
	
	/** The attachment paths. */
	private List<String> attachmentPaths;
	
	/** The html format. */
	private boolean htmlFormat;
	
	/**
	 * Gets the to address.
	 *
	 * @return the to address
	 */
	public InternetAddress[] getToAddress() {
		// Since: PMD 2.2:Exposing internal arrays to the caller violates object
		// encapsulation
		return toAddress;
	}

	/**
	 * Sets the to address.
	 *
	 * @param toAddress the new to address
	 */
	public void setToAddress(InternetAddress[] toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * Gets the from address.
	 *
	 * @return the from address
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * Sets the from address.
	 *
	 * @param fromAddress the new from address
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * Gets the cc address.
	 *
	 * @return the cc address
	 */
	public InternetAddress[] getCcAddress() {
		// Since: PMD 2.2:Exposing internal arrays to the caller violates object
		// encapsulation
		return ccAddress;
	}

	/**
	 * Sets the cc address.
	 *
	 * @param ccAddress the new cc address
	 */
	public void setCcAddress(InternetAddress[] ccAddress) {
		this.ccAddress = ccAddress;
	}

	/**
	 * Gets the bcc address.
	 *
	 * @return the bcc address
	 */
	public InternetAddress[] getBccAddress() {
		// Since: PMD 2.2:Exposing internal arrays to the caller violates object
		// encapsulation
		return bccAddress;
	}

	/**
	 * Sets the bcc address.
	 *
	 * @param bccAddress the new bcc address
	 */
	public void setBccAddress(InternetAddress[] bccAddress) {
		this.bccAddress = bccAddress;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets the body.
	 *
	 * @param body the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Gets the attachment paths.
	 *
	 * @return the attachment paths
	 */
	public List<String> getAttachmentPaths() {
		return attachmentPaths;
	}

	/**
	 * Sets the attachment paths.
	 *
	 * @param attachmentPaths the new attachment paths
	 */
	public void setAttachmentPaths(List<String> attachmentPaths) {
		this.attachmentPaths = attachmentPaths;
	}

	/**
	 * Checks if is html format.
	 *
	 * @return true, if is html format
	 */
	public boolean isHtmlFormat() {
		return htmlFormat;
	}

	/**
	 * Sets the html format.
	 *
	 * @param htmlFormat the new html format
	 */
	public void setHtmlFormat(boolean htmlFormat) {
		this.htmlFormat = htmlFormat;
	}
	

}