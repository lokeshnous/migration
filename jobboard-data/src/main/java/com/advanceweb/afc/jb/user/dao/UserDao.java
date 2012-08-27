package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;

public interface UserDao {
	MerUserDTO getUser(String email);

	List<UserRoleDTO> getUserRole(int userId);

	/**
	 * This method is to get the facilityId for logged in user
	 * 
	 * @param userId
	 * @return
	 */
	EmployerInfoDTO facilityDetails(int userId);
}
