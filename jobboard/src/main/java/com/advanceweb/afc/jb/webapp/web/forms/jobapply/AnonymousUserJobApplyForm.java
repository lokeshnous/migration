package com.advanceweb.afc.jb.webapp.web.forms.jobapply;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a Form Bean for the  Anonymous User to apply for the job
 */
public class AnonymousUserJobApplyForm {

	private String userName;
	private String userEmail;
	private String filePath;
	private CommonsMultipartFile fileContent;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
