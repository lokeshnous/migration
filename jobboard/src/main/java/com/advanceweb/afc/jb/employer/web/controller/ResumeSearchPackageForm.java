/**
 * 
 */
package com.advanceweb.afc.jb.employer.web.controller;

/**
 * @author anilm
 *
 */
public class ResumeSearchPackageForm implements Cloneable{
	private int packageId;
	private String packageType;
	private String packageName;
	private int netsuiteId;
	private int duration;
	private int active;
	private float priceAmt;
	private int discount;
	private int quantity;
	private float packageTotal;
	
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getNetsuiteId() {
		return netsuiteId;
	}
	public void setNetsuiteId(int netsuiteId) {
		this.netsuiteId = netsuiteId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public float getPriceAmt() {
		return priceAmt;
	}
	public void setPriceAmt(float priceAmt) {
		this.priceAmt = priceAmt;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPackageTotal() {
		return packageTotal;
	}
	public void setPackageTotal(float packageTotal) {
		this.packageTotal = packageTotal;
	}
	
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
