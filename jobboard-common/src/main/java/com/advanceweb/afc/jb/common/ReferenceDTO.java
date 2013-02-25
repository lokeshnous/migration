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
public class ReferenceDTO {
	
	/** The name. */
	private String name;
	
	/** The job title. */
	private String jobTitle;
	
	/** The company name. */
	private String companyName;
	
	/** The phone no. */
	private String phoneNo;
	
	/** The email. */
	private String email;
	
	/** The ref type. */
	private String refType;
	
	/** The item id. */
	private int itemId;
	
	/** The edit mode. */
	private boolean editMode;
	
	
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
	
	/** The builder ref id. */
	private int builderRefId;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	
	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the new job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * Gets the phone no.
	 *
	 * @return the phone no
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	
	/**
	 * Sets the phone no.
	 *
	 * @param phoneNo the new phone no
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the builder ref id.
	 *
	 * @return the builder ref id
	 */
	public int getBuilderRefId() {
		return builderRefId;
	}
	
	/**
	 * Sets the builder ref id.
	 *
	 * @param builderRefId the new builder ref id
	 */
	public void setBuilderRefId(int builderRefId) {
		this.builderRefId = builderRefId;
	}
	
	/**
	 * Gets the ref type.
	 *
	 * @return the ref type
	 */
	public String getRefType() {
		return refType;
	}
	
	/**
	 * Sets the ref type.
	 *
	 * @param refType the new ref type
	 */
	public void setRefType(String refType) {
		this.refType = refType;
	}
}
