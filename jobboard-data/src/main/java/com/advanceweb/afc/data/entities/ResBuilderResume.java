package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "res_builder_resume")
public class ResBuilderResume implements Serializable {
	private static final long serialVersionUID = 1L;

	private short active;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "builder_resume_id", insertable = false, updatable = false)
	private int builderResumeId;

	@Column(name = "can_apply_to_jobs")
	private short canApplyToJobs;

	private String city;

	private String country;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Column(name = "create_user_id")
	private int createUserId;

	private String credentials;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	@Column(name = "delete_user_id")
	private int deleteUserId;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "is_published")
	private short isPublished;

	@Column(name = "job_objective")
	private String jobObjective;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "other_interests")
	private String otherInterests;

	private String phone;

	private String phone2;

	private String postcode;

	@Column(name = "prefix_name")
	private String prefixName;

	// bi-directional many-to-one association to ResBuilderCertification
	@OneToMany(mappedBy = "resBuilderResume")
	private List<ResBuilderCertification> resBuilderCertifications;

	// bi-directional many-to-one association to ResBuilderEdu
	@OneToMany(mappedBy = "resBuilderResume")
	private List<ResBuilderEdu> resBuilderEdus;

	// bi-directional many-to-one association to ResBuilderEmployment
	@OneToMany(mappedBy = "resBuilderResume")
	private List<ResBuilderEmployment> resBuilderEmployments;

	// bi-directional many-to-one association to ResBuilderReference
	@OneToMany(mappedBy = "resBuilderResume")
	private List<ResBuilderReference> resBuilderReferences;

	// bi-directional many-to-one association to ResPublishResume
	@ManyToOne
	@JoinColumn(name = "publish_resume_id")
	private ResPublishResume resPublishResume;

	@Column(name = "resume_name")
	private String resumeName;

	private String state;

	private String street;

	private String street2;

	@Column(name = "suffix_name")
	private String suffixName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_dt")
	private Date updateDt;

	@Column(name = "update_user_id")
	private int updateUserId;

	@Column(name = "user_id")
	private int userId;

	public ResBuilderResume() {
	}

	public short getActive() {
		return active;
	}

	public int getBuilderResumeId() {
		return builderResumeId;
	}

	public short getCanApplyToJobs() {
		return canApplyToJobs;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public String getCredentials() {
		return credentials;
	}

	public Date getDeleteDt() {
		return deleteDt;
	}

	public int getDeleteUserId() {
		return deleteUserId;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public short getIsPublished() {
		return isPublished;
	}

	public String getJobObjective() {
		return jobObjective;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getOtherInterests() {
		return otherInterests;
	}

	public String getPhone() {
		return phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public List<ResBuilderCertification> getResBuilderCertifications() {
		return resBuilderCertifications;
	}

	public List<ResBuilderEdu> getResBuilderEdus() {
		return resBuilderEdus;
	}

	public List<ResBuilderEmployment> getResBuilderEmployments() {
		return resBuilderEmployments;
	}

	public List<ResBuilderReference> getResBuilderReferences() {
		return resBuilderReferences;
	}

	public ResPublishResume getResPublishResume() {
		return resPublishResume;
	}

	public String getResumeName() {
		return resumeName;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

	public String getStreet2() {
		return street2;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public int getUserId() {
		return userId;
	}

	public void setActive(short active) {
		this.active = active;
	}

	public void setBuilderResumeId(int builderResumeId) {
		this.builderResumeId = builderResumeId;
	}

	public void setCanApplyToJobs(short canApplyToJobs) {
		this.canApplyToJobs = canApplyToJobs;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setIsPublished(short isPublished) {
		this.isPublished = isPublished;
	}

	public void setJobObjective(String jobObjective) {
		this.jobObjective = jobObjective;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setOtherInterests(String otherInterests) {
		this.otherInterests = otherInterests;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public void setResBuilderCertifications(
			List<ResBuilderCertification> resBuilderCertifications) {
		this.resBuilderCertifications = resBuilderCertifications;
	}

	public void setResBuilderEdus(List<ResBuilderEdu> resBuilderEdus) {
		this.resBuilderEdus = resBuilderEdus;
	}

	public void setResBuilderEmployments(
			List<ResBuilderEmployment> resBuilderEmployments) {
		this.resBuilderEmployments = resBuilderEmployments;
	}

	public void setResBuilderReferences(
			List<ResBuilderReference> resBuilderReferences) {
		this.resBuilderReferences = resBuilderReferences;
	}

	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}