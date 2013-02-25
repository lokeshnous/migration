/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.List;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class AgencyProfileDTO extends BaseProfileDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The agency id. */
	private long agencyId;
	
	/** The package information dto. */
	private PackageInformationDTO packageInformationDTO;
	
	/** The metrics dto. */
	private MetricsDTO metricsDTO;
	
	/** The mer user dto. */
	private UserDTO merUserDTO;
	
	/** The comp profile dto. */
	private CompanyProfileDTO compProfileDTO;
	
	/** The add dto. */
	private AddressDTO addDTO;
	
	/** The profile type. */
	private String profileType;
	
	/** The attrib list. */
	private List<ProfileAttribDTO> attribList;

	/**
	 * @return the attribList
	 */
	public List<ProfileAttribDTO> getAttribList() {
		return attribList;
	}

	/**
	 * @param attribList
	 *            the attribList to set
	 */
	public void setAttribList(List<ProfileAttribDTO> attribList) {
		this.attribList = attribList;
	}


	/**
	 * @return the agencyId
	 */
	public long getAgencyId() {
		return agencyId;
	}

	/**
	 * @param agencyId
	 *            the agencyId to set
	 */
	public void setAgencyId(long agencyId) {
		this.agencyId = agencyId;
	}

	/**
	 * Gets the package information dto.
	 *
	 * @return the package information dto
	 */
	public PackageInformationDTO getPackageInformationDTO() {
		return packageInformationDTO;
	}

	/**
	 * Sets the package information dto.
	 *
	 * @param packageInformationDTO the new package information dto
	 */
	public void setPackageInformationDTO(
			PackageInformationDTO packageInformationDTO) {
		this.packageInformationDTO = packageInformationDTO;
	}

	/**
	 * Gets the metrics dto.
	 *
	 * @return the metrics dto
	 */
	public MetricsDTO getMetricsDTO() {
		return metricsDTO;
	}

	/**
	 * Sets the metrics dto.
	 *
	 * @param metricsDTO the new metrics dto
	 */
	public void setMetricsDTO(MetricsDTO metricsDTO) {
		this.metricsDTO = metricsDTO;
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
	 * Sets the mer user dto.
	 *
	 * @param merUserDTO the new mer user dto
	 */
	public void setMerUserDTO(UserDTO merUserDTO) {
		this.merUserDTO = merUserDTO;
	}

	/**
	 * Gets the comp profile dto.
	 *
	 * @return the comp profile dto
	 */
	public CompanyProfileDTO getCompProfileDTO() {
		return compProfileDTO;
	}

	/**
	 * Sets the comp profile dto.
	 *
	 * @param compProfileDTO the new comp profile dto
	 */
	public void setCompProfileDTO(CompanyProfileDTO compProfileDTO) {
		this.compProfileDTO = compProfileDTO;
	}

	/**
	 * Gets the adds the dto.
	 *
	 * @return the adds the dto
	 */
	public AddressDTO getAddDTO() {
		return addDTO;
	}

	/**
	 * Sets the adds the dto.
	 *
	 * @param addDTO the new adds the dto
	 */
	public void setAddDTO(AddressDTO addDTO) {
		this.addDTO = addDTO;
	}

	/**
	 * Gets the profile type.
	 *
	 * @return the profile type
	 */
	public String getProfileType() {
		return profileType;
	}

	/**
	 * Sets the profile type.
	 *
	 * @param profileType the new profile type
	 */
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

}