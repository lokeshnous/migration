package com.advanceweb.afc.jb.common;

import java.util.Date;

public class ClickEventDTO {
	private int jobid;
	private int views;
	private int clicks;
	private int applies;
	private Date statdate;
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public int getApplies() {
		return applies;
	}
	public void setApplies(int applies) {
		this.applies = applies;
	}
	public Date getStatdate() {
		return statdate;
	}
	public void setStatdate(Date statdate) {
		this.statdate = statdate;
	}



}
