/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;


/**
 * @Author : Devi mishra
   @Version: 1.0
   @Created: Oct 09, 2012
   @Purpose: This class will act as a Form Bean for the manage job seeker 
 */
public class ManageJobSeekerForm {

	
    /** The no of page. */
    private int noOfPage;
    
    /** The no of page lower. */
    private int noOfPageLower;
    
    /** The begin val. */
    private int beginVal=1;  
    
    /** The resume id. */
    private int resumeId;
	
	/** The resume name. */
	private String resumeName;
	
	/** The rating. */
	private int rating;
	
	/** The application status. */
	private int applicationStatus;
	
	/** The saved date. */
	private String savedDate;
	
	/** The selected row. */
	private String selectedRow;
	
	/** The manage job seeker dto list. */
	private List<ManageJobSeekerDTO> manageJobSeekerDTOList;
	
	/** The folder id. */
	private int folderId;
	
	/** The folder name. */
	private String folderName;
	
	/** The adm folder dto list. */
	private List<AdmFolderDTO> admFolderDTOList; 
	
	/** The resume dto list. */
	private List<ResumeDTO> resumeDTOList;
	
	/** The total record for comp. */
	private int totalRecordForComp;

	/**
	 * @return the noOfPage
	 */
	public int getNoOfPage() {
		return noOfPage;
	}

	/**
	 * @param noOfPage the noOfPage to set
	 */
	public void setNoOfPage(int noOfPage) {
		this.noOfPage = noOfPage;
	}

	/**
	 * @return the noOfPageLower
	 */
	public int getNoOfPageLower() {
		return noOfPageLower;
	}

	/**
	 * @param noOfPageLower the noOfPageLower to set
	 */
	public void setNoOfPageLower(int noOfPageLower) {
		this.noOfPageLower = noOfPageLower;
	}

	/**
	 * Gets the begin val.
	 *
	 * @return the begin val
	 */
	public int getBeginVal() {
		return beginVal;
	}

	/**
	 * Sets the begin val.
	 *
	 * @param beginVal the new begin val
	 */
	public void setBeginVal(int beginVal) {
		this.beginVal = beginVal;
	}

	/**
	 * @return the resumeId
	 */
	public int getResumeId() {
		return resumeId;
	}

	/**
	 * @param resumeId the resumeId to set
	 */
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	/**
	 * @return the resumeName
	 */
	public String getResumeName() {
		return resumeName;
	}

	/**
	 * @param resumeName the resumeName to set
	 */
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the applicationStatus
	 */
	public int getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * @param applicationStatus the applicationStatus to set
	 */
	public void setApplicationStatus(int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * @return the savedDate
	 */
	public String getSavedDate() {
		return savedDate;
	}

	/**
	 * @param savedDate the savedDate to set
	 */
	public void setSavedDate(String savedDate) {
		this.savedDate = savedDate;
	}

	/**
	 * @return the selectedRow
	 */
	public String getSelectedRow() {
		return selectedRow;
	}

	/**
	 * @param selectedRow the selectedRow to set
	 */
	public void setSelectedRow(String selectedRow) {
		this.selectedRow = selectedRow;
	}

	/**
	 * @return the manageJobSeekerDTOList
	 */
	public List<ManageJobSeekerDTO> getManageJobSeekerDTOList() {
		return manageJobSeekerDTOList;
	}

	/**
	 * @param manageJobSeekerDTOList the manageJobSeekerDTOList to set
	 */
	public void setManageJobSeekerDTOList(
			List<ManageJobSeekerDTO> manageJobSeekerDTOList) {
		this.manageJobSeekerDTOList = manageJobSeekerDTOList;
	}

	/**
	 * @return the folderId
	 */
	public int getFolderId() {
		return folderId;
	}

	/**
	 * @param folderId the folderId to set
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
	 * @param folderName the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	/**
	 * @return the admFolderDTOList
	 */
	public List<AdmFolderDTO> getAdmFolderDTOList() {
		return admFolderDTOList;
	}

	/**
	 * @param admFolderDTOList the admFolderDTOList to set
	 */
	public void setAdmFolderDTOList(List<AdmFolderDTO> admFolderDTOList) {
		this.admFolderDTOList = admFolderDTOList;
	}

	/**
	 * @return the resumeDTOList
	 */
	public List<ResumeDTO> getResumeDTOList() {
		return resumeDTOList;
	}

	/**
	 * @return the totalRecordForComp
	 */
	public int getTotalRecordForComp() {
		return totalRecordForComp;
	}

	/**
	 * @param totalRecordForComp the totalRecordForComp to set
	 */
	public void setTotalRecordForComp(int totalRecordForComp) {
		this.totalRecordForComp = totalRecordForComp;
	}

	/**
	 * @param resumeDTOList the resumeDTOList to set
	 */
	public void setResumeDTOList(List<ResumeDTO> resumeDTOList) {
		this.resumeDTOList = resumeDTOList;
	}

	

}
