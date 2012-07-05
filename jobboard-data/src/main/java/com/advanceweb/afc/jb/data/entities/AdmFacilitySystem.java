package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_facility_system database table.
 * 
 */
@Entity
@Table(name = "adm_facility_system")
public class AdmFacilitySystem implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to AdmFacilityGroup
	@OneToMany(mappedBy = "admFacilitySystem")
	private List<AdmFacilityGroup> admFacilityGroups;

	@Column(name = "admin_user_id")
	private int adminUserId;

	// bi-directional many-to-one association to AdmUserFacilitySystem
	@OneToMany(mappedBy = "admFacilitySystem")
	private List<AdmUserFacilitySystem> admUserFacilitySystems;

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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "facility_system_id", insertable = false, updatable = false)
	private int facilitySystemId;

	private String name;

	public AdmFacilitySystem() {
	}

	public List<AdmFacilityGroup> getAdmFacilityGroups() {
		return admFacilityGroups;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public List<AdmUserFacilitySystem> getAdmUserFacilitySystems() {
		return admUserFacilitySystems;
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

	public int getFacilitySystemId() {
		return facilitySystemId;
	}

	public String getName() {
		return name;
	}

	public void setAdmFacilityGroups(List<AdmFacilityGroup> admFacilityGroups) {
		this.admFacilityGroups = admFacilityGroups;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public void setAdmUserFacilitySystems(
			List<AdmUserFacilitySystem> admUserFacilitySystems) {
		this.admUserFacilitySystems = admUserFacilitySystems;
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

	public void setFacilitySystemId(int facilitySystemId) {
		this.facilitySystemId = facilitySystemId;
	}

	public void setName(String name) {
		this.name = name;
	}

}