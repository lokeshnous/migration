package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the jp_job_location database table.
 * 
 */
@Entity
@Table(name = "jp_job_location")
public class JpJobLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@EmbeddedId
	private JpJobLocationPK id;

	// bi-directional many-to-one association to JpJob
	@ManyToOne
	@JoinColumn(name = "job_id", insertable = false, updatable = false)
	private JpJob jpJob;

	// bi-directional many-to-one association to JpLocation
	@ManyToOne
	@JoinColumn(name = "location_id", insertable = false, updatable = false)
	private JpLocation jpLocation;

	public JpJobLocation() {
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public JpJobLocationPK getId() {
		return id;
	}

	public JpJob getJpJob() {
		return jpJob;
	}

	public JpLocation getJpLocation() {
		return jpLocation;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setId(JpJobLocationPK id) {
		this.id = id;
	}

	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}

	public void setJpLocation(JpLocation jpLocation) {
		this.jpLocation = jpLocation;
	}

}