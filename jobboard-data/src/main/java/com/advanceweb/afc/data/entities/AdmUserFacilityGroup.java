package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_user_facility_group database table.
 * 
 */
@Entity
@Table(name = "adm_user_facility_group")
public class AdmUserFacilityGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to AdmFacilityGroup
	@ManyToOne
	@JoinColumn(name = "facility_group_id", insertable = false, updatable = false)
	private AdmFacilityGroup admFacilityGroup;

	// bi-directional many-to-one association to AdmRole
	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private AdmRole admRole;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt")
	private Date createDt;

	@Column(name = "create_user_id")
	private int createUserId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	@Column(name = "delete_user_id")
	private int deleteUserId;

	@EmbeddedId
	private AdmUserFacilityGroupPK id;

	public AdmUserFacilityGroup() {
	}

	public AdmFacilityGroup getAdmFacilityGroup() {
		return admFacilityGroup;
	}

	public AdmRole getAdmRole() {
		return admRole;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public Date getDeleteDt() {
		return deleteDt;
	}

	public int getDeleteUserId() {
		return deleteUserId;
	}

	public AdmUserFacilityGroupPK getId() {
		return id;
	}

	public void setAdmFacilityGroup(AdmFacilityGroup admFacilityGroup) {
		this.admFacilityGroup = admFacilityGroup;
	}

	public void setAdmRole(AdmRole admRole) {
		this.admRole = admRole;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public void setId(AdmUserFacilityGroupPK id) {
		this.id = id;
	}

}