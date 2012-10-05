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
	private static final String ADM_ROLE = "admRole";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private int roleId;

	private String description;

	private String name;

	//bi-directional many-to-many association to AdmPermission
	@ManyToMany(mappedBy="admRoles")
	private List<AdmPermission> admPermissions;

	//bi-directional many-to-one association to AdmUserFacility
	@OneToMany(mappedBy=ADM_ROLE)
	private List<AdmUserFacility> admUserFacilities;

	//bi-directional many-to-one association to AdmUserFacilityGroup
	@OneToMany(mappedBy=ADM_ROLE)
	private List<AdmUserFacilityGroup> admUserFacilityGroups;

	//bi-directional many-to-one association to AdmUserFacilitySystem
	@OneToMany(mappedBy=ADM_ROLE)
	private List<AdmUserFacilitySystem> admUserFacilitySystems;

	//bi-directional many-to-one association to AdmUserRole
	@OneToMany(mappedBy=ADM_ROLE)
	private List<AdmUserRole> admUserRoles;

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AdmPermission> getAdmPermissions() {
		return this.admPermissions;
	}

	public void setAdmPermissions(List<AdmPermission> admPermissions) {
		this.admPermissions = admPermissions;
	}
	
	public List<AdmUserFacility> getAdmUserFacilities() {
		return this.admUserFacilities;
	}

	public void setAdmUserFacilities(List<AdmUserFacility> admUserFacilities) {
		this.admUserFacilities = admUserFacilities;
	}
	
	public List<AdmUserFacilityGroup> getAdmUserFacilityGroups() {
		return this.admUserFacilityGroups;
	}

	public void setAdmUserFacilityGroups(List<AdmUserFacilityGroup> admUserFacilityGroups) {
		this.admUserFacilityGroups = admUserFacilityGroups;
	}
	
	public List<AdmUserFacilitySystem> getAdmUserFacilitySystems() {
		return this.admUserFacilitySystems;
	}

	public void setAdmUserFacilitySystems(List<AdmUserFacilitySystem> admUserFacilitySystems) {
		this.admUserFacilitySystems = admUserFacilitySystems;
	}
	
	public List<AdmUserRole> getAdmUserRoles() {
		return this.admUserRoles;
	}

	public void setAdmUserRoles(List<AdmUserRole> admUserRoles) {
		this.admUserRoles = admUserRoles;
	}
	
}