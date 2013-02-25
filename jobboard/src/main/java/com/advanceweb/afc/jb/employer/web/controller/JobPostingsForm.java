/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The job post plan id. */
	private String jobPostPlanId;
	
	/** The job post plan name. */
	private String jobPostPlanName;
	
	/** The job post plan descr. */
	private String jobPostPlanDescr;
	
	/** The job post plan cretit amt. */
	private String jobPostPlanCretitAmt;
	
	/** The add on form. */
	private List<AddOnForm> addOnForm;
	
	/** The quantity. */
	private int quantity;
	
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
	 * Gets the adds the on form.
	 *
	 * @return the adds the on form
	 */
	public List<AddOnForm> getAddOnForm() {
		return addOnForm;
	}
	
	/**
	 * Sets the adds the on form.
	 *
	 * @param addOnForm the new adds the on form
	 */
	public void setAddOnForm(List<AddOnForm> addOnForm) {
		this.addOnForm = addOnForm;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
