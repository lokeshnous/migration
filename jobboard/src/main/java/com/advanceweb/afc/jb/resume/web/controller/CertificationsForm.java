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
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class CertificationsForm {
	
	/** The certification name. */
	private String certificationName;
	
	/** The date of receipt. */
	private String dateOfReceipt;
	
	/** The institute name. */
	private String instituteName;
	
	/** The summary. */
	private String summary;
	
	/** The certifying authority. */
	private String certifyingAuthority;
	
	/** The builder cert id. */
	private int builderCertId;
	
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
	 * Gets the certification name.
	 *
	 * @return the certification name
	 */
	public String getCertificationName() {
		return certificationName;
	}
	
	/**
	 * Sets the certification name.
	 *
	 * @param certificationName the new certification name
	 */
	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}
	
	/**
	 * Gets the date of receipt.
	 *
	 * @return the date of receipt
	 */
	public String getDateOfReceipt() {
		return dateOfReceipt;
	}
	
	/**
	 * Sets the date of receipt.
	 *
	 * @param dateOfReceipt the new date of receipt
	 */
	public void setDateOfReceipt(String dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}
	
	/**
	 * Gets the institute name.
	 *
	 * @return the institute name
	 */
	public String getInstituteName() {
		return instituteName;
	}
	
	/**
	 * Sets the institute name.
	 *
	 * @param instituteName the new institute name
	 */
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	
	/**
	 * Gets the summary.
	 *
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * Sets the summary.
	 *
	 * @param summary the new summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * Gets the builder cert id.
	 *
	 * @return the builder cert id
	 */
	public int getBuilderCertId() {
		return builderCertId;
	}
	
	/**
	 * Sets the builder cert id.
	 *
	 * @param builderCertId the new builder cert id
	 */
	public void setBuilderCertId(int builderCertId) {
		this.builderCertId = builderCertId;
	}
	
	/**
	 * Gets the certifying authority.
	 *
	 * @return the certifying authority
	 */
	public String getCertifyingAuthority() {
		return certifyingAuthority;
	}
	
	/**
	 * Sets the certifying authority.
	 *
	 * @param certifyingAuthority the new certifying authority
	 */
	public void setCertifyingAuthority(String certifyingAuthority) {
		this.certifyingAuthority = certifyingAuthority;
	}		
	
}
