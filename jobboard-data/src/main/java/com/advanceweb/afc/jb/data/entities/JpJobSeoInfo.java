package com.advanceweb.afc.jb.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the jp_job_seoInfo database table.
 * 
 */
@Entity
@Table(name="jp_job_seoInfo")
public class JpJobSeoInfo{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="seoInfo_id")
	private int seoInfoId;

	@Column(name = "jobtitle")
	private String jobtitle;
	
	@Column(name = "meta_title")
	private String metaTitle;
	
	@Column(name = "meta_desc")
	private String metaDesc;
	
	@Column(name = "static_content")
	private String staticContent;

	@Column(name = "meta_keywords")
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
	 * @return the jobtitle
	 */
	public String getJobtitle() {
		return jobtitle;
	}

	/**
	 * @param jobtitle the jobtitle to set
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
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