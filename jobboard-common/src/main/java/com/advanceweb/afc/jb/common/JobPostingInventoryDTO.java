package com.advanceweb.afc.jb.common;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 10th sept,2012
 */

public class JobPostingInventoryDTO {

	private int userId;
	private int facilityId;
	private int inventoryId;
	private int jbTypeId;
	private String jbType;
	private String duration;
	private int quantity;
	private int availableQty;
	private String productType;
	private String addon;
	private String days;
	private int productId;

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId
	 *            the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the inventoryId
	 */
	public int getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId
	 *            the inventoryId to set
	 */
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * @return the jbTypeId
	 */
	public int getJbTypeId() {
		return jbTypeId;
	}

	/**
	 * @param jbTypeId
	 *            the jbTypeId to set
	 */
	public void setJbTypeId(int jbTypeId) {
		this.jbTypeId = jbTypeId;
	}

	/**
	 * @return the jbType
	 */
	public String getJbType() {
		return jbType;
	}

	/**
	 * @param jbType
	 *            the jbType to set
	 */
	public void setJbType(String jbType) {
		this.jbType = jbType;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the availableQty
	 */
	public int getAvailableQty() {
		return availableQty;
	}

	/**
	 * @param availableQty
	 *            the availableQty to set
	 */
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the addon
	 */
	public String getAddon() {
		return addon;
	}

	/**
	 * @param addon
	 *            the addon to set
	 */
	public void setAddon(String addon) {
		this.addon = addon;
	}

	/**
	 * @return the days
	 */
	public String getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

}
