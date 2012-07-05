package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the jp_job_type database table.
 * 
 */
@Entity
@Table(name = "jp_job_type")
public class JpJobType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "credit_amt")
	private int creditAmt;

	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "job_type_id", insertable = false, updatable = false)
	private int jobTypeId;

	// bi-directional many-to-one association to JpJob
	@OneToMany(mappedBy = "jpJobType")
	private List<JpJob> jpJobs;

	private String name;

	public JpJobType() {
	}

	public int getCreditAmt() {
		return creditAmt;
	}

	public String getDescription() {
		return description;
	}

	public int getJobTypeId() {
		return jobTypeId;
	}

	public List<JpJob> getJpJobs() {
		return jpJobs;
	}

	public String getName() {
		return name;
	}

	public void setCreditAmt(int creditAmt) {
		this.creditAmt = creditAmt;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setJobTypeId(int jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public void setJpJobs(List<JpJob> jpJobs) {
		this.jpJobs = jpJobs;
	}

	public void setName(String name) {
		this.name = name;
	}

}