/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the res_upload_resume database table.
 * 
 */
@Entity
@Table(name="res_upload_resume")
public class ResUploadResume implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The upload resume id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="upload_resume_id")
	private int uploadResumeId;

	/** The active. */
	private int active;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The create user id. */
	@Column(name="create_user_id")
	private int createUserId;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The delete user id. */
	@Column(name="delete_user_id")
	private int deleteUserId;

	/** The file name. */
	@Column(name="file_name")
	private String fileName;

	/** The file path. */
	@Column(name="file_path")
	private String filePath;

	/** The file server. */
	@Column(name="file_server")
	private String fileServer;

	/** The is published. */
	@Column(name="is_published")
	private int isPublished;

	/** The resume name. */
	@Column(name="resume_name")
	private String resumeName;

    /** The resume text. */
    @Lob()
	@Column(name="resume_text")
	private String resumeText;

	/** The resume type. */
	@Column(name="resume_type")
	private String resumeType;

    /** The update dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	/** The update user id. */
	@Column(name="update_user_id")
	private int updateUserId;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to ResPublishResume
	/** The res publish resume. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publish_resume_id")
	private ResPublishResume resPublishResume;

	/**
	 * Gets the upload resume id.
	 *
	 * @return the upload resume id
	 */
	public int getUploadResumeId() {
		return this.uploadResumeId;
	}

	/**
	 * Sets the upload resume id.
	 *
	 * @param uploadResumeId the new upload resume id
	 */
	public void setUploadResumeId(int uploadResumeId) {
		this.uploadResumeId = uploadResumeId;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public int getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Timestamp getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the creates the user id.
	 *
	 * @return the creates the user id
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * Sets the creates the user id.
	 *
	 * @param createUserId the new creates the user id
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
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
	 * Gets the delete user id.
	 *
	 * @return the delete user id
	 */
	public int getDeleteUserId() {
		return this.deleteUserId;
	}

	/**
	 * Sets the delete user id.
	 *
	 * @param deleteUserId the new delete user id
	 */
	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return this.fileName;
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
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * Sets the file path.
	 *
	 * @param filePath the new file path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Gets the file server.
	 *
	 * @return the file server
	 */
	public String getFileServer() {
		return this.fileServer;
	}

	/**
	 * Sets the file server.
	 *
	 * @param fileServer the new file server
	 */
	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}

	/**
	 * Gets the checks if is published.
	 *
	 * @return the checks if is published
	 */
	public int getIsPublished() {
		return this.isPublished;
	}

	/**
	 * Sets the checks if is published.
	 *
	 * @param isPublished the new checks if is published
	 */
	public void setIsPublished(int isPublished) {
		this.isPublished = isPublished;
	}

	/**
	 * Gets the resume name.
	 *
	 * @return the resume name
	 */
	public String getResumeName() {
		return this.resumeName;
	}

	/**
	 * Sets the resume name.
	 *
	 * @param resumeName the new resume name
	 */
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	/**
	 * Gets the resume text.
	 *
	 * @return the resume text
	 */
	public String getResumeText() {
		return this.resumeText;
	}

	/**
	 * Sets the resume text.
	 *
	 * @param resumeText the new resume text
	 */
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	/**
	 * Gets the resume type.
	 *
	 * @return the resume type
	 */
	public String getResumeType() {
		return this.resumeType;
	}

	/**
	 * Sets the resume type.
	 *
	 * @param resumeType the new resume type
	 */
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
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

	/**
	 * Gets the update user id.
	 *
	 * @return the update user id
	 */
	public int getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * Sets the update user id.
	 *
	 * @param updateUserId the new update user id
	 */
	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the res publish resume.
	 *
	 * @return the res publish resume
	 */
	public ResPublishResume getResPublishResume() {
		return this.resPublishResume;
	}

	/**
	 * Sets the res publish resume.
	 *
	 * @param resPublishResume the new res publish resume
	 */
	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}
	
}