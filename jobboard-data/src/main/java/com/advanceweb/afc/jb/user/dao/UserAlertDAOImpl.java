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
import com.advanceweb.afc.jb.data.entities.AdmFacilityAlert;
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

	private static final Logger LOGGER = Logger
			.getLogger(UserAlertDAOImpl.class);

	private HibernateTemplate hibernateTemplate;

	@Autowired
	private EmpConversionHelper conversionHelper;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * The method is called to view the alerts for employer.
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserAlertDTO> viewalerts(int userId, int facilityId,
			List<ManageAccessPermissionDTO> jbOwnerList) {
		List<AdmFacilityAlert> facilityAlerts = new ArrayList<AdmFacilityAlert>();
		for (ManageAccessPermissionDTO permissionDTO : jbOwnerList) {
			int OwnerId = permissionDTO.getOwnerId();
			List<AdmFacilityAlert> userAlerts = hibernateTemplate
					.find(" from AdmFacilityAlert  where userId = ? and deleteDt is null",
							OwnerId);
			facilityAlerts.addAll(userAlerts);
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
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveAlerts(int userId, List<UserAlertDTO> alertDTOs) {
		try {

			if (userId != 0) {
				List<AdmFacilityAlert> listAlerts = hibernateTemplate
						.find("from AdmFacilityAlert where userId =? and deleteDt is null",
								userId);
				List<AdmFacilityAlert> userAlerts = conversionHelper
						.transformAlertDTOToAdmFacilityAlert(alertDTOs,
								listAlerts);
				hibernateTemplate.saveOrUpdateAll(userAlerts);
			}
		} catch (DataAccessException e) {
			LOGGER.info("not able to assign the alerts to the facility");
		}

		return true;
	}

}
