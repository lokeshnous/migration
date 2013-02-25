/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search.engine.solr;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 10th July 2012 onwards
 */

public class SolrJobDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2200423309708244407L;
	
	/** The ad text. */
	@Field("adtext")
	private String adText;
	
	/** The apply online. */
	@Field("apply_online")
	private int applyOnline;
	
	/** The blind ad. */
	@Field("blind_ad")
	private int blindAd;
	
	/** The city. */
	@Field
	private String city;
	
	/** The company. */
	@Field
	private String company;
	
	/** The email. */
	@Field
	private String email;
	
	/** The email display. */
	@Field("email_display")
	private String emailDisplay;
	
	/** The facility name. */
	@Field("facility_name")
	private String facilityName;
	
	/** The featured. */
	@Field
	private boolean featured;
	
	/** The international job. */
	@Field("is_international")
	private boolean internationalJob;
	
	/** The national job. */
	@Field("is_national")
	private boolean nationalJob;
	
	/** The job count. */
	@Field("job_count")
	private int jobCount;
	
	/** The job id. */
	@Field("job_id")
	private String jobId;
	
	/** The job number. */
	@Field("job_number")
	private String jobNumber;
	
	/** The job geo. */
	@Field("jobgeo")
	private String jobGeo;
	
	/** The job position. */
	@Field("jobposition")
	private String jobPosition;
	
	/** The job title. */
	@Field("jobtitle")
	private String jobTitle;
	
	/** The post code. */
	@Field("postcode")
	private String postCode;
	
	/** The posted date. */
	@Field("posted_dt")
	private Date postedDate;
	
	/** The state. */
	@Field("state")
	private String state;
	
	/** The url. */
	@Field("url")
	private String url;
	
	/** The url display. */
	@Field("url_display")
	private String urlDisplay;
	
	/** The job geo0 lat lon. */
	@Field("jobgeo_0_latlon")
	private String jobGeo0LatLon;
	
	/** The job geo1 lat lon. */
	@Field("jobgeo_1_latlon")
	private String jobGeo1LatLon;
	
	//New fields
	
	/** The template id. */
	@Field("template_id")
	private int templateId;

	/** The package name. */
	@Field("package_name")
	private String packageName;
	
	/** The is premium. */
	@Field("is_premium")
	private int isPremium;
	
	/** The universal geo. */
	@Field("is_universal_geo")
	private boolean universalGeo;
	
	/** The hide city. */
	@Field("hide_city")
	private int hideCity;
	
	/** The hide state. */
	@Field("hide_state")
	private int hideState;
	
	/** The hide postcode. */
	@Field("hide_postcode")
	private int hidePostcode;
	
	/** The hide country. */
	@Field("hide_country")
	private int hideCountry;
	
	/** The country. */
	@Field("country")
	private String country;
	
	/** The facility id. */
	@Field("facility_id")
	private int facilityId;

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
		this.templateId = templateId;
	}

	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Sets the package name.
	 *
	 * @param packageName the new package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * Gets the checks if is premium.
	 *
	 * @return the checks if is premium
	 */
	public int getIsPremium() {
		return isPremium;
	}

	/**
	 * Sets the checks if is premium.
	 *
	 * @param isPremium the new checks if is premium
	 */
	public void setIsPremium(int isPremium) {
		this.isPremium = isPremium;
	}

	/**
	 * Checks if is universal geo.
	 *
	 * @return true, if is universal geo
	 */
	public boolean isUniversalGeo() {
		return universalGeo;
	}

	/**
	 * Sets the universal geo.
	 *
	 * @param isUniversalGeo the new universal geo
	 */
	public void setUniversalGeo(boolean isUniversalGeo) {
		this.universalGeo = isUniversalGeo;
	}

	/**
	 * Gets the hide city.
	 *
	 * @return the hide city
	 */
	public int getHideCity() {
		return hideCity;
	}

	/**
	 * Sets the hide city.
	 *
	 * @param hideCity the new hide city
	 */
	public void setHideCity(int hideCity) {
		this.hideCity = hideCity;
	}

	/**
	 * Gets the hide state.
	 *
	 * @return the hide state
	 */
	public int getHideState() {
		return hideState;
	}

	/**
	 * Sets the hide state.
	 *
	 * @param hideState the new hide state
	 */
	public void setHideState(int hideState) {
		this.hideState = hideState;
	}

	/**
	 * Gets the hide postcode.
	 *
	 * @return the hide postcode
	 */
	public int getHidePostcode() {
		return hidePostcode;
	}

	/**
	 * Sets the hide postcode.
	 *
	 * @param hidePostcode the new hide postcode
	 */
	public void setHidePostcode(int hidePostcode) {
		this.hidePostcode = hidePostcode;
	}

	/**
	 * Gets the hide country.
	 *
	 * @return the hide country
	 */
	public int getHideCountry() {
		return hideCountry;
	}

	/**
	 * Sets the hide country.
	 *
	 * @param hideCountry the new hide country
	 */
	public void setHideCountry(int hideCountry) {
		this.hideCountry = hideCountry;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
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
	 * Gets the ad text.
	 *
	 * @return the ad text
	 */
	public String getAdText() {
		return adText;
	}

	/**
	 * Sets the ad text.
	 *
	 * @param adText the new ad text
	 */
	public void setAdText(String adText) {
		this.adText = adText;
	}

	/**
	 * Gets the apply online.
	 *
	 * @return the apply online
	 */
	public int getApplyOnline() {
		return applyOnline;
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
		return blindAd;
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
		return city;
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
	 * Gets the company.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
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
		return emailDisplay;
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
	 * Gets the facility name.
	 *
	 * @return the facility name
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * Sets the facility name.
	 *
	 * @param facilityName the new facility name
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * Checks if is featured.
	 *
	 * @return true, if is featured
	 */
	public boolean isFeatured() {
		return featured;
	}

	/**
	 * Sets the featured.
	 *
	 * @param featured the new featured
	 */
	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	/**
	 * Checks if is international job.
	 *
	 * @return true, if is international job
	 */
	public boolean isInternationalJob() {
		return internationalJob;
	}

	/**
	 * Sets the international job.
	 *
	 * @param internationalJob the new international job
	 */
	public void setInternationalJob(boolean internationalJob) {
		this.internationalJob = internationalJob;
	}

	/**
	 * Checks if is national job.
	 *
	 * @return true, if is national job
	 */
	public boolean isNationalJob() {
		return nationalJob;
	}

	/**
	 * Sets the national job.
	 *
	 * @param nationalJob the new national job
	 */
	public void setNationalJob(boolean nationalJob) {
		this.nationalJob = nationalJob;
	}

	/**
	 * Gets the job count.
	 *
	 * @return the job count
	 */
	public int getJobCount() {
		return jobCount;
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
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * Sets the job id.
	 *
	 * @param jobId the new job id
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * Gets the job number.
	 *
	 * @return the job number
	 */
	public String getJobNumber() {
		return jobNumber;
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
	 * Gets the job geo.
	 *
	 * @return the job geo
	 */
	public String getJobGeo() {
		return jobGeo;
	}

	/**
	 * Sets the job geo.
	 *
	 * @param jobGeo the new job geo
	 */
	public void setJobGeo(String jobGeo) {
		this.jobGeo = jobGeo;
	}

	/**
	 * Gets the job position.
	 *
	 * @return the job position
	 */
	public String getJobPosition() {
		return jobPosition;
	}

	/**
	 * Sets the job position.
	 *
	 * @param jobPosition the new job position
	 */
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	/**
	 * Gets the job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the new job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Gets the post code.
	 *
	 * @return the post code
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * Sets the post code.
	 *
	 * @param postCode the new post code
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * Gets the posted date.
	 *
	 * @return the posted date
	 */
	public Date getPostedDate() {

				return postedDate;
	}

	/**
	 * Sets the posted date.
	 *
	 * @param postedDate the new posted date
	 */
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
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
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
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
		return urlDisplay;
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
	 * Gets the job geo0 lat lon.
	 *
	 * @return the job geo0 lat lon
	 */
	public String getJobGeo0LatLon() {
		return jobGeo0LatLon;
	}

	/**
	 * Sets the job geo0 lat lon.
	 *
	 * @param jobGeo0LatLon the new job geo0 lat lon
	 */
	public void setJobGeo0LatLon(String jobGeo0LatLon) {
		this.jobGeo0LatLon = jobGeo0LatLon;
	}

	/**
	 * Gets the job geo1 lat lon.
	 *
	 * @return the job geo1 lat lon
	 */
	public String getJobGeo1LatLon() {
		return jobGeo1LatLon;
	}

	/**
	 * Sets the job geo1 lat lon.
	 *
	 * @param jobGeo1LatLon the new job geo1 lat lon
	 */
	public void setJobGeo1LatLon(String jobGeo1LatLon) {
		this.jobGeo1LatLon = jobGeo1LatLon;
	}

	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

}
