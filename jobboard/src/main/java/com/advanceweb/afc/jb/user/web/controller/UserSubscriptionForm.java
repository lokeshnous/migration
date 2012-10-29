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
				
}
