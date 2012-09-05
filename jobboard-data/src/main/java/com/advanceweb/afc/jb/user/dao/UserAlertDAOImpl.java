package com.advanceweb.afc.jb.user.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveSearch;
import com.advanceweb.afc.jb.data.entities.AdmUserAlert;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.employer.helper.EmpConversionHelper;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept,2012
 */

@Transactional
@Repository("alertDAO")
public class UserAlertDAOImpl implements UserAlertDAO {

	private HibernateTemplate hibernateTemplate;
	private HibernateTemplate hibernateTemplateTracker;

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

	/**
	 * The method is called to view the alerts for employer.
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<UserAlertDTO> viewalerts(int userId) {
		List<MerUser> user = hibernateTemplateTracker.find(
				" from  MerUser user where user.userId=?", userId);
		List<AdmUserAlert> userAlerts = hibernateTemplate
				.find("from AdmUserAlert au where  au.userId=? and au.deleteDt is NULL",
						userId);

		return conversionHelper.transformAdmUserAlertToAlertDTO(user,
				userAlerts);
	}

	/**
	 * This method is called to delete the alerts
	 * 
	 * @param userId
	 * @param alertId
	 * @return
	 */
	@Override
	public boolean deleteAlert(int userId, int alertId) {
		AdmUserAlert userAlert = hibernateTemplate.load(AdmUserAlert.class,
				alertId);
		userAlert.setDeleteDt(new Date());
		hibernateTemplate.saveOrUpdate(userAlert);
		return true;
	}
}
