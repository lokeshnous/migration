package com.advanceweb.afc.jb.common;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class JobSeekerProfileDTO extends BaseProfileDTO {

	private static final long	serialVersionUID	= 1L;
	
	private String employmentInformation;
	private String ethinicity;
	private String gender;
	private long jobSeekerId;
	private String veteranStatus;
	private String fileName;
	private int profileId;
	
	public String getEmploymentInformation() {
		return employmentInformation;
	}

	public String getEthinicity() {
		return ethinicity;
	}

	public String getGender() {
		return gender;
	}

	public long getJobSeekerId() {
		return jobSeekerId;
	}

	public String getVeteranStatus() {
		return veteranStatus;
	}

	public void setEmploymentInformation(String employmentInformation) {
		this.employmentInformation = employmentInformation;
	}

	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setJobSeekerId(long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public void setVeteranStatus(String veteranStatus) {
		this.veteranStatus = veteranStatus;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

}