/**
 * 
 */
package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This interface is used to handle the Resume Search Packages functionalities
 * @author anilm
 * @version 1.0
 * @since 15 Oct 2012
 */
public interface ResumePackageService {
	
	
	/**
	 * This method will fetch the all the Resume Search Packages Available 
	 * @return list of ResumePackageDTO
	 * @throws JobBoardServiceException
	 */
	List<ResumePackageDTO> showResumeSearchPackages() throws JobBoardServiceException;
}
