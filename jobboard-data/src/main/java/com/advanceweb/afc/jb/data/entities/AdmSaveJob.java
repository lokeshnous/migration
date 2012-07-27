package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the adm_save_job database table.
 * 
 */
@Entity
@Table(name="adm_save_job")
public class AdmSaveJob implements Serializable {
	private static final long serialVersionUID = 1L;
	private int saveJobId;
	private Date appliedDt;
	private Date createDt;
	private Date deleteDt;
	private String facilityName;
	private String jobtitle;
	private int userId;
	private JpJob jpJob;
	
    public AdmSaveJob() {
    }


	@Id
	@Column(name="save_job_id")
	public int getSaveJobId() {
		return this.saveJobId;
	}

	public void setSaveJobId(int saveJobId) {
		this.saveJobId = saveJobId;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="applied_dt")
	public Date getAppliedDt() {
		return this.appliedDt;
	}

	public void setAppliedDt(Date appliedDt) {
		this.appliedDt = appliedDt;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}


	@Column(name="facility_name")
	public String getFacilityName() {
		return this.facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}


	public String getJobtitle() {
		return this.jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}


	@Column(name="user_id")
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	//bi-directional many-to-one association to JpJob
    @ManyToOne
	@JoinColumn(name="job_id")
	public JpJob getJpJob() {
		return this.jpJob;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}
	
}