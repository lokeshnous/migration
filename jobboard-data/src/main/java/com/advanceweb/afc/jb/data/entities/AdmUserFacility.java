package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the adm_user_facility database table.
 * 
 */
@Entity
@Table(name="adm_user_facility")
public class AdmUserFacility implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdmUserFacilityPK id;

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

	//bi-directional many-to-one association to AdmFacility
    @ManyToOne
	@JoinColumn(name="facility_id", insertable = false, updatable = false)
	private AdmFacility admFacility;

    public AdmUserFacility() {
    }

	public AdmUserFacilityPK getId() {
		return this.id;
	}

	public void setId(AdmUserFacilityPK id) {
		this.id = id;
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

	public AdmRole getAdmRole() {
		return this.admRole;
	}

	public void setAdmRole(AdmRole admRole) {
		this.admRole = admRole;
	}
	
	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
	
}