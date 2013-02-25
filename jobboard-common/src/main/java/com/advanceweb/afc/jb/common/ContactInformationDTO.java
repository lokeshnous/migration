/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:24:25 PM
 */
public class ContactInformationDTO {

	/** The first name. */
	private String firstName;
	
	/** The middle name initial. */
	private String middleNameInitial;
	
	/** The last name. */
	private String lastName;
	
	/** The address dto. */
	private AddressDTO addressDTO;
	
	/** The email. */
	private String email;
	
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

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the middle name initial.
	 *
	 * @return the middle name initial
	 */
	public String getMiddleNameInitial() {
		return middleNameInitial;
	}

	/**
	 * Sets the middle name initial.
	 *
	 * @param middleNameInitial the new middle name initial
	 */
	public void setMiddleNameInitial(String middleNameInitial) {
		this.middleNameInitial = middleNameInitial;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the address dto.
	 *
	 * @return the address dto
	 */
	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	/**
	 * Sets the address dto.
	 *
	 * @param addressDTO the new address dto
	 */
	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
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
	
	
	

}