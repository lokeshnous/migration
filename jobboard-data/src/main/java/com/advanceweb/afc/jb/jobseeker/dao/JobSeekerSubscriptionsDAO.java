package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;

/**
 * Created JobSeekerSubscriptions DAO
 * 
 * @author sharadk
 * 
 */
public interface JobSeekerSubscriptionsDAO {
	/**
	 * 
	 * @param jobSeekerSubscriptionsDTO
	 * @return
	 */
	boolean saveJobSeekerSubscription(List<JobSeekerSubscriptionsDTO> listSubsDTO, int userId);
	
	/**
	 * To get current subscriptions of the user
	 * @param userId
	 * @return
	 */
	List<JobSeekerSubscriptionsDTO> getCurrentSubscriptions(int userId);
	/**
	 * 
	 * @param rclDTO.
	 * @return boolean.
	 */
	public boolean coverLetterSaveByjobSeeker(ResCoverLetterDTO rclDTO);
}
