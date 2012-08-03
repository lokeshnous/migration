package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the mer_user_profile database table.
 * 
 */
@Entity
@Table(name="mer_user_profile")
public class MerUserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MerUserProfilePK id;

	@Column(name="attrib_value")
	private String attribValue;

	private String city;

	private String country;

	@Column(name="create_dt")
	private Timestamp createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	private String ethinicity;

	private String gender;

	private String industry;

	@Column(name="job_title")
	private String jobTitle;

	private String phone;

	private String profession;

	private String seeking;

	private String speciality;

	private String state;

	@Column(name="street_address1")
	private String streetAddress1;

	@Column(name="street_address2")
	private String streetAddress2;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	@Column(name="veteran_status")
	private String veteranStatus;

	private String zip;

	//bi-directional many-to-one association to MerProfileAttrib
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profile_attrib_id")
	private MerProfileAttrib merProfileAttrib;

	//bi-directional many-to-one association to MerUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private MerUser merUser;

    public MerUserProfile() {
    }

	public MerUserProfilePK getId() {
		return this.id;
	}

	public void setId(MerUserProfilePK id) {
		this.id = id;
	}
	
	public String getAttribValue() {
		return this.attribValue;
	}

	public void setAttribValue(String attribValue) {
		this.attribValue = attribValue;
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

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public String getEthinicity() {
		return this.ethinicity;
	}

	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSeeking() {
		return this.seeking;
	}

	public void setSeeking(String seeking) {
		this.seeking = seeking;
	}

	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetAddress1() {
		return this.streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return this.streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getVeteranStatus() {
		return this.veteranStatus;
	}

	public void setVeteranStatus(String veteranStatus) {
		this.veteranStatus = veteranStatus;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public MerProfileAttrib getMerProfileAttrib() {
		return this.merProfileAttrib;
	}

	public void setMerProfileAttrib(MerProfileAttrib merProfileAttrib) {
		this.merProfileAttrib = merProfileAttrib;
	}
	
	public MerUser getMerUser() {
		return this.merUser;
	}

	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}
	
}