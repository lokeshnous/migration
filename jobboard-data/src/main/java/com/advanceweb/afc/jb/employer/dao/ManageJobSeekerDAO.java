package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;



/**
 * @Author : Devi Prasad
   @Version: 1.0
   @Created: Oct 15, 2012
   @Purpose: This interface defines all the DAO operations related to Manage Job seeker
 */
public interface ManageJobSeekerDAO {
	List<ManageJobSeekerDTO> retrieveAllResume(int userId) throws JobBoardDataException;
	List<DropDownDTO> applicationStatusList() throws JobBoardDataException;
	List<AdmFolderDTO> folderDetailList(int userId) throws JobBoardDataException;
	boolean updateAppStatus(int appStatusId,int resumeId) throws JobBoardDataException;
	boolean updateRating(int rating,int resumeId) throws JobBoardDataException;
	List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId, int folderId) throws JobBoardDataException;
	boolean updateResumeFolder(int folderId, int folderResumeId)throws JobBoardDataException;
	void deleteJobSeeker(int folderResumeId)throws JobBoardDataException;
	void addFolder(int userId,String folderName)throws JobBoardDataException;
	void removeFolder(int userId,String folderName)throws JobBoardDataException;
	void renameFolder(int userId,int folderId,String folderName) throws JobBoardDataException;
	
}
