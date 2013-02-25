/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.web.controller;

/**
 * 
 * @author deviprasadm
 * @Version 1.0
 * @Since 9th Aug, 2012
 */
public class PhoneDetailForm {
	
	/** The builder phone id. */
	private int builderPhoneId;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The phone type. */
	private String phoneType;
	
	/** The item id. */
	private int itemId;
	
	/** The edit mode. */
	private boolean editMode;
	

	/**
	 * Checks if is edits the mode.
	 *
	 * @return true, if is edits the mode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * Sets the edits the mode.
	 *
	 * @param editMode the new edits the mode
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Sets the item id.
	 *
	 * @param itemId the new item id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the builderPhoneId
	 */
	public int getBuilderPhoneId() {
		return builderPhoneId;
	}

	/**
	 * @param builderPhoneId
	 *            the builderPhoneId to set
	 */
	public void setBuilderPhoneId(int builderPhoneId) {
		this.builderPhoneId = builderPhoneId;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the phoneType
	 */
	public String getPhoneType() {
		return phoneType;
	}

	/**
	 * @param phoneType
	 *            the phoneType to set
	 */
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
}
