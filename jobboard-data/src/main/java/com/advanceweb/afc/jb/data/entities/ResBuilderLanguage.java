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
 * The persistent class for the res_builder_language database table.
 * 
 */
@Entity
@Table(name="res_builder_language")
public class ResBuilderLanguage implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The builder language id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_language_id")
	private int builderLanguageId;

	/** The exp level. */
	@Column(name="exp_level")
	private String expLevel;

	/** The language name. */
	@Column(name="language_name")
	private String languageName;

	//bi-directional many-to-one association to ResBuilderResume
	/** The res builder resume. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="builder_resume_id")
	private ResBuilderResume resBuilderResume;

	/**
	 * Gets the builder language id.
	 *
	 * @return the builder language id
	 */
	public int getBuilderLanguageId() {
		return this.builderLanguageId;
	}

	/**
	 * Sets the builder language id.
	 *
	 * @param builderLanguageId the new builder language id
	 */
	public void setBuilderLanguageId(int builderLanguageId) {
		this.builderLanguageId = builderLanguageId;
	}

	/**
	 * Gets the exp level.
	 *
	 * @return the exp level
	 */
	public String getExpLevel() {
		return this.expLevel;
	}

	/**
	 * Sets the exp level.
	 *
	 * @param expLevel the new exp level
	 */
	public void setExpLevel(String expLevel) {
		this.expLevel = expLevel;
	}

	/**
	 * Gets the language name.
	 *
	 * @return the language name
	 */
	public String getLanguageName() {
		return this.languageName;
	}

	/**
	 * Sets the language name.
	 *
	 * @param languageName the new language name
	 */
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	/**
	 * Gets the res builder resume.
	 *
	 * @return the res builder resume
	 */
	public ResBuilderResume getResBuilderResume() {
		return this.resBuilderResume;
	}

	/**
	 * Sets the res builder resume.
	 *
	 * @param resBuilderResume the new res builder resume
	 */
	public void setResBuilderResume(ResBuilderResume resBuilderResume) {
		this.resBuilderResume = resBuilderResume;
	}
	
}