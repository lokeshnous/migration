/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;


public class ManageAccessPermissionDTO {
	
	/** The owner id. */
	private int ownerId;
	
	/** The owner name. */
	private String ownerName;
	
	/** The owner email. */
	private String ownerEmail;
	
	/** The type of access. */
	private int typeOfAccess;
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
	 * @return the typeOfAccess
	 */
	public int getTypeOfAccess() {
		return typeOfAccess;
	}
	/**
	 * @param typeOfAccess the typeOfAccess to set
	 */
	public void setTypeOfAccess(int typeOfAccess) {
		this.typeOfAccess = typeOfAccess;
	} 
}
