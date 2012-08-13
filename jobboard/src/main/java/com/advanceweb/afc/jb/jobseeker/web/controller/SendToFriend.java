package com.advanceweb.afc.jb.jobseeker.web.controller;


public class SendToFriend {
    
    private String email;
	
	private String message;
	
	private String joburl;
	
	private int jobId;

	
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
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
}
