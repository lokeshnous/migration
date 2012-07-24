package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the res_upload_resume database table.
 * 
 */
@Entity
@Table(name="res_upload_resume")
public class ResUploadResume implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="upload_resume_id")
	private int uploadResumeId;

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

	@Column(name="emp_type_lookup_id")
	private int empTypeLookupId;

	@Column(name="employer_views")
	private int employerViews;

	@Column(name="file_name")
	private String fileName;

	@Column(name="file_path")
	private String filePath;

	@Column(name="file_server")
	private String fileServer;

	@Column(name="is_published")
	private short isPublished;

	@Column(name="job_title")
	private String jobTitle;

	private String relocate;

	@Column(name="resume_name")
	private String resumeName;

    @Lob()
	@Column(name="resume_text")
	private String resumeText;

	@Column(name="resume_type")
	private String resumeType;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	@Column(name="update_user_id")
	private int updateUserId;

	@Column(name="user_id")
	private int userId;

	@Column(name="visibility")
	private String visibility___Public_Private__;

	@Column(name="work_auth_lookup_id")
	private int workAuthLookupId;

	//bi-directional many-to-one association to ResPublishResume
    @ManyToOne
	@JoinColumn(name="publish_resume_id", insertable = false, updatable = false)
	private ResPublishResume resPublishResume;

    public ResUploadResume() {
    }

	public int getUploadResumeId() {
		return this.uploadResumeId;
	}

	public void setUploadResumeId(int uploadResumeId) {
		this.uploadResumeId = uploadResumeId;
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

	public int getEmpTypeLookupId() {
		return this.empTypeLookupId;
	}

	public void setEmpTypeLookupId(int empTypeLookupId) {
		this.empTypeLookupId = empTypeLookupId;
	}

	public int getEmployerViews() {
		return this.employerViews;
	}

	public void setEmployerViews(int employerViews) {
		this.employerViews = employerViews;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileServer() {
		return this.fileServer;
	}

	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}

	public short getIsPublished() {
		return this.isPublished;
	}

	public void setIsPublished(short isPublished) {
		this.isPublished = isPublished;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getRelocate() {
		return this.relocate;
	}

	public void setRelocate(String relocate) {
		this.relocate = relocate;
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

	public String getResumeType() {
		return this.resumeType;
	}

	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
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

	public String getVisibility___Public_Private__() {
		return this.visibility___Public_Private__;
	}

	public void setVisibility___Public_Private__(String visibility___Public_Private__) {
		this.visibility___Public_Private__ = visibility___Public_Private__;
	}

	public int getWorkAuthLookupId() {
		return this.workAuthLookupId;
	}

	public void setWorkAuthLookupId(int workAuthLookupId) {
		this.workAuthLookupId = workAuthLookupId;
	}

	public ResPublishResume getResPublishResume() {
		return this.resPublishResume;
	}

	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}
	
}