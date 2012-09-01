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

	private int jobApplyID;
	private JobPostDTO jobID;
	private String applyMethod;
	private String applyLink;
	private boolean bIsActive;

	public JobPostDTO getJobID() {
		return jobID;
	}
	
	public void setJobID(JobPostDTO jobID) {
		this.jobID = jobID;
	}
	

	public int getJobApplyID() {
		return jobApplyID;
	}

	public void setJobApplyID(int jobApplyID) {
		this.jobApplyID = jobApplyID;
	}

	public String getApplyMethod() {
		return applyMethod;
	}

	public void setApplyMethod(String applyMethod) {
		this.applyMethod = applyMethod;
	}

	public String getApplyLink() {
		return applyLink;
	}

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
