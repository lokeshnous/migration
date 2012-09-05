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
	List<UserAlertDTO> viewalerts(int userId);
}
