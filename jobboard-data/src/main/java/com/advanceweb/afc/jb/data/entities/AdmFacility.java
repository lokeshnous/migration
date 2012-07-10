package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the adm_facility database table.
 * 
 */
@Entity
@Table(name="adm_facility")
public class AdmFacility implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="facility_id")
	private int facilityId;

	@Column(name="account_number")
	private String accountNumber;

	@Column(name="admin_user_id")
	private int adminUserId;

	private String city;

	private String country;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

	@Column(name="create_user_id")
	private int createUserId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="delete_user_id")
	private int deleteUserId;

	private String name;

	private String postcode;

	private String state;

	private String street;

	//bi-directional many-to-one association to AdmFacilityGroup
    @ManyToOne
	@JoinColumn(name="facility_group_id", insertable = false, updatable = false)
	private AdmFacilityGroup admFacilityGroup;

	//bi-directional many-to-one association to AdmUserFacility
	@OneToMany(mappedBy="admFacility")
	private List<AdmUserFacility> admUserFacilities;

	//bi-directional many-to-one association to JpJob
	@OneToMany(mappedBy="admFacility")
	private List<JpJob> jpJobs;

    public AdmFacility() {
    }

	public int getFacilityId() {
		return this.facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAdminUserId() {
		return this.adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
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

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public int getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public AdmFacilityGroup getAdmFacilityGroup() {
		return this.admFacilityGroup;
	}

	public void setAdmFacilityGroup(AdmFacilityGroup admFacilityGroup) {
		this.admFacilityGroup = admFacilityGroup;
	}
	
	public List<AdmUserFacility> getAdmUserFacilities() {
		return this.admUserFacilities;
	}

	public void setAdmUserFacilities(List<AdmUserFacility> admUserFacilities) {
		this.admUserFacilities = admUserFacilities;
	}
	
	public List<JpJob> getJpJobs() {
		return this.jpJobs;
	}

	public void setJpJobs(List<JpJob> jpJobs) {
		this.jpJobs = jpJobs;
	}
	
}