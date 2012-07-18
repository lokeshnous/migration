package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the res_builder_resume database table.
 * 
 */
@Entity
@Table(name="res_builder_resume")
public class ResBuilderResume implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_resume_id")
	private int builderResumeId;

	private short active;

	@Column(name="can_apply_to_jobs")
	private short canApplyToJobs;

	private String city;

	private String country;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="create_user_id")
	private int createUserId;

	private String credentials;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="delete_user_id")
	private int deleteUserId;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="is_published")
	private short isPublished;

	@Column(name="job_objective")
	private String jobObjective;

	@Column(name="last_name")
	private String lastName;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="other_interests")
	private String otherInterests;

	private String phone;

	private String phone2;

	private String postcode;

	@Column(name="prefix_name")
	private String prefixName;

	@Column(name="resume_name")
	private String resumeName;

	private String state;

	private String street;

	private String street2;

	@Column(name="suffix_name")
	private String suffixName;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	@Column(name="update_user_id")
	private int updateUserId;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to ResBuilderCertification
	
	@OneToMany(mappedBy="resBuilderResume", cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<ResBuilderCertification> resBuilderCertifications;

	//bi-directional many-to-one association to ResBuilderEdu
	@OneToMany(mappedBy="resBuilderResume", cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<ResBuilderEdu> resBuilderEdus;

	//bi-directional many-to-one association to ResBuilderEmployment
	@OneToMany(mappedBy="resBuilderResume", cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<ResBuilderEmployment> resBuilderEmployments;

	//bi-directional many-to-one association to ResBuilderReference
	@OneToMany(mappedBy="resBuilderResume", cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<ResBuilderReference> resBuilderReferences;

	//bi-directional many-to-one association to ResPublishResume
    @ManyToOne
	@JoinColumn(name="publish_resume_id")
	private ResPublishResume resPublishResume;

    public ResBuilderResume() {
    }

	public int getBuilderResumeId() {
		return this.builderResumeId;
	}

	public void setBuilderResumeId(int builderResumeId) {
		this.builderResumeId = builderResumeId;
	}

	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
		this.active = active;
	}

	public short getCanApplyToJobs() {
		return this.canApplyToJobs;
	}

	public void setCanApplyToJobs(short canApplyToJobs) {
		this.canApplyToJobs = canApplyToJobs;
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

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public int getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public String getCredentials() {
		return this.credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public int getDeleteUserId() {
		return this.deleteUserId;
	}

	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public short getIsPublished() {
		return this.isPublished;
	}

	public void setIsPublished(short isPublished) {
		this.isPublished = isPublished;
	}

	public String getJobObjective() {
		return this.jobObjective;
	}

	public void setJobObjective(String jobObjective) {
		this.jobObjective = jobObjective;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getOtherInterests() {
		return this.otherInterests;
	}

	public void setOtherInterests(String otherInterests) {
		this.otherInterests = otherInterests;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPrefixName() {
		return this.prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getResumeName() {
		return this.resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return this.street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getSuffixName() {
		return this.suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public int getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<ResBuilderCertification> getResBuilderCertifications() {
		return this.resBuilderCertifications;
	}

	public void setResBuilderCertifications(List<ResBuilderCertification> resBuilderCertifications) {
		this.resBuilderCertifications = resBuilderCertifications;
	}
	
	public List<ResBuilderEdu> getResBuilderEdus() {
		return this.resBuilderEdus;
	}

	public void setResBuilderEdus(List<ResBuilderEdu> resBuilderEdus) {
		this.resBuilderEdus = resBuilderEdus;
	}
	
	public List<ResBuilderEmployment> getResBuilderEmployments() {
		return this.resBuilderEmployments;
	}

	public void setResBuilderEmployments(List<ResBuilderEmployment> resBuilderEmployments) {
		this.resBuilderEmployments = resBuilderEmployments;
	}
	
	public List<ResBuilderReference> getResBuilderReferences() {
		return this.resBuilderReferences;
	}

	public void setResBuilderReferences(List<ResBuilderReference> resBuilderReferences) {
		this.resBuilderReferences = resBuilderReferences;
	}
	
	public ResPublishResume getResPublishResume() {
		return this.resPublishResume;
	}

	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}
	
}