package com.advanceweb.afc.jb.common;

/**
 * This class has bean created to hold the values of AddOns 
 * @author anilm
 * @version 1.0
 * @created Aug 24, 2012
 */
public class AddOnDTO {
	
	private String addOnId;
	private String addOnName;
	private String addOnDescription;
	private String addOnCreditAmt;
	
	public String getAddOnId() {
		return addOnId;
	}
	public void setAddOnId(String addOnId) {
		this.addOnId = addOnId;
	}
	public String getAddOnName() {
		return addOnName;
	}
	public void setAddOnName(String addOnName) {
		this.addOnName = addOnName;
	}
	public String getAddOnDescription() {
		return addOnDescription;
	}
	public void setAddOnDescription(String addOnDescription) {
		this.addOnDescription = addOnDescription;
	}
	public String getAddOnCreditAmt() {
		return addOnCreditAmt;
	}
	public void setAddOnCreditAmt(String addOnCreditAmt) {
		this.addOnCreditAmt = addOnCreditAmt;
	}
}
