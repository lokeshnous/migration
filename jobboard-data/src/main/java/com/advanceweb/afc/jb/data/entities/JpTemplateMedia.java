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
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the jp_template_media database table.
 * 
 */
@Entity
@Table(name="jp_template_media")
public class JpTemplateMedia implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The template media id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="template_media_id")
	private int templateMediaId;

	/** The media path. */
	@Column(name="media_path")
	private String mediaPath;

	/** The media type. */
	@Column(name="media_type")
	private String mediaType;

	//bi-directional many-to-one association to JpTemplate
	/** The jp template. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="template_id")
	private JpTemplate jpTemplate;

	/**
	 * Gets the template media id.
	 *
	 * @return the template media id
	 */
	public int getTemplateMediaId() {
		return this.templateMediaId;
	}

	/**
	 * Sets the template media id.
	 *
	 * @param templateMediaId the new template media id
	 */
	public void setTemplateMediaId(int templateMediaId) {
		this.templateMediaId = templateMediaId;
	}

	/**
	 * Gets the media path.
	 *
	 * @return the media path
	 */
	public String getMediaPath() {
		return this.mediaPath;
	}

	/**
	 * Sets the media path.
	 *
	 * @param mediaPath the new media path
	 */
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	/**
	 * Gets the media type.
	 *
	 * @return the media type
	 */
	public String getMediaType() {
		return this.mediaType;
	}

	/**
	 * Sets the media type.
	 *
	 * @param mediaType the new media type
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
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