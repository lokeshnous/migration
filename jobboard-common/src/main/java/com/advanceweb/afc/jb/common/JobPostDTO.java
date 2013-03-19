/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 19, 2012
   @Purpose: This class will act as a DTO for post new job
 */
public class JobPostDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The job id. */
	private int jobId;
	
	/** The job owner. */
	private String jobOwner;
	
	/** The customer no. */
	private String customerNo;
	
	/** The company name. */
	private String companyName;
	
	/** The dis company name. */
	private String disCompanyName;
	
	/** The b hide comp name. */
	private boolean bHideCompName;
	
	/** The job posting type. */
	private String jobPostingType;
	
	/** The application method. */
	private String applicationMethod;
	
	/** The job number. */
	private String jobNumber;
	
	/** The job title. */
	private String jobTitle;
	
	/** The encoded job title. */
	private String encodedJobTitle;
	
	/** The apply email. */
	private String applyEmail;
	
	/** The apply url. */
	private String applyUrl;
	
	/** The ats url. */
	private String atsUrl;
	
	/** The job city. */
	private String jobCity;
	
	/** The job state. */
	private String jobState;
	
	/** The job country. */
	private String jobCountry;
	
	/** The job zip. */
	private String jobZip;
	
	/** The b hide city. */
	private boolean bHideCity;
	
	/** The b hide state. */
	private boolean bHideState;
	
	/** The b hide country. */
	private boolean bHideCountry;
	
	/** The b hide zip code. */
	private boolean bHideZipCode;
	
	/** The employment type. */
	private String employmentType;
	
	/** The b featured. */
	private boolean bFeatured;
	
	/** The req skills. */
	private String reqSkills;
	
	/** The job desc. */
	private String jobDesc;
	
	/** The track pixel. */
	private String trackPixel;
	
	/** The brand template. */
	private int brandTemplate;
	
	/** The b template override. */
	private boolean bTemplateOverride;
	
	/** The auto renew. */
	private boolean autoRenew;
	
	/** The start dt. */
	private String startDt;
	
	/** The end dt. */
	private String endDt;
	
	/** The job status. */
	private String jobStatus;
	
	/** The location. */
	private String location;
	
	/** The schedule start dt. */
	private String scheduleStartDt;
	
	/** The schedule expiry dt. */
	private String scheduleExpiryDt;
	
	/** The applies. */
	private long applies;
	
	/** The clicks. */
	private long clicks;
	
	/** The views. */
	private long views;
	
	/** The facility id. */
	private int facilityId;
	
	/** The main facility id. */
	private int mainFacilityId;

	/** The selected row. */
	private String selectedRow;
    
    /** The read only. */
    private boolean readOnly= false;
    
    /** The xml start end date enabled. */
    private boolean xmlStartEndDateEnabled;
    
    /** The user id. */
    private int userId;
    
    /** The b active. */
    private boolean bActive;
    
    /** The template name. */
    private String templateName;
    
    /** The auto renew val. */
    private String autoRenewVal;
    
    /** The email id. */
    private String emailId;
    
	/** The Source ID. */
	private int sourceId;
	
   /* private String brandTemplateVal;
    private String autoRenewVal="No";*/
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
	 * Gets the job owner.
	 *
	 * @return the job owner
	 */
	public String getJobOwner() {
		return jobOwner;
	}

	/**
	 * Sets the job owner.
	 *
	 * @param jobOwner the new job owner
	 */
	public void setJobOwner(String jobOwner) {
		this.jobOwner = jobOwner;
	}

	/**
	 * Gets the customer no.
	 *
	 * @return the customer no
	 */
	public String getCustomerNo() {
		return customerNo;
	}

	/**
	 * Sets the customer no.
	 *
	 * @param customerNo the new customer no
	 */
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the dis company name.
	 *
	 * @return the dis company name
	 */
	public String getDisCompanyName() {
		return disCompanyName;
	}

	/**
	 * Sets the dis company name.
	 *
	 * @param disCompanyName the new dis company name
	 */
	public void setDisCompanyName(String disCompanyName) {
		this.disCompanyName = disCompanyName;
	}

	/**
	 * Gets the job posting type.
	 *
	 * @return the job posting type
	 */
	public String getJobPostingType() {
		return jobPostingType;
	}

	/**
	 * Sets the job posting type.
	 *
	 * @param jobPostingType the new job posting type
	 */
	public void setJobPostingType(String jobPostingType) {
		this.jobPostingType = jobPostingType;
	}

	/**
	 * Gets the application method.
	 *
	 * @return the application method
	 */
	public String getApplicationMethod() {
		return applicationMethod;
	}

	/**
	 * Sets the application method.
	 *
	 * @param applicationMethod the new application method
	 */
	public void setApplicationMethod(String applicationMethod) {
		this.applicationMethod = applicationMethod;
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
	 * Gets the apply email.
	 *
	 * @return the apply email
	 */
	public String getApplyEmail() {
		return applyEmail;
	}

	/**
	 * Sets the apply email.
	 *
	 * @param applyEmail the new apply email
	 */
	public void setApplyEmail(String applyEmail) {
		this.applyEmail = applyEmail;
	}

	/**
	 * Gets the apply url.
	 *
	 * @return the apply url
	 */
	public String getApplyUrl() {
		return applyUrl;
	}

	/**
	 * Sets the apply url.
	 *
	 * @param applyUrl the new apply url
	 */
	public void setApplyUrl(String applyUrl) {
		this.applyUrl = applyUrl;
	}

	/**
	 * Gets the ats url.
	 *
	 * @return the ats url
	 */
	public String getAtsUrl() {
		return atsUrl;
	}

	/**
	 * Sets the ats url.
	 *
	 * @param atsUrl the new ats url
	 */
	public void setAtsUrl(String atsUrl) {
		this.atsUrl = atsUrl;
	}

	/**
	 * Gets the job city.
	 *
	 * @return the job city
	 */
	public String getJobCity() {
		return jobCity;
	}

	/**
	 * Sets the job city.
	 *
	 * @param jobCity the new job city
	 */
	public void setJobCity(String jobCity) {
		this.jobCity = jobCity;
	}
	
	/**
	 * Checks if is b hide comp name.
	 *
	 * @return true, if is b hide comp name
	 */
	public boolean isbHideCompName() {
		return bHideCompName;
	}

	/**
	 * Sets the b hide comp name.
	 *
	 * @param bHideCompName the new b hide comp name
	 */
	public void setbHideCompName(boolean bHideCompName) {
		this.bHideCompName = bHideCompName;
	}

	/**
	 * Gets the job state.
	 *
	 * @return the job state
	 */
	public String getJobState() {
		return jobState;
	}

	/**
	 * Sets the job state.
	 *
	 * @param jobState the new job state
	 */
	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	/**
	 * Checks if is b hide city.
	 *
	 * @return true, if is b hide city
	 */
	public boolean isbHideCity() {
		return bHideCity;
	}

	/**
	 * Sets the b hide city.
	 *
	 * @param bHideCity the new b hide city
	 */
	public void setbHideCity(boolean bHideCity) {
		this.bHideCity = bHideCity;
	}

	/**
	 * Checks if is b hide state.
	 *
	 * @return true, if is b hide state
	 */
	public boolean isbHideState() {
		return bHideState;
	}

	/**
	 * Sets the b hide state.
	 *
	 * @param bHideState the new b hide state
	 */
	public void setbHideState(boolean bHideState) {
		this.bHideState = bHideState;
	}

	/**
	 * Checks if is b hide country.
	 *
	 * @return true, if is b hide country
	 */
	public boolean isbHideCountry() {
		return bHideCountry;
	}

	/**
	 * Sets the b hide country.
	 *
	 * @param bHideCountry the new b hide country
	 */
	public void setbHideCountry(boolean bHideCountry) {
		this.bHideCountry = bHideCountry;
	}

	/**
	 * Checks if is b hide zip code.
	 *
	 * @return true, if is b hide zip code
	 */
	public boolean isbHideZipCode() {
		return bHideZipCode;
	}

	/**
	 * Sets the b hide zip code.
	 *
	 * @param bHideZipCode the new b hide zip code
	 */
	public void setbHideZipCode(boolean bHideZipCode) {
		this.bHideZipCode = bHideZipCode;
	}

	/**
	 * Gets the job country.
	 *
	 * @return the job country
	 */
	public String getJobCountry() {
		return jobCountry;
	}

	/**
	 * Sets the job country.
	 *
	 * @param jobCountry the new job country
	 */
	public void setJobCountry(String jobCountry) {
		this.jobCountry = jobCountry;
	}

	/**
	 * Gets the job zip.
	 *
	 * @return the job zip
	 */
	public String getJobZip() {
		return jobZip;
	}

	/**
	 * Sets the job zip.
	 *
	 * @param jobZip the new job zip
	 */
	public void setJobZip(String jobZip) {
		this.jobZip = jobZip;
	}

	/**
	 * Gets the employment type.
	 *
	 * @return the employment type
	 */
	public String getEmploymentType() {
		return employmentType;
	}

	/**
	 * Sets the employment type.
	 *
	 * @param employmentType the new employment type
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	/**
	 * Gets the req skills.
	 *
	 * @return the req skills
	 */
	public String getReqSkills() {
		return reqSkills;
	}

	/**
	 * Sets the req skills.
	 *
	 * @param reqSkills the new req skills
	 */
	public void setReqSkills(String reqSkills) {
		this.reqSkills = reqSkills;
	}

	/**
	 * Gets the job desc.
	 *
	 * @return the job desc
	 */
	public String getJobDesc() {
		return jobDesc;
	}

	/**
	 * Sets the job desc.
	 *
	 * @param jobDesc the new job desc
	 */
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	/**
	 * Gets the track pixel.
	 *
	 * @return the track pixel
	 */
	public String getTrackPixel() {
		return trackPixel;
	}

	/**
	 * Sets the track pixel.
	 *
	 * @param trackPixel the new track pixel
	 */
	public void setTrackPixel(String trackPixel) {
		this.trackPixel = trackPixel;
	}

	/**
	 * Checks if is auto renew.
	 *
	 * @return true, if is auto renew
	 */
	public boolean isAutoRenew() {
		return autoRenew;
	}

	/**
	 * Sets the auto renew.
	 *
	 * @param autoRenew the new auto renew
	 */
	public void setAutoRenew(boolean autoRenew) {
		this.autoRenew = autoRenew;
	}

	/**
	 * Gets the brand template.
	 *
	 * @return the brand template
	 */
	public int getBrandTemplate() {
		return brandTemplate;
	}

	/**
	 * Sets the brand template.
	 *
	 * @param brandTemplate the new brand template
	 */
	public void setBrandTemplate(int brandTemplate) {
		this.brandTemplate = brandTemplate;
	}

	/**
	 * Checks if is b template override.
	 *
	 * @return true, if is b template override
	 */
	public boolean isbTemplateOverride() {
		return bTemplateOverride;
	}

	/**
	 * Sets the b template override.
	 *
	 * @param bTemplateOverride the new b template override
	 */
	public void setbTemplateOverride(boolean bTemplateOverride) {
		this.bTemplateOverride = bTemplateOverride;
	}

	/**
	 * @return the startDt
	 */
	public String getStartDt() {
		return startDt;
	}

	/**
	 * @param startDt the startDt to set
	 */
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	/**
	 * @return the endDt
	 */
	public String getEndDt() {
		return endDt;
	}

	/**
	 * @param endDt the endDt to set
	 */
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	/**
	 * @return the jobStatus
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * @param jobStatus the jobStatus to set
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the schedule start dt.
	 *
	 * @return the schedule start dt
	 */
	public String getScheduleStartDt() {
		return scheduleStartDt;
	}

	/**
	 * Sets the schedule start dt.
	 *
	 * @param scheduleStartDt the new schedule start dt
	 */
	public void setScheduleStartDt(String scheduleStartDt) {
		this.scheduleStartDt = scheduleStartDt;
	}

	/**
	 * Gets the schedule expiry dt.
	 *
	 * @return the schedule expiry dt
	 */
	public String getScheduleExpiryDt() {
		return scheduleExpiryDt;
	}

	/**
	 * Sets the schedule expiry dt.
	 *
	 * @param scheduleExpiryDt the new schedule expiry dt
	 */
	public void setScheduleExpiryDt(String scheduleExpiryDt) {
		this.scheduleExpiryDt = scheduleExpiryDt;
	}

	/**
	 * @return the applies
	 */
	public long getApplies() {
		return applies;
	}

	/**
	 * @param applies the applies to set
	 */
	public void setApplies(long applies) {
		this.applies = applies;
	}

	/**
	 * @return the clicks
	 */
	public long getClicks() {
		return clicks;
	}

	/**
	 * @param clicks the clicks to set
	 */
	public void setClicks(long clicks) {
		this.clicks = clicks;
	}

	/**
	 * @return the views
	 */
	public long getViews() {
		return views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(long views) {
		this.views = views;
	}

	/**
	 * @return the selectedRow
	 */
	public String getSelectedRow() {
		return selectedRow;
	}

	/**
	 * @param selectedRow the selectedRow to set
	 */
	public void setSelectedRow(String selectedRow) {
		this.selectedRow = selectedRow;
	}

	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
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
	 * Checks if is b active.
	 *
	 * @return true, if is b active
	 */
	public boolean isbActive() {
		return bActive;
	}

	/**
	 * Sets the b active.
	 *
	 * @param bActive the new b active
	 */
	public void setbActive(boolean bActive) {
		this.bActive = bActive;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Checks if is b featured.
	 *
	 * @return true, if is b featured
	 */
	public boolean isbFeatured() {
		return bFeatured;
	}

	/**
	 * Sets the b featured.
	 *
	 * @param bFeatured the new b featured
	 */
	public void setbFeatured(boolean bFeatured) {
		this.bFeatured = bFeatured;
	}

	/**
	 * Checks if is xml start end date enabled.
	 *
	 * @return true, if is xml start end date enabled
	 */
	public boolean isXmlStartEndDateEnabled() {
		return xmlStartEndDateEnabled;
	}

	/**
	 * Sets the xml start end date enabled.
	 *
	 * @param xmlStartEndDateEnabled the new xml start end date enabled
	 */
	public void setXmlStartEndDateEnabled(boolean xmlStartEndDateEnabled) {
		this.xmlStartEndDateEnabled = xmlStartEndDateEnabled;
	}

	/**
	 * Gets the main facility id.
	 *
	 * @return the main facility id
	 */
	public int getMainFacilityId() {
		return mainFacilityId;
	}

	/**
	 * Sets the main facility id.
	 *
	 * @param mainFacilityId the new main facility id
	 */
	public void setMainFacilityId(int mainFacilityId) {
		this.mainFacilityId = mainFacilityId;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the autoRenewVal
	 */
	public String getAutoRenewVal() {
		return autoRenewVal;
	}

	/**
	 * @param autoRenewVal the autoRenewVal to set
	 */
	public void setAutoRenewVal(String autoRenewVal) {
		this.autoRenewVal = autoRenewVal;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

/*	*//**
	 * @return the brandTemplateVal
	 *//*
	public String getBrandTemplateVal() {
		return brandTemplateVal;
	}

	*//**
	 * @param brandTemplateVal the brandTemplateVal to set
	 *//*
	public void setBrandTemplateVal(String brandTemplateVal) {
		this.brandTemplateVal = brandTemplateVal;
	}

	*//**
	 * @return the autoRenewVal
	 *//*
	public String isAutoRenewVal() {
		return autoRenewVal;
	}

	*//**
	 * @param autoRenewVal the autoRenewVal to set
	 *//*
	public void setAutoRenewVal(String autoRenewVal) {
		this.autoRenewVal = autoRenewVal;
	}*/
	/**
	 * @return the encodedJobTitle
	 */
	public String getEncodedJobTitle() {
		return encodedJobTitle;
	}

	/**
	 * @param encodedJobTitle the encodedJobTitle to set
	 */
	public void setEncodedJobTitle(String encodedJobTitle) {
		this.encodedJobTitle = encodedJobTitle;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
		
}
