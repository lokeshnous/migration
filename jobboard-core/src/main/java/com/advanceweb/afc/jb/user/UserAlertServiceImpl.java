package com.advanceweb.afc.jb.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.dao.UserAlertDAO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept, 2012
 */

@Service("alertService")
public class UserAlertServiceImpl implements UserAlertService {

	private static final Logger LOGGER = Logger
			.getLogger(UserAlertServiceImpl.class);

	@Autowired
	private UserAlertDAO alertDAO;

	/**
	 * The method is called to view the alerts for employer.
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserAlertDTO> viewalerts(int userId, int facilityId,
			List<ManageAccessPermissionDTO> jbOwnerList) {
		return alertDAO.viewalerts(userId, facilityId, jbOwnerList);
	}

	/**
	 * This method is called to delete the alerts
	 * 
	 * @param userId
	 * @param alertId
	 * @return
	 */
	public boolean deleteAlert(int facilityAlertId) {
		return alertDAO.deleteAlert(facilityAlertId);
	}

	/**
	 * To get the check box values
	 * 
	 * @param dropDownName
	 * @return
	 */
	public List<DropDownDTO> populateValues(String dropDownName) {
		return alertDAO.populateValues(dropDownName);
	}

	/**
	 * To get the job owner list for logged in user
	 * 
	 * @param facilityId
	 * @param userId
	 * @return
	 * @throws JobBoardServiceException
	 */
	@Override
	public List<ManageAccessPermissionDTO> getJobOwner(int facilityId,
			int userId) throws JobBoardServiceException {
		List<ManageAccessPermissionDTO> manageAccessPermissionDTOs = new ArrayList<ManageAccessPermissionDTO>();
		try {
			manageAccessPermissionDTOs = alertDAO.getJobOwner(facilityId,
					userId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while fetching the job owner..." + jdex);
		}
		return manageAccessPermissionDTOs;
	}

	/**
	 * To get the details of logged in user
	 * 
	 * @param userId
	 * @return
	 * @throws JobBoardServiceException
	 */
	@Override
	public List<ManageAccessPermissionDTO> getOwnerDetails(int userId)
			throws JobBoardServiceException {
		List<ManageAccessPermissionDTO> userDTOs = new ArrayList<ManageAccessPermissionDTO>();
		try {
			userDTOs = alertDAO.getOwnerDetails(userId);
		} catch (JobBoardDataException jdex) {
			LOGGER.debug(jdex);
			throw new JobBoardServiceException(
					"Error while fetching the job owner..." + jdex);
		}
		return userDTOs;
	}

	/**
	 * This method is called to save the selected alerts
	 * 
	 * @param userId
	 * @param alertDTOs
	 * @return
	 */
	public boolean saveAlerts(int userId, List<UserAlertDTO> alertDTOs) {
		return alertDAO.saveAlerts(userId, alertDTOs);
	}

	/**
	 * This method is called to view the selected alerts
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserAlertDTO> viewAlerts(int userId) {
		return alertDAO.viewAlerts(userId);
	}
}
