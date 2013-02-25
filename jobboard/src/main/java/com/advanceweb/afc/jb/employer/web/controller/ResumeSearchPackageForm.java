/**
 * 
 */
package com.advanceweb.afc.jb.employer.web.controller;

/**
 * @author anilm
 *
 */
public class ResumeSearchPackageForm implements Cloneable{
	
	/** The package id. */
	private int packageId;
	
	/** The package type. */
	private String packageType;
	
	/** The package name. */
	private String packageName;
	
	/** The netsuite id. */
	private int netsuiteId;
	
	/** The duration. */
	private int duration;
	
	/** The active. */
	private int active;
	
	/** The price amt. */
	private float priceAmt;
	
	/** The discount. */
	private int discount;
	
	/** The quantity. */
	private int quantity;
	
	/** The package total. */
	private float packageTotal;
	
	/**
	 * Gets the package id.
	 *
	 * @return the package id
	 */
	public int getPackageId() {
		return packageId;
	}
	
	/**
	 * Sets the package id.
	 *
	 * @param packageId the new package id
	 */
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	
	/**
	 * Gets the package type.
	 *
	 * @return the package type
	 */
	public String getPackageType() {
		return packageType;
	}
	
	/**
	 * Sets the package type.
	 *
	 * @param packageType the new package type
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	
	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * Sets the package name.
	 *
	 * @param packageName the new package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 * Gets the netsuite id.
	 *
	 * @return the netsuite id
	 */
	public int getNetsuiteId() {
		return netsuiteId;
	}
	
	/**
	 * Sets the netsuite id.
	 *
	 * @param netsuiteId the new netsuite id
	 */
	public void setNetsuiteId(int netsuiteId) {
		this.netsuiteId = netsuiteId;
	}
	
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public int getActive() {
		return active;
	}
	
	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(int active) {
		this.active = active;
	}
	
	/**
	 * Gets the price amt.
	 *
	 * @return the price amt
	 */
	public float getPriceAmt() {
		return priceAmt;
	}
	
	/**
	 * Sets the price amt.
	 *
	 * @param priceAmt the new price amt
	 */
	public void setPriceAmt(float priceAmt) {
		this.priceAmt = priceAmt;
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
	 * Gets the package total.
	 *
	 * @return the package total
	 */
	public float getPackageTotal() {
		return packageTotal;
	}
	
	/**
	 * Sets the package total.
	 *
	 * @param packageTotal the new package total
	 */
	public void setPackageTotal(float packageTotal) {
		this.packageTotal = packageTotal;
	}
	
	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}
	
	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
