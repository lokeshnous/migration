package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the adm_facility_contact database table.
 * 
 */
@Entity
@Table(name = "adm_facility_contact")
public class AdmFacilityContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "facility_contact_id")
	private int facilityContactId;

	private int active;

	@Column(name = "city")
	private String city;

	@Column(name = "company")
	private String company;

	@Column(name = "contact_type")
	private String contactType;

	@Column(name = "country")
	private String country;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt")
	private Date createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "phone")
	private String phone;

	private String phone2;

	@Column(name = "postcode")
	private String postcode;

	@Column(name = "state")
	private String state;

	private String street;

	private String street2;

	// bi-directional many-to-one association to AdmFacility
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "facility_id")
	private AdmFacility admFacility;

	public int getFacilityContactId() {
		return this.facilityContactId;
	}

	public void setFacilityContactId(int facilityContactId) {
		this.facilityContactId = facilityContactId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContactType() {
		return this.contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(final Date deleteDt) {
		this.deleteDt = deleteDt;
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

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}

}