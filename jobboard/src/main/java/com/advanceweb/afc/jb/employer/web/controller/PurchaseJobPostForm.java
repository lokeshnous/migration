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
	
	private List<JobPostingsForm> jobPostingsForm;
	private List<JobPostingsForm> jobPostingsCart = new ArrayList<JobPostingsForm>();
	private int grandTotal;
	private String promotionCode;
	private String inventoryPage;
	
	public List<JobPostingsForm> getJobPostingsForm() {
		return jobPostingsForm;
	}
	public void setJobPostingsForm(List<JobPostingsForm> jobPostingsForm) {
		this.jobPostingsForm = jobPostingsForm;
	}
	public List<JobPostingsForm> getJobPostingsCart() {
		return jobPostingsCart;
	}
	public void setJobPostingsCart(List<JobPostingsForm> jobPostingsCart) {
		this.jobPostingsCart = jobPostingsCart;
	}
	public int getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	public String getInventoryPage() {
		return inventoryPage;
	}
	public void setInventoryPage(String inventoryPage) {
		this.inventoryPage = inventoryPage;
	}
}
