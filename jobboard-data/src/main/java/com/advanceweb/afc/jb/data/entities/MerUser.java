package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the mer_user database table.
 * 
 */
@Entity
@Table(name="mer_user")
public class MerUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;

	private String awards;

	@Column(name="country_location_id")
	private String countryLocationId;

	@Column(name="create_dt")
	private Timestamp createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	private String email;

	@Column(name="empinfo_lookup_id")
	private int empinfoLookupId;

	@Column(name="enews_email_id")
	private String enewsEmailId;

	@Column(name="ethinicity_lookup_id")
	private int ethinicityLookupId;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	private String industry;

	@Column(name="job_title")
	private String jobTitle;

	@Column(name="last_name")
	private String lastName;

	private String memberships;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="mobile_no")
	private String mobileNo;

	@Column(name="other_details")
	private String otherDetails;

	private String password;

	private String phone;

	private String profession;

	private String speciality;

	@Column(name="subs_lookup_id")
	private int subsLookupId;

	@Column(name="veteran_lookup_id")
	private int veteranLookupId;

	@Column(name="zip_code_location_id")
	private String zipCodeLocationId;

	//bi-directional many-to-one association to MerUserApplication
	@OneToMany(mappedBy="merUser")
	private List<MerUserApplication> merUserApplications;

	//bi-directional many-to-one association to MerUserProfile
	@OneToMany(mappedBy="merUser")
	private List<MerUserProfile> merUserProfiles;

	//bi-directional many-to-one association to VstSessioninfo
	@OneToMany(mappedBy="merUser")
	private List<VstSessioninfo> vstSessioninfos;

    public MerUser() {
    }

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAwards() {
		return this.awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getCountryLocationId() {
		return this.countryLocationId;
	}

	public void setCountryLocationId(String countryLocationId) {
		this.countryLocationId = countryLocationId;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmpinfoLookupId() {
		return this.empinfoLookupId;
	}

	public void setEmpinfoLookupId(int empinfoLookupId) {
		this.empinfoLookupId = empinfoLookupId;
	}

	public String getEnewsEmailId() {
		return this.enewsEmailId;
	}

	public void setEnewsEmailId(String enewsEmailId) {
		this.enewsEmailId = enewsEmailId;
	}

	public int getEthinicityLookupId() {
		return this.ethinicityLookupId;
	}

	public void setEthinicityLookupId(int ethinicityLookupId) {
		this.ethinicityLookupId = ethinicityLookupId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMemberships() {
		return this.memberships;
	}

	public void setMemberships(String memberships) {
		this.memberships = memberships;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOtherDetails() {
		return this.otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getSubsLookupId() {
		return this.subsLookupId;
	}

	public void setSubsLookupId(int subsLookupId) {
		this.subsLookupId = subsLookupId;
	}

	public int getVeteranLookupId() {
		return this.veteranLookupId;
	}

	public void setVeteranLookupId(int veteranLookupId) {
		this.veteranLookupId = veteranLookupId;
	}

	public String getZipCodeLocationId() {
		return this.zipCodeLocationId;
	}

	public void setZipCodeLocationId(String zipCodeLocationId) {
		this.zipCodeLocationId = zipCodeLocationId;
	}

	public List<MerUserApplication> getMerUserApplications() {
		return this.merUserApplications;
	}

	public void setMerUserApplications(List<MerUserApplication> merUserApplications) {
		this.merUserApplications = merUserApplications;
	}
	
	public List<MerUserProfile> getMerUserProfiles() {
		return this.merUserProfiles;
	}

	public void setMerUserProfiles(List<MerUserProfile> merUserProfiles) {
		this.merUserProfiles = merUserProfiles;
	}
	
	public List<VstSessioninfo> getVstSessioninfos() {
		return this.vstSessioninfos;
	}

	public void setVstSessioninfos(List<VstSessioninfo> vstSessioninfos) {
		this.vstSessioninfos = vstSessioninfos;
	}
	
}