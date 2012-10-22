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
	
	private int userId;
	
	
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
				
}
