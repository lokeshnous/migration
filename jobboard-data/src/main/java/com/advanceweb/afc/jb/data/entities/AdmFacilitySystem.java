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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the adm_facility_system database table.
 * 
 */
@Entity
@Table(name="adm_facility_system")
public class AdmFacilitySystem implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The facility system id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="facility_system_id")
	private int facilitySystemId;

	/** The admin user id. */
	@Column(name="admin_user_id")
	private int adminUserId;

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

	/** The name. */
	private String name;

	//bi-directional many-to-one association to AdmFacilityGroup
	/** The adm facility groups. */
	@OneToMany(mappedBy="admFacilitySystem")
	private List<AdmFacilityGroup> admFacilityGroups;

	//bi-directional many-to-one association to AdmUserFacilitySystem
	/** The adm user facility systems. */
	@OneToMany(mappedBy="admFacilitySystem")
	private List<AdmUserFacilitySystem> admUserFacilitySystems;

	/**
	 * Gets the facility system id.
	 *
	 * @return the facility system id
	 */
	public int getFacilitySystemId() {
		return this.facilitySystemId;
	}

	/**
	 * Sets the facility system id.
	 *
	 * @param facilitySystemId the new facility system id
	 */
	public void setFacilitySystemId(int facilitySystemId) {
		this.facilitySystemId = facilitySystemId;
	}

	/**
	 * Gets the admin user id.
	 *
	 * @return the admin user id
	 */
	public int getAdminUserId() {
		return this.adminUserId;
	}

	/**
	 * Sets the admin user id.
	 *
	 * @param adminUserId the new admin user id
	 */
	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
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
	 * Gets the adm facility groups.
	 *
	 * @return the adm facility groups
	 */
	public List<AdmFacilityGroup> getAdmFacilityGroups() {
		return this.admFacilityGroups;
	}

	/**
	 * Sets the adm facility groups.
	 *
	 * @param admFacilityGroups the new adm facility groups
	 */
	public void setAdmFacilityGroups(List<AdmFacilityGroup> admFacilityGroups) {
		this.admFacilityGroups = admFacilityGroups;
	}
	
	/**
	 * Gets the adm user facility systems.
	 *
	 * @return the adm user facility systems
	 */
	public List<AdmUserFacilitySystem> getAdmUserFacilitySystems() {
		return this.admUserFacilitySystems;
	}

	/**
	 * Sets the adm user facility systems.
	 *
	 * @param admUserFacilitySystems the new adm user facility systems
	 */
	public void setAdmUserFacilitySystems(List<AdmUserFacilitySystem> admUserFacilitySystems) {
		this.admUserFacilitySystems = admUserFacilitySystems;
	}
	
}