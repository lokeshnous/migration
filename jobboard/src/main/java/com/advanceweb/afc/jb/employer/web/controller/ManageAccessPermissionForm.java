/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;


/**
 * 
 * @author deviprasadm
 * @Created: Aug 03, 2012
 * @Purpose: This class will act as a Form Bean for the Manage Access Permission
 */
public class ManageAccessPermissionForm {
	
	/** The owner id. */
	private int ownerId;
	
	/** The owner name. */
	private String ownerName;
	
	/** The owner email. */
	private String ownerEmail;
	
	/** The full access. */
	private String fullAccess;
	
	/** The post edit access. */
	private String postEditAccess;
	
	/** The set alert page. */
	private String setAlertPage;
	
	/** The total size. */
	private int totalSize;
	
	/** The age perm page. */
	private String agePermPage;
	
	/** The manage access permissiondetails. */
	private List<ManageAccessPermissionDTO> manageAccessPermissiondetails; 
	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}
	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	/**
	 * @return the ownerEmail
	 */
	public String getOwnerEmail() {
		return ownerEmail;
	}
	/**
	 * @param ownerEmail the ownerEmail to set
	 */
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	/**
	 * @return the fullAccess
	 */
	public String getFullAccess() {
		return fullAccess;
	}
	/**
	 * @param fullAccess the fullAccess to set
	 */
	public void setFullAccess(String fullAccess) {
		this.fullAccess = fullAccess;
	}
	/**
	 * @return the postEditAccess
	 */
	public String getPostEditAccess() {
		return postEditAccess;
	}
	/**
	 * @param postEditAccess the postEditAccess to set
	 */
	public void setPostEditAccess(String postEditAccess) {
		this.postEditAccess = postEditAccess;
	}
	
	/**
	 * @return the ownerId
	 */
	public int getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the manageAccessPermissiondetails
	 */
	public List<ManageAccessPermissionDTO> getManageAccessPermissiondetails() {
		return manageAccessPermissiondetails;
	}
	/**
	 * @param manageAccessPermissiondetails the manageAccessPermissiondetails to set
	 */
	public void setManageAccessPermissiondetails(
			List<ManageAccessPermissionDTO> manageAccessPermissiondetails) {
		this.manageAccessPermissiondetails = manageAccessPermissiondetails;
	}
	/**
	 * @return the setAlertPage
	 */
	public String getSetAlertPage() {
		return setAlertPage;
	}
	/**
	 * @param setAlertPage the setAlertPage to set
	 */
	public void setSetAlertPage(String setAlertPage) {
		this.setAlertPage = setAlertPage;
	}
	/**
	 * 
	 * @return
	 */
	public int getTotalSize() {
		return totalSize;
	}
	/**
	 * 
	 * @param totalSize
	 */
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	/**
	 * Gets the age perm page.
	 *
	 * @return the age perm page
	 */
	public String getAgePermPage() {
		return agePermPage;
	}
	
	/**
	 * Sets the age perm page.
	 *
	 * @param agePermPage the new age perm page
	 */
	public void setAgePermPage(String agePermPage) {
		this.agePermPage = agePermPage;
	}
	
}
