package com.advanceweb.afc.jb.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.JpJobStat;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.helper.EmpConversionHelper;

@Transactional
@Repository("userDAO")
public class UserDaoImpl implements UserDao {

	private HibernateTemplate hibernateTemplateTracker;
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private EmpConversionHelper conversionHelper;

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
		MerUser user = null;
		@SuppressWarnings("unchecked")
		List<MerUser> userList = hibernateTemplateTracker.find(
				" from  MerUser user where user.email=?", email);
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

	/**
	 * This method is to get the facility id and role for a particular user
	 * 
	 * @param userId
	 * @return EmployerInfoDTO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EmployerInfoDTO facilityDetails(int userId) {
		EmployerInfoDTO employerInfoDTO = null;
		AdmUserFacility user = null;

		List<AdmUserFacility> userList = hibernateTemplate
				.find("from AdmUserFacility e where e.id.userId = " + userId);
		if (userList != null && !userList.isEmpty()) {
			user = userList.get(0);
			employerInfoDTO = new EmployerInfoDTO();

			employerInfoDTO
					.setFacilityId(user.getAdmFacility().getFacilityId());
			employerInfoDTO.setRoleId(user.getAdmRole().getRoleId());

		}
		return employerInfoDTO;
	}

	/**
	 * This method is get the metrics details for logged in employer
	 * 
	 * @param facilityId
	 * @return metricsDTO
	 */
	@Override
	public List<MetricsDTO> getJobPostTotal(int facilityId) {

		List<JpJobStat> jobStatsList = hibernateTemplate
				.find("SELECT js from JpJobStat js, JpJob jb where "
						+ "jb.jobId = js.jobId and "
						+ "jb.admFacility.facilityId	= " + facilityId);

		return conversionHelper.transformJpJobStatToMetricsDTO(jobStatsList);
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
}
