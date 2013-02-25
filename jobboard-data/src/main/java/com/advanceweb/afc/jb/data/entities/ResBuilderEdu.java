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
 * The persistent class for the res_builder_edu database table.
 * 
 */
@Entity
@Table(name="res_builder_edu")
public class ResBuilderEdu implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The builder edu id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_edu_id")
	private int builderEduId;

	/** The certifications. */
	private String certifications;

	/** The city. */
	private String city;

    /** The completion dt. */
    @Temporal( TemporalType.DATE)
	@Column(name="completion_dt")
	private Date completionDt;

	/** The country. */
	private String country;

	/** The course of study. */
	@Column(name="course_of_study")
	private String courseOfStudy;

	/** The degree level. */
	@Column(name="degree_level")
	private String degreeLevel;

	/** The degrees. */
	private String degrees;

	/** The institution name. */
	@Column(name="institution_name")
	private String institutionName;

	/** The is graduated. */
	@Column(name="is_graduated")
	private int isGraduated;

    /** The start dt. */
    @Temporal( TemporalType.DATE)
	@Column(name="start_dt")
	private Date startDt;

	/** The state. */
	private String state;

	//bi-directional many-to-one association to ResBuilderResume
	/** The res builder resume. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	//bi-directional many-to-one association to ResDegreeEdu
	/** The res degree edu. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="degree_edu_id")
	private ResDegreeEdu resDegreeEdu;

	/**
	 * Gets the builder edu id.
	 *
	 * @return the builder edu id
	 */
	public int getBuilderEduId() {
		return this.builderEduId;
	}

	/**
	 * Sets the builder edu id.
	 *
	 * @param builderEduId the new builder edu id
	 */
	public void setBuilderEduId(int builderEduId) {
		this.builderEduId = builderEduId;
	}

	/**
	 * Gets the certifications.
	 *
	 * @return the certifications
	 */
	public String getCertifications() {
		return this.certifications;
	}

	/**
	 * Sets the certifications.
	 *
	 * @param certifications the new certifications
	 */
	public void setCertifications(String certifications) {
		this.certifications = certifications;
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
	 * Gets the completion dt.
	 *
	 * @return the completion dt
	 */
	public Date getCompletionDt() {
		return this.completionDt;
	}

	/**
	 * Sets the completion dt.
	 *
	 * @param completionDt the new completion dt
	 */
	public void setCompletionDt(Date completionDt) {
		this.completionDt = completionDt;
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
	 * Gets the course of study.
	 *
	 * @return the course of study
	 */
	public String getCourseOfStudy() {
		return this.courseOfStudy;
	}

	/**
	 * Sets the course of study.
	 *
	 * @param courseOfStudy the new course of study
	 */
	public void setCourseOfStudy(String courseOfStudy) {
		this.courseOfStudy = courseOfStudy;
	}

	/**
	 * Gets the degree level.
	 *
	 * @return the degree level
	 */
	public String getDegreeLevel() {
		return this.degreeLevel;
	}

	/**
	 * Sets the degree level.
	 *
	 * @param degreeLevel the new degree level
	 */
	public void setDegreeLevel(String degreeLevel) {
		this.degreeLevel = degreeLevel;
	}

	/**
	 * Gets the degrees.
	 *
	 * @return the degrees
	 */
	public String getDegrees() {
		return this.degrees;
	}

	/**
	 * Sets the degrees.
	 *
	 * @param degrees the new degrees
	 */
	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	/**
	 * Gets the institution name.
	 *
	 * @return the institution name
	 */
	public String getInstitutionName() {
		return this.institutionName;
	}

	/**
	 * Sets the institution name.
	 *
	 * @param institutionName the new institution name
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	/**
	 * Gets the checks if is graduated.
	 *
	 * @return the checks if is graduated
	 */
	public int getIsGraduated() {
		return this.isGraduated;
	}

	/**
	 * Sets the checks if is graduated.
	 *
	 * @param isGraduated the new checks if is graduated
	 */
	public void setIsGraduated(int isGraduated) {
		this.isGraduated = isGraduated;
	}

	/**
	 * Gets the start dt.
	 *
	 * @return the start dt
	 */
	public Date getStartDt() {
		return this.startDt;
	}

	/**
	 * Sets the start dt.
	 *
	 * @param startDt the new start dt
	 */
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
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
	
	/**
	 * Gets the res degree edu.
	 *
	 * @return the res degree edu
	 */
	public ResDegreeEdu getResDegreeEdu() {
		return this.resDegreeEdu;
	}

	/**
	 * Sets the res degree edu.
	 *
	 * @param resDegreeEdu the new res degree edu
	 */
	public void setResDegreeEdu(ResDegreeEdu resDegreeEdu) {
		this.resDegreeEdu = resDegreeEdu;
	}
	
}