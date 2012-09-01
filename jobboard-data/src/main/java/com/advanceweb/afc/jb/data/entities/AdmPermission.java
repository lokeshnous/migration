package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adm_permission database table.
 * 
 */
@Entity
@Table(name="adm_permission")
public class AdmPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="permission_id")
	private int permissionId;

	private String description;

	private String name;

	//bi-directional many-to-many association to AdmRole
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

	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
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

	public List<AdmRole> getAdmRoles() {
		return this.admRoles;
	}

	public void setAdmRoles(List<AdmRole> admRoles) {
		this.admRoles = admRoles;
	}
	
}