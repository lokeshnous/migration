/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.data.entities.MerUserProfilePK;
import com.advanceweb.afc.jb.data.entities.WebMembership;
import com.advanceweb.afc.jb.data.entities.WebMembershipEmail;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.mysql.jdbc.StringUtils;

@SuppressWarnings("unchecked")
@Transactional
@Repository("userDAO")
public class UserDaoImpl implements UserDao {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(UserDaoImpl.class);
	
	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;
	
	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;
	
	/** The hibernate template advance pass. */
	private HibernateTemplate hibernateTemplateAdvancePass;

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactoryMerionTracker the session factory merion tracker
	 * @param sessionFactory the session factory
	 * @param sessionFactoryAdvancePass the session factory advance pass
	 */
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory, SessionFactory sessionFactoryAdvancePass) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		
		this.hibernateTemplateAdvancePass = new HibernateTemplate(
				sessionFactoryAdvancePass);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserDao#getUser(java.lang.String)
	 */
	@Override
	public UserDTO getUser(String email) {
		UserDTO userDTO = null;

		@SuppressWarnings("unchecked")
		MerUser user = (MerUser)DataAccessUtils
				.uniqueResult(hibernateTemplateTracker
						.find(" from  MerUser user where user.email=? and deleteDt is null",
								email));

		if (user != null) {
			boolean result = isAdmin(user.getUserId());
			if (result) {

				@SuppressWarnings("unchecked")
				List<AdmFacility> facility = hibernateTemplate.find(
						"from AdmFacility a where a.adminUserId=?",
						user.getUserId());
				@SuppressWarnings("unchecked")
				List<AdmUserFacility> admFacility = hibernateTemplate
						.find("from AdmUserFacility a where a.facilityPK.facilityId=?",
								facility.get(0).getFacilityId());
				int userId = admFacility.get(0).getFacilityPK().getUserId();
				@SuppressWarnings("unchecked")
				MerUser merUser = (MerUser) DataAccessUtils
						.uniqueResult(hibernateTemplateTracker
								.find(" from  MerUser user where user.userId=? and deleteDt is null",
										userId));
				userDTO = new UserDTO();
				userDTO.setEmailId(merUser.getEmail());
				userDTO.setFirstName(merUser.getFirstName());
				userDTO.setLastName(merUser.getLastName());
				userDTO.setUserId(merUser.getUserId());
				userDTO.setPassword(user.getPassword());
				userDTO.setAdmin(true);

			} else {
				userDTO = new UserDTO();
				userDTO.setEmailId(user.getEmail());
				userDTO.setFirstName(user.getFirstName());
				userDTO.setLastName(user.getLastName());
				userDTO.setUserId(user.getUserId());
				userDTO.setPassword(user.getPassword());
			}
		}
		return userDTO;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserDao#isAdmin(int)
	 */
	public boolean isAdmin(int userId) {
		boolean result = false;
		List<AdmUserRole> user = hibernateTemplate.find(
				"from AdmUserRole adm where adm.rolePK.userId=?", userId);
		if (user != null && !user.isEmpty() && user.get(0).getRolePK().getRoleId() == 1)
			{
				result = true;
			}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserDao#getUserRole(int)
	 */
	@Override
	public List<UserRoleDTO> getUserRole(int userId) {
		List<AdmUserRole> roleList = hibernateTemplate.find(
				"from AdmUserRole a where a.id.userId=?", userId);
		List<UserRoleDTO> userRoleDTOList = new ArrayList<UserRoleDTO>();

		for (AdmUserRole role : roleList) {

			List<AdmUserFacility> userFacilityList = hibernateTemplate
					.find("from AdmUserFacility f where f.id.userId=? and f.id.roleId=?",
							role.getRolePK().getUserId(), role.getRolePK()
									.getRoleId());

			for (AdmUserFacility facility : userFacilityList) {
				UserRoleDTO dto = new UserRoleDTO();
				dto.setRoleId(facility.getAdmFacility().getFacilityId());
				dto.setRoleName(facility.getAdmFacility().getFacilityType());
				userRoleDTOList.add(dto);

			}

			UserRoleDTO userRole = new UserRoleDTO();
			userRole.setRoleId(role.getAdmRole().getRoleId());
			userRole.setRoleName(role.getAdmRole().getName());
			userRoleDTOList.add(userRole);
		}

		return userRoleDTOList;
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserDao#getUserByUserId(int)
	 */
	@Override
	public UserDTO getUserByUserId(int userId) {
		UserDTO userDTO = null;
		MerUser user = null;
		@SuppressWarnings("unchecked")
		List<MerUser> userList = hibernateTemplateTracker.find(
				" from  MerUser user where user.userId=?", userId);
		if (userList != null && !userList.isEmpty()) {
			user = userList.get(0);
			userDTO = new UserDTO();

			userDTO.setEmailId(user.getEmail());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setUserId(user.getUserId());
			userDTO.setPassword(user.getPassword());
		}
		return userDTO;
	}

	/**
	 * This method to update the automatic generated password to DB
	 * 
	 * @param emailAddress
	 * @param tempassword
	 * @throws JobBoardServiceException
	 */
	public void saveNewPWD(String email, String tempassword)
			throws JobBoardDataException {
		try {
			MerUser search = (MerUser) hibernateTemplateTracker.find(
					"from MerUser e where e.email=?", email).get(0);
			
			WebMembershipEmail webMembershipEmail = (WebMembershipEmail)DataAccessUtils.uniqueResult(hibernateTemplateAdvancePass.find("from WebMembershipEmail where email = ?", search.getEmail()));
			WebMembership membership = hibernateTemplateAdvancePass.get(WebMembership.class, webMembershipEmail.getWebMembership().getWebMembershipID());
			search.setPassword(tempassword);
			membership.setPassword(tempassword);
			membership.setSalt(null);
			membership.setEncryptPassword(null);
			hibernateTemplateTracker.saveOrUpdate(search);
			hibernateTemplateAdvancePass.saveOrUpdate(membership);
		} catch (HibernateException e) {
			throw new JobBoardDataException(
					"Error occured while updating generated password to merUser table"
							+ e);
		}

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserDao#updateSocialProfileId(int, java.lang.String, int)
	 */
	@Override
	public void updateSocialProfileId(int userId, String profileId,int profileAttrId)throws JobBoardDataException{
		MerUserProfile userProfile=new MerUserProfile();
		try {
		MerUser merUser =hibernateTemplateTracker.get(MerUser.class,userId);
		MerUserProfilePK profilePK=new MerUserProfilePK();
		profilePK.setUserId(userId);
		profilePK.setProfileAttribId(profileAttrId);
		userProfile.setMerUser(merUser);
		userProfile.setAttribValue(profileId);
		userProfile.setProfilePK(profilePK);
		hibernateTemplateTracker.saveOrUpdate(userProfile);
		} catch (HibernateException e) {
			throw new JobBoardDataException(
					"Error occured while updating social profile id in MerUserProfile"
							+ e);
		}
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserDao#getUserBySocialProfileId(java.lang.String)
	 */
	@Override
	public UserDTO getUserBySocialProfileId(String socialProfileId)throws JobBoardDataException{
		UserDTO userDTO=null;
		try {
		MerUserProfile merUserProfile=(MerUserProfile)DataAccessUtils.uniqueResult(hibernateTemplateTracker.find("from MerUserProfile profile where profile.attribValue=?",socialProfileId));
		if(merUserProfile!=null){
		MerUser merUser=merUserProfile.getMerUser();
		userDTO=new UserDTO();
		userDTO.setEmailId(merUser.getEmail());
		userDTO.setFirstName(merUser.getFirstName());
		userDTO.setLastName(merUser.getLastName());
		userDTO.setUserId(merUser.getUserId());
		userDTO.setPassword(merUser.getPassword());
		}
	} catch (HibernateException e) {
		throw new JobBoardDataException(
				"Error occured while updating social profile id in MerUserProfile"
						+ e);
	}
		return userDTO;
	}
	
	/**
	 * This method is used to get the total count of employer
	 * 
	 * @return
	 * @throws JobBoardDataException
	 */
	public long getEmployerCount() throws JobBoardDataException {
		long count = 0;
		try {
			count = hibernateTemplate.find("from AdmFacility").size();
		} catch (HibernateException he) {
			throw new JobBoardDataException(
					"Error occured while getting the Result from Database" + he);
		}
		return count;
	}
	/**
	 * This method is used to get all job seeker list 
	 * @return List<SchedulerDTO>
	 */

	@Override
	public List<SchedulerDTO> getAllJobSeekerList() {
		List<AdmUserFacility> facility;
		List<SchedulerDTO> schedulerDTOList=new ArrayList<SchedulerDTO>();
		try{
			facility=(List<AdmUserFacility>)hibernateTemplate.find("from AdmUserFacility userFacility where userFacility.facilityPK.roleId=? and userFacility.deleteDt=NULL",MMJBCommonConstants.JOBSEEKER_ROLE_ID);
		for(AdmUserFacility admUserFacility:facility){
				SchedulerDTO schedulerDTO=new SchedulerDTO();
				UserDTO userDTO=getUserByUserId(admUserFacility.getFacilityPK().getUserId());
				if(userDTO!=null){
					schedulerDTO.setUserId(userDTO.getUserId());
					schedulerDTO.setFirstName(userDTO.getFirstName());
					schedulerDTO.setLastName(userDTO.getLastName());
					schedulerDTO.setEmailId(userDTO.getEmailId());
					schedulerDTOList.add(schedulerDTO);
				}
		}
		}catch(Exception e){
			LOGGER.error("Exception occur while getting all jobseeker list for scheduler job"+e.getMessage());
		}
		return schedulerDTOList;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserDao#getAdvancePassUser(java.lang.String)
	 */
	@Override
	public UserDTO getAdvancePassUser(String email) {
		UserDTO userDTO=null;
		try {
			WebMembershipEmail webMembershipEmail = (WebMembershipEmail) DataAccessUtils
					.uniqueResult(hibernateTemplateAdvancePass.find(
							"from WebMembershipEmail where email = ?", email));
			if (webMembershipEmail != null) {
				WebMembership membership = hibernateTemplateAdvancePass.get(
						WebMembership.class, webMembershipEmail
								.getWebMembership().getWebMembershipID());
				if (membership != null) {
					userDTO = new UserDTO();
					userDTO.setEmailId(email);
					userDTO.setPassword(membership.getPassword());
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception occur while fetching the username and password from Advance Pass DB"+e.getMessage());
		}
		return userDTO;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserDao#checkUserMail(java.lang.String)
	 */
	@Override
	public boolean checkUserMail(String email) {
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
				List<MerUser> usersList = hibernateTemplateTracker.find("from MerUser e where e.email = ?",email);
				List<WebMembershipEmail> webMembershipEmails = hibernateTemplateAdvancePass.find("from WebMembershipEmail e where e.email = ? and e.deleteDate is NULL", email);
				
				if(!webMembershipEmails.isEmpty() && usersList.isEmpty()){
					return true;
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserDao#getAdminInfo(java.lang.String)
	 */
	@Override
	public UserDTO getAdminInfo(String email) {
		UserDTO userDTO = null;
		MerUser user = (MerUser)DataAccessUtils
				.uniqueResult(hibernateTemplateTracker
						.find(" from  MerUser user where user.email=? and deleteDt is null",
								email));
		if (user != null) {
			userDTO = new UserDTO();
			userDTO.setEmailId(user.getEmail());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setUserId(user.getUserId());
//			userDTO.setPassword(user.getPassword());
		}
		return userDTO;
	}
}
