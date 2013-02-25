/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="res_builder_employment")
public class ResBuilderEmployment implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The builder employment id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_employment_id")
	private int builderEmploymentId;

	/** The annual salary. */
	@Column(name="annual_salary")
	private String annualSalary;

	/** The career level. */
	@Column(name="career_level")
	private String careerLevel;

	/** The city. */
	private String city;

	/** The country. */
	private String country;

	/** The employer name. */
	@Column(name="employer_name")
	private String employerName;

    /** The employment dt. */
    @Temporal( TemporalType.DATE)
	@Column(name="employment_dt")
	private Date employmentDt;

	/** The employment type. */
	@Column(name="employment_type")
	private String employmentType;

	/** The employment years. */
	@Column(name="employment_years")
	private int employmentYears;

	/** The hourly rate. */
	@Column(name="hourly_rate")
	private String hourlyRate;

	/** The is cur career level. */
	@Column(name="is_cur_career_level")
	private int isCurCareerLevel;

	/** The job description. */
	@Column(name="job_description")
	private String jobDescription;

	/** The position name. */
	@Column(name="position_name")
	private String positionName;

    /** The separation dt. */
    @Temporal( TemporalType.DATE)
	@Column(name="separation_dt")
	private Date separationDt;

	/** The state. */
	private String state;

	/** The still employed. */
	@Column(name="still_employed")
	private int stillEmployed;

	//bi-directional many-to-one association to ResBuilderResume
	/** The res builder resume. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	/**
	 * Gets the builder employment id.
	 *
	 * @return the builder employment id
	 */
	public int getBuilderEmploymentId() {
		return this.builderEmploymentId;
	}

	/**
	 * Sets the builder employment id.
	 *
	 * @param builderEmploymentId the new builder employment id
	 */
	public void setBuilderEmploymentId(int builderEmploymentId) {
		this.builderEmploymentId = builderEmploymentId;
	}

	/**
	 * Gets the annual salary.
	 *
	 * @return the annual salary
	 */
	public String getAnnualSalary() {
		return this.annualSalary;
	}

	/**
	 * Sets the annual salary.
	 *
	 * @param annualSalary the new annual salary
	 */
	public void setAnnualSalary(String annualSalary) {
		this.annualSalary = annualSalary;
	}

	/**
	 * Gets the career level.
	 *
	 * @return the career level
	 */
	public String getCareerLevel() {
		return this.careerLevel;
	}

	/**
	 * Sets the career level.
	 *
	 * @param careerLevel the new career level
	 */
	public void setCareerLevel(String careerLevel) {
		this.careerLevel = careerLevel;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the employer name.
	 *
	 * @return the employer name
	 */
	public String getEmployerName() {
		return this.employerName;
	}

	/**
	 * Sets the employer name.
	 *
	 * @param employerName the new employer name
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	/**
	 * Gets the employment dt.
	 *
	 * @return the employment dt
	 */
	public Date getEmploymentDt() {
		return this.employmentDt;
	}

	/**
	 * Sets the employment dt.
	 *
	 * @param employmentDt the new employment dt
	 */
	public void setEmploymentDt(Date employmentDt) {
		this.employmentDt = employmentDt;
	}

	/**
	 * Gets the employment type.
	 *
	 * @return the employment type
	 */
	public String getEmploymentType() {
		return this.employmentType;
	}

	/**
	 * Sets the employment type.
	 *
	 * @param employmentType the new employment type
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	/**
	 * Gets the employment years.
	 *
	 * @return the employment years
	 */
	public int getEmploymentYears() {
		return this.employmentYears;
	}

	/**
	 * Sets the employment years.
	 *
	 * @param employmentYears the new employment years
	 */
	public void setEmploymentYears(int employmentYears) {
		this.employmentYears = employmentYears;
	}

	/**
	 * Gets the hourly rate.
	 *
	 * @return the hourly rate
	 */
	public String getHourlyRate() {
		return this.hourlyRate;
	}

	/**
	 * Sets the hourly rate.
	 *
	 * @param hourlyRate the new hourly rate
	 */
	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	/**
	 * Gets the checks if is cur career level.
	 *
	 * @return the checks if is cur career level
	 */
	public int getIsCurCareerLevel() {
		return this.isCurCareerLevel;
	}

	/**
	 * Sets the checks if is cur career level.
	 *
	 * @param isCurCareerLevel the new checks if is cur career level
	 */
	public void setIsCurCareerLevel(int isCurCareerLevel) {
		this.isCurCareerLevel = isCurCareerLevel;
	}

	/**
	 * Gets the job description.
	 *
	 * @return the job description
	 */
	public String getJobDescription() {
		return this.jobDescription;
	}

	/**
	 * Sets the job description.
	 *
	 * @param jobDescription the new job description
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	/**
	 * Gets the position name.
	 *
	 * @return the position name
	 */
	public String getPositionName() {
		return this.positionName;
	}

	/**
	 * Sets the position name.
	 *
	 * @param positionName the new position name
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * Gets the separation dt.
	 *
	 * @return the separation dt
	 */
	public Date getSeparationDt() {
		return this.separationDt;
	}

	/**
	 * Sets the separation dt.
	 *
	 * @param separationDt the new separation dt
	 */
	public void setSeparationDt(Date separationDt) {
		this.separationDt = separationDt;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the still employed.
	 *
	 * @return the still employed
	 */
	public int getStillEmployed() {
		return this.stillEmployed;
	}

	/**
	 * Sets the still employed.
	 *
	 * @param stillEmployed the new still employed
	 */
	public void setStillEmployed(int stillEmployed) {
		this.stillEmployed = stillEmployed;
	}

	/**
	 * Gets the res builder resume.
	 *
	 * @return the res builder resume
	 */
	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	/**
	 * Sets the res builder resume.
	 *
	 * @param resBuilderResume the new res builder resume
	 */
	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}