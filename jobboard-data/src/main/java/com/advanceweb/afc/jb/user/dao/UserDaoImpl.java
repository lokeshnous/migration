package com.advanceweb.afc.jb.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmRole;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.data.entities.MerUserProfilePK;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

@Transactional
@Repository("userDAO")
public class UserDaoImpl implements UserDao {

	private HibernateTemplate hibernateTemplateTracker;
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

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

	private boolean isAdmin(int userId) {
		boolean result = false;
		List<AdmUserRole> user = hibernateTemplate.find(
				"from AdmUserRole adm where adm.rolePK.userId=?", userId);
		if (user != null && !user.isEmpty() && user.get(0).getRolePK().getRoleId() == 1)
			{
				result = true;
			}
		return result;
	}

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
			search.setPassword(tempassword);
			hibernateTemplateTracker.saveOrUpdate(search);
		} catch (HibernateException e) {
			throw new JobBoardDataException(
					"Error occured while updating generated password to merUser table"
							+ e);
		}

	}

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

	@Override
	public UserDTO getUserBySocialProfileId(String socialProfileId)throws JobBoardDataException{
		UserDTO userDTO=null;
		try {
		MerUserProfile merUserProfile=DataAccessUtils.uniqueResult(hibernateTemplateTracker.find("from MerUserProfile profile where profile.attribValue=?",socialProfileId));
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
}
