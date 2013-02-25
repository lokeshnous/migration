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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the mer_user_profile database table.
 * 
 */
@Entity
@Table(name="mer_user_profile")
public class MerUserProfile implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The profile pk. */
	@EmbeddedId
	private MerUserProfilePK profilePK;

	/** The attrib value. */
	@Column(name="attrib_value")
	private String attribValue;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

    /** The update dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	//bi-directional many-to-one association to MerUser
	/** The mer user. */
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	private MerUser merUser;

	//bi-directional many-to-one association to MerProfileAttrib
	/** The mer profile attrib. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="profile_attrib_id", insertable=false, updatable=false)
	private MerProfileAttrib merProfileAttrib;

	/**
	 * Gets the attrib value.
	 *
	 * @return the attrib value
	 */
	public String getAttribValue() {
		return this.attribValue;
	}

	/**
	 * Sets the attrib value.
	 *
	 * @param attribValue the new attrib value
	 */
	public void setAttribValue(String attribValue) {
		this.attribValue = attribValue;
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
	 * Gets the mer user.
	 *
	 * @return the mer user
	 */
	public MerUser getMerUser() {
		return this.merUser;
	}

	/**
	 * Sets the mer user.
	 *
	 * @param merUser the new mer user
	 */
	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}
	
	/**
	 * Gets the mer profile attrib.
	 *
	 * @return the mer profile attrib
	 */
	public MerProfileAttrib getMerProfileAttrib() {
		return this.merProfileAttrib;
	}

	/**
	 * Sets the mer profile attrib.
	 *
	 * @param merProfileAttrib the new mer profile attrib
	 */
	public void setMerProfileAttrib(MerProfileAttrib merProfileAttrib) {
		this.merProfileAttrib = merProfileAttrib;
	}

	/**
	 * @return the profilePK
	 */
	public MerUserProfilePK getProfilePK() {
		return profilePK;
	}

	/**
	 * @param profilePK the profilePK to set
	 */
	public void setProfilePK(MerUserProfilePK profilePK) {
		this.profilePK = profilePK;
	}
	
}