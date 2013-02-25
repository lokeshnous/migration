/**
 * 
 */
package com.advanceweb.afc.jb.jobseeker.service;

import java.util.List;

import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This method is called for create cover letter etc.
 * 
 * @author kartikm
 * @version V.0.1
 * 
 */
public interface CoverLetterService {

	/**
	 * @param rclDTO
	 * @return boolean
	 */
	boolean coverLetterSaveByjobSeeker(ResCoverLetterDTO rclDTO);

	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 */
	boolean findActiveStatus(int userId, int status);

	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 */
	boolean findFirstActiveStatus(int userId, int status);

	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean value.
	 */
	boolean findNameActiveStatus(int userId, String name, int coverLetterId);

	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	boolean findDuplicateActiveStatus(int userId, int status);

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
	 * @param userId
	 *            userId
	 * @return list ResCoverLetterDTO
	 * @throws JobBoardServiceException
	 *             JobBoardServiceException
	 */

	List<ResCoverLetterDTO> getJobOwnerList(int userId)
			throws JobBoardServiceException;

	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	boolean isDelete(int userId, int coverLetterId);

	/**
	 * 
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	boolean isupDateCover(int userId, int coverLetterId);

	/**
	 * 
	 * @param coverletterId
	 * @return
	 */
	ResCoverLetterDTO getCoverList(int coverletterId);

	/**
	 * Fetch public cover letter.
	 *
	 * @param jobSeekerId the job seeker id
	 * @param coverLetterId the cover letter id
	 * @return the res cover letter dto
	 */
	ResCoverLetterDTO fetchPublicCoverLetter(long jobSeekerId,String coverLetterId);

}
