package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
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
 * The persistent class for the adm_facility database table.
 * 
 */
@Entity
@Table(name = "adm_facility")
public class AdmFacility implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "account_number")
	private String accountNumber;

	// bi-directional many-to-one association to AdmFacilityGroup
	@ManyToOne
	@JoinColumn(name = "facility_group_id")
	private AdmFacilityGroup admFacilityGroup;

	@Column(name = "admin_user_id")
	private int adminUserId;

	// bi-directional many-to-one association to AdmUserFacility
	@OneToMany(mappedBy = "admFacility")
	private List<AdmUserFacility> admUserFacilities;

	private String city;

	private String country;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt")
	private Date createDt;

	@Column(name = "create_user_id")
	private int createUserId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	@Column(name = "delete_user_id")
	private int deleteUserId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "facility_id", insertable = false, updatable = false)
	private int facilityId;

	// bi-directional many-to-one association to JpJob
	@OneToMany(mappedBy = "admFacility")
	private List<JpJob> jpJobs;

	private String name;

	private String postcode;

	private String state;

	private String street;

	public AdmFacility() {
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public AdmFacilityGroup getAdmFacilityGroup() {
		return admFacilityGroup;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public List<AdmUserFacility> getAdmUserFacilities() {
		return admUserFacilities;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public Date getDeleteDt() {
		return deleteDt;
	}

	public int getDeleteUserId() {
		return deleteUserId;
	}

	public int getFacilityId() {
		return facilityId;
	}

	public List<JpJob> getJpJobs() {
		return jpJobs;
	}

	public String getName() {
		return name;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAdmFacilityGroup(AdmFacilityGroup admFacilityGroup) {
		this.admFacilityGroup = admFacilityGroup;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public void setAdmUserFacilities(List<AdmUserFacility> admUserFacilities) {
		this.admUserFacilities = admUserFacilities;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public void setJpJobs(List<JpJob> jpJobs) {
		this.jpJobs = jpJobs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}