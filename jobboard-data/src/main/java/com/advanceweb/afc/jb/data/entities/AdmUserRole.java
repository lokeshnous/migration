package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the adm_user_role database table.
 * 
 */
@Entity
@Table(name="adm_user_role")

public class AdmUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	//Commented this coz adm_user_role has have two primary keys
	@EmbeddedId
	private AdmUserRolePK id;
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="adm_user_id")
	private int admUserId; 
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="role_id")
	private int roleId;*/

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

	@Column(name="create_user_id")
	private int createUserId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="delete_user_id")
	private int deleteUserId;

	//bi-directional many-to-one association to AdmRole
    @ManyToOne
	@JoinColumn(name="role_id", insertable = false, updatable = false)
	private AdmRole admRole;

    public AdmUserRole() {
    }

	/*public AdmUserRolePK getId() {
		return this.id;
	}

	public void setId(AdmUserRolePK id) {
		this.id = id;
	}*/
	
	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public int getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public int getDeleteUserId() {
		return this.deleteUserId;
	}

	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public AdmRole getAdmRole() {
		return this.admRole;
	}

	public void setAdmRole(AdmRole admRole) {
		this.admRole = admRole;
	}

	/**
	 * @return the id
	 */
	public AdmUserRolePK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(AdmUserRolePK id) {
		this.id = id;
	}

	/**
	 * @return the admUserId
	 */
	/*public int getAdmUserId() {
		return admUserId;
	}

	*//**
	 * @param admUserId the admUserId to set
	 *//*
	public void setAdmUserId(int admUserId) {
		this.admUserId = admUserId;
	}

	*//**
	 * @return the userId
	 *//*
	public int getUserId() {
		return userId;
	}

	*//**
	 * @param userId the userId to set
	 *//*
	public void setUserId(int userId) {
		this.userId = userId;
	}

	*//**
	 * @return the roleId
	 *//*
	public int getRoleId() {
		return roleId;
	}

	*//**
	 * @param roleId the roleId to set
	 *//*
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}*/
	
}