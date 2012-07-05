package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the jp_job database table.
 * 
 */
@Entity
@Table(name = "jp_job")
public class JpJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "account_num")
	private String accountNum;

	private byte active;

	// bi-directional many-to-one association to AdmFacility
	@ManyToOne
	@JoinColumn(name = "facility_id")
	private AdmFacility admFacility;

	@Column(name = "admin_user_id")
	private int adminUserId;

	@Lob()
	private String adtext;

	@Column(name = "apply_online")
	private int applyOnline;

	@Column(name = "blind_ad")
	private int blindAd;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Column(name = "create_user_id")
	private int createUserId;

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

	private byte featured;

	@Column(name = "featured_ad")
	private byte featuredAd;

	private String headline;

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "is_international")
	private byte isInternational;

	@Column(name = "is_national")
	private byte isNational;

	@Column(name = "job_count")
	private int jobCount;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "job_id", insertable = false, updatable = false)
	private int jobId;

	@Column(name = "job_number")
	private String jobNumber;

	private String jobtitle;

	// bi-directional many-to-one association to JpJobAddon
	@OneToMany(mappedBy = "jpJob")
	private List<JpJobAddon> jpJobAddons;

	// bi-directional many-to-one association to JpJobLocation
	@OneToMany(mappedBy = "jpJob")
	private List<JpJobLocation> jpJobLocations;

	// bi-directional many-to-one association to JpJobType
	@ManyToOne
	@JoinColumn(name = "job_type_id")
	private JpJobType jpJobType;

	// bi-directional many-to-one association to JpSource
	@ManyToOne
	@JoinColumn(name = "source_id")
	private JpSource jpSource;

	private String keywords;

	private String logo;

	private String name;

	@Column(name = "pdf_path")
	private String pdfPath;

	@Column(name = "position_level")
	private String positionLevel;

	@Column(name = "position_type")
	private String positionType;

	@Column(name = "practice_setting")
	private String practiceSetting;

	@Column(name = "publication_id")
	private int publicationId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_dt")
	private Date startDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_dt")
	private Date updateDt;

	private String url;

	@Column(name = "url_display")
	private String urlDisplay;

	public JpJob() {
	}

	public String getAccountNum() {
		return accountNum;
	}

	public byte getActive() {
		return active;
	}

	public AdmFacility getAdmFacility() {
		return admFacility;
	}

	public int getAdminUserId() {
		return adminUserId;
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

	public Timestamp getCreateDt() {
		return createDt;
	}

	public int getCreateUserId() {
		return createUserId;
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

	public byte getFeatured() {
		return featured;
	}

	public byte getFeaturedAd() {
		return featuredAd;
	}

	public String getHeadline() {
		return headline;
	}

	public String getImagePath() {
		return imagePath;
	}

	public byte getIsInternational() {
		return isInternational;
	}

	public byte getIsNational() {
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

	public List<JpJobAddon> getJpJobAddons() {
		return jpJobAddons;
	}

	public List<JpJobLocation> getJpJobLocations() {
		return jpJobLocations;
	}

	public JpJobType getJpJobType() {
		return jpJobType;
	}

	public JpSource getJpSource() {
		return jpSource;
	}

	public String getKeywords() {
		return keywords;
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

	public String getPositionType() {
		return positionType;
	}

	public String getPracticeSetting() {
		return practiceSetting;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public Date getStartDt() {
		return startDt;
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

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
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

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
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

	public void setFeatured(byte featured) {
		this.featured = featured;
	}

	public void setFeaturedAd(byte featuredAd) {
		this.featuredAd = featuredAd;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setIsInternational(byte isInternational) {
		this.isInternational = isInternational;
	}

	public void setIsNational(byte isNational) {
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

	public void setJpJobAddons(List<JpJobAddon> jpJobAddons) {
		this.jpJobAddons = jpJobAddons;
	}

	public void setJpJobLocations(List<JpJobLocation> jpJobLocations) {
		this.jpJobLocations = jpJobLocations;
	}

	public void setJpJobType(JpJobType jpJobType) {
		this.jpJobType = jpJobType;
	}

	public void setJpSource(JpSource jpSource) {
		this.jpSource = jpSource;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public void setPracticeSetting(String practiceSetting) {
		this.practiceSetting = practiceSetting;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
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