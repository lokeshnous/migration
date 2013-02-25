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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the jp_job database table.
 * 
 */
@Entity
@Table(name="jp_job")
public class JpJob implements Serializable {

	/** The Constant JP_JOB. */
	private static final String JP_JOB = "jpJob";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The job id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_id")
	private int jobId;

	/** The account num. */
	@Column(name="account_num")
	private String accountNum;

	/** The active. */
	@Column(name="active")
	private byte active;

	/** The admin user id. */
	@Column(name="admin_user_id")
	private int adminUserId;

    /** The adtext. */
    @Lob()
	private String adtext;

	/** The apply online. */
	@Column(name="apply_online")
	private int applyOnline;

	/** The auto renew. */
	@Column(name="auto_renew")
	private int autoRenew;

	/** The blind ad. */
	@Column(name="blind_ad")
	private int blindAd;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The create user id. */
	@Column(name="create_user_id")
	private int createUserId;

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
	private byte featured;

	/** The featured ad. */
	@Column(name="featured_ad")
	private byte featuredAd;

	/** The headline. */
	private String headline;

	/** The image path. */
	@Column(name="image_path")
	private String imagePath;

	/** The is international. */
	@Column(name="is_international")
	private byte isInternational;

	/** The is national. */
	@Column(name="is_national")
	private byte isNational;

	/** The job count. */
	@Column(name="job_count")
	private int jobCount;

	/** The job number. */
	@Column(name="job_number")
	private String jobNumber;

	/** The jobtitle. */
	private String jobtitle;

	/** The keywords. */
	private String keywords;

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

	/** The practice setting. */
	@Column(name="practice_setting")
	private String practiceSetting;

	/** The publication id. */
	@Column(name="publication_id")
	private int publicationId;

	/** The skills. */
	private String skills;

    /** The start dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	/** The tracking pixel. */
	@Column(name="tracking_pixel")
	private String trackingPixel;

    /** The update dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	/** The url. */
	private String url;

	/** The url display. */
	@Column(name="url_display")
	private String urlDisplay;
	
	
	/** The template override. */
	@Column(name="template_override")
	private int templateOverride=0;
		
	//bi-directional many-to-one association to AdmSaveJob
	/** The adm save jobs. */
	@OneToMany(mappedBy=JP_JOB)
	private List<AdmSaveJob> admSaveJobs;

	//bi-directional many-to-one association to JpTemplate
	/** The jp template. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="template_id")
	private JpTemplate jpTemplate;

	//bi-directional many-to-one association to JpSource
	/** The jp source. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="source_id")
	private JpSource jpSource;

	//bi-directional many-to-one association to AdmFacility
	/** The adm facility. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id")
	private AdmFacility admFacility;

	//bi-directional many-to-one association to JpJobType
	/** The jp job type. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_type_id")
	private JpJobType jpJobType;

	//bi-directional many-to-one association to JpJobAddon
	/** The jp job addons. */
	@OneToMany(mappedBy=JP_JOB)
	private List<JpJobAddon> jpJobAddons;

