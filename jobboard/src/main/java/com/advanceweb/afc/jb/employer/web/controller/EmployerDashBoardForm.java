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

public class EmployerDashBoardForm {

	/** The sel employer. */
	private String selEmployer;
	
	/** The clicks. */
	private int clicks;
	

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

	/**
	 * @return the clicks
	 */
	public int getClicks() {
		return clicks;
	}

	/**
	 * @param clicks the clicks to set
	 */
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
}
