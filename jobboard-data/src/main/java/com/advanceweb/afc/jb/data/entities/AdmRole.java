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
@Table(name = "adm_role")
public class AdmRole implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-many association to AdmPermission
	@ManyToMany(mappedBy = "admRoles")
	private List<AdmPermission> admPermissions;

	// bi-directional many-to-one association to AdmUserFacility
	@OneToMany(mappedBy = "admRole")
	private List<AdmUserFacility> admUserFacilities;

	// bi-directional many-to-one association to AdmUserFacilityGroup
	@OneToMany(mappedBy = "admRole")
	private List<AdmUserFacilityGroup> admUserFacilityGroups;

	// bi-directional many-to-one association to AdmUserFacilitySystem
	@OneToMany(mappedBy = "admRole")
	private List<AdmUserFacilitySystem> admUserFacilitySystems;

	// bi-directional many-to-one association to AdmUserRole
	@OneToMany(mappedBy = "admRole")
	private List<AdmUserRole> admUserRoles;

	private String description;

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id", insertable = false, updatable = false)
	private int roleId;

	@Column(name = "role_level")
	private String roleLevel;

	public AdmRole() {
	}

	public List<AdmPermission> getAdmPermissions() {
		return admPermissions;
	}

	public List<AdmUserFacility> getAdmUserFacilities() {
		return admUserFacilities;
	}

	public List<AdmUserFacilityGroup> getAdmUserFacilityGroups() {
		return admUserFacilityGroups;
	}

	public List<AdmUserFacilitySystem> getAdmUserFacilitySystems() {
		return admUserFacilitySystems;
	}

	public List<AdmUserRole> getAdmUserRoles() {
		return admUserRoles;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public int getRoleId() {
		return roleId;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setAdmPermissions(List<AdmPermission> admPermissions) {
		this.admPermissions = admPermissions;
	}

	public void setAdmUserFacilities(List<AdmUserFacility> admUserFacilities) {
		this.admUserFacilities = admUserFacilities;
	}

	public void setAdmUserFacilityGroups(
			List<AdmUserFacilityGroup> admUserFacilityGroups) {
		this.admUserFacilityGroups = admUserFacilityGroups;
	}

	public void setAdmUserFacilitySystems(
			List<AdmUserFacilitySystem> admUserFacilitySystems) {
		this.admUserFacilitySystems = admUserFacilitySystems;
	}

	public void setAdmUserRoles(List<AdmUserRole> admUserRoles) {
		this.admUserRoles = admUserRoles;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

}