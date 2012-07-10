package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the res_publish_resume database table.
 * 
 */
@Entity
@Table(name="res_publish_resume")
public class ResPublishResume implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="publish_resume_id")
	private int publishResumeId;

	private short active;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="create_user_id")
	private int createUserId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="delete_user_id")
	private int deleteUserId;

	@Column(name="resume_name")
	private String resumeName;

    @Lob()
	@Column(name="resume_text")
	private String resumeText;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	@Column(name="update_user_id")
	private int updateUserId;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to ResBuilderResume
	@OneToMany(mappedBy="resPublishResume")
	private List<ResBuilderResume> resBuilderResumes;

	//bi-directional many-to-one association to ResPublishResumePriv
	@OneToMany(mappedBy="resPublishResume")
	private List<ResPublishResumePriv> resPublishResumePrivs;

	//bi-directional many-to-one association to ResUploadResume
	@OneToMany(mappedBy="resPublishResume")
	private List<ResUploadResume> resUploadResumes;

    public ResPublishResume() {
    }

	public int getPublishResumeId() {
		return this.publishResumeId;
	}

	public void setPublishResumeId(int publishResumeId) {
		this.publishResumeId = publishResumeId;
	}

	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
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

	public String getResumeName() {
		return this.resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public String getResumeText() {
		return this.resumeText;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public int getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<ResBuilderResume> getResBuilderResumes() {
		return this.resBuilderResumes;
	}

	public void setResBuilderResumes(List<ResBuilderResume> resBuilderResumes) {
		this.resBuilderResumes = resBuilderResumes;
	}
	
	public List<ResPublishResumePriv> getResPublishResumePrivs() {
		return this.resPublishResumePrivs;
	}

	public void setResPublishResumePrivs(List<ResPublishResumePriv> resPublishResumePrivs) {
		this.resPublishResumePrivs = resPublishResumePrivs;
	}
	
	public List<ResUploadResume> getResUploadResumes() {
		return this.resUploadResumes;
	}

	public void setResUploadResumes(List<ResUploadResume> resUploadResumes) {
		this.resUploadResumes = resUploadResumes;
	}
	
}