/**
 * 
 */
package com.advanceweb.afc.jb.user;

import java.util.List;

import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @author anilm
 *
 */
public interface UserService {
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
			throws JobBoardServiceException;
	/**
	 * This method is used to link the user's social media(e.g Facebook,LinkedIn)profile id with the corresponding user's profile
	 * @param int userId
	 * @param String profileId
	 * @param int profileAttrId
	 * @throws JobBoardDataException
	 */
	void updateSocialProfileId(int userId,String profileId,int profileAttrId)throws JobBoardServiceException;
	/**
	 * This method is used to fetch user information based on user's social media(e.g Facebook,LinkedIn)profile id 
	 * @param String socialProfileId
	 * @throws JobBoardDataException
	 */
	UserDTO getUserBySocialProfileId(String socialProfileId)throws JobBoardServiceException;
	
	long getEmployerCount() throws JobBoardServiceException;
	
	public List<SchedulerDTO> getAllJobSeekerList();
	public UserDTO getAdvancePassUser(String email);
}
