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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the adm_folder database table.
 * 
 */
@Entity
@Table(name="adm_folder")
public class AdmFolder implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The folder id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="folder_id")
	private int folderId;

	/** The folder name. */
	@Column(name="folder_name")
	private String folderName;

	/** The parent folder id. */
	@Column(name="parent_folder_id")
	private int parentFolderId;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

    /**
     * Instantiates a new adm folder.
     */
    public AdmFolder() {
    }

	/**
	 * Gets the folder id.
	 *
	 * @return the folder id
	 */
	public int getFolderId() {
		return this.folderId;
	}

	/**
	 * Sets the folder id.
	 *
	 * @param folderId the new folder id
	 */
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	/**
	 * Gets the folder name.
	 *
	 * @return the folder name
	 */
	public String getFolderName() {
		return this.folderName;
	}

	/**
	 * Sets the folder name.
	 *
	 * @param folderName the new folder name
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	/**
	 * Gets the parent folder id.
	 *
	 * @return the parent folder id
	 */
	public int getParentFolderId() {
		return this.parentFolderId;
	}

	/**
	 * Sets the parent folder id.
	 *
	 * @param parentFolderId the new parent folder id
	 */
	public void setParentFolderId(int parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}