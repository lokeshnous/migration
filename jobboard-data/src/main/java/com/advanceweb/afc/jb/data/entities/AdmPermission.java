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
@Table(name = "adm_permission")
public class AdmPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-many association to AdmRole
	@ManyToMany
	@JoinTable(name = "adm_role_permission", joinColumns = { @JoinColumn(name = "permission_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<AdmRole> admRoles;

	private String description;

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "permission_id", insertable = false, updatable = false)
	private int permissionId;

	public AdmPermission() {
	}

	public List<AdmRole> getAdmRoles() {
		return admRoles;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setAdmRoles(List<AdmRole> admRoles) {
		this.admRoles = admRoles;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

}