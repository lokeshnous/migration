/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

/**
 * @Author : Devi Prasad
 * @Version: 1.0
 * @Created: Oct 15, 2012
 * @Purpose: This interface defines all the DAO operations related to Manage Job
 *           seeker
 */
public interface ManageJobSeekerDAO {

	/**
	 * Retrieve all resume.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws JobBoardDataException the job board data exception
	 */
	List<ManageJobSeekerDTO> retrieveAllResume(int userId)
			throws JobBoardDataException;

	/**
	 * Application status list.
	 *
	 * @return the list
	 * @throws JobBoardDataException the job board data exception
	 */
	List<DropDownDTO> applicationStatusList() throws JobBoardDataException;

	/**
	 * Folder detail list.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws JobBoardDataException the job board data exception
	 */
	List<AdmFolderDTO> folderDetailList(int userId)
			throws JobBoardDataException;

	/**
	 * Update app status.
	 *
	 * @param appStatusId the app status id
	 * @param resumeId the resume id
	 * @return true, if successful
	 * @throws JobBoardDataException the job board data exception
	 */
	boolean updateAppStatus(int appStatusId, int resumeId)
			throws JobBoardDataException;

	/**
	 * Update rating.
	 *
	 * @param rating the rating
	 * @param resumeId the resume id
	 * @return true, if successful
	 * @throws JobBoardDataException the job board data exception
	 */
	boolean updateRating(int rating, int resumeId) throws JobBoardDataException;

	/**
	 * Retrieve all resume by folder.
	 *
	 * @param userId the user id
	 * @param folderId the folder id
	 * @return the list
	 * @throws JobBoardDataException the job board data exception
	 */
	List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId, int folderId)
			throws JobBoardDataException;

	/**
	 * Update resume folder.
	 *
	 * @param folderId the folder id
	 * @param folderResumeId the folder resume id
	 * @return true, if successful
	 * @throws JobBoardDataException the job board data exception
	 */
	boolean updateResumeFolder(int folderId, int folderResumeId)
			throws JobBoardDataException;

	/**
	 * Delete job seeker.
	 *
	 * @param folderResumeId the folder resume id
	 * @throws JobBoardDataException the job board data exception
	 */
	void deleteJobSeeker(int folderResumeId) throws JobBoardDataException;

	/**
	 * Adds the folder.
	 *
	 * @param userId the user id
	 * @param folderName the folder name
	 * @throws JobBoardDataException the job board data exception
	 */
	void addFolder(int userId, String folderName) throws JobBoardDataException;

	/**
	 * Removes the folder.
	 *
	 * @param userId the user id
	 * @param folderName the folder name
	 * @throws JobBoardDataException the job board data exception
	 */
	void removeFolder(int userId, String folderName)
			throws JobBoardDataException;

	/**
	 * Rename folder.
	 *
	 * @param userId the user id
	 * @param folderId the folder id
	 * @param folderName the folder name
	 * @throws JobBoardDataException the job board data exception
	 */
	void renameFolder(int userId, int folderId, String folderName)
			throws JobBoardDataException;

	/**
	 * Retrieve all resume.
	 *
	 * @param userId the user id
	 * @param offset the offset
	 * @param noOfRecords the no of records
	 * @return the list
	 * @throws JobBoardDataException the job board data exception
	 */
	List<ManageJobSeekerDTO> retrieveAllResume(int userId, int offset,
			int noOfRecords) throws JobBoardDataException;

	/**
	 * Retrieve all resume by folder.
	 *
	 * @param userId the user id
	 * @param folderId the folder id
	 * @param offset the offset
	 * @param noOfRecords the no of records
	 * @return the list
	 * @throws JobBoardDataException the job board data exception
	 */
	List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId,
			int folderId, int offset, int noOfRecords)
			throws JobBoardDataException;

	/**
	 * Gets the total number of job records.
	 *
	 * @param employerId the employer id
	 * @param folderId the folder id
	 * @return the total number of job records
	 * @throws JobBoardDataException the job board data exception
	 */
	int getTotalNumberOfJobRecords(int employerId, int folderId)
			throws JobBoardDataException;
}
