/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the res_builder_resume database table.
 * 
 */
@Entity
@Table(name="res_builder_resume")
public class ResBuilderResume implements Serializable {
	
	/** The Constant RES_BUILDER_RESUME. */
	private static final String RES_BUILDER_RESUME = "resBuilderResume";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The builder resume id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_resume_id")
	private int builderResumeId;

	/** The active. */
	private int active;

	/** The awards. */
	private String awards;

	/** The can apply to jobs. */
	@Column(name="can_apply_to_jobs")
	private int canApplyToJobs;

	/** The city. */
	private String city;

	/** The country. */
	private String country;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The create user id. */
	@Column(name="create_user_id")
	private int createUserId;

	/** The credentials. */
	private String credentials;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The delete user id. */
	@Column(name="delete_user_id")
	private int deleteUserId;

	/** The email. */
	private String email;

	/** The first name. */
	@Column(name="first_name")
	private String firstName;

	/** The is published. */
	@Column(name="is_published")
	private int isPublished;

	/** The job objective. */
	@Column(name="job_objective")
	private String jobObjective;

	/** The last name. */
	@Column(name="last_name")
	private String lastName;

	/** The memberships. */
	private String memberships;

	/** The middle name. */
	@Column(name="middle_name")
	private String middleName;

	/** The other interests. */
	@Column(name="other_interests")
	private String otherInterests;

	/** The phone. */
	private String phone;

	/** The phone2. */
	private String phone2;

	/** The postcode. */
	private String postcode;

	/** The prefix name. */
	@Column(name="prefix_name")
	private String prefixName;

	/** The resume name. */
	@Column(name="resume_name")
	private String resumeName;

	/** The state. */
	private String state;

	/** The street. */
	private String street;

	/** The street2. */
	private String street2;

	/** The suffix name. */
	@Column(name="suffix_name")
	private String suffixName;

