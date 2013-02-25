/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 10th Aug 2012 onwards
 */

public class JobDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ad text. */
	private String adText;
	
	/** The apply online. */
	private int applyOnline;
	
	/** The blind ad. */
	private int blindAd;
	
	/** The city. */
	private String city;
	
	/** The company. */
	private String company;
	
	/** The company name disp. */
	private String companyNameDisp;
	
	/** The email. */
	private String email;
	
	/** The email display. */
	private String emailDisplay;
	
	/** The facility name. */
	private String facilityName;
	
	/** The facility id. */
	private int facilityId;

	/** The featured. */
	private boolean featured;
	
	/** The international job. */
	private boolean internationalJob;
	
	/** The national job. */
	private boolean nationalJob;
	
	/** The job count. */
	private int jobCount;
	
	/** The job id. */
	private int jobId;
	
	/** The job number. */
	private String jobNumber;
	
	/** The job geo. */
	private String jobGeo;
	
	/** The job position. */
	private String jobPosition;
	
	/** The job title. */
	private String jobTitle;
	
	/** The job title encode. */
	private String jobTitleEncode;

	/** The post code. */
	private String postCode;
	
	/** The posted date. */
	private Date postedDate;
	
	/** The state. */
	private String state;
	
	/** The url. */
	private String url;
	
	/** The url display. */
	private String urlDisplay;
	
	/** The job geo0 lat lon. */
	private String jobGeo0LatLon;
	
	/** The job geo1 lat lon. */
	private String jobGeo1LatLon;
	
	/** The tracking pixel. */
	private String trackingPixel;
	
	//New fields

	/** The template id. */
	private int templateId;

	/** The package name. */
	private String packageName;
	
	/** The is premium. */
	private int isPremium;
	
	/** The universal geo. */
	private boolean universalGeo;
	
	/** The hide city. */
	private int hideCity;
	
	/** The hide state. */
	private int hideState;
	
	/** The hide postcode. */
	private int hidePostcode;
	
	/** The hide country. */
	private int hideCountry;
	
	/** The country. */
	private String country;
	
	/** The color. */
	private String color;
	
	/** The is silver customer. */
	private Boolean isSilverCustomer;
	
	/** The package id. */
	private int packageId;
	
	/** The list testimony. */
	private List<TestimonyDTO> listTestimony;
	
	/** The list add images. */
	private List<AddImageDTO> listAddImages;
	
	/** The list videos. */
	private List<VideoDTO> listVideos;
	
	/** The company overview. */
	private String companyOverview;
	
	/** The logo. */
	private String logo;
	
	/** The image path. */
	private String imagePath;
	
	/** The created date. */
	private Date createdDate;
	
	/** The user id. */
	private int userID;
	
	/** The count. */
	private int count;
	
	/** The position type. */
	private String positionType;
	
	/** The position level. */
	private String positionLevel;
	
	/** The head line. */
	private String headLine;
	
	/** The company url. */
	private String companyUrl;	
	
	/**
	 * Gets the position type.
	 *
	 * @return the position type
	 */
	public String getPositionType() {
		return positionType;
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
	 * Gets the position level.
	 *
	 * @return the position level
	 */
	public String getPositionLevel() {
		return positionLevel;
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
	 * Gets the head line.
	 *
	 * @return the head line
	 */
	public String getHeadLine() {
		return headLine;
	}

	/**
	 * Sets the head line.
	 *
	 * @param headLine the new head line
	 */
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}

	/**
	 * Gets the company url.
	 *
	 * @return the company url
	 */
	public String getCompanyUrl() {
		return companyUrl;
	}

	/**
	 * Sets the company url.
	 *
	 * @param companyUrl the new company url
	 */
	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
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

	/**
	 * Gets the company name disp.
	 *
	 * @return the company name disp
	 */
	public String getCompanyNameDisp() {
		return companyNameDisp;
	}

	/**
	 * Sets the company name disp.
	 *
	 * @param companyNameDisp the new company name disp
	 */
	public void setCompanyNameDisp(String companyNameDisp) {
		this.companyNameDisp = companyNameDisp;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the checks if is silver customer.
	 *
	 * @return the checks if is silver customer
	 */
	public Boolean getIsSilverCustomer() {
		return isSilverCustomer;
	}

	/**
	 * Sets the checks if is silver customer.
	 *
	 * @param isSilverCustomer the new checks if is silver customer
	 */
	public void setIsSilverCustomer(Boolean isSilverCustomer) {
		this.isSilverCustomer = isSilverCustomer;
	}

	/**
	 * Gets the package id.
	 *
	 * @return the package id
	 */
	public int getPackageId() {
		return packageId;
	}

	/**
	 * Sets the package id.
	 *
	 * @param packageId the new package id
	 */
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	/**
	 * Gets the list testimony.
	 *
	 * @return the list testimony
	 */
	public List<TestimonyDTO> getListTestimony() {
		return listTestimony;
	}

	/**
	 * Sets the list testimony.
	 *
	 * @param listTestimony the new list testimony
	 */
	public void setListTestimony(List<TestimonyDTO> listTestimony) {
		this.listTestimony = listTestimony;
	}

	/**
	 * Gets the list add images.
	 *
	 * @return the list add images
	 */
	public List<AddImageDTO> getListAddImages() {
		return listAddImages;
	}

	/**
	 * Sets the list add images.
	 *
	 * @param listAddImages the new list add images
	 */
	public void setListAddImages(List<AddImageDTO> listAddImages) {
		this.listAddImages = listAddImages;
	}

	/**
	 * Gets the list videos.
	 *
	 * @return the list videos
	 */
	public List<VideoDTO> getListVideos() {
		return listVideos;
	}

	/**
	 * Sets the list videos.
	 *
	 * @param listVideos the new list videos
	 */
	public void setListVideos(List<VideoDTO> listVideos) {
		this.listVideos = listVideos;
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
	 * Gets the logo.
	 *
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
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
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return imagePath;
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
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public int getJobId() {
		return jobId;
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
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userID the new user id
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Gets the job title encode.
	 *
	 * @return the job title encode
	 */
	public String getJobTitleEncode() {
		return jobTitleEncode;
	}

	/**
	 * Sets the job title encode.
	 *
	 * @param jobTitleEncode the new job title encode
	 */
	public void setJobTitleEncode(String jobTitleEncode) {
		this.jobTitleEncode = jobTitleEncode;
	}
	
	/**
	 * @return the trackingPixel
	 */
	public String getTrackingPixel() {
		return trackingPixel;
	}

	/**
	 * @param trackingPixel the trackingPixel to set
	 */
	public void setTrackingPixel(String trackingPixel) {
		this.trackingPixel = trackingPixel;
	}

}
