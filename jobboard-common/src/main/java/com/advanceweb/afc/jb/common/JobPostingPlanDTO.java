/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The job post plan id. */
	private String jobPostPlanId;
	
	/** The job post plan name. */
	private String jobPostPlanName;
	
	/** The job post plan descr. */
	private String jobPostPlanDescr;
	
	/** The job post plan cretit amt. */
	private String jobPostPlanCretitAmt;
	
	/** The add on dto list. */
	private List<AddOnDTO> addOnDTOList;
	
	/** The quanity. */
	private int quanity;
	
	/** The package sub total. */
	private int packageSubTotal;
	
	/**
	 * Gets the job post plan id.
	 *
	 * @return the job post plan id
	 */
	public String getJobPostPlanId() {
		return jobPostPlanId;
	}
	
	/**
	 * Sets the job post plan id.
	 *
	 * @param jobPostPlanId the new job post plan id
	 */
	public void setJobPostPlanId(String jobPostPlanId) {
		this.jobPostPlanId = jobPostPlanId;
	}
	
	/**
	 * Gets the job post plan name.
	 *
	 * @return the job post plan name
	 */
	public String getJobPostPlanName() {
		return jobPostPlanName;
	}
	
	/**
	 * Sets the job post plan name.
	 *
	 * @param jobPostPlanName the new job post plan name
	 */
	public void setJobPostPlanName(String jobPostPlanName) {
		this.jobPostPlanName = jobPostPlanName;
	}
	
	/**
	 * Gets the job post plan descr.
	 *
	 * @return the job post plan descr
	 */
	public String getJobPostPlanDescr() {
		return jobPostPlanDescr;
	}
	
	/**
	 * Sets the job post plan descr.
	 *
	 * @param jobPostPlanDescr the new job post plan descr
	 */
	public void setJobPostPlanDescr(String jobPostPlanDescr) {
		this.jobPostPlanDescr = jobPostPlanDescr;
	}
	
	/**
	 * Gets the job post plan cretit amt.
	 *
	 * @return the job post plan cretit amt
	 */
	public String getJobPostPlanCretitAmt() {
		return jobPostPlanCretitAmt;
	}
	
	/**
	 * Sets the job post plan cretit amt.
	 *
	 * @param jobPostPlanCretitAmt the new job post plan cretit amt
	 */
	public void setJobPostPlanCretitAmt(String jobPostPlanCretitAmt) {
		this.jobPostPlanCretitAmt = jobPostPlanCretitAmt;
	}
	
	/**
	 * Gets the adds the on dto list.
	 *
	 * @return the adds the on dto list
	 */
	public List<AddOnDTO> getAddOnDTOList() {
		return addOnDTOList;
	}
	
	/**
	 * Sets the adds the on dto list.
	 *
	 * @param addOnDTOList the new adds the on dto list
	 */
	public void setAddOnDTOList(List<AddOnDTO> addOnDTOList) {
		this.addOnDTOList = addOnDTOList;
	}
	
	/**
	 * Gets the quanity.
	 *
	 * @return the quanity
	 */
	public int getQuanity() {
		return quanity;
	}
	
	/**
	 * Sets the quanity.
	 *
	 * @param quanity the new quanity
	 */
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	
	/**
	 * Gets the package sub total.
	 *
	 * @return the package sub total
	 */
	public int getPackageSubTotal() {
		return packageSubTotal;
	}
	
	/**
	 * Sets the package sub total.
	 *
	 * @param packageSubTotal the new package sub total
	 */
	public void setPackageSubTotal(int packageSubTotal) {
		this.packageSubTotal = packageSubTotal;
	}
}
