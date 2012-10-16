package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the adm_folder database table.
 * 
 */
@Entity
@Table(name="adm_folder")
public class AdmFolder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="folder_id")
	private int folderId;

	@Column(name="folder_name")
	private String folderName;

	@Column(name="parent_folder_id")
	private int parentFolderId;

	@Column(name="user_id")
	private int userId;

    public AdmFolder() {
    }

	public int getFolderId() {
		return this.folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public int getParentFolderId() {
		return this.parentFolderId;
	}

	public void setParentFolderId(int parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}