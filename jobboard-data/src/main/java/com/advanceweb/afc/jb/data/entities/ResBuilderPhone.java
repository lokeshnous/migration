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
 * The persistent class for the res_builder_phone database table.
 * 
 */
@Entity
@Table(name="res_builder_phone")
public class ResBuilderPhone implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The builder phone id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_phone_id")
	private int builderPhoneId;

	/** The phone number. */
	@Column(name="phone_number")
	private String phoneNumber;

	/** The phone type. */
	@Column(name="phone_type")
	private String phoneType;

	//bi-directional many-to-one association to ResBuilderResume
	/** The res builder resume. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	/**
	 * Gets the builder phone id.
	 *
	 * @return the builder phone id
	 */
	public int getBuilderPhoneId() {
		return this.builderPhoneId;
	}

	/**
	 * Sets the builder phone id.
	 *
	 * @param builderPhoneId the new builder phone id
	 */
	public void setBuilderPhoneId(int builderPhoneId) {
		this.builderPhoneId = builderPhoneId;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the phone type.
	 *
	 * @return the phone type
	 */
	public String getPhoneType() {
		return this.phoneType;
	}

	/**
	 * Sets the phone type.
	 *
	 * @param phoneType the new phone type
	 */
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
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