package com.advanceweb.afc.jb.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.jobseeker.service.CoverLetterService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.dao.UserSubscriptionsDAO;

/**
 * 
 * @author sharadk
 * @since 10 July 2012
 */
@Service("userSubService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserSubscriptionServiceImpl implements UserSubscriptionService,
		CoverLetterService {
	private static final Logger LOGGER = Logger
			.getLogger("UserSubscriptionServiceImpl.class");
	@Autowired
	private UserSubscriptionsDAO subscriptionsDAO;

	/**
	 * save subscription
	 * 
	 * @return
	 */
	@Override
	public boolean saveJobSeekerSubscription(
			List<UserSubscriptionsDTO> listSubsDTO, int userId) {
		return subscriptionsDAO.saveJobSeekerSubscription(listSubsDTO, userId);
	}

	/**
	 * To get current subscriptions of the user
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<UserSubscriptionsDTO> getCurrentSubscriptions(int userId) {

		return subscriptionsDAO.getCurrentSubscriptions(userId);
	}

	/**
	 * To Save the Cover letter of particular user
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean coverLetterSaveByjobSeeker(ResCoverLetterDTO rclDTO) {
		return subscriptionsDAO.coverLetterSaveByjobSeeker(rclDTO);
	}

	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean findActiveStatus(int userId, int status) {
		return subscriptionsDAO.findActiveStatus(userId, status);
	}

	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean findFirstActiveStatus(int userId, int status) {
		return subscriptionsDAO.findFirstActiveStatus(userId, status);
	}

	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean findDuplicateActiveStatus(int userId, int status) {
		return subscriptionsDAO.findDuplicateActiveStatus(userId, status);
	}

	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean findNameActiveStatus(int userId, String name) {
		return subscriptionsDAO.findNameActiveStatus(userId, name);
	}

	/**
	 * To Save the Cover letter of particular user
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean coverLetterUpdateByjobSeeker(ResCoverLetterDTO rclDTO) {
		return subscriptionsDAO.coverLetterUpdateByjobSeeker(rclDTO);
	}

	/**
	 * @param rclDTO
	 * @return boolean
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean coverLetterEditByjobSeeker(ResCoverLetterDTO rclDTO) {
		return subscriptionsDAO.coverLetterEditByjobSeeker(rclDTO);
	}

	@Override
	public List<ResCoverLetterDTO> getJobOwnerList(int userId)
			throws JobBoardServiceException {
		List<ResCoverLetterDTO> coverLetterDTOs = new ArrayList<ResCoverLetterDTO>();
		try {
			coverLetterDTOs = subscriptionsDAO.getJobOwnerList(userId);
		} catch (JobBoardDataException jdex) {
			throw new JobBoardServiceException(
					"Error while fetching the job owner..." + jdex);
		}
		return coverLetterDTOs;
	}

	/**
	 * To find out the status it is public or private
	 */
	@Override
	public boolean isDelete(int userId, int coverLetterId) {
		return subscriptionsDAO.isDelete(userId, coverLetterId);
	}

	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	@Override
	public boolean isupDateCover(int userId, int coverLetterId) {
		return subscriptionsDAO.isupDateCover(userId, coverLetterId);
	}

	/**
	 * 
	 */
	@Override
	public ResCoverLetterDTO getCoverList(int coverletterId) {
		ResCoverLetterDTO resDTO = new ResCoverLetterDTO();
		try {
			resDTO = subscriptionsDAO.getCoverList(coverletterId);

		} catch (Exception e) {
			LOGGER.info("Error for employee registration edit");
		}
		return resDTO;
	}

	@Override
	public ResCoverLetterDTO fetchPublicCoverLetter(long jobSeekerId) {
		return subscriptionsDAO.fetchPublicCoverLetter(jobSeekerId);
	}

}