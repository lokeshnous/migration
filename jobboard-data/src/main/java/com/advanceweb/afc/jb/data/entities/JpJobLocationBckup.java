package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the jp_job_location_bckup database table.
 * 
 */
@Entity
@Table(name="jp_job_location_bckup")
public class JpJobLocationBckup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="job_id")
	private int jobId;

	@Column(name="location_id")
	private int locationId;

    public JpJobLocationBckup() {
    }

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

}