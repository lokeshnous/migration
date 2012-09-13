package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

/**
 * This class has been created for holding the values of Job Type, Respective AddOn, 
 * Quantity & Package Sub total
 * @author anilm
 * @version 1.0
 * @created Aug 27, 2012
 */
public class JobPostingsForm {
	
	private String jobPostPlanId;
	private String jobPostPlanName;
	private String jobPostPlanDescr;
	private String jobPostPlanCretitAmt;
	private String jobPostNetSuiteId;
	private List<AddOnForm> addOnForm;
	private int quantity;
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
	public List<AddOnForm> getAddOnForm() {
		return addOnForm;
	}
	public void setAddOnForm(List<AddOnForm> addOnForm) {
		this.addOnForm = addOnForm;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPackageSubTotal() {
		return packageSubTotal;
	}
	public void setPackageSubTotal(int packageSubTotal) {
		this.packageSubTotal = packageSubTotal;
	}
}
