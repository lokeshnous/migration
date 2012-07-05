package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * <code> SavedJobDTO </code> is a DTO class. The purpose of this class to
 * hold the required information for Saved Job job.
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 2 July 2012
 * 
 * 
 */

public class SavedJobDTO {

	private String jobTitle;
	private Date createdDate;
	private String companyName;
	private int jobAge;

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getJobAge() {
		return jobAge;
	}

	public void setJobAge(int jobAge) {
		this.jobAge = jobAge;
	}

}
