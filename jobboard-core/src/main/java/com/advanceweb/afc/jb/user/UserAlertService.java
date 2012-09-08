package com.advanceweb.afc.jb.user;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept, 2012
 */
public interface UserAlertService {

	/**
	 * This method is called to save the selected alerts
	 * 
	 * @param userId
	 * @param alertDTOs
	 * @return
	 */
	boolean saveAlerts(int userId, List<UserAlertDTO> alertDTOs);

	/**
	 * The method is called to view the alerts for employer.
	 * 
	 * @param userId
	 * @return
	 */
	List<UserAlertDTO> viewalerts(int userId, int facilityId, List<ManageAccessPermissionDTO> jbOwnerList);

	/**
	 * To get the check box values
	 * 
	 * @param dropDownName
	 * @return
	 */
	List<DropDownDTO> populateValues(String dropDownName);

	/**
	 * This method is called to delete the alerts
	 * 
	 * @param userId
	 * @param alertId
	 * @return
	 */
	boolean deleteAlert(int facilityAlertId);

}
