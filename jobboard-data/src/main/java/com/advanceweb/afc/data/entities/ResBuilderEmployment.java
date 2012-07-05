package com.advanceweb.afc.data.entities;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the res_builder_employment database table.
 * 
 */
@Entity
@Table(name = "res_builder_employment")
public class ResBuilderEmployment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "builder_employment_id", insertable = false, updatable = false)
	private int builderEmploymentId;

	private String city;

	private String country;

	@Column(name = "employer_name")
	private String employerName;

	@Temporal(TemporalType.DATE)
	@Column(name = "employment_dt")
	private Date employmentDt;

	@Column(name = "job_description")
	private String jobDescription;

	@Column(name = "position_name")
	private String positionName;

	// bi-directional many-to-one association to ResBuilderResume
	@ManyToOne
	@JoinColumn(name = "builder_resume_id")
	private ResBuilderResume resBuilderResume;

	@Temporal(TemporalType.DATE)
	@Column(name = "separation_dt")
	private Date separationDt;

	private String state;

	@Column(name = "still_employed")
	private short stillEmployed;

	public ResBuilderEmployment() {
	}

	public int getBuilderEmploymentId() {
		return builderEmploymentId;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getEmployerName() {
		return employerName;
	}

	public Date getEmploymentDt() {
		return employmentDt;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public String getPositionName() {
		return positionName;
	}

	public ResBuilderResume getResBuilderResume() {
		return resBuilderResume;
	}

	public Date getSeparationDt() {
		return separationDt;
	}

	public String getState() {
		return state;
	}

	public short getStillEmployed() {
		return stillEmployed;
	}

	public void setBuilderEmploymentId(int builderEmploymentId) {
		this.builderEmploymentId = builderEmploymentId;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public void setEmploymentDt(Date employmentDt) {
		this.employmentDt = employmentDt;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}

	public void setSeparationDt(Date separationDt) {
		this.separationDt = separationDt;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStillEmployed(short stillEmployed) {
		this.stillEmployed = stillEmployed;
	}

}