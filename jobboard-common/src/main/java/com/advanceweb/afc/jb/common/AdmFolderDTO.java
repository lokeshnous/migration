/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Devi Prasad
   @Version: 1.0
   @Created: Oct 15, 2012
   @Purpose: This class will act as a DTO for Manage Job seeker
 */
public class AdmFolderDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The folder id. */
	private int folderId;
	
	/** The folder name. */
	private String folderName;
	
	/** The parent folder id. */
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
