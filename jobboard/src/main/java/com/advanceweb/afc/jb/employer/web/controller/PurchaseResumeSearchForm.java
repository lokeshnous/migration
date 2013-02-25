/**
 * 
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anilm
 *
 */
public class PurchaseResumeSearchForm {
	
	/** The resume search package list. */
	public List<ResumeSearchPackageForm> resumeSearchPackageList;
	
	/** The resume search package cart. */
	public List<ResumeSearchPackageForm> resumeSearchPackageCart = new ArrayList<ResumeSearchPackageForm>();
	
	/** The grand total. */
	private float grandTotal;
	
	/** The promotion code. */
	private String promotionCode;
	
	/**
	 * Gets the resume search package list.
	 *
	 * @return the resume search package list
	 */
	public List<ResumeSearchPackageForm> getResumeSearchPackageList() {
		return resumeSearchPackageList;
	}
	
	/**
	 * Sets the resume search package list.
	 *
	 * @param resumeSearchPackageList the new resume search package list
	 */
	public void setResumeSearchPackageList(
			List<ResumeSearchPackageForm> resumeSearchPackageList) {
		this.resumeSearchPackageList = resumeSearchPackageList;
	}
	
	/**
	 * Gets the resume search package cart.
	 *
	 * @return the resume search package cart
	 */
	public List<ResumeSearchPackageForm> getResumeSearchPackageCart() {
		return resumeSearchPackageCart;
	}
	
	/**
	 * Sets the resume search package cart.
	 *
	 * @param resumeSearchPackageCart the new resume search package cart
	 */
	public void setResumeSearchPackageCart(
			List<ResumeSearchPackageForm> resumeSearchPackageCart) {
		this.resumeSearchPackageCart = resumeSearchPackageCart;
	}
	
	/**
	 * Gets the grand total.
	 *
	 * @return the grand total
	 */
	public float getGrandTotal() {
		return grandTotal;
	}
	
	/**
	 * Sets the grand total.
	 *
	 * @param grandTotal the new grand total
	 */
	public void setGrandTotal(float grandTotal) {
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
}
