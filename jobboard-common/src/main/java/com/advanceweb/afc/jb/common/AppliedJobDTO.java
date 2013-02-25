/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * <code> AppliedJobDTO </code> is a DTO class. The purpose of this class to
 * hold the required information for applied job.
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 2 July 2012
 * 
 * 
 */
public class AppliedJobDTO {

	/** The save job id. */
	private int saveJobId;
	
	/** The applied dt. */
	private String appliedDt;
	
	/** The create dt. */
	private String createDt;
	
	/** The delete dt. */
	private String deleteDt;
	
	/** The facility name. */
	private String facilityName;
	
	/** The job title. */
	private String jobTitle;
	
	/** The user id. */
	private int userId;
	
	/** The job age. */
	private long jobAge;
	
	/** The jp job. */
	private JobPostDTO jpJob;
	
	
	/**
	 * Gets the job age.
	 *
	 * @return the job age
	 */
	public long getJobAge() {
		return jobAge;
	}
	
	/**
	 * Sets the job age.
	 *
	 * @param jobAge the new job age
	 */
	public void setJobAge(long jobAge) {
		this.jobAge = jobAge;
	}
	/**
	 * @return the saveJobId
	 */
	public int getSaveJobId() {
		return saveJobId;
	}
	/**
	 * @param saveJobId the saveJobId to set
	 */
	public void setSaveJobId(int saveJobId) {
		this.saveJobId = saveJobId;
	}
	/**
	 * @return the appliedDt
	 */
	public String getAppliedDt() {
		return appliedDt;
	}
	/**
	 * @param appliedDt the appliedDt to set
	 */
	public void setAppliedDt(String appliedDt) {
		this.appliedDt = appliedDt;
	}
	/**
	 * @return the createDt
	 */
	public String getCreateDt() {
		return createDt;
	}
	/**
	 * @param createDt the createDt to set
	 */
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	/**
	 * @return the deleteDt
	 */
	public String getDeleteDt() {
		return deleteDt;
	}
	/**
	 * @param deleteDt the deleteDt to set
	 */
	public void setDeleteDt(String deleteDt) {
		this.deleteDt = deleteDt;
	}
	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}
	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	/**
	 * @return the jobtitle
	 */

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the jpJob
	 */
	public JobPostDTO getJpJob() {
		return jpJob;
	}
	/**
	 * @param jpJob the jpJob to set
	 */
	public void setJpJob(JobPostDTO jpJob) {
		this.jpJob = jpJob;
	}
}
