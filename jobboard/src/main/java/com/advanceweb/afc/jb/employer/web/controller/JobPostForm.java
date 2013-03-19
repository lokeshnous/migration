/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import com.advanceweb.afc.jb.common.JobPostDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will act as a Form Bean for the Post New Job 
 */
public class JobPostForm {

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
	
	/** The job number. */
	private String jobNumber;
	
	/** The job title. */
	private String jobTitle;
	
	/** The appl method. */
	private String applMethod;
	
	/** The apply email. */
	private String applyEmail;
	
	/** The apply url. */
	private String applyUrl;
	
	/** The ats url. */
	private String atsUrl;
	
	/** The job city. */
	private String jobCity;
	
	/** The b hide city. */
	private boolean bHideCity;
	
	/** The job state. */
	private String jobState;
	
	/** The b hide state. */
	private boolean bHideState;
	
	/** The job country. */
	private String jobCountry;
	
	/** The b hide country. */
	private boolean bHideCountry;
	
	/** The job zip code. */
	private String jobZipCode;
	
	/** The b hide zip code. */
	private boolean bHideZipCode;
	
	/** The employment type. */
	private String employmentType;
	
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

	/** The schedule start date. */
	private String scheduleStartDate;
	
	/** The schedule end date. */
	private String scheduleEndDate;
	
	/** The job status. */
	private String jobStatus;
	
	/** The selected row. */
	private String selectedRow;
    
    /** The read only. */
    private boolean readOnly= false;
    
    /** The job post dto list. */
    private List<JobPostDTO> jobPostDTOList;
    
    /** The status value. */
    private String statusValue;
    
    /** The no of page. */
    private int noOfPage;
    
    /** The begin val. */
    private int beginVal=0;
    
    /** The active or inactive. */
    private boolean activeOrInactive;
    
    /** The enable job title. */
    private boolean enableJobTitle;
    /*
    private String brandTemplateValue;
    private String autoRenewVal;*/
    
	/** The xml start end date enabled. */
    private boolean xmlStartEndDateEnabled;
    
    /** The admin login. */
    private boolean adminLogin;
    
    /** The sort asc. */
    private boolean sortAsc=true;
    
    /** The sort by. */
    private String sortBy="a.jobId";
    
    /** The facility id. */
    private int facilityId;
    
    /** The view page. */
    private boolean viewPage = false;
    
    /** The Source ID. */
	private int sourceId;
	
	/**
	 * Checks if is admin login.
	 *
	 * @return true, if is admin login
	 */
	public boolean isAdminLogin() {
		return adminLogin;
	}

	/**
	 * Sets the admin login.
	 *
	 * @param adminLogin the new admin login
	 */
	public void setAdminLogin(boolean adminLogin) {
		this.adminLogin = adminLogin;
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
	 * Gets the job zip code.
	 *
	 * @return the job zip code
	 */
	public String getJobZipCode() {
		return jobZipCode;
	}

	/**
	 * Sets the job zip code.
	 *
	 * @param jobZipCode the new job zip code
	 */
	public void setJobZipCode(String jobZipCode) {
		this.jobZipCode = jobZipCode;
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
	 * Gets the appl method.
	 *
	 * @return the appl method
	 */
	public String getApplMethod() {
		return applMethod;
	}

	/**
	 * Sets the appl method.
	 *
	 * @param applMethod the new appl method
	 */
	public void setApplMethod(String applMethod) {
		this.applMethod = applMethod;
	}

	/**
	 * Gets the schedule start date.
	 *
	 * @return the schedule start date
	 */
	public String getScheduleStartDate() {
		return scheduleStartDate;
	}

	/**
	 * Sets the schedule start date.
	 *
	 * @param scheduleStartDate the new schedule start date
	 */
	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	/**
	 * Gets the schedule end date.
	 *
	 * @return the schedule end date
	 */
	public String getScheduleEndDate() {
		return scheduleEndDate;
	}

	/**
	 * Sets the schedule end date.
	 *
	 * @param scheduleEndDate the new schedule end date
	 */
	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	/**
	 * Gets the job status.
	 *
	 * @return the job status
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * Sets the job status.
	 *
	 * @param jobStatus the new job status
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
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
	 * @return the jobPostDTOList
	 */
	public List<JobPostDTO> getJobPostDTOList() {
		return jobPostDTOList;
	}

	/**
	 * @param jobPostDTOList the jobPostDTOList to set
	 */
	public void setJobPostDTOList(List<JobPostDTO> jobPostDTOList) {
		this.jobPostDTOList = jobPostDTOList;
	}

	/**
	 * @return the statusValue
	 */
	public String getStatusValue() {
		return statusValue;
	}

	/**
	 * @param statusValue the statusValue to set
	 */
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	/**
	 * @return the noOfPage
	 */
	public int getNoOfPage() {
		return noOfPage;
	}

	/**
	 * @param noOfPage the noOfPage to set
	 */
	public void setNoOfPage(int noOfPage) {
		this.noOfPage = noOfPage;
	}

	/**
	 * Gets the begin val.
	 *
	 * @return the begin val
	 */
	public int getBeginVal() {
		return beginVal;
	}

	/**
	 * Sets the begin val.
	 *
	 * @param beginVal the new begin val
	 */
	public void setBeginVal(int beginVal) {
		this.beginVal = beginVal;
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
	/*public String getBrandTemplateValue() {
		return brandTemplateValue;
	}

	public void setBrandTemplateValue(String brandTemplateValue) {
		this.brandTemplateValue = brandTemplateValue;
	}

	public String getAutoRenewVal() {
		return autoRenewVal;
	}

	public void setAutoRenewVal(String autoRenewVal) {
		this.autoRenewVal = autoRenewVal;
	}*/

	/**
	 * Checks if is active or inactive.
	 *
	 * @return true, if is active or inactive
	 */
	public boolean isActiveOrInactive() {
		return activeOrInactive;
	}

	/**
	 * Sets the active or inactive.
	 *
	 * @param activeOrInactive the new active or inactive
	 */
	public void setActiveOrInactive(boolean activeOrInactive) {
		this.activeOrInactive = activeOrInactive;
	}
	
	/**
	 * Checks if is enable job title.
	 *
	 * @return true, if is enable job title
	 */
	public boolean isEnableJobTitle() {
		return enableJobTitle;
	}

	/**
	 * Sets the enable job title.
	 *
	 * @param enableJobTitle the new enable job title
	 */
	public void setEnableJobTitle(boolean enableJobTitle) {
		this.enableJobTitle = enableJobTitle;
	}

	/**
	 * @return the sortAsc
	 */
	public boolean isSortAsc() {
		return sortAsc;
	}

	/**
	 * @param sortAsc the sortAsc to set
	 */
	public void setSortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
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
	 * Checks if is view page.
	 *
	 * @return true, if is view page
	 */
	public boolean isViewPage() {
		return viewPage;
	}

	/**
	 * Sets the view page.
	 *
	 * @param viewPage the new view page
	 */
	public void setViewPage(boolean viewPage) {
		this.viewPage = viewPage;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

}
