/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the res_builder_references database table.
 * 
 */
@Entity
@Table(name="res_builder_references")
public class ResBuilderReference implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The builder reference id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_reference_id")
	private int builderReferenceId;

	/** The company name. */
	@Column(name="company_name")
	private String companyName;

	/** The contact name. */
	@Column(name="contact_name")
	private String contactName;

	/** The email. */
	private String email;

	/** The is available. */
	@Column(name="is_available")
	private int isAvailable;

	/** The job title. */
	@Column(name="job_title")
	private String jobTitle;

	/** The reference type. */
	@Column(name="reference_type")
	private String referenceType;

	/** The work phone. */
	@Column(name="work_phone")
	private String workPhone;

	//bi-directional many-to-one association to ResBuilderResume
	/** The res builder resume. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	/**
	 * Gets the builder reference id.
	 *
	 * @return the builder reference id
	 */
	public int getBuilderReferenceId() {
		return this.builderReferenceId;
	}

	/**
	 * Sets the builder reference id.
	 *
	 * @param builderReferenceId the new builder reference id
	 */
	public void setBuilderReferenceId(int builderReferenceId) {
		this.builderReferenceId = builderReferenceId;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the contact name.
	 *
	 * @return the contact name
	 */
	public String getContactName() {
		return this.contactName;
	}

	/**
	 * Sets the contact name.
	 *
	 * @param contactName the new contact name
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	 * Gets the checks if is available.
	 *
	 * @return the checks if is available
	 */
	public int getIsAvailable() {
		return this.isAvailable;
	}

	/**
	 * Sets the checks if is available.
	 *
	 * @param isAvailable the new checks if is available
	 */
	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * Gets the job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the new job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Gets the reference type.
	 *
	 * @return the reference type
	 */
	public String getReferenceType() {
		return this.referenceType;
	}

	/**
	 * Sets the reference type.
	 *
	 * @param referenceType the new reference type
	 */
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	/**
	 * Gets the work phone.
	 *
	 * @return the work phone
	 */
	public String getWorkPhone() {
		return this.workPhone;
	}

	/**
	 * Sets the work phone.
	 *
	 * @param workPhone the new work phone
	 */
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	/**
	 * Gets the res builder resume.
	 *
	 * @return the res builder resume
	 */
	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	/**
	 * Sets the res builder resume.
	 *
	 * @param resBuilderResume the new res builder resume
	 */
	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}