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

	List<ManageJobSeekerDTO> retrieveAllResume(int userId) throws JobBoardServiceException;
	List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId,int folderId) throws JobBoardServiceException;
	List<DropDownDTO> applicationStatusList() throws JobBoardServiceException;
	List<AdmFolderDTO> folderDetailList(int userId) throws JobBoardServiceException;
	boolean updateJobSeeker(int appStatusId,int resumeId,int rating) throws JobBoardServiceException;
	/**
	 * @param folderId
	 * @param folderResumeId
	 */
	boolean updateResumeFolder(int folderId, int folderResumeId) throws JobBoardServiceException;
	
	
}
