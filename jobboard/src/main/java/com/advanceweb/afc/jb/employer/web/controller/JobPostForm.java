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

	private int jobId;
	private String jobOwner;
	private String customerNo;
	private String companyName;
	private String disCompanyName;
	private boolean bHideCompName;
	
	private String jobPostingType;
	
	private String jobNumber;
	private String jobTitle;
	
	private String applMethod;
	private String applyEmail;
	private String applyUrl;
	private String atsUrl;
	
	private String jobCity;
	private boolean bHideCity;
	private String jobState;
	private boolean bHideState;
	private String jobCountry;
	private boolean bHideCountry;
	private String jobZipCode;
	private boolean bHideZipCode;
	
	private String employmentType;
	
	private String reqSkills;
	private String jobDesc;
	private String trackPixel;
	
	private int brandTemplate;
	private boolean bTemplateOverride;
	
	private boolean autoRenew;

	private String scheduleStartDate;
	private String scheduleEndDate;
	
	private String jobStatus;
	private String selectedRow;
    private boolean readOnly= false;
    
    private List<JobPostDTO> jobPostDTOList;
    private String statusValue;
    private int noOfPage;
    private int beginVal=0;
    private boolean activeOrInactive;
    private boolean enableJobTitle;
    /*
    private String brandTemplateValue;
    private String autoRenewVal;*/
    
	private boolean xmlStartEndDateEnabled;
    
    
	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public boolean isbHideCompName() {
		return bHideCompName;
	}

	public void setbHideCompName(boolean bHideCompName) {
		this.bHideCompName = bHideCompName;
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
	
	public String getJobState() {
		return jobState;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	public String getJobCountry() {
		return jobCountry;
	}

	public void setJobCountry(String jobCountry) {
		this.jobCountry = jobCountry;
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

	public String getJobZipCode() {
		return jobZipCode;
	}

	public void setJobZipCode(String jobZipCode) {
		this.jobZipCode = jobZipCode;
	}

	public boolean isbHideZipCode() {
		return bHideZipCode;
	}

	public void setbHideZipCode(boolean bHideZipCode) {
		this.bHideZipCode = bHideZipCode;
	}

	public String getApplMethod() {
		return applMethod;
	}

	public void setApplMethod(String applMethod) {
		this.applMethod = applMethod;
	}

	public String getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public String getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public String getJobStatus() {
		return jobStatus;
	}

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

	public int getBeginVal() {
		return beginVal;
	}

	public void setBeginVal(int beginVal) {
		this.beginVal = beginVal;
	}

	public boolean isXmlStartEndDateEnabled() {
		return xmlStartEndDateEnabled;
	}

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

	public boolean isActiveOrInactive() {
		return activeOrInactive;
	}

	public void setActiveOrInactive(boolean activeOrInactive) {
		this.activeOrInactive = activeOrInactive;
	}
	
	public boolean isEnableJobTitle() {
		return enableJobTitle;
	}

	public void setEnableJobTitle(boolean enableJobTitle) {
		this.enableJobTitle = enableJobTitle;
	}

}
