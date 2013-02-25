/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search.engine.solr;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**
 * This class is the bean class for the attributes of Resume search coming from SOLR.
 * @author ReeteshRN
 * @version 1.0
 * @Date 3rd October 2012 onwards
 */

public class SolrResumeDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The city. */
	@Field("city")
	private String city;
	
	/** The desired employment. */
	@Field("desired_employment")
	private String desiredEmployment;
	
	/** The desired job. */
	@Field("desired_job")
	private String desiredJob;
	
	/** The full name. */
	@Field("full_name")
	private String fullName;
	
	/** The html resume text. */
	@Field("html_resume_text")
	private String htmlResumeText;
	
	/** The post dt. */
	@Field("post_dt")
	private Date postDt;
	
	/** The post code. */
	@Field("postcode")
	private String postCode;
	
	/** The publish resume id. */
	@Field("publish_resume_id")
	private int publishResumeId;
	
	/** The res geo. */
	@Field("resgeo")
	private String resGeo;
	
	/** The resume name. */
	@Field("resume_name")
	private String resumeName;
	
	/** The resume text. */
	@Field("resume_text")
	private String resumeText;
	
	/** The state. */
	@Field("state")
	private String state;
	
	/** The will relocate. */
	@Field("will_relocate")
	private String willRelocate;
	
	/** The work authorization. */
	@Field("work_authorization")
	private String workAuthorization;

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the desired employment.
	 *
	 * @return the desired employment
	 */
	public String getDesiredEmployment() {
		return desiredEmployment;
	}

	/**
	 * Sets the desired employment.
	 *
	 * @param desiredEmployment the new desired employment
	 */
	public void setDesiredEmployment(String desiredEmployment) {
		this.desiredEmployment = desiredEmployment;
	}

	/**
	 * Gets the desired job.
	 *
	 * @return the desired job
	 */
	public String getDesiredJob() {
		return desiredJob;
	}

	/**
	 * Sets the desired job.
	 *
	 * @param desiredJob the new desired job
	 */
	public void setDesiredJob(String desiredJob) {
		this.desiredJob = desiredJob;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the html resume text.
	 *
	 * @return the html resume text
	 */
	public String getHtmlResumeText() {
		return htmlResumeText;
	}

	/**
	 * Sets the html resume text.
	 *
	 * @param htmlResumeText the new html resume text
	 */
	public void setHtmlResumeText(String htmlResumeText) {
		this.htmlResumeText = htmlResumeText;
	}

	/**
	 * Gets the post dt.
	 *
	 * @return the post dt
	 */
	public Date getPostDt() {
		return postDt;
	}

	/**
	 * Sets the post dt.
	 *
	 * @param postDt the new post dt
	 */
	public void setPostDt(Date postDt) {
		this.postDt = postDt;
	}

	/**
	 * Gets the post code.
	 *
	 * @return the post code
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * Sets the post code.
	 *
	 * @param postCode the new post code
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * Gets the publish resume id.
	 *
	 * @return the publish resume id
	 */
	public int getPublishResumeId() {
		return publishResumeId;
	}

	/**
	 * Sets the publish resume id.
	 *
	 * @param publishResumeId the new publish resume id
	 */
	public void setPublishResumeId(int publishResumeId) {
		this.publishResumeId = publishResumeId;
	}

	/**
	 * Gets the res geo.
	 *
	 * @return the res geo
	 */
	public String getResGeo() {
		return resGeo;
	}

	/**
	 * Sets the res geo.
	 *
	 * @param resGeo the new res geo
	 */
	public void setResGeo(String resGeo) {
		this.resGeo = resGeo;
	}

	/**
	 * Gets the resume name.
	 *
	 * @return the resume name
	 */
	public String getResumeName() {
		return resumeName;
	}

	/**
	 * Sets the resume name.
	 *
	 * @param resumeName the new resume name
	 */
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	/**
	 * Gets the resume text.
	 *
	 * @return the resume text
	 */
	public String getResumeText() {
		return resumeText;
	}

	/**
	 * Sets the resume text.
	 *
	 * @param resumeText the new resume text
	 */
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the will relocate.
	 *
	 * @return the will relocate
	 */
	public String getWillRelocate() {
		return willRelocate;
	}

	/**
	 * Sets the will relocate.
	 *
	 * @param willRelocate the new will relocate
	 */
	public void setWillRelocate(String willRelocate) {
		this.willRelocate = willRelocate;
	}

	/**
	 * Gets the work authorization.
	 *
	 * @return the work authorization
	 */
	public String getWorkAuthorization() {
		return workAuthorization;
	}

	/**
	 * Sets the work authorization.
	 *
	 * @param workAuthorization the new work authorization
	 */
	public void setWorkAuthorization(String workAuthorization) {
		this.workAuthorization = workAuthorization;
	}
	

	
	
	
}
