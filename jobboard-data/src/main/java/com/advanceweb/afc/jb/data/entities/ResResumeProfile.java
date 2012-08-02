package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the res_resume_profile database table.
 * 
 */
@Entity
@Table(name="res_resume_profile")
public class ResResumeProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="resume_profile_id")
	private int resumeProfileId;

	@Column(name="attrib_value")
	private String attribValue;

	@Column(name="create_dt")
	private Timestamp createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="resume_id")
	private int resumeId;

	@Column(name="resume_type")
	private String resumeType;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	//bi-directional many-to-one association to ResResumeAttrib
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="resume_attrib_id")
	private ResResumeAttrib resResumeAttrib;

    public ResResumeProfile() {
    }

	public int getResumeProfileId() {
		return this.resumeProfileId;
	}

	public void setResumeProfileId(int resumeProfileId) {
		this.resumeProfileId = resumeProfileId;
	}

	public String getAttribValue() {
		return this.attribValue;
	}

	public void setAttribValue(String attribValue) {
		this.attribValue = attribValue;
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

	public int getResumeId() {
		return this.resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
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

	public ResResumeAttrib getResResumeAttrib() {
		return this.resResumeAttrib;
	}

	public void setResResumeAttrib(ResResumeAttrib resResumeAttrib) {
		this.resResumeAttrib = resResumeAttrib;
	}
	
}