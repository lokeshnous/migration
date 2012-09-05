package com.advanceweb.afc.jb.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<UserAlertDTO> viewalerts(int userId) {
		return alertDAO.viewalerts(userId);
	}
}
