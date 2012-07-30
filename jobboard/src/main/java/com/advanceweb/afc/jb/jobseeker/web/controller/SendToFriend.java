package com.advanceweb.afc.jb.jobseeker.web.controller;


public class SendToFriend {
    
    private String email;
	
	private String message;
	
	private String joburl;

	
	public String getJoburl() {
		return joburl;
	}
	public void setJoburl(String joburl) {
		this.joburl = joburl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
