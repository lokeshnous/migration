package com.advanceweb.afc.jb.user.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

public class UserSubscriptionForm {

	private String[] currentsubs;
	private String[] currentmagazines;
	private String[] currentJobAlerts;
	private String[] facsub;
	private String[] currentsubscheck;
	private String[]  printSub;
	private String[]  digSub;
	private String[]  newsSub;
	private boolean printCheckbox;
	private boolean digCheckbox;
	private boolean enewsCheckbox;
	private boolean mailCheckbox;
	
	private int userId;
	private int facilityId;
	private int publicationId;
	
	public String[] getCurrentsubs() {
		return currentsubs;
	}
	public void setCurrentsubs(String[] currentsubs) {
		this.currentsubs = currentsubs;
	}
	public String[] getCurrentmagazines() {
		return currentmagazines;
	}
	public void setCurrentmagazines(String[] currentmagazines) {
		this.currentmagazines = currentmagazines;
	}
	public String[] getCurrentJobAlerts() {
		return currentJobAlerts;
	}
	public void setCurrentJobAlerts(String[] currentJobAlerts) {
		this.currentJobAlerts = currentJobAlerts;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String[] getFacsub() {
		return facsub;
	}
	public void setFacsub(String[] facsub) {
		this.facsub = facsub;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public String[] getCurrentsubscheck() {
		return currentsubscheck;
	}
	public void setCurrentsubscheck(String[] currentsubscheck) {
		this.currentsubscheck = currentsubscheck;
	}
	public int getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}
	public String[] getPrintSub() {
		return printSub;
	}
	public void setPrintSub(String[] printSub) {
		this.printSub = printSub;
	}
	public String[] getDigSub() {
		return digSub;
	}
	public void setDigSub(String[] digSub) {
		this.digSub = digSub;
	}
	public String[] getNewsSub() {
		return newsSub;
	}
	public void setNewsSub(String[] newsSub) {
		this.newsSub = newsSub;
	}
	public boolean isPrintCheckbox() {
		return printCheckbox;
	}
	public void setPrintCheckbox(boolean printCheckbox) {
		this.printCheckbox = printCheckbox;
	}
	public boolean isDigCheckbox() {
		return digCheckbox;
	}
	public void setDigCheckbox(boolean digCheckbox) {
		this.digCheckbox = digCheckbox;
	}
	public boolean isMailCheckbox() {
		return mailCheckbox;
	}
	public void setMailCheckbox(boolean mailCheckbox) {
		this.mailCheckbox = mailCheckbox;
	}
	public boolean isEnewsCheckbox() {
		return enewsCheckbox;
	}
	public void setEnewsCheckbox(boolean enewsCheckbox) {
		this.enewsCheckbox = enewsCheckbox;
	}
	
				
}
