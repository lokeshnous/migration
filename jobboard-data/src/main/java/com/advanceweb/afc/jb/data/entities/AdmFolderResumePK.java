package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the adm_folder_resume database table.
 * 
 */
@Embeddable
public class AdmFolderResumePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="folder_resume_id")
	private int folderResumeId;

	@Column(name="folder_id")
	private int folderId;

	@Column(name="publish_resume_id")
	private int publishResumeId;

	@Column(name="application_status_id")
	private int applicationStatusId;

    public AdmFolderResumePK() {
    }
	public int getFolderResumeId() {
		return this.folderResumeId;
	}
	public void setFolderResumeId(int folderResumeId) {
		this.folderResumeId = folderResumeId;
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
	public int getApplicationStatusId() {
		return this.applicationStatusId;
	}
	public void setApplicationStatusId(int applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmFolderResumePK)) {
			return false;
		}
		AdmFolderResumePK castOther = (AdmFolderResumePK)other;
		return 
			(this.folderResumeId == castOther.folderResumeId)
			&& (this.folderId == castOther.folderId)
			&& (this.publishResumeId == castOther.publishResumeId)
			&& (this.applicationStatusId == castOther.applicationStatusId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.folderResumeId;
		hash = hash * prime + this.folderId;
		hash = hash * prime + this.publishResumeId;
		hash = hash * prime + this.applicationStatusId;
		
		return hash;
    }
}