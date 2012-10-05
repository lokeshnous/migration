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
 * The persistent class for the res_builder_edu database table.
 * 
 */
@Entity
@Table(name="res_builder_edu")
public class ResBuilderEdu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_edu_id")
	private int builderEduId;

	private String certifications;

	private String city;

    @Temporal( TemporalType.DATE)
	@Column(name="completion_dt")
	private Date completionDt;

	private String country;

	@Column(name="course_of_study")
	private String courseOfStudy;

	@Column(name="degree_level")
	private String degreeLevel;

	private String degrees;

	@Column(name="institution_name")
	private String institutionName;

	@Column(name="is_graduated")
	private int isGraduated;

    @Temporal( TemporalType.DATE)
	@Column(name="start_dt")
	private Date startDt;

	private String state;

	//bi-directional many-to-one association to ResBuilderResume
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	//bi-directional many-to-one association to ResDegreeEdu
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="degree_edu_id")
	private ResDegreeEdu resDegreeEdu;

	public int getBuilderEduId() {
		return this.builderEduId;
	}

	public void setBuilderEduId(int builderEduId) {
		this.builderEduId = builderEduId;
	}

	public String getCertifications() {
		return this.certifications;
	}

	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCompletionDt() {
		return this.completionDt;
	}

	public void setCompletionDt(Date completionDt) {
		this.completionDt = completionDt;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCourseOfStudy() {
		return this.courseOfStudy;
	}

	public void setCourseOfStudy(String courseOfStudy) {
		this.courseOfStudy = courseOfStudy;
	}

	public String getDegreeLevel() {
		return this.degreeLevel;
	}

	public void setDegreeLevel(String degreeLevel) {
		this.degreeLevel = degreeLevel;
	}

	public String getDegrees() {
		return this.degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public int getIsGraduated() {
		return this.isGraduated;
	}

	public void setIsGraduated(int isGraduated) {
		this.isGraduated = isGraduated;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
	public ResDegreeEdu getResDegreeEdu() {
		return this.resDegreeEdu;
	}

	public void setResDegreeEdu(ResDegreeEdu resDegreeEdu) {
		this.resDegreeEdu = resDegreeEdu;
	}
	
}