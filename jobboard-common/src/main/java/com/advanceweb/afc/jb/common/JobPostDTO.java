package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 19, 2012
   @Purpose: This class will act as a DTO for post new job
 */
public class JobPostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int jobId;
	private String jobOwner;
	private String customerNo;
	private String companyName;
	private String disCompanyName;
	private boolean bHideCompName;
	
	private String jobPostingType;
	private String applicationMethod;
	
	private String jobNumber;
	private String jobTitle;
	
	private String applyEmail;
	private String applyUrl;
	private String atsUrl;
	
	private String jobCity;
	private String jobState;
	private String jobCountry;
	private String jobZip;
	private boolean bHideCity;
	private boolean bHideState;
	private boolean bHideCountry;
	private boolean bHideZipCode;
	
	private String employmentType;
	private boolean bFeatured;
	
	private String reqSkills;
	private String jobDesc;
	private String trackPixel;
	private int brandTemplate;
	private boolean bTemplateOverride;
	
	private boolean autoRenew;
	private String startDt;
	private String endDt;
	private String jobStatus;
	private String location;
	
	private String scheduleStartDt;
	private String scheduleExpiryDt;
	private long applies;
	private long clicks;
	private long views;
	private int facilityId;
	private int mainFacilityId;

	private String selectedRow;
    private boolean readOnly= false;
    private boolean xmlStartEndDateEnabled;
    
    private int userId;
    
    private boolean bActive;
    private String templateName;
    private String autoRenewVal;
    private String emailId;
   /* private String brandTemplateVal;
    private String autoRenewVal="No";*/
	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobOwner() {
		return jobOwner;
	}

	public void setJobOwner(String jobOwner) {
		this.jobOwner = jobOwner;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDisCompanyName() {
		return disCompanyName;
	}

	public void setDisCompanyName(String disCompanyName) {
		this.disCompanyName = disCompanyName;
	}

	public String getJobPostingType() {
		return jobPostingType;
	}

	public void setJobPostingType(String jobPostingType) {
		this.jobPostingType = jobPostingType;
	}

	public String getApplicationMethod() {
		return applicationMethod;
	}

	public void setApplicationMethod(String applicationMethod) {
		this.applicationMethod = applicationMethod;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getApplyEmail() {
		return applyEmail;
	}

	public void setApplyEmail(String applyEmail) {
		this.applyEmail = applyEmail;
	}

	public String getApplyUrl() {
		return applyUrl;
	}

	public void setApplyUrl(String applyUrl) {
		this.applyUrl = applyUrl;
	}

	public String getAtsUrl() {
		return atsUrl;
	}

	public void setAtsUrl(String atsUrl) {
		this.atsUrl = atsUrl;
	}

	public String getJobCity() {
		return jobCity;
	}

	public void setJobCity(String jobCity) {
		this.jobCity = jobCity;
	}
	
	public boolean isbHideCompName() {
		return bHideCompName;
	}

	public void setbHideCompName(boolean bHideCompName) {
		this.bHideCompName = bHideCompName;
	}

	public String getJobState() {
		return jobState;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	public boolean isbHideCity() {
		return bHideCity;
	}

	public void setbHideCity(boolean bHideCity) {
		this.bHideCity = bHideCity;
	}

	public boolean isbHideState() {
		return bHideState;
	}

	public void setbHideState(boolean bHideState) {
		this.bHideState = bHideState;
	}

	public boolean isbHideCountry() {
		return bHideCountry;
	}

	public void setbHideCountry(boolean bHideCountry) {
		this.bHideCountry = bHideCountry;
	}

	public boolean isbHideZipCode() {
		return bHideZipCode;
	}

	public void setbHideZipCode(boolean bHideZipCode) {
		this.bHideZipCode = bHideZipCode;
	}

	public String getJobCountry() {
		return jobCountry;
	}

	public void setJobCountry(String jobCountry) {
		this.jobCountry = jobCountry;
	}

	public String getJobZip() {
		return jobZip;
	}

	public void setJobZip(String jobZip) {
		this.jobZip = jobZip;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getReqSkills() {
		return reqSkills;
	}

	public void setReqSkills(String reqSkills) {
		this.reqSkills = reqSkills;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getTrackPixel() {
		return trackPixel;
	}

	public void setTrackPixel(String trackPixel) {
		this.trackPixel = trackPixel;
	}

	public boolean isAutoRenew() {
		return autoRenew;
	}

	public void setAutoRenew(boolean autoRenew) {
		this.autoRenew = autoRenew;
	}

	public int getBrandTemplate() {
		return brandTemplate;
	}

	public void setBrandTemplate(int brandTemplate) {
		this.brandTemplate = brandTemplate;
	}

	public boolean isbTemplateOverride() {
		return bTemplateOverride;
	}

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

	public String getScheduleStartDt() {
		return scheduleStartDt;
	}

	public void setScheduleStartDt(String scheduleStartDt) {
		this.scheduleStartDt = scheduleStartDt;
	}

	public String getScheduleExpiryDt() {
		return scheduleExpiryDt;
	}

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

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public boolean isbActive() {
		return bActive;
	}

	public void setbActive(boolean bActive) {
		this.bActive = bActive;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isbFeatured() {
		return bFeatured;
	}

	public void setbFeatured(boolean bFeatured) {
		this.bFeatured = bFeatured;
	}

	public boolean isXmlStartEndDateEnabled() {
		return xmlStartEndDateEnabled;
	}

	public void setXmlStartEndDateEnabled(boolean xmlStartEndDateEnabled) {
		this.xmlStartEndDateEnabled = xmlStartEndDateEnabled;
	}

	public int getMainFacilityId() {
		return mainFacilityId;
	}

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

		
}
