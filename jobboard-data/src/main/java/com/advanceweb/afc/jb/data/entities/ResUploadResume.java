package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the res_upload_resume database table.
 * 
 */
@Entity
@Table(name = "res_upload_resume")
public class ResUploadResume implements Serializable {
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

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "file_server")
	private String fileServer;

	@Column(name = "is_published")
	private short isPublished;

	// bi-directional many-to-one association to ResPublishResume
	@ManyToOne
	@JoinColumn(name = "publish_resume_id")
	private ResPublishResume resPublishResume;

	@Column(name = "resume_name")
	private String resumeName;

	@Lob()
	@Column(name = "resume_text")
	private String resumeText;

	@Column(name = "resume_type")
	private String resumeType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_dt")
	private Date updateDt;

	@Column(name = "update_user_id")
	private int updateUserId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "upload_resume_id", insertable = false, updatable = false)
	private int uploadResumeId;

	@Column(name = "user_id")
	private int userId;

	public ResUploadResume() {
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

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileServer() {
		return fileServer;
	}

	public short getIsPublished() {
		return isPublished;
	}

	public ResPublishResume getResPublishResume() {
		return resPublishResume;
	}

	public String getResumeName() {
		return resumeName;
	}

	public String getResumeText() {
		return resumeText;
	}

	public String getResumeType() {
		return resumeType;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public int getUploadResumeId() {
		return uploadResumeId;
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

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}

	public void setIsPublished(short isPublished) {
		this.isPublished = isPublished;
	}

	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public void setUploadResumeId(int uploadResumeId) {
		this.uploadResumeId = uploadResumeId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}