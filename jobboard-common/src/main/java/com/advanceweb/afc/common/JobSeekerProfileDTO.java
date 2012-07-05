package com.advanceweb.afc.common;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class JobSeekerProfileDTO extends BaseProfileDTO {

	private String employmentInformation;
	private String ethinicity;
	private String gender;
	private long jobSeekerId;
	private String veteranStatus;

	public JobSeekerProfileDTO() {

	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}

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

}