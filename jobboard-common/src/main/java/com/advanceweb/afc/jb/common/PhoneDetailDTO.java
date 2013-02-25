/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class PhoneDetailDTO {
	
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
	 * Gets the builder phone id.
	 *
	 * @return the builder phone id
	 */
	public int getBuilderPhoneId() {
		return builderPhoneId;
	}
	
	/**
	 * Sets the builder phone id.
	 *
	 * @param builderPhoneId the new builder phone id
	 */
	public void setBuilderPhoneId(int builderPhoneId) {
		this.builderPhoneId = builderPhoneId;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Gets the phone type.
	 *
	 * @return the phone type
	 */
	public String getPhoneType() {
		return phoneType;
	}
	
	/**
	 * Sets the phone type.
	 *
	 * @param phoneType the new phone type
	 */
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	
	
}
