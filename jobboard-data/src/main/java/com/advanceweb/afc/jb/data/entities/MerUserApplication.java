package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the mer_user_application database table.
 * 
 */
@Entity
@Table(name="mer_user_application")
public class MerUserApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MerUserApplicationPK id;

	@Column(name="create_dt")
	private Timestamp createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="last_visit_dt")
	private Date lastVisitDt;

	//bi-directional many-to-one association to MerUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	private MerUser merUser;

	//bi-directional many-to-one association to MerApplication
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="application_id", insertable=false, updatable=false)
	private MerApplication merApplication;

    public MerUserApplication() {
    }

	public MerUserApplicationPK getId() {
		return this.id;
	}

	public void setId(MerUserApplicationPK id) {
		this.id = id;
	}
	
	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public Date getLastVisitDt() {
		return this.lastVisitDt;
	}

	public void setLastVisitDt(Date lastVisitDt) {
		this.lastVisitDt = lastVisitDt;
	}

	public MerUser getMerUser() {
		return this.merUser;
	}

	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}
	
	public MerApplication getMerApplication() {
		return this.merApplication;
	}

	public void setMerApplication(MerApplication merApplication) {
		this.merApplication = merApplication;
	}
	
}