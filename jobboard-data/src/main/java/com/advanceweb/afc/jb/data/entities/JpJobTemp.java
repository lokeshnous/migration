package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "jp_job_temp")
public class JpJobTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private int abridgedJobTitleID;

	@Column(name = "account_num")
	private String accountNum;

	@Lob()
	private String adtext;

	@Column(name = "apply_online")
	private int applyOnline;

	@Column(name = "blind_ad")
	private int blindAd;

	private String city;

	@Column(name = "client_id")
	private int clientId;

	private int copied;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	private String email;

	@Column(name = "email_display")
	private String emailDisplay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_dt")
	private Date endDt;

	private String facility;

	private int featured;

	@Column(name = "featured_ad")
	private int featuredAd;

	private String headline;

	@Column(name = "image_path")
	private String imagePath;

	private int insertionListID;

	private String insertionNo;

	@Column(name = "is_international")
	private int isInternational;

	@Column(name = "is_national")
	private int isNational;

	@Column(name = "job_count")
	private int jobCount;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "job_id", insertable = false, updatable = false)
	private int jobId;

	@Column(name = "job_number")
	private String jobNumber;

	private String jobtitle;

	@Column(name = "jobtitle_desc")
	private String jobtitleDesc;

	@Column(name = "jobtitle_specialty_desc")
	private String jobtitleSpecialtyDesc;

	// bi-directional many-to-one association to JpSource
	@ManyToOne
	@JoinColumn(name = "source_id")
	private JpSource jpSource;

	private String keywords;

	private String location;

	@Column(name = "location_description")
	private String locationDescription;

	private String logo;

	private String name;

	@Column(name = "pdf_path")
	private String pdfPath;

	@Column(name = "position_level")
	private String positionLevel;

	private int positionLevelID;

	@Column(name = "position_type")
	private String positionType;

	private int positionTypeID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "posted_dt")
	private Date postedDt;

	@Column(name = "practice_setting")
	private String practiceSetting;

	private int practiceSettingID;

	@Column(name = "primary_zip")
	private String primaryZip;

	@Column(name = "publication_id")
	private int publicationId;

	private int salesOrderItemID;

	private String salesOrderNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_dt")
	private Date startDt;

	private int tempAdID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_dt")
	private Date updateDt;

	private String url;

	@Column(name = "url_display")
	private String urlDisplay;

	public JpJobTemp() {
	}

	public int getAbridgedJobTitleID() {
		return abridgedJobTitleID;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public String getAdtext() {
		return adtext;
	}

	public int getApplyOnline() {
		return applyOnline;
	}

	public int getBlindAd() {
		return blindAd;
	}

	public String getCity() {
		return city;
	}

	public int getClientId() {
		return clientId;
	}

	public int getCopied() {
		return copied;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public Date getDeleteDt() {
		return deleteDt;
	}

	public String getEmail() {
		return email;
	}

	public String getEmailDisplay() {
		return emailDisplay;
	}

	public Date getEndDt() {
		return endDt;
	}

	public String getFacility() {
		return facility;
	}

	public int getFeatured() {
		return featured;
	}

	public int getFeaturedAd() {
		return featuredAd;
	}

	public String getHeadline() {
		return headline;
	}

	public String getImagePath() {
		return imagePath;
	}

	public int getInsertionListID() {
		return insertionListID;
	}

	public String getInsertionNo() {
		return insertionNo;
	}

	public int getIsInternational() {
		return isInternational;
	}

	public int getIsNational() {
		return isNational;
	}

	public int getJobCount() {
		return jobCount;
	}

	public int getJobId() {
		return jobId;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public String getJobtitleDesc() {
		return jobtitleDesc;
	}

	public String getJobtitleSpecialtyDesc() {
		return jobtitleSpecialtyDesc;
	}

	public JpSource getJpSource() {
		return jpSource;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getLocation() {
		return location;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public String getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public String getPositionLevel() {
		return positionLevel;
	}

	public int getPositionLevelID() {
		return positionLevelID;
	}

	public String getPositionType() {
		return positionType;
	}

	public int getPositionTypeID() {
		return positionTypeID;
	}

	public Date getPostedDt() {
		return postedDt;
	}

	public String getPracticeSetting() {
		return practiceSetting;
	}

	public int getPracticeSettingID() {
		return practiceSettingID;
	}

	public String getPrimaryZip() {
		return primaryZip;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public int getSalesOrderItemID() {
		return salesOrderItemID;
	}

	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public Date getStartDt() {
		return startDt;
	}

	public int getTempAdID() {
		return tempAdID;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public String getUrl() {
		return url;
	}

	public String getUrlDisplay() {
		return urlDisplay;
	}

	public void setAbridgedJobTitleID(int abridgedJobTitleID) {
		this.abridgedJobTitleID = abridgedJobTitleID;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public void setAdtext(String adtext) {
		this.adtext = adtext;
	}

	public void setApplyOnline(int applyOnline) {
		this.applyOnline = applyOnline;
	}

	public void setBlindAd(int blindAd) {
		this.blindAd = blindAd;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setCopied(int copied) {
		this.copied = copied;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmailDisplay(String emailDisplay) {
		this.emailDisplay = emailDisplay;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public void setFeatured(int featured) {
		this.featured = featured;
	}

	public void setFeaturedAd(int featuredAd) {
		this.featuredAd = featuredAd;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setInsertionListID(int insertionListID) {
		this.insertionListID = insertionListID;
	}

	public void setInsertionNo(String insertionNo) {
		this.insertionNo = insertionNo;
	}

	public void setIsInternational(int isInternational) {
		this.isInternational = isInternational;
	}

	public void setIsNational(int isNational) {
		this.isNational = isNational;
	}

	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public void setJobtitleDesc(String jobtitleDesc) {
		this.jobtitleDesc = jobtitleDesc;
	}

	public void setJobtitleSpecialtyDesc(String jobtitleSpecialtyDesc) {
		this.jobtitleSpecialtyDesc = jobtitleSpecialtyDesc;
	}

	public void setJpSource(JpSource jpSource) {
		this.jpSource = jpSource;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}

	public void setPositionLevelID(int positionLevelID) {
		this.positionLevelID = positionLevelID;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public void setPositionTypeID(int positionTypeID) {
		this.positionTypeID = positionTypeID;
	}

	public void setPostedDt(Date postedDt) {
		this.postedDt = postedDt;
	}

	public void setPracticeSetting(String practiceSetting) {
		this.practiceSetting = practiceSetting;
	}

	public void setPracticeSettingID(int practiceSettingID) {
		this.practiceSettingID = practiceSettingID;
	}

	public void setPrimaryZip(String primaryZip) {
		this.primaryZip = primaryZip;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public void setSalesOrderItemID(int salesOrderItemID) {
		this.salesOrderItemID = salesOrderItemID;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public void setTempAdID(int tempAdID) {
		this.tempAdID = tempAdID;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUrlDisplay(String urlDisplay) {
		this.urlDisplay = urlDisplay;
	}

}