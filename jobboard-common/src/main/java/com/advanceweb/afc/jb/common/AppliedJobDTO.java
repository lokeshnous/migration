package com.advanceweb.afc.jb.common;

import java.util.Date;

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

	private int saveJobId;
	private String appliedDt;
	private Date createDt;
	private Date deleteDt;
	private String facilityName;
	private String jobTitle;
	private int userId;
	private JobPostDTO jpJob;
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
	public Date getCreateDt() {
		return createDt;
	}
	/**
	 * @param createDt the createDt to set
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	/**
	 * @return the deleteDt
	 */
	public Date getDeleteDt() {
		return deleteDt;
	}
	/**
	 * @param deleteDt the deleteDt to set
	 */
	public void setDeleteDt(Date deleteDt) {
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
	
	
	
	/*private String jobTitle;
	private Date appliedDate;
	private String companyName;

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
*/
}
