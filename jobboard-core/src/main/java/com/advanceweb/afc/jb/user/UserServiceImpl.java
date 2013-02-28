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
	
	/** The user dao. */
	@Autowired
	UserDao userDAO;
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#getUser(java.lang.String)
	 */
	@Override
	public UserDTO getUser(String email) {
		
		return userDAO.getUser(email);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#getUserRole(int)
	 */
	@Override
	public List<UserRoleDTO> getUserRole(int userId) {
		return userDAO.getUserRole(userId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#getUserByUserId(int)
	 */
	@Override
	public UserDTO getUserByUserId(int userId) {
		return userDAO.getUserByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#saveNewPWD(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveNewPWD(String emailAddress, String tempassword)
			throws JobBoardServiceException {
		try{
			userDAO.saveNewPWD(emailAddress, tempassword);
		}catch(JobBoardDataException e){
			throw new JobBoardServiceException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#updateSocialProfileId(int, java.lang.String, int)
	 */
	@Override
	public void updateSocialProfileId(int userId, String profileId,
			int profileAttrId) throws JobBoardServiceException {
		try{
			userDAO.updateSocialProfileId(userId, profileId, profileAttrId);
		}catch(JobBoardDataException e){
			throw new JobBoardServiceException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#getUserBySocialProfileId(java.lang.String)
	 */
	@Override
	public UserDTO getUserBySocialProfileId(String socialProfileId)
			throws JobBoardServiceException {
		try{
			return userDAO.getUserBySocialProfileId(socialProfileId);
		}catch(JobBoardDataException e){
			throw new JobBoardServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#getEmployerCount()
	 */
	@Override
	public long getEmployerCount() throws JobBoardServiceException {
		try{
			return userDAO.getEmployerCount();
		}catch(JobBoardDataException e){
			throw new JobBoardServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#getAllJobSeekerList()
	 */
	@Override
	public List<SchedulerDTO> getAllJobSeekerList() {
		return userDAO.getAllJobSeekerList();
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#getAdvancePassUser(java.lang.String)
	 */
	@Override
	public UserDTO getAdvancePassUser(String email) {
		return userDAO.getAdvancePassUser(email);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#checkUserMail(java.lang.String)
	 */
	@Override
	public boolean checkUserMail(String email) {
		return userDAO.checkUserMail(email);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.UserService#getAdminInfo(java.lang.String)
	 */
	@Override
	public UserDTO getAdminInfo(String email) {
		return userDAO.getAdminInfo(email);
	}
	
	@Override
	public UserDTO getAdvancePassUserDetails(String email) {
		return userDAO.getAdvancePassUserDetails(email);
	}
}
