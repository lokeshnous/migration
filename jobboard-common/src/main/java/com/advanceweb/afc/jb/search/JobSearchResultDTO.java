/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search;

import java.io.Serializable;

import com.advanceweb.afc.jb.common.JobDTO;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 10th July 2012 onwards
 */

public class JobSearchResultDTO extends SearchResultDTO<JobDTO> implements
		Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// This String holds the location entered by user
	/** The universal location. */
	private String universalLocation;

	/**
	 * Gets the universal location.
	 *
	 * @return the universal location
	 */
	public String getUniversalLocation() {
		return universalLocation;
	}

	/**
	 * Sets the universal location.
	 *
	 * @param universalLocation the new universal location
	 */
	public void setUniversalLocation(String universalLocation) {
		this.universalLocation = universalLocation;
	}
	
}
