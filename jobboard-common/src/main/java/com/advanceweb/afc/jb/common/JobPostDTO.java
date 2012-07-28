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
	private String jobNumber;
	private String jobTitle;
	private String applyEmail;
	private String applyUrl;
	private String atsUrl;
	private String jobCity;
	private int jobState;
	private String jobCountry;
	private String jobZip;
	private int empTypeId;
	private String reqSkills;
	private String jobDesc;
	private String trackPixel;
	private int jpBrdTemp;
	private char autoRenew;
	
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	/**
	 * @return the trackPixel
	 */
	public String getTrackPixel() {
		return trackPixel;
	}
	/**
	 * @param trackPixel the trackPixel to set
	 */
	public void setTrackPixel(String trackPixel) {
		this.trackPixel = trackPixel;
	}
	/**
	 * @return the jobOwner
	 */
	public String getJobOwner() {
		return jobOwner;
	}
	/**
	 * @param jobOwner the jobOwner to set
	 */
	public void setJobOwner(String jobOwner) {
		this.jobOwner = jobOwner;
	}
	/**
	 * @return the customerNo
	 */
	public String getCustomerNo() {
		return customerNo;
	}
	/**
	 * @param customerNo the customerNo to set
	 */
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the disCompanyName
	 */
	public String getDisCompanyName() {
		return disCompanyName;
	}
	/**
	 * @param disCompanyName the disCompanyName to set
	 */
	public void setDisCompanyName(String disCompanyName) {
		this.disCompanyName = disCompanyName;
	}
	/**
	 * @return the jobId
	 */

	
	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return the applyEmail
	 */
	public String getApplyEmail() {
		return applyEmail;
	}
	/**
	 * @param applyEmail the applyEmail to set
	 */
	public void setApplyEmail(String applyEmail) {
		this.applyEmail = applyEmail;
	}
	/**
	 * @return the applyUrl
	 */
	public String getApplyUrl() {
		return applyUrl;
	}
	/**
	 * @param applyUrl the applyUrl to set
	 */
	public void setApplyUrl(String applyUrl) {
		this.applyUrl = applyUrl;
	}
	/**
	 * @return the atsUrl
	 */
	public String getAtsUrl() {
		return atsUrl;
	}
	/**
	 * @param atsUrl the atsUrl to set
	 */
	public void setAtsUrl(String atsUrl) {
		this.atsUrl = atsUrl;
	}
	/**
	 * @return the jobCity
	 */
	public String getJobCity() {
		return jobCity;
	}
	/**
	 * @param jobCity the jobCity to set
	 */
	public void setJobCity(String jobCity) {
		this.jobCity = jobCity;
	}
	/**
	 * @return the jobState
	 */
	public int getJobState() {
		return jobState;
	}
	/**
	 * @param jobState the jobState to set
	 */
	public void setJobState(int jobState) {
		this.jobState = jobState;
	}
	/**
	 * @return the jobCountry
	 */
	public String getJobCountry() {
		return jobCountry;
	}
	/**
	 * @param jobCountry the jobCountry to set
	 */
	public void setJobCountry(String jobCountry) {
		this.jobCountry = jobCountry;
	}
	/**
	 * @return the jobZip
	 */
	public String getJobZip() {
		return jobZip;
	}
	/**
	 * @param jobZip the jobZip to set
	 */
	public void setJobZip(String jobZip) {
		this.jobZip = jobZip;
	}
	/**
	 * @return the empTypeId
	 */
	public int getEmpTypeId() {
		return empTypeId;
	}
	/**
	 * @param empTypeId the empTypeId to set
	 */
	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}
	/**
	 * @return the reqSkills
	 */
	public String getReqSkills() {
		return reqSkills;
	}
	/**
	 * @param reqSkills the reqSkills to set
	 */
	public void setReqSkills(String reqSkills) {
		this.reqSkills = reqSkills;
	}
	/**
	 * @return the jobDesc
	 */
	public String getJobDesc() {
		return jobDesc;
	}
	/**
	 * @param jobDesc the jobDesc to set
	 */
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	/**
	 * @return the jpBrdTemp
	 */
	public int getJpBrdTemp() {
		return jpBrdTemp;
	}
	/**
	 * @param jpBrdTemp the jpBrdTemp to set
	 */
	public void setJpBrdTemp(int jpBrdTemp) {
		this.jpBrdTemp = jpBrdTemp;
	}
	/**
	 * @return the autoRenew
	 */
	public char getAutoRenew() {
		return autoRenew;
	}
	/**
	 * @param autoRenew the autoRenew to set
	 */
	public void setAutoRenew(char autoRenew) {
		this.autoRenew = autoRenew;
	}
	
	
}
