package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the adm_facility_system database table.
 * 
 */
@Entity
@Table(name="adm_facility_system")
public class AdmFacilitySystem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="facility_system_id")
	private int facilitySystemId;

	@Column(name="admin_user_id")
	private int adminUserId;

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

	private String name;

	//bi-directional many-to-one association to AdmFacilityGroup
	@OneToMany(mappedBy="admFacilitySystem")
	private List<AdmFacilityGroup> admFacilityGroups;

	//bi-directional many-to-one association to AdmUserFacilitySystem
	@OneToMany(mappedBy="admFacilitySystem")
	private List<AdmUserFacilitySystem> admUserFacilitySystems;

	public int getFacilitySystemId() {
		return this.facilitySystemId;
	}

	public void setFacilitySystemId(int facilitySystemId) {
		this.facilitySystemId = facilitySystemId;
	}

	public int getAdminUserId() {
		return this.adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AdmFacilityGroup> getAdmFacilityGroups() {
		return this.admFacilityGroups;
	}

	public void setAdmFacilityGroups(List<AdmFacilityGroup> admFacilityGroups) {
		this.admFacilityGroups = admFacilityGroups;
	}
	
	public List<AdmUserFacilitySystem> getAdmUserFacilitySystems() {
		return this.admUserFacilitySystems;
	}

	public void setAdmUserFacilitySystems(List<AdmUserFacilitySystem> admUserFacilitySystems) {
		this.admUserFacilitySystems = admUserFacilitySystems;
	}
	
}