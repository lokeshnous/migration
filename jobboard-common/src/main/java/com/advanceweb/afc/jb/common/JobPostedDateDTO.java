package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act a DTO for the Dropdown of Job Posted field in Jobseekers Advance Search
 */
public class JobPostedDateDTO implements Serializable {

	
	private String JobPostedDateId;
	private String JobPostedDateValue;
	
	
	/**
	 * @return the jobPostedDateId
	 */
	public String getJobPostedDateId() {
		return JobPostedDateId;
	}
	/**
	 * @param jobPostedDateId the jobPostedDateId to set
	 */
	public void setJobPostedDateId(String jobPostedDateId) {
		JobPostedDateId = jobPostedDateId;
	}
	/**
	 * @return the jobPostedDateValue
	 */
	public String getJobPostedDateValue() {
		return JobPostedDateValue;
	}
	/**
	 * @param jobPostedDateValue the jobPostedDateValue to set
	 */
	public void setJobPostedDateValue(String jobPostedDateValue) {
		JobPostedDateValue = jobPostedDateValue;
	}
	
	
	
}
