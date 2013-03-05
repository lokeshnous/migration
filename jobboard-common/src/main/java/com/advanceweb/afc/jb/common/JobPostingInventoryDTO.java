/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 10th sept,2012
 */

public class JobPostingInventoryDTO {

	/** The user id. */
	private int userId;
	
	/** The facility id. */
	private int facilityId;
	
	/** The inventory id. */
	private int inventoryId;
	
	/** The jb type id. */
	private int jbTypeId;
	
	/** The jb type. */
	private String jbType;
	
	/** The duration. */
	private String duration;
	
	/** The quantity. */
	private int quantity;
	
	/** The available qty. */
	private int availableQty;
	
	/** The product type. */
	private String productType;
	
	/** The addon. */
	private String addon;
	
	/** The days. */
	private String days;
	
	/** The product id. */
	private int productId;
	
	/** The inv detail id. */
	private int invDetailId;
	private Date startDt;
	private Date endtDt;

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndtDt() {
		return endtDt;
	}

	public void setEndtDt(Date endtDt) {
		this.endtDt = endtDt;
	}

	/**
	 * @return the invDetailId
	 */
	public int getInvDetailId() {
		return invDetailId;
	}

	/**
	 * @param invDetailId
	 *            the invDetailId to set
	 */
	public void setInvDetailId(int invDetailId) {
		this.invDetailId = invDetailId;
	}

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
