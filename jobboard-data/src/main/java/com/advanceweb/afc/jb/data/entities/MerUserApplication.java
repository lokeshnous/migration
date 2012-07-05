package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * The persistent class for the mer_user_application database table.
 * 
 */
@Entity
@Table(name = "mer_user_application")
public class MerUserApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	@EmbeddedId
	private MerUserApplicationPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_visit_dt")
	private Date lastVisitDt;

	// bi-directional many-to-one association to MerApplication
	@ManyToOne
	@JoinColumn(name = "application_id", insertable = false, updatable = false)
	private MerApplication merApplication;

	// bi-directional many-to-one association to MerUser
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private MerUser merUser;

	public MerUserApplication() {
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public Date getDeleteDt() {
		return deleteDt;
	}

	public MerUserApplicationPK getId() {
		return id;
	}

	public Date getLastVisitDt() {
		return lastVisitDt;
	}

	public MerApplication getMerApplication() {
		return merApplication;
	}

	public MerUser getMerUser() {
		return merUser;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public void setId(MerUserApplicationPK id) {
		this.id = id;
	}

	public void setLastVisitDt(Date lastVisitDt) {
		this.lastVisitDt = lastVisitDt;
	}

	public void setMerApplication(MerApplication merApplication) {
		this.merApplication = merApplication;
	}

	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}

}