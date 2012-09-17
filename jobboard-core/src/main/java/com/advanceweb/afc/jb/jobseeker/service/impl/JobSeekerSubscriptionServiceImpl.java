package com.advanceweb.afc.jb.jobseeker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerSubscriptionsDAO;
import com.advanceweb.afc.jb.jobseeker.service.CoverLetterService;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerSubscriptionService;

/**
 * 
 * @author sharadk
 * @since 10 July 2012
 */
@Service("jobSeekerSubscriptionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSeekerSubscriptionServiceImpl implements JobSeekerSubscriptionService,CoverLetterService {

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
	/**
	 * To Save the Cover letter of particular user
	 * @param userId
	 * @return
	 */
	@Override
	public boolean coverLetterSaveByjobSeeker(ResCoverLetterDTO rclDTO) {
		return jobSeekerSubscriptionsDAO.coverLetterSaveByjobSeeker(rclDTO);
	}
	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean findActiveStatus(int userId,int status) {
		return jobSeekerSubscriptionsDAO.findActiveStatus(userId,status);
	}
}
