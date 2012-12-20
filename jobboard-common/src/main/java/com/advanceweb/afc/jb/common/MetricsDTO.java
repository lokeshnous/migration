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
	private long views;
	private long clicks;
	private long applies;
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

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public long getClicks() {
		return clicks;
	}

	public void setClicks(long clicks) {
		this.clicks = clicks;
	}

	public long getApplies() {
		return applies;
	}

	public void setApplies(long applies) {
		this.applies = applies;
	}

}
