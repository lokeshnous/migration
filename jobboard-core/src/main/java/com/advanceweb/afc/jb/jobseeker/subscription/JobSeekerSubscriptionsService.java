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
@Service("jobSeekerSubscriptionsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSeekerSubscriptionsService implements JobSeekerSubscriptions {

	@Autowired
	private JobSeekerSubscriptionsDAO jobSeekerSubscriptionsDAO;

	/**
	 * save subscription
	 * @return 
	 */
	@Override
	public boolean saveJobSeekerSubscription(List<JobSeekerSubscriptionsDTO> listSubsDTO, long userId) {
		return jobSeekerSubscriptionsDAO.saveJobSeekerSubscription(listSubsDTO, userId);
	}
	
	/**
	 * To get current subscriptions of the user
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<JobSeekerSubscriptionsDTO> getCurrentSubscriptions(long userId) {

		return jobSeekerSubscriptionsDAO.getCurrentSubscriptions(userId);
	}

}