	//bi-directional many-to-one association to JpJobApply
	/** The jp job applies. */
	@OneToMany(mappedBy=JP_JOB, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<JpJobApply> jpJobApplies;

	//bi-directional many-to-one association to JpJobLocation
	/** The jp job locations. */
	@OneToMany(mappedBy=JP_JOB)
	private List<JpJobLocation> jpJobLocations;

	//bi-directional one-to-one association to JpJobStat
	/** The jp job stat. */
	@OneToOne(mappedBy=JP_JOB, fetch=FetchType.LAZY)
	private JpJobStat jpJobStat;

	//bi-directional many-to-one association to ResGuestApply
	/** The res guest applies. */
	@OneToMany(mappedBy=JP_JOB)
	private List<ResGuestApply> resGuestApplies;

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
	 * Gets the active.
	 *
	 * @return the active
	 */
	public byte getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(byte active) {
		this.active = active;
	}

	/**
	 * Gets the admin user id.
	 *
	 * @return the admin user id
	 */
	public int getAdminUserId() {
		return this.adminUserId;
	}

	/**
	 * Sets the admin user id.
	 *
	 * @param adminUserId the new admin user id
	 */
	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
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
	 * Gets the auto renew.
	 *
	 * @return the auto renew
	 */
	public int getAutoRenew() {
		return this.autoRenew;
	}

	/**
	 * Sets the auto renew.
	 *
	 * @param autoRenew the new auto renew
	 */
	public void setAutoRenew(int autoRenew) {
		this.autoRenew = autoRenew;
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
	 * Gets the creates the user id.
	 *
	 * @return the creates the user id
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * Sets the creates the user id.
	 *
	 * @param createUserId the new creates the user id
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
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
	public byte getFeatured() {
		return this.featured;
	}

	/**
	 * Sets the featured.
	 *
	 * @param featured the new featured
	 */
	public void setFeatured(byte featured) {
		this.featured = featured;
	}

	/**
	 * Gets the featured ad.
	 *
	 * @return the featured ad
	 */
	public byte getFeaturedAd() {
		return this.featuredAd;
	}

	/**
	 * Sets the featured ad.
	 *
	 * @param featuredAd the new featured ad
	 */
	public void setFeaturedAd(byte featuredAd) {
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
	 * Gets the checks if is international.
	 *
	 * @return the checks if is international
	 */
	public byte getIsInternational() {
		return this.isInternational;
	}

	/**
	 * Sets the checks if is international.
	 *
	 * @param isInternational the new checks if is international
	 */
	public void setIsInternational(byte isInternational) {
		this.isInternational = isInternational;
	}

	/**
	 * Gets the checks if is national.
	 *
	 * @return the checks if is national
	 */
	public byte getIsNational() {
		return this.isNational;
	}

	/**
	 * Sets the checks if is national.
	 *
	 * @param isNational the new checks if is national
	 */
	public void setIsNational(byte isNational) {
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
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public String getSkills() {
		return this.skills;
	}

	/**
	 * Sets the skills.
	 *
	 * @param skills the new skills
	 */
	public void setSkills(String skills) {
		this.skills = skills;
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
	 * Gets the tracking pixel.
	 *
	 * @return the tracking pixel
	 */
	public String getTrackingPixel() {
		return this.trackingPixel;
	}

	/**
	 * Sets the tracking pixel.
	 *
	 * @param trackingPixel the new tracking pixel
	 */
	public void setTrackingPixel(String trackingPixel) {
		this.trackingPixel = trackingPixel;
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
	 * Gets the adm save jobs.
	 *
	 * @return the adm save jobs
	 */
	public List<AdmSaveJob> getAdmSaveJobs() {
		return this.admSaveJobs;
	}

	/**
	 * Sets the adm save jobs.
	 *
	 * @param admSaveJobs the new adm save jobs
	 */
	public void setAdmSaveJobs(List<AdmSaveJob> admSaveJobs) {
		this.admSaveJobs = admSaveJobs;
	}
	
	/**
	 * Gets the jp template.
	 *
	 * @return the jp template
	 */
	public JpTemplate getJpTemplate() {
		return this.jpTemplate;
	}

	/**
	 * Sets the jp template.
	 *
	 * @param jpTemplate the new jp template
	 */
	public void setJpTemplate(JpTemplate jpTemplate) {
		this.jpTemplate = jpTemplate;
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
	
	/**
	 * Gets the adm facility.
	 *
	 * @return the adm facility
	 */
	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	/**
	 * Sets the adm facility.
	 *
	 * @param admFacility the new adm facility
	 */
	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
	
	/**
	 * Gets the jp job type.
	 *
	 * @return the jp job type
	 */
	public JpJobType getJpJobType() {
		return this.jpJobType;
	}

	/**
	 * Sets the jp job type.
	 *
	 * @param jpJobType the new jp job type
	 */
	public void setJpJobType(JpJobType jpJobType) {
		this.jpJobType = jpJobType;
	}
	
	/**
	 * Gets the jp job addons.
	 *
	 * @return the jp job addons
	 */
	public List<JpJobAddon> getJpJobAddons() {
		return this.jpJobAddons;
	}

	/**
	 * Sets the jp job addons.
	 *
	 * @param jpJobAddons the new jp job addons
	 */
	public void setJpJobAddons(List<JpJobAddon> jpJobAddons) {
		this.jpJobAddons = jpJobAddons;
	}
	
	/**
	 * Gets the jp job applies.
	 *
	 * @return the jp job applies
	 */
	public List<JpJobApply> getJpJobApplies() {
		return this.jpJobApplies;
	}

	/**
	 * Sets the jp job applies.
	 *
	 * @param jpJobApplies the new jp job applies
	 */
	public void setJpJobApplies(List<JpJobApply> jpJobApplies) {
		this.jpJobApplies = jpJobApplies;
	}
	
	/**
	 * Gets the jp job locations.
	 *
	 * @return the jp job locations
	 */
	public List<JpJobLocation> getJpJobLocations() {
		return this.jpJobLocations;
	}

	/**
	 * Sets the jp job locations.
	 *
	 * @param jpJobLocations the new jp job locations
	 */
	public void setJpJobLocations(List<JpJobLocation> jpJobLocations) {
		this.jpJobLocations = jpJobLocations;
	}
	
	/**
	 * Gets the jp job stat.
	 *
	 * @return the jp job stat
	 */
	public JpJobStat getJpJobStat() {
		return this.jpJobStat;
	}

	/**
	 * Sets the jp job stat.
	 *
	 * @param jpJobStat the new jp job stat
	 */
	public void setJpJobStat(JpJobStat jpJobStat) {
		this.jpJobStat = jpJobStat;
	}
	
	/**
	 * Gets the res guest applies.
	 *
	 * @return the res guest applies
	 */
	public List<ResGuestApply> getResGuestApplies() {
		return this.resGuestApplies;
	}

	/**
	 * Sets the res guest applies.
	 *
	 * @param resGuestApplies the new res guest applies
	 */
	public void setResGuestApplies(List<ResGuestApply> resGuestApplies) {
		this.resGuestApplies = resGuestApplies;
	}

	/**
	 * Gets the template override.
	 *
	 * @return the template override
	 */
	public int getTemplateOverride() {
		return templateOverride;
	}

	/**
	 * Sets the template override.
	 *
	 * @param templateOverride the new template override
	 */
	public void setTemplateOverride(int templateOverride) {
		this.templateOverride = templateOverride;
	}

}