/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the jp_template database table.
 * 
 */
@Entity
@Table(name="jp_template")
public class JpTemplate implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The template id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="template_id")
	private int templateId;

	/** The color palette. */
	@Column(name="color_palette")
	private String colorPalette;

    /** The company overview. */
    @Lob()
	@Column(name="company_overview")
	private String companyOverview;

    /** The create dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;
    
    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;
    
	/** The create user id. */
	@Column(name="create_user_id")
	private int createUserId;

	/** The logo path. */
	@Column(name="logo_path")
	private String logoPath;

	/** The main image path. */
	@Column(name="main_image_path")
	private String mainImagePath;

	/** The template name. */
	@Column(name="template_name")
	private String templateName;

	//bi-directional many-to-one association to JpJob
	/** The jp jobs. */
	@OneToMany(mappedBy="jpTemplate")
	private List<JpJob> jpJobs;

	/** The delete user id. */
	@Column(name="delete_user_id")
	private int deleteUserId;
	
	//bi-directional many-to-one association to AdmFacility
	/** The adm facility. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id")
	private AdmFacility admFacility;

	//bi-directional many-to-one association to JpTemplateMedia
	/** The jp template medias. */
	@OneToMany(mappedBy="jpTemplate",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<JpTemplateMedia> jpTemplateMedias;

	//bi-directional many-to-one association to JpTemplateTestimonial
	/** The jp template testimonials. */
	@OneToMany(mappedBy="jpTemplate",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<JpTemplateTestimonial> jpTemplateTestimonials;

//    public JpTemplate() {
//    }

	
	/**
 * Gets the template id.
 *
 * @return the template id
 */
public int getTemplateId() {
		return this.templateId;
	}

	/**
	 * Gets the delete dt.
	 *
	 * @return the delete dt
	 */
	public Date getDeleteDt() {
		return deleteDt;
	}

	/**
	 * Sets the delete dt.
	 *
	 * @param deleteDt the new delete dt
	 */
	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	/**
	 * Sets the template id.
	 *
	 * @param templateId the new template id
	 */
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	/**
	 * Gets the color palette.
	 *
	 * @return the color palette
	 */
	public String getColorPalette() {
		return this.colorPalette;
	}

	/**
	 * Sets the color palette.
	 *
	 * @param colorPalette the new color palette
	 */
	public void setColorPalette(String colorPalette) {
		this.colorPalette = colorPalette;
	}

	/**
	 * Gets the company overview.
	 *
	 * @return the company overview
	 */
	public String getCompanyOverview() {
		return this.companyOverview;
	}

	/**
	 * Sets the company overview.
	 *
	 * @param companyOverview the new company overview
	 */
	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Date getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the creates the user id.
	 *
	 * @return the creates the user id
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * Sets the creates the user id.
	 *
	 * @param createUserId the new creates the user id
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

//	public MerUser getMerUser() {
//		return merUser;
//	}
//
//	public void setMerUser(MerUser merUser) {
//		this.merUser = merUser;
//	}

	/**
 * Gets the jp jobs.
 *
 * @return the jp jobs
 */
public List<JpJob> getJpJobs() {
		return jpJobs;
	}

	/**
	 * Sets the jp jobs.
	 *
	 * @param jpJobs the new jp jobs
	 */
	public void setJpJobs(List<JpJob> jpJobs) {
		this.jpJobs = jpJobs;
	}

	/**
	 * Gets the delete user id.
	 *
	 * @return the delete user id
	 */
	public int getDeleteUserId() {
		return deleteUserId;
	}

	/**
	 * Sets the delete user id.
	 *
	 * @param deleteUserId the new delete user id
	 */
	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	/**
	 * Gets the logo path.
	 *
	 * @return the logo path
	 */
	public String getLogoPath() {
		return this.logoPath;
	}

	
	/**
	 * Sets the logo path.
	 *
	 * @param logoPath the new logo path
	 */
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	/**
	 * Gets the main image path.
	 *
	 * @return the main image path
	 */
	public String getMainImagePath() {
		return this.mainImagePath;
	}

	/**
	 * Sets the main image path.
	 *
	 * @param mainImagePath the new main image path
	 */
	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}

	/**
	 * Gets the template name.
	 *
	 * @return the template name
	 */
	public String getTemplateName() {
		return this.templateName;
	}

	/**
	 * Sets the template name.
	 *
	 * @param templateName the new template name
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

//	public List<JpJob> getJpJobs() {
//		return this.jpJobs;
//	}
//
//	public void setJpJobs(List<JpJob> jpJobs) {
//		this.jpJobs = jpJobs;
//	}
	
	/**
 * Gets the adm facility.
 *
 * @return the adm facility
 */
public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	/**
	 * Sets the adm facility.
	 *
	 * @param admFacility the new adm facility
	 */
	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
	
	/**
	 * Gets the jp template medias.
	 *
	 * @return the jp template medias
	 */
	public List<JpTemplateMedia> getJpTemplateMedias() {
		return this.jpTemplateMedias;
	}

	/**
	 * Sets the jp template medias.
	 *
	 * @param jpTemplateMedias the new jp template medias
	 */
	public void setJpTemplateMedias(List<JpTemplateMedia> jpTemplateMedias) {
		this.jpTemplateMedias = jpTemplateMedias;
	}
	
	/**
	 * Gets the jp template testimonials.
	 *
	 * @return the jp template testimonials
	 */
	public List<JpTemplateTestimonial> getJpTemplateTestimonials() {
		return this.jpTemplateTestimonials;
	}

	/**
	 * Sets the jp template testimonials.
	 *
	 * @param jpTemplateTestimonials the new jp template testimonials
	 */
	public void setJpTemplateTestimonials(List<JpTemplateTestimonial> jpTemplateTestimonials) {
		this.jpTemplateTestimonials = jpTemplateTestimonials;
	}
	
}