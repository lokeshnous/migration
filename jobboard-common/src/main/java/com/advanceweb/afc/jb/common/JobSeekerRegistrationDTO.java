/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.List;

public class JobSeekerRegistrationDTO extends ProfileDTO {

	/** The address dto. */
	private AddressDTO addressDTO;
	
	/** The job seeker profile dto. */
	private JobSeekerProfileDTO jobSeekerProfileDTO;
	
	/** The mer user dto. */
	private UserDTO merUserDTO;
	
	/** The attrib list. */
	private List<ProfileAttribDTO> attribList;
	
	/** The email id. */
	private String emailId;
	
	/**
	 * Gets the address dto.
	 *
	 * @return the address dto
	 */
	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	/**
	 * Gets the job seeker profile dto.
	 *
	 * @return the job seeker profile dto
	 */
	public JobSeekerProfileDTO getJobSeekerProfileDTO() {
		return jobSeekerProfileDTO;
	}

	/**
	 * Gets the mer user dto.
	 *
	 * @return the mer user dto
	 */
	public UserDTO getMerUserDTO() {
		return merUserDTO;
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
	 * Sets the job seeker profile dto.
	 *
	 * @param jobSeekerProfileDTO the new job seeker profile dto
	 */
	public void setJobSeekerProfileDTO(JobSeekerProfileDTO jobSeekerProfileDTO) {
		this.jobSeekerProfileDTO = jobSeekerProfileDTO;
	}

	/**
	 * Sets the mer user dto.
	 *
	 * @param merUserDTO the new mer user dto
	 */
	public void setMerUserDTO(UserDTO merUserDTO) {
		this.merUserDTO = merUserDTO;
	}

	/**
	 * Gets the attrib list.
	 *
	 * @return the attrib list
	 */
	public List<ProfileAttribDTO> getAttribList() {
		return attribList;
	}

	/**
	 * Sets the attrib list.
	 *
	 * @param attribList the new attrib list
	 */
	public void setAttribList(List<ProfileAttribDTO> attribList) {
		this.attribList = attribList;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
