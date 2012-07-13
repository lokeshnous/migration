package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * <code> ApplyJobDTO </code> is a DTO class. The purpose of this class to
 * hold the required information to apply job.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 13 July 2012
 * 
 * 
 */
public class ApplyJobDTO {

	private int userId;
	private int jobId;
	private Date createDate;
	private Date appliedDate;
	private byte isApplied;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	public byte getIsApplied() {
		return isApplied;
	}
	public void setIsApplied(byte isApplied) {
		this.isApplied = isApplied;
	}
	

}
