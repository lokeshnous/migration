package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * anilm
 * @version 1.0
 * @created Aug 27, 2012
 */
public class PurchaseJobPostForm {
	
	List<JobPostingsForm> jobPostingsForm;
	List<JobPostingsForm> jobPostingsCart = new ArrayList<JobPostingsForm>();
	int grandTotal;
	String promotionCode;
	
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
}
