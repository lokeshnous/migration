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
 * The persistent class for the adm_user_facility_group database table.
 * 
 */
@Entity
@Table(name="adm_user_facility_group")
public class AdmUserFacilityGroup implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The group pk. */
	@EmbeddedId
	private AdmUserFacilityGroupPK groupPK;

	/** The create dt. */
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

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

	//bi-directional many-to-one association to AdmRole
	/** The adm role. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", insertable=false, updatable=false)
	private AdmRole admRole;

	//bi-directional many-to-one association to AdmFacilityGroup
	/** The adm facility group. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_group_id", insertable=false, updatable=false)
	private AdmFacilityGroup admFacilityGroup;

	/**
	 * Gets the group pk.
	 *
	 * @return the group pk
	 */
	public AdmUserFacilityGroupPK getGroupPK() {
		return groupPK;
	}

	/**
	 * Sets the group pk.
	 *
	 * @param groupPK the new group pk
	 */
	public void setGroupPK(AdmUserFacilityGroupPK groupPK) {
		this.groupPK = groupPK;
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
	 * Gets the adm role.
	 *
	 * @return the adm role
	 */
	public AdmRole getAdmRole() {
		return this.admRole;
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
	 * Gets the adm facility group.
	 *
	 * @return the adm facility group
	 */
	public AdmFacilityGroup getAdmFacilityGroup() {
		return this.admFacilityGroup;
	}

	/**
	 * Sets the adm facility group.
	 *
	 * @param admFacilityGroup the new adm facility group
	 */
	public void setAdmFacilityGroup(AdmFacilityGroup admFacilityGroup) {
		this.admFacilityGroup = admFacilityGroup;
	}
	
}