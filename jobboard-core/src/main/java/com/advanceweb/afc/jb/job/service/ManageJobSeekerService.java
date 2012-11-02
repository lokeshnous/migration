package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;


/**
 * @Author : Devi Prasad
   @Version: 1.0
   @Created: Oct 15, 2012
   @Purpose: This will work as a service interface for  Manage Job Seeker
 */
public interface ManageJobSeekerService {
/**
 * Method to retrieve all resumes
 * @param userId
 * @return
 * @throws JobBoardServiceException
 */
	List<ManageJobSeekerDTO> retrieveAllResume(int userId) throws JobBoardServiceException;
	/**
	 * Method to retrieve all resumes by Folder
	 * @param userId
	 * @param folderId
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId,int folderId) throws JobBoardServiceException;
	/**
	 * Method to retrieve application status details
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<DropDownDTO> applicationStatusList() throws JobBoardServiceException;
	/**
	 * Method to retrieve all folder Details
	 * @param userId
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<AdmFolderDTO> folderDetailList(int userId) throws JobBoardServiceException;
	/**
	 * Method to update Application Status
	 * @param appStatusId
	 * @param resumeId
	 * @return
	 * @throws JobBoardServiceException
	 */
	boolean updateAppStatus(int appStatusId,int resumeId) throws JobBoardServiceException;
	/**
	 * Method to update Resume Rating
	 * @param resumeId
	 * @param rating
	 * @return
	 * @throws JobBoardServiceException
	 */
	boolean updateRatings(int rating,int resumeId) throws JobBoardServiceException;
	/**
	 * Method to update folder details 
	 * @param folderId
	 * @param folderResumeId
	 */
	boolean updateResumeFolder(int folderId, int folderResumeId) throws JobBoardServiceException;
	/**Method to delete the job seeker detail from adm folder resume
	 * @param resumeId
	 */
	void deleteJobSeeker(int resumeId) throws JobBoardServiceException;
	/**Method to Add New Folder folder 
	 * @param userId
	 * @param folderName
	 */
	void addFolder(int userId,String folderName) throws JobBoardServiceException;
	/**
	 * Method to remove folder 
	 * @param userId
	 * @param folderName
	 * @throws JobBoardServiceException
	 */
	void removeFolder(int userId,String folderName) throws JobBoardServiceException;
	/**
	 * Method to Rename Folder
	 * @param userId
	 * @param folderName
	 * @throws JobBoardServiceException
	 */
	void renameFolder(int userId,int folderId,String folderName) throws JobBoardServiceException;
	/**
	 * 
	 * @param userId
	 * @param offset
	 * @param noOfRecords
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<ManageJobSeekerDTO> retrieveAllResume(int userId, int offset, int noOfRecords) throws JobBoardServiceException;
	/**
	 * 
	 * @param userId
	 * @param folderId
	 * @param offset
	 * @param noOfRecords
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId, int folderId, int offset, int noOfRecords) throws JobBoardServiceException;
	/**
	 * Get total number of records
	 * @param employerId
	 * @return
	 */
	int getTotalNumberOfRecords(int employerId) throws JobBoardServiceException;
	}
