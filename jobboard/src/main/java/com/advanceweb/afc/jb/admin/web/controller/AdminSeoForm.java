package com.advanceweb.afc.jb.admin.web.controller;

/**
 * @author pramodap
 *
 */
public class AdminSeoForm {
	
	private int seoInfoId;
	private String jobTitle;
	private String metaTitle;
	private String metaDesc;
	private String staticContent;
	private String description;
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the seoInfoId
	 */
	public int getSeoInfoId() {
		return seoInfoId;
	}
	/**
	 * @param seoInfoId the seoInfoId to set
	 */
	public void setSeoInfoId(int seoInfoId) {
		this.seoInfoId = seoInfoId;
	}
	
	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * @return the metaTitle
	 */
	public String getMetaTitle() {
		return metaTitle;
	}
	/**
	 * @param metaTitle the metaTitle to set
	 */
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	/**
	 * @return the metaDesc
	 */
	public String getMetaDesc() {
		return metaDesc;
	}
	/**
	 * @param metaDesc the metaDesc to set
	 */
	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}
	/**
	 * @return the staticContent
	 */
	public String getStaticContent() {
		return staticContent;
	}
	/**
	 * @param staticContent the staticContent to set
	 */
	public void setStaticContent(String staticContent) {
		this.staticContent = staticContent;
	}
}
