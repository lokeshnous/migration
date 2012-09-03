package com.advanceweb.afc.jb.employer.web.controller;

/**
 * 
 * @author deviprasadm
 * @Created: Aug 03, 2012
 * @Purpose: This class will act as a Form Bean for the Manage Access Permission
 */
public class ManageAccessPermissionForm {
	private String ownerName;
	private String ownerEmail;
	private boolean accessTypeFull;
	private boolean accessTypePost;
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
	 * @return the accessTypeFull
	 */
	public boolean isAccessTypeFull() {
		return accessTypeFull;
	}
	/**
	 * @param accessTypeFull the accessTypeFull to set
	 */
	public void setAccessTypeFull(boolean accessTypeFull) {
		this.accessTypeFull = accessTypeFull;
	}
	/**
	 * @return the accessTypePost
	 */
	public boolean isAccessTypePost() {
		return accessTypePost;
	}
	/**
	 * @param accessTypePost the accessTypePost to set
	 */
	public void setAccessTypePost(boolean accessTypePost) {
		this.accessTypePost = accessTypePost;
	}
	
}
