package com.advanceweb.afc.jb.dto;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CreateResumeDTO implements Serializable{
	
	private static final long serialVersionUID = -2200423309708244707L;
	
	private String resumeText;
	private String resumeName;
	private String desiredJobTitle;
	private String desiredEmploymentType;
	private String workAuthorizationUS;
	private String willingToRelocate;
	private String resumeVisibility;
	private String resumeType;
	private String employmentType;
	private CommonsMultipartFile fileData;
	private String fileServer;
	private String filePath;
	private String fileName;
	private String isPublished;
	
	
	public String getResumeText() {
		return resumeText;
	}
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getDesiredJobTitle() {
		return desiredJobTitle;
	}
	public void setDesiredJobTitle(String desiredJobTitle) {
		this.desiredJobTitle = desiredJobTitle;
	}
	public String getDesiredEmploymentType() {
		return desiredEmploymentType;
	}
	public void setDesiredEmploymentType(String desiredEmploymentType) {
		this.desiredEmploymentType = desiredEmploymentType;
	}
	public String getWorkAuthorizationUS() {
		return workAuthorizationUS;
	}
	public void setWorkAuthorizationUS(String workAuthorizationUS) {
		this.workAuthorizationUS = workAuthorizationUS;
	}
	public String getWillingToRelocate() {
		return willingToRelocate;
	}
	public void setWillingToRelocate(String willingToRelocate) {
		this.willingToRelocate = willingToRelocate;
	}
	public String getResumeVisibility() {
		return resumeVisibility;
	}
	public void setResumeVisibility(String resumeVisibility) {
		this.resumeVisibility = resumeVisibility;
	}
	public String getResumeType() {
		return resumeType;
	}
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	public String getFileServer() {
		return fileServer;
	}
	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}
	




}
