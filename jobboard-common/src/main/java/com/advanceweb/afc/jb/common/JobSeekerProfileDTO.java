/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class JobSeekerProfileDTO extends BaseProfileDTO {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;
	
	/** The employment information. */
	private String employmentInformation;
	
	/** The ethinicity. */
	private String ethinicity;
	
	/** The gender. */
	private String gender;
	
	/** The job seeker id. */
	private long jobSeekerId;
	
	/** The veteran status. */
	private String veteranStatus;
	
	/** The file name. */
	private String fileName;
	
	/** The profile id. */
	private int profileId;
	
	/**
	 * Gets the employment information.
	 *
	 * @return the employment information
	 */
	public String getEmploymentInformation() {
		return employmentInformation;
	}

	/**
	 * Gets the ethinicity.
	 *
	 * @return the ethinicity
	 */
	public String getEthinicity() {
		return ethinicity;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Gets the job seeker id.
	 *
	 * @return the job seeker id
	 */
	public long getJobSeekerId() {
		return jobSeekerId;
	}

	/**
	 * Gets the veteran status.
	 *
	 * @return the veteran status
	 */
	public String getVeteranStatus() {
		return veteranStatus;
	}

	/**
	 * Sets the employment information.
	 *
	 * @param employmentInformation the new employment information
	 */
	public void setEmploymentInformation(String employmentInformation) {
		this.employmentInformation = employmentInformation;
	}

	/**
	 * Sets the ethinicity.
	 *
	 * @param ethinicity the new ethinicity
	 */
	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Sets the job seeker id.
	 *
	 * @param jobSeekerId the new job seeker id
	 */
	public void setJobSeekerId(long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	/**
	 * Sets the veteran status.
	 *
	 * @param veteranStatus the new veteran status
	 */
	public void setVeteranStatus(String veteranStatus) {
		this.veteranStatus = veteranStatus;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the profile id.
	 *
	 * @return the profile id
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * Sets the profile id.
	 *
	 * @param profileId the new profile id
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

}