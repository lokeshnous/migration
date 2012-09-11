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
	private int ownerId;
	private String ownerName;
	private String ownerEmail;
	private String fullAccess;
	private String postEditAccess;
	private String setAlertPage;
	
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
	
	
	
}
