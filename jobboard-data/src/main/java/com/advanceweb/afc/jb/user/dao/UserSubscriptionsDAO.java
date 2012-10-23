package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;


/**
 * Created JobSeekerSubscriptions DAO
 * 
 * @author sharadk
 * 
 */
public interface UserSubscriptionsDAO {
	/**
	 * 
	 * @param jobSeekerSubscriptionsDTO
	 * @return
	 */
	boolean saveJobSeekerSubscription(List<UserSubscriptionsDTO> listSubsDTO, int userId);
	
	/**
	 * To get current subscriptions of the user
	 * @param userId
	 * @return
	 */
	List<UserSubscriptionsDTO> getCurrentSubscriptions(int userId);
	/**
	 * 
	 * @param rclDTO.
	 * @return boolean.
	 */
	boolean coverLetterSaveByjobSeeker(ResCoverLetterDTO rclDTO);
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 */
	boolean findActiveStatus(int userId,int status);
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 * 
	 */
	boolean findFirstActiveStatus(int userId,int status);
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 */
	boolean findNameActiveStatus(int userId,String name);
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 */
	boolean findDuplicateActiveStatus(int userId,int status);
	/**
	 * @param rclDTO
	 * @return boolean
	 */
	boolean coverLetterUpdateByjobSeeker(ResCoverLetterDTO rclDTO);
	/**
	 * @param rclDTO
	 * @return boolean
	 */
	boolean coverLetterEditByjobSeeker(ResCoverLetterDTO rclDTO);
	/**
	 * 
	 * @param userId userId
	 * @return List List
	 * @throws JobBoardDataException JobBoardDataException
	 */
	
	List<ResCoverLetterDTO> getJobOwnerList( int userId) throws JobBoardDataException;
	
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	boolean isDelete(int userId,int coverLetterId);
	
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	boolean isupDateCover(int userId,int coverLetterId);
	/**
	 * 
	 * @param coverletterId
	 * @return
	 */
	ResCoverLetterDTO getCoverList(int coverletterId);

	/**
	 * @param jobSeekerId
	 * @return
	 */
	ResCoverLetterDTO fetchPublicCoverLetter(long jobSeekerId);
}