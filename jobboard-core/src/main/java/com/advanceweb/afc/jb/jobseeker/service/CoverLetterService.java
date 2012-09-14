/**
 * 
 */
package com.advanceweb.afc.jb.jobseeker.service;

import com.advanceweb.afc.jb.common.ResCoverLetterDTO;;

/**
 * @author kartikm
 *
 */
public interface CoverLetterService {

	/**
	 * @param rclDTO
	 * @return boolean
	 */
	public boolean coverLetterSaveByjobSeeker(ResCoverLetterDTO rclDTO);
}
