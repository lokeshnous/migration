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
	
	private String jobPostingType;
	private String applicationMethod;
	
	private String jobNumber;
	private String jobTitle;
	
	private String applyEmail;
	private String applyUrl;
	private String atsUrl;
	
	private String jobCity;
	private int jobState;
	private String jobCountry;
	private String jobZip;
	
	private String employmentType;
	
	private String reqSkills;
	private String jobDesc;
	private String trackPixel;
	private String brandTemplate;
	
	private boolean autoRenew;

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

	public int getJobState() {
		return jobState;
	}

	public void setJobState(int jobState) {
		this.jobState = jobState;
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

	public String getBrandTemplate() {
		return brandTemplate;
	}

	public void setBrandTemplate(String brandTemplate) {
		this.brandTemplate = brandTemplate;
	}
	
}
