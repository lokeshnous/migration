package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the adm_facility_group database table.
 * 
 */
@Entity
@Table(name="adm_facility_group")
public class AdmFacilityGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="facility_group_id")
	private int facilityGroupId;

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

	//bi-directional many-to-one association to AdmFacilitySystem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_system_id")
	private AdmFacilitySystem admFacilitySystem;

	//bi-directional many-to-one association to AdmUserFacilityGroup
	@OneToMany(mappedBy="admFacilityGroup")
	private List<AdmUserFacilityGroup> admUserFacilityGroups;

	public int getFacilityGroupId() {
		return this.facilityGroupId;
	}

	public void setFacilityGroupId(int facilityGroupId) {
		this.facilityGroupId = facilityGroupId;
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

	public AdmFacilitySystem getAdmFacilitySystem() {
		return this.admFacilitySystem;
	}

	public void setAdmFacilitySystem(AdmFacilitySystem admFacilitySystem) {
		this.admFacilitySystem = admFacilitySystem;
	}
	
	public List<AdmUserFacilityGroup> getAdmUserFacilityGroups() {
		return this.admUserFacilityGroups;
	}

	public void setAdmUserFacilityGroups(List<AdmUserFacilityGroup> admUserFacilityGroups) {
		this.admUserFacilityGroups = admUserFacilityGroups;
	}
	
}