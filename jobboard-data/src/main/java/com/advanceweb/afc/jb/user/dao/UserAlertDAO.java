/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept, 2012
 */

public interface UserAlertDAO {

	/**
	 * The method is called to view the alerts for employer.
	 * 
	 * @param userId
	 * @return
	 */
	List<UserAlertDTO> viewalerts(int userId, int facilityId,
			List<ManageAccessPermissionDTO> jbOwnerList);

	/**
	 * This method is called to delete the alerts
	 * 
	 * @param userId
	 * @param alertId
	 * @return
	 */
	boolean deleteAlert(int facilityAlertId);

	/**
	 * To get the check box values
	 * 
	 * @param dropDownName
	 * @return
	 */
	List<DropDownDTO> populateValues(String dropDownName);

	/**
	 * This method is called to save the selected alerts
	 * 
	 * @param userId
	 * @param alertDTOs
	 * @return
	 */
	boolean saveAlerts(int userId, List<UserAlertDTO> alertDTOs);
	
	/**
	 * This method is called to view the job owner alerts
	 * 
	 * @param userId
	 * @return
	 */
	List<UserAlertDTO> viewAlerts(int userId);

	/**
	 * To get the job owner list for logged in user
	 * 
	 * @param facilityId
	 * @param userId
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<ManageAccessPermissionDTO> getJobOwner(int facilityId,
			int userId) throws JobBoardDataException;

	/**
	 * To get the details of logged in user
	 * 
	 * @param userId
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<ManageAccessPermissionDTO> getOwnerDetails(int userId)
			throws JobBoardDataException;
}
