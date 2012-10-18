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
	public boolean updateJobSeeker(int appStatusId, int resumeId, int rating) throws JobBoardServiceException  {
		boolean result=false;
		try {
			result= manageJobSeekerDAO.updateJobSeeker(appStatusId, resumeId, rating);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while retriving the folder details..." + jdex);
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
					"Error while retriving resume..." + jdex);
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

}
