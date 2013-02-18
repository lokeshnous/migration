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
 * The persistent class for the adm_user_role database table.
 * 
 */
@Entity
@Table(name="adm_user_role")
public class AdmUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdmUserRolePK rolePK;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

	@Column(name="create_user_id")
	private Integer createUserId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="delete_user_id")
	private Integer deleteUserId;

	//bi-directional many-to-one association to AdmRole
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id", insertable=false, updatable=false)
	private AdmRole admRole;

	public AdmUserRolePK getRolePK() {
		return rolePK;
	}

	public void setRolePK(AdmUserRolePK rolePK) {
		this.rolePK = rolePK;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getDeleteDt() {
		return deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public Integer getDeleteUserId() {
		return deleteUserId;
	}

	public void setDeleteUserId(Integer deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public AdmRole getAdmRole() {
		return admRole;
	}

	public void setAdmRole(AdmRole admRole) {
		this.admRole = admRole;
	}

}