/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

/**
 * 
 * @author HarshaV
 * @Version 1.0
 * @Since 29th Aug, 2012
 */

public class TestimonyForm {
	
	/** The testimony. */
	private String testimony;

	/** The testimony id. */
	private int testimonyId;
	
	/** The item id. */
	private int itemId;
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * Gets the testimony id.
	 *
	 * @return the testimony id
	 */
	public int getTestimonyId() {
		return testimonyId;
	}

	/**
	 * Sets the testimony id.
	 *
	 * @param testimonyId the new testimony id
	 */
	public void setTestimonyId(int testimonyId) {
		this.testimonyId = testimonyId;
	}

	/**
	 * Gets the testimony.
	 *
	 * @return the testimony
	 */
	public String getTestimony() {
		return testimony;
	}

	/**
	 * Sets the testimony.
	 *
	 * @param testimony the new testimony
	 */
	public void setTestimony(String testimony) {
		this.testimony = testimony;
	}
	

}
