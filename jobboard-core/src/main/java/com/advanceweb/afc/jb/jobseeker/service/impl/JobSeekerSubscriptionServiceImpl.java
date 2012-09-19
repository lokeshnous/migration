package com.advanceweb.afc.jb.jobseeker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerSubscriptionsDAO;
import com.advanceweb.afc.jb.jobseeker.service.CoverLetterService;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerSubscriptionService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * 
 * @author sharadk
 * @since 10 July 2012
 */
@Service("jobSeekerSubscriptionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSeekerSubscriptionServiceImpl implements JobSeekerSubscriptionService,CoverLetterService {
	private static final Logger LOGGER = Logger
			.getLogger("JobSeekerSubscriptionServiceImpl.class");
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
	
	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean findFirstActiveStatus(int userId,int status) {
		return jobSeekerSubscriptionsDAO.findFirstActiveStatus(userId,status);
	}
	
	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean findDuplicateActiveStatus(int userId,int status) {
		return jobSeekerSubscriptionsDAO.findDuplicateActiveStatus(userId,status);
	}
	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean findNameActiveStatus(int userId,String name) {
		return jobSeekerSubscriptionsDAO.findNameActiveStatus(userId,name);
	}
	
	/**
	 * To Save the Cover letter of particular user
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean coverLetterUpdateByjobSeeker(ResCoverLetterDTO rclDTO) {
		return jobSeekerSubscriptionsDAO.coverLetterUpdateByjobSeeker(rclDTO);
	}
	
	@Override
	public List<ResCoverLetterDTO> getJobOwnerList(int userId) throws JobBoardServiceException {
		List<ResCoverLetterDTO> manageAccessPermissionDTOs = new ArrayList<ResCoverLetterDTO>();
		try {
			manageAccessPermissionDTOs = jobSeekerSubscriptionsDAO.getJobOwnerList(userId);
		} catch (JobBoardDataException jdex) {			
			throw new JobBoardServiceException("Error while fetching the job owner..." + jdex);
		}
		return manageAccessPermissionDTOs;
	}
	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean isDelete(int userId,int coverLetterId) {
		return jobSeekerSubscriptionsDAO.isDelete(userId,coverLetterId);
	}
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	@Override
	public boolean isupDateCover(int userId,int coverLetterId){
		return jobSeekerSubscriptionsDAO.isupDateCover(userId,coverLetterId);
	}
	/**
	 * 
	 */
	@Override
	public ResCoverLetterDTO getCoverList(int coverletterId){
		ResCoverLetterDTO resDTO=new ResCoverLetterDTO();
		try {
			resDTO=jobSeekerSubscriptionsDAO.getCoverList(coverletterId);
			
		}catch (Exception e) {
			LOGGER.info("Error for employee registration edit");
		}
		return resDTO;
	}
	
	
	
}
