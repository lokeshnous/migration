package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * <code>JpSaveJob</code>The persistent class for the jp_save_job database table.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 13 July 2012
 * 
 */

@Entity
@Table(name="jp_save_job")
public class JpSaveJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="save_job_id")
	private int saveJobId;

	@Column(name="user_id")
	private int userId;

	@Column(name="job_id")
	private int jobId;

	@Column(name="created_date")
	private Date createDt;

	@Column(name="applied_date")
	private Date appliedDate;
	
	@Column(name="is_applied")
	private byte isApplied;

	//bi-directional many-to-one association to JpJob
    @ManyToOne
	@JoinColumn(name="job_id", insertable = false, updatable = false)
	private JpJob jpJob;

    public JpSaveJob() {
    }

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getSaveJobId() {
		return saveJobId;
	}

	public void setSaveJobId(int saveJobId) {
		this.saveJobId = saveJobId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public byte getIsApplied() {
		return isApplied;
	}

	public void setIsApplied(byte isApplied) {
		this.isApplied = isApplied;
	}

	public JpJob getJpJob() {
		return jpJob;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}

}