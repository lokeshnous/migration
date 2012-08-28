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

	private int jobId;
	private int views;
	private int clicks;
	private int applies;
	private Date statsDate;
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
	 * @return the views
	 */
	public int getViews() {
		return views;
	}

	/**
	 * @param views
	 *            the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}

	/**
	 * @return the clicks
	 */
	public int getClicks() {
		return clicks;
	}

	/**
	 * @param clicks
	 *            the clicks to set
	 */
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	/**
	 * @return the applies
	 */
	public int getApplies() {
		return applies;
	}

	/**
	 * @param applies
	 *            the applies to set
	 */
	public void setApplies(int applies) {
		this.applies = applies;
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

}
