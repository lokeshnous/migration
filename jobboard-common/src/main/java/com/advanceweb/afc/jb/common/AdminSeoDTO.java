/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * @Author : Pramodap
   @Version: 1.0
   @Created: Feb 3, 2013
   @Purpose: This class will act as a DTO for SEO info of job
 */
public class AdminSeoDTO {
	
	/** The seo info id. */
	private int seoInfoId;
	
	/** The job title. */
	private String jobTitle;
	
	/** The meta title. */
	private String metaTitle;
	
	/** The meta desc. */
	private String metaDesc;
	
	/** The static content. */
	private String staticContent;
	
	/** The meta keywords. */
	private String metaKeywords;
	
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
	 * @return the metaKeywords
	 */
	public String getMetaKeywords() {
		return metaKeywords;
	}
	/**
	 * @param metaKeywords the metaKeywords to set
	 */
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
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
