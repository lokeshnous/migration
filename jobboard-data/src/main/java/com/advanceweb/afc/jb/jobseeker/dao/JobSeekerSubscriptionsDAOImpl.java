package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.MerUserAlerts;
import com.advanceweb.afc.jb.jobseeker.helper.JobSeekerSubscriptionsConversionHelper;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@SuppressWarnings("unchecked")
@Repository("jobSeekerSubscriptionsDAO")
public class JobSeekerSubscriptionsDAOImpl implements JobSeekerSubscriptionsDAO {
	
	private HibernateTemplate hibernateTemplate;
	
	private HibernateTemplate hibernateTemplateCareers;
	
	@Autowired
	private JobSeekerSubscriptionsConversionHelper jsSubscriptionHelper;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker, SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);
		
	}
	
	

	/**
	 * save subscription
	 */

	@Override
	@Transactional(readOnly = false)
	public boolean saveJobSeekerSubscription(List<JobSeekerSubscriptionsDTO> listSubsDTO, int userId) {
		try {
			if(userId != 0){
				List<MerUserAlerts> listSubsAlerts= hibernateTemplate.find("from MerUserAlerts m where m.userid="+userId);
				hibernateTemplate.deleteAll(listSubsAlerts);
			}
			List<MerUserAlerts> userAlerts = jsSubscriptionHelper.transformjsSubsDTOToMerUserAlerts(listSubsDTO);
			hibernateTemplate.saveOrUpdateAll(userAlerts);
		} catch (DataAccessException e) {

			e.printStackTrace();
		}
		
		return true;
	}

	/**
	 * To get current subscriptions of the user
	 * @param userId
	 * @return
	 */
	@Override
	public List<JobSeekerSubscriptionsDTO> getCurrentSubscriptions(int userId) {
		
		List<JobSeekerSubscriptionsDTO> listSubscriptiosns = null;
		try {
			List<AdmUserSubscription> listSubs= hibernateTemplateCareers.find("Select m from AdmUserSubscription m where m.id.userId="+userId);
			listSubscriptiosns = jsSubscriptionHelper.transformMerUserAlertsTojsSubsDTO(listSubs);
		} catch (DataAccessException e) {

			e.printStackTrace();
		}
		
		return listSubscriptiosns;
	}

}
