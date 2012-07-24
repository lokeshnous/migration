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
	@Column(name="facility_id", unique=true, nullable=false)
	private Integer facilityId;

	@Column(name="account_number", length=255)
	private String accountNumber;

	@Column(name="admin_user_id", nullable=false)
	private Integer adminUserId;

	@Column(length=255)
	private String city;

	@Column(name="company_news", length=500)
	private String companyNews;

	@Column(name="company_overview", length=500)
	private String companyOverview;

	@Column(length=5)
	private String country;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

	@Column(name="create_user_id")
	private Integer createUserId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="delete_user_id")
	private Integer deleteUserId;

	@Column(length=300)
	private String email;

	@Column(name="email_display", length=300)
	private String emailDisplay;

	@Column(name="logo_path", length=255)
	private String logoPath;

	@Column(nullable=false, length=255)
	private String name;

	@Column(name="name_display", length=255)
	private String nameDisplay;

	@Column(length=10)
	private String postcode;

	@Column(length=255)
	private String state;

	@Column(length=255)
	private String street;

	@Column(length=255)
	private String url;

	@Column(name="url_display", length=300)
	private String urlDisplay;

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

	public Integer getFacilityId() {
		return this.facilityId;
	}

	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getAdminUserId() {
		return this.adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyNews() {
		return this.companyNews;
	}

	public void setCompanyNews(String companyNews) {
		this.companyNews = companyNews;
	}

	public String getCompanyOverview() {
		return this.companyOverview;
	}

	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
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

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public Integer getDeleteUserId() {
		return this.deleteUserId;
	}

	public void setDeleteUserId(Integer deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailDisplay() {
		return this.emailDisplay;
	}

	public void setEmailDisplay(String emailDisplay) {
		this.emailDisplay = emailDisplay;
	}

	public String getLogoPath() {
		return this.logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameDisplay() {
		return this.nameDisplay;
	}

	public void setNameDisplay(String nameDisplay) {
		this.nameDisplay = nameDisplay;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlDisplay() {
		return this.urlDisplay;
	}

	public void setUrlDisplay(String urlDisplay) {
		this.urlDisplay = urlDisplay;
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