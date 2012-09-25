package com.advanceweb.afc.jb.user.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
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
				" from  MerUser user where user.email=? and deleteDt is null",
				email);
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

	@SuppressWarnings("unchecked")
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

		Query getMetricsData = hibernateTemplate.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(" { call GetMetricsData(?) }");
		getMetricsData.setInteger(0, facilityId);
		List<?> metricsDeatil = getMetricsData.list();
		Iterator<?> iterator = metricsDeatil.iterator();
		List<MetricsDTO> DTOs = new ArrayList<MetricsDTO>();
		while (iterator.hasNext()) {
			MetricsDTO dto = new MetricsDTO();
			Object[] row = (Object[]) iterator.next();
			dto.setViews((Integer) row[0]);
			dto.setClicks((Integer) row[1]);
			dto.setApplies((Integer) row[2]);
			DTOs.add(dto);
		}
		return DTOs;
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
	 * This method is to get all list of facilities
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownDTO> getFacilityGroup(int facilityId)
			throws JobBoardDataException {

		AdmFacility admFacility = (AdmFacility) hibernateTemplate.find(
				"from AdmFacility e where e.facilityId=?", facilityId).get(0);

		List<AdmFacility> facilityList = new ArrayList<AdmFacility>();

		if (admFacility.getFacilityType().equals(
				MMJBCommonConstants.FACILITY_GROUP)) {
			try {
				facilityList = hibernateTemplate.find(
						"from AdmFacility e where e.facilityParentId=?",
						facilityId);
			} catch (HibernateException e) {
				throw new JobBoardDataException(
						"Error occured while getting the facility details from Database"
								+ e);
			}
		}
		return conversionHelper.transformFacilityToDropDownDTO(facilityList,
				admFacility, facilityId);
	}

	/**
	 * This method is to get facility parent id
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardDataException
	 * @throws JobBoardServiceException
	 */
	public int getFacilityParent(int facilityId) throws JobBoardDataException {
		int facilityParentId = 0;
		AdmFacility admFacility;
		try {
			admFacility = (AdmFacility) hibernateTemplate.find(
					"from AdmFacility e where e.facilityId=?", facilityId).get(
					0);
			facilityParentId = admFacility.getFacilityParentId();
		} catch (Exception e) {
			throw new JobBoardDataException(
					"Error occured while getting the facility parent id from Database"
							+ e);
		}

		return facilityParentId;
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
}
