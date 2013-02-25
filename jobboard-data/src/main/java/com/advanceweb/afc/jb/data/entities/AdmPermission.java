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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * The persistent class for the adm_permission database table.
 * 
 */
@Entity
@Table(name="adm_permission")
public class AdmPermission implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The permission id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="permission_id")
	private int permissionId;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	//bi-directional many-to-many association to AdmRole
    /** The adm roles. */
	@ManyToMany
	@JoinTable(
		name="adm_role_permission"
		, joinColumns={
			@JoinColumn(name="permission_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<AdmRole> admRoles;

	/**
	 * Gets the permission id.
	 *
	 * @return the permission id
	 */
	public int getPermissionId() {
		return this.permissionId;
	}

	/**
	 * Sets the permission id.
	 *
	 * @param permissionId the new permission id
	 */
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
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
	 * Gets the adm roles.
	 *
	 * @return the adm roles
	 */
	public List<AdmRole> getAdmRoles() {
		return this.admRoles;
	}

	/**
	 * Sets the adm roles.
	 *
	 * @param admRoles the new adm roles
	 */
	public void setAdmRoles(List<AdmRole> admRoles) {
		this.admRoles = admRoles;
	}
	
}