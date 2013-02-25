/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.user.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

public class UserSubscriptionForm {

	/** The currentsubs. */
	private String[] currentsubs;
	
	/** The currentmagazines. */
	private String[] currentmagazines;
	
	/** The current job alerts. */
	private String[] currentJobAlerts;
	
	/** The facsub. */
	private String[] facsub;
	
	/** The currentsubscheck. */
	private String[] currentsubscheck;
	
	/** The print sub. */
	private String[]  printSub;
	
	/** The dig sub. */
	private String[]  digSub;
	
	/** The news sub. */
	private String[]  newsSub;
	
	/** The Email sub. */
	private String[]  EmailSub;
	
	/** The print checkbox. */
	private boolean printCheckbox;
	
	/** The dig checkbox. */
	private boolean digCheckbox;
	
	/** The enews checkbox. */
	private boolean enewsCheckbox;
	
	/** The mail checkbox. */
	private boolean mailCheckbox;
	
	/** The user id. */
	private int userId;
	
	/** The facility id. */
	private int facilityId;
	
	/** The publication id. */
	private int publicationId;
	
	/**
	 * Gets the currentsubs.
	 *
	 * @return the currentsubs
	 */
	public String[] getCurrentsubs() {
		return currentsubs;
	}
	
	/**
	 * Sets the currentsubs.
	 *
	 * @param currentsubs the new currentsubs
	 */
	public void setCurrentsubs(String[] currentsubs) {
		this.currentsubs = currentsubs;
	}
	
	/**
	 * Gets the currentmagazines.
	 *
	 * @return the currentmagazines
	 */
	public String[] getCurrentmagazines() {
		return currentmagazines;
	}
	
	/**
	 * Sets the currentmagazines.
	 *
	 * @param currentmagazines the new currentmagazines
	 */
	public void setCurrentmagazines(String[] currentmagazines) {
		this.currentmagazines = currentmagazines;
	}
	
	/**
	 * Gets the current job alerts.
	 *
	 * @return the current job alerts
	 */
	public String[] getCurrentJobAlerts() {
		return currentJobAlerts;
	}
	
	/**
	 * Sets the current job alerts.
	 *
	 * @param currentJobAlerts the new current job alerts
	 */
	public void setCurrentJobAlerts(String[] currentJobAlerts) {
		this.currentJobAlerts = currentJobAlerts;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the facsub.
	 *
	 * @return the facsub
	 */
	public String[] getFacsub() {
		return facsub;
	}
	
	/**
	 * Sets the facsub.
	 *
	 * @param facsub the new facsub
	 */
	public void setFacsub(String[] facsub) {
		this.facsub = facsub;
	}
	
	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
	}
	
	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	
	/**
	 * Gets the currentsubscheck.
	 *
	 * @return the currentsubscheck
	 */
	public String[] getCurrentsubscheck() {
		return currentsubscheck;
	}
	
	/**
	 * Sets the currentsubscheck.
	 *
	 * @param currentsubscheck the new currentsubscheck
	 */
	public void setCurrentsubscheck(String[] currentsubscheck) {
		this.currentsubscheck = currentsubscheck;
	}
	
	/**
	 * Gets the publication id.
	 *
	 * @return the publication id
	 */
	public int getPublicationId() {
		return publicationId;
	}
	
	/**
	 * Sets the publication id.
	 *
	 * @param publicationId the new publication id
	 */
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}
	
	/**
	 * Gets the prints the sub.
	 *
	 * @return the prints the sub
	 */
	public String[] getPrintSub() {
		return printSub;
	}
	
	/**
	 * Sets the prints the sub.
	 *
	 * @param printSub the new prints the sub
	 */
	public void setPrintSub(String[] printSub) {
		this.printSub = printSub;
	}
	
	/**
	 * Gets the dig sub.
	 *
	 * @return the dig sub
	 */
	public String[] getDigSub() {
		return digSub;
	}
	
	/**
	 * Sets the dig sub.
	 *
	 * @param digSub the new dig sub
	 */
	public void setDigSub(String[] digSub) {
		this.digSub = digSub;
	}
	
	/**
	 * Gets the news sub.
	 *
	 * @return the news sub
	 */
	public String[] getNewsSub() {
		return newsSub;
	}
	
	/**
	 * Sets the news sub.
	 *
	 * @param newsSub the new news sub
	 */
	public void setNewsSub(String[] newsSub) {
		this.newsSub = newsSub;
	}
	
	/**
	 * Checks if is prints the checkbox.
	 *
	 * @return true, if is prints the checkbox
	 */
	public boolean isPrintCheckbox() {
		return printCheckbox;
	}
	
	/**
	 * Sets the prints the checkbox.
	 *
	 * @param printCheckbox the new prints the checkbox
	 */
	public void setPrintCheckbox(boolean printCheckbox) {
		this.printCheckbox = printCheckbox;
	}
	
	/**
	 * Checks if is dig checkbox.
	 *
	 * @return true, if is dig checkbox
	 */
	public boolean isDigCheckbox() {
		return digCheckbox;
	}
	
	/**
	 * Sets the dig checkbox.
	 *
	 * @param digCheckbox the new dig checkbox
	 */
	public void setDigCheckbox(boolean digCheckbox) {
		this.digCheckbox = digCheckbox;
	}
	
	/**
	 * Checks if is mail checkbox.
	 *
	 * @return true, if is mail checkbox
	 */
	public boolean isMailCheckbox() {
		return mailCheckbox;
	}
	
	/**
	 * Sets the mail checkbox.
	 *
	 * @param mailCheckbox the new mail checkbox
	 */
	public void setMailCheckbox(boolean mailCheckbox) {
		this.mailCheckbox = mailCheckbox;
	}
	
	/**
	 * Checks if is enews checkbox.
	 *
	 * @return true, if is enews checkbox
	 */
	public boolean isEnewsCheckbox() {
		return enewsCheckbox;
	}
	
	/**
	 * Sets the enews checkbox.
	 *
	 * @param enewsCheckbox the new enews checkbox
	 */
	public void setEnewsCheckbox(boolean enewsCheckbox) {
		this.enewsCheckbox = enewsCheckbox;
	}
	
	/**
	 * Gets the email sub.
	 *
	 * @return the email sub
	 */
	public String[] getEmailSub() {
		return EmailSub;
	}
	
	/**
	 * Sets the email sub.
	 *
	 * @param emailSub the new email sub
	 */
	public void setEmailSub(String[] emailSub) {
		EmailSub = emailSub;
	}
	
				
}
