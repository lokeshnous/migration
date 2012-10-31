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
	public List<ResumeSearchPackageForm> resumeSearchPackageList;
	public List<ResumeSearchPackageForm> resumeSearchPackageCart = new ArrayList<ResumeSearchPackageForm>();
	private float grandTotal;
	private String promotionCode;
	
	public List<ResumeSearchPackageForm> getResumeSearchPackageList() {
		return resumeSearchPackageList;
	}
	public void setResumeSearchPackageList(
			List<ResumeSearchPackageForm> resumeSearchPackageList) {
		this.resumeSearchPackageList = resumeSearchPackageList;
	}
	public List<ResumeSearchPackageForm> getResumeSearchPackageCart() {
		return resumeSearchPackageCart;
	}
	public void setResumeSearchPackageCart(
			List<ResumeSearchPackageForm> resumeSearchPackageCart) {
		this.resumeSearchPackageCart = resumeSearchPackageCart;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
}
