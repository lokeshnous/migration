/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 21st sept, 2012
 * 
 */

public class MetricsForm {

	/** The sel employer. */
	private String selEmployer;
	
	/** The resume search count. */
	private int resumeSearchCount;


	/**
	 * @return the resumeSearchCount
	 */
	public int getResumeSearchCount() {
		return resumeSearchCount;
	}

	/**
	 * @param resumeSearchCount the resumeSearchCount to set
	 */
	public void setResumeSearchCount(int resumeSearchCount) {
		this.resumeSearchCount = resumeSearchCount;
	}

	/**
	 * @return the selEmployer
	 */
	public String getSelEmployer() {
		return selEmployer;
	}

	/**
	 * @param selEmployer
	 *            the selEmployer to set
	 */
	public void setSelEmployer(String selEmployer) {
		this.selEmployer = selEmployer;
	}
}
