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
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_id")
	private int jobId;

	@Column(name="AbridgedJobTitleID")
	private int abridgedJobTitleID;

	@Column(name="account_num")
	private String accountNum;

    @Lob()
	private String adtext;

	@Column(name="apply_online")
	private int applyOnline;

	@Column(name="blind_ad")
	private int blindAd;

	private String city;

	@Column(name="client_id")
	private int clientId;

	@Column(name="Copied")
	private int copied;

	@Column(name="create_dt")
	private Timestamp createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	private String email;

	@Column(name="email_display")
	private String emailDisplay;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="end_dt")
	private Date endDt;

	private String facility;

	private int featured;

	@Column(name="featured_ad")
	private int featuredAd;

	private String headline;

	@Column(name="image_path")
	private String imagePath;

	@Column(name="InsertionListID")
	private int insertionListID;

	@Column(name="InsertionNo")
	private String insertionNo;

	@Column(name="is_international")
	private int isInternational;

	@Column(name="is_national")
	private int isNational;

	@Column(name="job_count")
	private int jobCount;

	@Column(name="job_number")
	private String jobNumber;

	private String jobtitle;

	@Column(name="jobtitle_desc")
	private String jobtitleDesc;

	@Column(name="jobtitle_specialty_desc")
	private String jobtitleSpecialtyDesc;

	private String keywords;

	private String location;

	@Column(name="location_description")
	private String locationDescription;

	private String logo;

	private String name;

	@Column(name="pdf_path")
	private String pdfPath;

	@Column(name="position_level")
	private String positionLevel;

	@Column(name="position_type")
	private String positionType;

	@Column(name="PositionLevelID")
	private int positionLevelID;

	@Column(name="PositionTypeID")
	private int positionTypeID;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="posted_dt")
	private Date postedDt;

	@Column(name="practice_setting")
	private String practiceSetting;

	@Column(name="PracticeSettingID")
	private int practiceSettingID;

	@Column(name="primary_zip")
	private String primaryZip;

	@Column(name="publication_id")
	private int publicationId;

	@Column(name="SalesOrderItemID")
	private int salesOrderItemID;

	@Column(name="SalesOrderNumber")
	private String salesOrderNumber;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	@Column(name="TempAdID")
	private int tempAdID;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	private String url;

	@Column(name="url_display")
	private String urlDisplay;

	//bi-directional many-to-one association to JpSource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="source_id")
	private JpSource jpSource;

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getAbridgedJobTitleID() {
		return this.abridgedJobTitleID;
	}

	public void setAbridgedJobTitleID(int abridgedJobTitleID) {
		this.abridgedJobTitleID = abridgedJobTitleID;
	}

	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAdtext() {
		return this.adtext;
	}

	public void setAdtext(String adtext) {
		this.adtext = adtext;
	}

	public int getApplyOnline() {
		return this.applyOnline;
	}

	public void setApplyOnline(int applyOnline) {
		this.applyOnline = applyOnline;
	}

	public int getBlindAd() {
		return this.blindAd;
	}

	public void setBlindAd(int blindAd) {
		this.blindAd = blindAd;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getClientId() {
		return this.clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getCopied() {
		return this.copied;
	}

	public void setCopied(int copied) {
		this.copied = copied;
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

	public String getEmailDisplay() {
		return this.emailDisplay;
	}

	public void setEmailDisplay(String emailDisplay) {
		this.emailDisplay = emailDisplay;
	}

	public Date getEndDt() {
		return this.endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public String getFacility() {
		return this.facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public int getFeatured() {
		return this.featured;
	}

	public void setFeatured(int featured) {
		this.featured = featured;
	}

	public int getFeaturedAd() {
		return this.featuredAd;
	}

	public void setFeaturedAd(int featuredAd) {
		this.featuredAd = featuredAd;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getInsertionListID() {
		return this.insertionListID;
	}

	public void setInsertionListID(int insertionListID) {
		this.insertionListID = insertionListID;
	}

	public String getInsertionNo() {
		return this.insertionNo;
	}

	public void setInsertionNo(String insertionNo) {
		this.insertionNo = insertionNo;
	}

	public int getIsInternational() {
		return this.isInternational;
	}

	public void setIsInternational(int isInternational) {
		this.isInternational = isInternational;
	}

	public int getIsNational() {
		return this.isNational;
	}

	public void setIsNational(int isNational) {
		this.isNational = isNational;
	}

	public int getJobCount() {
		return this.jobCount;
	}

	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}

	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getJobtitle() {
		return this.jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getJobtitleDesc() {
		return this.jobtitleDesc;
	}

	public void setJobtitleDesc(String jobtitleDesc) {
		this.jobtitleDesc = jobtitleDesc;
	}

	public String getJobtitleSpecialtyDesc() {
		return this.jobtitleSpecialtyDesc;
	}

	public void setJobtitleSpecialtyDesc(String jobtitleSpecialtyDesc) {
		this.jobtitleSpecialtyDesc = jobtitleSpecialtyDesc;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationDescription() {
		return this.locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPdfPath() {
		return this.pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public String getPositionLevel() {
		return this.positionLevel;
	}

	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}

	public String getPositionType() {
		return this.positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public int getPositionLevelID() {
		return this.positionLevelID;
	}

	public void setPositionLevelID(int positionLevelID) {
		this.positionLevelID = positionLevelID;
	}

	public int getPositionTypeID() {
		return this.positionTypeID;
	}

	public void setPositionTypeID(int positionTypeID) {
		this.positionTypeID = positionTypeID;
	}

	public Date getPostedDt() {
		return this.postedDt;
	}

	public void setPostedDt(Date postedDt) {
		this.postedDt = postedDt;
	}

	public String getPracticeSetting() {
		return this.practiceSetting;
	}

	public void setPracticeSetting(String practiceSetting) {
		this.practiceSetting = practiceSetting;
	}

	public int getPracticeSettingID() {
		return this.practiceSettingID;
	}

	public void setPracticeSettingID(int practiceSettingID) {
		this.practiceSettingID = practiceSettingID;
	}

	public String getPrimaryZip() {
		return this.primaryZip;
	}

	public void setPrimaryZip(String primaryZip) {
		this.primaryZip = primaryZip;
	}

	public int getPublicationId() {
		return this.publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public int getSalesOrderItemID() {
		return this.salesOrderItemID;
	}

	public void setSalesOrderItemID(int salesOrderItemID) {
		this.salesOrderItemID = salesOrderItemID;
	}

	public String getSalesOrderNumber() {
		return this.salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public int getTempAdID() {
		return this.tempAdID;
	}

	public void setTempAdID(int tempAdID) {
		this.tempAdID = tempAdID;
	}

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
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

	public JpSource getJpSource() {
		return this.jpSource;
	}

	public void setJpSource(JpSource jpSource) {
		this.jpSource = jpSource;
	}
	
}