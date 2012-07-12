package com.advanceweb.afc.jb.common;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Author : Prince Mathew
   @Version: 1.0 
   @Created: Jul 12, 2012
   @Purpose: This class will act as a DTO for the Anonymous User to apply for job
 */
public class AnonymousUserJobApplyDTO implements Serializable {

	private String name;
	private String email;
	private String fileName;
	private CommonsMultipartFile fileContent;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileContent
	 */
	public CommonsMultipartFile getFileContent() {
		return fileContent;
	}
	/**
	 * @param fileContent the fileContent to set
	 */
	public void setFileContent(CommonsMultipartFile fileContent) {
		this.fileContent = fileContent;
	}
	
	
	
	
}
