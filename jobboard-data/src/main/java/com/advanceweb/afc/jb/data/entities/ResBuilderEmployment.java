package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the res_builder_employment database table.
 * 
 */
@Entity
@Table(name="res_builder_employment")
public class ResBuilderEmployment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_employment_id")
	private int builderEmploymentId;

	@Column(name="annual_salary")
	private String annualSalary;

	@Column(name="career_level")
	private String careerLevel;

	private String city;

	private String country;

	@Column(name="employer_name")
	private String employerName;

    @Temporal( TemporalType.DATE)
	@Column(name="employment_dt")
	private Date employmentDt;

	@Column(name="employment_type")
	private String employmentType;

	@Column(name="employment_years")
	private int employmentYears;

	@Column(name="hourly_rate")
	private String hourlyRate;

	@Column(name="is_cur_career_level")
	private int isCurCareerLevel;

	@Column(name="job_description")
	private String jobDescription;

	@Column(name="position_name")
	private String positionName;

    @Temporal( TemporalType.DATE)
	@Column(name="separation_dt")
	private Date separationDt;

	private String state;

	@Column(name="still_employed")
	private int stillEmployed;

	//bi-directional many-to-one association to ResBuilderResume
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

    public ResBuilderEmployment() {
    }

	public int getBuilderEmploymentId() {
		return this.builderEmploymentId;
	}

	public void setBuilderEmploymentId(int builderEmploymentId) {
		this.builderEmploymentId = builderEmploymentId;
	}

	public String getAnnualSalary() {
		return this.annualSalary;
	}

	public void setAnnualSalary(String annualSalary) {
		this.annualSalary = annualSalary;
	}

	public String getCareerLevel() {
		return this.careerLevel;
	}

	public void setCareerLevel(String careerLevel) {
		this.careerLevel = careerLevel;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmployerName() {
		return this.employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public Date getEmploymentDt() {
		return this.employmentDt;
	}

	public void setEmploymentDt(Date employmentDt) {
		this.employmentDt = employmentDt;
	}

	public String getEmploymentType() {
		return this.employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public int getEmploymentYears() {
		return this.employmentYears;
	}

	public void setEmploymentYears(int employmentYears) {
		this.employmentYears = employmentYears;
	}

	public String getHourlyRate() {
		return this.hourlyRate;
	}

	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public int getIsCurCareerLevel() {
		return this.isCurCareerLevel;
	}

	public void setIsCurCareerLevel(int isCurCareerLevel) {
		this.isCurCareerLevel = isCurCareerLevel;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Date getSeparationDt() {
		return this.separationDt;
	}

	public void setSeparationDt(Date separationDt) {
		this.separationDt = separationDt;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getStillEmployed() {
		return this.stillEmployed;
	}

	public void setStillEmployed(int stillEmployed) {
		this.stillEmployed = stillEmployed;
	}

	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}