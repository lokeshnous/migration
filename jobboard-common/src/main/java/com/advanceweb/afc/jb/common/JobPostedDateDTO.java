package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act a DTO for the Dropdown of Job Posted field in Jobseekers Advance Search
 */
public class JobPostedDateDTO implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	private String jobPostedDateId;
	private String jobPostedDateValue;
	/**
	 * @return the jobPostedDateId
	 */
	public String getJobPostedDateId() {
		return jobPostedDateId;
	}
	/**
	 * @param jobPostedDateId the jobPostedDateId to set
	 */
	public void setJobPostedDateId(String jobPostedDateId) {
		this.jobPostedDateId = jobPostedDateId;
	}
	/**
	 * @return the jobPostedDateValue
	 */
	public String getJobPostedDateValue() {
		return jobPostedDateValue;
	}
	/**
	 * @param jobPostedDateValue the jobPostedDateValue to set
	 */
	public void setJobPostedDateValue(String jobPostedDateValue) {
		this.jobPostedDateValue = jobPostedDateValue;
	}
	
	
	
}
