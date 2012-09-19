/**
 * 
 */
package com.advanceweb.afc.jb.jobseeker.service;

import java.util.List;


import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This method is called for create cover letter
 * etc.
 * @author kartikm
 * @version V.0.1
 *
 */
public interface CoverLetterService {

	/**
	 * @param rclDTO
	 * @return boolean
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
	 * @return boolean
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
	 * @return list ResCoverLetterDTO
	 * @throws JobBoardServiceException JobBoardServiceException
	 */
	
	public List<ResCoverLetterDTO> getJobOwnerList( int userId) throws JobBoardServiceException;
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	public boolean isDelete(int userId,int coverLetterId);
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	public boolean isupDateCover(int userId,int coverLetterId);
	/**
	 * 
	 * @param coverletterId
	 * @return
	 */
	ResCoverLetterDTO getCoverList(int coverletterId);
	
}
