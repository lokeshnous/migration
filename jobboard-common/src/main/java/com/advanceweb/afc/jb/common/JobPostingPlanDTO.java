package com.advanceweb.afc.jb.common;

import java.util.List;

/**
 * This class is created to hold the values of JOB_TYPE, respective ADDONS, Quantity, PackageSubTotal. 
 * Used to store the JOB_TYPE, ADDONS values fetched from db & pass to form.
 * Also acts as cart to hold the selected JOB_TYPE, ADDONS, Quantity & packageSubtotal values         
 * @author anilm
 * @version 1.0
 * @created Aug 24, 2012
 */
public class JobPostingPlanDTO {
	
	private String jobPostPlanId;
	private String jobPostPlanName;
	private String jobPostPlanDescr;
	private String jobPostPlanCretitAmt;
	private String jobPostNetSuiteId;
	private List<AddOnDTO> addOnDTOList;
	private int quanity;
	private int packageSubTotal;
	
	public String getJobPostPlanId() {
		return jobPostPlanId;
	}
	public void setJobPostPlanId(String jobPostPlanId) {
		this.jobPostPlanId = jobPostPlanId;
	}
	public String getJobPostPlanName() {
		return jobPostPlanName;
	}
	public void setJobPostPlanName(String jobPostPlanName) {
		this.jobPostPlanName = jobPostPlanName;
	}
	public String getJobPostPlanDescr() {
		return jobPostPlanDescr;
	}
	public void setJobPostPlanDescr(String jobPostPlanDescr) {
		this.jobPostPlanDescr = jobPostPlanDescr;
	}
	public String getJobPostPlanCretitAmt() {
		return jobPostPlanCretitAmt;
	}
	public void setJobPostPlanCretitAmt(String jobPostPlanCretitAmt) {
		this.jobPostPlanCretitAmt = jobPostPlanCretitAmt;
	}
	public String getJobPostNetSuiteId() {
		return jobPostNetSuiteId;
	}
	public void setJobPostNetSuiteId(String jobPostNetSuiteId) {
		this.jobPostNetSuiteId = jobPostNetSuiteId;
	}
	public List<AddOnDTO> getAddOnDTOList() {
		return addOnDTOList;
	}
	public void setAddOnDTOList(List<AddOnDTO> addOnDTOList) {
		this.addOnDTOList = addOnDTOList;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	public int getPackageSubTotal() {
		return packageSubTotal;
	}
	public void setPackageSubTotal(int packageSubTotal) {
		this.packageSubTotal = packageSubTotal;
	}
}
