/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.data.entities.AdmAlert;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityAlert;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacilityPK;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.employer.helper.EmpConversionHelper;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 4th sept,2012
 */

@Transactional
@Repository("alertDAO")
public class UserAlertDAOImpl implements UserAlertDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(UserAlertDAOImpl.class);

	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;

	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;

	/** The conversion helper. */
	@Autowired
	private EmpConversionHelper conversionHelper;

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactoryMerionTracker the session factory merion tracker
	 * @param sessionFactory the session factory
	 */
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * The method is called to view the alerts for employer.
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<UserAlertDTO> viewalerts(int userId, int facilityId,
			List<ManageAccessPermissionDTO> jbOwnerList) {
		List<AdmFacilityAlert> facilityAlerts = new ArrayList<AdmFacilityAlert>();
		if (jbOwnerList != null && !jbOwnerList.isEmpty()) {
			for (ManageAccessPermissionDTO permissionDTO : jbOwnerList) {
				int OwnerId = permissionDTO.getOwnerId();
				List<AdmFacilityAlert> userAlerts = hibernateTemplate
						.find(" from AdmFacilityAlert  where userId = ? and deleteDt is null",
								OwnerId);
				facilityAlerts.addAll(userAlerts);
			}
		}
		return conversionHelper.transformAdmUserAlertToAlertDTO(jbOwnerList,
				facilityAlerts);
	}

	/**
	 * This method is called to delete the alerts
	 * 
	 * @param userId
	 * @param alertId
	 * @return
	 */
	@Override
	public boolean deleteAlert(int facilityAlertId) {
		AdmFacilityAlert facilityAlert = (AdmFacilityAlert) hibernateTemplate
				.find("from AdmFacilityAlert where facilityAlertId = ? ",
						facilityAlertId).get(0);
		if (null != facilityAlert) {
			facilityAlert.setDeleteDt(new Date());
		}
		hibernateTemplate.saveOrUpdate(facilityAlert);
		return true;
	}

	/**
	 * To get the check box values
	 * 
	 * @param dropDownName
	 * @return
	 */

	@Override
	public List<DropDownDTO> populateValues(String dropDownName) {
		List<AdmAlert> admAlerts = hibernateTemplate.find(
				"from AdmAlert e where e.alertType=?", dropDownName);
		return conversionHelper.convertAdmAlertToDropDownDTO(admAlerts);
	}

	/**
	 * This method is called to save the selected alerts
	 * 
	 * @param userId
	 * @param alertDTOs
	 * @return
	 */
	@Override
	public boolean saveAlerts(int userId, List<UserAlertDTO> alertDTOs) {
		try {

			if (userId != 0) {
				// delete the alerts of 
				List<AdmFacilityAlert> facilityAlerts = (List<AdmFacilityAlert>) hibernateTemplate
						.find("from AdmFacilityAlert where userId = ? and deleteDt is null",
								userId);
				/*for (AdmFacilityAlert admFacilityAlert : facilityAlerts) {
					admFacilityAlert.setDeleteDt(new Date());
				}*/
				if(!facilityAlerts.isEmpty()){
					hibernateTemplate.deleteAll(facilityAlerts);
				}
				
				AdmUserFacility userFacility = (AdmUserFacility) hibernateTemplate
						.find("from AdmUserFacility e where e.facilityPK.userId =?",
								userId).get(0);
				AdmUserFacilityPK facilityPk = userFacility.getFacilityPK();
				int facilityId = facilityPk.getFacilityId();
				List<AdmFacilityAlert> listAlerts = hibernateTemplate
						.find("from AdmFacilityAlert where userId =? and deleteDt is null",
								userId);
				List<AdmFacilityAlert> userAlerts = conversionHelper
						.transformAlertDTOToAdmFacilityAlert(alertDTOs,
								listAlerts, facilityId);
				hibernateTemplate.saveOrUpdateAll(userAlerts);
			}
		} catch (DataAccessException e) {
			LOGGER.error("not able to assign the alerts to the facility", e);
		}

		return true;
	}

	/**
	 * This method is called to view the job owner alerts
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<UserAlertDTO> viewAlerts(int userId) {
		List<UserAlertDTO> userAlertDTOs = null;
		try {
			
			if (userId != 0) {
				List<AdmFacilityAlert> userFacility = (List<AdmFacilityAlert>) hibernateTemplate
						.find("from AdmFacilityAlert e where e.userId =? and deleteDt is null",
								userId);
				userAlertDTOs = conversionHelper
						.transformAdmFacilityAlertToAdmFacilityAlertDTO(userFacility);
				
			}
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return userAlertDTOs;
	}
	
	/**
	 * To get the job owner list for logged in user
	 * 
	 * @param facilityId
	 * @param userId
	 * @return
	 * @throws JobBoardServiceException
	 */
	@Override
	public List<ManageAccessPermissionDTO> getJobOwner(int facilityId,
			int userId) {

		try {
			List<MerUser> merUsers = new ArrayList<MerUser>();
			List<AdmFacility> facilityList = new ArrayList<AdmFacility>();
			List<Integer> roleId = new ArrayList<Integer>();

			AdmFacility facilityDetails = (AdmFacility) hibernateTemplate.find(
					"from AdmFacility adm where adm.facilityId=?", facilityId)
					.get(0);
			List<AdmFacility> admFacilityList = hibernateTemplate.find(
					"from AdmFacility adm where adm.facilityParentId=?",
					facilityDetails.getFacilityParentId());

			facilityList.addAll(admFacilityList);
			for (AdmFacility facility : facilityList) {
				Object[] inputs = { facility.getFacilityId() };
				List<AdmUserFacility> admUsersList = hibernateTemplate
						.find("from AdmUserFacility admFacility where admFacility.id.facilityId=?",
								inputs);
				if (null != admUsersList && !admUsersList.isEmpty()) {
					AdmUserFacility admUserFacility = admUsersList.get(0);
					List<MerUser> merUserList = hibernateTemplateTracker
							.find("from MerUser user where user.userId=? and user.deleteDt is null",
									admUserFacility.getFacilityPK().getUserId());
					merUsers.addAll(merUserList);

				}

			}
			for (MerUser merUser : merUsers) {
				List<AdmUserRole> admUserRolesList = new ArrayList<AdmUserRole>();
				admUserRolesList = (List<AdmUserRole>) hibernateTemplate.find(
						"from AdmUserRole a where a.id.userId=?",
						merUser.getUserId());
				AdmUserRole admUserRole = admUserRolesList.get(0);
				roleId.add(admUserRole.getRolePK().getRoleId());

			}
			return conversionHelper
					.transformAdmFacilityToManageAccessPermissionDTO(merUsers,
							roleId);

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;

	}

	/**
	 * To get the details of logged in user
	 * 
	 * @param userId
	 * @return
	 * @throws JobBoardServiceException
	 */
	@Override
	public List<ManageAccessPermissionDTO> getOwnerDetails(int userId) {
		try {
			List<MerUser> userList = hibernateTemplateTracker.find(
					" from  MerUser user where user.userId=?", userId);
			return conversionHelper
					.transformMerUserToManageAccessPermissionDTO(userList);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}
}
