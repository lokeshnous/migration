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

	@Column(name="annual_sal_lookup_id")
	private int annualSalLookupId;

	private String city;

	private String country;

	@Column(name="curr_level_lookup_id")
	private int currLevelLookupId;

	@Column(name="emp_type_lookup_id")
	private int empTypeLookupId;

	@Column(name="employer_name")
	private String employerName;

    @Temporal( TemporalType.DATE)
	@Column(name="employment_dt")
	private Date employmentDt;

	@Column(name="hr_pay_rate_lookup_id")
	private int hrPayRateLookupId;

	@Column(name="job_description")
	private String jobDescription;

	@Column(name="job_title")
	private String jobTitle;

	@Column(name="position_name")
	private String positionName;

    @Temporal( TemporalType.DATE)
	@Column(name="separation_dt")
	private Date separationDt;

	private String state;

	@Column(name="still_employed")
	private short stillEmployed;

	@Column(name="year_at_position")
	private int yearAtPosition;

	//bi-directional many-to-one association to ResBuilderResume
    @ManyToOne
	@JoinColumn(name="builder_resume_id", insertable = false, updatable = false)
	private ResBuilderResume resBuilderResume;

    public ResBuilderEmployment() {
    }

	public int getBuilderEmploymentId() {
		return this.builderEmploymentId;
	}

	public void setBuilderEmploymentId(int builderEmploymentId) {
		this.builderEmploymentId = builderEmploymentId;
	}

	public int getAnnualSalLookupId() {
		return this.annualSalLookupId;
	}

	public void setAnnualSalLookupId(int annualSalLookupId) {
		this.annualSalLookupId = annualSalLookupId;
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

	public int getCurrLevelLookupId() {
		return this.currLevelLookupId;
	}

	public void setCurrLevelLookupId(int currLevelLookupId) {
		this.currLevelLookupId = currLevelLookupId;
	}

	public int getEmpTypeLookupId() {
		return this.empTypeLookupId;
	}

	public void setEmpTypeLookupId(int empTypeLookupId) {
		this.empTypeLookupId = empTypeLookupId;
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

	public int getHrPayRateLookupId() {
		return this.hrPayRateLookupId;
	}

	public void setHrPayRateLookupId(int hrPayRateLookupId) {
		this.hrPayRateLookupId = hrPayRateLookupId;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

	public short getStillEmployed() {
		return this.stillEmployed;
	}

	public void setStillEmployed(short stillEmployed) {
		this.stillEmployed = stillEmployed;
	}

	public int getYearAtPosition() {
		return this.yearAtPosition;
	}

	public void setYearAtPosition(int yearAtPosition) {
		this.yearAtPosition = yearAtPosition;
	}

	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}