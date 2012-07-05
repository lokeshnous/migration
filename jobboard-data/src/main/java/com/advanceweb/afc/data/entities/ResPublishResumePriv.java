package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the res_publish_resume_priv database table.
 * 
 */
@Entity
@Table(name = "res_publish_resume_priv")
public class ResPublishResumePriv implements Serializable {
	private static final long serialVersionUID = 1L;

	private short active;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Column(name = "create_user_id")
	private int createUserId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	@Column(name = "delete_user_id")
	private int deleteUserId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "publish_resume_priv_id", insertable = false, updatable = false)
	private int publishResumePrivId;

	// bi-directional many-to-one association to ResPrivacy
	@ManyToOne
	@JoinColumn(name = "privacy_id")
	private ResPrivacy resPrivacy;

	// bi-directional many-to-one association to ResPublishResume
	@ManyToOne
	@JoinColumn(name = "publish_resume_id")
	private ResPublishResume resPublishResume;

	public ResPublishResumePriv() {
	}

	public short getActive() {
		return active;
	}

	public Timestamp getCreateDt() {
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

	public int getPublishResumePrivId() {
		return publishResumePrivId;
	}

	public ResPrivacy getResPrivacy() {
		return resPrivacy;
	}

	public ResPublishResume getResPublishResume() {
		return resPublishResume;
	}

	public void setActive(short active) {
		this.active = active;
	}

	public void setCreateDt(Timestamp createDt) {
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

	public void setPublishResumePrivId(int publishResumePrivId) {
		this.publishResumePrivId = publishResumePrivId;
	}

	public void setResPrivacy(ResPrivacy resPrivacy) {
		this.resPrivacy = resPrivacy;
	}

	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}

}