package com.advanceweb.afc.jb.data.jobseeker.subscription;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.data.common.helpers.JobSeekerActivityConversionHelper;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.MerUserAlerts;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@Repository("jobSeekerSubscriptionsDAO")
public class JobSeekerSubscriptionsDAOImpl implements JobSeekerSubscriptionsDAO {

	@Autowired
	private SessionFactory sessionFactoryMerionTracker;

	/**
	 * save subscription
	 */

	@Override
	@Transactional(readOnly = false)
	public void saveJobSeekerSubscription(Long id) {

		MerUserAlerts merUserAlerts = new MerUserAlerts();
		merUserAlerts.setUserId(id.intValue());
		merUserAlerts.setEmail("sharad@nous.com");
		sessionFactoryMerionTracker.getCurrentSession().saveOrUpdate(
				merUserAlerts);
	}

}
