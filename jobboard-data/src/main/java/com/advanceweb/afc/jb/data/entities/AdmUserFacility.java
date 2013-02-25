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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the adm_user_facility database table.
 * 
 */
@Entity
@Table(name="adm_user_facility")
public class AdmUserFacility implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The facility pk. */
	@EmbeddedId
	private AdmUserFacilityPK facilityPK;

	/** The create dt. */
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

	/** The create user id. */
	@Column(name="create_user_id")
	private Integer createUserId;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The delete user id. */
	@Column(name="delete_user_id")
	private Integer deleteUserId;

	//bi-directional many-to-one association to AdmRole
	/** The adm role. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", insertable=false, updatable=false)
	private AdmRole admRole;

	//bi-directional many-to-one association to AdmFacility
	/** The adm facility. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="facility_id", insertable=false, updatable=false)
	private AdmFacility admFacility;

	/**
	 * Gets the facility pk.
	 *
	 * @return the facility pk
	 */
	public AdmUserFacilityPK getFacilityPK() {
		return facilityPK;
	}

	/**
	 * Sets the facility pk.
	 *
	 * @param facilityPK the new facility pk
	 */
	public void setFacilityPK(AdmUserFacilityPK facilityPK) {
		this.facilityPK = facilityPK;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Date getCreateDt() {
		return createDt;
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
	 * Gets the creates the user id.
	 *
	 * @return the creates the user id
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}

	/**
	 * Sets the creates the user id.
	 *
	 * @param createUserId the new creates the user id
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * Gets the delete dt.
	 *
	 * @return the delete dt
	 */
	public Date getDeleteDt() {
		return deleteDt;
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
	public Integer getDeleteUserId() {
		return deleteUserId;
	}

	/**
	 * Sets the delete user id.
	 *
	 * @param deleteUserId the new delete user id
	 */
	public void setDeleteUserId(Integer deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	/**
	 * Gets the adm role.
	 *
	 * @return the adm role
	 */
	public AdmRole getAdmRole() {
		return admRole;
	}

	/**
	 * Sets the adm role.
	 *
	 * @param admRole the new adm role
	 */
	public void setAdmRole(AdmRole admRole) {
		this.admRole = admRole;
	}

	/**
	 * Gets the adm facility.
	 *
	 * @return the adm facility
	 */
	public AdmFacility getAdmFacility() {
		return admFacility;
	}

	/**
	 * Sets the adm facility.
	 *
	 * @param admFacility the new adm facility
	 */
	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}

}