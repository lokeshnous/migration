package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the jp_job database table.
 * 
 */
@Entity
@Table(name="jp_job")
public class JpJob implements Serializable {

	private static final String JP_JOB = "jpJob";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_id")
	private int jobId;

	@Column(name="account_num")
	private String accountNum;

	@Column(name="active")
	private byte active;

	@Column(name="admin_user_id")
	private int adminUserId;

    @Lob()
	private String adtext;

	@Column(name="apply_online")
	private int applyOnline;

	@Column(name="auto_renew")
	private int autoRenew;

	@Column(name="blind_ad")
	private int blindAd;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="create_user_id")
	private int createUserId;

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

	private byte featured;

	@Column(name="featured_ad")
	private byte featuredAd;

	private String headline;

	@Column(name="image_path")
	private String imagePath;

	@Column(name="is_international")
	private byte isInternational;

	@Column(name="is_national")
	private byte isNational;

	@Column(name="job_count")
	private int jobCount;

	@Column(name="job_number")
	private String jobNumber;

	private String jobtitle;

	private String keywords;

	private String logo;

	private String name;

	@Column(name="pdf_path")
	private String pdfPath;

	@Column(name="position_level")
	private String positionLevel;

	@Column(name="position_type")
	private String positionType;

	@Column(name="practice_setting")
	private String practiceSetting;

	@Column(name="publication_id")
	private int publicationId;

	private String skills;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	@Column(name="tracking_pixel")
	private String trackingPixel;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	private String url;

	@Column(name="url_display")
	private String urlDisplay;
	
	@Column(name="job_status")
	private String jobStatus;
	
	//bi-directional many-to-one association to AdmSaveJob
	@OneToMany(mappedBy=JP_JOB)
	private List<AdmSaveJob> admSaveJobs;

	//bi-directional many-to-one association to JpTemplate
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="template_id")
	private JpTemplate jpTemplate;

	//bi-directional many-to-one association to JpSource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="source_id")
	private JpSource jpSource;

	//bi-directional many-to-one association to AdmFacility
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id")
	private AdmFacility admFacility;

	//bi-directional many-to-one association to JpJobType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_type_id")
	private JpJobType jpJobType;

	//bi-directional many-to-one association to JpJobAddon
	@OneToMany(mappedBy=JP_JOB)
	private List<JpJobAddon> jpJobAddons;

	//bi-directional many-to-one association to JpJobApply
	@OneToMany(mappedBy=JP_JOB, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<JpJobApply> jpJobApplies;

	//bi-directional many-to-one association to JpJobLocation
	@OneToMany(mappedBy=JP_JOB)
	private List<JpJobLocation> jpJobLocations;

	//bi-directional one-to-one association to JpJobStat
	@OneToOne(mappedBy=JP_JOB, fetch=FetchType.LAZY)
	private JpJobStat jpJobStat;

	//bi-directional many-to-one association to ResGuestApply
	@OneToMany(mappedBy=JP_JOB)
	private List<ResGuestApply> resGuestApplies;

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public int getAdminUserId() {
		return this.adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
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

	public int getAutoRenew() {
		return this.autoRenew;
	}

	public void setAutoRenew(int autoRenew) {
		this.autoRenew = autoRenew;
	}

	public int getBlindAd() {
		return this.blindAd;
	}

	public void setBlindAd(int blindAd) {
		this.blindAd = blindAd;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
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

	public byte getFeatured() {
		return this.featured;
	}

	public void setFeatured(byte featured) {
		this.featured = featured;
	}

	public byte getFeaturedAd() {
		return this.featuredAd;
	}

	public void setFeaturedAd(byte featuredAd) {
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

	public byte getIsInternational() {
		return this.isInternational;
	}

	public void setIsInternational(byte isInternational) {
		this.isInternational = isInternational;
	}

	public byte getIsNational() {
		return this.isNational;
	}

	public void setIsNational(byte isNational) {
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

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public String getPracticeSetting() {
		return this.practiceSetting;
	}

	public void setPracticeSetting(String practiceSetting) {
		this.practiceSetting = practiceSetting;
	}

	public int getPublicationId() {
		return this.publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public String getSkills() {
		return this.skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getTrackingPixel() {
		return this.trackingPixel;
	}

	public void setTrackingPixel(String trackingPixel) {
		this.trackingPixel = trackingPixel;
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

	public List<AdmSaveJob> getAdmSaveJobs() {
		return this.admSaveJobs;
	}

	public void setAdmSaveJobs(List<AdmSaveJob> admSaveJobs) {
		this.admSaveJobs = admSaveJobs;
	}
	
	public JpTemplate getJpTemplate() {
		return this.jpTemplate;
	}

	public void setJpTemplate(JpTemplate jpTemplate) {
		this.jpTemplate = jpTemplate;
	}
	
	public JpSource getJpSource() {
		return this.jpSource;
	}

	public void setJpSource(JpSource jpSource) {
		this.jpSource = jpSource;
	}
	
	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
	
	public JpJobType getJpJobType() {
		return this.jpJobType;
	}

	public void setJpJobType(JpJobType jpJobType) {
		this.jpJobType = jpJobType;
	}
	
	public List<JpJobAddon> getJpJobAddons() {
		return this.jpJobAddons;
	}

	public void setJpJobAddons(List<JpJobAddon> jpJobAddons) {
		this.jpJobAddons = jpJobAddons;
	}
	
	public List<JpJobApply> getJpJobApplies() {
		return this.jpJobApplies;
	}

	public void setJpJobApplies(List<JpJobApply> jpJobApplies) {
		this.jpJobApplies = jpJobApplies;
	}
	
	public List<JpJobLocation> getJpJobLocations() {
		return this.jpJobLocations;
	}

	public void setJpJobLocations(List<JpJobLocation> jpJobLocations) {
		this.jpJobLocations = jpJobLocations;
	}
	
	public JpJobStat getJpJobStat() {
		return this.jpJobStat;
	}

	public void setJpJobStat(JpJobStat jpJobStat) {
		this.jpJobStat = jpJobStat;
	}
	
	public List<ResGuestApply> getResGuestApplies() {
		return this.resGuestApplies;
	}

	public void setResGuestApplies(List<ResGuestApply> resGuestApplies) {
		this.resGuestApplies = resGuestApplies;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	
}