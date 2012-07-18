package com.advanceweb.afc.jb.jobseeker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerSubscriptionsDAO;

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
	public boolean saveJobSeekerSubscription(List<JobSeekerSubscriptionsDTO> listSubsDTO, int userId) {
		return jobSeekerSubscriptionsDAO.saveJobSeekerSubscription(listSubsDTO, userId);
	}
	
	/**
	 * To get current subscriptions of the user
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<JobSeekerSubscriptionsDTO> getCurrentSubscriptions(int userId) {

		return jobSeekerSubscriptionsDAO.getCurrentSubscriptions(userId);
	}

}
