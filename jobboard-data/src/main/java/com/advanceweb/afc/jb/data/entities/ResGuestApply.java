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


/**
 * The persistent class for the res_guest_apply database table.
 * 
 */
@Entity
@Table(name="res_guest_apply")
public class ResGuestApply implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The guest apply id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="guest_apply_id")
	private int guestApplyId;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The email. */
	private String email;

	/** The file name. */
	@Column(name="file_name")
	private String fileName;

	/** The file path. */
	@Column(name="file_path")
	private String filePath;

	/** The file server. */
	@Column(name="file_server")
	private String fileServer;

	/** The name. */
	private String name;

    /** The resume text. */
    @Lob()
	@Column(name="resume_text")
	private String resumeText;

	//bi-directional many-to-one association to JpJob
	/** The jp job. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id")
	private JpJob jpJob;

	/**
	 * Gets the guest apply id.
	 *
	 * @return the guest apply id
	 */
	public int getGuestApplyId() {
		return this.guestApplyId;
	}

	/**
	 * Sets the guest apply id.
	 *
	 * @param guestApplyId the new guest apply id
	 */
	public void setGuestApplyId(int guestApplyId) {
		this.guestApplyId = guestApplyId;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Gets the jp job.
	 *
	 * @return the jp job
	 */
	public JpJob getJpJob() {
		return this.jpJob;
	}

	/**
	 * Sets the jp job.
	 *
	 * @param jpJob the new jp job
	 */
	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}
	
}