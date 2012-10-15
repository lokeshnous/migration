package com.advanceweb.afc.jb.resume.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.ResumeDTO;

/**
 * This class has been created as a service interface for getting the resume 
 * list from the DB.
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 15th October 2012
 */

public interface ResumeSearchDAO {


	/**
	 * This method is used to get the resume details from the DB
	 * @param String searchString
	 * @return List<ResumeDTO>
	 */
	List<ResumeDTO> getResumeSearchDetails(String searchString);
	
}
