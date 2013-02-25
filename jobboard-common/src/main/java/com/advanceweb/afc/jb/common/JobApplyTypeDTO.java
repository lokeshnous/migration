/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * <code> JobApplyTypeDTO </code> is a DTO class. The purpose of this class to
 * hold the apply type of job.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 28 August 2012
 */

public class JobApplyTypeDTO {

	/** The job apply id. */
	private int jobApplyID;
	
	/** The job id. */
	private JobPostDTO jobID;
	
	/** The apply method. */
	private String applyMethod;
	
	/** The apply link. */
	private String applyLink;
	
	/** The b is active. */
	private boolean bIsActive;

	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public JobPostDTO getJobID() {
		return jobID;
	}
	
	/**
	 * Sets the job id.
	 *
	 * @param jobID the new job id
	 */
	public void setJobID(JobPostDTO jobID) {
		this.jobID = jobID;
	}
	

	/**
	 * Gets the job apply id.
	 *
	 * @return the job apply id
	 */
	public int getJobApplyID() {
		return jobApplyID;
	}

	/**
	 * Sets the job apply id.
	 *
	 * @param jobApplyID the new job apply id
	 */
	public void setJobApplyID(int jobApplyID) {
		this.jobApplyID = jobApplyID;
	}

	/**
	 * Gets the apply method.
	 *
	 * @return the apply method
	 */
	public String getApplyMethod() {
		return applyMethod;
	}

	/**
	 * Sets the apply method.
	 *
	 * @param applyMethod the new apply method
	 */
	public void setApplyMethod(String applyMethod) {
		this.applyMethod = applyMethod;
	}

	/**
	 * Gets the apply link.
	 *
	 * @return the apply link
	 */
	public String getApplyLink() {
		return applyLink;
	}

	/**
	 * Sets the apply link.
	 *
	 * @param applyLink the new apply link
	 */
	public void setApplyLink(String applyLink) {
		this.applyLink = applyLink;
	}

	/**
	 * @return the bIsActive
	 */
	public boolean isbIsActive() {
		return bIsActive;
	}

	/**
	 * @param bIsActive the bIsActive to set
	 */
	public void setbIsActive(boolean bIsActive) {
		this.bIsActive = bIsActive;
	}


}
