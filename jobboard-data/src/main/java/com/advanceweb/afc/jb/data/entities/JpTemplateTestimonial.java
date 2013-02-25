/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the jp_template_testimonial database table.
 * 
 */
@Entity
@Table(name="jp_template_testimonial")
public class JpTemplateTestimonial implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The template testimonial id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="template_testimonial_id")
	private int templateTestimonialId;

    /** The testimonial. */
    @Lob()
	private String testimonial;

	//bi-directional many-to-one association to JpTemplate
	/** The jp template. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="template_id")
	private JpTemplate jpTemplate;

	/**
	 * Gets the template testimonial id.
	 *
	 * @return the template testimonial id
	 */
	public int getTemplateTestimonialId() {
		return this.templateTestimonialId;
	}

	/**
	 * Sets the template testimonial id.
	 *
	 * @param templateTestimonialId the new template testimonial id
	 */
	public void setTemplateTestimonialId(int templateTestimonialId) {
		this.templateTestimonialId = templateTestimonialId;
	}

	/**
	 * Gets the testimonial.
	 *
	 * @return the testimonial
	 */
	public String getTestimonial() {
		return this.testimonial;
	}

	/**
	 * Sets the testimonial.
	 *
	 * @param testimonial the new testimonial
	 */
	public void setTestimonial(String testimonial) {
		this.testimonial = testimonial;
	}

	/**
	 * Gets the jp template.
	 *
	 * @return the jp template
	 */
	public JpTemplate getJpTemplate() {
		return this.jpTemplate;
	}

	/**
	 * Sets the jp template.
	 *
	 * @param jpTemplate the new jp template
	 */
	public void setJpTemplate(JpTemplate jpTemplate) {
		this.jpTemplate = jpTemplate;
	}
	
}