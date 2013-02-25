/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * This class have been created to hold the values of Job Post Type list, 
 * Selected Job postings in the Cart list,calculated Grand total & Promotion code
 * @author anilm
 * @version 1.0
 * @created Aug 27, 2012
 */
public class PurchaseJobPostForm {
	
	/** The job postings form. */
	private List<JobPostingsForm> jobPostingsForm;
	
	/** The job postings cart. */
	private List<JobPostingsForm> jobPostingsCart = new ArrayList<JobPostingsForm>();
	
	/** The grand total. */
	private double grandTotal;
	
	/** The total. */
	private double total;
	
	/** The discount amt. */
	private double discountAmt;
	
	/** The promotion code. */
	private String promotionCode;
	
	/** The inventory page. */
	private String inventoryPage;
	
	/**
	 * Gets the job postings form.
	 *
	 * @return the job postings form
	 */
	public List<JobPostingsForm> getJobPostingsForm() {
		return jobPostingsForm;
	}
	
	/**
	 * Sets the job postings form.
	 *
	 * @param jobPostingsForm the new job postings form
	 */
	public void setJobPostingsForm(List<JobPostingsForm> jobPostingsForm) {
		this.jobPostingsForm = jobPostingsForm;
	}
	
	/**
	 * Gets the job postings cart.
	 *
	 * @return the job postings cart
	 */
	public List<JobPostingsForm> getJobPostingsCart() {
		return jobPostingsCart;
	}
	
	/**
	 * Sets the job postings cart.
	 *
	 * @param jobPostingsCart the new job postings cart
	 */
	public void setJobPostingsCart(List<JobPostingsForm> jobPostingsCart) {
		this.jobPostingsCart = jobPostingsCart;
	}
	
	/**
	 * Gets the grand total.
	 *
	 * @return the grand total
	 */
	public double getGrandTotal() {
		return grandTotal;
	}
	
	/**
	 * Sets the grand total.
	 *
	 * @param grandTotal the new grand total
	 */
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	/**
	 * Gets the promotion code.
	 *
	 * @return the promotion code
	 */
	public String getPromotionCode() {
		return promotionCode;
	}
	
	/**
	 * Sets the promotion code.
	 *
	 * @param promotionCode the new promotion code
	 */
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	
	/**
	 * Gets the inventory page.
	 *
	 * @return the inventory page
	 */
	public String getInventoryPage() {
		return inventoryPage;
	}
	
	/**
	 * Sets the inventory page.
	 *
	 * @param inventoryPage the new inventory page
	 */
	public void setInventoryPage(String inventoryPage) {
		this.inventoryPage = inventoryPage;
	}
	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	/**
	 * @return the discountAmt
	 */
	public double getDiscountAmt() {
		return discountAmt;
	}
	/**
	 * @param discountAmt the discountAmt to set
	 */
	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}
}
