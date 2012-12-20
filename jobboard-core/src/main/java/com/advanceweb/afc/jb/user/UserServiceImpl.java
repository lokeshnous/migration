/**
 * 
 */
package com.advanceweb.afc.jb.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.dao.UserDao;

/**
 * @author anilm
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDAO;
	
	@Override
	public UserDTO getUser(String email) {
		
		return userDAO.getUser(email);
	}

	@Override
	public List<UserRoleDTO> getUserRole(int userId) {
		return userDAO.getUserRole(userId);
	}

	@Override
	public UserDTO getUserByUserId(int userId) {
		return userDAO.getUserByUserId(userId);
	}

	@Override
	public void saveNewPWD(String emailAddress, String tempassword)
			throws JobBoardServiceException {
		try{
			userDAO.saveNewPWD(emailAddress, tempassword);
		}catch(JobBoardDataException e){
			throw new JobBoardServiceException(e);
		}
		
	}

	@Override
	public void updateSocialProfileId(int userId, String profileId,
			int profileAttrId) throws JobBoardServiceException {
		try{
			userDAO.updateSocialProfileId(userId, profileId, profileAttrId);
		}catch(JobBoardDataException e){
			throw new JobBoardServiceException(e);
		}
		
	}

	@Override
	public UserDTO getUserBySocialProfileId(String socialProfileId)
			throws JobBoardServiceException {
		try{
			return userDAO.getUserBySocialProfileId(socialProfileId);
		}catch(JobBoardDataException e){
			throw new JobBoardServiceException(e);
		}
	}

	@Override
	public long getEmployerCount() throws JobBoardServiceException {
		try{
			return userDAO.getEmployerCount();
		}catch(JobBoardDataException e){
			throw new JobBoardServiceException(e);
		}
	}

	@Override
	public List<SchedulerDTO> getAllJobSeekerList() {
		return userDAO.getAllJobSeekerList();
	}

	@Override
	public UserDTO getAdvancePassUser(String email) {
		return userDAO.getAdvancePassUser(email);
	}

}
