package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the res_publish_resume database table.
 * 
 */
@Entity
@Table(name = "res_publish_resume")
public class ResPublishResume implements Serializable {
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
	@Column(name = "publish_resume_id", insertable = false, updatable = false)
	private int publishResumeId;

	// bi-directional many-to-one association to ResBuilderResume
	@OneToMany(mappedBy = "resPublishResume")
	private List<ResBuilderResume> resBuilderResumes;

	// bi-directional many-to-one association to ResPublishResumePriv
	@OneToMany(mappedBy = "resPublishResume")
	private List<ResPublishResumePriv> resPublishResumePrivs;

	@Column(name = "resume_name")
	private String resumeName;

	@Lob()
	@Column(name = "resume_text")
	private String resumeText;

	// bi-directional many-to-one association to ResUploadResume
	@OneToMany(mappedBy = "resPublishResume")
	private List<ResUploadResume> resUploadResumes;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_dt")
	private Date updateDt;

	@Column(name = "update_user_id")
	private int updateUserId;

	@Column(name = "user_id")
	private int userId;

	public ResPublishResume() {
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

	public int getPublishResumeId() {
		return publishResumeId;
	}

	public List<ResBuilderResume> getResBuilderResumes() {
		return resBuilderResumes;
	}

	public List<ResPublishResumePriv> getResPublishResumePrivs() {
		return resPublishResumePrivs;
	}

	public String getResumeName() {
		return resumeName;
	}

	public String getResumeText() {
		return resumeText;
	}

	public List<ResUploadResume> getResUploadResumes() {
		return resUploadResumes;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public int getUserId() {
		return userId;
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

	public void setPublishResumeId(int publishResumeId) {
		this.publishResumeId = publishResumeId;
	}

	public void setResBuilderResumes(List<ResBuilderResume> resBuilderResumes) {
		this.resBuilderResumes = resBuilderResumes;
	}

	public void setResPublishResumePrivs(
			List<ResPublishResumePriv> resPublishResumePrivs) {
		this.resPublishResumePrivs = resPublishResumePrivs;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public void setResUploadResumes(List<ResUploadResume> resUploadResumes) {
		this.resUploadResumes = resUploadResumes;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}