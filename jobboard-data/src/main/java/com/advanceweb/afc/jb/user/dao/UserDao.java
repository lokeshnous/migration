/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

public interface UserDao {
	
	/**
	 * Gets the user.
	 *
	 * @param email the email
	 * @return the user
	 */
	UserDTO getUser(String email);

	/**
	 * Gets the user role.
	 *
	 * @param userId the user id
	 * @return the user role
	 */
	List<UserRoleDTO> getUserRole(int userId);

	/**
	 * Gets the user by user id.
	 *
	 * @param userId the user id
	 * @return the user by user id
	 */
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
	
	/**
	 * Gets the employer count.
	 *
	 * @return the employer count
	 * @throws JobBoardDataException the job board data exception
	 */
	long getEmployerCount() throws JobBoardDataException;
	
	/**
	 * Gets the all job seeker list.
	 *
	 * @return the all job seeker list
	 */
	public List<SchedulerDTO> getAllJobSeekerList();
	
	/**
	 * Gets the advance pass user.
	 *
	 * @param email the email
	 * @return the advance pass user
	 */
	public UserDTO getAdvancePassUser(String email);
	
	/**
	 * Check user mail.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean checkUserMail(String email);
	
	/**
	 * Checks if is admin.
	 *
	 * @param userId the user id
	 * @return true, if is admin
	 */
	public boolean isAdmin(int userId);
	
	/**
	 * Gets the admin info.
	 *
	 * @param email the email
	 * @return the admin info
	 */
	public UserDTO getAdminInfo(String email);
}
