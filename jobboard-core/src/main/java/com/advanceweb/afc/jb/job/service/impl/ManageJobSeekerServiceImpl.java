package com.advanceweb.afc.jb.job.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO;
import com.advanceweb.afc.jb.job.service.ManageJobSeekerService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @Author : Devi Prasad
 * @Version: 1.0
 * @Created: Oct 15, 2012
 * @Purpose: This will work as a service Impl for Manage Job Seeker
 */
@Service("ManageJobSeekerService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class ManageJobSeekerServiceImpl implements ManageJobSeekerService {
	@Autowired
	private ManageJobSeekerDAO manageJobSeekerDAO;
	private static final Logger LOGGER = Logger
			.getLogger(ManageJobSeekerServiceImpl.class);

	/**
	 * Method to retrieve all Resume by the Employer
	 * 
	 * @param : userId
	 */
	@Override
	public List<ManageJobSeekerDTO> retrieveAllResume(int userId)
			throws JobBoardServiceException {
		List<ManageJobSeekerDTO> manageJobSeekerDTOs = new ArrayList<ManageJobSeekerDTO>();

		try {
			manageJobSeekerDTOs = manageJobSeekerDAO.retrieveAllResume(userId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while retriving resume..." + jdex);
		}

		return manageJobSeekerDTOs;
	}

	/**
	 * Method to retrieve all Application status List
	 */
	@Override
	public List<DropDownDTO> applicationStatusList()
			throws JobBoardServiceException {
		List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();

		try {
			dropDownDTOList = manageJobSeekerDAO.applicationStatusList();
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while retriving the application status details..." + jdex);
		}

		return dropDownDTOList;
	}

	/**
	 * Method to retrieve Folder details by user
	 */
	@Override
	public List<AdmFolderDTO> folderDetailList(int userId)
			throws JobBoardServiceException {
		List<AdmFolderDTO> admFolderDTOList = new ArrayList<AdmFolderDTO>();
		try {
			admFolderDTOList = manageJobSeekerDAO.folderDetailList(userId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while retriving the folder details..." + jdex);
		}

		return admFolderDTOList;
	}

	/** 
	 * Method to update application status and rating
	 */
	@Override
	public boolean updateAppStatus(int appStatusId, int resumeId) throws JobBoardServiceException  {
		boolean result=false;
		try {
			result= manageJobSeekerDAO.updateAppStatus(appStatusId, resumeId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while updating details..." + jdex);
		}
		return result;
	}

	/**
	 * Method to retrieve all resume by folder
	 */
	@Override
	public List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId,
			int folderId) throws JobBoardServiceException {
		List<ManageJobSeekerDTO> manageJobSeekerDTOs = new ArrayList<ManageJobSeekerDTO>();

		try {
			manageJobSeekerDTOs = manageJobSeekerDAO.retrieveAllResumeByFolder(userId,folderId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while retriving resume by folder..." + jdex);
		}

		return manageJobSeekerDTOs;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageJobSeekerService#updateResumeFolder(int, int)
	 */
	@Override
	public boolean updateResumeFolder(int folderId, int folderResumeId) throws JobBoardServiceException {
		boolean result=false;
		try {
			result= manageJobSeekerDAO.updateResumeFolder(folderId, folderResumeId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while updating the folder details..." + jdex);
		}
		return result;
		
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageJobSeekerService#deleteJobSeeker(int)
	 */
	@Override
	public void deleteJobSeeker(int folderResumeId) throws JobBoardServiceException {
		
		try {
			manageJobSeekerDAO.deleteJobSeeker(folderResumeId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while deleting the folder details..." + jdex);
		}
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageJobSeekerService#addFolder(java.lang.String)
	 */
	@Override
	public void addFolder(int userId,String folderName) throws JobBoardServiceException {
		try {
			manageJobSeekerDAO.addFolder(userId,folderName);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while ading new folder..." + jdex);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageJobSeekerService#removeFolder(int, java.lang.String)
	 */
	@Override
	public void removeFolder(int userId, String folderName)
			throws JobBoardServiceException {
		try {
			manageJobSeekerDAO.removeFolder(userId,folderName);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while remove the folder details..." + jdex);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageJobSeekerService#updateRatings(int, int)
	 */
	@Override
	public boolean updateRatings(int rating, int resumeId)
			throws JobBoardServiceException {
		boolean result=false;
		try {
			result= manageJobSeekerDAO.updateRating(rating, resumeId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while updating details..." + jdex);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageJobSeekerService#renameFolder(int, int, java.lang.String)
	 */
	@Override
	public void renameFolder(int userId, int folderId, String folderName)
			throws JobBoardServiceException {
		try {
			manageJobSeekerDAO.renameFolder(userId,folderId,folderName);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while renaming  folder..." + jdex);
		}
		
	}
	/**
	 * Method to retrieve all Resume by the Employer
	 * 
	 * @param : userId
	 */
	@Override
	public List<ManageJobSeekerDTO> retrieveAllResume(int userId, int offset, int noOfRecords)
			throws JobBoardServiceException {
		List<ManageJobSeekerDTO> manageJobSeekerDTOs = new ArrayList<ManageJobSeekerDTO>();

		try {
			manageJobSeekerDTOs = manageJobSeekerDAO.retrieveAllResume(userId,offset, noOfRecords);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while retriving resume..." + jdex);
		}

		return manageJobSeekerDTOs;
	}


/**
	 * Method to retrieve all resume by folder
	 */
	@Override
	public List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId,
			int folderId, int offset, int noOfRecords) throws JobBoardServiceException {
		List<ManageJobSeekerDTO> manageJobSeekerDTOs = new ArrayList<ManageJobSeekerDTO>();

		try {
			manageJobSeekerDTOs = manageJobSeekerDAO.retrieveAllResumeByFolder(userId,folderId,offset,noOfRecords);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while retriving resume by folder..." + jdex);
		}

		return manageJobSeekerDTOs;
	}

/* (non-Javadoc)
 * @see com.advanceweb.afc.jb.job.service.ManageJobSeekerService#getTotalNumberOfJobRecords(int)
 */
@Override
public int getTotalNumberOfRecords(int userId,int folderId)
		throws JobBoardServiceException {
	int count;

	try {
		count = manageJobSeekerDAO.getTotalNumberOfJobRecords(userId,folderId);
	} catch (JobBoardDataException jdex) {
		LOGGER.debug(jdex);
		throw new JobBoardServiceException(
				"Error while retriving resume by folder..." + jdex);
	}

	return count;
}
}
