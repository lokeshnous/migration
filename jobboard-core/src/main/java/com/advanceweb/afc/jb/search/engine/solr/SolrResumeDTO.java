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

	private static final long serialVersionUID = 1L;
	
	@Field("city")
	private String city;
	
	@Field("desired_employment")
	private String desiredEmployment;
	
	@Field("desired_job")
	private String desiredJob;
	
	@Field("full_name")
	private String fullName;
	
	@Field("html_resume_text")
	private String htmlResumeText;
	
	@Field("post_dt")
	private Date postDt;
	
	@Field("postcode")
	private String postCode;
	
	@Field("publish_resume_id")
	private int publishResumeId;
	
	@Field("resgeo")
	private String resGeo;
	
	@Field("resume_name")
	private String resumeName;
	
	@Field("resume_text")
	private String resumeText;
	
	@Field("state")
	private String state;
	
	@Field("will_relocate")
	private String willRelocate;
	
	@Field("work_authorization")
	private String workAuthorization;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDesiredEmployment() {
		return desiredEmployment;
	}

	public void setDesiredEmployment(String desiredEmployment) {
		this.desiredEmployment = desiredEmployment;
	}

	public String getDesiredJob() {
		return desiredJob;
	}

	public void setDesiredJob(String desiredJob) {
		this.desiredJob = desiredJob;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHtmlResumeText() {
		return htmlResumeText;
	}

	public void setHtmlResumeText(String htmlResumeText) {
		this.htmlResumeText = htmlResumeText;
	}

	public Date getPostDt() {
		return postDt;
	}

	public void setPostDt(Date postDt) {
		this.postDt = postDt;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public int getPublishResumeId() {
		return publishResumeId;
	}

	public void setPublishResumeId(int publishResumeId) {
		this.publishResumeId = publishResumeId;
	}

	public String getResGeo() {
		return resGeo;
	}

	public void setResGeo(String resGeo) {
		this.resGeo = resGeo;
	}

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public String getResumeText() {
		return resumeText;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWillRelocate() {
		return willRelocate;
	}

	public void setWillRelocate(String willRelocate) {
		this.willRelocate = willRelocate;
	}

	public String getWorkAuthorization() {
		return workAuthorization;
	}

	public void setWorkAuthorization(String workAuthorization) {
		this.workAuthorization = workAuthorization;
	}
	

	
	
	
}
