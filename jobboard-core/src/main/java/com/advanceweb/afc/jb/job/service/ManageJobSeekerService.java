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
	 * Method to update Job seeker details
	 * @param appStatusId
	 * @param resumeId
	 * @param rating
	 * @return
	 * @throws JobBoardServiceException
	 */
	boolean updateJobSeeker(int appStatusId,int resumeId,int rating) throws JobBoardServiceException;
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

	}
