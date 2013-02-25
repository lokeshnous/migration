/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.login.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

@SuppressWarnings("unchecked")
@Transactional
@Repository("loginFormDAO")
public class LoginFormDAOImpl implements LoginFormDAO {

	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;
	
	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;

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
	 * This method is used to get the userId and roleId of logged in user
	 * 
	 * @param email
	 * @param password
	 * 
	 */
	public LoginDTO validateLoginFormValues(String email, String password) {

		// Get the user id by passing the email address of user by passing the
		// email address from MerUser entity
		int loggedinUserId = 0;
		LoginDTO loginFormDTO = new LoginDTO();
		List<MerUser> listMerUser = hibernateTemplateTracker
				.find("from MerUser where email = '" + email + "'");

		if (listMerUser != null && !listMerUser.isEmpty()) {
			MerUser merUserNew = listMerUser.get(0);
			loggedinUserId = merUserNew.getUserId();
			loginFormDTO.setEmailAddress(merUserNew.getEmail());
			loginFormDTO.setPassword(merUserNew.getPassword());
			loginFormDTO.setUserID(merUserNew.getUserId());
		}

		// Get the roleId for a logged in user by passing the userId from
		// AdmUserRole entity
		if (loggedinUserId != 0) {
			List<AdmUserRole> listAdmUserRole = hibernateTemplate
					.find("from AdmUserRole e where e.id.userId = "
							+ loggedinUserId);

			if (listAdmUserRole != null && !listAdmUserRole.isEmpty()) {
				AdmUserRole admUserRoleNew = listAdmUserRole.get(0);
				loginFormDTO.setRoleId(admUserRoleNew.getRolePK().getRoleId());
			}
		}
		return loginFormDTO;
	}

	/**
	 * Get the user password based on email
	 * 
	 * @param email
	 * @return
	 */
	public LoginDTO getUserEmailDetails(String email) {
		LoginDTO userDetailsDTO = new LoginDTO();

		List<MerUser> listMerUser = hibernateTemplateTracker
				.find("from MerUser where email = '" + email + "'");

		if (!(listMerUser.isEmpty()) && !listMerUser.isEmpty()) {
			MerUser merUserNew = listMerUser.get(0);
			userDetailsDTO.setEmailAddress(merUserNew.getEmail());
			userDetailsDTO.setPassword(merUserNew.getPassword());
			userDetailsDTO.setUserID(merUserNew.getUserId());
		}
		AdmUserRole userRole = null;
		if (userDetailsDTO.getUserID() != 0) {
			userRole = (AdmUserRole) hibernateTemplate.find(
					"from AdmUserRole a where a.rolePK.userId ="
							+ userDetailsDTO.getUserID()).get(0);

			if (userRole.getRolePK().getRoleId() != 0) {
				userDetailsDTO.setRoleId(userRole.getRolePK().getRoleId());
			}
		}

		if (userDetailsDTO.getUserID() != 0
				&& userRole.getRolePK().getRoleId() != MMJBCommonConstants.JOBSEEKER_ROLE_ID) {
			try {
				AdmUserFacility facility = (AdmUserFacility) hibernateTemplate
						.find("from AdmUserFacility e where e.id.userId = "
								+ userDetailsDTO.getUserID()).get(0);

				AdmFacility admFacility = (AdmFacility) hibernateTemplate.find(
						"from AdmFacility a where a.facilityId=?",
						facility.getAdmFacility().getFacilityId()).get(0);
				userDetailsDTO.setRoleId(facility.getAdmRole().getRoleId());
				userDetailsDTO.setFacilityType(admFacility.getFacilityType());
			} catch (DataAccessException e) {

			}

		}
		return userDetailsDTO;
	}

	/**
	 * Get the data to be active job posting
	 * 
	 * @param facilityId
	 * @return
	 */
	@Override
	public int getactivejobposting(int facilityId) {

		int count = 0;
		count = hibernateTemplate
				.find("from JpJob jj where jj.admFacility.facilityId=? and active = 1",
						facilityId).size();

		return count;
	}

}
