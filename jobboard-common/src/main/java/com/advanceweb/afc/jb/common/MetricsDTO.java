/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 27th August, 2012
 * 
 */

public class MetricsDTO {

	/** The job id. */
	private int jobId;
	
	/** The views. */
	private long views;
	
	/** The clicks. */
	private long clicks;
	
	/** The applies. */
	private long applies;
	
	/** The stats date. */
	private Date statsDate;
	
	/** The metrics name. */
	private String metricsName;

	/**
	 * @return the jobId
	 */
	public int getJobId() {
		return jobId;
	}

	/**
	 * @param jobId
	 *            the jobId to set
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the statsDate
	 */
	public Date getStatsDate() {
		return statsDate;
	}

	/**
	 * @param statsDate
	 *            the statsDate to set
	 */
	public void setStatsDate(Date statsDate) {
		this.statsDate = statsDate;
	}

	/**
	 * @return the metricsName
	 */
	public String getMetricsName() {
		return metricsName;
	}

	/**
	 * @param metricsName the metricsName to set
	 */
	public void setMetricsName(String metricsName) {
		this.metricsName = metricsName;
	}

	/**
	 * Gets the views.
	 *
	 * @return the views
	 */
	public long getViews() {
		return views;
	}

	/**
	 * Sets the views.
	 *
	 * @param views the new views
	 */
	public void setViews(long views) {
		this.views = views;
	}

	/**
	 * Gets the clicks.
	 *
	 * @return the clicks
	 */
	public long getClicks() {
		return clicks;
	}

	/**
	 * Sets the clicks.
	 *
	 * @param clicks the new clicks
	 */
	public void setClicks(long clicks) {
		this.clicks = clicks;
	}

	/**
	 * Gets the applies.
	 *
	 * @return the applies
	 */
	public long getApplies() {
		return applies;
	}

	/**
	 * Sets the applies.
	 *
	 * @param applies the new applies
	 */
	public void setApplies(long applies) {
		this.applies = applies;
	}

}
