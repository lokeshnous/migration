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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="save_job_id")
	private int saveJobId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="facility_name")
	private String facilityName;

	private String jobtitle;

	@Column(name="save_type")
	private String saveType;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to JpJob
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id")
	private JpJob jpJob;

    public AdmSaveJob() {
    }

	public int getSaveJobId() {
		return this.saveJobId;
	}

	public void setSaveJobId(int saveJobId) {
		this.saveJobId = saveJobId;
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

	public String getSaveType() {
		return this.saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public JpJob getJpJob() {
		return this.jpJob;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}
	
}