package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 10th Aug 2012 onwards
 */

public class JobDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private String adText;
	
	private int applyOnline;
	
	private int blindAd;
	
	private String city;
	
	private String company;
	
	private String email;
	
	private String emailDisplay;
	
	private String facilityName;
	
	private boolean featured;
	
	private boolean internationalJob;
	
	private boolean nationalJob;
	
	private int jobCount;
	
	private String jobId;
	
	private String jobNumber;
	
	private String jobGeo;
	
	private String jobPosition;
	
	private String jobTitle;
	
	private String postCode;
	
	private Date postedDate;
	
	private String state;
	
	private String url;
	
	private String urlDisplay;
	
	private String jobGeo0LatLon;
	
	private String jobGeo1LatLon;
	
	//New fields
	
	private int templateId;

	private String packageName;
	
	private int isPremium;
	
	private boolean universalGeo;
	
	private int hideCity;
	
	private int hideState;
	
	private int hidePostcode;
	
	private int hideCountry;
	
	private String country;
	

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(int isPremium) {
		this.isPremium = isPremium;
	}

	public boolean isUniversalGeo() {
		return universalGeo;
	}

	public void setUniversalGeo(boolean isUniversalGeo) {
		this.universalGeo = isUniversalGeo;
	}

	public int getHideCity() {
		return hideCity;
	}

	public void setHideCity(int hideCity) {
		this.hideCity = hideCity;
	}

	public int getHideState() {
		return hideState;
	}

	public void setHideState(int hideState) {
		this.hideState = hideState;
	}

	public int getHidePostcode() {
		return hidePostcode;
	}

	public void setHidePostcode(int hidePostcode) {
		this.hidePostcode = hidePostcode;
	}

	public int getHideCountry() {
		return hideCountry;
	}

	public void setHideCountry(int hideCountry) {
		this.hideCountry = hideCountry;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAdText() {
		return adText;
	}

	public void setAdText(String adText) {
		this.adText = adText;
	}

	public int getApplyOnline() {
		return applyOnline;
	}

	public void setApplyOnline(int applyOnline) {
		this.applyOnline = applyOnline;
	}

	public int getBlindAd() {
		return blindAd;
	}

	public void setBlindAd(int blindAd) {
		this.blindAd = blindAd;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailDisplay() {
		return emailDisplay;
	}

	public void setEmailDisplay(String emailDisplay) {
		this.emailDisplay = emailDisplay;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public boolean isInternationalJob() {
		return internationalJob;
	}

	public void setInternationalJob(boolean internationalJob) {
		this.internationalJob = internationalJob;
	}

	public boolean isNationalJob() {
		return nationalJob;
	}

	public void setNationalJob(boolean nationalJob) {
		this.nationalJob = nationalJob;
	}

	public int getJobCount() {
		return jobCount;
	}

	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getJobGeo() {
		return jobGeo;
	}

	public void setJobGeo(String jobGeo) {
		this.jobGeo = jobGeo;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlDisplay() {
		return urlDisplay;
	}

	public void setUrlDisplay(String urlDisplay) {
		this.urlDisplay = urlDisplay;
	}

	public String getJobGeo0LatLon() {
		return jobGeo0LatLon;
	}

	public void setJobGeo0LatLon(String jobGeo0LatLon) {
		this.jobGeo0LatLon = jobGeo0LatLon;
	}

	public String getJobGeo1LatLon() {
		return jobGeo1LatLon;
	}

	public void setJobGeo1LatLon(String jobGeo1LatLon) {
		this.jobGeo1LatLon = jobGeo1LatLon;
	}


}
