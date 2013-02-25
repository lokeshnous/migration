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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the res_publish_resume database table.
 * 
 */
@Entity
@Table(name="res_publish_resume")
public class ResPublishResume implements Serializable {
	
	/** The Constant RES_PUBLISH_RESUME. */
	private static final String RES_PUBLISH_RESUME = "resPublishResume";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The publish resume id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="publish_resume_id")
	private int publishResumeId;

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

	/** The orig resume id. */
	@Column(name="orig_resume_id")
	private int origResumeId;

	/** The orig resume type. */
	@Column(name="orig_resume_type")
	private String origResumeType;

	/** The resume name. */
	@Column(name="resume_name")
	private String resumeName;

    /** The resume text. */
    @Lob()
	@Column(name="resume_text")
	private String resumeText;

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

	//bi-directional many-to-one association to ResBuilderResume
	/** The res builder resumes. */
	@OneToMany(mappedBy=RES_PUBLISH_RESUME)
	private List<ResBuilderResume> resBuilderResumes;

	//bi-directional many-to-one association to ResPublishResumePriv
	/** The res publish resume privs. */
	@OneToMany(mappedBy=RES_PUBLISH_RESUME)
	private List<ResPublishResumePriv> resPublishResumePrivs;

	//bi-directional one-to-one association to ResPublishResumeStat
	/** The res publish resume stat. */
	@OneToOne(mappedBy=RES_PUBLISH_RESUME, fetch=FetchType.LAZY)
	private ResPublishResumeStat resPublishResumeStat;

	//bi-directional many-to-one association to ResUploadResume
	/** The res upload resumes. */
	@OneToMany(mappedBy=RES_PUBLISH_RESUME)
	private List<ResUploadResume> resUploadResumes;

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
	 * Gets the orig resume id.
	 *
	 * @return the orig resume id
	 */
	public int getOrigResumeId() {
		return this.origResumeId;
	}

	/**
	 * Sets the orig resume id.
	 *
	 * @param origResumeId the new orig resume id
	 */
	public void setOrigResumeId(int origResumeId) {
		this.origResumeId = origResumeId;
	}

	/**
	 * Gets the orig resume type.
	 *
	 * @return the orig resume type
	 */
	public String getOrigResumeType() {
		return this.origResumeType;
	}

	/**
	 * Sets the orig resume type.
	 *
	 * @param origResumeType the new orig resume type
	 */
	public void setOrigResumeType(String origResumeType) {
		this.origResumeType = origResumeType;
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
	 * Gets the res builder resumes.
	 *
	 * @return the res builder resumes
	 */
	public List<ResBuilderResume> getResBuilderResumes() {
		return this.resBuilderResumes;
	}

	/**
	 * Sets the res builder resumes.
	 *
	 * @param resBuilderResumes the new res builder resumes
	 */
	public void setResBuilderResumes(List<ResBuilderResume> resBuilderResumes) {
		this.resBuilderResumes = resBuilderResumes;
	}
	
	/**
	 * Gets the res publish resume privs.
	 *
	 * @return the res publish resume privs
	 */
	public List<ResPublishResumePriv> getResPublishResumePrivs() {
		return this.resPublishResumePrivs;
	}

	/**
	 * Sets the res publish resume privs.
	 *
	 * @param resPublishResumePrivs the new res publish resume privs
	 */
	public void setResPublishResumePrivs(List<ResPublishResumePriv> resPublishResumePrivs) {
		this.resPublishResumePrivs = resPublishResumePrivs;
	}
	
	/**
	 * Gets the res publish resume stat.
	 *
	 * @return the res publish resume stat
	 */
	public ResPublishResumeStat getResPublishResumeStat() {
		return this.resPublishResumeStat;
	}

	/**
	 * Sets the res publish resume stat.
	 *
	 * @param resPublishResumeStat the new res publish resume stat
	 */
	public void setResPublishResumeStat(ResPublishResumeStat resPublishResumeStat) {
		this.resPublishResumeStat = resPublishResumeStat;
	}
	
	/**
	 * Gets the res upload resumes.
	 *
	 * @return the res upload resumes
	 */
	public List<ResUploadResume> getResUploadResumes() {
		return this.resUploadResumes;
	}

	/**
	 * Sets the res upload resumes.
	 *
	 * @param resUploadResumes the new res upload resumes
	 */
	public void setResUploadResumes(List<ResUploadResume> resUploadResumes) {
		this.resUploadResumes = resUploadResumes;
	}
	
}