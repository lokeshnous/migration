package com.advanceweb.afc.jb.resume.web.controller;

/**
 * 
 * @author deviprasadm
 * @Version 1.0
 * @Since 9th Aug, 2012
 */
public class PhoneDetailForm {
	private int builderPhoneId;
	private String phoneNumber;
	private String phoneType;
	private int itemId;
	private boolean editMode;
	

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public int getItemId() {
		return itemId;
	}

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
