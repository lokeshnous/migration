package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

public interface UserDao {
	UserDTO getUser(String email);

	List<UserRoleDTO> getUserRole(int userId);

	UserDTO getUserByUserId(int userId);

	int getfacility(int facilityId);

	/**
	 * This method to update the automatic generated password to DB
	 * 
	 * @param emailAddress
	 * @param tempassword
	 * @throws JobBoardServiceException
	 */
	void saveNewPWD(String emailAddress, String tempassword)
			throws JobBoardDataException;

}
