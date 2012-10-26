/**
 * 
 */
package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

/**
 * This interface used to handle the Resume Search Packages functionalities
 * @author anilm
 * @version 1.0
 * @since 15 Oct 2012
 */
public interface ResumePackageDAO {
	
	/**
	 * This service will fetch all the Resume Search Packages
	 * @return list of ResumePackageDTO
	 * @throws JobBoardDataException
	 */
	List<ResumePackageDTO> showResumeSearchPackages() throws JobBoardDataException;
}
