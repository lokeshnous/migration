/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.dto;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CreateResumeDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2200423309708244707L;
	
	/** The resume text. */
	private String resumeText;
	
	/** The resume name. */
	private String resumeName;
	
	/** The desired job title. */
	private String desiredJobTitle;
	
	/** The desired employment type. */
	private String desiredEmploymentType;
	
	/** The work authorization us. */
	private String workAuthorizationUS;
	
	/** The willing to relocate. */
	private String willingToRelocate;
	
	/** The resume visibility. */
	private String resumeVisibility;
	
	/** The resume type. */
	private String resumeType;
	
	/** The employment type. */
	private String employmentType;
	
	/** The file data. */
	private CommonsMultipartFile fileData;
	
	/** The file server. */
	private String fileServer;
	
	/** The file path. */
	private String filePath;
	
	/** The file name. */
	private String fileName;
	
	/** The is published. */
	private String isPublished;
	
	
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
	 * Gets the desired job title.
	 *
	 * @return the desired job title
	 */
	public String getDesiredJobTitle() {
		return desiredJobTitle;
	}
	
	/**
	 * Sets the desired job title.
	 *
	 * @param desiredJobTitle the new desired job title
	 */
	public void setDesiredJobTitle(String desiredJobTitle) {
		this.desiredJobTitle = desiredJobTitle;
	}
	
	/**
	 * Gets the desired employment type.
	 *
	 * @return the desired employment type
	 */
	public String getDesiredEmploymentType() {
		return desiredEmploymentType;
	}
	
	/**
	 * Sets the desired employment type.
	 *
	 * @param desiredEmploymentType the new desired employment type
	 */
	public void setDesiredEmploymentType(String desiredEmploymentType) {
		this.desiredEmploymentType = desiredEmploymentType;
	}
	
	/**
	 * Gets the work authorization us.
	 *
	 * @return the work authorization us
	 */
	public String getWorkAuthorizationUS() {
		return workAuthorizationUS;
	}
	
	/**
	 * Sets the work authorization us.
	 *
	 * @param workAuthorizationUS the new work authorization us
	 */
	public void setWorkAuthorizationUS(String workAuthorizationUS) {
		this.workAuthorizationUS = workAuthorizationUS;
	}
	
	/**
	 * Gets the willing to relocate.
	 *
	 * @return the willing to relocate
	 */
	public String getWillingToRelocate() {
		return willingToRelocate;
	}
	
	/**
	 * Sets the willing to relocate.
	 *
	 * @param willingToRelocate the new willing to relocate
	 */
	public void setWillingToRelocate(String willingToRelocate) {
		this.willingToRelocate = willingToRelocate;
	}
	
	/**
	 * Gets the resume visibility.
	 *
	 * @return the resume visibility
	 */
	public String getResumeVisibility() {
		return resumeVisibility;
	}
	
	/**
	 * Sets the resume visibility.
	 *
	 * @param resumeVisibility the new resume visibility
	 */
	public void setResumeVisibility(String resumeVisibility) {
		this.resumeVisibility = resumeVisibility;
	}
	
	/**
	 * Gets the resume type.
	 *
	 * @return the resume type
	 */
	public String getResumeType() {
		return resumeType;
	}
	
	/**
	 * Sets the resume type.
	 *
	 * @param resumeType the new resume type
	 */
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}
	
	/**
	 * Gets the employment type.
	 *
	 * @return the employment type
	 */
	public String getEmploymentType() {
		return employmentType;
	}
	
	/**
	 * Sets the employment type.
	 *
	 * @param employmentType the new employment type
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	
	/**
	 * Gets the file data.
	 *
	 * @return the file data
	 */
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	
	/**
	 * Sets the file data.
	 *
	 * @param fileData the new file data
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	
	/**
	 * Gets the file server.
	 *
	 * @return the file server
	 */
	public String getFileServer() {
		return fileServer;
	}
	
	/**
	 * Sets the file server.
	 *
	 * @param fileServer the new file server
	 */
	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}
	
	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * Sets the file path.
	 *
	 * @param filePath the new file path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Gets the checks if is published.
	 *
	 * @return the checks if is published
	 */
	public String getIsPublished() {
		return isPublished;
	}
	
	/**
	 * Sets the checks if is published.
	 *
	 * @param isPublished the new checks if is published
	 */
	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}
	




}
