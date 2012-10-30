package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

public interface UserDao {
	UserDTO getUser(String email);

	List<UserRoleDTO> getUserRole(int userId);

	UserDTO getUserByUserId(int userId);

	/**
	 * This method to update the automatic generated password to DB
	 * 
	 * @param emailAddress
	 * @param tempassword
	 * @throws JobBoardServiceException
	 */
	void saveNewPWD(String emailAddress, String tempassword)
			throws JobBoardDataException;
	/**
	 * This method is used to link the user's social media(e.g Facebook,LinkedIn)profile id with the corresponding user's profile
	 * @param int userId
	 * @param String profileId
	 * @param int profileAttrId
	 * @throws JobBoardDataException
	 */
	void updateSocialProfileId(int userId,String profileId,int profileAttrId)throws JobBoardDataException;
	/**
	 * This method is used to fetch user information based on user's social media(e.g Facebook,LinkedIn)profile id 
	 * @param String socialProfileId
	 * @throws JobBoardDataException
	 */
	UserDTO getUserBySocialProfileId(String socialProfileId)throws JobBoardDataException;
	
	long getEmployerCount() throws JobBoardDataException;
}
