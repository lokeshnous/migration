package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the adm_folder_resume database table.
 * 
 */
@Entity
@Table(name="adm_folder_resume")
public class AdmFolderResume implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="folder_resume_id")
	private int folderResumeId;

	@Column(name="application_status_id")
	private int applicationStatusId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="folder_id")
	private int folderId;

	@Column(name="publish_resume_id")
	private int publishResumeId;

	private int rating;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

    public AdmFolderResume() {
    }

	public int getFolderResumeId() {
		return this.folderResumeId;
	}

	public void setFolderResumeId(int folderResumeId) {
		this.folderResumeId = folderResumeId;
	}

	public int getApplicationStatusId() {
		return this.applicationStatusId;
	}

	public void setApplicationStatusId(int applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public int getFolderId() {
		return this.folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	public int getPublishResumeId() {
		return this.publishResumeId;
	}

	public void setPublishResumeId(int publishResumeId) {
		this.publishResumeId = publishResumeId;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

}