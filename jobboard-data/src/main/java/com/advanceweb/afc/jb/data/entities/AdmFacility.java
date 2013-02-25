/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The Constant ADM_FACILITY. */
	private static final String ADM_FACILITY = "admFacility";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The facility id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="facility_id")
	private Integer facilityId;

	/** The account number. */
	@Column(name="account_number")
	private String accountNumber;

	/** The admin user id. */
	@Column(name="admin_user_id")
	private Integer adminUserId;

	/** The city. */
	private String city;

	/** The color palette. */
	@Column(name="color_palette")
	private String colorPalette;

	/** The company news. */
	@Column(name="company_news")
	private String companyNews;

	/** The company overview. */
	@Column(name="company_overview")
	private String companyOverview;

	/** The country. */
	private String country;

    /** The create dt. */
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

	/** The create user id. */
	@Column(name="create_user_id")
	private Integer createUserId;

    /** The delete dt. */
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The delete user id. */
	@Column(name="delete_user_id")
	private Integer deleteUserId;

	/** The email. */
	private String email;

	/** The email display. */
	@Column(name="email_display")
	private String emailDisplay;

	/** The facility type. */
	@Column(name="facility_type" )
	private String facilityType;

	/** The logo path. */
	@Column(name="logo_path")
	private String logoPath;

	/** The name. */
	private String name;

	/** The name display. */
	@Column(name="name_display")
	private String nameDisplay;

	/** The postcode. */
	private String postcode;

	/** The promo media path. */
	@Column(name="promo_media_path")
	private String promoMediaPath;

	/** The state. */
	private String state;

	/** The street. */
	private String street;

	/** The url. */
	private String url;

	/** The url display. */
	@Column(name="url_display")
	private String urlDisplay;

	//bi-directional many-to-one association to AdmFacility
	/** The facility parent id. */
	@Column(name="facility_parent_id")
	private int  facilityParentId;

	/** The ns customer id. */
	@Column(name="netsuite_id")
	private int nsCustomerID;
	
	/** The template id. */
	@Column(name="template_id")
	private int templateId=-1;
	
//	Featured Employer start date
	/** The fe start dt. */
@Column(name="fe_start_dt")
	private Date feStartDt;

//	Featured Employer end date
	/** The fe end dt. */
@Column(name="fe_end_dt")
	private Date feEndDt;
	
//	Featured Employer 
	/** The featured emp. */
@Column(name="featured_employer")
	private byte featuredEmp;
	
	//bi-directional many-to-one association to AdmFacility
