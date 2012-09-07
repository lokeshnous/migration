package com.advanceweb.afc.jb.common;


public class ManageAccessPermissionDTO {
	private int ownerId;
	private String ownerName;
	private String ownerEmail;
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
