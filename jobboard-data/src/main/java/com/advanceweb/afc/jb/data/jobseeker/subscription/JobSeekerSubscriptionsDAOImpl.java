package com.advanceweb.afc.jb.data.jobseeker.subscription;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.data.common.helpers.JobSeekerSubscriptionsConversionHelper;
import com.advanceweb.afc.jb.data.entities.MerUserAlerts;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@Repository("jobSeekerSubscriptionsDAO")
public class JobSeekerSubscriptionsDAOImpl implements JobSeekerSubscriptionsDAO {
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private JobSeekerSubscriptionsConversionHelper jsSubscriptionHelper;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactoryMerionTracker);
	}

	/**
	 * save subscription
	 */

	@Override
	@Transactional(readOnly = false)
	public boolean saveJobSeekerSubscription(List<JobSeekerSubscriptionsDTO> listSubsDTO, long userId) {
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
	public List<JobSeekerSubscriptionsDTO> getCurrentSubscriptions(long userId) {
		
		List<JobSeekerSubscriptionsDTO> listSubscriptiosns = null;
		try {
			List<MerUserAlerts> listSubsAlerts= hibernateTemplate.find("from MerUserAlerts m where m.userid="+userId);
			listSubscriptiosns = jsSubscriptionHelper.transformMerUserAlertsTojsSubsDTO(listSubsAlerts);
		} catch (DataAccessException e) {

			e.printStackTrace();
		}
		
		return listSubscriptiosns;
	}

}
