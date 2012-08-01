package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class JobSeekerDashBoardForm {
	private String userName;
	private int savedSearchCount;
	private int savedJobsCount;
	private int appliedJobsCount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSavedSearchCount() {
		return savedSearchCount;
	}

	public void setSavedSearchCount(int savedSearchCount) {
		this.savedSearchCount = savedSearchCount;
	}

	public int getSavedJobsCount() {
		return savedJobsCount;
	}

	public void setSavedJobsCount(int savedJobsCount) {
		this.savedJobsCount = savedJobsCount;
	}

	public int getAppliedJobsCount() {
		return appliedJobsCount;
	}

	public void setAppliedJobsCount(int appliedJobsCount) {
		this.appliedJobsCount = appliedJobsCount;
	}
	
}
