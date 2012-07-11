package com.advanceweb.afc.jb.webapp.web.forms.jobsearch;

/**
 * <code> JobSearchViewDetailForm </code> is a form bean for searched Job.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 11 July 2012
 */

public class JobSearchViewDetailForm {

	private String jobTitle;
	private String areaOfInterest;
	private int positionType;
	private int city;
	private int state;
	private String jobDesc;
	
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getAreaOfInterest() {
		return areaOfInterest;
	}
	public void setAreaOfInterest(String areaOfInterest) {
		this.areaOfInterest = areaOfInterest;
	}
	public int getPositionType() {
		return positionType;
	}
	public void setPositionType(int positionType) {
		this.positionType = positionType;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	
}
