package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the res_publish_resume_priv database table.
 * 
 */
@Entity
@Table(name="res_publish_resume_priv")
public class ResPublishResumePriv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="publish_resume_priv_id")
	private int publishResumePrivId;

	private int active;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="create_user_id")
	private int createUserId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="delete_user_id")
	private int deleteUserId;

	//bi-directional many-to-one association to ResPublishResume
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publish_resume_id")
	private ResPublishResume resPublishResume;

	//bi-directional many-to-one association to ResPrivacy
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="privacy_id")
	private ResPrivacy resPrivacy;

	public int getPublishResumePrivId() {
		return this.publishResumePrivId;
	}

	public void setPublishResumePrivId(int publishResumePrivId) {
		this.publishResumePrivId = publishResumePrivId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
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

	public ResPublishResume getResPublishResume() {
		return this.resPublishResume;
	}

	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}
	
	public ResPrivacy getResPrivacy() {
		return this.resPrivacy;
	}

	public void setResPrivacy(ResPrivacy resPrivacy) {
		this.resPrivacy = resPrivacy;
	}
	
}