package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the adm_facility database table.
 * 
 */
@Entity
@Table(name="adm_facility")
public class AdmFacility implements Serializable {
	private static final String ADM_FACILITY = "admFacility";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="facility_id")
	private Integer facilityId;

	@Column(name="account_number")
	private String accountNumber;

	@Column(name="admin_user_id")
	private Integer adminUserId;

	private String city;

	@Column(name="color_palette")
	private String colorPalette;

	@Column(name="company_news")
	private String companyNews;

	@Column(name="company_overview")
	private String companyOverview;

	private String country;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

	@Column(name="create_user_id")
	private Integer createUserId;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="delete_user_id")
	private Integer deleteUserId;

	private String email;

	@Column(name="email_display")
	private String emailDisplay;

	@Column(name="facility_type" )
	private String facilityType;

	@Column(name="logo_path")
	private String logoPath;

	private String name;

	@Column(name="name_display")
	private String nameDisplay;

	private String postcode;

	@Column(name="promo_media_path")
	private String promoMediaPath;

	private String state;

	private String street;

	private String url;

	@Column(name="url_display")
	private String urlDisplay;

	//bi-directional many-to-one association to AdmFacility
	@Column(name="facility_parent_id")
	private int  facilityParentId;

	@Column(name="netsuite_id")
	private int nsCustomerID;
	
	@Column(name="template_id")
	private int templateId=-1;
	
//	Featured Employer start date
	@Column(name="fe_start_dt")
	private Date feStartDt;

//	Featured Employer end date
	@Column(name="fe_end_dt")
	private Date feEndDt;
	
//	Featured Employer 
	@Column(name="featured_employer")
	private byte featuredEmp;
	
	//bi-directional many-to-one association to AdmFacility
//	@OneToMany(mappedBy="admFacility")
//	private List<AdmFacility> admFacilities;

	//bi-directional many-to-one association to AdmFacilityContact
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<AdmFacilityContact> admFacilityContacts;

	//bi-directional one-to-one association to AdmFacilityCredit
	@OneToOne(mappedBy=ADM_FACILITY)
	private AdmFacilityCredit admFacilityCredit;

	//bi-directional many-to-one association to AdmFacilitySubscription
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<AdmFacilitySubscription> admFacilitySubscriptions;

	//bi-directional many-to-one association to AdmPurchaseHistory
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<AdmPurchaseHistory> admPurchaseHistories;

	//bi-directional many-to-one association to AdmUserFacility
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<AdmUserFacility> admUserFacilities;

	//bi-directional many-to-one association to JpJob
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<JpJob> jpJobs;

	//bi-directional many-to-one association to JpTemplate
	@OneToMany(mappedBy=ADM_FACILITY,cascade=CascadeType.ALL)
	private List<JpTemplate> jpTemplates;
	
	//bi-directional many-to-one association to AdmOrderHeader
	@OneToMany(mappedBy=ADM_FACILITY, cascade = CascadeType.ALL)
	private List<AdmOrderHeader> admOrderHeader;
	
	//bi-directional many-to-one association to AdmPurchaseHistory
	@OneToMany(mappedBy=ADM_FACILITY, cascade = CascadeType.ALL)
	private List<AdmFacilityInventory> admFacilityInventories;

	public Integer getFacilityId() {
		return this.facilityId;
	}

	/**
	 * @return the admOrderHeader
	 */
	public List<AdmOrderHeader> getAdmOrderHeader() {
		return admOrderHeader;
	}

	/**
	 * @param admOrderHeader the admOrderHeader to set
	 */
	public void setAdmOrderHeader(List<AdmOrderHeader> admOrderHeader) {
		this.admOrderHeader = admOrderHeader;
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

	public String getColorPalette() {
		return this.colorPalette;
	}

	public void setColorPalette(String colorPalette) {
		this.colorPalette = colorPalette;
	}

	public String getCompanyNews() {
		return this.companyNews;
	}

	public void setCompanyNews(String companyNews) {
		this.companyNews = companyNews;
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

	/**
	 * @return the feStartDt
	 */
	public Date getFeStartDt() {
		return feStartDt;
	}

	/**
	 * @param feStartDt the feStartDt to set
	 */
	public void setFeStartDt(Date feStartDt) {
		this.feStartDt = feStartDt;
	}

	/**
	 * @return the feEndDt
	 */
	public Date getFeEndDt() {
		return feEndDt;
	}

	/**
	 * @param feEndDt the feEndDt to set
	 */
	public void setFeEndDt(Date feEndDt) {
		this.feEndDt = feEndDt;
	}

	public byte getFeaturedEmp() {
		return featuredEmp;
	}

	public void setFeaturedEmp(byte featuredEmp) {
		this.featuredEmp = featuredEmp;
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

	public String getFacilityType() {
		return this.facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
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

	public String getPromoMediaPath() {
		return this.promoMediaPath;
	}

	public void setPromoMediaPath(String promoMediaPath) {
		this.promoMediaPath = promoMediaPath;
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
	
	public int getFacilityParentId() {
		return facilityParentId;
	}

	public void setFacilityParentId(int facilityParentId) {
		if(facilityParentId==0){
			facilityParentId=-1;
		}
		this.facilityParentId = facilityParentId;
	}

//	public List<AdmFacility> getAdmFacilities() {
//		return this.admFacilities;
//	}
//
//	public void setAdmFacilities(List<AdmFacility> admFacilities) {
//		this.admFacilities = admFacilities;
//	}
	
	public List<AdmFacilityContact> getAdmFacilityContacts() {
		return this.admFacilityContacts;
	}

	public void setAdmFacilityContacts(List<AdmFacilityContact> admFacilityContacts) {
		this.admFacilityContacts = admFacilityContacts;
	}
	
	public AdmFacilityCredit getAdmFacilityCredit() {
		return this.admFacilityCredit;
	}

	public void setAdmFacilityCredit(AdmFacilityCredit admFacilityCredit) {
		this.admFacilityCredit = admFacilityCredit;
	}
	
	public List<AdmFacilitySubscription> getAdmFacilitySubscriptions() {
		return this.admFacilitySubscriptions;
	}

	public void setAdmFacilitySubscriptions(List<AdmFacilitySubscription> admFacilitySubscriptions) {
		this.admFacilitySubscriptions = admFacilitySubscriptions;
	}
	
	public List<AdmPurchaseHistory> getAdmPurchaseHistories() {
		return this.admPurchaseHistories;
	}

	public void setAdmPurchaseHistories(List<AdmPurchaseHistory> admPurchaseHistories) {
		this.admPurchaseHistories = admPurchaseHistories;
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
	
	public List<JpTemplate> getJpTemplates() {
		return this.jpTemplates;
	}

	public void setJpTemplates(List<JpTemplate> jpTemplates) {
		this.jpTemplates = jpTemplates;
	}

	public String getCompanyOverview() {
		return companyOverview;
	}

	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}

	public int getNsCustomerID() {
		return nsCustomerID;
	}

	public void setNsCustomerID(int nsCustomerID) {
		this.nsCustomerID = nsCustomerID;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		if(templateId==0){
			templateId=-1;
		}
		this.templateId = templateId;
	}

	public List<AdmFacilityInventory> getAdmFacilityInventories() {
		return admFacilityInventories;
	}

	public void setAdmFacilityInventories(
			List<AdmFacilityInventory> admFacilityInventories) {
		this.admFacilityInventories = admFacilityInventories;
	}
}