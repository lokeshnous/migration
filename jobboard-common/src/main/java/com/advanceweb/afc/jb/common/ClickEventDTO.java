/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.Date;

public class ClickEventDTO {
	
	/** The jobid. */
	private int jobid;
	
	/** The views. */
	private int views;
	
	/** The clicks. */
	private int clicks;
	
	/** The applies. */
	private int applies;
	
	/** The statdate. */
	private Date statdate;
	
	/**
	 * Gets the jobid.
	 *
	 * @return the jobid
	 */
	public int getJobid() {
		return jobid;
	}
	
	/**
	 * Sets the jobid.
	 *
	 * @param jobid the new jobid
	 */
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	
	/**
	 * Gets the views.
	 *
	 * @return the views
	 */
	public int getViews() {
		return views;
	}
	
	/**
	 * Sets the views.
	 *
	 * @param views the new views
	 */
	public void setViews(int views) {
		this.views = views;
	}
	
	/**
	 * Gets the clicks.
	 *
	 * @return the clicks
	 */
	public int getClicks() {
		return clicks;
	}
	
	/**
	 * Sets the clicks.
	 *
	 * @param clicks the new clicks
	 */
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	
	/**
	 * Gets the applies.
	 *
	 * @return the applies
	 */
	public int getApplies() {
		return applies;
	}
	
	/**
	 * Sets the applies.
	 *
	 * @param applies the new applies
	 */
	public void setApplies(int applies) {
		this.applies = applies;
	}
	
	/**
	 * Gets the statdate.
	 *
	 * @return the statdate
	 */
	public Date getStatdate() {
		return statdate;
	}
	
	/**
	 * Sets the statdate.
	 *
	 * @param statdate the new statdate
	 */
	public void setStatdate(Date statdate) {
		this.statdate = statdate;
	}



}
