package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;


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
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 */
	public boolean findActiveStatus(int userId,int status);
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 * 
	 */
	public boolean findFirstActiveStatus(int userId,int status);
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 */
	public boolean findNameActiveStatus(int userId,String name);
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 */
	public boolean findDuplicateActiveStatus(int userId,int status);
	/**
	 * @param rclDTO
	 * @return boolean
	 */
	public boolean coverLetterUpdateByjobSeeker(ResCoverLetterDTO rclDTO);
	/**
	 * 
	 * @param userId userId
	 * @return List List
	 * @throws JobBoardDataException JobBoardDataException
	 */
	
	public List<ResCoverLetterDTO> getJobOwnerList( int userId) throws JobBoardDataException;
}
