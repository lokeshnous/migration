package com.advanceweb.afc.jb.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.user.dao.UserAlertDAO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept, 2012
 */

@Service("alertService")
public class UserAlertServiceImpl implements UserAlertService {

	@Autowired
	private UserAlertDAO alertDAO;

	/**
	 * The method is called to view the alerts for employer.
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserAlertDTO> viewalerts(int userId, int facilityId) {
		return alertDAO.viewalerts(userId, facilityId);
	}

	/**
	 * This method is called to delete the alerts
	 * 
	 * @param userId
	 * @param alertId
	 * @return
	 */
	public boolean deleteAlert(int userId, int alertId) {
		return alertDAO.deleteAlert(userId, alertId);
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
	 * This method is called to save the selected alerts
	 * 
	 * @param userId
	 * @param alertDTOs
	 * @return
	 */
	public boolean saveAlerts(int userId, List<UserAlertDTO> alertDTOs) {
		return alertDAO.saveAlerts(userId, alertDTOs);
	}
}
