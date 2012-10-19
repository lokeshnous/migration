package com.advanceweb.afc.jb.login.web.controller;


public class SocialLoginForm {
	private String emailId;	
	private String password;
	private String profileId;
	private String pageValue;
	private String serviceProviderId;
	private boolean error ;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getPageValue() {
		return pageValue;
	}
	public void setPageValue(String pageValue) {
		this.pageValue = pageValue;
	}
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	
}