    /** The update dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	/** The update user id. */
	@Column(name="update_user_id")
	private int updateUserId;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to ResBuilderCertification
	/** The res builder certifications. */
	@OneToMany(mappedBy=RES_BUILDER_RESUME, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ResBuilderCertification> resBuilderCertifications;

	//bi-directional many-to-one association to ResBuilderEdu
	/** The res builder edus. */
	@OneToMany(mappedBy=RES_BUILDER_RESUME, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ResBuilderEdu> resBuilderEdus;

	//bi-directional many-to-one association to ResBuilderEmployment
	/** The res builder employments. */
	@OneToMany(mappedBy=RES_BUILDER_RESUME, cascade=CascadeType.ALL,fetch=FetchType.EAGER, orphanRemoval=true)
	private List<ResBuilderEmployment> resBuilderEmployments;

	//bi-directional many-to-one association to ResBuilderLanguage
	/** The res builder languages. */
	@OneToMany(mappedBy=RES_BUILDER_RESUME, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ResBuilderLanguage> resBuilderLanguages;

	//bi-directional many-to-one association to ResBuilderPhone
	/** The res builder phones. */
	@OneToMany(mappedBy=RES_BUILDER_RESUME, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ResBuilderPhone> resBuilderPhones;

	//bi-directional many-to-one association to ResBuilderReference
	/** The res builder references. */
	@OneToMany(mappedBy=RES_BUILDER_RESUME, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ResBuilderReference> resBuilderReferences;

	//bi-directional many-to-one association to ResPublishResume
	/** The res publish resume. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="publish_resume_id")
	private ResPublishResume resPublishResume;
		
	/** The res upload resume id. */
	@Column(name="upload_resume_id")
	private int resUploadResumeId;

	//bi-directional many-to-one association to ResBuilderSkill
	/** The res builder skills. */
	@OneToMany(mappedBy=RES_BUILDER_RESUME, cascade=CascadeType.ALL)
	private List<ResBuilderSkill> resBuilderSkills;

	/**
	 * Gets the builder resume id.
	 *
	 * @return the builder resume id
	 */
	public int getBuilderResumeId() {
		return this.builderResumeId;
	}

	/**
	 * Sets the builder resume id.
	 *
	 * @param builderResumeId the new builder resume id
	 */
	public void setBuilderResumeId(int builderResumeId) {
		this.builderResumeId = builderResumeId;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public int getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * Gets the awards.
	 *
	 * @return the awards
	 */
	public String getAwards() {
		return this.awards;
	}

	/**
	 * Sets the awards.
	 *
	 * @param awards the new awards
	 */
	public void setAwards(String awards) {
		this.awards = awards;
	}

	/**
	 * Gets the can apply to jobs.
	 *
	 * @return the can apply to jobs
	 */
	public int getCanApplyToJobs() {
		return this.canApplyToJobs;
	}

	/**
	 * Sets the can apply to jobs.
	 *
	 * @param canApplyToJobs the new can apply to jobs
	 */
	public void setCanApplyToJobs(int canApplyToJobs) {
		this.canApplyToJobs = canApplyToJobs;
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
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Timestamp getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the creates the user id.
	 *
	 * @return the creates the user id
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * Sets the creates the user id.
	 *
	 * @param createUserId the new creates the user id
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * Gets the credentials.
	 *
	 * @return the credentials
	 */
	public String getCredentials() {
		return this.credentials;
	}

	/**
	 * Sets the credentials.
	 *
	 * @param credentials the new credentials
	 */
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	/**
	 * Gets the delete dt.
	 *
	 * @return the delete dt
	 */
	public Date getDeleteDt() {
		return this.deleteDt;
	}

	/**
	 * Sets the delete dt.
	 *
	 * @param deleteDt the new delete dt
	 */
	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	/**
	 * Gets the delete user id.
	 *
	 * @return the delete user id
	 */
	public int getDeleteUserId() {
		return this.deleteUserId;
	}

	/**
	 * Sets the delete user id.
	 *
	 * @param deleteUserId the new delete user id
	 */
	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the checks if is published.
	 *
	 * @return the checks if is published
	 */
	public int getIsPublished() {
		return this.isPublished;
	}

	/**
	 * Sets the checks if is published.
	 *
	 * @param isPublished the new checks if is published
	 */
	public void setIsPublished(int isPublished) {
		this.isPublished = isPublished;
	}

	/**
	 * Gets the job objective.
	 *
	 * @return the job objective
	 */
	public String getJobObjective() {
		return this.jobObjective;
	}

	/**
	 * Sets the job objective.
	 *
	 * @param jobObjective the new job objective
	 */
	public void setJobObjective(String jobObjective) {
		this.jobObjective = jobObjective;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the memberships.
	 *
	 * @return the memberships
	 */
	public String getMemberships() {
		return this.memberships;
	}

	/**
	 * Sets the memberships.
	 *
	 * @param memberships the new memberships
	 */
	public void setMemberships(String memberships) {
		this.memberships = memberships;
	}

	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Sets the middle name.
	 *
	 * @param middleName the new middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets the other interests.
	 *
	 * @return the other interests
	 */
	public String getOtherInterests() {
		return this.otherInterests;
	}

	/**
	 * Sets the other interests.
	 *
	 * @param otherInterests the new other interests
	 */
	public void setOtherInterests(String otherInterests) {
		this.otherInterests = otherInterests;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the phone2.
	 *
	 * @return the phone2
	 */
	public String getPhone2() {
		return this.phone2;
	}

	/**
	 * Sets the phone2.
	 *
	 * @param phone2 the new phone2
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * Gets the postcode.
	 *
	 * @return the postcode
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Sets the postcode.
	 *
	 * @param postcode the new postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Gets the prefix name.
	 *
	 * @return the prefix name
	 */
	public String getPrefixName() {
		return this.prefixName;
	}

	/**
	 * Sets the prefix name.
	 *
	 * @param prefixName the new prefix name
	 */
	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	/**
	 * Gets the resume name.
	 *
	 * @return the resume name
	 */
	public String getResumeName() {
		return this.resumeName;
	}

	/**
	 * Sets the resume name.
	 *
	 * @param resumeName the new resume name
	 */
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
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
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the street2.
	 *
	 * @return the street2
	 */
	public String getStreet2() {
		return this.street2;
	}

	/**
	 * Sets the street2.
	 *
	 * @param street2 the new street2
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * Gets the suffix name.
	 *
	 * @return the suffix name
	 */
	public String getSuffixName() {
		return this.suffixName;
	}

	/**
	 * Sets the suffix name.
	 *
	 * @param suffixName the new suffix name
	 */
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	/**
	 * Gets the update dt.
	 *
	 * @return the update dt
	 */
	public Date getUpdateDt() {
		return this.updateDt;
	}

	/**
	 * Sets the update dt.
	 *
	 * @param updateDt the new update dt
	 */
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	/**
	 * Gets the update user id.
	 *
	 * @return the update user id
	 */
	public int getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * Sets the update user id.
	 *
	 * @param updateUserId the new update user id
	 */
	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the res builder certifications.
	 *
	 * @return the res builder certifications
	 */
	public List<ResBuilderCertification> getResBuilderCertifications() {
		return this.resBuilderCertifications;
	}

	/**
	 * Sets the res builder certifications.
	 *
	 * @param resBuilderCertifications the new res builder certifications
	 */
	public void setResBuilderCertifications(List<ResBuilderCertification> resBuilderCertifications) {
		this.resBuilderCertifications = resBuilderCertifications;
	}
	
	/**
	 * Gets the res builder edus.
	 *
	 * @return the res builder edus
	 */
	public List<ResBuilderEdu> getResBuilderEdus() {
		return this.resBuilderEdus;
	}

	/**
	 * Sets the res builder edus.
	 *
	 * @param resBuilderEdus the new res builder edus
	 */
	public void setResBuilderEdus(List<ResBuilderEdu> resBuilderEdus) {
		this.resBuilderEdus = resBuilderEdus;
	}
	
	/**
	 * Gets the res builder employments.
	 *
	 * @return the res builder employments
	 */
	public List<ResBuilderEmployment> getResBuilderEmployments() {
		return this.resBuilderEmployments;
	}

	/**
	 * Sets the res builder employments.
	 *
	 * @param resBuilderEmployments the new res builder employments
	 */
	public void setResBuilderEmployments(List<ResBuilderEmployment> resBuilderEmployments) {
		this.resBuilderEmployments = resBuilderEmployments;
	}
	
	/**
	 * Gets the res builder languages.
	 *
	 * @return the res builder languages
	 */
	public List<ResBuilderLanguage> getResBuilderLanguages() {
		return this.resBuilderLanguages;
	}

	/**
	 * Sets the res builder languages.
	 *
	 * @param resBuilderLanguages the new res builder languages
	 */
	public void setResBuilderLanguages(List<ResBuilderLanguage> resBuilderLanguages) {
		this.resBuilderLanguages = resBuilderLanguages;
	}
	
	/**
	 * Gets the res builder phones.
	 *
	 * @return the res builder phones
	 */
	public List<ResBuilderPhone> getResBuilderPhones() {
		return this.resBuilderPhones;
	}

	/**
	 * Sets the res builder phones.
	 *
	 * @param resBuilderPhones the new res builder phones
	 */
	public void setResBuilderPhones(List<ResBuilderPhone> resBuilderPhones) {
		this.resBuilderPhones = resBuilderPhones;
	}
	
	/**
	 * Gets the res builder references.
	 *
	 * @return the res builder references
	 */
	public List<ResBuilderReference> getResBuilderReferences() {
		return this.resBuilderReferences;
	}

	/**
	 * Sets the res builder references.
	 *
	 * @param resBuilderReferences the new res builder references
	 */
	public void setResBuilderReferences(List<ResBuilderReference> resBuilderReferences) {
		this.resBuilderReferences = resBuilderReferences;
	}
	
	/**
	 * Gets the res publish resume.
	 *
	 * @return the res publish resume
	 */
	public ResPublishResume getResPublishResume() {
		return this.resPublishResume;
	}

	/**
	 * Sets the res publish resume.
	 *
	 * @param resPublishResume the new res publish resume
	 */
	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}
	
	/**
	 * Gets the res builder skills.
	 *
	 * @return the res builder skills
	 */
	public List<ResBuilderSkill> getResBuilderSkills() {
		return this.resBuilderSkills;
	}

	/**
	 * Sets the res builder skills.
	 *
	 * @param resBuilderSkills the new res builder skills
	 */
	public void setResBuilderSkills(List<ResBuilderSkill> resBuilderSkills) {
		this.resBuilderSkills = resBuilderSkills;
	}

	/**
	 * Gets the res upload resume id.
	 *
	 * @return the res upload resume id
	 */
	public int getResUploadResumeId() {
		return resUploadResumeId;
	}

	/**
	 * Sets the res upload resume id.
	 *
	 * @param resUploadResumeId the new res upload resume id
	 */
	public void setResUploadResumeId(int resUploadResumeId) {
		this.resUploadResumeId = resUploadResumeId;
	}

	
	
}