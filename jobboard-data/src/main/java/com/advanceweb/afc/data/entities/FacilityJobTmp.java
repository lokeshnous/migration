package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the facility_job_tmp database table.
 * 
 */
@Entity
@Table(name="facility_job_tmp")
public class FacilityJobTmp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="account_number")
	private String accountNumber;

	@Column(name="facility_id")
	private int facilityId;

	@Column(name="job_id")
	private int jobId;

	private String name;

    public FacilityJobTmp() {
    }

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getFacilityId() {
		return this.facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}