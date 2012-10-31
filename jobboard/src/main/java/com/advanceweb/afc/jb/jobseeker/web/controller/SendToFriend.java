package com.advanceweb.afc.jb.jobseeker.web.controller;


public class SendToFriend {
    
    private String email;
	
	private String message;
	
	private String joburl;
	
	private int jobId;
	
	private int resumeId;
	
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
	/**
	 * @return the resumeId
	 */
	public int getResumeId() {
		return resumeId;
	}
	/**
	 * @param resumeId the resumeId to set
	 */
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}
}
