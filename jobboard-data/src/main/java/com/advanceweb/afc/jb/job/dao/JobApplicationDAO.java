/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.dao;

import com.advanceweb.afc.jb.common.JobApplicationDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This interface defines all the DAO operations related to applying job for Anonymous User
 */
public interface JobApplicationDAO {
	
	/**
	 * Apply job anonymous user.
	 *
	 * @param dto the dto
	 * @return true, if successful
	 */
	boolean applyJobAnonymousUser(JobApplicationDTO dto);
}
