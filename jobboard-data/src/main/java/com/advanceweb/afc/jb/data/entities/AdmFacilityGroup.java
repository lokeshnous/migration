package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_facility_group database table.
 * 
 */
@Entity
@Table(name = "adm_facility_group")
public class AdmFacilityGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to AdmFacility
	@OneToMany(mappedBy = "admFacilityGroup")
	private List<AdmFacility> admFacilities;

	// bi-directional many-to-one association to AdmFacilitySystem
	@ManyToOne
	@JoinColumn(name = "facility_system_id")
	private AdmFacilitySystem admFacilitySystem;

	@Column(name = "admin_user_id")
	private int adminUserId;

	// bi-directional many-to-one association to AdmUserFacilityGroup
	@OneToMany(mappedBy = "admFacilityGroup")
	private List<AdmUserFacilityGroup> admUserFacilityGroups;

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
	@Column(name = "facility_group_id", insertable = false, updatable = false)
	private int facilityGroupId;

	private String name;

	public AdmFacilityGroup() {
	}

	public List<AdmFacility> getAdmFacilities() {
		return admFacilities;
	}

	public AdmFacilitySystem getAdmFacilitySystem() {
		return admFacilitySystem;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public List<AdmUserFacilityGroup> getAdmUserFacilityGroups() {
		return admUserFacilityGroups;
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

	public int getFacilityGroupId() {
		return facilityGroupId;
	}

	public String getName() {
		return name;
	}

	public void setAdmFacilities(List<AdmFacility> admFacilities) {
		this.admFacilities = admFacilities;
	}

	public void setAdmFacilitySystem(AdmFacilitySystem admFacilitySystem) {
		this.admFacilitySystem = admFacilitySystem;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public void setAdmUserFacilityGroups(
			List<AdmUserFacilityGroup> admUserFacilityGroups) {
		this.admUserFacilityGroups = admUserFacilityGroups;
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

	public void setFacilityGroupId(int facilityGroupId) {
		this.facilityGroupId = facilityGroupId;
	}

	public void setName(String name) {
		this.name = name;
	}

}