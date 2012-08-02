package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adm_role database table.
 * 
 */
@Entity
@Table(name="adm_role")
public class AdmRole implements Serializable {
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
	@OneToMany(mappedBy="admRole")
	private List<AdmUserFacility> admUserFacilities;

	//bi-directional many-to-one association to AdmUserFacilityGroup
	@OneToMany(mappedBy="admRole")
	private List<AdmUserFacilityGroup> admUserFacilityGroups;

	//bi-directional many-to-one association to AdmUserFacilitySystem
	@OneToMany(mappedBy="admRole")
	private List<AdmUserFacilitySystem> admUserFacilitySystems;

	//bi-directional many-to-one association to AdmUserRole
	@OneToMany(mappedBy="admRole")
	private List<AdmUserRole> admUserRoles;

    public AdmRole() {
    }

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