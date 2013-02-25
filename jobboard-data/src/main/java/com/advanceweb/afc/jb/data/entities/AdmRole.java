/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the adm_role database table.
 * 
 */
@Entity
@Table(name="adm_role")
public class AdmRole implements Serializable {
	
	/** The Constant ADM_ROLE. */
	private static final String ADM_ROLE = "admRole";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The role id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private int roleId;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	//bi-directional many-to-many association to AdmPermission
	/** The adm permissions. */
	@ManyToMany(mappedBy="admRoles")
	private List<AdmPermission> admPermissions;

	//bi-directional many-to-one association to AdmUserFacility
	/** The adm user facilities. */
	@OneToMany(mappedBy=ADM_ROLE)
	private List<AdmUserFacility> admUserFacilities;

	//bi-directional many-to-one association to AdmUserFacilityGroup
	/** The adm user facility groups. */
	@OneToMany(mappedBy=ADM_ROLE)
	private List<AdmUserFacilityGroup> admUserFacilityGroups;

	//bi-directional many-to-one association to AdmUserFacilitySystem
	/** The adm user facility systems. */
	@OneToMany(mappedBy=ADM_ROLE)
	private List<AdmUserFacilitySystem> admUserFacilitySystems;

	//bi-directional many-to-one association to AdmUserRole
	/** The adm user roles. */
	@OneToMany(mappedBy=ADM_ROLE)
	private List<AdmUserRole> admUserRoles;

	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public int getRoleId() {
		return this.roleId;
	}

	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
	 * Gets the adm permissions.
	 *
	 * @return the adm permissions
	 */
	public List<AdmPermission> getAdmPermissions() {
		return this.admPermissions;
	}

	/**
	 * Sets the adm permissions.
	 *
	 * @param admPermissions the new adm permissions
	 */
	public void setAdmPermissions(List<AdmPermission> admPermissions) {
		this.admPermissions = admPermissions;
	}
	
	/**
	 * Gets the adm user facilities.
	 *
	 * @return the adm user facilities
	 */
	public List<AdmUserFacility> getAdmUserFacilities() {
		return this.admUserFacilities;
	}

	/**
	 * Sets the adm user facilities.
	 *
	 * @param admUserFacilities the new adm user facilities
	 */
	public void setAdmUserFacilities(List<AdmUserFacility> admUserFacilities) {
		this.admUserFacilities = admUserFacilities;
	}
	
	/**
	 * Gets the adm user facility groups.
	 *
	 * @return the adm user facility groups
	 */
	public List<AdmUserFacilityGroup> getAdmUserFacilityGroups() {
		return this.admUserFacilityGroups;
	}

	/**
	 * Sets the adm user facility groups.
	 *
	 * @param admUserFacilityGroups the new adm user facility groups
	 */
	public void setAdmUserFacilityGroups(List<AdmUserFacilityGroup> admUserFacilityGroups) {
		this.admUserFacilityGroups = admUserFacilityGroups;
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
	
	/**
	 * Gets the adm user roles.
	 *
	 * @return the adm user roles
	 */
	public List<AdmUserRole> getAdmUserRoles() {
		return this.admUserRoles;
	}

	/**
	 * Sets the adm user roles.
	 *
	 * @param admUserRoles the new adm user roles
	 */
	public void setAdmUserRoles(List<AdmUserRole> admUserRoles) {
		this.admUserRoles = admUserRoles;
	}
	
}