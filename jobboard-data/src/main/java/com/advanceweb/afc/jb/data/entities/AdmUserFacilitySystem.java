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
 * The persistent class for the adm_user_facility_system database table.
 * 
 */
@Entity
@Table(name="adm_user_facility_system")
public class AdmUserFacilitySystem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdmUserFacilitySystemPK facilitySystemPK;

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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", insertable=false, updatable=false)
	private AdmRole admRole;

	//bi-directional many-to-one association to AdmFacilitySystem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_system_id", insertable=false, updatable=false)
	private AdmFacilitySystem admFacilitySystem;

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
	
	public AdmFacilitySystem getAdmFacilitySystem() {
		return this.admFacilitySystem;
	}

	public void setAdmFacilitySystem(AdmFacilitySystem admFacilitySystem) {
		this.admFacilitySystem = admFacilitySystem;
	}

	/**
	 * @return the facilitySystemPK
	 */
	public AdmUserFacilitySystemPK getFacilitySystemPK() {
		return facilitySystemPK;
	}

	/**
	 * @param facilitySystemPK the facilitySystemPK to set
	 */
	public void setFacilitySystemPK(AdmUserFacilitySystemPK facilitySystemPK) {
		this.facilitySystemPK = facilitySystemPK;
	}
	
}