//	@OneToMany(mappedBy="admFacility")
//	private List<AdmFacility> admFacilities;

	//bi-directional many-to-one association to AdmFacilityContact
	/** The adm facility contacts. */
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<AdmFacilityContact> admFacilityContacts;

	//bi-directional one-to-one association to AdmFacilityCredit
	/** The adm facility credit. */
	@OneToOne(mappedBy=ADM_FACILITY)
	private AdmFacilityCredit admFacilityCredit;

	//bi-directional many-to-one association to AdmFacilitySubscription
	/** The adm facility subscriptions. */
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<AdmFacilitySubscription> admFacilitySubscriptions;

	//bi-directional many-to-one association to AdmPurchaseHistory
	/** The adm purchase histories. */
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<AdmPurchaseHistory> admPurchaseHistories;

	//bi-directional many-to-one association to AdmUserFacility
	/** The adm user facilities. */
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<AdmUserFacility> admUserFacilities;

	//bi-directional many-to-one association to JpJob
	/** The jp jobs. */
	@OneToMany(mappedBy=ADM_FACILITY)
	private List<JpJob> jpJobs;

	//bi-directional many-to-one association to JpTemplate
	/** The jp templates. */
	@OneToMany(mappedBy=ADM_FACILITY,cascade=CascadeType.ALL)
	private List<JpTemplate> jpTemplates;
	
	//bi-directional many-to-one association to AdmOrderHeader
	/** The adm order header. */
	@OneToMany(mappedBy=ADM_FACILITY, cascade = CascadeType.ALL)
	private List<AdmOrderHeader> admOrderHeader;
	
	//bi-directional many-to-one association to AdmPurchaseHistory
	/** The adm facility inventories. */
	@OneToMany(mappedBy=ADM_FACILITY, cascade = CascadeType.ALL)
	private List<AdmFacilityInventory> admFacilityInventories;

	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
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

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber the new account number
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Gets the admin user id.
	 *
	 * @return the admin user id
	 */
	public Integer getAdminUserId() {
		return this.adminUserId;
	}

	/**
	 * Sets the admin user id.
	 *
	 * @param adminUserId the new admin user id
	 */
	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
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
	 * Gets the color palette.
	 *
	 * @return the color palette
	 */
	public String getColorPalette() {
		return this.colorPalette;
	}

	/**
	 * Sets the color palette.
	 *
	 * @param colorPalette the new color palette
	 */
	public void setColorPalette(String colorPalette) {
		this.colorPalette = colorPalette;
	}

	/**
	 * Gets the company news.
	 *
	 * @return the company news
	 */
	public String getCompanyNews() {
		return this.companyNews;
	}

	/**
	 * Sets the company news.
	 *
	 * @param companyNews the new company news
	 */
	public void setCompanyNews(String companyNews) {
		this.companyNews = companyNews;
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
	public Date getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the creates the user id.
	 *
	 * @return the creates the user id
	 */
	public Integer getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * Sets the creates the user id.
	 *
	 * @param createUserId the new creates the user id
	 */
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

	/**
	 * Gets the featured emp.
	 *
	 * @return the featured emp
	 */
	public byte getFeaturedEmp() {
		return featuredEmp;
	}

	/**
	 * Sets the featured emp.
	 *
	 * @param featuredEmp the new featured emp
	 */
	public void setFeaturedEmp(byte featuredEmp) {
		this.featuredEmp = featuredEmp;
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
	public Integer getDeleteUserId() {
		return this.deleteUserId;
	}

	/**
	 * Sets the delete user id.
	 *
	 * @param deleteUserId the new delete user id
	 */
	public void setDeleteUserId(Integer deleteUserId) {
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
	 * Gets the email display.
	 *
	 * @return the email display
	 */
	public String getEmailDisplay() {
		return this.emailDisplay;
	}

	/**
	 * Sets the email display.
	 *
	 * @param emailDisplay the new email display
	 */
	public void setEmailDisplay(String emailDisplay) {
		this.emailDisplay = emailDisplay;
	}

	/**
	 * Gets the facility type.
	 *
	 * @return the facility type
	 */
	public String getFacilityType() {
		return this.facilityType;
	}

	/**
	 * Sets the facility type.
	 *
	 * @param facilityType the new facility type
	 */
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	/**
	 * Gets the logo path.
	 *
	 * @return the logo path
	 */
	public String getLogoPath() {
		return this.logoPath;
	}

	/**
	 * Sets the logo path.
	 *
	 * @param logoPath the new logo path
	 */
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name display.
	 *
	 * @return the name display
	 */
	public String getNameDisplay() {
		return this.nameDisplay;
	}

	/**
	 * Sets the name display.
	 *
	 * @param nameDisplay the new name display
	 */
	public void setNameDisplay(String nameDisplay) {
		this.nameDisplay = nameDisplay;
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
	 * Gets the promo media path.
	 *
	 * @return the promo media path
	 */
	public String getPromoMediaPath() {
		return this.promoMediaPath;
	}

	/**
	 * Sets the promo media path.
	 *
	 * @param promoMediaPath the new promo media path
	 */
	public void setPromoMediaPath(String promoMediaPath) {
		this.promoMediaPath = promoMediaPath;
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
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the url display.
	 *
	 * @return the url display
	 */
	public String getUrlDisplay() {
		return this.urlDisplay;
	}

	/**
	 * Sets the url display.
	 *
	 * @param urlDisplay the new url display
	 */
	public void setUrlDisplay(String urlDisplay) {
		this.urlDisplay = urlDisplay;
	}	
	
	/**
	 * Gets the facility parent id.
	 *
	 * @return the facility parent id
	 */
	public int getFacilityParentId() {
		return facilityParentId;
	}

	/**
	 * Sets the facility parent id.
	 *
	 * @param facilityParentId the new facility parent id
	 */
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
	
	/**
 * Gets the adm facility contacts.
 *
 * @return the adm facility contacts
 */
public List<AdmFacilityContact> getAdmFacilityContacts() {
		return this.admFacilityContacts;
	}

	/**
	 * Sets the adm facility contacts.
	 *
	 * @param admFacilityContacts the new adm facility contacts
	 */
	public void setAdmFacilityContacts(List<AdmFacilityContact> admFacilityContacts) {
		this.admFacilityContacts = admFacilityContacts;
	}
	
	/**
	 * Gets the adm facility credit.
	 *
	 * @return the adm facility credit
	 */
	public AdmFacilityCredit getAdmFacilityCredit() {
		return this.admFacilityCredit;
	}

	/**
	 * Sets the adm facility credit.
	 *
	 * @param admFacilityCredit the new adm facility credit
	 */
	public void setAdmFacilityCredit(AdmFacilityCredit admFacilityCredit) {
		this.admFacilityCredit = admFacilityCredit;
	}
	
	/**
	 * Gets the adm facility subscriptions.
	 *
	 * @return the adm facility subscriptions
	 */
	public List<AdmFacilitySubscription> getAdmFacilitySubscriptions() {
		return this.admFacilitySubscriptions;
	}

	/**
	 * Sets the adm facility subscriptions.
	 *
	 * @param admFacilitySubscriptions the new adm facility subscriptions
	 */
	public void setAdmFacilitySubscriptions(List<AdmFacilitySubscription> admFacilitySubscriptions) {
		this.admFacilitySubscriptions = admFacilitySubscriptions;
	}
	
	/**
	 * Gets the adm purchase histories.
	 *
	 * @return the adm purchase histories
	 */
	public List<AdmPurchaseHistory> getAdmPurchaseHistories() {
		return this.admPurchaseHistories;
	}

	/**
	 * Sets the adm purchase histories.
	 *
	 * @param admPurchaseHistories the new adm purchase histories
	 */
	public void setAdmPurchaseHistories(List<AdmPurchaseHistory> admPurchaseHistories) {
		this.admPurchaseHistories = admPurchaseHistories;
	}
	
	/**
	 * Gets the adm user facilities.
	 *
	 * @return the adm user facilities
	 */
	public List<AdmUserFacility> getAdmUserFacilities() {
		return this.admUserFacilities;
	}

	/**
	 * Sets the adm user facilities.
	 *
	 * @param admUserFacilities the new adm user facilities
	 */
	public void setAdmUserFacilities(List<AdmUserFacility> admUserFacilities) {
		this.admUserFacilities = admUserFacilities;
	}
	
	/**
	 * Gets the jp jobs.
	 *
	 * @return the jp jobs
	 */
	public List<JpJob> getJpJobs() {
		return this.jpJobs;
	}

	/**
	 * Sets the jp jobs.
	 *
	 * @param jpJobs the new jp jobs
	 */
	public void setJpJobs(List<JpJob> jpJobs) {
		this.jpJobs = jpJobs;
	}
	
	/**
	 * Gets the jp templates.
	 *
	 * @return the jp templates
	 */
	public List<JpTemplate> getJpTemplates() {
		return this.jpTemplates;
	}

	/**
	 * Sets the jp templates.
	 *
	 * @param jpTemplates the new jp templates
	 */
	public void setJpTemplates(List<JpTemplate> jpTemplates) {
		this.jpTemplates = jpTemplates;
	}

	/**
	 * Gets the company overview.
	 *
	 * @return the company overview
	 */
	public String getCompanyOverview() {
		return companyOverview;
	}

	/**
	 * Sets the company overview.
	 *
	 * @param companyOverview the new company overview
	 */
	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}

	/**
	 * Gets the ns customer id.
	 *
	 * @return the ns customer id
	 */
	public int getNsCustomerID() {
		return nsCustomerID;
	}

	/**
	 * Sets the ns customer id.
	 *
	 * @param nsCustomerID the new ns customer id
	 */
	public void setNsCustomerID(int nsCustomerID) {
		this.nsCustomerID = nsCustomerID;
	}

	/**
	 * Gets the template id.
	 *
	 * @return the template id
	 */
	public int getTemplateId() {
		return templateId;
	}

	/**
	 * Sets the template id.
	 *
	 * @param templateId the new template id
	 */
	public void setTemplateId(int templateId) {
		if(templateId==0){
			templateId=-1;
		}
		this.templateId = templateId;
	}

	/**
	 * Gets the adm facility inventories.
	 *
	 * @return the adm facility inventories
	 */
	public List<AdmFacilityInventory> getAdmFacilityInventories() {
		return admFacilityInventories;
	}

	/**
	 * Sets the adm facility inventories.
	 *
	 * @param admFacilityInventories the new adm facility inventories
	 */
	public void setAdmFacilityInventories(
			List<AdmFacilityInventory> admFacilityInventories) {
		this.admFacilityInventories = admFacilityInventories;
	}
}