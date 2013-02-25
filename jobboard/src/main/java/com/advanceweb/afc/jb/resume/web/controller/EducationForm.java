/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class EducationForm {
	
	/** The degree lvl. */
	private String degreeLvl;
	
	/** The field of study. */
	private String fieldOfStudy;
	
	/** The start date. */
	private String startDate;
	
	/** The end date. */
	private String endDate;
	
	/** The institute name. */
	private String instituteName;
	
	/** The degrees. */
	private String degrees;
	
	/** The certifications. */
	private String certifications;
	
	/** The language. */
	private String language;
	
	/** The b not graduated yet. */
	private boolean bNotGraduatedYet;
	
	/** The builder edu id. */
	private int builderEduId;
	
	/** The item id. */
	private int itemId;
	
	/** The edit mode. */
	private boolean editMode;
	
	
	
	
	
	/**
	 * Checks if is edits the mode.
	 *
	 * @return true, if is edits the mode
	 */
	public boolean isEditMode() {
		return editMode;
	}
	
	/**
	 * Sets the edits the mode.
	 *
	 * @param editMode the new edits the mode
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	public int getItemId() {
		return itemId;
	}
	
	/**
	 * Sets the item id.
	 *
	 * @param itemId the new item id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * Gets the degree lvl.
	 *
	 * @return the degree lvl
	 */
	public String getDegreeLvl() {
		return degreeLvl;
	}
	
	/**
	 * Sets the degree lvl.
	 *
	 * @param degreeLvl the new degree lvl
	 */
	public void setDegreeLvl(String degreeLvl) {
		this.degreeLvl = degreeLvl;
	}
	
	/**
	 * Gets the field of study.
	 *
	 * @return the field of study
	 */
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	
	/**
	 * Sets the field of study.
	 *
	 * @param fieldOfStudy the new field of study
	 */
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Gets the institute name.
	 *
	 * @return the institute name
	 */
	public String getInstituteName() {
		return instituteName;
	}
	
	/**
	 * Sets the institute name.
	 *
	 * @param instituteName the new institute name
	 */
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	
	/**
	 * Gets the degrees.
	 *
	 * @return the degrees
	 */
	public String getDegrees() {
		return degrees;
	}
	
	/**
	 * Sets the degrees.
	 *
	 * @param degrees the new degrees
	 */
	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}	
	
	/**
	 * Gets the certifications.
	 *
	 * @return the certifications
	 */
	public String getCertifications() {
		return certifications;
	}
	
	/**
	 * Sets the certifications.
	 *
	 * @param certifications the new certifications
	 */
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
	
	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * Gets the builder edu id.
	 *
	 * @return the builder edu id
	 */
	public int getBuilderEduId() {
		return builderEduId;
	}
	
	/**
	 * Sets the builder edu id.
	 *
	 * @param builderEduId the new builder edu id
	 */
	public void setBuilderEduId(int builderEduId) {
		this.builderEduId = builderEduId;
	}
	
	/**
	 * Checks if is b not graduated yet.
	 *
	 * @return true, if is b not graduated yet
	 */
	public boolean isbNotGraduatedYet() {
		return bNotGraduatedYet;
	}
	
	/**
	 * Sets the b not graduated yet.
	 *
	 * @param bNotGraduatedYet the new b not graduated yet
	 */
	public void setbNotGraduatedYet(boolean bNotGraduatedYet) {
		this.bNotGraduatedYet = bNotGraduatedYet;
	}
	
	
}
