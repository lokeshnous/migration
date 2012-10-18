package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Devi Prasad
   @Version: 1.0
   @Created: Oct 15, 2012
   @Purpose: This class will act as a DTO for Manage Job seeker
 */
public class AdmFolderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int folderId;
	private String folderName;
	private int parentFolderId;

	/**
	 * @return the folderId
	 */
	public int getFolderId() {
		return folderId;
	}

	/**
	 * @param folderId
	 *            the folderId to set
	 */
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * @param folderName
	 *            the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	/**
	 * @return the parentFolderId
	 */
	public int getParentFolderId() {
		return parentFolderId;
	}

	/**
	 * @param parentFolderId
	 *            the parentFolderId to set
	 */
	public void setParentFolderId(int parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

}
