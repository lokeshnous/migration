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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the res_builder_certification database table.
 * 
 */
@Entity
@Table(name="res_builder_certification")
public class ResBuilderCertification implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The builder certification id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_certification_id")
	private int builderCertificationId;

	/** The certification name. */
	@Column(name="certification_name")
	private String certificationName;

	/** The certifying authority. */
	@Column(name="certifying_authority")
	private String certifyingAuthority;

	/** The description. */
	private String description;

    /** The earned dt. */
    @Temporal( TemporalType.DATE)
	@Column(name="earned_dt")
	private Date earnedDt;

    /** The expire dt. */
    @Temporal( TemporalType.DATE)
	@Column(name="expire_dt")
	private Date expireDt;

	//bi-directional many-to-one association to ResBuilderResume
	/** The res builder resume. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	/**
	 * Gets the builder certification id.
	 *
	 * @return the builder certification id
	 */
	public int getBuilderCertificationId() {
		return this.builderCertificationId;
	}

	/**
	 * Sets the builder certification id.
	 *
	 * @param builderCertificationId the new builder certification id
	 */
	public void setBuilderCertificationId(int builderCertificationId) {
		this.builderCertificationId = builderCertificationId;
	}

	/**
	 * Gets the certification name.
	 *
	 * @return the certification name
	 */
	public String getCertificationName() {
		return this.certificationName;
	}

	/**
	 * Sets the certification name.
	 *
	 * @param certificationName the new certification name
	 */
	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	/**
	 * Gets the certifying authority.
	 *
	 * @return the certifying authority
	 */
	public String getCertifyingAuthority() {
		return this.certifyingAuthority;
	}

	/**
	 * Sets the certifying authority.
	 *
	 * @param certifyingAuthority the new certifying authority
	 */
	public void setCertifyingAuthority(String certifyingAuthority) {
		this.certifyingAuthority = certifyingAuthority;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the earned dt.
	 *
	 * @return the earned dt
	 */
	public Date getEarnedDt() {
		return this.earnedDt;
	}

	/**
	 * Sets the earned dt.
	 *
	 * @param earnedDt the new earned dt
	 */
	public void setEarnedDt(Date earnedDt) {
		this.earnedDt = earnedDt;
	}

	/**
	 * Gets the expire dt.
	 *
	 * @return the expire dt
	 */
	public Date getExpireDt() {
		return this.expireDt;
	}

	/**
	 * Sets the expire dt.
	 *
	 * @param expireDt the new expire dt
	 */
	public void setExpireDt(Date expireDt) {
		this.expireDt = expireDt;
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