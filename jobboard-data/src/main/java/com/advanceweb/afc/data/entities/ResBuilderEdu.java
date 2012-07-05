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
 * The persistent class for the res_builder_edu database table.
 * 
 */
@Entity
@Table(name = "res_builder_edu")
public class ResBuilderEdu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "builder_edu_id", insertable = false, updatable = false)
	private int builderEduId;

	private String city;

	@Temporal(TemporalType.DATE)
	@Column(name = "completion_dt")
	private Date completionDt;

	private String country;

	@Column(name = "course_of_study")
	private String courseOfStudy;

	@Column(name = "institution_name")
	private String institutionName;

	// bi-directional many-to-one association to ResBuilderResume
	@ManyToOne
	@JoinColumn(name = "builder_resume_id")
	private ResBuilderResume resBuilderResume;

	// bi-directional many-to-one association to ResDegreeEdu
	@ManyToOne
	@JoinColumn(name = "degree_edu_id")
	private ResDegreeEdu resDegreeEdu;

	private String state;

	public ResBuilderEdu() {
	}

	public int getBuilderEduId() {
		return builderEduId;
	}

	public String getCity() {
		return city;
	}

	public Date getCompletionDt() {
		return completionDt;
	}

	public String getCountry() {
		return country;
	}

	public String getCourseOfStudy() {
		return courseOfStudy;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public ResBuilderResume getResBuilderResume() {
		return resBuilderResume;
	}

	public ResDegreeEdu getResDegreeEdu() {
		return resDegreeEdu;
	}

	public String getState() {
		return state;
	}

	public void setBuilderEduId(int builderEduId) {
		this.builderEduId = builderEduId;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCompletionDt(Date completionDt) {
		this.completionDt = completionDt;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCourseOfStudy(String courseOfStudy) {
		this.courseOfStudy = courseOfStudy;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}

	public void setResDegreeEdu(ResDegreeEdu resDegreeEdu) {
		this.resDegreeEdu = resDegreeEdu;
	}

	public void setState(String state) {
		this.state = state;
	}

}