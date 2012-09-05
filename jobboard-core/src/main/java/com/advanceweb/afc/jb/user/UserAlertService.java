package com.advanceweb.afc.jb.user;

import java.util.List;

import com.advanceweb.afc.jb.common.UserAlertDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept, 2012
 */
public interface UserAlertService {
	
	/**
	 * The method is called to view the alerts for employer.
	 * 
	 * @param userId
	 * @return
	 */
	List<UserAlertDTO> viewalerts(int userId);

	/**
	 * This method is called to delete the alerts
	 * 
	 * @param userId
	 * @param alertId
	 * @return
	 */
	boolean deleteAlert(int userId, int alertId);
}
