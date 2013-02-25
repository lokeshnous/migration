/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the adm_folder_resume database table.
 * 
 */
@Entity
@Table(name="adm_folder_resume")
public class AdmFolderResume implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The folder resume id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="folder_resume_id")
	private int folderResumeId;

	/** The application status id. */
	@Column(name="application_status_id")
	private int applicationStatusId;

    /** The create dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The folder id. */
	@Column(name="folder_id")
	private int folderId;

	/** The publish resume id. */
	@Column(name="publish_resume_id")
	private int publishResumeId;

	/** The rating. */
	private int rating;

    /** The update dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

    /**
     * Instantiates a new adm folder resume.
     */
    public AdmFolderResume() {
    }

	/**
	 * Gets the folder resume id.
	 *
	 * @return the folder resume id
	 */
	public int getFolderResumeId() {
		return this.folderResumeId;
	}

	/**
	 * Sets the folder resume id.
	 *
	 * @param folderResumeId the new folder resume id
	 */
	public void setFolderResumeId(int folderResumeId) {
		this.folderResumeId = folderResumeId;
	}

	/**
	 * Gets the application status id.
	 *
	 * @return the application status id
	 */
	public int getApplicationStatusId() {
		return this.applicationStatusId;
	}

	/**
	 * Sets the application status id.
	 *
	 * @param applicationStatusId the new application status id
	 */
	public void setApplicationStatusId(int applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Date getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the delete dt.
	 *
	 * @return the delete dt
	 */
	public Date getDeleteDt() {
		return this.deleteDt;
	}

	/**
	 * Sets the delete dt.
	 *
	 * @param deleteDt the new delete dt
	 */
	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	/**
	 * Gets the folder id.
	 *
	 * @return the folder id
	 */
	public int getFolderId() {
		return this.folderId;
	}

	/**
	 * Sets the folder id.
	 *
	 * @param folderId the new folder id
	 */
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	/**
	 * Gets the publish resume id.
	 *
	 * @return the publish resume id
	 */
	public int getPublishResumeId() {
		return this.publishResumeId;
	}

	/**
	 * Sets the publish resume id.
	 *
	 * @param publishResumeId the new publish resume id
	 */
	public void setPublishResumeId(int publishResumeId) {
		this.publishResumeId = publishResumeId;
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public int getRating() {
		return this.rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Gets the update dt.
	 *
	 * @return the update dt
	 */
	public Date getUpdateDt() {
		return this.updateDt;
	}

	/**
	 * Sets the update dt.
	 *
	 * @param updateDt the new update dt
	 */
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

}