package com.advanceweb.afc.jb.jobseeker.subscription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.data.jobseeker.activity.JobSeekerActivityDAO;
import com.advanceweb.afc.jb.data.jobseeker.subscription.JobSeekerSubscriptionsDAO;

/**
 * 
 * @author sharadk
 * @since 10 July 2012
 */
@Service("subscriptionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSeekerSubscriptionsService implements JobSeekerSubscriptions {

	@Autowired
	private JobSeekerSubscriptionsDAO jobSeekerSubscriptionsDAO;

	/**
	 * save subscription
	 */
	@Override
	public void saveJobSeekerSubscription(Long id) {
		jobSeekerSubscriptionsDAO.saveJobSeekerSubscription(id);
	}

}
