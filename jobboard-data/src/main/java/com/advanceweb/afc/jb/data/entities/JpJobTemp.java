/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the jp_job_temp database table.
 * 
 */
@Entity
@Table(name="jp_job_temp")
public class JpJobTemp implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The job id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_id")
	private int jobId;

	/** The abridged job title id. */
	@Column(name="AbridgedJobTitleID")
	private int abridgedJobTitleID;

	/** The account num. */
	@Column(name="account_num")
	private String accountNum;

    /** The adtext. */
    @Lob()
	private String adtext;

	/** The apply online. */
	@Column(name="apply_online")
	private int applyOnline;

	/** The blind ad. */
	@Column(name="blind_ad")
	private int blindAd;

	/** The city. */
	private String city;

	/** The client id. */
	@Column(name="client_id")
	private int clientId;

	/** The copied. */
	@Column(name="Copied")
	private int copied;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The email. */
	private String email;

	/** The email display. */
	@Column(name="email_display")
	private String emailDisplay;

    /** The end dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="end_dt")
	private Date endDt;

	/** The facility. */
	private String facility;

	/** The featured. */
	private int featured;

	/** The featured ad. */
	@Column(name="featured_ad")
	private int featuredAd;

	/** The headline. */
	private String headline;

	/** The image path. */
	@Column(name="image_path")
	private String imagePath;

	/** The insertion list id. */
	@Column(name="InsertionListID")
	private int insertionListID;

	/** The insertion no. */
	@Column(name="InsertionNo")
	private String insertionNo;

	/** The is international. */
	@Column(name="is_international")
	private int isInternational;

	/** The is national. */
	@Column(name="is_national")
	private int isNational;

	/** The job count. */
	@Column(name="job_count")
	private int jobCount;

	/** The job number. */
	@Column(name="job_number")
	private String jobNumber;

	/** The jobtitle. */
	private String jobtitle;

	/** The jobtitle desc. */
	@Column(name="jobtitle_desc")
	private String jobtitleDesc;

	/** The jobtitle specialty desc. */
	@Column(name="jobtitle_specialty_desc")
	private String jobtitleSpecialtyDesc;

	/** The keywords. */
	private String keywords;

	/** The location. */
	private String location;

	/** The location description. */
	@Column(name="location_description")
	private String locationDescription;

	/** The logo. */
	private String logo;

	/** The name. */
	private String name;

	/** The pdf path. */
	@Column(name="pdf_path")
	private String pdfPath;

	/** The position level. */
	@Column(name="position_level")
	private String positionLevel;

	/** The position type. */
	@Column(name="position_type")
	private String positionType;

	/** The position level id. */
	@Column(name="PositionLevelID")
	private int positionLevelID;

	/** The position type id. */
	@Column(name="PositionTypeID")
	private int positionTypeID;

    /** The posted dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="posted_dt")
	private Date postedDt;

	/** The practice setting. */
	@Column(name="practice_setting")
	private String practiceSetting;

	/** The practice setting id. */
	@Column(name="PracticeSettingID")
	private int practiceSettingID;

	/** The primary zip. */
	@Column(name="primary_zip")
	private String primaryZip;

	/** The publication id. */
	@Column(name="publication_id")
	private int publicationId;

	/** The sales order item id. */
	@Column(name="SalesOrderItemID")
	private int salesOrderItemID;

	/** The sales order number. */
	@Column(name="SalesOrderNumber")
	private String salesOrderNumber;

    /** The start dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	/** The temp ad id. */
	@Column(name="TempAdID")
	private int tempAdID;

    /** The update dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	/** The url. */
	private String url;

	/** The url display. */
	@Column(name="url_display")
	private String urlDisplay;

	//bi-directional many-to-one association to JpSource
	/** The jp source. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="source_id")
	private JpSource jpSource;

	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public int getJobId() {
		return this.jobId;
	}

	/**
	 * Sets the job id.
	 *
	 * @param jobId the new job id
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	/**
	 * Gets the abridged job title id.
	 *
	 * @return the abridged job title id
	 */
	public int getAbridgedJobTitleID() {
		return this.abridgedJobTitleID;
	}

	/**
	 * Sets the abridged job title id.
	 *
	 * @param abridgedJobTitleID the new abridged job title id
	 */
	public void setAbridgedJobTitleID(int abridgedJobTitleID) {
		this.abridgedJobTitleID = abridgedJobTitleID;
	}

	/**
	 * Gets the account num.
	 *
	 * @return the account num
	 */
	public String getAccountNum() {
		return this.accountNum;
	}

	/**
	 * Sets the account num.
	 *
	 * @param accountNum the new account num
	 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	/**
	 * Gets the adtext.
	 *
	 * @return the adtext
	 */
	public String getAdtext() {
		return this.adtext;
	}

	/**
	 * Sets the adtext.
	 *
	 * @param adtext the new adtext
	 */
	public void setAdtext(String adtext) {
		this.adtext = adtext;
	}

	/**
	 * Gets the apply online.
	 *
	 * @return the apply online
	 */
	public int getApplyOnline() {
		return this.applyOnline;
	}

	/**
	 * Sets the apply online.
	 *
	 * @param applyOnline the new apply online
	 */
	public void setApplyOnline(int applyOnline) {
		this.applyOnline = applyOnline;
	}

	/**
	 * Gets the blind ad.
	 *
	 * @return the blind ad
	 */
	public int getBlindAd() {
		return this.blindAd;
	}

	/**
	 * Sets the blind ad.
	 *
	 * @param blindAd the new blind ad
	 */
	public void setBlindAd(int blindAd) {
		this.blindAd = blindAd;
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
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public int getClientId() {
		return this.clientId;
	}

	/**
	 * Sets the client id.
	 *
	 * @param clientId the new client id
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * Gets the copied.
	 *
	 * @return the copied
	 */
	public int getCopied() {
		return this.copied;
	}

	/**
	 * Sets the copied.
	 *
	 * @param copied the new copied
	 */
	public void setCopied(int copied) {
		this.copied = copied;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Timestamp getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
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
	 * Gets the end dt.
	 *
	 * @return the end dt
	 */
	public Date getEndDt() {
		return this.endDt;
	}

	/**
	 * Sets the end dt.
	 *
	 * @param endDt the new end dt
	 */
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	/**
	 * Gets the facility.
	 *
	 * @return the facility
	 */
	public String getFacility() {
		return this.facility;
	}

	/**
	 * Sets the facility.
	 *
	 * @param facility the new facility
	 */
	public void setFacility(String facility) {
		this.facility = facility;
	}

	/**
	 * Gets the featured.
	 *
	 * @return the featured
	 */
	public int getFeatured() {
		return this.featured;
	}

	/**
	 * Sets the featured.
	 *
	 * @param featured the new featured
	 */
	public void setFeatured(int featured) {
		this.featured = featured;
	}

	/**
	 * Gets the featured ad.
	 *
	 * @return the featured ad
	 */
	public int getFeaturedAd() {
		return this.featuredAd;
	}

	/**
	 * Sets the featured ad.
	 *
	 * @param featuredAd the new featured ad
	 */
	public void setFeaturedAd(int featuredAd) {
		this.featuredAd = featuredAd;
	}

	/**
	 * Gets the headline.
	 *
	 * @return the headline
	 */
	public String getHeadline() {
		return this.headline;
	}

	/**
	 * Sets the headline.
	 *
	 * @param headline the new headline
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return this.imagePath;
	}

	/**
	 * Sets the image path.
	 *
	 * @param imagePath the new image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Gets the insertion list id.
	 *
	 * @return the insertion list id
	 */
	public int getInsertionListID() {
		return this.insertionListID;
	}

	/**
	 * Sets the insertion list id.
	 *
	 * @param insertionListID the new insertion list id
	 */
	public void setInsertionListID(int insertionListID) {
		this.insertionListID = insertionListID;
	}

	/**
	 * Gets the insertion no.
	 *
	 * @return the insertion no
	 */
	public String getInsertionNo() {
		return this.insertionNo;
	}

	/**
	 * Sets the insertion no.
	 *
	 * @param insertionNo the new insertion no
	 */
	public void setInsertionNo(String insertionNo) {
		this.insertionNo = insertionNo;
	}

	/**
	 * Gets the checks if is international.
	 *
	 * @return the checks if is international
	 */
	public int getIsInternational() {
		return this.isInternational;
	}

	/**
	 * Sets the checks if is international.
	 *
	 * @param isInternational the new checks if is international
	 */
	public void setIsInternational(int isInternational) {
		this.isInternational = isInternational;
	}

	/**
	 * Gets the checks if is national.
	 *
	 * @return the checks if is national
	 */
	public int getIsNational() {
		return this.isNational;
	}

	/**
	 * Sets the checks if is national.
	 *
	 * @param isNational the new checks if is national
	 */
	public void setIsNational(int isNational) {
		this.isNational = isNational;
	}

	/**
	 * Gets the job count.
	 *
	 * @return the job count
	 */
	public int getJobCount() {
		return this.jobCount;
	}

	/**
	 * Sets the job count.
	 *
	 * @param jobCount the new job count
	 */
	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}

	/**
	 * Gets the job number.
	 *
	 * @return the job number
	 */
	public String getJobNumber() {
		return this.jobNumber;
	}

	/**
	 * Sets the job number.
	 *
	 * @param jobNumber the new job number
	 */
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	/**
	 * Gets the jobtitle.
	 *
	 * @return the jobtitle
	 */
	public String getJobtitle() {
		return this.jobtitle;
	}

	/**
	 * Sets the jobtitle.
	 *
	 * @param jobtitle the new jobtitle
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	/**
	 * Gets the jobtitle desc.
	 *
	 * @return the jobtitle desc
	 */
	public String getJobtitleDesc() {
		return this.jobtitleDesc;
	}

	/**
	 * Sets the jobtitle desc.
	 *
	 * @param jobtitleDesc the new jobtitle desc
	 */
	public void setJobtitleDesc(String jobtitleDesc) {
		this.jobtitleDesc = jobtitleDesc;
	}

	/**
	 * Gets the jobtitle specialty desc.
	 *
	 * @return the jobtitle specialty desc
	 */
	public String getJobtitleSpecialtyDesc() {
		return this.jobtitleSpecialtyDesc;
	}

	/**
	 * Sets the jobtitle specialty desc.
	 *
	 * @param jobtitleSpecialtyDesc the new jobtitle specialty desc
	 */
	public void setJobtitleSpecialtyDesc(String jobtitleSpecialtyDesc) {
		this.jobtitleSpecialtyDesc = jobtitleSpecialtyDesc;
	}

	/**
	 * Gets the keywords.
	 *
	 * @return the keywords
	 */
	public String getKeywords() {
		return this.keywords;
	}

	/**
	 * Sets the keywords.
	 *
	 * @param keywords the new keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the location description.
	 *
	 * @return the location description
	 */
	public String getLocationDescription() {
		return this.locationDescription;
	}

	/**
	 * Sets the location description.
	 *
	 * @param locationDescription the new location description
	 */
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	/**
	 * Gets the logo.
	 *
	 * @return the logo
	 */
	public String getLogo() {
		return this.logo;
	}

	/**
	 * Sets the logo.
	 *
	 * @param logo the new logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
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
	 * Gets the pdf path.
	 *
	 * @return the pdf path
	 */
	public String getPdfPath() {
		return this.pdfPath;
	}

	/**
	 * Sets the pdf path.
	 *
	 * @param pdfPath the new pdf path
	 */
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	/**
	 * Gets the position level.
	 *
	 * @return the position level
	 */
	public String getPositionLevel() {
		return this.positionLevel;
	}

	/**
	 * Sets the position level.
	 *
	 * @param positionLevel the new position level
	 */
	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}

	/**
	 * Gets the position type.
	 *
	 * @return the position type
	 */
	public String getPositionType() {
		return this.positionType;
	}

	/**
	 * Sets the position type.
	 *
	 * @param positionType the new position type
	 */
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	/**
	 * Gets the position level id.
	 *
	 * @return the position level id
	 */
	public int getPositionLevelID() {
		return this.positionLevelID;
	}

	/**
	 * Sets the position level id.
	 *
	 * @param positionLevelID the new position level id
	 */
	public void setPositionLevelID(int positionLevelID) {
		this.positionLevelID = positionLevelID;
	}

	/**
	 * Gets the position type id.
	 *
	 * @return the position type id
	 */
	public int getPositionTypeID() {
		return this.positionTypeID;
	}

	/**
	 * Sets the position type id.
	 *
	 * @param positionTypeID the new position type id
	 */
	public void setPositionTypeID(int positionTypeID) {
		this.positionTypeID = positionTypeID;
	}

	/**
	 * Gets the posted dt.
	 *
	 * @return the posted dt
	 */
	public Date getPostedDt() {
		return this.postedDt;
	}

	/**
	 * Sets the posted dt.
	 *
	 * @param postedDt the new posted dt
	 */
	public void setPostedDt(Date postedDt) {
		this.postedDt = postedDt;
	}

	/**
	 * Gets the practice setting.
	 *
	 * @return the practice setting
	 */
	public String getPracticeSetting() {
		return this.practiceSetting;
	}

	/**
	 * Sets the practice setting.
	 *
	 * @param practiceSetting the new practice setting
	 */
	public void setPracticeSetting(String practiceSetting) {
		this.practiceSetting = practiceSetting;
	}

	/**
	 * Gets the practice setting id.
	 *
	 * @return the practice setting id
	 */
	public int getPracticeSettingID() {
		return this.practiceSettingID;
	}

	/**
	 * Sets the practice setting id.
	 *
	 * @param practiceSettingID the new practice setting id
	 */
	public void setPracticeSettingID(int practiceSettingID) {
		this.practiceSettingID = practiceSettingID;
	}

	/**
	 * Gets the primary zip.
	 *
	 * @return the primary zip
	 */
	public String getPrimaryZip() {
		return this.primaryZip;
	}

	/**
	 * Sets the primary zip.
	 *
	 * @param primaryZip the new primary zip
	 */
	public void setPrimaryZip(String primaryZip) {
		this.primaryZip = primaryZip;
	}

	/**
	 * Gets the publication id.
	 *
	 * @return the publication id
	 */
	public int getPublicationId() {
		return this.publicationId;
	}

	/**
	 * Sets the publication id.
	 *
	 * @param publicationId the new publication id
	 */
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	/**
	 * Gets the sales order item id.
	 *
	 * @return the sales order item id
	 */
	public int getSalesOrderItemID() {
		return this.salesOrderItemID;
	}

	/**
	 * Sets the sales order item id.
	 *
	 * @param salesOrderItemID the new sales order item id
	 */
	public void setSalesOrderItemID(int salesOrderItemID) {
		this.salesOrderItemID = salesOrderItemID;
	}

	/**
	 * Gets the sales order number.
	 *
	 * @return the sales order number
	 */
	public String getSalesOrderNumber() {
		return this.salesOrderNumber;
	}

	/**
	 * Sets the sales order number.
	 *
	 * @param salesOrderNumber the new sales order number
	 */
	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	/**
	 * Gets the start dt.
	 *
	 * @return the start dt
	 */
	public Date getStartDt() {
		return this.startDt;
	}

	/**
	 * Sets the start dt.
	 *
	 * @param startDt the new start dt
	 */
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	/**
	 * Gets the temp ad id.
	 *
	 * @return the temp ad id
	 */
	public int getTempAdID() {
		return this.tempAdID;
	}

	/**
	 * Sets the temp ad id.
	 *
	 * @param tempAdID the new temp ad id
	 */
	public void setTempAdID(int tempAdID) {
		this.tempAdID = tempAdID;
	}

	/**
	 * Gets the update dt.
	 *
	 * @return the update dt
	 */
	public Date getUpdateDt() {
		return this.updateDt;
	}

	/**
	 * Sets the update dt.
	 *
	 * @param updateDt the new update dt
	 */
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
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
	 * Gets the jp source.
	 *
	 * @return the jp source
	 */
	public JpSource getJpSource() {
		return this.jpSource;
	}

	/**
	 * Sets the jp source.
	 *
	 * @param jpSource the new jp source
	 */
	public void setJpSource(JpSource jpSource) {
		this.jpSource = jpSource;
	}
	
}