/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.dao.ManageAccessPermissionDAO;
import com.advanceweb.afc.jb.job.service.ManageAccessPermissionService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
/**
 * 
 * @author deviprasadm
 * @Purpose: This class will act as a service impl for the Manage Access Permission
 */
@Service("manageAccessPermissionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class ManageAccessPermissionServiceImpl implements
		ManageAccessPermissionService {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ManageAccessPermissionServiceImpl.class);
	
	/** The access permission dao. */
	@Autowired
	public ManageAccessPermissionDAO accessPermissionDAO;

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageAccessPermissionService#createJobOwner(com.advanceweb.afc.jb.common.EmployerProfileDTO, int, int)
	 */
	@Override
	public UserDTO createJobOwner(EmployerProfileDTO profileDTO,
			int facilityIdP, int userIdP) throws JobBoardServiceException {
		UserDTO userDTO = null;

		try {
			userDTO = accessPermissionDAO.createJobOwner(profileDTO,
					facilityIdP, userIdP);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while creating the job owner..." + jdex);
		}

		return userDTO;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageAccessPermissionService#deleteJobOwner(int)
	 */
	@Override
	public boolean deleteJobOwner(int ownerId) throws JobBoardServiceException {
		try {
			return accessPermissionDAO.deleteJobOwner(ownerId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while deleting the job owner..." + jdex);
		}
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageAccessPermissionService#updateJobOwner(java.util.List)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateJobOwner(
			List<ManageAccessPermissionDTO> accessPermissionDTO)
			throws JobBoardServiceException {
		boolean result = true;
		try {
			result = accessPermissionDAO.updateJobOwner(accessPermissionDTO);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while updating the job owner..." + jdex);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.ManageAccessPermissionService#getJobOwnerList(int, int)
	 */
	@Override
	public List<ManageAccessPermissionDTO> getJobOwnerList(int facilityId,
			int userId) throws JobBoardServiceException {
		List<ManageAccessPermissionDTO> manageAccessPermissionDTOs = new ArrayList<ManageAccessPermissionDTO>();
		try {
			manageAccessPermissionDTOs = accessPermissionDAO.getJobOwnerList(
					facilityId, userId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while fetching the job owner..." + jdex);
		}
		return manageAccessPermissionDTOs;
	}
	/**
	 * 
	 * @param email
	 * @return
	 * @throws JobBoardServiceException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserDTO getUserListByEmail(String email) throws JobBoardServiceException{
		UserDTO userDTO =null;
		try {
			userDTO = accessPermissionDAO.getUserListByEmail(email);
		} catch (JobBoardDataException exc) {
			LOGGER.debug(exc);
			throw new JobBoardServiceException(
					"Error while fetching the User List..." + exc);
		}
		
		return userDTO;
		
	}
}
