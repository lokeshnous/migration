/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.service.impl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobApplicationDTO;
import com.advanceweb.afc.jb.job.service.JobApplicationService;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a implementation class of the AnonymousUserJobApply interface and implement all the service
 */
@Service
public class JobApplicationServiceImpl implements JobApplicationService {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JobApplicationServiceImpl.class);


/*	@Autowired 
	AnonymousUserJobApplyDAO anonymousUserJobApplyDAO;
*/	
	/**
	   @Author :Prince Mathew
	   @Purpose:this method will call the DAO layer Method for apply a job for Anonymous User
	   @Created:Jul 12, 2012
	   @Param  :Object of AnonymousUserJobApplyDTO
	   @Return :boolean value
	 * @see com.advanceweb.afc.jb.job.service.JobApplicationService#applyJobAnonymousUser(com.advanceweb.afc.jb.common.JobApplicationDTO)
	 */
	public boolean applyJobAnonymousUser(JobApplicationDTO dto){
		boolean result=false;
		try {
			//result=anonymousUserJobApplyDAO.applyJobAnonymousUser(dto);
			return result;
		} catch (Exception e) {
			LOGGER.error("Error in applyJobAnonymousUser:",e);
		}
		return result;
	}
}
