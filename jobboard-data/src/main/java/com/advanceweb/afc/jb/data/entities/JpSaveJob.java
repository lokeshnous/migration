package com.advanceweb.afc.jb.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the jp_save_job database table
 * 
 * @author bharatiu
 * @version 1.0
 * @since 12th July 2012
 */

@Entity
@Table(name="jp_save_job")
public class JpSaveJob {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="save_job_id")
	private int jpSaveJobId; 
	
	@Column(name="user_id")
	private String userID;
	
	@Column(name="job_id")
	private String jobID;
	
	@Column(name="job_title")
	private String jobTitle;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="created_date")
	private Date createdDate;

	/**
	 * @return the jpSaveJobId
	 */
	public int getJpSaveJobId() {
		return jpSaveJobId;
	}

	/**
	 * @param jpSaveJobId the jpSaveJobId to set
	 */
	public void setJpSaveJobId(int jpSaveJobId) {
		this.jpSaveJobId = jpSaveJobId;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the jobID
	 */
	public String getJobID() {
		return jobID;
	}

	/**
	 * @param jobID the jobID to set
	 */
	public void setJobID(String jobID) {
		this.jobID = jobID;
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
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	
}
