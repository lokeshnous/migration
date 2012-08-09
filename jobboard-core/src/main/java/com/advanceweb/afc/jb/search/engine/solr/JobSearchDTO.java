package com.advanceweb.afc.jb.search.engine.solr;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 10th July 2012 onwards
 */

public class JobSearchDTO implements Serializable{
	
	private static final long serialVersionUID = -2200423309708244407L;
	
	
	@Field("adtext")
	private String adText;
	
	@Field("apply_online")
	private int applyOnline;
	
	@Field("blind_ad")
	private int blindAd;
	
	@Field
	private String city;
	
	@Field
	private String company;
	
	@Field
	private String email;
	
	@Field("email_display")
	private String emailDisplay;
	
	@Field("facility_name")
	private String facilityName;
	
	@Field
	private boolean featured;
	
	@Field("is_international")
	private boolean internationalJob;
	
	@Field("is_national")
	private boolean nationalJob;
	
	@Field("job_count")
	private int jobCount;
	
	@Field("job_id")
	private String jobId;
	
	@Field("job_number")
	private String jobNumber;
	
	@Field("jobgeo")
	private String jobGeo;
	
	@Field("jobposition")
	private String jobPosition;
	
	@Field("jobtitle")
	private String jobTitle;
	
	@Field("postcode")
	private String postCode;
	
	@Field("posted_dt")
	private Date postedDate;
	
	@Field("state")
	private String state;
	
	@Field("url")
	private String url;
	
	@Field("url_display")
	private String urlDisplay;
	
	@Field("jobgeo_0_latlon")
	private String jobGeo0LatLon;
	
	@Field("jobgeo_1_latlon")
	private String jobGeo1LatLon;

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
